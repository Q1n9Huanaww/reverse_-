package net.lingala.zip4j.p088io.outputstream;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;

/* JADX INFO: loaded from: classes2.dex */
public class SplitOutputStream extends OutputStream implements OutputStreamWithSplitZipSupport {
    private long bytesWrittenForThisPart;
    private int currSplitFileCounter;
    private RandomAccessFile raf;
    private RawIO rawIO;
    private long splitLength;
    private File zipFile;

    public SplitOutputStream(File file) throws ZipException, FileNotFoundException {
        this(file, -1L);
    }

    public SplitOutputStream(File file, long splitLength) throws ZipException, FileNotFoundException {
        this.rawIO = new RawIO();
        if (splitLength >= 0 && splitLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            throw new ZipException("split length less than minimum allowed split length of 65536 Bytes");
        }
        this.raf = new RandomAccessFile(file, RandomAccessFileMode.WRITE.getValue());
        this.splitLength = splitLength;
        this.zipFile = file;
        this.currSplitFileCounter = 0;
        this.bytesWrittenForThisPart = 0L;
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
        if (len <= 0) {
            return;
        }
        if (this.splitLength == -1) {
            this.raf.write(b, off, len);
            this.bytesWrittenForThisPart += (long) len;
            return;
        }
        if (this.bytesWrittenForThisPart >= this.splitLength) {
            startNextSplitFile();
            this.raf.write(b, off, len);
            this.bytesWrittenForThisPart = len;
        } else {
            if (this.bytesWrittenForThisPart + ((long) len) > this.splitLength) {
                if (isHeaderData(b)) {
                    startNextSplitFile();
                    this.raf.write(b, off, len);
                    this.bytesWrittenForThisPart = len;
                    return;
                } else {
                    this.raf.write(b, off, (int) (this.splitLength - this.bytesWrittenForThisPart));
                    startNextSplitFile();
                    this.raf.write(b, ((int) (this.splitLength - this.bytesWrittenForThisPart)) + off, (int) (((long) len) - (this.splitLength - this.bytesWrittenForThisPart)));
                    this.bytesWrittenForThisPart = ((long) len) - (this.splitLength - this.bytesWrittenForThisPart);
                    return;
                }
            }
            this.raf.write(b, off, len);
            this.bytesWrittenForThisPart += (long) len;
        }
    }

    private void startNextSplitFile() throws IOException {
        String zipFileWithoutExt = FileUtils.getZipFileNameWithoutExtension(this.zipFile.getName());
        String zipFileName = this.zipFile.getAbsolutePath();
        String parentPath = this.zipFile.getParent() == null ? "" : this.zipFile.getParent() + System.getProperty("file.separator");
        String fileExtension = ".z0" + (this.currSplitFileCounter + 1);
        if (this.currSplitFileCounter >= 9) {
            fileExtension = ".z" + (this.currSplitFileCounter + 1);
        }
        File currSplitFile = new File(parentPath + zipFileWithoutExt + fileExtension);
        this.raf.close();
        if (currSplitFile.exists()) {
            throw new IOException("split file: " + currSplitFile.getName() + " already exists in the current directory, cannot rename this file");
        }
        if (!this.zipFile.renameTo(currSplitFile)) {
            throw new IOException("cannot rename newly created split file");
        }
        this.zipFile = new File(zipFileName);
        this.raf = new RandomAccessFile(this.zipFile, RandomAccessFileMode.WRITE.getValue());
        this.currSplitFileCounter++;
    }

    private boolean isHeaderData(byte[] buff) {
        int signature = this.rawIO.readIntLittleEndian(buff);
        for (HeaderSignature headerSignature : HeaderSignature.values()) {
            if (headerSignature != HeaderSignature.SPLIT_ZIP && headerSignature.getValue() == signature) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBufferSizeAndStartNextSplitFile(int bufferSize) throws ZipException {
        if (bufferSize < 0) {
            throw new ZipException("negative buffersize for checkBufferSizeAndStartNextSplitFile");
        }
        if (!isBufferSizeFitForCurrSplitFile(bufferSize)) {
            try {
                startNextSplitFile();
                this.bytesWrittenForThisPart = 0L;
                return true;
            } catch (IOException e) {
                throw new ZipException(e);
            }
        }
        return false;
    }

    private boolean isBufferSizeFitForCurrSplitFile(int bufferSize) {
        return this.splitLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH || this.bytesWrittenForThisPart + ((long) bufferSize) <= this.splitLength;
    }

    public void seek(long pos) throws IOException {
        this.raf.seek(pos);
    }

    public int skipBytes(int n) throws IOException {
        return this.raf.skipBytes(n);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.raf.close();
    }

    @Override // net.lingala.zip4j.p088io.outputstream.OutputStreamWithSplitZipSupport
    public long getFilePointer() throws IOException {
        return this.raf.getFilePointer();
    }

    public boolean isSplitZipFile() {
        return this.splitLength != -1;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    @Override // net.lingala.zip4j.p088io.outputstream.OutputStreamWithSplitZipSupport
    public int getCurrentSplitFileCounter() {
        return this.currSplitFileCounter;
    }
}
