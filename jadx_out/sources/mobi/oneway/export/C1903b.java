package mobi.oneway.export;

import dalvik.system.DexClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import mobi.oneway.export.enums.PluginErrorType;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p078a.C1900b;
import mobi.oneway.export.p078a.C1901c;
import mobi.oneway.export.p078a.C1902d;
import mobi.oneway.export.p084e.C1929c;
import mobi.oneway.export.p087g.C1953g;
import mobi.oneway.export.p087g.C1954h;
import mobi.oneway.export.p087g.C1956j;
import mobi.oneway.export.p087g.C1959m;

/* JADX INFO: renamed from: mobi.oneway.export.b */
/* JADX INFO: loaded from: classes.dex */
public class C1903b {

    /* JADX INFO: renamed from: a */
    private static ClassLoader f4838a;

    /* JADX INFO: renamed from: a */
    public static Class m4720a(String str) {
        try {
            return m4721a().loadClass(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static ClassLoader m4721a() throws Throwable {
        try {
            if (f4838a == null) {
                m4725e();
                f4838a = C1902d.m4719a() ? C1903b.class.getClassLoader() : new DexClassLoader(m4722b(), m4723c(), null, C1903b.class.getClassLoader());
            }
            return f4838a;
        } catch (Exception e) {
            C1929c.m4872a(PluginErrorType.shell_error_classloader, C1953g.m4999a((Throwable) e));
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private static String m4722b() {
        if (C1900b.m4706a() == null) {
            return "";
        }
        return C1900b.m4706a().getDir("plugin", 0).getPath() + File.separator + C1899a.f4827j;
    }

    /* JADX INFO: renamed from: c */
    private static String m4723c() {
        return C1900b.m4706a() != null ? C1900b.m4706a().getDir(C1899a.f4825h, 0).getPath() : "";
    }

    /* JADX INFO: renamed from: d */
    private static String m4724d() {
        return C1900b.m4706a() != null ? C1900b.m4706a().getDir("plugin", 0).getPath() : "";
    }

    /* JADX INFO: renamed from: e */
    private static void m4725e() throws Throwable {
        Throwable th;
        InputStream inputStreamOpen;
        IOException e;
        Closeable[] closeableArr;
        if (C1900b.m4706a() == null) {
            C1959m.m5053d("context is null,please invoke OnewaySdk.init method first");
            return;
        }
        m4726f();
        if (C1954h.m5007a(m4722b())) {
            return;
        }
        try {
            inputStreamOpen = C1900b.m4706a().getAssets().open(C1901c.m4716a() + File.separator + C1901c.m4717b());
            try {
                try {
                    byte[] bArr = new byte[inputStreamOpen.available()];
                    inputStreamOpen.read(bArr);
                    C1954h.m5006a(new File(m4722b()), bArr);
                    closeableArr = new Closeable[]{inputStreamOpen};
                } catch (IOException e2) {
                    e = e2;
                    C1959m.m5053d("load local default OnewaySdk failed");
                    C1929c.m4872a(PluginErrorType.shell_error_assetsIo, C1953g.m4999a((Throwable) e));
                    closeableArr = new Closeable[]{inputStreamOpen};
                }
            } catch (Throwable th2) {
                th = th2;
                C1956j.m5037a(inputStreamOpen);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            inputStreamOpen = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpen = null;
            C1956j.m5037a(inputStreamOpen);
            throw th;
        }
        C1956j.m5037a(closeableArr);
    }

    /* JADX INFO: renamed from: f */
    private static void m4726f() {
        File[] fileArrListFiles;
        File file = new File(m4724d());
        if (!C1954h.m5021e(file) || (fileArrListFiles = file.listFiles(new FileFilter() { // from class: mobi.oneway.export.b.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.getName().startsWith("OnewaySdk");
            }
        })) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.getName().equals(C1899a.f4827j)) {
                C1954h.m5028j(file2);
            }
        }
    }
}
