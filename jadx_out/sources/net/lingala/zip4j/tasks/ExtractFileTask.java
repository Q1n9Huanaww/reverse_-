package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p088io.inputstream.SplitInputStream;
import net.lingala.zip4j.p088io.inputstream.ZipInputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.UnzipUtil;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ExtractFileTask extends AbstractExtractFileTask<ExtractFileTaskParameters> {
    private char[] password;
    private SplitInputStream splitInputStream;

    public ExtractFileTask(ZipModel zipModel, char[] password, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, unzipParameters, asyncTaskParameters);
        this.password = password;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:14:0x0049 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0079 A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #5 {all -> 0x004f, blocks: (B:2:0x0000, B:24:0x005e, B:31:0x0070, B:30:0x006c, B:15:0x004b, B:34:0x0079, B:33:0x0075, B:16:0x004e), top: B:44:0x0000, inners: #0, #3 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:? A[Catch: all -> 0x004f, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x004f, blocks: (B:2:0x0000, B:24:0x005e, B:31:0x0070, B:30:0x006c, B:15:0x004b, B:34:0x0079, B:33:0x0075, B:16:0x004e), top: B:44:0x0000, inners: #0, #3 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractFileTaskParameters taskParameters, ProgressMonitor progressMonitor) throws IOException {
        Throwable th;
        try {
            ZipInputStream zipInputStream = createZipInputStream(taskParameters.fileHeader, taskParameters.zip4jConfig);
            Throwable th2 = null;
            try {
                List<FileHeader> fileHeadersUnderDirectory = getFileHeadersToExtract(taskParameters.fileHeader);
                byte[] readBuff = new byte[taskParameters.zip4jConfig.getBufferSize()];
                for (FileHeader fileHeader : fileHeadersUnderDirectory) {
                    String newFileName = determineNewFileName(taskParameters.newFileName, taskParameters.fileHeader, fileHeader);
                    extractFile(zipInputStream, fileHeader, taskParameters.outputPath, newFileName, progressMonitor, readBuff);
                }
                if (zipInputStream != null) {
                    if (0 != 0) {
                        try {
                            zipInputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    } else {
                        zipInputStream.close();
                    }
                }
                if (this.splitInputStream != null) {
                    this.splitInputStream.close();
                }
            } catch (Throwable th4) {
                th = th4;
                th = null;
                if (zipInputStream != null) {
                    throw th;
                }
                if (th == null) {
                    zipInputStream.close();
                    throw th;
                }
                zipInputStream.close();
                throw th;
            }
        } catch (Throwable th5) {
            if (this.splitInputStream != null) {
                this.splitInputStream.close();
            }
            throw th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(ExtractFileTaskParameters taskParameters) {
        List<FileHeader> fileHeadersUnderDirectory = getFileHeadersToExtract(taskParameters.fileHeader);
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(fileHeadersUnderDirectory);
    }

    private List<FileHeader> getFileHeadersToExtract(FileHeader rootFileHeader) {
        if (!rootFileHeader.isDirectory()) {
            return Collections.singletonList(rootFileHeader);
        }
        return HeaderUtil.getFileHeadersUnderDirectory(getZipModel().getCentralDirectory().getFileHeaders(), rootFileHeader);
    }

    private ZipInputStream createZipInputStream(FileHeader fileHeader, Zip4jConfig zip4jConfig) throws IOException {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
        return new ZipInputStream(this.splitInputStream, this.password, zip4jConfig);
    }

    private String determineNewFileName(String newFileName, FileHeader fileHeaderToExtract, FileHeader fileHeaderBeingExtracted) {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(newFileName) && fileHeaderToExtract.isDirectory()) {
            String fileSeparator = InternalZipConstants.ZIP_FILE_SEPARATOR;
            if (newFileName.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                fileSeparator = "";
            }
            return fileHeaderBeingExtracted.getFileName().replaceFirst(fileHeaderToExtract.getFileName(), newFileName + fileSeparator);
        }
        return newFileName;
    }

    public static class ExtractFileTaskParameters extends AbstractZipTaskParameters {
        private FileHeader fileHeader;
        private String newFileName;
        private String outputPath;

        public ExtractFileTaskParameters(String outputPath, FileHeader fileHeader, String newFileName, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputPath = outputPath;
            this.fileHeader = fileHeader;
            this.newFileName = newFileName;
        }
    }
}
