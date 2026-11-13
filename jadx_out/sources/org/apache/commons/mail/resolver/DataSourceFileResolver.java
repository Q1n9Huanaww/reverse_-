package org.apache.commons.mail.resolver;

import java.io.File;
import java.io.IOException;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/* JADX INFO: loaded from: classes.dex */
public class DataSourceFileResolver extends DataSourceBaseResolver {
    private final File baseDir;

    public DataSourceFileResolver() {
        this.baseDir = new File(".");
    }

    public DataSourceFileResolver(File file) {
        this.baseDir = file;
    }

    public DataSourceFileResolver(File file, boolean z) {
        super(z);
        this.baseDir = file;
    }

    public File getBaseDir() {
        return this.baseDir;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        return resolve(str, isLenient());
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        if (!isCid(str)) {
            File file = new File(str);
            if (!file.isAbsolute()) {
                file = getBaseDir() != null ? new File(getBaseDir(), str) : new File(str);
            }
            if (file.exists()) {
                return new FileDataSource(file);
            }
            if (!z) {
                throw new IOException("Cant resolve the following file resource :" + file.getAbsolutePath());
            }
        }
        return null;
    }
}
