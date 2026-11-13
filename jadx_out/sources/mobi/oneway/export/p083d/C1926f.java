package mobi.oneway.export.p083d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import mobi.oneway.export.p087g.C1954h;
import mobi.oneway.export.p087g.C1956j;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.d.f */
/* JADX INFO: loaded from: classes.dex */
public class C1926f {

    /* JADX INFO: renamed from: a */
    public static final String f4897a = "POST";

    /* JADX INFO: renamed from: b */
    public static final String f4898b = "HEAD";

    /* JADX INFO: renamed from: c */
    public static final String f4899c = "Content-Type";

    /* JADX INFO: renamed from: d */
    public static final String f4900d = "application/json";

    /* JADX INFO: renamed from: e */
    private static final int f4901e = 15000;

    /* JADX INFO: renamed from: f */
    private String f4902f;

    /* JADX INFO: renamed from: g */
    private String f4903g;

    /* JADX INFO: renamed from: h */
    private String f4904h;

    /* JADX INFO: renamed from: i */
    private final Map<String, String> f4905i;

    /* JADX INFO: renamed from: j */
    private final Map<String, String> f4906j;

    /* JADX INFO: renamed from: k */
    private int f4907k;

    /* JADX INFO: renamed from: l */
    private int f4908l;

    /* JADX INFO: renamed from: m */
    private boolean f4909m;

    /* JADX INFO: renamed from: n */
    private File f4910n;

    /* JADX INFO: renamed from: o */
    private boolean f4911o;

    /* JADX INFO: renamed from: p */
    private volatile boolean f4912p;

    /* JADX INFO: renamed from: q */
    private boolean f4913q;

    /* JADX INFO: renamed from: r */
    private long f4914r;

    /* JADX INFO: renamed from: s */
    private long f4915s;

    /* JADX INFO: renamed from: t */
    private boolean f4916t;

    /* JADX INFO: renamed from: u */
    private String[] f4917u;

    /* JADX INFO: renamed from: v */
    private String f4918v;

    /* JADX INFO: renamed from: w */
    private int f4919w;

    /* JADX INFO: renamed from: mobi.oneway.export.d.f$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void m4849a(int i, int i2, C1922b c1922b);

        /* JADX INFO: renamed from: b */
        void m4850b(int i, int i2, C1922b c1922b);
    }

    /* JADX INFO: renamed from: mobi.oneway.export.d.f$b */
    public interface b {
        /* JADX INFO: renamed from: a */
        void mo4783a(Throwable th, C1922b c1922b);

        /* JADX INFO: renamed from: a */
        void mo4784a(C1922b c1922b);
    }

    /* JADX INFO: renamed from: mobi.oneway.export.d.f$c */
    private static class c extends Handler {

        /* JADX INFO: renamed from: a */
        public static final int f4920a = 0;

        /* JADX INFO: renamed from: b */
        public static final int f4921b = 1;

        /* JADX INFO: renamed from: c */
        private b f4922c;

        public c(b bVar) {
            super(Looper.getMainLooper());
            this.f4922c = bVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                this.f4922c.mo4784a((C1922b) message.obj);
            } else {
                C1922b c1922b = (C1922b) message.obj;
                this.f4922c.mo4783a(c1922b.m4805m(), c1922b);
            }
        }
    }

    public C1926f(String str) {
        this.f4905i = new HashMap();
        this.f4906j = new HashMap();
        this.f4907k = 15000;
        this.f4908l = 15000;
        this.f4909m = false;
        this.f4911o = false;
        this.f4912p = false;
        this.f4913q = false;
        this.f4916t = false;
        m4833b(str);
        this.f4914r = System.currentTimeMillis();
    }

    public C1926f(String[] strArr, String str) {
        this.f4905i = new HashMap();
        this.f4906j = new HashMap();
        this.f4907k = 15000;
        this.f4908l = 15000;
        this.f4909m = false;
        this.f4911o = false;
        this.f4912p = false;
        this.f4913q = false;
        this.f4916t = false;
        this.f4917u = strArr;
        this.f4918v = str;
        this.f4919w = 0;
        this.f4914r = System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: a */
    private int m4809a(HttpURLConnection httpURLConnection, OutputStream outputStream, C1922b c1922b) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        int i;
        Thread threadCurrentThread = Thread.currentThread();
        try {
            httpURLConnection.getContentLength();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getResponseCode() == 200 ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream());
                try {
                    byte[] bArr = new byte[1024];
                    int i2 = 0;
                    while (!m4839e() && !threadCurrentThread.isInterrupted() && (i = bufferedInputStream.read(bArr)) != -1) {
                        bufferedOutputStream.write(bArr, 0, i);
                        i2 += i;
                        bufferedOutputStream.flush();
                    }
                    bufferedOutputStream.flush();
                    if (!m4839e() && threadCurrentThread.isInterrupted()) {
                        m4838d();
                    }
                    C1956j.m5037a(outputStream, bufferedInputStream, bufferedOutputStream);
                    return i2;
                } catch (Throwable th) {
                    th = th;
                    C1956j.m5037a(outputStream, bufferedInputStream, bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            bufferedInputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m4810a(Map<String, String> map, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (String strM4813d : map.keySet()) {
            String strM4813d2 = map.get(strM4813d);
            if (strM4813d2 != null) {
                if (z) {
                    strM4813d = m4813d(strM4813d);
                    strM4813d2 = m4813d(strM4813d2);
                }
                sb.append(strM4813d);
                sb.append('=');
                sb.append(strM4813d2);
                sb.append('&');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private void m4811a(HttpURLConnection httpURLConnection) throws Throwable {
        PrintWriter printWriter;
        String strM4842h;
        if (!"POST".equals(m4841g())) {
            return;
        }
        try {
            httpURLConnection.setDoOutput(true);
            printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            try {
                if (TextUtils.isEmpty(this.f4904h)) {
                    if (!TextUtils.isEmpty(m4842h())) {
                        strM4842h = m4842h();
                    }
                    printWriter.flush();
                    C1956j.m5037a(printWriter);
                }
                strM4842h = this.f4904h;
                printWriter.write(strM4842h);
                printWriter.flush();
                C1956j.m5037a(printWriter);
            } catch (Throwable th) {
                th = th;
                C1956j.m5037a(printWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            printWriter = null;
        }
    }

    /* JADX INFO: renamed from: c */
    private boolean m4812c(int i) {
        return i >= 200 && i < 300;
    }

    /* JADX INFO: renamed from: d */
    private static String m4813d(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    /* JADX INFO: renamed from: o */
    private boolean m4814o() {
        return this.f4910n != null;
    }

    /* JADX INFO: renamed from: p */
    private boolean m4815p() {
        return this.f4917u != null && this.f4917u.length > 0 && this.f4919w < this.f4917u.length - 1;
    }

    /* JADX INFO: renamed from: q */
    private HttpURLConnection m4816q() throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(m4840f()).openConnection();
        httpURLConnection.setInstanceFollowRedirects(this.f4909m);
        httpURLConnection.setConnectTimeout(m4843i());
        httpURLConnection.setReadTimeout(m4844j());
        httpURLConnection.setRequestMethod(m4841g());
        httpURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "");
        if (!this.f4906j.isEmpty()) {
            for (String str : this.f4906j.keySet()) {
                httpURLConnection.setRequestProperty(str, this.f4906j.get(str));
            }
        }
        if (URLUtil.isHttpsUrl(this.f4902f)) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setHostnameVerifier(C1924d.m4808b());
            httpsURLConnection.setSSLSocketFactory(C1924d.m4807a());
        }
        return httpURLConnection;
    }

    /* JADX INFO: renamed from: r */
    private void m4817r() {
        if (this.f4903g == null) {
            this.f4903g = "GET";
        }
        this.f4903g = this.f4903g.toUpperCase();
        if (m4844j() <= 0) {
            throw new IllegalArgumentException("read timeout=" + m4844j() + " is <= 0.");
        }
        if (this.f4907k <= 0) {
            throw new IllegalArgumentException("connect timeout=" + m4843i() + " is <= 0.");
        }
        if (this.f4917u != null && this.f4917u.length > 0 && !TextUtils.isEmpty(this.f4918v)) {
            this.f4902f = this.f4917u[this.f4919w] + this.f4918v;
        }
        if (this.f4905i.isEmpty()) {
            return;
        }
        String strM4810a = m4810a(this.f4905i, true);
        if ("POST".equals(this.f4903g) && TextUtils.isEmpty(this.f4904h)) {
            this.f4904h = strM4810a;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f4902f);
        sb.append(this.f4902f.indexOf("?") > 0 ? "&" : "?");
        this.f4902f = sb.toString();
        this.f4902f += strM4810a;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4818a(int i) {
        this.f4907k = i;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4819a(int i, int i2) {
        m4818a(i);
        m4830b(i2);
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4820a(File file) {
        return m4821a(file, false);
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4821a(File file, boolean z) {
        this.f4910n = file;
        this.f4911o = z;
        if (z) {
            m4824a(HttpHeaders.RANGE, "bytes=" + file.length() + "-");
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4822a(String str) {
        this.f4904h = str;
        m4824a("Content-Type", "application/json");
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4823a(String str, Object obj) {
        if (obj == null) {
            return this;
        }
        this.f4905i.put(str, String.valueOf(obj));
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4824a(String str, String str2) {
        this.f4906j.put(str, str2);
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4825a(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            this.f4906j.putAll(map);
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4826a(JSONArray jSONArray) {
        this.f4904h = jSONArray.toString();
        m4824a("Content-Type", "application/json");
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4827a(JSONObject jSONObject) {
        this.f4904h = jSONObject.toString();
        m4824a("Content-Type", "application/json");
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1926f m4828a(boolean z) {
        this.f4913q = z;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public boolean m4829a() {
        return this.f4913q;
    }

    /* JADX INFO: renamed from: b */
    public C1926f m4830b(int i) {
        this.f4908l = i;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public C1926f m4831b(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            this.f4905i.putAll(map);
        }
        return this;
    }

    /* JADX INFO: renamed from: b */
    public C1926f m4832b(boolean z) {
        this.f4916t = z;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public void m4833b(String str) {
        this.f4902f = str;
    }

    /* JADX INFO: renamed from: b */
    public boolean m4834b() {
        return this.f4916t;
    }

    /* JADX INFO: renamed from: c */
    public Map<String, String> m4835c() {
        return this.f4905i;
    }

    /* JADX INFO: renamed from: c */
    public C1926f m4836c(String str) {
        this.f4903g = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public C1926f m4837c(boolean z) {
        this.f4909m = z;
        return this;
    }

    /* JADX INFO: renamed from: d */
    public void m4838d() {
        this.f4912p = true;
    }

    /* JADX INFO: renamed from: e */
    public boolean m4839e() {
        return this.f4912p;
    }

    /* JADX INFO: renamed from: f */
    public String m4840f() {
        return this.f4902f;
    }

    /* JADX INFO: renamed from: g */
    public String m4841g() {
        return this.f4903g;
    }

    /* JADX INFO: renamed from: h */
    public String m4842h() {
        return new URL(this.f4902f).getQuery();
    }

    /* JADX INFO: renamed from: i */
    public int m4843i() {
        return this.f4907k;
    }

    /* JADX INFO: renamed from: j */
    public int m4844j() {
        return this.f4908l;
    }

    /* JADX INFO: renamed from: k */
    public String m4845k() {
        return m4848n().m4796d();
    }

    /* JADX INFO: renamed from: l */
    public byte[] m4846l() {
        return m4848n().m4795c();
    }

    /* JADX INFO: renamed from: m */
    public JSONObject m4847m() {
        return new JSONObject(m4845k());
    }

    /* JADX INFO: renamed from: n */
    public C1922b m4848n() throws Throwable {
        HttpURLConnection httpURLConnectionM4816q;
        this.f4915s = System.currentTimeMillis();
        C1922b c1922b = new C1922b(this);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                m4817r();
                httpURLConnectionM4816q = m4816q();
                try {
                    m4811a(httpURLConnectionM4816q);
                    int responseCode = httpURLConnectionM4816q.getResponseCode();
                    c1922b.m4788a(responseCode);
                    c1922b.m4791a(httpURLConnectionM4816q.getHeaderFields());
                    int contentLength = httpURLConnectionM4816q.getContentLength();
                    c1922b.m4789a(contentLength);
                    if (!this.f4913q) {
                        if (m4814o()) {
                            c1922b.m4790a(this.f4910n);
                            C1954h.m5015c(this.f4910n.getParent(), (String) null);
                            m4809a(httpURLConnectionM4816q, new FileOutputStream(this.f4910n, this.f4911o), c1922b);
                        } else {
                            if (contentLength > 2097152) {
                                throw new IOException("Body is too large to load in memory: contentLength = " + contentLength + " B");
                            }
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            m4809a(httpURLConnectionM4816q, byteArrayOutputStream, c1922b);
                            c1922b.m4793a(byteArrayOutputStream.toByteArray());
                        }
                    }
                    if (!m4815p() || m4812c(responseCode)) {
                        C1956j.m5036a(httpURLConnectionM4816q);
                        return c1922b;
                    }
                    this.f4919w++;
                    C1922b c1922bM4848n = m4848n();
                    C1956j.m5036a(httpURLConnectionM4816q);
                    return c1922bM4848n;
                } catch (IOException e) {
                    e = e;
                    httpURLConnection = httpURLConnectionM4816q;
                    if (!m4815p()) {
                        throw e;
                    }
                    this.f4919w++;
                    C1922b c1922bM4848n2 = m4848n();
                    C1956j.m5036a(httpURLConnection);
                    return c1922bM4848n2;
                } catch (Throwable th) {
                    th = th;
                    C1956j.m5036a(httpURLConnectionM4816q);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnectionM4816q = null;
        }
    }
}
