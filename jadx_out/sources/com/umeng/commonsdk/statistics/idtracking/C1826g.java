package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1740ad;
import com.umeng.commonsdk.proguard.C1746aj;
import com.umeng.commonsdk.statistics.C1811b;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.InterfaceC1845d;
import com.umeng.commonsdk.statistics.proto.C1855d;
import com.umeng.commonsdk.statistics.proto.C1856e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.g */
/* JADX INFO: compiled from: ImprintHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class C1826g {

    /* JADX INFO: renamed from: a */
    private static final String f4570a = "ImprintHandler";

    /* JADX INFO: renamed from: c */
    private static final String f4572c = ".imprint";

    /* JADX INFO: renamed from: h */
    private static C1826g f4574h;

    /* JADX INFO: renamed from: i */
    private static Context f4575i;

    /* JADX INFO: renamed from: e */
    private InterfaceC1845d f4576e;

    /* JADX INFO: renamed from: f */
    private a f4577f = new a();

    /* JADX INFO: renamed from: g */
    private C1855d f4578g = null;

    /* JADX INFO: renamed from: b */
    private static Object f4571b = new Object();

    /* JADX INFO: renamed from: d */
    private static final byte[] f4573d = "pbl0".getBytes();

    private C1826g(Context context) {
        f4575i = context.getApplicationContext();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1826g m4461a(Context context) {
        if (f4574h == null) {
            f4574h = new C1826g(context);
            f4574h.m4466e();
        }
        return f4574h;
    }

    /* JADX INFO: renamed from: a */
    public void m4469a(InterfaceC1845d interfaceC1845d) {
        this.f4576e = interfaceC1845d;
    }

    /* JADX INFO: renamed from: a */
    public String m4468a(C1855d c1855d) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(c1855d.m4638c()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((C1856e) entry.getValue()).m4674d()) {
                sb.append(((C1856e) entry.getValue()).m4670b());
            }
            sb.append(((C1856e) entry.getValue()).m4675e());
            sb.append(((C1856e) entry.getValue()).m4678h());
        }
        sb.append(c1855d.f4703b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }

    /* JADX INFO: renamed from: c */
    private boolean m4464c(C1855d c1855d) {
        if (!c1855d.m4645i().equals(m4468a(c1855d))) {
            return false;
        }
        for (C1856e c1856e : c1855d.m4638c().values()) {
            byte[] bArrReverseHexString = DataHelper.reverseHexString(c1856e.m4678h());
            byte[] bArrM4470a = m4470a(c1856e);
            for (int i = 0; i < 4; i++) {
                if (bArrReverseHexString[i] != bArrM4470a[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public byte[] m4470a(C1856e c1856e) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(null);
        byteBufferAllocate.putLong(c1856e.m4675e());
        byte[] bArrArray = byteBufferAllocate.array();
        byte[] bArr = f4573d;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (bArrArray[i] ^ bArr[i]);
        }
        return bArr2;
    }

    /* JADX INFO: renamed from: b */
    public void m4472b(C1855d c1855d) throws Throwable {
        C1855d c1855dM4462a;
        boolean z;
        if (c1855d == null) {
            if (C1811b.f4488f) {
                MLog.m4331d("Imprint is null");
                return;
            }
            return;
        }
        if (!m4464c(c1855d)) {
            if (C1811b.f4488f) {
                MLog.m4337e("Imprint is not valid");
                return;
            }
            return;
        }
        if (C1811b.f4488f) {
            MLog.m4331d("Imprint is ok");
        }
        synchronized (this) {
            C1855d c1855d2 = this.f4578g;
            String strM4645i = null;
            String strM4645i2 = c1855d2 == null ? null : c1855d2.m4645i();
            if (c1855d2 == null) {
                c1855dM4462a = m4465d(c1855d);
            } else {
                c1855dM4462a = m4462a(c1855d2, c1855d);
            }
            this.f4578g = c1855dM4462a;
            if (c1855dM4462a != null) {
                strM4645i = c1855dM4462a.m4645i();
            }
            z = !m4463a(strM4645i2, strM4645i);
        }
        if (this.f4578g != null) {
            boolean z2 = C1811b.f4488f;
            if (z) {
                this.f4577f.m4477a(this.f4578g);
                if (this.f4576e != null) {
                    this.f4576e.onImprintChanged(this.f4577f);
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m4463a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    /* JADX INFO: renamed from: a */
    private C1855d m4462a(C1855d c1855d, C1855d c1855d2) {
        if (c1855d2 == null) {
            return c1855d;
        }
        Map<String, C1856e> mapM4638c = c1855d.m4638c();
        for (Map.Entry<String, C1856e> entry : c1855d2.m4638c().entrySet()) {
            if (entry.getValue().m4674d()) {
                mapM4638c.put(entry.getKey(), entry.getValue());
            } else {
                String key = entry.getKey();
                mapM4638c.remove(key);
                this.f4577f.m4478a(key);
            }
        }
        c1855d.m4630a(c1855d2.m4642f());
        c1855d.m4631a(m4468a(c1855d));
        return c1855d;
    }

    /* JADX INFO: renamed from: d */
    private C1855d m4465d(C1855d c1855d) {
        Map<String, C1856e> mapM4638c = c1855d.m4638c();
        ArrayList arrayList = new ArrayList(mapM4638c.size() / 2);
        for (Map.Entry<String, C1856e> entry : mapM4638c.entrySet()) {
            if (!entry.getValue().m4674d()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            mapM4638c.remove((String) it2.next());
        }
        return c1855d;
    }

    /* JADX INFO: renamed from: a */
    public synchronized C1855d m4467a() {
        return this.f4578g;
    }

    /* JADX INFO: renamed from: b */
    public a m4471b() {
        return this.f4577f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX INFO: renamed from: e */
    private void m4466e() {
        FileInputStream fileInputStreamOpenFileInput;
        File filesDir = f4575i.getFilesDir();
        Object obj = f4572c;
        File file = new File(filesDir, f4572c);
        synchronized (f4571b) {
            if (file.exists()) {
                ?? r0 = 0;
                byte[] bArr = null;
                try {
                    try {
                        fileInputStreamOpenFileInput = f4575i.openFileInput(f4572c);
                        try {
                            byte[] streamToByteArray = HelperUtils.readStreamToByteArray(fileInputStreamOpenFileInput);
                            HelperUtils.safeClose(fileInputStreamOpenFileInput);
                            bArr = streamToByteArray;
                            obj = fileInputStreamOpenFileInput;
                        } catch (Exception e) {
                            e = e;
                            ThrowableExtension.printStackTrace(e);
                            HelperUtils.safeClose(fileInputStreamOpenFileInput);
                            obj = fileInputStreamOpenFileInput;
                        }
                    } catch (Throwable th) {
                        Object obj2 = obj;
                        th = th;
                        r0 = obj2;
                        HelperUtils.safeClose((InputStream) r0);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStreamOpenFileInput = null;
                } catch (Throwable th2) {
                    th = th2;
                    HelperUtils.safeClose((InputStream) r0);
                    throw th;
                }
                if (bArr != null) {
                    try {
                        C1855d c1855d = new C1855d();
                        new C1740ad().m3795a(c1855d, bArr);
                        this.f4578g = c1855d;
                        this.f4577f.m4477a(c1855d);
                    } catch (Exception e3) {
                        ThrowableExtension.printStackTrace(e3);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public void m4473c() {
        if (this.f4578g == null) {
            return;
        }
        try {
            synchronized (f4571b) {
                byte[] bArrM3809a = new C1746aj().m3809a(this.f4578g);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(f4575i.getFilesDir(), f4572c));
                try {
                    fileOutputStream.write(bArrM3809a);
                    fileOutputStream.flush();
                    HelperUtils.safeClose(fileOutputStream);
                } catch (Throwable th) {
                    HelperUtils.safeClose(fileOutputStream);
                    throw th;
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: d */
    public boolean m4474d() {
        return new File(f4575i.getFilesDir(), f4572c).delete();
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.g$a */
    /* JADX INFO: compiled from: ImprintHandler.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        private Map<String, String> f4579a = new HashMap();

        a() {
        }

        /* JADX INFO: renamed from: a */
        public synchronized void m4478a(String str) {
            if (this.f4579a != null && this.f4579a.size() > 0 && !TextUtils.isEmpty(str) && this.f4579a.containsKey(str)) {
                this.f4579a.remove(str);
            }
        }

        a(C1855d c1855d) {
            m4477a(c1855d);
        }

        /* JADX INFO: renamed from: a */
        public void m4477a(C1855d c1855d) {
            if (c1855d == null) {
                return;
            }
            m4475b(c1855d);
        }

        /* JADX INFO: renamed from: b */
        private synchronized void m4475b(C1855d c1855d) {
            C1856e c1856e;
            if (c1855d != null) {
                try {
                    if (c1855d.m4641e()) {
                        Map<String, C1856e> mapM4638c = c1855d.m4638c();
                        for (String str : mapM4638c.keySet()) {
                            if (!TextUtils.isEmpty(str) && (c1856e = mapM4638c.get(str)) != null) {
                                String strM4670b = c1856e.m4670b();
                                if (TextUtils.isEmpty(strM4670b)) {
                                    continue;
                                } else {
                                    this.f4579a.put(str, strM4670b);
                                    if (C1811b.f4488f) {
                                        Log.i(C1826g.f4570a, "imKey is " + str + ", imValue is " + strM4670b);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }

        /* JADX INFO: renamed from: a */
        public synchronized String m4476a(String str, String str2) {
            if (!TextUtils.isEmpty(str) && this.f4579a.size() > 0) {
                String str3 = this.f4579a.get(str);
                return !TextUtils.isEmpty(str3) ? str3 : str2;
            }
            return str2;
        }
    }
}
