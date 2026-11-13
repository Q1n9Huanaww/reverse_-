package org.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.ActivityChooserView;
import android.util.SparseArray;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1799v;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.litepal.LitePalBase;
import org.litepal.Operator;
import org.litepal.annotation.Encrypt;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;
import org.litepal.util.cipher.CipherUtil;

/* JADX INFO: loaded from: classes.dex */
abstract class DataHandler extends LitePalBase {
    public static final String TAG = "DataHandler";
    private List<AssociationsInfo> fkInCurrentModel;
    private List<AssociationsInfo> fkInOtherModel;
    SQLiteDatabase mDatabase;
    private LitePalSupport tempEmptyModel;

    protected boolean shouldGetOrSet(LitePalSupport litePalSupport, Field field) {
        return (litePalSupport == null || field == null) ? false : true;
    }

    DataHandler() {
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:38:? A[SYNTHETIC] */
    protected <T> List<T> query(Class<T> cls, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, List<AssociationsInfo> list) throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        ArrayList arrayList = new ArrayList();
        try {
            List<Field> supportedFields = getSupportedFields(cls.getName());
            List<Field> supportedGenericFields = getSupportedGenericFields(cls.getName());
            cursorQuery = this.mDatabase.query(getTableName(cls), DBUtility.convertSelectClauseToValidNames(getCustomizedColumns(strArr, supportedGenericFields, list)), str, strArr2, str2, str3, str4, str5);
            try {
                try {
                    if (cursorQuery.moveToFirst()) {
                        SparseArray<QueryInfoCache> sparseArray = new SparseArray<>();
                        HashMap map = new HashMap();
                        do {
                            Object objCreateInstanceFromClass = createInstanceFromClass(cls);
                            giveBaseObjIdValue((LitePalSupport) objCreateInstanceFromClass, cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("id")));
                            setValueToModel(objCreateInstanceFromClass, supportedFields, list, cursorQuery, sparseArray);
                            setGenericValueToModel((LitePalSupport) objCreateInstanceFromClass, supportedGenericFields, map);
                            if (list != null) {
                                setAssociatedModel((LitePalSupport) objCreateInstanceFromClass);
                            }
                            arrayList.add(objCreateInstanceFromClass);
                        } while (cursorQuery.moveToNext());
                        sparseArray.clear();
                        map.clear();
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                } catch (Exception e) {
                    e = e;
                    Exception exc = e;
                    throw new LitePalSupportException(exc.getMessage(), exc);
                }
            } catch (Throwable th2) {
                th = th2;
                th = th;
                if (cursorQuery != null) {
                    throw th;
                }
                cursorQuery.close();
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            cursorQuery = null;
            th = th;
            if (cursorQuery != null) {
                throw th;
            }
            cursorQuery.close();
            throw th;
        }
    }

    protected <T> T mathQuery(String str, String[] strArr, String[] strArr2, Class<T> cls) throws Throwable {
        Cursor cursorQuery;
        BaseUtility.checkConditionsCorrect(strArr2);
        Cursor cursor = (T) null;
        try {
            try {
                cursorQuery = this.mDatabase.query(str, strArr, getWhereClause(strArr2), getWhereArgs(strArr2), null, null, null);
                try {
                    if (cursorQuery.moveToFirst()) {
                        cursor = (T) cursorQuery.getClass().getMethod(genGetColumnMethod((Class<?>) cls), Integer.TYPE).invoke(cursorQuery, 0);
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return (T) cursor;
                } catch (Exception e) {
                    e = e;
                    cursor = (T) cursorQuery;
                    throw new LitePalSupportException(e.getMessage(), e);
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

    protected void giveBaseObjIdValue(LitePalSupport litePalSupport, long j) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        if (j > 0) {
            DynamicExecutor.set(litePalSupport, "baseObjId", Long.valueOf(j), LitePalSupport.class);
        }
    }

    protected void putFieldsValue(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        for (Field field : list) {
            if (!isIdColumn(field.getName())) {
                putFieldsValueDependsOnSaveOrUpdate(litePalSupport, field, contentValues);
            }
        }
    }

    protected void putContentValuesForSave(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object field2 = DynamicExecutor.getField(litePalSupport, field.getName(), litePalSupport.getClass());
        if (field2 != null) {
            if ("java.util.Date".equals(field.getType().getName())) {
                field2 = Long.valueOf(((Date) field2).getTime());
            }
            Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
            if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
                field2 = encryptValue(encrypt.algorithm(), field2);
            }
            Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), field2};
            DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, field2, objArr));
        }
    }

    protected void putContentValuesForUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object fieldValue = getFieldValue(litePalSupport, field);
        if ("java.util.Date".equals(field.getType().getName()) && fieldValue != null) {
            fieldValue = Long.valueOf(((Date) fieldValue).getTime());
        }
        Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
            fieldValue = encryptValue(encrypt.algorithm(), fieldValue);
        }
        Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), fieldValue};
        DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, fieldValue, objArr));
    }

    protected Object encryptValue(String str, Object obj) {
        if (str == null || obj == null) {
            return obj;
        }
        if ("AES".equalsIgnoreCase(str)) {
            return CipherUtil.aesEncrypt((String) obj);
        }
        return "MD5".equalsIgnoreCase(str) ? CipherUtil.md5Encrypt((String) obj) : obj;
    }

    protected Object getFieldValue(LitePalSupport litePalSupport, Field field) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (shouldGetOrSet(litePalSupport, field)) {
            return DynamicExecutor.getField(litePalSupport, field.getName(), litePalSupport.getClass());
        }
        return null;
    }

    protected void setFieldValue(LitePalSupport litePalSupport, Field field, Object obj) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (shouldGetOrSet(litePalSupport, field)) {
            DynamicExecutor.setField(litePalSupport, field.getName(), obj, litePalSupport.getClass());
        }
    }

    protected void analyzeAssociatedModels(LitePalSupport litePalSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo associationsInfo : collection) {
                if (associationsInfo.getAssociationType() == 2) {
                    new Many2OneAnalyzer().analyze(litePalSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 1) {
                    new One2OneAnalyzer().analyze(litePalSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 3) {
                    new Many2ManyAnalyzer().analyze(litePalSupport, associationsInfo);
                }
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    protected LitePalSupport getAssociatedModel(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        return (LitePalSupport) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    protected Collection<LitePalSupport> getAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        return (Collection) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    protected LitePalSupport getEmptyModel(LitePalSupport litePalSupport) {
        InstantiationException e;
        String className;
        if (this.tempEmptyModel != null) {
            return this.tempEmptyModel;
        }
        try {
            try {
                className = litePalSupport.getClassName();
                try {
                    this.tempEmptyModel = (LitePalSupport) Class.forName(className).newInstance();
                    return this.tempEmptyModel;
                } catch (ClassNotFoundException unused) {
                    throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + className);
                } catch (InstantiationException e2) {
                    e = e2;
                    throw new LitePalSupportException(className + LitePalSupportException.INSTANTIATION_EXCEPTION, e);
                }
            } catch (Exception e3) {
                throw new LitePalSupportException(e3.getMessage(), e3);
            }
        } catch (ClassNotFoundException unused2) {
            className = null;
        } catch (InstantiationException e4) {
            e = e4;
            className = null;
        }
    }

    protected String getWhereClause(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    protected String[] getWhereArgs(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String[] strArr2 = new String[strArr.length - 1];
        System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
        return strArr2;
    }

    protected boolean isAffectAllLines(Object... objArr) {
        return objArr != null && objArr.length == 0;
    }

    protected String getWhereOfIdsWithOr(Collection<Long> collection) {
        StringBuilder sb = new StringBuilder();
        Iterator<Long> it2 = collection.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            long jLongValue = it2.next().longValue();
            if (z) {
                sb.append(" or ");
            }
            z = true;
            sb.append("id = ");
            sb.append(jLongValue);
        }
        return BaseUtility.changeCase(sb.toString());
    }

    protected String getWhereOfIdsWithOr(long... jArr) {
        StringBuilder sb = new StringBuilder();
        int length = jArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            long j = jArr[i];
            if (z) {
                sb.append(" or ");
            }
            sb.append("id = ");
            sb.append(j);
            i++;
            z = true;
        }
        return BaseUtility.changeCase(sb.toString());
    }

    protected String getIntermediateTableName(LitePalSupport litePalSupport, String str) {
        return BaseUtility.changeCase(DBUtility.getIntermediateTableName(litePalSupport.getTableName(), str));
    }

    protected String getTableName(Class<?> cls) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName()));
    }

    protected Object createInstanceFromClass(Class<?> cls) {
        try {
            Constructor<?> constructorFindBestSuitConstructor = findBestSuitConstructor(cls);
            return constructorFindBestSuitConstructor.newInstance(getConstructorParams(cls, constructorFindBestSuitConstructor));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    protected Constructor<?> findBestSuitConstructor(Class<?> cls) {
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        SparseArray sparseArray = new SparseArray();
        int i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (Constructor<?> constructor : declaredConstructors) {
            int length = constructor.getParameterTypes().length;
            int i2 = length;
            for (Class<?> cls2 : constructor.getParameterTypes()) {
                if (cls2 == cls) {
                    i2 += Constants.REQUEST_CODE_APP_INSTALL;
                } else if (cls2.getName().startsWith("com.android") && cls2.getName().endsWith("InstantReloadException")) {
                    i2 += Constants.REQUEST_CODE_APP_INSTALL;
                }
            }
            if (sparseArray.get(i2) == null) {
                sparseArray.put(i2, constructor);
            }
            if (i2 < i) {
                i = i2;
            }
        }
        Constructor<?> constructor2 = (Constructor) sparseArray.get(i);
        if (constructor2 != null) {
            constructor2.setAccessible(true);
        }
        return constructor2;
    }

    protected Object[] getConstructorParams(Class<?> cls, Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            objArr[i] = getInitParamValue(cls, parameterTypes[i]);
        }
        return objArr;
    }

    protected void setValueToModel(Object obj, List<Field> list, List<AssociationsInfo> list2, Cursor cursor, SparseArray<QueryInfoCache> sparseArray) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int size = sparseArray.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                int iKeyAt = sparseArray.keyAt(i);
                QueryInfoCache queryInfoCache = sparseArray.get(iKeyAt);
                setToModelByReflection(obj, queryInfoCache.field, iKeyAt, queryInfoCache.getMethodName, cursor);
            }
        } else {
            for (Field field : list) {
                String strGenGetColumnMethod = genGetColumnMethod(field);
                int columnIndex = cursor.getColumnIndex(BaseUtility.changeCase(isIdColumn(field.getName()) ? "id" : DBUtility.convertToValidColumnName(field.getName())));
                if (columnIndex != -1) {
                    setToModelByReflection(obj, field, columnIndex, strGenGetColumnMethod, cursor);
                    QueryInfoCache queryInfoCache2 = new QueryInfoCache();
                    queryInfoCache2.getMethodName = strGenGetColumnMethod;
                    queryInfoCache2.field = field;
                    sparseArray.put(columnIndex, queryInfoCache2);
                }
            }
        }
        if (list2 != null) {
            for (AssociationsInfo associationsInfo : list2) {
                int columnIndex2 = cursor.getColumnIndex(getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName())));
                if (columnIndex2 != -1) {
                    try {
                        LitePalSupport litePalSupport = (LitePalSupport) Operator.find(Class.forName(associationsInfo.getAssociatedClassName()), cursor.getLong(columnIndex2));
                        if (litePalSupport != null) {
                            setFieldValue((LitePalSupport) obj, associationsInfo.getAssociateOtherModelFromSelf(), litePalSupport);
                        }
                    } catch (ClassNotFoundException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        }
    }

    protected void setGenericValueToModel(LitePalSupport litePalSupport, List<Field> list, Map<Field, GenericModel> map) throws Throwable {
        String tableName;
        String valueIdColumnName;
        String getMethodName;
        String str;
        Cursor cursorQuery;
        String strConvertToValidColumnName;
        String strGenGetColumnMethod;
        for (Field field : list) {
            GenericModel genericModel = map.get(field);
            if (genericModel == null) {
                if (litePalSupport.getClassName().equals(getGenericTypeName(field))) {
                    strConvertToValidColumnName = DBUtility.getM2MSelfRefColumnName(field);
                    strGenGetColumnMethod = "getLong";
                } else {
                    strConvertToValidColumnName = DBUtility.convertToValidColumnName(field.getName());
                    strGenGetColumnMethod = genGetColumnMethod(field);
                }
                tableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), field.getName());
                valueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                GenericModel genericModel2 = new GenericModel();
                genericModel2.setTableName(tableName);
                genericModel2.setValueColumnName(strConvertToValidColumnName);
                genericModel2.setValueIdColumnName(valueIdColumnName);
                genericModel2.setGetMethodName(strGenGetColumnMethod);
                map.put(field, genericModel2);
                str = strConvertToValidColumnName;
                getMethodName = strGenGetColumnMethod;
            } else {
                tableName = genericModel.getTableName();
                String valueColumnName = genericModel.getValueColumnName();
                valueIdColumnName = genericModel.getValueIdColumnName();
                getMethodName = genericModel.getGetMethodName();
                str = valueColumnName;
            }
            String str2 = tableName;
            try {
                cursorQuery = this.mDatabase.query(str2, null, valueIdColumnName + " = ?", new String[]{String.valueOf(litePalSupport.getBaseObjId())}, null, null, null);
                try {
                    if (cursorQuery.moveToFirst()) {
                        while (true) {
                            int columnIndex = cursorQuery.getColumnIndex(BaseUtility.changeCase(str));
                            if (columnIndex != -1) {
                                setToModelByReflection(litePalSupport, field, columnIndex, getMethodName, cursorQuery);
                            }
                            if (!cursorQuery.moveToNext()) {
                                break;
                            } else {
                                str = str;
                            }
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    if (cursorQuery == null) {
                        throw th2;
                    }
                    cursorQuery.close();
                    throw th2;
                }
            } catch (Throwable th3) {
                th = th3;
                cursorQuery = null;
            }
        }
    }

    protected List<AssociationsInfo> getForeignKeyAssociations(String str, boolean z) {
        if (!z) {
            return null;
        }
        analyzeAssociations(str);
        return this.fkInCurrentModel;
    }

    protected Class<?>[] getParameterTypes(Field field, Object obj, Object[] objArr) {
        Class<?>[] clsArr;
        if (isCharType(field)) {
            objArr[1] = String.valueOf(obj);
            return new Class[]{String.class, String.class};
        }
        if (field.getType().isPrimitive()) {
            clsArr = new Class[]{String.class, getObjectType(field.getType())};
        } else {
            if ("java.util.Date".equals(field.getType().getName())) {
                return new Class[]{String.class, Long.class};
            }
            clsArr = new Class[]{String.class, field.getType()};
        }
        return clsArr;
    }

    private Class<?> getObjectType(Class<?> cls) {
        if (cls == null || !cls.isPrimitive()) {
            return null;
        }
        String name = cls.getName();
        if ("int".equals(name)) {
            return Integer.class;
        }
        if ("short".equals(name)) {
            return Short.class;
        }
        if ("long".equals(name)) {
            return Long.class;
        }
        if ("float".equals(name)) {
            return Float.class;
        }
        if ("double".equals(name)) {
            return Double.class;
        }
        if ("boolean".equals(name)) {
            return Boolean.class;
        }
        if ("char".equals(name)) {
            return Character.class;
        }
        return null;
    }

    private Object getInitParamValue(Class<?> cls, Class<?> cls2) {
        String name = cls2.getName();
        if ("boolean".equals(name) || "java.lang.Boolean".equals(name)) {
            return false;
        }
        if ("float".equals(name) || "java.lang.Float".equals(name)) {
            return Float.valueOf(0.0f);
        }
        if ("double".equals(name) || "java.lang.Double".equals(name)) {
            return Double.valueOf(0.0d);
        }
        if ("int".equals(name) || "java.lang.Integer".equals(name)) {
            return 0;
        }
        if ("long".equals(name) || "java.lang.Long".equals(name)) {
            return 0L;
        }
        if ("short".equals(name) || "java.lang.Short".equals(name)) {
            return 0;
        }
        if ("char".equals(name) || "java.lang.Character".equals(name)) {
            return ' ';
        }
        if ("[B".equals(name) || "[Ljava.lang.Byte;".equals(name)) {
            return new byte[0];
        }
        if ("java.lang.String".equals(name)) {
            return "";
        }
        if (cls == cls2) {
            return null;
        }
        return createInstanceFromClass(cls2);
    }

    private boolean isCharType(Field field) {
        String name = field.getType().getName();
        return name.equals("char") || name.endsWith("Character");
    }

    private boolean isPrimitiveBooleanType(Field field) {
        return "boolean".equals(field.getType().getName());
    }

    private void putFieldsValueDependsOnSaveOrUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (isUpdating()) {
            if (isFieldWithDefaultValue(litePalSupport, field)) {
                return;
            }
            putContentValuesForUpdate(litePalSupport, field, contentValues);
        } else if (isSaving()) {
            putContentValuesForSave(litePalSupport, field, contentValues);
        }
    }

    private boolean isUpdating() {
        return UpdateHandler.class.getName().equals(getClass().getName());
    }

    private boolean isSaving() {
        return SaveHandler.class.getName().equals(getClass().getName());
    }

    private boolean isFieldWithDefaultValue(LitePalSupport litePalSupport, Field field) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        LitePalSupport emptyModel = getEmptyModel(litePalSupport);
        Object fieldValue = getFieldValue(litePalSupport, field);
        Object fieldValue2 = getFieldValue(emptyModel, field);
        if (fieldValue == null || fieldValue2 == null) {
            return fieldValue == fieldValue2;
        }
        return fieldValue.toString().equals(fieldValue2.toString());
    }

    private String makeGetterMethodName(Field field) {
        String str;
        String name = field.getName();
        if (isPrimitiveBooleanType(field)) {
            if (name.matches("^is[A-Z]{1}.*$")) {
                name = name.substring(2);
            }
            str = C1799v.f4286Y;
        } else {
            str = "get";
        }
        if (name.matches("^[a-z]{1}[A-Z]{1}.*")) {
            return str + name;
        }
        return str + BaseUtility.capitalize(name);
    }

    private String makeSetterMethodName(Field field) {
        if (isPrimitiveBooleanType(field) && field.getName().matches("^is[A-Z]{1}.*$")) {
            return "set" + field.getName().substring(2);
        }
        if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
            return "set" + field.getName();
        }
        return "set" + BaseUtility.capitalize(field.getName());
    }

    private String genGetColumnMethod(Field field) {
        Class<?> type;
        if (isCollection(field.getType())) {
            type = getGenericTypeClass(field);
        } else {
            type = field.getType();
        }
        return genGetColumnMethod(type);
    }

    private String genGetColumnMethod(Class<?> cls) {
        String simpleName;
        if (cls.isPrimitive()) {
            simpleName = BaseUtility.capitalize(cls.getName());
        } else {
            simpleName = cls.getSimpleName();
        }
        String str = "get" + simpleName;
        if ("getBoolean".equals(str)) {
            return "getInt";
        }
        if ("getChar".equals(str) || "getCharacter".equals(str)) {
            return "getString";
        }
        if ("getDate".equals(str)) {
            return "getLong";
        }
        if ("getInteger".equals(str)) {
            return "getInt";
        }
        return "getbyte[]".equalsIgnoreCase(str) ? "getBlob" : str;
    }

    private String[] getCustomizedColumns(String[] strArr, List<Field> list, List<AssociationsInfo> list2) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        Iterator<Field> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getName());
        }
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (BaseUtility.containsIgnoreCases(arrayList2, str)) {
                arrayList3.add(Integer.valueOf(i));
            } else if (isIdColumn(str)) {
                if ("_id".equalsIgnoreCase(str)) {
                    arrayList.set(i, BaseUtility.changeCase("id"));
                }
                z = true;
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            arrayList4.add((String) arrayList.remove(((Integer) arrayList3.get(size)).intValue()));
        }
        for (Field field : list) {
            if (BaseUtility.containsIgnoreCases(arrayList4, field.getName())) {
                arrayList5.add(field);
            }
        }
        list.clear();
        list.addAll(arrayList5);
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                arrayList.add(getForeignKeyColumnName(DBUtility.getTableNameByClassName(list2.get(i2).getAssociatedClassName())));
            }
        }
        if (!z) {
            arrayList.add(BaseUtility.changeCase("id"));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void analyzeAssociations(String str) {
        Collection<AssociationsInfo> associationInfo = getAssociationInfo(str);
        if (this.fkInCurrentModel == null) {
            this.fkInCurrentModel = new ArrayList();
        } else {
            this.fkInCurrentModel.clear();
        }
        if (this.fkInOtherModel == null) {
            this.fkInOtherModel = new ArrayList();
        } else {
            this.fkInOtherModel.clear();
        }
        for (AssociationsInfo associationsInfo : associationInfo) {
            if (associationsInfo.getAssociationType() == 2 || associationsInfo.getAssociationType() == 1) {
                if (associationsInfo.getClassHoldsForeignKey().equals(str)) {
                    this.fkInCurrentModel.add(associationsInfo);
                } else {
                    this.fkInOtherModel.add(associationsInfo);
                }
            } else if (associationsInfo.getAssociationType() == 3) {
                this.fkInOtherModel.add(associationsInfo);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:111:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:89:0x01d4  */
    private void setAssociatedModel(LitePalSupport litePalSupport) throws Throwable {
        Throwable th;
        Cursor cursor;
        Exception exc;
        Cursor cursor2;
        Cursor cursorFindBySQL;
        Iterator<AssociationsInfo> it2;
        HashMap map;
        DataHandler dataHandler;
        DataHandler dataHandler2 = this;
        if (dataHandler2.fkInOtherModel == null) {
            return;
        }
        Iterator<AssociationsInfo> it3 = dataHandler2.fkInOtherModel.iterator();
        while (it3.hasNext()) {
            AssociationsInfo next = it3.next();
            String associatedClassName = next.getAssociatedClassName();
            boolean z = next.getAssociationType() == 3;
            try {
                List<Field> supportedFields = dataHandler2.getSupportedFields(associatedClassName);
                List<Field> supportedGenericFields = dataHandler2.getSupportedGenericFields(associatedClassName);
                if (z) {
                    try {
                        String tableName = litePalSupport.getTableName();
                        String tableNameByClassName = DBUtility.getTableNameByClassName(associatedClassName);
                        String intermediateTableName = DBUtility.getIntermediateTableName(tableName, tableNameByClassName);
                        StringBuilder sb = new StringBuilder();
                        sb.append("select * from ");
                        sb.append(tableNameByClassName);
                        sb.append(" a inner join ");
                        sb.append(intermediateTableName);
                        sb.append(" b on a.id = b.");
                        sb.append(tableNameByClassName + "_id");
                        sb.append(" where b.");
                        sb.append(tableName);
                        sb.append("_id = ?");
                        cursorFindBySQL = Operator.findBySQL(BaseUtility.changeCase(sb.toString()), String.valueOf(litePalSupport.getBaseObjId()));
                    } catch (Exception e) {
                        exc = e;
                        cursor2 = null;
                        try {
                            throw new LitePalSupportException(exc.getMessage(), exc);
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = cursor2;
                            if (cursor != null) {
                                throw th;
                            }
                            cursor.close();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = null;
                        if (cursor != null) {
                            throw th;
                        }
                        cursor.close();
                        throw th;
                    }
                } else {
                    String foreignKeyColumnName = dataHandler2.getForeignKeyColumnName(DBUtility.getTableNameByClassName(next.getSelfClassName()));
                    String tableNameByClassName2 = DBUtility.getTableNameByClassName(associatedClassName);
                    SQLiteDatabase sQLiteDatabase = dataHandler2.mDatabase;
                    String strChangeCase = BaseUtility.changeCase(tableNameByClassName2);
                    String str = foreignKeyColumnName + "=?";
                    String[] strArr = new String[1];
                    try {
                        strArr[0] = String.valueOf(litePalSupport.getBaseObjId());
                        cursorFindBySQL = sQLiteDatabase.query(strChangeCase, null, str, strArr, null, null, null, null);
                    } catch (Exception e2) {
                        e = e2;
                        exc = e;
                        cursor2 = null;
                        throw new LitePalSupportException(exc.getMessage(), exc);
                    } catch (Throwable th4) {
                        th = th4;
                        th = th;
                        cursor = null;
                        if (cursor != null) {
                            throw th;
                        }
                        cursor.close();
                        throw th;
                    }
                }
                cursor = cursorFindBySQL;
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            SparseArray<QueryInfoCache> sparseArray = new SparseArray<>();
                            HashMap map2 = new HashMap();
                            while (true) {
                                try {
                                    LitePalSupport litePalSupport2 = (LitePalSupport) createInstanceFromClass(Class.forName(associatedClassName));
                                    giveBaseObjIdValue(litePalSupport2, cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                                    it2 = it3;
                                    String str2 = associatedClassName;
                                    List<Field> list = supportedFields;
                                    map = map2;
                                    setValueToModel(litePalSupport2, supportedFields, null, cursor, sparseArray);
                                    dataHandler = this;
                                    try {
                                        dataHandler.setGenericValueToModel(litePalSupport2, supportedGenericFields, map);
                                        if (next.getAssociationType() == 2 || z) {
                                            Field associateOtherModelFromSelf = next.getAssociateOtherModelFromSelf();
                                            Collection hashSet = (Collection) dataHandler.getFieldValue(litePalSupport, associateOtherModelFromSelf);
                                            if (hashSet == null) {
                                                if (dataHandler.isList(associateOtherModelFromSelf.getType())) {
                                                    hashSet = new ArrayList();
                                                } else {
                                                    hashSet = new HashSet();
                                                }
                                                DynamicExecutor.setField(litePalSupport, associateOtherModelFromSelf.getName(), hashSet, litePalSupport.getClass());
                                            }
                                            hashSet.add(litePalSupport2);
                                        } else if (next.getAssociationType() == 1) {
                                            dataHandler.setFieldValue(litePalSupport, next.getAssociateOtherModelFromSelf(), litePalSupport2);
                                        }
                                        if (!cursor.moveToNext()) {
                                            break;
                                        }
                                        map2 = map;
                                        it3 = it2;
                                        associatedClassName = str2;
                                        supportedFields = list;
                                    } catch (Exception e3) {
                                        e = e3;
                                        exc = e;
                                        cursor2 = cursor;
                                        throw new LitePalSupportException(exc.getMessage(), exc);
                                    } catch (Throwable th5) {
                                        th = th5;
                                        th = th;
                                        if (cursor != null) {
                                            throw th;
                                        }
                                        cursor.close();
                                        throw th;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                } catch (Throwable th6) {
                                    th = th6;
                                }
                            }
                            sparseArray.clear();
                            map.clear();
                        } else {
                            it2 = it3;
                            dataHandler = this;
                        }
                    } catch (Exception e5) {
                        e = e5;
                    } catch (Throwable th7) {
                        th = th7;
                    }
                } else {
                    it2 = it3;
                    dataHandler = this;
                }
                if (cursor != null) {
                    cursor.close();
                }
                dataHandler2 = dataHandler;
                it3 = it2;
            } catch (Exception e6) {
                e = e6;
            } catch (Throwable th8) {
                th = th8;
            }
        }
    }

    private void setToModelByReflection(Object obj, Field field, int i, String str, Cursor cursor) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object objInvoke = cursor.getClass().getMethod(str, Integer.TYPE).invoke(cursor, Integer.valueOf(i));
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            if (AccountManager.TYPE_LOGIN.equals(String.valueOf(objInvoke))) {
                objInvoke = false;
            } else if ("1".equals(String.valueOf(objInvoke))) {
                objInvoke = true;
            }
        } else if (field.getType() == Character.TYPE || field.getType() == Character.class) {
            objInvoke = Character.valueOf(((String) objInvoke).charAt(0));
        } else if (field.getType() == Date.class) {
            long jLongValue = ((Long) objInvoke).longValue();
            objInvoke = jLongValue <= 0 ? null : new Date(jLongValue);
        }
        if (isCollection(field.getType())) {
            Collection hashSet = (Collection) DynamicExecutor.getField(obj, field.getName(), obj.getClass());
            if (hashSet == null) {
                if (isList(field.getType())) {
                    hashSet = new ArrayList();
                } else {
                    hashSet = new HashSet();
                }
                DynamicExecutor.setField(obj, field.getName(), hashSet, obj.getClass());
            }
            String genericTypeName = getGenericTypeName(field);
            if ("java.lang.String".equals(genericTypeName)) {
                Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
                if (encrypt != null) {
                    objInvoke = decryptValue(encrypt.algorithm(), objInvoke);
                }
            } else if (obj.getClass().getName().equals(genericTypeName) && ((objInvoke instanceof Long) || (objInvoke instanceof Integer))) {
                objInvoke = Operator.find(obj.getClass(), ((Long) objInvoke).longValue());
            }
            hashSet.add(objInvoke);
            return;
        }
        Encrypt encrypt2 = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt2 != null && "java.lang.String".equals(field.getType().getName())) {
            objInvoke = decryptValue(encrypt2.algorithm(), objInvoke);
        }
        DynamicExecutor.setField(obj, field.getName(), objInvoke, obj.getClass());
    }

    protected Object decryptValue(String str, Object obj) {
        return (str == null || obj == null || !"AES".equalsIgnoreCase(str)) ? obj : CipherUtil.aesDecrypt((String) obj);
    }

    class QueryInfoCache {
        Field field;
        String getMethodName;

        QueryInfoCache() {
        }
    }
}
