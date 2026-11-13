package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.p088io.outputstream.SplitOutputStream;
import net.lingala.zip4j.p088io.outputstream.ZipOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.BitUtils;
import net.lingala.zip4j.util.CrcUtil;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractAddFileToZipTask<T> extends AsyncZipTask<T> {
    private final HeaderWriter headerWriter;
    private final char[] password;
    private final ZipModel zipModel;

    AbstractAddFileToZipTask(ZipModel zipModel, char[] password, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.zipModel = zipModel;
        this.password = password;
        this.headerWriter = headerWriter;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0093 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x00a3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x00c9 A[Catch: Throwable -> 0x0099, all -> 0x00bd, TRY_LEAVE, TryCatch #5 {all -> 0x00bd, blocks: (B:3:0x0033, B:34:0x00ad, B:43:0x00c0, B:40:0x00b9, B:22:0x0095, B:46:0x00c9, B:45:0x00c5, B:23:0x0098), top: B:66:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00db  */
    /* JADX WARN: Code duplicated, block: B:60:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:? A[Catch: Throwable -> 0x0099, all -> 0x00bd, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x00bd, blocks: (B:3:0x0033, B:34:0x00ad, B:43:0x00c0, B:40:0x00b9, B:22:0x0095, B:46:0x00c9, B:45:0x00c5, B:23:0x0098), top: B:66:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:81:? A[SYNTHETIC] */
    void addFilesToZip(List<File> filesToAdd, ProgressMonitor progressMonitor, ZipParameters zipParameters, Zip4jConfig zip4jConfig) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        FileUtils.assertFilesExist(filesToAdd, zipParameters.getSymbolicLinkAction());
        byte[] readBuff = new byte[zip4jConfig.getBufferSize()];
        List<File> updatedFilesToAdd = removeFilesIfExists(filesToAdd, zipParameters, progressMonitor, zip4jConfig);
        SplitOutputStream splitOutputStream = new SplitOutputStream(this.zipModel.getZipFile(), this.zipModel.getSplitLength());
        Throwable th4 = null;
        try {
            try {
                ZipOutputStream zipOutputStream = initializeOutputStream(splitOutputStream, zip4jConfig);
                Throwable th5 = null;
                try {
                    for (File fileToAdd : updatedFilesToAdd) {
                        verifyIfTaskIsCancelled();
                        ZipParameters clonedZipParameters = cloneAndAdjustZipParameters(zipParameters, fileToAdd, progressMonitor);
                        progressMonitor.setFileName(fileToAdd.getAbsolutePath());
                        if (FileUtils.isSymbolicLink(fileToAdd) && addSymlink(clonedZipParameters)) {
                            addSymlinkToZip(fileToAdd, zipOutputStream, clonedZipParameters, splitOutputStream);
                            if (!ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(clonedZipParameters.getSymbolicLinkAction())) {
                            }
                        }
                        addFileToZip(fileToAdd, zipOutputStream, clonedZipParameters, splitOutputStream, progressMonitor, readBuff);
                    }
                    if (zipOutputStream != null) {
                        if (0 != 0) {
                            try {
                                zipOutputStream.close();
                            } catch (Throwable th6) {
                                th5.addSuppressed(th6);
                            }
                        } else {
                            zipOutputStream.close();
                        }
                    }
                    if (splitOutputStream != null) {
                        if (0 == 0) {
                            splitOutputStream.close();
                            return;
                        }
                        try {
                            splitOutputStream.close();
                        } catch (Throwable th7) {
                            th4.addSuppressed(th7);
                        }
                    }
                } catch (Throwable th8) {
                    th = th8;
                    th3 = null;
                    if (zipOutputStream != null) {
                        throw th;
                    }
                    if (th3 == null) {
                        zipOutputStream.close();
                        throw th;
                    }
                    zipOutputStream.close();
                    throw th;
                }
            } catch (Throwable th9) {
                th2 = th9;
                th = null;
                if (splitOutputStream != null) {
                    throw th2;
                }
                if (th == null) {
                    splitOutputStream.close();
                    throw th2;
                }
                try {
                    splitOutputStream.close();
                    throw th2;
                } catch (Throwable th10) {
                    th.addSuppressed(th10);
                    throw th2;
                }
            }
        } catch (Throwable th11) {
            try {
                throw th11;
            } catch (Throwable th12) {
                th = th11;
                th2 = th12;
                if (splitOutputStream != null) {
                    throw th2;
                }
                if (th == null) {
                    splitOutputStream.close();
                    throw th2;
                }
                splitOutputStream.close();
                throw th2;
            }
        }
    }

    private void addSymlinkToZip(File fileToAdd, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream) throws Throwable {
        ZipParameters clonedZipParameters = new ZipParameters(zipParameters);
        clonedZipParameters.setFileNameInZip(replaceFileNameInZip(zipParameters.getFileNameInZip(), fileToAdd.getName()));
        clonedZipParameters.setEncryptFiles(false);
        clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);
        zipOutputStream.putNextEntry(clonedZipParameters);
        String symLinkTarget = FileUtils.readSymbolicLink(fileToAdd);
        zipOutputStream.write(symLinkTarget.getBytes());
        closeEntry(zipOutputStream, splitOutputStream, fileToAdd, true);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0031 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:32:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void addFileToZip(File fileToAdd, ZipOutputStream zipOutputStream, ZipParameters zipParameters, SplitOutputStream splitOutputStream, ProgressMonitor progressMonitor, byte[] readBuff) throws Throwable {
        Throwable th;
        zipOutputStream.putNextEntry(zipParameters);
        if (fileToAdd.exists() && !fileToAdd.isDirectory()) {
            InputStream inputStream = new FileInputStream(fileToAdd);
            Throwable th2 = null;
            while (true) {
                try {
                    int readLen = inputStream.read(readBuff);
                    if (readLen == -1) {
                        break;
                    }
                    zipOutputStream.write(readBuff, 0, readLen);
                    progressMonitor.updateWorkCompleted(readLen);
                    verifyIfTaskIsCancelled();
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        th2 = th3;
                        th = th4;
                        if (inputStream != null) {
                            if (th2 != null) {
                                inputStream.close();
                            } else {
                                inputStream.close();
                            }
                        }
                        throw th;
                    }
                }
            }
            if (inputStream != null) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (Throwable th5) {
                        th2.addSuppressed(th5);
                    }
                } else {
                    inputStream.close();
                }
            }
        }
        closeEntry(zipOutputStream, splitOutputStream, fileToAdd, false);
    }

    private void closeEntry(ZipOutputStream zipOutputStream, SplitOutputStream splitOutputStream, File fileToAdd, boolean isSymlink) throws Throwable {
        FileHeader fileHeader = zipOutputStream.closeEntry();
        byte[] fileAttributes = FileUtils.getFileAttributes(fileToAdd);
        if (!isSymlink) {
            fileAttributes[3] = BitUtils.unsetBit(fileAttributes[3], 5);
        }
        fileHeader.setExternalFileAttributes(fileAttributes);
        updateLocalFileHeader(fileHeader, splitOutputStream);
    }

    long calculateWorkForFiles(List<File> filesToAdd, ZipParameters zipParameters) throws ZipException {
        long totalWork = 0;
        for (File fileToAdd : filesToAdd) {
            if (fileToAdd.exists()) {
                if (zipParameters.isEncryptFiles() && zipParameters.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
                    totalWork += fileToAdd.length() * 2;
                } else {
                    totalWork += fileToAdd.length();
                }
                String relativeFileName = FileUtils.getRelativeFileName(fileToAdd, zipParameters);
                FileHeader fileHeader = HeaderUtil.getFileHeader(getZipModel(), relativeFileName);
                if (fileHeader != null) {
                    totalWork += getZipModel().getZipFile().length() - fileHeader.getCompressedSize();
                }
            }
        }
        return totalWork;
    }

    ZipOutputStream initializeOutputStream(SplitOutputStream splitOutputStream, Zip4jConfig zip4jConfig) throws IOException {
        if (this.zipModel.getZipFile().exists()) {
            splitOutputStream.seek(HeaderUtil.getOffsetStartOfCentralDirectory(this.zipModel));
        }
        return new ZipOutputStream(splitOutputStream, this.password, zip4jConfig, this.zipModel);
    }

    void verifyZipParameters(ZipParameters parameters) throws ZipException {
        if (parameters == null) {
            throw new ZipException("cannot validate zip parameters");
        }
        if (parameters.getCompressionMethod() != CompressionMethod.STORE && parameters.getCompressionMethod() != CompressionMethod.DEFLATE) {
            throw new ZipException("unsupported compression type");
        }
        if (parameters.isEncryptFiles()) {
            if (parameters.getEncryptionMethod() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set, when encrypt files flag is set");
            }
            if (this.password == null || this.password.length <= 0) {
                throw new ZipException("input password is empty or null");
            }
            return;
        }
        parameters.setEncryptionMethod(EncryptionMethod.NONE);
    }

    void updateLocalFileHeader(FileHeader fileHeader, SplitOutputStream splitOutputStream) throws IOException {
        this.headerWriter.updateLocalFileHeader(fileHeader, getZipModel(), splitOutputStream);
    }

    private ZipParameters cloneAndAdjustZipParameters(ZipParameters zipParameters, File fileToAdd, ProgressMonitor progressMonitor) throws IOException {
        ZipParameters clonedZipParameters = new ZipParameters(zipParameters);
        clonedZipParameters.setLastModifiedFileTime(Zip4jUtil.epochToExtendedDosTime(fileToAdd.lastModified()));
        if (fileToAdd.isDirectory()) {
            clonedZipParameters.setEntrySize(0L);
        } else {
            clonedZipParameters.setEntrySize(fileToAdd.length());
        }
        clonedZipParameters.setWriteExtendedLocalFileHeader(false);
        clonedZipParameters.setLastModifiedFileTime(fileToAdd.lastModified());
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(zipParameters.getFileNameInZip())) {
            String relativeFileName = FileUtils.getRelativeFileName(fileToAdd, zipParameters);
            clonedZipParameters.setFileNameInZip(relativeFileName);
        }
        if (fileToAdd.isDirectory()) {
            clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);
            clonedZipParameters.setEncryptionMethod(EncryptionMethod.NONE);
            clonedZipParameters.setEncryptFiles(false);
        } else {
            if (clonedZipParameters.isEncryptFiles() && clonedZipParameters.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
                progressMonitor.setCurrentTask(ProgressMonitor.Task.CALCULATE_CRC);
                clonedZipParameters.setEntryCRC(CrcUtil.computeFileCrc(fileToAdd, progressMonitor));
                progressMonitor.setCurrentTask(ProgressMonitor.Task.ADD_ENTRY);
            }
            if (fileToAdd.length() == 0) {
                clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);
            }
        }
        return clonedZipParameters;
    }

    private List<File> removeFilesIfExists(List<File> files, ZipParameters zipParameters, ProgressMonitor progressMonitor, Zip4jConfig zip4jConfig) throws ZipException {
        List<File> filesToAdd = new ArrayList<>(files);
        if (this.zipModel.getZipFile().exists()) {
            for (File file : files) {
                if (!Zip4jUtil.isStringNotNullAndNotEmpty(file.getName())) {
                    filesToAdd.remove(file);
                }
                String fileName = FileUtils.getRelativeFileName(file, zipParameters);
                FileHeader fileHeader = HeaderUtil.getFileHeader(this.zipModel, fileName);
                if (fileHeader != null) {
                    if (zipParameters.isOverrideExistingFilesInZip()) {
                        progressMonitor.setCurrentTask(ProgressMonitor.Task.REMOVE_ENTRY);
                        removeFile(fileHeader, progressMonitor, zip4jConfig);
                        verifyIfTaskIsCancelled();
                        progressMonitor.setCurrentTask(ProgressMonitor.Task.ADD_ENTRY);
                    } else {
                        filesToAdd.remove(file);
                    }
                }
            }
        }
        return filesToAdd;
    }

    void removeFile(FileHeader fileHeader, ProgressMonitor progressMonitor, Zip4jConfig zip4jConfig) throws ZipException {
        AsyncZipTask.AsyncTaskParameters asyncTaskParameters = new AsyncZipTask.AsyncTaskParameters(null, false, progressMonitor);
        RemoveFilesFromZipTask removeFilesFromZipTask = new RemoveFilesFromZipTask(this.zipModel, this.headerWriter, asyncTaskParameters);
        RemoveFilesFromZipTask.RemoveFilesFromZipTaskParameters parameters = new RemoveFilesFromZipTask.RemoveFilesFromZipTaskParameters(Collections.singletonList(fileHeader.getFileName()), zip4jConfig);
        removeFilesFromZipTask.execute(parameters);
    }

    private String replaceFileNameInZip(String fileInZipWithPath, String newFileName) {
        if (fileInZipWithPath.contains(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            return fileInZipWithPath.substring(0, fileInZipWithPath.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1) + newFileName;
        }
        return newFileName;
    }

    private boolean addSymlink(ZipParameters zipParameters) {
        return ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(zipParameters.getSymbolicLinkAction()) || ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.getSymbolicLinkAction());
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.ADD_ENTRY;
    }

    protected ZipModel getZipModel() {
        return this.zipModel;
    }
}
