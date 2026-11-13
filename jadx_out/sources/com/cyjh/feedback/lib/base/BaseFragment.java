package com.cyjh.feedback.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cyjh.share.config.MyConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    private static final String LOGGER_TAG = "ELFIN";

    public abstract int getFragmentLayoutId();

    @Override // android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (MyConfig.isOutputLog()) {
            Logger.init(LOGGER_TAG).setLogLevel(LogLevel.FULL);
        } else {
            Logger.init(LOGGER_TAG).setLogLevel(LogLevel.NONE);
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(getFragmentLayoutId(), viewGroup, false);
    }
}
