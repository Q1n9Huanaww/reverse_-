package net.lingala.zip4j.tasks;

import java.io.InputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.p088io.outputstream.SplitOutputStream;
import net.lingala.zip4j.p088io.outputstream.ZipOutputStream;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AddStreamToZipTask extends AbstractAddFileToZipTask<AddStreamToZipTaskParameters> {
    public AddStreamToZipTask(ZipModel zipModel, char[] password, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, password, headerWriter, asyncTaskParameters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:25:0x00ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x00b8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:53:0x00f0 A[Catch: Throwable -> 0x00b1, all -> 0x00e5, TRY_LEAVE, TryCatch #4 {all -> 0x00e5, blocks: (B:10:0x0065, B:41:0x00d5, B:50:0x00e7, B:47:0x00e1, B:26:0x00ad, B:53:0x00f0, B:52:0x00ec, B:27:0x00b0), top: B:71:0x0065 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0102  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:? A[Catch: Throwable -> 0x00b1, all -> 0x00e5, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00e5, blocks: (B:10:0x0065, B:41:0x00d5, B:50:0x00e7, B:47:0x00e1, B:26:0x00ad, B:53:0x00f0, B:52:0x00ec, B:27:0x00b0), top: B:71:0x0065 }] */
    /* JADX WARN: Code duplicated, block: B:82:? A[SYNTHETIC] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(AddStreamToZipTaskParameters taskParameters, ProgressMonitor progressMonitor) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4 = null;
        verifyZipParameters(taskParameters.zipParameters);
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(taskParameters.zipParameters.getFileNameInZip())) {
            throw new ZipException("fileNameInZip has to be set in zipParameters when adding stream");
        }
        removeFileIfExists(getZipModel(), taskParameters.zip4jConfig, taskParameters.zipParameters.getFileNameInZip(), progressMonitor);
        taskParameters.zipParameters.setWriteExtendedLocalFileHeader(true);
        if (taskParameters.zipParameters.getCompressionMethod().equals(CompressionMethod.STORE)) {
            taskParameters.zipParameters.setEntrySize(0L);
        }
        SplitOutputStream splitOutputStream = new SplitOutputStream(getZipModel().getZipFile(), getZipModel().getSplitLength());
        try {
            try {
                ZipOutputStream zipOutputStream = initializeOutputStream(splitOutputStream, taskParameters.zip4jConfig);
                Throwable th5 = null;
                try {
                    byte[] readBuff = new byte[taskParameters.zip4jConfig.getBufferSize()];
                    ZipParameters zipParameters = taskParameters.zipParameters;
                    zipOutputStream.putNextEntry(zipParameters);
                    if (!zipParameters.getFileNameInZip().endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && !zipParameters.getFileNameInZip().endsWith("\\")) {
                        while (true) {
                            int readLen = taskParameters.inputStream.read(readBuff);
                            if (readLen == -1) {
                                break;
                            } else {
                                zipOutputStream.write(readBuff, 0, readLen);
                            }
                        }
                    }
                    FileHeader fileHeader = zipOutputStream.closeEntry();
                    if (CompressionMethod.STORE.equals(Zip4jUtil.getCompressionMethod(fileHeader))) {
                        updateLocalFileHeader(fileHeader, splitOutputStream);
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
                    try {
                        throw th8;
                    } catch (Throwable th9) {
                        th2 = th8;
                        th3 = th9;
                        if (zipOutputStream != null) {
                            throw th3;
                        }
                        if (th2 == null) {
                            zipOutputStream.close();
                            throw th3;
                        }
                        zipOutputStream.close();
                        throw th3;
                    }
                }
            } catch (Throwable th10) {
                th = th10;
                if (splitOutputStream != null) {
                    throw th;
                }
                if (th4 == null) {
                    splitOutputStream.close();
                    throw th;
                }
                try {
                    splitOutputStream.close();
                    throw th;
                } catch (Throwable th11) {
                    th4.addSuppressed(th11);
                    throw th;
                }
            }
        } catch (Throwable th12) {
            try {
                throw th12;
            } catch (Throwable th13) {
                th4 = th12;
                th = th13;
                if (splitOutputStream != null) {
                    throw th;
                }
                if (th4 == null) {
                    splitOutputStream.close();
                    throw th;
                }
                splitOutputStream.close();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(AddStreamToZipTaskParameters taskParameters) {
        return 0L;
    }

    private void removeFileIfExists(ZipModel zipModel, Zip4jConfig zip4jConfig, String fileNameInZip, ProgressMonitor progressMonitor) throws ZipException {
        FileHeader fileHeader = HeaderUtil.getFileHeader(zipModel, fileNameInZip);
        if (fileHeader != null) {
            removeFile(fileHeader, progressMonitor, zip4jConfig);
        }
    }

    public static class AddStreamToZipTaskParameters extends AbstractZipTaskParameters {
        private final InputStream inputStream;
        private final ZipParameters zipParameters;

        public AddStreamToZipTaskParameters(InputStream inputStream, ZipParameters zipParameters, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.inputStream = inputStream;
            this.zipParameters = zipParameters;
        }
    }
}
