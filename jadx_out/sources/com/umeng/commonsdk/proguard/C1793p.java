package com.umeng.commonsdk.proguard;

import android.os.Build;
import java.io.File;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.p */
/* JADX INFO: compiled from: Root.java */
/* JADX INFO: loaded from: classes.dex */
public class C1793p {
    /* JADX INFO: renamed from: a */
    public boolean m4052a() {
        return m4048b() || m4049c() || m4050d() || m4051e();
    }

    /* JADX INFO: renamed from: b */
    private boolean m4048b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* JADX INFO: renamed from: c */
    private boolean m4049c() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: d */
    private boolean m4050d() {
        return new C1790m().m4035a(C1790m.a.check_su_binary) != null;
    }

    /* JADX INFO: renamed from: e */
    private boolean m4051e() {
        for (String str : new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
