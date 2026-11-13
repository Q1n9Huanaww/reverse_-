package com.p024ds.daisi.util;

import android.content.Context;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.share.util.ErrorUtil;

/* JADX INFO: loaded from: classes.dex */
public class ErrorUtilHelper {
    public static boolean debugMode;
    private static ErrorUtilHelper instance;

    private ErrorUtilHelper() {
    }

    public static synchronized ErrorUtilHelper getInstance() {
        if (instance == null) {
            instance = new ErrorUtilHelper();
        }
        return instance;
    }

    public void writeProcessedStringToFile(final Context context, final String str) {
        if (debugMode) {
            ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.util.ErrorUtilHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ErrorUtil.isFileMoreThan1M(ErrorUtil.getErrorFile(context))) {
                        ErrorUtil.writeProcessedStringToFile(ErrorUtil.getErrorFile(context), "", null, false);
                    }
                    ErrorUtil.writeProcessedStringToFile(ErrorUtil.getErrorFile(context), str, null, true);
                }
            });
        }
    }
}
