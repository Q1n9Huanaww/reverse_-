package com.cyjh.share.helper;

import com.cyjh.share.util.ShellUtils;

/* JADX INFO: loaded from: classes.dex */
public class DdyEnvironmentHelper {
    private static String TAG = "DdyEnvironmentHelper";
    private static DdyEnvironmentHelper instance;

    public interface OnDetectedListener {
        void onDetected(boolean z);
    }

    private DdyEnvironmentHelper() {
    }

    public static DdyEnvironmentHelper get() {
        if (instance == null) {
            synchronized (DdyEnvironmentHelper.class) {
                if (instance == null) {
                    instance = new DdyEnvironmentHelper();
                }
            }
        }
        return instance;
    }

    public boolean isDdy() {
        ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand("getprop phone.id", false, 100);
        return commandResultExecCommand.successMsg != null && commandResultExecCommand.successMsg.length() > 0;
    }
}
