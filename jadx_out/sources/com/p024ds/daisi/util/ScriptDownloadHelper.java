package com.p024ds.daisi.util;

import android.content.Context;
import android.os.AsyncTask;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.share.event.RTDFileUIEvent;
import com.cyjh.share.util.DownloadUtil;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SpUtil;
import com.cyjh.share.util.ZipUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.entity.ScriptUIEvent;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class ScriptDownloadHelper {
    public static int HOT_SCRIPT_CHECK_UPDATE_WHEN_START_RUN = 1004;
    public static int HOT_SCRIPT_DOWNLOAD_IMMEDIATELY = 1001;
    public static int HOT_SCRIPT_DOWNLOAD_IMMEDIATELY_AND_ENGINE_RUNNING = 1003;
    public static int HOT_SCRIPT_DOWNLOAD_NEXT_TIME = 1002;
    private static String TAG = "ScriptDownloadHelper";
    private static ScriptDownloadHelper instance;
    private Context mContext;
    private int mCurrentType = HOT_SCRIPT_DOWNLOAD_NEXT_TIME;
    DownloadUtil.OnDownloadListener mOnDownloadListener = new DownloadUtil.OnDownloadListener() { // from class: com.ds.daisi.util.ScriptDownloadHelper.1
        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloadSuccess(File file) {
            SlLog.m1334i(ScriptDownloadHelper.TAG, "onDownloadSuccess --> file=" + file.getName());
            ScriptDownloadHelper.this.afterDownloadSuccess(file);
        }

        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloading(long j, long j2, int i) {
            SlLog.m1334i(ScriptDownloadHelper.TAG, "onDownloading --> progress=" + i);
        }

        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloadFailed(Exception exc) {
            SlLog.m1334i(ScriptDownloadHelper.TAG, "onDownloadFailed --> exception=" + exc.getMessage());
            ScriptDownloadHelper.this.afterDownloadFailure();
        }
    };
    private String mScriptId;

    private ScriptDownloadHelper() {
    }

    public static ScriptDownloadHelper get() {
        if (instance == null) {
            synchronized (ScriptDownloadHelper.class) {
                if (instance == null) {
                    instance = new ScriptDownloadHelper();
                }
            }
        }
        return instance;
    }

    public ScriptDownloadHelper init(Context context, String str, int i) {
        this.mContext = context;
        this.mScriptId = str;
        this.mCurrentType = i;
        return this;
    }

    public void downloadScriptOperate(String str) {
        scriptDownloadTask(str);
    }

    private void scriptDownloadTask(String str) {
        String str2 = this.mContext.getPackageName() + File.separator + Constants.SD_CARD_SCRIPT_DIR;
        SlLog.m1334i(TAG, "downloadScriptOperate --> path=" + str2 + ",scriptDownloadUrl = " + str);
        new ScriptDownloadTask().execute(str, str2);
    }

    private class ScriptDownloadTask extends AsyncTask<String, Void, Void> {
        private ScriptDownloadTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                String str = strArr[0];
                File fileByPath = FileUtil.getFileByPath(ScriptDownloadHelper.this.mContext, strArr[1], str.substring(str.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR)));
                DownloadUtil.get().download(str, fileByPath.getParentFile().getAbsolutePath(), fileByPath.getName(), -1, ScriptDownloadHelper.this.mOnDownloadListener);
                return null;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterDownloadSuccess(File file) {
        SlLog.m1334i(TAG, "afterDownloadSuccess --> 1 开始解压……");
        boolean zUnzipFile = ZipUtil.unzipFile(file, FileUtil.createDataDataFileDir(this.mContext, Constants.SD_CARD_SCRIPT_ASSETS));
        SlLog.m1334i(TAG, "afterDownloadSuccess --> 2 解压是否成功：" + zUnzipFile);
        if (zUnzipFile) {
            FileUtil.deleteSingleFile(file);
            if (ScriptUtil.isValidScriptId(this.mContext)) {
                initScript();
                return;
            }
            return;
        }
        SpUtil.getInstance().setAppVersion(SpUtil.getInstance().getLastAppVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterDownloadFailure() {
        SpUtil.getInstance().setAppVersion(SpUtil.getInstance().getLastAppVersion());
    }

    private void initScript() {
        AppContext.getInstance().updateEngineScript();
        SpUtil.getInstance().setLastAppVersion(SpUtil.getInstance().getAppVersion());
        if (this.mCurrentType == HOT_SCRIPT_DOWNLOAD_IMMEDIATELY_AND_ENGINE_RUNNING && MqRunner.getInstance().isScriptStarted()) {
            MqRunner.getInstance().stop();
        }
        if (HOT_SCRIPT_CHECK_UPDATE_WHEN_START_RUN == this.mCurrentType) {
            MqRunner.getInstance().start();
        }
        EventBus.getDefault().post(new ScriptUIEvent(this.mCurrentType));
        EventBus.getDefault().post(new RTDFileUIEvent());
    }
}
