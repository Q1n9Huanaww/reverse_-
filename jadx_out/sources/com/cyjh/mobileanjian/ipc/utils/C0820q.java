package com.cyjh.mobileanjian.ipc.utils;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.q */
/* JADX INFO: compiled from: TypeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0820q {

    /* JADX INFO: renamed from: a */
    public static final HashMap<String, Class> f1450a = new HashMap<String, Class>() { // from class: com.cyjh.mobileanjian.ipc.utils.q.1
        {
            put("boolean", Boolean.TYPE);
            put("int", Integer.TYPE);
            put("long", Long.TYPE);
            put("String", String.class);
        }
    };

    /* JADX INFO: renamed from: a */
    private static Object m1115a(String str, String str2) {
        switch (C0817n.f1448j.f1426a.get(str).intValue()) {
            case 1:
                return Boolean.valueOf(Boolean.parseBoolean(str2));
            case 2:
                if (str2.length() > 0) {
                    return Character.valueOf(str2.charAt(0));
                }
                break;
            case 3:
                Byte.parseByte(str2);
                break;
            case 4:
                return Integer.valueOf(Integer.parseInt(str2));
            case 5:
                return Long.valueOf(Long.parseLong(str2));
            case 6:
                return Float.valueOf(Float.parseFloat(str2));
            case 7:
                Double.parseDouble(str2);
                break;
            case 9:
                return str2;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static Object m1114a(Class cls, String str) {
        String str2 = null;
        try {
            switch (C0817n.f1448j.f1427b.get(cls).intValue()) {
                case 1:
                    return Boolean.valueOf(Boolean.parseBoolean(str));
                case 2:
                    return Character.valueOf(str.length() > 0 ? str.charAt(0) : ' ');
                case 3:
                    Byte.parseByte(str);
                    break;
                case 4:
                    return Integer.valueOf(Integer.parseInt(str));
                case 5:
                    return Long.valueOf(Long.parseLong(str));
                case 6:
                    return Float.valueOf(Float.parseFloat(str));
                case 7:
                    Double.parseDouble(str);
                    break;
                case 9:
                    if (str == null) {
                        str = "";
                    }
                    str2 = str;
                    break;
            }
        } catch (NumberFormatException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return str2;
    }

    /* JADX INFO: renamed from: a */
    private static Object[] m1118a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            Object objM1115a = m1115a(strArr[i], strArr2[i]);
            objArr[i] = objM1115a;
            if (objM1115a == null) {
                return null;
            }
        }
        return objArr;
    }

    /* JADX INFO: renamed from: a */
    private static Object[] m1117a(Class[] clsArr, String[] strArr) {
        if (clsArr == null || clsArr.length == 0) {
            return null;
        }
        Object[] objArr = new Object[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            Object objM1114a = m1114a(clsArr[i], strArr[i]);
            objArr[i] = objM1114a;
            if (objM1114a == null) {
                return null;
            }
        }
        return objArr;
    }

    /* JADX INFO: renamed from: a */
    public static Object[] m1116a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return null;
        }
        int size = list.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            Object objM1115a = m1115a(list.get(i), list2.get(i));
            objArr[i] = objM1115a;
            if (objM1115a == null) {
                return null;
            }
        }
        return objArr;
    }
}
