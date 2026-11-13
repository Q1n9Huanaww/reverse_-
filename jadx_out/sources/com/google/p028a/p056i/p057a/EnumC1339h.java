package com.google.p028a.p056i.p057a;

/* JADX INFO: renamed from: com.google.a.i.a.h */
/* JADX INFO: compiled from: Mode.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1339h {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);


    /* JADX INFO: renamed from: k */
    public final int f2754k;

    /* JADX INFO: renamed from: l */
    private final int[] f2755l;

    EnumC1339h(int[] iArr, int i) {
        this.f2755l = iArr;
        this.f2754k = i;
    }

    /* JADX INFO: renamed from: a */
    public static EnumC1339h m2467a(int i) {
        switch (i) {
            case 0:
                return TERMINATOR;
            case 1:
                return NUMERIC;
            case 2:
                return ALPHANUMERIC;
            case 3:
                return STRUCTURED_APPEND;
            case 4:
                return BYTE;
            case 5:
                return FNC1_FIRST_POSITION;
            case 6:
            case 10:
            case 11:
            case 12:
            default:
                throw new IllegalArgumentException();
            case 7:
                return ECI;
            case 8:
                return KANJI;
            case 9:
                return FNC1_SECOND_POSITION;
            case 13:
                return HANZI;
        }
    }

    /* JADX INFO: renamed from: a */
    private int m2466a() {
        return this.f2754k;
    }

    /* JADX INFO: renamed from: a */
    public final int m2468a(C1341j c1341j) {
        char c;
        int i = c1341j.f2759a;
        if (i <= 9) {
            c = 0;
        } else {
            c = i <= 26 ? (char) 1 : (char) 2;
        }
        return this.f2755l[c];
    }
}
