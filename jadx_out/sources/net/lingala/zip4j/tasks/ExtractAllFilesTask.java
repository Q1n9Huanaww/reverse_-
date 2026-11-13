package net.lingala.zip4j.tasks;

import java.io.IOException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.p088io.inputstream.SplitInputStream;
import net.lingala.zip4j.p088io.inputstream.ZipInputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.UnzipUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ExtractAllFilesTask extends AbstractExtractFileTask<ExtractAllFilesTaskParameters> {
    private final char[] password;
    private SplitInputStream splitInputStream;

    public ExtractAllFilesTask(ZipModel zipModel, char[] password, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, unzipParameters, asyncTaskParameters);
        this.password = password;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:17:0x0040 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x008e A[Catch: all -> 0x0046, TRY_LEAVE, TryCatch #0 {all -> 0x0046, blocks: (B:3:0x0001, B:31:0x0073, B:38:0x0085, B:37:0x0081, B:18:0x0042, B:41:0x008e, B:40:0x008a, B:19:0x0045), top: B:43:0x0001, inners: #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:? A[Catch: all -> 0x0046, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0046, blocks: (B:3:0x0001, B:31:0x0073, B:38:0x0085, B:37:0x0081, B:18:0x0042, B:41:0x008e, B:40:0x008a, B:19:0x0045), top: B:43:0x0001, inners: #1, #4 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractAllFilesTaskParameters taskParameters, ProgressMonitor progressMonitor) throws IOException {
        Throwable th;
        Throwable th2;
        try {
            ZipInputStream zipInputStream = prepareZipInputStream(taskParameters.zip4jConfig);
            Throwable th3 = null;
            try {
                for (FileHeader fileHeader : getZipModel().getCentralDirectory().getFileHeaders()) {
                    if (fileHeader.getFileName().startsWith("__MACOSX")) {
                        progressMonitor.updateWorkCompleted(fileHeader.getUncompressedSize());
                    } else {
                        this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
                        byte[] readBuff = new byte[taskParameters.zip4jConfig.getBufferSize()];
                        extractFile(zipInputStream, fileHeader, taskParameters.outputPath, null, progressMonitor, readBuff);
                        verifyIfTaskIsCancelled();
                    }
                }
                if (zipInputStream != null) {
                    if (0 != 0) {
                        try {
                            zipInputStream.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    } else {
                        zipInputStream.close();
                    }
                }
                if (this.splitInputStream != null) {
                    this.splitInputStream.close();
                }
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    th = th5;
                    th2 = th6;
                    if (zipInputStream != null) {
                        throw th2;
                    }
                    if (th == null) {
                        zipInputStream.close();
                        throw th2;
                    }
                    zipInputStream.close();
                    throw th2;
                }
            }
        } catch (Throwable th7) {
            if (this.splitInputStream != null) {
                this.splitInputStream.close();
            }
            throw th7;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(ExtractAllFilesTaskParameters taskParameters) {
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(getZipModel().getCentralDirectory().getFileHeaders());
    }

    private ZipInputStream prepareZipInputStream(Zip4jConfig zip4jConfig) throws IOException {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        FileHeader fileHeader = getFirstFileHeader(getZipModel());
        if (fileHeader != null) {
            this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
        }
        return new ZipInputStream(this.splitInputStream, this.password, zip4jConfig);
    }

    private FileHeader getFirstFileHeader(ZipModel zipModel) {
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() == 0) {
            return null;
        }
        return zipModel.getCentralDirectory().getFileHeaders().get(0);
    }

    public static class ExtractAllFilesTaskParameters extends AbstractZipTaskParameters {
        private final String outputPath;

        public ExtractAllFilesTaskParameters(String outputPath, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputPath = outputPath;
        }
    }
}
