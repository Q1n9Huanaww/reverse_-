package com.p004b.p005a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.y */
/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0628y {

    /* JADX INFO: renamed from: a */
    private final List<String> f793a = new ArrayList();

    /* JADX INFO: renamed from: b */
    private final List<String> f794b = new ArrayList();

    /* JADX INFO: renamed from: a */
    public final C0628y m504a(String str, String str2) {
        this.f793a.add(C0583ac.m336a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        this.f794b.add(C0583ac.m336a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0627x m503a() {
        return new C0627x(this.f793a, this.f794b);
    }
}
