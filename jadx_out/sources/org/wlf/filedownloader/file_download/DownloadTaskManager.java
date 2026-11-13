package org.wlf.filedownloader.file_download;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.wlf.filedownloader.DownloadConfiguration;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.DownloadStatusConfiguration;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.DownloadRecorder;
import org.wlf.filedownloader.file_download.base.DownloadTask;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.OnTaskRunFinishListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnDetectUrlFileListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.NetworkUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadTaskManager implements Pauseable {
    private static final String TAG = DownloadTaskManager.class.getSimpleName();
    private FileDownloadConfiguration mConfiguration;
    private DownloadRecorder mDownloadRecorder;
    private Map<String, DownloadTask> mRunningDownloadTaskMap = new ConcurrentHashMap();
    private Object mDownloadTaskLock = new Object();
    private DetectUrlFileCacher mDetectUrlFileCacher = new DetectUrlFileCacher();
    private DownloadStatusObserver mDownloadStatusObserver = new DownloadStatusObserver();

    public interface OnReleaseListener {
        void onReleased();
    }

    public DownloadTaskManager(FileDownloadConfiguration configuration, DownloadRecorder downloadRecorder) {
        this.mConfiguration = configuration;
        this.mDownloadRecorder = downloadRecorder;
    }

    public void release(final OnReleaseListener onReleaseListener) {
        final Set<String> runningUrls = this.mRunningDownloadTaskMap.keySet();
        pause(new ArrayList(runningUrls), new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.1
            private List<String> mSucceed = new ArrayList();
            private List<String> mFailed = new ArrayList();
            private boolean mIsNotify = false;

            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskSucceed(String url) {
                this.mSucceed.add(url);
                if (runningUrls.size() == this.mSucceed.size() + this.mFailed.size() && !this.mIsNotify) {
                    DownloadTaskManager.this.notifyReleased(onReleaseListener);
                    this.mIsNotify = true;
                }
            }

            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskFailed(String url, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                this.mFailed.add(url);
                if (runningUrls.size() == this.mSucceed.size() + this.mFailed.size() && !this.mIsNotify) {
                    DownloadTaskManager.this.notifyReleased(onReleaseListener);
                }
            }
        });
    }

    private DetectUrlFileInfo getDetectUrlFile(String url) {
        return this.mDetectUrlFileCacher.getDetectUrlFile(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadRecorder.getDownloadFile(url);
    }

    private DownloadTask getRunningDownloadTask(String url) {
        return getRunningDownloadTask(url, false);
    }

    private DownloadTask getRunningDownloadTask(String url, boolean includeStopped) {
        DownloadTask task;
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
            return this.mRunningDownloadTaskMap.get(url);
        }
        if (!DownloadFileUtil.isDownloadingStatus(downloadFileInfo) || (task = this.mRunningDownloadTaskMap.get(url)) == null || (!includeStopped && task.isStopped())) {
            return null;
        }
        return task;
    }

    public void registerDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener, DownloadStatusConfiguration downloadStatusConfiguration) {
        this.mDownloadStatusObserver.addOnFileDownloadStatusListener(onFileDownloadStatusListener, downloadStatusConfiguration);
    }

    public void unregisterDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        this.mDownloadStatusObserver.removeOnFileDownloadStatusListener(onFileDownloadStatusListener);
    }

    private void addAndRunDetectUrlFileTask(String url, boolean forceDetect, OnDetectBigUrlFileListener onDetectBigUrlFileListener, DownloadConfiguration downloadConfiguration) {
        OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason = null;
        if (!UrlUtil.isUrl(url)) {
            failReason = new OnDetectUrlFileListener.DetectUrlFileFailReason(url, "url illegal !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_URL_ILLEGAL);
        }
        if (failReason == null && !NetworkUtil.isNetworkAvailable(this.mConfiguration.getContext())) {
            failReason = new OnDetectUrlFileListener.DetectUrlFileFailReason(url, "network not available !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_NETWORK_DENIED);
        }
        if (failReason != null) {
            notifyDetectUrlFileFailed(url, failReason, onDetectBigUrlFileListener);
            return;
        }
        DetectUrlFileTask detectUrlFileTask = new DetectUrlFileTask(url, this.mConfiguration.getFileDownloadDir(), this.mDetectUrlFileCacher, this.mDownloadRecorder);
        detectUrlFileTask.setOnDetectBigUrlFileListener(onDetectBigUrlFileListener);
        detectUrlFileTask.setCloseConnectionEngine(this.mConfiguration.getFileOperationEngine());
        detectUrlFileTask.setConnectTimeout(this.mConfiguration.getConnectTimeout());
        if (downloadConfiguration != null) {
            detectUrlFileTask.setRequestMethod(downloadConfiguration.getRequestMethod(url));
            detectUrlFileTask.setHeaders(downloadConfiguration.getHeaders(url));
        }
        if (forceDetect) {
            detectUrlFileTask.enableForceDetect();
        }
        this.mConfiguration.getFileDetectEngine().execute(detectUrlFileTask);
    }

    private void addAndRunDownloadTask(String callerUrl, DownloadFileInfo downloadFileInfo, DownloadConfiguration downloadConfiguration) {
        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = null;
        if (!UrlUtil.isUrl(callerUrl)) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "url illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_URL_ILLEGAL);
        }
        if (failReason == null && !NetworkUtil.isNetworkAvailable(this.mConfiguration.getContext())) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "network not available !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_NETWORK_DENIED);
        }
        if (failReason == null) {
            if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
                failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "the download file does not exist or illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_DOWNLOAD_FILE_ERROR);
            }
            if (failReason == null && (TextUtils.isEmpty(callerUrl) || !callerUrl.equals(downloadFileInfo.getUrl()) || !downloadFileInfo.equals(getDownloadFile(callerUrl)))) {
                failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "the download file does not exist or illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_DOWNLOAD_FILE_ERROR);
            }
        }
        if (failReason == null && downloadFileInfo.getDownloadedSizeLong() > downloadFileInfo.getFileSizeLong()) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "download size illegal, please delete or re-download !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_DOWNLOAD_FILE_ERROR);
        }
        if (failReason != null) {
            notifyDownloadStatusFailed(callerUrl, failReason, downloadFileInfo != null);
            return;
        }
        synchronized (this.mDownloadTaskLock) {
            DownloadTask taskInMap = this.mRunningDownloadTaskMap.get(callerUrl);
            if (taskInMap != null) {
                Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
                Log.m5156d(TAG, "mRunningDownloadTaskMap，忽略1：" + callerUrl + "，old task：" + taskInMap.hashCode() + "，线程数：" + threads.size());
            } else {
                int retryDownloadTimes = this.mConfiguration.getRetryDownloadTimes();
                int connectTimeout = this.mConfiguration.getConnectTimeout();
                String requestMethod = "GET";
                Map<String, String> headers = null;
                if (downloadConfiguration != null) {
                    int localRetryDownloadTimes = downloadConfiguration.getRetryDownloadTimes(callerUrl);
                    if (localRetryDownloadTimes != 0) {
                        retryDownloadTimes = localRetryDownloadTimes;
                    }
                    int localConnectTimeout = downloadConfiguration.getConnectTimeout(callerUrl);
                    if (localConnectTimeout != 15000) {
                        connectTimeout = localConnectTimeout;
                    }
                    String localRequestMethod = downloadConfiguration.getRequestMethod(callerUrl);
                    if (TextUtils.isEmpty(localRequestMethod)) {
                        localRequestMethod = "GET";
                    }
                    if (!TextUtils.isEmpty(localRequestMethod)) {
                        requestMethod = localRequestMethod;
                    }
                    headers = downloadConfiguration.getHeaders(callerUrl);
                }
                final RetryableDownloadTaskImpl downloadTask = new RetryableDownloadTaskImpl(FileDownloadTaskParam.createByDownloadFile(downloadFileInfo, requestMethod, headers), this.mDownloadRecorder, this.mDownloadStatusObserver);
                downloadTask.setCloseConnectionEngine(this.mConfiguration.getFileOperationEngine());
                downloadTask.setRetryDownloadTimes(retryDownloadTimes);
                downloadTask.setConnectTimeout(connectTimeout);
                downloadTask.setOnTaskRunFinishListener(new OnTaskRunFinishListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.2
                    @Override // org.wlf.filedownloader.file_download.base.OnTaskRunFinishListener
                    public void onTaskRunFinish() {
                        synchronized (DownloadTaskManager.this.mDownloadTaskLock) {
                            DownloadTaskManager.this.mRunningDownloadTaskMap.remove(downloadTask.getUrl());
                            Map<Thread, StackTraceElement[]> threads2 = Thread.getAllStackTraces();
                            Log.m5158e(DownloadTaskManager.TAG, "mRunningDownloadTaskMap，--移除--：" + downloadTask.getUrl() + "，task：" + downloadTask.hashCode() + "，线程数：" + threads2.size());
                        }
                    }
                });
                synchronized (this.mDownloadTaskLock) {
                    DownloadTask taskInMap2 = this.mRunningDownloadTaskMap.get(downloadTask.getUrl());
                    if (taskInMap2 != null) {
                        Map<Thread, StackTraceElement[]> threads2 = Thread.getAllStackTraces();
                        Log.m5156d(TAG, "mRunningDownloadTaskMap，忽略2：" + downloadTask.getUrl() + "，old task：" + taskInMap2.hashCode() + "，线程数：" + threads2.size());
                    } else {
                        this.mRunningDownloadTaskMap.put(downloadTask.getUrl(), downloadTask);
                        Map<Thread, StackTraceElement[]> threads3 = Thread.getAllStackTraces();
                        Log.m5156d(TAG, "mRunningDownloadTaskMap，--增加--：" + downloadTask.getUrl() + "，task：" + downloadTask.hashCode() + "，线程数：" + threads3.size());
                        this.mConfiguration.getFileDownloadEngine().execute(downloadTask);
                    }
                }
            }
        }
    }

    private void notifyDetectUrlFileFailed(String url, OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason, OnDetectBigUrlFileListener onDetectBigUrlFileListener) {
        Log.m5156d(TAG, "探测文件失败，url：" + url);
        OnDetectBigUrlFileListener.MainThreadHelper.onDetectUrlFileFailed(url, failReason, onDetectBigUrlFileListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean notifyDownloadStatusFailed(final String url, final OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason, final boolean recordStatus) {
        if (!isDownloading(url)) {
            return notifyDownloadStatusFailedInternal(url, failReason, recordStatus);
        }
        pause(url, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.3
            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskSucceed(String url2) {
                DownloadTaskManager.this.notifyDownloadStatusFailedInternal(url, failReason, recordStatus);
            }

            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason stopFailReason) {
                OnFileDownloadStatusListener.FileDownloadStatusFailReason notifyFailReason = failReason == null ? new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, stopFailReason) : failReason;
                DownloadTaskManager.this.notifyDownloadStatusFailedInternal(url2, notifyFailReason, recordStatus);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean notifyDownloadStatusFailedInternal(String url, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason, boolean recordStatus) {
        if (recordStatus) {
            try {
                this.mDownloadRecorder.recordStatus(url, 7, 0);
                this.mDownloadStatusObserver.onFileDownloadStatusFailed(url, getDownloadFile(url), failReason);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        this.mDownloadStatusObserver.onFileDownloadStatusFailed(url, getDownloadFile(url), failReason);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStopDownloadTaskSucceed(String url, OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        if (onStopFileDownloadTaskListener != null) {
            onStopFileDownloadTaskListener.onStopFileDownloadTaskSucceed(url);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStopDownloadTaskFailed(String url, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason, OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        if (onStopFileDownloadTaskListener != null) {
            onStopFileDownloadTaskListener.onStopFileDownloadTaskFailed(url, failReason);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyReleased(OnReleaseListener onReleaseListener) {
        this.mDetectUrlFileCacher.release();
        this.mDownloadStatusObserver.release();
        if (onReleaseListener != null) {
            onReleaseListener.onReleased();
        }
    }

    private void detectInternal(String url, boolean forceDetect, OnDetectBigUrlFileListener onDetectBigUrlFileListener, DownloadConfiguration downloadConfiguration) {
        addAndRunDetectUrlFileTask(url, forceDetect, onDetectBigUrlFileListener, downloadConfiguration);
    }

    @Deprecated
    public void detect(final String url, final OnDetectUrlFileListener onDetectUrlFileListener, DownloadConfiguration downloadConfiguration) {
        detect(url, new OnDetectBigUrlFileListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.4
            @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
            public void onDetectNewDownloadFile(String url2, String fileName, String saveDir, long fileSize) {
                if (onDetectUrlFileListener != null) {
                    onDetectUrlFileListener.onDetectNewDownloadFile(url, fileName, saveDir, (int) fileSize);
                }
            }

            @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
            public void onDetectUrlFileExist(String url2) {
                if (onDetectUrlFileListener != null) {
                    onDetectUrlFileListener.onDetectUrlFileExist(url);
                }
            }

            @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
            public void onDetectUrlFileFailed(String url2, OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason) {
                if (onDetectUrlFileListener != null) {
                    onDetectUrlFileListener.onDetectUrlFileFailed(url, new OnDetectUrlFileListener.DetectUrlFileFailReason(url, failReason));
                }
            }
        }, downloadConfiguration);
    }

    public void detect(String url, OnDetectBigUrlFileListener onDetectBigUrlFileListener, DownloadConfiguration downloadConfiguration) {
        detectInternal(url, false, onDetectBigUrlFileListener, downloadConfiguration);
    }

    public void createAndStart(String url, String saveDir, String fileName, DownloadConfiguration downloadConfiguration) {
        DetectUrlFileInfo detectUrlFileInfo = getDetectUrlFile(url);
        if (detectUrlFileInfo != null) {
            if (FileUtil.isFilePath(saveDir)) {
                detectUrlFileInfo.setFileDir(saveDir);
            }
            if (!TextUtils.isEmpty(fileName)) {
                detectUrlFileInfo.setFileName(fileName);
            }
            int index = checkFileName(detectUrlFileInfo.getFilePath());
            if (index > 0) {
                detectUrlFileInfo.setFileName(detectUrlFileInfo.getFileName() + index);
            }
        }
        createAndStartByDetectUrlFile(url, detectUrlFileInfo, downloadConfiguration);
    }

    private int checkFileName(String fileNamePath) {
        return checkFileNameInternal(fileNamePath, 0);
    }

    private int checkFileNameInternal(String fileNamePath, int index) {
        String checkFileNamePath = fileNamePath + (index > 0 ? Integer.valueOf(index) : "");
        if (!TextUtils.isEmpty(checkFileNamePath)) {
            List<DownloadFileInfo> downloadFileInfos = this.mDownloadRecorder.getDownloadFiles();
            if (!CollectionUtil.isEmpty(downloadFileInfos)) {
                boolean isFindSame = false;
                for (DownloadFileInfo info : downloadFileInfos) {
                    if (info != null && checkFileNamePath.equals(info.getFilePath())) {
                        isFindSame = true;
                        break;
                    }
                }
                return isFindSame ? checkFileNameInternal(fileNamePath, index + 1) : index;
            }
        }
        return index;
    }

    private void createAndStartByDetectUrlFile(String callerUrl, DetectUrlFileInfo detectUrlFileInfo, DownloadConfiguration downloadConfiguration) {
        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = null;
        if (!DownloadFileUtil.isLegal(detectUrlFileInfo) && (failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "detect file does not exist, please use detect(String,OnDetectBigUrlFileListener) first !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_FILE_NOT_DETECT)) == null && (!UrlUtil.isUrl(callerUrl) || !callerUrl.equals(detectUrlFileInfo.getUrl()))) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(callerUrl, "detect file does not exist, please use detect(String,OnDetectBigUrlFileListener) first !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_FILE_NOT_DETECT);
        }
        if (failReason != null) {
            notifyDownloadStatusFailed(callerUrl, failReason, getDownloadFile(callerUrl) != null);
            return;
        }
        DownloadFileInfo downloadFileInfo = this.mDownloadRecorder.createDownloadFileInfo(detectUrlFileInfo);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            removeDetectUrlFile(detectUrlFileInfo.getUrl());
        }
        startInternal(callerUrl, downloadFileInfo, downloadConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInternal(String callerUrl, DownloadFileInfo downloadFileInfo, DownloadConfiguration downloadConfiguration) {
        addAndRunDownloadTask(callerUrl, downloadFileInfo, downloadConfiguration);
    }

    public void start(final String url, final DownloadConfiguration downloadConfiguration) {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (downloadFileInfo != null) {
            startInternal(url, downloadFileInfo, downloadConfiguration);
            return;
        }
        DetectUrlFileInfo detectUrlFileInfo = getDetectUrlFile(url);
        if (detectUrlFileInfo != null) {
            createAndStartByDetectUrlFile(url, detectUrlFileInfo, downloadConfiguration);
        } else {
            detect(url, new OnDetectBigUrlFileListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.5
                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectUrlFileFailed(String url2, OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason) {
                    DownloadTaskManager.this.notifyDownloadStatusFailed(url, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, failReason), false);
                }

                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectUrlFileExist(String url2) {
                    DownloadTaskManager.this.startInternal(url, DownloadTaskManager.this.getDownloadFile(url), downloadConfiguration);
                }

                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectNewDownloadFile(String url2, String fileName, String savedDir, long fileSize) {
                    DownloadTaskManager.this.createAndStart(url, savedDir, fileName, downloadConfiguration);
                }
            }, downloadConfiguration);
        }
    }

    public void start(List<String> urls, DownloadConfiguration downloadConfiguration) {
        for (String url : urls) {
            start(url, downloadConfiguration);
        }
    }

    private void pauseInternal(final String url, final OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        DownloadTask downloadTask = getRunningDownloadTask(url);
        Log.m5156d(TAG, "pauseInternal fileDownloadTask是否已经停止：" + (downloadTask != null ? downloadTask.isStopped() : true) + ",url：" + url);
        if (downloadTask == null || downloadTask.isStopped()) {
            OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason = new OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason(url, "the download task has been paused !", OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED);
            Log.m5156d(TAG, "pauseInternal 任务已经被暂停，url：" + url + ",failReason:" + failReason.getType());
            DownloadFileInfo downloadFileInfo = getDownloadFile(url);
            if (DownloadFileUtil.isDownloadingStatus(downloadFileInfo)) {
                try {
                    this.mDownloadRecorder.recordStatus(url, 6, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            notifyStopDownloadTaskFailed(url, failReason, onStopFileDownloadTaskListener);
            return;
        }
        downloadTask.setOnStopFileDownloadTaskListener(new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.6
            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskSucceed(String url2) {
                Log.m5156d(DownloadTaskManager.TAG, "pauseInternal 暂停成功，url：" + url);
                DownloadTaskManager.this.notifyStopDownloadTaskSucceed(url, onStopFileDownloadTaskListener);
            }

            @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
            public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason2) {
                Log.m5156d(DownloadTaskManager.TAG, "pauseInternal 暂停失败，url：" + url + ",failReason:" + failReason2.getType());
                DownloadTaskManager.this.notifyStopDownloadTaskFailed(url, failReason2, onStopFileDownloadTaskListener);
            }
        });
        downloadTask.stop();
    }

    @Override // org.wlf.filedownloader.file_download.base.Pauseable
    public boolean isDownloading(String url) {
        return getRunningDownloadTask(url) != null;
    }

    @Override // org.wlf.filedownloader.file_download.base.Pauseable
    public void pause(String url, OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        pauseInternal(url, onStopFileDownloadTaskListener);
    }

    public void pause(List<String> urls, OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        for (String url : urls) {
            pause(url, onStopFileDownloadTaskListener);
        }
    }

    public void pauseAll(OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        Set<String> urls = this.mRunningDownloadTaskMap.keySet();
        pause(new ArrayList(urls), onStopFileDownloadTaskListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reStartInternal(final String url, final boolean isDelete, final DownloadConfiguration downloadConfiguration) {
        if (!UrlUtil.isUrl(url)) {
            OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "url illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_URL_ILLEGAL);
            notifyDownloadStatusFailed(url, failReason, getDownloadFile(url) != null);
            return;
        }
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            final String oldFileName = downloadFileInfo.getFileName();
            final String oldFileDir = downloadFileInfo.getFileDir();
            detectInternal(url, true, new OnDetectBigUrlFileListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.7
                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectNewDownloadFile(String url2, String fileName, String saveDir, long fileSize) {
                    try {
                        DownloadTaskManager.this.mDownloadRecorder.resetDownloadFile(url, isDelete);
                        DownloadTaskManager.this.createAndStart(url, oldFileDir, oldFileName, downloadConfiguration);
                    } catch (Exception e) {
                        e.printStackTrace();
                        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason2 = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, e);
                        DownloadTaskManager.this.notifyDownloadStatusFailed(url, failReason2, DownloadTaskManager.this.getDownloadFile(url) != null);
                    }
                }

                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectUrlFileExist(String url2) {
                    try {
                        DownloadTaskManager.this.mDownloadRecorder.resetDownloadFile(url, isDelete);
                        DownloadTaskManager.this.start(url2, downloadConfiguration);
                    } catch (Exception e) {
                        e.printStackTrace();
                        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason2 = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, e);
                        DownloadTaskManager.this.notifyDownloadStatusFailed(url, failReason2, DownloadTaskManager.this.getDownloadFile(url) != null);
                    }
                }

                @Override // org.wlf.filedownloader.listener.OnDetectBigUrlFileListener
                public void onDetectUrlFileFailed(String url2, OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason2) {
                    DownloadTaskManager.this.notifyDownloadStatusFailed(url, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, failReason2), DownloadTaskManager.this.getDownloadFile(url) != null);
                }
            }, downloadConfiguration);
            return;
        }
        start(url, downloadConfiguration);
    }

    public void reStart(final String url, final DownloadConfiguration downloadConfiguration) {
        if (isDownloading(url)) {
            pause(url, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_download.DownloadTaskManager.8
                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskSucceed(String url2) {
                    DownloadTaskManager.this.reStartInternal(url, true, downloadConfiguration);
                }

                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                    if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                        DownloadTaskManager.this.notifyDownloadStatusFailed(url, failReason, DownloadTaskManager.this.getDownloadFile(url) != null);
                    } else {
                        DownloadTaskManager.this.reStartInternal(url, true, downloadConfiguration);
                    }
                }
            });
        } else {
            reStartInternal(url, true, downloadConfiguration);
        }
    }

    public void reStart(List<String> urls, DownloadConfiguration downloadConfiguration) {
        for (String url : urls) {
            reStart(url, downloadConfiguration);
        }
    }

    private void removeDetectUrlFile(String url) {
        this.mDetectUrlFileCacher.removeDetectUrlFile(url);
    }
}
