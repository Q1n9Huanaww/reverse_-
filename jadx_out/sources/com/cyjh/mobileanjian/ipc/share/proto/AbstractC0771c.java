package com.cyjh.mobileanjian.ipc.share.proto;

import java.nio.ByteBuffer;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.share.proto.c */
/* JADX INFO: compiled from: ProtoBufDataWraper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0771c {
    /* JADX INFO: renamed from: a */
    public abstract byte[] mo801a();

    /* JADX INFO: renamed from: b */
    private ByteBuffer m802b() {
        int length = mo801a().length;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[length + 4]);
        byteBufferWrap.putInt(length);
        byteBufferWrap.put(mo801a());
        byteBufferWrap.flip();
        return byteBufferWrap;
    }
}
