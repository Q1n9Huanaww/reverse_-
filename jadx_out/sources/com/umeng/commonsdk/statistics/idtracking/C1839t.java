package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.t */
/* JADX INFO: compiled from: UUIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1839t extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4608a = "uuid";

    /* JADX INFO: renamed from: e */
    private static final String f4609e = "yosuid";

    /* JADX INFO: renamed from: f */
    private static final String f4610f = "23346339";

    /* JADX INFO: renamed from: b */
    private Context f4611b;

    /* JADX INFO: renamed from: c */
    private String f4612c;

    /* JADX INFO: renamed from: d */
    private String f4613d;

    public C1839t(Context context) {
        super("uuid");
        this.f4611b = null;
        this.f4612c = null;
        this.f4613d = null;
        this.f4611b = context;
        this.f4612c = null;
        this.f4613d = null;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editorEdit;
        try {
            if (TextUtils.isEmpty(m4489a("ro.yunos.version", "")) || this.f4611b == null || (sharedPreferences = PreferenceWrapper.getDefault(this.f4611b)) == null) {
                return null;
            }
            String string = sharedPreferences.getString(f4609e, "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            this.f4613d = m4490b(f4610f);
            if (!TextUtils.isEmpty(this.f4613d) && this.f4611b != null && sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null) {
                editorEdit.putString(f4609e, this.f4613d).commit();
            }
            return this.f4613d;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0185  */
    /* JADX WARN: Code duplicated, block: B:114:0x014c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:0x0167 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:126:0x0171 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:135:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:150:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x0160  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v22, types: [int] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v47 */
    /* JADX INFO: renamed from: b */
    private String m4490b(String str) throws Throwable {
        HttpsURLConnection httpsURLConnection;
        Throwable th;
        Exception e;
        ?? r0;
        BufferedReader bufferedReader;
        Exception e2;
        InputStream inputStream;
        this.f4613d = m4489a("ro.yunos.openuuid", "");
        if (!TextUtils.isEmpty(this.f4613d)) {
            return this.f4613d;
        }
        this.f4612c = m4489a("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(this.f4612c)) {
            this.f4612c = m4489a("ro.sys.aliyun.clouduuid", "");
        }
        if (!TextUtils.isEmpty(this.f4612c)) {
            DataOutputStream dataOutputStream = null;
            Object obj = null;
            ?? r3 = 0;
            dataOutputStream = null;
            dataOutputStream = null;
            dataOutputStream = null;
            try {
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL("https://cmnsguider.yunos.com:443/genDeviceToken").openConnection();
                try {
                    httpsURLConnection2.setConnectTimeout(30000);
                    httpsURLConnection2.setReadTimeout(30000);
                    httpsURLConnection2.setRequestMethod("POST");
                    httpsURLConnection2.setDoInput(true);
                    httpsURLConnection2.setDoOutput(true);
                    httpsURLConnection2.setUseCaches(false);
                    httpsURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpsURLConnection2.setHostnameVerifier(new HostnameVerifier() { // from class: com.umeng.commonsdk.statistics.idtracking.t.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str2, SSLSession sSLSession) {
                            return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sSLSession);
                        }
                    });
                    String str2 = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                    DataOutputStream dataOutputStream2 = new DataOutputStream(httpsURLConnection2.getOutputStream());
                    try {
                        try {
                            dataOutputStream2.writeBytes(str2);
                            dataOutputStream2.flush();
                            ?? responseCode = httpsURLConnection2.getResponseCode();
                            r3 = 200;
                            try {
                                if (responseCode == 200) {
                                    try {
                                        inputStream = httpsURLConnection2.getInputStream();
                                        try {
                                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                            try {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                while (true) {
                                                    String line = bufferedReader.readLine();
                                                    if (line == null) {
                                                        break;
                                                    }
                                                    stringBuffer.append(line);
                                                }
                                                if (stringBuffer != null) {
                                                    this.f4613d = stringBuffer.toString();
                                                }
                                            } catch (Exception e3) {
                                                e2 = e3;
                                                try {
                                                    ThrowableExtension.printStackTrace(e2);
                                                } catch (Exception e4) {
                                                    InputStream inputStream2 = inputStream;
                                                    e = e4;
                                                    dataOutputStream = dataOutputStream2;
                                                    httpsURLConnection = httpsURLConnection2;
                                                    r0 = inputStream2;
                                                    r3 = bufferedReader;
                                                    try {
                                                        ThrowableExtension.printStackTrace(e);
                                                        if (dataOutputStream != null) {
                                                            try {
                                                                dataOutputStream.close();
                                                            } catch (Exception e5) {
                                                                ThrowableExtension.printStackTrace(e5);
                                                            }
                                                        }
                                                        if (r3 != 0) {
                                                            try {
                                                                r3.close();
                                                            } catch (Exception e6) {
                                                                ThrowableExtension.printStackTrace(e6);
                                                            }
                                                        }
                                                        if (r0 != 0) {
                                                            try {
                                                                r0.close();
                                                            } catch (Exception e7) {
                                                                ThrowableExtension.printStackTrace(e7);
                                                            }
                                                        }
                                                        if (httpsURLConnection != null) {
                                                            httpsURLConnection.disconnect();
                                                        }
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        if (dataOutputStream != null) {
                                                            try {
                                                                dataOutputStream.close();
                                                            } catch (Exception e8) {
                                                                ThrowableExtension.printStackTrace(e8);
                                                            }
                                                        }
                                                        if (r3 != 0) {
                                                            try {
                                                                r3.close();
                                                            } catch (Exception e9) {
                                                                ThrowableExtension.printStackTrace(e9);
                                                            }
                                                        }
                                                        if (r0 != 0) {
                                                            try {
                                                                r0.close();
                                                            } catch (Exception e10) {
                                                                ThrowableExtension.printStackTrace(e10);
                                                            }
                                                        }
                                                        if (httpsURLConnection == null) {
                                                            throw th;
                                                        }
                                                        httpsURLConnection.disconnect();
                                                        throw th;
                                                    }
                                                }
                                            }
                                        } catch (Exception e11) {
                                            bufferedReader = null;
                                            e2 = e11;
                                        } catch (Throwable th3) {
                                            r3 = 0;
                                            dataOutputStream = dataOutputStream2;
                                            httpsURLConnection = httpsURLConnection2;
                                            r0 = inputStream;
                                            th = th3;
                                            if (dataOutputStream != null) {
                                                dataOutputStream.close();
                                            }
                                            if (r3 != 0) {
                                                r3.close();
                                            }
                                            if (r0 != 0) {
                                                r0.close();
                                            }
                                            if (httpsURLConnection == null) {
                                                throw th;
                                            }
                                            httpsURLConnection.disconnect();
                                            throw th;
                                        }
                                    } catch (Exception e12) {
                                        bufferedReader = null;
                                        e2 = e12;
                                        inputStream = null;
                                    }
                                } else {
                                    inputStream = null;
                                    bufferedReader = null;
                                }
                                if (dataOutputStream2 != null) {
                                    try {
                                        dataOutputStream2.close();
                                    } catch (Exception e13) {
                                        ThrowableExtension.printStackTrace(e13);
                                    }
                                }
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception e14) {
                                        ThrowableExtension.printStackTrace(e14);
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e15) {
                                        ThrowableExtension.printStackTrace(e15);
                                    }
                                }
                                if (httpsURLConnection2 != null) {
                                    httpsURLConnection2.disconnect();
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                dataOutputStream = dataOutputStream2;
                                httpsURLConnection = httpsURLConnection2;
                                r0 = responseCode;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            dataOutputStream = dataOutputStream2;
                            httpsURLConnection = httpsURLConnection2;
                            r0 = r3;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (r3 != 0) {
                                r3.close();
                            }
                            if (r0 != 0) {
                                r0.close();
                            }
                            if (httpsURLConnection == null) {
                                throw th;
                            }
                            httpsURLConnection.disconnect();
                            throw th;
                        }
                    } catch (Exception e16) {
                        e = e16;
                        dataOutputStream = dataOutputStream2;
                        httpsURLConnection = httpsURLConnection2;
                        r0 = obj;
                        r3 = obj;
                        ThrowableExtension.printStackTrace(e);
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (r3 != 0) {
                            r3.close();
                        }
                        if (r0 != 0) {
                            r0.close();
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        return this.f4613d;
                    }
                } catch (Exception e17) {
                    e = e17;
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Exception e18) {
                httpsURLConnection = null;
                obj = null;
                e = e18;
            } catch (Throwable th7) {
                httpsURLConnection = null;
                r3 = 0;
                th = th7;
            }
        }
        return this.f4613d;
    }

    /* JADX INFO: renamed from: a */
    public static String m4489a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return str2;
        }
    }
}
