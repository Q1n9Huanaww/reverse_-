package org.litepal.crud.async;

import org.litepal.crud.callback.AverageCallback;

/* JADX INFO: loaded from: classes.dex */
public class AverageExecutor extends AsyncExecutor {

    /* JADX INFO: renamed from: cb */
    private AverageCallback f5076cb;

    public void listen(AverageCallback averageCallback) {
        this.f5076cb = averageCallback;
        execute();
    }

    public AverageCallback getListener() {
        return this.f5076cb;
    }
}
