package com.p024ds.daisi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.share.bean.NotifyMsgBean;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.Constants;
import com.umeng.analytics.pro.C1708b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MsgDatabaseOpera {
    private static final int DB_VERSION = 1;
    private MsgDatabaseHelper dbHelper = new MsgDatabaseHelper(AppContext.getInstance(), "MESSAGE_PAY.db", null, 1);

    public void deleteAll() {
        this.dbHelper.getWritableDatabase().delete(Constants.DATABASE_TABLE_MSG, null, null);
    }

    public void insertData(List<NotifyMsgBean> list) {
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Logger.m3442e("长度:" + list.size(), new Object[0]);
        for (int size = list.size() + (-1); size >= 0; size--) {
            NotifyMsgBean notifyMsgBean = list.get(size);
            contentValues.clear();
            contentValues.put("number", notifyMsgBean.f1679ID);
            contentValues.put("title", notifyMsgBean.NoticeTitle);
            contentValues.put("time", notifyMsgBean.NoticeTime);
            contentValues.put(C1708b.f3711W, notifyMsgBean.NoticeContent);
            contentValues.put("description", notifyMsgBean.NoticeDescription);
            contentValues.put("type", notifyMsgBean.DescriptionType);
            writableDatabase.insert(Constants.DATABASE_TABLE_MSG, null, contentValues);
        }
        writableDatabase.close();
    }

    public void queryAll(final Handler handler) {
        ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.database.MsgDatabaseOpera.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                SQLiteDatabase writableDatabase = MsgDatabaseOpera.this.dbHelper.getWritableDatabase();
                Cursor cursorQuery = writableDatabase.query(Constants.DATABASE_TABLE_MSG, null, null, null, null, null, "id desc");
                if (cursorQuery.moveToFirst()) {
                    do {
                        NotifyMsgBean notifyMsgBean = new NotifyMsgBean();
                        notifyMsgBean.NoticeContent = cursorQuery.getString(cursorQuery.getColumnIndex(C1708b.f3711W));
                        notifyMsgBean.DescriptionType = cursorQuery.getString(cursorQuery.getColumnIndex("type"));
                        notifyMsgBean.NoticeDescription = cursorQuery.getString(cursorQuery.getColumnIndex("description"));
                        notifyMsgBean.f1679ID = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
                        notifyMsgBean.NoticeTitle = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
                        notifyMsgBean.NoticeTime = cursorQuery.getString(cursorQuery.getColumnIndex("time"));
                        arrayList.add(notifyMsgBean);
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
                writableDatabase.close();
                Message messageObtainMessage = handler.obtainMessage();
                messageObtainMessage.obj = arrayList;
                messageObtainMessage.what = 1005;
                messageObtainMessage.sendToTarget();
            }
        });
    }
}
