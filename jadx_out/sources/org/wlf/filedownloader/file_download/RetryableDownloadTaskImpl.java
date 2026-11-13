package org.wlf.filedownloader.file_download;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.DownloadRecorder;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.OnTaskRunFinishListener;
import org.wlf.filedownloader.file_download.base.RetryableDownloadTask;
import org.wlf.filedownloader.file_download.http_downloader.Range;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener;
import org.wlf.filedownloader.util.DownloadFileUtil;

/* JADX INFO: loaded from: classes2.dex */
class RetryableDownloadTaskImpl implements RetryableDownloadTask, OnFileDownloadStatusListener {
    private static final String TAG = RetryableDownloadTaskImpl.class.getSimpleName();
    private ExecutorService mCloseConnectionEngine;
    private Thread mCurrentTaskThread;
    private DownloadRecorder mDownloadRecorder;
    private DownloadTaskImpl mFileDownloadTaskImpl;
    private DownloadTaskImpl.FinishState mFinishState;
    private boolean mIsTaskStop;
    private OnFileDownloadStatusListener mOnFileDownloadStatusListener;
    private OnStopFileDownloadTaskListener mOnStopFileDownloadTaskListener;
    private OnTaskRunFinishListener mOnTaskRunFinishListener;
    private final FileDownloadTaskParam mOriginalTaskParamInfo;
    private Range mRecordedRange;
    private int mRetryDownloadTimes = 0;
    private int mHasRetriedTimes = 0;
    private boolean mIsRunning = false;
    private AtomicBoolean mIsNotifyTaskFinish = new AtomicBoolean(false);
    private int mConnectTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;

    public RetryableDownloadTaskImpl(FileDownloadTaskParam taskParamInfo, DownloadRecorder downloadRecorder, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        this.mIsTaskStop = false;
        this.mOriginalTaskParamInfo = taskParamInfo;
        this.mDownloadRecorder = downloadRecorder;
        this.mOnFileDownloadStatusListener = onFileDownloadStatusListener;
        this.mIsTaskStop = false;
        init();
        if (this.mFileDownloadTaskImpl == null || this.mFileDownloadTaskImpl.isStopped()) {
            stop();
            DownloadTaskImpl.FinishState finishStateInternal = this.mFileDownloadTaskImpl.getFinishState();
            if (finishStateInternal != null) {
                this.mFinishState = new DownloadTaskImpl.FinishState(finishStateInternal.getStatus(), finishStateInternal.getFailReason());
            }
            notifyTaskFinish();
        }
    }

    private void init() {
        if (this.mRecordedRange == null) {
            this.mRecordedRange = new Range(this.mOriginalTaskParamInfo.getStartPosInTotal(), this.mOriginalTaskParamInfo.getStartPosInTotal());
        }
        FileDownloadTaskParam taskParamInfo = new FileDownloadTaskParam(getUrl(), this.mOriginalTaskParamInfo.getStartPosInTotal() + this.mRecordedRange.getLength(), this.mOriginalTaskParamInfo.getFileTotalSize(), this.mOriginalTaskParamInfo.getETag(), this.mOriginalTaskParamInfo.getLastModified(), this.mOriginalTaskParamInfo.getAcceptRangeType(), this.mOriginalTaskParamInfo.getTempFilePath(), this.mOriginalTaskParamInfo.getFilePath());
        taskParamInfo.setRequestMethod(this.mOriginalTaskParamInfo.getRequestMethod());
        taskParamInfo.setHeaders(this.mOriginalTaskParamInfo.getHeaders());
        this.mFileDownloadTaskImpl = new DownloadTaskImpl(taskParamInfo, this.mDownloadRecorder, this);
        this.mFileDownloadTaskImpl.setCloseConnectionEngine(this.mCloseConnectionEngine);
        this.mFileDownloadTaskImpl.setConnectTimeout(this.mConnectTimeout);
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadTask
    public void setOnStopFileDownloadTaskListener(OnStopFileDownloadTaskListener onStopFileDownloadTaskListener) {
        this.mOnStopFileDownloadTaskListener = onStopFileDownloadTaskListener;
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadTask
    public void setOnTaskRunFinishListener(OnTaskRunFinishListener onTaskRunFinishListener) {
        this.mOnTaskRunFinishListener = onTaskRunFinishListener;
    }

    public void setCloseConnectionEngine(ExecutorService closeConnectionEngine) {
        this.mCloseConnectionEngine = closeConnectionEngine;
        if (this.mFileDownloadTaskImpl != null) {
            this.mFileDownloadTaskImpl.setCloseConnectionEngine(this.mCloseConnectionEngine);
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
        if (this.mFileDownloadTaskImpl != null) {
            this.mFileDownloadTaskImpl.setConnectTimeout(this.mConnectTimeout);
        }
    }

    @Override // org.wlf.filedownloader.file_download.base.RetryableDownloadTask
    public void setRetryDownloadTimes(int retryDownloadTimes) {
        this.mRetryDownloadTimes = retryDownloadTimes;
    }

    private DownloadFileInfo getDownloadFile() {
        if (this.mDownloadRecorder == null) {
            return null;
        }
        return this.mDownloadRecorder.getDownloadFile(getUrl());
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadTask
    public String getUrl() {
        if (this.mOriginalTaskParamInfo == null) {
            return null;
        }
        return this.mOriginalTaskParamInfo.getUrl();
    }

    private boolean notifyStatusRetrying() {
        try {
            if (this.mOnFileDownloadStatusListener instanceof OnRetryableFileDownloadStatusListener) {
                OnRetryableFileDownloadStatusListener onRetryableFileDownloadStatusListener = (OnRetryableFileDownloadStatusListener) this.mOnFileDownloadStatusListener;
                this.mDownloadRecorder.recordStatus(getUrl(), 9, 0);
                if (onRetryableFileDownloadStatusListener != null) {
                    onRetryableFileDownloadStatusListener.onFileDownloadStatusRetrying(getDownloadFile(), this.mHasRetriedTimes);
                }
                Log.m5160i(TAG, "file-downloader-status 记录【重试状态】成功，url：" + getUrl());
                return true;
            }
            return notifyStatusWaiting();
        } catch (Exception e) {
            e.printStackTrace();
            this.mFinishState = new DownloadTaskImpl.FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
            return false;
        }
    }

    private boolean notifyStatusWaiting() {
        try {
            this.mDownloadRecorder.recordStatus(getUrl(), 1, 0);
            if (this.mOnFileDownloadStatusListener != null) {
                this.mOnFileDownloadStatusListener.onFileDownloadStatusWaiting(getDownloadFile());
            }
            Log.m5160i(TAG, "file-downloader-status 记录【等待状态】成功，url：" + getUrl());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mFinishState = new DownloadTaskImpl.FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:37:0x0109 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:82:0x00fa A[DONT_GENERATE, EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:? A[RETURN, SYNTHETIC] */
    public void notifyTaskFinish() {
        boolean zCompareAndSet;
        if (this.mFinishState == null) {
            this.mFinishState = new DownloadTaskImpl.FinishState(6);
        }
        int status = this.mFinishState.status;
        int increaseSize = this.mFinishState.increaseSize;
        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = this.mFinishState.failReason;
        switch (status) {
            case 5:
            case 6:
            case 7:
            case 8:
                try {
                    if (!this.mIsNotifyTaskFinish.get()) {
                        this.mDownloadRecorder.recordStatus(getUrl(), status, increaseSize);
                        switch (status) {
                            case 5:
                                if (this.mOnFileDownloadStatusListener != null && this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                                    this.mOnFileDownloadStatusListener.onFileDownloadStatusCompleted(getDownloadFile());
                                    Log.m5160i(TAG, "file-downloader-status 记录【完成状态】成功，url：" + getUrl());
                                }
                                break;
                            case 6:
                                if (this.mOnFileDownloadStatusListener != null && this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                                    this.mOnFileDownloadStatusListener.onFileDownloadStatusPaused(getDownloadFile());
                                    Log.m5160i(TAG, "file-downloader-status 记录【暂停状态】成功，url：" + getUrl());
                                }
                                break;
                            case 7:
                                if (this.mOnFileDownloadStatusListener != null && this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                                    this.mOnFileDownloadStatusListener.onFileDownloadStatusFailed(getUrl(), getDownloadFile(), failReason);
                                    Log.m5160i(TAG, "file-downloader-status 记录【错误状态】成功，url：" + getUrl());
                                }
                                break;
                            case 8:
                                if (this.mOnFileDownloadStatusListener != null && this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                                    this.mOnFileDownloadStatusListener.onFileDownloadStatusFailed(getUrl(), getDownloadFile(), failReason);
                                    Log.m5160i(TAG, "file-downloader-status 记录【文件不存在状态】成功，url：" + getUrl());
                                }
                                break;
                        }
                        if (zCompareAndSet) {
                            try {
                                break;
                            } catch (Exception e) {
                            }
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e1) {
                    e1.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                        this.mDownloadRecorder.recordStatus(getUrl(), 7, 0);
                    }
                    if (zCompareAndSet) {
                        try {
                            break;
                        } catch (Exception e3) {
                        }
                    }
                } finally {
                    if (this.mIsNotifyTaskFinish.compareAndSet(false, true)) {
                        try {
                            this.mDownloadRecorder.recordStatus(getUrl(), 6, 0);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        if (this.mOnFileDownloadStatusListener != null) {
                            this.mOnFileDownloadStatusListener.onFileDownloadStatusPaused(getDownloadFile());
                        }
                        Log.m5160i(TAG, "file-downloader-status 记录【暂停状态】成功，url：" + getUrl());
                        break;
                    }
                }
                break;
            default:
                return;
        }
        if (this.mOnFileDownloadStatusListener != null) {
            this.mOnFileDownloadStatusListener.onFileDownloadStatusFailed(getUrl(), getDownloadFile(), new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e2));
            Log.m5158e(TAG, "file-downloader-status 记录【暂停/完成/出错状态】失败，url：" + getUrl());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStopTaskSucceedIfNecessary() {
        if (this.mOnStopFileDownloadTaskListener != null) {
            this.mOnStopFileDownloadTaskListener.onStopFileDownloadTaskSucceed(getUrl());
            this.mOnStopFileDownloadTaskListener = null;
            Log.m5160i(TAG, "file-downloader-status 通知【暂停任务】成功，url：" + getUrl());
        }
    }

    private void notifyStopTaskFailedIfNecessary(OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
        if (this.mOnStopFileDownloadTaskListener != null) {
            this.mOnStopFileDownloadTaskListener.onStopFileDownloadTaskFailed(getUrl(), failReason);
            this.mOnStopFileDownloadTaskListener = null;
            Log.m5158e(TAG, "file-downloader-status 通知【暂停任务】失败，url：" + getUrl());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean hasException;
        try {
            try {
                this.mIsRunning = true;
                this.mCurrentTaskThread = Thread.currentThread();
                if (this.mIsTaskStop) {
                    stopInternalImpl();
                    stopInternalImpl();
                    this.mIsTaskStop = true;
                    this.mIsRunning = false;
                    notifyTaskFinish();
                    notifyStopTaskSucceedIfNecessary();
                    if (this.mOnTaskRunFinishListener != null) {
                        this.mOnTaskRunFinishListener.onTaskRunFinish();
                    }
                    hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                    Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                    return;
                }
                if (this.mFileDownloadTaskImpl == null || this.mFileDownloadTaskImpl.isStopped()) {
                    init();
                }
                if (this.mFileDownloadTaskImpl == null || this.mFileDownloadTaskImpl.isStopped()) {
                    stopInternalImpl();
                    stopInternalImpl();
                    this.mIsTaskStop = true;
                    this.mIsRunning = false;
                    notifyTaskFinish();
                    notifyStopTaskSucceedIfNecessary();
                    if (this.mOnTaskRunFinishListener != null) {
                        this.mOnTaskRunFinishListener.onTaskRunFinish();
                    }
                    hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                    Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                    return;
                }
                this.mFinishState = null;
                this.mFileDownloadTaskImpl.run();
                AtomicBoolean isInternalStop = new AtomicBoolean(false);
                DownloadFileInfo downloadFileInfo = getDownloadFile();
                while (DownloadFileUtil.isTempFileExist(downloadFileInfo) && !this.mIsTaskStop && this.mHasRetriedTimes < this.mRetryDownloadTimes && this.mRetryDownloadTimes > 0 && this.mFinishState.status == 7) {
                    isInternalStop.set(this.mFileDownloadTaskImpl.isStopped());
                    if (isInternalStop.get()) {
                        init();
                        if (this.mFileDownloadTaskImpl == null || this.mFileDownloadTaskImpl.isStopped()) {
                            stopInternalImpl();
                            stopInternalImpl();
                            this.mIsTaskStop = true;
                            this.mIsRunning = false;
                            notifyTaskFinish();
                            notifyStopTaskSucceedIfNecessary();
                            if (this.mOnTaskRunFinishListener != null) {
                                this.mOnTaskRunFinishListener.onTaskRunFinish();
                            }
                            hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                            Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                            return;
                        }
                        isInternalStop.set(false);
                        this.mHasRetriedTimes++;
                        if (!notifyStatusRetrying()) {
                            stopInternalImpl();
                            this.mIsTaskStop = true;
                            this.mIsRunning = false;
                            notifyTaskFinish();
                            notifyStopTaskSucceedIfNecessary();
                            if (this.mOnTaskRunFinishListener != null) {
                                this.mOnTaskRunFinishListener.onTaskRunFinish();
                            }
                            hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                            Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                            return;
                        }
                        Log.m5156d(TAG, TAG + ".run 正在重试，url：" + getUrl());
                        try {
                            Thread.sleep(2000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.mIsTaskStop) {
                            stopInternalImpl();
                            this.mFinishState = new DownloadTaskImpl.FinishState(6);
                            stopInternalImpl();
                            this.mIsTaskStop = true;
                            this.mIsRunning = false;
                            notifyTaskFinish();
                            notifyStopTaskSucceedIfNecessary();
                            if (this.mOnTaskRunFinishListener != null) {
                                this.mOnTaskRunFinishListener.onTaskRunFinish();
                            }
                            hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                            Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                            return;
                        }
                        if (this.mFileDownloadTaskImpl == null || this.mFileDownloadTaskImpl.isStopped()) {
                            init();
                        }
                        this.mFinishState = null;
                        this.mFileDownloadTaskImpl.run();
                    } else {
                        stopInternalImpl();
                        try {
                            Thread.sleep(2000L);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                stopInternalImpl();
                this.mIsTaskStop = true;
                this.mIsRunning = false;
                notifyTaskFinish();
                notifyStopTaskSucceedIfNecessary();
                if (this.mOnTaskRunFinishListener != null) {
                    this.mOnTaskRunFinishListener.onTaskRunFinish();
                }
                hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
            } catch (Throwable th) {
                stopInternalImpl();
                this.mIsTaskStop = true;
                this.mIsRunning = false;
                notifyTaskFinish();
                notifyStopTaskSucceedIfNecessary();
                if (this.mOnTaskRunFinishListener != null) {
                    this.mOnTaskRunFinishListener.onTaskRunFinish();
                }
                hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
                Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            this.mFinishState = new DownloadTaskImpl.FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e3));
            stopInternalImpl();
            this.mIsTaskStop = true;
            this.mIsRunning = false;
            notifyTaskFinish();
            notifyStopTaskSucceedIfNecessary();
            if (this.mOnTaskRunFinishListener != null) {
                this.mOnTaskRunFinishListener.onTaskRunFinish();
            }
            hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
            Log.m5156d(TAG, TAG + ".run 文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + getUrl());
        }
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public boolean isStopped() {
        if (this.mIsTaskStop && !this.mFileDownloadTaskImpl.isStopped()) {
            stopInternalImpl();
        }
        return this.mIsTaskStop;
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public void stop() {
        Log.m5156d(TAG, TAG + ".stop 结束任务执行，url：" + getUrl() + ",是否已经暂停：" + this.mIsTaskStop);
        if (isStopped()) {
            notifyStopTaskFailedIfNecessary(new OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason(getUrl(), "the task has been stopped!", OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED));
            return;
        }
        if (Thread.currentThread() == this.mCurrentTaskThread) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() { // from class: org.wlf.filedownloader.file_download.RetryableDownloadTaskImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    RetryableDownloadTaskImpl.this.mIsTaskStop = true;
                    Log.m5156d(RetryableDownloadTaskImpl.TAG, RetryableDownloadTaskImpl.TAG + ".stop 结束任务执行(主线程发起)，url：" + RetryableDownloadTaskImpl.this.getUrl() + ",是否已经暂停：" + RetryableDownloadTaskImpl.this.mIsTaskStop);
                    RetryableDownloadTaskImpl.this.stopInternalImpl();
                }
            });
        } else {
            this.mIsTaskStop = true;
            Log.m5156d(TAG, TAG + ".stop 结束任务执行(其它线程发起)，url：" + getUrl() + ",是否已经暂停：" + this.mIsTaskStop);
            stopInternalImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternalImpl() {
        if (Thread.currentThread() == this.mCurrentTaskThread) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() { // from class: org.wlf.filedownloader.file_download.RetryableDownloadTaskImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!RetryableDownloadTaskImpl.this.mFileDownloadTaskImpl.isStopped()) {
                        RetryableDownloadTaskImpl.this.mFileDownloadTaskImpl.stop();
                    }
                    if (!RetryableDownloadTaskImpl.this.mIsRunning) {
                        RetryableDownloadTaskImpl.this.notifyTaskFinish();
                        RetryableDownloadTaskImpl.this.notifyStopTaskSucceedIfNecessary();
                    }
                }
            });
            return;
        }
        if (!this.mFileDownloadTaskImpl.isStopped()) {
            this.mFileDownloadTaskImpl.stop();
        }
        if (!this.mIsRunning) {
            notifyTaskFinish();
            notifyStopTaskSucceedIfNecessary();
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
        if (this.mIsTaskStop) {
            stopInternalImpl();
        } else if (this.mOnFileDownloadStatusListener != null) {
            this.mOnFileDownloadStatusListener.onFileDownloadStatusWaiting(downloadFileInfo);
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
        if (this.mIsTaskStop) {
            stopInternalImpl();
        } else if (this.mOnFileDownloadStatusListener != null) {
            this.mOnFileDownloadStatusListener.onFileDownloadStatusPreparing(downloadFileInfo);
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
        if (this.mIsTaskStop) {
            stopInternalImpl();
            return;
        }
        if (this.mOnFileDownloadStatusListener != null) {
            this.mOnFileDownloadStatusListener.onFileDownloadStatusPrepared(downloadFileInfo);
        }
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            this.mRecordedRange = new Range(downloadFileInfo.getDownloadedSizeLong(), this.mRecordedRange.endPos);
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime) {
        if (this.mIsTaskStop) {
            stopInternalImpl();
        } else if (this.mOnFileDownloadStatusListener != null) {
            this.mOnFileDownloadStatusListener.onFileDownloadStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime);
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
        this.mFinishState = new DownloadTaskImpl.FinishState(6);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            this.mRecordedRange = new Range(this.mRecordedRange.startPos, downloadFileInfo.getDownloadedSizeLong());
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
        this.mFinishState = new DownloadTaskImpl.FinishState(5);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            this.mRecordedRange = new Range(this.mRecordedRange.startPos, downloadFileInfo.getDownloadedSizeLong());
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            if (downloadFileInfo.getStatus() == 8) {
                this.mFinishState = new DownloadTaskImpl.FinishState(8, failReason);
            } else {
                this.mFinishState = new DownloadTaskImpl.FinishState(7, failReason);
            }
            this.mRecordedRange = new Range(this.mRecordedRange.startPos, downloadFileInfo.getDownloadedSizeLong());
        }
    }
}
