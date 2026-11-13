package com.cyjh.mobileanjian.ipc.share.proto;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.share.proto.b */
/* JADX INFO: compiled from: IpcRaw.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0770b extends AbstractC0771c {

    /* JADX INFO: renamed from: a */
    private Ipc.IpcMessage f1156a;

    public C0770b(Ipc.IpcMessage ipcMessage) {
        this.f1156a = null;
        this.f1156a = ipcMessage;
    }

    @Override // com.cyjh.mobileanjian.ipc.share.proto.AbstractC0771c
    /* JADX INFO: renamed from: a */
    public final byte[] mo801a() {
        if (this.f1156a == null) {
            return null;
        }
        return this.f1156a.toByteArray();
    }

    /* JADX INFO: renamed from: a */
    public static final Ipc.IpcMessage m800a(int i) {
        return Ipc.IpcMessage.newBuilder().setCmd(i).build();
    }
}
