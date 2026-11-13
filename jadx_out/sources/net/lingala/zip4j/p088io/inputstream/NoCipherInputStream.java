package net.lingala.zip4j.p088io.inputstream;

import java.io.IOException;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.model.LocalFileHeader;

/* JADX INFO: loaded from: classes2.dex */
class NoCipherInputStream extends CipherInputStream {
    public NoCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] password, int bufferSize) throws IOException {
        super(zipEntryInputStream, localFileHeader, password, bufferSize);
    }

    @Override // net.lingala.zip4j.p088io.inputstream.CipherInputStream
    protected Decrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] password) {
        return new NoDecrypter();
    }

    static class NoDecrypter implements Decrypter {
        NoDecrypter() {
        }

        @Override // net.lingala.zip4j.crypto.Decrypter
        public int decryptData(byte[] buff, int start, int len) {
            return len;
        }
    }
}
