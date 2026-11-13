package com.sun.mail.smtp;

import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import com.umeng.commonsdk.proguard.C1770bg;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.ParseException;

/* JADX INFO: loaded from: classes.dex */
public class SMTPTransport extends Transport {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String UNKNOWN = "UNKNOWN";
    private Address[] addresses;
    private SMTPOutputStream dataStream;
    private int defaultPort;
    private MessagingException exception;
    private Hashtable extMap;
    private Address[] invalidAddr;
    private boolean isSSL;
    private int lastReturnCode;
    private String lastServerResponse;
    private LineInputStream lineInputStream;
    private String localHostName;
    private DigestMD5 md5support;
    private MimeMessage message;
    private String name;
    private PrintStream out;
    private boolean quitWait;
    private boolean reportSuccess;
    private String saslRealm;
    private boolean sendPartiallyFailed;
    private BufferedInputStream serverInput;
    private OutputStream serverOutput;
    private Socket serverSocket;
    private boolean useRset;
    private boolean useStartTLS;
    private Address[] validSentAddr;
    private Address[] validUnsentAddr;
    private static final String[] ignoreList = {"Bcc", "Content-Length"};
    private static final byte[] CRLF = {C1770bg.f4123k, 10};
    private static char[] hexchar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public SMTPTransport(Session session, URLName uRLName) {
        this(session, uRLName, "smtp", 25, false);
    }

    protected SMTPTransport(Session session, URLName uRLName, String str, int i, boolean z) {
        super(session, uRLName);
        this.name = "smtp";
        this.defaultPort = 25;
        this.isSSL = false;
        this.sendPartiallyFailed = false;
        this.quitWait = false;
        this.saslRealm = UNKNOWN;
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        this.defaultPort = i;
        this.isSSL = z;
        this.out = session.getDebugOut();
        String property = session.getProperty("mail." + str + ".quitwait");
        this.quitWait = property == null || property.equalsIgnoreCase("true");
        String property2 = session.getProperty("mail." + str + ".reportsuccess");
        this.reportSuccess = property2 != null && property2.equalsIgnoreCase("true");
        String property3 = session.getProperty("mail." + str + ".starttls.enable");
        this.useStartTLS = property3 != null && property3.equalsIgnoreCase("true");
        String property4 = session.getProperty("mail." + str + ".userset");
        this.useRset = property4 != null && property4.equalsIgnoreCase("true");
    }

    public synchronized String getLocalHost() {
        try {
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                this.localHostName = this.session.getProperty("mail." + this.name + ".localhost");
            }
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                this.localHostName = this.session.getProperty("mail." + this.name + ".localaddress");
            }
            if (this.localHostName == null || this.localHostName.length() <= 0) {
                InetAddress localHost = InetAddress.getLocalHost();
                this.localHostName = localHost.getHostName();
                if (this.localHostName == null) {
                    this.localHostName = "[" + localHost.getHostAddress() + "]";
                }
            }
        } catch (UnknownHostException unused) {
        }
        return this.localHostName;
    }

    public synchronized void setLocalHost(String str) {
        this.localHostName = str;
    }

    public synchronized void connect(Socket socket) throws MessagingException {
        this.serverSocket = socket;
        super.connect();
    }

    public synchronized String getSASLRealm() {
        if (this.saslRealm == UNKNOWN) {
            this.saslRealm = this.session.getProperty("mail." + this.name + ".sasl.realm");
            if (this.saslRealm == null) {
                this.saslRealm = this.session.getProperty("mail." + this.name + ".saslrealm");
            }
        }
        return this.saslRealm;
    }

    public synchronized void setSASLRealm(String str) {
        this.saslRealm = str;
    }

    public synchronized boolean getReportSuccess() {
        return this.reportSuccess;
    }

    public synchronized void setReportSuccess(boolean z) {
        this.reportSuccess = z;
    }

    public synchronized boolean getStartTLS() {
        return this.useStartTLS;
    }

    public synchronized void setStartTLS(boolean z) {
        this.useStartTLS = z;
    }

    public synchronized boolean getUseRset() {
        return this.useRset;
    }

    public synchronized void setUseRset(boolean z) {
        this.useRset = z;
    }

    public synchronized String getLastServerResponse() {
        return this.lastServerResponse;
    }

    public synchronized int getLastReturnCode() {
        return this.lastReturnCode;
    }

    private synchronized DigestMD5 getMD5() {
        if (this.md5support == null) {
            this.md5support = new DigestMD5(this.debug ? this.out : null);
        }
        return this.md5support;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public boolean protocolConnect(String str, int i, String str2, String str3) throws Throwable {
        int iSimpleCommand;
        DigestMD5 md5;
        String property = this.session.getProperty("mail." + this.name + ".ehlo");
        boolean z = property == null || !property.equalsIgnoreCase("false");
        String property2 = this.session.getProperty("mail." + this.name + ".auth");
        boolean z2 = property2 != null && property2.equalsIgnoreCase("true");
        if (this.debug) {
            this.out.println("DEBUG SMTP: useEhlo " + z + ", useAuth " + z2);
        }
        if (z2 && (str2 == null || str3 == null)) {
            return false;
        }
        int iSimpleCommand2 = -1;
        int i2 = i;
        if (i2 == -1) {
            String property3 = this.session.getProperty("mail." + this.name + ".port");
            if (property3 != null) {
                i2 = Integer.parseInt(property3);
            } else {
                i2 = this.defaultPort;
            }
        }
        int i3 = i2;
        String str4 = (str == null || str.length() == 0) ? "localhost" : str;
        if (this.serverSocket != null) {
            openServer();
        } else {
            openServer(str4, i3);
        }
        if (!(z ? ehlo(getLocalHost()) : false)) {
            helo(getLocalHost());
        }
        if (this.useStartTLS && supportsExtension("STARTTLS")) {
            startTLS();
            ehlo(getLocalHost());
        }
        if ((z2 || (str2 != null && str3 != null)) && (supportsExtension("AUTH") || supportsExtension("AUTH=LOGIN"))) {
            if (this.debug) {
                this.out.println("DEBUG SMTP: Attempt to authenticate");
                if (!supportsAuthentication("LOGIN") && supportsExtension("AUTH=LOGIN")) {
                    this.out.println("DEBUG SMTP: use AUTH=LOGIN hack");
                }
            }
            if (supportsAuthentication("LOGIN") || supportsExtension("AUTH=LOGIN")) {
                int iSimpleCommand3 = simpleCommand("AUTH LOGIN");
                if (iSimpleCommand3 == 530) {
                    startTLS();
                    iSimpleCommand3 = simpleCommand("AUTH LOGIN");
                }
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    if (iSimpleCommand3 == 334) {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str2));
                        bASE64EncoderStream.flush();
                        iSimpleCommand = simpleCommand(byteArrayOutputStream.toByteArray());
                        try {
                            byteArrayOutputStream.reset();
                            iSimpleCommand3 = iSimpleCommand;
                        } catch (IOException unused) {
                            iSimpleCommand3 = iSimpleCommand;
                            if (iSimpleCommand3 != 235) {
                                closeConnection();
                                return false;
                            }
                        } catch (Throwable th) {
                            th = th;
                            Throwable th2 = th;
                            if (iSimpleCommand == 235) {
                                throw th2;
                            }
                            closeConnection();
                            return false;
                        }
                    }
                    if (iSimpleCommand3 == 334) {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str3));
                        bASE64EncoderStream.flush();
                        iSimpleCommand = simpleCommand(byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.reset();
                        iSimpleCommand3 = iSimpleCommand;
                    }
                    if (iSimpleCommand3 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    th = th3;
                    iSimpleCommand = iSimpleCommand3;
                }
            } else if (supportsAuthentication("PLAIN")) {
                int iSimpleCommand4 = simpleCommand("AUTH PLAIN");
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    BASE64EncoderStream bASE64EncoderStream2 = new BASE64EncoderStream(byteArrayOutputStream2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    if (iSimpleCommand4 == 334) {
                        bASE64EncoderStream2.write(0);
                        bASE64EncoderStream2.write(ASCIIUtility.getBytes(str2));
                        bASE64EncoderStream2.write(0);
                        bASE64EncoderStream2.write(ASCIIUtility.getBytes(str3));
                        bASE64EncoderStream2.flush();
                        iSimpleCommand4 = simpleCommand(byteArrayOutputStream2.toByteArray());
                    }
                    if (iSimpleCommand4 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (IOException unused3) {
                    if (iSimpleCommand4 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (Throwable th4) {
                    if (iSimpleCommand4 == 235) {
                        throw th4;
                    }
                    closeConnection();
                    return false;
                }
            } else if (supportsAuthentication("DIGEST-MD5") && (md5 = getMD5()) != null) {
                int iSimpleCommand5 = simpleCommand("AUTH DIGEST-MD5");
                try {
                    if (iSimpleCommand5 == 334) {
                        try {
                            int iSimpleCommand6 = simpleCommand(md5.authClient(str4, str2, str3, getSASLRealm(), this.lastServerResponse));
                            if (iSimpleCommand6 == 334) {
                                try {
                                    if (md5.authServer(this.lastServerResponse)) {
                                        iSimpleCommand2 = simpleCommand(new byte[0]);
                                    }
                                } catch (Exception e) {
                                    e = e;
                                    iSimpleCommand5 = iSimpleCommand6;
                                    Exception exc = e;
                                    if (this.debug) {
                                        this.out.println("DEBUG SMTP: DIGEST-MD5: " + exc);
                                    }
                                    if (iSimpleCommand5 != 235) {
                                        closeConnection();
                                        return false;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    iSimpleCommand5 = iSimpleCommand6;
                                    Throwable th6 = th;
                                    if (iSimpleCommand5 == 235) {
                                        throw th6;
                                    }
                                    closeConnection();
                                    return false;
                                }
                            } else {
                                iSimpleCommand2 = iSimpleCommand6;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } else {
                        iSimpleCommand2 = iSimpleCommand5;
                    }
                    if (iSimpleCommand2 != 235) {
                        closeConnection();
                        return false;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
        }
        return true;
    }

    @Override // javax.mail.Transport
    public synchronized void sendMessage(Message message, Address[] addressArr) throws MessagingException {
        checkConnected();
        if (!(message instanceof MimeMessage)) {
            if (this.debug) {
                this.out.println("DEBUG SMTP: Can only send RFC822 msgs");
            }
            throw new MessagingException("SMTP can only send RFC822 messages");
        }
        for (int i = 0; i < addressArr.length; i++) {
            if (!(addressArr[i] instanceof InternetAddress)) {
                throw new MessagingException(addressArr[i] + " is not an InternetAddress");
            }
        }
        this.message = (MimeMessage) message;
        this.addresses = addressArr;
        this.validUnsentAddr = addressArr;
        expandGroups();
        boolean allow8bitMIME = message instanceof SMTPMessage ? ((SMTPMessage) message).getAllow8bitMIME() : false;
        if (!allow8bitMIME) {
            String property = this.session.getProperty("mail." + this.name + ".allow8bitmime");
            allow8bitMIME = property != null && property.equalsIgnoreCase("true");
        }
        if (this.debug) {
            this.out.println("DEBUG SMTP: use8bit " + allow8bitMIME);
        }
        if (allow8bitMIME && supportsExtension("8BITMIME") && convertTo8Bit(this.message)) {
            try {
                this.message.saveChanges();
            } catch (MessagingException unused) {
            }
        }
        try {
            try {
                mailFrom();
                rcptTo();
                this.message.writeTo(data(), ignoreList);
                finishData();
                if (this.sendPartiallyFailed) {
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: Sending partially failed because of invalid destination addresses");
                    }
                    notifyTransportListeners(3, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                    throw new SMTPSendFailedException(".", this.lastReturnCode, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
                }
                notifyTransportListeners(1, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                this.invalidAddr = null;
                this.validUnsentAddr = null;
                this.validSentAddr = null;
                this.addresses = null;
                this.message = null;
                this.exception = null;
                this.sendPartiallyFailed = false;
            } catch (Throwable th) {
                this.invalidAddr = null;
                this.validUnsentAddr = null;
                this.validSentAddr = null;
                this.addresses = null;
                this.message = null;
                this.exception = null;
                this.sendPartiallyFailed = false;
                throw th;
            }
        } catch (IOException e) {
            if (this.debug) {
                ThrowableExtension.printStackTrace(e, this.out);
            }
            try {
                closeConnection();
            } catch (MessagingException unused2) {
            }
            notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
            throw new MessagingException("IOException while sending message", e);
        } catch (MessagingException e2) {
            if (this.debug) {
                ThrowableExtension.printStackTrace(e2, this.out);
            }
            notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
            throw e2;
        }
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        int serverResponse;
        if (super.isConnected()) {
            try {
                if (this.serverSocket != null) {
                    sendCommand("QUIT");
                    if (this.quitWait && (serverResponse = readServerResponse()) != 221 && serverResponse != -1) {
                        this.out.println("DEBUG SMTP: QUIT failed with " + serverResponse);
                    }
                }
                closeConnection();
            } catch (Throwable th) {
                closeConnection();
                throw th;
            }
        }
    }

    private void closeConnection() throws MessagingException {
        try {
            try {
                if (this.serverSocket != null) {
                    this.serverSocket.close();
                }
                this.serverSocket = null;
                this.serverOutput = null;
                this.serverInput = null;
                this.lineInputStream = null;
                if (super.isConnected()) {
                    super.close();
                }
            } catch (IOException e) {
                throw new MessagingException("Server Close Failed", e);
            }
        } catch (Throwable th) {
            this.serverSocket = null;
            this.serverOutput = null;
            this.serverInput = null;
            this.lineInputStream = null;
            if (super.isConnected()) {
                super.close();
            }
            throw th;
        }
    }

    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        if (!super.isConnected()) {
            return false;
        }
        try {
            try {
                if (this.useRset) {
                    sendCommand("RSET");
                } else {
                    sendCommand("NOOP");
                }
                int serverResponse = readServerResponse();
                if (serverResponse >= 0 && serverResponse != 421) {
                    return true;
                }
                try {
                    closeConnection();
                } catch (MessagingException unused) {
                }
                return false;
            } catch (Exception unused2) {
                closeConnection();
                return false;
            }
        } catch (MessagingException unused3) {
        }
        return false;
    }

    private void expandGroups() {
        Vector vector = null;
        for (int i = 0; i < this.addresses.length; i++) {
            InternetAddress internetAddress = (InternetAddress) this.addresses[i];
            if (internetAddress.isGroup()) {
                if (vector == null) {
                    Vector vector2 = new Vector();
                    for (int i2 = 0; i2 < i; i2++) {
                        vector2.addElement(this.addresses[i2]);
                    }
                    vector = vector2;
                }
                try {
                    InternetAddress[] group = internetAddress.getGroup(true);
                    if (group != null) {
                        for (InternetAddress internetAddress2 : group) {
                            vector.addElement(internetAddress2);
                        }
                    } else {
                        vector.addElement(internetAddress);
                    }
                } catch (ParseException unused) {
                    vector.addElement(internetAddress);
                }
            } else if (vector != null) {
                vector.addElement(internetAddress);
            }
        }
        if (vector != null) {
            InternetAddress[] internetAddressArr = new InternetAddress[vector.size()];
            vector.copyInto(internetAddressArr);
            this.addresses = internetAddressArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean convertTo8Bit(MimePart mimePart) {
        boolean z = false;
        z = false;
        z = false;
        z = false;
        z = false;
        try {
            if (mimePart.isMimeType("text/*")) {
                String encoding = mimePart.getEncoding();
                if (encoding != null && ((encoding.equalsIgnoreCase("quoted-printable") || encoding.equalsIgnoreCase("base64")) && is8Bit(mimePart.getInputStream()))) {
                    mimePart.setContent(mimePart.getContent(), mimePart.getContentType());
                    mimePart.setHeader(MIME.CONTENT_TRANSFER_ENC, MIME.ENC_8BIT);
                    z = true;
                }
            } else if (mimePart.isMimeType("multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) mimePart.getContent();
                int count = mimeMultipart.getCount();
                boolean z2 = false;
                for (int i = 0; i < count; i++) {
                    try {
                        if (convertTo8Bit((MimePart) mimeMultipart.getBodyPart(i))) {
                            z2 = true;
                        }
                    } catch (IOException | MessagingException unused) {
                    }
                }
                z = z2;
            }
        } catch (IOException | MessagingException unused2) {
        }
        return z;
    }

    private boolean is8Bit(InputStream inputStream) {
        boolean z = false;
        int i = 0;
        while (true) {
            try {
                int i2 = inputStream.read();
                if (i2 < 0) {
                    if (this.debug && z) {
                        this.out.println("DEBUG SMTP: found an 8bit part");
                    }
                    return z;
                }
                int i3 = i2 & 255;
                if (i3 == 13 || i3 == 10) {
                    i = 0;
                } else if (i3 == 0 || (i = i + 1) > 998) {
                    return false;
                }
                if (i3 > 127) {
                    z = true;
                }
            } catch (IOException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        try {
            closeConnection();
        } catch (MessagingException unused) {
        }
    }

    protected void helo(String str) throws MessagingException {
        if (str != null) {
            issueCommand("HELO " + str, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            return;
        }
        issueCommand("HELO", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    protected boolean ehlo(String str) throws MessagingException {
        String str2;
        if (str != null) {
            str2 = "EHLO " + str;
        } else {
            str2 = "EHLO";
        }
        sendCommand(str2);
        int serverResponse = readServerResponse();
        if (serverResponse == 250) {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(this.lastServerResponse));
            this.extMap = new Hashtable();
            boolean z = true;
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (z) {
                        z = false;
                    } else if (line.length() >= 5) {
                        String strSubstring = line.substring(4);
                        int iIndexOf = strSubstring.indexOf(32);
                        String strSubstring2 = "";
                        if (iIndexOf > 0) {
                            strSubstring2 = strSubstring.substring(iIndexOf + 1);
                            strSubstring = strSubstring.substring(0, iIndexOf);
                        }
                        if (this.debug) {
                            this.out.println("DEBUG SMTP: Found extension \"" + strSubstring + "\", arg \"" + strSubstring2 + "\"");
                        }
                        this.extMap.put(strSubstring.toUpperCase(Locale.ENGLISH), strSubstring2);
                    }
                } catch (IOException unused) {
                }
            }
        }
        return serverResponse == 250;
    }

    protected void mailFrom() throws MessagingException {
        Address localAddress;
        Address[] from;
        String envelopeFrom = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getEnvelopeFrom() : null;
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            envelopeFrom = this.session.getProperty("mail." + this.name + ".from");
        }
        if (envelopeFrom == null || envelopeFrom.length() <= 0) {
            if (this.message != null && (from = this.message.getFrom()) != null && from.length > 0) {
                localAddress = from[0];
            } else {
                localAddress = InternetAddress.getLocalAddress(this.session);
            }
            if (localAddress != null) {
                envelopeFrom = ((InternetAddress) localAddress).getAddress();
            } else {
                throw new MessagingException("can't determine local email address");
            }
        }
        String str = "MAIL FROM:" + normalizeAddress(envelopeFrom);
        if (supportsExtension("DSN")) {
            String dSNRet = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getDSNRet() : null;
            if (dSNRet == null) {
                dSNRet = this.session.getProperty("mail." + this.name + ".dsn.ret");
            }
            if (dSNRet != null) {
                str = String.valueOf(str) + " RET=" + dSNRet;
            }
        }
        if (supportsExtension("AUTH")) {
            String submitter = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getSubmitter() : null;
            if (submitter == null) {
                submitter = this.session.getProperty("mail." + this.name + ".submitter");
            }
            if (submitter != null) {
                try {
                    str = String.valueOf(str) + " AUTH=" + xtext(submitter);
                } catch (IllegalArgumentException e) {
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: ignoring invalid submitter: " + submitter + ", Exception: " + e);
                    }
                }
            }
        }
        String mailExtension = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getMailExtension() : null;
        if (mailExtension == null) {
            mailExtension = this.session.getProperty("mail." + this.name + ".mailextension");
        }
        if (mailExtension != null && mailExtension.length() > 0) {
            str = String.valueOf(str) + " " + mailExtension;
        }
        issueSendCommand(str, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x024c  */
    /* JADX WARN: Code duplicated, block: B:138:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:141:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:154:0x02fe A[PHI: r8 r11
  0x02fe: PHI (r8v7 java.lang.Exception) = (r8v6 java.lang.Exception), (r8v11 java.lang.Exception), (r8v12 java.lang.Exception), (r8v16 java.lang.Exception) binds: [B:159:0x0311, B:153:0x02fc, B:147:0x02e7, B:133:0x029a] A[DONT_GENERATE, DONT_INLINE]
  0x02fe: PHI (r11v6 boolean) = (r11v4 boolean), (r11v1 boolean), (r11v8 boolean), (r11v10 boolean) binds: [B:159:0x0311, B:153:0x02fc, B:147:0x02e7, B:133:0x029a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:156:0x0304 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:157:0x0306  */
    /* JADX WARN: Code duplicated, block: B:161:0x0314  */
    /* JADX WARN: Instruction removed from duplicated block: B:112:0x024c, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:138:0x02a6, please report this as an issue */
    protected void rcptTo() throws MessagingException {
        String str;
        boolean z;
        MessagingException messagingException;
        int i;
        boolean z2;
        InternetAddress internetAddress;
        String str2;
        int serverResponse;
        Exception sMTPAddressFailedException;
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        this.invalidAddr = null;
        this.validUnsentAddr = null;
        this.validSentAddr = null;
        boolean sendPartial = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getSendPartial() : false;
        boolean z3 = true;
        if (!sendPartial) {
            String property = this.session.getProperty("mail." + this.name + ".sendpartial");
            sendPartial = property != null && property.equalsIgnoreCase("true");
        }
        if (this.debug && sendPartial) {
            this.out.println("DEBUG SMTP: sendPartial set");
        }
        if (supportsExtension("DSN")) {
            String dSNNotify = this.message instanceof SMTPMessage ? ((SMTPMessage) this.message).getDSNNotify() : null;
            if (dSNNotify == null) {
                dSNNotify = this.session.getProperty("mail." + this.name + ".dsn.notify");
            }
            if (dSNNotify != null) {
                str = dSNNotify;
                z = true;
            } else {
                str = dSNNotify;
            }
            messagingException = null;
            i = 0;
            z2 = false;
            while (i < this.addresses.length) {
                internetAddress = (InternetAddress) this.addresses[i];
                str2 = "RCPT TO:" + normalizeAddress(internetAddress.getAddress());
                if (z) {
                    str2 = String.valueOf(str2) + " NOTIFY=" + str;
                }
                sendCommand(str2);
                serverResponse = readServerResponse();
                if (serverResponse == 501 && serverResponse != 503) {
                    switch (serverResponse) {
                        case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                        case 251:
                            vector.addElement(internetAddress);
                            if (this.reportSuccess) {
                                sMTPAddressFailedException = new SMTPAddressSucceededException(internetAddress, str2, serverResponse, this.lastServerResponse);
                                if (messagingException == null) {
                                    messagingException = sMTPAddressFailedException;
                                } else {
                                    messagingException.setNextException(sMTPAddressFailedException);
                                }
                            }
                            break;
                        default:
                            switch (serverResponse) {
                                default:
                                    switch (serverResponse) {
                                        case 550:
                                        case 551:
                                        case 553:
                                            if (!sendPartial) {
                                                z2 = true;
                                            }
                                            vector3.addElement(internetAddress);
                                            sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                                            if (messagingException == null) {
                                                messagingException = sMTPAddressFailedException;
                                            } else {
                                                messagingException.setNextException(sMTPAddressFailedException);
                                            }
                                            break;
                                        case 552:
                                            break;
                                        default:
                                            if (serverResponse >= 400 && serverResponse <= 499) {
                                                vector2.addElement(internetAddress);
                                            } else if (serverResponse >= 500 && serverResponse <= 599) {
                                                vector3.addElement(internetAddress);
                                            } else {
                                                if (this.debug) {
                                                    this.out.println("DEBUG SMTP: got response code " + serverResponse + ", with response: " + this.lastServerResponse);
                                                }
                                                String str3 = this.lastServerResponse;
                                                int i2 = this.lastReturnCode;
                                                if (this.serverSocket != null) {
                                                    issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                                                }
                                                this.lastServerResponse = str3;
                                                this.lastReturnCode = i2;
                                                throw new SMTPAddressFailedException(internetAddress, str2, serverResponse, str3);
                                            }
                                            if (!sendPartial) {
                                                z2 = true;
                                            }
                                            sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                                            if (messagingException == null) {
                                                messagingException = sMTPAddressFailedException;
                                            } else {
                                                messagingException.setNextException(sMTPAddressFailedException);
                                            }
                                            break;
                                    }
                                case 450:
                                case 451:
                                case 452:
                                    if (!sendPartial) {
                                        z2 = true;
                                    }
                                    vector2.addElement(internetAddress);
                                    sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                                    if (messagingException == null) {
                                        messagingException = sMTPAddressFailedException;
                                    } else {
                                        messagingException.setNextException(sMTPAddressFailedException);
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    if (!sendPartial) {
                        z2 = true;
                    }
                    vector3.addElement(internetAddress);
                    sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                    if (messagingException == null) {
                        messagingException = sMTPAddressFailedException;
                    } else {
                        messagingException.setNextException(sMTPAddressFailedException);
                    }
                }
                i++;
                z3 = true;
            }
            if (sendPartial && vector.size() == 0) {
                z2 = true;
            }
            if (z2) {
                this.invalidAddr = new Address[vector3.size()];
                vector3.copyInto(this.invalidAddr);
                this.validUnsentAddr = new Address[vector.size() + vector2.size()];
                int i3 = 0;
                int i4 = 0;
                while (i3 < vector.size()) {
                    this.validUnsentAddr[i4] = (Address) vector.elementAt(i3);
                    i3++;
                    i4++;
                }
                int i5 = 0;
                while (i5 < vector2.size()) {
                    this.validUnsentAddr[i4] = (Address) vector2.elementAt(i5);
                    i5++;
                    i4++;
                }
            } else if (this.reportSuccess || (sendPartial && (vector3.size() > 0 || vector2.size() > 0))) {
                this.sendPartiallyFailed = z3;
                this.exception = messagingException;
                this.invalidAddr = new Address[vector3.size()];
                vector3.copyInto(this.invalidAddr);
                this.validUnsentAddr = new Address[vector2.size()];
                vector2.copyInto(this.validUnsentAddr);
                this.validSentAddr = new Address[vector.size()];
                vector.copyInto(this.validSentAddr);
            } else {
                this.validSentAddr = this.addresses;
            }
            if (this.debug) {
                if (this.validSentAddr != null && this.validSentAddr.length > 0) {
                    this.out.println("DEBUG SMTP: Verified Addresses");
                    for (int i6 = 0; i6 < this.validSentAddr.length; i6++) {
                        this.out.println("DEBUG SMTP:   " + this.validSentAddr[i6]);
                    }
                }
                if (this.validUnsentAddr != null && this.validUnsentAddr.length > 0) {
                    this.out.println("DEBUG SMTP: Valid Unsent Addresses");
                    for (int i7 = 0; i7 < this.validUnsentAddr.length; i7++) {
                        this.out.println("DEBUG SMTP:   " + this.validUnsentAddr[i7]);
                    }
                }
                if (this.invalidAddr != null && this.invalidAddr.length > 0) {
                    this.out.println("DEBUG SMTP: Invalid Addresses");
                    for (int i8 = 0; i8 < this.invalidAddr.length; i8++) {
                        this.out.println("DEBUG SMTP:   " + this.invalidAddr[i8]);
                    }
                }
            }
            if (z2) {
                if (this.debug) {
                    this.out.println("DEBUG SMTP: Sending failed because of invalid destination addresses");
                }
                notifyTransportListeners(2, this.validSentAddr, this.validUnsentAddr, this.invalidAddr, this.message);
                String str4 = this.lastServerResponse;
                int i9 = this.lastReturnCode;
                try {
                    try {
                        try {
                            if (this.serverSocket != null) {
                                issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                            }
                        } catch (MessagingException e) {
                            if (this.debug) {
                                ThrowableExtension.printStackTrace(e, this.out);
                            }
                        }
                    } finally {
                        this.lastServerResponse = str4;
                        this.lastReturnCode = i9;
                    }
                } catch (MessagingException unused) {
                    close();
                }
                throw new SendFailedException("Invalid Addresses", messagingException, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
            }
            return;
        }
        str = null;
        z = false;
        messagingException = null;
        i = 0;
        z2 = false;
        while (true) {
            internetAddress = (InternetAddress) this.addresses[i];
            str2 = "RCPT TO:" + normalizeAddress(internetAddress.getAddress());
            if (z) {
                str2 = String.valueOf(str2) + " NOTIFY=" + str;
            }
            sendCommand(str2);
            serverResponse = readServerResponse();
            if (serverResponse == 501) {
                if (!sendPartial) {
                    z2 = true;
                }
                vector3.addElement(internetAddress);
                sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                if (messagingException == null) {
                    messagingException = sMTPAddressFailedException;
                } else {
                    messagingException.setNextException(sMTPAddressFailedException);
                }
            } else {
                if (!sendPartial) {
                    z2 = true;
                }
                vector3.addElement(internetAddress);
                sMTPAddressFailedException = new SMTPAddressFailedException(internetAddress, str2, serverResponse, this.lastServerResponse);
                if (messagingException == null) {
                    messagingException = sMTPAddressFailedException;
                } else {
                    messagingException.setNextException(sMTPAddressFailedException);
                }
            }
            i++;
            z3 = true;
        }
        if (this.debug) {
            this.out.println("DEBUG SMTP: got response code " + serverResponse + ", with response: " + this.lastServerResponse);
        }
        String str5 = this.lastServerResponse;
        int i10 = this.lastReturnCode;
        if (this.serverSocket != null) {
            issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
        this.lastServerResponse = str5;
        this.lastReturnCode = i10;
        throw new SMTPAddressFailedException(internetAddress, str2, serverResponse, str5);
    }

    protected OutputStream data() throws MessagingException {
        issueSendCommand("DATA", 354);
        this.dataStream = new SMTPOutputStream(this.serverOutput);
        return this.dataStream;
    }

    protected void finishData() throws MessagingException, IOException {
        this.dataStream.ensureAtBOL();
        issueSendCommand(".", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    protected void startTLS() throws MessagingException {
        issueCommand("STARTTLS", 220);
        try {
            this.serverSocket = SocketFetcher.startTLS(this.serverSocket, this.session.getProperties(), "mail." + this.name);
            initStreams();
        } catch (IOException e) {
            closeConnection();
            throw new MessagingException("Could not convert socket to TLS", e);
        }
    }

    private void openServer(String str, int i) throws MessagingException {
        int port;
        IOException e;
        if (this.debug) {
            this.out.println("DEBUG SMTP: trying to connect to host \"" + str + "\", port " + i + ", isSSL " + this.isSSL);
        }
        try {
            try {
                this.serverSocket = SocketFetcher.getSocket(str, i, this.session.getProperties(), "mail." + this.name, this.isSSL);
                port = this.serverSocket.getPort();
                try {
                    initStreams();
                    int serverResponse = readServerResponse();
                    if (serverResponse != 220) {
                        this.serverSocket.close();
                        this.serverSocket = null;
                        this.serverOutput = null;
                        this.serverInput = null;
                        this.lineInputStream = null;
                        if (this.debug) {
                            this.out.println("DEBUG SMTP: could not connect to host \"" + str + "\", port: " + port + ", response: " + serverResponse + "\n");
                        }
                        throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + port + ", response: " + serverResponse);
                    }
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: connected to host \"" + str + "\", port: " + port + "\n");
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new MessagingException("Could not connect to SMTP host: " + str + ", port: " + port, e);
                }
            } catch (UnknownHostException e3) {
                throw new MessagingException("Unknown SMTP host: " + str, e3);
            }
        } catch (IOException e4) {
            port = i;
            e = e4;
        }
    }

    private void openServer() throws MessagingException {
        String hostName;
        IOException e;
        int port;
        try {
            port = this.serverSocket.getPort();
            try {
                hostName = this.serverSocket.getInetAddress().getHostName();
                try {
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: starting protocol to host \"" + hostName + "\", port " + port);
                    }
                    initStreams();
                    int serverResponse = readServerResponse();
                    if (serverResponse != 220) {
                        this.serverSocket.close();
                        this.serverSocket = null;
                        this.serverOutput = null;
                        this.serverInput = null;
                        this.lineInputStream = null;
                        if (this.debug) {
                            this.out.println("DEBUG SMTP: got bad greeting from host \"" + hostName + "\", port: " + port + ", response: " + serverResponse + "\n");
                        }
                        throw new MessagingException("Got bad greeting from SMTP host: " + hostName + ", port: " + port + ", response: " + serverResponse);
                    }
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: protocol started to host \"" + hostName + "\", port: " + port + "\n");
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new MessagingException("Could not start protocol to SMTP host: " + hostName + ", port: " + port, e);
                }
            } catch (IOException e3) {
                hostName = UNKNOWN;
                e = e3;
            }
        } catch (IOException e4) {
            hostName = UNKNOWN;
            e = e4;
            port = -1;
        }
    }

    private void initStreams() throws IOException {
        Properties properties = this.session.getProperties();
        PrintStream debugOut = this.session.getDebugOut();
        boolean debug = this.session.getDebug();
        String property = properties.getProperty("mail.debug.quote");
        boolean z = property != null && property.equalsIgnoreCase("true");
        TraceInputStream traceInputStream = new TraceInputStream(this.serverSocket.getInputStream(), debugOut);
        traceInputStream.setTrace(debug);
        traceInputStream.setQuote(z);
        TraceOutputStream traceOutputStream = new TraceOutputStream(this.serverSocket.getOutputStream(), debugOut);
        traceOutputStream.setTrace(debug);
        traceOutputStream.setQuote(z);
        this.serverOutput = new BufferedOutputStream(traceOutputStream);
        this.serverInput = new BufferedInputStream(traceInputStream);
        this.lineInputStream = new LineInputStream(this.serverInput);
    }

    public synchronized void issueCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        if (readServerResponse() != i) {
            throw new MessagingException(this.lastServerResponse);
        }
    }

    private void issueSendCommand(String str, int i) throws MessagingException {
        sendCommand(str);
        int serverResponse = readServerResponse();
        if (serverResponse != i) {
            int length = this.validSentAddr == null ? 0 : this.validSentAddr.length;
            int length2 = this.validUnsentAddr == null ? 0 : this.validUnsentAddr.length;
            Address[] addressArr = new Address[length + length2];
            if (length > 0) {
                System.arraycopy(this.validSentAddr, 0, addressArr, 0, length);
            }
            if (length2 > 0) {
                System.arraycopy(this.validUnsentAddr, 0, addressArr, length, length2);
            }
            this.validSentAddr = null;
            this.validUnsentAddr = addressArr;
            if (this.debug) {
                this.out.println("DEBUG SMTP: got response code " + serverResponse + ", with response: " + this.lastServerResponse);
            }
            String str2 = this.lastServerResponse;
            int i2 = this.lastReturnCode;
            if (this.serverSocket != null) {
                issueCommand("RSET", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
            this.lastServerResponse = str2;
            this.lastReturnCode = i2;
            throw new SMTPSendFailedException(str, serverResponse, this.lastServerResponse, this.exception, this.validSentAddr, this.validUnsentAddr, this.invalidAddr);
        }
    }

    public synchronized int simpleCommand(String str) throws MessagingException {
        sendCommand(str);
        return readServerResponse();
    }

    protected int simpleCommand(byte[] bArr) throws MessagingException {
        sendCommand(bArr);
        return readServerResponse();
    }

    protected void sendCommand(String str) throws MessagingException {
        sendCommand(ASCIIUtility.getBytes(str));
    }

    private void sendCommand(byte[] bArr) throws MessagingException {
        try {
            this.serverOutput.write(bArr);
            this.serverOutput.write(CRLF);
            this.serverOutput.flush();
        } catch (IOException e) {
            throw new MessagingException("Can't send command to SMTP host", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x007d  */
    protected int readServerResponse() throws MessagingException {
        String line;
        int i;
        StringBuffer stringBuffer = new StringBuffer(100);
        do {
            try {
                line = this.lineInputStream.readLine();
                if (line == null) {
                    String string = stringBuffer.toString();
                    if (string.length() == 0) {
                        string = "[EOF]";
                    }
                    this.lastServerResponse = string;
                    this.lastReturnCode = -1;
                    if (this.debug) {
                        this.out.println("DEBUG SMTP: EOF: " + string);
                    }
                    return -1;
                }
                stringBuffer.append(line);
                stringBuffer.append("\n");
            } catch (IOException e) {
                if (this.debug) {
                    this.out.println("DEBUG SMTP: exception reading response: " + e);
                }
                this.lastServerResponse = "";
                this.lastReturnCode = 0;
                throw new MessagingException("Exception reading response", e);
            }
        } while (isNotLastLine(line));
        String string2 = stringBuffer.toString();
        if (string2 == null || string2.length() < 3) {
            i = -1;
        } else {
            try {
                try {
                    try {
                        i = Integer.parseInt(string2.substring(0, 3));
                    } catch (MessagingException e2) {
                        if (this.debug) {
                            ThrowableExtension.printStackTrace(e2, this.out);
                        }
                        i = -1;
                    }
                } catch (MessagingException e3) {
                    if (this.debug) {
                        ThrowableExtension.printStackTrace(e3, this.out);
                    }
                    i = -1;
                }
            } catch (NumberFormatException unused) {
                close();
                i = -1;
                if (i == -1) {
                    this.out.println("DEBUG SMTP: bad server response: " + string2);
                }
                this.lastServerResponse = string2;
                this.lastReturnCode = i;
                return i;
            } catch (StringIndexOutOfBoundsException unused2) {
                close();
                i = -1;
                if (i == -1) {
                    this.out.println("DEBUG SMTP: bad server response: " + string2);
                }
                this.lastServerResponse = string2;
                this.lastReturnCode = i;
                return i;
            }
        }
        if (i == -1 && this.debug) {
            this.out.println("DEBUG SMTP: bad server response: " + string2);
        }
        this.lastServerResponse = string2;
        this.lastReturnCode = i;
        return i;
    }

    protected void checkConnected() {
        if (!super.isConnected()) {
            throw new IllegalStateException("Not connected");
        }
    }

    private boolean isNotLastLine(String str) {
        return str != null && str.length() >= 4 && str.charAt(3) == '-';
    }

    private String normalizeAddress(String str) {
        if (str.startsWith("<") || str.endsWith(">")) {
            return str;
        }
        return "<" + str + ">";
    }

    public boolean supportsExtension(String str) {
        return (this.extMap == null || this.extMap.get(str.toUpperCase(Locale.ENGLISH)) == null) ? false : true;
    }

    public String getExtensionParameter(String str) {
        if (this.extMap == null) {
            return null;
        }
        return (String) this.extMap.get(str.toUpperCase(Locale.ENGLISH));
    }

    protected boolean supportsAuthentication(String str) {
        String str2;
        if (this.extMap == null || (str2 = (String) this.extMap.get("AUTH")) == null) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str2);
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    protected static String xtext(String str) {
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= 128) {
                throw new IllegalArgumentException("Non-ASCII character in SMTP submitter: " + str);
            }
            if (cCharAt < '!' || cCharAt > '~' || cCharAt == '+' || cCharAt == '=') {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length() + 4);
                    stringBuffer.append(str.substring(0, i));
                }
                stringBuffer.append('+');
                stringBuffer.append(hexchar[(cCharAt & 240) >> 4]);
                stringBuffer.append(hexchar[cCharAt & 15]);
            } else if (stringBuffer != null) {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer != null ? stringBuffer.toString() : str;
    }
}
