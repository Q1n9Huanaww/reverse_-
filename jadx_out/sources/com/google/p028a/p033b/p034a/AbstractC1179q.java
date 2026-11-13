package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.q */
/* JADX INFO: compiled from: ParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final int f2034a;

    /* JADX INFO: renamed from: a */
    public abstract String mo1571a();

    protected AbstractC1179q(int i) {
        this.f2034a = i;
    }

    /* JADX INFO: renamed from: b */
    private int m1701b() {
        return this.f2034a;
    }

    public final String toString() {
        return mo1571a();
    }

    /* JADX INFO: renamed from: a */
    public static void m1699a(String str, StringBuilder sb) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append('\n');
        }
        sb.append(str);
    }

    /* JADX INFO: renamed from: a */
    public static void m1700a(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                m1699a(str, sb);
            }
        }
    }
}
