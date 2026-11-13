package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.u */
/* JADX INFO: compiled from: UMUdpSender.java */
/* JADX INFO: loaded from: classes.dex */
public class C1798u {

    /* JADX INFO: renamed from: a */
    private static String f4257a = "239.192.152.163";

    /* JADX INFO: renamed from: b */
    private static int f4258b = 48809;

    /* JADX INFO: renamed from: c */
    private WifiManager.MulticastLock f4259c;

    /* JADX INFO: renamed from: d */
    private WifiManager f4260d;

    /* JADX INFO: renamed from: e */
    private Context f4261e;

    public C1798u(Context context) {
        if (context != null) {
            this.f4261e = context;
            this.f4260d = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4109a(String str, int i, String str2) throws Throwable {
        MulticastSocket multicastSocket;
        MulticastSocket multicastSocket2;
        m4105a();
        MulticastSocket multicastSocket3 = null;
        MulticastSocket multicastSocket4 = null;
        try {
            try {
                try {
                    multicastSocket = new MulticastSocket(i);
                    try {
                        multicastSocket.joinGroup(InetAddress.getByName(str));
                        multicastSocket.setLoopbackMode(true);
                        DatagramPacket datagramPacket = new DatagramPacket(str2.getBytes(), str2.getBytes().length, InetAddress.getByName(str), i);
                        multicastSocket.send(datagramPacket);
                        if (multicastSocket != null) {
                            try {
                                multicastSocket.close();
                            } catch (Exception e) {
                                C1782e.m3972a(this.f4261e, e);
                            }
                        }
                        multicastSocket2 = datagramPacket;
                        multicastSocket3 = datagramPacket;
                        if (this.f4259c != null) {
                            this.f4259c.release();
                            multicastSocket3 = multicastSocket2;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        multicastSocket4 = multicastSocket;
                        C1782e.m3972a(this.f4261e, e);
                        if (multicastSocket4 != null) {
                            try {
                                multicastSocket4.close();
                            } catch (Exception e3) {
                                C1782e.m3972a(this.f4261e, e3);
                            }
                        }
                        multicastSocket2 = multicastSocket4;
                        multicastSocket3 = multicastSocket4;
                        if (this.f4259c != null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (multicastSocket != null) {
                            try {
                                multicastSocket.close();
                            } catch (Exception e4) {
                                C1782e.m3972a(this.f4261e, e4);
                            }
                        }
                        try {
                            if (this.f4259c != null) {
                                this.f4259c.release();
                                throw th;
                            }
                            throw th;
                        } catch (Exception unused) {
                            throw th;
                        }
                    }
                } catch (Exception unused2) {
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
            multicastSocket = multicastSocket3;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4105a() {
        try {
            if (this.f4260d != null) {
                this.f4259c = this.f4260d.createMulticastLock("multicast.test");
                if (this.f4259c != null) {
                    this.f4259c.acquire();
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m4108a(Context context, String str, int i, String str2) throws Throwable {
        new C1798u(context).m4109a(str, i, str2);
    }

    /* JADX INFO: renamed from: a */
    private static void m4107a(Context context, String str) throws Throwable {
        m4108a(context, f4257a, f4258b, str);
    }

    /* JADX INFO: renamed from: a */
    public static void m4106a(Context context) throws Throwable {
        String uMIDString = UMConfigure.getUMIDString(context);
        if (TextUtils.isEmpty(uMIDString)) {
            return;
        }
        m4107a(context, uMIDString);
    }
}
