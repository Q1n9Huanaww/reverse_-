package mobi.oneway.export.p087g;

import android.text.TextUtils;
import com.cyjh.share.util.AppUtils;
import com.umeng.commonsdk.amap.UMAmapConfig;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: renamed from: mobi.oneway.export.g.s */
/* JADX INFO: loaded from: classes.dex */
public class C1965s {
    /* JADX INFO: renamed from: a */
    public static String m5070a(int i) {
        String str;
        Object[] objArr;
        if (i > 1048576) {
            str = "%.1fM";
            objArr = new Object[]{Float.valueOf((float) ((((double) i) * 1.0d) / ((double) 1048576)))};
        } else {
            str = "%.1fK";
            objArr = new Object[]{Float.valueOf((float) ((((double) i) * 1.0d) / ((double) 1024)))};
        }
        return m5084b(str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static String m5071a(long j) {
        if (j > UMAmapConfig.AMAP_CACHE_WRITE_TIME) {
            return m5084b("%.1f 万", Double.valueOf(j / 10000.0d));
        }
        return String.valueOf(j) + " ";
    }

    /* JADX INFO: renamed from: a */
    public static String m5072a(InputStream inputStream) {
        return m5073a(inputStream, (String) null);
    }

    /* JADX INFO: renamed from: a */
    public static String m5073a(InputStream inputStream, String str) {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReaderM5091b = C1966t.m5091b(inputStream, str);
        char[] cArr = new char[4096];
        while (true) {
            int i = inputStreamReaderM5091b.read(cArr);
            if (i == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, i);
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m5074a(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /* JADX INFO: renamed from: a */
    public static String m5075a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(".")) {
            return str;
        }
        String[] strArrSplit = str.split("\\.");
        return strArrSplit[strArrSplit.length - 1];
    }

    /* JADX INFO: renamed from: a */
    public static String m5076a(String str, int i) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("[\\r\\n]+");
        if (i >= strArrSplit.length) {
            return null;
        }
        return strArrSplit[i];
    }

    /* JADX INFO: renamed from: a */
    public static String m5077a(String str, String str2, String str3, Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        for (int i = 0; i < objArr.length - 1; i++) {
            sb.append(objArr[i]);
            if (str3 != null) {
                sb.append(str3);
            }
        }
        sb.append(objArr[objArr.length - 1]);
        if (str2 != null) {
            sb.append(str2);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    public static String m5078a(String str, List list) {
        if (list == null) {
            return null;
        }
        Object[] objArr = new Object[list.size()];
        list.toArray(objArr);
        return m5079a(str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static String m5079a(String str, Object... objArr) {
        return m5077a(null, null, str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static String m5080a(Date date) {
        return new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(date);
    }

    /* JADX INFO: renamed from: a */
    public static String m5081a(Object... objArr) {
        return m5077a(null, null, null, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5082a(String str, String str2) {
        return str != null && str.equals(str2);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m5083a(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (!str.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: b */
    public static String m5084b(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    /* JADX INFO: renamed from: b */
    public static String m5085b(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(date);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m5086b(String str, String... strArr) {
        if (str == null || strArr.length == 0) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: c */
    public static boolean m5087c(String str, String... strArr) {
        if (str == null || strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }
}
