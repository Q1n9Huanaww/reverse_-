package org.wlf.filedownloader.file_download;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.DownloadRecorder;
import org.wlf.filedownloader.file_download.base.DownloadTask;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.OnTaskRunFinishListener;
import org.wlf.filedownloader.file_download.file_saver.FileSaver;
import org.wlf.filedownloader.file_download.http_downloader.ContentLengthInputStream;
import org.wlf.filedownloader.file_download.http_downloader.HttpDownloader;
import org.wlf.filedownloader.file_download.http_downloader.Range;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DownloadTaskImpl implements DownloadTask, HttpDownloader.OnHttpDownloadListener, FileSaver.OnFileSaveListener, HttpDownloader.OnRangeChangeListener {
    private static final String TAG = DownloadTaskImpl.class.getSimpleName();
    private ExecutorService mCloseConnectionEngine;
    private Thread mCurrentTaskThread;
    private DownloadRecorder mDownloadRecorder;
    private HttpDownloader mDownloader;
    private FinishState mFinishState;
    private OnFileDownloadStatusListener mOnFileDownloadStatusListener;
    private OnStopFileDownloadTaskListener mOnStopFileDownloadTaskListener;
    private OnTaskRunFinishListener mOnTaskRunFinishListener;
    private FileSaver mSaver;
    private FileDownloadTaskParam mTaskParamInfo;
    private boolean mIsTaskStop = false;
    private boolean mIsRunning = false;
    private long mLastDownloadingTime = -1;
    private AtomicBoolean mIsNotifyTaskFinish = new AtomicBoolean(false);
    private int mConnectTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;

    public DownloadTaskImpl(FileDownloadTaskParam taskParamInfo, DownloadRecorder downloadRecorder, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        this.mTaskParamInfo = taskParamInfo;
        init();
        this.mDownloadRecorder = downloadRecorder;
        this.mOnFileDownloadStatusListener = onFileDownloadStatusListener;
        if (!checkTaskCanExecute()) {
            stop();
            notifyTaskFinish();
            return;
        }
        if (!notifyStatusWaiting()) {
            stop();
            notifyTaskFinish();
        } else if (this.mSaver == null || this.mSaver.isStopped()) {
            stop();
            notifyTaskFinish();
        } else if (this.mIsTaskStop) {
            stopInternalImpl();
            notifyTaskFinish();
        }
    }

    private void init() {
        Log.m5156d(TAG, TAG + ".init 1、初始化新下载任务，url：" + getUrl());
        Range range = new Range(this.mTaskParamInfo.getStartPosInTotal(), this.mTaskParamInfo.getFileTotalSize());
        this.mDownloader = new HttpDownloader(getUrl(), range, this.mTaskParamInfo.getAcceptRangeType(), this.mTaskParamInfo.getETag(), this.mTaskParamInfo.getLastModified());
        this.mDownloader.setOnHttpDownloadListener(this);
        this.mDownloader.setCloseConnectionEngine(this.mCloseConnectionEngine);
        this.mDownloader.setConnectTimeout(this.mConnectTimeout);
        this.mDownloader.setOnRangeChangeListener(this);
        this.mDownloader.setRequestMethod(this.mTaskParamInfo.getRequestMethod());
        this.mDownloader.setHeaders(this.mTaskParamInfo.getHeaders());
        this.mSaver = new FileSaver(getUrl(), this.mTaskParamInfo.getTempFilePath(), this.mTaskParamInfo.getFilePath(), this.mTaskParamInfo.getFileTotalSize());
        this.mSaver.setOnFileSaveListener(this);
    }

    private boolean checkTaskCanExecute() {
        DownloadFileInfo downloadFileInfo;
        OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = null;
        String url = getUrl();
        if (this.mTaskParamInfo == null) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "init param is null pointer !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_NULL_POINTER);
        }
        if (failReason == null && !UrlUtil.isUrl(url)) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "url illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_URL_ILLEGAL);
        }
        if (failReason == null && !FileUtil.isFilePath(this.mTaskParamInfo.getFilePath())) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "saveDir illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_FILE_SAVE_PATH_ILLEGAL);
        }
        if (failReason == null && (!FileUtil.canWrite(this.mTaskParamInfo.getTempFilePath()) || !FileUtil.canWrite(this.mTaskParamInfo.getFilePath()))) {
            failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "savePath can not write !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_STORAGE_SPACE_CAN_NOT_WRITE);
        }
        if (failReason == null && (downloadFileInfo = getDownloadFile()) != null) {
            if (downloadFileInfo.getStatus() == 5) {
                this.mFinishState = new FinishState(5);
                return false;
            }
            if (downloadFileInfo.getDownloadedSizeLong() == downloadFileInfo.getFileSizeLong()) {
                boolean isSucceed = DownloadFileUtil.tryToRenameTempFileToSaveFile(downloadFileInfo);
                if (isSucceed) {
                    this.mFinishState = new FinishState(5);
                    return false;
                }
            }
        }
        if (failReason == null) {
            String checkPath = null;
            try {
                File file = new File(this.mTaskParamInfo.getFilePath());
                if (file != null) {
                    checkPath = file.getParentFile().getAbsolutePath();
                }
                if (!FileUtil.isFilePath(checkPath)) {
                    failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "file save path illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_FILE_SAVE_PATH_ILLEGAL);
                } else {
                    long freeSize = FileUtil.getAvailableSpace(checkPath);
                    long needDownloadSize = this.mTaskParamInfo.getFileTotalSize() - this.mTaskParamInfo.getStartPosInTotal();
                    if (freeSize == -1 || needDownloadSize > freeSize) {
                        failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "storage space is full or storage can not write !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_STORAGE_SPACE_IS_FULL);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, e);
            }
        }
        if (failReason != null) {
            this.mFinishState = new FinishState(7, failReason);
            return false;
        }
        return true;
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
        if (this.mDownloader != null) {
            this.mDownloader.setCloseConnectionEngine(this.mCloseConnectionEngine);
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
        if (this.mDownloader != null) {
            this.mDownloader.setConnectTimeout(this.mConnectTimeout);
        }
    }

    private DownloadFileInfo getDownloadFile() {
        if (this.mDownloadRecorder == null) {
            return null;
        }
        return this.mDownloadRecorder.getDownloadFile(getUrl());
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadTask
    public String getUrl() {
        if (this.mTaskParamInfo == null) {
            return null;
        }
        return this.mTaskParamInfo.getUrl();
    }

    public FinishState getFinishState() {
        return this.mFinishState;
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0370 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:158:0x0478 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:194:0x0583 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:235:0x06ab A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:40:0x0115 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:84:0x023a A[DONT_GENERATE] */
    @Override // java.lang.Runnable
    public void run() {
        String url = getUrl();
        try {
            this.mIsRunning = true;
            this.mCurrentTaskThread = Thread.currentThread();
            if (this.mIsTaskStop) {
                stopInternalImpl();
                return;
            }
            if (this.mSaver == null || this.mSaver.isStopped()) {
                init();
            }
            if (this.mSaver == null || this.mSaver.isStopped()) {
                stopInternalImpl();
                return;
            }
            Log.m5156d(TAG, TAG + ".run 2、任务开始执行，正在获取资源，url：：" + url);
            if (!UrlUtil.isUrl(url)) {
                OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "url illegal !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_URL_ILLEGAL);
                this.mFinishState = new FinishState(7, failReason);
            } else if (!notifyStatusPreparing()) {
                stopInternalImpl();
            } else {
                this.mFinishState = null;
                this.mDownloader.download();
            }
        } catch (Exception e) {
            e.printStackTrace();
            int status = 7;
            if (e instanceof FileSaver.FileSaveException) {
                FileSaver.FileSaveException fileSaveException = (FileSaver.FileSaveException) e;
                if (FileSaver.FileSaveException.TYPE_TEMP_FILE_DOES_NOT_EXIST.equals(fileSaveException.getType())) {
                    status = 8;
                }
            }
            this.mFinishState = new FinishState(status, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, e));
        } finally {
            DownloadFileInfo downloadFileInfo = getDownloadFile();
            if (downloadFileInfo == null) {
                OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason2 = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "the DownloadFile is null, may be not deleted ?", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_NULL_POINTER);
                this.mFinishState = new FinishState(7, failReason2);
            } else {
                long downloadedSize = downloadFileInfo.getDownloadedSizeLong();
                long fileSize = downloadFileInfo.getFileSizeLong();
                if (downloadedSize == fileSize) {
                    if (this.mFinishState == null || this.mFinishState.status != 5) {
                        this.mFinishState = new FinishState(5);
                    }
                } else if (downloadedSize >= fileSize) {
                    OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason3 = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(url, "the download file size error !", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_DOWNLOAD_FILE_ERROR);
                    this.mFinishState = new FinishState(7, failReason3);
                } else if (this.mFinishState == null) {
                    this.mFinishState = new FinishState(6);
                } else if (this.mFinishState.failReason == null && !DownloadFileUtil.hasException(this.mFinishState.status)) {
                    this.mFinishState = new FinishState(6);
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
            boolean hasException = (this.mFinishState == null || this.mFinishState.failReason == null || !DownloadFileUtil.hasException(this.mFinishState.status)) ? false : true;
            Log.m5156d(TAG, TAG + ".run 7、文件下载任务【已结束】，是否有异常：" + hasException + "，url：" + url);
        }
    }

    @Override // org.wlf.filedownloader.file_download.http_downloader.HttpDownloader.OnRangeChangeListener
    public boolean onRangeChanged(Range oldRange, Range newRange) {
        if (!Range.isLegal(newRange)) {
            return true;
        }
        if (newRange.startPos > oldRange.startPos && oldRange.startPos >= 0) {
            return false;
        }
        try {
            this.mDownloadRecorder.resetDownloadSize(getUrl(), newRange.startPos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // org.wlf.filedownloader.file_download.http_downloader.HttpDownloader.OnHttpDownloadListener
    public void onDownloadConnected(ContentLengthInputStream inputStream, long startPosInTotal) throws Throwable {
        if (this.mIsTaskStop) {
            stopInternalImpl();
            return;
        }
        Log.m5156d(TAG, TAG + ".run 3、已经连接到资源，url：" + getUrl());
        if (!notifyStatusPrepared()) {
            stopInternalImpl();
            return;
        }
        try {
            this.mSaver.saveData(inputStream, startPosInTotal);
        } catch (FileSaver.FileSaveException e) {
            e.printStackTrace();
            this.mFinishState = new FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
        }
    }

    @Override // org.wlf.filedownloader.file_download.file_saver.FileSaver.OnFileSaveListener
    public void onSaveDataStart() {
        if (this.mIsTaskStop) {
            stopInternalImpl();
            return;
        }
        Log.m5156d(TAG, TAG + ".run 4、准备下载，url：" + getUrl());
        if (!notifyStatusDownloading(0)) {
            stopInternalImpl();
        }
    }

    @Override // org.wlf.filedownloader.file_download.file_saver.FileSaver.OnFileSaveListener
    public void onSavingData(int increaseSize, long totalSize) {
        if (this.mIsTaskStop) {
            stopInternalImpl();
            return;
        }
        Log.m5156d(TAG, TAG + ".run 5、下载中，url：" + getUrl());
        if (!notifyStatusDownloading(increaseSize)) {
            stopInternalImpl();
        }
    }

    @Override // org.wlf.filedownloader.file_download.file_saver.FileSaver.OnFileSaveListener
    public void onSaveDataEnd(int increaseSize, boolean complete) {
        if (!complete) {
            this.mFinishState = new FinishState(6, increaseSize);
            Log.m5156d(TAG, TAG + ".run 6、暂停下载，url：" + getUrl());
        } else {
            this.mFinishState = new FinishState(5, increaseSize);
            Log.m5156d(TAG, TAG + ".run 6、下载完成，url：" + getUrl());
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
            this.mFinishState = new FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
            return false;
        }
    }

    private boolean notifyStatusPreparing() {
        try {
            this.mDownloadRecorder.recordStatus(getUrl(), 2, 0);
            if (this.mOnFileDownloadStatusListener != null) {
                this.mOnFileDownloadStatusListener.onFileDownloadStatusPreparing(getDownloadFile());
            }
            Log.m5160i(TAG, "file-downloader-status 记录【正在准备状态】成功，url：" + getUrl());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mFinishState = new FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
            return false;
        }
    }

    private boolean notifyStatusPrepared() {
        try {
            this.mDownloadRecorder.recordStatus(getUrl(), 3, 0);
            if (this.mOnFileDownloadStatusListener != null) {
                this.mOnFileDownloadStatusListener.onFileDownloadStatusPrepared(getDownloadFile());
            }
            Log.m5160i(TAG, "file-downloader-status 记录【已准备状态】成功，url：" + getUrl());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mFinishState = new FinishState(7, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
            return false;
        }
    }

    private boolean notifyStatusDownloading(int increaseSize) {
        try {
            this.mDownloadRecorder.recordStatus(getUrl(), 4, increaseSize);
            DownloadFileInfo downloadFileInfo = getDownloadFile();
            if (downloadFileInfo == null) {
                OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason = new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), "the DownloadFile is null!", OnFileDownloadStatusListener.OnFileDownloadStatusFailReason.TYPE_NULL_POINTER);
                this.mFinishState = new FinishState(7, 0, failReason);
                return false;
            }
            if (this.mOnFileDownloadStatusListener != null) {
                double downloadSpeed = 0.0d;
                long remainingTime = -1;
                long curDownloadingTime = SystemClock.elapsedRealtime();
                if (this.mLastDownloadingTime != -1) {
                    double increaseKbs = ((double) increaseSize) / 1024.0d;
                    double increaseSeconds = (curDownloadingTime - this.mLastDownloadingTime) / 1000.0d;
                    downloadSpeed = increaseKbs / increaseSeconds;
                }
                if (downloadSpeed > 0.0d) {
                    long remainingSize = downloadFileInfo.getFileSizeLong() - downloadFileInfo.getDownloadedSizeLong();
                    if (remainingSize > 0) {
                        remainingTime = (long) ((remainingSize / 1024.0d) / downloadSpeed);
                    }
                }
                this.mLastDownloadingTime = curDownloadingTime;
                if (this.mOnFileDownloadStatusListener != null) {
                    this.mOnFileDownloadStatusListener.onFileDownloadStatusDownloading(downloadFileInfo, (float) downloadSpeed, remainingTime);
                }
            }
            Log.m5160i(TAG, "file-downloader-status 记录【正在下载状态】成功，url：" + getUrl());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mFinishState = new FinishState(7, 0, new OnFileDownloadStatusListener.OnFileDownloadStatusFailReason(getUrl(), e));
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
            this.mFinishState = new FinishState(6);
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

    @Override // org.wlf.filedownloader.base.Stoppable
    public boolean isStopped() {
        if (this.mIsTaskStop && !this.mSaver.isStopped()) {
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
            handler.post(new Runnable() { // from class: org.wlf.filedownloader.file_download.DownloadTaskImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    DownloadTaskImpl.this.mIsTaskStop = true;
                    Log.m5156d(DownloadTaskImpl.TAG, DownloadTaskImpl.TAG + ".stop 结束任务执行(主线程发起)，url：" + DownloadTaskImpl.this.getUrl() + ",是否已经暂停：" + DownloadTaskImpl.this.mIsTaskStop);
                    DownloadTaskImpl.this.stopInternalImpl();
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
            handler.post(new Runnable() { // from class: org.wlf.filedownloader.file_download.DownloadTaskImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!DownloadTaskImpl.this.mSaver.isStopped()) {
                        DownloadTaskImpl.this.mSaver.stop();
                    }
                    if (!DownloadTaskImpl.this.mIsRunning) {
                        DownloadTaskImpl.this.notifyTaskFinish();
                        DownloadTaskImpl.this.notifyStopTaskSucceedIfNecessary();
                    }
                }
            });
            return;
        }
        if (!this.mSaver.isStopped()) {
            this.mSaver.stop();
        }
        if (!this.mIsRunning) {
            notifyTaskFinish();
            notifyStopTaskSucceedIfNecessary();
        }
    }

    static class FinishState {
        public final OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason;
        public final int increaseSize;
        public final int status;

        public FinishState(int status) {
            this.status = status;
            this.increaseSize = 0;
            this.failReason = null;
        }

        public FinishState(int status, int increaseSize) {
            this.status = status;
            this.increaseSize = increaseSize;
            this.failReason = null;
        }

        public FinishState(int status, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
            this.status = status;
            this.increaseSize = 0;
            this.failReason = failReason;
        }

        public FinishState(int status, int increaseSize, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
            this.status = status;
            this.increaseSize = increaseSize;
            this.failReason = failReason;
        }

        public int getStatus() {
            return this.status;
        }

        public int getIncreaseSize() {
            return this.increaseSize;
        }

        public OnFileDownloadStatusListener.FileDownloadStatusFailReason getFailReason() {
            return this.failReason;
        }
    }
}
