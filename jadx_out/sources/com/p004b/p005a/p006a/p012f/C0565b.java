package com.p004b.p005a.p006a.p012f;

import java.io.File;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.f.b */
/* JADX INFO: compiled from: FileSystem.java */
/* JADX INFO: loaded from: classes.dex */
final class C0565b implements InterfaceC0564a {
    C0565b() {
    }

    @Override // com.p004b.p005a.p006a.p012f.InterfaceC0564a
    /* JADX INFO: renamed from: a */
    public final void mo282a(File file) throws IOException {
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }
}
