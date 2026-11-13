package com.cyjh.event;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: renamed from: com.cyjh.event.c */
/* JADX INFO: compiled from: UiTransHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0721c {

    /* JADX INFO: renamed from: a */
    private static ArrayBlockingQueue<ByteString> f936a = new ArrayBlockingQueue<>(16);

    /* JADX INFO: renamed from: b */
    private static ArrayBlockingQueue<ByteString> f937b = new ArrayBlockingQueue<>(1024);

    /* JADX INFO: renamed from: c */
    private static ArrayBlockingQueue<ByteString> f938c = new ArrayBlockingQueue<>(1024);

    /* JADX INFO: renamed from: a */
    public static byte[] m653a() {
        try {
            return f936a.take().toByteArray();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m652a(ByteString byteString) {
        try {
            f936a.put(byteString);
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m655b() {
        try {
            return f937b.take().toByteArray();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m654b(ByteString byteString) {
        try {
            f937b.put(byteString);
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m656c() {
        f937b.clear();
        f938c.clear();
    }

    /* JADX INFO: renamed from: d */
    public static ByteString m658d() {
        try {
            return f938c.take();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m657c(ByteString byteString) {
        try {
            f938c.put(byteString);
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m659e() {
        try {
            f938c.put(ByteString.copyFrom("floatEventThreadExit".getBytes()));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m660f() {
        f938c.clear();
    }
}
