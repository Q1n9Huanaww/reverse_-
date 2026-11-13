package com.p024ds.daisi.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.cyjh.share.util.CommonUtils;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.AdEvent;
import com.p024ds.daisi.entity.FeLingAdReportInfo;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.CollectionUtils;
import com.p024ds.daisi.util.FeLingAdDBHelper;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class ApkDownloadHelper {
    private static final int INSTALL_APK_DELAY_TIME = 500;
    private static final String TAG = "ApkDownloadHelper";
    private DownloadManager downloadManager;
    String mAdPid;
    private Context mContext;
    private long mTaskId;
    private BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ds.daisi.download.ApkDownloadHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ApkDownloadHelper.this.checkDownloadStatus();
        }
    };
    private String fileName = System.currentTimeMillis() + ".apk";

    public ApkDownloadHelper(Context context, String str) {
        this.downloadManager = null;
        this.mAdPid = str;
        this.mContext = context;
        this.downloadManager = (DownloadManager) context.getSystemService("download");
    }

    public void downloadAPK(String str) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setAllowedOverRoaming(false);
        request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str)));
        request.setNotificationVisibility(0);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir("/download/", this.fileName);
        this.downloadManager = (DownloadManager) this.mContext.getSystemService("download");
        this.mTaskId = this.downloadManager.enqueue(request);
        this.mContext.registerReceiver(this.receiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(this.mTaskId);
        Cursor cursorQuery = this.downloadManager.query(query);
        if (cursorQuery.moveToFirst()) {
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
            if (i != 4) {
                if (i != 8) {
                    if (i != 16) {
                        switch (i) {
                            case 1:
                                break;
                            case 2:
                                break;
                            default:
                                return;
                        }
                        Log.i("download", ">>>正在下载");
                    }
                    Log.e("download", ">>>下载失败");
                    return;
                }
                Log.i("download", ">>>下载完成");
                this.mContext.unregisterReceiver(this.receiver);
                String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + this.fileName;
                saveReportInfo(str);
                installApkOperate(new File(str));
                EventBus.getDefault().post(new AdEvent(100));
                return;
            }
            Log.i("download", ">>>下载暂停");
            Log.i("download", ">>>下载延迟");
            Log.i("download", ">>>正在下载");
        }
    }

    private void installApkOperate(final File file) {
        new Handler().postDelayed(new Runnable(this, file) { // from class: com.ds.daisi.download.ApkDownloadHelper$$Lambda$0
            private final ApkDownloadHelper arg$1;
            private final File arg$2;

            {
                this.arg$1 = this;
                this.arg$2 = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.lambda$installApkOperate$0$ApkDownloadHelper(this.arg$2);
            }
        }, 500L);
    }

    final /* synthetic */ void lambda$installApkOperate$0$ApkDownloadHelper(File file) {
        try {
            AppDeviceUtils.installAPK(this.mContext, file);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void saveReportInfo(String str) {
        SlLog.m1334i(TAG, "saveReportInfo --> 1 apkPath=" + str);
        String localApkFileInfo = CommonUtils.getLocalApkFileInfo(this.mContext, str);
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(this.mAdPid);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            SlLog.m1334i(TAG, "saveReportInfo --> 2 ");
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                feLingAdReportInfo.setState(1);
                feLingAdReportInfo.setApkPageName(localApkFileInfo);
                feLingAdReportInfo.save();
            }
        }
    }
}
