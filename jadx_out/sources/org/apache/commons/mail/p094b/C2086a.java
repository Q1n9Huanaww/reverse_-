package org.apache.commons.mail.p094b;

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

/* JADX INFO: renamed from: org.apache.commons.mail.b.a */
/* JADX INFO: compiled from: MimeMessageParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C2086a {

    /* JADX INFO: renamed from: a */
    private final MimeMessage f5058a;

    /* JADX INFO: renamed from: b */
    private String f5059b;

    /* JADX INFO: renamed from: c */
    private String f5060c;

    /* JADX INFO: renamed from: d */
    private final List<DataSource> f5061d = new ArrayList();

    /* JADX INFO: renamed from: e */
    private final Map<String, DataSource> f5062e = new HashMap();

    /* JADX INFO: renamed from: f */
    private boolean f5063f = false;

    private C2086a(MimeMessage mimeMessage) {
        this.f5058a = mimeMessage;
    }

    /* JADX INFO: renamed from: a */
    private C2086a m5119a() throws Exception {
        m5120a(this.f5058a);
        return this;
    }

    /* JADX INFO: renamed from: b */
    private List<Address> m5123b() throws Exception {
        Address[] recipients = this.f5058a.getRecipients(Message.RecipientType.f4740TO);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* JADX INFO: renamed from: c */
    private List<Address> m5126c() throws Exception {
        Address[] recipients = this.f5058a.getRecipients(Message.RecipientType.f4739CC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* JADX INFO: renamed from: d */
    private List<Address> m5129d() throws Exception {
        Address[] recipients = this.f5058a.getRecipients(Message.RecipientType.BCC);
        return recipients != null ? Arrays.asList(recipients) : new ArrayList();
    }

    /* JADX INFO: renamed from: e */
    private String m5130e() throws Exception {
        Address[] from = this.f5058a.getFrom();
        if (from == null || from.length == 0) {
            return null;
        }
        return ((InternetAddress) from[0]).getAddress();
    }

    /* JADX INFO: renamed from: f */
    private String m5131f() throws Exception {
        Address[] replyTo = this.f5058a.getReplyTo();
        if (replyTo == null || replyTo.length == 0) {
            return null;
        }
        return ((InternetAddress) replyTo[0]).getAddress();
    }

    /* JADX INFO: renamed from: g */
    private String m5132g() throws Exception {
        return this.f5058a.getSubject();
    }

    /* JADX INFO: renamed from: a */
    private void m5120a(MimePart mimePart) throws MessagingException, IOException {
        if (m5121a(mimePart, "text/plain") && this.f5059b == null && !"attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            this.f5059b = (String) mimePart.getContent();
            return;
        }
        if (m5121a(mimePart, "text/html") && this.f5060c == null && !"attachment".equalsIgnoreCase(mimePart.getDisposition())) {
            this.f5060c = (String) mimePart.getContent();
            return;
        }
        if (m5121a(mimePart, "multipart/*")) {
            this.f5063f = true;
            Multipart multipart = (Multipart) mimePart.getContent();
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                m5120a((MimeBodyPart) multipart.getBodyPart(i));
            }
            return;
        }
        String contentID = mimePart.getContentID();
        String strDecodeText = null;
        String strReplaceAll = contentID == null ? null : contentID.trim().replaceAll("[\\<\\>]", "");
        DataSource dataSource = mimePart.getDataHandler().getDataSource();
        String contentType = dataSource.getContentType();
        int iIndexOf = contentType.indexOf(59);
        if (iIndexOf >= 0) {
            contentType = contentType.substring(0, iIndexOf);
        }
        InputStream inputStream = dataSource.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        while (true) {
            int i2 = bufferedInputStream.read();
            if (i2 == -1) {
                break;
            } else {
                bufferedOutputStream.write(i2);
            }
        }
        bufferedOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bufferedOutputStream.close();
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray, contentType);
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = mimePart.getFileName();
        }
        if (name != null && name.length() > 0) {
            strDecodeText = MimeUtility.decodeText(name);
        }
        byteArrayDataSource.setName(strDecodeText);
        if (strReplaceAll != null) {
            this.f5062e.put(strReplaceAll, byteArrayDataSource);
        }
        this.f5061d.add(byteArrayDataSource);
    }

    /* JADX INFO: renamed from: a */
    private static String m5117a(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("[\\<\\>]", "");
    }

    /* JADX INFO: renamed from: a */
    private static boolean m5121a(MimePart mimePart, String str) throws MessagingException, IOException {
        try {
            return new ContentType(mimePart.getDataHandler().getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    /* JADX INFO: renamed from: b */
    private static DataSource m5125b(MimePart mimePart) throws MessagingException, IOException {
        DataSource dataSource = mimePart.getDataHandler().getDataSource();
        String contentType = dataSource.getContentType();
        int iIndexOf = contentType.indexOf(59);
        if (iIndexOf >= 0) {
            contentType = contentType.substring(0, iIndexOf);
        }
        InputStream inputStream = dataSource.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        while (true) {
            int i = bufferedInputStream.read();
            if (i == -1) {
                break;
            }
            bufferedOutputStream.write(i);
        }
        bufferedOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bufferedOutputStream.close();
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray, contentType);
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = mimePart.getFileName();
        }
        byteArrayDataSource.setName((name == null || name.length() <= 0) ? null : MimeUtility.decodeText(name));
        return byteArrayDataSource;
    }

    /* JADX INFO: renamed from: h */
    private MimeMessage m5133h() {
        return this.f5058a;
    }

    /* JADX INFO: renamed from: i */
    private boolean m5134i() {
        return this.f5063f;
    }

    /* JADX INFO: renamed from: j */
    private String m5135j() {
        return this.f5059b;
    }

    /* JADX INFO: renamed from: k */
    private List<DataSource> m5136k() {
        return this.f5061d;
    }

    /* JADX INFO: renamed from: l */
    private Collection<String> m5137l() {
        return Collections.unmodifiableSet(this.f5062e.keySet());
    }

    /* JADX INFO: renamed from: m */
    private String m5138m() {
        return this.f5060c;
    }

    /* JADX INFO: renamed from: n */
    private boolean m5139n() {
        return this.f5059b != null;
    }

    /* JADX INFO: renamed from: o */
    private boolean m5140o() {
        return this.f5060c != null;
    }

    /* JADX INFO: renamed from: p */
    private boolean m5141p() {
        return this.f5061d.size() > 0;
    }

    /* JADX INFO: renamed from: c */
    private DataSource m5127c(String str) {
        return this.f5062e.get(str);
    }

    /* JADX INFO: renamed from: a */
    private static String m5118a(Part part, DataSource dataSource) throws MessagingException, UnsupportedEncodingException {
        String name = dataSource.getName();
        if (name == null || name.length() == 0) {
            name = part.getFileName();
        }
        if (name == null || name.length() <= 0) {
            return null;
        }
        return MimeUtility.decodeText(name);
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m5122a(InputStream inputStream) throws IOException {
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

    /* JADX INFO: renamed from: d */
    private static String m5128d(String str) {
        int iIndexOf = str.indexOf(59);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : str;
    }

    /* JADX INFO: renamed from: b */
    private DataSource m5124b(String str) {
        for (int i = 0; i < this.f5061d.size(); i++) {
            DataSource dataSource = this.f5061d.get(i);
            if (str.equalsIgnoreCase(dataSource.getName())) {
                return dataSource;
            }
        }
        return null;
    }
}
