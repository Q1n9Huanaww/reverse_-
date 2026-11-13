package org.wlf.filedownloader;

import android.content.Context;
import java.util.List;
import org.wlf.filedownloader.base.Control;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_delete.DownloadDeleteManager;
import org.wlf.filedownloader.file_download.DownloadTaskManager;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_move.DownloadMoveManager;
import org.wlf.filedownloader.file_rename.DownloadRenameManager;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnDetectUrlFileListener;
import org.wlf.filedownloader.listener.OnDownloadFileChangeListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFileListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFilesListener;
import org.wlf.filedownloader.listener.OnRenameDownloadFileListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class FileDownloadManager {
    private static final String TAG = FileDownloadManager.class.getSimpleName();
    private static FileDownloadManager sInstance;
    private FileDownloadConfiguration mConfiguration;
    private DownloadDeleteManager mDownloadDeleteManager;
    private DownloadCacher mDownloadFileCacher;
    private DownloadMoveManager mDownloadMoveManager;
    private DownloadRenameManager mDownloadRenameManager;
    private DownloadTaskManager mDownloadTaskManager;
    private Object mInitLock = new Object();

    private FileDownloadManager(Context context) {
        Context appContext = context.getApplicationContext();
        this.mDownloadFileCacher = new DownloadCacher(appContext);
        checkAndRecoveryExceptionStatus(getDownloadFiles());
    }

    public static FileDownloadManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (FileDownloadManager.class) {
                if (sInstance == null) {
                    sInstance = new FileDownloadManager(context);
                }
            }
        }
        return sInstance;
    }

    private void checkAndRecoveryExceptionStatus(List<DownloadFileInfo> downloadFileInfos) {
        Log.m5160i(TAG, "checkAndRecoveryExceptionStatus 异常恢复检查！");
        if (!CollectionUtil.isEmpty(downloadFileInfos)) {
            for (DownloadFileInfo downloadFileInfo : downloadFileInfos) {
                if (DownloadFileUtil.isLegal(downloadFileInfo)) {
                    String url = downloadFileInfo.getUrl();
                    if (!isInit() || !getDownloadTaskManager().isDownloading(url)) {
                        DownloadFileUtil.recoveryExceptionStatus(this.mDownloadFileCacher, downloadFileInfo);
                    }
                }
            }
        }
    }

    public void init(FileDownloadConfiguration configuration) {
        synchronized (this.mInitLock) {
            this.mConfiguration = configuration;
        }
    }

    public boolean isInit() {
        boolean z;
        synchronized (this.mInitLock) {
            z = this.mConfiguration != null;
        }
        return z;
    }

    private void checkInit() {
        if (!isInit()) {
            throw new IllegalStateException("Please init the file-downloader by using " + FileDownloader.class.getSimpleName() + ".init(FileDownloadConfiguration) or " + FileDownloadManager.class.getSimpleName() + ".init(FileDownloadConfiguration) if the version is below 0.2.0 !");
        }
    }

    public void release() {
        getDownloadTaskManager().release(new DownloadTaskManager.OnReleaseListener() { // from class: org.wlf.filedownloader.FileDownloadManager.1
            @Override // org.wlf.filedownloader.file_download.DownloadTaskManager.OnReleaseListener
            public void onReleased() {
                synchronized (FileDownloadManager.this.mInitLock) {
                    if (FileDownloadManager.this.mConfiguration != null) {
                        FileDownloadManager.this.mConfiguration.getFileDetectEngine().shutdown();
                        FileDownloadManager.this.mConfiguration.getFileDownloadEngine().shutdown();
                        FileDownloadManager.this.mConfiguration.getFileOperationEngine().shutdown();
                    }
                    FileDownloadManager.this.mDownloadFileCacher.release();
                    FileDownloadManager unused = FileDownloadManager.sInstance = null;
                }
            }
        });
    }

    private DownloadTaskManager getDownloadTaskManager() {
        checkInit();
        if (this.mDownloadTaskManager == null) {
            this.mDownloadTaskManager = new DownloadTaskManager(this.mConfiguration, this.mDownloadFileCacher);
        }
        return this.mDownloadTaskManager;
    }

    private DownloadMoveManager getDownloadMoveManager() {
        checkInit();
        if (this.mDownloadMoveManager == null) {
            this.mDownloadMoveManager = new DownloadMoveManager(this.mConfiguration.getFileOperationEngine(), this.mDownloadFileCacher, getDownloadTaskManager());
        }
        return this.mDownloadMoveManager;
    }

    private DownloadDeleteManager getDownloadDeleteManager() {
        checkInit();
        if (this.mDownloadDeleteManager == null) {
            this.mDownloadDeleteManager = new DownloadDeleteManager(this.mConfiguration.getFileOperationEngine(), this.mDownloadFileCacher, getDownloadTaskManager());
        }
        return this.mDownloadDeleteManager;
    }

    private DownloadRenameManager getDownloadRenameManager() {
        checkInit();
        if (this.mDownloadRenameManager == null) {
            this.mDownloadRenameManager = new DownloadRenameManager(this.mConfiguration.getFileOperationEngine(), this.mDownloadFileCacher, getDownloadTaskManager());
        }
        return this.mDownloadRenameManager;
    }

    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileCacher.getDownloadFile(url);
    }

    @Deprecated
    public DownloadFileInfo getDownloadFileByUrl(String url) {
        return this.mDownloadFileCacher.getDownloadFile(url);
    }

    public DownloadFileInfo getDownloadFileBySavePath(String savePath) {
        return this.mDownloadFileCacher.getDownloadFileBySavePath(savePath, false);
    }

    public DownloadFileInfo getDownloadFileByTempPath(String tempPath) {
        return this.mDownloadFileCacher.getDownloadFileBySavePath(tempPath, true);
    }

    @Deprecated
    public DownloadFileInfo getDownloadFileBySavePath(String savePath, boolean includeTempFilePath) {
        return this.mDownloadFileCacher.getDownloadFileBySavePath(savePath, includeTempFilePath);
    }

    public List<DownloadFileInfo> getDownloadFiles() {
        return this.mDownloadFileCacher.getDownloadFiles();
    }

    public String getDownloadDir() {
        checkInit();
        return this.mConfiguration.getFileDownloadDir();
    }

    public void registerDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        registerDownloadStatusListener(onFileDownloadStatusListener, null);
    }

    public void unregisterDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        getDownloadTaskManager().unregisterDownloadStatusListener(onFileDownloadStatusListener);
    }

    @Deprecated
    public void detect(String url, OnDetectUrlFileListener onDetectUrlFileListener) {
        getDownloadTaskManager().detect(url, onDetectUrlFileListener, (DownloadConfiguration) null);
    }

    public void detect(String url, OnDetectBigUrlFileListener onDetectBigUrlFileListener) {
        detect(url, onDetectBigUrlFileListener, null);
    }

    public void createAndStart(String url, String saveDir, String fileName) {
        getDownloadTaskManager().createAndStart(url, saveDir, fileName, null);
    }

    @Deprecated
    public void createAndStart(String url, String saveDir, String fileName, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        DownloadStatusConfiguration.Builder builder = new DownloadStatusConfiguration.Builder();
        builder.addListenUrl(url);
        builder.configAutoRelease(true);
        registerDownloadStatusListener(onFileDownloadStatusListener, builder.build());
        createAndStart(url, saveDir, fileName);
    }

    public void start(String url) {
        getDownloadTaskManager().start(url, (DownloadConfiguration) null);
    }

    @Deprecated
    public void start(String url, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        DownloadStatusConfiguration.Builder builder = new DownloadStatusConfiguration.Builder();
        builder.addListenUrl(url);
        builder.configAutoRelease(true);
        registerDownloadStatusListener(onFileDownloadStatusListener, builder.build());
        start(url);
    }

    public void start(List<String> urls) {
        getDownloadTaskManager().start(urls, (DownloadConfiguration) null);
    }

    @Deprecated
    public void start(List<String> urls, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        DownloadStatusConfiguration.Builder builder = new DownloadStatusConfiguration.Builder();
        builder.addListenUrls(urls);
        builder.configAutoRelease(true);
        registerDownloadStatusListener(onFileDownloadStatusListener, builder.build());
        start(urls);
    }

    public void pause(String url) {
        getDownloadTaskManager().pause(url, (OnStopFileDownloadTaskListener) null);
    }

    public void pause(List<String> urls) {
        getDownloadTaskManager().pause(urls, (OnStopFileDownloadTaskListener) null);
    }

    public void pauseAll() {
        getDownloadTaskManager().pauseAll(null);
    }

    public void reStart(String url) {
        getDownloadTaskManager().reStart(url, (DownloadConfiguration) null);
    }

    @Deprecated
    public void reStart(String url, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        DownloadStatusConfiguration.Builder builder = new DownloadStatusConfiguration.Builder();
        builder.addListenUrl(url);
        builder.configAutoRelease(true);
        registerDownloadStatusListener(onFileDownloadStatusListener, builder.build());
        reStart(url);
    }

    public void reStart(List<String> urls) {
        getDownloadTaskManager().reStart(urls, (DownloadConfiguration) null);
    }

    @Deprecated
    public void reStart(List<String> urls, OnFileDownloadStatusListener onFileDownloadStatusListener) {
        DownloadStatusConfiguration.Builder builder = new DownloadStatusConfiguration.Builder();
        builder.addListenUrls(urls);
        builder.configAutoRelease(true);
        registerDownloadStatusListener(onFileDownloadStatusListener, builder.build());
        reStart(urls);
    }

    public void move(String url, String newDirPath, OnMoveDownloadFileListener onMoveDownloadFileListener) {
        getDownloadMoveManager().move(url, newDirPath, onMoveDownloadFileListener);
    }

    public Control move(List<String> urls, String newDirPath, OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
        return getDownloadMoveManager().move(urls, newDirPath, onMoveDownloadFilesListener);
    }

    public void delete(String url, boolean deleteDownloadedFileInPath, OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        getDownloadDeleteManager().delete(url, deleteDownloadedFileInPath, onDeleteDownloadFileListener);
    }

    public Control delete(List<String> urls, boolean deleteDownloadedFile, OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
        return getDownloadDeleteManager().delete(urls, deleteDownloadedFile, onDeleteDownloadFilesListener);
    }

    public void rename(String url, String newFileName, boolean includedSuffix, OnRenameDownloadFileListener onRenameDownloadFileListener) {
        getDownloadRenameManager().rename(url, newFileName, includedSuffix, onRenameDownloadFileListener);
    }

    static FileDownloadConfiguration getFileDownloadConfiguration() {
        if (sInstance != null) {
            synchronized (sInstance.mInitLock) {
                if (sInstance != null) {
                    return sInstance.mConfiguration;
                }
            }
        }
        return null;
    }

    void registerDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener, DownloadStatusConfiguration downloadStatusConfiguration) {
        getDownloadTaskManager().registerDownloadStatusListener(onFileDownloadStatusListener, downloadStatusConfiguration);
    }

    void registerDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener, DownloadFileChangeConfiguration downloadFileChangeConfiguration) {
        this.mDownloadFileCacher.registerDownloadFileChangeListener(onDownloadFileChangeListener, downloadFileChangeConfiguration);
    }

    void unregisterDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener) {
        this.mDownloadFileCacher.unregisterDownloadFileChangeListener(onDownloadFileChangeListener);
    }

    void detect(String url, OnDetectBigUrlFileListener onDetectBigUrlFileListener, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().detect(url, onDetectBigUrlFileListener, downloadConfiguration);
    }

    void createAndStart(String url, String saveDir, String fileName, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().createAndStart(url, saveDir, fileName, downloadConfiguration);
    }

    void start(String url, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().start(url, downloadConfiguration);
    }

    void start(List<String> urls, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().start(urls, downloadConfiguration);
    }

    void reStart(String url, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().reStart(url, downloadConfiguration);
    }

    void reStart(List<String> urls, DownloadConfiguration downloadConfiguration) {
        getDownloadTaskManager().reStart(urls, downloadConfiguration);
    }
}
