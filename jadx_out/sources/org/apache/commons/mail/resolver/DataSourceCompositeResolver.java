package org.apache.commons.mail.resolver;

import java.io.IOException;
import javax.activation.DataSource;
import org.apache.commons.mail.DataSourceResolver;

/* JADX INFO: loaded from: classes.dex */
public class DataSourceCompositeResolver extends DataSourceBaseResolver {
    private final DataSourceResolver[] dataSourceResolvers;

    public DataSourceCompositeResolver(DataSourceResolver[] dataSourceResolverArr) {
        this.dataSourceResolvers = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.dataSourceResolvers, 0, dataSourceResolverArr.length);
    }

    public DataSourceCompositeResolver(DataSourceResolver[] dataSourceResolverArr, boolean z) {
        super(z);
        this.dataSourceResolvers = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.dataSourceResolvers, 0, dataSourceResolverArr.length);
    }

    public DataSourceResolver[] getDataSourceResolvers() {
        DataSourceResolver[] dataSourceResolverArr = new DataSourceResolver[this.dataSourceResolvers.length];
        System.arraycopy(this.dataSourceResolvers, 0, dataSourceResolverArr, 0, this.dataSourceResolvers.length);
        return dataSourceResolverArr;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        DataSource dataSourceResolve = resolve(str, true);
        if (isLenient() || dataSourceResolve != null) {
            return dataSourceResolve;
        }
        throw new IOException("The following resource was not found : " + str);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        for (int i = 0; i < getDataSourceResolvers().length; i++) {
            DataSource dataSourceResolve = getDataSourceResolvers()[i].resolve(str, z);
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
