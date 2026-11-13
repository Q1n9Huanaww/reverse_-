package org.apache.commons.mail.p093a;

import org.apache.commons.mail.DataSourceResolver;

/* JADX INFO: renamed from: org.apache.commons.mail.a.a */
/* JADX INFO: compiled from: DataSourceBaseResolver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2081a implements DataSourceResolver {

    /* JADX INFO: renamed from: a */
    final boolean f5053a;

    public AbstractC2081a() {
        this.f5053a = false;
    }

    public AbstractC2081a(boolean z) {
        this.f5053a = z;
    }

    /* JADX INFO: renamed from: a */
    private boolean m5107a() {
        return this.f5053a;
    }

    /* JADX INFO: renamed from: b */
    private static boolean m5109b(String str) {
        return str.startsWith("cid:");
    }

    /* JADX INFO: renamed from: c */
    private static boolean m5110c(String str) {
        return str.startsWith("file:/");
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m5108a(String str) {
        return str.startsWith("http://") || str.startsWith("https://");
    }
}
