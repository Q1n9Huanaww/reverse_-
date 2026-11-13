package com.cyjh.mobileanjian.rpc;

import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.utils.C0804a;
import com.cyjh.mobileanjian.ipc.utils.C0815l;
import com.cyjh.mobileanjian.ipc.utils.C0819p;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Rpc {
    private static int checkArgs(String[] strArr, String[] strArr2) {
        if (C0804a.m1062a(strArr) && C0804a.m1062a(strArr2)) {
            return 1;
        }
        return (C0804a.m1062a(strArr) || C0804a.m1062a(strArr2) || C0804a.m1063b(strArr) || C0804a.m1063b(strArr2) || strArr.length != strArr2.length) ? -1 : 0;
    }

    private static List<String> arrayToList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean SimpleCallStringParam(boolean z, String str, String str2, String str3, String str4) {
        return z ? SynCall(str, str2, str3, new String[]{"String"}, new String[]{str4}) : AsynCall(str, str2, str3, new String[]{"String"}, new String[]{str4});
    }

    public static boolean SimpleCallStringStringParam(boolean z, String str, String str2, String str3, String str4, String str5) {
        return z ? SynCall(str, str2, str3, new String[]{"String", "String"}, new String[]{str4, str5}) : AsynCall(str, str2, str3, new String[]{"String", "String"}, new String[]{str4, str5});
    }

    public static String SimpleCallReturnString(String str, String str2, String str3) {
        return (String) SynCall(str, str2, str3, null, null, "String");
    }

    public static String SimpleCallStringStringParamReturnString(String str, String str2, String str3, String str4, String str5) {
        String[] strArr;
        String[] strArr2;
        if (str4 == null) {
            return "Use SimpleCallReturnString instead.";
        }
        if (str5 == null) {
            strArr2 = new String[]{String.class.getSimpleName()};
            strArr = new String[]{str4};
        } else {
            strArr = new String[]{str4, str5};
            strArr2 = new String[]{String.class.getSimpleName(), String.class.getSimpleName()};
        }
        return (String) SynCall(str, str2, str3, strArr2, strArr, "String");
    }

    public static int SimpleCallReturnInt(String str, String str2, String str3) {
        return ((Integer) SynCall(str, str2, str3, null, null, "int")).intValue();
    }

    public static boolean AsynCall(String str, String str2, String str3, String[] strArr, String[] strArr2) {
        if (C0819p.m1113a(str) || C0819p.m1113a(str2) || C0819p.m1113a(str3) || checkArgs(strArr, strArr2) == -1) {
            return false;
        }
        new StringBuilder("retObj = ").append(Invocator.invoke(str, str2, str3, arrayToList(strArr), arrayToList(strArr2), 0));
        StringBuilder sb = new StringBuilder("已经调用了AsynCall ");
        sb.append(str);
        sb.append(".");
        sb.append(str3);
        return true;
    }

    public static boolean SynCall(String str, String str2, String str3, String[] strArr, String[] strArr2) {
        SynCall(str, str2, str3, strArr, strArr2, "void");
        return true;
    }

    public static Object SynCall(String str, String str2, String str3, String[] strArr, String[] strArr2, String str4) {
        List<String> list;
        List<String> listArrayToList;
        if (C0819p.m1113a(str) || C0819p.m1113a(str2) || C0819p.m1113a(str3)) {
            return false;
        }
        int iCheckArgs = checkArgs(strArr, strArr2);
        if (iCheckArgs != -1 && C0815l.m1109a(str4) != null) {
            if (iCheckArgs == 0) {
                List<String> listArrayToList2 = arrayToList(strArr);
                listArrayToList = arrayToList(strArr2);
                list = listArrayToList2;
            } else {
                list = null;
                listArrayToList = null;
            }
            Object objInvoke = Invocator.invoke(str, str2, str3, list, listArrayToList, 0);
            new StringBuilder("retObj = ").append(objInvoke);
            try {
                switch (C0815l.m1109a(str4)) {
                    case BOOLEAN:
                        return (Boolean) objInvoke;
                    case INT:
                        return (Integer) objInvoke;
                    case LONG:
                        return (Long) objInvoke;
                    case FLOAT:
                        return (Float) objInvoke;
                    case DOUBLE:
                        return (Double) objInvoke;
                    case STRING:
                        return objInvoke == null ? "" : (String) objInvoke;
                }
            } catch (Throwable th) {
                ThrowableExtension.printStackTrace(th);
            }
            return true;
        }
        return false;
    }
}
