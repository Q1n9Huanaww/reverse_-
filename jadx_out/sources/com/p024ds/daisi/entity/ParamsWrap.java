package com.p024ds.daisi.entity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.cyjh.share.bean.ILFYBean;
import com.cyjh.share.bean.response.BackgroundSetAdInfo;
import com.cyjh.share.bean.response.BuyRegCodeConfigInfo;
import com.cyjh.share.bean.response.FlySettingInfo;
import com.cyjh.share.bean.response.RecommendSettingInfo;
import com.cyjh.share.bean.response.ScriptStartRunInfo;
import com.cyjh.share.util.ToastUtils;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.util.IntentUtils;
import com.warnship.autoplay.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class ParamsWrap {
    private static final int ILFY_KEY_LENGTH = 32;
    private static ParamsWrap sParamsWrap;
    public long ExpireTime;
    public int dailyTryTimes;
    public String engineToken;
    public String feedbackTips;
    public int isFreeAd;
    public List<BackgroundSetAdInfo> mAdInfos;
    public BackgroundSetAdInfo mBackgroundSetAdInfo;
    public List<BuyRegCodeConfigInfo> mBuyRegCodeConfigInfos;
    private FlySettingInfo mFlySettingInfo;
    public List<FlySettingInfo> mFlySettingInfos;
    public List<RecommendSettingInfo> mRecommendSettingInfos;
    public ScriptStartRunInfo mStartRunResponseInfo;
    public int onceTryMinute;
    public int unbindDeductHours;

    public ILFYBean displayILFYAd(int i, String str) {
        ILFYBean iLFYBean = new ILFYBean();
        if (this.mFlySettingInfos == null || this.mFlySettingInfos.size() <= 0) {
            iLFYBean.isDisplay = true;
            iLFYBean.key = str;
            return iLFYBean;
        }
        for (FlySettingInfo flySettingInfo : this.mFlySettingInfos) {
            if (i == flySettingInfo.AdType) {
                this.mFlySettingInfo = flySettingInfo;
                break;
            }
        }
        iLFYBean.isDisplay = this.mFlySettingInfo.Active == 1 && ((double) (new Random().nextInt(100) + 1)) < this.mFlySettingInfo.Probability;
        String str2 = this.mFlySettingInfo.FlyKey;
        if (TextUtils.isEmpty(str2) && str2.length() != 32) {
            str2 = str;
        }
        iLFYBean.key = str2;
        return iLFYBean;
    }

    public boolean isDisplayBackgroundSetAd(int i) {
        if (this.mAdInfos == null || this.mAdInfos.size() <= 0) {
            return false;
        }
        for (BackgroundSetAdInfo backgroundSetAdInfo : this.mAdInfos) {
            if (backgroundSetAdInfo.AdType == i) {
                this.mBackgroundSetAdInfo = backgroundSetAdInfo;
                return ((double) (new Random().nextInt(100) + 1)) < this.mBackgroundSetAdInfo.Probability;
            }
        }
        return false;
    }

    public boolean isFreeAd() {
        return this.isFreeAd == 1;
    }

    public void cleanData() {
        cleanCollect();
        sParamsWrap = null;
    }

    private ParamsWrap() {
    }

    public void crateCollect() {
        this.mBuyRegCodeConfigInfos = new ArrayList();
        this.mFlySettingInfos = new ArrayList();
        this.mRecommendSettingInfos = new ArrayList();
        this.mAdInfos = new ArrayList();
    }

    private void cleanCollect() {
        this.mFlySettingInfos = null;
        this.mAdInfos = null;
        this.mRecommendSettingInfos = null;
    }

    public static ParamsWrap getParamsWrap() {
        if (sParamsWrap == null) {
            synchronized (ParamsWrap.class) {
                if (sParamsWrap == null) {
                    sParamsWrap = new ParamsWrap();
                }
            }
        }
        return sParamsWrap;
    }

    public void onClickBackgroundAdOpera(Context context) {
        String str = getParamsWrap().mBackgroundSetAdInfo.LinkUrl;
        int i = getParamsWrap().mBackgroundSetAdInfo.OpenMethod;
        String str2 = getParamsWrap().mBackgroundSetAdInfo.Title;
        if (i == 3) {
            if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                context.startActivity(intent);
                return;
            }
            ToastUtils.showToastLong(AppContext.getInstance(), AppContext.getInstance().getResources().getString(R.string.link_url_error));
            return;
        }
        IntentUtils.toCallWebH5LinkPageActivity(context, str2, str);
    }
}
