package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.C1817e;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.k */
/* JADX INFO: compiled from: BaseStationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1788k {

    /* JADX INFO: renamed from: b */
    private static final String f4182b = "BaseStationUtils";

    /* JADX INFO: renamed from: c */
    private static boolean f4183c;

    /* JADX INFO: renamed from: d */
    private static Context f4184d;

    /* JADX INFO: renamed from: a */
    PhoneStateListener f4185a;

    /* JADX INFO: renamed from: e */
    private TelephonyManager f4186e;

    private C1788k(Context context) {
        this.f4185a = new PhoneStateListener() { // from class: com.umeng.commonsdk.proguard.k.1
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) throws Throwable {
                String str;
                super.onSignalStrengthsChanged(signalStrength);
                C1817e.m4409c(C1788k.f4182b, "base station onSignalStrengthsChanged");
                try {
                    C1788k.this.f4186e = (TelephonyManager) C1788k.f4184d.getSystemService(URLConstant.USER_ACCOUNT);
                    String[] strArrSplit = signalStrength.toString().split(" ");
                    String str2 = null;
                    if (C1788k.this.f4186e.getNetworkType() != 13) {
                        if (C1788k.this.f4186e.getNetworkType() == 8 || C1788k.this.f4186e.getNetworkType() == 10 || C1788k.this.f4186e.getNetworkType() == 9 || C1788k.this.f4186e.getNetworkType() == 3) {
                            String strM4024e = C1788k.this.m4024e();
                            if (!TextUtils.isEmpty(strM4024e) && strM4024e.equals("中国移动")) {
                                str2 = AccountManager.TYPE_LOGIN;
                            } else if (!TextUtils.isEmpty(strM4024e) && strM4024e.equals("中国联通")) {
                                str2 = signalStrength.getCdmaDbm() + "";
                            } else if (!TextUtils.isEmpty(strM4024e) && strM4024e.equals("中国电信")) {
                                str2 = signalStrength.getEvdoDbm() + "";
                            }
                            str = str2;
                        } else {
                            str = ((signalStrength.getGsmSignalStrength() * 2) - 113) + "";
                        }
                    } else {
                        str = "" + Integer.parseInt(strArrSplit[9]);
                    }
                    C1817e.m4409c(C1788k.f4182b, "stationStrength is " + str);
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            UMWorkDispatch.sendEvent(C1788k.f4184d, C1736a.f4002h, C1763b.m3919a(C1788k.f4184d).m3920a(), str);
                        } catch (Throwable th) {
                            C1782e.m3972a(C1788k.f4184d, th);
                        }
                    }
                    C1788k.this.m4027c();
                } catch (Exception e) {
                    C1782e.m3972a(C1788k.f4184d, e);
                }
            }
        };
        if (context != null) {
            try {
                this.f4186e = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.k$a */
    /* JADX INFO: compiled from: BaseStationUtils.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1788k f4188a = new C1788k(C1788k.f4184d);

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1788k m4021a(Context context) {
        if (f4184d == null && context != null) {
            f4184d = context.getApplicationContext();
        }
        return a.f4188a;
    }

    /* JADX INFO: renamed from: a */
    public synchronized boolean m4025a() {
        return f4183c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e */
    public String m4024e() {
        String str;
        try {
            String simOperator = ((TelephonyManager) f4184d.getSystemService(URLConstant.USER_ACCOUNT)).getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (simOperator.equals("46000") || simOperator.equals("46002")) {
                str = "中国移动";
            } else if (simOperator.equals("46001")) {
                str = "中国联通";
            } else {
                if (!simOperator.equals("46003")) {
                    return null;
                }
                str = "中国电信";
            }
            return str;
        } catch (Throwable th) {
            C1782e.m3972a(f4184d, th);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public synchronized void m4026b() {
        C1817e.m4409c(f4182b, "base station registerListener");
        try {
            if (this.f4186e != null) {
                this.f4186e.listen(this.f4185a, 256);
            }
            f4183c = true;
        } catch (Throwable th) {
            C1782e.m3972a(f4184d, th);
        }
    }

    /* JADX INFO: renamed from: c */
    public synchronized void m4027c() {
        C1817e.m4409c(f4182b, "base station unRegisterListener");
        try {
            if (this.f4186e != null) {
                this.f4186e.listen(this.f4185a, 0);
            }
            f4183c = false;
        } catch (Throwable th) {
            C1782e.m3972a(f4184d, th);
        }
    }
}
