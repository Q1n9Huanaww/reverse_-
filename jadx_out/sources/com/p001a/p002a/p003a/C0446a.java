package com.p001a.p002a.p003a;

import android.support.annotation.NonNull;
import java.util.List;

/* JADX INFO: renamed from: com.a.a.a.a */
/* JADX INFO: compiled from: CommandResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0446a {

    /* JADX INFO: renamed from: a */
    @NonNull
    public final List<String> f33a;

    /* JADX INFO: renamed from: b */
    @NonNull
    public final List<String> f34b;

    /* JADX INFO: renamed from: c */
    public final int f35c;

    public C0446a(@NonNull List<String> list, @NonNull List<String> list2, int i) {
        this.f33a = list;
        this.f34b = list2;
        this.f35c = i;
    }

    /* JADX INFO: renamed from: a */
    public final String m11a() {
        List<String> list = this.f33a;
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            String str = "";
            for (String str2 : list) {
                sb.append(str);
                sb.append(str2);
                str = "\n";
            }
        }
        return sb.toString();
    }

    public final String toString() {
        return m11a();
    }
}
