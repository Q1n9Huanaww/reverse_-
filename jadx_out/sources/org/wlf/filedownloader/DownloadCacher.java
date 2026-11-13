package org.wlf.filedownloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_delete.DownloadFileDeleter;
import org.wlf.filedownloader.file_download.DetectUrlFileInfo;
import org.wlf.filedownloader.file_download.base.DownloadRecorder;
import org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbHelper;
import org.wlf.filedownloader.file_move.DownloadFileMover;
import org.wlf.filedownloader.file_rename.DownloadFileRenamer;
import org.wlf.filedownloader.listener.OnDownloadFileChangeListener;
import org.wlf.filedownloader.p095db.ContentDbDao;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.ContentValuesUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.MapUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadCacher implements DownloadRecorder, DownloadFileMover, DownloadFileDeleter, DownloadFileRenamer {
    private static final String TAG = DownloadCacher.class.getSimpleName();
    private DownloadFileDbHelper mDownloadFileDbHelper;
    private Map<String, DownloadFileInfo> mDownloadFileInfoMap = new HashMap();
    private Object mModifyLock = new Object();
    private DownloadFileChangeObserver mDownloadFileChangeObserver = new DownloadFileChangeObserver();

    DownloadCacher(Context context) {
        this.mDownloadFileDbHelper = new DownloadFileDbHelper(context);
        initDownloadFileInfoMapFromDb();
    }

    private void initDownloadFileInfoMapFromDb() {
        ContentDbDao dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE);
        if (dao != null) {
            Cursor cursor = dao.query(null, null, null, null);
            List<DownloadFileInfo> downloadFileInfos = getDownloadFilesFromCursor(cursor);
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (!CollectionUtil.isEmpty(downloadFileInfos)) {
                for (DownloadFileInfo downloadFileInfo : downloadFileInfos) {
                    if (DownloadFileUtil.isLegal(downloadFileInfo)) {
                        synchronized (this.mModifyLock) {
                            this.mDownloadFileInfoMap.put(downloadFileInfo.getUrl(), downloadFileInfo);
                        }
                    }
                }
            }
        }
    }

    private void checkDownloadFileStatus(DownloadFileInfo downloadFileInfo) {
        try {
            if (DownloadFileUtil.isLegal(downloadFileInfo) && downloadFileInfo.getDownloadedSizeLong() > 0) {
                String saveFilePath = downloadFileInfo.getFilePath();
                String tempFilePath = downloadFileInfo.getTempFilePath();
                File saveFile = null;
                File tempFile = null;
                if (FileUtil.isFilePath(saveFilePath)) {
                    saveFile = new File(saveFilePath);
                }
                if (FileUtil.isFilePath(tempFilePath)) {
                    tempFile = new File(tempFilePath);
                }
                if (downloadFileInfo.getStatus() == 8) {
                    boolean handled = false;
                    if (saveFile != null && saveFile.length() == downloadFileInfo.getDownloadedSizeLong() && downloadFileInfo.getDownloadedSizeLong() == downloadFileInfo.getFileSizeLong()) {
                        Log.m5156d(TAG, "checkDownloadFileStatus，文件已下载完，但当前状态为文件不存在，需要更改状态为已下载完成，url:" + downloadFileInfo.getUrl());
                        handled = updateDownloadFileWithStatus(downloadFileInfo, 5);
                    }
                    if (!handled) {
                        if ((tempFile != null && tempFile.exists() && tempFile.length() > 0) || (saveFile != null && saveFile.exists() && saveFile.length() > 0)) {
                            Log.m5156d(TAG, "checkDownloadFileStatus，文件未下载完/下载文件出现问题，但当前状态为文件不存在，需要更改状态为下载出错，url:" + downloadFileInfo.getUrl());
                            updateDownloadFileWithStatus(downloadFileInfo, 7);
                            return;
                        }
                        return;
                    }
                    return;
                }
                boolean fileExist = true;
                if (DownloadFileUtil.isCompleted(downloadFileInfo)) {
                    if (saveFile != null && !saveFile.exists() && (tempFile == null || !tempFile.exists() || tempFile.length() != downloadFileInfo.getDownloadedSizeLong() || downloadFileInfo.getDownloadedSizeLong() != downloadFileInfo.getFileSizeLong())) {
                        Log.m5156d(TAG, "checkDownloadFileStatus，文件下载完成，但是文件不存在了，需要更改状态为文件不存在，url:" + downloadFileInfo.getUrl());
                        fileExist = false;
                    }
                } else if (tempFile == null || !tempFile.exists() || tempFile.length() <= 0) {
                    Log.m5156d(TAG, "checkDownloadFileStatus，文件没有下载完成，但是文件不存在了，需要更改状态为文件不存在，url:" + downloadFileInfo.getUrl());
                    fileExist = false;
                }
                if (!fileExist) {
                    updateDownloadFileWithStatus(downloadFileInfo, 8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        synchronized (this.mModifyLock) {
            this.mDownloadFileInfoMap.clear();
            this.mDownloadFileChangeObserver.release();
            if (this.mDownloadFileDbHelper != null) {
                this.mDownloadFileDbHelper.close();
            }
        }
    }

    public void registerDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener, DownloadFileChangeConfiguration downloadFileChangeConfiguration) {
        this.mDownloadFileChangeObserver.addOnDownloadFileChangeListener(onDownloadFileChangeListener, downloadFileChangeConfiguration);
    }

    public void unregisterDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener) {
        this.mDownloadFileChangeObserver.removeOnDownloadFileChangeListener(onDownloadFileChangeListener);
    }

    private void notifyDownloadFileCreated(DownloadFileInfo downloadFileInfo) {
        if (this.mDownloadFileChangeObserver != null) {
            this.mDownloadFileChangeObserver.onDownloadFileCreated(downloadFileInfo);
        }
    }

    private void notifyDownloadFileUpdated(DownloadFileInfo downloadFileInfo, OnDownloadFileChangeListener.Type type) {
        if (this.mDownloadFileChangeObserver != null) {
            this.mDownloadFileChangeObserver.onDownloadFileUpdated(downloadFileInfo, type);
        }
    }

    private void notifyDownloadFileDeleted(DownloadFileInfo downloadFileInfo) {
        if (this.mDownloadFileChangeObserver != null) {
            this.mDownloadFileChangeObserver.onDownloadFileDeleted(downloadFileInfo);
        }
    }

    private boolean addDownloadFile(DownloadFileInfo downloadFileInfo) {
        ContentDbDao dao;
        boolean z;
        if (!DownloadFileUtil.isLegal(downloadFileInfo) || (dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE)) == null) {
            return false;
        }
        ContentValues values = downloadFileInfo.getContentValues();
        if (ContentValuesUtil.isEmpty(values)) {
            return false;
        }
        String url = downloadFileInfo.getUrl();
        DownloadFileInfo downloadFileInfoExist = getDownloadFile(url);
        if (DownloadFileUtil.isLegal(downloadFileInfoExist) && downloadFileInfoExist != downloadFileInfo) {
            OnDownloadFileChangeListener.Type type = OnDownloadFileChangeListener.Type.OTHER;
            int changeCount = 0;
            if (downloadFileInfoExist.getStatus() != downloadFileInfo.getStatus()) {
                changeCount = 0 + 1;
                type = OnDownloadFileChangeListener.Type.DOWNLOAD_STATUS;
            }
            if (downloadFileInfoExist.getDownloadedSizeLong() != downloadFileInfo.getDownloadedSizeLong()) {
                changeCount++;
                type = OnDownloadFileChangeListener.Type.DOWNLOADED_SIZE;
            }
            if (downloadFileInfoExist.getFileDir() != null && !downloadFileInfoExist.getFileDir().equals(downloadFileInfo.getFileDir())) {
                changeCount++;
                type = OnDownloadFileChangeListener.Type.SAVE_DIR;
            }
            if (downloadFileInfoExist.getFileName() != null && !downloadFileInfoExist.getFileName().equals(downloadFileInfo.getFileName())) {
                changeCount++;
                type = OnDownloadFileChangeListener.Type.SAVE_FILE_NAME;
            }
            if (changeCount > 1) {
                type = OnDownloadFileChangeListener.Type.OTHER;
            }
            synchronized (this.mModifyLock) {
                downloadFileInfoExist.update(downloadFileInfo);
                boolean isSucceed = updateDownloadFileInternal(downloadFileInfoExist, false, type);
                if (!isSucceed) {
                }
            }
            return true;
        }
        synchronized (this.mModifyLock) {
            long id = dao.insert(values);
            if (id != -1) {
                downloadFileInfo.setId(new Integer((int) id));
                this.mDownloadFileInfoMap.put(url, downloadFileInfo);
                notifyDownloadFileCreated(downloadFileInfo);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    private boolean updateDownloadFileInternal(DownloadFileInfo downloadFileInfo, boolean lockInternal, OnDownloadFileChangeListener.Type notifyType) {
        ContentDbDao dao;
        if (!DownloadFileUtil.isLegal(downloadFileInfo) || (dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE)) == null) {
            return false;
        }
        ContentValues values = downloadFileInfo.getContentValues();
        if (ContentValuesUtil.isEmpty(values)) {
            return false;
        }
        String url = downloadFileInfo.getUrl();
        if (lockInternal) {
            synchronized (this.mModifyLock) {
                int result = dao.update(values, "_id= ?", new String[]{downloadFileInfo.getId() + ""});
                if (result == 1) {
                    if (this.mDownloadFileInfoMap.containsKey(url)) {
                        DownloadFileInfo downloadFileInfoInMap = this.mDownloadFileInfoMap.get(url);
                        downloadFileInfoInMap.update(downloadFileInfo);
                    } else {
                        this.mDownloadFileInfoMap.put(url, downloadFileInfo);
                    }
                    notifyDownloadFileUpdated(downloadFileInfo, notifyType);
                    return true;
                }
            }
        } else {
            int result2 = dao.update(values, "_id= ?", new String[]{downloadFileInfo.getId() + ""});
            if (result2 == 1) {
                if (this.mDownloadFileInfoMap.containsKey(url)) {
                    DownloadFileInfo downloadFileInfoInMap2 = this.mDownloadFileInfoMap.get(url);
                    downloadFileInfoInMap2.update(downloadFileInfo);
                } else {
                    this.mDownloadFileInfoMap.put(url, downloadFileInfo);
                }
                notifyDownloadFileUpdated(downloadFileInfo, notifyType);
                return true;
            }
        }
        return false;
    }

    private boolean updateDownloadFileWithStatus(DownloadFileInfo downloadFileInfo, int newStatus) {
        boolean z = false;
        synchronized (this.mModifyLock) {
            int oldStatus = downloadFileInfo.getStatus();
            downloadFileInfo.setStatus(newStatus);
            boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, OnDownloadFileChangeListener.Type.DOWNLOAD_STATUS);
            if (!isSucceed) {
                downloadFileInfo.setStatus(oldStatus);
            } else {
                z = true;
            }
        }
        return z;
    }

    private boolean deleteDownloadFile(DownloadFileInfo downloadFileInfo) {
        ContentDbDao dao;
        boolean z = false;
        if (DownloadFileUtil.isLegal(downloadFileInfo) && (dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE)) != null) {
            String url = downloadFileInfo.getUrl();
            synchronized (this.mModifyLock) {
                int result = dao.delete("_id= ?", new String[]{downloadFileInfo.getId() + ""});
                if (result == 1) {
                    this.mDownloadFileInfoMap.remove(url);
                    notifyDownloadFileDeleted(downloadFileInfo);
                    z = true;
                } else {
                    int result2 = dao.delete("url= ?", new String[]{url + ""});
                    if (result2 == 1) {
                        this.mDownloadFileInfoMap.remove(url);
                        notifyDownloadFileDeleted(downloadFileInfo);
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public DownloadFileInfo getDownloadFileBySavePath(String savePath, boolean includeTempFilePath) {
        int separatorIndex;
        DownloadFileInfo info;
        if (!FileUtil.isFilePath(savePath)) {
            return null;
        }
        DownloadFileInfo downloadFileInfo = null;
        Set<Map.Entry<String, DownloadFileInfo>> set = this.mDownloadFileInfoMap.entrySet();
        for (Map.Entry<String, DownloadFileInfo> entry : set) {
            if (entry != null && (info = entry.getValue()) != null) {
                String filePath = info.getFilePath();
                if (!TextUtils.isEmpty(filePath) && filePath.equals(savePath)) {
                    downloadFileInfo = info;
                    break;
                }
            }
        }
        if (downloadFileInfo == null) {
            ContentDbDao dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE);
            if (dao == null || (separatorIndex = savePath.lastIndexOf(File.separator)) == -1) {
                return null;
            }
            String fileSaveDir = savePath.substring(0, separatorIndex);
            String fileSaveName = savePath.substring(separatorIndex + 1, savePath.length());
            Cursor cursor = dao.query(null, "file_dir= ? AND file_name= ?", new String[]{fileSaveDir, fileSaveName}, null);
            if (cursor != null && cursor.moveToFirst()) {
                downloadFileInfo = new DownloadFileInfo(cursor);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (downloadFileInfo == null && includeTempFilePath) {
                Cursor cursor2 = dao.query(null, "file_dir= ? AND temp_file_name= ?", new String[]{fileSaveDir, fileSaveName}, null);
                if (cursor2 != null && cursor2.moveToFirst()) {
                    downloadFileInfo = new DownloadFileInfo(cursor2);
                }
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
            }
            if (downloadFileInfo == null) {
                return null;
            }
            String url = downloadFileInfo.getUrl();
            if (UrlUtil.isUrl(url)) {
                synchronized (this.mModifyLock) {
                    this.mDownloadFileInfoMap.put(url, downloadFileInfo);
                    downloadFileInfo = this.mDownloadFileInfoMap.get(url);
                }
            }
        }
        checkDownloadFileStatus(downloadFileInfo);
        return downloadFileInfo;
    }

    private List<DownloadFileInfo> getDownloadFilesFromCursor(Cursor cursor) {
        List<DownloadFileInfo> downloadFileInfos = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            DownloadFileInfo downloadFileInfo = new DownloadFileInfo(cursor);
            if (downloadFileInfo != null) {
                downloadFileInfos.add(downloadFileInfo);
            }
        }
        return downloadFileInfos;
    }

    @Override // org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder
    public DownloadFileInfo getDownloadFile(String url) {
        DownloadFileInfo downloadFileInfo = getDownloadFileInternal(url);
        if (downloadFileInfo == null && UrlUtil.isUrl(url)) {
            return getDownloadFileInternal(url.trim());
        }
        return downloadFileInfo;
    }

    private DownloadFileInfo getDownloadFileInternal(String url) {
        DownloadFileInfo downloadFileInfo = null;
        if (this.mDownloadFileInfoMap.get(url) != null) {
            downloadFileInfo = this.mDownloadFileInfoMap.get(url);
        } else {
            ContentDbDao dao = this.mDownloadFileDbHelper.getContentDbDao(DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE);
            if (dao == null) {
                return null;
            }
            Cursor cursor = dao.query(null, "url= ?", new String[]{url}, null);
            if (cursor != null && cursor.moveToFirst()) {
                downloadFileInfo = new DownloadFileInfo(cursor);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (downloadFileInfo == null) {
                return null;
            }
            String downloadUrl = downloadFileInfo.getUrl();
            if (UrlUtil.isUrl(downloadUrl)) {
                synchronized (this.mModifyLock) {
                    this.mDownloadFileInfoMap.put(downloadUrl, downloadFileInfo);
                    downloadFileInfo = this.mDownloadFileInfoMap.get(url);
                }
            }
        }
        checkDownloadFileStatus(downloadFileInfo);
        return downloadFileInfo;
    }

    @Override // org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder
    public List<DownloadFileInfo> getDownloadFiles() {
        if (MapUtil.isEmpty(this.mDownloadFileInfoMap)) {
            initDownloadFileInfoMapFromDb();
        }
        if (!MapUtil.isEmpty(this.mDownloadFileInfoMap)) {
            List<DownloadFileInfo> downloadFileInfos = new ArrayList<>(this.mDownloadFileInfoMap.values());
            if (CollectionUtil.isEmpty(downloadFileInfos)) {
                return downloadFileInfos;
            }
            for (DownloadFileInfo downloadFileInfo : downloadFileInfos) {
                checkDownloadFileStatus(downloadFileInfo);
            }
            return downloadFileInfos;
        }
        return new ArrayList();
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadRecorder
    public DownloadFileInfo createDownloadFileInfo(DetectUrlFileInfo detectUrlFileInfo) {
        if (!DownloadFileUtil.isLegal(detectUrlFileInfo)) {
            return null;
        }
        DownloadFileInfo downloadFileInfo = new DownloadFileInfo(detectUrlFileInfo);
        boolean isSucceed = addDownloadFile(downloadFileInfo);
        if (isSucceed) {
            return downloadFileInfo;
        }
        return null;
    }

    @Override // org.wlf.filedownloader.file_download.db_recorder.Record
    public void recordStatus(String url, int status, int increaseSize) throws Exception {
        Log.m5160i(TAG, TAG + ".recordStatus 记录状态：status：" + status + "，increaseSize：" + increaseSize + "，url：" + url);
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            synchronized (this.mModifyLock) {
                int oldStatus = downloadFileInfo.getStatus();
                long oldDownloadedSize = downloadFileInfo.getDownloadedSizeLong();
                boolean isStatusChange = status != downloadFileInfo.getStatus();
                if (isStatusChange || increaseSize > 0) {
                    OnDownloadFileChangeListener.Type type = OnDownloadFileChangeListener.Type.OTHER;
                    int changeCount = 0;
                    if (isStatusChange) {
                        downloadFileInfo.setStatus(status);
                        changeCount = 0 + 1;
                        type = OnDownloadFileChangeListener.Type.DOWNLOAD_STATUS;
                    }
                    if (increaseSize > 0) {
                        downloadFileInfo.setDownloadedSize(downloadFileInfo.getDownloadedSizeLong() + ((long) increaseSize));
                        changeCount++;
                        type = OnDownloadFileChangeListener.Type.DOWNLOADED_SIZE;
                    }
                    if (changeCount > 1) {
                        type = OnDownloadFileChangeListener.Type.OTHER;
                    }
                    boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, type);
                    if (!isSucceed) {
                        downloadFileInfo.setStatus(oldStatus);
                        downloadFileInfo.setDownloadedSize(oldDownloadedSize);
                        throw new Exception("record failed !");
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadRecorder
    public void resetDownloadFile(String url, boolean deleteMode) throws Exception {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            if (!deleteMode) {
                synchronized (this.mModifyLock) {
                    long oldDownloadedSize = downloadFileInfo.getDownloadedSizeLong();
                    downloadFileInfo.setDownloadedSize(0L);
                    boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, OnDownloadFileChangeListener.Type.DOWNLOADED_SIZE);
                    if (!isSucceed) {
                        downloadFileInfo.setDownloadedSize(oldDownloadedSize);
                        throw new Exception("reset failed !");
                    }
                }
                return;
            }
            deleteDownloadFile(url);
        }
    }

    @Override // org.wlf.filedownloader.file_download.base.DownloadRecorder
    public void resetDownloadSize(String url, long downloadSize) throws Exception {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            if (downloadSize < 0 || downloadSize > downloadFileInfo.getFileSizeLong()) {
                throw new Exception("the download size nee to set is illegal !");
            }
            synchronized (this.mModifyLock) {
                long oldDownloadedSize = downloadFileInfo.getDownloadedSizeLong();
                downloadFileInfo.setDownloadedSize(downloadSize);
                boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, OnDownloadFileChangeListener.Type.DOWNLOADED_SIZE);
                if (!isSucceed) {
                    downloadFileInfo.setDownloadedSize(oldDownloadedSize);
                    throw new Exception("reset downloadSize failed !");
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.file_move.DownloadFileMover
    public void moveDownloadFile(String url, String newDirPath) throws Exception {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
            throw new Exception("download file doest not exist or illegal !");
        }
        synchronized (this.mModifyLock) {
            String oldFileDir = downloadFileInfo.getFileDir();
            downloadFileInfo.setFileDir(newDirPath);
            boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, OnDownloadFileChangeListener.Type.SAVE_DIR);
            if (!isSucceed) {
                downloadFileInfo.setFileDir(oldFileDir);
                throw new Exception("move failed !");
            }
        }
    }

    @Override // org.wlf.filedownloader.file_delete.DownloadFileDeleter
    public void deleteDownloadFile(String url) throws Exception {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
            throw new Exception("download file doest not exist or illegal !");
        }
        boolean isSucceed = deleteDownloadFile(downloadFileInfo);
        if (!isSucceed) {
            DownloadFileInfo DownloadFileInfoDeleted = getDownloadFileInternal(downloadFileInfo.getUrl());
            if (DownloadFileInfoDeleted != null) {
                throw new Exception("delete failed !");
            }
        }
    }

    @Override // org.wlf.filedownloader.file_rename.DownloadFileRenamer
    public void renameDownloadFile(String url, String newFileName) throws Exception {
        DownloadFileInfo downloadFileInfo = getDownloadFile(url);
        if (downloadFileInfo == null) {
            throw new Exception("download file doest not exist or illegal !");
        }
        synchronized (this.mModifyLock) {
            String oldFileName = downloadFileInfo.getFileName();
            downloadFileInfo.setFileName(newFileName);
            boolean isSucceed = updateDownloadFileInternal(downloadFileInfo, false, OnDownloadFileChangeListener.Type.SAVE_FILE_NAME);
            if (!isSucceed) {
                downloadFileInfo.setFileName(oldFileName);
                throw new Exception("rename failed !");
            }
        }
    }
}
