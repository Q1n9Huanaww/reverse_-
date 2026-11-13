package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.InterfaceC0637g;

/* JADX INFO: renamed from: com.b.a.aq */
/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0597aq {
    /* JADX INFO: renamed from: a */
    public abstract C0588ah mo415a();

    /* JADX INFO: renamed from: a */
    public abstract void mo416a(InterfaceC0637g interfaceC0637g);

    /* JADX INFO: renamed from: b */
    public long mo417b() {
        return -1L;
    }

    /* JADX INFO: renamed from: a */
    public static AbstractC0597aq m414a(byte[] bArr) {
        int length = bArr.length;
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        C0503c.m115a(bArr.length, length);
        return new C0598ar(length, bArr);
    }
}
