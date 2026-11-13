package org.wlf.filedownloader.file_download.http_downloader;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ContentLengthInputStream extends InputStream {
    private final long mLength;
    private final InputStream mStream;

    public ContentLengthInputStream(InputStream stream, long length) {
        this.mStream = stream;
        this.mLength = length;
    }

    public long getLength() {
        return this.mLength;
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) this.mLength;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mStream.close();
    }

    @Override // java.io.InputStream
    public void mark(int readLimit) {
        this.mStream.mark(readLimit);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.mStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        return this.mStream.read(buffer);
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        return this.mStream.read(buffer, byteOffset, byteCount);
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.mStream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        return this.mStream.skip(byteCount);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.mStream.markSupported();
    }
}
