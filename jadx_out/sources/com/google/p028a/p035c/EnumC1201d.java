package com.google.p028a.p035c;

import com.google.p028a.C1303h;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.c.d */
/* JADX INFO: compiled from: CharacterSetECI.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1201d {
    Cp437(new int[]{0, 2}, new String[0]),
    ISO8859_1(new int[]{1, 3}, "ISO-8859-1"),
    ISO8859_2(4, "ISO-8859-2"),
    ISO8859_3(5, "ISO-8859-3"),
    ISO8859_4(6, "ISO-8859-4"),
    ISO8859_5(7, "ISO-8859-5"),
    ISO8859_6(8, "ISO-8859-6"),
    ISO8859_7(9, "ISO-8859-7"),
    ISO8859_8(10, "ISO-8859-8"),
    ISO8859_9(11, "ISO-8859-9"),
    ISO8859_10(12, "ISO-8859-10"),
    ISO8859_11(13, "ISO-8859-11"),
    ISO8859_13(15, "ISO-8859-13"),
    ISO8859_14(16, "ISO-8859-14"),
    ISO8859_15(17, "ISO-8859-15"),
    ISO8859_16(18, "ISO-8859-16"),
    SJIS(20, "Shift_JIS"),
    Cp1250(21, "windows-1250"),
    Cp1251(22, "windows-1251"),
    Cp1252(23, "windows-1252"),
    Cp1256(24, "windows-1256"),
    UnicodeBigUnmarked(25, "UTF-16BE", "UnicodeBig"),
    UTF8(26, "UTF-8"),
    ASCII(new int[]{27, 170}, "US-ASCII"),
    Big5,
    GB18030(29, C1209l.f2171b, "EUC_CN", "GBK"),
    EUC_KR(30, "EUC-KR");


    /* JADX INFO: renamed from: C */
    private static final Map<Integer, EnumC1201d> f2105C = new HashMap();

    /* JADX INFO: renamed from: D */
    private static final Map<String, EnumC1201d> f2106D = new HashMap();

    /* JADX INFO: renamed from: B */
    public final int[] f2134B;

    /* JADX INFO: renamed from: E */
    private final String[] f2135E;

    static {
        for (EnumC1201d enumC1201d : values()) {
            for (int i : enumC1201d.f2134B) {
                f2105C.put(Integer.valueOf(i), enumC1201d);
            }
            f2106D.put(enumC1201d.name(), enumC1201d);
            for (String str : enumC1201d.f2135E) {
                f2106D.put(str, enumC1201d);
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: (I)V */
    EnumC1201d() {
        this(new int[]{28}, new String[0]);
    }

    EnumC1201d(int i, String... strArr) {
        this.f2134B = new int[]{i};
        this.f2135E = strArr;
    }

    EnumC1201d(int[] iArr, String... strArr) {
        this.f2134B = iArr;
        this.f2135E = strArr;
    }

    /* JADX INFO: renamed from: a */
    private int m1826a() {
        return this.f2134B[0];
    }

    /* JADX INFO: renamed from: a */
    public static EnumC1201d m1827a(int i) throws C1303h {
        if (i < 0 || i >= 900) {
            throw C1303h.m2217a();
        }
        return f2105C.get(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: a */
    public static EnumC1201d m1828a(String str) {
        return f2106D.get(str);
    }
}
