package org.litepal.crud.async;

import org.litepal.crud.callback.FindCallback;

/* JADX INFO: loaded from: classes.dex */
public class FindExecutor<T> extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private FindCallback<T> f5078cb;

    public void listen(FindCallback<T> findCallback) {
        this.f5078cb = findCallback;
        execute();
    }

    public FindCallback<T> getListener() {
        return this.f5078cb;
    }
}
