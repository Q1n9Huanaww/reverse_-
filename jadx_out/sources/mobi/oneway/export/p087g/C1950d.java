package mobi.oneway.export.p087g;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: mobi.oneway.export.g.d */
/* JADX INFO: loaded from: classes.dex */
public class C1950d extends OutputStream {

    /* JADX INFO: renamed from: a */
    private OutputStream f5003a;

    /* JADX INFO: renamed from: b */
    private int f5004b;

    /* JADX INFO: renamed from: c */
    private int f5005c;

    /* JADX INFO: renamed from: d */
    private int f5006d;

    /* JADX INFO: renamed from: e */
    private int f5007e;

    public C1950d(OutputStream outputStream) {
        this(outputStream, 76);
    }

    public C1950d(OutputStream outputStream, int i) {
        this.f5003a = null;
        this.f5004b = 0;
        this.f5005c = 0;
        this.f5006d = 0;
        this.f5007e = 0;
        this.f5003a = outputStream;
        this.f5007e = i;
    }

    /* JADX INFO: renamed from: a */
    void m4983a() throws IOException {
        if (this.f5005c <= 0) {
            return;
        }
        if (this.f5007e > 0 && this.f5006d == this.f5007e) {
            this.f5003a.write(IOUtils.LINE_SEPARATOR_WINDOWS.getBytes("UTF-8"));
            this.f5006d = 0;
        }
        char cCharAt = C1963q.f5029a.charAt((this.f5004b << 8) >>> 26);
        char cCharAt2 = C1963q.f5029a.charAt((this.f5004b << 14) >>> 26);
        char cCharAt3 = this.f5005c < 2 ? C1963q.f5030b : C1963q.f5029a.charAt((this.f5004b << 20) >>> 26);
        char cCharAt4 = this.f5005c < 3 ? C1963q.f5030b : C1963q.f5029a.charAt((this.f5004b << 26) >>> 26);
        this.f5003a.write(cCharAt);
        this.f5003a.write(cCharAt2);
        this.f5003a.write(cCharAt3);
        this.f5003a.write(cCharAt4);
        this.f5006d += 4;
        this.f5005c = 0;
        this.f5004b = 0;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        m4983a();
        this.f5003a.close();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.f5004b = ((i & 255) << (16 - (this.f5005c * 8))) | this.f5004b;
        this.f5005c++;
        if (this.f5005c == 3) {
            m4983a();
        }
    }
}
