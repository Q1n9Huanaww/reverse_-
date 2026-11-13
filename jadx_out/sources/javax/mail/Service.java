package javax.mail;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;

/* JADX INFO: loaded from: classes.dex */
public abstract class Service {
    protected boolean debug;

    /* JADX INFO: renamed from: q */
    private EventQueue f4741q;
    protected Session session;
    protected URLName url;
    private boolean connected = false;
    private Vector connectionListeners = null;
    private Object qLock = new Object();

    public boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        return false;
    }

    protected Service(Session session, URLName uRLName) {
        this.url = null;
        this.debug = false;
        this.session = session;
        this.url = uRLName;
        this.debug = session.getDebug();
    }

    public void connect() throws MessagingException, UnknownHostException {
        connect(null, null, null);
    }

    public void connect(String str, String str2, String str3) throws MessagingException, UnknownHostException {
        connect(str, -1, str2, str3);
    }

    public void connect(String str, String str2) throws MessagingException, UnknownHostException {
        connect(null, str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x005b A[PHI: r2
  0x005b: PHI (r2v17 java.lang.String) = (r2v0 java.lang.String), (r2v0 java.lang.String), (r2v20 java.lang.String) binds: [B:22:0x0046, B:24:0x0052, B:20:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x0142 A[Catch: all -> 0x0181, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000b, B:7:0x0012, B:8:0x0013, B:10:0x0017, B:12:0x001f, B:16:0x002d, B:19:0x0037, B:21:0x003f, B:27:0x005d, B:31:0x0071, B:33:0x008c, B:35:0x00a7, B:37:0x00b1, B:39:0x00bb, B:48:0x00d7, B:50:0x00db, B:53:0x00ff, B:62:0x0123, B:67:0x012f, B:70:0x0136, B:72:0x0142, B:75:0x0152, B:76:0x0153, B:77:0x0158, B:78:0x0159, B:80:0x016b, B:81:0x0179, B:54:0x0108, B:56:0x0112, B:43:0x00c5, B:45:0x00c9, B:23:0x0048, B:25:0x0054), top: B:88:0x0005, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0150 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x0152 A[Catch: all -> 0x0181, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000b, B:7:0x0012, B:8:0x0013, B:10:0x0017, B:12:0x001f, B:16:0x002d, B:19:0x0037, B:21:0x003f, B:27:0x005d, B:31:0x0071, B:33:0x008c, B:35:0x00a7, B:37:0x00b1, B:39:0x00bb, B:48:0x00d7, B:50:0x00db, B:53:0x00ff, B:62:0x0123, B:67:0x012f, B:70:0x0136, B:72:0x0142, B:75:0x0152, B:76:0x0153, B:77:0x0158, B:78:0x0159, B:80:0x016b, B:81:0x0179, B:54:0x0108, B:56:0x0112, B:43:0x00c5, B:45:0x00c9, B:23:0x0048, B:25:0x0054), top: B:88:0x0005, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0153 A[Catch: all -> 0x0181, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000b, B:7:0x0012, B:8:0x0013, B:10:0x0017, B:12:0x001f, B:16:0x002d, B:19:0x0037, B:21:0x003f, B:27:0x005d, B:31:0x0071, B:33:0x008c, B:35:0x00a7, B:37:0x00b1, B:39:0x00bb, B:48:0x00d7, B:50:0x00db, B:53:0x00ff, B:62:0x0123, B:67:0x012f, B:70:0x0136, B:72:0x0142, B:75:0x0152, B:76:0x0153, B:77:0x0158, B:78:0x0159, B:80:0x016b, B:81:0x0179, B:54:0x0108, B:56:0x0112, B:43:0x00c5, B:45:0x00c9, B:23:0x0048, B:25:0x0054), top: B:88:0x0005, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0159 A[Catch: all -> 0x0181, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000b, B:7:0x0012, B:8:0x0013, B:10:0x0017, B:12:0x001f, B:16:0x002d, B:19:0x0037, B:21:0x003f, B:27:0x005d, B:31:0x0071, B:33:0x008c, B:35:0x00a7, B:37:0x00b1, B:39:0x00bb, B:48:0x00d7, B:50:0x00db, B:53:0x00ff, B:62:0x0123, B:67:0x012f, B:70:0x0136, B:72:0x0142, B:75:0x0152, B:76:0x0153, B:77:0x0158, B:78:0x0159, B:80:0x016b, B:81:0x0179, B:54:0x0108, B:56:0x0112, B:43:0x00c5, B:45:0x00c9, B:23:0x0048, B:25:0x0054), top: B:88:0x0005, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x016b A[Catch: all -> 0x0181, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000b, B:7:0x0012, B:8:0x0013, B:10:0x0017, B:12:0x001f, B:16:0x002d, B:19:0x0037, B:21:0x003f, B:27:0x005d, B:31:0x0071, B:33:0x008c, B:35:0x00a7, B:37:0x00b1, B:39:0x00bb, B:48:0x00d7, B:50:0x00db, B:53:0x00ff, B:62:0x0123, B:67:0x012f, B:70:0x0136, B:72:0x0142, B:75:0x0152, B:76:0x0153, B:77:0x0158, B:78:0x0159, B:80:0x016b, B:81:0x0179, B:54:0x0108, B:56:0x0112, B:43:0x00c5, B:45:0x00c9, B:23:0x0048, B:25:0x0054), top: B:88:0x0005, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x012f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    public synchronized void connect(String str, int i, String str2, String str3) throws MessagingException, UnknownHostException {
        String property;
        String password;
        int port;
        String protocol;
        String file;
        String str4;
        boolean z;
        String password2;
        boolean z2;
        ?? r3;
        AuthenticationFailedException authenticationFailedException;
        InetAddress byName;
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        boolean z3;
        String property2 = str2;
        synchronized (this) {
            if (isConnected()) {
                throw new IllegalStateException("already connected");
            }
            if (this.url != null) {
                protocol = this.url.getProtocol();
                property = str == null ? this.url.getHost() : str;
                port = i == -1 ? this.url.getPort() : i;
                if (property2 == null) {
                    property2 = this.url.getUsername();
                    if (str3 == null) {
                        password = this.url.getPassword();
                    } else {
                        password = str3;
                    }
                } else if (str3 == null && property2.equals(this.url.getUsername())) {
                    password = this.url.getPassword();
                } else {
                    password = str3;
                }
                file = this.url.getFile();
            } else {
                property = str;
                password = str3;
                port = i;
                protocol = null;
                file = null;
            }
            if (protocol != null) {
                if (property == null) {
                    property = this.session.getProperty("mail." + protocol + ".host");
                }
                if (property2 == null) {
                    property2 = this.session.getProperty("mail." + protocol + ".user");
                }
            }
            if (property == null) {
                property = this.session.getProperty("mail.host");
            }
            if (property2 == null) {
                property2 = this.session.getProperty("mail.user");
            }
            if (property2 == null) {
                try {
                    property2 = System.getProperty("user.name");
                } catch (SecurityException e) {
                    if (this.debug) {
                        ThrowableExtension.printStackTrace(e, this.session.getDebugOut());
                    }
                }
            }
            boolean zProtocolConnect = false;
            try {
                if (password != null || this.url == null) {
                    str4 = password;
                    z = true;
                } else {
                    str4 = password;
                    r3 = 1;
                    z3 = true;
                    z3 = true;
                    z = true;
                    setURLName(new URLName(protocol, property, port, file, property2, null));
                    PasswordAuthentication passwordAuthentication = this.session.getPasswordAuthentication(getURLName());
                    if (passwordAuthentication != null) {
                        if (property2 == null) {
                            property2 = passwordAuthentication.getUserName();
                            password2 = passwordAuthentication.getPassword();
                        } else if (property2.equals(passwordAuthentication.getUserName())) {
                            password2 = passwordAuthentication.getPassword();
                        }
                        z2 = false;
                        r3 = z3;
                    } else {
                        password2 = str4;
                        z2 = true;
                    }
                    zProtocolConnect = protocolConnect(property, port, property2, password2);
                    authenticationFailedException = null;
                    if (!zProtocolConnect) {
                        try {
                            byName = InetAddress.getByName(property);
                        } catch (UnknownHostException unused) {
                            byName = null;
                        }
                        passwordAuthenticationRequestPasswordAuthentication = this.session.requestPasswordAuthentication(byName, port, protocol, null, property2);
                        if (passwordAuthenticationRequestPasswordAuthentication != null) {
                            property2 = passwordAuthenticationRequestPasswordAuthentication.getUserName();
                            password2 = passwordAuthenticationRequestPasswordAuthentication.getPassword();
                            zProtocolConnect = protocolConnect(property, port, property2, password2);
                        }
                    }
                    if (!zProtocolConnect) {
                        if (authenticationFailedException != null) {
                            throw authenticationFailedException;
                        }
                        throw new AuthenticationFailedException();
                    }
                    setURLName(new URLName(protocol, property, port, file, property2, password2));
                    if (z2) {
                        this.session.setPasswordAuthentication(getURLName(), new PasswordAuthentication(property2, password2));
                    }
                    setConnected(r3);
                    notifyConnectionListeners(r3);
                }
                zProtocolConnect = protocolConnect(property, port, property2, password2);
                authenticationFailedException = null;
            } catch (AuthenticationFailedException e2) {
                authenticationFailedException = e2;
            }
            password2 = str4;
            z3 = z;
            z2 = false;
            r3 = z3;
            if (!zProtocolConnect) {
                byName = InetAddress.getByName(property);
                passwordAuthenticationRequestPasswordAuthentication = this.session.requestPasswordAuthentication(byName, port, protocol, null, property2);
                if (passwordAuthenticationRequestPasswordAuthentication != null) {
                    property2 = passwordAuthenticationRequestPasswordAuthentication.getUserName();
                    password2 = passwordAuthenticationRequestPasswordAuthentication.getPassword();
                    zProtocolConnect = protocolConnect(property, port, property2, password2);
                }
            }
            if (!zProtocolConnect) {
                if (authenticationFailedException != null) {
                    throw authenticationFailedException;
                }
                throw new AuthenticationFailedException();
            }
            setURLName(new URLName(protocol, property, port, file, property2, password2));
            if (z2) {
                this.session.setPasswordAuthentication(getURLName(), new PasswordAuthentication(property2, password2));
            }
            setConnected(r3);
            notifyConnectionListeners(r3);
        }
    }

    public synchronized boolean isConnected() {
        return this.connected;
    }

    protected synchronized void setConnected(boolean z) {
        this.connected = z;
    }

    public synchronized void close() throws MessagingException {
        setConnected(false);
        notifyConnectionListeners(3);
    }

    public synchronized URLName getURLName() {
        if (this.url != null && (this.url.getPassword() != null || this.url.getFile() != null)) {
            return new URLName(this.url.getProtocol(), this.url.getHost(), this.url.getPort(), null, this.url.getUsername(), null);
        }
        return this.url;
    }

    protected synchronized void setURLName(URLName uRLName) {
        this.url = uRLName;
    }

    public synchronized void addConnectionListener(ConnectionListener connectionListener) {
        if (this.connectionListeners == null) {
            this.connectionListeners = new Vector();
        }
        this.connectionListeners.addElement(connectionListener);
    }

    public synchronized void removeConnectionListener(ConnectionListener connectionListener) {
        if (this.connectionListeners != null) {
            this.connectionListeners.removeElement(connectionListener);
        }
    }

    protected synchronized void notifyConnectionListeners(int i) {
        if (this.connectionListeners != null) {
            queueEvent(new ConnectionEvent(this, i), this.connectionListeners);
        }
        if (i == 3) {
            terminateQueue();
        }
    }

    public String toString() {
        URLName uRLName = getURLName();
        if (uRLName != null) {
            return uRLName.toString();
        }
        return super.toString();
    }

    protected void queueEvent(MailEvent mailEvent, Vector vector) {
        synchronized (this.qLock) {
            if (this.f4741q == null) {
                this.f4741q = new EventQueue();
            }
        }
        this.f4741q.enqueue(mailEvent, (Vector) vector.clone());
    }

    static class TerminatorEvent extends MailEvent {
        private static final long serialVersionUID = 5542172141759168416L;

        TerminatorEvent() {
            super(new Object());
        }

        @Override // javax.mail.event.MailEvent
        public void dispatch(Object obj) {
            Thread.currentThread().interrupt();
        }
    }

    private void terminateQueue() {
        synchronized (this.qLock) {
            if (this.f4741q != null) {
                Vector vector = new Vector();
                vector.setSize(1);
                this.f4741q.enqueue(new TerminatorEvent(), vector);
                this.f4741q = null;
            }
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        terminateQueue();
    }
}
