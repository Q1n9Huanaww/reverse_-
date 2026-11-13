package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.os.Environment;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.s */
/* JADX INFO: compiled from: UTDIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1838s extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4604a = "utdid";

    /* JADX INFO: renamed from: b */
    private static final String f4605b = "android.permission.WRITE_EXTERNAL_STORAGE";

    /* JADX INFO: renamed from: c */
    private static final Pattern f4606c = Pattern.compile("UTDID\">([^<]+)");

    /* JADX INFO: renamed from: d */
    private Context f4607d;

    public C1838s(Context context) {
        super(f4604a);
        this.f4607d = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.f4607d);
        } catch (Exception unused) {
            return m4487g();
        }
    }

    /* JADX INFO: renamed from: g */
    private String m4487g() {
        File fileM4488h = m4488h();
        if (fileM4488h == null || !fileM4488h.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileM4488h);
            try {
                return m4486b(HelperUtils.readStreamToString(fileInputStream));
            } finally {
                HelperUtils.safeClose(fileInputStream);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private String m4486b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f4606c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /* JADX INFO: renamed from: h */
    private File m4488h() {
        if (DeviceConfig.checkPermission(this.f4607d, f4605b) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
