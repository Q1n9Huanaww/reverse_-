package com.cyjh.share.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class ElfinCompat {
    private static final String TAG = "ElfinCompat";

    public static void openImage(Activity activity, int i) {
        activity.startActivityForResult(getOpenImageIntent(activity), i);
    }

    public static void openImage(Fragment fragment, int i) {
        fragment.startActivityForResult(getOpenImageIntent(fragment.getActivity()), i);
    }

    private static Intent getOpenImageIntent(Context context) {
        Intent intent;
        if (VersionUtils.isTargetQ(context)) {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
        } else if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        } else {
            intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        intent.addFlags(3);
        intent.setType("image/*");
        return intent;
    }
}
