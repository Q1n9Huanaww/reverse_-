package com.p024ds.daisi.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cyjh.share.event.RTDFileUIEvent;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SlToast;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.File;
import java.io.IOException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class DescriptionFragment extends BaseFragement {
    public static final String TAG = "DescriptionFragment";
    private ScrollView scrollView;
    private TextView tvDescription;
    private WebView webView;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_scriptdescription;
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        setUserVisibleHint(true);
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        View view = getView();
        this.scrollView = (ScrollView) view.findViewById(R.id.scrollview_scriptdescription);
        this.webView = (WebView) view.findViewById(R.id.webview_scriptdescription);
        this.tvDescription = (TextView) view.findViewById(R.id.tv_description);
        this.webView.getSettings().setJavaScriptEnabled(true);
        try {
            this.appContext.getAssets().open(APPConstant.SCRIPT_FILENAME_RTD);
            this.webView.setVisibility(0);
            this.scrollView.setVisibility(8);
            webViewLoad();
        } catch (IOException unused) {
            this.scrollView.setVisibility(0);
            this.webView.setVisibility(8);
        }
    }

    private void webViewLoad() {
        String str;
        File file = new File(getContext().getFilesDir() + File.separator + Constants.SD_CARD_SCRIPT_ASSETS, APPConstant.SCRIPT_FILENAME_RTD);
        SlLog.m1334i(TAG, "webViewLoad --> sdCardRtdFile path =" + file.getAbsolutePath());
        if (AppContext.getInstance().isUseSdcardScript) {
            str = "file://" + file.getAbsolutePath();
        } else {
            str = "file:///android_asset/script.rtd";
        }
        this.commonLog.m1348e(str);
        this.webView.loadUrl(str);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveRTDFileUIEvent(RTDFileUIEvent rTDFileUIEvent) {
        SlToast.showToastLong(getActivity(), "onReceiveRTDFileUIEvent --> 更新RTDFileUI");
        SlLog.m1334i(TAG, "onReceiveRTDFileUIEvent --> 更新RTDFileUI");
        webViewLoad();
    }
}
