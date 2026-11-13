package com.p001a.p002a.p003a;

import android.support.annotation.NonNull;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.a.a.a.b */
/* JADX INFO: compiled from: Shell.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0447b {

    /* JADX INFO: renamed from: a */
    static final String[] f36a = {"echo -BOC-", "id"};

    /* JADX INFO: renamed from: a */
    public static C0446a m12a(@NonNull String str, @NonNull String... strArr) {
        return m13b(str, strArr);
    }

    /* JADX INFO: renamed from: b */
    private static C0446a m13b(@NonNull String str, @NonNull String[] strArr) {
        int iWaitFor;
        List listSynchronizedList = Collections.synchronizedList(new ArrayList());
        List listSynchronizedList2 = Collections.synchronizedList(new ArrayList());
        try {
            try {
                Process processExec = Runtime.getRuntime().exec(str, (String[]) null);
                DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                C0448c c0448c = new C0448c(processExec.getInputStream(), listSynchronizedList);
                C0448c c0448c2 = new C0448c(processExec.getErrorStream(), listSynchronizedList2);
                c0448c.start();
                c0448c2.start();
                try {
                    for (String str2 : strArr) {
                        dataOutputStream.write((str2 + "\n").getBytes("UTF-8"));
                        dataOutputStream.flush();
                    }
                    dataOutputStream.write("exit\n".getBytes("UTF-8"));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    if (!e.getMessage().contains("EPIPE")) {
                        throw e;
                    }
                }
                iWaitFor = processExec.waitFor();
                try {
                    dataOutputStream.close();
                } catch (IOException unused) {
                }
                c0448c.join();
                c0448c2.join();
                processExec.destroy();
            } catch (InterruptedException unused2) {
                iWaitFor = -1;
            }
        } catch (IOException unused3) {
            iWaitFor = -4;
        }
        return new C0446a(listSynchronizedList, listSynchronizedList2, iWaitFor);
    }
}
