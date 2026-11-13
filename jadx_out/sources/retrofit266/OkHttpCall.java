package retrofit266;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp366.MediaType;
import okhttp366.Request;
import okhttp366.ResponseBody;
import okio66.Buffer;
import okio66.BufferedSource;
import okio66.ForwardingSource;
import okio66.Okio;
import okio66.Timeout;

/* JADX INFO: loaded from: classes2.dex */
final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private final okhttp366.Call.Factory callFactory;
    private volatile boolean canceled;

    @Nullable
    private Throwable creationFailure;
    private boolean executed;
    private final Object instance;

    @Nullable
    private okhttp366.Call rawCall;
    private final RequestFactory requestFactory;
    private final Converter<ResponseBody, T> responseConverter;

    OkHttpCall(RequestFactory requestFactory, Object instance, Object[] args, okhttp366.Call.Factory callFactory, Converter<ResponseBody, T> responseConverter) {
        this.requestFactory = requestFactory;
        this.instance = instance;
        this.args = args;
        this.callFactory = callFactory;
        this.responseConverter = responseConverter;
    }

    @Override // retrofit266.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.requestFactory, this.instance, this.args, this.callFactory, this.responseConverter);
    }

    @Override // retrofit266.Call
    public synchronized Request request() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create request.", e);
        }
        return getRawCall().request();
    }

    @Override // retrofit266.Call
    public synchronized Timeout timeout() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create call.", e);
        }
        return getRawCall().timeout();
    }

    private okhttp366.Call getRawCall() throws IOException {
        okhttp366.Call call = this.rawCall;
        if (call != null) {
            return call;
        }
        if (this.creationFailure != null) {
            if (this.creationFailure instanceof IOException) {
                throw ((IOException) this.creationFailure);
            }
            if (this.creationFailure instanceof RuntimeException) {
                throw ((RuntimeException) this.creationFailure);
            }
            throw ((Error) this.creationFailure);
        }
        try {
            okhttp366.Call callCreateRawCall = createRawCall();
            this.rawCall = callCreateRawCall;
            return callCreateRawCall;
        } catch (IOException | Error | RuntimeException e) {
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        }
    }

    @Override // retrofit266.Call
    public void enqueue(final Callback<T> callback) {
        okhttp366.Call call;
        Throwable failure;
        Objects.requireNonNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            call = this.rawCall;
            failure = this.creationFailure;
            if (call == null && failure == null) {
                try {
                    okhttp366.Call callCreateRawCall = createRawCall();
                    this.rawCall = callCreateRawCall;
                    call = callCreateRawCall;
                } catch (Throwable t) {
                    Utils.throwIfFatal(t);
                    this.creationFailure = t;
                    failure = t;
                }
            }
        }
        if (failure != null) {
            callback.onFailure(this, failure);
            return;
        }
        if (this.canceled) {
            call.cancel();
        }
        call.enqueue(new okhttp366.Callback() { // from class: retrofit266.OkHttpCall.1
            @Override // okhttp366.Callback
            public void onResponse(okhttp366.Call call2, okhttp366.Response rawResponse) {
                try {
                    Response<T> response = OkHttpCall.this.parseResponse(rawResponse);
                    try {
                        callback.onResponse(OkHttpCall.this, response);
                    } catch (Throwable t2) {
                        Utils.throwIfFatal(t2);
                        t2.printStackTrace();
                    }
                } catch (Throwable e) {
                    Utils.throwIfFatal(e);
                    callFailure(e);
                }
            }

            @Override // okhttp366.Callback
            public void onFailure(okhttp366.Call call2, IOException e) {
                callFailure(e);
            }

            private void callFailure(Throwable e) {
                try {
                    callback.onFailure(OkHttpCall.this, e);
                } catch (Throwable t2) {
                    Utils.throwIfFatal(t2);
                    t2.printStackTrace();
                }
            }
        });
    }

    @Override // retrofit266.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override // retrofit266.Call
    public Response<T> execute() throws IOException {
        okhttp366.Call call;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            call = getRawCall();
        }
        if (this.canceled) {
            call.cancel();
        }
        return parseResponse(call.execute());
    }

    private okhttp366.Call createRawCall() throws IOException {
        okhttp366.Call call = this.callFactory.newCall(this.requestFactory.create(this.instance, this.args));
        if (call == null) {
            throw new NullPointerException("Call.Factory returned null.");
        }
        return call;
    }

    Response<T> parseResponse(okhttp366.Response rawResponse) throws IOException {
        ResponseBody rawBody = rawResponse.body();
        okhttp366.Response rawResponse2 = rawResponse.newBuilder().body(new NoContentResponseBody(rawBody.contentType(), rawBody.contentLength())).build();
        int code = rawResponse2.code();
        if (code < 200 || code >= 300) {
            try {
                ResponseBody bufferedBody = Utils.buffer(rawBody);
                return Response.error(bufferedBody, rawResponse2);
            } finally {
                rawBody.close();
            }
        }
        if (code == 204 || code == 205) {
            rawBody.close();
            return Response.success((Object) null, rawResponse2);
        }
        ExceptionCatchingResponseBody catchingBody = new ExceptionCatchingResponseBody(rawBody);
        try {
            T body = this.responseConverter.convert(catchingBody);
            return Response.success(body, rawResponse2);
        } catch (RuntimeException e) {
            catchingBody.throwIfCaught();
            throw e;
        }
    }

    @Override // retrofit266.Call
    public void cancel() {
        okhttp366.Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    @Override // retrofit266.Call
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            if (this.rawCall == null || !this.rawCall.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;

        @Nullable
        private final MediaType contentType;

        NoContentResponseBody(@Nullable MediaType contentType, long contentLength) {
            this.contentType = contentType;
            this.contentLength = contentLength;
        }

        @Override // okhttp366.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp366.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // okhttp366.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;

        @Nullable
        IOException thrownException;

        ExceptionCatchingResponseBody(ResponseBody delegate) {
            this.delegate = delegate;
            this.delegateSource = Okio.buffer(new ForwardingSource(delegate.source()) { // from class: retrofit266.OkHttpCall.ExceptionCatchingResponseBody.1
                @Override // okio66.ForwardingSource, okio66.Source
                public long read(Buffer sink, long byteCount) throws IOException {
                    try {
                        return super.read(sink, byteCount);
                    } catch (IOException e) {
                        ExceptionCatchingResponseBody.this.thrownException = e;
                        throw e;
                    }
                }
            });
        }

        @Override // okhttp366.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override // okhttp366.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp366.ResponseBody
        public BufferedSource source() {
            return this.delegateSource;
        }

        @Override // okhttp366.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        void throwIfCaught() throws IOException {
            if (this.thrownException != null) {
                throw this.thrownException;
            }
        }
    }
}
