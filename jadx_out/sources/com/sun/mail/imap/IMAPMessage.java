package com.sun.mail.imap;

import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.protocol.BODY;
import com.sun.mail.imap.protocol.BODYSTRUCTURE;
import com.sun.mail.imap.protocol.ENVELOPE;
import com.sun.mail.imap.protocol.FetchResponse;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.INTERNALDATE;
import com.sun.mail.imap.protocol.Item;
import com.sun.mail.imap.protocol.MessageSet;
import com.sun.mail.imap.protocol.RFC822DATA;
import com.sun.mail.imap.protocol.RFC822SIZE;
import com.sun.mail.imap.protocol.UID;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.FolderClosedException;
import javax.mail.Header;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.UIDFolder;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.litepal.util.Const;

/* JADX INFO: loaded from: classes.dex */
public class IMAPMessage extends MimeMessage {
    private static String EnvelopeCmd = "ENVELOPE INTERNALDATE RFC822.SIZE";

    /* JADX INFO: renamed from: bs */
    protected BODYSTRUCTURE f3601bs;
    private String description;
    protected ENVELOPE envelope;
    private boolean headersLoaded;
    private Hashtable loadedHeaders;
    private boolean peek;
    private Date receivedDate;
    protected String sectionId;
    private int seqnum;
    private int size;
    private String subject;
    private String type;
    private long uid;

    protected IMAPMessage(IMAPFolder iMAPFolder, int i, int i2) {
        super(iMAPFolder, i);
        this.size = -1;
        this.uid = -1L;
        this.headersLoaded = false;
        this.seqnum = i2;
        this.flags = null;
    }

    protected IMAPMessage(Session session) {
        super(session);
        this.size = -1;
        this.uid = -1L;
        this.headersLoaded = false;
    }

    protected IMAPProtocol getProtocol() throws ProtocolException, FolderClosedException {
        ((IMAPFolder) this.folder).waitIfIdle();
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol == null) {
            throw new FolderClosedException(this.folder);
        }
        return iMAPProtocol;
    }

    protected boolean isREV1() throws FolderClosedException {
        IMAPProtocol iMAPProtocol = ((IMAPFolder) this.folder).protocol;
        if (iMAPProtocol == null) {
            throw new FolderClosedException(this.folder);
        }
        return iMAPProtocol.isREV1();
    }

    protected Object getMessageCacheLock() {
        return ((IMAPFolder) this.folder).messageCacheLock;
    }

    protected int getSequenceNumber() {
        return this.seqnum;
    }

    protected void setSequenceNumber(int i) {
        this.seqnum = i;
    }

    @Override // javax.mail.Message
    protected void setMessageNumber(int i) {
        super.setMessageNumber(i);
    }

    protected long getUID() {
        return this.uid;
    }

    protected void setUID(long j) {
        this.uid = j;
    }

    @Override // javax.mail.Message
    protected void setExpunged(boolean z) {
        super.setExpunged(z);
        this.seqnum = -1;
    }

    protected void checkExpunged() throws MessageRemovedException {
        if (this.expunged) {
            throw new MessageRemovedException();
        }
    }

    protected void forceCheckExpunged() throws FolderClosedException, MessageRemovedException {
        synchronized (getMessageCacheLock()) {
            try {
                getProtocol().noop();
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException unused) {
            }
        }
        if (this.expunged) {
            throw new MessageRemovedException();
        }
    }

    protected int getFetchBlockSize() {
        return ((IMAPStore) this.folder.getStore()).getFetchBlockSize();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getFrom() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return aaclone(this.envelope.from);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setFrom(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addFrom(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public Address getSender() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (this.envelope.sender != null) {
            return this.envelope.sender[0];
        }
        return null;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSender(Address address) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getRecipients(Message.RecipientType recipientType) throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (recipientType == Message.RecipientType.f4740TO) {
            return aaclone(this.envelope.f3604to);
        }
        if (recipientType == Message.RecipientType.f4739CC) {
            return aaclone(this.envelope.f3603cc);
        }
        if (recipientType == Message.RecipientType.BCC) {
            return aaclone(this.envelope.bcc);
        }
        return super.getRecipients(recipientType);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void addRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Address[] getReplyTo() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return aaclone(this.envelope.replyTo);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setReplyTo(Address[] addressArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public String getSubject() throws MessagingException {
        checkExpunged();
        if (this.subject != null) {
            return this.subject;
        }
        loadEnvelope();
        if (this.envelope.subject == null) {
            return null;
        }
        try {
            this.subject = MimeUtility.decodeText(this.envelope.subject);
        } catch (UnsupportedEncodingException unused) {
            this.subject = this.envelope.subject;
        }
        return this.subject;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setSubject(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getSentDate() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (this.envelope.date == null) {
            return null;
        }
        return new Date(this.envelope.date.getTime());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setSentDate(Date date) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public Date getReceivedDate() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        if (this.receivedDate == null) {
            return null;
        }
        return new Date(this.receivedDate.getTime());
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getSize() throws MessagingException {
        checkExpunged();
        if (this.size == -1) {
            loadEnvelope();
        }
        return this.size;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getLineCount() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f3601bs.lines;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String[] getContentLanguage() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        if (this.f3601bs.language != null) {
            return (String[]) this.f3601bs.language.clone();
        }
        return null;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentLanguage(String[] strArr) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    public String getInReplyTo() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return this.envelope.inReplyTo;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getContentType() throws MessagingException {
        checkExpunged();
        if (this.type == null) {
            loadBODYSTRUCTURE();
            this.type = new ContentType(this.f3601bs.type, this.f3601bs.subtype, this.f3601bs.cParams).toString();
        }
        return this.type;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDisposition() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f3601bs.disposition;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDisposition(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getEncoding() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f3601bs.encoding;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentID() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f3601bs.f3602id;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setContentID(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getContentMD5() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        return this.f3601bs.md5;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void setContentMD5(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getDescription() throws MessagingException {
        checkExpunged();
        if (this.description != null) {
            return this.description;
        }
        loadBODYSTRUCTURE();
        if (this.f3601bs.description == null) {
            return null;
        }
        try {
            this.description = MimeUtility.decodeText(this.f3601bs.description);
        } catch (UnsupportedEncodingException unused) {
            this.description = this.f3601bs.description;
        }
        return this.description;
    }

    @Override // javax.mail.internet.MimeMessage
    public void setDescription(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage
    public String getMessageID() throws MessagingException {
        checkExpunged();
        loadEnvelope();
        return this.envelope.messageId;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String getFileName() throws MessagingException {
        checkExpunged();
        loadBODYSTRUCTURE();
        String str = this.f3601bs.dParams != null ? this.f3601bs.dParams.get("filename") : null;
        return (str != null || this.f3601bs.cParams == null) ? str : this.f3601bs.cParams.get(Const.TableSchema.COLUMN_NAME);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setFileName(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:25:0x006f  */
    @Override // javax.mail.internet.MimeMessage
    public InputStream getContentStream() throws MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        BODY bodyFetchBody;
        boolean peek = getPeek();
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    if (getFetchBlockSize() != -1) {
                        return new IMAPInputStream(this, toSection("TEXT"), this.f3601bs != null ? this.f3601bs.size : -1, peek);
                    }
                }
                if (protocol.isREV1()) {
                    if (peek) {
                        bodyFetchBody = protocol.peekBody(getSequenceNumber(), toSection("TEXT"));
                    } else {
                        bodyFetchBody = protocol.fetchBody(getSequenceNumber(), toSection("TEXT"));
                    }
                    if (bodyFetchBody != null) {
                        byteArrayInputStream = bodyFetchBody.getByteArrayInputStream();
                    } else {
                        byteArrayInputStream = null;
                    }
                } else {
                    RFC822DATA rfc822dataFetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "TEXT");
                    if (rfc822dataFetchRFC822 != null) {
                        byteArrayInputStream = rfc822dataFetchRFC822.getByteArrayInputStream();
                    } else {
                        byteArrayInputStream = null;
                    }
                }
                if (byteArrayInputStream == null) {
                    throw new MessagingException("No content");
                }
                return byteArrayInputStream;
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public synchronized DataHandler getDataHandler() throws MessagingException {
        String str;
        checkExpunged();
        if (this.f4745dh == null) {
            loadBODYSTRUCTURE();
            if (this.type == null) {
                this.type = new ContentType(this.f3601bs.type, this.f3601bs.subtype, this.f3601bs.cParams).toString();
            }
            if (this.f3601bs.isMulti()) {
                this.f4745dh = new DataHandler(new IMAPMultipartDataSource(this, this.f3601bs.bodies, this.sectionId, this));
            } else if (this.f3601bs.isNested() && isREV1()) {
                BODYSTRUCTURE bodystructure = this.f3601bs.bodies[0];
                ENVELOPE envelope = this.f3601bs.envelope;
                if (this.sectionId == null) {
                    str = "1";
                } else {
                    str = String.valueOf(this.sectionId) + ".1";
                }
                this.f4745dh = new DataHandler(new IMAPNestedMessage(this, bodystructure, envelope, str), this.type);
            }
        }
        return super.getDataHandler();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void writeTo(OutputStream outputStream) throws MessagingException, IOException {
        ByteArrayInputStream byteArrayInputStream;
        BODY bodyFetchBody;
        boolean peek = getPeek();
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                byteArrayInputStream = null;
                if (protocol.isREV1()) {
                    if (peek) {
                        bodyFetchBody = protocol.peekBody(getSequenceNumber(), this.sectionId);
                    } else {
                        bodyFetchBody = protocol.fetchBody(getSequenceNumber(), this.sectionId);
                    }
                    if (bodyFetchBody != null) {
                        byteArrayInputStream = bodyFetchBody.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA rfc822dataFetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), null);
                    if (rfc822dataFetchRFC822 != null) {
                        byteArrayInputStream = rfc822dataFetchRFC822.getByteArrayInputStream();
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream == null) {
            throw new MessagingException("No content");
        }
        byte[] bArr = new byte[1024];
        while (true) {
            int i = byteArrayInputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x006b  */
    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        ByteArrayInputStream byteArrayInputStream;
        checkExpunged();
        if (isHeaderLoaded(str)) {
            return this.headers.getHeader(str);
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    BODY bodyPeekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER.FIELDS (" + str + ")"));
                    if (bodyPeekBody != null) {
                        byteArrayInputStream = bodyPeekBody.getByteArrayInputStream();
                    } else {
                        byteArrayInputStream = null;
                    }
                } else {
                    RFC822DATA rfc822dataFetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER.LINES (" + str + ")");
                    if (rfc822dataFetchRFC822 != null) {
                        byteArrayInputStream = rfc822dataFetchRFC822.getByteArrayInputStream();
                    } else {
                        byteArrayInputStream = null;
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream == null) {
            return null;
        }
        if (this.headers == null) {
            this.headers = new InternetHeaders();
        }
        this.headers.load(byteArrayInputStream);
        setHeaderLoaded(str);
        return this.headers.getHeader(str);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        checkExpunged();
        if (getHeader(str) == null) {
            return null;
        }
        return this.headers.getHeader(str, str2);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaders();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void addHeaderLine(String str) throws MessagingException {
        throw new IllegalWriteException("IMAPMessage is read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getAllHeaderLines() throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getAllHeaderLines();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        checkExpunged();
        loadHeaders();
        return super.getNonMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized Flags getFlags() throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.getFlags();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized boolean isSet(Flags.Flag flag) throws MessagingException {
        checkExpunged();
        loadFlags();
        return super.isSet(flag);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public synchronized void setFlags(Flags flags, boolean z) throws MessagingException {
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                protocol.storeFlags(getSequenceNumber(), flags, z);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    public synchronized void setPeek(boolean z) {
        this.peek = z;
    }

    public synchronized boolean getPeek() {
        return this.peek;
    }

    public synchronized void invalidateHeaders() {
        this.headersLoaded = false;
        this.loadedHeaders = null;
        this.envelope = null;
        this.f3601bs = null;
        this.receivedDate = null;
        this.size = -1;
        this.type = null;
        this.subject = null;
        this.description = null;
    }

    static void fetch(IMAPFolder iMAPFolder, Message[] messageArr, FetchProfile fetchProfile) throws MessagingException {
        boolean z;
        boolean z2;
        String[] headerNames;
        Response[] responseArrFetch;
        Response[] responseArr;
        ByteArrayInputStream byteArrayInputStream;
        FetchProfile fetchProfile2 = fetchProfile;
        StringBuffer stringBuffer = new StringBuffer();
        if (fetchProfile2.contains(FetchProfile.Item.ENVELOPE)) {
            stringBuffer.append(EnvelopeCmd);
            z = false;
        } else {
            z = true;
        }
        if (fetchProfile2.contains(FetchProfile.Item.FLAGS)) {
            stringBuffer.append(z ? "FLAGS" : " FLAGS");
            z = false;
        }
        if (fetchProfile2.contains(FetchProfile.Item.CONTENT_INFO)) {
            stringBuffer.append(z ? "BODYSTRUCTURE" : " BODYSTRUCTURE");
            z = false;
        }
        if (fetchProfile2.contains(UIDFolder.FetchProfileItem.UID)) {
            stringBuffer.append(z ? "UID" : " UID");
            z = false;
        }
        if (fetchProfile2.contains(IMAPFolder.FetchProfileItem.HEADERS)) {
            if (iMAPFolder.protocol.isREV1()) {
                stringBuffer.append(z ? "BODY.PEEK[HEADER]" : " BODY.PEEK[HEADER]");
            } else {
                stringBuffer.append(z ? "RFC822.HEADER" : " RFC822.HEADER");
            }
            z = false;
            z2 = true;
        } else {
            z2 = false;
        }
        if (fetchProfile2.contains(IMAPFolder.FetchProfileItem.SIZE)) {
            stringBuffer.append(z ? "RFC822.SIZE" : " RFC822.SIZE");
            z = false;
        }
        if (z2) {
            headerNames = null;
        } else {
            headerNames = fetchProfile.getHeaderNames();
            if (headerNames.length > 0) {
                if (!z) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append(craftHeaderCmd(iMAPFolder.protocol, headerNames));
            }
        }
        Utility.Condition condition = new Utility.Condition(fetchProfile2) { // from class: com.sun.mail.imap.IMAPMessage.1FetchProfileCondition
            private String[] hdrs;
            private boolean needBodyStructure;
            private boolean needEnvelope;
            private boolean needFlags;
            private boolean needHeaders;
            private boolean needSize;
            private boolean needUID;

            {
                this.needEnvelope = false;
                this.needFlags = false;
                this.needBodyStructure = false;
                this.needUID = false;
                this.needHeaders = false;
                this.needSize = false;
                this.hdrs = null;
                if (fetchProfile2.contains(FetchProfile.Item.ENVELOPE)) {
                    this.needEnvelope = true;
                }
                if (fetchProfile2.contains(FetchProfile.Item.FLAGS)) {
                    this.needFlags = true;
                }
                if (fetchProfile2.contains(FetchProfile.Item.CONTENT_INFO)) {
                    this.needBodyStructure = true;
                }
                if (fetchProfile2.contains(UIDFolder.FetchProfileItem.UID)) {
                    this.needUID = true;
                }
                if (fetchProfile2.contains(IMAPFolder.FetchProfileItem.HEADERS)) {
                    this.needHeaders = true;
                }
                if (fetchProfile2.contains(IMAPFolder.FetchProfileItem.SIZE)) {
                    this.needSize = true;
                }
                this.hdrs = fetchProfile2.getHeaderNames();
            }

            @Override // com.sun.mail.imap.Utility.Condition
            public boolean test(IMAPMessage iMAPMessage) {
                if (this.needEnvelope && iMAPMessage._getEnvelope() == null) {
                    return true;
                }
                if (this.needFlags && iMAPMessage._getFlags() == null) {
                    return true;
                }
                if (this.needBodyStructure && iMAPMessage._getBodyStructure() == null) {
                    return true;
                }
                if (this.needUID && iMAPMessage.getUID() == -1) {
                    return true;
                }
                if (this.needHeaders && !iMAPMessage.areHeadersLoaded()) {
                    return true;
                }
                if (this.needSize && iMAPMessage.size == -1) {
                    return true;
                }
                for (int i = 0; i < this.hdrs.length; i++) {
                    if (!iMAPMessage.isHeaderLoaded(this.hdrs[i])) {
                        return true;
                    }
                }
                return false;
            }
        };
        synchronized (iMAPFolder.messageCacheLock) {
            try {
                MessageSet[] messageSet = Utility.toMessageSet(messageArr, condition);
                if (messageSet == null) {
                    return;
                }
                Vector vector = new Vector();
                try {
                    try {
                        responseArrFetch = iMAPFolder.protocol.fetch(messageSet, stringBuffer.toString());
                    } catch (ConnectionException e) {
                        throw new FolderClosedException(iMAPFolder, e.getMessage());
                    }
                } catch (CommandFailedException unused) {
                    responseArrFetch = null;
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
                if (responseArrFetch == null) {
                    return;
                }
                int i = 0;
                while (i < responseArrFetch.length) {
                    if (responseArrFetch[i] != null) {
                        if (!(responseArrFetch[i] instanceof FetchResponse)) {
                            vector.addElement(responseArrFetch[i]);
                        } else {
                            FetchResponse fetchResponse = (FetchResponse) responseArrFetch[i];
                            IMAPMessage messageBySeqNumber = iMAPFolder.getMessageBySeqNumber(fetchResponse.getNumber());
                            int itemCount = fetchResponse.getItemCount();
                            int i2 = 0;
                            boolean z3 = false;
                            while (i2 < itemCount) {
                                Object item = fetchResponse.getItem(i2);
                                if (item instanceof Flags) {
                                    if (!fetchProfile2.contains(FetchProfile.Item.FLAGS) || messageBySeqNumber == null) {
                                        responseArr = responseArrFetch;
                                        z3 = true;
                                    } else {
                                        messageBySeqNumber.flags = (Flags) item;
                                        responseArr = responseArrFetch;
                                    }
                                } else {
                                    if (item instanceof ENVELOPE) {
                                        messageBySeqNumber.envelope = (ENVELOPE) item;
                                    } else if (item instanceof INTERNALDATE) {
                                        messageBySeqNumber.receivedDate = ((INTERNALDATE) item).getDate();
                                    } else if (item instanceof RFC822SIZE) {
                                        messageBySeqNumber.size = ((RFC822SIZE) item).size;
                                    } else if (item instanceof BODYSTRUCTURE) {
                                        messageBySeqNumber.f3601bs = (BODYSTRUCTURE) item;
                                    } else if (item instanceof UID) {
                                        UID uid = (UID) item;
                                        responseArr = responseArrFetch;
                                        messageBySeqNumber.uid = uid.uid;
                                        if (iMAPFolder.uidTable == null) {
                                            iMAPFolder.uidTable = new Hashtable();
                                        }
                                        iMAPFolder.uidTable.put(new Long(uid.uid), messageBySeqNumber);
                                    } else {
                                        responseArr = responseArrFetch;
                                        if ((item instanceof RFC822DATA) || (item instanceof BODY)) {
                                            if (item instanceof RFC822DATA) {
                                                byteArrayInputStream = ((RFC822DATA) item).getByteArrayInputStream();
                                            } else {
                                                byteArrayInputStream = ((BODY) item).getByteArrayInputStream();
                                            }
                                            InternetHeaders internetHeaders = new InternetHeaders();
                                            internetHeaders.load(byteArrayInputStream);
                                            if (messageBySeqNumber.headers == null || z2) {
                                                messageBySeqNumber.headers = internetHeaders;
                                            } else {
                                                Enumeration allHeaders = internetHeaders.getAllHeaders();
                                                while (allHeaders.hasMoreElements()) {
                                                    Header header = (Header) allHeaders.nextElement();
                                                    if (!messageBySeqNumber.isHeaderLoaded(header.getName())) {
                                                        messageBySeqNumber.headers.addHeader(header.getName(), header.getValue());
                                                    }
                                                }
                                            }
                                            if (z2) {
                                                messageBySeqNumber.setHeadersLoaded(true);
                                            } else {
                                                for (String str : headerNames) {
                                                    messageBySeqNumber.setHeaderLoaded(str);
                                                }
                                            }
                                        }
                                    }
                                    responseArr = responseArrFetch;
                                }
                                i2++;
                                responseArrFetch = responseArr;
                                fetchProfile2 = fetchProfile;
                            }
                            if (z3) {
                                vector.addElement(fetchResponse);
                            }
                        }
                    }
                    i++;
                    responseArrFetch = responseArrFetch;
                    fetchProfile2 = fetchProfile;
                }
                int size = vector.size();
                if (size != 0) {
                    Response[] responseArr2 = new Response[size];
                    vector.copyInto(responseArr2);
                    iMAPFolder.handleResponses(responseArr2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private synchronized void loadEnvelope() throws MessagingException {
        if (this.envelope != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                int sequenceNumber = getSequenceNumber();
                Response[] responseArrFetch = protocol.fetch(sequenceNumber, EnvelopeCmd);
                for (int i = 0; i < responseArrFetch.length; i++) {
                    if (responseArrFetch[i] != null && (responseArrFetch[i] instanceof FetchResponse) && ((FetchResponse) responseArrFetch[i]).getNumber() == sequenceNumber) {
                        FetchResponse fetchResponse = (FetchResponse) responseArrFetch[i];
                        int itemCount = fetchResponse.getItemCount();
                        for (int i2 = 0; i2 < itemCount; i2++) {
                            Item item = fetchResponse.getItem(i2);
                            if (item instanceof ENVELOPE) {
                                this.envelope = (ENVELOPE) item;
                            } else if (item instanceof INTERNALDATE) {
                                this.receivedDate = ((INTERNALDATE) item).getDate();
                            } else if (item instanceof RFC822SIZE) {
                                this.size = ((RFC822SIZE) item).size;
                            }
                        }
                    }
                }
                protocol.notifyResponseHandlers(responseArrFetch);
                protocol.handleResult(responseArrFetch[responseArrFetch.length - 1]);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (this.envelope == null) {
            throw new MessagingException("Failed to load IMAP envelope");
        }
    }

    private static String craftHeaderCmd(IMAPProtocol iMAPProtocol, String[] strArr) {
        StringBuffer stringBuffer;
        if (iMAPProtocol.isREV1()) {
            stringBuffer = new StringBuffer("BODY.PEEK[HEADER.FIELDS (");
        } else {
            stringBuffer = new StringBuffer("RFC822.HEADER.LINES (");
        }
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(strArr[i]);
        }
        if (iMAPProtocol.isREV1()) {
            stringBuffer.append(")]");
        } else {
            stringBuffer.append(")");
        }
        return stringBuffer.toString();
    }

    private synchronized void loadBODYSTRUCTURE() throws MessagingException {
        if (this.f3601bs != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                this.f3601bs = protocol.fetchBodyStructure(getSequenceNumber());
                if (this.f3601bs == null) {
                    forceCheckExpunged();
                    throw new MessagingException("Unable to load BODYSTRUCTURE");
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    private synchronized void loadHeaders() throws MessagingException {
        if (this.headersLoaded) {
            return;
        }
        ByteArrayInputStream byteArrayInputStream = null;
        synchronized (getMessageCacheLock()) {
            try {
                IMAPProtocol protocol = getProtocol();
                checkExpunged();
                if (protocol.isREV1()) {
                    BODY bodyPeekBody = protocol.peekBody(getSequenceNumber(), toSection("HEADER"));
                    if (bodyPeekBody != null) {
                        byteArrayInputStream = bodyPeekBody.getByteArrayInputStream();
                    }
                } else {
                    RFC822DATA rfc822dataFetchRFC822 = protocol.fetchRFC822(getSequenceNumber(), "HEADER");
                    if (rfc822dataFetchRFC822 != null) {
                        byteArrayInputStream = rfc822dataFetchRFC822.getByteArrayInputStream();
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this.folder, e.getMessage());
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
        if (byteArrayInputStream == null) {
            throw new MessagingException("Cannot load header");
        }
        this.headers = new InternetHeaders(byteArrayInputStream);
        this.headersLoaded = true;
    }

    private synchronized void loadFlags() throws MessagingException {
        if (this.flags != null) {
            return;
        }
        synchronized (getMessageCacheLock()) {
            try {
                try {
                    IMAPProtocol protocol = getProtocol();
                    checkExpunged();
                    this.flags = protocol.fetchFlags(getSequenceNumber());
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this.folder, e.getMessage());
                }
            } catch (ProtocolException e2) {
                forceCheckExpunged();
                throw new MessagingException(e2.getMessage(), e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean areHeadersLoaded() {
        return this.headersLoaded;
    }

    private synchronized void setHeadersLoaded(boolean z) {
        this.headersLoaded = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isHeaderLoaded(String str) {
        if (this.headersLoaded) {
            return true;
        }
        if (this.loadedHeaders == null) {
            return false;
        }
        return this.loadedHeaders.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    private synchronized void setHeaderLoaded(String str) {
        if (this.loadedHeaders == null) {
            this.loadedHeaders = new Hashtable(1);
        }
        this.loadedHeaders.put(str.toUpperCase(Locale.ENGLISH), str);
    }

    private String toSection(String str) {
        if (this.sectionId == null) {
            return str;
        }
        return String.valueOf(this.sectionId) + "." + str;
    }

    private InternetAddress[] aaclone(InternetAddress[] internetAddressArr) {
        if (internetAddressArr == null) {
            return null;
        }
        return (InternetAddress[]) internetAddressArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Flags _getFlags() {
        return this.flags;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ENVELOPE _getEnvelope() {
        return this.envelope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BODYSTRUCTURE _getBodyStructure() {
        return this.f3601bs;
    }

    void _setFlags(Flags flags) {
        this.flags = flags;
    }

    Session _getSession() {
        return this.session;
    }
}
