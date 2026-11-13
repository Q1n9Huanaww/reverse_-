package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: renamed from: com.king.sdk.d */
/* JADX INFO: compiled from: I2FABB9840C76199A1E170A7C19698595.java */
/* JADX INFO: loaded from: classes.dex */
final class C1636d implements InterfaceC1634b {

    /* JADX INFO: renamed from: a */
    private IBinder f3584a;

    C1636d(IBinder iBinder) {
        this.f3584a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f3584a;
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final String mo3377a(Account account) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                parcelObtain.writeInt(1);
                account.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f3584a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final int mo3375a(Account account, String str, String str2, String str3) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                parcelObtain.writeInt(1);
                account.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeString(str3);
            this.f3584a.transact(2, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: b */
    public final int mo3391b(Account account, String str, String str2, String str3) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (account != null) {
                parcelObtain.writeInt(1);
                account.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeString(str3);
            this.f3584a.transact(3, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final int mo3374a() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(4, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3385a(String str, String str2, int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeInt(i);
            this.f3584a.transact(5, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3384a(String str, IBinder iBinder, int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeStrongBinder(iBinder);
            parcelObtain.writeInt(i);
            this.f3584a.transact(6, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3382a(String str, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(i);
            parcelObtain.writeInt(i2);
            this.f3584a.transact(7, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3380a(ComponentName componentName, int i, int i2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            if (componentName != null) {
                parcelObtain.writeInt(1);
                componentName.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            parcelObtain.writeInt(i);
            parcelObtain.writeInt(i2);
            this.f3584a.transact(8, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3386a(String str, boolean z) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(z ? 1 : 0);
            this.f3584a.transact(9, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: b */
    public final byte[] mo3394b() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(10, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.createByteArray();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3378a(int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeInt(i);
            this.f3584a.transact(11, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final Intent mo3376a(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            this.f3584a.transact(12, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcelObtain2) : null;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3379a(int i, int i2, String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeInt(i);
            parcelObtain.writeInt(i2);
            parcelObtain.writeString(str);
            this.f3584a.transact(13, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: b */
    public final IBinder mo3392b(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            this.f3584a.transact(14, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readStrongBinder();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: c */
    public final void mo3396c(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            this.f3584a.transact(15, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: c */
    public final String mo3395c() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(16, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: d */
    public final String mo3397d() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(17, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3387a(String[] strArr, int i, String str, String str2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeStringArray(strArr);
            parcelObtain.writeInt(i);
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            this.f3584a.transact(18, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: e */
    public final int mo3399e() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(19, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: f */
    public final String mo3401f() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(20, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: d */
    public final boolean mo3398d(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            this.f3584a.transact(21, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3381a(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeStrongBinder(idfee16b42c8b2890d8ff2860af5562b1 != null ? idfee16b42c8b2890d8ff2860af5562b1.asBinder() : null);
            this.f3584a.transact(22, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: b */
    public final void mo3393b(IDFEE16B42C8B2890D8FF2860AF5562B1 idfee16b42c8b2890d8ff2860af5562b1) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeStrongBinder(idfee16b42c8b2890d8ff2860af5562b1 != null ? idfee16b42c8b2890d8ff2860af5562b1.asBinder() : null);
            this.f3584a.transact(23, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final void mo3383a(String str, int i, String str2, Notification notification) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(i);
            parcelObtain.writeString(str2);
            if (notification != null) {
                parcelObtain.writeInt(1);
                notification.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f3584a.transact(24, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: g */
    public final String mo3402g() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            this.f3584a.transact(25, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readString();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: e */
    public final void mo3400e(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            this.f3584a.transact(26, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final boolean mo3390a(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeString(str3);
            if (pendingIntent != null) {
                parcelObtain.writeInt(1);
                pendingIntent.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            if (pendingIntent2 != null) {
                parcelObtain.writeInt(1);
                pendingIntent2.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f3584a.transact(27, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final boolean mo3388a(String str, String str2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            this.f3584a.transact(28, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.king.sdk.InterfaceC1634b
    /* JADX INFO: renamed from: a */
    public final boolean mo3389a(String str, String str2, int i, byte[] bArr, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            parcelObtain.writeString(str);
            parcelObtain.writeString(str2);
            parcelObtain.writeInt(i);
            parcelObtain.writeByteArray(bArr);
            if (pendingIntent != null) {
                parcelObtain.writeInt(1);
                pendingIntent.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            if (pendingIntent2 != null) {
                parcelObtain.writeInt(1);
                pendingIntent2.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f3584a.transact(29, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
