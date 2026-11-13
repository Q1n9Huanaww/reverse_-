package retrofit266;

import android.support.v4.app.NotificationCompat;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.reflect.Method;
import kotlin.KotlinNothingValueException;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: compiled from: KotlinExtensions.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@¢\u0006\u0002\u0010\u0004\u001a(\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003H\u0087@¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00060\u0003H\u0087@¢\u0006\u0004\b\u0007\u0010\u0004\u001a$\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\n\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\f\u001a\u0012\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0080@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"await", TessBaseAPI.f2987e, "", "Lretrofit266/Call;", "(Lretrofit266/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitNullable", "", "awaitUnit", "awaitResponse", "Lretrofit266/Response;", "create", "Lretrofit266/Retrofit;", "(Lretrofit266/Retrofit;)Ljava/lang/Object;", "suspendAndThrow", "", "", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrofit"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class KotlinExtensions {

    /* JADX INFO: renamed from: retrofit266.KotlinExtensions$suspendAndThrow$1 */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "retrofit266.KotlinExtensions", f = "KotlinExtensions.kt", i = {0}, l = {119}, m = "suspendAndThrow", n = {"$this$suspendAndThrow"}, s = {"L$0"})
    static final class C22901 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C22901(Continuation<? super C22901> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinExtensions.suspendAndThrow(null, (Continuation) this);
        }
    }

    public static final /* synthetic */ <T> T create(Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "<this>");
        Intrinsics.reifiedOperationMarker(4, TessBaseAPI.f2987e);
        T t = (T) retrofit.create(Object.class);
        Intrinsics.checkNotNullExpressionValue(t, "create(...)");
        return t;
    }

    public static final <T> Object await(final Call<T> call, Continuation<? super T> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuation continuation2 = cancellableContinuationImpl;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit266.KotlinExtensions$await$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((Throwable) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable it2) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit266.KotlinExtensions$await$2$2
            @Override // retrofit266.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    Object body = response.body();
                    if (body == null) {
                        Object objTag = call2.request().tag(Invocation.class);
                        Intrinsics.checkNotNull(objTag);
                        Invocation invocation = (Invocation) objTag;
                        Class<?> clsService = invocation.service();
                        Method method = invocation.method();
                        Throwable kotlinNullPointerException = new KotlinNullPointerException("Response from " + clsService.getName() + FilenameUtils.EXTENSION_SEPARATOR + method.getName() + " was null but response body type was declared as non-null");
                        Continuation continuation3 = continuation2;
                        Result.Companion companion = Result.Companion;
                        continuation3.resumeWith(Result.constructor-impl(ResultKt.createFailure(kotlinNullPointerException)));
                        return;
                    }
                    Continuation continuation4 = continuation2;
                    Result.Companion companion2 = Result.Companion;
                    continuation4.resumeWith(Result.constructor-impl(body));
                    return;
                }
                Continuation continuation5 = continuation2;
                Result.Companion companion3 = Result.Companion;
                continuation5.resumeWith(Result.constructor-impl(ResultKt.createFailure(new HttpException(response))));
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(t, "t");
                Continuation continuation3 = continuation2;
                Result.Companion companion = Result.Companion;
                continuation3.resumeWith(Result.constructor-impl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final <T> Object awaitNullable(final Call<T> call, Continuation<? super T> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuation continuation2 = cancellableContinuationImpl;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit266.KotlinExtensions$await$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((Throwable) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable it2) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit266.KotlinExtensions$await$4$2
            @Override // retrofit266.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    Continuation continuation3 = continuation2;
                    Result.Companion companion = Result.Companion;
                    continuation3.resumeWith(Result.constructor-impl(response.body()));
                } else {
                    Continuation continuation4 = continuation2;
                    Result.Companion companion2 = Result.Companion;
                    continuation4.resumeWith(Result.constructor-impl(ResultKt.createFailure(new HttpException(response))));
                }
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(t, "t");
                Continuation continuation3 = continuation2;
                Result.Companion companion = Result.Companion;
                continuation3.resumeWith(Result.constructor-impl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final Object awaitUnit(Call<Unit> call, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNull(call, "null cannot be cast to non-null type retrofit2.Call<kotlin.Unit?>");
        return awaitNullable(call, continuation);
    }

    public static final <T> Object awaitResponse(final Call<T> call, Continuation<? super Response<T>> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuation continuation2 = cancellableContinuationImpl;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit266.KotlinExtensions$awaitResponse$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                invoke((Throwable) p1);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable it2) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit266.KotlinExtensions$awaitResponse$2$2
            @Override // retrofit266.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(response, "response");
                Continuation continuation3 = continuation2;
                Result.Companion companion = Result.Companion;
                continuation3.resumeWith(Result.constructor-impl(response));
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(t, "t");
                Continuation continuation3 = continuation2;
                Result.Companion companion = Result.Companion;
                continuation3.resumeWith(Result.constructor-impl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object suspendAndThrow(final Throwable $this$suspendAndThrow, Continuation<?> continuation) throws KotlinNothingValueException {
        C22901 c22901;
        if (continuation instanceof C22901) {
            c22901 = (C22901) continuation;
            if ((c22901.label & Integer.MIN_VALUE) != 0) {
                c22901.label -= Integer.MIN_VALUE;
            } else {
                c22901 = new C22901(continuation);
            }
        } else {
            c22901 = new C22901(continuation);
        }
        C22901 c22902 = c22901;
        Object $result = c22902.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c22902.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c22902.L$0 = $this$suspendAndThrow;
                c22902.label = 1;
                final Continuation continuation2 = (Continuation) c22902;
                Dispatchers.getDefault().dispatch(continuation2.getContext(), new Runnable() { // from class: retrofit266.KotlinExtensions$suspendAndThrow$2$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Continuation continuationIntercepted = IntrinsicsKt.intercepted(continuation2);
                        Result.Companion companion = Result.Companion;
                        continuationIntercepted.resumeWith(Result.constructor-impl(ResultKt.createFailure($this$suspendAndThrow)));
                    }
                });
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (coroutine_suspended2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended((Continuation) c22902);
                }
                if (coroutine_suspended2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }
}
