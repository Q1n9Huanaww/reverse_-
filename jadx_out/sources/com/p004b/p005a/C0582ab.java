package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.ab */
/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0582ab {

    /* JADX INFO: renamed from: a */
    final List<String> f450a = new ArrayList(20);

    /* JADX INFO: renamed from: a */
    public final C0582ab m329a(String str, String str2) {
        m326d(str, str2);
        return m330b(str, str2);
    }

    /* JADX INFO: renamed from: b */
    final C0582ab m330b(String str, String str2) {
        this.f450a.add(str);
        this.f450a.add(str2.trim());
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0582ab m328a(String str) {
        int i = 0;
        while (i < this.f450a.size()) {
            if (str.equalsIgnoreCase(this.f450a.get(i))) {
                this.f450a.remove(i);
                this.f450a.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    /* JADX INFO: renamed from: c */
    public final C0582ab m331c(String str, String str2) {
        m326d(str, str2);
        m328a(str);
        m330b(str, str2);
        return this;
    }

    /* JADX INFO: renamed from: d */
    private static void m326d(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                throw new IllegalArgumentException(C0503c.m110a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
            }
        }
        if (str2 == null) {
            throw new NullPointerException("value == null");
        }
        int length2 = str2.length();
        for (int i2 = 0; i2 < length2; i2++) {
            char cCharAt2 = str2.charAt(i2);
            if ((cCharAt2 <= 31 && cCharAt2 != '\t') || cCharAt2 >= 127) {
                throw new IllegalArgumentException(C0503c.m110a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i2), str, str2));
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final C0581aa m327a() {
        return new C0581aa(this);
    }
}
