package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.p006a.C0503c;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: renamed from: com.b.a.a.c.e */
/* JADX INFO: compiled from: HttpDate.java */
/* JADX INFO: loaded from: classes.dex */
final class C0508e extends ThreadLocal<DateFormat> {
    C0508e() {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ DateFormat initialValue() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(C0503c.f174f);
        return simpleDateFormat;
    }
}
