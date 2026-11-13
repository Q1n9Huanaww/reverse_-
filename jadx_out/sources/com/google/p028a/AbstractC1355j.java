package com.google.p028a;

import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: renamed from: com.google.a.j */
/* JADX INFO: compiled from: LuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1355j {

    /* JADX INFO: renamed from: a */
    public final int f2820a;

    /* JADX INFO: renamed from: b */
    public final int f2821b;

    /* JADX INFO: renamed from: a */
    public abstract byte[] mo2423a();

    /* JADX INFO: renamed from: a */
    public abstract byte[] mo2424a(int i, byte[] bArr);

    /* JADX INFO: renamed from: b */
    public boolean mo2425b() {
        return false;
    }

    /* JADX INFO: renamed from: c */
    public boolean mo2426c() {
        return false;
    }

    protected AbstractC1355j(int i, int i2) {
        this.f2820a = i;
        this.f2821b = i2;
    }

    /* JADX INFO: renamed from: g */
    private int m2606g() {
        return this.f2820a;
    }

    /* JADX INFO: renamed from: h */
    private int m2607h() {
        return this.f2821b;
    }

    /* JADX INFO: renamed from: a */
    public AbstractC1355j mo2422a(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    /* JADX INFO: renamed from: d */
    public AbstractC1355j mo2427d() {
        return new C1330i(this);
    }

    /* JADX INFO: renamed from: e */
    public AbstractC1355j mo2428e() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    /* JADX INFO: renamed from: f */
    public AbstractC1355j mo2429f() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        char c;
        byte[] bArr = new byte[this.f2820a];
        StringBuilder sb = new StringBuilder(this.f2821b * (this.f2820a + 1));
        byte[] bArrMo2424a = bArr;
        for (int i = 0; i < this.f2821b; i++) {
            bArrMo2424a = mo2424a(i, bArrMo2424a);
            for (int i2 = 0; i2 < this.f2820a; i2++) {
                int i3 = bArrMo2424a[i2] & 255;
                if (i3 < 64) {
                    c = '#';
                } else if (i3 < 128) {
                    c = '+';
                } else {
                    c = i3 < 192 ? FilenameUtils.EXTENSION_SEPARATOR : ' ';
                }
                sb.append(c);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
