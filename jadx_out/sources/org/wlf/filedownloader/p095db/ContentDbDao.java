package org.wlf.filedownloader.p095db;

import android.content.ContentValues;
import android.database.Cursor;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentDbDao extends DatabaseCallback {
    int delete(String str, String[] strArr);

    String getTableIdFieldName();

    String getTableName();

    long insert(ContentValues contentValues);

    Cursor query(String[] strArr, String str, String[] strArr2, String str2);

    int update(ContentValues contentValues, String str, String[] strArr);
}
