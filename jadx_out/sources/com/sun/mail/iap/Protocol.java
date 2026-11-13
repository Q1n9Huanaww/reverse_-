package com.sun.mail.iap;

import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import com.umeng.commonsdk.proguard.C1770bg;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Protocol {
    private static final byte[] CRLF = {C1770bg.f4123k, 10};
    private boolean connected;
    protected boolean debug;
    private volatile Vector handlers;
    protected String host;
    private volatile ResponseInputStream input;
    protected PrintStream out;
    private volatile DataOutputStream output;
    protected String prefix;
    protected Properties props;
    protected boolean quote;
    private Socket socket;
    private int tagCounter;
    private volatile long timestamp;
    private TraceInputStream traceInput;
    private TraceOutputStream traceOutput;

    public ByteArray getResponseBuffer() {
        return null;
    }

    public Protocol(String str, int i, boolean z, PrintStream printStream, Properties properties, String str2, boolean z2) throws ProtocolException, IOException {
        boolean z3 = false;
        this.connected = false;
        this.tagCounter = 0;
        this.handlers = null;
        try {
            this.host = str;
            this.debug = z;
            this.out = printStream;
            this.props = properties;
            this.prefix = str2;
            this.socket = SocketFetcher.getSocket(str, i, properties, str2, z2);
            String property = properties.getProperty("mail.debug.quote");
            if (property != null && property.equalsIgnoreCase("true")) {
                z3 = true;
            }
            this.quote = z3;
            initStreams(printStream);
            processGreeting(readResponse());
            this.timestamp = System.currentTimeMillis();
            this.connected = true;
        } finally {
            if (!this.connected) {
                disconnect();
            }
        }
    }

    private void initStreams(PrintStream printStream) throws IOException {
        this.traceInput = new TraceInputStream(this.socket.getInputStream(), printStream);
        this.traceInput.setTrace(this.debug);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(this.socket.getOutputStream(), printStream);
        this.traceOutput.setTrace(this.debug);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
    }

    public Protocol(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        this.connected = false;
        this.tagCounter = 0;
        this.handlers = null;
        this.host = "localhost";
        this.debug = z;
        this.quote = false;
        this.out = System.out;
        this.traceInput = new TraceInputStream(inputStream, System.out);
        this.traceInput.setTrace(z);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(outputStream, System.out);
        this.traceOutput.setTrace(z);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public synchronized void addResponseHandler(ResponseHandler responseHandler) {
        if (this.handlers == null) {
            this.handlers = new Vector();
        }
        this.handlers.addElement(responseHandler);
    }

    public synchronized void removeResponseHandler(ResponseHandler responseHandler) {
        if (this.handlers != null) {
            this.handlers.removeElement(responseHandler);
        }
    }

    public void notifyResponseHandlers(Response[] responseArr) {
        if (this.handlers == null) {
            return;
        }
        for (Response response : responseArr) {
            if (response != null) {
                int size = this.handlers.size();
                if (size == 0) {
                    return;
                }
                Object[] objArr = new Object[size];
                this.handlers.copyInto(objArr);
                for (int i = 0; i < size; i++) {
                    ((ResponseHandler) objArr[i]).handleResponse(response);
                }
            }
        }
    }

    public void processGreeting(Response response) throws ProtocolException {
        if (response.isBYE()) {
            throw new ConnectionException(this, response);
        }
    }

    protected ResponseInputStream getInputStream() {
        return this.input;
    }

    protected OutputStream getOutputStream() {
        return this.output;
    }

    public synchronized boolean supportsNonSyncLiterals() {
        return false;
    }

    public Response readResponse() throws ProtocolException, IOException {
        return new Response(this);
    }

    public String writeCommand(String str, Argument argument) throws ProtocolException, IOException {
        StringBuilder sb = new StringBuilder("A");
        int i = this.tagCounter;
        this.tagCounter = i + 1;
        sb.append(Integer.toString(i, 10));
        String string = sb.toString();
        this.output.writeBytes(String.valueOf(string) + " " + str);
        if (argument != null) {
            this.output.write(32);
            argument.write(this);
        }
        this.output.write(CRLF);
        this.output.flush();
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0047  */
    /* JADX WARN: Code duplicated, block: B:34:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x004e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x0021 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0020 -> B:12:0x0021). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:38:0x004e
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public synchronized com.sun.mail.iap.Response[] command(java.lang.String r5, com.sun.mail.iap.Argument r6) throws com.sun.mail.iap.ProtocolException, java.io.IOException {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Vector r0 = new java.util.Vector     // Catch: java.lang.Throwable -> L59
            r0.<init>()     // Catch: java.lang.Throwable -> L59
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r5 = r4.writeCommand(r5, r6)     // Catch: java.lang.Exception -> Lf com.sun.mail.iap.LiteralException -> L18 java.lang.Throwable -> L59
            r2 = r5
            goto L21
        Lf:
            r5 = move-exception
            com.sun.mail.iap.Response r5 = com.sun.mail.iap.Response.byeResponse(r5)     // Catch: java.lang.Throwable -> L59
            r0.addElement(r5)     // Catch: java.lang.Throwable -> L59
            goto L20
        L18:
            r5 = move-exception
            com.sun.mail.iap.Response r5 = r5.getResponse()     // Catch: java.lang.Throwable -> L59
            r0.addElement(r5)     // Catch: java.lang.Throwable -> L59
        L20:
            r1 = 1
        L21:
            if (r1 == 0) goto L34
            int r5 = r0.size()     // Catch: java.lang.Throwable -> L59
            com.sun.mail.iap.Response[] r5 = new com.sun.mail.iap.Response[r5]     // Catch: java.lang.Throwable -> L59
            r0.copyInto(r5)     // Catch: java.lang.Throwable -> L59
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L59
            r4.timestamp = r0     // Catch: java.lang.Throwable -> L59
            monitor-exit(r4)
            return r5
        L34:
            com.sun.mail.iap.Response r5 = r4.readResponse()     // Catch: com.sun.mail.iap.ProtocolException -> L21 java.io.IOException -> L39 java.lang.Throwable -> L59
            goto L3e
        L39:
            r5 = move-exception
            com.sun.mail.iap.Response r5 = com.sun.mail.iap.Response.byeResponse(r5)     // Catch: java.lang.Throwable -> L59
        L3e:
            r0.addElement(r5)     // Catch: java.lang.Throwable -> L59
            boolean r6 = r5.isBYE()     // Catch: java.lang.Throwable -> L59
            if (r6 == 0) goto L48
            r1 = 1
        L48:
            boolean r6 = r5.isTagged()     // Catch: java.lang.Throwable -> L59
            if (r6 == 0) goto L21
            java.lang.String r5 = r5.getTag()     // Catch: java.lang.Throwable -> L59
            boolean r5 = r5.equals(r2)     // Catch: java.lang.Throwable -> L59
            if (r5 == 0) goto L21
            goto L20
        L59:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.iap.Protocol.command(java.lang.String, com.sun.mail.iap.Argument):com.sun.mail.iap.Response[]");
    }

    public void handleResult(Response response) throws ProtocolException {
        if (response.isOK()) {
            return;
        }
        if (response.isNO()) {
            throw new CommandFailedException(response);
        }
        if (response.isBAD()) {
            throw new BadCommandException(response);
        }
        if (response.isBYE()) {
            disconnect();
            throw new ConnectionException(this, response);
        }
    }

    public void simpleCommand(String str, Argument argument) throws ProtocolException, IOException {
        Response[] responseArrCommand = command(str, argument);
        notifyResponseHandlers(responseArrCommand);
        handleResult(responseArrCommand[responseArrCommand.length - 1]);
    }

    public synchronized void startTLS(String str) throws ProtocolException, IOException {
        simpleCommand(str, null);
        this.socket = SocketFetcher.startTLS(this.socket, this.props, this.prefix);
        initStreams(this.out);
    }

    public synchronized void disconnect() {
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException unused) {
            }
            this.socket = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
}
