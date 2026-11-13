package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import java.util.Map;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.g.f */
/* JADX INFO: compiled from: Code39Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1282f extends AbstractC1295s {
    @Override // com.google.p028a.p047g.AbstractC1295s, com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.CODE_39) {
            throw new IllegalArgumentException("Can only encode CODE_39, but got ".concat(String.valueOf(enumC1138a)));
        }
        return super.mo1517a(str, enumC1138a, i, i2, map);
    }

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        for (int i = 0; i < length; i++) {
            if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i)) < 0) {
                str = m2170b(str);
                length = str.length();
                if (length <= 80) {
                    break;
                }
                throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length + " (extended full ASCII mode)");
            }
        }
        int[] iArr = new int[9];
        int i2 = length + 25;
        int i3 = 0;
        while (i3 < length) {
            m2169a(C1281e.f2484b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i3))], iArr);
            int i4 = i2;
            for (int i5 = 0; i5 < 9; i5++) {
                i4 += iArr[i5];
            }
            i3++;
            i2 = i4;
        }
        boolean[] zArr = new boolean[i2];
        m2169a(148, iArr);
        int iA = m2196a(zArr, 0, iArr, true);
        int[] iArr2 = {1};
        int iA2 = iA + m2196a(zArr, iA, iArr2, false);
        for (int i6 = 0; i6 < length; i6++) {
            m2169a(C1281e.f2484b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i6))], iArr);
            int iA3 = iA2 + m2196a(zArr, iA2, iArr, true);
            iA2 = iA3 + m2196a(zArr, iA3, iArr2, false);
        }
        m2169a(148, iArr);
        m2196a(zArr, iA2, iArr, true);
        return zArr;
    }

    /* JADX INFO: renamed from: a */
    private static void m2169a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) != 0) {
                i3 = 2;
            }
            iArr[i2] = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00e7  */
    /* JADX INFO: renamed from: b */
    private static String m2170b(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == 0) {
                sb.append("%U");
            } else if (cCharAt == ' ') {
                sb.append(cCharAt);
            } else if (cCharAt == '@') {
                sb.append("%V");
            } else if (cCharAt != '`') {
                switch (cCharAt) {
                    case '-':
                    case '.':
                        sb.append(cCharAt);
                        break;
                    default:
                        if (cCharAt <= 26) {
                            sb.append('$');
                            sb.append((char) ((cCharAt - 1) + 65));
                        } else if (cCharAt < ' ') {
                            sb.append('%');
                            sb.append((char) ((cCharAt - 27) + 65));
                        } else if (cCharAt <= ',' || cCharAt == '/' || cCharAt == ':') {
                            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                            sb.append((char) ((cCharAt - '!') + 65));
                        } else if (cCharAt <= '9') {
                            sb.append((char) ((cCharAt - '0') + 48));
                        } else if (cCharAt <= '?') {
                            sb.append('%');
                            sb.append((char) ((cCharAt - ';') + 70));
                        } else if (cCharAt <= 'Z') {
                            sb.append((char) ((cCharAt - 'A') + 65));
                        } else if (cCharAt <= '_') {
                            sb.append('%');
                            sb.append((char) ((cCharAt - '[') + 75));
                        } else if (cCharAt <= 'z') {
                            sb.append('+');
                            sb.append((char) ((cCharAt - 'a') + 65));
                        } else if (cCharAt <= 127) {
                            sb.append('%');
                            sb.append((char) ((cCharAt - '{') + 80));
                        } else {
                            throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + str.charAt(i) + "'");
                        }
                        break;
                }
            } else {
                sb.append("%W");
            }
        }
        return sb.toString();
    }
}
