package com.umeng.commonsdk.proguard;

import java.util.BitSet;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bf */
/* JADX INFO: compiled from: TTupleProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1769bf extends C1756at {

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bf$a */
    /* JADX INFO: compiled from: TTupleProtocol.java */
    public static class a implements InterfaceC1765bb {
        @Override // com.umeng.commonsdk.proguard.InterfaceC1765bb
        /* JADX INFO: renamed from: a */
        public AbstractC1762az mo3893a(AbstractC1777bn abstractC1777bn) {
            return new C1769bf(abstractC1777bn);
        }
    }

    public C1769bf(AbstractC1777bn abstractC1777bn) {
        super(abstractC1777bn);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: D */
    public Class<? extends InterfaceC1771bh> mo3918D() {
        return AbstractC1774bk.class;
    }

    /* JADX INFO: renamed from: a */
    public void m3928a(BitSet bitSet, int i) throws C1743ag {
        for (byte b : m3927b(bitSet, i)) {
            mo3851a(b);
        }
    }

    /* JADX INFO: renamed from: b */
    public BitSet m3929b(int i) throws C1743ag {
        int iCeil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[iCeil];
        for (int i2 = 0; i2 < iCeil; i2++) {
            bArr[i2] = mo3887u();
        }
        return m3926a(bArr);
    }

    /* JADX INFO: renamed from: a */
    public static BitSet m3926a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m3927b(BitSet bitSet, int i) {
        byte[] bArr = new byte[(int) Math.ceil(((double) i) / 8.0d)];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) ((1 << (i2 % 8)) | bArr[length]);
            }
        }
        return bArr;
    }
}
