package com.p004b.p005a.p006a.p011e;

/* JADX INFO: renamed from: com.b.a.a.e.b */
/* JADX INFO: compiled from: ErrorCode.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC0539b {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    REFUSED_STREAM(7),
    CANCEL(8);


    /* JADX INFO: renamed from: g */
    public final int f287g;

    EnumC0539b(int i) {
        this.f287g = i;
    }

    /* JADX INFO: renamed from: a */
    public static EnumC0539b m231a(int i) {
        for (EnumC0539b enumC0539b : values()) {
            if (enumC0539b.f287g == i) {
                return enumC0539b;
            }
        }
        return null;
    }
}
