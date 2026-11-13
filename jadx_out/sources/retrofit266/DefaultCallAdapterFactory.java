package retrofit266;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp366.Request;
import okio66.Timeout;

/* JADX INFO: loaded from: classes2.dex */
final class DefaultCallAdapterFactory extends CallAdapter.Factory {

    @Nullable
    private final Executor callbackExecutor;

    DefaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
        this.callbackExecutor = callbackExecutor;
    }

    @Override // retrofit266.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Call.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        final Type responseType = Utils.getParameterUpperBound(0, (ParameterizedType) returnType);
        final Executor executor = Utils.isAnnotationPresent(annotations, SkipCallbackExecutor.class) ? null : this.callbackExecutor;
        return new CallAdapter<Object, Call<?>>() { // from class: retrofit266.DefaultCallAdapterFactory.1
            @Override // retrofit266.CallAdapter
            public Type responseType() {
                return responseType;
            }

            @Override // retrofit266.CallAdapter
            public Call<?> adapt(Call<Object> call) {
                return executor == null ? call : new ExecutorCallbackCall(executor, call);
            }
        };
    }

    static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor callbackExecutor, Call<T> delegate) {
            this.callbackExecutor = callbackExecutor;
            this.delegate = delegate;
        }

        @Override // retrofit266.Call
        public void enqueue(Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            this.delegate.enqueue(new C22871(callback));
        }

        /* JADX INFO: renamed from: retrofit266.DefaultCallAdapterFactory$ExecutorCallbackCall$1 */
        class C22871 implements Callback<T> {
            final /* synthetic */ Callback val$callback;

            C22871(Callback callback) {
                this.val$callback = callback;
            }

            @Override // retrofit266.Callback
            public void onResponse(Call<T> call, final Response<T> response) {
                Executor executor = ExecutorCallbackCall.this.callbackExecutor;
                final Callback callback = this.val$callback;
                executor.execute(new Runnable() { // from class: retrofit266.DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m5168xddacc936(callback, response);
                    }
                });
            }

            /* JADX INFO: renamed from: lambda$onResponse$0$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1 */
            /* synthetic */ void m5168xddacc936(Callback callback, Response response) {
                if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                    callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                } else {
                    callback.onResponse(ExecutorCallbackCall.this, response);
                }
            }

            /* JADX INFO: renamed from: lambda$onFailure$1$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1 */
            /* synthetic */ void m5167x714e864(Callback callback, Throwable t) {
                callback.onFailure(ExecutorCallbackCall.this, t);
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<T> call, final Throwable t) {
                Executor executor = ExecutorCallbackCall.this.callbackExecutor;
                final Callback callback = this.val$callback;
                executor.execute(new Runnable() { // from class: retrofit266.DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m5167x714e864(callback, t);
                    }
                });
            }
        }

        @Override // retrofit266.Call
        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        @Override // retrofit266.Call
        public Response<T> execute() throws IOException {
            return this.delegate.execute();
        }

        @Override // retrofit266.Call
        public void cancel() {
            this.delegate.cancel();
        }

        @Override // retrofit266.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override // retrofit266.Call
        public Call<T> clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
        }

        @Override // retrofit266.Call
        public Request request() {
            return this.delegate.request();
        }

        @Override // retrofit266.Call
        public Timeout timeout() {
            return this.delegate.timeout();
        }
    }
}
