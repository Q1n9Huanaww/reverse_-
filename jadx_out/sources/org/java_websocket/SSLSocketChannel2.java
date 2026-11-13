package org.java_websocket;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.java_websocket.interfaces.ISSLChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class SSLSocketChannel2 implements ByteChannel, WrappedByteChannel, ISSLChannel {
    static final /* synthetic */ boolean $assertionsDisabled;
    protected static ByteBuffer emptybuffer;
    protected ExecutorService exec;
    protected ByteBuffer inCrypt;
    protected ByteBuffer inData;
    protected ByteBuffer outCrypt;
    protected SSLEngineResult readEngineResult;
    protected SelectionKey selectionKey;
    protected SocketChannel socketChannel;
    protected SSLEngine sslEngine;
    protected List<Future<?>> tasks;
    protected SSLEngineResult writeEngineResult;
    private final Logger log = LoggerFactory.getLogger((Class<?>) SSLSocketChannel2.class);
    protected int bufferallocations = 0;
    private byte[] saveCryptData = null;

    static {
        $assertionsDisabled = !SSLSocketChannel2.class.desiredAssertionStatus();
        emptybuffer = ByteBuffer.allocate(0);
    }

    public SSLSocketChannel2(SocketChannel channel, SSLEngine sslEngine, ExecutorService exec, SelectionKey key) throws IOException {
        if (channel == null || sslEngine == null || exec == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = channel;
        this.sslEngine = sslEngine;
        this.exec = exec;
        SSLEngineResult sSLEngineResult = new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, sslEngine.getHandshakeStatus(), 0, 0);
        this.writeEngineResult = sSLEngineResult;
        this.readEngineResult = sSLEngineResult;
        this.tasks = new ArrayList(3);
        if (key != null) {
            key.interestOps(key.interestOps() | 4);
            this.selectionKey = key;
        }
        createBuffers(sslEngine.getSession());
        this.socketChannel.write(wrap(emptybuffer));
        processHandshake();
    }

    private void consumeFutureUninterruptible(Future<?> f) {
        while (true) {
            try {
                try {
                    f.get();
                    return;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } catch (ExecutionException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0048 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0058 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0068 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0070 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x0087 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0092 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x009d A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00a7 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00bc A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c7 A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00cb A[Catch: all -> 0x0031, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000d, B:9:0x0015, B:10:0x001b, B:12:0x0021, B:14:0x002d, B:19:0x0034, B:21:0x003a, B:22:0x003e, B:24:0x0048, B:26:0x004e, B:33:0x0075, B:35:0x0087, B:28:0x0058, B:30:0x0068, B:31:0x006f, B:32:0x0070, B:36:0x0092, B:38:0x009d, B:43:0x00c7, B:45:0x00cb, B:47:0x00d5, B:48:0x00da, B:49:0x00db, B:40:0x00a7, B:42:0x00bc), top: B:51:0x0001 }] */
    private synchronized void processHandshake() throws IOException {
        int read;
        if (this.sslEngine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            if (!this.tasks.isEmpty()) {
                Iterator<Future<?>> it2 = this.tasks.iterator();
                while (it2.hasNext()) {
                    Future<?> f = it2.next();
                    if (f.isDone()) {
                        it2.remove();
                    } else if (isBlocking()) {
                        consumeFutureUninterruptible(f);
                    }
                }
                if (this.sslEngine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                    if (isBlocking() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                        this.inCrypt.compact();
                        read = this.socketChannel.read(this.inCrypt);
                        if (read == -1) {
                            throw new IOException("connection closed unexpectedly by peer");
                        }
                        this.inCrypt.flip();
                    }
                    this.inData.compact();
                    unwrap();
                    if (this.readEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                        createBuffers(this.sslEngine.getSession());
                    } else {
                        consumeDelegatedTasks();
                        if (!this.tasks.isEmpty()) {
                            this.socketChannel.write(wrap(emptybuffer));
                            if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                                createBuffers(this.sslEngine.getSession());
                            } else {
                                if ($assertionsDisabled) {
                                }
                                this.bufferallocations = 1;
                            }
                        } else {
                            this.socketChannel.write(wrap(emptybuffer));
                            if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                                createBuffers(this.sslEngine.getSession());
                            } else {
                                if ($assertionsDisabled) {
                                }
                                this.bufferallocations = 1;
                            }
                        }
                    }
                } else {
                    consumeDelegatedTasks();
                    if (!this.tasks.isEmpty() || this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
                        this.socketChannel.write(wrap(emptybuffer));
                        if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                            createBuffers(this.sslEngine.getSession());
                        } else {
                            if ($assertionsDisabled && this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
                                throw new AssertionError();
                            }
                            this.bufferallocations = 1;
                        }
                    } else {
                        if ($assertionsDisabled) {
                        }
                        this.bufferallocations = 1;
                    }
                }
            } else if (this.sslEngine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                if (isBlocking()) {
                    this.inCrypt.compact();
                    read = this.socketChannel.read(this.inCrypt);
                    if (read == -1) {
                        throw new IOException("connection closed unexpectedly by peer");
                    }
                    this.inCrypt.flip();
                } else {
                    this.inCrypt.compact();
                    read = this.socketChannel.read(this.inCrypt);
                    if (read == -1) {
                        throw new IOException("connection closed unexpectedly by peer");
                    }
                    this.inCrypt.flip();
                }
                this.inData.compact();
                unwrap();
                if (this.readEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                    createBuffers(this.sslEngine.getSession());
                } else {
                    consumeDelegatedTasks();
                    if (!this.tasks.isEmpty()) {
                        this.socketChannel.write(wrap(emptybuffer));
                        if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                            createBuffers(this.sslEngine.getSession());
                        } else {
                            if ($assertionsDisabled) {
                            }
                            this.bufferallocations = 1;
                        }
                    } else {
                        this.socketChannel.write(wrap(emptybuffer));
                        if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                            createBuffers(this.sslEngine.getSession());
                        } else {
                            if ($assertionsDisabled) {
                            }
                            this.bufferallocations = 1;
                        }
                    }
                }
            } else {
                consumeDelegatedTasks();
                if (!this.tasks.isEmpty()) {
                    this.socketChannel.write(wrap(emptybuffer));
                    if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                        createBuffers(this.sslEngine.getSession());
                    } else {
                        if ($assertionsDisabled) {
                        }
                        this.bufferallocations = 1;
                    }
                } else {
                    this.socketChannel.write(wrap(emptybuffer));
                    if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                        createBuffers(this.sslEngine.getSession());
                    } else {
                        if ($assertionsDisabled) {
                        }
                        this.bufferallocations = 1;
                    }
                }
            }
        }
    }

    private synchronized ByteBuffer wrap(ByteBuffer b) throws SSLException {
        this.outCrypt.compact();
        this.writeEngineResult = this.sslEngine.wrap(b, this.outCrypt);
        this.outCrypt.flip();
        return this.outCrypt;
    }

    private synchronized ByteBuffer unwrap() throws SSLException {
        if (this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED && this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            try {
                close();
            } catch (IOException e) {
            }
        }
        while (true) {
            int rem = this.inData.remaining();
            this.readEngineResult = this.sslEngine.unwrap(this.inCrypt, this.inData);
            if (this.readEngineResult.getStatus() != SSLEngineResult.Status.OK || (rem == this.inData.remaining() && this.sslEngine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
                break;
            }
        }
        this.inData.flip();
        return this.inData;
    }

    protected void consumeDelegatedTasks() {
        while (true) {
            Runnable task = this.sslEngine.getDelegatedTask();
            if (task != null) {
                this.tasks.add(this.exec.submit(task));
            } else {
                return;
            }
        }
    }

    protected void createBuffers(SSLSession session) {
        saveCryptedData();
        int netBufferMax = session.getPacketBufferSize();
        int appBufferMax = Math.max(session.getApplicationBufferSize(), netBufferMax);
        if (this.inData == null) {
            this.inData = ByteBuffer.allocate(appBufferMax);
            this.outCrypt = ByteBuffer.allocate(netBufferMax);
            this.inCrypt = ByteBuffer.allocate(netBufferMax);
        } else {
            if (this.inData.capacity() != appBufferMax) {
                this.inData = ByteBuffer.allocate(appBufferMax);
            }
            if (this.outCrypt.capacity() != netBufferMax) {
                this.outCrypt = ByteBuffer.allocate(netBufferMax);
            }
            if (this.inCrypt.capacity() != netBufferMax) {
                this.inCrypt = ByteBuffer.allocate(netBufferMax);
            }
        }
        if (this.inData.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inData.array(), this.inData.position(), this.inData.remaining()));
        }
        this.inData.rewind();
        this.inData.flip();
        if (this.inCrypt.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inCrypt.array(), this.inCrypt.position(), this.inCrypt.remaining()));
        }
        this.inCrypt.rewind();
        this.inCrypt.flip();
        this.outCrypt.rewind();
        this.outCrypt.flip();
        this.bufferallocations++;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer src) throws IOException {
        if (!isHandShakeComplete()) {
            processHandshake();
            return 0;
        }
        int iWrite = this.socketChannel.write(wrap(src));
        if (this.writeEngineResult.getStatus() == SSLEngineResult.Status.CLOSED) {
            throw new EOFException("Connection is closed");
        }
        return iWrite;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer dst) throws IOException {
        tryRestoreCryptedData();
        while (dst.hasRemaining()) {
            if (!isHandShakeComplete()) {
                if (isBlocking()) {
                    while (!isHandShakeComplete()) {
                        processHandshake();
                    }
                } else {
                    processHandshake();
                    if (!isHandShakeComplete()) {
                        return 0;
                    }
                }
            }
            int purged = readRemaining(dst);
            if (purged == 0) {
                if (!$assertionsDisabled && this.inData.position() != 0) {
                    throw new AssertionError();
                }
                this.inData.clear();
                if (!this.inCrypt.hasRemaining()) {
                    this.inCrypt.clear();
                } else {
                    this.inCrypt.compact();
                }
                if ((isBlocking() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) && this.socketChannel.read(this.inCrypt) == -1) {
                    return -1;
                }
                this.inCrypt.flip();
                unwrap();
                int transferred = transfereTo(this.inData, dst);
                if (transferred != 0 || !isBlocking()) {
                    return transferred;
                }
            } else {
                return purged;
            }
        }
        return 0;
    }

    private int readRemaining(ByteBuffer dst) throws SSLException {
        if (this.inData.hasRemaining()) {
            return transfereTo(this.inData, dst);
        }
        if (!this.inData.hasRemaining()) {
            this.inData.clear();
        }
        tryRestoreCryptedData();
        if (this.inCrypt.hasRemaining()) {
            unwrap();
            int amount = transfereTo(this.inData, dst);
            if (this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED) {
                return -1;
            }
            if (amount > 0) {
                return amount;
            }
        }
        return 0;
    }

    public boolean isConnected() {
        return this.socketChannel.isConnected();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sslEngine.closeOutbound();
        this.sslEngine.getSession().invalidate();
        if (this.socketChannel.isOpen()) {
            this.socketChannel.write(wrap(emptybuffer));
        }
        this.socketChannel.close();
    }

    private boolean isHandShakeComplete() {
        SSLEngineResult.HandshakeStatus status = this.sslEngine.getHandshakeStatus();
        return status == SSLEngineResult.HandshakeStatus.FINISHED || status == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    public SelectableChannel configureBlocking(boolean b) throws IOException {
        return this.socketChannel.configureBlocking(b);
    }

    public boolean connect(SocketAddress remote) throws IOException {
        return this.socketChannel.connect(remote);
    }

    public boolean finishConnect() throws IOException {
        return this.socketChannel.finishConnect();
    }

    public Socket socket() {
        return this.socketChannel.socket();
    }

    public boolean isInboundDone() {
        return this.sslEngine.isInboundDone();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedWrite() {
        return this.outCrypt.hasRemaining() || !isHandShakeComplete();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public void writeMore() throws IOException {
        write(this.outCrypt);
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedRead() {
        return (this.saveCryptData == null && !this.inData.hasRemaining() && (!this.inCrypt.hasRemaining() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW || this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED)) ? false : true;
    }

    @Override // org.java_websocket.WrappedByteChannel
    public int readMore(ByteBuffer dst) throws SSLException {
        return readRemaining(dst);
    }

    private int transfereTo(ByteBuffer from, ByteBuffer to) {
        int fremain = from.remaining();
        int toremain = to.remaining();
        if (fremain > toremain) {
            int limit = Math.min(fremain, toremain);
            for (int i = 0; i < limit; i++) {
                to.put(from.get());
            }
            return limit;
        }
        to.put(from);
        return fremain;
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    @Override // org.java_websocket.interfaces.ISSLChannel
    public SSLEngine getSSLEngine() {
        return this.sslEngine;
    }

    private void saveCryptedData() {
        if (this.inCrypt != null && this.inCrypt.remaining() > 0) {
            int saveCryptSize = this.inCrypt.remaining();
            this.saveCryptData = new byte[saveCryptSize];
            this.inCrypt.get(this.saveCryptData);
        }
    }

    private void tryRestoreCryptedData() {
        if (this.saveCryptData != null) {
            this.inCrypt.clear();
            this.inCrypt.put(this.saveCryptData);
            this.inCrypt.flip();
            this.saveCryptData = null;
        }
    }
}
