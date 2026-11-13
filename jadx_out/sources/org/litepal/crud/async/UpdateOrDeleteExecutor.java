package org.litepal.crud.async;

import org.litepal.crud.callback.UpdateOrDeleteCallback;

/* JADX INFO: loaded from: classes.dex */
public class UpdateOrDeleteExecutor extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private UpdateOrDeleteCallback f5081cb;

    public void listen(UpdateOrDeleteCallback updateOrDeleteCallback) {
        this.f5081cb = updateOrDeleteCallback;
        execute();
    }

    public UpdateOrDeleteCallback getListener() {
        return this.f5081cb;
    }
}
