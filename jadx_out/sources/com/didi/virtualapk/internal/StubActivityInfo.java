package com.didi.virtualapk.internal;

import android.R;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class StubActivityInfo {
    public static final int MAX_COUNT_SINGLEINSTANCE = 8;
    public static final int MAX_COUNT_SINGLETASK = 8;
    public static final int MAX_COUNT_SINGLETOP = 8;
    public static final int MAX_COUNT_STANDARD = 1;
    public static final String STUB_ACTIVITY_SINGLEINSTANCE = "%s.D$%d";
    public static final String STUB_ACTIVITY_SINGLETASK = "%s.C$%d";
    public static final String STUB_ACTIVITY_SINGLETOP = "%s.B$%d";
    public static final String STUB_ACTIVITY_STANDARD = "%s.A$%d";
    public static final String corePackage = "com.didi.virtualapk.core";
    public final int usedStandardStubActivity = 1;
    public int usedSingleTopStubActivity = 0;
    public int usedSingleTaskStubActivity = 0;
    public int usedSingleInstanceStubActivity = 0;
    private HashMap<String, String> mCachedStubActivity = new HashMap<>();

    StubActivityInfo() {
    }

    public String getStubActivity(String str, int i, Resources.Theme theme) {
        String str2 = this.mCachedStubActivity.get(str);
        if (str2 != null) {
            return str2;
        }
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(new int[]{R.attr.windowIsTranslucent, R.attr.windowBackground});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        Log.d("VA.StubActivityInfo", "getStubActivity, is transparent theme ? " + z);
        String str3 = String.format(STUB_ACTIVITY_STANDARD, "com.didi.virtualapk.core", 1);
        switch (i) {
            case 0:
                str3 = String.format(STUB_ACTIVITY_STANDARD, "com.didi.virtualapk.core", 1);
                if (z) {
                    str3 = String.format(STUB_ACTIVITY_STANDARD, "com.didi.virtualapk.core", 2);
                }
                break;
            case 1:
                this.usedSingleTopStubActivity = (this.usedSingleTopStubActivity % 8) + 1;
                str3 = String.format(STUB_ACTIVITY_SINGLETOP, "com.didi.virtualapk.core", Integer.valueOf(this.usedSingleTopStubActivity));
                break;
            case 2:
                this.usedSingleTaskStubActivity = (this.usedSingleTaskStubActivity % 8) + 1;
                str3 = String.format(STUB_ACTIVITY_SINGLETASK, "com.didi.virtualapk.core", Integer.valueOf(this.usedSingleTaskStubActivity));
                break;
            case 3:
                this.usedSingleInstanceStubActivity = (this.usedSingleInstanceStubActivity % 8) + 1;
                str3 = String.format(STUB_ACTIVITY_SINGLEINSTANCE, "com.didi.virtualapk.core", Integer.valueOf(this.usedSingleInstanceStubActivity));
                break;
        }
        this.mCachedStubActivity.put(str, str3);
        return str3;
    }
}
