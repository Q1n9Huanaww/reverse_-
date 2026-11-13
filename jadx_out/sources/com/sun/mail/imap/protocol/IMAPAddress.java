package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;
import java.util.Vector;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/* JADX INFO: compiled from: ENVELOPE.java */
/* JADX INFO: loaded from: classes.dex */
class IMAPAddress extends InternetAddress {
    private static final long serialVersionUID = -3835822029483122232L;
    private boolean group;
    private InternetAddress[] grouplist;
    private String groupname;

    IMAPAddress(Response response) throws ParsingException {
        this.group = false;
        response.skipSpaces();
        if (response.readByte() != 40) {
            throw new ParsingException("ADDRESS parse error");
        }
        this.encodedPersonal = response.readString();
        response.readString();
        String string = response.readString();
        String string2 = response.readString();
        if (response.readByte() != 41) {
            throw new ParsingException("ADDRESS parse error");
        }
        if (string2 == null) {
            this.group = true;
            this.groupname = string;
            if (this.groupname == null) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.groupname);
            stringBuffer.append(':');
            Vector vector = new Vector();
            while (response.peekByte() != 41) {
                IMAPAddress iMAPAddress = new IMAPAddress(response);
                if (iMAPAddress.isEndOfGroup()) {
                    break;
                }
                if (vector.size() != 0) {
                    stringBuffer.append(',');
                }
                stringBuffer.append(iMAPAddress.toString());
                vector.addElement(iMAPAddress);
            }
            stringBuffer.append(';');
            this.address = stringBuffer.toString();
            this.grouplist = new IMAPAddress[vector.size()];
            vector.copyInto(this.grouplist);
            return;
        }
        if (string == null || string.length() == 0) {
            this.address = string2;
            return;
        }
        if (string2.length() == 0) {
            this.address = string;
            return;
        }
        this.address = String.valueOf(string) + "@" + string2;
    }

    boolean isEndOfGroup() {
        return this.group && this.groupname == null;
    }

    @Override // javax.mail.internet.InternetAddress
    public boolean isGroup() {
        return this.group;
    }

    @Override // javax.mail.internet.InternetAddress
    public InternetAddress[] getGroup(boolean z) throws AddressException {
        if (this.grouplist == null) {
            return null;
        }
        return (InternetAddress[]) this.grouplist.clone();
    }
}
