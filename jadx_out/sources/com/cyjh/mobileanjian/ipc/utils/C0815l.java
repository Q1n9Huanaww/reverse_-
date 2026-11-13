package com.cyjh.mobileanjian.ipc.utils;

import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import java.util.HashMap;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.l */
/* JADX INFO: compiled from: ReturnTypeMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0815l {

    /* JADX INFO: renamed from: a */
    private static final HashMap<String, Ipc.FundType> f1429a = new HashMap<String, Ipc.FundType>() { // from class: com.cyjh.mobileanjian.ipc.utils.l.1
        {
            put("void", Ipc.FundType.VOID);
            put("boolean", Ipc.FundType.BOOLEAN);
            put("int", Ipc.FundType.INT);
            put("long", Ipc.FundType.LONG);
            put("float", Ipc.FundType.FLOAT);
            put("double", Ipc.FundType.DOUBLE);
            put("String", Ipc.FundType.STRING);
        }
    };

    /* JADX INFO: renamed from: a */
    public static Ipc.FundType m1109a(String str) {
        if (C0819p.m1113a(str)) {
            return Ipc.FundType.VOID;
        }
        return f1429a.get(str);
    }
}
