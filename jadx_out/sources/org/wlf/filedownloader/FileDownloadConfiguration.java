package org.wlf.filedownloader;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;
import org.wlf.filedownloader.base.Log;

/* JADX INFO: loaded from: classes2.dex */
public class FileDownloadConfiguration {
    private static final String TAG = FileDownloadConfiguration.class.getSimpleName();
    private Builder mBuilder;
    private ExecutorService mFileDetectEngine;
    private ExecutorService mFileDownloadEngine;
    private ExecutorService mFileOperationEngine;

    public static class Builder extends BaseDownloadConfigBuilder {
        public static final int DEFAULT_DOWNLOAD_TASK_SIZE = 2;
        public static final int MAX_DOWNLOAD_TASK_SIZE = 10;
        private Context mContext;
        private int mDownloadTaskSize;
        private String mFileDownloadDir;
        private boolean mIsDebugMode = false;

        public Builder(Context context) {
            this.mContext = context.getApplicationContext();
            try {
                this.mFileDownloadDir = this.mContext.getExternalFilesDir(null).getAbsolutePath() + File.separator + "file_downloader";
            } catch (Exception e) {
                e.printStackTrace();
                this.mFileDownloadDir = this.mContext.getFilesDir().getAbsolutePath() + File.separator + "file_downloader";
            }
            this.mDownloadTaskSize = 2;
            Log.setDebugMode(this.mIsDebugMode);
        }

        public Builder configFileDownloadDir(String fileDownloadDir) {
            if (!TextUtils.isEmpty(fileDownloadDir)) {
                File file = new File(fileDownloadDir);
                if (!file.exists()) {
                    Log.m5160i(FileDownloadConfiguration.TAG, "configFileDownloadDir 要设置的文件下载保存目录：" + fileDownloadDir + " 还不存在，需要创建！");
                    boolean isCreateSuccess = file.mkdirs();
                    if (isCreateSuccess) {
                        Log.m5160i(FileDownloadConfiguration.TAG, "configFileDownloadDir 要设置的文件下载保存目录：" + fileDownloadDir + " 创建成功！");
                    } else {
                        Log.m5160i(FileDownloadConfiguration.TAG, "configFileDownloadDir 要设置的文件下载保存目录：" + fileDownloadDir + " 创建失败！");
                    }
                } else {
                    Log.m5160i(FileDownloadConfiguration.TAG, "configFileDownloadDir 要设置的文件下载保存目录：" + fileDownloadDir + " 已存在，不需要创建！");
                }
                this.mFileDownloadDir = fileDownloadDir;
            }
            return this;
        }

        public Builder configDownloadTaskSize(int downloadTaskSize) {
            if (downloadTaskSize >= 1 && downloadTaskSize <= 10) {
                this.mDownloadTaskSize = downloadTaskSize;
            } else if (downloadTaskSize > 10) {
                this.mDownloadTaskSize = 10;
            } else if (downloadTaskSize >= 1) {
                Log.m5160i(FileDownloadConfiguration.TAG, "configDownloadTaskSize 配置同时下载任务的数量失败，downloadTaskSize：" + downloadTaskSize);
            } else {
                this.mDownloadTaskSize = 1;
            }
            return this;
        }

        public Builder configDebugMode(boolean isDebugMode) {
            this.mIsDebugMode = isDebugMode;
            Log.setDebugMode(this.mIsDebugMode);
            return this;
        }

        @Override // org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public Builder configRetryDownloadTimes(int retryDownloadTimes) {
            super.configRetryDownloadTimes(retryDownloadTimes);
            return this;
        }

        @Override // org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public Builder configConnectTimeout(int connectTimeout) {
            super.configConnectTimeout(connectTimeout);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getRetryDownloadTimes() {
            return this.mRetryDownloadTimes;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getConnectTimeout() {
            return this.mConnectTimeout;
        }

        public FileDownloadConfiguration build() {
            return new FileDownloadConfiguration(this);
        }
    }

    public static FileDownloadConfiguration createDefault(Context context) {
        return new Builder(context).build();
    }

    private FileDownloadConfiguration(Builder builder) {
        if (builder == null) {
            throw new NullPointerException("builder can not be empty!");
        }
        this.mBuilder = builder;
        this.mFileDownloadEngine = Executors.newFixedThreadPool(builder.mDownloadTaskSize);
        this.mFileDetectEngine = Executors.newCachedThreadPool();
        this.mFileOperationEngine = Executors.newCachedThreadPool();
    }

    public Context getContext() {
        return this.mBuilder.mContext;
    }

    public String getFileDownloadDir() {
        return this.mBuilder.mFileDownloadDir;
    }

    public boolean isDebugMode() {
        return this.mBuilder.mIsDebugMode;
    }

    public int getRetryDownloadTimes() {
        return this.mBuilder.getRetryDownloadTimes();
    }

    public int getConnectTimeout() {
        return this.mBuilder.getConnectTimeout();
    }

    public ExecutorService getFileDownloadEngine() {
        return this.mFileDownloadEngine;
    }

    public ExecutorService getFileDetectEngine() {
        return this.mFileDetectEngine;
    }

    public ExecutorService getFileOperationEngine() {
        return this.mFileOperationEngine;
    }
}
