package org.apache.commons.mail.p093a;

import java.io.File;
import java.io.IOException;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/* JADX INFO: renamed from: org.apache.commons.mail.a.d */
/* JADX INFO: compiled from: DataSourceFileResolver.java */
/* JADX INFO: loaded from: classes.dex */
public final class C2084d extends AbstractC2081a {

    /* JADX INFO: renamed from: b */
    private final File f5056b;

    public C2084d() {
        this.f5056b = new File(".");
    }

    private C2084d(File file) {
        this.f5056b = file;
    }

    private C2084d(File file, boolean z) {
        super(z);
        this.f5056b = file;
    }

    /* JADX INFO: renamed from: a */
    private File m5114a() {
        return this.f5056b;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        return resolve(str, this.f5053a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        if (!str.startsWith("cid:")) {
            File file = new File(str);
            if (!file.isAbsolute()) {
                file = this.f5056b != null ? new File(this.f5056b, str) : new File(str);
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
