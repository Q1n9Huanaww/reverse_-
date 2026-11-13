package net.lingala.zip4j.p088io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.AESEncrypter;
import net.lingala.zip4j.model.ZipParameters;

/* JADX INFO: loaded from: classes2.dex */
class AesCipherOutputStream extends CipherOutputStream<AESEncrypter> {
    private byte[] pendingBuffer;
    private int pendingBufferLength;

    public AesCipherOutputStream(ZipEntryOutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException {
        super(outputStream, zipParameters, password);
        this.pendingBuffer = new byte[16];
        this.pendingBufferLength = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream
    public AESEncrypter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] password) throws IOException {
        AESEncrypter encrypter = new AESEncrypter(password, zipParameters.getAesKeyStrength());
        writeAesEncryptionHeaderData(encrypter);
        return encrypter;
    }

    private void writeAesEncryptionHeaderData(AESEncrypter encrypter) throws IOException {
        writeHeaders(encrypter.getSaltBytes());
        writeHeaders(encrypter.getDerivedPasswordVerifier());
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
        if (len >= 16 - this.pendingBufferLength) {
            System.arraycopy(b, off, this.pendingBuffer, this.pendingBufferLength, 16 - this.pendingBufferLength);
            super.write(this.pendingBuffer, 0, this.pendingBuffer.length);
            int off2 = 16 - this.pendingBufferLength;
            int len2 = len - off2;
            this.pendingBufferLength = 0;
            if (len2 != 0 && len2 % 16 != 0) {
                System.arraycopy(b, (len2 + off2) - (len2 % 16), this.pendingBuffer, 0, len2 % 16);
                this.pendingBufferLength = len2 % 16;
                len2 -= this.pendingBufferLength;
            }
            super.write(b, off2, len2);
            return;
        }
        System.arraycopy(b, off, this.pendingBuffer, this.pendingBufferLength, len);
        this.pendingBufferLength += len;
    }

    @Override // net.lingala.zip4j.p088io.outputstream.CipherOutputStream
    public void closeEntry() throws IOException {
        if (this.pendingBufferLength != 0) {
            super.write(this.pendingBuffer, 0, this.pendingBufferLength);
            this.pendingBufferLength = 0;
        }
        writeHeaders(getEncrypter().getFinalMac());
        super.closeEntry();
    }
}
