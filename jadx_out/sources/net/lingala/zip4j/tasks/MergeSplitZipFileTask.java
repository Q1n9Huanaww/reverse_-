package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.RawIO;

/* JADX INFO: loaded from: classes2.dex */
public class MergeSplitZipFileTask extends AsyncZipTask<MergeSplitZipFileTaskParameters> {
    private final RawIO rawIO;
    private final ZipModel zipModel;

    public MergeSplitZipFileTask(ZipModel zipModel, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.rawIO = new RawIO();
        this.zipModel = zipModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:102:? A[Catch: Throwable -> 0x003e, all -> 0x00e5, SYNTHETIC, TRY_ENTER, TryCatch #5 {all -> 0x00e5, blocks: (B:8:0x0028, B:10:0x0036, B:11:0x003d, B:26:0x005d, B:42:0x00c9, B:60:0x00f2, B:59:0x00ee, B:53:0x00e1, B:63:0x00fb, B:62:0x00f7, B:54:0x00e4, B:64:0x00ff), top: B:85:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0048 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x00df A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x00fb A[Catch: Throwable -> 0x003e, all -> 0x00e5, TryCatch #5 {all -> 0x00e5, blocks: (B:8:0x0028, B:10:0x0036, B:11:0x003d, B:26:0x005d, B:42:0x00c9, B:60:0x00f2, B:59:0x00ee, B:53:0x00e1, B:63:0x00fb, B:62:0x00f7, B:54:0x00e4, B:64:0x00ff), top: B:85:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x012e A[Catch: CloneNotSupportedException -> 0x004e, TRY_LEAVE, TryCatch #4 {CloneNotSupportedException -> 0x004e, blocks: (B:6:0x001b, B:67:0x0119, B:71:0x0124, B:70:0x011e, B:18:0x004a, B:74:0x012e, B:73:0x0129, B:19:0x004d), top: B:84:0x001b, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:99:? A[Catch: CloneNotSupportedException -> 0x004e, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #4 {CloneNotSupportedException -> 0x004e, blocks: (B:6:0x001b, B:67:0x0119, B:71:0x0124, B:70:0x011e, B:18:0x004a, B:74:0x012e, B:73:0x0129, B:19:0x004d), top: B:84:0x001b, inners: #1, #10 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(MergeSplitZipFileTaskParameters taskParameters, ProgressMonitor progressMonitor) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        if (!this.zipModel.isSplitArchive()) {
            ZipException e = new ZipException("archive not a split zip file");
            progressMonitor.endProgressMonitor(e);
            throw e;
        }
        try {
            OutputStream outputStream = new FileOutputStream(taskParameters.outputZipFile);
            Throwable th5 = null;
            long totalBytesWritten = 0;
            try {
                try {
                    int totalNumberOfSplitFiles = this.zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk();
                    if (totalNumberOfSplitFiles <= 0) {
                        throw new ZipException("zip archive not a split zip file");
                    }
                    int splitSignatureOverhead = 0;
                    int i = 0;
                    while (i <= totalNumberOfSplitFiles) {
                        RandomAccessFile randomAccessFile = createSplitZipFileStream(this.zipModel, i);
                        Throwable th6 = null;
                        int start = 0;
                        try {
                            long end = randomAccessFile.length();
                            if (i == 0) {
                                if (this.rawIO.readIntLittleEndian(randomAccessFile) == HeaderSignature.SPLIT_ZIP.getValue()) {
                                    splitSignatureOverhead = 4;
                                    start = 4;
                                } else {
                                    randomAccessFile.seek(0L);
                                }
                            }
                            if (i == totalNumberOfSplitFiles) {
                                end = this.zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory();
                            }
                            FileUtils.copyFile(randomAccessFile, outputStream, start, end, progressMonitor, taskParameters.zip4jConfig.getBufferSize());
                            totalBytesWritten += end - ((long) start);
                            updateFileHeaderOffsetsForIndex(this.zipModel.getCentralDirectory().getFileHeaders(), i == 0 ? 0L : totalBytesWritten, i, splitSignatureOverhead);
                            verifyIfTaskIsCancelled();
                            if (randomAccessFile != null) {
                                if (0 != 0) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th7) {
                                        th6.addSuppressed(th7);
                                    }
                                } else {
                                    randomAccessFile.close();
                                }
                            }
                            i++;
                        } catch (Throwable th8) {
                            try {
                                throw th8;
                            } catch (Throwable th9) {
                                th3 = th8;
                                th4 = th9;
                                if (randomAccessFile != null) {
                                    throw th4;
                                }
                                if (th3 == null) {
                                    randomAccessFile.close();
                                    throw th4;
                                }
                                randomAccessFile.close();
                                throw th4;
                            }
                        }
                    }
                    updateHeadersForMergeSplitFileAction(this.zipModel, totalBytesWritten, outputStream, taskParameters.zip4jConfig.getCharset());
                    progressMonitor.endProgressMonitor();
                    if (outputStream != null) {
                        if (0 == 0) {
                            outputStream.close();
                            return;
                        }
                        try {
                            outputStream.close();
                        } catch (Throwable th10) {
                            th5.addSuppressed(th10);
                        }
                    }
                } catch (Throwable th11) {
                    th2 = th11;
                    th = null;
                    if (outputStream != null) {
                        throw th2;
                    }
                    if (th == null) {
                        outputStream.close();
                        throw th2;
                    }
                    try {
                        outputStream.close();
                        throw th2;
                    } catch (Throwable th12) {
                        th.addSuppressed(th12);
                        throw th2;
                    }
                }
            } catch (Throwable th13) {
                try {
                    throw th13;
                } catch (Throwable th14) {
                    th = th13;
                    th2 = th14;
                    if (outputStream != null) {
                        throw th2;
                    }
                    if (th == null) {
                        outputStream.close();
                        throw th2;
                    }
                    outputStream.close();
                    throw th2;
                }
            }
        } catch (CloneNotSupportedException e2) {
            throw new ZipException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(MergeSplitZipFileTaskParameters taskParameters) {
        if (!this.zipModel.isSplitArchive()) {
            return 0L;
        }
        long totalSize = 0;
        for (int i = 0; i <= this.zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk(); i++) {
            totalSize += getNextSplitZipFile(this.zipModel, i).length();
        }
        return totalSize;
    }

    private void updateFileHeaderOffsetsForIndex(List<FileHeader> fileHeaders, long offsetToAdd, int index, int splitSignatureOverhead) {
        for (FileHeader fileHeader : fileHeaders) {
            if (fileHeader.getDiskNumberStart() == index) {
                fileHeader.setOffsetLocalHeader((fileHeader.getOffsetLocalHeader() + offsetToAdd) - ((long) splitSignatureOverhead));
                fileHeader.setDiskNumberStart(0);
            }
        }
    }

    private File getNextSplitZipFile(ZipModel zipModel, int partNumber) {
        if (partNumber == zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk()) {
            return zipModel.getZipFile();
        }
        String splitZipExtension = ".z0";
        if (partNumber >= 9) {
            splitZipExtension = ".z";
        }
        String rootZipFile = zipModel.getZipFile().getPath();
        String nextSplitZipFileName = zipModel.getZipFile().getPath().substring(0, rootZipFile.lastIndexOf(".")) + splitZipExtension + (partNumber + 1);
        return new File(nextSplitZipFileName);
    }

    private RandomAccessFile createSplitZipFileStream(ZipModel zipModel, int partNumber) throws FileNotFoundException {
        File splitFile = getNextSplitZipFile(zipModel, partNumber);
        return new RandomAccessFile(splitFile, RandomAccessFileMode.READ.getValue());
    }

    private void updateHeadersForMergeSplitFileAction(ZipModel zipModel, long totalBytesWritten, OutputStream outputStream, Charset charset) throws Throwable {
        ZipModel newZipModel = (ZipModel) zipModel.clone();
        newZipModel.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(totalBytesWritten);
        updateSplitZipModel(newZipModel, totalBytesWritten);
        HeaderWriter headerWriter = new HeaderWriter();
        headerWriter.finalizeZipFileWithoutValidations(newZipModel, outputStream, charset);
    }

    private void updateSplitZipModel(ZipModel zipModel, long totalFileSize) {
        zipModel.setSplitArchive(false);
        updateSplitEndCentralDirectory(zipModel);
        if (zipModel.isZip64Format()) {
            updateSplitZip64EndCentralDirLocator(zipModel, totalFileSize);
            updateSplitZip64EndCentralDirRec(zipModel, totalFileSize);
        }
    }

    private void updateSplitEndCentralDirectory(ZipModel zipModel) {
        int numberOfFileHeaders = zipModel.getCentralDirectory().getFileHeaders().size();
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setNumberOfThisDisk(0);
        endOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDir(0);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(numberOfFileHeaders);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(numberOfFileHeaders);
    }

    private void updateSplitZip64EndCentralDirLocator(ZipModel zipModel, long totalFileSize) {
        if (zipModel.getZip64EndOfCentralDirectoryLocator() != null) {
            Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = zipModel.getZip64EndOfCentralDirectoryLocator();
            zip64EndOfCentralDirectoryLocator.setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(0);
            zip64EndOfCentralDirectoryLocator.setOffsetZip64EndOfCentralDirectoryRecord(zip64EndOfCentralDirectoryLocator.getOffsetZip64EndOfCentralDirectoryRecord() + totalFileSize);
            zip64EndOfCentralDirectoryLocator.setTotalNumberOfDiscs(1);
        }
    }

    private void updateSplitZip64EndCentralDirRec(ZipModel zipModel, long totalFileSize) {
        if (zipModel.getZip64EndOfCentralDirectoryRecord() != null) {
            Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = zipModel.getZip64EndOfCentralDirectoryRecord();
            zip64EndOfCentralDirectoryRecord.setNumberOfThisDisk(0);
            zip64EndOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDirectory(0);
            zip64EndOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(zipModel.getEndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory());
            zip64EndOfCentralDirectoryRecord.setOffsetStartCentralDirectoryWRTStartDiskNumber(zip64EndOfCentralDirectoryRecord.getOffsetStartCentralDirectoryWRTStartDiskNumber() + totalFileSize);
        }
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.MERGE_ZIP_FILES;
    }

    public static class MergeSplitZipFileTaskParameters extends AbstractZipTaskParameters {
        private File outputZipFile;

        public MergeSplitZipFileTaskParameters(File outputZipFile, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputZipFile = outputZipFile;
        }
    }
}
