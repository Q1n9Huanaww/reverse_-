package com.lidroid.xutils.p076db.table;

import android.database.Cursor;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p076db.converter.ColumnConverter;
import com.lidroid.xutils.p076db.converter.ColumnConverterFactory;
import com.lidroid.xutils.p076db.sqlite.ColumnDbType;
import com.lidroid.xutils.p076db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Foreign extends Column {
    private final ColumnConverter foreignColumnConverter;
    private final String foreignColumnName;

    @Override // com.lidroid.xutils.p076db.table.Column
    public Object getDefaultValue() {
        return null;
    }

    Foreign(Class<?> cls, Field field) {
        super(cls, field);
        this.foreignColumnName = ColumnUtils.getForeignColumnNameByField(field);
        this.foreignColumnConverter = ColumnConverterFactory.getColumnConverter(TableUtils.getColumnOrId(getForeignEntityType(), this.foreignColumnName).columnField.getType());
    }

    public String getForeignColumnName() {
        return this.foreignColumnName;
    }

    public Class<?> getForeignEntityType() {
        return ColumnUtils.getForeignEntityType(this);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.lidroid.xutils.p076db.table.Column
    public void setValue2Entity(Object obj, Cursor cursor, int i) {
        Object firstFromDb;
        Object fieldValue = this.foreignColumnConverter.getFieldValue(cursor, i);
        if (fieldValue == null) {
            return;
        }
        ForeignLazyLoader foreignLazyLoader = null;
        Class<?> type = this.columnField.getType();
        if (type.equals(ForeignLazyLoader.class)) {
            foreignLazyLoader = new ForeignLazyLoader(this, fieldValue);
        } else {
            if (type.equals(List.class)) {
                try {
                    firstFromDb = new ForeignLazyLoader(this, fieldValue).getAllFromDb();
                } catch (DbException e) {
                    LogUtils.m3421e(e.getMessage(), e);
                    firstFromDb = foreignLazyLoader;
                }
            } else {
                try {
                    firstFromDb = new ForeignLazyLoader(this, fieldValue).getFirstFromDb();
                } catch (DbException e2) {
                    LogUtils.m3421e(e2.getMessage(), e2);
                    firstFromDb = foreignLazyLoader;
                }
            }
            if (this.setMethod != null) {
                try {
                    this.setMethod.invoke(obj, firstFromDb);
                } catch (Throwable th) {
                    LogUtils.m3421e(th.getMessage(), th);
                    return;
                }
            }
            try {
                this.columnField.setAccessible(true);
                this.columnField.set(obj, firstFromDb);
            } catch (Throwable th2) {
                LogUtils.m3421e(th2.getMessage(), th2);
                return;
            }
        }
        firstFromDb = foreignLazyLoader;
        if (this.setMethod != null) {
            this.setMethod.invoke(obj, firstFromDb);
        } else {
            this.columnField.setAccessible(true);
            this.columnField.set(obj, firstFromDb);
        }
    }

    @Override // com.lidroid.xutils.p076db.table.Column
    public Object getColumnValue(Object obj) {
        Object fieldValue = getFieldValue(obj);
        Object obj2 = null;
        if (fieldValue == null) {
            return null;
        }
        Class<?> type = this.columnField.getType();
        if (type.equals(ForeignLazyLoader.class)) {
            return ((ForeignLazyLoader) fieldValue).getColumnValue();
        }
        if (type.equals(List.class)) {
            try {
                List list = (List) fieldValue;
                if (list.size() <= 0) {
                    return null;
                }
                Column columnOrId = TableUtils.getColumnOrId(ColumnUtils.getForeignEntityType(this), this.foreignColumnName);
                Object columnValue = columnOrId.getColumnValue(list.get(0));
                try {
                    Table table = getTable();
                    if (table != null && (columnOrId instanceof C1649Id)) {
                        for (Object obj3 : list) {
                            if (columnOrId.getColumnValue(obj3) == null) {
                                table.f3591db.saveOrUpdate(obj3);
                            }
                        }
                    }
                    return columnOrId.getColumnValue(list.get(0));
                } catch (Throwable th) {
                    th = th;
                    obj2 = columnValue;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            LogUtils.m3421e(th.getMessage(), th);
            return obj2;
        }
        try {
            Column columnOrId2 = TableUtils.getColumnOrId(type, this.foreignColumnName);
            Object columnValue2 = columnOrId2.getColumnValue(fieldValue);
            try {
                Table table2 = getTable();
                if (table2 != null && columnValue2 == null && (columnOrId2 instanceof C1649Id)) {
                    table2.f3591db.saveOrUpdate(fieldValue);
                }
                return columnOrId2.getColumnValue(fieldValue);
            } catch (Throwable th3) {
                th = th3;
                obj2 = columnValue2;
                LogUtils.m3421e(th.getMessage(), th);
                return obj2;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    @Override // com.lidroid.xutils.p076db.table.Column
    public ColumnDbType getColumnDbType() {
        return this.foreignColumnConverter.getColumnDbType();
    }
}
