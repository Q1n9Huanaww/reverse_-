package com.p024ds.daisi.fragment;

import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class LicenseFragment extends BaseFragement {
    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_license;
    }

    public static LicenseFragment newInstance() {
        return new LicenseFragment();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(LicenseFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(LicenseFragment.class.getCanonicalName());
    }
}
