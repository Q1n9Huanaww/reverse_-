package com.sun.mail.dsn;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeUtility;
import myjava.awt.datatransfer.DataFlavor;

/* JADX INFO: loaded from: classes.dex */
public class text_rfc822headers implements DataContentHandler {
    private static ActivationDataFlavor myDF = new ActivationDataFlavor(MessageHeaders.class, "text/rfc822-headers", "RFC822 headers");
    private static ActivationDataFlavor myDFs = new ActivationDataFlavor(String.class, "text/rfc822-headers", "RFC822 headers");

    @Override // javax.activation.DataContentHandler
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{myDF, myDFs};
    }

    @Override // javax.activation.DataContentHandler
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (myDF.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        if (myDFs.equals(dataFlavor)) {
            return getStringContent(dataSource);
        }
        return null;
    }

    @Override // javax.activation.DataContentHandler
    public Object getContent(DataSource dataSource) throws IOException {
        try {
            return new MessageHeaders(dataSource.getInputStream());
        } catch (MessagingException e) {
            throw new IOException("Exception creating MessageHeaders: " + e);
        }
    }

    private Object getStringContent(DataSource dataSource) throws IOException {
        String charset;
        try {
            charset = getCharset(dataSource.getContentType());
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(dataSource.getInputStream(), charset);
                char[] cArr = new char[1024];
                int i = 0;
                while (true) {
                    int i2 = inputStreamReader.read(cArr, i, cArr.length - i);
                    if (i2 != -1) {
                        i += i2;
                        if (i >= cArr.length) {
                            int length = cArr.length;
                            char[] cArr2 = new char[length < 262144 ? length + length : length + 262144];
                            System.arraycopy(cArr, 0, cArr2, 0, i);
                            cArr = cArr2;
                        }
                    } else {
                        return new String(cArr, 0, i);
                    }
                }
            } catch (IllegalArgumentException unused) {
                throw new UnsupportedEncodingException(charset);
            }
        } catch (IllegalArgumentException unused2) {
            charset = null;
        }
    }

    @Override // javax.activation.DataContentHandler
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        String charset;
        if (obj instanceof MessageHeaders) {
            try {
                ((MessageHeaders) obj).writeTo(outputStream);
                return;
            } catch (MessagingException e) {
                Exception nextException = e.getNextException();
                if (nextException instanceof IOException) {
                    throw ((IOException) nextException);
                }
                throw new IOException("Exception writing headers: " + e);
            }
        }
        if (!(obj instanceof String)) {
            throw new IOException("\"" + myDFs.getMimeType() + "\" DataContentHandler requires String object, was given object of type " + obj.getClass().toString());
        }
        try {
            charset = getCharset(str);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
                String str2 = (String) obj;
                outputStreamWriter.write(str2, 0, str2.length());
                outputStreamWriter.flush();
            } catch (IllegalArgumentException unused) {
                throw new UnsupportedEncodingException(charset);
            }
        } catch (IllegalArgumentException unused2) {
            charset = null;
        }
    }

    private String getCharset(String str) {
        try {
            String parameter = new ContentType(str).getParameter(HttpRequest.PARAM_CHARSET);
            if (parameter == null) {
                parameter = "us-ascii";
            }
            return MimeUtility.javaCharset(parameter);
        } catch (Exception unused) {
            return null;
        }
    }
}
