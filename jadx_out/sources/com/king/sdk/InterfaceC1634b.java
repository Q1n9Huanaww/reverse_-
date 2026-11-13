package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: renamed from: com.king.sdk.b */
/* JADX INFO: compiled from: I2FABB9840C76199A1E170A7C19698595.java */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC1634b extends IInterface {
    /* JADX INFO: renamed from: a */
    int mo3374a();

    /* JADX INFO: renamed from: a */
    int mo3375a(Account account, String str, String str2, String str3);

    /* JADX INFO: renamed from: a */
    Intent mo3376a(String str);

    /* JADX INFO: renamed from: a */
    String mo3377a(Account account);

    /* JADX INFO: renamed from: a */
    void mo3378a(int i);

    /* JADX INFO: renamed from: a */
    void mo3379a(int i, int i2, String str);

    /* JADX INFO: renamed from: a */
    void mo3380a(ComponentName componentName, int i, int i2);

    /* JADX INFO: renamed from: a */
    void mo3381a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1);

    /* JADX INFO: renamed from: a */
    void mo3382a(String str, int i, int i2);

    /* JADX INFO: renamed from: a */
    void mo3383a(String str, int i, String str2, Notification notification);

    /* JADX INFO: renamed from: a */
    void mo3384a(String str, IBinder iBinder, int i);

    /* JADX INFO: renamed from: a */
    void mo3385a(String str, String str2, int i);

    /* JADX INFO: renamed from: a */
    void mo3386a(String str, boolean z);

    /* JADX INFO: renamed from: a */
    void mo3387a(String[] strArr, int i, String str, String str2);

    /* JADX INFO: renamed from: a */
    boolean mo3388a(String str, String str2);

    /* JADX INFO: renamed from: a */
    boolean mo3389a(String str, String str2, int i, byte[] bArr, PendingIntent pendingIntent, PendingIntent pendingIntent2);

    /* JADX INFO: renamed from: a */
    boolean mo3390a(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2);

    /* JADX INFO: renamed from: b */
    int mo3391b(Account account, String str, String str2, String str3);

    /* JADX INFO: renamed from: b */
    IBinder mo3392b(String str);

    /* JADX INFO: renamed from: b */
    void mo3393b(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1);

    /* JADX INFO: renamed from: b */
    byte[] mo3394b();

    /* JADX INFO: renamed from: c */
    String mo3395c();

    /* JADX INFO: renamed from: c */
    void mo3396c(String str);

    /* JADX INFO: renamed from: d */
    String mo3397d();

    /* JADX INFO: renamed from: d */
    boolean mo3398d(String str);

    /* JADX INFO: renamed from: e */
    int mo3399e();

    /* JADX INFO: renamed from: e */
    void mo3400e(String str);

    /* JADX INFO: renamed from: f */
    String mo3401f();

    /* JADX INFO: renamed from: g */
    String mo3402g();
}
