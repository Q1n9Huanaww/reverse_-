package com.umeng.commonsdk.proguard;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bl */
/* JADX INFO: compiled from: TIOStreamTransport.java */
/* JADX INFO: loaded from: classes.dex */
public class C1775bl extends AbstractC1777bn {

    /* JADX INFO: renamed from: a */
    protected InputStream f4127a;

    /* JADX INFO: renamed from: b */
    protected OutputStream f4128b;

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: a */
    public boolean mo3931a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: b */
    public void mo3932b() throws C1778bo {
    }

    protected C1775bl() {
        this.f4127a = null;
        this.f4128b = null;
    }

    public C1775bl(InputStream inputStream) {
        this.f4127a = null;
        this.f4128b = null;
        this.f4127a = inputStream;
    }

    public C1775bl(OutputStream outputStream) {
        this.f4127a = null;
        this.f4128b = null;
        this.f4128b = outputStream;
    }

    public C1775bl(InputStream inputStream, OutputStream outputStream) {
        this.f4127a = null;
        this.f4128b = null;
        this.f4127a = inputStream;
        this.f4128b = outputStream;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: c */
    public void mo3934c() {
        if (this.f4127a != null) {
            try {
                this.f4127a.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
            this.f4127a = null;
        }
        if (this.f4128b != null) {
            try {
                this.f4128b.close();
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            this.f4128b = null;
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: a */
    public int mo3930a(byte[] bArr, int i, int i2) throws C1778bo {
        if (this.f4127a == null) {
            throw new C1778bo(1, "Cannot read from null inputStream");
        }
        try {
            int i3 = this.f4127a.read(bArr, i, i2);
            if (i3 < 0) {
                throw new C1778bo(4);
            }
            return i3;
        } catch (IOException e) {
            throw new C1778bo(0, e);
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: b */
    public void mo3933b(byte[] bArr, int i, int i2) throws C1778bo {
        if (this.f4128b == null) {
            throw new C1778bo(1, "Cannot write to null outputStream");
        }
        try {
            this.f4128b.write(bArr, i, i2);
        } catch (IOException e) {
            throw new C1778bo(0, e);
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: d */
    public void mo3935d() throws C1778bo {
        if (this.f4128b == null) {
            throw new C1778bo(1, "Cannot flush null outputStream");
        }
        try {
            this.f4128b.flush();
        } catch (IOException e) {
            throw new C1778bo(0, e);
        }
    }
}
