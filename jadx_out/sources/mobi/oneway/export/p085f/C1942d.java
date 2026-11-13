package mobi.oneway.export.p085f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mobi.oneway.export.enums.AdType;

/* JADX INFO: renamed from: mobi.oneway.export.f.d */
/* JADX INFO: loaded from: classes.dex */
public class C1942d {

    /* JADX INFO: renamed from: a */
    private static List<C1941c> f4977a;

    /* JADX INFO: renamed from: b */
    private static List<C1945g> f4978b;

    /* JADX INFO: renamed from: c */
    private static String f4979c;

    /* JADX INFO: renamed from: a */
    public static String m4929a() {
        return f4979c;
    }

    /* JADX INFO: renamed from: a */
    public static List<C1931a> m4930a(String str, AdType adType) {
        List<C1931a> listM4932a;
        if (f4977a != null) {
            for (C1941c c1941c : f4977a) {
                if (str.equals(c1941c.m4911a())) {
                    listM4932a = m4932a(c1941c.m4914b(), str, adType);
                }
            }
            listM4932a = null;
        } else {
            listM4932a = null;
        }
        return listM4932a == null ? m4938b(str) : listM4932a;
    }

    /* JADX INFO: renamed from: a */
    public static List<String> m4931a(List<C1931a> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<C1931a> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().m4900f());
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private static List<C1931a> m4932a(List<C1941c.a> list, String str, AdType adType) {
        ArrayList arrayList = null;
        if (f4977a != null) {
            if (list == null) {
                return null;
            }
            for (C1941c.a aVar : list) {
                int iM4915a = aVar.m4915a();
                C1945g c1945gM4934a = m4934a(iM4915a);
                if (c1945gM4934a != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    C1931a c1931a = new C1931a(c1945gM4934a, str, iM4915a == 1 ? str : aVar.m4918b());
                    if (c1931a.m4898d(adType)) {
                        arrayList.add(c1931a);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    public static C1931a m4933a(List<C1931a> list, String str) {
        if (list == null) {
            return null;
        }
        Iterator<C1931a> it2 = list.iterator();
        if (!it2.hasNext()) {
            return null;
        }
        C1931a next = it2.next();
        if (next.m4900f().equals(str)) {
            return next;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static C1945g m4934a(int i) {
        if (f4978b == null) {
            return null;
        }
        for (C1945g c1945g : f4978b) {
            if (c1945g.m4947a() == i) {
                return c1945g;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static void m4935a(String str) {
        f4979c = str;
    }

    /* JADX INFO: renamed from: a */
    public static void m4936a(C1941c c1941c) {
        if (f4977a == null) {
            f4977a = new ArrayList();
        }
        f4977a.add(c1941c);
    }

    /* JADX INFO: renamed from: a */
    public static void m4937a(C1945g c1945g) {
        if (f4978b == null) {
            f4978b = new ArrayList();
        }
        f4978b.add(c1945g);
    }

    /* JADX INFO: renamed from: b */
    private static List<C1931a> m4938b(String str) {
        C1945g c1945gM4934a = m4934a(1);
        if (c1945gM4934a == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1931a(c1945gM4934a, str, str));
        return arrayList;
    }
}
