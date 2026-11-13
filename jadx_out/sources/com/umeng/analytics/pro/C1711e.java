package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.umeng.analytics.pro.e */
/* JADX INFO: compiled from: UMDBManager.java */
/* JADX INFO: loaded from: classes.dex */
class C1711e {

    /* JADX INFO: renamed from: b */
    private static SQLiteOpenHelper f3817b;

    /* JADX INFO: renamed from: d */
    private static Context f3818d;

    /* JADX INFO: renamed from: a */
    private AtomicInteger f3819a;

    /* JADX INFO: renamed from: c */
    private SQLiteDatabase f3820c;

    private C1711e() {
        this.f3819a = new AtomicInteger();
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.e$a */
    /* JADX INFO: compiled from: UMDBManager.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1711e f3821a = new C1711e();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1711e m3543a(Context context) {
        if (f3818d == null && context != null) {
            f3818d = context.getApplicationContext();
            f3817b = C1710d.m3535a(f3818d);
        }
        return a.f3821a;
    }

    /* JADX INFO: renamed from: a */
    public synchronized SQLiteDatabase m3544a() {
        if (this.f3819a.incrementAndGet() == 1) {
            this.f3820c = f3817b.getWritableDatabase();
        }
        return this.f3820c;
    }

    /* JADX INFO: renamed from: b */
    public synchronized void m3545b() {
        try {
            if (this.f3819a.decrementAndGet() == 0) {
                this.f3820c.close();
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
