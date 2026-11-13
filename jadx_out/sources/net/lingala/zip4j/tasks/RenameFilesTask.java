package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.p088io.outputstream.SplitOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.RawIO;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
public class RenameFilesTask extends AbstractModifyFileTask<RenameFilesTaskParameters> {
    private final HeaderWriter headerWriter;
    private final RawIO rawIO;
    private final ZipModel zipModel;

    public RenameFilesTask(ZipModel zipModel, HeaderWriter headerWriter, RawIO rawIO, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
        this.headerWriter = headerWriter;
        this.rawIO = rawIO;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:23:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:56:0x0170 A[Catch: Throwable -> 0x00b9, all -> 0x0161, TRY_LEAVE, TryCatch #2 {all -> 0x0161, blocks: (B:8:0x003a, B:44:0x013d, B:53:0x0166, B:50:0x015b, B:24:0x00b5, B:56:0x0170, B:55:0x016b, B:25:0x00b8), top: B:69:0x003a }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0186 A[Catch: all -> 0x00c9, TRY_LEAVE, TryCatch #5 {all -> 0x00c9, blocks: (B:6:0x0025, B:47:0x0144, B:60:0x017c, B:59:0x0176, B:32:0x00c5, B:63:0x0186, B:62:0x0181, B:33:0x00c8), top: B:72:0x0025, inners: #3, #6 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x00c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:? A[Catch: Throwable -> 0x00b9, all -> 0x0161, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0161, blocks: (B:8:0x003a, B:44:0x013d, B:53:0x0166, B:50:0x015b, B:24:0x00b5, B:56:0x0170, B:55:0x016b, B:25:0x00b8), top: B:69:0x003a }] */
    /* JADX WARN: Code duplicated, block: B:88:? A[Catch: all -> 0x00c9, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x00c9, blocks: (B:6:0x0025, B:47:0x0144, B:60:0x017c, B:59:0x0176, B:32:0x00c5, B:63:0x0186, B:62:0x0181, B:33:0x00c8), top: B:72:0x0025, inners: #3, #6 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(RenameFilesTaskParameters taskParameters, ProgressMonitor progressMonitor) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Map<String, String> fileNamesMap = filterNonExistingEntriesAndAddSeparatorIfNeeded(taskParameters.fileNamesMap);
        if (fileNamesMap.size() == 0) {
            return;
        }
        File temporaryFile = getTemporaryFile(this.zipModel.getZipFile().getPath());
        boolean successFlag = false;
        try {
            RandomAccessFile inputStream = new RandomAccessFile(this.zipModel.getZipFile(), RandomAccessFileMode.WRITE.getValue());
            Throwable th5 = null;
            try {
                try {
                    SplitOutputStream outputStream = new SplitOutputStream(temporaryFile);
                    Throwable th6 = null;
                    long currentFileCopyPointer = 0;
                    try {
                        Charset charset = taskParameters.zip4jConfig.getCharset();
                        List<FileHeader> sortedFileHeaders = cloneAndSortFileHeadersByOffset(this.zipModel.getCentralDirectory().getFileHeaders());
                        for (FileHeader fileHeader : sortedFileHeaders) {
                            Map.Entry<String, String> fileNameMapForThisEntry = getCorrespondingEntryFromMap(fileHeader, fileNamesMap);
                            progressMonitor.setFileName(fileHeader.getFileName());
                            long lengthToCopy = getOffsetOfNextEntry(sortedFileHeaders, fileHeader, this.zipModel) - outputStream.getFilePointer();
                            if (fileNameMapForThisEntry == null) {
                                currentFileCopyPointer += copyFile(inputStream, outputStream, currentFileCopyPointer, lengthToCopy, progressMonitor, taskParameters.zip4jConfig.getBufferSize());
                            } else {
                                String newFileName = getNewFileName(fileNameMapForThisEntry.getValue(), fileNameMapForThisEntry.getKey(), fileHeader.getFileName());
                                byte[] newFileNameBytes = HeaderUtil.getBytesFromString(newFileName, charset);
                                int headersOffset = newFileNameBytes.length - fileHeader.getFileNameLength();
                                currentFileCopyPointer = copyEntryAndChangeFileName(newFileNameBytes, fileHeader, currentFileCopyPointer, lengthToCopy, inputStream, outputStream, progressMonitor, taskParameters.zip4jConfig.getBufferSize());
                                updateHeadersInZipModel(sortedFileHeaders, fileHeader, newFileName, newFileNameBytes, headersOffset);
                            }
                            verifyIfTaskIsCancelled();
                        }
                        this.headerWriter.finalizeZipFile(this.zipModel, outputStream, charset);
                        successFlag = true;
                        if (outputStream != null) {
                            if (0 != 0) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th7) {
                                    th6.addSuppressed(th7);
                                }
                            } else {
                                outputStream.close();
                            }
                        }
                        if (inputStream != null) {
                            if (0 != 0) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th8) {
                                    th5.addSuppressed(th8);
                                }
                            } else {
                                inputStream.close();
                            }
                        }
                        cleanupFile(true, this.zipModel.getZipFile(), temporaryFile);
                    } catch (Throwable th9) {
                        try {
                            throw th9;
                        } catch (Throwable th10) {
                            th3 = th9;
                            th4 = th10;
                            if (outputStream != null) {
                                throw th4;
                            }
                            if (th3 == null) {
                                outputStream.close();
                                throw th4;
                            }
                            outputStream.close();
                            throw th4;
                        }
                    }
                } catch (Throwable th11) {
                    th2 = th11;
                    th = null;
                    if (inputStream != null) {
                        throw th2;
                    }
                    if (th == null) {
                        inputStream.close();
                        throw th2;
                    }
                    try {
                        inputStream.close();
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
                    if (inputStream != null) {
                        throw th2;
                    }
                    if (th == null) {
                        inputStream.close();
                        throw th2;
                    }
                    inputStream.close();
                    throw th2;
                }
            }
        } catch (Throwable th15) {
            cleanupFile(successFlag, this.zipModel.getZipFile(), temporaryFile);
            throw th15;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(RenameFilesTaskParameters taskParameters) {
        return this.zipModel.getZipFile().length();
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.RENAME_FILE;
    }

    private long copyEntryAndChangeFileName(byte[] newFileNameBytes, FileHeader fileHeader, long start, long totalLengthOfEntry, RandomAccessFile inputStream, OutputStream outputStream, ProgressMonitor progressMonitor, int bufferSize) throws IOException {
        long currentFileCopyPointer = start + copyFile(inputStream, outputStream, start, 26L, progressMonitor, bufferSize);
        this.rawIO.writeShortLittleEndian(outputStream, newFileNameBytes.length);
        long currentFileCopyPointer2 = currentFileCopyPointer + 2;
        long currentFileCopyPointer3 = currentFileCopyPointer2 + copyFile(inputStream, outputStream, currentFileCopyPointer2, 2L, progressMonitor, bufferSize);
        outputStream.write(newFileNameBytes);
        long currentFileCopyPointer4 = currentFileCopyPointer3 + ((long) fileHeader.getFileNameLength());
        long remainingLengthToCopy = totalLengthOfEntry - (currentFileCopyPointer4 - start);
        return currentFileCopyPointer4 + copyFile(inputStream, outputStream, currentFileCopyPointer4, remainingLengthToCopy, progressMonitor, bufferSize);
    }

    private Map.Entry<String, String> getCorrespondingEntryFromMap(FileHeader fileHeaderToBeChecked, Map<String, String> fileNamesMap) {
        for (Map.Entry<String, String> fileHeaderToBeRenamed : fileNamesMap.entrySet()) {
            if (fileHeaderToBeChecked.getFileName().startsWith(fileHeaderToBeRenamed.getKey())) {
                return fileHeaderToBeRenamed;
            }
        }
        return null;
    }

    private void updateHeadersInZipModel(List<FileHeader> sortedFileHeaders, FileHeader fileHeader, String newFileName, byte[] newFileNameBytes, int headersOffset) throws ZipException {
        FileHeader fileHeaderToBeChanged = HeaderUtil.getFileHeader(this.zipModel, fileHeader.getFileName());
        if (fileHeaderToBeChanged == null) {
            throw new ZipException("could not find any header with name: " + fileHeader.getFileName());
        }
        fileHeaderToBeChanged.setFileName(newFileName);
        fileHeaderToBeChanged.setFileNameLength(newFileNameBytes.length);
        updateOffsetsForAllSubsequentFileHeaders(sortedFileHeaders, this.zipModel, fileHeaderToBeChanged, headersOffset);
        this.zipModel.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(this.zipModel.getEndOfCentralDirectoryRecord().getOffsetOfStartOfCentralDirectory() + ((long) headersOffset));
        if (this.zipModel.isZip64Format()) {
            this.zipModel.getZip64EndOfCentralDirectoryRecord().setOffsetStartCentralDirectoryWRTStartDiskNumber(this.zipModel.getZip64EndOfCentralDirectoryRecord().getOffsetStartCentralDirectoryWRTStartDiskNumber() + ((long) headersOffset));
            this.zipModel.getZip64EndOfCentralDirectoryLocator().setOffsetZip64EndOfCentralDirectoryRecord(this.zipModel.getZip64EndOfCentralDirectoryLocator().getOffsetZip64EndOfCentralDirectoryRecord() + ((long) headersOffset));
        }
    }

    private Map<String, String> filterNonExistingEntriesAndAddSeparatorIfNeeded(Map<String, String> inputFileNamesMap) throws ZipException {
        FileHeader fileHeaderToBeChanged;
        Map<String, String> fileNamesMapToBeChanged = new HashMap<>();
        for (Map.Entry<String, String> allNamesToBeChanged : inputFileNamesMap.entrySet()) {
            if (Zip4jUtil.isStringNotNullAndNotEmpty(allNamesToBeChanged.getKey()) && (fileHeaderToBeChanged = HeaderUtil.getFileHeader(this.zipModel, allNamesToBeChanged.getKey())) != null) {
                if (fileHeaderToBeChanged.isDirectory() && !allNamesToBeChanged.getValue().endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    fileNamesMapToBeChanged.put(allNamesToBeChanged.getKey(), allNamesToBeChanged.getValue() + InternalZipConstants.ZIP_FILE_SEPARATOR);
                } else {
                    fileNamesMapToBeChanged.put(allNamesToBeChanged.getKey(), allNamesToBeChanged.getValue());
                }
            }
        }
        return fileNamesMapToBeChanged;
    }

    private String getNewFileName(String newFileName, String oldFileName, String fileNameFromHeaderToBeChanged) throws ZipException {
        if (!fileNameFromHeaderToBeChanged.equals(oldFileName)) {
            if (fileNameFromHeaderToBeChanged.startsWith(oldFileName)) {
                String fileNameWithoutOldName = fileNameFromHeaderToBeChanged.substring(oldFileName.length());
                return newFileName + fileNameWithoutOldName;
            }
            throw new ZipException("old file name was neither an exact match nor a partial match");
        }
        return newFileName;
    }

    public static class RenameFilesTaskParameters extends AbstractZipTaskParameters {
        private final Map<String, String> fileNamesMap;

        public RenameFilesTaskParameters(Map<String, String> fileNamesMap, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.fileNamesMap = fileNamesMap;
        }
    }
}
