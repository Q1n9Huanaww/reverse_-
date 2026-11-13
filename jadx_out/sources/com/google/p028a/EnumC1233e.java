package com.google.p028a;

import java.util.List;

/* JADX INFO: renamed from: com.google.a.e */
/* JADX INFO: compiled from: DecodeHintType.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1233e {
    OTHER(Object.class),
    PURE_BARCODE(Void.class),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(Void.class),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(int[].class),
    ASSUME_CODE_39_CHECK_DIGIT(Void.class),
    ASSUME_GS1(Void.class),
    RETURN_CODABAR_START_END(Void.class),
    NEED_RESULT_POINT_CALLBACK(InterfaceC1366u.class),
    ALLOWED_EAN_EXTENSIONS(int[].class);


    /* JADX INFO: renamed from: l */
    private final Class<?> f2281l;

    EnumC1233e(Class cls) {
        this.f2281l = cls;
    }

    /* JADX INFO: renamed from: a */
    private Class<?> m2004a() {
        return this.f2281l;
    }
}
