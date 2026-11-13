package javax.mail;

/* JADX INFO: loaded from: classes.dex */
public abstract class BodyPart implements Part {
    protected Multipart parent;

    public Multipart getParent() {
        return this.parent;
    }

    void setParent(Multipart multipart) {
        this.parent = multipart;
    }
}
