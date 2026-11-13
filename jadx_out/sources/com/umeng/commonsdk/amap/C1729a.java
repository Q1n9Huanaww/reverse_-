package com.umeng.commonsdk.amap;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.MLog;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.umeng.commonsdk.amap.a */
/* JADX INFO: compiled from: UMAmapLocation.java */
/* JADX INFO: loaded from: classes.dex */
public class C1729a {

    /* JADX INFO: renamed from: a */
    private Context f3938a;

    /* JADX INFO: renamed from: b */
    private Class<?> f3939b;

    /* JADX INFO: renamed from: c */
    private Object f3940c;

    /* JADX INFO: renamed from: a */
    public Class<?> m3705a() {
        return this.f3939b;
    }

    public C1729a(Context context) throws Throwable {
        C1817e.m4397a("ContentValues", "new UMAmapLocation");
        if (context == null) {
            MLog.m4337e("Context参数不能为null");
            return;
        }
        this.f3938a = context.getApplicationContext();
        try {
            this.f3939b = Class.forName("com.amap.api.netlocation.AMapNetworkLocationClient");
            if (this.f3939b != null) {
                this.f3940c = this.f3939b.getConstructor(Context.class).newInstance(this.f3938a);
            }
        } catch (Exception e) {
            C1817e.m4397a("ContentValues", "new UMAmapLocation e is " + e);
        }
    }

    /* JADX INFO: renamed from: b */
    public synchronized byte[] m3706b() {
        byte[] bArr;
        Method method;
        C1817e.m4397a("ContentValues", "UMAmapLocation getNetworkLocationParameter");
        bArr = null;
        try {
            if (this.f3939b != null && this.f3940c != null && (method = this.f3939b.getMethod("getNetworkLocationParameter", new Class[0])) != null) {
                bArr = (byte[]) method.invoke(this.f3940c, new Object[0]);
            }
        } catch (Exception e) {
            C1817e.m4397a("ContentValues", "getNetworkLocationParameter e is " + e);
        }
        return bArr;
    }

    /* JADX INFO: renamed from: c */
    public synchronized void m3707c() {
        Method method;
        C1817e.m4397a("ContentValues", "UMAmapLocation destory");
        try {
            if (this.f3939b != null && this.f3940c != null && (method = this.f3939b.getMethod("destroy", new Class[0])) != null) {
                method.invoke(this.f3940c, new Object[0]);
            }
        } catch (Exception e) {
            C1817e.m4397a("ContentValues", "UMAmapLocation destory e is " + e);
        }
    }
}
