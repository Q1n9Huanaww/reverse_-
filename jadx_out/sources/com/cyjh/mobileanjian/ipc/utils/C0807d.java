package com.cyjh.mobileanjian.ipc.utils;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.d */
/* JADX INFO: compiled from: ContactsUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C0807d {

    /* JADX INFO: renamed from: a */
    private static final String f1418a = "d";

    /* JADX INFO: renamed from: a */
    public static boolean m1078a(Context context, String str, String str2, String str3) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", str2).withValue("data3", str).build());
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", str3).withValue("data2", 2).build());
        try {
            context.getContentResolver().applyBatch("com.android.contacts", arrayList);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Toast.makeText(context, e.getMessage(), 1).show();
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1077a(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[]{"raw_contact_id"}, "data1=?", new String[]{str}, null);
        try {
            try {
                if (cursorQuery.moveToFirst()) {
                    do {
                        context.getContentResolver().delete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, cursorQuery.getInt(0)), null, null);
                    } while (cursorQuery.moveToNext());
                }
            } catch (Exception e) {
                Log.e(f1418a, e.getMessage());
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1076a(Context context) {
        context.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, null, null);
    }
}
