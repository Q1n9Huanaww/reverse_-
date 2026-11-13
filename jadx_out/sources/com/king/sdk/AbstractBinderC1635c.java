package com.king.sdk;

import android.accounts.Account;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: renamed from: com.king.sdk.c */
/* JADX INFO: compiled from: I2FABB9840C76199A1E170A7C19698595.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1635c extends Binder implements InterfaceC1634b {
    /* JADX INFO: renamed from: a */
    public static InterfaceC1634b m3403a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
        if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof InterfaceC1634b)) {
            return (InterfaceC1634b) iInterfaceQueryLocalInterface;
        }
        return new C1636d(iBinder);
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1598968902) {
            parcel2.writeString("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
            return true;
        }
        switch (i) {
            case 1:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                String strA = mo3377a(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeString(strA);
                return true;
            case 2:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                int iA = mo3375a(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            case 3:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                int iB = mo3391b(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iB);
                return true;
            case 4:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                int iA2 = mo3374a();
                parcel2.writeNoException();
                parcel2.writeInt(iA2);
                return true;
            case 5:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3385a(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 6:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3384a(parcel.readString(), parcel.readStrongBinder(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 7:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3382a(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3380a(parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3386a(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                byte[] bArrB = mo3394b();
                parcel2.writeNoException();
                parcel2.writeByteArray(bArrB);
                return true;
            case 11:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3378a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 12:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                Intent intentA = mo3376a(parcel.readString());
                parcel2.writeNoException();
                if (intentA != null) {
                    parcel2.writeInt(1);
                    intentA.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 13:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3379a(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 14:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                IBinder iBinderB = mo3392b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(iBinderB);
                return true;
            case 15:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3396c(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 16:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                String strC = mo3395c();
                parcel2.writeNoException();
                parcel2.writeString(strC);
                return true;
            case 17:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                String strD = mo3397d();
                parcel2.writeNoException();
                parcel2.writeString(strD);
                return true;
            case 18:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3387a(parcel.createStringArray(), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 19:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                int iE = mo3399e();
                parcel2.writeNoException();
                parcel2.writeInt(iE);
                return true;
            case 20:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                String strF = mo3401f();
                parcel2.writeNoException();
                parcel2.writeString(strF);
                return true;
            case 21:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                boolean zD = mo3398d(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zD ? 1 : 0);
                return true;
            case 22:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3381a(AbstractBinderC1639g.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3393b(AbstractBinderC1639g.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 24:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3383a(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 25:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                String strG = mo3402g();
                parcel2.writeNoException();
                parcel2.writeString(strG);
                return true;
            case 26:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                mo3400e(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 27:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                boolean zA = mo3390a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(zA ? 1 : 0);
                return true;
            case 28:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                boolean zA2 = mo3388a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zA2 ? 1 : 0);
                return true;
            case 29:
                parcel.enforceInterface("com.king.sdk.I2FABB9840C76199A1E170A7C19698595");
                boolean zA3 = mo3389a(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray(), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(zA3 ? 1 : 0);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
