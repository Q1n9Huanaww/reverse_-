package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.umeng.commonsdk.amap.UMAmapConfig;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.g */
/* JADX INFO: compiled from: UMStoreManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1713g {

    /* JADX INFO: renamed from: a */
    public static final int f3822a = 2049;

    /* JADX INFO: renamed from: b */
    public static final int f3823b = 2050;

    /* JADX INFO: renamed from: c */
    private static Context f3824c = null;

    /* JADX INFO: renamed from: d */
    private static String f3825d = null;

    /* JADX INFO: renamed from: e */
    private static final String f3826e = "umeng+";

    /* JADX INFO: renamed from: f */
    private static final String f3827f = "ek__id";

    /* JADX INFO: renamed from: g */
    private static final String f3828g = "ek_key";

    /* JADX INFO: renamed from: h */
    private List<String> f3829h;

    /* JADX INFO: renamed from: i */
    private List<Integer> f3830i;

    /* JADX INFO: renamed from: j */
    private String f3831j;

    /* JADX INFO: renamed from: com.umeng.analytics.pro.g$a */
    /* JADX INFO: compiled from: UMStoreManager.java */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        if (r1 != 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003a, code lost:
    
        if (r1 != 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
    
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3564a(int i) throws Throwable {
        ?? M3544a;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        if (i == 0) {
                            M3544a.execSQL("delete from __dp where __ty=0");
                        } else {
                            r0 = 4;
                            if (i == 4) {
                                M3544a.execSQL("delete from __dp where __ty=3");
                                M3544a.execSQL("delete from __dp where __ty=2");
                            } else {
                                r0 = 1;
                                r0 = 1;
                                if (i == 1) {
                                    M3544a.execSQL("delete from __dp where __ty=1");
                                }
                            }
                        }
                        M3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r0 = M3544a;
                        C1712f.m3549a(f3824c);
                        if (r0 != 0) {
                            r0.endTransaction();
                            r0 = r0;
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable th) {
                        th = th;
                        if (M3544a != 0) {
                            try {
                                M3544a.endTransaction();
                            } catch (Throwable unused2) {
                            }
                        }
                        C1711e.m3543a(f3824c).m3545b();
                        throw th;
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable th2) {
            th = th2;
            M3544a = r0;
        }
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.g$b */
    /* JADX INFO: compiled from: UMStoreManager.java */
    private static class b {

        /* JADX INFO: renamed from: a */
        private static final C1713g f3838a = new C1713g();

        private b() {
        }
    }

    private C1713g() {
        this.f3829h = new ArrayList();
        this.f3830i = new ArrayList();
        this.f3831j = null;
    }

    /* JADX INFO: renamed from: a */
    public static C1713g m3553a(Context context) {
        C1713g c1713g = b.f3838a;
        if (f3824c == null && context != null) {
            f3824c = context.getApplicationContext();
            c1713g.m3559h();
        }
        return c1713g;
    }

    /* JADX INFO: renamed from: h */
    private void m3559h() {
        synchronized (this) {
            m3560i();
            this.f3829h.clear();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3563a() {
        this.f3829h.clear();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0070, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0072, code lost:
    
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0065, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3566a(JSONObject jSONObject, int i) throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                try {
                    sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        sQLiteDatabaseM3544a.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        String strOptString = jSONObject.optString("__ii");
                        contentValues.put(C1709c.a.C2299a.f3765a, Long.valueOf(System.currentTimeMillis() - UMAmapConfig.AMAP_CACHE_WRITE_TIME));
                        if (TextUtils.isEmpty(strOptString)) {
                            strOptString = ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
                        }
                        contentValues.put("__ii", strOptString);
                        jSONObject.remove("__ii");
                        contentValues.put(C1709c.a.C2299a.f3767c, m3561a(jSONObject.toString()));
                        contentValues.put(C1709c.a.C2299a.f3768d, Integer.valueOf(i));
                        contentValues.put(C1709c.a.C2299a.f3769e, DeviceConfig.getAppVersionCode(f3824c));
                        sQLiteDatabaseM3544a.insert(C1709c.a.f3764a, null, contentValues);
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        C1712f.m3549a(f3824c);
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                sQLiteDatabaseM3544a = null;
            }
        } catch (Throwable th) {
            th = th;
            sQLiteDatabaseM3544a = sQLiteDatabase;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0083, code lost:
    
        if (r1 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008e, code lost:
    
        if (r1 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0090, code lost:
    
        r1.endTransaction();
     */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3565a(JSONArray jSONArray) throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                try {
                    sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        sQLiteDatabaseM3544a.beginTransaction();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            try {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                ContentValues contentValues = new ContentValues();
                                String strOptString = jSONObject.optString("__i");
                                if (TextUtils.isEmpty(strOptString) || ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG.equals(strOptString)) {
                                    strOptString = C1721o.m3668a().m3680c();
                                    if (TextUtils.isEmpty(strOptString)) {
                                        strOptString = ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
                                    }
                                }
                                contentValues.put("__i", strOptString);
                                contentValues.put("__e", jSONObject.optString("id"));
                                contentValues.put("__t", Integer.valueOf(jSONObject.optInt("__t")));
                                jSONObject.remove("__i");
                                jSONObject.remove("__t");
                                contentValues.put(C1709c.d.a.f3791c, m3561a(jSONObject.toString()));
                                sQLiteDatabaseM3544a.insert(C1709c.d.f3788a, null, contentValues);
                            } catch (Exception unused) {
                            }
                        }
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused2) {
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        C1712f.m3549a(f3824c);
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable unused3) {
                    }
                } catch (Throwable unused4) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused5) {
            } catch (Throwable unused6) {
                sQLiteDatabaseM3544a = null;
            }
        } catch (Throwable th) {
            th = th;
            sQLiteDatabaseM3544a = sQLiteDatabase;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0043, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0045, code lost:
    
        r1.endTransaction();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0038, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m3569a(String str, String str2, int i) {
        SQLiteDatabase sQLiteDatabaseM3544a;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                try {
                    sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        sQLiteDatabaseM3544a.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("__i", str);
                        String strM3561a = m3561a(str2);
                        if (!TextUtils.isEmpty(strM3561a)) {
                            contentValues.put("__a", strM3561a);
                            contentValues.put("__t", Integer.valueOf(i));
                            sQLiteDatabaseM3544a.insert(C1709c.c.f3781a, null, contentValues);
                        }
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        C1712f.m3549a(f3824c);
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        C1711e.m3543a(f3824c).m3545b();
                        return false;
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                    return false;
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                sQLiteDatabaseM3544a = null;
            }
        } catch (Throwable th) {
            th = th;
            sQLiteDatabaseM3544a = sQLiteDatabase;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
    
        if (r1 != 0) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v11, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3572b() throws Throwable {
        ?? M3544a;
        Throwable th;
        ?? r0 = 0;
        strM3682d = null;
        ?? r1 = 0;
        String strM3682d = null;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        strM3682d = C1721o.m3668a().m3682d();
                        if (!TextUtils.isEmpty(strM3682d)) {
                            for (String str : new String[]{"", ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG}) {
                                M3544a.execSQL("update __et set __i=\"" + strM3682d + "\" where __i=\"" + str + "\"");
                            }
                            M3544a.setTransactionSuccessful();
                            r0 = strM3682d;
                            if (M3544a != 0) {
                                M3544a.endTransaction();
                                r0 = strM3682d;
                            }
                        } else {
                            if (M3544a != 0) {
                                try {
                                    M3544a.endTransaction();
                                } catch (Throwable unused) {
                                }
                            }
                            C1711e.m3543a(f3824c).m3545b();
                            return;
                        }
                    } catch (SQLiteDatabaseCorruptException unused2) {
                        r1 = M3544a;
                        C1712f.m3549a(f3824c);
                        r0 = r1;
                        if (r1 != 0) {
                            r1.endTransaction();
                            r0 = r1;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (M3544a != 0) {
                            try {
                                M3544a.endTransaction();
                            } catch (Throwable unused3) {
                            }
                        }
                        C1711e.m3543a(f3824c).m3545b();
                        throw th;
                    }
                } catch (Throwable th3) {
                    M3544a = r0;
                    th = th3;
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable unused6) {
        }
        C1711e.m3543a(f3824c).m3545b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0095, code lost:
    
        if (r2 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a0, code lost:
    
        if (r2 != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a2, code lost:
    
        r2.endTransaction();
        r1 = r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.umeng.analytics.pro.g] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m3570a(String str, JSONObject jSONObject, a aVar) throws Throwable {
        ?? M3544a;
        a aVar2;
        if (jSONObject == null) {
            return false;
        }
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        if (aVar == a.BEGIN) {
                            long jLongValue = ((Long) jSONObject.get("__e")).longValue();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("__ii", str);
                            contentValues.put("__e", String.valueOf(jLongValue));
                            M3544a.insert(C1709c.e.f3797a, null, contentValues);
                        } else if (aVar == a.END) {
                            long jLongValue2 = ((Long) jSONObject.get(C1709c.e.a.f3804g)).longValue();
                            StringBuilder sb = new StringBuilder();
                            sb.append("update __sd set __f=\"");
                            sb.append(jLongValue2);
                            sb.append("\" where ");
                            sb.append("__ii");
                            sb.append("=\"");
                            sb.append(str);
                            sb.append("\"");
                            M3544a.execSQL(sb.toString());
                            r1 = sb;
                        } else {
                            a aVar3 = a.PAGE;
                            if (aVar == aVar3) {
                                m3556a(str, jSONObject, M3544a, "__a");
                                r1 = aVar3;
                            } else {
                                a aVar4 = a.AUTOPAGE;
                                if (aVar == aVar4) {
                                    m3556a(str, jSONObject, M3544a, C1709c.e.a.f3800c);
                                    r1 = aVar4;
                                } else {
                                    aVar2 = a.NEWSESSION;
                                    if (aVar == aVar2) {
                                        r1 = aVar2;
                                        m3555a(str, jSONObject, M3544a);
                                        r1 = aVar2;
                                    }
                                }
                            }
                        }
                        r1 = aVar2;
                        M3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r1 = M3544a;
                        C1712f.m3549a(f3824c);
                        if (r1 != 0) {
                            r1.endTransaction();
                            r1 = r1;
                        }
                        C1711e.m3543a(f3824c).m3545b();
                        return false;
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                    return false;
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable th) {
            th = th;
            M3544a = r1;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3555a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Cursor cursorRawQuery;
        String strM3571b = null;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(C1709c.e.a.f3802e);
            if (jSONObject2 != null) {
                cursorRawQuery = sQLiteDatabase.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            strM3571b = m3571b(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.e.a.f3802e)));
                        } catch (Throwable th) {
                            th = th;
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            throw th;
                        }
                    }
                }
            } else {
                cursorRawQuery = null;
            }
            if (jSONObject2 != null) {
                JSONArray jSONArray = new JSONArray();
                if (!TextUtils.isEmpty(strM3571b)) {
                    jSONArray = new JSONArray(strM3571b);
                }
                jSONArray.put(jSONObject2);
                String strM3561a = m3561a(jSONArray.toString());
                if (!TextUtils.isEmpty(strM3561a)) {
                    sQLiteDatabase.execSQL("update  __sd set __d=\"" + strM3561a + "\" where __ii=\"" + str + "\"");
                }
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject(C1709c.e.a.f3801d);
            if (jSONObject3 != null) {
                String strM3561a2 = m3561a(jSONObject3.toString());
                if (!TextUtils.isEmpty(strM3561a2)) {
                    sQLiteDatabase.execSQL("update  __sd set __c=\"" + strM3561a2 + "\" where __ii=\"" + str + "\"");
                }
            }
            sQLiteDatabase.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.getLong(C1709c.e.a.f3804g)) + "\" where __ii=\"" + str + "\"");
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
        cursorRawQuery.close();
    }

    /* JADX INFO: renamed from: a */
    private void m3556a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws Throwable {
        Cursor cursorRawQuery;
        String strM3571b = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select " + str2 + " from " + C1709c.e.f3797a + " where __ii=\"" + str + "\"", null);
            if (cursorRawQuery != null) {
                while (cursorRawQuery.moveToNext()) {
                    try {
                        strM3571b = m3571b(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(str2)));
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(strM3571b)) {
                jSONArray = new JSONArray(strM3571b);
            }
            jSONArray.put(jSONObject);
            String strM3561a = m3561a(jSONArray.toString());
            if (!TextUtils.isEmpty(strM3561a)) {
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + strM3561a + "\" where __ii=\"" + str + "\"");
            }
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
        cursorRawQuery.close();
    }

    /* JADX INFO: renamed from: a */
    public JSONObject m3562a(boolean z) throws Throwable {
        m3563a();
        this.f3830i.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            m3554a(jSONObject, z);
            m3558b(jSONObject, null);
            m3557a(jSONObject, (String) null);
        } else {
            String strM3554a = m3554a(jSONObject, z);
            if (!TextUtils.isEmpty(strM3554a)) {
                m3558b(jSONObject, strM3554a);
                m3557a(jSONObject, strM3554a);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:76:0x00b5 A[EXC_TOP_SPLITTER, PHI: r1 r2
  0x00b5: PHI (r1v6 ??) = (r1v2 ??), (r1v5 ??), (r1v13 ??) binds: [B:49:0x00b3, B:56:0x00cd, B:41:0x00a3] A[DONT_GENERATE, DONT_INLINE]
  0x00b5: PHI (r2v7 android.database.sqlite.SQLiteDatabase) = 
  (r2v4 android.database.sqlite.SQLiteDatabase)
  (r2v6 android.database.sqlite.SQLiteDatabase)
  (r2v11 android.database.sqlite.SQLiteDatabase)
 binds: [B:49:0x00b3, B:56:0x00cd, B:41:0x00a3] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object, org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX INFO: renamed from: c */
    public JSONObject m3574c() throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        ?? r3;
        Cursor cursorRawQuery;
        JSONObject jSONObject;
        ?? jSONObject2 = new JSONObject();
        ?? jSONArray = 0;
        jSONArray = 0;
        jSONArray = 0;
        jSONArray = 0;
        jSONArray = 0;
        jSONArray = 0;
        jSONArray = 0;
        try {
            try {
                sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                try {
                    sQLiteDatabaseM3544a.beginTransaction();
                    cursorRawQuery = sQLiteDatabaseM3544a.rawQuery("select *  from __dp", null);
                    if (cursorRawQuery != null) {
                        try {
                            jSONArray = new JSONArray();
                            JSONArray jSONArray2 = new JSONArray();
                            JSONArray jSONArray3 = new JSONArray();
                            while (cursorRawQuery.moveToNext()) {
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.a.C2299a.f3767c));
                                int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(C1709c.a.C2299a.f3768d));
                                if (!TextUtils.isEmpty(string) && (jSONObject = new JSONObject(m3571b(string))) != null && jSONObject.length() > 0) {
                                    if (i == 2 || i == 3) {
                                        jSONArray2.put(jSONObject);
                                    } else if (i == 0) {
                                        jSONArray.put(jSONObject);
                                    } else if (i == 1) {
                                        jSONArray3.put(jSONObject);
                                    }
                                }
                                if (jSONArray.length() > 0) {
                                    jSONObject2.put(C1708b.f3713Y, jSONArray);
                                }
                                if (jSONArray2.length() > 0) {
                                    jSONObject2.put(C1708b.f3718ac, jSONArray2);
                                }
                                if (jSONArray3.length() > 0) {
                                    jSONObject2.put(C1708b.f3723ah, jSONArray3);
                                }
                            }
                            sQLiteDatabaseM3544a.setTransactionSuccessful();
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                try {
                                    sQLiteDatabaseM3544a.endTransaction();
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (SQLiteDatabaseCorruptException unused2) {
                            jSONArray = cursorRawQuery;
                            C1712f.m3549a(f3824c);
                            if (jSONArray != 0) {
                                jSONArray.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                sQLiteDatabaseM3544a.endTransaction();
                            }
                        } catch (Throwable unused3) {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                            }
                            C1711e.m3543a(f3824c).m3545b();
                            return jSONObject2;
                        }
                    } else {
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                        }
                    }
                } catch (SQLiteDatabaseCorruptException unused4) {
                } catch (Throwable unused5) {
                    cursorRawQuery = null;
                }
            } catch (Throwable th) {
                th = th;
                r3 = jSONArray;
            }
        } catch (SQLiteDatabaseCorruptException unused6) {
            sQLiteDatabaseM3544a = null;
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabaseM3544a = null;
            r3 = 0;
        }
        C1711e.m3543a(f3824c).m3545b();
        return jSONObject2;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0168  */
    /* JADX WARN: Code duplicated, block: B:94:0x017d A[EXC_TOP_SPLITTER, PHI: r1
  0x017d: PHI (r1v6 android.database.sqlite.SQLiteDatabase) = 
  (r1v3 android.database.sqlite.SQLiteDatabase)
  (r1v5 android.database.sqlite.SQLiteDatabase)
  (r1v9 android.database.sqlite.SQLiteDatabase)
 binds: [B:72:0x017b, B:79:0x0195, B:64:0x016b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private void m3557a(JSONObject jSONObject, String str) throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase sQLiteDatabaseM3544a;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                try {
                    sQLiteDatabaseM3544a.beginTransaction();
                    cursorRawQuery = sQLiteDatabaseM3544a.rawQuery(TextUtils.isEmpty(str) ? "select *  from __et" : "select *  from __et where __i=\"" + str + "\"", null);
                    if (cursorRawQuery != null) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            JSONObject jSONObject3 = new JSONObject();
                            String strM3680c = C1721o.m3668a().m3680c();
                            while (cursorRawQuery.moveToNext()) {
                                int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("__t"));
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__i"));
                                String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.d.a.f3791c));
                                if (TextUtils.isEmpty(string) || ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG.equals(string)) {
                                    if (!TextUtils.isEmpty(strM3680c)) {
                                        string = strM3680c;
                                    }
                                }
                                this.f3830i.add(Integer.valueOf(cursorRawQuery.getInt(0)));
                                switch (i) {
                                    case f3822a /* 2049 */:
                                        if (!TextUtils.isEmpty(string2)) {
                                            JSONObject jSONObject4 = new JSONObject(m3571b(string2));
                                            if (jSONObject2.has(string)) {
                                                jSONArray = jSONObject2.optJSONArray(string);
                                            } else {
                                                jSONArray = new JSONArray();
                                            }
                                            jSONArray.put(jSONObject4);
                                            jSONObject2.put(string, jSONArray);
                                        }
                                        break;
                                    case f3823b /* 2050 */:
                                        if (!TextUtils.isEmpty(string2)) {
                                            JSONObject jSONObject5 = new JSONObject(m3571b(string2));
                                            if (jSONObject3.has(string)) {
                                                jSONArray2 = jSONObject3.optJSONArray(string);
                                            } else {
                                                jSONArray2 = new JSONArray();
                                            }
                                            jSONArray2.put(jSONObject5);
                                            jSONObject3.put(string, jSONArray2);
                                        }
                                        break;
                                }
                            }
                            if (jSONObject2.length() > 0) {
                                JSONArray jSONArray3 = new JSONArray();
                                Iterator<String> itKeys = jSONObject2.keys();
                                while (itKeys.hasNext()) {
                                    JSONObject jSONObject6 = new JSONObject();
                                    String next = itKeys.next();
                                    jSONObject6.put(next, new JSONArray(jSONObject2.optString(next)));
                                    if (jSONObject6.length() > 0) {
                                        jSONArray3.put(jSONObject6);
                                    }
                                }
                                if (jSONArray3.length() > 0) {
                                    jSONObject.put(C1708b.f3702N, jSONArray3);
                                }
                            }
                            if (jSONObject3.length() > 0) {
                                JSONArray jSONArray4 = new JSONArray();
                                Iterator<String> itKeys2 = jSONObject3.keys();
                                while (itKeys2.hasNext()) {
                                    JSONObject jSONObject7 = new JSONObject();
                                    String next2 = itKeys2.next();
                                    jSONObject7.put(next2, new JSONArray(jSONObject3.optString(next2)));
                                    if (jSONObject7.length() > 0) {
                                        jSONArray4.put(jSONObject7);
                                    }
                                }
                                if (jSONArray4.length() > 0) {
                                    jSONObject.put(C1708b.f3703O, jSONArray4);
                                }
                            }
                            sQLiteDatabaseM3544a.setTransactionSuccessful();
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                try {
                                    sQLiteDatabaseM3544a.endTransaction();
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (SQLiteDatabaseCorruptException unused2) {
                            cursor = cursorRawQuery;
                            C1712f.m3549a(f3824c);
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                sQLiteDatabaseM3544a.endTransaction();
                            }
                        } catch (Throwable unused3) {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                sQLiteDatabaseM3544a.endTransaction();
                            }
                        }
                    } else {
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                        }
                    }
                } catch (SQLiteDatabaseCorruptException unused4) {
                } catch (Throwable unused5) {
                    cursorRawQuery = null;
                }
            } catch (Throwable th) {
                th = th;
                cursorRawQuery = null;
            }
        } catch (SQLiteDatabaseCorruptException unused6) {
            sQLiteDatabaseM3544a = null;
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
            sQLiteDatabaseM3544a = null;
        }
        C1711e.m3543a(f3824c).m3545b();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0070  */
    /* JADX WARN: Code duplicated, block: B:56:0x0084 A[EXC_TOP_SPLITTER, PHI: r1
  0x0084: PHI (r1v6 android.database.sqlite.SQLiteDatabase) = 
  (r1v4 android.database.sqlite.SQLiteDatabase)
  (r1v5 android.database.sqlite.SQLiteDatabase)
  (r1v9 android.database.sqlite.SQLiteDatabase)
 binds: [B:32:0x0082, B:39:0x009c, B:24:0x0073] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX INFO: renamed from: b */
    private void m3558b(JSONObject jSONObject, String str) throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        Cursor cursorRawQuery;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                try {
                    sQLiteDatabaseM3544a.beginTransaction();
                    String str2 = "select *  from __er";
                    if (!TextUtils.isEmpty(str)) {
                        str2 = "select *  from __er where __i=\"" + str + "\"";
                    }
                    cursorRawQuery = sQLiteDatabaseM3544a.rawQuery(str2, null);
                    if (cursorRawQuery != null) {
                        try {
                            JSONArray jSONArray = new JSONArray();
                            while (cursorRawQuery.moveToNext()) {
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                                if (!TextUtils.isEmpty(string)) {
                                    jSONArray.put(new JSONObject(m3571b(string)));
                                }
                            }
                            if (jSONArray.length() > 0) {
                                jSONObject.put(C1708b.f3698J, jSONArray);
                            }
                            sQLiteDatabaseM3544a.setTransactionSuccessful();
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                try {
                                    sQLiteDatabaseM3544a.endTransaction();
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (SQLiteDatabaseCorruptException unused2) {
                            cursor = cursorRawQuery;
                            C1712f.m3549a(f3824c);
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                sQLiteDatabaseM3544a.endTransaction();
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorRawQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabaseM3544a != null) {
                                try {
                                    sQLiteDatabaseM3544a.endTransaction();
                                } catch (Throwable unused3) {
                                }
                            }
                            C1711e.m3543a(f3824c).m3545b();
                            throw th;
                        }
                    } else {
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                        }
                    }
                } catch (SQLiteDatabaseCorruptException unused4) {
                } catch (Throwable unused5) {
                    cursorRawQuery = null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteDatabaseCorruptException unused6) {
            sQLiteDatabaseM3544a = null;
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabaseM3544a = null;
        }
        C1711e.m3543a(f3824c).m3545b();
    }

    /* JADX WARN: Code duplicated, block: B:106:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:124:0x01cf A[EXC_TOP_SPLITTER, PHI: r2 r4
  0x01cf: PHI (r2v9 java.lang.String) = (r2v1 java.lang.String), (r2v40 java.lang.String) binds: [B:92:0x01cd, B:78:0x01b3] A[DONT_GENERATE, DONT_INLINE]
  0x01cf: PHI (r4v8 android.database.sqlite.SQLiteDatabase) = (r4v3 android.database.sqlite.SQLiteDatabase), (r4v38 android.database.sqlite.SQLiteDatabase) binds: [B:92:0x01cd, B:78:0x01b3] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:0x0202 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:0x01ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:98:0x01e5  */
    /* JADX INFO: renamed from: a */
    private String m3554a(JSONObject jSONObject, boolean z) throws Throwable {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        String string;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2;
        Cursor cursor2;
        String str = null;
        str = null;
        str = null;
        str = null;
        str = null;
        cursor = null;
        str = null;
        cursor = null;
        Cursor cursor3 = null;
        try {
            SQLiteDatabase sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
            try {
                sQLiteDatabaseM3544a.beginTransaction();
                Cursor cursorRawQuery = sQLiteDatabaseM3544a.rawQuery("select *  from __sd", null);
                if (cursorRawQuery != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        while (true) {
                            if (!cursorRawQuery.moveToNext()) {
                                sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                cursor2 = cursorRawQuery;
                                break;
                            }
                            try {
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.e.a.f3804g));
                                    String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__e"));
                                    string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ii"));
                                    try {
                                        if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                                            sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                            cursor2 = cursorRawQuery;
                                        } else {
                                            if (Long.parseLong(string2) - Long.parseLong(string3) > 0) {
                                                String string4 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                                                String string5 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.e.a.f3800c));
                                                String string6 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.e.a.f3801d));
                                                String string7 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(C1709c.e.a.f3802e));
                                                this.f3829h.add(string);
                                                jSONObject2.put("id", string);
                                                jSONObject2.put(C1708b.f3747p, string3);
                                                jSONObject2.put(C1708b.f3748q, string2);
                                                sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                                cursor2 = cursorRawQuery;
                                                try {
                                                    jSONObject2.put("duration", Long.parseLong(string2) - Long.parseLong(string3));
                                                    if (!TextUtils.isEmpty(string4)) {
                                                        jSONObject2.put(C1708b.f3750s, new JSONArray(m3571b(string4)));
                                                    }
                                                    if (!TextUtils.isEmpty(string5)) {
                                                        jSONObject2.put(C1708b.f3751t, new JSONArray(m3571b(string5)));
                                                    }
                                                    if (!TextUtils.isEmpty(string6)) {
                                                        jSONObject2.put(C1708b.f3689A, new JSONObject(m3571b(string6)));
                                                    }
                                                    if (!TextUtils.isEmpty(string7)) {
                                                        jSONObject2.put(C1708b.f3754w, new JSONArray(m3571b(string7)));
                                                    }
                                                    if (jSONObject2.length() > 0) {
                                                        jSONArray.put(jSONObject2);
                                                    }
                                                } catch (SQLiteDatabaseCorruptException unused) {
                                                    sQLiteDatabase = sQLiteDatabase2;
                                                    cursor3 = cursor2;
                                                    try {
                                                        C1712f.m3549a(f3824c);
                                                        if (cursor3 != null) {
                                                            cursor3.close();
                                                        }
                                                        if (sQLiteDatabase != null) {
                                                            try {
                                                                sQLiteDatabase.endTransaction();
                                                            } catch (Throwable unused2) {
                                                            }
                                                        }
                                                        C1711e.m3543a(f3824c).m3545b();
                                                        return string;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        cursor = cursor3;
                                                        th = th;
                                                        if (cursor != null) {
                                                            cursor.close();
                                                        }
                                                        if (sQLiteDatabase != null) {
                                                            try {
                                                                sQLiteDatabase.endTransaction();
                                                            } catch (Throwable unused3) {
                                                            }
                                                        }
                                                        C1711e.m3543a(f3824c).m3545b();
                                                        throw th;
                                                    }
                                                } catch (Throwable unused4) {
                                                    str = string;
                                                    sQLiteDatabase = sQLiteDatabase2;
                                                    cursor = cursor2;
                                                    if (cursor != null) {
                                                        cursor.close();
                                                    }
                                                    if (sQLiteDatabase != null) {
                                                        try {
                                                            sQLiteDatabase.endTransaction();
                                                        } catch (Throwable unused5) {
                                                        }
                                                    }
                                                    C1711e.m3543a(f3824c).m3545b();
                                                    return str;
                                                }
                                            } else {
                                                sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                                cursor2 = cursorRawQuery;
                                            }
                                            if (z) {
                                                str = string;
                                                break;
                                            }
                                        }
                                        str = string;
                                        sQLiteDatabaseM3544a = sQLiteDatabase2;
                                        cursorRawQuery = cursor2;
                                    } catch (SQLiteDatabaseCorruptException unused6) {
                                        sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                        cursor2 = cursorRawQuery;
                                    } catch (Throwable unused7) {
                                        sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                        cursor2 = cursorRawQuery;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                    cursor2 = cursorRawQuery;
                                }
                            } catch (SQLiteDatabaseCorruptException unused8) {
                                sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                cursor2 = cursorRawQuery;
                                string = str;
                            } catch (Throwable unused9) {
                                sQLiteDatabase2 = sQLiteDatabaseM3544a;
                                cursor2 = cursorRawQuery;
                            }
                        }
                        try {
                            if (this.f3829h.size() < 1) {
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (sQLiteDatabase2 != null) {
                                    try {
                                        sQLiteDatabase2.endTransaction();
                                    } catch (Throwable unused10) {
                                    }
                                }
                                C1711e.m3543a(f3824c).m3545b();
                                return str;
                            }
                            sQLiteDatabase = sQLiteDatabase2;
                            cursor = cursor2;
                            try {
                                if (jSONArray.length() > 0) {
                                    jSONObject.put(C1708b.f3745n, jSONArray);
                                }
                            } catch (SQLiteDatabaseCorruptException unused11) {
                                string = str;
                                cursor3 = cursor;
                                C1712f.m3549a(f3824c);
                                if (cursor3 != null) {
                                    cursor3.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.endTransaction();
                                }
                                C1711e.m3543a(f3824c).m3545b();
                                return string;
                            } catch (Throwable th4) {
                                th = th4;
                                th = th;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.endTransaction();
                                }
                                C1711e.m3543a(f3824c).m3545b();
                                throw th;
                            }
                        } catch (SQLiteDatabaseCorruptException unused12) {
                            sQLiteDatabase = sQLiteDatabase2;
                            cursor = cursor2;
                            string = str;
                            cursor3 = cursor;
                            C1712f.m3549a(f3824c);
                            if (cursor3 != null) {
                                cursor3.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            C1711e.m3543a(f3824c).m3545b();
                            return string;
                        } catch (Throwable unused13) {
                            sQLiteDatabase = sQLiteDatabase2;
                            cursor = cursor2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException unused14) {
                        Cursor cursor4 = cursorRawQuery;
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        cursor = cursor4;
                    } catch (Throwable unused15) {
                        Cursor cursor5 = cursorRawQuery;
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        cursor = cursor5;
                    }
                } else {
                    sQLiteDatabase = sQLiteDatabaseM3544a;
                    cursor = cursorRawQuery;
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (SQLiteDatabaseCorruptException unused16) {
                sQLiteDatabase = sQLiteDatabaseM3544a;
                string = null;
            } catch (Throwable th5) {
                th = th5;
                sQLiteDatabase = sQLiteDatabaseM3544a;
                cursor = cursor3;
                th = th;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                C1711e.m3543a(f3824c).m3545b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException unused17) {
            sQLiteDatabase = null;
            string = null;
        } catch (Throwable th6) {
            th = th6;
            cursor = null;
            sQLiteDatabase = null;
        }
        C1711e.m3543a(f3824c).m3545b();
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
    
        if (r1 != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        if (r1 != 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005d, code lost:
    
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3568a(boolean z, boolean z2) throws Throwable {
        ?? M3544a;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        if (z2) {
                            if (z) {
                                M3544a.execSQL("delete from __sd");
                            }
                        } else if (this.f3829h.size() > 0) {
                            int i = 0;
                            while (i < this.f3829h.size()) {
                                M3544a.execSQL("delete from __sd where __ii=\"" + this.f3829h.get(i) + "\"");
                                i++;
                                r0 = "\"";
                            }
                        }
                        M3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r0 = M3544a;
                        C1712f.m3549a(f3824c);
                        if (r0 != 0) {
                            r0.endTransaction();
                            r0 = r0;
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable th) {
            th = th;
            M3544a = r0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
    
        if (r1 != 0) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v11, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3575d() throws Throwable {
        ?? M3544a;
        Throwable th;
        ?? r0 = 0;
        list = null;
        ?? r1 = 0;
        List<Integer> list = null;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        if (this.f3830i.size() > 0) {
                            for (int i = 0; i < this.f3830i.size(); i++) {
                                M3544a.execSQL("delete from __et where rowid=" + this.f3830i.get(i));
                            }
                        }
                        list = this.f3830i;
                        list.clear();
                        M3544a.setTransactionSuccessful();
                        r0 = list;
                        if (M3544a != 0) {
                            M3544a.endTransaction();
                            r0 = list;
                        }
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r1 = M3544a;
                        C1712f.m3549a(f3824c);
                        r0 = r1;
                        if (r1 != 0) {
                            r1.endTransaction();
                            r0 = r1;
                        }
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable th2) {
                    M3544a = r0;
                    th = th2;
                }
            } catch (SQLiteDatabaseCorruptException unused3) {
            } catch (Throwable unused4) {
                M3544a = 0;
            }
        } catch (Throwable unused5) {
        }
        C1711e.m3543a(f3824c).m3545b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        if (r1 != null) goto L14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX INFO: renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3576e() throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        ?? r0 = 0;
        str = null;
        SQLiteDatabase sQLiteDatabase = null;
        String str = null;
        try {
            try {
                try {
                    sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        sQLiteDatabaseM3544a.beginTransaction();
                        str = "delete from __er";
                        sQLiteDatabaseM3544a.execSQL("delete from __er");
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                        r0 = str;
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                            r0 = str;
                        }
                    } catch (SQLiteDatabaseCorruptException unused) {
                        sQLiteDatabase = sQLiteDatabaseM3544a;
                        C1712f.m3549a(f3824c);
                        r0 = sQLiteDatabase;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                            r0 = sQLiteDatabase;
                        }
                    } catch (Throwable th) {
                        th = th;
                        r0 = sQLiteDatabaseM3544a;
                        if (r0 != 0) {
                            try {
                                r0.endTransaction();
                            } catch (Throwable unused2) {
                            }
                        }
                        C1711e.m3543a(f3824c).m3545b();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (SQLiteDatabaseCorruptException unused3) {
            } catch (Throwable unused4) {
                sQLiteDatabaseM3544a = null;
            }
        } catch (Throwable unused5) {
        }
        C1711e.m3543a(f3824c).m3545b();
    }

    /* JADX INFO: renamed from: f */
    public void m3577f() throws Throwable {
        m3573b(-1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
    
        if (r1 != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0055, code lost:
    
        if (r1 != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0057, code lost:
    
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3573b(int i) throws Throwable {
        ?? M3544a;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        String str = "delete from __dp";
                        if (i == -1) {
                            M3544a.execSQL("delete from __dp");
                            r0 = str;
                        } else {
                            r0 = 1;
                            if (i == 1) {
                                M3544a.execSQL("delete from __dp where __ty=1");
                            } else {
                                r0 = 4;
                                if (i == 4) {
                                    M3544a.execSQL("delete from __dp where __ty=3");
                                    M3544a.execSQL("delete from __dp where __ty=2");
                                } else {
                                    r0 = 3;
                                    r0 = 3;
                                    if (i == 3) {
                                        M3544a.execSQL("delete from __dp where __ty=3");
                                    } else if (i == 0) {
                                        M3544a.execSQL("delete from __dp where __ty=0");
                                    } else {
                                        r0 = 2;
                                        r0 = 2;
                                        if (i == 2) {
                                            M3544a.execSQL("delete from __dp where __ty=2");
                                        }
                                    }
                                }
                            }
                        }
                        M3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r0 = M3544a;
                        C1712f.m3549a(f3824c);
                        if (r0 != 0) {
                            r0.endTransaction();
                            r0 = r0;
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable th) {
            th = th;
            M3544a = r0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x005a A[EXC_TOP_SPLITTER, PHI: r0
  0x005a: PHI (r0v12 android.database.sqlite.SQLiteDatabase) = 
  (r0v10 android.database.sqlite.SQLiteDatabase)
  (r0v11 android.database.sqlite.SQLiteDatabase)
  (r0v17 android.database.sqlite.SQLiteDatabase)
 binds: [B:11:0x0058, B:16:0x006d, B:6:0x004f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.umeng.analytics.pro.e] */
    /* JADX INFO: renamed from: g */
    public void m3578g() throws Throwable {
        SQLiteDatabase sQLiteDatabaseM3544a;
        SQLiteDatabase sQLiteDatabaseIsEmpty = TextUtils.isEmpty(this.f3831j);
        try {
            if (sQLiteDatabaseIsEmpty == 0) {
                try {
                    sQLiteDatabaseM3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        sQLiteDatabaseM3544a.beginTransaction();
                        sQLiteDatabaseM3544a.execSQL("delete from __er where __i=\"" + this.f3831j + "\"");
                        sQLiteDatabaseM3544a.execSQL("delete from __et where __i=\"" + this.f3831j + "\"");
                        sQLiteDatabaseM3544a.setTransactionSuccessful();
                        if (sQLiteDatabaseM3544a != null) {
                            try {
                                sQLiteDatabaseM3544a.endTransaction();
                            } catch (Throwable unused) {
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException unused2) {
                        C1712f.m3549a(f3824c);
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                        }
                    } catch (Throwable unused3) {
                        if (sQLiteDatabaseM3544a != null) {
                            sQLiteDatabaseM3544a.endTransaction();
                        }
                    }
                } catch (SQLiteDatabaseCorruptException unused4) {
                    sQLiteDatabaseM3544a = null;
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabaseIsEmpty = 0;
                    if (sQLiteDatabaseIsEmpty != 0) {
                        try {
                            sQLiteDatabaseIsEmpty.endTransaction();
                        } catch (Throwable unused5) {
                        }
                    }
                    C1711e.m3543a(f3824c).m3545b();
                    throw th;
                }
                sQLiteDatabaseIsEmpty = C1711e.m3543a(f3824c);
                sQLiteDatabaseIsEmpty.m3545b();
            }
            this.f3831j = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00a3, code lost:
    
        if (r1 != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00ae, code lost:
    
        if (r1 != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b0, code lost:
    
        r1.endTransaction();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v7, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3567a(boolean z, String str) throws Throwable {
        ?? M3544a;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    this.f3831j = str;
                    M3544a = C1711e.m3543a(f3824c).m3544a();
                    try {
                        M3544a.beginTransaction();
                        if (!TextUtils.isEmpty(str)) {
                            M3544a.execSQL("delete from __er where __i=\"" + str + "\"");
                            M3544a.execSQL("delete from __et where __i=\"" + str + "\"");
                            if (z) {
                                M3544a.execSQL("update __sd set __b=\"" + ((Object) null) + "\" where __ii=\"" + str + "\"");
                                M3544a.execSQL("update __sd set __a=\"" + ((Object) null) + "\" where __ii=\"" + str + "\"");
                                r0 = "=\"";
                            }
                        }
                        M3544a.setTransactionSuccessful();
                    } catch (SQLiteDatabaseCorruptException unused) {
                        r0 = M3544a;
                        C1712f.m3549a(f3824c);
                        if (r0 != 0) {
                            r0.endTransaction();
                            r0 = r0;
                        }
                        C1711e.m3543a(f3824c).m3545b();
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    C1711e.m3543a(f3824c).m3545b();
                }
            } catch (SQLiteDatabaseCorruptException unused4) {
            } catch (Throwable unused5) {
                M3544a = 0;
            }
        } catch (Throwable th) {
            th = th;
            M3544a = r0;
        }
    }

    /* JADX INFO: renamed from: i */
    private void m3560i() {
        try {
            if (TextUtils.isEmpty(f3825d)) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3824c);
                String string = sharedPreferences.getString(f3827f, null);
                if (TextUtils.isEmpty(string)) {
                    string = DeviceConfig.getDBencryptID(f3824c);
                    if (!TextUtils.isEmpty(string)) {
                        sharedPreferences.edit().putString(f3827f, string).commit();
                    }
                }
                if (!TextUtils.isEmpty(string)) {
                    String strSubstring = string.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < strSubstring.length(); i++) {
                        char cCharAt = strSubstring.charAt(i);
                        if (Character.isDigit(cCharAt)) {
                            if (Integer.parseInt(Character.toString(cCharAt)) == 0) {
                                sb.append(0);
                            } else {
                                sb.append(10 - Integer.parseInt(Character.toString(cCharAt)));
                            }
                        } else {
                            sb.append(cCharAt);
                        }
                    }
                    f3825d = sb.toString();
                }
                if (TextUtils.isEmpty(f3825d)) {
                    return;
                }
                f3825d += new StringBuilder(f3825d).reverse().toString();
                String string2 = sharedPreferences.getString(f3828g, null);
                if (TextUtils.isEmpty(string2)) {
                    sharedPreferences.edit().putString(f3828g, m3561a(f3826e)).commit();
                } else {
                    if (f3826e.equals(m3571b(string2))) {
                        return;
                    }
                    m3568a(true, false);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public String m3561a(String str) {
        try {
            return TextUtils.isEmpty(f3825d) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), f3825d.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public String m3571b(String str) {
        try {
            return TextUtils.isEmpty(f3825d) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f3825d.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }
}
