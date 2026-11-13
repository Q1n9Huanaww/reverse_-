package com.alibaba.sdk.android.oss.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class Owner implements Serializable {
    private static final long serialVersionUID = -1942759024112448066L;
    private String displayName;

    /* JADX INFO: renamed from: id */
    private String f49id;

    public Owner() {
        this(null, null);
    }

    public Owner(String str, String str2) {
        this.f49id = str;
        this.displayName = str2;
    }

    public String toString() {
        return "Owner [name=" + getDisplayName() + ",id=" + getId() + "]";
    }

    public String getId() {
        return this.f49id;
    }

    public void setId(String str) {
        this.f49id = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) obj;
        String id = owner.getId();
        String displayName = owner.getDisplayName();
        String id2 = getId();
        String displayName2 = getDisplayName();
        if (id == null) {
            id = "";
        }
        if (displayName == null) {
            displayName = "";
        }
        if (id2 == null) {
            id2 = "";
        }
        if (displayName2 == null) {
            displayName2 = "";
        }
        return id.equals(id2) && displayName.equals(displayName2);
    }

    public int hashCode() {
        if (this.f49id != null) {
            return this.f49id.hashCode();
        }
        return 0;
    }
}
