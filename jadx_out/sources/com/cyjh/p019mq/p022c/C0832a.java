package com.cyjh.p019mq.p022c;

import android.net.LocalSocket;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: com.cyjh.mq.c.a */
/* JADX INFO: compiled from: BaseSocketConnection.java */
/* JADX INFO: loaded from: classes.dex */
public class C0832a {

    /* JADX INFO: renamed from: a */
    private static final int f1537a = 4096;

    /* JADX INFO: renamed from: b */
    private Socket f1538b;

    /* JADX INFO: renamed from: c */
    private LocalSocket f1539c;

    /* JADX INFO: renamed from: d */
    private InputStream f1540d;

    /* JADX INFO: renamed from: e */
    private OutputStream f1541e;

    public C0832a(Socket socket) {
        this.f1538b = null;
        this.f1539c = null;
        this.f1540d = null;
        this.f1541e = null;
        this.f1538b = socket;
        try {
            this.f1540d = this.f1538b.getInputStream();
            this.f1541e = this.f1538b.getOutputStream();
        } catch (IOException e) {
            new StringBuilder("BaseSocketConnection(Socket socket) e").append(e.toString());
            ThrowableExtension.printStackTrace(e);
        }
    }

    public C0832a(LocalSocket localSocket) {
        this.f1538b = null;
        this.f1539c = null;
        this.f1540d = null;
        this.f1541e = null;
        this.f1539c = localSocket;
        try {
            this.f1540d = this.f1539c.getInputStream();
            this.f1541e = this.f1539c.getOutputStream();
        } catch (IOException e) {
            new StringBuilder("BaseSocketConnection(LocalSocket localSocket) e").append(e.toString());
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1187b(byte[] bArr) {
        m1190a(ByteBuffer.wrap(bArr));
    }

    /* JADX INFO: renamed from: a */
    protected final void m1190a(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[4096];
            while (byteBuffer.hasRemaining()) {
                if (byteBuffer.remaining() >= 4096) {
                    byteBuffer.get(bArr);
                } else {
                    bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                }
                this.f1541e.write(bArr);
            }
            this.f1541e.flush();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            new StringBuilder("sendData e").append(e.toString());
        }
    }

    /* JADX INFO: renamed from: a */
    protected final int m1188a(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            try {
                i2 = this.f1540d.read(bArr, i, length - i);
                if (i2 < 0) {
                    return -1;
                }
                i += i2;
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
                new StringBuilder("receiveData e").append(e.toString());
                return -1;
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    protected void mo1189a() {
        try {
            if (this.f1538b != null) {
                this.f1538b.close();
            }
            if (this.f1539c != null) {
                this.f1539c.close();
            }
            if (this.f1540d != null) {
                this.f1540d.close();
            }
            if (this.f1541e != null) {
                this.f1541e.close();
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
