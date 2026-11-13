package org.wlf.filedownloader.file_download.db_recorder;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.p095db.BaseContentDbDao;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileDao extends BaseContentDbDao {
    private static final String TAG = DownloadFileDao.class.getSimpleName();

    public DownloadFileDao(SQLiteOpenHelper dbHelper) {
        super(dbHelper, DownloadFileInfo.Table.TABLE_NAME_OF_DOWNLOAD_FILE, "_id");
    }

    @Override // org.wlf.filedownloader.p095db.DatabaseCallback
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DownloadFileInfo.Table.getCreateTableSql());
    }

    @Override // org.wlf.filedownloader.p095db.DatabaseCallback
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.m5160i(TAG, TAG + ".onUpgrade，oldVersion：" + oldVersion + "，oldVersion：" + newVersion);
        if (newVersion == 2) {
            switch (oldVersion) {
                case 1:
                    updateVersion1To2(db);
                    break;
            }
        } else if (newVersion == 3) {
            switch (oldVersion) {
                case 1:
                    updateVersion1To3(db);
                    break;
                case 2:
                    updateVersion2To3(db);
                    break;
            }
        }
    }

    private void updateVersion1To2(SQLiteDatabase db) {
        db.execSQL(DownloadFileInfo.Table.getUpdateTableVersion1To2Sql());
    }

    private void updateVersion2To3(SQLiteDatabase db) {
        db.execSQL(DownloadFileInfo.Table.getUpdateTableVersion2To3Sql());
    }

    private void updateVersion1To3(SQLiteDatabase db) {
        db.execSQL(DownloadFileInfo.Table.getUpdateTableVersion1To2Sql());
        db.execSQL(DownloadFileInfo.Table.getUpdateTableVersion2To3Sql());
    }
}
