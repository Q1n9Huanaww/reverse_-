package com.cyjh.feedback.lib.utils;

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
    private static final String TAG = "ROOT_UTIL";

    public static boolean isRoot() throws Throwable {
        String str = System.getenv("PATH");
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(":")) {
            String str3 = str2 + File.separator + "su";
            if (new File(str3).exists()) {
                arrayList.add("ls -l " + str3);
            }
        }
        ArrayList<String> arrayListRun = run("/system/bin/sh", arrayList);
        String str4 = "";
        for (int i = 0; i < arrayListRun.size(); i++) {
            str4 = str4 + arrayListRun.get(i);
        }
        if (str4.contains("-rws")) {
            return true;
        }
        return execSuCmd();
    }

    private static boolean execSuCmd() throws Throwable {
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
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception unused) {
                            return true;
                        }
                    }
                    processExec.destroy();
                    return true;
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

    private static ArrayList<String> run(String str, ArrayList<String> arrayList) throws Throwable {
        Process processExec;
        ArrayList<String> arrayList2 = new ArrayList<>();
        Process process = null;
        try {
            try {
                processExec = Runtime.getRuntime().exec(str);
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(processExec.getOutputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
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
                    processExec.waitFor();
                    processExec.destroy();
                } catch (IOException e) {
                    e = e;
                    process = processExec;
                    ThrowableExtension.printStackTrace(e);
                    process.destroy();
                } catch (InterruptedException e2) {
                    e = e2;
                    process = processExec;
                    ThrowableExtension.printStackTrace(e);
                    process.destroy();
                } catch (Throwable th) {
                    th = th;
                    processExec.destroy();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                processExec = null;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (InterruptedException e4) {
            e = e4;
        }
        return arrayList2;
    }
}
