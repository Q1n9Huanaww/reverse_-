package org.litepal.crud.async;

import org.litepal.crud.callback.CountCallback;

/* JADX INFO: loaded from: classes.dex */
public class CountExecutor extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private CountCallback f5077cb;

    public void listen(CountCallback countCallback) {
        this.f5077cb = countCallback;
        execute();
    }

    public CountCallback getListener() {
        return this.f5077cb;
    }
}
