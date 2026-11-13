package net.lingala.zip4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* JADX INFO: loaded from: classes2.dex */
public class CrcUtil {
    private static final int BUF_SIZE = 16384;

    /* JADX WARN: Code duplicated, block: B:38:0x0076 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x0081  */
    /* JADX WARN: Code duplicated, block: B:48:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:? A[SYNTHETIC] */
    public static long computeFileCrc(File inputFile, ProgressMonitor progressMonitor) throws Throwable {
        Throwable th;
        Throwable th2;
        long value;
        if (inputFile == null || !inputFile.exists() || !inputFile.canRead()) {
            throw new ZipException("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
        }
        byte[] buff = new byte[16384];
        CRC32 crc32 = new CRC32();
        InputStream inputStream = new FileInputStream(inputFile);
        Throwable th3 = null;
        while (true) {
            try {
                int readLen = inputStream.read(buff);
                if (readLen == -1) {
                    value = crc32.getValue();
                    if (inputStream == null) {
                        break;
                    }
                    if (0 == 0) {
                        inputStream.close();
                        break;
                    }
                    try {
                        inputStream.close();
                        break;
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                        break;
                    }
                }
                crc32.update(buff, 0, readLen);
                if (progressMonitor != null) {
                    progressMonitor.updateWorkCompleted(readLen);
                    if (progressMonitor.isCancelAllTasks()) {
                        progressMonitor.setResult(ProgressMonitor.Result.CANCELLED);
                        progressMonitor.setState(ProgressMonitor.State.READY);
                        value = 0;
                        if (inputStream == null) {
                            break;
                        }
                        if (0 == 0) {
                            inputStream.close();
                            break;
                        }
                        try {
                            inputStream.close();
                            break;
                        } catch (Throwable th5) {
                            th3.addSuppressed(th5);
                            break;
                        }
                    }
                }
            } catch (Throwable th6) {
                try {
                    throw th6;
                } catch (Throwable th7) {
                    th = th6;
                    th2 = th7;
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
        }
        return value;
    }
}
