package com.king.sdk;

import android.os.Binder;
import android.os.Parcel;

/* JADX INFO: renamed from: com.king.sdk.f */
/* JADX INFO: compiled from: IBA2159EDE8B5B1B06F70D35A9630B683.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC1638f extends Binder implements InterfaceC1637e {
    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            if (i == 1598968902) {
                parcel2.writeString("com.king.sdk.IBA2159EDE8B5B1B06F70D35A9630B683");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface("com.king.sdk.IBA2159EDE8B5B1B06F70D35A9630B683");
        parcel.readInt();
        AbstractBinderC1635c.m3403a(parcel.readStrongBinder());
        parcel2.writeNoException();
        return true;
    }
}
