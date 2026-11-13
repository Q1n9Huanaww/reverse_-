package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.p088io.outputstream.SplitOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes2.dex */
public class RemoveFilesFromZipTask extends AbstractModifyFileTask<RemoveFilesFromZipTaskParameters> {
    private final HeaderWriter headerWriter;
    private final ZipModel zipModel;

    public RemoveFilesFromZipTask(ZipModel zipModel, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
        this.headerWriter = headerWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:28:0x00b7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x013c A[Catch: Throwable -> 0x00bd, all -> 0x012f, TRY_LEAVE, TryCatch #2 {Throwable -> 0x00bd, blocks: (B:12:0x003d, B:60:0x0133, B:57:0x0129, B:63:0x013c, B:62:0x0138, B:30:0x00bc), top: B:72:0x003d }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0152 A[Catch: all -> 0x00cd, TRY_LEAVE, TryCatch #6 {all -> 0x00cd, blocks: (B:10:0x0036, B:54:0x0116, B:67:0x0148, B:66:0x0142, B:37:0x00c9, B:70:0x0152, B:69:0x014d, B:38:0x00cc), top: B:79:0x0036, inners: #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:? A[Catch: Throwable -> 0x00bd, all -> 0x012f, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Throwable -> 0x00bd, blocks: (B:12:0x003d, B:60:0x0133, B:57:0x0129, B:63:0x013c, B:62:0x0138, B:30:0x00bc), top: B:72:0x003d }] */
    /* JADX WARN: Code duplicated, block: B:95:? A[Catch: all -> 0x00cd, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x00cd, blocks: (B:10:0x0036, B:54:0x0116, B:67:0x0148, B:66:0x0142, B:37:0x00c9, B:70:0x0152, B:69:0x014d, B:38:0x00cc), top: B:79:0x0036, inners: #3, #4 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(RemoveFilesFromZipTaskParameters taskParameters, ProgressMonitor progressMonitor) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (this.zipModel.isSplitArchive()) {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        List<String> entriesToRemove = filterNonExistingEntries(taskParameters.filesToRemove);
        if (entriesToRemove.isEmpty()) {
            return;
        }
        File temporaryZipFile = getTemporaryFile(this.zipModel.getZipFile().getPath());
        boolean successFlag = false;
        try {
            SplitOutputStream outputStream = new SplitOutputStream(temporaryZipFile);
            Throwable th4 = null;
            try {
                try {
                    RandomAccessFile inputStream = new RandomAccessFile(this.zipModel.getZipFile(), RandomAccessFileMode.READ.getValue());
                    Throwable th5 = null;
                    long currentFileCopyPointer = 0;
                    try {
                        List<FileHeader> sortedFileHeaders = cloneAndSortFileHeadersByOffset(this.zipModel.getCentralDirectory().getFileHeaders());
                        for (FileHeader fileHeader : sortedFileHeaders) {
                            long lengthOfCurrentEntry = getOffsetOfNextEntry(sortedFileHeaders, fileHeader, this.zipModel) - outputStream.getFilePointer();
                            if (shouldEntryBeRemoved(fileHeader, entriesToRemove)) {
                                updateHeaders(sortedFileHeaders, fileHeader, lengthOfCurrentEntry);
                                if (!this.zipModel.getCentralDirectory().getFileHeaders().remove(fileHeader)) {
                                    throw new ZipException("Could not remove entry from list of central directory headers");
                                }
                                currentFileCopyPointer += lengthOfCurrentEntry;
                            } else {
                                currentFileCopyPointer += super.copyFile(inputStream, outputStream, currentFileCopyPointer, lengthOfCurrentEntry, progressMonitor, taskParameters.zip4jConfig.getBufferSize());
                            }
                            verifyIfTaskIsCancelled();
                        }
                        this.headerWriter.finalizeZipFile(this.zipModel, outputStream, taskParameters.zip4jConfig.getCharset());
                        successFlag = true;
                        if (inputStream != null) {
                            if (0 != 0) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th6) {
                                    th5.addSuppressed(th6);
                                }
                            } else {
                                inputStream.close();
                            }
                        }
                        if (outputStream != null) {
                            if (0 != 0) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th7) {
                                    th4.addSuppressed(th7);
                                }
                            } else {
                                outputStream.close();
                            }
                        }
                        cleanupFile(true, this.zipModel.getZipFile(), temporaryZipFile);
                    } catch (Throwable th8) {
                        try {
                            throw th8;
                        } catch (Throwable th9) {
                            th2 = th8;
                            th3 = th9;
                            if (inputStream != null) {
                                throw th3;
                            }
                            if (th2 == null) {
                                inputStream.close();
                                throw th3;
                            }
                            inputStream.close();
                            throw th3;
                        }
                    }
                } catch (Throwable th10) {
                    try {
                        throw th10;
                    } catch (Throwable th11) {
                        th = th10;
                        th = th11;
                        if (outputStream != null) {
                            throw th;
                        }
                        if (th == null) {
                            outputStream.close();
                            throw th;
                        }
                        try {
                            outputStream.close();
                            throw th;
                        } catch (Throwable th12) {
                            th.addSuppressed(th12);
                            throw th;
                        }
                    }
                }
            } catch (Throwable th13) {
                th = th13;
                th = null;
                if (outputStream != null) {
                    throw th;
                }
                if (th == null) {
                    outputStream.close();
                    throw th;
                }
                outputStream.close();
                throw th;
            }
        } catch (Throwable th14) {
            cleanupFile(successFlag, this.zipModel.getZipFile(), temporaryZipFile);
            throw th14;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(RemoveFilesFromZipTaskParameters taskParameters) {
        return this.zipModel.getZipFile().length();
    }

    private List<String> filterNonExistingEntries(List<String> filesToRemove) throws ZipException {
        List<String> filteredFilesToRemove = new ArrayList<>();
        for (String fileToRemove : filesToRemove) {
            if (HeaderUtil.getFileHeader(this.zipModel, fileToRemove) != null) {
                filteredFilesToRemove.add(fileToRemove);
            }
        }
        return filteredFilesToRemove;
    }

    private boolean shouldEntryBeRemoved(FileHeader fileHeaderToBeChecked, List<String> fileNamesToBeRemoved) {
        for (String fileNameToBeRemoved : fileNamesToBeRemoved) {
            if ((fileNameToBeRemoved.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && fileHeaderToBeChecked.getFileName().startsWith(fileNameToBeRemoved)) || fileHeaderToBeChecked.getFileName().equals(fileNameToBeRemoved)) {
                return true;
            }
        }
        return false;
    }

    private void updateHeaders(List<FileHeader> sortedFileHeaders, FileHeader fileHeaderThatWasRemoved, long offsetToSubtract) throws ZipException {
        updateOffsetsForAllSubsequentFileHeaders(sortedFileHeaders, this.zipModel, fileHeaderThatWasRemoved, negate(offsetToSubtract));
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = this.zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setOffsetOfStartOfCentralDirectory(endOfCentralDirectoryRecord.getOffsetOfStartOfCentralDirectory() - offsetToSubtract);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectory() - 1);
        if (endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectoryOnThisDisk() > 0) {
            endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(endOfCentralDirectoryRecord.getTotalNumberOfEntriesInCentralDirectoryOnThisDisk() - 1);
        }
        if (this.zipModel.isZip64Format()) {
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setOffsetStartCentralDirectoryWRTStartDiskNumber(this.zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber() - offsetToSubtract);
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(this.zipModel.getZip64EndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory() - 1);
            this.zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(this.zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord() - offsetToSubtract);
        }
    }

    private long negate(long val) {
        if (val == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return -val;
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.REMOVE_ENTRY;
    }

    public static class RemoveFilesFromZipTaskParameters extends AbstractZipTaskParameters {
        private final List<String> filesToRemove;

        public RemoveFilesFromZipTaskParameters(List<String> filesToRemove, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.filesToRemove = filesToRemove;
        }
    }
}
