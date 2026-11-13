package com.cyjh.mobileanjian.ipc.utils;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class RootUtil {

    /* JADX INFO: renamed from: a */
    private static final String f1416a = "ROOT_UTIL";

    public static boolean isRoot() {
        String str = System.getenv("PATH");
        new ArrayList();
        for (String str2 : str.split(":")) {
            File file = new File(str2, "su");
            if (file.exists() && file.canExecute()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1059a() throws Throwable {
        DataOutputStream dataOutputStream;
        Throwable th;
        Process processExec;
        DataOutputStream dataOutputStream2 = null;
        try {
            processExec = Runtime.getRuntime().exec("su");
            try {
                dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                try {
                    dataOutputStream.writeBytes("exit\n");
                    dataOutputStream.flush();
                    processExec.waitFor();
                    try {
                        dataOutputStream.close();
                        processExec.destroy();
                        return true;
                    } catch (Exception unused) {
                        return true;
                    }
                } catch (Exception unused2) {
                    dataOutputStream2 = dataOutputStream;
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (Exception unused3) {
                            return false;
                        }
                    }
                    processExec.destroy();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception unused4) {
                            throw th;
                        }
                    }
                    processExec.destroy();
                    throw th;
                }
            } catch (Exception unused5) {
            } catch (Throwable th3) {
                dataOutputStream = null;
                th = th3;
            }
        } catch (Exception unused6) {
            processExec = null;
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
            processExec = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Process] */
    /* JADX INFO: renamed from: a */
    private static ArrayList<String> m1058a(String str, ArrayList<String> arrayList) throws Throwable {
        ?? Exec;
        ?? r1;
        ArrayList<String> arrayList2 = new ArrayList<>();
        ?? r2 = 0;
        ?? r3 = 0;
        ?? r4 = 0;
        try {
            try {
                Exec = Runtime.getRuntime().exec(str);
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Exec.getOutputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Exec.getInputStream()));
                    Iterator<String> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        bufferedOutputStream.write((it2.next() + " 2>&1\n").getBytes());
                    }
                    bufferedOutputStream.write("exit\n".getBytes());
                    bufferedOutputStream.flush();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        arrayList2.add(line);
                    }
                    Exec.waitFor();
                    r2 = bufferedOutputStream;
                    if (Exec != 0) {
                        Exec.destroy();
                        r2 = bufferedOutputStream;
                    }
                } catch (IOException e) {
                    e = e;
                    r3 = Exec;
                    ThrowableExtension.printStackTrace(e);
                    r2 = r3;
                    r1 = r3;
                    if (r3 != 0) {
                        r1.destroy();
                        r2 = r1;
                    }
                } catch (InterruptedException e2) {
                    e = e2;
                    r4 = Exec;
                    ThrowableExtension.printStackTrace(e);
                    r2 = r4;
                    if (r4 != 0) {
                        r1 = r4;
                        r1.destroy();
                        r2 = r1;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (Exec != 0) {
                        Exec.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Exec = r2;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (InterruptedException e4) {
            e = e4;
        }
        return arrayList2;
    }
}
