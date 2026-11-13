package com.cyjh.mobileanjian.ipc.utils;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MiscUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0069 -> B:32:0x007e). Please report as a decompilation issue!!! */
    public static String sendGet(String str, String str2) throws Throwable {
        String string = "";
        ?? r1 = 0;
        StringBuilder sb = null;
        BufferedReader bufferedReader = null;
        r1 = 0;
        try {
            try {
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append("?");
                    if (str2 == null) {
                        str2 = "";
                    }
                    sb2.append(str2);
                    URLConnection uRLConnectionOpenConnection = new URL(sb2.toString()).openConnection();
                    uRLConnectionOpenConnection.setRequestProperty("accept", "*/*");
                    uRLConnectionOpenConnection.setRequestProperty("connection", "Keep-Alive");
                    uRLConnectionOpenConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                    uRLConnectionOpenConnection.connect();
                    uRLConnectionOpenConnection.getHeaderFields();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(uRLConnectionOpenConnection.getInputStream()));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(string);
                            sb3.append(line);
                            string = sb3.toString();
                            sb = sb3;
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            ThrowableExtension.printStackTrace(e);
                            r1 = bufferedReader;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                r1 = bufferedReader;
                            }
                        } catch (Throwable th) {
                            th = th;
                            r1 = bufferedReader2;
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (Exception e2) {
                                    ThrowableExtension.printStackTrace(e2);
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                    r1 = sb;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            ThrowableExtension.printStackTrace(e4);
            r1 = r1;
        }
        return string;
    }

    public static String parseId(String str) {
        try {
            return new JSONObject(FileUtils.file2Text(str, "GBK")).getString("id");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String parseScriptName(String str) {
        try {
            return new JSONObject(FileUtils.file2Text(str, "GBK")).getString("Name");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static boolean hasClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }
}
