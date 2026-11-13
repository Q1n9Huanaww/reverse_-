package org.litepal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.TableModel;

/* JADX INFO: loaded from: classes.dex */
public class DBUtility {
    private static final String KEYWORDS_COLUMN_SUFFIX = "_lpcolumn";
    private static final String REG_COLLECTION = "\\s+(not\\s+)?(in)\\s*\\(";
    private static final String REG_FUZZY = "\\s+(not\\s+)?(like|between)\\s+";
    private static final String REG_OPERATOR = "\\s*(=|!=|<>|<|>)";
    private static final String SQLITE_KEYWORDS = ",abort,add,after,all,alter,and,as,asc,autoincrement,before,begin,between,by,cascade,check,collate,column,commit,conflict,constraint,create,cross,database,deferrable,deferred,delete,desc,distinct,drop,each,end,escape,except,exclusive,exists,foreign,from,glob,group,having,in,index,inner,insert,intersect,into,is,isnull,join,like,limit,match,natural,not,notnull,null,of,offset,on,or,order,outer,plan,pragma,primary,query,raise,references,regexp,reindex,release,rename,replace,restrict,right,rollback,row,savepoint,select,set,table,temp,temporary,then,to,transaction,trigger,union,unique,update,using,vacuum,values,view,virtual,when,where,";
    private static final String TAG = "DBUtility";

    private DBUtility() {
    }

    public static String getTableNameByClassName(String str) {
        if (TextUtils.isEmpty(str) || '.' == str.charAt(str.length() - 1)) {
            return null;
        }
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static List<String> getTableNameListByClassNameList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Iterator<String> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(getTableNameByClassName(it2.next()));
            }
        }
        return arrayList;
    }

    public static String getTableNameByForeignColumn(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.US).endsWith("_id")) {
            return null;
        }
        return str.substring(0, str.length() - "_id".length());
    }

    public static String getIntermediateTableName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.toLowerCase(Locale.US).compareTo(str2.toLowerCase(Locale.US)) <= 0) {
            return str + "_" + str2;
        }
        return str2 + "_" + str;
    }

    public static String getGenericTableName(String str, String str2) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_" + str2);
    }

    public static String getGenericValueIdColumnName(String str) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_id");
    }

    public static String getM2MSelfRefColumnName(Field field) {
        return BaseUtility.changeCase(field.getName() + "_id");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v3, types: [android.database.Cursor] */
    public static boolean isIntermediateTable(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        ?? Query;
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        ?? MoveToFirst = 0;
        MoveToFirst = 0;
        try {
            try {
                Query = sQLiteDatabase.query(Const.TableSchema.TABLE_NAME, null, null, null, null, null, null);
                try {
                    MoveToFirst = Query.moveToFirst();
                    if (MoveToFirst != 0) {
                        do {
                            if (str.equalsIgnoreCase(Query.getString(Query.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME)))) {
                                MoveToFirst = 1;
                                if (Query.getInt(Query.getColumnIndexOrThrow("type")) != 1) {
                                    break;
                                }
                                if (Query != 0) {
                                    Query.close();
                                }
                                return true;
                            }
                            MoveToFirst = Query.moveToNext();
                        } while (MoveToFirst != 0);
                    }
                    if (Query != 0) {
                        Query.close();
                    }
                } catch (Exception e) {
                    e = e;
                    MoveToFirst = Query;
                    ThrowableExtension.printStackTrace(e);
                    if (MoveToFirst != 0) {
                        MoveToFirst.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (Query != 0) {
                        Query.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Query = MoveToFirst;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return false;
    }

    public static boolean isGenericTable(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Cursor cursorQuery;
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query(Const.TableSchema.TABLE_NAME, null, null, null, null, null, null);
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            if (str.equalsIgnoreCase(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME)))) {
                                if (cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("type")) != 2) {
                                    break;
                                }
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                return true;
                            }
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery == null) {
                        return false;
                    }
                    cursorQuery.close();
                    return false;
                } catch (Exception e) {
                    e = e;
                    cursor = cursorQuery;
                    ThrowableExtension.printStackTrace(e);
                    if (cursor == null) {
                        return false;
                    }
                    cursor.close();
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = cursor;
        }
    }

    public static boolean isTableExists(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(sQLiteDatabase), str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean isColumnExists(String str, String str2, SQLiteDatabase sQLiteDatabase) throws Throwable {
        boolean z = false;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery("pragma table_info(" + str2 + ")", null);
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        do {
                            if (str.equalsIgnoreCase(cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME)))) {
                                z = true;
                                break;
                            }
                        } while (cursorRawQuery.moveToNext());
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception e) {
                    e = e;
                    cursor = cursorRawQuery;
                    ThrowableExtension.printStackTrace(e);
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorRawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return z;
    }

    public static List<String> findAllTableNames(SQLiteDatabase sQLiteDatabase) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from sqlite_master where type = ?", new String[]{"table"});
                try {
                    if (cursorRawQuery.moveToFirst()) {
                        do {
                            String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("tbl_name"));
                            if (!arrayList.contains(string)) {
                                arrayList.add(string);
                            }
                        } while (cursorRawQuery.moveToNext());
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return arrayList;
                } catch (Exception e) {
                    e = e;
                    cursor = cursorRawQuery;
                    ThrowableExtension.printStackTrace(e);
                    throw new DatabaseGenerateException(e.getMessage());
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorRawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static TableModel findPragmaTableInfo(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Cursor cursorRawQuery;
        if (isTableExists(str, sQLiteDatabase)) {
            List<String> listFindUniqueColumns = findUniqueColumns(str, sQLiteDatabase);
            TableModel tableModel = new TableModel();
            tableModel.setTableName(str);
            Cursor cursor = null;
            try {
                try {
                    cursorRawQuery = sQLiteDatabase.rawQuery("pragma table_info(" + str + ")", null);
                    try {
                        if (cursorRawQuery.moveToFirst()) {
                            do {
                                ColumnModel columnModel = new ColumnModel();
                                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME));
                                String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("type"));
                                boolean z = true;
                                if (cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow("notnull")) == 1) {
                                    z = false;
                                }
                                boolean zContains = listFindUniqueColumns.contains(string);
                                String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("dflt_value"));
                                columnModel.setColumnName(string);
                                columnModel.setColumnType(string2);
                                columnModel.setNullable(z);
                                columnModel.setUnique(zContains);
                                columnModel.setDefaultValue(string3 != null ? string3.replace("'", "") : "");
                                tableModel.addColumnModel(columnModel);
                            } while (cursorRawQuery.moveToNext());
                        }
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return tableModel;
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorRawQuery;
                        ThrowableExtension.printStackTrace(e);
                        throw new DatabaseGenerateException(e.getMessage());
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
                cursorRawQuery = cursor;
            }
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST_WHEN_EXECUTING + str);
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ae  */
    public static List<String> findUniqueColumns(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
        Cursor cursorRawQuery;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("pragma index_list(" + str + ")", null);
            try {
                if (cursorRawQuery.moveToFirst()) {
                    cursor = null;
                    do {
                        try {
                            if (cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow("unique")) == 1) {
                                Cursor cursorRawQuery2 = sQLiteDatabase.rawQuery("pragma index_info(" + cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME)) + ")", null);
                                try {
                                    if (cursorRawQuery2.moveToFirst()) {
                                        arrayList.add(cursorRawQuery2.getString(cursorRawQuery2.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME)));
                                    }
                                    cursor = cursorRawQuery2;
                                } catch (Exception e) {
                                    e = e;
                                    cursor2 = cursorRawQuery;
                                    cursor = cursorRawQuery2;
                                    try {
                                        ThrowableExtension.printStackTrace(e);
                                        throw new DatabaseGenerateException(e.getMessage());
                                    } catch (Throwable th) {
                                        th = th;
                                        cursorRawQuery = cursor2;
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                        }
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = cursorRawQuery2;
                                    if (cursorRawQuery != null) {
                                        cursorRawQuery.close();
                                    }
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor2 = cursorRawQuery;
                            ThrowableExtension.printStackTrace(e);
                            throw new DatabaseGenerateException(e.getMessage());
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } while (cursorRawQuery.moveToNext());
                    cursor2 = cursor;
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                return arrayList;
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
            }
        } catch (Exception e4) {
            e = e4;
            cursor = null;
        } catch (Throwable th5) {
            th = th5;
            cursorRawQuery = null;
            cursor = null;
        }
    }

    public static boolean isFieldNameConflictWithSQLiteKeywords(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(",");
        sb.append(str.toLowerCase(Locale.US));
        sb.append(",");
        return SQLITE_KEYWORDS.contains(sb.toString());
    }

    public static String convertToValidColumnName(String str) {
        if (!isFieldNameConflictWithSQLiteKeywords(str)) {
            return str;
        }
        return str + KEYWORDS_COLUMN_SUFFIX;
    }

    public static String convertWhereClauseToColumnName(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("(\\w+\\s*(=|!=|<>|<|>)|\\w+\\s+(not\\s+)?(like|between)\\s+|\\w+\\s+(not\\s+)?(in)\\s*\\()").matcher(str);
                while (matcher.find()) {
                    String strGroup = matcher.group();
                    String strReplaceAll = strGroup.replaceAll("(\\s*(=|!=|<>|<|>)|\\s+(not\\s+)?(like|between)\\s+|\\s+(not\\s+)?(in)\\s*\\()", "");
                    String strReplace = strGroup.replace(strReplaceAll, "");
                    matcher.appendReplacement(stringBuffer, convertToValidColumnName(strReplaceAll) + strReplace);
                }
                matcher.appendTail(stringBuffer);
                return stringBuffer.toString();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return str;
    }

    public static String[] convertSelectClauseToValidNames(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = convertToValidColumnName(strArr[i]);
        }
        return strArr2;
    }

    public static String convertOrderByClauseToValidName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        if (lowerCase.contains(",")) {
            String[] strArrSplit = lowerCase.split(",");
            StringBuilder sb = new StringBuilder();
            int length = strArrSplit.length;
            int i = 0;
            boolean z = false;
            while (i < length) {
                String str2 = strArrSplit[i];
                if (z) {
                    sb.append(",");
                }
                sb.append(convertOrderByItem(str2));
                i++;
                z = true;
            }
            return sb.toString();
        }
        return convertOrderByItem(lowerCase);
    }

    private static String convertOrderByItem(String str) {
        String str2;
        if (str.endsWith("asc")) {
            str = str.replace("asc", "").trim();
            str2 = " asc";
        } else if (str.endsWith("desc")) {
            str = str.replace("desc", "").trim();
            str2 = " desc";
        } else {
            str2 = "";
        }
        return convertToValidColumnName(str) + str2;
    }
}
