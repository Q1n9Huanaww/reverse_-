package javax.mail.util;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;

/* JADX INFO: loaded from: classes.dex */
public class ByteArrayDataSource implements DataSource {
    private byte[] data;
    private int len;
    private String name;
    private String type;

    static class DSByteArrayOutputStream extends ByteArrayOutputStream {
        DSByteArrayOutputStream() {
        }

        public byte[] getBuf() {
            return this.buf;
        }

        public int getCount() {
            return this.count;
        }
    }

    public ByteArrayDataSource(InputStream inputStream, String str) throws IOException {
        this.len = -1;
        this.name = "";
        DSByteArrayOutputStream dSByteArrayOutputStream = new DSByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                break;
            } else {
                dSByteArrayOutputStream.write(bArr, 0, i);
            }
        }
        this.data = dSByteArrayOutputStream.getBuf();
        this.len = dSByteArrayOutputStream.getCount();
        if (this.data.length - this.len > 262144) {
            this.data = dSByteArrayOutputStream.toByteArray();
            this.len = this.data.length;
        }
        this.type = str;
    }

    public ByteArrayDataSource(byte[] bArr, String str) {
        this.len = -1;
        this.name = "";
        this.data = bArr;
        this.type = str;
    }

    public ByteArrayDataSource(String str, String str2) throws IOException {
        String parameter;
        this.len = -1;
        this.name = "";
        try {
            parameter = new ContentType(str2).getParameter(HttpRequest.PARAM_CHARSET);
        } catch (ParseException unused) {
            parameter = null;
        }
        this.data = str.getBytes(parameter == null ? MimeUtility.getDefaultJavaCharset() : parameter);
        this.type = str2;
    }

    @Override // javax.activation.DataSource
    public InputStream getInputStream() throws IOException {
        if (this.data == null) {
            throw new IOException("no data");
        }
        if (this.len < 0) {
            this.len = this.data.length;
        }
        return new SharedByteArrayInputStream(this.data, 0, this.len);
    }

    @Override // javax.activation.DataSource
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("cannot do this");
    }

    @Override // javax.activation.DataSource
    public String getContentType() {
        return this.type;
    }

    @Override // javax.activation.DataSource
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
