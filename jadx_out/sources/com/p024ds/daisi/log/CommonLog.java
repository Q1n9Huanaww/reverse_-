package com.p024ds.daisi.log;

import android.util.Log;
import com.p024ds.daisi.net.URL.MyBuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class CommonLog {
    private String tag = "ELFIN";
    private boolean isDebug = MyBuildConfig.isOutputLog();

    private String getFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getId() + ": " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    private void verbose(Object obj) {
        Log.v(this.tag, getMeg(obj));
    }

    private void debug(Object obj) {
        Log.d(this.tag, getMeg(obj));
    }

    private void info(Object obj) {
        Log.i(this.tag, getMeg(obj));
    }

    private void warn(Object obj) {
        Log.w(this.tag, getMeg(obj));
    }

    private void error(Object obj) {
        Log.e(this.tag, getMeg(obj));
    }

    private String getMeg(Object obj) {
        String functionName = getFunctionName();
        if (functionName == null) {
            return obj.toString();
        }
        return functionName + " >>>>" + obj;
    }

    /* JADX INFO: renamed from: v */
    public void m1352v(Object obj) {
        if (this.isDebug) {
            verbose(obj);
        }
    }

    /* JADX INFO: renamed from: v */
    public void m1353v(String str, Object obj) {
        this.tag = str;
        if (this.isDebug) {
            verbose(obj);
        }
    }

    /* JADX INFO: renamed from: d */
    public void m1346d(Object obj) {
        if (this.isDebug) {
            debug(obj);
        }
    }

    /* JADX INFO: renamed from: d */
    public void m1347d(String str, Object obj) {
        this.tag = str;
        if (this.isDebug) {
            debug(obj);
        }
    }

    /* JADX INFO: renamed from: i */
    public void m1350i(Object obj) {
        if (this.isDebug) {
            info(obj);
        }
    }

    /* JADX INFO: renamed from: i */
    public void m1351i(String str, Object obj) {
        this.tag = str;
        if (this.isDebug) {
            info(obj);
        }
    }

    /* JADX INFO: renamed from: w */
    public void m1354w(Object obj) {
        if (this.isDebug) {
            warn(obj);
        }
    }

    /* JADX INFO: renamed from: w */
    public void m1355w(String str, Object obj) {
        this.tag = str;
        if (this.isDebug) {
            warn(obj);
        }
    }

    /* JADX INFO: renamed from: e */
    public void m1348e(Object obj) {
        if (this.isDebug) {
            error(obj);
        }
    }

    /* JADX INFO: renamed from: e */
    public void m1349e(String str, Object obj) {
        this.tag = str;
        if (this.isDebug) {
            error(obj);
        }
    }
}
