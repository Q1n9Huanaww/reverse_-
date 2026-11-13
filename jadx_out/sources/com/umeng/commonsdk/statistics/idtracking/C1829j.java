package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.j */
/* JADX INFO: compiled from: OldUMIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1829j extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4584a = "oldumid";

    /* JADX INFO: renamed from: b */
    private Context f4585b;

    /* JADX INFO: renamed from: c */
    private String f4586c;

    /* JADX INFO: renamed from: d */
    private String f4587d;

    public C1829j(Context context) {
        super(f4584a);
        this.f4586c = null;
        this.f4587d = null;
        this.f4585b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return this.f4586c;
    }

    /* JADX INFO: renamed from: g */
    public boolean m4483g() {
        return m4484h();
    }

    /* JADX INFO: renamed from: h */
    public boolean m4484h() throws Throwable {
        this.f4587d = UMEnvelopeBuild.imprintProperty(this.f4585b, C1799v.f4314e, null);
        if (TextUtils.isEmpty(this.f4587d)) {
            return false;
        }
        this.f4587d = DataHelper.encryptBySHA1(this.f4587d);
        String file = HelperUtils.readFile(new File("/sdcard/Android/data/.um/sysid.dat"));
        String file2 = HelperUtils.readFile(new File("/sdcard/Android/obj/.um/sysid.dat"));
        String file3 = HelperUtils.readFile(new File("/data/local/tmp/.um/sysid.dat"));
        if (TextUtils.isEmpty(file)) {
            m4482l();
        } else if (!this.f4587d.equals(file)) {
            this.f4586c = file;
            return true;
        }
        if (TextUtils.isEmpty(file2)) {
            m4481k();
        } else if (!this.f4587d.equals(file2)) {
            this.f4586c = file2;
            return true;
        }
        if (TextUtils.isEmpty(file3)) {
            m4480j();
            return false;
        }
        if (this.f4587d.equals(file3)) {
            return false;
        }
        this.f4586c = file3;
        return true;
    }

    /* JADX INFO: renamed from: i */
    public void m4485i() {
        try {
            m4482l();
            m4481k();
            m4480j();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: j */
    private void m4480j() {
        try {
            m4479b("/data/local/tmp/.um");
            HelperUtils.writeFile(new File("/data/local/tmp/.um/sysid.dat"), this.f4587d);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: k */
    private void m4481k() {
        try {
            m4479b("/sdcard/Android/obj/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.f4587d);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: l */
    private void m4482l() {
        try {
            m4479b("/sdcard/Android/data/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.f4587d);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m4479b(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
