package org.litepal.crud;

import android.database.Cursor;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.tablemanager.Connector;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

/* JADX INFO: loaded from: classes.dex */
public class Many2ManyAnalyzer extends AssociationsAnalyzer {
    void analyze(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Collection<LitePalSupport> associatedModels = getAssociatedModels(litePalSupport, associationsInfo);
        declareAssociations(litePalSupport, associationsInfo);
        if (associatedModels != null) {
            for (LitePalSupport litePalSupport2 : associatedModels) {
                Collection<LitePalSupport> collectionCheckAssociatedModelCollection = checkAssociatedModelCollection(getReverseAssociatedModels(litePalSupport2, associationsInfo), associationsInfo.getAssociateSelfFromOtherModel());
                addNewModelForAssociatedModel(collectionCheckAssociatedModelCollection, litePalSupport);
                setReverseAssociatedModels(litePalSupport2, associationsInfo, collectionCheckAssociatedModelCollection);
                dealAssociatedModel(litePalSupport, litePalSupport2);
            }
        }
    }

    private void declareAssociations(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) {
        litePalSupport.addEmptyModelForJoinTable(getAssociatedTableName(associationsInfo));
    }

    private void addNewModelForAssociatedModel(Collection<LitePalSupport> collection, LitePalSupport litePalSupport) {
        if (collection.contains(litePalSupport)) {
            return;
        }
        collection.add(litePalSupport);
    }

    private void dealAssociatedModel(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        if (litePalSupport2.isSaved()) {
            litePalSupport.addAssociatedModelForJoinTable(litePalSupport2.getTableName(), litePalSupport2.getBaseObjId());
        }
    }

    private String getAssociatedTableName(AssociationsInfo associationsInfo) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }

    @Deprecated
    private boolean isDataExists(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) throws Throwable {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = Connector.getDatabase().query(getJoinTableName(litePalSupport, litePalSupport2), null, getSelection(litePalSupport, litePalSupport2), getSelectionArgs(litePalSupport, litePalSupport2), null, null, null);
                try {
                    boolean z = cursorQuery.getCount() > 0;
                    cursorQuery.close();
                    return z;
                } catch (Exception e) {
                    e = e;
                    cursor = cursorQuery;
                    ThrowableExtension.printStackTrace(e);
                    cursor.close();
                    return true;
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    cursor.close();
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private String getSelection(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        return getForeignKeyColumnName(litePalSupport.getTableName()) + " = ? and " + getForeignKeyColumnName(litePalSupport2.getTableName()) + " = ?";
    }

    private String[] getSelectionArgs(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        return new String[]{String.valueOf(litePalSupport.getBaseObjId()), String.valueOf(litePalSupport2.getBaseObjId())};
    }

    private String getJoinTableName(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        return getIntermediateTableName(litePalSupport, litePalSupport2.getTableName());
    }
}
