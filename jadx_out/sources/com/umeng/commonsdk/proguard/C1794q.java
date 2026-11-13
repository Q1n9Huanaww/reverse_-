package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.umeng.commonsdk.framework.C1731a;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.q */
/* JADX INFO: compiled from: SDStorage.java */
/* JADX INFO: loaded from: classes.dex */
public class C1794q {

    /* JADX INFO: renamed from: b */
    private static final String f4220b = "/.um/.umm.dat";

    /* JADX INFO: renamed from: c */
    private static final String f4221c = "/.uxx/.cca.dat";

    /* JADX INFO: renamed from: d */
    private static final String f4222d = "/.cc/.adfwe.dat";

    /* JADX INFO: renamed from: e */
    private static final String f4223e = "/.a.dat";

    /* JADX INFO: renamed from: f */
    private static final String f4224f = "umdat";

    /* JADX INFO: renamed from: a */
    private Context f4225a;

    public C1794q(Context context) {
        this.f4225a = context;
    }

    /* JADX INFO: renamed from: a */
    public String m4058a() {
        String strM4060b = m4060b();
        if (strM4060b == null || strM4060b.equals("")) {
            strM4060b = m4065g();
        }
        if (strM4060b == null || strM4060b.equals("")) {
            strM4060b = m4061c();
        }
        if (strM4060b == null || strM4060b.equals("")) {
            strM4060b = m4062d();
        }
        if (strM4060b == null || strM4060b.equals("")) {
            strM4060b = m4063e();
        }
        return (strM4060b == null || strM4060b.equals("")) ? m4064f() : strM4060b;
    }

    /* JADX INFO: renamed from: b */
    public String m4060b() {
        return m4056h();
    }

    /* JADX INFO: renamed from: c */
    public String m4061c() {
        return m4054b(f4220b);
    }

    /* JADX INFO: renamed from: d */
    public String m4062d() {
        return m4054b(f4221c);
    }

    /* JADX INFO: renamed from: e */
    public String m4063e() {
        return m4054b(f4222d);
    }

    /* JADX INFO: renamed from: f */
    public String m4064f() {
        return m4054b(f4223e);
    }

    /* JADX INFO: renamed from: g */
    public String m4065g() {
        return m4057i();
    }

    /* JADX INFO: renamed from: h */
    private String m4056h() {
        return C1731a.m3709a(this.f4225a, C1799v.f4313d, (String) null);
    }

    /* JADX INFO: renamed from: a */
    public void m4059a(String str) throws Throwable {
        m4053a(str, f4220b);
        m4053a(str, f4221c);
        m4053a(str, f4222d);
        m4053a(str, f4223e);
        m4055c(str);
    }

    /* JADX INFO: renamed from: a */
    private void m4053a(String str, String str2) throws Throwable {
        if (DeviceConfig.checkPermission(this.f4225a, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                    return;
                }
                String strM4054b = m4054b(str2);
                if (strM4054b == null || !strM4054b.equals(str)) {
                    File file = new File(Environment.getExternalStorageDirectory() + str2);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdir();
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + str2, "rw");
                    randomAccessFile.setLength((long) str.getBytes().length);
                    FileChannel channel = randomAccessFile.getChannel();
                    FileLock fileLockTryLock = channel.tryLock();
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
                    byteBufferAllocate.clear();
                    byteBufferAllocate.put(str.getBytes());
                    byteBufferAllocate.flip();
                    while (byteBufferAllocate.hasRemaining()) {
                        channel.write(byteBufferAllocate);
                    }
                    channel.force(true);
                    if (fileLockTryLock != null) {
                        fileLockTryLock.release();
                    }
                    channel.close();
                }
            } catch (Exception e) {
                if (e != null) {
                    C1817e.m4418e("saveFileUmtt:" + e.getMessage());
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private String m4054b(String str) throws Throwable {
        String externalStorageState;
        try {
            if (!DeviceConfig.checkPermission(this.f4225a, "android.permission.READ_EXTERNAL_STORAGE") || (externalStorageState = Environment.getExternalStorageState()) == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                return null;
            }
            if (!new File(Environment.getExternalStorageDirectory() + str).exists()) {
                return null;
            }
            FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + str, "rw").getChannel();
            FileLock fileLockTryLock = channel.tryLock();
            StringBuilder sb = new StringBuilder();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
            byteBufferAllocate.clear();
            while (channel.read(byteBufferAllocate) != -1) {
                byte[] bArr = new byte[byteBufferAllocate.position()];
                for (int i = 0; i < byteBufferAllocate.position(); i++) {
                    bArr[i] = byteBufferAllocate.get(i);
                }
                sb.append(new String(bArr));
                byteBufferAllocate.clear();
            }
            if (channel != null) {
                fileLockTryLock.release();
            }
            channel.close();
            return sb.toString();
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            C1817e.m4418e("getFileUmtt:" + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private void m4055c(String str) {
        SharedPreferences sharedPreferences = this.f4225a.getApplicationContext().getSharedPreferences(f4224f, 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("u", null);
            if (string == null || !string.equals(str)) {
                sharedPreferences.edit().putString("u", str).commit();
            }
        }
    }

    /* JADX INFO: renamed from: i */
    private String m4057i() {
        SharedPreferences sharedPreferences = this.f4225a.getApplicationContext().getSharedPreferences(f4224f, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("u", null);
        }
        return null;
    }
}
