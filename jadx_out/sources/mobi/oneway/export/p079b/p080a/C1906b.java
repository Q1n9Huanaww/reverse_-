package mobi.oneway.export.p079b.p080a;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import mobi.oneway.export.AdListener.feed.OWFeedAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p079b.p081b.C1910c;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1942d;

/* JADX INFO: renamed from: mobi.oneway.export.b.a.b */
/* JADX INFO: loaded from: classes.dex */
public class C1906b {

    /* JADX INFO: renamed from: a */
    private final AdType f4856a = AdType.feed;

    /* JADX INFO: renamed from: b */
    private String f4857b;

    /* JADX INFO: renamed from: c */
    private Context f4858c;

    public C1906b(Context context, String str) {
        this.f4857b = str;
        this.f4858c = context;
    }

    /* JADX INFO: renamed from: a */
    public void m4748a(OWFeedAdListener oWFeedAdListener) {
        List<C1931a> listM4930a = C1942d.m4930a(this.f4857b, this.f4856a);
        if (listM4930a == null) {
            if (oWFeedAdListener != null) {
                oWFeedAdListener.onError(OnewaySdkError.INITIALIZE_FAILED, this.f4856a.name() + C1899a.f4830m);
                return;
            }
            return;
        }
        C1910c c1910c = new C1910c(this.f4856a, listM4930a, C1942d.m4931a(listM4930a), C1904a.m4727a().m4739c());
        c1910c.m4766a(oWFeedAdListener);
        c1910c.mo4770c();
        Iterator<C1931a> it2 = listM4930a.iterator();
        while (it2.hasNext()) {
            it2.next().m4885a(this.f4858c, c1910c);
        }
    }
}
