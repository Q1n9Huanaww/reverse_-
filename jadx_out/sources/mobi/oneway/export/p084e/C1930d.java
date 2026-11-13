package mobi.oneway.export.p084e;

/* JADX INFO: renamed from: mobi.oneway.export.e.d */
/* JADX INFO: loaded from: classes.dex */
public class C1930d extends Thread {

    /* JADX INFO: renamed from: a */
    private a f4938a;

    /* JADX INFO: renamed from: b */
    private long f4939b;

    /* JADX INFO: renamed from: c */
    private long f4940c;

    /* JADX INFO: renamed from: d */
    private boolean f4941d = false;

    /* JADX INFO: renamed from: mobi.oneway.export.e.d$a */
    public interface a {
        void timeout();
    }

    public C1930d(a aVar, long j) {
        this.f4939b = 5000L;
        this.f4938a = aVar;
        if (j != 0) {
            this.f4939b = j;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        this.f4940c = System.currentTimeMillis();
        while (!C1928b.m4863a().m4871d() && !this.f4941d) {
            if (System.currentTimeMillis() - this.f4940c > this.f4939b) {
                this.f4941d = true;
                if (this.f4938a != null) {
                    C1928b.m4863a().m4870c(new Runnable() { // from class: mobi.oneway.export.e.d.1
                        @Override // java.lang.Runnable
                        public void run() {
                            C1930d.this.f4938a.timeout();
                        }
                    });
                }
            }
        }
    }
}
