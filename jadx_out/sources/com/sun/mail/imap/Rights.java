package com.sun.mail.imap;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Rights implements Cloneable {
    private boolean[] rights;

    public static final class Right {
        char right;
        private static Right[] cache = new Right[128];
        public static final Right LOOKUP = getInstance('l');
        public static final Right READ = getInstance('r');
        public static final Right KEEP_SEEN = getInstance('s');
        public static final Right WRITE = getInstance('w');
        public static final Right INSERT = getInstance('i');
        public static final Right POST = getInstance('p');
        public static final Right CREATE = getInstance('c');
        public static final Right DELETE = getInstance('d');
        public static final Right ADMINISTER = getInstance('a');

        private Right(char c) {
            if (c >= 128) {
                throw new IllegalArgumentException("Right must be ASCII");
            }
            this.right = c;
        }

        public static synchronized Right getInstance(char c) {
            try {
                if (c >= 128) {
                    throw new IllegalArgumentException("Right must be ASCII");
                }
                if (cache[c] == null) {
                    cache[c] = new Right(c);
                }
            } catch (Throwable th) {
                throw th;
            }
            return cache[c];
        }

        public final String toString() {
            return String.valueOf(this.right);
        }
    }

    public Rights() {
        this.rights = new boolean[128];
    }

    public Rights(Rights rights) {
        this.rights = new boolean[128];
        System.arraycopy(rights.rights, 0, this.rights, 0, this.rights.length);
    }

    public Rights(String str) {
        this.rights = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            add(Right.getInstance(str.charAt(i)));
        }
    }

    public Rights(Right right) {
        this.rights = new boolean[128];
        this.rights[right.right] = true;
    }

    public void add(Right right) {
        this.rights[right.right] = true;
    }

    public void add(Rights rights) {
        for (int i = 0; i < rights.rights.length; i++) {
            if (rights.rights[i]) {
                this.rights[i] = true;
            }
        }
    }

    public void remove(Right right) {
        this.rights[right.right] = false;
    }

    public void remove(Rights rights) {
        for (int i = 0; i < rights.rights.length; i++) {
            if (rights.rights[i]) {
                this.rights[i] = false;
            }
        }
    }

    public boolean contains(Right right) {
        return this.rights[right.right];
    }

    public boolean contains(Rights rights) {
        for (int i = 0; i < rights.rights.length; i++) {
            if (rights.rights[i] && !this.rights[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rights)) {
            return false;
        }
        Rights rights = (Rights) obj;
        for (int i = 0; i < rights.rights.length; i++) {
            if (rights.rights[i] != this.rights[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.rights.length; i2++) {
            if (this.rights[i2]) {
                i++;
            }
        }
        return i;
    }

    public Right[] getRights() {
        Vector vector = new Vector();
        for (int i = 0; i < this.rights.length; i++) {
            if (this.rights[i]) {
                vector.addElement(Right.getInstance((char) i));
            }
        }
        Right[] rightArr = new Right[vector.size()];
        vector.copyInto(rightArr);
        return rightArr;
    }

    public Object clone() {
        try {
            Rights rights = (Rights) super.clone();
            try {
                rights.rights = new boolean[128];
                System.arraycopy(this.rights, 0, rights.rights, 0, this.rights.length);
                return rights;
            } catch (CloneNotSupportedException unused) {
                return rights;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.rights.length; i++) {
            if (this.rights[i]) {
                stringBuffer.append((char) i);
            }
        }
        return stringBuffer.toString();
    }
}
