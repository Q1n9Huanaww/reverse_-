package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.m */
/* JADX INFO: compiled from: GeoParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1175m extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final double f2028a;

    /* JADX INFO: renamed from: b */
    private final double f2029b;

    /* JADX INFO: renamed from: c */
    private final double f2030c;

    /* JADX INFO: renamed from: d */
    private final String f2031d;

    C1175m(double d, double d2, double d3, String str) {
        super(C1180r.f2040f);
        this.f2028a = d;
        this.f2029b = d2;
        this.f2030c = d3;
        this.f2031d = str;
    }

    /* JADX INFO: renamed from: b */
    private String m1691b() {
        StringBuilder sb = new StringBuilder();
        sb.append("geo:");
        sb.append(this.f2028a);
        sb.append(',');
        sb.append(this.f2029b);
        if (this.f2030c > 0.0d) {
            sb.append(',');
            sb.append(this.f2030c);
        }
        if (this.f2031d != null) {
            sb.append('?');
            sb.append(this.f2031d);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: c */
    private double m1692c() {
        return this.f2028a;
    }

    /* JADX INFO: renamed from: d */
    private double m1693d() {
        return this.f2029b;
    }

    /* JADX INFO: renamed from: e */
    private double m1694e() {
        return this.f2030c;
    }

    /* JADX INFO: renamed from: f */
    private String m1695f() {
        return this.f2031d;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.f2028a);
        sb.append(", ");
        sb.append(this.f2029b);
        if (this.f2030c > 0.0d) {
            sb.append(", ");
            sb.append(this.f2030c);
            sb.append('m');
        }
        if (this.f2031d != null) {
            sb.append(" (");
            sb.append(this.f2031d);
            sb.append(')');
        }
        return sb.toString();
    }
}
