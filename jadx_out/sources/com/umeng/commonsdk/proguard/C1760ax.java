package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ax */
/* JADX INFO: compiled from: TMessage.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1760ax {

    /* JADX INFO: renamed from: a */
    public final String f4090a;

    /* JADX INFO: renamed from: b */
    public final byte f4091b;

    /* JADX INFO: renamed from: c */
    public final int f4092c;

    public C1760ax() {
        this("", (byte) 0, 0);
    }

    public C1760ax(String str, byte b, int i) {
        this.f4090a = str;
        this.f4091b = b;
        this.f4092c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.f4090a + "' type: " + ((int) this.f4091b) + " seqid:" + this.f4092c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof C1760ax) {
            return m3916a((C1760ax) obj);
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    public boolean m3916a(C1760ax c1760ax) {
        return this.f4090a.equals(c1760ax.f4090a) && this.f4091b == c1760ax.f4091b && this.f4092c == c1760ax.f4092c;
    }
}
