package com.sun.mail.imap.protocol;

import android.support.v7.widget.ActivityChooserView;
import com.github.kevinsawicki.http.HttpRequest;
import com.sun.mail.iap.Argument;
import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.Literal;
import com.sun.mail.iap.LiteralException;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.imap.ACL;
import com.sun.mail.imap.AppendUID;
import com.sun.mail.imap.Rights;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import com.umeng.commonsdk.proguard.C1770bg;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Flags;
import javax.mail.Quota;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchException;
import javax.mail.search.SearchTerm;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class IMAPProtocol extends Protocol {
    private static final byte[] CRLF = {C1770bg.f4123k, 10};
    private static final byte[] DONE = {68, 79, 78, 69, C1770bg.f4123k, 10};
    private boolean authenticated;
    private List authmechs;

    /* JADX INFO: renamed from: ba */
    private ByteArray f3605ba;
    private Map capabilities;
    private boolean connected;
    private String idleTag;
    private String name;
    private boolean rev1;
    private SaslAuthenticator saslAuthenticator;
    private String[] searchCharsets;

    public IMAPProtocol(String str, String str2, int i, boolean z, PrintStream printStream, Properties properties, boolean z2) throws ProtocolException, IOException {
        super(str2, i, z, printStream, properties, "mail." + str, z2);
        this.connected = false;
        this.rev1 = false;
        this.capabilities = null;
        this.authmechs = null;
        try {
            this.name = str;
            if (this.capabilities == null) {
                capability();
            }
            if (hasCapability("IMAP4rev1")) {
                this.rev1 = true;
            }
            this.searchCharsets = new String[2];
            this.searchCharsets[0] = "UTF-8";
            this.searchCharsets[1] = MimeUtility.mimeCharset(MimeUtility.getDefaultJavaCharset());
            this.connected = true;
        } finally {
            if (!this.connected) {
                disconnect();
            }
        }
    }

    public void capability() throws ProtocolException {
        Response[] responseArrCommand = command("CAPABILITY", null);
        if (!responseArrCommand[responseArrCommand.length - 1].isOK()) {
            throw new ProtocolException(responseArrCommand[responseArrCommand.length - 1].toString());
        }
        this.capabilities = new HashMap(10);
        this.authmechs = new ArrayList(5);
        int length = responseArrCommand.length;
        for (int i = 0; i < length; i++) {
            if (responseArrCommand[i] instanceof IMAPResponse) {
                IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                if (iMAPResponse.keyEquals("CAPABILITY")) {
                    parseCapabilities(iMAPResponse);
                }
            }
        }
    }

    protected void setCapabilities(Response response) {
        byte b;
        do {
            b = response.readByte();
            if (b <= 0) {
                break;
            }
        } while (b != 91);
        if (b != 0 && response.readAtom().equalsIgnoreCase("CAPABILITY")) {
            this.capabilities = new HashMap(10);
            this.authmechs = new ArrayList(5);
            parseCapabilities(response);
        }
    }

    protected void parseCapabilities(Response response) {
        while (true) {
            String atom = response.readAtom(']');
            if (atom == null) {
                return;
            }
            if (atom.length() == 0) {
                if (response.peekByte() == 93) {
                    return;
                } else {
                    response.skipToken();
                }
            } else {
                this.capabilities.put(atom.toUpperCase(Locale.ENGLISH), atom);
                if (atom.regionMatches(true, 0, "AUTH=", 0, 5)) {
                    this.authmechs.add(atom.substring(5));
                    if (this.debug) {
                        this.out.println("IMAP DEBUG: AUTH: " + atom.substring(5));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public void processGreeting(Response response) throws ProtocolException {
        super.processGreeting(response);
        if (response.isOK()) {
            setCapabilities(response);
        } else {
            if (((IMAPResponse) response).keyEquals("PREAUTH")) {
                this.authenticated = true;
                setCapabilities(response);
                return;
            }
            throw new ConnectionException(this, response);
        }
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isREV1() {
        return this.rev1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public boolean supportsNonSyncLiterals() {
        return hasCapability("LITERAL+");
    }

    @Override // com.sun.mail.iap.Protocol
    public Response readResponse() throws ProtocolException, IOException {
        return IMAPResponse.readResponse(this);
    }

    public boolean hasCapability(String str) {
        return this.capabilities.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    public Map getCapabilities() {
        return this.capabilities;
    }

    @Override // com.sun.mail.iap.Protocol
    public void disconnect() {
        super.disconnect();
        this.authenticated = false;
    }

    public void noop() throws ProtocolException {
        if (this.debug) {
            this.out.println("IMAP DEBUG: IMAPProtocol noop");
        }
        simpleCommand("NOOP", null);
    }

    public void logout() throws ProtocolException {
        Response[] responseArrCommand = command("LOGOUT", null);
        this.authenticated = false;
        notifyResponseHandlers(responseArrCommand);
        disconnect();
    }

    public void login(String str, String str2) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        argument.writeString(str2);
        Response[] responseArrCommand = command("LOGIN", argument);
        notifyResponseHandlers(responseArrCommand);
        handleResult(responseArrCommand[responseArrCommand.length - 1]);
        setCapabilities(responseArrCommand[responseArrCommand.length - 1]);
        this.authenticated = true;
    }

    public synchronized void authlogin(String str, String str2) throws ProtocolException {
        Response responseByeResponse;
        String strWriteCommand;
        boolean z;
        boolean z2;
        String str3;
        Vector vector = new Vector();
        try {
            strWriteCommand = writeCommand("AUTHENTICATE LOGIN", null);
            responseByeResponse = null;
            z = false;
        } catch (Exception e) {
            responseByeResponse = Response.byeResponse(e);
            strWriteCommand = null;
            z = true;
        }
        OutputStream outputStream = getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        Response responseByeResponse2 = responseByeResponse;
        boolean z3 = true;
        while (!z) {
            try {
                responseByeResponse2 = readResponse();
                if (responseByeResponse2.isContinuation()) {
                    if (z3) {
                        str3 = str;
                        z2 = false;
                    } else {
                        z2 = z3;
                        str3 = str2;
                    }
                    try {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(str3));
                        bASE64EncoderStream.flush();
                        byteArrayOutputStream.write(CRLF);
                        outputStream.write(byteArrayOutputStream.toByteArray());
                        outputStream.flush();
                        byteArrayOutputStream.reset();
                        z3 = z2;
                    } catch (Exception e2) {
                        e = e2;
                        z3 = z2;
                        responseByeResponse2 = Response.byeResponse(e);
                        z = true;
                    }
                } else if ((responseByeResponse2.isTagged() && responseByeResponse2.getTag().equals(strWriteCommand)) || responseByeResponse2.isBYE()) {
                    z = true;
                } else {
                    vector.addElement(responseByeResponse2);
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        Response[] responseArr = new Response[vector.size()];
        vector.copyInto(responseArr);
        notifyResponseHandlers(responseArr);
        handleResult(responseByeResponse2);
        setCapabilities(responseByeResponse2);
        this.authenticated = true;
    }

    public synchronized void authplain(String str, String str2, String str3) throws ProtocolException {
        Response responseByeResponse;
        try {
            Vector vector = new Vector();
            boolean z = false;
            String strWriteCommand = null;
            try {
                responseByeResponse = null;
                strWriteCommand = writeCommand("AUTHENTICATE PLAIN", null);
            } catch (Exception e) {
                responseByeResponse = Response.byeResponse(e);
                z = true;
            }
            OutputStream outputStream = getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            while (!z) {
                try {
                    responseByeResponse = readResponse();
                    if (responseByeResponse.isContinuation()) {
                        bASE64EncoderStream.write(ASCIIUtility.getBytes(String.valueOf(str) + "\u0000" + str2 + "\u0000" + str3));
                        bASE64EncoderStream.flush();
                        byteArrayOutputStream.write(CRLF);
                        outputStream.write(byteArrayOutputStream.toByteArray());
                        outputStream.flush();
                        byteArrayOutputStream.reset();
                    } else if ((responseByeResponse.isTagged() && responseByeResponse.getTag().equals(strWriteCommand)) || responseByeResponse.isBYE()) {
                        z = true;
                    } else {
                        vector.addElement(responseByeResponse);
                    }
                } catch (Exception e2) {
                    responseByeResponse = Response.byeResponse(e2);
                }
            }
            Response[] responseArr = new Response[vector.size()];
            vector.copyInto(responseArr);
            notifyResponseHandlers(responseArr);
            handleResult(responseByeResponse);
            setCapabilities(responseByeResponse);
            this.authenticated = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void sasllogin(String[] strArr, String str, String str2, String str3, String str4) throws ProtocolException {
        List arrayList;
        if (this.saslAuthenticator == null) {
            try {
                Constructor<?> constructor = Class.forName("com.sun.mail.imap.protocol.IMAPSaslAuthenticator").getConstructor(IMAPProtocol.class, String.class, Properties.class, Boolean.TYPE, PrintStream.class, String.class);
                Object[] objArr = new Object[6];
                objArr[0] = this;
                objArr[1] = this.name;
                objArr[2] = this.props;
                objArr[3] = this.debug ? Boolean.TRUE : Boolean.FALSE;
                objArr[4] = this.out;
                objArr[5] = this.host;
                this.saslAuthenticator = (SaslAuthenticator) constructor.newInstance(objArr);
            } catch (Exception e) {
                if (this.debug) {
                    this.out.println("IMAP DEBUG: Can't load SASL authenticator: " + e);
                    return;
                }
                return;
            }
        }
        if (strArr != null && strArr.length > 0) {
            arrayList = new ArrayList(strArr.length);
            for (int i = 0; i < strArr.length; i++) {
                if (this.authmechs.contains(strArr[i])) {
                    arrayList.add(strArr[i]);
                }
            }
        } else {
            arrayList = this.authmechs;
        }
        if (this.saslAuthenticator.authenticate((String[]) arrayList.toArray(new String[arrayList.size()]), str, str2, str3, str4)) {
            this.authenticated = true;
        }
    }

    OutputStream getIMAPOutputStream() {
        return getOutputStream();
    }

    public void proxyauth(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(str);
        simpleCommand("PROXYAUTH", argument);
    }

    public void startTLS() throws ProtocolException {
        try {
            super.startTLS("STARTTLS");
        } catch (ProtocolException e) {
            throw e;
        } catch (Exception e2) {
            notifyResponseHandlers(new Response[]{Response.byeResponse(e2)});
            disconnect();
        }
    }

    public MailboxInfo select(String str) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Response[] responseArrCommand = command("SELECT", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(responseArrCommand);
        notifyResponseHandlers(responseArrCommand);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        if (response.isOK()) {
            if (response.toString().indexOf("READ-ONLY") != -1) {
                mailboxInfo.mode = 1;
            } else {
                mailboxInfo.mode = 2;
            }
        }
        handleResult(response);
        return mailboxInfo;
    }

    public MailboxInfo examine(String str) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Response[] responseArrCommand = command("EXAMINE", argument);
        MailboxInfo mailboxInfo = new MailboxInfo(responseArrCommand);
        mailboxInfo.mode = 1;
        notifyResponseHandlers(responseArrCommand);
        handleResult(responseArrCommand[responseArrCommand.length - 1]);
        return mailboxInfo;
    }

    public Status status(String str, String[] strArr) throws ProtocolException {
        if (!isREV1() && !hasCapability("IMAP4SUNVERSION")) {
            throw new BadCommandException("STATUS not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Argument argument2 = new Argument();
        if (strArr == null) {
            strArr = Status.standardItems;
        }
        for (String str2 : strArr) {
            argument2.writeAtom(str2);
        }
        argument.writeArgument(argument2);
        Response[] responseArrCommand = command("STATUS", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        Status status = null;
        if (response.isOK()) {
            int length = responseArrCommand.length;
            Status status2 = null;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("STATUS")) {
                        if (status2 == null) {
                            status2 = new Status(iMAPResponse);
                        } else {
                            Status.add(status2, new Status(iMAPResponse));
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
            status = status2;
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        return status;
    }

    public void create(String str) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        simpleCommand("CREATE", argument);
    }

    public void delete(String str) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        simpleCommand(HttpRequest.METHOD_DELETE, argument);
    }

    public void rename(String str, String str2) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str);
        String strEncode2 = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        argument.writeString(strEncode2);
        simpleCommand("RENAME", argument);
    }

    public void subscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("SUBSCRIBE", argument);
    }

    public void unsubscribe(String str) throws ProtocolException {
        Argument argument = new Argument();
        argument.writeString(BASE64MailboxEncoder.encode(str));
        simpleCommand("UNSUBSCRIBE", argument);
    }

    public ListInfo[] list(String str, String str2) throws ProtocolException {
        return doList("LIST", str, str2);
    }

    public ListInfo[] lsub(String str, String str2) throws ProtocolException {
        return doList("LSUB", str, str2);
    }

    private ListInfo[] doList(String str, String str2, String str3) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str2);
        String strEncode2 = BASE64MailboxEncoder.encode(str3);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        argument.writeString(strEncode2);
        Response[] responseArrCommand = command(str, argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        ListInfo[] listInfoArr = null;
        if (response.isOK()) {
            Vector vector = new Vector(1);
            int length = responseArrCommand.length;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals(str)) {
                        vector.addElement(new ListInfo(iMAPResponse));
                        responseArrCommand[i] = null;
                    }
                }
            }
            if (vector.size() > 0) {
                listInfoArr = new ListInfo[vector.size()];
                vector.copyInto(listInfoArr);
            }
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        return listInfoArr;
    }

    public void append(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        appenduid(str, flags, date, literal, false);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal) throws ProtocolException {
        return appenduid(str, flags, date, literal, true);
    }

    public AppendUID appenduid(String str, Flags flags, Date date, Literal literal, boolean z) throws ProtocolException {
        Flags flags2;
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        if (flags != null) {
            if (flags.contains(Flags.Flag.RECENT)) {
                flags2 = new Flags(flags);
                flags2.remove(Flags.Flag.RECENT);
            } else {
                flags2 = flags;
            }
            argument.writeAtom(createFlagList(flags2));
        }
        if (date != null) {
            argument.writeString(INTERNALDATE.format(date));
        }
        argument.writeBytes(literal);
        Response[] responseArrCommand = command("APPEND", argument);
        notifyResponseHandlers(responseArrCommand);
        handleResult(responseArrCommand[responseArrCommand.length - 1]);
        if (z) {
            return getAppendUID(responseArrCommand[responseArrCommand.length - 1]);
        }
        return null;
    }

    private AppendUID getAppendUID(Response response) {
        byte b;
        if (!response.isOK()) {
            return null;
        }
        do {
            b = response.readByte();
            if (b <= 0) {
                break;
            }
        } while (b != 91);
        if (b != 0 && response.readAtom().equalsIgnoreCase("APPENDUID")) {
            return new AppendUID(response.readLong(), response.readLong());
        }
        return null;
    }

    public void check() throws ProtocolException {
        simpleCommand("CHECK", null);
    }

    public void close() throws ProtocolException {
        simpleCommand("CLOSE", null);
    }

    public void expunge() throws ProtocolException {
        simpleCommand("EXPUNGE", null);
    }

    public void uidexpunge(UIDSet[] uIDSetArr) throws ProtocolException {
        if (!hasCapability("UIDPLUS")) {
            throw new BadCommandException("UID EXPUNGE not supported");
        }
        simpleCommand("UID EXPUNGE " + UIDSet.toString(uIDSetArr), null);
    }

    public BODYSTRUCTURE fetchBodyStructure(int i) throws ProtocolException {
        Response[] responseArrFetch = fetch(i, "BODYSTRUCTURE");
        notifyResponseHandlers(responseArrFetch);
        Response response = responseArrFetch[responseArrFetch.length - 1];
        if (response.isOK()) {
            return (BODYSTRUCTURE) FetchResponse.getItem(responseArrFetch, i, BODYSTRUCTURE.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public BODY peekBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, true);
    }

    public BODY fetchBody(int i, String str) throws ProtocolException {
        return fetchBody(i, str, false);
    }

    protected BODY fetchBody(int i, String str, boolean z) throws ProtocolException {
        String str2;
        Response[] responseArrFetch;
        String str3;
        if (z) {
            StringBuilder sb = new StringBuilder("BODY.PEEK[");
            if (str == null) {
                str3 = "]";
            } else {
                str3 = String.valueOf(str) + "]";
            }
            sb.append(str3);
            responseArrFetch = fetch(i, sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder("BODY[");
            if (str == null) {
                str2 = "]";
            } else {
                str2 = String.valueOf(str) + "]";
            }
            sb2.append(str2);
            responseArrFetch = fetch(i, sb2.toString());
        }
        notifyResponseHandlers(responseArrFetch);
        Response response = responseArrFetch[responseArrFetch.length - 1];
        if (response.isOK()) {
            return (BODY) FetchResponse.getItem(responseArrFetch, i, BODY.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public BODY peekBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, null);
    }

    public BODY fetchBody(int i, String str, int i2, int i3) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, null);
    }

    public BODY peekBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, true, byteArray);
    }

    public BODY fetchBody(int i, String str, int i2, int i3, ByteArray byteArray) throws ProtocolException {
        return fetchBody(i, str, i2, i3, false, byteArray);
    }

    protected BODY fetchBody(int i, String str, int i2, int i3, boolean z, ByteArray byteArray) throws ProtocolException {
        String str2;
        this.f3605ba = byteArray;
        StringBuilder sb = new StringBuilder(String.valueOf(z ? "BODY.PEEK[" : "BODY["));
        if (str == null) {
            str2 = "]<";
        } else {
            str2 = String.valueOf(str) + "]<";
        }
        sb.append(str2);
        sb.append(String.valueOf(i2));
        sb.append(".");
        sb.append(String.valueOf(i3));
        sb.append(">");
        Response[] responseArrFetch = fetch(i, sb.toString());
        notifyResponseHandlers(responseArrFetch);
        Response response = responseArrFetch[responseArrFetch.length - 1];
        if (response.isOK()) {
            return (BODY) FetchResponse.getItem(responseArrFetch, i, BODY.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.mail.iap.Protocol
    public ByteArray getResponseBuffer() {
        ByteArray byteArray = this.f3605ba;
        this.f3605ba = null;
        return byteArray;
    }

    public RFC822DATA fetchRFC822(int i, String str) throws ProtocolException {
        String str2;
        if (str == null) {
            str2 = "RFC822";
        } else {
            str2 = "RFC822." + str;
        }
        Response[] responseArrFetch = fetch(i, str2);
        notifyResponseHandlers(responseArrFetch);
        Response response = responseArrFetch[responseArrFetch.length - 1];
        if (response.isOK()) {
            return (RFC822DATA) FetchResponse.getItem(responseArrFetch, i, RFC822DATA.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public Flags fetchFlags(int i) throws ProtocolException {
        Response[] responseArrFetch = fetch(i, "FLAGS");
        int length = responseArrFetch.length;
        Flags flags = null;
        for (int i2 = 0; i2 < length; i2++) {
            if (responseArrFetch[i2] != null && (responseArrFetch[i2] instanceof FetchResponse) && ((FetchResponse) responseArrFetch[i2]).getNumber() == i && (flags = (Flags) ((FetchResponse) responseArrFetch[i2]).getItem(Flags.class)) != null) {
                responseArrFetch[i2] = null;
                break;
            }
        }
        notifyResponseHandlers(responseArrFetch);
        handleResult(responseArrFetch[responseArrFetch.length - 1]);
        return flags;
    }

    public UID fetchUID(int i) throws ProtocolException {
        Response[] responseArrFetch = fetch(i, "UID");
        notifyResponseHandlers(responseArrFetch);
        Response response = responseArrFetch[responseArrFetch.length - 1];
        if (response.isOK()) {
            return (UID) FetchResponse.getItem(responseArrFetch, i, UID.class);
        }
        if (response.isNO()) {
            return null;
        }
        handleResult(response);
        return null;
    }

    public UID fetchSequenceNumber(long j) throws ProtocolException {
        Response[] responseArrFetch = fetch(String.valueOf(j), "UID", true);
        int length = responseArrFetch.length;
        UID uid = null;
        for (int i = 0; i < length; i++) {
            if (responseArrFetch[i] != null && (responseArrFetch[i] instanceof FetchResponse) && (uid = (UID) ((FetchResponse) responseArrFetch[i]).getItem(UID.class)) != null) {
                if (uid.uid == j) {
                    break;
                }
                uid = null;
            }
        }
        notifyResponseHandlers(responseArrFetch);
        handleResult(responseArrFetch[responseArrFetch.length - 1]);
        return uid;
    }

    public UID[] fetchSequenceNumbers(long j, long j2) throws ProtocolException {
        UID uid;
        StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(j)));
        sb.append(":");
        sb.append(j2 == -1 ? Marker.ANY_MARKER : String.valueOf(j2));
        Response[] responseArrFetch = fetch(sb.toString(), "UID", true);
        Vector vector = new Vector();
        int length = responseArrFetch.length;
        for (int i = 0; i < length; i++) {
            if (responseArrFetch[i] != null && (responseArrFetch[i] instanceof FetchResponse) && (uid = (UID) ((FetchResponse) responseArrFetch[i]).getItem(UID.class)) != null) {
                vector.addElement(uid);
            }
        }
        notifyResponseHandlers(responseArrFetch);
        handleResult(responseArrFetch[responseArrFetch.length - 1]);
        UID[] uidArr = new UID[vector.size()];
        vector.copyInto(uidArr);
        return uidArr;
    }

    public UID[] fetchSequenceNumbers(long[] jArr) throws ProtocolException {
        UID uid;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(String.valueOf(jArr[i]));
        }
        Response[] responseArrFetch = fetch(stringBuffer.toString(), "UID", true);
        Vector vector = new Vector();
        int length = responseArrFetch.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (responseArrFetch[i2] != null && (responseArrFetch[i2] instanceof FetchResponse) && (uid = (UID) ((FetchResponse) responseArrFetch[i2]).getItem(UID.class)) != null) {
                vector.addElement(uid);
            }
        }
        notifyResponseHandlers(responseArrFetch);
        handleResult(responseArrFetch[responseArrFetch.length - 1]);
        UID[] uidArr = new UID[vector.size()];
        vector.copyInto(uidArr);
        return uidArr;
    }

    public Response[] fetch(MessageSet[] messageSetArr, String str) throws ProtocolException {
        return fetch(MessageSet.toString(messageSetArr), str, false);
    }

    public Response[] fetch(int i, int i2, String str) throws ProtocolException {
        return fetch(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), str, false);
    }

    public Response[] fetch(int i, String str) throws ProtocolException {
        return fetch(String.valueOf(i), str, false);
    }

    private Response[] fetch(String str, String str2, boolean z) throws ProtocolException {
        if (z) {
            return command("UID FETCH " + str + " (" + str2 + ")", null);
        }
        return command("FETCH " + str + " (" + str2 + ")", null);
    }

    public void copy(MessageSet[] messageSetArr, String str) throws ProtocolException {
        copy(MessageSet.toString(messageSetArr), str);
    }

    public void copy(int i, int i2, String str) throws ProtocolException {
        copy(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), str);
    }

    private void copy(String str, String str2) throws ProtocolException {
        String strEncode = BASE64MailboxEncoder.encode(str2);
        Argument argument = new Argument();
        argument.writeAtom(str);
        argument.writeString(strEncode);
        simpleCommand("COPY", argument);
    }

    public void storeFlags(MessageSet[] messageSetArr, Flags flags, boolean z) throws ProtocolException {
        storeFlags(MessageSet.toString(messageSetArr), flags, z);
    }

    public void storeFlags(int i, int i2, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(String.valueOf(i)) + ":" + String.valueOf(i2), flags, z);
    }

    public void storeFlags(int i, Flags flags, boolean z) throws ProtocolException {
        storeFlags(String.valueOf(i), flags, z);
    }

    private void storeFlags(String str, Flags flags, boolean z) throws ProtocolException {
        Response[] responseArrCommand;
        if (z) {
            responseArrCommand = command("STORE " + str + " +FLAGS " + createFlagList(flags), null);
        } else {
            responseArrCommand = command("STORE " + str + " -FLAGS " + createFlagList(flags), null);
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(responseArrCommand[responseArrCommand.length - 1]);
    }

    private String createFlagList(Flags flags) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        boolean z = true;
        for (Flags.Flag flag : flags.getSystemFlags()) {
            if (flag == Flags.Flag.ANSWERED) {
                str = "\\Answered";
            } else if (flag == Flags.Flag.DELETED) {
                str = "\\Deleted";
            } else if (flag == Flags.Flag.DRAFT) {
                str = "\\Draft";
            } else if (flag == Flags.Flag.FLAGGED) {
                str = "\\Flagged";
            } else if (flag == Flags.Flag.RECENT) {
                str = "\\Recent";
            } else {
                if (flag == Flags.Flag.SEEN) {
                    str = "\\Seen";
                }
            }
            if (z) {
                z = false;
            } else {
                stringBuffer.append(' ');
            }
            stringBuffer.append(str);
        }
        String[] userFlags = flags.getUserFlags();
        for (String str2 : userFlags) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(' ');
            }
            stringBuffer.append(str2);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public int[] search(MessageSet[] messageSetArr, SearchTerm searchTerm) throws SearchException, ProtocolException {
        return search(MessageSet.toString(messageSetArr), searchTerm);
    }

    public int[] search(SearchTerm searchTerm) throws SearchException, ProtocolException {
        return search("ALL", searchTerm);
    }

    private int[] search(String str, SearchTerm searchTerm) throws SearchException, ProtocolException {
        if (SearchSequence.isAscii(searchTerm)) {
            try {
                return issueSearch(str, searchTerm, null);
            } catch (IOException unused) {
            }
        }
        for (int i = 0; i < this.searchCharsets.length; i++) {
            if (this.searchCharsets[i] != null) {
                try {
                    return issueSearch(str, searchTerm, this.searchCharsets[i]);
                } catch (CommandFailedException unused2) {
                    this.searchCharsets[i] = null;
                } catch (ProtocolException e) {
                    throw e;
                } catch (IOException unused3) {
                    continue;
                } catch (SearchException e2) {
                    throw e2;
                }
            }
        }
        throw new SearchException("Search failed");
    }

    private int[] issueSearch(String str, SearchTerm searchTerm, String str2) throws SearchException, ProtocolException, IOException {
        int[] iArr = null;
        Argument argumentGenerateSequence = SearchSequence.generateSequence(searchTerm, str2 == null ? null : MimeUtility.javaCharset(str2));
        argumentGenerateSequence.writeAtom(str);
        Response[] responseArrCommand = str2 == null ? command("SEARCH", argumentGenerateSequence) : command("SEARCH CHARSET " + str2, argumentGenerateSequence);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        if (response.isOK()) {
            Vector vector = new Vector();
            int length = responseArrCommand.length;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("SEARCH")) {
                        while (true) {
                            int number = iMAPResponse.readNumber();
                            if (number == -1) {
                                break;
                            }
                            vector.addElement(new Integer(number));
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
            int size = vector.size();
            int[] iArr2 = new int[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr2[i2] = ((Integer) vector.elementAt(i2)).intValue();
            }
            iArr = iArr2;
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        return iArr;
    }

    public Namespaces namespace() throws ProtocolException {
        if (!hasCapability("NAMESPACE")) {
            throw new BadCommandException("NAMESPACE not supported");
        }
        Namespaces namespaces = null;
        Response[] responseArrCommand = command("NAMESPACE", null);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        if (response.isOK()) {
            int length = responseArrCommand.length;
            Namespaces namespaces2 = null;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("NAMESPACE")) {
                        if (namespaces2 == null) {
                            namespaces2 = new Namespaces(iMAPResponse);
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
            namespaces = namespaces2;
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        return namespaces;
    }

    public Quota[] getQuotaRoot(String str) throws ProtocolException {
        if (!hasCapability("QUOTA")) {
            throw new BadCommandException("GETQUOTAROOT not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Response[] responseArrCommand = command("GETQUOTAROOT", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        Hashtable hashtable = new Hashtable();
        int i = 0;
        if (response.isOK()) {
            int length = responseArrCommand.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (responseArrCommand[i2] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i2];
                    if (iMAPResponse.keyEquals("QUOTAROOT")) {
                        iMAPResponse.readAtomString();
                        while (true) {
                            String atomString = iMAPResponse.readAtomString();
                            if (atomString == null) {
                                break;
                            }
                            hashtable.put(atomString, new Quota(atomString));
                        }
                        responseArrCommand[i2] = null;
                    } else if (iMAPResponse.keyEquals("QUOTA")) {
                        Quota quota = parseQuota(iMAPResponse);
                        hashtable.get(quota.quotaRoot);
                        hashtable.put(quota.quotaRoot, quota);
                        responseArrCommand[i2] = null;
                    }
                }
            }
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        Quota[] quotaArr = new Quota[hashtable.size()];
        Enumeration enumerationElements = hashtable.elements();
        while (enumerationElements.hasMoreElements()) {
            quotaArr[i] = (Quota) enumerationElements.nextElement();
            i++;
        }
        return quotaArr;
    }

    public Quota[] getQuota(String str) throws ProtocolException {
        if (!hasCapability("QUOTA")) {
            throw new BadCommandException("QUOTA not supported");
        }
        Argument argument = new Argument();
        argument.writeString(str);
        Response[] responseArrCommand = command("GETQUOTA", argument);
        Vector vector = new Vector();
        Response response = responseArrCommand[responseArrCommand.length - 1];
        if (response.isOK()) {
            int length = responseArrCommand.length;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("QUOTA")) {
                        vector.addElement(parseQuota(iMAPResponse));
                        responseArrCommand[i] = null;
                    }
                }
            }
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        Quota[] quotaArr = new Quota[vector.size()];
        vector.copyInto(quotaArr);
        return quotaArr;
    }

    public void setQuota(Quota quota) throws ProtocolException {
        if (!hasCapability("QUOTA")) {
            throw new BadCommandException("QUOTA not supported");
        }
        Argument argument = new Argument();
        argument.writeString(quota.quotaRoot);
        Argument argument2 = new Argument();
        if (quota.resources != null) {
            for (int i = 0; i < quota.resources.length; i++) {
                argument2.writeAtom(quota.resources[i].name);
                argument2.writeNumber(quota.resources[i].limit);
            }
        }
        argument.writeArgument(argument2);
        Response[] responseArrCommand = command("SETQUOTA", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
    }

    private Quota parseQuota(Response response) throws ParsingException {
        Quota quota = new Quota(response.readAtomString());
        response.skipSpaces();
        if (response.readByte() != 40) {
            throw new ParsingException("parse error in QUOTA");
        }
        Vector vector = new Vector();
        while (response.peekByte() != 41) {
            String atom = response.readAtom();
            if (atom != null) {
                vector.addElement(new Quota.Resource(atom, response.readLong(), response.readLong()));
            }
        }
        response.readByte();
        quota.resources = new Quota.Resource[vector.size()];
        vector.copyInto(quota.resources);
        return quota;
    }

    public void setACL(String str, char c, ACL acl) throws ProtocolException {
        if (!hasCapability("ACL")) {
            throw new BadCommandException("ACL not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        argument.writeString(acl.getName());
        String string = acl.getRights().toString();
        if (c == '+' || c == '-') {
            string = String.valueOf(c) + string;
        }
        argument.writeString(string);
        Response[] responseArrCommand = command("SETACL", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
    }

    public void deleteACL(String str, String str2) throws ProtocolException {
        if (!hasCapability("ACL")) {
            throw new BadCommandException("ACL not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        argument.writeString(str2);
        Response[] responseArrCommand = command("DELETEACL", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
    }

    public ACL[] getACL(String str) throws ProtocolException {
        String atomString;
        if (!hasCapability("ACL")) {
            throw new BadCommandException("ACL not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Response[] responseArrCommand = command("GETACL", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        Vector vector = new Vector();
        if (response.isOK()) {
            int length = responseArrCommand.length;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("ACL")) {
                        iMAPResponse.readAtomString();
                        while (true) {
                            String atomString2 = iMAPResponse.readAtomString();
                            if (atomString2 == null || (atomString = iMAPResponse.readAtomString()) == null) {
                                break;
                            }
                            vector.addElement(new ACL(atomString2, new Rights(atomString)));
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        ACL[] aclArr = new ACL[vector.size()];
        vector.copyInto(aclArr);
        return aclArr;
    }

    public Rights[] listRights(String str, String str2) throws ProtocolException {
        if (!hasCapability("ACL")) {
            throw new BadCommandException("ACL not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        argument.writeString(str2);
        Response[] responseArrCommand = command("LISTRIGHTS", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        Vector vector = new Vector();
        if (response.isOK()) {
            int length = responseArrCommand.length;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("LISTRIGHTS")) {
                        iMAPResponse.readAtomString();
                        iMAPResponse.readAtomString();
                        while (true) {
                            String atomString = iMAPResponse.readAtomString();
                            if (atomString == null) {
                                break;
                            }
                            vector.addElement(new Rights(atomString));
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        Rights[] rightsArr = new Rights[vector.size()];
        vector.copyInto(rightsArr);
        return rightsArr;
    }

    public Rights myRights(String str) throws ProtocolException {
        if (!hasCapability("ACL")) {
            throw new BadCommandException("ACL not supported");
        }
        String strEncode = BASE64MailboxEncoder.encode(str);
        Argument argument = new Argument();
        argument.writeString(strEncode);
        Response[] responseArrCommand = command("MYRIGHTS", argument);
        Response response = responseArrCommand[responseArrCommand.length - 1];
        Rights rights = null;
        if (response.isOK()) {
            int length = responseArrCommand.length;
            Rights rights2 = null;
            for (int i = 0; i < length; i++) {
                if (responseArrCommand[i] instanceof IMAPResponse) {
                    IMAPResponse iMAPResponse = (IMAPResponse) responseArrCommand[i];
                    if (iMAPResponse.keyEquals("MYRIGHTS")) {
                        iMAPResponse.readAtomString();
                        String atomString = iMAPResponse.readAtomString();
                        if (rights2 == null) {
                            rights2 = new Rights(atomString);
                        }
                        responseArrCommand[i] = null;
                    }
                }
            }
            rights = rights2;
        }
        notifyResponseHandlers(responseArrCommand);
        handleResult(response);
        return rights;
    }

    public synchronized void idleStart() throws ProtocolException {
        Response responseByeResponse;
        if (!hasCapability("IDLE")) {
            throw new BadCommandException("IDLE not supported");
        }
        try {
            this.idleTag = writeCommand("IDLE", null);
            responseByeResponse = readResponse();
        } catch (LiteralException e) {
            responseByeResponse = e.getResponse();
        } catch (Exception e2) {
            responseByeResponse = Response.byeResponse(e2);
        }
        if (!responseByeResponse.isContinuation()) {
            handleResult(responseByeResponse);
        }
    }

    public synchronized Response readIdleResponse() {
        Response responseByeResponse;
        if (this.idleTag == null) {
            return null;
        }
        try {
            responseByeResponse = readResponse();
        } catch (ProtocolException e) {
            responseByeResponse = Response.byeResponse(e);
        } catch (IOException e2) {
            responseByeResponse = Response.byeResponse(e2);
        }
        return responseByeResponse;
    }

    public boolean processIdleResponse(Response response) throws ProtocolException {
        notifyResponseHandlers(new Response[]{response});
        boolean zIsBYE = response.isBYE();
        if (response.isTagged() && response.getTag().equals(this.idleTag)) {
            zIsBYE = true;
        }
        if (zIsBYE) {
            this.idleTag = null;
        }
        handleResult(response);
        return !zIsBYE;
    }

    public void idleAbort() throws ProtocolException {
        OutputStream outputStream = getOutputStream();
        try {
            outputStream.write(DONE);
            outputStream.flush();
        } catch (IOException unused) {
        }
    }
}
