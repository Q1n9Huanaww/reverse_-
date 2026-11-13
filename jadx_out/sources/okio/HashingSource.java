package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class HashingSource extends ForwardingSource {
    private final MessageDigest messageDigest;

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, "SHA-1");
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        long j2 = super.read(buffer, j);
        if (j2 != -1) {
            long j3 = buffer.size - j2;
            long j4 = buffer.size;
            Segment segment = buffer.head;
            while (j4 > j3) {
                segment = segment.prev;
                j4 -= (long) (segment.limit - segment.pos);
            }
            while (j4 < buffer.size) {
                int i = (int) ((((long) segment.pos) + j3) - j4);
                this.messageDigest.update(segment.data, i, segment.limit - i);
                long j5 = j4 + ((long) (segment.limit - segment.pos));
                segment = segment.next;
                j4 = j5;
                j3 = j4;
            }
        }
        return j2;
    }

    public ByteString hash() {
        return ByteString.m5099of(this.messageDigest.digest());
    }
}
