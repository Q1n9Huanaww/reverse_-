package org.wlf.filedownloader;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import java.io.File;
import java.util.Date;
import org.wlf.filedownloader.base.BaseUrlFileInfo;
import org.wlf.filedownloader.file_download.DetectUrlFileInfo;
import org.wlf.filedownloader.util.DateUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileInfo extends BaseUrlFileInfo {
    private static final String TEMP_FILE_SUFFIX = "temp";
    private long mDownloadedSize;
    private Integer mId;
    private int mStatus;
    private String mTempFileName;

    public static final class Table {
        public static final String COLUMN_NAME_OF_FIELD_ACCEPT_RANGE_TYPE = "accept_range_type";
        public static final String COLUMN_NAME_OF_FIELD_CREATE_DATETIME = "create_datetime";
        public static final String COLUMN_NAME_OF_FIELD_DOWNLOADED_SIZE = "downloaded_size";
        public static final String COLUMN_NAME_OF_FIELD_E_TAG = "e_tag";
        public static final String COLUMN_NAME_OF_FIELD_FILE_DIR = "file_dir";
        public static final String COLUMN_NAME_OF_FIELD_FILE_NAME = "file_name";
        public static final String COLUMN_NAME_OF_FIELD_FILE_SIZE = "file_size";
        public static final String COLUMN_NAME_OF_FIELD_ID = "_id";
        public static final String COLUMN_NAME_OF_FIELD_LAST_MODIFIED = "last_modified";
        public static final String COLUMN_NAME_OF_FIELD_STATUS = "status";
        public static final String COLUMN_NAME_OF_FIELD_TEMP_FILE_NAME = "temp_file_name";
        public static final String COLUMN_NAME_OF_FIELD_URL = "url";
        public static final String TABLE_NAME_OF_DOWNLOAD_FILE = "tb_download_file";

        public static final String getCreateTableSql() {
            return "CREATE TABLE IF NOT EXISTS tb_download_file(_id INTEGER PRIMARY KEY AUTOINCREMENT,url TEXT UNIQUE,downloaded_size INTEGER,file_size INTEGER,e_tag TEXT,last_modified TEXT,accept_range_type TEXT,file_dir TEXT,temp_file_name TEXT,file_name TEXT,status INTEGER,create_datetime TEXT)";
        }

        public static final String getUpdateTableVersion1To2Sql() {
            return "ALTER TABLE tb_download_file ADD create_datetime TEXT";
        }

        public static final String getUpdateTableVersion2To3Sql() {
            return "ALTER TABLE tb_download_file ADD last_modified TEXT";
        }
    }

    private DownloadFileInfo() {
        this.mStatus = 0;
    }

    DownloadFileInfo(DetectUrlFileInfo detectUrlFileInfo) {
        this.mStatus = 0;
        this.mUrl = detectUrlFileInfo.getUrl();
        this.mFileName = detectUrlFileInfo.getFileName();
        this.mFileSize = detectUrlFileInfo.getFileSizeLong();
        this.mETag = detectUrlFileInfo.getETag();
        this.mLastModified = detectUrlFileInfo.getLastModified();
        this.mAcceptRangeType = detectUrlFileInfo.getAcceptRangeType();
        this.mFileDir = detectUrlFileInfo.getFileDir();
        this.mTempFileName = this.mFileName + "." + TEMP_FILE_SUFFIX;
        this.mCreateDatetime = DateUtil.date2String_yyyy_MM_dd_HH_mm_ss(new Date());
    }

    DownloadFileInfo(Cursor cursor) {
        this.mStatus = 0;
        if (cursor != null && !cursor.isClosed()) {
            int columnIndex = cursor.getColumnIndex("_id");
            int id = columnIndex != -1 ? cursor.getInt(columnIndex) : -1;
            int columnIndex2 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_URL);
            String url = columnIndex2 != -1 ? cursor.getString(columnIndex2) : null;
            int columnIndex3 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_DOWNLOADED_SIZE);
            long downloadedSize = columnIndex3 != -1 ? cursor.getLong(columnIndex3) : 0L;
            int columnIndex4 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_FILE_SIZE);
            long fileSize = columnIndex4 != -1 ? cursor.getLong(columnIndex4) : 0L;
            int columnIndex5 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_E_TAG);
            String eTag = columnIndex5 != -1 ? cursor.getString(columnIndex5) : null;
            int columnIndex6 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_LAST_MODIFIED);
            String lastModified = columnIndex6 != -1 ? cursor.getString(columnIndex6) : null;
            int columnIndex7 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_ACCEPT_RANGE_TYPE);
            String acceptRangeType = columnIndex7 != -1 ? cursor.getString(columnIndex7) : null;
            int columnIndex8 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_FILE_DIR);
            String fileDir = columnIndex8 != -1 ? cursor.getString(columnIndex8) : null;
            int columnIndex9 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_TEMP_FILE_NAME);
            String tempFileName = columnIndex9 != -1 ? cursor.getString(columnIndex9) : null;
            int columnIndex10 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_FILE_NAME);
            String fileName = columnIndex10 != -1 ? cursor.getString(columnIndex10) : null;
            int columnIndex11 = cursor.getColumnIndex("status");
            int status = columnIndex11 != -1 ? cursor.getInt(columnIndex11) : 0;
            int columnIndex12 = cursor.getColumnIndex(Table.COLUMN_NAME_OF_FIELD_CREATE_DATETIME);
            String createDatetime = columnIndex12 != -1 ? cursor.getString(columnIndex12) : null;
            if (id > 0 && !TextUtils.isEmpty(url)) {
                this.mId = Integer.valueOf(id);
                this.mUrl = url;
                this.mDownloadedSize = downloadedSize;
                this.mFileSize = fileSize;
                this.mETag = eTag;
                this.mLastModified = lastModified;
                this.mAcceptRangeType = acceptRangeType;
                this.mFileDir = fileDir;
                this.mTempFileName = tempFileName;
                this.mFileName = fileName;
                this.mStatus = status;
                this.mCreateDatetime = createDatetime;
                return;
            }
            throw new IllegalArgumentException("id or url from cursor illegal!");
        }
        throw new NullPointerException("cursor illegal!");
    }

    void update(DownloadFileInfo downloadFileInfo) {
        if (downloadFileInfo.mId != null && downloadFileInfo.mId.intValue() > 0) {
            this.mId = downloadFileInfo.mId;
        }
        if (UrlUtil.isUrl(downloadFileInfo.mUrl)) {
            this.mUrl = downloadFileInfo.mUrl;
        }
        if (downloadFileInfo.mDownloadedSize > 0 && downloadFileInfo.mDownloadedSize != this.mDownloadedSize) {
            this.mDownloadedSize = downloadFileInfo.mDownloadedSize;
        }
        if (downloadFileInfo.mFileSize > 0 && downloadFileInfo.mFileSize != this.mFileSize) {
            this.mFileSize = downloadFileInfo.mFileSize;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mETag)) {
            this.mETag = downloadFileInfo.mETag;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mLastModified)) {
            this.mLastModified = downloadFileInfo.mLastModified;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mAcceptRangeType)) {
            this.mAcceptRangeType = downloadFileInfo.mAcceptRangeType;
        }
        if (FileUtil.isFilePath(downloadFileInfo.mFileDir)) {
            this.mFileDir = downloadFileInfo.mFileDir;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mTempFileName)) {
            this.mTempFileName = downloadFileInfo.mTempFileName;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mFileName)) {
            this.mFileName = downloadFileInfo.mFileName;
        }
        if (downloadFileInfo.mStatus != this.mStatus) {
            this.mStatus = downloadFileInfo.mStatus;
        }
        if (!TextUtils.isEmpty(downloadFileInfo.mCreateDatetime)) {
            this.mCreateDatetime = downloadFileInfo.mCreateDatetime;
        }
    }

    ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Table.COLUMN_NAME_OF_FIELD_URL, this.mUrl);
        values.put(Table.COLUMN_NAME_OF_FIELD_DOWNLOADED_SIZE, Long.valueOf(this.mDownloadedSize));
        values.put(Table.COLUMN_NAME_OF_FIELD_FILE_SIZE, Long.valueOf(this.mFileSize));
        values.put(Table.COLUMN_NAME_OF_FIELD_E_TAG, this.mETag);
        values.put(Table.COLUMN_NAME_OF_FIELD_LAST_MODIFIED, this.mLastModified);
        values.put(Table.COLUMN_NAME_OF_FIELD_ACCEPT_RANGE_TYPE, this.mAcceptRangeType);
        values.put(Table.COLUMN_NAME_OF_FIELD_FILE_DIR, this.mFileDir);
        values.put(Table.COLUMN_NAME_OF_FIELD_TEMP_FILE_NAME, this.mTempFileName);
        values.put(Table.COLUMN_NAME_OF_FIELD_FILE_NAME, this.mFileName);
        values.put("status", Integer.valueOf(this.mStatus));
        values.put(Table.COLUMN_NAME_OF_FIELD_CREATE_DATETIME, this.mCreateDatetime);
        return values;
    }

    public boolean equals(Object o) {
        if (TextUtils.isEmpty(this.mUrl) || !(o instanceof DownloadFileInfo)) {
            return super.equals(o);
        }
        DownloadFileInfo other = (DownloadFileInfo) o;
        return this.mUrl.equals(other.mUrl);
    }

    public int hashCode() {
        return !TextUtils.isEmpty(this.mUrl) ? this.mUrl.hashCode() : super.hashCode();
    }

    void setId(Integer id) {
        this.mId = id;
    }

    void setDownloadedSize(long downloadedSize) {
        this.mDownloadedSize = downloadedSize;
    }

    void setStatus(int status) {
        this.mStatus = status;
    }

    void setFileDir(String fileDir) {
        this.mFileDir = fileDir;
    }

    void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public Integer getId() {
        return this.mId;
    }

    @Deprecated
    public int getDownloadedSize() {
        return (int) this.mDownloadedSize;
    }

    public long getDownloadedSizeLong() {
        return this.mDownloadedSize;
    }

    public String getTempFileName() {
        return this.mTempFileName;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public String getTempFilePath() {
        return getFileDir() + File.separator + this.mTempFileName;
    }

    @Override // org.wlf.filedownloader.base.BaseUrlFileInfo
    public String toString() {
        return "DownloadFileInfo{mId=" + this.mId + ", mDownloadedSize=" + this.mDownloadedSize + ", mTempFileName='" + this.mTempFileName + "', mStatus=" + this.mStatus + "} " + super.toString();
    }
}
