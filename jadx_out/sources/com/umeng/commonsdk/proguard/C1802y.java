package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.y */
/* JADX INFO: compiled from: ShortStack.java */
/* JADX INFO: loaded from: classes.dex */
public class C1802y {

    /* JADX INFO: renamed from: a */
    private short[] f4380a;

    /* JADX INFO: renamed from: b */
    private int f4381b = -1;

    public C1802y(int i) {
        this.f4380a = new short[i];
    }

    /* JADX INFO: renamed from: a */
    public short m4204a() {
        short[] sArr = this.f4380a;
        int i = this.f4381b;
        this.f4381b = i - 1;
        return sArr[i];
    }

    /* JADX INFO: renamed from: a */
    public void m4205a(short s) {
        if (this.f4380a.length == this.f4381b + 1) {
            m4203d();
        }
        short[] sArr = this.f4380a;
        int i = this.f4381b + 1;
        this.f4381b = i;
        sArr[i] = s;
    }

    /* JADX INFO: renamed from: d */
    private void m4203d() {
        short[] sArr = new short[this.f4380a.length * 2];
        System.arraycopy(this.f4380a, 0, sArr, 0, this.f4380a.length);
        this.f4380a = sArr;
    }

    /* JADX INFO: renamed from: b */
    public short m4206b() {
        return this.f4380a[this.f4381b];
    }

    /* JADX INFO: renamed from: c */
    public void m4207c() {
        this.f4381b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.f4380a.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.f4381b) {
                sb.append(">>");
            }
            sb.append((int) this.f4380a[i]);
            if (i == this.f4381b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
