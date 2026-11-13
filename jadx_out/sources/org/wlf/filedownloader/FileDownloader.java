package org.wlf.filedownloader;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.wlf.filedownloader.base.Control;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnDetectUrlFileListener;
import org.wlf.filedownloader.listener.OnDownloadFileChangeListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFileListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFilesListener;
import org.wlf.filedownloader.listener.OnRenameDownloadFileListener;
import org.wlf.filedownloader.util.DownloadFileUtil;

/* JADX INFO: loaded from: classes2.dex */
public final class FileDownloader {
    public static void init(FileDownloadConfiguration configuration) {
        if (configuration != null) {
            Context context = configuration.getContext();
            FileDownloadManager.getInstance(context).init(configuration);
        }
    }

    public static boolean isInit() {
        if (FileDownloadManager.getFileDownloadConfiguration() == null) {
            return false;
        }
        return getFileDownloadManager().isInit();
    }

    public static void release() {
        if (FileDownloadManager.getFileDownloadConfiguration() != null && isInit()) {
            getFileDownloadManager().release();
        }
    }

    private static FileDownloadManager getFileDownloadManager() {
        if (FileDownloadManager.getFileDownloadConfiguration() == null) {
            throw new IllegalStateException("Please init the file-downloader by using " + FileDownloader.class.getSimpleName() + ".init(FileDownloadConfiguration) !");
        }
        return FileDownloadManager.getInstance(FileDownloadManager.getFileDownloadConfiguration().getContext());
    }

    public static DownloadFileInfo getDownloadFile(String url) {
        return getFileDownloadManager().getDownloadFile(url);
    }

    public static DownloadFileInfo getDownloadFileBySavePath(String savePath) {
        return getFileDownloadManager().getDownloadFileBySavePath(savePath);
    }

    public static DownloadFileInfo getDownloadFileByTempPath(String tempPath) {
        return getFileDownloadManager().getDownloadFileByTempPath(tempPath);
    }

    public static List<DownloadFileInfo> getDownloadFiles() {
        return getFileDownloadManager().getDownloadFiles();
    }

    public static String getDownloadDir() {
        return getFileDownloadManager().getDownloadDir();
    }

    public static FileDownloadConfiguration getFileDownloadConfiguration() {
        return FileDownloadManager.getFileDownloadConfiguration();
    }

    public static void registerDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        getFileDownloadManager().registerDownloadStatusListener(onFileDownloadStatusListener);
    }

    public static void registerDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener, DownloadStatusConfiguration downloadStatusConfiguration) {
        getFileDownloadManager().registerDownloadStatusListener(onFileDownloadStatusListener, downloadStatusConfiguration);
    }

    public static void unregisterDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        getFileDownloadManager().unregisterDownloadStatusListener(onFileDownloadStatusListener);
    }

    public static void registerDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener) {
        getFileDownloadManager().registerDownloadFileChangeListener(onDownloadFileChangeListener, null);
    }

    public static void registerDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener, DownloadFileChangeConfiguration downloadFileChangeConfiguration) {
        getFileDownloadManager().registerDownloadFileChangeListener(onDownloadFileChangeListener, downloadFileChangeConfiguration);
    }

    public static void unregisterDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener) {
        getFileDownloadManager().unregisterDownloadFileChangeListener(onDownloadFileChangeListener);
    }

    @Deprecated
    public static void detect(String url, OnDetectUrlFileListener onDetectUrlFileListener) {
        getFileDownloadManager().detect(url, onDetectUrlFileListener);
    }

    public static void detect(String url, OnDetectBigUrlFileListener onDetectBigUrlFileListener) {
        getFileDownloadManager().detect(url, onDetectBigUrlFileListener);
    }

    public static void detect(String url, OnDetectBigUrlFileListener onDetectBigUrlFileListener, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrl(url);
        }
        getFileDownloadManager().detect(url, onDetectBigUrlFileListener, downloadConfiguration);
    }

    public static void createAndStart(String url, String saveDir, String fileName) {
        getFileDownloadManager().createAndStart(url, saveDir, fileName);
    }

    public static void createAndStart(String url, String saveDir, String fileName, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrl(url);
        }
        getFileDownloadManager().createAndStart(url, saveDir, fileName, downloadConfiguration);
    }

    public static void start(String url) {
        getFileDownloadManager().start(url);
    }

    public static void start(String url, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrl(url);
        }
        getFileDownloadManager().start(url, downloadConfiguration);
    }

    public static void start(List<String> urls) {
        getFileDownloadManager().start(urls);
    }

    public static void start(List<String> urls, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrls(urls);
        }
        getFileDownloadManager().start(urls, downloadConfiguration);
    }

    public static void continueAll(boolean isIncludedErrorDownloads) {
        continueAll(isIncludedErrorDownloads, null);
    }

    public static void continueAll(boolean isIncludedErrorDownloads, DownloadConfiguration downloadConfiguration) {
        List<String> urls = new ArrayList<>();
        List<DownloadFileInfo> downloadFileInfos = getDownloadFiles();
        if (downloadFileInfos != null) {
            for (DownloadFileInfo downloadFileInfo : downloadFileInfos) {
                if (DownloadFileUtil.isLegal(downloadFileInfo) && downloadFileInfo.getStatus() != 5) {
                    if (isIncludedErrorDownloads) {
                        urls.add(downloadFileInfo.getUrl());
                    } else if (downloadFileInfo.getStatus() != 7) {
                        urls.add(downloadFileInfo.getUrl());
                    }
                }
            }
        }
        start(urls, downloadConfiguration);
    }

    public static void pause(String url) {
        getFileDownloadManager().pause(url);
    }

    public static void pause(List<String> urls) {
        getFileDownloadManager().pause(urls);
    }

    public static void pauseAll() {
        getFileDownloadManager().pauseAll();
    }

    public static void reStart(String url) {
        getFileDownloadManager().reStart(url);
    }

    public static void reStart(String url, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrl(url);
        }
        getFileDownloadManager().reStart(url, downloadConfiguration);
    }

    public static void reStart(List<String> urls) {
        getFileDownloadManager().reStart(urls);
    }

    public void reStart(List<String> urls, DownloadConfiguration downloadConfiguration) {
        if (downloadConfiguration != null) {
            downloadConfiguration.initNullKeyForUrls(urls);
        }
        getFileDownloadManager().reStart(urls, downloadConfiguration);
    }

    public static void move(String url, String newDirPath, OnMoveDownloadFileListener onMoveDownloadFileListener) {
        getFileDownloadManager().move(url, newDirPath, onMoveDownloadFileListener);
    }

    public static Control move(List<String> urls, String newDirPath, OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
        return getFileDownloadManager().move(urls, newDirPath, onMoveDownloadFilesListener);
    }

    public static void delete(String url, boolean deleteDownloadedFileInPath, OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        getFileDownloadManager().delete(url, deleteDownloadedFileInPath, onDeleteDownloadFileListener);
    }

    public static Control delete(List<String> urls, boolean deleteDownloadedFile, OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
        return getFileDownloadManager().delete(urls, deleteDownloadedFile, onDeleteDownloadFilesListener);
    }

    public static void rename(String url, String newFileName, boolean includedSuffix, OnRenameDownloadFileListener onRenameDownloadFileListener) {
        getFileDownloadManager().rename(url, newFileName, includedSuffix, onRenameDownloadFileListener);
    }
}
