package mobi.oneway.export.p087g;

import android.content.SharedPreferences;
import java.util.Map;
import mobi.oneway.export.p078a.C1900b;

/* JADX INFO: renamed from: mobi.oneway.export.g.r */
/* JADX INFO: loaded from: classes.dex */
public class C1964r {

    /* JADX INFO: renamed from: a */
    private static final String f5031a = "OneWay_sp";

    /* JADX INFO: renamed from: b */
    private String f5032b;

    public C1964r(String str) {
        this.f5032b = str;
    }

    /* JADX INFO: renamed from: a */
    public static C1964r m5062a() {
        return m5063a(f5031a);
    }

    /* JADX INFO: renamed from: a */
    public static C1964r m5063a(String str) {
        return new C1964r(str);
    }

    /* JADX INFO: renamed from: d */
    private SharedPreferences m5064d() {
        return C1900b.m4706a().getSharedPreferences(this.f5032b, 0);
    }

    /* JADX INFO: renamed from: a */
    public C1964r m5065a(String str, Object obj) {
        SharedPreferences.Editor editorEdit = m5064d().edit();
        if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else {
            editorEdit.putString(str, String.valueOf(obj));
        }
        editorEdit.apply();
        return this;
    }

    /* JADX INFO: renamed from: b */
    public Object m5066b(String str, Object obj) {
        SharedPreferences sharedPreferencesM5064d = m5064d();
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferencesM5064d.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferencesM5064d.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferencesM5064d.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferencesM5064d.getLong(str, ((Long) obj).longValue()));
        }
        if (obj instanceof String) {
            return sharedPreferencesM5064d.getString(str, (String) obj);
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    public Map<String, ?> m5067b() {
        return m5064d().getAll();
    }

    /* JADX INFO: renamed from: b */
    public C1964r m5068b(String str) {
        m5064d().edit().remove(str).apply();
        return this;
    }

    /* JADX INFO: renamed from: c */
    public C1964r m5069c() {
        m5064d().edit().clear().apply();
        return this;
    }
}
