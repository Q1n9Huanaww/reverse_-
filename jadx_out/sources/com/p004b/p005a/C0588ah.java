package com.p004b.p005a;

import com.github.kevinsawicki.http.HttpRequest;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.b.a.ah */
/* JADX INFO: compiled from: MediaType.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0588ah {

    /* JADX INFO: renamed from: a */
    private static final Pattern f475a = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: b */
    private static final Pattern f476b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: c */
    private final String f477c;

    /* JADX INFO: renamed from: d */
    private final String f478d;

    /* JADX INFO: renamed from: e */
    private final String f479e;

    /* JADX INFO: renamed from: f */
    private final String f480f;

    private C0588ah(String str, String str2, String str3, String str4) {
        this.f477c = str;
        this.f478d = str2;
        this.f479e = str3;
        this.f480f = str4;
    }

    /* JADX INFO: renamed from: a */
    public static C0588ah m367a(String str) {
        Matcher matcher = f475a.matcher(str);
        if (!matcher.lookingAt()) {
            return null;
        }
        String lowerCase = matcher.group(1).toLowerCase(Locale.US);
        String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        Matcher matcher2 = f476b.matcher(str);
        String str2 = null;
        for (int iEnd = matcher.end(); iEnd < str.length(); iEnd = matcher2.end()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                return null;
            }
            String strGroup = matcher2.group(1);
            if (strGroup != null && strGroup.equalsIgnoreCase(HttpRequest.PARAM_CHARSET)) {
                String strGroup2 = matcher2.group(2);
                if (strGroup2 != null) {
                    if (strGroup2.startsWith("'") && strGroup2.endsWith("'") && strGroup2.length() > 2) {
                        strGroup2 = strGroup2.substring(1, strGroup2.length() - 1);
                    }
                } else {
                    strGroup2 = matcher2.group(3);
                }
                if (str2 != null && !strGroup2.equalsIgnoreCase(str2)) {
                    throw new IllegalArgumentException("Multiple different charsets: " + str);
                }
                str2 = strGroup2;
            }
        }
        return new C0588ah(str, lowerCase, lowerCase2, str2);
    }

    /* JADX INFO: renamed from: a */
    public final Charset m368a(Charset charset) {
        return this.f480f != null ? Charset.forName(this.f480f) : charset;
    }

    public final String toString() {
        return this.f477c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0588ah) && ((C0588ah) obj).f477c.equals(this.f477c);
    }

    public final int hashCode() {
        return this.f477c.hashCode();
    }
}
