package com.lidroid.xutils.p076db.sqlite;

import android.database.Cursor;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.p076db.table.C1649Id;
import com.lidroid.xutils.p076db.table.Column;
import com.lidroid.xutils.p076db.table.DbModel;
import com.lidroid.xutils.p076db.table.Finder;
import com.lidroid.xutils.p076db.table.Table;
import com.lidroid.xutils.util.LogUtils;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CursorUtils {
    public static <T> T getEntity(DbUtils dbUtils, Cursor cursor, Class<T> cls, long j) {
        if (dbUtils == null || cursor == null) {
            return null;
        }
        EntityTempCache.setSeq(j);
        try {
            Table table = Table.get(dbUtils, cls);
            C1649Id c1649Id = table.f3592id;
            String columnName = c1649Id.getColumnName();
            int index = c1649Id.getIndex();
            if (index < 0) {
                index = cursor.getColumnIndex(columnName);
            }
            Object fieldValue = c1649Id.getColumnConverter().getFieldValue(cursor, index);
            T t = (T) EntityTempCache.get(cls, fieldValue);
            if (t != null) {
                return t;
            }
            T tNewInstance = cls.newInstance();
            c1649Id.setValue2Entity(tNewInstance, cursor, index);
            EntityTempCache.put(cls, fieldValue, tNewInstance);
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                Column column = table.columnMap.get(cursor.getColumnName(i));
                if (column != null) {
                    column.setValue2Entity(tNewInstance, cursor, i);
                }
            }
            Iterator<Finder> it2 = table.finderMap.values().iterator();
            while (it2.hasNext()) {
                it2.next().setValue2Entity(tNewInstance, null, 0);
            }
            return tNewInstance;
        } catch (Throwable th) {
            LogUtils.m3421e(th.getMessage(), th);
            return null;
        }
    }

    public static DbModel getDbModel(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        DbModel dbModel = new DbModel();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            dbModel.add(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }

    public static class FindCacheSequence {
        private static long seq;
        private static final String FOREIGN_LAZY_LOADER_CLASS_NAME = ForeignLazyLoader.class.getName();
        private static final String FINDER_LAZY_LOADER_CLASS_NAME = FinderLazyLoader.class.getName();

        private FindCacheSequence() {
        }

        public static long getSeq() {
            String className = Thread.currentThread().getStackTrace()[4].getClassName();
            if (!className.equals(FOREIGN_LAZY_LOADER_CLASS_NAME) && !className.equals(FINDER_LAZY_LOADER_CLASS_NAME)) {
                seq++;
            }
            return seq;
        }
    }

    private static class EntityTempCache {
        private static final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
        private static long seq;

        private EntityTempCache() {
        }

        public static <T> void put(Class<T> cls, Object obj, Object obj2) {
            cache.put(String.valueOf(cls.getName()) + "#" + obj, obj2);
        }

        public static <T> T get(Class<T> cls, Object obj) {
            return (T) cache.get(String.valueOf(cls.getName()) + "#" + obj);
        }

        public static void setSeq(long j) {
            if (seq != j) {
                cache.clear();
                seq = j;
            }
        }
    }
}
