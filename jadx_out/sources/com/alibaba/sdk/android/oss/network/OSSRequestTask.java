package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.OSSRetryType;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.internal.ResponseParsers;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0338  */
    /* JADX WARN: Code duplicated, block: B:103:0x0346  */
    /* JADX WARN: Code duplicated, block: B:105:0x034a  */
    /* JADX WARN: Code duplicated, block: B:107:0x0352  */
    /* JADX WARN: Code duplicated, block: B:108:0x0365  */
    /* JADX WARN: Code duplicated, block: B:110:0x036d  */
    /* JADX WARN: Code duplicated, block: B:114:0x028c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:121:0x029a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x023e  */
    /* JADX WARN: Code duplicated, block: B:62:0x024d  */
    /* JADX WARN: Code duplicated, block: B:73:0x028a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:89:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:91:0x0306  */
    /* JADX WARN: Code duplicated, block: B:94:0x0314  */
    /* JADX WARN: Code duplicated, block: B:96:0x0318 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x031a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v4, types: [okhttp3.Call] */
    @Override // java.util.concurrent.Callable
    public T call() throws Exception {
        Request requestBuild;
        Response responseExecute;
        Object obj;
        Exception clientException;
        ?? r2;
        T t;
        Exception clientException2;
        OSSRetryType oSSRetryTypeShouldRetry;
        InputStream uploadInputStream;
        try {
            if (this.context.getApplicationContext() != null) {
                OSSLog.logInfo(OSSUtils.buildBaseLogInfo(this.context.getApplicationContext()));
            }
            OSSLog.logDebug("[call] - ");
            OSSRequest request = this.context.getRequest();
            OSSUtils.ensureRequestValid(request, this.message);
            OSSUtils.signRequest(this.message);
            if (this.context.getCancellationHandler().isCancelled()) {
                throw new InterruptedIOException("This task is cancelled!");
            }
            Request.Builder builderUrl = new Request.Builder().url(this.message.buildCanonicalURL());
            for (String str : this.message.getHeaders().keySet()) {
                builderUrl = builderUrl.addHeader(str, this.message.getHeaders().get(str));
            }
            String str2 = this.message.getHeaders().get("Content-Type");
            switch (this.message.getMethod()) {
                case POST:
                case PUT:
                    OSSUtils.assertTrue(str2 != null, "Content type can't be null when upload!");
                    long readStreamLength = 0;
                    if (this.message.getUploadData() != null) {
                        uploadInputStream = new ByteArrayInputStream(this.message.getUploadData());
                        readStreamLength = this.message.getUploadData().length;
                    } else if (this.message.getUploadFilePath() != null) {
                        File file = new File(this.message.getUploadFilePath());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        long length = file.length();
                        uploadInputStream = fileInputStream;
                        readStreamLength = length;
                    } else if (this.message.getUploadInputStream() != null) {
                        uploadInputStream = this.message.getUploadInputStream();
                        readStreamLength = this.message.getReadStreamLength();
                    } else {
                        uploadInputStream = null;
                    }
                    if (uploadInputStream != null) {
                        builderUrl = builderUrl.method(this.message.getMethod().toString(), NetworkProgressHelper.addProgressRequestBody(uploadInputStream, readStreamLength, str2, this.context));
                    } else {
                        builderUrl = builderUrl.method(this.message.getMethod().toString(), RequestBody.create((MediaType) null, new byte[0]));
                    }
                    break;
                case GET:
                    builderUrl = builderUrl.get();
                    break;
                case HEAD:
                    builderUrl = builderUrl.head();
                    break;
                case DELETE:
                    builderUrl = builderUrl.delete();
                    break;
            }
            requestBuild = builderUrl.build();
            try {
                if (request instanceof GetObjectRequest) {
                    this.client = NetworkProgressHelper.addProgressResponseListener(this.client, this.context);
                    OSSLog.logDebug("getObject");
                }
                Call callNewCall = this.client.newCall(requestBuild);
                try {
                    this.context.getCancellationHandler().setCall(callNewCall);
                    responseExecute = callNewCall.execute();
                    try {
                        Map<String, List<String>> multimap = responseExecute.headers().toMultimap();
                        StringBuilder sb = new StringBuilder();
                        sb.append("response:---------------------\n");
                        sb.append("response code: " + responseExecute.code() + " for url: " + requestBuild.url() + "\n");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("response msg: ");
                        sb2.append(responseExecute.message());
                        sb2.append("\n");
                        sb.append(sb2.toString());
                        for (String str3 : multimap.keySet()) {
                            sb.append("responseHeader [" + str3 + "]: ");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(multimap.get(str3).get(0));
                            sb3.append("\n");
                            sb.append(sb3.toString());
                        }
                        OSSLog.logDebug(sb.toString());
                        clientException = null;
                        r2 = callNewCall;
                    } catch (Exception e) {
                        obj = callNewCall;
                        e = e;
                        OSSLog.logError("Encounter local execpiton: " + e.toString());
                        if (OSSLog.isEnableLog()) {
                            ThrowableExtension.printStackTrace(e);
                        }
                        clientException = new ClientException(e.getMessage(), e);
                        r2 = obj;
                    }
                } catch (Exception e2) {
                    obj = callNewCall;
                    e = e2;
                    responseExecute = null;
                }
            } catch (Exception e3) {
                e = e3;
                responseExecute = null;
                obj = responseExecute;
                OSSLog.logError("Encounter local execpiton: " + e.toString());
                if (OSSLog.isEnableLog()) {
                    ThrowableExtension.printStackTrace(e);
                }
                clientException = new ClientException(e.getMessage(), e);
                r2 = obj;
                if (responseExecute != null) {
                    try {
                        DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(responseExecute.header("Date")).getTime());
                    } catch (Exception unused) {
                    }
                }
                if (clientException != null) {
                    if (clientException == null) {
                        try {
                            t = (T) this.responseParser.parse(responseExecute);
                            if (this.context.getCompletedCallback() != null) {
                                try {
                                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), t);
                                } catch (Exception unused2) {
                                }
                            }
                            return t;
                        } catch (IOException e4) {
                            clientException = new ClientException(e4.getMessage(), e4);
                        }
                    }
                } else if (clientException == null) {
                    t = (T) this.responseParser.parse(responseExecute);
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onSuccess(this.context.getRequest(), t);
                    }
                    return t;
                }
                clientException2 = r2 == 0 ? clientException : clientException;
                oSSRetryTypeShouldRetry = this.retryHandler.shouldRetry(clientException2, this.currentRetryCount);
                OSSLog.logError("[run] - retry, retry type: " + oSSRetryTypeShouldRetry);
                if (oSSRetryTypeShouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                    this.currentRetryCount++;
                    if (this.context.getRetryCallback() != null) {
                        this.context.getRetryCallback().onRetryCallback();
                    }
                    return (T) call();
                }
                if (oSSRetryTypeShouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                    if (responseExecute != null) {
                        this.message.getHeaders().put("Date", responseExecute.header("Date"));
                    }
                    this.currentRetryCount++;
                    if (this.context.getRetryCallback() != null) {
                        this.context.getRetryCallback().onRetryCallback();
                    }
                    return (T) call();
                }
                if (clientException2 instanceof ClientException) {
                    if (this.context.getCompletedCallback() != null) {
                        this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) clientException2, null);
                        throw clientException2;
                    }
                    throw clientException2;
                }
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) clientException2);
                    throw clientException2;
                }
                throw clientException2;
            }
            if (responseExecute != null) {
                DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date(responseExecute.header("Date")).getTime());
            }
            if (clientException != null && (responseExecute.code() == 203 || responseExecute.code() >= 300)) {
                try {
                    clientException = ResponseParsers.parseResponseErrorXML(responseExecute, requestBuild.method().equals("HEAD"));
                } catch (IOException e5) {
                    clientException = new ClientException(e5.getMessage(), e5);
                }
            } else if (clientException == null) {
                t = (T) this.responseParser.parse(responseExecute);
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onSuccess(this.context.getRequest(), t);
                }
                return t;
            }
            if ((r2 == 0 && r2.isCanceled()) || this.context.getCancellationHandler().isCancelled()) {
                clientException2 = new ClientException("Task is cancelled!", clientException.getCause(), true);
            }
            oSSRetryTypeShouldRetry = this.retryHandler.shouldRetry(clientException2, this.currentRetryCount);
            OSSLog.logError("[run] - retry, retry type: " + oSSRetryTypeShouldRetry);
            if (oSSRetryTypeShouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                this.currentRetryCount++;
                if (this.context.getRetryCallback() != null) {
                    this.context.getRetryCallback().onRetryCallback();
                }
                return (T) call();
            }
            if (oSSRetryTypeShouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
                if (responseExecute != null) {
                    this.message.getHeaders().put("Date", responseExecute.header("Date"));
                }
                this.currentRetryCount++;
                if (this.context.getRetryCallback() != null) {
                    this.context.getRetryCallback().onRetryCallback();
                }
                return (T) call();
            }
            if (clientException2 instanceof ClientException) {
                if (this.context.getCompletedCallback() != null) {
                    this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) clientException2, null);
                    throw clientException2;
                }
                throw clientException2;
            }
            if (this.context.getCompletedCallback() != null) {
                this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) clientException2);
                throw clientException2;
            }
            throw clientException2;
        } catch (Exception e6) {
            e = e6;
            requestBuild = null;
            responseExecute = null;
        }
    }
}
