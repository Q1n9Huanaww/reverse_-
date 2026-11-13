package javax.mail.search;

/* JADX INFO: loaded from: classes.dex */
public abstract class ComparisonTerm extends SearchTerm {

    /* JADX INFO: renamed from: EQ */
    public static final int f4750EQ = 3;

    /* JADX INFO: renamed from: GE */
    public static final int f4751GE = 6;

    /* JADX INFO: renamed from: GT */
    public static final int f4752GT = 5;

    /* JADX INFO: renamed from: LE */
    public static final int f4753LE = 1;

    /* JADX INFO: renamed from: LT */
    public static final int f4754LT = 2;

    /* JADX INFO: renamed from: NE */
    public static final int f4755NE = 4;
    private static final long serialVersionUID = 1456646953666474308L;
    protected int comparison;

    public boolean equals(Object obj) {
        return (obj instanceof ComparisonTerm) && ((ComparisonTerm) obj).comparison == this.comparison;
    }

    public int hashCode() {
        return this.comparison;
    }
}
