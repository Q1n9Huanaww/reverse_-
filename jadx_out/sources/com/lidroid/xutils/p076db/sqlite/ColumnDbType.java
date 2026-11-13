package com.lidroid.xutils.p076db.sqlite;

/* JADX INFO: loaded from: classes.dex */
public enum ColumnDbType {
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    BLOB("BLOB");

    private String value;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static ColumnDbType[] valuesCustom() {
        ColumnDbType[] columnDbTypeArrValuesCustom = values();
        int length = columnDbTypeArrValuesCustom.length;
        ColumnDbType[] columnDbTypeArr = new ColumnDbType[length];
        System.arraycopy(columnDbTypeArrValuesCustom, 0, columnDbTypeArr, 0, length);
        return columnDbTypeArr;
    }

    ColumnDbType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.value;
    }
}
