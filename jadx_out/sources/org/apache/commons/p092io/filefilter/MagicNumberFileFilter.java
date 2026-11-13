package org.apache.commons.p092io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] bArr) {
        this(bArr, 0L);
    }

    public MagicNumberFileFilter(String str) {
        this(str, 0L);
    }

    public MagicNumberFileFilter(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = str.getBytes(Charset.defaultCharset());
        this.byteOffset = j;
    }

    public MagicNumberFileFilter(byte[] bArr, long j) {
        if (bArr == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.magicNumbers, 0, bArr.length);
        this.byteOffset = j;
    }

    @Override // org.apache.commons.p092io.filefilter.AbstractFileFilter, org.apache.commons.p092io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        if (file != null && file.isFile() && file.canRead()) {
            RandomAccessFile randomAccessFile2 = null;
            try {
                byte[] bArr = new byte[this.magicNumbers.length];
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile.seek(this.byteOffset);
                    if (randomAccessFile.read(bArr) == this.magicNumbers.length) {
                        boolean zEquals = Arrays.equals(this.magicNumbers, bArr);
                        IOUtils.closeQuietly(randomAccessFile);
                        return zEquals;
                    }
                    IOUtils.closeQuietly(randomAccessFile);
                    return false;
                } catch (IOException unused) {
                    randomAccessFile2 = randomAccessFile;
                    IOUtils.closeQuietly(randomAccessFile2);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    IOUtils.closeQuietly(randomAccessFile);
                    throw th;
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
            }
        }
        return false;
    }

    @Override // org.apache.commons.p092io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + "(" + new String(this.magicNumbers, Charset.defaultCharset()) + "," + this.byteOffset + ")";
    }
}
