package com.p004b.p005a.p006a.p013g;

import com.p004b.p005a.p006a.C0503c;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.g.f */
/* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0571f implements InvocationHandler {

    /* JADX INFO: renamed from: a */
    boolean f429a;

    /* JADX INFO: renamed from: b */
    String f430b;

    /* JADX INFO: renamed from: c */
    private final List<String> f431c;

    public C0571f(List<String> list) {
        this.f431c = list;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (objArr == null) {
            objArr = C0503c.f170b;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return true;
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.f429a = true;
            return null;
        }
        if (name.equals("protocols") && objArr.length == 0) {
            return this.f431c;
        }
        if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
            List list = (List) objArr[0];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (this.f431c.contains(list.get(i))) {
                    String str = (String) list.get(i);
                    this.f430b = str;
                    return str;
                }
            }
            String str2 = this.f431c.get(0);
            this.f430b = str2;
            return str2;
        }
        if ((name.equals("protocolSelected") || name.equals("selected")) && objArr.length == 1) {
            this.f430b = (String) objArr[0];
            return null;
        }
        return method.invoke(this, objArr);
    }
}
