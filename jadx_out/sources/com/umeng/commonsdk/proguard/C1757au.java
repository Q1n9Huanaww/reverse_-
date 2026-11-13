package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.au */
/* JADX INFO: compiled from: TField.java */
/* JADX INFO: loaded from: classes.dex */
public class C1757au {

    /* JADX INFO: renamed from: a */
    public final String f4082a;

    /* JADX INFO: renamed from: b */
    public final byte f4083b;

    /* JADX INFO: renamed from: c */
    public final short f4084c;

    public C1757au() {
        this("", (byte) 0, (short) 0);
    }

    public C1757au(String str, byte b, short s) {
        this.f4082a = str;
        this.f4083b = b;
        this.f4084c = s;
    }

    public String toString() {
        return "<TField name:'" + this.f4082a + "' type:" + ((int) this.f4083b) + " field-id:" + ((int) this.f4084c) + ">";
    }

    /* JADX INFO: renamed from: a */
    public boolean m3915a(C1757au c1757au) {
        return this.f4083b == c1757au.f4083b && this.f4084c == c1757au.f4084c;
    }
}
