package com.iflytek.voiceads.videolib;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.o */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1617o {

    /* JADX INFO: renamed from: a */
    public static JZPlayer f3525a;

    /* JADX INFO: renamed from: b */
    public static JZPlayer f3526b;

    /* JADX INFO: renamed from: a */
    public static JZPlayer m3313a() {
        return f3525a;
    }

    /* JADX INFO: renamed from: a */
    public static void m3314a(JZPlayer jZPlayer) {
        f3525a = jZPlayer;
    }

    /* JADX INFO: renamed from: b */
    public static JZPlayer m3315b() {
        return f3526b;
    }

    /* JADX INFO: renamed from: b */
    public static void m3316b(JZPlayer jZPlayer) {
        f3526b = jZPlayer;
    }

    /* JADX INFO: renamed from: c */
    public static JZPlayer m3317c() {
        return m3315b() != null ? m3315b() : m3313a();
    }

    /* JADX INFO: renamed from: d */
    public static void m3318d() {
        if (f3526b != null) {
            f3526b.mo3264u();
            f3526b = null;
        }
        if (f3525a != null) {
            f3525a.mo3264u();
            f3525a = null;
        }
    }
}
