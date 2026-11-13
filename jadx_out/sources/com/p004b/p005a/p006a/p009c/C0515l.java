package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.EnumC0592al;
import java.net.ProtocolException;

/* JADX INFO: renamed from: com.b.a.a.c.l */
/* JADX INFO: compiled from: StatusLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0515l {

    /* JADX INFO: renamed from: a */
    public final EnumC0592al f205a;

    /* JADX INFO: renamed from: b */
    public final int f206b;

    /* JADX INFO: renamed from: c */
    public final String f207c;

    private C0515l(EnumC0592al enumC0592al, int i, String str) {
        this.f205a = enumC0592al;
        this.f206b = i;
        this.f207c = str;
    }

    /* JADX INFO: renamed from: a */
    public static C0515l m160a(String str) throws ProtocolException {
        EnumC0592al enumC0592al;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                enumC0592al = EnumC0592al.HTTP_1_0;
            } else if (iCharAt == 1) {
                enumC0592al = EnumC0592al.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            enumC0592al = EnumC0592al.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int i3 = Integer.parseInt(str.substring(i, i2));
            String strSubstring = "";
            if (str.length() > i2) {
                if (str.charAt(i2) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                strSubstring = str.substring(i + 4);
            }
            return new C0515l(enumC0592al, i3, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f205a == EnumC0592al.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.f206b);
        if (this.f207c != null) {
            sb.append(' ');
            sb.append(this.f207c);
        }
        return sb.toString();
    }
}
