package com.umeng.commonsdk.proguard;

import java.io.Serializable;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.an */
/* JADX INFO: compiled from: FieldValueMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public class C1750an implements Serializable {

    /* JADX INFO: renamed from: a */
    private final boolean f4025a;

    /* JADX INFO: renamed from: b */
    public final byte f4026b;

    /* JADX INFO: renamed from: c */
    private final String f4027c;

    /* JADX INFO: renamed from: d */
    private final boolean f4028d;

    public C1750an(byte b, boolean z) {
        this.f4026b = b;
        this.f4025a = false;
        this.f4027c = null;
        this.f4028d = z;
    }

    public C1750an(byte b) {
        this(b, false);
    }

    public C1750an(byte b, String str) {
        this.f4026b = b;
        this.f4025a = true;
        this.f4027c = str;
        this.f4028d = false;
    }

    /* JADX INFO: renamed from: a */
    public boolean m3843a() {
        return this.f4025a;
    }

    /* JADX INFO: renamed from: b */
    public String m3844b() {
        return this.f4027c;
    }

    /* JADX INFO: renamed from: c */
    public boolean m3845c() {
        return this.f4026b == 12;
    }

    /* JADX INFO: renamed from: d */
    public boolean m3846d() {
        return this.f4026b == 15 || this.f4026b == 13 || this.f4026b == 14;
    }

    /* JADX INFO: renamed from: e */
    public boolean m3847e() {
        return this.f4028d;
    }
}
