package javax.mail.internet;

import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.LineOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.MultipartDataSource;

/* JADX INFO: loaded from: classes.dex */
public class MimeMultipart extends Multipart {
    private static boolean bmparse = true;
    private static boolean ignoreMissingBoundaryParameter = true;
    private static boolean ignoreMissingEndBoundary = true;
    private boolean complete;

    /* JADX INFO: renamed from: ds */
    protected DataSource f4746ds;
    protected boolean parsed;
    private String preamble;

    static {
        try {
            String property = System.getProperty("mail.mime.multipart.ignoremissingendboundary");
            boolean z = true;
            ignoreMissingEndBoundary = property == null || !property.equalsIgnoreCase("false");
            String property2 = System.getProperty("mail.mime.multipart.ignoremissingboundaryparameter");
            ignoreMissingBoundaryParameter = property2 == null || !property2.equalsIgnoreCase("false");
            String property3 = System.getProperty("mail.mime.multipart.bmparse");
            if (property3 != null && property3.equalsIgnoreCase("false")) {
                z = false;
            }
            bmparse = z;
        } catch (SecurityException unused) {
        }
    }

    public MimeMultipart() {
        this("mixed");
    }

    public MimeMultipart(String str) {
        this.f4746ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        String uniqueBoundaryValue = UniqueValue.getUniqueBoundaryValue();
        ContentType contentType = new ContentType("multipart", str, null);
        contentType.setParameter("boundary", uniqueBoundaryValue);
        this.contentType = contentType.toString();
    }

    public MimeMultipart(DataSource dataSource) throws MessagingException {
        this.f4746ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        if (dataSource instanceof MessageAware) {
            setParent(((MessageAware) dataSource).getMessageContext().getPart());
        }
        if (dataSource instanceof MultipartDataSource) {
            setMultipartDataSource((MultipartDataSource) dataSource);
            return;
        }
        this.parsed = false;
        this.f4746ds = dataSource;
        this.contentType = dataSource.getContentType();
    }

    public synchronized void setSubType(String str) throws MessagingException {
        ContentType contentType = new ContentType(this.contentType);
        contentType.setSubType(str);
        this.contentType = contentType.toString();
    }

    @Override // javax.mail.Multipart
    public synchronized int getCount() throws MessagingException {
        parse();
        return super.getCount();
    }

    @Override // javax.mail.Multipart
    public synchronized BodyPart getBodyPart(int i) throws MessagingException {
        parse();
        return super.getBodyPart(i);
    }

    public synchronized BodyPart getBodyPart(String str) throws MessagingException {
        parse();
        int count = getCount();
        for (int i = 0; i < count; i++) {
            MimeBodyPart mimeBodyPart = (MimeBodyPart) getBodyPart(i);
            String contentID = mimeBodyPart.getContentID();
            if (contentID != null && contentID.equals(str)) {
                return mimeBodyPart;
            }
        }
        return null;
    }

    @Override // javax.mail.Multipart
    public boolean removeBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        return super.removeBodyPart(bodyPart);
    }

    @Override // javax.mail.Multipart
    public void removeBodyPart(int i) throws MessagingException {
        parse();
        super.removeBodyPart(i);
    }

    @Override // javax.mail.Multipart
    public synchronized void addBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart);
    }

    @Override // javax.mail.Multipart
    public synchronized void addBodyPart(BodyPart bodyPart, int i) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart, i);
    }

    public synchronized boolean isComplete() throws MessagingException {
        parse();
        return this.complete;
    }

    public synchronized String getPreamble() throws MessagingException {
        parse();
        return this.preamble;
    }

    public synchronized void setPreamble(String str) throws MessagingException {
        this.preamble = str;
    }

    protected void updateHeaders() throws MessagingException {
        for (int i = 0; i < this.parts.size(); i++) {
            ((MimeBodyPart) this.parts.elementAt(i)).updateHeaders();
        }
    }

    @Override // javax.mail.Multipart
    public synchronized void writeTo(OutputStream outputStream) throws MessagingException, IOException {
        parse();
        String str = "--" + new ContentType(this.contentType).getParameter("boundary");
        LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
        if (this.preamble != null) {
            byte[] bytes = ASCIIUtility.getBytes(this.preamble);
            lineOutputStream.write(bytes);
            if (bytes.length > 0 && bytes[bytes.length - 1] != 13 && bytes[bytes.length - 1] != 10) {
                lineOutputStream.writeln();
            }
        }
        for (int i = 0; i < this.parts.size(); i++) {
            lineOutputStream.writeln(str);
            ((MimeBodyPart) this.parts.elementAt(i)).writeTo(outputStream);
            lineOutputStream.writeln();
        }
        lineOutputStream.writeln(String.valueOf(str) + "--");
    }

    /* JADX WARN: Code duplicated, block: B:129:0x0193 A[Catch: all -> 0x023c, IOException -> 0x023f, EDGE_INSN: B:129:0x0193->B:130:0x0197 BREAK  A[LOOP:4: B:86:0x010d->B:221:0x010d], TryCatch #5 {all -> 0x023c, blocks: (B:33:0x005e, B:34:0x0065, B:53:0x009e, B:54:0x00a5, B:56:0x00a8, B:57:0x00ae, B:61:0x00be, B:62:0x00c2, B:64:0x00c8, B:67:0x00d0, B:69:0x00d4, B:70:0x00db, B:71:0x00dc, B:78:0x00eb, B:80:0x00f1, B:81:0x00f8, B:83:0x00fb, B:87:0x010f, B:94:0x012c, B:96:0x0134, B:98:0x013a, B:131:0x0199, B:133:0x01ae, B:132:0x01a4, B:107:0x014f, B:109:0x0159, B:111:0x015e, B:112:0x0163, B:116:0x016b, B:118:0x0172, B:123:0x0181, B:125:0x0187, B:127:0x018b, B:128:0x0192, B:129:0x0193, B:140:0x01c9, B:143:0x01d7, B:147:0x01e6, B:150:0x01fc, B:90:0x011a, B:92:0x0124, B:84:0x0101, B:77:0x00e7, B:37:0x0072, B:44:0x0084, B:46:0x008c, B:152:0x0207, B:155:0x020f, B:160:0x021d, B:161:0x0229, B:49:0x0093, B:40:0x007a, B:168:0x0241, B:169:0x0248), top: B:188:0x005e, outer: #2, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d7 A[Catch: all -> 0x023c, IOException -> 0x023f, TryCatch #5 {all -> 0x023c, blocks: (B:33:0x005e, B:34:0x0065, B:53:0x009e, B:54:0x00a5, B:56:0x00a8, B:57:0x00ae, B:61:0x00be, B:62:0x00c2, B:64:0x00c8, B:67:0x00d0, B:69:0x00d4, B:70:0x00db, B:71:0x00dc, B:78:0x00eb, B:80:0x00f1, B:81:0x00f8, B:83:0x00fb, B:87:0x010f, B:94:0x012c, B:96:0x0134, B:98:0x013a, B:131:0x0199, B:133:0x01ae, B:132:0x01a4, B:107:0x014f, B:109:0x0159, B:111:0x015e, B:112:0x0163, B:116:0x016b, B:118:0x0172, B:123:0x0181, B:125:0x0187, B:127:0x018b, B:128:0x0192, B:129:0x0193, B:140:0x01c9, B:143:0x01d7, B:147:0x01e6, B:150:0x01fc, B:90:0x011a, B:92:0x0124, B:84:0x0101, B:77:0x00e7, B:37:0x0072, B:44:0x0084, B:46:0x008c, B:152:0x0207, B:155:0x020f, B:160:0x021d, B:161:0x0229, B:49:0x0093, B:40:0x007a, B:168:0x0241, B:169:0x0248), top: B:188:0x005e, outer: #2, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:147:0x01e6 A[Catch: all -> 0x023c, IOException -> 0x023f, TryCatch #5 {all -> 0x023c, blocks: (B:33:0x005e, B:34:0x0065, B:53:0x009e, B:54:0x00a5, B:56:0x00a8, B:57:0x00ae, B:61:0x00be, B:62:0x00c2, B:64:0x00c8, B:67:0x00d0, B:69:0x00d4, B:70:0x00db, B:71:0x00dc, B:78:0x00eb, B:80:0x00f1, B:81:0x00f8, B:83:0x00fb, B:87:0x010f, B:94:0x012c, B:96:0x0134, B:98:0x013a, B:131:0x0199, B:133:0x01ae, B:132:0x01a4, B:107:0x014f, B:109:0x0159, B:111:0x015e, B:112:0x0163, B:116:0x016b, B:118:0x0172, B:123:0x0181, B:125:0x0187, B:127:0x018b, B:128:0x0192, B:129:0x0193, B:140:0x01c9, B:143:0x01d7, B:147:0x01e6, B:150:0x01fc, B:90:0x011a, B:92:0x0124, B:84:0x0101, B:77:0x00e7, B:37:0x0072, B:44:0x0084, B:46:0x008c, B:152:0x0207, B:155:0x020f, B:160:0x021d, B:161:0x0229, B:49:0x0093, B:40:0x007a, B:168:0x0241, B:169:0x0248), top: B:188:0x005e, outer: #2, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x021d A[Catch: all -> 0x023c, IOException -> 0x023f, TryCatch #5 {all -> 0x023c, blocks: (B:33:0x005e, B:34:0x0065, B:53:0x009e, B:54:0x00a5, B:56:0x00a8, B:57:0x00ae, B:61:0x00be, B:62:0x00c2, B:64:0x00c8, B:67:0x00d0, B:69:0x00d4, B:70:0x00db, B:71:0x00dc, B:78:0x00eb, B:80:0x00f1, B:81:0x00f8, B:83:0x00fb, B:87:0x010f, B:94:0x012c, B:96:0x0134, B:98:0x013a, B:131:0x0199, B:133:0x01ae, B:132:0x01a4, B:107:0x014f, B:109:0x0159, B:111:0x015e, B:112:0x0163, B:116:0x016b, B:118:0x0172, B:123:0x0181, B:125:0x0187, B:127:0x018b, B:128:0x0192, B:129:0x0193, B:140:0x01c9, B:143:0x01d7, B:147:0x01e6, B:150:0x01fc, B:90:0x011a, B:92:0x0124, B:84:0x0101, B:77:0x00e7, B:37:0x0072, B:44:0x0084, B:46:0x008c, B:152:0x0207, B:155:0x020f, B:160:0x021d, B:161:0x0229, B:49:0x0093, B:40:0x007a, B:168:0x0241, B:169:0x0248), top: B:188:0x005e, outer: #2, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:183:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:194:0x020d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:196:0x0065 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:205:0x018b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:216:0x0187 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:218:0x01ff A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:0x01fc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:220:0x01f2 A[SYNTHETIC] */
    protected synchronized void parse() throws MessagingException {
        String str;
        String line;
        boolean z;
        boolean z2;
        InternetHeaders internetHeadersCreateInternetHeaders;
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        long j;
        int i2;
        long j2;
        MimeBodyPart mimeBodyPartCreateMimeBodyPart;
        String line2;
        String property;
        if (this.parsed) {
            return;
        }
        if (bmparse) {
            parsebm();
            return;
        }
        try {
            InputStream inputStream = this.f4746ds.getInputStream();
            if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            SharedInputStream sharedInputStream = inputStream instanceof SharedInputStream ? (SharedInputStream) inputStream : null;
            String parameter = new ContentType(this.contentType).getParameter("boundary");
            if (parameter != null) {
                str = "--" + parameter;
            } else {
                if (!ignoreMissingBoundaryParameter) {
                    throw new MessagingException("Missing boundary parameter");
                }
                str = null;
            }
            try {
                try {
                    LineInputStream lineInputStream = new LineInputStream(inputStream);
                    StringBuffer stringBuffer = null;
                    String str2 = null;
                    while (true) {
                        line = lineInputStream.readLine();
                        char c = '\t';
                        char c2 = ' ';
                        z = false;
                        z = false;
                        z2 = true;
                        if (line == null) {
                            break;
                        }
                        int length = line.length() - 1;
                        while (length >= 0) {
                            char cCharAt = line.charAt(length);
                            if (cCharAt != c2 && cCharAt != c) {
                                break;
                            }
                            length--;
                            c = '\t';
                            c2 = ' ';
                            z = false;
                            z2 = true;
                        }
                        line = line.substring(z ? 1 : 0, length + 1);
                        if (str != null) {
                            if (line.equals(str)) {
                                break;
                            }
                            if (line.length() <= 0) {
                                if (str2 == null) {
                                    try {
                                        property = System.getProperty("line.separator", "\n");
                                    } catch (SecurityException unused) {
                                        property = "\n";
                                    }
                                    str2 = property;
                                }
                                if (stringBuffer == null) {
                                    stringBuffer = new StringBuffer(line.length() + 2);
                                }
                                stringBuffer.append(line);
                                stringBuffer.append(str2);
                            }
                        } else {
                            if (line.startsWith("--")) {
                                str = line;
                                break;
                            }
                            if (line.length() <= 0) {
                                if (str2 == null) {
                                    property = System.getProperty("line.separator", "\n");
                                    str2 = property;
                                }
                                if (stringBuffer == null) {
                                    stringBuffer = new StringBuffer(line.length() + 2);
                                }
                                stringBuffer.append(line);
                                stringBuffer.append(str2);
                            }
                        }
                    }
                    if (line == null) {
                        throw new MessagingException("Missing start boundary");
                    }
                    if (stringBuffer != null) {
                        this.preamble = stringBuffer.toString();
                    }
                    byte[] bytes = ASCIIUtility.getBytes(str);
                    int length2 = bytes.length;
                    long position = 0;
                    long position2 = 0;
                    boolean z3 = false;
                    while (!z3) {
                        if (sharedInputStream != null) {
                            position = sharedInputStream.getPosition();
                            do {
                                line2 = lineInputStream.readLine();
                                if (line2 == null) {
                                    break;
                                }
                            } while (line2.length() > 0);
                            if (line2 == null) {
                                if (!ignoreMissingEndBoundary) {
                                    throw new MessagingException("missing multipart end boundary");
                                }
                                this.complete = z;
                                break;
                            }
                            internetHeadersCreateInternetHeaders = null;
                        } else {
                            internetHeadersCreateInternetHeaders = createInternetHeaders(inputStream);
                        }
                        if (!inputStream.markSupported()) {
                            throw new MessagingException("Stream doesn't support mark");
                        }
                        if (sharedInputStream == null) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } else {
                            position2 = sharedInputStream.getPosition();
                            byteArrayOutputStream = null;
                        }
                        long position3 = position2;
                        int i3 = -1;
                        boolean z4 = true;
                        int i4 = -1;
                        while (true) {
                            if (z4) {
                                inputStream.mark(length2 + 4 + 1000);
                                int i5 = 0;
                                while (i5 < length2 && inputStream.read() == (bytes[i5] & 255)) {
                                    i5++;
                                }
                                if (i5 == length2) {
                                    int i6 = inputStream.read();
                                    if (i6 == 45 && inputStream.read() == 45) {
                                        this.complete = true;
                                        z3 = true;
                                    } else {
                                        while (true) {
                                            if (i6 != 32 && i6 != 9) {
                                                break;
                                            } else {
                                                i6 = inputStream.read();
                                            }
                                        }
                                        if (i6 != 10) {
                                            if (i6 == 13) {
                                                inputStream.mark(1);
                                                if (inputStream.read() != 10) {
                                                    inputStream.reset();
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                                inputStream.reset();
                                if (byteArrayOutputStream == null || i3 == -1) {
                                    i4 = i4;
                                } else {
                                    byteArrayOutputStream.write(i3);
                                    int i7 = i4;
                                    if (i7 != -1) {
                                        byteArrayOutputStream.write(i7);
                                    }
                                    i3 = -1;
                                    i4 = -1;
                                }
                                i = inputStream.read();
                                if (i < 0) {
                                    if (!ignoreMissingEndBoundary) {
                                        throw new MessagingException("missing multipart end boundary");
                                    }
                                    this.complete = false;
                                    z3 = true;
                                    break;
                                }
                                j = position3;
                                if (i != 13 || i == 10) {
                                    if (sharedInputStream != null) {
                                        position3 = sharedInputStream.getPosition() - 1;
                                    } else {
                                        position3 = j;
                                    }
                                    if (i == 13) {
                                        inputStream.mark(1);
                                        i2 = inputStream.read();
                                        if (i2 == 10) {
                                            i4 = i2;
                                            z4 = true;
                                            i3 = i;
                                        } else {
                                            inputStream.reset();
                                        }
                                    }
                                    i3 = i;
                                    z4 = true;
                                } else {
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.write(i);
                                    }
                                    position3 = j;
                                    z4 = false;
                                }
                            } else {
                                i4 = i4;
                                i = inputStream.read();
                                if (i < 0) {
                                    if (!ignoreMissingEndBoundary) {
                                        throw new MessagingException("missing multipart end boundary");
                                    }
                                    this.complete = false;
                                    z3 = true;
                                    break;
                                }
                                j = position3;
                                if (i != 13) {
                                }
                                if (sharedInputStream != null) {
                                    position3 = sharedInputStream.getPosition() - 1;
                                } else {
                                    position3 = j;
                                }
                                if (i == 13) {
                                    inputStream.mark(1);
                                    i2 = inputStream.read();
                                    if (i2 == 10) {
                                        i4 = i2;
                                        z4 = true;
                                        i3 = i;
                                    } else {
                                        inputStream.reset();
                                    }
                                }
                                i3 = i;
                                z4 = true;
                            }
                        }
                        if (sharedInputStream != null) {
                            j2 = position3;
                            mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(sharedInputStream.newStream(position, j2));
                        } else {
                            j2 = position3;
                            mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(internetHeadersCreateInternetHeaders, byteArrayOutputStream.toByteArray());
                        }
                        super.addBodyPart(mimeBodyPartCreateMimeBodyPart);
                        position2 = j2;
                        z = false;
                        z2 = true;
                    }
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                    this.parsed = z2;
                } catch (IOException e) {
                    throw new MessagingException("IO Error", e);
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    throw th;
                } catch (IOException unused3) {
                    throw th;
                }
            }
        } catch (Exception e2) {
            throw new MessagingException("No inputstream from datasource", e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0193 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:125:0x019b A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:129:0x01b4 A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x01c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:136:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:140:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:144:0x01e2 A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x01f5 A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x0204 A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:151:0x020e A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:154:0x0216 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:158:0x023f  */
    /* JADX WARN: Code duplicated, block: B:159:0x0244  */
    /* JADX WARN: Code duplicated, block: B:191:0x032f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:197:0x033f A[Catch: all -> 0x0368, IOException -> 0x036b, TryCatch #5 {IOException -> 0x036b, blocks: (B:28:0x0055, B:29:0x005c, B:49:0x009a, B:50:0x00a1, B:52:0x00a4, B:53:0x00aa, B:55:0x00b6, B:57:0x00bb, B:61:0x00c9, B:62:0x00cd, B:64:0x00d3, B:67:0x00db, B:69:0x00df, B:70:0x00e6, B:71:0x00e7, B:78:0x00fa, B:80:0x0100, B:81:0x0107, B:83:0x010a, B:85:0x0115, B:86:0x011d, B:88:0x012d, B:90:0x0131, B:91:0x0138, B:93:0x013b, B:94:0x013f, B:148:0x01f5, B:157:0x0224, B:149:0x0204, B:151:0x020e, B:152:0x0212, B:155:0x0218, B:156:0x021c, B:105:0x016e, B:114:0x0182, B:162:0x0268, B:167:0x027f, B:168:0x0285, B:170:0x028f, B:171:0x029e, B:175:0x02ac, B:177:0x02b2, B:125:0x019b, B:127:0x01ac, B:129:0x01b4, B:131:0x01bc, B:142:0x01d8, B:144:0x01e2, B:160:0x024a, B:99:0x0157, B:101:0x0161, B:84:0x0110, B:77:0x00f4, B:179:0x02d3, B:187:0x02f9, B:183:0x02e5, B:184:0x02ea, B:186:0x02f2, B:188:0x030b, B:32:0x006a, B:39:0x007c, B:41:0x0084, B:189:0x0329, B:192:0x0331, B:197:0x033f, B:198:0x034b, B:45:0x008f, B:35:0x0072), top: B:223:0x0055, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:224:0x0331 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:232:0x0351 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:253:0x01ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:254:0x01d8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:261:0x01d0 A[EDGE_INSN: B:261:0x01d0->B:138:0x01d0 BREAK  A[LOOP:10: B:134:0x01c6->B:160:0x024a], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    private synchronized void parsebm() throws MessagingException {
        String str;
        boolean z;
        boolean z2;
        String strSubstring;
        InternetHeaders internetHeadersCreateInternetHeaders;
        long j;
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        long j2;
        int i2;
        int i3;
        boolean z3;
        long j3;
        int i4;
        MimeBodyPart mimeBodyPartCreateMimeBodyPart;
        byte[] bArr;
        int i5;
        long j4;
        int i6;
        int i7;
        int i8;
        byte b;
        String line;
        String property;
        if (this.parsed) {
            return;
        }
        try {
            InputStream inputStream = this.f4746ds.getInputStream();
            if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            SharedInputStream sharedInputStream = inputStream instanceof SharedInputStream ? (SharedInputStream) inputStream : null;
            String parameter = new ContentType(this.contentType).getParameter("boundary");
            if (parameter != null) {
                str = "--" + parameter;
            } else {
                if (!ignoreMissingBoundaryParameter) {
                    throw new MessagingException("Missing boundary parameter");
                }
                str = null;
            }
            try {
                try {
                    LineInputStream lineInputStream = new LineInputStream(inputStream);
                    StringBuffer stringBuffer = null;
                    String str2 = null;
                    while (true) {
                        String line2 = lineInputStream.readLine();
                        char c = '\t';
                        char c2 = ' ';
                        z = false;
                        z = false;
                        z2 = true;
                        z2 = true;
                        if (line2 == null) {
                            strSubstring = line2;
                            break;
                        }
                        int length = line2.length() - 1;
                        while (length >= 0) {
                            char cCharAt = line2.charAt(length);
                            if (cCharAt != c2 && cCharAt != c) {
                                break;
                            }
                            length--;
                            lineInputStream = lineInputStream;
                            c = '\t';
                            c2 = ' ';
                            z = false;
                            z2 = true;
                        }
                        strSubstring = line2.substring(z ? 1 : 0, length + 1);
                        if (str != null) {
                            if (strSubstring.equals(str)) {
                                break;
                            }
                            LineInputStream lineInputStream2 = lineInputStream;
                            if (strSubstring.length() <= 0) {
                                if (str2 == null) {
                                    try {
                                        property = System.getProperty("line.separator", "\n");
                                    } catch (SecurityException unused) {
                                        property = "\n";
                                    }
                                    str2 = property;
                                }
                                if (stringBuffer == null) {
                                    stringBuffer = new StringBuffer(strSubstring.length() + 2);
                                }
                                stringBuffer.append(strSubstring);
                                stringBuffer.append(str2);
                            }
                            lineInputStream = lineInputStream2;
                        } else {
                            if (strSubstring.startsWith("--")) {
                                str = strSubstring;
                                break;
                            }
                            LineInputStream lineInputStream3 = lineInputStream;
                            if (strSubstring.length() <= 0) {
                                if (str2 == null) {
                                    property = System.getProperty("line.separator", "\n");
                                    str2 = property;
                                }
                                if (stringBuffer == null) {
                                    stringBuffer = new StringBuffer(strSubstring.length() + 2);
                                }
                                stringBuffer.append(strSubstring);
                                stringBuffer.append(str2);
                            }
                            lineInputStream = lineInputStream3;
                        }
                    }
                    if (strSubstring == null) {
                        throw new MessagingException("Missing start boundary");
                    }
                    if (stringBuffer != null) {
                        this.preamble = stringBuffer.toString();
                    }
                    byte[] bytes = ASCIIUtility.getBytes(str);
                    int length2 = bytes.length;
                    int[] iArr = new int[256];
                    int i9 = 0;
                    while (i9 < length2) {
                        byte[] bArr2 = bytes;
                        int i10 = length2;
                        byte b2 = bArr2[i9];
                        i9++;
                        iArr[b2] = i9;
                        lineInputStream = lineInputStream;
                        bytes = bArr2;
                        length2 = i10;
                        z = false;
                        z2 = true;
                    }
                    int[] iArr2 = new int[length2];
                    int i11 = length2;
                    while (i11 > 0) {
                        byte[] bArr3 = bytes;
                        int i12 = length2;
                        LineInputStream lineInputStream4 = lineInputStream;
                        int[] iArr3 = iArr2;
                        int i13 = i12 - 1;
                        while (true) {
                            if (i13 >= i11) {
                                if (bArr3[i13] != bArr3[i13 - i11]) {
                                    break;
                                }
                                iArr3[i13 - 1] = i11;
                                i13--;
                            } else {
                                while (i13 > 0) {
                                    i13--;
                                    iArr3[i13] = i11;
                                }
                                break;
                            }
                        }
                        i11--;
                        lineInputStream = lineInputStream4;
                        iArr2 = iArr3;
                        bytes = bArr3;
                        length2 = i12;
                        z = false;
                        z2 = true;
                    }
                    int i14 = length2 - 1;
                    iArr2[i14] = z2 ? 1 : 0;
                    long j5 = 0;
                    long position = 0;
                    boolean z4 = false;
                    while (!z4) {
                        if (sharedInputStream != null) {
                            long position2 = sharedInputStream.getPosition();
                            do {
                                line = lineInputStream.readLine();
                                if (line == null) {
                                    break;
                                }
                            } while (line.length() > 0);
                            if (line == null) {
                                if (!ignoreMissingEndBoundary) {
                                    throw new MessagingException("missing multipart end boundary");
                                }
                                this.complete = z;
                                break;
                            }
                            j = position2;
                            internetHeadersCreateInternetHeaders = null;
                        } else {
                            internetHeadersCreateInternetHeaders = createInternetHeaders(inputStream);
                            j = j5;
                        }
                        if (!inputStream.markSupported()) {
                            throw new MessagingException("Stream doesn't support mark");
                        }
                        if (sharedInputStream == null) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } else {
                            position = sharedInputStream.getPosition();
                            byteArrayOutputStream = null;
                        }
                        byte[] bArr4 = new byte[length2];
                        byte[] bArr5 = new byte[length2];
                        boolean z5 = true;
                        int i15 = 0;
                        while (true) {
                            inputStream.mark(length2 + 4 + 1000);
                            LineInputStream lineInputStream5 = lineInputStream;
                            int fully = readFully(inputStream, bArr4, 0, length2);
                            if (fully >= length2) {
                                int i16 = i14;
                                while (true) {
                                    if (i16 >= 0) {
                                        i = i14;
                                        z3 = z4;
                                        if (bArr4[i16] != bytes[i16]) {
                                            break;
                                        }
                                        i16--;
                                        i14 = i;
                                        z4 = z3;
                                    } else {
                                        i = i14;
                                        z3 = z4;
                                        break;
                                    }
                                }
                                if (i16 < 0) {
                                    if (z5) {
                                        i2 = i15;
                                    } else {
                                        i2 = i15;
                                        byte b3 = bArr5[i2 - 1];
                                        if (b3 != 13) {
                                            b = 10;
                                            if (b3 == 10) {
                                            }
                                            if (!z5 || i3 > 0) {
                                                if (sharedInputStream != null) {
                                                    position = (sharedInputStream.getPosition() - ((long) length2)) - ((long) i3);
                                                }
                                                i6 = inputStream.read();
                                                if (i6 == 45 || inputStream.read() != 45) {
                                                    i7 = i6;
                                                    while (true) {
                                                        if (i7 != 32) {
                                                            i8 = i3;
                                                            if (i7 != 9) {
                                                                break;
                                                            }
                                                        } else {
                                                            i8 = i3;
                                                        }
                                                        i7 = inputStream.read();
                                                        j = j;
                                                        i3 = i8;
                                                        bytes = bytes;
                                                        length2 = length2;
                                                    }
                                                    if (i7 != 10) {
                                                        if (i7 == 13) {
                                                            inputStream.mark(1);
                                                            if (inputStream.read() != 10) {
                                                                inputStream.reset();
                                                            }
                                                        }
                                                    }
                                                    j2 = position;
                                                    i3 = i8;
                                                } else {
                                                    this.complete = true;
                                                    j2 = position;
                                                }
                                                if (sharedInputStream != null) {
                                                    j3 = j;
                                                    mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(sharedInputStream.newStream(j3, j2));
                                                } else {
                                                    j3 = j;
                                                    i4 = i2 - i3;
                                                    if (i4 > 0) {
                                                        byteArrayOutputStream.write(bArr5, 0, i4);
                                                    }
                                                    if (!this.complete && fully > 0) {
                                                        byteArrayOutputStream.write(bArr4, 0, fully);
                                                    }
                                                    mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(internetHeadersCreateInternetHeaders, byteArrayOutputStream.toByteArray());
                                                }
                                                super.addBodyPart(mimeBodyPartCreateMimeBodyPart);
                                                j5 = j3;
                                                position = j2;
                                                lineInputStream = lineInputStream5;
                                                i14 = i;
                                                z4 = z3;
                                                iArr2 = iArr2;
                                                bytes = bytes;
                                                length2 = length2;
                                                z = false;
                                                z2 = true;
                                            } else {
                                                iArr2 = iArr2;
                                            }
                                            bArr = bytes;
                                            i5 = length2;
                                            j4 = j;
                                            i16 = 0;
                                        } else {
                                            b = 10;
                                        }
                                        i3 = (b3 == b && i2 >= 2 && bArr5[i2 + (-2)] == 13) ? 2 : 1;
                                        if (z5) {
                                        }
                                        if (sharedInputStream != null) {
                                            position = (sharedInputStream.getPosition() - ((long) length2)) - ((long) i3);
                                        }
                                        i6 = inputStream.read();
                                        if (i6 == 45) {
                                        }
                                        i7 = i6;
                                        while (true) {
                                            if (i7 != 32) {
                                                i8 = i3;
                                                if (i7 != 9) {
                                                    break;
                                                    break;
                                                }
                                            } else {
                                                i8 = i3;
                                            }
                                            i7 = inputStream.read();
                                            j = j;
                                            i3 = i8;
                                            bytes = bytes;
                                            length2 = length2;
                                        }
                                        if (i7 != 10) {
                                            if (i7 == 13) {
                                                inputStream.mark(1);
                                                if (inputStream.read() != 10) {
                                                    inputStream.reset();
                                                }
                                            }
                                            bArr = bytes;
                                            i5 = length2;
                                            j4 = j;
                                            i16 = 0;
                                        }
                                        j2 = position;
                                        i3 = i8;
                                        if (sharedInputStream != null) {
                                            j3 = j;
                                            mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(sharedInputStream.newStream(j3, j2));
                                        } else {
                                            j3 = j;
                                            i4 = i2 - i3;
                                            if (i4 > 0) {
                                                byteArrayOutputStream.write(bArr5, 0, i4);
                                            }
                                            if (!this.complete) {
                                                byteArrayOutputStream.write(bArr4, 0, fully);
                                            }
                                            mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(internetHeadersCreateInternetHeaders, byteArrayOutputStream.toByteArray());
                                        }
                                        super.addBodyPart(mimeBodyPartCreateMimeBodyPart);
                                        j5 = j3;
                                        position = j2;
                                        lineInputStream = lineInputStream5;
                                        i14 = i;
                                        z4 = z3;
                                        iArr2 = iArr2;
                                        bytes = bytes;
                                        length2 = length2;
                                        z = false;
                                        z2 = true;
                                    }
                                    i3 = 0;
                                    if (z5) {
                                    }
                                    if (sharedInputStream != null) {
                                        position = (sharedInputStream.getPosition() - ((long) length2)) - ((long) i3);
                                    }
                                    i6 = inputStream.read();
                                    if (i6 == 45) {
                                    }
                                    i7 = i6;
                                    while (true) {
                                        if (i7 != 32) {
                                            i8 = i3;
                                            if (i7 != 9) {
                                                break;
                                                break;
                                            }
                                        } else {
                                            i8 = i3;
                                        }
                                        i7 = inputStream.read();
                                        j = j;
                                        i3 = i8;
                                        bytes = bytes;
                                        length2 = length2;
                                    }
                                    if (i7 != 10) {
                                        if (i7 == 13) {
                                            inputStream.mark(1);
                                            if (inputStream.read() != 10) {
                                                inputStream.reset();
                                            }
                                        }
                                        bArr = bytes;
                                        i5 = length2;
                                        j4 = j;
                                        i16 = 0;
                                    }
                                    j2 = position;
                                    i3 = i8;
                                    if (sharedInputStream != null) {
                                        j3 = j;
                                        mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(sharedInputStream.newStream(j3, j2));
                                    } else {
                                        j3 = j;
                                        i4 = i2 - i3;
                                        if (i4 > 0) {
                                            byteArrayOutputStream.write(bArr5, 0, i4);
                                        }
                                        if (!this.complete) {
                                            byteArrayOutputStream.write(bArr4, 0, fully);
                                        }
                                        mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(internetHeadersCreateInternetHeaders, byteArrayOutputStream.toByteArray());
                                    }
                                    super.addBodyPart(mimeBodyPartCreateMimeBodyPart);
                                    j5 = j3;
                                    position = j2;
                                    lineInputStream = lineInputStream5;
                                    i14 = i;
                                    z4 = z3;
                                    iArr2 = iArr2;
                                    bytes = bytes;
                                    length2 = length2;
                                    z = false;
                                    z2 = true;
                                } else {
                                    bArr = bytes;
                                    i5 = length2;
                                    iArr2 = iArr2;
                                    j4 = j;
                                    i2 = i15;
                                }
                                int iMax = Math.max((i16 + 1) - iArr[bArr4[i16] & 127 ? 1 : 0], iArr2[i16]);
                                if (iMax < 2) {
                                    if (sharedInputStream == null && i2 > 1) {
                                        byteArrayOutputStream.write(bArr5, 0, i2 - 1);
                                    }
                                    inputStream.reset();
                                    skipFully(inputStream, 1L);
                                    if (i2 > 0) {
                                        bArr5[0] = bArr5[i2 - 1];
                                        bArr5[1] = bArr4[0];
                                        i15 = 2;
                                    } else {
                                        bArr5[0] = bArr4[0];
                                        i15 = 1;
                                    }
                                } else {
                                    if (i2 > 0 && sharedInputStream == null) {
                                        byteArrayOutputStream.write(bArr5, 0, i2);
                                    }
                                    inputStream.reset();
                                    skipFully(inputStream, iMax);
                                    i15 = iMax;
                                    byte[] bArr6 = bArr4;
                                    bArr4 = bArr5;
                                    bArr5 = bArr6;
                                }
                                j = j4;
                                lineInputStream = lineInputStream5;
                                i14 = i;
                                z4 = z3;
                                iArr2 = iArr2;
                                bytes = bArr;
                                length2 = i5;
                                z5 = false;
                            } else {
                                if (!ignoreMissingEndBoundary) {
                                    throw new MessagingException("missing multipart end boundary");
                                }
                                if (sharedInputStream != null) {
                                    position = sharedInputStream.getPosition();
                                }
                                this.complete = false;
                                iArr2 = iArr2;
                                i = i14;
                                j2 = position;
                                i2 = i15;
                                i3 = 0;
                            }
                            z3 = true;
                            if (sharedInputStream != null) {
                                j3 = j;
                                mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(sharedInputStream.newStream(j3, j2));
                            } else {
                                j3 = j;
                                i4 = i2 - i3;
                                if (i4 > 0) {
                                    byteArrayOutputStream.write(bArr5, 0, i4);
                                }
                                if (!this.complete) {
                                    byteArrayOutputStream.write(bArr4, 0, fully);
                                }
                                mimeBodyPartCreateMimeBodyPart = createMimeBodyPart(internetHeadersCreateInternetHeaders, byteArrayOutputStream.toByteArray());
                            }
                            super.addBodyPart(mimeBodyPartCreateMimeBodyPart);
                            j5 = j3;
                            position = j2;
                            lineInputStream = lineInputStream5;
                            i14 = i;
                            z4 = z3;
                            iArr2 = iArr2;
                            bytes = bytes;
                            length2 = length2;
                            z = false;
                            z2 = true;
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                    this.parsed = z2;
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                        throw th;
                    } catch (IOException unused3) {
                        throw th;
                    }
                }
            } catch (IOException e) {
                throw new MessagingException("IO Error", e);
            }
        } catch (Exception e2) {
            throw new MessagingException("No inputstream from datasource", e2);
        }
    }

    private static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        while (i2 > 0) {
            int i4 = inputStream.read(bArr, i, i2);
            if (i4 <= 0) {
                break;
            }
            i += i4;
            i3 += i4;
            i2 -= i4;
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    private void skipFully(InputStream inputStream, long j) throws IOException {
        while (j > 0) {
            long jSkip = inputStream.skip(j);
            if (jSkip <= 0) {
                throw new EOFException("can't skip");
            }
            j -= jSkip;
        }
    }

    protected InternetHeaders createInternetHeaders(InputStream inputStream) throws MessagingException {
        return new InternetHeaders(inputStream);
    }

    protected MimeBodyPart createMimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        return new MimeBodyPart(internetHeaders, bArr);
    }

    protected MimeBodyPart createMimeBodyPart(InputStream inputStream) throws MessagingException {
        return new MimeBodyPart(inputStream);
    }
}
