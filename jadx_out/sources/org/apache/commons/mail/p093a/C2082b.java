package org.apache.commons.mail.p093a;

import java.io.IOException;
import java.io.InputStream;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.mail.util.ByteArrayDataSource;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: org.apache.commons.mail.a.b */
/* JADX INFO: compiled from: DataSourceClassPathResolver.java */
/* JADX INFO: loaded from: classes.dex */
public class C2082b extends AbstractC2081a {

    /* JADX INFO: renamed from: b */
    private final String f5054b;

    public C2082b() {
        this.f5054b = InternalZipConstants.ZIP_FILE_SEPARATOR;
    }

    private C2082b(String str) {
        if (!str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str = str + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        this.f5054b = str;
    }

    private C2082b(String str, boolean z) {
        super(z);
        if (!str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str = str + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        this.f5054b = str;
    }

    /* JADX INFO: renamed from: a */
    private String m5111a() {
        return this.f5054b;
    }

    /* JADX INFO: renamed from: b */
    private String m5112b(String str) {
        return (this.f5054b + str).replaceAll("//", InternalZipConstants.ZIP_FILE_SEPARATOR);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        return resolve(str, this.f5053a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        try {
            if (str.startsWith("cid:") || m5108a(str)) {
                return null;
            }
            String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(str);
            String strReplaceAll = (this.f5054b + str).replaceAll("//", InternalZipConstants.ZIP_FILE_SEPARATOR);
            InputStream resourceAsStream = C2082b.class.getResourceAsStream(strReplaceAll);
            if (resourceAsStream != null) {
                ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(resourceAsStream, contentType);
                byteArrayDataSource.setName(C2082b.class.getResource(strReplaceAll).toString());
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
}
