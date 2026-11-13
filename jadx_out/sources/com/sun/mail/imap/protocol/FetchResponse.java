package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import java.io.IOException;
import java.util.Vector;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public class FetchResponse extends IMAPResponse {
    private static final char[] HEADER = {FilenameUtils.EXTENSION_SEPARATOR, 'H', 'E', 'A', 'D', 'E', 'R'};
    private static final char[] TEXT = {FilenameUtils.EXTENSION_SEPARATOR, 'T', 'E', 'X', 'T'};
    private Item[] items;

    public FetchResponse(Protocol protocol) throws ProtocolException, IOException {
        super(protocol);
        parse();
    }

    public FetchResponse(IMAPResponse iMAPResponse) throws ProtocolException, IOException {
        super(iMAPResponse);
        parse();
    }

    public int getItemCount() {
        return this.items.length;
    }

    public Item getItem(int i) {
        return this.items[i];
    }

    public Item getItem(Class cls) {
        for (int i = 0; i < this.items.length; i++) {
            if (cls.isInstance(this.items[i])) {
                return this.items[i];
            }
        }
        return null;
    }

    public static Item getItem(Response[] responseArr, int i, Class cls) {
        if (responseArr == null) {
            return null;
        }
        for (int i2 = 0; i2 < responseArr.length; i2++) {
            if (responseArr[i2] != null && (responseArr[i2] instanceof FetchResponse) && ((FetchResponse) responseArr[i2]).getNumber() == i) {
                FetchResponse fetchResponse = (FetchResponse) responseArr[i2];
                for (int i3 = 0; i3 < fetchResponse.items.length; i3++) {
                    if (cls.isInstance(fetchResponse.items[i3])) {
                        return fetchResponse.items[i3];
                    }
                }
            }
        }
        return null;
    }

    private void parse() throws ParsingException {
        skipSpaces();
        if (this.buffer[this.index] != 40) {
            throw new ParsingException("error in FETCH parsing, missing '(' at index " + this.index);
        }
        Vector vector = new Vector();
        Object bodystructure = null;
        do {
            this.index++;
            if (this.index >= this.size) {
                throw new ParsingException("error in FETCH parsing, ran off end of buffer, size " + this.size);
            }
            byte b = this.buffer[this.index];
            if (b != 66) {
                if (b != 73) {
                    if (b != 82) {
                        if (b != 85) {
                            switch (b) {
                                case 69:
                                    if (match(ENVELOPE.name)) {
                                        this.index += ENVELOPE.name.length;
                                        bodystructure = new ENVELOPE(this);
                                    }
                                    break;
                                case 70:
                                    if (match(FLAGS.name)) {
                                        this.index += FLAGS.name.length;
                                        bodystructure = new FLAGS(this);
                                    }
                                    break;
                            }
                        } else if (match(UID.name)) {
                            this.index += UID.name.length;
                            bodystructure = new UID(this);
                        }
                    } else if (match(RFC822SIZE.name)) {
                        this.index += RFC822SIZE.name.length;
                        bodystructure = new RFC822SIZE(this);
                    } else if (match(RFC822DATA.name)) {
                        this.index += RFC822DATA.name.length;
                        if (match(HEADER)) {
                            this.index += HEADER.length;
                        } else if (match(TEXT)) {
                            this.index += TEXT.length;
                        }
                        bodystructure = new RFC822DATA(this);
                    }
                } else if (match(INTERNALDATE.name)) {
                    this.index += INTERNALDATE.name.length;
                    bodystructure = new INTERNALDATE(this);
                }
            } else if (match(BODY.name)) {
                if (this.buffer[this.index + 4] == 91) {
                    this.index += BODY.name.length;
                    bodystructure = new BODY(this);
                } else {
                    if (match(BODYSTRUCTURE.name)) {
                        this.index += BODYSTRUCTURE.name.length;
                    } else {
                        this.index += BODY.name.length;
                    }
                    bodystructure = new BODYSTRUCTURE(this);
                }
            }
            if (bodystructure != null) {
                vector.addElement(bodystructure);
            }
        } while (this.buffer[this.index] != 41);
        this.index++;
        this.items = new Item[vector.size()];
        vector.copyInto(this.items);
    }

    private boolean match(char[] cArr) {
        int length = cArr.length;
        int i = this.index;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            if (Character.toUpperCase((char) this.buffer[i]) != cArr[i2]) {
                return false;
            }
            i2 = i4;
            i = i3;
        }
        return true;
    }
}
