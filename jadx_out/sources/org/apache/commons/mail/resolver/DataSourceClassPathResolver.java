package org.apache.commons.mail.resolver;

import java.io.IOException;
import java.io.InputStream;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.mail.util.ByteArrayDataSource;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class DataSourceClassPathResolver extends DataSourceBaseResolver {
    private final String classPathBase;

    public DataSourceClassPathResolver() {
        this.classPathBase = InternalZipConstants.ZIP_FILE_SEPARATOR;
    }

    public DataSourceClassPathResolver(String str) {
        if (!str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str = str + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        this.classPathBase = str;
    }

    public DataSourceClassPathResolver(String str, boolean z) {
        super(z);
        if (!str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str = str + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        this.classPathBase = str;
    }

    public String getClassPathBase() {
        return this.classPathBase;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        return resolve(str, isLenient());
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        try {
            if (isCid(str) || isHttpUrl(str)) {
                return null;
            }
            String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(str);
            String resourceName = getResourceName(str);
            InputStream resourceAsStream = DataSourceClassPathResolver.class.getResourceAsStream(resourceName);
            if (resourceAsStream != null) {
                ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(resourceAsStream, contentType);
                byteArrayDataSource.setName(DataSourceClassPathResolver.class.getResource(resourceName).toString());
                return byteArrayDataSource;
            }
            if (z) {
                return null;
            }
            throw new IOException("The following class path resource was not found : " + str);
        } catch (IOException e) {
            if (z) {
                return null;
            }
            throw e;
        }
    }

    private String getResourceName(String str) {
        return (getClassPathBase() + str).replaceAll("//", InternalZipConstants.ZIP_FILE_SEPARATOR);
    }
}
