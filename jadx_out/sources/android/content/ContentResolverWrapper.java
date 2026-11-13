package android.content;

import android.annotation.TargetApi;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContentResolverWrapper extends ContentResolver {
    ContentResolver mBase;

    @TargetApi(20)
    public void appNotRespondingViaProvider(IContentProvider iContentProvider) {
    }

    public ContentResolverWrapper(Context context) {
        super(context);
        this.mBase = context.getContentResolver();
    }

    protected IContentProvider acquireProvider(Context context, String str) {
        return this.mBase.acquireProvider(context, str);
    }

    protected IContentProvider acquireExistingProvider(Context context, String str) {
        return this.mBase.acquireExistingProvider(context, str);
    }

    @TargetApi(16)
    protected IContentProvider acquireUnstableProvider(Context context, String str) {
        return this.mBase.acquireUnstableProvider(context, str);
    }

    public boolean releaseProvider(IContentProvider iContentProvider) {
        return this.mBase.releaseProvider(iContentProvider);
    }

    @TargetApi(16)
    public boolean releaseUnstableProvider(IContentProvider iContentProvider) {
        return this.mBase.releaseUnstableProvider(iContentProvider);
    }

    @TargetApi(16)
    public void unstableProviderDied(IContentProvider iContentProvider) {
        this.mBase.unstableProviderDied(iContentProvider);
    }
}
