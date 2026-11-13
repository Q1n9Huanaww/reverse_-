package org.apache.commons.mail.resolver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.activation.DataSource;
import javax.activation.URLDataSource;

/* JADX INFO: loaded from: classes.dex */
public class DataSourceUrlResolver extends DataSourceBaseResolver {
    private final URL baseUrl;

    public DataSourceUrlResolver(URL url) {
        this.baseUrl = url;
    }

    public DataSourceUrlResolver(URL url, boolean z) {
        super(z);
        this.baseUrl = url;
    }

    public URL getBaseUrl() {
        return this.baseUrl;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        return resolve(str, isLenient());
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        try {
            if (isCid(str)) {
                return null;
            }
            URLDataSource uRLDataSource = new URLDataSource(createUrl(str));
            uRLDataSource.getInputStream();
            return uRLDataSource;
        } catch (IOException e) {
            if (z) {
                return null;
            }
            throw e;
        }
    }

    protected URL createUrl(String str) throws MalformedURLException {
        if (this.baseUrl == null) {
            return new URL(str);
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("No resource defined");
        }
        if (isFileUrl(str) || isHttpUrl(str)) {
            return new URL(str);
        }
        return new URL(getBaseUrl(), str.replaceAll("&amp;", "&"));
    }
}
