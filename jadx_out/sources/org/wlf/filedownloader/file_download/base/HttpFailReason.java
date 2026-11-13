package org.wlf.filedownloader.file_download.base;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.wlf.filedownloader.base.UrlFailReason;

/* JADX INFO: loaded from: classes2.dex */
public class HttpFailReason extends UrlFailReason {
    public static final String TYPE_NETWORK_DENIED = HttpFailReason.class.getName() + "_TYPE_NETWORK_DENIED";
    public static final String TYPE_NETWORK_TIMEOUT = HttpFailReason.class.getName() + "_TYPE_NETWORK_TIMEOUT";

    public HttpFailReason(String url, String detailMessage, String type) {
        super(url, detailMessage, type);
    }

    public HttpFailReason(String url, Throwable throwable) {
        super(url, throwable);
    }

    @Override // org.wlf.filedownloader.base.FailReason
    protected void onInitTypeWithOriginalThrowable(Throwable throwable) {
        super.onInitTypeWithOriginalThrowable(throwable);
        if (throwable != null) {
            if (throwable instanceof SocketTimeoutException) {
                setType(TYPE_NETWORK_TIMEOUT);
                return;
            }
            if ((throwable instanceof ConnectException) || (throwable instanceof UnknownHostException)) {
                setType(TYPE_NETWORK_DENIED);
            } else if (throwable instanceof SocketException) {
                setType(TYPE_NETWORK_DENIED);
            }
        }
    }
}
