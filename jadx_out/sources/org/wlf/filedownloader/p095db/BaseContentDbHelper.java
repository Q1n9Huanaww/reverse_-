package org.wlf.filedownloader.p095db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.wlf.filedownloader.util.CollectionUtil;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseContentDbHelper extends SQLiteOpenHelper {
    private Map<String, ContentDbDao> mContentDbDaoMap;

    protected abstract void onConfigContentDbDaos(List<ContentDbDao> list);

    public BaseContentDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContentDbDaoMap = new HashMap();
        initContentDbDaoMap();
    }

    private void initContentDbDaoMap() {
        List<ContentDbDao> contentDbDaos = new ArrayList<>();
        onConfigContentDbDaos(contentDbDaos);
        if (!CollectionUtil.isEmpty(contentDbDaos)) {
            for (ContentDbDao contentDbDao : contentDbDaos) {
                if (contentDbDao != null) {
                    String tableName = contentDbDao.getTableName();
                    if (!TextUtils.isEmpty(tableName) && !this.mContentDbDaoMap.containsKey(tableName)) {
                        this.mContentDbDaoMap.put(tableName, contentDbDao);
                    }
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Collection<ContentDbDao> contentDbDaos = this.mContentDbDaoMap.values();
        if (!CollectionUtil.isEmpty(contentDbDaos)) {
            for (ContentDbDao contentDbDao : contentDbDaos) {
                if (contentDbDao != null) {
                    contentDbDao.onCreate(db);
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Collection<ContentDbDao> contentDbDaos = this.mContentDbDaoMap.values();
        if (!CollectionUtil.isEmpty(contentDbDaos)) {
            for (ContentDbDao contentDbDao : contentDbDaos) {
                if (contentDbDao != null) {
                    contentDbDao.onUpgrade(db, oldVersion, newVersion);
                }
            }
        }
    }

    public ContentDbDao getContentDbDao(String tableName) {
        if (!this.mContentDbDaoMap.containsKey(tableName)) {
            throw new RuntimeException("unregistered database table:" + tableName + " in " + getClass().getSimpleName());
        }
        return this.mContentDbDaoMap.get(tableName);
    }
}
