package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0639i;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.g */
/* JADX INFO: compiled from: Http2.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0544g {

    /* JADX INFO: renamed from: a */
    static final C0639i f317a = C0639i.m575a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* JADX INFO: renamed from: d */
    private static final String[] f320d = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* JADX INFO: renamed from: b */
    static final String[] f318b = new String[64];

    /* JADX INFO: renamed from: c */
    static final String[] f319c = new String[256];

    static {
        for (int i = 0; i < f319c.length; i++) {
            f319c[i] = C0503c.m110a("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        f318b[0] = "";
        f318b[1] = "END_STREAM";
        int[] iArr = {1};
        f318b[8] = "PADDED";
        for (int i2 = 0; i2 <= 0; i2++) {
            int i3 = iArr[i2];
            f318b[i3 | 8] = f318b[i3] + "|PADDED";
        }
        f318b[4] = "END_HEADERS";
        f318b[32] = "PRIORITY";
        f318b[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i4 = 0; i4 < 3; i4++) {
            int i5 = iArr2[i4];
            for (int i6 = 0; i6 <= 0; i6++) {
                int i7 = iArr[i6];
                int i8 = i7 | i5;
                f318b[i8] = f318b[i7] + '|' + f318b[i5];
                f318b[i8 | 8] = f318b[i7] + '|' + f318b[i5] + "|PADDED";
            }
        }
        for (int i9 = 0; i9 < f318b.length; i9++) {
            if (f318b[i9] == null) {
                f318b[i9] = f319c[i9];
            }
        }
    }

    private C0544g() {
    }

    /* JADX INFO: renamed from: a */
    static IllegalArgumentException m252a(String str, Object... objArr) {
        throw new IllegalArgumentException(C0503c.m110a(str, objArr));
    }

    /* JADX INFO: renamed from: b */
    static IOException m254b(String str, Object... objArr) throws IOException {
        throw new IOException(C0503c.m110a(str, objArr));
    }

    /* JADX INFO: renamed from: a */
    static String m253a(boolean z, int i, int i2, byte b, byte b2) {
        String strReplace;
        String strM110a = b < f320d.length ? f320d[b] : C0503c.m110a("0x%02x", Byte.valueOf(b));
        if (b2 != 0) {
            switch (b) {
                case 2:
                case 3:
                case 7:
                case 8:
                    strReplace = f319c[b2];
                    break;
                case 4:
                case 6:
                    strReplace = b2 != 1 ? f319c[b2] : "ACK";
                    break;
                case 5:
                default:
                    String str = b2 < f318b.length ? f318b[b2] : f319c[b2];
                    if (b == 5 && (b2 & 4) != 0) {
                        strReplace = str.replace("HEADERS", "PUSH_PROMISE");
                    } else if (b == 0 && (b2 & 32) != 0) {
                        strReplace = str.replace("PRIORITY", "COMPRESSED");
                    } else {
                        strReplace = str;
                    }
                    break;
            }
        } else {
            strReplace = "";
        }
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = strM110a;
        objArr[4] = strReplace;
        return C0503c.m110a("%s 0x%08x %5d %-13s %s", objArr);
    }
}
