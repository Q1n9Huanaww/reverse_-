package com.cyjh.mobileanjian.ipc.uip;

import android.os.Handler;
import android.os.Process;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class UipEventStub {

    /* JADX INFO: renamed from: a */
    private static Handler f1357a;

    /* JADX INFO: renamed from: b */
    private static ArrayBlockingQueue<ByteString> f1358b = new ArrayBlockingQueue<>(1);

    /* JADX INFO: renamed from: c */
    private static ArrayBlockingQueue<ByteString> f1359c = new ArrayBlockingQueue<>(256);

    public static void registerHandler(Handler handler) {
        f1357a = handler;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1016a() {
        return (Process.myUid() == 0 || Process.myUid() == 2000) ? false : true;
    }

    public static byte[] SendUiRequest(byte[] bArr) {
        if (!m1016a()) {
            return null;
        }
        f1357a.obtainMessage(0, ByteString.copyFrom(bArr)).sendToTarget();
        try {
            return f1358b.take().toByteArray();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static void UiRequestReturn(ByteString byteString) {
        try {
            f1358b.put(byteString);
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static byte[] GetUiEvent() {
        if (!m1016a()) {
            return null;
        }
        try {
            return f1359c.take().toByteArray();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static void hasEvent(ByteString byteString) {
        try {
            if (f1359c.size() < 256) {
                f1359c.put(byteString);
            }
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void clear() {
        f1359c.clear();
    }
}
