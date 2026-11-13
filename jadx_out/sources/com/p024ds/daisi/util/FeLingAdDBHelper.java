package com.p024ds.daisi.util;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.FeLingAdReportInfo;
import java.util.List;
import org.litepal.LitePal;

/* JADX INFO: loaded from: classes.dex */
public class FeLingAdDBHelper {
    public static final int DSTRACK_URL = 1;
    public static final int ISTRACK_URL = 2;
    private static FeLingAdDBHelper instance;

    private FeLingAdDBHelper() {
    }

    public static synchronized FeLingAdDBHelper getInstance() {
        if (instance == null) {
            instance = new FeLingAdDBHelper();
        }
        return instance;
    }

    public List<FeLingAdReportInfo> getFeLingAdReportInfoList(String str) {
        try {
            return LitePal.where("adPid =" + str).find(FeLingAdReportInfo.class);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public int deleteAllFeLingAdReportInfo(String str) {
        try {
            return LitePal.deleteAll((Class<?>) FeLingAdReportInfo.class, "adPid =?", str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }
}
