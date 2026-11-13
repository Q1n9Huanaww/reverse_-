package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1770bg;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.common.C1817e;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.umeng.commonsdk.stateless.f */
/* JADX INFO: compiled from: UMSLUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1809f {

    /* JADX INFO: renamed from: a */
    public static int f4478a;

    /* JADX INFO: renamed from: b */
    private static final byte[] f4479b = {10, 1, 11, 5, 4, C1770bg.f4125m, 7, 9, 23, 3, 1, 6, 8, 12, C1770bg.f4123k, 91};

    /* JADX INFO: renamed from: c */
    private static Object f4480c = new Object();

    /* JADX INFO: renamed from: a */
    public static boolean m4311a(long j, long j2) {
        return j > j2;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x00d5 */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m4312a(Context context, String str, String str2, byte[] bArr) throws Throwable {
        boolean z;
        String str3;
        Object[] objArr;
        if (context == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                synchronized (f4480c) {
                    try {
                        C1817e.m4397a("walle", "[stateless] begin write envelope, thread is " + Thread.currentThread());
                        File file = new File(context.getFilesDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + C1804a.f4397c);
                        if (!file.isDirectory()) {
                            file.mkdir();
                        }
                        File file2 = new File(file.getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
                        if (!file2.isDirectory()) {
                            file2.mkdir();
                        }
                        File file3 = new File(file2.getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR + str2);
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                        try {
                            fileOutputStream2.write(bArr);
                            fileOutputStream2.close();
                            try {
                                C1817e.m4397a("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                                return true;
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                while (true) {
                                    break;
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            z = false;
                            while (true) {
                                break;
                                break;
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                try {
                    break;
                    throw th;
                } catch (IOException e) {
                    e = e;
                    C1817e.m4397a("walle", "[stateless] write envelope, e is " + e.getMessage());
                    C1782e.m3972a(context, e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                    }
                    str3 = "walle";
                    objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
                    C1817e.m4397a(str3, objArr);
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                    C1817e.m4397a("walle", "[stateless] write envelope, e is " + th.getMessage());
                    C1782e.m3972a(context, th);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            ThrowableExtension.printStackTrace(e3);
                        }
                    }
                    str3 = "walle";
                    objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
                    C1817e.m4397a(str3, objArr);
                    return z;
                }
            } catch (Throwable th5) {
                if (0 != 0) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        ThrowableExtension.printStackTrace(e4);
                    }
                }
                C1817e.m4397a("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                throw th5;
            }
        } catch (IOException e5) {
            e = e5;
            z = false;
        } catch (Throwable th6) {
            th = th6;
            z = false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX INFO: renamed from: a */
    public static byte[] m4313a(String str) throws IOException {
        Throwable th;
        IOException e;
        byte[] bArr;
        synchronized (f4480c) {
            C1817e.m4397a("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                try {
                    FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                    try {
                        MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                        System.out.println(mappedByteBufferLoad.isLoaded());
                        bArr = new byte[(int) channel.size()];
                        if (mappedByteBufferLoad.remaining() > 0) {
                            mappedByteBufferLoad.get(bArr, 0, mappedByteBufferLoad.remaining());
                        }
                        C1817e.m4397a("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException e2) {
                                ThrowableExtension.printStackTrace(e2);
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        C1817e.m4397a("walle", "[stateless] write envelope, e is " + e.getMessage());
                        throw e;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (str != 0) {
                        try {
                            str.close();
                        } catch (IOException e4) {
                            ThrowableExtension.printStackTrace(e4);
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
                if (str != 0) {
                    str.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    public static File m4309a(Context context) {
        File file;
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        File file2 = null;
        try {
            synchronized (f4480c) {
                try {
                    C1817e.m4397a("walle", "get last envelope begin, thread is " + Thread.currentThread());
                    if (context != null && context.getApplicationContext() != null) {
                        String str = context.getApplicationContext().getFilesDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + C1804a.f4397c;
                        if (!TextUtils.isEmpty(str) && (file = new File(str)) != null && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
                            File file3 = null;
                            for (File file4 : fileArrListFiles) {
                                try {
                                    if (file4 != null && file4.isDirectory() && (fileArrListFiles2 = file4.listFiles()) != null && fileArrListFiles2.length > 0) {
                                        Arrays.sort(fileArrListFiles2, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.1
                                            @Override // java.util.Comparator
                                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                                            public int compare(File file5, File file6) {
                                                long jLastModified = file5.lastModified() - file6.lastModified();
                                                if (jLastModified > 0) {
                                                    return 1;
                                                }
                                                return jLastModified == 0 ? 0 : -1;
                                            }
                                        });
                                        File file5 = fileArrListFiles2[0];
                                        if (file5 != null && (file3 == null || file3.lastModified() > file5.lastModified())) {
                                            file3 = file5;
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    file2 = file3;
                                    throw th;
                                }
                            }
                            file2 = file3;
                        }
                    }
                    C1817e.m4397a("walle", "get last envelope end, thread is " + Thread.currentThread());
                    return file2;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            C1782e.m3972a(context, th3);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4310a(Context context, String str, int i) {
        try {
            if (str == null) {
                C1817e.m4397a("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            File file = new File(str);
            if (!file.isDirectory()) {
                C1817e.m4397a("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (f4480c) {
                File[] fileArrListFiles = file.listFiles();
                C1817e.m4397a("AmapLBS", "[lbs-build] delete file begin " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
                if (fileArrListFiles != null && fileArrListFiles.length >= i) {
                    C1817e.m4397a("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : fileArrListFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList != null && arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.2
                            @Override // java.util.Comparator
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public int compare(File file3, File file4) {
                                if (file3 == null || file4 == null || file3.lastModified() >= file4.lastModified()) {
                                    return (file3 == null || file4 == null || file3.lastModified() != file4.lastModified()) ? 1 : 0;
                                }
                                return -1;
                            }
                        });
                        if (C1817e.f4515a) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                C1817e.m4397a("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList != null && arrayList.get(i3) != null) {
                                C1817e.m4397a("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                } else {
                    C1817e.m4397a("AmapLBS", "[lbs-build] file size < max");
                }
                C1817e.m4397a("AmapLBS", "[lbs-build] delete file end " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
            }
        } catch (Throwable th) {
            C1782e.m3972a(context, th);
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m4314a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        f4478a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int iDeflate = deflater.deflate(bArr2);
                    f4478a += iDeflate;
                    byteArrayOutputStream.write(bArr2, 0, iDeflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m4315a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f4479b));
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m4316b(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m4317c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }
}
