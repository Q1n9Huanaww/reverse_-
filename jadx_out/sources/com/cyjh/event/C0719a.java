package com.cyjh.event;

import com.google.protobuf.ByteString;

/* JADX INFO: renamed from: com.cyjh.event.a */
/* JADX INFO: compiled from: DebugHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0719a {

    /* JADX INFO: renamed from: a */
    private static boolean f929a;

    /* JADX INFO: renamed from: b */
    private static ByteString f930b;

    /* JADX INFO: renamed from: a */
    public static byte[] m651a() {
        f930b = null;
        f929a = true;
        if (f930b == null) {
            return null;
        }
        return f930b.toByteArray();
    }

    /* JADX INFO: renamed from: a */
    private static void m650a(ByteString byteString) {
        f930b = byteString;
        f929a = false;
    }
}
