package com.google.p028a.p038d.p041c;

import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.helper.ItemTouchHelper;

/* JADX INFO: renamed from: com.google.a.d.c.b */
/* JADX INFO: compiled from: Base256Encoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1220b implements InterfaceC1225g {
    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final int mo1923a() {
        return 5;
    }

    C1220b() {
    }

    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final void mo1924a(C1226h c1226h) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (c1226h.m1966b()) {
            sb.append(c1226h.m1962a());
            c1226h.f2226f++;
            if (C1228j.m1974a(c1226h.f2221a, c1226h.f2226f, 5) != 5) {
                c1226h.f2227g = 0;
                break;
            }
        }
        int length = sb.length() - 1;
        int length2 = c1226h.f2225e.length() + length + 1;
        c1226h.m1964a(length2);
        boolean z = c1226h.f2228h.f2257b - length2 > 0;
        if (c1226h.m1966b() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                sb.setCharAt(0, (char) ((length / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249));
                sb.insert(1, (char) (length % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: ".concat(String.valueOf(length)));
            }
        }
        int length3 = sb.length();
        for (int i = 0; i < length3; i++) {
            int iCharAt = sb.charAt(i) + (((c1226h.f2225e.length() + 1) * 149) % 255) + 1;
            if (iCharAt > 255) {
                iCharAt += InputDeviceCompat.SOURCE_ANY;
            }
            c1226h.m1963a((char) iCharAt);
        }
    }

    /* JADX INFO: renamed from: a */
    private static char m1925a(char c, int i) {
        int i2 = c + ((i * 149) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }
}
