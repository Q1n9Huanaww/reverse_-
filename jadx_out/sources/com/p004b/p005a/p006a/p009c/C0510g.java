package com.p004b.p005a.p006a.p009c;

import com.android.volley.toolbox.HttpClientStack;
import com.github.kevinsawicki.http.HttpRequest;

/* JADX INFO: renamed from: com.b.a.a.c.g */
/* JADX INFO: compiled from: HttpMethod.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0510g {
    /* JADX INFO: renamed from: a */
    public static boolean m144a(String str) {
        return str.equals("POST") || str.equals(HttpRequest.METHOD_PUT) || str.equals(HttpClientStack.HttpPatch.METHOD_NAME) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    /* JADX INFO: renamed from: b */
    public static boolean m145b(String str) {
        return m144a(str) || str.equals(HttpRequest.METHOD_OPTIONS) || str.equals(HttpRequest.METHOD_DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }
}
