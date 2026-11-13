package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.z */
/* JADX INFO: compiled from: TApplicationException.java */
/* JADX INFO: loaded from: classes.dex */
public class C1803z extends C1743ag {

    /* JADX INFO: renamed from: a */
    public static final int f4382a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f4383b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f4384c = 2;

    /* JADX INFO: renamed from: d */
    public static final int f4385d = 3;

    /* JADX INFO: renamed from: e */
    public static final int f4386e = 4;

    /* JADX INFO: renamed from: f */
    public static final int f4387f = 5;

    /* JADX INFO: renamed from: g */
    public static final int f4388g = 6;

    /* JADX INFO: renamed from: h */
    public static final int f4389h = 7;

    /* JADX INFO: renamed from: j */
    private static final C1768be f4390j = new C1768be("TApplicationException");

    /* JADX INFO: renamed from: k */
    private static final C1757au f4391k = new C1757au("message", (byte) 11, 1);

    /* JADX INFO: renamed from: l */
    private static final C1757au f4392l = new C1757au("type", (byte) 8, 2);

    /* JADX INFO: renamed from: m */
    private static final long f4393m = 1;

    /* JADX INFO: renamed from: i */
    protected int f4394i;

    public C1803z() {
        this.f4394i = 0;
    }

    public C1803z(int i) {
        this.f4394i = 0;
        this.f4394i = i;
    }

    public C1803z(int i, String str) {
        super(str);
        this.f4394i = 0;
        this.f4394i = i;
    }

    public C1803z(String str) {
        super(str);
        this.f4394i = 0;
    }

    /* JADX INFO: renamed from: a */
    public int m4209a() {
        return this.f4394i;
    }

    /* JADX INFO: renamed from: a */
    public static C1803z m4208a(AbstractC1762az abstractC1762az) throws C1743ag {
        abstractC1762az.mo3876j();
        String strMo3892z = null;
        int iMo3889w = 0;
        while (true) {
            C1757au c1757auMo3878l = abstractC1762az.mo3878l();
            if (c1757auMo3878l.f4083b != 0) {
                switch (c1757auMo3878l.f4084c) {
                    case 1:
                        if (c1757auMo3878l.f4083b == 11) {
                            strMo3892z = abstractC1762az.mo3892z();
                        } else {
                            C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                        }
                        break;
                    case 2:
                        if (c1757auMo3878l.f4083b == 8) {
                            iMo3889w = abstractC1762az.mo3889w();
                        } else {
                            C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                        }
                        break;
                    default:
                        C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                        break;
                }
                abstractC1762az.mo3879m();
            } else {
                abstractC1762az.mo3877k();
                return new C1803z(iMo3889w, strMo3892z);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public void m4210b(AbstractC1762az abstractC1762az) throws C1743ag {
        abstractC1762az.mo3860a(f4390j);
        if (getMessage() != null) {
            abstractC1762az.mo3855a(f4391k);
            abstractC1762az.mo3861a(getMessage());
            abstractC1762az.mo3867c();
        }
        abstractC1762az.mo3855a(f4392l);
        abstractC1762az.mo3853a(this.f4394i);
        abstractC1762az.mo3867c();
        abstractC1762az.mo3869d();
        abstractC1762az.mo3866b();
    }
}
