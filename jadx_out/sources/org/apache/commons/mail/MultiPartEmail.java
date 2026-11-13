package org.apache.commons.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;

/* JADX INFO: loaded from: classes.dex */
public class MultiPartEmail extends Email {
    private boolean boolHasAttachments;
    private MimeMultipart container;
    private boolean initialized;
    private BodyPart primaryBodyPart;
    private String subType;

    public void setSubType(String str) {
        this.subType = str;
    }

    public String getSubType() {
        return this.subType;
    }

    public Email addPart(String str, String str2) throws EmailException {
        BodyPart bodyPartCreateBodyPart = createBodyPart();
        try {
            bodyPartCreateBodyPart.setContent(str, str2);
            getContainer().addBodyPart(bodyPartCreateBodyPart);
            return this;
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    public Email addPart(MimeMultipart mimeMultipart) throws EmailException {
        try {
            return addPart(mimeMultipart, getContainer().getCount());
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    public Email addPart(MimeMultipart mimeMultipart, int i) throws EmailException {
        BodyPart bodyPartCreateBodyPart = createBodyPart();
        try {
            bodyPartCreateBodyPart.setContent(mimeMultipart);
            getContainer().addBodyPart(bodyPartCreateBodyPart, i);
            return this;
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    protected void init() {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized");
        }
        this.container = createMimeMultipart();
        super.setContent(this.container);
        this.initialized = true;
    }

    @Override // org.apache.commons.mail.Email
    public Email setMsg(String str) throws EmailException {
        if (EmailUtils.isEmpty(str)) {
            throw new EmailException("Invalid message supplied");
        }
        try {
            Part primaryBodyPart = getPrimaryBodyPart();
            if ((primaryBodyPart instanceof MimePart) && EmailUtils.isNotEmpty(this.charset)) {
                ((MimePart) primaryBodyPart).setText(str, this.charset);
            } else {
                primaryBodyPart.setText(str);
            }
            return this;
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    @Override // org.apache.commons.mail.Email
    public void buildMimeMessage() throws EmailException {
        try {
            if (this.primaryBodyPart != null) {
                try {
                    getPrimaryBodyPart().getContent();
                } catch (IOException unused) {
                }
            }
            if (this.subType != null) {
                getContainer().setSubType(this.subType);
            }
            super.buildMimeMessage();
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    public MultiPartEmail attach(File file) throws EmailException {
        String absolutePath = file.getAbsolutePath();
        try {
            if (!file.exists()) {
                throw new IOException("\"" + absolutePath + "\" does not exist");
            }
            return attach(new FileDataSource(file), file.getName(), (String) null, "attachment");
        } catch (IOException e) {
            throw new EmailException("Cannot attach file \"" + absolutePath + "\"", e);
        }
    }

    public MultiPartEmail attach(EmailAttachment emailAttachment) throws EmailException {
        String path;
        if (emailAttachment == null) {
            throw new EmailException("Invalid attachment supplied");
        }
        URL url = emailAttachment.getURL();
        if (url == null) {
            try {
                path = emailAttachment.getPath();
                try {
                    File file = new File(path);
                    if (!file.exists()) {
                        throw new IOException("\"" + path + "\" does not exist");
                    }
                    return attach(new FileDataSource(file), emailAttachment.getName(), emailAttachment.getDescription(), emailAttachment.getDisposition());
                } catch (IOException e) {
                    e = e;
                    throw new EmailException("Cannot attach file \"" + path + "\"", e);
                }
            } catch (IOException e2) {
                e = e2;
                path = null;
            }
        } else {
            return attach(url, emailAttachment.getName(), emailAttachment.getDescription(), emailAttachment.getDisposition());
        }
    }

    public MultiPartEmail attach(URL url, String str, String str2) throws EmailException {
        return attach(url, str, str2, "attachment");
    }

    public MultiPartEmail attach(URL url, String str, String str2, String str3) throws EmailException {
        try {
            url.openStream().close();
            return attach(new URLDataSource(url), str, str2, str3);
        } catch (IOException e) {
            throw new EmailException("Invalid URL set:" + url, e);
        }
    }

    public MultiPartEmail attach(DataSource dataSource, String str, String str2) throws EmailException {
        InputStream inputStream;
        if (dataSource != null) {
            try {
                inputStream = dataSource.getInputStream();
            } catch (IOException e) {
                throw new EmailException("Invalid Datasource", e);
            }
        } else {
            inputStream = null;
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (inputStream == null) {
            throw new EmailException("Invalid Datasource");
        }
        return attach(dataSource, str, str2, "attachment");
    }

    public MultiPartEmail attach(DataSource dataSource, String str, String str2, String str3) throws EmailException {
        if (EmailUtils.isEmpty(str)) {
            str = dataSource.getName();
        }
        BodyPart bodyPartCreateBodyPart = createBodyPart();
        try {
            bodyPartCreateBodyPart.setDisposition(str3);
            bodyPartCreateBodyPart.setFileName(MimeUtility.encodeText(str));
            bodyPartCreateBodyPart.setDescription(str2);
            bodyPartCreateBodyPart.setDataHandler(new DataHandler(dataSource));
            getContainer().addBodyPart(bodyPartCreateBodyPart);
            setBoolHasAttachments(true);
            return this;
        } catch (UnsupportedEncodingException e) {
            throw new EmailException(e);
        } catch (MessagingException e2) {
            throw new EmailException(e2);
        }
    }

    protected BodyPart getPrimaryBodyPart() throws MessagingException {
        if (!this.initialized) {
            init();
        }
        if (this.primaryBodyPart == null) {
            this.primaryBodyPart = createBodyPart();
            getContainer().addBodyPart(this.primaryBodyPart, 0);
        }
        return this.primaryBodyPart;
    }

    protected MimeMultipart getContainer() {
        if (!this.initialized) {
            init();
        }
        return this.container;
    }

    protected BodyPart createBodyPart() {
        return new MimeBodyPart();
    }

    protected MimeMultipart createMimeMultipart() {
        return new MimeMultipart();
    }

    public boolean isBoolHasAttachments() {
        return this.boolHasAttachments;
    }

    public void setBoolHasAttachments(boolean z) {
        this.boolHasAttachments = z;
    }

    protected boolean isInitialized() {
        return this.initialized;
    }

    protected void setInitialized(boolean z) {
        this.initialized = z;
    }
}
