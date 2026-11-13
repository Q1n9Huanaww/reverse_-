package org.litepal.crud.async;

import org.litepal.crud.callback.FindMultiCallback;

/* JADX INFO: loaded from: classes.dex */
public class FindMultiExecutor<T> extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private FindMultiCallback<T> f5079cb;

    public void listen(FindMultiCallback<T> findMultiCallback) {
        this.f5079cb = findMultiCallback;
        execute();
    }

    public FindMultiCallback<T> getListener() {
        return this.f5079cb;
    }
}
