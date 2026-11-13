package net.lingala.zip4j.p088io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.exception.ZipException;

/* JADX INFO: loaded from: classes2.dex */
public class CountingOutputStream extends OutputStream implements OutputStreamWithSplitZipSupport {
    private long numberOfBytesWritten = 0;
    private OutputStream outputStream;

    public CountingOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.outputStream.write(b, off, len);
        this.numberOfBytesWritten += (long) len;
    }

    @Override // net.lingala.zip4j.p088io.outputstream.OutputStreamWithSplitZipSupport
    public int getCurrentSplitFileCounter() {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).getCurrentSplitFileCounter();
        }
        return 0;
    }

    public long getOffsetForNextEntry() throws IOException {
        return this.outputStream instanceof SplitOutputStream ? ((SplitOutputStream) this.outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    public long getSplitLength() {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).getSplitLength();
        }
        return 0L;
    }

    public boolean isSplitZipFile() {
        return (this.outputStream instanceof SplitOutputStream) && ((SplitOutputStream) this.outputStream).isSplitZipFile();
    }

    public long getNumberOfBytesWritten() throws IOException {
        return this.outputStream instanceof SplitOutputStream ? ((SplitOutputStream) this.outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    public boolean checkBuffSizeAndStartNextSplitFile(int bufferSize) throws ZipException {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).checkBufferSizeAndStartNextSplitFile(bufferSize);
        }
        return false;
    }

    @Override // net.lingala.zip4j.p088io.outputstream.OutputStreamWithSplitZipSupport
    public long getFilePointer() throws IOException {
        return this.outputStream instanceof SplitOutputStream ? ((SplitOutputStream) this.outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.outputStream.close();
    }
}
