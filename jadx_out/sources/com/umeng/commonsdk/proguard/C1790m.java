package com.umeng.commonsdk.proguard;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.m */
/* JADX INFO: compiled from: ExecShell.java */
/* JADX INFO: loaded from: classes.dex */
public class C1790m {

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.m$a */
    /* JADX INFO: compiled from: ExecShell.java */
    public enum a {
        check_su_binary(new String[]{"/system/xbin/which", "su"});


        /* JADX INFO: renamed from: b */
        String[] f4197b;

        a(String[] strArr) {
            this.f4197b = strArr;
        }
    }

    /* JADX INFO: renamed from: a */
    public ArrayList m4035a(a aVar) {
        ArrayList arrayList = new ArrayList();
        try {
            Process processExec = Runtime.getRuntime().exec(aVar.f4197b);
            new BufferedWriter(new OutputStreamWriter(processExec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    arrayList.add(line);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }
}
