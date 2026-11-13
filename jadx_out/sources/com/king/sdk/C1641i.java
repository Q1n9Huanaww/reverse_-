package com.king.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.king.sdk.p075a.C1633a;

/* JADX INFO: renamed from: com.king.sdk.i */
/* JADX INFO: compiled from: KingService.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1641i {

    /* JADX INFO: renamed from: b */
    private static C1641i f3586b;

    /* JADX INFO: renamed from: a */
    private InterfaceC1634b f3587a;

    private C1641i(InterfaceC1634b interfaceC1634b) {
        this.f3587a = interfaceC1634b;
    }

    /* JADX INFO: renamed from: a */
    public final void m3405a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        try {
            this.f3587a.mo3381a(idfee16b42c8b2890d8ff2860af5562b1);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m3406a(KingListener kingListener) {
        try {
            this.f3587a.mo3393b(kingListener);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m3407a(String[] strArr, int i, String str, String str2) {
        try {
            this.f3587a.mo3387a(strArr, i, str, str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1641i m3404a(Context context) {
        InterfaceC1634b interfaceC1634bM3403a;
        if (f3586b != null) {
            return f3586b;
        }
        InterfaceC1634b interfaceC1634bM3372a = ServiceC1632a.m3372a();
        if (interfaceC1634bM3372a != null) {
            f3586b = new C1641i(interfaceC1634bM3372a);
        } else {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("GET_KING_SERVICE"));
            if (intentRegisterReceiver != null && (interfaceC1634bM3403a = AbstractBinderC1635c.m3403a(C1633a.m3373a(intentRegisterReceiver, "GET_KING_SERVICE"))) != null) {
                f3586b = new C1641i(interfaceC1634bM3403a);
            }
        }
        return f3586b;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m3409a(String str, String str2) {
        try {
            return this.f3587a.mo3390a(str, null, str2, null, null);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m3408a(String str, int i, byte[] bArr) {
        try {
            return this.f3587a.mo3389a(str, null, i, bArr, null, null);
        } catch (Exception unused) {
            return false;
        }
    }
}
