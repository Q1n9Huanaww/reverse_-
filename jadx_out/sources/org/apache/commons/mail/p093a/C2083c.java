package org.apache.commons.mail.p093a;

import java.io.IOException;
import javax.activation.DataSource;
import org.apache.commons.mail.DataSourceResolver;

/* JADX INFO: renamed from: org.apache.commons.mail.a.c */
/* JADX INFO: compiled from: DataSourceCompositeResolver.java */
/* JADX INFO: loaded from: classes.dex */
public final class C2083c extends AbstractC2081a {

    /* JADX INFO: renamed from: b */
    private final DataSourceResolver[] f5055b;

    private C2083c(DataSourceResolver[] dataSourceResolverArr) {
        this.f5055b = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.f5055b, 0, dataSourceResolverArr.length);
    }

    private C2083c(DataSourceResolver[] dataSourceResolverArr, boolean z) {
        super(z);
        this.f5055b = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.f5055b, 0, dataSourceResolverArr.length);
    }

    /* JADX INFO: renamed from: a */
    private DataSourceResolver[] m5113a() {
        DataSourceResolver[] dataSourceResolverArr = new DataSourceResolver[this.f5055b.length];
        System.arraycopy(this.f5055b, 0, dataSourceResolverArr, 0, this.f5055b.length);
        return dataSourceResolverArr;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        DataSource dataSourceResolve = resolve(str, true);
        if (this.f5053a || dataSourceResolve != null) {
            return dataSourceResolve;
        }
        throw new IOException("The following resource was not found : " + str);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        for (int i = 0; i < m5113a().length; i++) {
            DataSource dataSourceResolve = m5113a()[i].resolve(str, z);
            if (dataSourceResolve != null) {
                return dataSourceResolve;
            }
        }
        if (z) {
            return null;
        }
        throw new IOException("The following resource was not found : " + str);
    }
}
