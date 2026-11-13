package net.lingala.zip4j.p088io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
abstract class CipherInputStream<T extends Decrypter> extends InputStream {
    private T decrypter;
    private byte[] lastReadRawDataCache;
    private LocalFileHeader localFileHeader;
    private byte[] singleByteBuffer = new byte[1];
    private ZipEntryInputStream zipEntryInputStream;

    protected abstract T initializeDecrypter(LocalFileHeader localFileHeader, char[] cArr) throws IOException;

    public CipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i) throws IOException {
        this.zipEntryInputStream = zipEntryInputStream;
        this.decrypter = (T) initializeDecrypter(localFileHeader, cArr);
        this.localFileHeader = localFileHeader;
        if (Zip4jUtil.getCompressionMethod(localFileHeader).equals(CompressionMethod.DEFLATE)) {
            this.lastReadRawDataCache = new byte[i];
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int readLen = read(this.singleByteBuffer);
        if (readLen == -1) {
            return -1;
        }
        return this.singleByteBuffer[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int readLen = Zip4jUtil.readFully(this.zipEntryInputStream, b, off, len);
        if (readLen > 0) {
            cacheRawData(b, readLen);
            this.decrypter.decryptData(b, off, readLen);
        }
        return readLen;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipEntryInputStream.close();
    }

    public byte[] getLastReadRawDataCache() {
        return this.lastReadRawDataCache;
    }

    protected int readRaw(byte[] b) throws IOException {
        return this.zipEntryInputStream.readRawFully(b);
    }

    private void cacheRawData(byte[] b, int len) {
        if (this.lastReadRawDataCache != null) {
            System.arraycopy(b, 0, this.lastReadRawDataCache, 0, len);
        }
    }

    public T getDecrypter() {
        return this.decrypter;
    }

    protected void endOfEntryReached(InputStream inputStream) throws IOException {
    }

    protected long getNumberOfBytesReadForThisEntry() {
        return this.zipEntryInputStream.getNumberOfBytesRead();
    }

    public LocalFileHeader getLocalFileHeader() {
        return this.localFileHeader;
    }
}
