package com.lidroid.xutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p076db.sqlite.CursorUtils;
import com.lidroid.xutils.p076db.sqlite.DbModelSelector;
import com.lidroid.xutils.p076db.sqlite.Selector;
import com.lidroid.xutils.p076db.sqlite.SqlInfo;
import com.lidroid.xutils.p076db.sqlite.SqlInfoBuilder;
import com.lidroid.xutils.p076db.sqlite.WhereBuilder;
import com.lidroid.xutils.p076db.table.C1649Id;
import com.lidroid.xutils.p076db.table.DbModel;
import com.lidroid.xutils.p076db.table.Table;
import com.lidroid.xutils.p076db.table.TableUtils;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import com.p024ds.daisi.constant.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class DbUtils {
    private static HashMap<String, DbUtils> daoMap = new HashMap<>();
    private DaoConfig daoConfig;
    private SQLiteDatabase database;
    private boolean debug = false;
    private boolean allowTransaction = false;
    private Lock writeLock = new ReentrantLock();
    private volatile boolean writeLocked = false;
    private final FindTempCache findTempCache = new FindTempCache(this, null);

    public interface DbUpgradeListener {
        void onUpgrade(DbUtils dbUtils, int i, int i2);
    }

    private DbUtils(DaoConfig daoConfig) {
        if (daoConfig == null) {
            throw new IllegalArgumentException("daoConfig may not be null");
        }
        this.database = createDatabase(daoConfig);
        this.daoConfig = daoConfig;
    }

    private static synchronized DbUtils getInstance(DaoConfig daoConfig) {
        DbUtils dbUtils;
        dbUtils = daoMap.get(daoConfig.getDbName());
        if (dbUtils == null) {
            dbUtils = new DbUtils(daoConfig);
            daoMap.put(daoConfig.getDbName(), dbUtils);
        } else {
            dbUtils.daoConfig = daoConfig;
        }
        SQLiteDatabase sQLiteDatabase = dbUtils.database;
        int version = sQLiteDatabase.getVersion();
        int dbVersion = daoConfig.getDbVersion();
        if (version != dbVersion) {
            if (version != 0) {
                DbUpgradeListener dbUpgradeListener = daoConfig.getDbUpgradeListener();
                if (dbUpgradeListener != null) {
                    dbUpgradeListener.onUpgrade(dbUtils, version, dbVersion);
                } else {
                    try {
                        dbUtils.dropDb();
                    } catch (DbException e) {
                        LogUtils.m3421e(e.getMessage(), e);
                    }
                }
            }
            sQLiteDatabase.setVersion(dbVersion);
        }
        return dbUtils;
    }

    public static DbUtils create(Context context) {
        return getInstance(new DaoConfig(context));
    }

    public static DbUtils create(Context context, String str) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbDir(str);
        daoConfig.setDbName(str2);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbDir(str);
        daoConfig.setDbName(str2);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(DaoConfig daoConfig) {
        return getInstance(daoConfig);
    }

    public DbUtils configDebug(boolean z) {
        this.debug = z;
        return this;
    }

    public DbUtils configAllowTransaction(boolean z) {
        this.allowTransaction = z;
        return this;
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public DaoConfig getDaoConfig() {
        return this.daoConfig;
    }

    public void saveOrUpdate(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            saveOrUpdateWithoutTransaction(obj);
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveOrUpdateAll(List<?> list) throws DbException {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            beginTransaction();
            createTableIfNotExist(list.get(0).getClass());
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                saveOrUpdateWithoutTransaction(it2.next());
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replace(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, obj));
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replaceAll(List<?> list) throws DbException {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            beginTransaction();
            createTableIfNotExist(list.get(0).getClass());
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, it2.next()));
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void save(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveAll(List<?> list) throws DbException {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            beginTransaction();
            createTableIfNotExist(list.get(0).getClass());
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, it2.next()));
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public boolean saveBindingId(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            boolean zSaveBindingIdWithoutTransaction = saveBindingIdWithoutTransaction(obj);
            setTransactionSuccessful();
            return zSaveBindingIdWithoutTransaction;
        } finally {
            endTransaction();
        }
    }

    public void saveBindingIdAll(List<?> list) throws DbException {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            beginTransaction();
            createTableIfNotExist(list.get(0).getClass());
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                if (!saveBindingIdWithoutTransaction(it2.next())) {
                    throw new DbException("saveBindingId error, transaction will not commit!");
                }
            }
            setTransactionSuccessful();
            endTransaction();
        } catch (Throwable th) {
            endTransaction();
            throw th;
        }
    }

    public void deleteById(Class<?> cls, Object obj) throws DbException {
        if (tableIsExist(cls)) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, cls, obj));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void delete(Object obj) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, obj));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void delete(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        if (tableIsExist(cls)) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, cls, whereBuilder));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void deleteAll(List<?> list) throws DbException {
        if (list == null || list.size() == 0 || !tableIsExist(list.get(0).getClass())) {
            return;
        }
        try {
            beginTransaction();
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, it2.next()));
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void deleteAll(Class<?> cls) throws DbException {
        delete(cls, null);
    }

    public void update(Object obj, String... strArr) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, strArr));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void update(Object obj, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, whereBuilder, strArr));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void updateAll(List<?> list, String... strArr) throws DbException {
        if (list == null || list.size() == 0 || !tableIsExist(list.get(0).getClass())) {
            return;
        }
        try {
            beginTransaction();
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, it2.next(), strArr));
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void updateAll(List<?> list, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (list == null || list.size() == 0 || !tableIsExist(list.get(0).getClass())) {
            return;
        }
        try {
            beginTransaction();
            Iterator<?> it2 = list.iterator();
            while (it2.hasNext()) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, it2.next(), whereBuilder, strArr));
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public <T> T findById(Class<T> cls, Object obj) throws DbException {
        if (!tableIsExist(cls)) {
            return null;
        }
        String string = Selector.from(cls).where(Table.get(this, cls).f3592id.getColumnName(), "=", obj).limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(string);
        if (t != null) {
            return t;
        }
        Cursor cursorExecQuery = execQuery(string);
        try {
            if (cursorExecQuery != null) {
                try {
                    if (cursorExecQuery.moveToNext()) {
                        T t2 = (T) CursorUtils.getEntity(this, cursorExecQuery, cls, seq);
                        this.findTempCache.put(string, t2);
                        IOUtils.closeQuietly(cursorExecQuery);
                        return t2;
                    }
                    IOUtils.closeQuietly(cursorExecQuery);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            }
            return null;
        } catch (Throwable th2) {
            IOUtils.closeQuietly(cursorExecQuery);
            throw th2;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public <T> T findFirst(Selector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String string = selector.limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(string);
        if (t != null) {
            return t;
        }
        Cursor cursorExecQuery = execQuery(string);
        if (cursorExecQuery != null) {
            try {
                try {
                    if (cursorExecQuery.moveToNext()) {
                        T t2 = (T) CursorUtils.getEntity(this, cursorExecQuery, selector.getEntityType(), seq);
                        this.findTempCache.put(string, t2);
                        IOUtils.closeQuietly(cursorExecQuery);
                        return t2;
                    }
                    IOUtils.closeQuietly(cursorExecQuery);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly(cursorExecQuery);
                throw th2;
            }
            IOUtils.closeQuietly(cursorExecQuery);
            throw th2;
        }
        return null;
    }

    public <T> T findFirst(Class<T> cls) throws DbException {
        return (T) findFirst(Selector.from(cls));
    }

    public <T> List<T> findAll(Selector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String string = selector.toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        Object obj = this.findTempCache.get(string);
        if (obj != null) {
            return (List) obj;
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorExecQuery = execQuery(string);
        if (cursorExecQuery != null) {
            while (cursorExecQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(CursorUtils.getEntity(this, cursorExecQuery, selector.getEntityType(), seq));
                    } catch (Throwable th) {
                        throw new DbException(th);
                    }
                } catch (Throwable th2) {
                    IOUtils.closeQuietly(cursorExecQuery);
                    throw th2;
                }
            }
            this.findTempCache.put(string, arrayList);
            IOUtils.closeQuietly(cursorExecQuery);
        }
        return arrayList;
    }

    public <T> List<T> findAll(Class<T> cls) throws DbException {
        return findAll(Selector.from(cls));
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0018 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        Cursor cursorExecQuery = execQuery(sqlInfo);
        if (cursorExecQuery == null) {
            return null;
        }
        try {
            if (!cursorExecQuery.moveToNext()) {
                IOUtils.closeQuietly(cursorExecQuery);
                return null;
            }
            DbModel dbModel = CursorUtils.getDbModel(cursorExecQuery);
            IOUtils.closeQuietly(cursorExecQuery);
            return dbModel;
        } catch (Throwable th) {
            throw new DbException(th);
        }
        IOUtils.closeQuietly(cursorExecQuery);
        throw th;
    }

    public DbModel findDbModelFirst(DbModelSelector dbModelSelector) throws DbException {
        Cursor cursorExecQuery;
        if (tableIsExist(dbModelSelector.getEntityType()) && (cursorExecQuery = execQuery(dbModelSelector.limit(1).toString())) != null) {
            try {
                try {
                    if (cursorExecQuery.moveToNext()) {
                        DbModel dbModel = CursorUtils.getDbModel(cursorExecQuery);
                        IOUtils.closeQuietly(cursorExecQuery);
                        return dbModel;
                    }
                    IOUtils.closeQuietly(cursorExecQuery);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly(cursorExecQuery);
                throw th2;
            }
            IOUtils.closeQuietly(cursorExecQuery);
            throw th2;
        }
        return null;
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        ArrayList arrayList = new ArrayList();
        Cursor cursorExecQuery = execQuery(sqlInfo);
        if (cursorExecQuery != null) {
            while (cursorExecQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(CursorUtils.getDbModel(cursorExecQuery));
                    } catch (Throwable th) {
                        throw new DbException(th);
                    }
                } catch (Throwable th2) {
                    IOUtils.closeQuietly(cursorExecQuery);
                    throw th2;
                }
            }
            IOUtils.closeQuietly(cursorExecQuery);
        }
        return arrayList;
    }

    public List<DbModel> findDbModelAll(DbModelSelector dbModelSelector) throws DbException {
        if (!tableIsExist(dbModelSelector.getEntityType())) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorExecQuery = execQuery(dbModelSelector.toString());
        if (cursorExecQuery != null) {
            while (cursorExecQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(CursorUtils.getDbModel(cursorExecQuery));
                    } catch (Throwable th) {
                        throw new DbException(th);
                    }
                } catch (Throwable th2) {
                    IOUtils.closeQuietly(cursorExecQuery);
                    throw th2;
                }
            }
            IOUtils.closeQuietly(cursorExecQuery);
        }
        return arrayList;
    }

    public long count(Selector selector) throws DbException {
        Class<?> entityType = selector.getEntityType();
        if (!tableIsExist(entityType)) {
            return 0L;
        }
        return findDbModelFirst(selector.select("count(" + Table.get(this, entityType).f3592id.getColumnName() + ") as count")).getLong(Constants.SCRIPT_USE_COUNT);
    }

    public long count(Class<?> cls) throws DbException {
        return count(Selector.from(cls));
    }

    public static class DaoConfig {
        private Context context;
        private String dbDir;
        private DbUpgradeListener dbUpgradeListener;
        private String dbName = "xUtils.db";
        private int dbVersion = 1;

        public DaoConfig(Context context) {
            this.context = context.getApplicationContext();
        }

        public Context getContext() {
            return this.context;
        }

        public String getDbName() {
            return this.dbName;
        }

        public void setDbName(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.dbName = str;
        }

        public int getDbVersion() {
            return this.dbVersion;
        }

        public void setDbVersion(int i) {
            this.dbVersion = i;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return this.dbUpgradeListener;
        }

        public void setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
        }

        public String getDbDir() {
            return this.dbDir;
        }

        public void setDbDir(String str) {
            this.dbDir = str;
        }
    }

    private SQLiteDatabase createDatabase(DaoConfig daoConfig) {
        String dbDir = daoConfig.getDbDir();
        if (!TextUtils.isEmpty(dbDir)) {
            File file = new File(dbDir);
            if (file.exists() || file.mkdirs()) {
                return SQLiteDatabase.openOrCreateDatabase(new File(dbDir, daoConfig.getDbName()), (SQLiteDatabase.CursorFactory) null);
            }
            return null;
        }
        return daoConfig.getContext().openOrCreateDatabase(daoConfig.getDbName(), 0, null);
    }

    private void saveOrUpdateWithoutTransaction(Object obj) throws DbException {
        C1649Id c1649Id = Table.get(this, obj.getClass()).f3592id;
        if (c1649Id.isAutoIncrement()) {
            if (c1649Id.getColumnValue(obj) != null) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, new String[0]));
                return;
            } else {
                saveBindingIdWithoutTransaction(obj);
                return;
            }
        }
        execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, obj));
    }

    private boolean saveBindingIdWithoutTransaction(Object obj) throws DbException {
        Table table = Table.get(this, obj.getClass());
        C1649Id c1649Id = table.f3592id;
        if (c1649Id.isAutoIncrement()) {
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
            long lastAutoIncrementId = getLastAutoIncrementId(table.tableName);
            if (lastAutoIncrementId == -1) {
                return false;
            }
            c1649Id.setAutoIncrementId(obj, lastAutoIncrementId);
            return true;
        }
        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
        return true;
    }

    private long getLastAutoIncrementId(String str) throws DbException {
        Cursor cursorExecQuery = execQuery("SELECT seq FROM sqlite_sequence WHERE name='" + str + "'");
        long j = -1;
        if (cursorExecQuery != null) {
            try {
                try {
                    j = cursorExecQuery.moveToNext() ? cursorExecQuery.getLong(0) : -1L;
                    IOUtils.closeQuietly(cursorExecQuery);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly(cursorExecQuery);
                throw th2;
            }
        }
        return j;
    }

    public void createTableIfNotExist(Class<?> cls) throws DbException {
        if (tableIsExist(cls)) {
            return;
        }
        execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo(this, cls));
        String execAfterTableCreated = TableUtils.getExecAfterTableCreated(cls);
        if (TextUtils.isEmpty(execAfterTableCreated)) {
            return;
        }
        execNonQuery(execAfterTableCreated);
    }

    public boolean tableIsExist(Class<?> cls) throws DbException {
        Table table = Table.get(this, cls);
        if (table.isCheckedDatabase()) {
            return true;
        }
        Cursor cursorExecQuery = execQuery("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + table.tableName + "'");
        if (cursorExecQuery != null) {
            try {
                try {
                    if (cursorExecQuery.moveToNext() && cursorExecQuery.getInt(0) > 0) {
                        table.setCheckedDatabase(true);
                        IOUtils.closeQuietly(cursorExecQuery);
                        return true;
                    }
                    IOUtils.closeQuietly(cursorExecQuery);
                } catch (Throwable th) {
                    throw new DbException(th);
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly(cursorExecQuery);
                throw th2;
            }
            IOUtils.closeQuietly(cursorExecQuery);
            throw th2;
        }
        return false;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0035 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void dropDb() throws DbException {
        Cursor cursorExecQuery = execQuery("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (cursorExecQuery != null) {
            while (cursorExecQuery.moveToNext()) {
                try {
                    try {
                        String string = cursorExecQuery.getString(0);
                        execNonQuery("DROP TABLE " + string);
                        Table.remove(this, string);
                    } catch (Throwable th) {
                        LogUtils.m3421e(th.getMessage(), th);
                    }
                } catch (Throwable th2) {
                    throw new DbException(th2);
                }
            }
            IOUtils.closeQuietly(cursorExecQuery);
        }
    }

    public void dropTable(Class<?> cls) throws DbException {
        if (tableIsExist(cls)) {
            execNonQuery("DROP TABLE " + TableUtils.getTableName(cls));
            Table.remove(this, cls);
        }
    }

    public void close() {
        String dbName = this.daoConfig.getDbName();
        if (daoMap.containsKey(dbName)) {
            daoMap.remove(dbName);
            this.database.close();
        }
    }

    private void debugSql(String str) {
        if (this.debug) {
            LogUtils.m3418d(str);
        }
    }

    private void beginTransaction() {
        if (this.allowTransaction) {
            this.database.beginTransaction();
        } else {
            this.writeLock.lock();
            this.writeLocked = true;
        }
    }

    private void setTransactionSuccessful() {
        if (this.allowTransaction) {
            this.database.setTransactionSuccessful();
        }
    }

    private void endTransaction() {
        if (this.allowTransaction) {
            this.database.endTransaction();
        }
        if (this.writeLocked) {
            this.writeLock.unlock();
            this.writeLocked = false;
        }
    }

    public void execNonQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            if (sqlInfo.getBindArgs() != null) {
                this.database.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
            } else {
                this.database.execSQL(sqlInfo.getSql());
            }
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public void execNonQuery(String str) throws DbException {
        debugSql(str);
        try {
            this.database.execSQL(str);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            return this.database.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor execQuery(String str) throws DbException {
        debugSql(str);
        try {
            return this.database.rawQuery(str, null);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    private class FindTempCache {
        private final ConcurrentHashMap<String, Object> cache;
        private long seq;

        private FindTempCache() {
            this.cache = new ConcurrentHashMap<>();
            this.seq = 0L;
        }

        /* synthetic */ FindTempCache(DbUtils dbUtils, FindTempCache findTempCache) {
            this();
        }

        public void put(String str, Object obj) {
            if (str == null || obj == null) {
                return;
            }
            this.cache.put(str, obj);
        }

        public Object get(String str) {
            return this.cache.get(str);
        }

        public void setSeq(long j) {
            if (this.seq != j) {
                this.cache.clear();
                this.seq = j;
            }
        }
    }
}
