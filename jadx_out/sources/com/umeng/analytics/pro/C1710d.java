package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/* JADX INFO: renamed from: com.umeng.analytics.pro.d */
/* JADX INFO: compiled from: UMDBCreater.java */
/* JADX INFO: loaded from: classes.dex */
class C1710d extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: b */
    private static Context f3814b;

    /* JADX INFO: renamed from: a */
    private String f3815a;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.d$a */
    /* JADX INFO: compiled from: UMDBCreater.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1710d f3816a = new C1710d(C1710d.f3814b, C1712f.m3551b(C1710d.f3814b), C1709c.f3760c, null, 1);

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1710d m3535a(Context context) {
        if (f3814b == null) {
            f3814b = context.getApplicationContext();
        }
        return a.f3816a;
    }

    private C1710d(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new C1707a(context, str), str2, cursorFactory, i);
    }

    private C1710d(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, TextUtils.isEmpty(str) ? C1709c.f3760c : str, cursorFactory, i);
        this.f3815a = null;
        m3541a();
    }

    /* JADX INFO: renamed from: a */
    public void m3541a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!C1712f.m3550a(C1709c.e.f3797a, writableDatabase)) {
                m3540d(writableDatabase);
            }
            if (!C1712f.m3550a(C1709c.d.f3788a, writableDatabase)) {
                m3539c(writableDatabase);
            }
            if (!C1712f.m3550a(C1709c.c.f3781a, writableDatabase)) {
                m3538b(writableDatabase);
            }
            if (C1712f.m3550a(C1709c.a.f3764a, writableDatabase)) {
                return;
            }
            m3536a(writableDatabase);
        } catch (Exception unused) {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                m3540d(sQLiteDatabase);
                m3539c(sQLiteDatabase);
                m3538b(sQLiteDatabase);
                m3536a(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable unused) {
                    }
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Throwable unused2) {
                    }
                }
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException unused3) {
            C1712f.m3549a(f3814b);
            if (sQLiteDatabase != null) {
            }
        } catch (Throwable unused4) {
            if (sQLiteDatabase == null) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3536a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f3815a = "create table if not exists __dp(id INTEGER primary key autoincrement, __id INTEGER, __ii TEXT, __ty INTEGER, __ve TEXT, __io TEXT)";
            sQLiteDatabase.execSQL(this.f3815a);
        } catch (SQLException unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3538b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f3815a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.f3815a);
        } catch (SQLException unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    private void m3539c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f3815a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER)";
            sQLiteDatabase.execSQL(this.f3815a);
        } catch (SQLException unused) {
        }
    }

    /* JADX INFO: renamed from: d */
    private void m3540d(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f3815a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT)";
            sQLiteDatabase.execSQL(this.f3815a);
        } catch (SQLException unused) {
        }
    }
}
