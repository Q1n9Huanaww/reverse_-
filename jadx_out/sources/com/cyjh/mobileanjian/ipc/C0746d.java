package com.cyjh.mobileanjian.ipc;

import com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.d */
/* JADX INFO: compiled from: RootShell.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0746d {

    /* JADX INFO: renamed from: a */
    public static C0746d f1001a = null;

    /* JADX INFO: renamed from: h */
    private static final String f1002h = "echo \"rootOK\"\n";

    /* JADX INFO: renamed from: b */
    OnRootApplyCallback f1003b = null;

    /* JADX INFO: renamed from: c */
    public a f1004c = null;

    /* JADX INFO: renamed from: d */
    public boolean f1005d = false;

    /* JADX INFO: renamed from: e */
    public DataOutputStream f1006e = null;

    /* JADX INFO: renamed from: f */
    InputStream f1007f = null;

    /* JADX INFO: renamed from: g */
    Process f1008g = null;

    /* JADX INFO: renamed from: e */
    private static /* synthetic */ boolean m741e(C0746d c0746d) {
        c0746d.f1005d = true;
        return true;
    }

    /* JADX INFO: renamed from: b */
    private boolean m736b() {
        return this.f1005d;
    }

    /* JADX INFO: renamed from: a */
    public final void m742a(OnRootApplyCallback onRootApplyCallback) {
        if (this.f1005d) {
            onRootApplyCallback.onObtained();
            return;
        }
        this.f1003b = onRootApplyCallback;
        this.f1004c = new a(this, (byte) 0);
        this.f1004c.start();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C0746d m728a() {
        if (f1001a == null) {
            f1001a = new C0746d();
        }
        return f1001a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.d$a */
    /* JADX INFO: compiled from: RootShell.java */
    public class a extends Thread {
        private a() {
        }

        /* synthetic */ a(C0746d c0746d, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                C0746d.this.f1008g = Runtime.getRuntime().exec("su");
                C0746d.this.f1006e = new DataOutputStream(C0746d.this.f1008g.getOutputStream());
                C0746d.this.f1007f = C0746d.this.f1008g.getInputStream();
                C0746d.this.f1006e.writeBytes(C0746d.f1002h);
                C0746d.this.f1006e.flush();
                C0746d c0746d = C0746d.this;
                c0746d.new AnonymousClass1("", C0746d.this.f1008g);
                AnonymousClass2 anonymousClass2 = c0746d.new AnonymousClass2("", false);
                anonymousClass2.start();
                try {
                    anonymousClass2.join();
                } catch (InterruptedException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                new StringBuilder("go straight here, mRoot = ").append(c0746d.f1005d);
                if (c0746d.f1005d) {
                    return;
                }
                c0746d.f1008g.destroy();
                if (c0746d.f1003b != null) {
                    c0746d.f1003b.onRefused();
                }
                try {
                    c0746d.f1006e.close();
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            } catch (IOException e3) {
                if (C0746d.this.f1008g != null) {
                    C0746d.this.f1008g.destroy();
                }
                if (C0746d.this.f1003b != null) {
                    C0746d.this.f1003b.onRefused();
                }
                ThrowableExtension.printStackTrace(e3);
            }
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.d$1, reason: invalid class name */
    /* JADX INFO: compiled from: RootShell.java */
    final class AnonymousClass1 extends Thread {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ Process f1009a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Process process) {
            super(str);
            this.f1009a = process;
        }

        /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
        
            return;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f1009a.getErrorStream()));
            do {
                try {
                    try {
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                        return;
                    }
                } catch (IOException unused) {
                    bufferedReader.close();
                    return;
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                    throw th;
                }
            } while (bufferedReader.readLine() != null);
            bufferedReader.close();
        }
    }

    /* JADX INFO: renamed from: a */
    private void m733a(Process process, boolean z) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1("", process);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2("", z);
        if (z) {
            anonymousClass1.start();
        }
        anonymousClass2.start();
        if (z) {
            try {
                anonymousClass1.join();
            } catch (InterruptedException unused) {
            }
            try {
                anonymousClass2.join();
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
        } else {
            try {
                anonymousClass2.join();
            } catch (InterruptedException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        new StringBuilder("go straight here, mRoot = ").append(this.f1005d);
        if (this.f1005d) {
            return;
        }
        this.f1008g.destroy();
        if (this.f1003b != null) {
            this.f1003b.onRefused();
        }
        try {
            this.f1006e.close();
        } catch (IOException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.d$2, reason: invalid class name */
    /* JADX INFO: compiled from: RootShell.java */
    final class AnonymousClass2 extends Thread {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ boolean f1011a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, boolean z) {
            super(str);
            this.f1011a = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(C0746d.this.f1007f));
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equals("rootOK")) {
                            C0746d.this.f1005d = true;
                            if (C0746d.this.f1003b != null) {
                                C0746d.this.f1003b.onObtained();
                            }
                            if (!this.f1011a) {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                        try {
                            bufferedReader.close();
                            return;
                        } catch (IOException e2) {
                            ThrowableExtension.printStackTrace(e2);
                            return;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        ThrowableExtension.printStackTrace(e3);
                    }
                    throw th;
                }
            }
            try {
                bufferedReader.close();
            } catch (IOException e4) {
                ThrowableExtension.printStackTrace(e4);
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private void m738c() {
        if (this.f1005d) {
            m743a("exit");
            if (this.f1004c.isAlive()) {
                this.f1004c.interrupt();
            }
            try {
                this.f1006e.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        f1001a = null;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m743a(String str) {
        if (!this.f1005d) {
            return false;
        }
        try {
            this.f1006e.write(str.getBytes());
            this.f1006e.write("\n".getBytes());
            this.f1006e.flush();
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return true;
        }
    }

    /* JADX INFO: renamed from: d */
    private void m740d() {
        this.f1004c = new a(this, (byte) 0);
        this.f1004c.start();
    }

    /* JADX INFO: renamed from: b */
    private static /* synthetic */ void m735b(C0746d c0746d, Process process) {
        c0746d.new AnonymousClass1("", process);
        AnonymousClass2 anonymousClass2 = c0746d.new AnonymousClass2("", false);
        anonymousClass2.start();
        try {
            anonymousClass2.join();
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
        new StringBuilder("go straight here, mRoot = ").append(c0746d.f1005d);
        if (c0746d.f1005d) {
            return;
        }
        c0746d.f1008g.destroy();
        if (c0746d.f1003b != null) {
            c0746d.f1003b.onRefused();
        }
        try {
            c0746d.f1006e.close();
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }
}
