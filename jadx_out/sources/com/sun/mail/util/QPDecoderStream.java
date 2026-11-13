package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* JADX INFO: loaded from: classes.dex */
public class QPDecoderStream extends FilterInputStream {

    /* JADX INFO: renamed from: ba */
    protected byte[] f3612ba;
    protected int spaces;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public QPDecoderStream(InputStream inputStream) {
        super(new PushbackInputStream(inputStream, 2));
        this.f3612ba = new byte[2];
        this.spaces = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i;
        if (this.spaces > 0) {
            this.spaces--;
            return 32;
        }
        int i2 = this.in.read();
        if (i2 == 32) {
            while (true) {
                i = this.in.read();
                if (i != 32) {
                    break;
                }
                this.spaces++;
            }
            if (i == 13 || i == 10 || i == -1) {
                this.spaces = 0;
                return i;
            }
            ((PushbackInputStream) this.in).unread(i);
            return 32;
        }
        if (i2 != 61) {
            return i2;
        }
        int i3 = this.in.read();
        if (i3 == 10) {
            return read();
        }
        if (i3 == 13) {
            int i4 = this.in.read();
            if (i4 != 10) {
                ((PushbackInputStream) this.in).unread(i4);
            }
            return read();
        }
        if (i3 == -1) {
            return -1;
        }
        this.f3612ba[0] = (byte) i3;
        this.f3612ba[1] = (byte) this.in.read();
        try {
            return ASCIIUtility.parseInt(this.f3612ba, 0, 2, 16);
        } catch (NumberFormatException unused) {
            ((PushbackInputStream) this.in).unread(this.f3612ba);
            return i2;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = read();
            if (i4 == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            bArr[i + i3] = (byte) i4;
            i3++;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }
}
