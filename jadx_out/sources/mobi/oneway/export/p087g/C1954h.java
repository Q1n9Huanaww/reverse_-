package mobi.oneway.export.p087g;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* JADX INFO: renamed from: mobi.oneway.export.g.h */
/* JADX INFO: loaded from: classes.dex */
public class C1954h {
    /* JADX INFO: renamed from: a */
    private static long m5000a(long j) {
        return Math.round(j / 1024.0d);
    }

    /* JADX INFO: renamed from: a */
    public static long m5001a(File file) {
        if (m5023f(file)) {
            return -1L;
        }
        try {
            return m5000a(file.getFreeSpace());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0L;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m5002a(Context context, String str, String str2) throws Throwable {
        InputStream inputStream = null;
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                String strM5092c = C1966t.m5092c(inputStreamOpen, str2);
                C1956j.m5037a(inputStreamOpen);
                return strM5092c;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpen;
                C1956j.m5037a(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m5003a(String str, String str2) throws Throwable {
        byte[] bArrM5026h = m5026h(new File(str));
        return str2 == null ? new String(bArrM5026h) : new String(bArrM5026h, str2);
    }

    /* JADX INFO: renamed from: a */
    public static List<String> m5004a(File file, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(C1966t.m5091b(new FileInputStream(file), str));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        C1956j.m5037a(bufferedReader2);
                        return arrayList;
                    }
                    arrayList.add(line);
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    C1956j.m5037a(bufferedReader);
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5005a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5006a(File file, byte[] bArr) throws Throwable {
        if (file == null || bArr == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            m5015c(file.getParent(), (String) null);
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bufferedOutputStream2.write(bArr);
                bufferedOutputStream2.flush();
                C1956j.m5037a(bufferedOutputStream2);
                return true;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                C1956j.m5037a(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5007a(String str) {
        return m5021e(new File(str));
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5008a(String str, Object obj) {
        return obj != null && m5013b(new File(str), obj.toString());
    }

    /* JADX INFO: renamed from: b */
    private static long m5009b(long j) {
        return Math.round((j / 1024.0d) / 1024.0d);
    }

    /* JADX INFO: renamed from: b */
    public static long m5010b(File file) {
        if (m5023f(file)) {
            return -1L;
        }
        try {
            return m5000a(file.getTotalSpace());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0L;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m5011b(String str) {
        return m5027i(new File(str));
    }

    /* JADX INFO: renamed from: b */
    public static String m5012b(String str, String str2) throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(C1966t.m5091b(new FileInputStream(str), str2));
            try {
                String line = bufferedReader2.readLine();
                C1956j.m5037a(bufferedReader2);
                return line;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                C1956j.m5037a(bufferedReader);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: b */
    public static boolean m5013b(File file, String str) {
        return m5006a(file, str.getBytes());
    }

    /* JADX INFO: renamed from: c */
    public static File m5014c(File file, String str) {
        if (file == null) {
            return null;
        }
        return m5015c(file.getAbsolutePath(), str);
    }

    /* JADX INFO: renamed from: c */
    public static File m5015c(String str, String str2) {
        if (str == null) {
            return null;
        }
        File file = TextUtils.isEmpty(str2) ? new File(str) : new File(str, str2);
        if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: renamed from: c */
    public static String m5016c(String str) {
        return m5012b(str, (String) null);
    }

    /* JADX INFO: renamed from: c */
    public static boolean m5017c(File file) {
        return m5021e(file) && file.isFile();
    }

    /* JADX INFO: renamed from: d */
    public static List<String> m5018d(String str) {
        return m5004a(new File(str), (String) null);
    }

    /* JADX INFO: renamed from: d */
    public static boolean m5019d(File file) {
        return m5021e(file) && file.isDirectory();
    }

    /* JADX INFO: renamed from: d */
    public static boolean m5020d(String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str2);
                try {
                    C1966t.m5088a(fileInputStream, fileOutputStream2);
                    C1956j.m5037a(fileInputStream, fileOutputStream2);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    C1956j.m5037a(fileInputStream, fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX INFO: renamed from: e */
    public static boolean m5021e(File file) {
        return file != null && file.exists();
    }

    /* JADX INFO: renamed from: e */
    public static boolean m5022e(String str) {
        return !TextUtils.isEmpty(str) && m5028j(new File(str));
    }

    /* JADX INFO: renamed from: f */
    public static boolean m5023f(File file) {
        return !m5021e(file);
    }

    /* JADX INFO: renamed from: f */
    public static boolean m5024f(String str) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + str);
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    /* JADX INFO: renamed from: g */
    public static boolean m5025g(File file) {
        return m5021e(file) && file.canRead();
    }

    /* JADX INFO: renamed from: h */
    public static byte[] m5026h(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        if (file == null) {
            return null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(bufferedInputStream.available());
                try {
                    C1966t.m5088a(bufferedInputStream, byteArrayOutputStream2);
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    C1956j.m5037a(bufferedInputStream, byteArrayOutputStream2);
                    return byteArray;
                } catch (Throwable th) {
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th = th;
                    C1956j.m5037a(bufferedInputStream, byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }

    /* JADX INFO: renamed from: i */
    public static String m5027i(File file) {
        return m5003a(file.getAbsolutePath(), (String) null);
    }

    /* JADX INFO: renamed from: j */
    public static boolean m5028j(File file) {
        if (file == null) {
            return false;
        }
        boolean z = true;
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            try {
                return file.delete();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return false;
            }
        }
        Stack stack = new Stack();
        stack.push(file);
        while (!stack.isEmpty()) {
            File[] fileArrListFiles = ((File) stack.peek()).listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                boolean z2 = z;
                for (File file2 : fileArrListFiles) {
                    if (!file2.isFile()) {
                        stack.push(file2);
                    } else if (!file2.delete()) {
                        z2 = false;
                    }
                }
                z = z2;
            } else if (!((File) stack.pop()).delete()) {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: k */
    public static boolean m5029k(File file) {
        return file != null && file.isDirectory() && file.canRead() && file.canWrite() && file.canExecute();
    }

    /* JADX INFO: renamed from: l */
    public static boolean m5030l(File file) {
        return file != null && m5024f(file.getAbsolutePath());
    }
}
