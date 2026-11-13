package com.google.p028a.p038d.p041c;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import mobi.oneway.export.C1898a;

/* JADX INFO: renamed from: com.google.a.d.c.i */
/* JADX INFO: compiled from: ErrorCorrection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1227i {

    /* JADX INFO: renamed from: c */
    private static final int f2232c = 301;

    /* JADX INFO: renamed from: a */
    private static final int[] f2230a = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};

    /* JADX INFO: renamed from: b */
    private static final int[][] f2231b = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, 223, 248, 116, 255, 110, 61}, new int[]{175, 138, UiMessage.CommandToUi.Command_Type.SET_CHECK_BOX_VALUE, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 109, C0769a.f1097aC, 94, 254, 225, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, 190, 88, UiMessage.CommandToUi.Command_Type.SET_CHECK_BOX_VALUE, 109, 39, 176, 21, 155, 197, 251, 223, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{UiMessage.CommandToUi.Command_Type.SET_EDIT_INPUT_TYPE_VALUE, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, 121, 17, 138, 110, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, 127, 242, 218, 130, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 162, 181, 102, 120, 84, 179, 220, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, 225, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, C1898a.f4816e, 105, 122, 2, 245, 133, 242, 8, 175, 95, 100, 9, 167, 105, UiMessage.CommandToUi.Command_Type.SET_ROW_ALIGNMENT_VALUE, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, UiMessage.CommandToUi.Command_Type.SET_EDIT_TEXT_VALUE, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{245, 132, 172, 223, 96, 32, 117, 22, 238, 133, 238, 231, UiMessage.CommandToUi.Command_Type.SET_CHECK_BOX_VALUE, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, UiMessage.CommandToUi.Command_Type.SET_CHECK_BOX_VALUE, 131, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 92, 253, 225, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, UiMessage.CommandToUi.Command_Type.SET_TAB_VALUE, 100, 29, 175, 170, 230, 192, UiMessage.CommandToUi.Command_Type.SET_FW_BUTTON_VALUE, 235, 150, 159, 36, 223, 38, 200, 132, 54, 228, 146, 218, 234, 117, UiMessage.CommandToUi.Command_Type.SET_BOTTON_VALUE, 29, 232, 144, 238, 22, 150, UiMessage.CommandToUi.Command_Type.SET_TEXT_VIEW_VALUE, 117, 62, UiMessage.CommandToUi.Command_Type.SET_SPINNER_VALUE, 164, 13, 137, 245, 127, 67, C1898a.f4816e, 28, 155, 43, UiMessage.CommandToUi.Command_Type.SET_BOTTON_VALUE, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, UiMessage.CommandToUi.Command_Type.SET_LINE_VALUE, 39, 118, UiMessage.CommandToUi.Command_Type.SET_EDIT_TEXT_VALUE, 188, UiMessage.CommandToUi.Command_Type.SET_TEXT_VIEW_VALUE, 189, 143, 108, 196, 37, 185, 112, 134, 230, 245, 63, 197, 190, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, 130, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, 224, 124, 86, 47, 11, UiMessage.CommandToUi.Command_Type.SET_RADIO_GROUP_VALUE}, new int[]{220, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, 127, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, UiMessage.CommandToUi.Command_Type.SET_FONT_SIZE_LEVEL_VALUE, 15, 160, 227, 236, 66, 139, 153, 185, UiMessage.CommandToUi.Command_Type.SET_EDIT_TEXT_VALUE, 167, 179, 25, 220, 232, 96, UiMessage.CommandToUi.Command_Type.SET_LINE_VALUE, 231, 136, 223, 239, 181, 241, 59, 52, 172, 25, 49, 232, UiMessage.CommandToUi.Command_Type.SET_EDIT_INPUT_TYPE_VALUE, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};

    /* JADX INFO: renamed from: d */
    private static final int[] f2233d = new int[256];

    /* JADX INFO: renamed from: e */
    private static final int[] f2234e = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            f2234e[i2] = i;
            f2233d[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private C1227i() {
    }

    /* JADX INFO: renamed from: a */
    public static String m1971a(String str, C1229k c1229k) {
        if (str.length() != c1229k.f2257b) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(c1229k.f2257b + c1229k.f2258c);
        sb.append(str);
        int iMo1931a = c1229k.mo1931a();
        if (iMo1931a == 1) {
            sb.append(m1969a(str, c1229k.f2258c));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[iMo1931a];
            int[] iArr2 = new int[iMo1931a];
            int[] iArr3 = new int[iMo1931a];
            int i = 0;
            while (i < iMo1931a) {
                int i2 = i + 1;
                iArr[i] = c1229k.mo1932a(i2);
                iArr2[i] = c1229k.f2261f;
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < iMo1931a; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < c1229k.f2257b; i4 += iMo1931a) {
                    sb2.append(str.charAt(i4));
                }
                String strM1969a = m1969a(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * iMo1931a) {
                    sb.setCharAt(c1229k.f2257b + i5, strM1969a.charAt(i6));
                    i5 += iMo1931a;
                    i6++;
                }
            }
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static String m1969a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (true) {
            if (i2 >= f2230a.length) {
                i2 = -1;
                break;
            }
            if (f2230a[i2] == i) {
                break;
            }
            i2++;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i)));
        }
        int[] iArr = f2231b[i2];
        char[] cArr = new char[i];
        for (int i3 = 0; i3 < i; i3++) {
            cArr[i3] = 0;
        }
        for (int i4 = 0; i4 < length + 0; i4++) {
            int i5 = i - 1;
            int iCharAt = cArr[i5] ^ charSequence.charAt(i4);
            while (i5 > 0) {
                if (iCharAt != 0 && iArr[i5] != 0) {
                    cArr[i5] = (char) (cArr[i5 - 1] ^ f2234e[(f2233d[iCharAt] + f2233d[iArr[i5]]) % 255]);
                } else {
                    cArr[i5] = cArr[i5 - 1];
                }
                i5--;
            }
            if (iCharAt != 0 && iArr[0] != 0) {
                cArr[0] = (char) f2234e[(f2233d[iCharAt] + f2233d[iArr[0]]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i];
        for (int i6 = 0; i6 < i; i6++) {
            cArr2[i6] = cArr[(i - i6) - 1];
        }
        return String.valueOf(cArr2);
    }

    /* JADX INFO: renamed from: a */
    private static String m1970a(CharSequence charSequence, int i, int i2) {
        int i3 = 0;
        while (true) {
            if (i3 >= f2230a.length) {
                i3 = -1;
                break;
            }
            if (f2230a[i3] == i2) {
                break;
            }
            i3++;
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i2)));
        }
        int[] iArr = f2231b[i3];
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = 0;
        }
        for (int i5 = 0; i5 < i + 0; i5++) {
            int i6 = i2 - 1;
            int iCharAt = cArr[i6] ^ charSequence.charAt(i5);
            while (i6 > 0) {
                if (iCharAt != 0 && iArr[i6] != 0) {
                    cArr[i6] = (char) (cArr[i6 - 1] ^ f2234e[(f2233d[iCharAt] + f2233d[iArr[i6]]) % 255]);
                } else {
                    cArr[i6] = cArr[i6 - 1];
                }
                i6--;
            }
            if (iCharAt != 0 && iArr[0] != 0) {
                cArr[0] = (char) f2234e[(f2233d[iCharAt] + f2233d[iArr[0]]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            cArr2[i7] = cArr[(i2 - i7) - 1];
        }
        return String.valueOf(cArr2);
    }
}
