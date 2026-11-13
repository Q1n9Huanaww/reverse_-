package com.androidquery.util;

import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class PredefinedBAOS extends ByteArrayOutputStream {
    public PredefinedBAOS(int i) {
        super(i);
    }

    @Override // java.io.ByteArrayOutputStream
    public byte[] toByteArray() {
        if (this.count == this.buf.length) {
            return this.buf;
        }
        return super.toByteArray();
    }
}
