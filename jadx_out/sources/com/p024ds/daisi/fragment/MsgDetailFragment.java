package com.p024ds.daisi.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.share.bean.response.NoticeBean;
import com.cyjh.share.mvp.presenter.NoticeDetailsPresenter;
import com.cyjh.share.mvp.view.NoticeDetailsView;
import com.cyjh.share.util.AppUtils;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class MsgDetailFragment extends BaseFragement implements NoticeDetailsView {
    private LinearLayout mLLContainer;
    private NoticeDetailsPresenter mNoticeDetailsPresenter;
    private TextView mTvMsgTime;
    private TextView mTvMsgTitle;
    private WebView mWebViewMsgContent;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_msg_detail;
    }

    @Override // com.cyjh.share.mvp.view.NoticeDetailsView
    public void onFail() {
    }

    public static MsgDetailFragment newInstance(long j) {
        MsgDetailFragment msgDetailFragment = new MsgDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(MsgDetailFragment.class.getCanonicalName(), j);
        msgDetailFragment.setArguments(bundle);
        return msgDetailFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getArguments() != null) {
            long j = getArguments().getLong(MsgDetailFragment.class.getCanonicalName());
            if (j != 0) {
                this.mNoticeDetailsPresenter = new NoticeDetailsPresenter(this);
                this.mNoticeDetailsPresenter.loadAd(getActivity(), j);
            }
        }
    }

    private void initView(View view) {
        this.mTvMsgTitle = (TextView) view.findViewById(R.id.id_tv_msg_title);
        this.mTvMsgTime = (TextView) view.findViewById(R.id.id_tv_msg_time);
        this.mLLContainer = (LinearLayout) view.findViewById(R.id.ll_fragment_msg_detail_container);
        this.mWebViewMsgContent = new WebView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, AppDeviceUtils.getDipToPx(view.getContext(), 10.0f), 0, 0);
        this.mLLContainer.addView(this.mWebViewMsgContent, layoutParams);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(MsgDetailFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(MsgDetailFragment.class.getCanonicalName());
    }

    @Override // com.cyjh.share.mvp.view.NoticeDetailsView
    public void onSuc(NoticeBean noticeBean) {
        this.mTvMsgTitle.setText(noticeBean.NoticeTitle);
        this.mTvMsgTime.setText(AppUtils.timeStamp2Date(noticeBean.NoticeTime, "yyyy-MM-dd HH:mm:ss"));
        this.mWebViewMsgContent.loadDataWithBaseURL(null, noticeBean.NoticeContent, "text/html", "utf-8", null);
        this.mWebViewMsgContent.getSettings().setJavaScriptEnabled(true);
        this.mWebViewMsgContent.setWebChromeClient(new WebChromeClient());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mNoticeDetailsPresenter != null) {
            this.mNoticeDetailsPresenter.onCancel();
        }
        if (this.mWebViewMsgContent != null) {
            this.mLLContainer.removeView(this.mWebViewMsgContent);
            this.mWebViewMsgContent.removeAllViews();
            this.mWebViewMsgContent.destroy();
            this.mWebViewMsgContent = null;
        }
    }
}
