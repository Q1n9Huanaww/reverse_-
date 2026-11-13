package org.litepal.crud.async;

import org.litepal.crud.callback.SaveCallback;

/* JADX INFO: loaded from: classes.dex */
public class SaveExecutor extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private SaveCallback f5080cb;

    public void listen(SaveCallback saveCallback) {
        this.f5080cb = saveCallback;
        execute();
    }

    public SaveCallback getListener() {
        return this.f5080cb;
    }
}
