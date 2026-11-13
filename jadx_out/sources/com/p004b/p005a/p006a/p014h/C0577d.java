package com.p004b.p005a.p006a.p014h;

import com.p004b.p005a.p006a.C0503c;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.apache.commons.p092io.FilenameUtils;
import org.slf4j.Marker;

/* JADX INFO: renamed from: com.b.a.a.h.d */
/* JADX INFO: compiled from: OkHostnameVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0577d implements HostnameVerifier {

    /* JADX INFO: renamed from: a */
    public static final C0577d f445a = new C0577d();

    private C0577d() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        String strM315a;
        try {
            X509Certificate x509Certificate = (X509Certificate) sSLSession.getPeerCertificates()[0];
            if (C0503c.m127c(str)) {
                List<String> listM317a = m317a(x509Certificate, 7);
                int size = listM317a.size();
                for (int i = 0; i < size; i++) {
                    if (str.equalsIgnoreCase(listM317a.get(i))) {
                        return true;
                    }
                }
                return false;
            }
            String lowerCase = str.toLowerCase(Locale.US);
            List<String> listM317a2 = m317a(x509Certificate, 2);
            int size2 = listM317a2.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size2) {
                if (m318a(lowerCase, listM317a2.get(i2))) {
                    return true;
                }
                i2++;
                z = true;
            }
            if (z || (strM315a = new C0576c(x509Certificate.getSubjectX500Principal()).m315a("cn")) == null) {
                return false;
            }
            return m318a(lowerCase, strM315a);
        } catch (SSLException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static List<String> m316a(X509Certificate x509Certificate) {
        List<String> listM317a = m317a(x509Certificate, 7);
        List<String> listM317a2 = m317a(x509Certificate, 2);
        ArrayList arrayList = new ArrayList(listM317a.size() + listM317a2.size());
        arrayList.addAll(listM317a);
        arrayList.addAll(listM317a2);
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private static List<String> m317a(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m318a(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(".") || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(".") || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(".")) {
            str = str + FilenameUtils.EXTENSION_SEPARATOR;
        }
        if (!str2.endsWith(".")) {
            str2 = str2 + FilenameUtils.EXTENSION_SEPARATOR;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains(Marker.ANY_MARKER)) {
            return str.equals(lowerCase);
        }
        if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
            return false;
        }
        String strSubstring = lowerCase.substring(1);
        if (!str.endsWith(strSubstring)) {
            return false;
        }
        int length = str.length() - strSubstring.length();
        return length <= 0 || str.lastIndexOf(46, length - 1) == -1;
    }
}
