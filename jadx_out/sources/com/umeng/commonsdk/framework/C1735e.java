package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.common.C1817e;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.framework.e */
/* JADX INFO: compiled from: UMWorkDispatchImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class C1735e {

    /* JADX INFO: renamed from: a */
    private static HandlerThread f3989a = null;

    /* JADX INFO: renamed from: b */
    private static Handler f3990b = null;

    /* JADX INFO: renamed from: c */
    private static C1734d f3991c = null;

    /* JADX INFO: renamed from: d */
    private static final int f3992d = 768;

    /* JADX INFO: renamed from: e */
    private static final int f3993e = 769;

    /* JADX INFO: renamed from: f */
    private static final int f3994f = 770;

    /* JADX INFO: renamed from: a */
    public static void m3758a(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj) throws Throwable {
        if (context == null || uMLogDataProtocol == null) {
            C1817e.m4400b("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        C1733c.m3736a(context.getApplicationContext());
        if (C1733c.m3737a(i, uMLogDataProtocol)) {
            if (f3989a == null || f3990b == null) {
                m3764e();
            }
            if (f3990b != null) {
                if (f3991c == null) {
                    C1732b.m3726f(context);
                    f3991c = new C1734d(context, f3990b);
                }
                Message messageObtainMessage = f3990b.obtainMessage();
                messageObtainMessage.what = f3992d;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.obj = obj;
                f3990b.sendMessage(messageObtainMessage);
            }
        }
    }

    private C1735e() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d */
    public static void m3763d() throws Throwable {
        C1817e.m4400b("--->>> autoProcess Enter...");
        Context contextM3733a = C1733c.m3733a();
        if (contextM3733a != null) {
            long jMaxDataSpace = UMEnvelopeBuild.maxDataSpace(contextM3733a);
            UMLogDataProtocol uMLogDataProtocolM3734a = C1733c.m3734a("analytics");
            JSONObject jSONObject = null;
            int length = 0;
            try {
                if (UMEnvelopeBuild.isReadyBuild(contextM3733a, UMLogDataProtocol.UMBusinessType.U_DPLUS) && uMLogDataProtocolM3734a != null && (jSONObject = uMLogDataProtocolM3734a.setupReportData(jMaxDataSpace)) != null) {
                    length = jSONObject.toString().getBytes().length;
                }
                if (jSONObject != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("header", new JSONObject());
                        jSONObject2.put(C1708b.f3711W, new JSONObject());
                    } catch (Throwable th) {
                        C1782e.m3972a(contextM3733a, th);
                    }
                    if (jSONObject != null && length > 0) {
                        jSONObject2 = m3756a(m3756a(jSONObject2, jSONObject.optJSONObject("header"), "header"), jSONObject.optJSONObject(C1708b.f3711W), C1708b.f3711W);
                    }
                    if (jSONObject2 == null || UMEnvelopeBuild.buildEnvelopeWithExtHeader(contextM3733a, jSONObject2.optJSONObject("header"), jSONObject2.optJSONObject(C1708b.f3711W)) == null || jSONObject == null) {
                        return;
                    }
                    uMLogDataProtocolM3734a.removeCacheData(jSONObject);
                }
            } catch (Throwable th2) {
                C1782e.m3972a(contextM3733a, th2);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static JSONObject m3756a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        String str2;
        Context contextM3733a = C1733c.m3733a();
        if (jSONObject != null && jSONObject2 != null) {
            try {
                if (jSONObject.opt(str) != null && (jSONObject.opt(str) instanceof JSONObject)) {
                    JSONObject jSONObject3 = (JSONObject) jSONObject.opt(str);
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (next != null && (next instanceof String) && (str2 = next) != null && jSONObject2.opt(str2) != null) {
                            try {
                                jSONObject3.put(str2, jSONObject2.opt(str2));
                            } catch (Exception e) {
                                C1782e.m3972a(contextM3733a, e);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                C1782e.m3972a(contextM3733a, th);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: e */
    private static void m3764e() throws Throwable {
        C1817e.m4400b("--->>> Dispatch: init Enter...");
        if (f3989a == null) {
            f3989a = new HandlerThread("work_thread");
            f3989a.start();
            if (f3990b == null) {
                f3990b = new Handler(f3989a.getLooper()) { // from class: com.umeng.commonsdk.framework.e.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) throws Throwable {
                        switch (message.what) {
                            case C1735e.f3992d /* 768 */:
                                C1735e.m3761b(message);
                                break;
                            case C1735e.f3993e /* 769 */:
                                C1735e.m3763d();
                                break;
                            case C1735e.f3994f /* 770 */:
                                C1735e.m3766g();
                                break;
                        }
                    }
                };
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m3761b(Message message) throws Throwable {
        int i = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol uMLogDataProtocolM3734a = C1733c.m3734a(C1733c.m3735a(i));
        if (uMLogDataProtocolM3734a != null) {
            C1817e.m4400b("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i) + "]");
            uMLogDataProtocolM3734a.workEvent(obj, i);
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m3765f() {
        if (f3989a != null) {
            f3989a = null;
        }
        if (f3990b != null) {
            f3990b = null;
        }
        if (f3991c != null) {
            f3991c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g */
    public static void m3766g() throws Throwable {
        if (f3991c == null || f3989a == null) {
            return;
        }
        C1734d.m3740a();
        C1817e.m4400b("--->>> handleQuit: Quit dispatch thread.");
        f3989a.quit();
        m3765f();
    }

    /* JADX INFO: renamed from: a */
    public static void m3757a() {
        if (f3990b != null) {
            Message messageObtainMessage = f3990b.obtainMessage();
            messageObtainMessage.what = f3994f;
            f3990b.sendMessage(messageObtainMessage);
        }
    }
}
