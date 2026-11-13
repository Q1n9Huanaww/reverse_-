package org.wlf.filedownloader.p095db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseContentDbDao implements ContentDbDao, DatabaseCallback {
    public static final String DEFAULT_TABLE_ID_FIELD_NAME = "_id";
    private SQLiteOpenHelper mDbHelper;
    private String mTableIdFieldName;
    private String mTableName;

    public BaseContentDbDao(SQLiteOpenHelper dbHelper, String tableName, String tableIdFieldName) {
        this.mTableIdFieldName = "_id";
        this.mDbHelper = dbHelper;
        this.mTableName = tableName;
        this.mTableIdFieldName = tableIdFieldName;
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public long insert(ContentValues values) {
        long id = -1;
        try {
            SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
            id = database.insert(this.mTableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public int delete(String selection, String[] selectionArgs) {
        try {
            SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
            int count = database.delete(this.mTableName, selection, selectionArgs);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public int update(ContentValues values, String selection, String[] selectionArgs) {
        try {
            SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
            int count = database.update(this.mTableName, values, selection, selectionArgs);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            SQLiteDatabase database = this.mDbHelper.getReadableDatabase();
            Cursor cursor = database.query(true, this.mTableName, null, selection, selectionArgs, null, null, sortOrder, null);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public String getTableName() {
        return this.mTableName;
    }

    @Override // org.wlf.filedownloader.p095db.ContentDbDao
    public String getTableIdFieldName() {
        return this.mTableIdFieldName;
    }
}
