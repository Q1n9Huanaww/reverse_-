package com.p004b.p005a.p006a.p007a;

import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;

/* JADX INFO: renamed from: com.b.a.a.a.d */
/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0488d {

    /* JADX INFO: renamed from: a */
    public final C0595ao f88a;

    /* JADX INFO: renamed from: b */
    public final C0599as f89b;

    C0488d(C0595ao c0595ao, C0599as c0599as) {
        this.f88a = c0595ao;
        this.f89b = c0599as;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r3.m425g().m455d() == false) goto L21;
     */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m50a(C0599as c0599as, C0595ao c0595ao) {
        switch (c0599as.m420b()) {
            case 200:
            case SET_BOTTON_VALUE:
            case SET_RADIO_GROUP_VALUE:
            case 300:
            case SET_ENABLED_VALUE:
            case 308:
            case GET_TEXT_VALUE:
            case GET_BACKCOLOR_VALUE:
            case GET_FW_VISIBLE_VALUE:
            case 414:
            case SET_WINDOW_GRAVITY_VALUE:
                return c0599as.m425g().m453b() && !c0595ao.m404f().m453b();
            case SET_VISIBLE_VALUE:
            case 307:
                if (c0599as.m419a("Expires") == null) {
                    if (c0599as.m425g().m454c() == -1) {
                        if (!c0599as.m425g().m456e()) {
                        }
                    }
                }
                if (c0599as.m425g().m453b()) {
                }
                break;
            default:
                return false;
        }
    }
}
