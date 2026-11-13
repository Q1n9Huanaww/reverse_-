package com.p004b.p005a.p006a.p008b;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.b.a.a.b.e */
/* JADX INFO: compiled from: RouteException.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0499e extends RuntimeException {

    /* JADX INFO: renamed from: a */
    private static final Method f146a;

    /* JADX INFO: renamed from: b */
    private IOException f147b;

    static {
        Method declaredMethod;
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
            declaredMethod = null;
        }
        f146a = declaredMethod;
    }

    public C0499e(IOException iOException) {
        super(iOException);
        this.f147b = iOException;
    }

    /* JADX INFO: renamed from: a */
    public final IOException m80a() {
        return this.f147b;
    }

    /* JADX INFO: renamed from: a */
    public final void m81a(IOException iOException) {
        IOException iOException2 = this.f147b;
        if (f146a != null) {
            try {
                f146a.invoke(iOException, iOException2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
        this.f147b = iOException;
    }
}
