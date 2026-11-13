package com.iflytek.voiceads;

import android.content.Context;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.config.SDKLogger;
import com.iflytek.voiceads.dex.C1521a;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.view.AdLayout;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class IFLYInterstitialAd extends AdLayout {
    private static Class<?> cIFLYInterstitialAdImpl;
    private static IFLYInterstitialAd iFLYInterstitialAdImpl;
    private static boolean isClassLoaded;

    protected IFLYInterstitialAd(Context context) {
        super(context);
    }

    public static IFLYInterstitialAd createInterstitialAd(Context context, String str) {
        StringBuilder sb;
        String message;
        try {
            if (!isClassLoaded) {
                cIFLYInterstitialAdImpl = C1521a.m2908a(context, "com.iflytek.voiceads.IFLYInterstitialAdImpl");
                isClassLoaded = true;
            }
            iFLYInterstitialAdImpl = (IFLYInterstitialAd) cIFLYInterstitialAdImpl.getMethod("createInterstitialAd", Context.class, String.class).invoke(null, context, str);
            return iFLYInterstitialAdImpl;
        } catch (ClassNotFoundException e) {
            sb = new StringBuilder();
            sb.append("inter:");
            message = e.getMessage();
            sb.append(message);
            SDKLogger.m2901e(sb.toString());
            return new IFLYInterstitialAd(context);
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder();
            sb.append("inter:");
            message = e2.getMessage();
            sb.append(message);
            SDKLogger.m2901e(sb.toString());
            return new IFLYInterstitialAd(context);
        } catch (NoSuchMethodException e3) {
            sb = new StringBuilder();
            sb.append("inter:");
            message = e3.getMessage();
            sb.append(message);
            SDKLogger.m2901e(sb.toString());
            return new IFLYInterstitialAd(context);
        } catch (InvocationTargetException e4) {
            sb = new StringBuilder();
            sb.append("inter:");
            message = e4.getMessage();
            sb.append(message);
            SDKLogger.m2901e(sb.toString());
            return new IFLYInterstitialAd(context);
        }
    }

    public void loadAd(IFLYAdListener iFLYAdListener) {
        if (iFLYInterstitialAdImpl != null) {
            iFLYInterstitialAdImpl.loadAd(iFLYAdListener);
            return;
        }
        SDKLogger.m2899d(SDKConstants.ERROR_INFO_DEX_FILE);
        if (iFLYAdListener != null) {
            iFLYAdListener.onAdFailed(new AdError(ErrorCode.ERROR_INVALID_REQUEST));
        }
    }

    public void setParameter(String str, Object obj) {
        if (iFLYInterstitialAdImpl == null) {
            SDKLogger.m2899d(SDKConstants.ERROR_INFO_DEX_FILE);
        } else {
            iFLYInterstitialAdImpl.setParameter(str, obj);
        }
    }

    public void showAd() {
        if (iFLYInterstitialAdImpl == null) {
            SDKLogger.m2899d(SDKConstants.ERROR_INFO_DEX_FILE);
        } else {
            iFLYInterstitialAdImpl.showAd();
        }
    }
}
