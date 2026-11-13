package org.wlf.filedownloader.util;

import java.io.File;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.BaseUrlFileInfo;
import org.wlf.filedownloader.file_download.db_recorder.Record;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileUtil {
    public static boolean isDownloadingStatus(DownloadFileInfo downloadFileInfo) {
        if (!isLegal(downloadFileInfo)) {
            return false;
        }
        switch (downloadFileInfo.getStatus()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
                return true;
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                return false;
        }
    }

    public static boolean canDelete(DownloadFileInfo downloadFileInfo) {
        return isLegal(downloadFileInfo) && !isDownloadingStatus(downloadFileInfo);
    }

    public static boolean canMove(DownloadFileInfo downloadFileInfo) {
        return isLegal(downloadFileInfo) && !isDownloadingStatus(downloadFileInfo);
    }

    public static boolean canRename(DownloadFileInfo downloadFileInfo) {
        return isLegal(downloadFileInfo) && !isDownloadingStatus(downloadFileInfo);
    }

    public static boolean isCompleted(DownloadFileInfo downloadFileInfo) {
        if (!isLegal(downloadFileInfo)) {
            return false;
        }
        switch (downloadFileInfo.getStatus()) {
            case 5:
                return true;
            default:
                return false;
        }
    }

    public static boolean isLegal(BaseUrlFileInfo baseUrlFileInfo) {
        return baseUrlFileInfo != null && UrlUtil.isUrl(baseUrlFileInfo.getUrl());
    }

    public static boolean hasException(int status) {
        switch (status) {
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static boolean isTempFileExist(DownloadFileInfo downloadFileInfo) {
        if (isLegal(downloadFileInfo) && downloadFileInfo.getDownloadedSizeLong() >= 0) {
            return FileUtil.isFileExist(downloadFileInfo.getTempFilePath());
        }
        return false;
    }

    public static boolean tryToRenameTempFileToSaveFile(DownloadFileInfo downloadFileInfo) {
        if (!isLegal(downloadFileInfo)) {
            return false;
        }
        if (isCompleted(downloadFileInfo)) {
            File saveFile = new File(downloadFileInfo.getFilePath());
            return saveFile.exists() && saveFile.length() == downloadFileInfo.getDownloadedSizeLong() && saveFile.length() == downloadFileInfo.getFileSizeLong();
        }
        if (downloadFileInfo.getDownloadedSizeLong() != downloadFileInfo.getFileSizeLong()) {
            return false;
        }
        File tempFile = new File(downloadFileInfo.getTempFilePath());
        File saveFile2 = new File(downloadFileInfo.getFilePath());
        if (tempFile.exists() && tempFile.length() == downloadFileInfo.getDownloadedSizeLong() && !saveFile2.exists()) {
            return tempFile.renameTo(saveFile2);
        }
        return false;
    }

    public static void recoveryExceptionStatus(Record record, DownloadFileInfo downloadFileInfo) {
        if (isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            switch (downloadFileInfo.getStatus()) {
                case 1:
                case 2:
                case 3:
                case 4:
                    try {
                        record.recordStatus(url, 6, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    break;
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    try {
                        record.recordStatus(url, 7, 0);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                    break;
            }
        }
    }
}
