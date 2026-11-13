package net.lingala.zip4j.p088io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes2.dex */
class AesCipherInputStream extends CipherInputStream<AESDecrypter> {
    private byte[] aes16ByteBlock;
    private int aes16ByteBlockPointer;
    private int aes16ByteBlockReadLength;
    private int bytesCopiedInThisIteration;
    private int lengthToCopyInThisIteration;
    private int lengthToRead;
    private int offsetWithAesBlock;
    private int remainingAes16ByteBlockLength;
    private byte[] singleByteBuffer;

    public AesCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] password, int bufferSize) throws IOException {
        super(zipEntryInputStream, localFileHeader, password, bufferSize);
        this.singleByteBuffer = new byte[1];
        this.aes16ByteBlock = new byte[16];
        this.aes16ByteBlockPointer = 0;
        this.remainingAes16ByteBlockLength = 0;
        this.lengthToRead = 0;
        this.offsetWithAesBlock = 0;
        this.bytesCopiedInThisIteration = 0;
        this.lengthToCopyInThisIteration = 0;
        this.aes16ByteBlockReadLength = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream
    public AESDecrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] password) throws IOException {
        return new AESDecrypter(localFileHeader.getAesExtraDataRecord(), password, getSalt(localFileHeader), getPasswordVerifier());
    }

    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream, java.io.InputStream
    public int read() throws IOException {
        int readLen = read(this.singleByteBuffer);
        if (readLen == -1) {
            return -1;
        }
        return this.singleByteBuffer[0];
    }

    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        this.lengthToRead = len;
        this.offsetWithAesBlock = off;
        this.bytesCopiedInThisIteration = 0;
        if (this.remainingAes16ByteBlockLength != 0) {
            copyBytesFromBuffer(b, this.offsetWithAesBlock);
            if (this.bytesCopiedInThisIteration == len) {
                return this.bytesCopiedInThisIteration;
            }
        }
        if (this.lengthToRead < 16) {
            this.aes16ByteBlockReadLength = super.read(this.aes16ByteBlock, 0, this.aes16ByteBlock.length);
            this.aes16ByteBlockPointer = 0;
            if (this.aes16ByteBlockReadLength == -1) {
                this.remainingAes16ByteBlockLength = 0;
                if (this.bytesCopiedInThisIteration > 0) {
                    return this.bytesCopiedInThisIteration;
                }
                return -1;
            }
            this.remainingAes16ByteBlockLength = this.aes16ByteBlockReadLength;
            copyBytesFromBuffer(b, this.offsetWithAesBlock);
            if (this.bytesCopiedInThisIteration == len) {
                return this.bytesCopiedInThisIteration;
            }
        }
        int readLen = super.read(b, this.offsetWithAesBlock, this.lengthToRead - (this.lengthToRead % 16));
        if (readLen == -1) {
            if (this.bytesCopiedInThisIteration > 0) {
                return this.bytesCopiedInThisIteration;
            }
            return -1;
        }
        return this.bytesCopiedInThisIteration + readLen;
    }

    private void copyBytesFromBuffer(byte[] b, int off) {
        this.lengthToCopyInThisIteration = this.lengthToRead < this.remainingAes16ByteBlockLength ? this.lengthToRead : this.remainingAes16ByteBlockLength;
        System.arraycopy(this.aes16ByteBlock, this.aes16ByteBlockPointer, b, off, this.lengthToCopyInThisIteration);
        incrementAesByteBlockPointer(this.lengthToCopyInThisIteration);
        decrementRemainingAesBytesLength(this.lengthToCopyInThisIteration);
        this.bytesCopiedInThisIteration += this.lengthToCopyInThisIteration;
        this.lengthToRead -= this.lengthToCopyInThisIteration;
        this.offsetWithAesBlock += this.lengthToCopyInThisIteration;
    }

    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream
    protected void endOfEntryReached(InputStream inputStream) throws IOException {
        verifyContent(readStoredMac(inputStream));
    }

    private void verifyContent(byte[] storedMac) throws IOException {
        if (!getLocalFileHeader().isDataDescriptorExists() || !CompressionMethod.DEFLATE.equals(Zip4jUtil.getCompressionMethod(getLocalFileHeader()))) {
            byte[] calculatedMac = getDecrypter().getCalculatedAuthenticationBytes();
            byte[] first10BytesOfCalculatedMac = new byte[10];
            System.arraycopy(calculatedMac, 0, first10BytesOfCalculatedMac, 0, 10);
            if (!Arrays.equals(storedMac, first10BytesOfCalculatedMac)) {
                throw new IOException("Reached end of data for this entry, but aes verification failed");
            }
        }
    }

    protected byte[] readStoredMac(InputStream inputStream) throws IOException {
        byte[] storedMac = new byte[10];
        int readLen = Zip4jUtil.readFully(inputStream, storedMac);
        if (readLen != 10) {
            throw new ZipException("Invalid AES Mac bytes. Could not read sufficient data");
        }
        return storedMac;
    }

    private byte[] getSalt(LocalFileHeader localFileHeader) throws IOException {
        if (localFileHeader.getAesExtraDataRecord() == null) {
            throw new IOException("invalid aes extra data record");
        }
        AESExtraDataRecord aesExtraDataRecord = localFileHeader.getAesExtraDataRecord();
        byte[] saltBytes = new byte[aesExtraDataRecord.getAesKeyStrength().getSaltLength()];
        readRaw(saltBytes);
        return saltBytes;
    }

    private byte[] getPasswordVerifier() throws IOException {
        byte[] pvBytes = new byte[2];
        readRaw(pvBytes);
        return pvBytes;
    }

    private void incrementAesByteBlockPointer(int incrementBy) {
        this.aes16ByteBlockPointer += incrementBy;
        if (this.aes16ByteBlockPointer >= 15) {
            this.aes16ByteBlockPointer = 15;
        }
    }

    private void decrementRemainingAesBytesLength(int decrementBy) {
        this.remainingAes16ByteBlockLength -= decrementBy;
        if (this.remainingAes16ByteBlockLength <= 0) {
            this.remainingAes16ByteBlockLength = 0;
        }
    }
}
