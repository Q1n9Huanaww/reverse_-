package com.p004b.p005a;

import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: renamed from: com.b.a.aa */
/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0581aa {

    /* JADX INFO: renamed from: a */
    private final String[] f449a;

    C0581aa(C0582ab c0582ab) {
        this.f449a = (String[]) c0582ab.f450a.toArray(new String[c0582ab.f450a.size()]);
    }

    /* JADX INFO: renamed from: a */
    public final String m323a(String str) {
        String[] strArr = this.f449a;
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public final int m321a() {
        return this.f449a.length / 2;
    }

    /* JADX INFO: renamed from: a */
    public final String m322a(int i) {
        return this.f449a[i * 2];
    }

    /* JADX INFO: renamed from: b */
    public final String m325b(int i) {
        return this.f449a[(i * 2) + 1];
    }

    /* JADX INFO: renamed from: b */
    public final C0582ab m324b() {
        C0582ab c0582ab = new C0582ab();
        Collections.addAll(c0582ab.f450a, this.f449a);
        return c0582ab;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0581aa) && Arrays.equals(((C0581aa) obj).f449a, this.f449a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f449a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.f449a.length / 2;
        for (int i = 0; i < length; i++) {
            sb.append(m322a(i));
            sb.append(": ");
            sb.append(m325b(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
