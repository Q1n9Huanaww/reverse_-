package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.C1732b;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.d */
/* JADX INFO: compiled from: StoreHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1816d {

    /* JADX INFO: renamed from: a */
    private static C1816d f4504a = null;

    /* JADX INFO: renamed from: b */
    private static Context f4505b = null;

    /* JADX INFO: renamed from: c */
    private static String f4506c = null;

    /* JADX INFO: renamed from: e */
    private static final String f4507e = "mobclick_agent_user_";

    /* JADX INFO: renamed from: f */
    private static final String f4508f = "mobclick_agent_header_";

    /* JADX INFO: renamed from: g */
    private static final String f4509g = "mobclick_agent_cached_";

    /* JADX INFO: renamed from: d */
    private a f4510d;

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.d$b */
    /* JADX INFO: compiled from: StoreHelper.java */
    public interface b {
        /* JADX INFO: renamed from: a */
        void m4390a(File file);

        /* JADX INFO: renamed from: b */
        boolean m4391b(File file);

        /* JADX INFO: renamed from: c */
        void m4392c(File file);
    }

    public C1816d(Context context) {
        this.f4510d = new a(context);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1816d m4375a(Context context) {
        f4505b = context.getApplicationContext();
        f4506c = context.getPackageName();
        if (f4504a == null) {
            f4504a = new C1816d(context);
        }
        return f4504a;
    }

    /* JADX INFO: renamed from: a */
    public void m4378a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4505b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    /* JADX INFO: renamed from: a */
    public int m4377a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4505b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    /* JADX INFO: renamed from: b */
    public String m4381b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4505b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void m4379a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4505b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    /* JADX INFO: renamed from: c */
    public boolean m4382c() {
        return C1732b.m3722c(f4505b) > 0;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.d$a */
    /* JADX INFO: compiled from: StoreHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        private final int f4511a;

        /* JADX INFO: renamed from: b */
        private File f4512b;

        /* JADX INFO: renamed from: c */
        private FilenameFilter f4513c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.f4511a = 10;
            this.f4513c = new FilenameFilter() { // from class: com.umeng.commonsdk.statistics.common.d.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith("um");
                }
            };
            this.f4512b = new File(context.getFilesDir(), str);
            if (this.f4512b.exists() && this.f4512b.isDirectory()) {
                return;
            }
            this.f4512b.mkdir();
        }

        /* JADX INFO: renamed from: a */
        public boolean m4387a() {
            File[] fileArrListFiles = this.f4512b.listFiles();
            return fileArrListFiles != null && fileArrListFiles.length > 0;
        }

        /* JADX INFO: renamed from: a */
        public void m4385a(b bVar) {
            File file;
            File[] fileArrListFiles = this.f4512b.listFiles(this.f4513c);
            if (fileArrListFiles != null && fileArrListFiles.length >= 10) {
                Arrays.sort(fileArrListFiles);
                int length = fileArrListFiles.length - 10;
                for (int i = 0; i < length; i++) {
                    fileArrListFiles[i].delete();
                }
            }
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return;
            }
            bVar.m4390a(this.f4512b);
            int length2 = fileArrListFiles.length;
            for (int i2 = 0; i2 < length2; i2++) {
                try {
                    if (bVar.m4391b(fileArrListFiles[i2])) {
                        file = fileArrListFiles[i2];
                        file.delete();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            bVar.m4392c(this.f4512b);
        }

        /* JADX INFO: renamed from: a */
        public void m4386a(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return;
            }
            try {
                HelperUtils.writeFile(new File(this.f4512b, String.format(Locale.US, "um_cache_%d.env", Long.valueOf(System.currentTimeMillis()))), bArr);
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: renamed from: b */
        public void m4388b() {
            File[] fileArrListFiles = this.f4512b.listFiles(this.f4513c);
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return;
            }
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }

        /* JADX INFO: renamed from: c */
        public int m4389c() {
            File[] fileArrListFiles = this.f4512b.listFiles(this.f4513c);
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return 0;
            }
            return fileArrListFiles.length;
        }
    }

    /* JADX INFO: renamed from: f */
    private SharedPreferences m4376f() {
        return f4505b.getSharedPreferences(f4507e + f4506c, 0);
    }

    /* JADX INFO: renamed from: a */
    public void m4380a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = m4376f().edit();
        editorEdit.putString("au_p", str);
        editorEdit.putString("au_u", str2);
        editorEdit.commit();
    }

    /* JADX INFO: renamed from: d */
    public String[] m4383d() {
        try {
            SharedPreferences sharedPreferencesM4376f = m4376f();
            String string = sharedPreferencesM4376f.getString("au_p", null);
            String string2 = sharedPreferencesM4376f.getString("au_u", null);
            if (string != null && string2 != null) {
                return new String[]{string, string2};
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX INFO: renamed from: e */
    public void m4384e() {
        m4376f().edit().remove("au_p").remove("au_u").commit();
    }
}
