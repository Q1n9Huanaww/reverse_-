package com.umeng.commonsdk.statistics.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.a */
/* JADX INFO: compiled from: AdvertisingId.java */
/* JADX INFO: loaded from: classes.dex */
public class C1813a {

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.a$a */
    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        private final String f4498a;

        /* JADX INFO: renamed from: b */
        private final boolean f4499b;

        a(String str, boolean z) {
            this.f4498a = str;
            this.f4499b = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b */
        public String m4364b() {
            return this.f4498a;
        }

        /* JADX INFO: renamed from: a */
        public boolean m4365a() {
            return this.f4499b;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m4361a(Context context) {
        try {
            a aVarM4362b = m4362b(context);
            if (aVarM4362b == null) {
                return null;
            }
            return aVarM4362b.m4364b();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private static a m4362b(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, bVar, 1)) {
                try {
                    try {
                        c cVar = new c(bVar.m4366a());
                        a aVar = new a(cVar.m4367a(), cVar.m4368a(true));
                        context.unbindService(bVar);
                        return aVar;
                    } catch (Exception e) {
                        throw e;
                    }
                } catch (Throwable th) {
                    context.unbindService(bVar);
                    throw th;
                }
            }
            throw new IOException("Google Play connection failed");
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.a$b */
    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class b implements ServiceConnection {

        /* JADX INFO: renamed from: a */
        boolean f4500a;

        /* JADX INFO: renamed from: b */
        private final LinkedBlockingQueue<IBinder> f4501b;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private b() {
            this.f4500a = false;
            this.f4501b = new LinkedBlockingQueue<>(1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f4501b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        /* JADX INFO: renamed from: a */
        public IBinder m4366a() throws InterruptedException {
            if (this.f4500a) {
                throw new IllegalStateException();
            }
            this.f4500a = true;
            return this.f4501b.take();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.a$c */
    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class c implements IInterface {

        /* JADX INFO: renamed from: a */
        private IBinder f4502a;

        public c(IBinder iBinder) {
            this.f4502a = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f4502a;
        }

        /* JADX INFO: renamed from: a */
        public String m4367a() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f4502a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        /* JADX INFO: renamed from: a */
        public boolean m4368a(boolean z) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                parcelObtain.writeInt(z ? 1 : 0);
                this.f4502a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }
}
