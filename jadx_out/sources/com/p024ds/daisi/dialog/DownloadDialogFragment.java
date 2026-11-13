package com.p024ds.daisi.dialog;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.cyjh.share.view.SubmitProcessButton;
import com.lidroid.xutils.http.HttpHandler;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.warnship.autoplay.R;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class DownloadDialogFragment extends DialogFragment {
    private HttpHandler<File> downloadHandler;
    private SubmitProcessButton mButtonDownload;
    private View.OnClickListener mDownloadOnClickListener = new View.OnClickListener() { // from class: com.ds.daisi.dialog.DownloadDialogFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DownloadDialogFragment.this.mOnDownloadClickListener.downloadOnClick(DownloadDialogFragment.this.mButtonDownload);
        }
    };
    private OnDownloadClickListener mOnDownloadClickListener;

    public interface OnDownloadClickListener {
        void downloadOnClick(SubmitProcessButton submitProcessButton);
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().requestWindowFeature(1);
        return layoutInflater.inflate(R.layout.download_fragment_dialog, viewGroup);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mButtonDownload = (SubmitProcessButton) getView().findViewById(R.id.button_download);
        if (AppDeviceUtils.isAInstallPackage(getActivity(), "com.cyjh.gundam")) {
            this.mButtonDownload.setText("启动");
        } else if (this.downloadHandler != null && this.downloadHandler.getState() == HttpHandler.State.SUCCESS) {
            this.mButtonDownload.setText("安装");
        }
        this.mButtonDownload.setOnClickListener(this.mDownloadOnClickListener);
    }

    protected void setDialogSize() {
        Point resolution = AppDeviceUtils.getResolution(getActivity());
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = (int) (resolution.x * 0.8f);
        attributes.height = (int) (resolution.y * 0.5f);
        getDialog().getWindow().setAttributes(attributes);
    }

    public void setOnDownloadClickListener(OnDownloadClickListener onDownloadClickListener) {
        this.mOnDownloadClickListener = onDownloadClickListener;
    }

    public void setHttpHandler(HttpHandler<File> httpHandler) {
        this.downloadHandler = httpHandler;
    }
}
