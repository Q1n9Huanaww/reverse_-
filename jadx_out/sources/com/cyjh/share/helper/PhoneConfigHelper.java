package com.cyjh.share.helper;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.share.bean.response.PhoneConfig;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.SlLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneConfigHelper {
    public static int AND_MODE = 1;
    public static int DISABLE_STATUS = 0;
    public static int ENABLE_STATUS = 1;
    public static int OR_MODE = 2;
    private static String TAG = "PhoneConfigHelper";
    private static PhoneConfigHelper instance;
    private int mCloudPhoneType;

    private PhoneConfigHelper() {
    }

    public static PhoneConfigHelper get() {
        if (instance == null) {
            synchronized (PhoneConfigHelper.class) {
                if (instance == null) {
                    instance = new PhoneConfigHelper();
                }
            }
        }
        return instance;
    }

    public PhoneConfig init(Context context, List<PhoneConfig> list) {
        for (PhoneConfig phoneConfig : list) {
            boolean recognitionResult = getRecognitionResult(context, phoneConfig.getJudgeMode(), getPackageArr(phoneConfig.getPackage()));
            SlLog.m1334i(TAG, "init --> recognitionResult=" + recognitionResult);
            if (recognitionResult) {
                this.mCloudPhoneType = phoneConfig.getCloudPhoneType();
                if (phoneConfig.getActive() == DISABLE_STATUS) {
                    return phoneConfig;
                }
            }
        }
        return null;
    }

    public int getCloudPhoneType() {
        return this.mCloudPhoneType;
    }

    private String[] getPackageArr(String str) {
        SlLog.m1334i(TAG, "getPackageArr --> packageNames=" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains(",")) {
            return str.split(",");
        }
        return new String[]{str};
    }

    private boolean getRecognitionResult(Context context, int i, String[] strArr) {
        boolean z;
        SlLog.m1334i(TAG, "getRecognitionResult --> mode=" + i);
        if (strArr != null) {
            boolean z2 = true;
            boolean z3 = false;
            z = false;
            for (String str : strArr) {
                boolean zIsPackageInstalled = AppUtils.isPackageInstalled(context, str);
                SlLog.m1334i(TAG, "getRecognitionResult --> packageName=" + str + ",isInstall=" + zIsPackageInstalled);
                if (i == AND_MODE) {
                    z2 &= zIsPackageInstalled;
                    z = z2;
                } else if (i == OR_MODE) {
                    z3 |= zIsPackageInstalled;
                    z = z3;
                }
                if (!z2 || z3) {
                    break;
                }
            }
        } else {
            z = false;
        }
        SlLog.m1334i(TAG, "getRecognitionResult --> result=" + z);
        return z;
    }
}
