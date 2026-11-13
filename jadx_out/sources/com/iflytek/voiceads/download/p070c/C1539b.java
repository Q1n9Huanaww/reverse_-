package com.iflytek.voiceads.download.p070c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.didi.virtualapk.delegate.RemoteContentProvider;
import com.iflytek.voiceads.download.p071d.C1542a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.c.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1539b implements InterfaceC1538a {

    /* JADX INFO: renamed from: a */
    private static final String[] f3228a = {"_id", RemoteContentProvider.KEY_URI, "path"};

    /* JADX INFO: renamed from: b */
    private static final String f3229b = String.format("REPLACE INTO %s (_id,uri,path) VALUES(?,?,?);", "download_info");

    /* JADX INFO: renamed from: c */
    private C1540c f3230c;

    /* JADX INFO: renamed from: d */
    private SQLiteDatabase f3231d;

    /* JADX INFO: renamed from: e */
    private SQLiteDatabase f3232e;

    public C1539b(Context context) {
        this.f3230c = new C1540c(context);
        this.f3231d = this.f3230c.getWritableDatabase();
        this.f3232e = this.f3230c.getReadableDatabase();
    }

    /* JADX INFO: renamed from: a */
    private void m2965a(Cursor cursor, C1542a c1542a) {
        c1542a.m3012b(cursor.getInt(0));
        c1542a.m3008a(cursor.getString(1));
        c1542a.m3014b(cursor.getString(2));
    }

    @Override // com.iflytek.voiceads.download.p070c.InterfaceC1538a
    /* JADX INFO: renamed from: a */
    public C1542a mo2961a(int i) {
        C1542a c1542a = null;
        Cursor cursorQuery = this.f3232e.query("download_info", f3228a, "_id=?", new String[]{String.valueOf(i)}, null, null, null);
        if (cursorQuery != null && cursorQuery.getCount() > 0) {
            while (cursorQuery.moveToNext()) {
                c1542a = new C1542a();
                m2965a(cursorQuery, c1542a);
            }
            cursorQuery.close();
        }
        return c1542a;
    }

    @Override // com.iflytek.voiceads.download.p070c.InterfaceC1538a
    /* JADX INFO: renamed from: a */
    public List<C1542a> mo2962a() {
        Cursor cursorQuery = this.f3232e.query("download_info", null, null, null, null, null, null);
        ArrayList arrayList = new ArrayList();
        if (cursorQuery != null && cursorQuery.getCount() > 0) {
            while (cursorQuery.moveToNext()) {
                C1542a c1542a = new C1542a();
                m2965a(cursorQuery, c1542a);
                arrayList.add(c1542a);
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    @Override // com.iflytek.voiceads.download.p070c.InterfaceC1538a
    /* JADX INFO: renamed from: a */
    public void mo2963a(C1542a c1542a) {
        this.f3231d.execSQL(f3229b, new Object[]{Integer.valueOf(c1542a.m3020h()), c1542a.m3011b(), c1542a.m3015c()});
    }

    @Override // com.iflytek.voiceads.download.p070c.InterfaceC1538a
    /* JADX INFO: renamed from: b */
    public void mo2964b(C1542a c1542a) {
        this.f3231d.delete("download_info", "_id=?", new String[]{String.valueOf(c1542a.m3020h())});
    }
}
