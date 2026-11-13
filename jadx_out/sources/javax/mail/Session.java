package javax.mail;

import android.support.v4.app.NotificationCompat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.sun.mail.util.LineInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public final class Session {
    private static Session defaultSession;
    private final Authenticator authenticator;
    private boolean debug;
    private PrintStream out;
    private final Properties props;
    private final Hashtable authTable = new Hashtable();
    private final Vector providers = new Vector();
    private final Hashtable providersByProtocol = new Hashtable();
    private final Hashtable providersByClassName = new Hashtable();
    private final Properties addressMap = new Properties();

    private Session(Properties properties, Authenticator authenticator) throws Throwable {
        Class<?> cls;
        this.debug = false;
        this.props = properties;
        this.authenticator = authenticator;
        if (Boolean.valueOf(properties.getProperty("mail.debug")).booleanValue()) {
            this.debug = true;
        }
        if (this.debug) {
            m4691pr("DEBUG: JavaMail version 1.4.1");
        }
        if (authenticator != null) {
            cls = authenticator.getClass();
        } else {
            cls = getClass();
        }
        loadProviders(cls);
        loadAddressMap(cls);
    }

    public static Session getInstance(Properties properties, Authenticator authenticator) {
        return new Session(properties, authenticator);
    }

    public static Session getInstance(Properties properties) {
        return new Session(properties, null);
    }

    public static synchronized Session getDefaultInstance(Properties properties, Authenticator authenticator) {
        if (defaultSession == null) {
            defaultSession = new Session(properties, authenticator);
        } else if (defaultSession.authenticator != authenticator && (defaultSession.authenticator == null || authenticator == null || defaultSession.authenticator.getClass().getClassLoader() != authenticator.getClass().getClassLoader())) {
            throw new SecurityException("Access to default session denied");
        }
        return defaultSession;
    }

    public static Session getDefaultInstance(Properties properties) {
        return getDefaultInstance(properties, null);
    }

    public final synchronized void setDebug(boolean z) {
        this.debug = z;
        if (z) {
            m4691pr("DEBUG: setDebug: JavaMail version 1.4.1");
        }
    }

    public final synchronized boolean getDebug() {
        return this.debug;
    }

    public final synchronized void setDebugOut(PrintStream printStream) {
        this.out = printStream;
    }

    public final synchronized PrintStream getDebugOut() {
        if (this.out == null) {
            return System.out;
        }
        return this.out;
    }

    public final synchronized Provider[] getProviders() {
        Provider[] providerArr;
        providerArr = new Provider[this.providers.size()];
        this.providers.copyInto(providerArr);
        return providerArr;
    }

    public final synchronized Provider getProvider(String str) throws NoSuchProviderException {
        if (str != null) {
            if (str.length() > 0) {
                Provider provider = null;
                String property = this.props.getProperty("mail." + str + ".class");
                if (property != null) {
                    if (this.debug) {
                        m4691pr("DEBUG: mail." + str + ".class property exists and points to " + property);
                    }
                    provider = (Provider) this.providersByClassName.get(property);
                }
                if (provider != null) {
                    return provider;
                }
                Provider provider2 = (Provider) this.providersByProtocol.get(str);
                if (provider2 == null) {
                    throw new NoSuchProviderException("No provider for " + str);
                }
                if (this.debug) {
                    m4691pr("DEBUG: getProvider() returning " + provider2.toString());
                }
                return provider2;
            }
        }
        throw new NoSuchProviderException("Invalid protocol: null");
    }

    public final synchronized void setProvider(Provider provider) throws NoSuchProviderException {
        try {
            if (provider == null) {
                throw new NoSuchProviderException("Can't set null provider");
            }
            this.providersByProtocol.put(provider.getProtocol(), provider);
            this.props.put("mail." + provider.getProtocol() + ".class", provider.getClassName());
        } catch (Throwable th) {
            throw th;
        }
    }

    public final Store getStore() throws NoSuchProviderException {
        return getStore(getProperty("mail.store.protocol"));
    }

    public final Store getStore(String str) throws NoSuchProviderException {
        return getStore(new URLName(str, null, -1, null, null, null));
    }

    public final Store getStore(URLName uRLName) throws NoSuchProviderException {
        return getStore(getProvider(uRLName.getProtocol()), uRLName);
    }

    public final Store getStore(Provider provider) throws NoSuchProviderException {
        return getStore(provider, null);
    }

    private Store getStore(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (provider == null || provider.getType() != Provider.Type.STORE) {
            throw new NoSuchProviderException("invalid provider");
        }
        try {
            return (Store) getService(provider, uRLName);
        } catch (ClassCastException unused) {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    public final Folder getFolder(URLName uRLName) throws MessagingException {
        Store store = getStore(uRLName);
        store.connect();
        return store.getFolder(uRLName);
    }

    public final Transport getTransport() throws NoSuchProviderException {
        return getTransport(getProperty("mail.transport.protocol"));
    }

    public final Transport getTransport(String str) throws NoSuchProviderException {
        return getTransport(new URLName(str, null, -1, null, null, null));
    }

    public final Transport getTransport(URLName uRLName) throws NoSuchProviderException {
        return getTransport(getProvider(uRLName.getProtocol()), uRLName);
    }

    public final Transport getTransport(Provider provider) throws NoSuchProviderException {
        return getTransport(provider, null);
    }

    public final Transport getTransport(Address address) throws NoSuchProviderException {
        String str = (String) this.addressMap.get(address.getType());
        if (str == null) {
            throw new NoSuchProviderException("No provider for Address type: " + address.getType());
        }
        return getTransport(str);
    }

    private Transport getTransport(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (provider == null || provider.getType() != Provider.Type.TRANSPORT) {
            throw new NoSuchProviderException("invalid provider");
        }
        try {
            return (Transport) getService(provider, uRLName);
        } catch (ClassCastException unused) {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    private Object getService(Provider provider, URLName uRLName) throws NoSuchProviderException, ClassNotFoundException {
        ClassLoader classLoader;
        if (provider == null) {
            throw new NoSuchProviderException("null");
        }
        if (uRLName == null) {
            uRLName = new URLName(provider.getProtocol(), null, -1, null, null, null);
        }
        if (this.authenticator != null) {
            classLoader = this.authenticator.getClass().getClassLoader();
        } else {
            classLoader = getClass().getClassLoader();
        }
        Class<?> cls = null;
        try {
            try {
                ClassLoader contextClassLoader = getContextClassLoader();
                if (contextClassLoader != null) {
                    try {
                        cls = contextClassLoader.loadClass(provider.getClassName());
                    } catch (ClassNotFoundException unused) {
                    }
                }
                if (cls == null) {
                    cls = classLoader.loadClass(provider.getClassName());
                }
            } catch (Exception unused2) {
                cls = Class.forName(provider.getClassName());
            }
            try {
                return cls.getConstructor(Session.class, URLName.class).newInstance(this, uRLName);
            } catch (Exception e) {
                if (this.debug) {
                    ThrowableExtension.printStackTrace(e, getDebugOut());
                }
                throw new NoSuchProviderException(provider.getProtocol());
            }
        } catch (Exception e2) {
            if (this.debug) {
                ThrowableExtension.printStackTrace(e2, getDebugOut());
            }
            throw new NoSuchProviderException(provider.getProtocol());
        }
    }

    public final void setPasswordAuthentication(URLName uRLName, PasswordAuthentication passwordAuthentication) {
        if (passwordAuthentication == null) {
            this.authTable.remove(uRLName);
        } else {
            this.authTable.put(uRLName, passwordAuthentication);
        }
    }

    public final PasswordAuthentication getPasswordAuthentication(URLName uRLName) {
        return (PasswordAuthentication) this.authTable.get(uRLName);
    }

    public final PasswordAuthentication requestPasswordAuthentication(InetAddress inetAddress, int i, String str, String str2, String str3) {
        if (this.authenticator != null) {
            return this.authenticator.requestPasswordAuthentication(inetAddress, i, str, str2, str3);
        }
        return null;
    }

    public final Properties getProperties() {
        return this.props;
    }

    public final String getProperty(String str) {
        return this.props.getProperty(str);
    }

    private void loadProviders(Class cls) throws Throwable {
        StreamLoader streamLoader = new StreamLoader() { // from class: javax.mail.Session.1
            @Override // javax.mail.StreamLoader
            public void load(InputStream inputStream) throws IOException {
                Session.this.loadProvidersFromStream(inputStream);
            }
        };
        try {
            loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "javamail.providers", streamLoader);
        } catch (SecurityException e) {
            if (this.debug) {
                m4691pr("DEBUG: can't get java.home: " + e);
            }
        }
        loadAllResources("META-INF/javamail.providers", cls, streamLoader);
        loadResource("/META-INF/javamail.default.providers", cls, streamLoader);
        if (this.providers.size() == 0) {
            if (this.debug) {
                m4691pr("DEBUG: failed to load any providers, using defaults");
            }
            addProvider(new Provider(Provider.Type.STORE, "imap", "com.sun.mail.imap.IMAPStore", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "imaps", "com.sun.mail.imap.IMAPSSLStore", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "pop3", "com.sun.mail.pop3.POP3Store", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "pop3s", "com.sun.mail.pop3.POP3SSLStore", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtp", "com.sun.mail.smtp.SMTPTransport", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtps", "com.sun.mail.smtp.SMTPSSLTransport", "Sun Microsystems, Inc.", Version.version));
        }
        if (this.debug) {
            m4691pr("DEBUG: Tables of loaded providers");
            m4691pr("DEBUG: Providers Listed By Class Name: " + this.providersByClassName.toString());
            m4691pr("DEBUG: Providers Listed By Protocol: " + this.providersByProtocol.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadProvidersFromStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        LineInputStream lineInputStream = new LineInputStream(inputStream);
        while (true) {
            String line = lineInputStream.readLine();
            if (line == null) {
                return;
            }
            if (!line.startsWith("#")) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                Provider.Type type = null;
                String strSubstring = null;
                String strSubstring2 = null;
                String strSubstring3 = null;
                String strSubstring4 = null;
                while (stringTokenizer.hasMoreTokens()) {
                    String strTrim = stringTokenizer.nextToken().trim();
                    int iIndexOf = strTrim.indexOf("=");
                    if (strTrim.startsWith("protocol=")) {
                        strSubstring = strTrim.substring(iIndexOf + 1);
                    } else if (strTrim.startsWith("type=")) {
                        String strSubstring5 = strTrim.substring(iIndexOf + 1);
                        if (strSubstring5.equalsIgnoreCase("store")) {
                            type = Provider.Type.STORE;
                        } else if (strSubstring5.equalsIgnoreCase(NotificationCompat.CATEGORY_TRANSPORT)) {
                            type = Provider.Type.TRANSPORT;
                        }
                    } else if (strTrim.startsWith("class=")) {
                        strSubstring2 = strTrim.substring(iIndexOf + 1);
                    } else if (strTrim.startsWith("vendor=")) {
                        strSubstring3 = strTrim.substring(iIndexOf + 1);
                    } else if (strTrim.startsWith("version=")) {
                        strSubstring4 = strTrim.substring(iIndexOf + 1);
                    }
                }
                if (type == null || strSubstring == null || strSubstring2 == null || strSubstring.length() <= 0 || strSubstring2.length() <= 0) {
                    if (this.debug) {
                        m4691pr("DEBUG: Bad provider entry: " + line);
                    }
                } else {
                    addProvider(new Provider(type, strSubstring, strSubstring2, strSubstring3, strSubstring4));
                }
            }
        }
    }

    public final synchronized void addProvider(Provider provider) {
        this.providers.addElement(provider);
        this.providersByClassName.put(provider.getClassName(), provider);
        if (!this.providersByProtocol.containsKey(provider.getProtocol())) {
            this.providersByProtocol.put(provider.getProtocol(), provider);
        }
    }

    private void loadAddressMap(Class cls) throws Throwable {
        StreamLoader streamLoader = new StreamLoader() { // from class: javax.mail.Session.2
            @Override // javax.mail.StreamLoader
            public void load(InputStream inputStream) throws IOException {
                Session.this.addressMap.load(inputStream);
            }
        };
        loadResource("/META-INF/javamail.default.address.map", cls, streamLoader);
        loadAllResources("META-INF/javamail.address.map", cls, streamLoader);
        try {
            loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "javamail.address.map", streamLoader);
        } catch (SecurityException e) {
            if (this.debug) {
                m4691pr("DEBUG: can't get java.home: " + e);
            }
        }
        if (this.addressMap.isEmpty()) {
            if (this.debug) {
                m4691pr("DEBUG: failed to load address map, using defaults");
            }
            this.addressMap.put("rfc822", "smtp");
        }
    }

    public final synchronized void setProtocolForAddress(String str, String str2) {
        try {
            if (str2 == null) {
                this.addressMap.remove(str);
            } else {
                this.addressMap.put(str, str2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void loadFile(String str, StreamLoader streamLoader) throws Throwable {
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                try {
                    streamLoader.load(bufferedInputStream2);
                    if (this.debug) {
                        m4691pr("DEBUG: successfully loaded file: " + str);
                    }
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (IOException e) {
                    e = e;
                    bufferedInputStream = bufferedInputStream2;
                    if (this.debug) {
                        m4691pr("DEBUG: not loading file: " + str);
                        m4691pr("DEBUG: " + e);
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                } catch (SecurityException e2) {
                    e = e2;
                    bufferedInputStream = bufferedInputStream2;
                    if (this.debug) {
                        m4691pr("DEBUG: not loading file: " + str);
                        m4691pr("DEBUG: " + e);
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (SecurityException e4) {
            e = e4;
        }
    }

    private void loadResource(String str, Class cls, StreamLoader streamLoader) throws Throwable {
        InputStream resourceAsStream;
        InputStream inputStream = null;
        try {
            try {
                resourceAsStream = getResourceAsStream(cls, str);
                try {
                    if (resourceAsStream != null) {
                        streamLoader.load(resourceAsStream);
                        if (this.debug) {
                            m4691pr("DEBUG: successfully loaded resource: " + str);
                        }
                    } else if (this.debug) {
                        m4691pr("DEBUG: not loading resource: " + str);
                    }
                    if (resourceAsStream != null) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException unused) {
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    inputStream = resourceAsStream;
                    if (this.debug) {
                        m4691pr("DEBUG: " + e);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                } catch (SecurityException e2) {
                    e = e2;
                    inputStream = resourceAsStream;
                    if (this.debug) {
                        m4691pr("DEBUG: " + e);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (resourceAsStream != null) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                resourceAsStream = null;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (SecurityException e4) {
            e = e4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00a5 A[Catch: IOException -> 0x00c2, Exception -> 0x00cc, PHI: r2 r4
  0x00a5: PHI (r2v7 int) = (r2v6 int), (r2v8 int) binds: [B:49:0x00a3, B:56:0x00bf] A[DONT_GENERATE, DONT_INLINE]
  0x00a5: PHI (r4v3 java.io.InputStream) = (r4v2 java.io.InputStream), (r4v4 java.io.InputStream) binds: [B:49:0x00a3, B:56:0x00bf] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x00cc, blocks: (B:11:0x0019, B:14:0x001f, B:16:0x0026, B:41:0x0087, B:60:0x00c8, B:61:0x00cb, B:50:0x00a5), top: B:77:0x0019 }] */
    private void loadAllResources(String str, Class cls, StreamLoader streamLoader) throws Throwable {
        URL[] systemResources;
        int i = 0;
        try {
            ClassLoader contextClassLoader = getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = cls.getClassLoader();
            }
            if (contextClassLoader != null) {
                systemResources = getResources(contextClassLoader, str);
            } else {
                systemResources = getSystemResources(str);
            }
            if (systemResources != null) {
                int i2 = 0;
                while (i < systemResources.length) {
                    try {
                        URL url = systemResources[i];
                        InputStream inputStream = null;
                        if (this.debug) {
                            m4691pr("DEBUG: URL " + url);
                        }
                        try {
                            try {
                                InputStream inputStreamOpenStream = openStream(url);
                                if (inputStreamOpenStream != null) {
                                    try {
                                        streamLoader.load(inputStreamOpenStream);
                                        try {
                                            if (this.debug) {
                                                m4691pr("DEBUG: successfully loaded resource: " + url);
                                            }
                                            i2 = 1;
                                        } catch (IOException e) {
                                            e = e;
                                            inputStream = inputStreamOpenStream;
                                            i2 = 1;
                                            if (this.debug) {
                                                m4691pr("DEBUG: " + e);
                                            }
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                        } catch (SecurityException e2) {
                                            e = e2;
                                            inputStream = inputStreamOpenStream;
                                            i2 = 1;
                                            if (this.debug) {
                                                m4691pr("DEBUG: " + e);
                                            }
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            inputStream = inputStreamOpenStream;
                                            i2 = 1;
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException unused) {
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        inputStream = inputStreamOpenStream;
                                    } catch (SecurityException e4) {
                                        e = e4;
                                        inputStream = inputStreamOpenStream;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream = inputStreamOpenStream;
                                    }
                                } else if (this.debug) {
                                    m4691pr("DEBUG: not loading resource: " + url);
                                }
                                if (inputStreamOpenStream != null) {
                                    try {
                                        inputStreamOpenStream.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (IOException e5) {
                            e = e5;
                        } catch (SecurityException e6) {
                            e = e6;
                        }
                        i++;
                    } catch (Exception e7) {
                        e = e7;
                        i = i2;
                        if (this.debug) {
                            m4691pr("DEBUG: " + e);
                        }
                    }
                }
                i = i2;
            }
        } catch (Exception e8) {
            e = e8;
        }
        if (i == 0) {
            if (this.debug) {
                m4691pr("DEBUG: !anyLoaded");
            }
            loadResource(InternalZipConstants.ZIP_FILE_SEPARATOR + str, cls, streamLoader);
        }
    }

    /* JADX INFO: renamed from: pr */
    private void m4691pr(String str) {
        getDebugOut().println(str);
    }

    private static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }

    private static InputStream getResourceAsStream(final Class cls, final String str) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: javax.mail.Session.4
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws IOException {
                    return cls.getResourceAsStream(str);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    private static URL[] getResources(final ClassLoader classLoader, final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (resources != null && resources.hasMoreElements()) {
                        URL urlNextElement = resources.nextElement();
                        if (urlNextElement != null) {
                            vector.addElement(urlNextElement);
                        }
                    }
                    if (vector.size() <= 0) {
                        return null;
                    }
                    URL[] urlArr = new URL[vector.size()];
                    try {
                        vector.copyInto(urlArr);
                    } catch (IOException | SecurityException unused) {
                    }
                    return urlArr;
                } catch (IOException | SecurityException unused2) {
                    return null;
                }
            }
        });
    }

    private static URL[] getSystemResources(final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.6
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> systemResources = ClassLoader.getSystemResources(str);
                    while (systemResources != null && systemResources.hasMoreElements()) {
                        URL urlNextElement = systemResources.nextElement();
                        if (urlNextElement != null) {
                            vector.addElement(urlNextElement);
                        }
                    }
                    if (vector.size() <= 0) {
                        return null;
                    }
                    URL[] urlArr = new URL[vector.size()];
                    try {
                        vector.copyInto(urlArr);
                    } catch (IOException | SecurityException unused) {
                    }
                    return urlArr;
                } catch (IOException | SecurityException unused2) {
                    return null;
                }
            }
        });
    }

    private static InputStream openStream(final URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: javax.mail.Session.7
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws IOException {
                    return url.openStream();
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }
}
