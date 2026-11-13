package org.apache.commons.mail.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;
import javax.mail.util.ByteArrayDataSource;

/* JADX INFO: loaded from: classes.dex */
public class MimeMessageParser {
    private String htmlContent;
    private final MimeMessage mimeMessage;
    private String plainContent;
    private final List<DataSource> attachmentList = new ArrayList();
    private final Map<String, DataSource> cidMap = new HashMap();
    private boolean isMultiPart = false;

    public MimeMessageParser(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public MimeMessageParser parse() throws Exception {
        parse(null, this.mimeMessage);
        return this;
    }

    public List<Address> getTo() throws Exception {
        Address[] recipients = this.mimeMessage.getRecipients(Message.RecipientType.f4740TO);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    public List<Address> getCc() throws Exception {
        Address[] recipients = this.mimeMessage.getRecipients(Message.RecipientType.f4739CC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    public List<Address> getBcc() throws Exception {
        Address[] recipients = this.mimeMessage.getRecipients(Message.RecipientType.BCC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    public String getFrom() throws Exception {
        Address[] from = this.mimeMessage.getFrom();
        if (from == null || from.length == 0) {
            return null;
        }
        return ((InternetAddress) from[0]).getAddress();
    }

    public String getReplyTo() throws Exception {
        Address[] replyTo = this.mimeMessage.getReplyTo();
        if (replyTo == null || replyTo.length == 0) {
            return null;
        }
        return ((InternetAddress) replyTo[0]).getAddress();
    }

    public String getSubject() throws Exception {
        return this.mimeMessage.getSubject();
    }

    protected void parse(Multipart multipart, MimePart mimePart) throws MessagingException, IOException {
        if (isMimeType(mimePart, "text/plain") && this.plainContent == null && !"attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            this.plainContent = (String) mimePart.getContent();
            return;
        }
        if (isMimeType(mimePart, "text/html") && this.htmlContent == null && !"attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            this.htmlContent = (String) mimePart.getContent();
            return;
        }
        if (isMimeType(mimePart, "multipart/*")) {
            this.isMultiPart = true;
            Multipart multipart2 = (Multipart) mimePart.getContent();
            int count = multipart2.getCount();
            for (int i = 0; i < count; i++) {
                parse(multipart2, (MimeBodyPart) multipart2.getBodyPart(i));
            }
            return;
        }
        String strStripContentId = stripContentId(mimePart.getContentID());
        DataSource dataSourceCreateDataSource = createDataSource(multipart, mimePart);
        if (strStripContentId != null) {
            this.cidMap.put(strStripContentId, dataSourceCreateDataSource);
        }
        this.attachmentList.add(dataSourceCreateDataSource);
    }

    private String stripContentId(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("[\\<\\>]", "");
    }

    private boolean isMimeType(MimePart mimePart, String str) throws MessagingException, IOException {
        try {
            return new ContentType(mimePart.getDataHandler().getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    protected DataSource createDataSource(Multipart multipart, MimePart mimePart) throws MessagingException, IOException {
        DataSource dataSource = mimePart.getDataHandler().getDataSource();
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(getContent(dataSource.getInputStream()), getBaseMimeType(dataSource.getContentType()));
        byteArrayDataSource.setName(getDataSourceName(mimePart, dataSource));
        return byteArrayDataSource;
    }

    public MimeMessage getMimeMessage() {
        return this.mimeMessage;
    }

    public boolean isMultipart() {
        return this.isMultiPart;
    }

    public String getPlainContent() {
        return this.plainContent;
    }

    public List<DataSource> getAttachmentList() {
        return this.attachmentList;
    }

    public Collection<String> getContentIds() {
        return Collections.unmodifiableSet(this.cidMap.keySet());
    }

    public String getHtmlContent() {
        return this.htmlContent;
    }

    public boolean hasPlainContent() {
        return this.plainContent != null;
    }

    public boolean hasHtmlContent() {
        return this.htmlContent != null;
    }

    public boolean hasAttachments() {
        return this.attachmentList.size() > 0;
    }

    public DataSource findAttachmentByName(String str) {
        for (int i = 0; i < getAttachmentList().size(); i++) {
            DataSource dataSource = getAttachmentList().get(i);
            if (str.equalsIgnoreCase(dataSource.getName())) {
                return dataSource;
            }
        }
        return null;
    }

    public DataSource findAttachmentByCid(String str) {
        return this.cidMap.get(str);
    }

    protected String getDataSourceName(Part part, DataSource dataSource) throws MessagingException, UnsupportedEncodingException {
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = part.getFileName();
        }
        if (name == null || name.length() <= 0) {
            return null;
        }
        return MimeUtility.decodeText(name);
    }

    private byte[] getContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        while (true) {
            int i = bufferedInputStream.read();
            if (i != -1) {
                bufferedOutputStream.write(i);
            } else {
                bufferedOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bufferedOutputStream.close();
                return byteArray;
            }
        }
    }

    private String getBaseMimeType(String str) {
        int iIndexOf = str.indexOf(59);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : str;
    }
}
