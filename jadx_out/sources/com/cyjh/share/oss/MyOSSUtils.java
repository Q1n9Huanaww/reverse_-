package com.cyjh.share.oss;

import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.cyjh.share.bean.response.AliCloudServerResponse;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;

/* JADX INFO: loaded from: classes.dex */
public class MyOSSUtils {
    private static String TAG = "MyOSSUtils";
    private AliCloudServerResponse aliCloudServerResponse;
    private Context mContext;
    private OSS oss;

    public interface UploadFileCallBack {
        void uploadFail(String str);

        void uploadProgress(long j, long j2);

        void uploadSuc(String str, String str2);
    }

    public static class Builder {
        private AliCloudServerResponse aliCloudServerResponse;
        private Context context;
        private int connectionTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;
        private int socketTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;
        private int maxConcurrentRequest = 5;
        private int maxErrorRetry = 2;

        public Builder(Context context, AliCloudServerResponse aliCloudServerResponse) {
            this.context = context;
            this.aliCloudServerResponse = aliCloudServerResponse;
        }

        public Builder setConnectionTimeout(int i) {
            this.connectionTimeout = i;
            return this;
        }

        public Builder setSocketTimeout(int i) {
            this.socketTimeout = i;
            return this;
        }

        public Builder setMaxConcurrentRequest(int i) {
            this.maxConcurrentRequest = i;
            return this;
        }

        public Builder setMaxErrorRetry(int i) {
            this.maxErrorRetry = i;
            return this;
        }

        public MyOSSUtils build() {
            return new MyOSSUtils(this);
        }
    }

    private MyOSSUtils(Builder builder) {
        this.aliCloudServerResponse = builder.aliCloudServerResponse;
        this.mContext = builder.context;
        MyOSSAuthCredentialsProvider myOSSAuthCredentialsProvider = new MyOSSAuthCredentialsProvider(this.aliCloudServerResponse);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(builder.connectionTimeout);
        clientConfiguration.setSocketTimeout(builder.socketTimeout);
        clientConfiguration.setMaxConcurrentRequest(builder.maxConcurrentRequest);
        clientConfiguration.setMaxErrorRetry(builder.maxErrorRetry);
        this.oss = new OSSClient(this.mContext, this.aliCloudServerResponse.OssEndpoint, myOSSAuthCredentialsProvider, clientConfiguration);
    }

    public void uploadFile(String str, final String str2, final UploadFileCallBack uploadFileCallBack) {
        final File file = new File(str);
        if (file.exists()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.aliCloudServerResponse.OssBucket, str2, str);
            putObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() { // from class: com.cyjh.share.oss.MyOSSUtils.1
                @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
                public void onProgress(PutObjectRequest putObjectRequest2, long j, long j2) {
                    uploadFileCallBack.uploadProgress(j, j2);
                }
            });
            this.oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.cyjh.share.oss.MyOSSUtils.2
                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onSuccess(PutObjectRequest putObjectRequest2, PutObjectResult putObjectResult) {
                    String strPresignPublicObjectURL = MyOSSUtils.this.oss.presignPublicObjectURL(MyOSSUtils.this.aliCloudServerResponse.OssBucket, str2);
                    SlLog.m1334i(MyOSSUtils.TAG, "onSuccess --> uploadOssUrl=" + strPresignPublicObjectURL);
                    uploadFileCallBack.uploadSuc(strPresignPublicObjectURL, file.getName());
                }

                @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
                public void onFailure(PutObjectRequest putObjectRequest2, ClientException clientException, ServiceException serviceException) {
                    uploadFileCallBack.uploadFail(file.getName());
                    File errorFile = CommonUtil.getErrorFile(MyOSSUtils.this.mContext);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("message=");
                    stringBuffer.append(putObjectRequest2.toString());
                    FileUtil.writeStringToFile(errorFile, stringBuffer.toString(), "UTF-8", true);
                    if (clientException != null) {
                        SlLog.m1334i(MyOSSUtils.TAG, "uploadFile --> ex=" + clientException.getMessage());
                        stringBuffer.append("clientException=");
                        stringBuffer.append(clientException.getMessage());
                        stringBuffer.append("\n");
                        Log.e("zzz", "MyOSSUtils--uploadImage--clientException" + clientException.getMessage());
                        ThrowableExtension.printStackTrace(clientException);
                        FileUtil.writeStringToFile(errorFile, stringBuffer.toString(), "UTF-8", true);
                    }
                    if (serviceException != null) {
                        Log.e("uploadImage--ErrorCode", serviceException.getErrorCode());
                        Log.e("uploadImage--RequestId", serviceException.getRequestId());
                        Log.e("uploadImage--HostId", serviceException.getHostId());
                        Log.e("uploadImage--RawMessage", serviceException.getRawMessage());
                        stringBuffer.append("ErrorCode=");
                        stringBuffer.append(serviceException.getErrorCode());
                        stringBuffer.append("\n");
                        stringBuffer.append("RequestId=");
                        stringBuffer.append(serviceException.getRequestId());
                        stringBuffer.append("\n");
                        stringBuffer.append("HostId=");
                        stringBuffer.append(serviceException.getHostId());
                        stringBuffer.append("\n");
                        stringBuffer.append("RawMessage=");
                        stringBuffer.append(serviceException.getRawMessage());
                        stringBuffer.append("\n\n\n");
                        FileUtil.writeStringToFile(errorFile, stringBuffer.toString(), "UTF-8", true);
                    }
                }
            });
        }
    }

    public boolean isTokenExpireTime() {
        try {
            if (this.aliCloudServerResponse != null) {
                return this.aliCloudServerResponse.AliCloundExpireTime.longValue() - Long.valueOf(System.currentTimeMillis() / 1000).longValue() < 3;
            }
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return true;
        }
    }
}
