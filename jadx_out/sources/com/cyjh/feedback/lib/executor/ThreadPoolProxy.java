package com.cyjh.feedback.lib.executor;

import android.support.annotation.NonNull;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ThreadPoolProxy {
    private static final int KEEP_ALIVE_SECONDS = 30;
    private ThreadPoolExecutor mPoolExecutor;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;
    private static final ThreadFactory M_THREAD_FACTORY = new ThreadFactory() { // from class: com.cyjh.feedback.lib.executor.ThreadPoolProxy.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "ThreadPoolProxy #" + this.mCount.getAndIncrement());
        }
    };

    protected ThreadPoolProxy() {
        initThreadPoolExecutor();
    }

    private void initThreadPoolExecutor() {
        if (this.mPoolExecutor == null || this.mPoolExecutor.isShutdown() || this.mPoolExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if (this.mPoolExecutor == null || this.mPoolExecutor.isShutdown() || this.mPoolExecutor.isTerminated()) {
                    this.mPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), M_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
    }

    public void executeRunnable(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.mPoolExecutor.execute(runnable);
    }

    public void removeRunnable(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.mPoolExecutor.remove(runnable);
    }
}
