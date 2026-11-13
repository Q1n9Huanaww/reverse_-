package com.didi.virtualapk.internal;

import android.annotation.TargetApi;
import android.content.ContentResolverWrapper;
import android.content.Context;
import android.content.IContentProvider;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.delegate.RemoteContentProvider;

/* JADX INFO: loaded from: classes.dex */
public class PluginContentResolver extends ContentResolverWrapper {
    private PluginManager mPluginManager;

    @Override // android.content.ContentResolverWrapper
    @TargetApi(20)
    public void appNotRespondingViaProvider(IContentProvider iContentProvider) {
    }

    @Override // android.content.ContentResolverWrapper
    public boolean releaseProvider(IContentProvider iContentProvider) {
        return true;
    }

    @Override // android.content.ContentResolverWrapper
    @TargetApi(16)
    public boolean releaseUnstableProvider(IContentProvider iContentProvider) {
        return true;
    }

    protected int resolveUserIdFromAuthority(String str) {
        return 0;
    }

    @Override // android.content.ContentResolverWrapper
    @TargetApi(16)
    public void unstableProviderDied(IContentProvider iContentProvider) {
    }

    public PluginContentResolver(Context context) {
        super(context);
        this.mPluginManager = PluginManager.getInstance(context);
    }

    @Override // android.content.ContentResolverWrapper
    protected IContentProvider acquireProvider(Context context, String str) {
        if (this.mPluginManager.resolveContentProvider(str, 0) != null) {
            return this.mPluginManager.getIContentProvider();
        }
        return super.acquireProvider(context, str);
    }

    @Override // android.content.ContentResolverWrapper
    protected IContentProvider acquireExistingProvider(Context context, String str) {
        if (this.mPluginManager.resolveContentProvider(str, 0) != null) {
            return this.mPluginManager.getIContentProvider();
        }
        return super.acquireExistingProvider(context, str);
    }

    @Override // android.content.ContentResolverWrapper
    @TargetApi(16)
    protected IContentProvider acquireUnstableProvider(Context context, String str) {
        if (this.mPluginManager.resolveContentProvider(str, 0) != null) {
            return this.mPluginManager.getIContentProvider();
        }
        return super.acquireUnstableProvider(context, str);
    }

    @Keep
    public static Uri wrapperUri(LoadedPlugin loadedPlugin, Uri uri) {
        String packageName = loadedPlugin.getPackageName();
        String strEncode = Uri.encode(uri.toString());
        StringBuilder sb = new StringBuilder(RemoteContentProvider.getUri(loadedPlugin.getHostContext()));
        sb.append("/?plugin=" + loadedPlugin.getLocation());
        sb.append("&pkg=" + packageName);
        sb.append("&uri=" + strEncode);
        return Uri.parse(sb.toString());
    }

    @Deprecated
    public static String getAuthority(Context context) {
        return RemoteContentProvider.getAuthority(context);
    }

    @Deprecated
    public static String getUri(Context context) {
        return RemoteContentProvider.getUri(context);
    }

    @Keep
    public static Bundle getBundleForCall(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString(RemoteContentProvider.KEY_WRAPPER_URI, uri.toString());
        return bundle;
    }
}
