package mobi.oneway.export.p087g;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: mobi.oneway.export.g.c */
/* JADX INFO: loaded from: classes.dex */
public class C1949c extends InputStream {

    /* JADX INFO: renamed from: a */
    private InputStream f4999a;

    /* JADX INFO: renamed from: b */
    private int[] f5000b;

    /* JADX INFO: renamed from: c */
    private int f5001c = 0;

    /* JADX INFO: renamed from: d */
    private boolean f5002d = false;

    public C1949c(InputStream inputStream) {
        this.f4999a = inputStream;
    }

    /* JADX INFO: renamed from: a */
    private void m4982a() throws IOException {
        int i;
        char[] cArr = new char[4];
        int i2 = 0;
        do {
            int i3 = this.f4999a.read();
            i = 1;
            if (i3 == -1) {
                if (i2 != 0) {
                    throw new IOException("Bad base64 stream");
                }
                this.f5000b = new int[0];
                this.f5002d = true;
                return;
            }
            char c = (char) i3;
            if (C1963q.f5029a.indexOf(c) != -1 || c == C1963q.f5030b) {
                cArr[i2] = c;
                i2++;
            } else if (c != '\r' && c != '\n') {
                throw new IOException("Bad base64 stream");
            }
        } while (i2 < 4);
        boolean z = false;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != C1963q.f5030b) {
                if (z) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (!z) {
                z = true;
            }
        }
        if (cArr[3] != C1963q.f5030b) {
            i = 3;
        } else {
            if (this.f4999a.read() != -1) {
                throw new IOException("Bad base64 stream");
            }
            this.f5002d = true;
            if (cArr[2] != C1963q.f5030b) {
                i = 2;
            }
        }
        int iIndexOf = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            if (cArr[i5] != C1963q.f5030b) {
                iIndexOf |= C1963q.f5029a.indexOf(cArr[i5]) << ((3 - i5) * 6);
            }
        }
        this.f5000b = new int[i];
        for (int i6 = 0; i6 < i; i6++) {
            this.f5000b[i6] = (iIndexOf >>> ((2 - i6) * 8)) & 255;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f4999a.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.f5000b != null && this.f5001c != this.f5000b.length) {
            int[] iArr = this.f5000b;
            int i = this.f5001c;
            this.f5001c = i + 1;
            return iArr[i];
        }
        if (this.f5002d) {
            return -1;
        }
        m4982a();
        if (this.f5000b.length == 0) {
            this.f5000b = null;
            return -1;
        }
        this.f5001c = 0;
        int[] iArr2 = this.f5000b;
        int i2 = this.f5001c;
        this.f5001c = i2 + 1;
        return iArr2[i2];
    }
}
