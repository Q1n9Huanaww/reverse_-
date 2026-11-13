package com.p001a.p002a.p003a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: renamed from: com.a.a.a.c */
/* JADX INFO: compiled from: StreamGobbler.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0448c extends Thread {

    /* JADX INFO: renamed from: a */
    private final BufferedReader f37a;

    /* JADX INFO: renamed from: b */
    private List<String> f38b;

    /* JADX INFO: renamed from: c */
    private InterfaceC0449d f39c;

    public C0448c(InputStream inputStream, List<String> list) {
        this.f37a = new BufferedReader(new InputStreamReader(inputStream));
        this.f38b = list;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            try {
                String line = this.f37a.readLine();
                if (line == null) {
                    break;
                }
                if (this.f38b != null) {
                    this.f38b.add(line);
                }
                InterfaceC0449d interfaceC0449d = this.f39c;
            } catch (IOException unused) {
            }
        }
        this.f37a.close();
        try {
            this.f37a.close();
        } catch (IOException unused2) {
        }
    }
}
