package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0639i;
import com.p004b.p015b.InterfaceC0638h;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.b.a.a.e.y */
/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes.dex */
final class C0562y implements Closeable {

    /* JADX INFO: renamed from: a */
    static final Logger f399a = Logger.getLogger(C0544g.class.getName());

    /* JADX INFO: renamed from: b */
    final C0542e f400b;

    /* JADX INFO: renamed from: c */
    private final InterfaceC0638h f401c;

    /* JADX INFO: renamed from: d */
    private final C0563z f402d;

    /* JADX INFO: renamed from: e */
    private final boolean f403e;

    public C0562y(InterfaceC0638h interfaceC0638h, boolean z) {
        this.f401c = interfaceC0638h;
        this.f403e = z;
        this.f402d = new C0563z(this.f401c);
        this.f400b = new C0542e(this.f402d);
    }

    /* JADX INFO: renamed from: a */
    public final void m280a(InterfaceC0526aa interfaceC0526aa) throws IOException {
        if (this.f403e) {
            if (!m281a(true, interfaceC0526aa)) {
                throw C0544g.m254b("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        C0639i c0639iMo548c = this.f401c.mo548c(C0544g.f317a.mo590g());
        if (f399a.isLoggable(Level.FINE)) {
            f399a.fine(C0503c.m110a("<< CONNECTION %s", c0639iMo548c.mo588e()));
        }
        if (!C0544g.f317a.equals(c0639iMo548c)) {
            throw C0544g.m254b("Expected a connection header but was %s", c0639iMo548c.mo581a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: a */
    public final boolean m281a(boolean z, InterfaceC0526aa interfaceC0526aa) throws IOException {
        try {
            this.f401c.mo538a(9L);
            int iM277a = m277a(this.f401c);
            if (iM277a < 0 || iM277a > 16384) {
                throw C0544g.m254b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iM277a));
            }
            byte bMo552e = (byte) (this.f401c.mo552e() & 255);
            if (z && bMo552e != 4) {
                throw C0544g.m254b("Expected a SETTINGS frame but was %s", Byte.valueOf(bMo552e));
            }
            byte bMo552e2 = (byte) (this.f401c.mo552e() & 255);
            int iMo558g = this.f401c.mo558g() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (f399a.isLoggable(Level.FINE)) {
                f399a.fine(C0544g.m253a(true, iMo558g, iM277a, bMo552e, bMo552e2));
            }
            switch (bMo552e) {
                case 0:
                    boolean z2 = (bMo552e2 & 1) != 0;
                    if ((bMo552e2 & 32) != 0) {
                        throw C0544g.m254b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
                    }
                    short sMo552e = (bMo552e2 & 8) != 0 ? (short) (this.f401c.mo552e() & 255) : (short) 0;
                    interfaceC0526aa.mo174a(z2, iMo558g, this.f401c, m276a(iM277a, bMo552e2, sMo552e));
                    this.f401c.mo557f(sMo552e);
                    return true;
                case 1:
                    if (iMo558g == 0) {
                        throw C0544g.m254b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
                    }
                    boolean z3 = (bMo552e2 & 1) != 0;
                    short sMo552e2 = (bMo552e2 & 8) != 0 ? (short) (this.f401c.mo552e() & 255) : (short) 0;
                    if ((bMo552e2 & 32) != 0) {
                        m279a();
                        iM277a -= 5;
                    }
                    interfaceC0526aa.mo175a(z3, iMo558g, m278a(m276a(iM277a, bMo552e2, sMo552e2), sMo552e2, bMo552e2, iMo558g));
                    return true;
                case 2:
                    if (iM277a != 5) {
                        throw C0544g.m254b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(iM277a));
                    }
                    if (iMo558g == 0) {
                        throw C0544g.m254b("TYPE_PRIORITY streamId == 0", new Object[0]);
                    }
                    m279a();
                    return true;
                case 3:
                    if (iM277a != 4) {
                        throw C0544g.m254b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(iM277a));
                    }
                    if (iMo558g == 0) {
                        throw C0544g.m254b("TYPE_RST_STREAM streamId == 0", new Object[0]);
                    }
                    int iMo558g2 = this.f401c.mo558g();
                    EnumC0539b enumC0539bM231a = EnumC0539b.m231a(iMo558g2);
                    if (enumC0539bM231a == null) {
                        throw C0544g.m254b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(iMo558g2));
                    }
                    interfaceC0526aa.mo169a(iMo558g, enumC0539bM231a);
                    return true;
                case 4:
                    if (iMo558g != 0) {
                        throw C0544g.m254b("TYPE_SETTINGS streamId != 0", new Object[0]);
                    }
                    if ((bMo552e2 & 1) == 0) {
                        if (iM277a % 6 != 0) {
                            throw C0544g.m254b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(iM277a));
                        }
                        C0537al c0537al = new C0537al();
                        for (int i = 0; i < iM277a; i += 6) {
                            short sMo556f = this.f401c.mo556f();
                            int iMo558g3 = this.f401c.mo558g();
                            switch (sMo556f) {
                                case 2:
                                    if (iMo558g3 != 0 && iMo558g3 != 1) {
                                        throw C0544g.m254b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                                    }
                                    break;
                                    break;
                                case 3:
                                    sMo556f = 4;
                                    break;
                                case 4:
                                    sMo556f = 7;
                                    if (iMo558g3 < 0) {
                                        throw C0544g.m254b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                                    }
                                    break;
                                    break;
                                case 5:
                                    if (iMo558g3 < 16384 || iMo558g3 > 16777215) {
                                        throw C0544g.m254b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(iMo558g3));
                                    }
                                    break;
                                    break;
                            }
                            c0537al.m224a(sMo556f, iMo558g3);
                        }
                        interfaceC0526aa.mo172a(c0537al);
                    } else if (iM277a != 0) {
                        throw C0544g.m254b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                    }
                    return true;
                case 5:
                    if (iMo558g == 0) {
                        throw C0544g.m254b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
                    }
                    short sMo552e3 = (bMo552e2 & 8) != 0 ? (short) (this.f401c.mo552e() & 255) : (short) 0;
                    interfaceC0526aa.mo171a(this.f401c.mo558g() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, m278a(m276a(iM277a - 4, bMo552e2, sMo552e3), sMo552e3, bMo552e2, iMo558g));
                    return true;
                case 6:
                    if (iM277a != 8) {
                        throw C0544g.m254b("TYPE_PING length != 8: %s", Integer.valueOf(iM277a));
                    }
                    if (iMo558g != 0) {
                        throw C0544g.m254b("TYPE_PING streamId != 0", new Object[0]);
                    }
                    interfaceC0526aa.mo173a((bMo552e2 & 1) != 0, this.f401c.mo558g(), this.f401c.mo558g());
                    return true;
                case 7:
                    if (iM277a < 8) {
                        throw C0544g.m254b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(iM277a));
                    }
                    if (iMo558g != 0) {
                        throw C0544g.m254b("TYPE_GOAWAY streamId != 0", new Object[0]);
                    }
                    int iMo558g4 = this.f401c.mo558g();
                    int iMo558g5 = this.f401c.mo558g();
                    int i2 = iM277a - 8;
                    if (EnumC0539b.m231a(iMo558g5) == null) {
                        throw C0544g.m254b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(iMo558g5));
                    }
                    C0639i c0639iMo548c = C0639i.f816b;
                    if (i2 > 0) {
                        c0639iMo548c = this.f401c.mo548c(i2);
                    }
                    interfaceC0526aa.mo170a(iMo558g4, c0639iMo548c);
                    return true;
                case 8:
                    if (iM277a != 4) {
                        throw C0544g.m254b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(iM277a));
                    }
                    long jMo558g = ((long) this.f401c.mo558g()) & 2147483647L;
                    if (jMo558g == 0) {
                        throw C0544g.m254b("windowSizeIncrement was 0", Long.valueOf(jMo558g));
                    }
                    interfaceC0526aa.mo168a(iMo558g, jMo558g);
                    return true;
                default:
                    this.f401c.mo557f(iM277a);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private List<C0540c> m278a(int i, short s, byte b, int i2) {
        C0563z c0563z = this.f402d;
        this.f402d.f407d = i;
        c0563z.f404a = i;
        this.f402d.f408e = s;
        this.f402d.f405b = b;
        this.f402d.f406c = i2;
        this.f400b.m243a();
        return this.f400b.m244b();
    }

    /* JADX INFO: renamed from: a */
    private void m279a() {
        this.f401c.mo558g();
        this.f401c.mo552e();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f401c.close();
    }

    /* JADX INFO: renamed from: a */
    static int m277a(InterfaceC0638h interfaceC0638h) {
        return (interfaceC0638h.mo552e() & 255) | ((interfaceC0638h.mo552e() & 255) << 16) | ((interfaceC0638h.mo552e() & 255) << 8);
    }

    /* JADX INFO: renamed from: a */
    private static int m276a(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s > i) {
            throw C0544g.m254b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        }
        return (short) (i - s);
    }
}
