package com.umeng.commonsdk.stateless;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1746aj;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.stateless.c */
/* JADX INFO: compiled from: UMSLEnvelopeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1806c {

    /* JADX INFO: renamed from: e */
    private String f4454e;

    /* JADX INFO: renamed from: j */
    private int f4459j;

    /* JADX INFO: renamed from: k */
    private int f4460k;

    /* JADX INFO: renamed from: l */
    private byte[] f4461l;

    /* JADX INFO: renamed from: m */
    private byte[] f4462m;

    /* JADX INFO: renamed from: a */
    private final byte[] f4450a = {0, 0, 0, 0, 0, 0, 0, 0};

    /* JADX INFO: renamed from: b */
    private final int f4451b = 1;

    /* JADX INFO: renamed from: c */
    private final int f4452c = 0;

    /* JADX INFO: renamed from: d */
    private String f4453d = "1.0";

    /* JADX INFO: renamed from: f */
    private byte[] f4455f = null;

    /* JADX INFO: renamed from: g */
    private byte[] f4456g = null;

    /* JADX INFO: renamed from: h */
    private byte[] f4457h = null;

    /* JADX INFO: renamed from: i */
    private int f4458i = 0;

    /* JADX INFO: renamed from: n */
    private boolean f4463n = false;

    private C1806c(byte[] bArr, String str, byte[] bArr2) throws Exception {
        this.f4454e = null;
        this.f4459j = 0;
        this.f4460k = 0;
        this.f4461l = null;
        this.f4462m = null;
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.f4454e = str;
        this.f4460k = bArr.length;
        this.f4461l = C1809f.m4314a(bArr);
        this.f4459j = (int) (System.currentTimeMillis() / 1000);
        this.f4462m = bArr2;
    }

    /* JADX INFO: renamed from: a */
    public static C1806c m4288a(Context context, String str, byte[] bArr) throws Throwable {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("[stateless] build envelope, raw is  ");
            sb.append(bArr == null);
            sb.append("m app key is ");
            sb.append(str);
            sb.append("device id is ");
            sb.append(deviceId);
            sb.append(", mac is ");
            sb.append(mac);
            objArr[0] = sb.toString();
            C1817e.m4397a("walle", objArr);
            C1806c c1806c = new C1806c(bArr, str, (deviceId + mac).getBytes());
            c1806c.m4293a();
            return c1806c;
        } catch (Exception e) {
            C1817e.m4397a("walle", "[stateless] build envelope, e is " + e.getMessage());
            C1782e.m3972a(context, e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static C1806c m4290b(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            C1806c c1806c = new C1806c(bArr, str, (DeviceConfig.getDeviceId(context) + mac).getBytes());
            c1806c.m4294a(true);
            c1806c.m4293a();
            return c1806c;
        } catch (Exception e) {
            C1782e.m3972a(context, e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4294a(boolean z) {
        this.f4463n = z;
    }

    /* JADX INFO: renamed from: a */
    public void m4293a() {
        if (this.f4455f == null) {
            this.f4455f = m4291c();
        }
        if (this.f4463n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f4455f, 1, bArr, 0, 16);
                this.f4461l = C1809f.m4315a(this.f4461l, bArr);
            } catch (Exception unused) {
            }
        }
        this.f4456g = m4289a(this.f4455f, this.f4459j);
        this.f4457h = m4292d();
    }

    /* JADX INFO: renamed from: a */
    private byte[] m4289a(byte[] bArr, int i) {
        byte[] bArrM4316b = C1809f.m4316b(this.f4462m);
        byte[] bArrM4316b2 = C1809f.m4316b(this.f4461l);
        int length = bArrM4316b.length;
        byte[] bArr2 = new byte[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr2[i3] = bArrM4316b2[i2];
            bArr2[i3 + 1] = bArrM4316b[i2];
        }
        for (int i4 = 0; i4 < 2; i4++) {
            bArr2[i4] = bArr[i4];
            bArr2[(bArr2.length - i4) - 1] = bArr[(bArr.length - i4) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i5 = 0; i5 < bArr2.length; i5++) {
            bArr2[i5] = (byte) (bArr2[i5] ^ bArr3[i5 % 4]);
        }
        return bArr2;
    }

    /* JADX INFO: renamed from: c */
    private byte[] m4291c() {
        return m4289a(this.f4450a, (int) (System.currentTimeMillis() / 1000));
    }

    /* JADX INFO: renamed from: d */
    private byte[] m4292d() {
        return C1809f.m4316b((C1809f.m4317c(this.f4455f) + this.f4458i + this.f4459j + this.f4460k + C1809f.m4317c(this.f4456g)).getBytes());
    }

    /* JADX INFO: renamed from: b */
    public byte[] m4295b() {
        C1805b c1805b = new C1805b();
        c1805b.m4233a(this.f4453d);
        c1805b.m4238b(this.f4454e);
        c1805b.m4242c(C1809f.m4317c(this.f4455f));
        c1805b.m4232a(this.f4458i);
        c1805b.m4237b(this.f4459j);
        c1805b.m4241c(this.f4460k);
        c1805b.m4235a(this.f4461l);
        c1805b.m4245d(this.f4463n ? 1 : 0);
        c1805b.m4246d(C1809f.m4317c(this.f4456g));
        c1805b.m4250e(C1809f.m4317c(this.f4457h));
        try {
            return new C1746aj().m3809a(c1805b);
        } catch (Exception unused) {
            return null;
        }
    }
}
