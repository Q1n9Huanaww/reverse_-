package com.sun.mail.iap;

import com.sun.mail.util.ASCIIUtility;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ResponseInputStream {
    private static final int incrementSlop = 16;
    private static final int maxIncrement = 262144;
    private static final int minIncrement = 256;
    private BufferedInputStream bin;

    public ResponseInputStream(InputStream inputStream) {
        this.bin = new BufferedInputStream(inputStream, 2048);
    }

    public ByteArray readResponse() throws IOException {
        return readResponse(null);
    }

    public ByteArray readResponse(ByteArray byteArray) throws IOException {
        byte[] bytes;
        if (byteArray == null) {
            byteArray = new ByteArray(new byte[128], 0, 128);
        }
        byte[] bytes2 = byteArray.getBytes();
        int i = 0;
        while (true) {
            byte[] bytes3 = bytes2;
            int i2 = i;
            boolean z = false;
            int i3 = 0;
            while (!z && (i3 = this.bin.read()) != -1) {
                if (i3 == 10 && i2 > 0 && bytes3[i2 - 1] == 13) {
                    z = true;
                }
                if (i2 >= bytes3.length) {
                    int length = bytes3.length;
                    if (length > 262144) {
                        length = 262144;
                    }
                    byteArray.grow(length);
                    bytes3 = byteArray.getBytes();
                }
                bytes3[i2] = (byte) i3;
                i2++;
            }
            if (i3 == -1) {
                throw new IOException();
            }
            if (i2 >= 5) {
                int i4 = i2 - 3;
                if (bytes3[i4] == 125) {
                    int i5 = i2 - 4;
                    while (i5 >= 0 && bytes3[i5] != 123) {
                        i5--;
                    }
                    if (i5 >= 0) {
                        try {
                            int i6 = ASCIIUtility.parseInt(bytes3, i5 + 1, i4);
                            if (i6 > 0) {
                                int length2 = bytes3.length - i2;
                                int i7 = i6 + 16;
                                if (i7 > length2) {
                                    int i8 = i7 - length2;
                                    byteArray.grow(256 <= i8 ? i8 : 256);
                                    bytes = byteArray.getBytes();
                                } else {
                                    bytes = bytes3;
                                }
                                while (i6 > 0) {
                                    int i9 = this.bin.read(bytes, i2, i6);
                                    i6 -= i9;
                                    i2 += i9;
                                }
                                bytes2 = bytes;
                                i = i2;
                            } else {
                                i = i2;
                                bytes2 = bytes3;
                            }
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            }
            byteArray.setCount(i2);
            return byteArray;
        }
    }
}
