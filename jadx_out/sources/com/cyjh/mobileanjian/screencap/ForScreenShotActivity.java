package com.cyjh.mobileanjian.screencap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ForScreenShotActivity extends Activity {

    /* JADX INFO: renamed from: a */
    public static final int f1499a = 32896;

    /* JADX INFO: renamed from: b */
    private static final String f1500b = "ForScreenShotActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0.0f);
        m1150a();
    }

    /* JADX INFO: renamed from: a */
    private void m1150a() {
        if (Build.VERSION.SDK_INT >= 21) {
            startActivityForResult(((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent(), f1499a);
        } else {
            Log.e(f1500b, "The API version is too low,required is >= 21.");
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 32896) {
            return;
        }
        if (i2 == -1 && intent != null) {
            setResult(-1);
            ScreenShoterV3.getInstance().init(this, intent);
            finish();
        } else {
            m1150a();
            setResult(0);
        }
    }
}
