package org.apache.commons.mail.p093a;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.activation.DataSource;
import javax.activation.URLDataSource;

/* JADX INFO: renamed from: org.apache.commons.mail.a.e */
/* JADX INFO: compiled from: DataSourceUrlResolver.java */
/* JADX INFO: loaded from: classes.dex */
public final class C2085e extends AbstractC2081a {

    /* JADX INFO: renamed from: b */
    private final URL f5057b;

    private C2085e(URL url) {
        this.f5057b = url;
    }

    private C2085e(URL url, boolean z) {
        super(z);
        this.f5057b = url;
    }

    /* JADX INFO: renamed from: a */
    private URL m5115a() {
        return this.f5057b;
    }

    /* JADX INFO: renamed from: b */
    private URL m5116b(String str) throws MalformedURLException {
        if (this.f5057b == null) {
            return new URL(str);
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("No resource defined");
        }
        if (str.startsWith("file:/") || m5108a(str)) {
            return new URL(str);
        }
        return new URL(this.f5057b, str.replaceAll("&amp;", "&"));
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        return resolve(str, this.f5053a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        URL url;
        try {
            if (str.startsWith("cid:")) {
                return null;
            }
            if (this.f5057b == null) {
                url = new URL(str);
            } else {
                if (str != null && str.length() != 0) {
                    if (str.startsWith("file:/") || m5108a(str)) {
                        url = new URL(str);
                    } else {
                        url = new URL(this.f5057b, str.replaceAll("&amp;", "&"));
                    }
                }
                throw new IllegalArgumentException("No resource defined");
            }
            URLDataSource uRLDataSource = new URLDataSource(url);
            uRLDataSource.getInputStream();
            return uRLDataSource;
        } catch (IOException e) {
            if (z) {
                return null;
            }
            throw e;
        }
    }
}
