package com.sun.mail.pop3;

import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.SocketFetcher;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
class Protocol {
    private static final String CRLF = "\r\n";
    private static final int POP3_PORT = 110;
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String apopChallenge;
    private boolean debug;
    private DataInputStream input;
    private PrintStream out;
    private PrintWriter output;
    private Socket socket;

    Protocol(String str, int i, boolean z, PrintStream printStream, Properties properties, String str2, boolean z2) throws IOException {
        boolean z3 = false;
        this.debug = false;
        this.apopChallenge = null;
        this.debug = z;
        this.out = printStream;
        String property = properties.getProperty(String.valueOf(str2) + ".apop.enable");
        if (property != null && property.equalsIgnoreCase("true")) {
            z3 = true;
        }
        i = i == -1 ? 110 : i;
        if (z) {
            try {
                printStream.println("DEBUG POP3: connecting to host \"" + str + "\", port " + i + ", isSSL " + z2);
            } catch (IOException e) {
                try {
                    this.socket.close();
                } catch (Throwable unused) {
                }
                throw e;
            }
        }
        this.socket = SocketFetcher.getSocket(str, i, properties, str2, z2);
        this.input = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        this.output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "iso-8859-1")));
        Response responseSimpleCommand = simpleCommand(null);
        if (!responseSimpleCommand.f3608ok) {
            try {
                this.socket.close();
            } catch (Throwable unused2) {
            }
            throw new IOException("Connect failed");
        }
        if (z3) {
            int iIndexOf = responseSimpleCommand.data.indexOf(60);
            int iIndexOf2 = responseSimpleCommand.data.indexOf(62, iIndexOf);
            if (iIndexOf != -1 && iIndexOf2 != -1) {
                this.apopChallenge = responseSimpleCommand.data.substring(iIndexOf, iIndexOf2 + 1);
            }
            if (z) {
                printStream.println("DEBUG POP3: APOP challenge: " + this.apopChallenge);
            }
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.socket != null) {
            quit();
        }
    }

    synchronized String login(String str, String str2) throws IOException {
        Response responseSimpleCommand;
        String digest = this.apopChallenge != null ? getDigest(str2) : null;
        if (this.apopChallenge != null && digest != null) {
            responseSimpleCommand = simpleCommand("APOP " + str + " " + digest);
        } else {
            Response responseSimpleCommand2 = simpleCommand("USER " + str);
            if (!responseSimpleCommand2.f3608ok) {
                if (responseSimpleCommand2.data != null) {
                    return responseSimpleCommand2.data;
                }
                return "USER command failed";
            }
            responseSimpleCommand = simpleCommand("PASS " + str2);
        }
        if (responseSimpleCommand.f3608ok) {
            return null;
        }
        if (responseSimpleCommand.data != null) {
            return responseSimpleCommand.data;
        }
        return "login failed";
    }

    private String getDigest(String str) {
        try {
            return toHex(MessageDigest.getInstance("MD5").digest((String.valueOf(this.apopChallenge) + str).getBytes("iso-8859-1")));
        } catch (UnsupportedEncodingException unused) {
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            return null;
        }
    }

    private static String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = i + 1;
            cArr[i] = digits[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = digits[i2 & 15];
        }
        return new String(cArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    synchronized boolean quit() throws IOException {
        boolean z;
        try {
            z = simpleCommand("QUIT").f3608ok;
            try {
                this.socket.close();
                this.socket = null;
                this.input = null;
                this.output = null;
            } catch (Throwable th) {
                this.socket = null;
                this.input = null;
                this.output = null;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                this.socket.close();
                throw th2;
            } finally {
                this.socket = null;
                this.input = null;
                this.output = null;
            }
        }
        return z;
    }

    synchronized Status stat() throws IOException {
        Status status;
        Response responseSimpleCommand = simpleCommand("STAT");
        status = new Status();
        if (responseSimpleCommand.f3608ok && responseSimpleCommand.data != null) {
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(responseSimpleCommand.data);
                status.total = Integer.parseInt(stringTokenizer.nextToken());
                status.size = Integer.parseInt(stringTokenizer.nextToken());
            } catch (Exception unused) {
            }
        }
        return status;
    }

    synchronized int list(int i) throws IOException {
        int i2;
        Response responseSimpleCommand = simpleCommand("LIST " + i);
        if (!responseSimpleCommand.f3608ok || responseSimpleCommand.data == null) {
            i2 = -1;
        } else {
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(responseSimpleCommand.data);
                stringTokenizer.nextToken();
                i2 = Integer.parseInt(stringTokenizer.nextToken());
            } catch (Exception unused) {
                i2 = -1;
            }
        }
        return i2;
    }

    synchronized InputStream list() throws IOException {
        return multilineCommand("LIST", 128).bytes;
    }

    synchronized InputStream retr(int i, int i2) throws IOException {
        return multilineCommand("RETR " + i, i2).bytes;
    }

    synchronized InputStream top(int i, int i2) throws IOException {
        return multilineCommand("TOP " + i + " " + i2, 0).bytes;
    }

    synchronized boolean dele(int i) throws IOException {
        return simpleCommand("DELE " + i).f3608ok;
    }

    synchronized String uidl(int i) throws IOException {
        Response responseSimpleCommand = simpleCommand("UIDL " + i);
        if (!responseSimpleCommand.f3608ok) {
            return null;
        }
        int iIndexOf = responseSimpleCommand.data.indexOf(32);
        if (iIndexOf <= 0) {
            return null;
        }
        return responseSimpleCommand.data.substring(iIndexOf + 1);
    }

    synchronized boolean uidl(String[] strArr) throws IOException {
        int i;
        Response responseMultilineCommand = multilineCommand("UIDL", strArr.length * 15);
        if (!responseMultilineCommand.f3608ok) {
            return false;
        }
        LineInputStream lineInputStream = new LineInputStream(responseMultilineCommand.bytes);
        while (true) {
            String line = lineInputStream.readLine();
            if (line == null) {
                return true;
            }
            int iIndexOf = line.indexOf(32);
            if (iIndexOf > 0 && iIndexOf < line.length() && (i = Integer.parseInt(line.substring(0, iIndexOf))) > 0 && i <= strArr.length) {
                strArr[i - 1] = line.substring(iIndexOf + 1);
            }
        }
    }

    synchronized boolean noop() throws IOException {
        return simpleCommand("NOOP").f3608ok;
    }

    synchronized boolean rset() throws IOException {
        return simpleCommand("RSET").f3608ok;
    }

    private Response simpleCommand(String str) throws IOException {
        if (this.socket == null) {
            throw new IOException("Folder is closed");
        }
        if (str != null) {
            if (this.debug) {
                this.out.println("C: " + str);
            }
            this.output.print(String.valueOf(str) + "\r\n");
            this.output.flush();
        }
        String line = this.input.readLine();
        if (line == null) {
            if (this.debug) {
                this.out.println("S: EOF");
            }
            throw new EOFException("EOF on socket");
        }
        if (this.debug) {
            this.out.println("S: " + line);
        }
        Response response = new Response();
        if (line.startsWith("+OK")) {
            response.f3608ok = true;
        } else if (line.startsWith("-ERR")) {
            response.f3608ok = false;
        } else {
            throw new IOException("Unexpected response: " + line);
        }
        int iIndexOf = line.indexOf(32);
        if (iIndexOf >= 0) {
            response.data = line.substring(iIndexOf + 1);
        }
        return response;
    }

    private Response multilineCommand(String str, int i) throws IOException {
        int i2;
        Response responseSimpleCommand = simpleCommand(str);
        if (!responseSimpleCommand.f3608ok) {
            return responseSimpleCommand;
        }
        SharedByteArrayOutputStream sharedByteArrayOutputStream = new SharedByteArrayOutputStream(i);
        int i3 = 10;
        while (true) {
            i2 = this.input.read();
            if (i2 < 0) {
                break;
            }
            if (i3 == 10 && i2 == 46) {
                if (this.debug) {
                    this.out.write(i2);
                }
                i3 = this.input.read();
                if (i3 == 13) {
                    if (this.debug) {
                        this.out.write(i3);
                    }
                    i2 = this.input.read();
                    if (!this.debug) {
                        break;
                    }
                    this.out.write(i2);
                    break;
                }
            } else {
                i3 = i2;
            }
            sharedByteArrayOutputStream.write(i3);
            if (this.debug) {
                this.out.write(i3);
            }
        }
        if (i2 < 0) {
            throw new EOFException("EOF on socket");
        }
        responseSimpleCommand.bytes = sharedByteArrayOutputStream.toStream();
        return responseSimpleCommand;
    }
}
