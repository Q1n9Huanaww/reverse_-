package com.cyjh.feedback.lib.executor;

/* JADX INFO: loaded from: classes.dex */
public class ThreadPoolProxyFactory {
    private static ThreadPoolProxy sNormalThreadPool;

    public static ThreadPoolProxy getNormalThreadPool() {
        if (sNormalThreadPool == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (sNormalThreadPool == null) {
                    sNormalThreadPool = new ThreadPoolProxy();
                }
            }
        }
        return sNormalThreadPool;
    }
}
