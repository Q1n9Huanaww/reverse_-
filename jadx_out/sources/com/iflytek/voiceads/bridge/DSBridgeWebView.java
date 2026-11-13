package com.iflytek.voiceads.bridge;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class DSBridgeWebView extends WebView {

    /* JADX INFO: renamed from: c */
    private static boolean f3070c = false;

    /* JADX INFO: renamed from: a */
    Map<Integer, InterfaceC1513n> f3071a;

    /* JADX INFO: renamed from: b */
    public WebViewClient f3072b;

    /* JADX INFO: renamed from: d */
    private Map<String, Object> f3073d;

    /* JADX INFO: renamed from: e */
    private String f3074e;

    /* JADX INFO: renamed from: f */
    private int f3075f;

    /* JADX INFO: renamed from: g */
    private WebChromeClient f3076g;

    /* JADX INFO: renamed from: h */
    private InterfaceC1514o f3077h;

    /* JADX INFO: renamed from: i */
    private volatile boolean f3078i;

    /* JADX INFO: renamed from: j */
    private InterfaceC1499c f3079j;

    /* JADX INFO: renamed from: k */
    private ArrayList<C1497a> f3080k;

    /* JADX INFO: renamed from: l */
    private C1498b f3081l;

    /* JADX INFO: renamed from: m */
    private Handler f3082m;

    /* JADX INFO: renamed from: n */
    private WebChromeClient f3083n;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.iflytek.voiceads.bridge.DSBridgeWebView$a */
    static class C1497a {

        /* JADX INFO: renamed from: a */
        private String f3084a;

        /* JADX INFO: renamed from: b */
        private int f3085b;

        /* JADX INFO: renamed from: c */
        private String f3086c;

        C1497a(String str, int i, Object[] objArr) {
            this.f3084a = new JSONArray((Collection) Arrays.asList(objArr == null ? new Object[0] : objArr)).toString();
            this.f3085b = i;
            this.f3086c = str;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("method", this.f3086c);
                jSONObject.put("callbackId", this.f3085b);
                jSONObject.put("data", this.f3084a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.iflytek.voiceads.bridge.DSBridgeWebView$b */
    class C1498b {
        private C1498b() {
        }

        /* synthetic */ C1498b(DSBridgeWebView dSBridgeWebView, C1502c c1502c) {
            this();
        }

        /* JADX INFO: renamed from: a */
        private void m2876a(String str) {
            if (DSBridgeWebView.f3070c) {
                DSBridgeWebView.this.m2873a(String.format("alert('%s')", "DEBUG ERR MSG:\\n" + str.replaceAll("\\'", "\\\\'")));
            }
        }

        /* JADX INFO: renamed from: a */
        public String m2877a(String str, String str2) throws NoSuchMethodException {
            Method method;
            boolean z;
            String string;
            String[] strArrM2861b = DSBridgeWebView.this.m2861b(str.trim());
            String str3 = strArrM2861b[1];
            Object obj = DSBridgeWebView.this.f3073d.get(strArrM2861b[0]);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (obj == null) {
                m2876a("Js bridge  called, but can't find a corresponded JavascriptInterface object , please check your code!");
                return jSONObject.toString();
            }
            try {
                JSONObject jSONObject2 = new JSONObject(str2);
                String string2 = jSONObject2.has("_dscbstub") ? jSONObject2.getString("_dscbstub") : null;
                Object obj2 = jSONObject2.has("data") ? jSONObject2.get("data") : null;
                Class<?> cls = obj.getClass();
                try {
                    method = cls.getMethod(str3, Object.class, InterfaceC1500a.class);
                    z = true;
                } catch (Exception e2) {
                    try {
                        method = cls.getMethod(str3, Object.class);
                        z = false;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        method = null;
                        z = false;
                    }
                }
                if (method == null) {
                    m2876a("Not find method \"" + str3 + "\" implementation! please check if the  signature or namespace of the method is right ");
                    return jSONObject.toString();
                }
                if (Build.VERSION.SDK_INT >= 17 && ((JavascriptInterface) method.getAnnotation(JavascriptInterface.class)) == null) {
                    m2876a("Method " + str3 + " is not invoked, since  it is not declared with JavascriptInterface annotation! ");
                    return jSONObject.toString();
                }
                method.setAccessible(true);
                try {
                    if (z) {
                        method.invoke(obj, obj2, new C1512m(this, string2));
                        string = jSONObject.toString();
                    } else {
                        Object objInvoke = method.invoke(obj, obj2);
                        jSONObject.put("code", 0);
                        jSONObject.put("data", objInvoke);
                        string = jSONObject.toString();
                    }
                    return string;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    m2876a(String.format("Call failed：The parameter of \"%s\" in Java is invalid.", str3));
                    return jSONObject.toString();
                }
            } catch (JSONException e5) {
                m2876a(String.format("The argument of \"%s\" must be a JSON object string!", str3));
                e5.printStackTrace();
                return jSONObject.toString();
            }
        }
    }

    /* JADX INFO: renamed from: com.iflytek.voiceads.bridge.DSBridgeWebView$c */
    public interface InterfaceC1499c {
    }

    public DSBridgeWebView(Context context) {
        super(context);
        this.f3073d = new HashMap();
        this.f3075f = 0;
        this.f3078i = true;
        this.f3079j = null;
        this.f3081l = new C1498b(this, null);
        this.f3082m = new Handler(Looper.getMainLooper());
        this.f3071a = new HashMap();
        this.f3083n = new C1507h(this);
        this.f3072b = new C1511l(this);
        m2857b();
    }

    public DSBridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3073d = new HashMap();
        this.f3075f = 0;
        this.f3078i = true;
        this.f3079j = null;
        this.f3081l = new C1498b(this, null);
        this.f3082m = new Handler(Looper.getMainLooper());
        this.f3071a = new HashMap();
        this.f3083n = new C1507h(this);
        this.f3072b = new C1511l(this);
        m2857b();
    }

    /* JADX INFO: renamed from: a */
    private void m2851a(C1497a c1497a) {
        m2873a(String.format("window._handleMessageFromNative(%s)", c1497a.toString()));
    }

    /* JADX INFO: renamed from: a */
    private void m2854a(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.f3082m.post(runnable);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2857b() {
        this.f3074e = getContext().getFilesDir().getAbsolutePath() + "/webcache";
        WebSettings settings = getSettings();
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            settings.setMixedContentMode(0);
        }
        settings.setUserAgentString(settings.getUserAgentString() + " iflytek_webview");
        settings.setAllowFileAccess(false);
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCachePath(this.f3074e);
        settings.setUseWideViewPort(true);
        super.setWebChromeClient(this.f3083n);
        super.setWebViewClient(this.f3072b);
        m2863c();
        if (Build.VERSION.SDK_INT > 16) {
            super.addJavascriptInterface(this.f3081l, "_dsbridge");
        } else {
            settings.setUserAgentString(settings.getUserAgentString() + " _dsbridge");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public String[] m2861b(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        String strSubstring = "";
        if (iLastIndexOf != -1) {
            strSubstring = str.substring(0, iLastIndexOf);
            str = str.substring(iLastIndexOf + 1);
        }
        return new String[]{strSubstring, str};
    }

    /* JADX INFO: renamed from: c */
    private void m2863c() {
        m2872a(new C1502c(this), "_dsb");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public void m2865c(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.evaluateJavascript(str, null);
        } else {
            super.loadUrl("javascript:" + str);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2870a(InterfaceC1514o interfaceC1514o) {
        this.f3077h = interfaceC1514o;
    }

    /* JADX INFO: renamed from: a */
    public void m2871a(File file) {
        if (!file.exists()) {
            C1593g.m3190b(SDKConstants.TAG, "delete file no exists " + file.getAbsolutePath());
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m2871a(file2);
            }
        }
        file.delete();
    }

    /* JADX INFO: renamed from: a */
    public void m2872a(Object obj, String str) {
        if (str == null) {
            str = "";
        }
        if (obj != null) {
            this.f3073d.put(str, obj);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2873a(String str) {
        m2854a(new RunnableC1503d(this, str));
    }

    /* JADX INFO: renamed from: a */
    public synchronized <T> void m2874a(String str, Object[] objArr, InterfaceC1513n<T> interfaceC1513n) {
        int i = this.f3075f + 1;
        this.f3075f = i;
        C1497a c1497a = new C1497a(str, i, objArr);
        if (interfaceC1513n != null) {
            this.f3071a.put(Integer.valueOf(c1497a.f3085b), interfaceC1513n);
        }
        if (this.f3080k != null) {
            this.f3080k.add(c1497a);
        } else {
            m2851a(c1497a);
        }
    }

    @Override // android.webkit.WebView
    public void clearCache(boolean z) {
        super.clearCache(z);
        CookieManager.getInstance().removeAllCookie();
        Context context = getContext();
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(this.f3074e);
        File file2 = new File(context.getCacheDir().getAbsolutePath() + "/webviewCache");
        if (file2.exists()) {
            m2871a(file2);
        }
        if (file.exists()) {
            m2871a(file);
        }
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str) {
        m2854a(new RunnableC1504e(this, str));
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str, Map<String, String> map) {
        m2854a(new RunnableC1505f(this, str, map));
    }

    @Override // android.webkit.WebView
    public void reload() {
        m2854a(new RunnableC1506g(this));
    }

    @Override // android.webkit.WebView
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.f3076g = webChromeClient;
    }
}
