package com.google.p028a;

import com.google.p028a.p029a.C1143c;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p038d.C1217b;
import com.google.p028a.p047g.C1277ab;
import com.google.p028a.p047g.C1278b;
import com.google.p028a.p047g.C1280d;
import com.google.p028a.p047g.C1282f;
import com.google.p028a.p047g.C1284h;
import com.google.p028a.p047g.C1286j;
import com.google.p028a.p047g.C1288l;
import com.google.p028a.p047g.C1291o;
import com.google.p028a.p047g.C1297u;
import com.google.p028a.p051h.C1329d;
import com.google.p028a.p056i.C1342b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.l */
/* JADX INFO: compiled from: MultiFormatWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1357l implements InterfaceC1367v {
    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) throws C1368w {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        InterfaceC1367v c1288l;
        switch (enumC1138a) {
            case EAN_8:
                c1288l = new C1288l();
                break;
            case UPC_E:
                c1288l = new C1277ab();
                break;
            case EAN_13:
                c1288l = new C1286j();
                break;
            case UPC_A:
                c1288l = new C1297u();
                break;
            case QR_CODE:
                c1288l = new C1342b();
                break;
            case CODE_39:
                c1288l = new C1282f();
                break;
            case CODE_93:
                c1288l = new C1284h();
                break;
            case CODE_128:
                c1288l = new C1280d();
                break;
            case ITF:
                c1288l = new C1291o();
                break;
            case PDF_417:
                c1288l = new C1329d();
                break;
            case CODABAR:
                c1288l = new C1278b();
                break;
            case DATA_MATRIX:
                c1288l = new C1217b();
                break;
            case AZTEC:
                c1288l = new C1143c();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format ".concat(String.valueOf(enumC1138a)));
        }
        return c1288l.mo1517a(str, enumC1138a, i, i2, map);
    }
}
