package com.cyjh.share.oss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.cyjh.share.bean.response.AliCloudServerResponse;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: loaded from: classes.dex */
public class MyOSSAuthCredentialsProvider extends OSSFederationCredentialProvider {
    private AliCloudServerResponse aliCloudServerResponse;
    private AuthDecoder mDecoder;

    public interface AuthDecoder {
        String decode(String str);
    }

    public MyOSSAuthCredentialsProvider(AliCloudServerResponse aliCloudServerResponse) {
        this.aliCloudServerResponse = aliCloudServerResponse;
    }

    public void setDecoder(AuthDecoder authDecoder) {
        this.mDecoder = authDecoder;
    }

    @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider
    public OSSFederationToken getFederationToken() {
        try {
            if (this.aliCloudServerResponse != null) {
                return new OSSFederationToken(this.aliCloudServerResponse.AliCloundAccessKeyId, this.aliCloudServerResponse.AliCloundAccessKeySecret, this.aliCloudServerResponse.AliCloundSecurityToken, this.aliCloudServerResponse.AliCloundExpireTime.longValue());
            }
            throw new ClientException("ErrorMessage: Token信息为null");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}
