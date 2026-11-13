package net.lingala.zip4j.p088io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.StandardEncrypter;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
class ZipStandardCipherOutputStream extends CipherOutputStream<StandardEncrypter> {
    public ZipStandardCipherOutputStream(ZipEntryOutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException {
        super(outputStream, zipParameters, password);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream
    public StandardEncrypter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException {
        long key = getEncryptionKey(zipParameters);
        StandardEncrypter encrypter = new StandardEncrypter(password, key);
        writeHeaders(encrypter.getHeaderBytes());
        return encrypter;
    }

    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    private long getEncryptionKey(ZipParameters zipParameters) {
        if (!zipParameters.isWriteExtendedLocalFileHeader()) {
            return zipParameters.getEntryCRC();
        }
        long dosTime = Zip4jUtil.epochToExtendedDosTime(zipParameters.getLastModifiedFileTime());
        return (65535 & dosTime) << 16;
    }
}
