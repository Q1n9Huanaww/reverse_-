package com.lidroid.xutils.http.client.multipart.content;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.lidroid.xutils.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class InputStreamBody extends AbstractContentBody {
    private final String filename;

    /* JADX INFO: renamed from: in */
    private final InputStream f3594in;
    private long length;

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public InputStreamBody(InputStream inputStream, long j, String str, String str2) {
        super(str2);
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        this.f3594in = inputStream;
        this.filename = str;
        this.length = j;
    }

    public InputStreamBody(InputStream inputStream, long j, String str) {
        this(inputStream, j, str, OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
    }

    public InputStreamBody(InputStream inputStream, long j) {
        this(inputStream, j, "no_name", OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
    }

    public InputStream getInputStream() {
        return this.f3594in;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            byte[] bArr = new byte[4096];
            do {
                int i = this.f3594in.read(bArr);
                if (i != -1) {
                    outputStream.write(bArr, 0, i);
                    this.callBackInfo.pos += (long) i;
                } else {
                    outputStream.flush();
                    IOUtils.closeQuietly(this.f3594in);
                    return;
                }
            } while (this.callBackInfo.doCallBack(false));
            throw new InterruptedIOException("cancel");
        } catch (Throwable th) {
            IOUtils.closeQuietly(this.f3594in);
            throw th;
        }
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.length;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }
}
