package com.p004b.p015b;

import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: renamed from: com.b.b.u */
/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
final class C0651u {

    /* JADX INFO: renamed from: a */
    static C0650t f850a;

    /* JADX INFO: renamed from: b */
    static long f851b;

    private C0651u() {
    }

    /* JADX INFO: renamed from: a */
    static C0650t m615a() {
        synchronized (C0651u.class) {
            if (f850a != null) {
                C0650t c0650t = f850a;
                f850a = c0650t.f848f;
                c0650t.f848f = null;
                f851b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                return c0650t;
            }
            return new C0650t();
        }
    }

    /* JADX INFO: renamed from: a */
    static void m616a(C0650t c0650t) {
        if (c0650t.f848f != null || c0650t.f849g != null) {
            throw new IllegalArgumentException();
        }
        if (c0650t.f846d) {
            return;
        }
        synchronized (C0651u.class) {
            if (f851b + PlaybackStateCompat.ACTION_PLAY_FROM_URI > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                return;
            }
            f851b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            c0650t.f848f = f850a;
            c0650t.f845c = 0;
            c0650t.f844b = 0;
            f850a = c0650t;
        }
    }
}
