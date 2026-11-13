package org.apache.commons.mail.resolver;

import org.apache.commons.mail.DataSourceResolver;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataSourceBaseResolver implements DataSourceResolver {
    private final boolean lenient;

    public DataSourceBaseResolver() {
        this.lenient = false;
    }

    public DataSourceBaseResolver(boolean z) {
        this.lenient = z;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    protected boolean isCid(String str) {
        return str.startsWith("cid:");
    }

    protected boolean isFileUrl(String str) {
        return str.startsWith("file:/");
    }

    protected boolean isHttpUrl(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }
}
