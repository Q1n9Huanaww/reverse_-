package org.litepal.tablemanager.callback;

/* JADX INFO: loaded from: classes.dex */
public interface DatabaseListener {
    void onCreate();

    void onUpgrade(int i, int i2);
}
