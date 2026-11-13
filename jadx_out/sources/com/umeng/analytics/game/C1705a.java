package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.pro.C1725s;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.Serializable;

/* JADX INFO: renamed from: com.umeng.analytics.game.a */
/* JADX INFO: compiled from: GameState.java */
/* JADX INFO: loaded from: classes.dex */
public class C1705a {

    /* JADX INFO: renamed from: a */
    public String f3649a;

    /* JADX INFO: renamed from: b */
    public String f3650b;

    /* JADX INFO: renamed from: c */
    private Context f3651c;

    /* JADX INFO: renamed from: d */
    private final String f3652d = "um_g_cache";

    /* JADX INFO: renamed from: e */
    private final String f3653e = "single_level";

    /* JADX INFO: renamed from: f */
    private final String f3654f = "stat_player_level";

    /* JADX INFO: renamed from: g */
    private final String f3655g = "stat_game_level";

    /* JADX INFO: renamed from: h */
    private a f3656h = null;

    public C1705a(Context context) {
        this.f3651c = context;
    }

    /* JADX INFO: renamed from: a */
    public a m3508a(String str) {
        this.f3656h = new a(str);
        this.f3656h.m3512a();
        return this.f3656h;
    }

    /* JADX INFO: renamed from: a */
    public void m3509a() {
        try {
            if (this.f3656h != null) {
                this.f3656h.m3514b();
                SharedPreferences.Editor editorEdit = this.f3651c.getSharedPreferences("um_g_cache", 0).edit();
                editorEdit.putString("single_level", C1725s.m3689a(this.f3656h));
                editorEdit.putString("stat_player_level", this.f3650b);
                editorEdit.putString("stat_game_level", this.f3649a);
                editorEdit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3511b() {
        SharedPreferences sharedPreferences;
        try {
            SharedPreferences preferenceWrapper = PreferenceWrapper.getInstance(this.f3651c, "um_g_cache");
            String string = preferenceWrapper.getString("single_level", null);
            if (!TextUtils.isEmpty(string)) {
                this.f3656h = (a) C1725s.m3688a(string);
                if (this.f3656h != null) {
                    this.f3656h.m3515c();
                }
            }
            if (TextUtils.isEmpty(this.f3650b)) {
                this.f3650b = preferenceWrapper.getString("stat_player_level", null);
                if (this.f3650b == null && (sharedPreferences = PreferenceWrapper.getDefault(this.f3651c)) != null) {
                    this.f3650b = sharedPreferences.getString("userlevel", null);
                }
            }
            if (this.f3649a == null) {
                this.f3649a = preferenceWrapper.getString("stat_game_level", null);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public a m3510b(String str) {
        if (this.f3656h != null) {
            this.f3656h.m3516d();
            if (this.f3656h.m3513a(str)) {
                a aVar = this.f3656h;
                this.f3656h = null;
                return aVar;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: com.umeng.analytics.game.a$a */
    /* JADX INFO: compiled from: GameState.java */
    static class a implements Serializable {

        /* JADX INFO: renamed from: a */
        private static final long f3657a = 20140327;

        /* JADX INFO: renamed from: b */
        private String f3658b;

        /* JADX INFO: renamed from: c */
        private long f3659c;

        /* JADX INFO: renamed from: d */
        private long f3660d;

        public a(String str) {
            this.f3658b = str;
        }

        /* JADX INFO: renamed from: a */
        public boolean m3513a(String str) {
            return this.f3658b.equals(str);
        }

        /* JADX INFO: renamed from: a */
        public void m3512a() {
            this.f3660d = System.currentTimeMillis();
        }

        /* JADX INFO: renamed from: b */
        public void m3514b() {
            this.f3659c += System.currentTimeMillis() - this.f3660d;
            this.f3660d = 0L;
        }

        /* JADX INFO: renamed from: c */
        public void m3515c() {
            m3512a();
        }

        /* JADX INFO: renamed from: d */
        public void m3516d() {
            m3514b();
        }

        /* JADX INFO: renamed from: e */
        public long m3517e() {
            return this.f3659c;
        }

        /* JADX INFO: renamed from: f */
        public String m3518f() {
            return this.f3658b;
        }
    }
}
