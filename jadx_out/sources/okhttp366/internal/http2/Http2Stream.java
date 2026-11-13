package okhttp366.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp366.Headers;
import okhttp366.internal.Util;
import okio66.AsyncTimeout;
import okio66.Buffer;
import okio66.BufferedSource;
import okio66.Sink;
import okio66.Source;
import okio66.Timeout;

/* JADX INFO: loaded from: classes2.dex */
public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    final Http2Connection connection;

    @Nullable
    ErrorCode errorCode;

    @Nullable
    IOException errorException;
    private boolean hasResponseHeaders;

    /* JADX INFO: renamed from: id */
    final int f5046id;
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    private final Deque<Headers> headersQueue = new ArrayDeque();
    final StreamTimeout readTimeout = new StreamTimeout();
    final StreamTimeout writeTimeout = new StreamTimeout();

    Http2Stream(int id, Http2Connection connection, boolean outFinished, boolean inFinished, @Nullable Headers headers) {
        if (connection == null) {
            throw new NullPointerException("connection == null");
        }
        this.f5046id = id;
        this.connection = connection;
        this.bytesLeftInWriteWindow = connection.peerSettings.getInitialWindowSize();
        this.source = new FramingSource(connection.okHttpSettings.getInitialWindowSize());
        this.sink = new FramingSink();
        this.source.finished = inFinished;
        this.sink.finished = outFinished;
        if (headers != null) {
            this.headersQueue.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int getId() {
        return this.f5046id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        return ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed) && this.hasResponseHeaders) ? false : true;
    }

    public boolean isLocallyInitiated() {
        boolean streamIsClient = (this.f5046id & 1) == 1;
        return this.connection.client == streamIsClient;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized Headers takeHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty()) {
            try {
                try {
                    if (this.errorCode != null) {
                        break;
                    }
                    waitForIo();
                } catch (Throwable th) {
                    th = th;
                    this.readTimeout.exitAndThrowIfTimedOut();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!this.headersQueue.isEmpty()) {
        } else {
            if (this.errorException != null) {
                throw this.errorException;
            }
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public synchronized Headers trailers() throws IOException {
        if (this.errorCode != null) {
            if (this.errorException != null) {
                throw this.errorException;
            }
            throw new StreamResetException(this.errorCode);
        }
        if (!this.source.finished || !this.source.receiveBuffer.exhausted() || !this.source.readBuffer.exhausted()) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return this.source.trailers != null ? this.source.trailers : Util.EMPTY_HEADERS;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void writeHeaders(List<Header> responseHeaders, boolean outFinished, boolean flushHeaders) throws IOException {
        boolean z;
        if (Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (responseHeaders == null) {
            throw new NullPointerException("headers == null");
        }
        synchronized (this) {
            z = true;
            this.hasResponseHeaders = true;
            if (outFinished) {
                this.sink.finished = true;
            }
        }
        if (!flushHeaders) {
            synchronized (this.connection) {
                if (this.connection.bytesLeftInWriteWindow != 0) {
                    z = false;
                }
                flushHeaders = z;
            }
        }
        this.connection.writeHeaders(this.f5046id, outFinished, responseHeaders);
        if (flushHeaders) {
            this.connection.flush();
        }
    }

    public void enqueueTrailers(Headers trailers) {
        synchronized (this) {
            if (this.sink.finished) {
                throw new IllegalStateException("already finished");
            }
            if (trailers.size() == 0) {
                throw new IllegalArgumentException("trailers.size() == 0");
            }
            this.sink.trailers = trailers;
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode rstStatusCode, @Nullable IOException errorException) throws IOException {
        if (!closeInternal(rstStatusCode, errorException)) {
            return;
        }
        this.connection.writeSynReset(this.f5046id, rstStatusCode);
    }

    public void closeLater(ErrorCode errorCode) {
        if (!closeInternal(errorCode, null)) {
            return;
        }
        this.connection.writeSynResetLater(this.f5046id, errorCode);
    }

    private boolean closeInternal(ErrorCode errorCode, @Nullable IOException errorException) {
        if (Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            this.errorException = errorException;
            notifyAll();
            this.connection.removeStream(this.f5046id);
            return true;
        }
    }

    void receiveData(BufferedSource in, int length) throws IOException {
        if (Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        this.source.receive(in, length);
    }

    void receiveHeaders(Headers headers, boolean inFinished) {
        boolean open;
        if (Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (!this.hasResponseHeaders || !inFinished) {
                this.hasResponseHeaders = true;
                this.headersQueue.add(headers);
            } else {
                this.source.trailers = headers;
            }
            if (inFinished) {
                this.source.finished = true;
            }
            open = isOpen();
            notifyAll();
        }
        if (!open) {
            this.connection.removeStream(this.f5046id);
        }
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    private final class FramingSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private Headers trailers;
        private final Buffer receiveBuffer = new Buffer();
        private final Buffer readBuffer = new Buffer();

        FramingSource(long maxByteCount) {
            this.maxByteCount = maxByteCount;
        }

        /* JADX WARN: Code duplicated, block: B:34:0x00a3  */
        /* JADX WARN: Code duplicated, block: B:36:0x00a7 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:37:0x00a9 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x00aa  */
        @Override // okio66.Source
        public long read(Buffer sink, long byteCount) throws IOException {
            long readBytesDelivered;
            IOException errorExceptionToDeliver;
            IOException streamResetException;
            if (byteCount < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + byteCount);
            }
            while (true) {
                readBytesDelivered = -1;
                errorExceptionToDeliver = null;
                synchronized (Http2Stream.this) {
                    Http2Stream.this.readTimeout.enter();
                    try {
                        if (Http2Stream.this.errorCode != null) {
                            if (Http2Stream.this.errorException != null) {
                                streamResetException = Http2Stream.this.errorException;
                            } else {
                                streamResetException = new StreamResetException(Http2Stream.this.errorCode);
                            }
                            errorExceptionToDeliver = streamResetException;
                        }
                        if (this.closed) {
                            throw new IOException("stream closed");
                        }
                        if (this.readBuffer.size() > 0) {
                            readBytesDelivered = this.readBuffer.read(sink, Math.min(byteCount, this.readBuffer.size()));
                            Http2Stream.this.unacknowledgedBytesRead += readBytesDelivered;
                            if (errorExceptionToDeliver != null || Http2Stream.this.unacknowledgedBytesRead < Http2Stream.this.connection.okHttpSettings.getInitialWindowSize() / 2) {
                                break;
                                break;
                            }
                            Http2Stream.this.connection.writeWindowUpdateLater(Http2Stream.this.f5046id, Http2Stream.this.unacknowledgedBytesRead);
                            Http2Stream.this.unacknowledgedBytesRead = 0L;
                            break;
                        }
                        if (this.finished || errorExceptionToDeliver != null) {
                            break;
                        }
                        Http2Stream.this.waitForIo();
                        Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                    } catch (Throwable th) {
                        Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                if (readBytesDelivered != -1) {
                    updateConnectionFlowControl(readBytesDelivered);
                    return readBytesDelivered;
                }
                if (errorExceptionToDeliver == null) {
                    return -1L;
                }
                throw errorExceptionToDeliver;
            }
            Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
            if (readBytesDelivered != -1) {
                updateConnectionFlowControl(readBytesDelivered);
                return readBytesDelivered;
            }
            if (errorExceptionToDeliver == null) {
                return -1L;
            }
            throw errorExceptionToDeliver;
        }

        private void updateConnectionFlowControl(long read) {
            if (Thread.holdsLock(Http2Stream.this)) {
                throw new AssertionError();
            }
            Http2Stream.this.connection.updateConnectionFlowControl(read);
        }

        void receive(BufferedSource in, long byteCount) throws IOException {
            boolean finished;
            boolean wasEmpty;
            boolean flowControlError;
            if (Thread.holdsLock(Http2Stream.this)) {
                throw new AssertionError();
            }
            long bytesDiscarded = byteCount;
            while (bytesDiscarded > 0) {
                synchronized (Http2Stream.this) {
                    finished = this.finished;
                    wasEmpty = true;
                    flowControlError = this.readBuffer.size() + bytesDiscarded > this.maxByteCount;
                }
                if (flowControlError) {
                    in.skip(bytesDiscarded);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (finished) {
                    in.skip(bytesDiscarded);
                    return;
                }
                long read = in.read(this.receiveBuffer, bytesDiscarded);
                if (read == -1) {
                    throw new EOFException();
                }
                long byteCount2 = bytesDiscarded - read;
                long bytesDiscarded2 = 0;
                synchronized (Http2Stream.this) {
                    if (!this.closed) {
                        if (this.readBuffer.size() != 0) {
                            wasEmpty = false;
                        }
                        this.readBuffer.writeAll(this.receiveBuffer);
                        if (wasEmpty) {
                            Http2Stream.this.notifyAll();
                        }
                    } else {
                        bytesDiscarded2 = this.receiveBuffer.size();
                        this.receiveBuffer.clear();
                    }
                }
                if (bytesDiscarded2 > 0) {
                    updateConnectionFlowControl(bytesDiscarded2);
                }
                bytesDiscarded = byteCount2;
            }
        }

        @Override // okio66.Source
        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        @Override // okio66.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long bytesDiscarded;
            synchronized (Http2Stream.this) {
                this.closed = true;
                bytesDiscarded = this.readBuffer.size();
                this.readBuffer.clear();
                Http2Stream.this.notifyAll();
            }
            if (bytesDiscarded > 0) {
                updateConnectionFlowControl(bytesDiscarded);
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }
    }

    void cancelStreamIfNecessary() throws IOException {
        boolean cancel;
        boolean open;
        if (Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            cancel = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            open = isOpen();
        }
        if (cancel) {
            close(ErrorCode.CANCEL, null);
        } else if (!open) {
            this.connection.removeStream(this.f5046id);
        }
    }

    final class FramingSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final Buffer sendBuffer = new Buffer();
        private Headers trailers;

        FramingSink() {
        }

        @Override // okio66.Sink
        public void write(Buffer source, long byteCount) throws IOException {
            if (Thread.holdsLock(Http2Stream.this)) {
                throw new AssertionError();
            }
            this.sendBuffer.write(source, byteCount);
            while (this.sendBuffer.size() >= 16384) {
                emitFrame(false);
            }
        }

        /* JADX WARN: Code duplicated, block: B:24:0x0063  */
        private void emitFrame(boolean outFinishedOnLastFrame) throws IOException {
            long toWrite;
            boolean outFinished;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (Http2Stream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                    try {
                        Http2Stream.this.waitForIo();
                    } catch (Throwable th) {
                        Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                        throw th;
                    }
                }
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                toWrite = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                Http2Stream.this.bytesLeftInWriteWindow -= toWrite;
            }
            Http2Stream.this.writeTimeout.enter();
            if (outFinishedOnLastFrame) {
                try {
                    if (toWrite == this.sendBuffer.size()) {
                        outFinished = true;
                    } else {
                        outFinished = false;
                    }
                } finally {
                    Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                }
            } else {
                outFinished = false;
            }
            Http2Stream.this.connection.writeData(Http2Stream.this.f5046id, outFinished, this.sendBuffer, toWrite);
        }

        @Override // okio66.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (Thread.holdsLock(Http2Stream.this)) {
                throw new AssertionError();
            }
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override // okio66.Sink
        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        @Override // okio66.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (Thread.holdsLock(Http2Stream.this)) {
                throw new AssertionError();
            }
            synchronized (Http2Stream.this) {
                if (this.closed) {
                    return;
                }
                if (!Http2Stream.this.sink.finished) {
                    boolean hasData = this.sendBuffer.size() > 0;
                    boolean hasTrailers = this.trailers != null;
                    if (hasTrailers) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(false);
                        }
                        Http2Stream.this.connection.writeHeaders(Http2Stream.this.f5046id, true, Util.toHeaderBlock(this.trailers));
                    } else if (hasData) {
                        while (this.sendBuffer.size() > 0) {
                            emitFrame(true);
                        }
                    } else {
                        Http2Stream.this.connection.writeData(Http2Stream.this.f5046id, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.closed = true;
                }
                Http2Stream.this.connection.flush();
                Http2Stream.this.cancelStreamIfNecessary();
            }
        }
    }

    void addBytesToWriteWindow(long delta) {
        this.bytesLeftInWriteWindow += delta;
        if (delta > 0) {
            notifyAll();
        }
    }

    void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        }
        if (this.sink.finished) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            if (this.errorException == null) {
                throw new StreamResetException(this.errorCode);
            }
        }
    }

    void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    class StreamTimeout extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override // okio66.AsyncTimeout
        protected void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
            Http2Stream.this.connection.sendDegradedPingLater();
        }

        @Override // okio66.AsyncTimeout
        protected IOException newTimeoutException(IOException cause) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (cause != null) {
                socketTimeoutException.initCause(cause);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }
}
