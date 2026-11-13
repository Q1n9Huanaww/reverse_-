package com.umeng.analytics.pro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.umeng.analytics.pro.f */
/* JADX INFO: compiled from: UMDBUtils.java */
/* JADX INFO: loaded from: classes.dex */
class C1712f {
    C1712f() {
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3550a(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        boolean z = false;
        if (str == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
            try {
                if (cursorRawQuery.moveToNext() && cursorRawQuery.getInt(0) > 0) {
                    z = true;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Exception unused) {
                cursor = cursorRawQuery;
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                th = th;
                cursor = cursorRawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    /* JADX INFO: renamed from: a */
    public static void m3549a(Context context) {
        if (context == null) {
            return;
        }
        try {
            File file = new File(C1709c.f3758a + context.getPackageName() + C1709c.f3759b + C1709c.f3760c);
            if (file != null && file.exists()) {
                file.delete();
            }
            C1710d.m3535a(context).m3541a();
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m3551b(Context context) {
        return C1709c.f3758a + context.getPackageName() + C1709c.f3759b;
    }

    /* JADX INFO: renamed from: a */
    public static String m3547a(List<String> list) {
        return TextUtils.join("!", list);
    }

    /* JADX INFO: renamed from: a */
    public static List<String> m3548a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    /* JADX INFO: renamed from: b */
    public static List<String> m3552b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return arrayList;
    }
}
