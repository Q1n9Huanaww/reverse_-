package com.p024ds.daisi.dialog;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.util.DownloadUtil;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.receiver.NetStateReceiver;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.warnship.autoplay.R;
import java.io.File;
import java.math.BigInteger;
import org.apache.commons.p092io.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class UpdateDialog extends BaseDialog implements View.OnClickListener {
    private static final int ANDROID_SDK19 = 19;
    private static final int ANDROID_SDK24 = 24;
    private static final int INSTALL_APK_DELAY_TIME = 500;
    private static final int MSG_DOWNLOAD_FAILURE = 1002;
    private static final int MSG_DOWNLOAD_SUCCESS = 1000;
    private static final int MSG_UPDATE_PROCESS = 1001;
    private static String TAG = "UpdateDialog";
    private String downloadSize;
    private String fileSize;
    private boolean isCancel;
    private boolean isDismiss;
    private Button mButtonCancel;
    private Button mButtonForceUpdate;
    private boolean mCanSubmit;
    private Context mContext;
    private Handler mHandler;
    private boolean mIsBeginDownload;
    private boolean mIsDisConnect;
    private LinearLayout mLinearLayoutDownload;
    private LinearLayout mLinearLayoutUpdate;
    protected NetStateReceiver.NetChangeObserver mNetChangeObserver;
    DownloadUtil.OnDownloadListener mOnDownloadListener;
    private ProgressBar mProgressBarDownload;
    private TextView mTextViewProgress;
    private VersionUpdateInfo updateInfo;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    final /* synthetic */ boolean lambda$new$0$UpdateDialog(Message message) {
        switch (message.what) {
            case 1000:
                afterDownloadSuccess((File) message.obj);
                return false;
            case 1001:
                int iIntValue = ((Integer) message.obj).intValue();
                this.mTextViewProgress.setText(String.format(getContext().getString(R.string.download_ratio_total), this.downloadSize, this.fileSize));
                this.mProgressBarDownload.setProgress(iIntValue);
                return false;
            case 1002:
                this.mCanSubmit = true;
                changeButtonsState();
                return false;
            default:
                return false;
        }
    }

    public UpdateDialog(Context context, VersionUpdateInfo versionUpdateInfo, boolean z) {
        super(context, R.style.Theme_Dialog);
        this.mCanSubmit = true;
        this.mHandler = new Handler(new Handler.Callback(this) { // from class: com.ds.daisi.dialog.UpdateDialog$$Lambda$0
            private final UpdateDialog arg$1;

            {
                this.arg$1 = this;
            }

            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return this.arg$1.lambda$new$0$UpdateDialog(message);
            }
        });
        this.mOnDownloadListener = new DownloadUtil.OnDownloadListener() { // from class: com.ds.daisi.dialog.UpdateDialog.2
            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess(File file) {
                SlLog.m1334i(UpdateDialog.TAG, "onDownloadSuccess --> file=" + file.getName());
                Message messageObtain = Message.obtain();
                messageObtain.obj = file;
                messageObtain.what = 1000;
                UpdateDialog.this.mHandler.sendMessage(messageObtain);
            }

            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloading(long j, long j2, int i) {
                SlLog.m1334i(UpdateDialog.TAG, "onDownloading --> progress=" + i);
                UpdateDialog.this.downloadSize = FileUtils.byteCountToDisplaySize(BigInteger.valueOf(j2));
                UpdateDialog.this.fileSize = FileUtils.byteCountToDisplaySize(BigInteger.valueOf(j));
                Message messageObtain = Message.obtain();
                messageObtain.obj = Integer.valueOf(i);
                messageObtain.what = 1001;
                UpdateDialog.this.mHandler.sendMessage(messageObtain);
            }

            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloadFailed(Exception exc) {
                SlLog.m1334i(UpdateDialog.TAG, "onDownloadFailed --> exception=" + exc.getMessage());
                UpdateDialog.this.mHandler.sendEmptyMessage(1002);
            }
        };
        this.isDismiss = z;
        this.mContext = context;
        if (z) {
            if (Build.VERSION.SDK_INT >= 26) {
                getWindow().setType(2038);
            } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
                if (getWindow() != null) {
                    getWindow().setType(2005);
                }
            } else if (getWindow() != null) {
                getWindow().setType(2002);
            }
        }
        this.updateInfo = versionUpdateInfo;
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.update_dialog);
        setDialogSize(0.9f, 0.6f);
        initView();
        initData();
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.update_dialog_textview_size);
        TextView textView2 = (TextView) findViewById(R.id.update_dialog_textview_updatecontent);
        this.mTextViewProgress = (TextView) findViewById(R.id.update_dialog_textview_downloadprogress);
        this.mButtonForceUpdate = (Button) findViewById(R.id.update_dialog_button_forceupdate);
        Button button = (Button) findViewById(R.id.update_dialog_button_updatenow);
        Button button2 = (Button) findViewById(R.id.update_dialog_button_nexttime);
        this.mButtonCancel = (Button) findViewById(R.id.update_dialog_button_cancel);
        this.mProgressBarDownload = (ProgressBar) findViewById(R.id.update_dialog_progressBar);
        this.mLinearLayoutUpdate = (LinearLayout) findViewById(R.id.update_dialog_linearlayout_update);
        this.mLinearLayoutDownload = (LinearLayout) findViewById(R.id.update_dialog_linearlayout_download);
        this.mButtonForceUpdate.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        this.mButtonCancel.setOnClickListener(this);
        changeButtonsState();
        textView.setText(com.p024ds.daisi.util.FileUtils.formetFileSize(this.updateInfo.PackageSize));
        textView2.setText(this.updateInfo.UpdateContent);
    }

    private void initData() {
        this.mNetChangeObserver = new NetStateReceiver.NetChangeObserver() { // from class: com.ds.daisi.dialog.UpdateDialog.1
            @Override // com.ds.daisi.receiver.NetStateReceiver.NetChangeObserver
            public void onNetConnected(int i) {
                SlLog.m1334i(UpdateDialog.TAG, "onNetConnected --> ");
                if (UpdateDialog.this.mIsBeginDownload && UpdateDialog.this.mIsDisConnect) {
                    UpdateDialog.this.mIsDisConnect = false;
                    UpdateDialog.this.download();
                }
            }

            @Override // com.ds.daisi.receiver.NetStateReceiver.NetChangeObserver
            public void onNetDisConnect() {
                SlLog.m1334i(UpdateDialog.TAG, "onNetDisConnect --> ");
                UpdateDialog.this.mIsDisConnect = true;
                UpdateDialog.this.mCanSubmit = true;
            }
        };
        NetStateReceiver.registerNetworkStateReceiver(this.mContext);
        NetStateReceiver.registerObserver(this.mNetChangeObserver);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_dialog_button_cancel /* 2131755521 */:
                this.isCancel = true;
                NetStateReceiver.unRegisterNetworkStateReceiver(this.mContext);
                DownloadUtil.get().cancelDownload();
                dismiss();
                break;
            case R.id.update_dialog_button_forceupdate /* 2131755522 */:
            case R.id.update_dialog_button_updatenow /* 2131755525 */:
                download();
                break;
            case R.id.update_dialog_button_nexttime /* 2131755524 */:
                dismiss();
                break;
        }
    }

    private void changeButtonsState() {
        this.mLinearLayoutDownload.setVisibility(8);
        if (this.updateInfo.UpdateType == 1) {
            this.mLinearLayoutUpdate.setVisibility(8);
            this.mButtonCancel.setVisibility(8);
            this.mButtonForceUpdate.setVisibility(0);
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            return;
        }
        this.mLinearLayoutUpdate.setVisibility(0);
        this.mButtonForceUpdate.setVisibility(8);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download() {
        if (this.mCanSubmit) {
            this.mCanSubmit = false;
            setCanceledOnTouchOutside(false);
            setCancelable(false);
            this.mLinearLayoutDownload.setVisibility(0);
            this.mLinearLayoutUpdate.setVisibility(8);
            this.mButtonForceUpdate.setVisibility(8);
            new MyAsyncTask().execute(Environment.getExternalStorageDirectory() + File.separator + getContext().getPackageName() + ".apk");
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        AppContext.getInstance().isDisableVolumeKey = true;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        SlLog.m1334i(TAG, "dismiss -->");
        AppContext.getInstance().isDisableVolumeKey = false;
        NetStateReceiver.unRegisterNetworkStateReceiver(this.mContext);
    }

    private void afterDownloadSuccess(final File file) {
        if (this.isCancel) {
            return;
        }
        SlLog.m1334i(TAG, "afterDownloadSuccess --> 1 ");
        installApkOperate(file);
        this.mButtonCancel.setText("安装");
        this.mButtonCancel.setOnClickListener(new View.OnClickListener(this, file) { // from class: com.ds.daisi.dialog.UpdateDialog$$Lambda$1
            private final UpdateDialog arg$1;
            private final File arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = file;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.arg$1.lambda$afterDownloadSuccess$1$UpdateDialog(this.arg$2, view);
            }
        });
    }

    final /* synthetic */ void lambda$afterDownloadSuccess$1$UpdateDialog(File file, View view) {
        installApkOperate(file);
    }

    private void installApkOperate(final File file) {
        new Handler().postDelayed(new Runnable(this, file) { // from class: com.ds.daisi.dialog.UpdateDialog$$Lambda$2
            private final UpdateDialog arg$1;
            private final File arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.lambda$installApkOperate$2$UpdateDialog(this.arg$2);
            }
        }, 500L);
    }

    final /* synthetic */ void lambda$installApkOperate$2$UpdateDialog(File file) {
        try {
            dismiss();
            AppDeviceUtils.installAPK(this.mContext, file);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Void> {
        private MyAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                UpdateDialog.this.mIsBeginDownload = true;
                File file = new File(strArr[0]);
                DownloadUtil.get().download(UpdateDialog.this.updateInfo.UpdateUrl, file.getParentFile().getAbsolutePath(), file.getName(), UpdateDialog.this.updateInfo.PackageSize, UpdateDialog.this.mOnDownloadListener);
                return null;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }
    }
}
