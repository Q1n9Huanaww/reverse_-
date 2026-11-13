package org.litepal.tablemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.Const;
import org.litepal.util.LitePalLog;

/* JADX INFO: loaded from: classes.dex */
public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    @Override // org.litepal.tablemanager.AssociationUpdater, org.litepal.tablemanager.Creator, org.litepal.tablemanager.AssociationCreator, org.litepal.tablemanager.Generator
    protected void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mTableModels = getAllTableModels();
        this.mDb = sQLiteDatabase;
        dropTables();
    }

    private void dropTables() throws Throwable {
        List<String> listFindTablesToDrop = findTablesToDrop();
        dropTables(listFindTablesToDrop, this.mDb);
        clearCopyInTableSchema(listFindTablesToDrop);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0064 A[PHI: r2
  0x0064: PHI (r2v5 android.database.Cursor) = (r2v4 android.database.Cursor), (r2v7 android.database.Cursor) binds: [B:20:0x0062, B:11:0x0053] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:25:0x006b  */
    private List<String> findTablesToDrop() throws Throwable {
        Cursor cursorQuery;
        Exception e;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursorQuery = this.mDb.query(Const.TableSchema.TABLE_NAME, null, null, null, null, null, null);
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(Const.TableSchema.COLUMN_NAME));
                            if (shouldDropThisTable(string, cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("type")))) {
                                LitePalLog.m5154d(AssociationUpdater.TAG, "need to drop " + string);
                                arrayList.add(string);
                            }
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    ThrowableExtension.printStackTrace(e);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            cursorQuery = null;
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    private List<String> pickTableNamesFromTableModels() {
        ArrayList arrayList = new ArrayList();
        Iterator<TableModel> it2 = this.mTableModels.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getTableName());
        }
        return arrayList;
    }

    private boolean shouldDropThisTable(String str, int i) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), str) && i == 0;
    }
}
