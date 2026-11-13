package com.p024ds.daisi.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.cyjh.share.bean.response.PhoneConfig;
import com.p024ds.daisi.activity.BindRegisterCodeActivity;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.activity.SweepCodeActivity;
import com.p024ds.daisi.activity.UnbindRegistrationCodeActivity;
import com.p024ds.daisi.activity.guide.RecognitionGuideActivity;
import com.p024ds.daisi.activity.news.FengLingAdWebViewActivity;
import com.p024ds.daisi.activity.news.FullScreenTwoAdActivity;
import com.p024ds.daisi.activity.news.H5LinkJumpPageActivity;

/* JADX INFO: loaded from: classes.dex */
public class IntentUtils {
    public static void toCallRegisterCodeActivity(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) BindRegisterCodeActivity.class);
        intent.putExtra(BindRegisterCodeActivity.class.getCanonicalName(), str);
        context.startActivity(intent);
    }

    public static void toCallElfinPayActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) ElfinPayActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static void toCallFullscreenTwoAdActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) FullScreenTwoAdActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static void toCallFengLingAdWebViewActivity(Context context, String str, int i) {
        Intent intent = new Intent(context, (Class<?>) FengLingAdWebViewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("web_view_url", str);
        intent.putExtra("type", i);
        context.startActivity(intent);
    }

    public static void toCallWebH5LinkPageActivity(Context context, String str, String str2) {
        Intent intent = new Intent(context, (Class<?>) H5LinkJumpPageActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(H5LinkJumpPageActivity.class.getCanonicalName(), str);
        intent.putExtra(H5LinkJumpPageActivity.class.getSimpleName(), str2);
        context.startActivity(intent);
    }

    public static void toCallBindRegCodeActivity(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) BindRegisterCodeActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(BindRegisterCodeActivity.class.getCanonicalName(), str);
        context.startActivity(intent);
    }

    public static void toUnbindRegistrationCodeActivity(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) UnbindRegistrationCodeActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(UnbindRegistrationCodeActivity.class.getCanonicalName(), str);
        context.startActivity(intent);
    }

    public static Intent toSweepCodeActivity(Context context) {
        return new Intent(context, (Class<?>) SweepCodeActivity.class);
    }

    public static void toCallRecognitionGuideActivity(Context context, PhoneConfig phoneConfig) {
        Intent intent = new Intent(context, (Class<?>) RecognitionGuideActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(RecognitionGuideActivity.PHONE_CONFIG, phoneConfig);
        context.startActivity(intent);
    }

    public static void toCallNoTitleBarWebViewActivity(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
