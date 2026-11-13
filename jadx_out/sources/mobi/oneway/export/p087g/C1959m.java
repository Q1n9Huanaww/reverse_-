package mobi.oneway.export.p087g;

import android.util.Log;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: mobi.oneway.export.g.m */
/* JADX INFO: loaded from: classes.dex */
public class C1959m {

    /* JADX INFO: renamed from: a */
    private static final String f5026a = "OnewaySdk";

    /* JADX INFO: renamed from: b */
    private static boolean f5027b;

    /* JADX INFO: renamed from: c */
    private static boolean f5028c;

    /* JADX INFO: renamed from: mobi.oneway.export.g.m$a */
    private static class a extends Exception {
        a(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public String toString() {
            String string;
            String str;
            StackTraceElement[] stackTrace = getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 4 && i < stackTrace.length; i++) {
                StackTraceElement stackTraceElement = stackTrace[i];
                boolean zIsNativeMethod = stackTraceElement.isNativeMethod();
                String strM5075a = C1965s.m5075a(stackTraceElement.getClassName());
                int lineNumber = stackTraceElement.getLineNumber();
                String fileName = stackTraceElement.getFileName();
                String methodName = stackTraceElement.getMethodName();
                if (i == 1) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(strM5075a);
                    sb2.append(".");
                    sb2.append(methodName);
                    if (zIsNativeMethod) {
                        str = "(Native)";
                    } else if (fileName != null) {
                        str = "(" + fileName + ":" + lineNumber + ")";
                    } else {
                        str = "(UnSrc)";
                    }
                    sb2.append(str);
                    string = sb2.toString();
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(strM5075a);
                    sb3.append(".");
                    sb3.append(methodName);
                    sb3.append(zIsNativeMethod ? "(Native)" : "(" + lineNumber + ")");
                    string = sb3.toString();
                }
                sb.append("[");
                sb.append(string);
                sb.append("]");
                if (i < 3) {
                    sb.append("->");
                }
            }
            return ((Object) sb) + IOUtils.LINE_SEPARATOR_WINDOWS + getMessage();
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m5043a(Object obj) {
        if (f5028c) {
            Log.v("OnewaySdk-", obj + "");
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m5044a(String str) {
        if (f5027b) {
            Log.d(f5026a, new a(str).toString());
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m5045a(String str, Throwable th) {
        Log.e(f5026a, str, th);
    }

    /* JADX INFO: renamed from: a */
    public static void m5046a(boolean z) {
        f5027b = z;
    }

    /* JADX INFO: renamed from: a */
    public static void m5047a(Object... objArr) {
        if (f5027b) {
            Log.v(f5026a, new a(C1965s.m5081a(objArr)).toString());
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5048a() {
        return f5027b;
    }

    /* JADX INFO: renamed from: b */
    public static void m5049b(String str) {
        if (f5027b) {
            Log.i(f5026a, new a(str).toString());
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m5050b(boolean z) {
        f5028c = z;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m5051b() {
        return f5028c;
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /* JADX INFO: renamed from: c */
    public static void m5052c(String str) {
        String str2;
        if (f5027b) {
            str2 = f5026a;
            str = new a(str).toString();
        } else {
            str2 = f5026a;
        }
        Log.w(str2, str);
    }

    /* JADX INFO: renamed from: d */
    public static void m5053d(String str) {
        Log.e(f5026a, new a(str).toString());
    }
}
