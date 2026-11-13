package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.pay.PayConstants;
import com.umeng.commonsdk.framework.C1732b;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.r */
/* JADX INFO: compiled from: SystemLayerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class C1795r {

    /* JADX INFO: renamed from: a */
    private static final String f4226a = "info";

    /* JADX INFO: renamed from: b */
    private static final String f4227b = "stat";

    /* JADX INFO: renamed from: c */
    private static boolean f4228c = false;

    /* JADX INFO: renamed from: d */
    private static HandlerThread f4229d = null;

    /* JADX INFO: renamed from: e */
    private static Context f4230e = null;

    /* JADX INFO: renamed from: f */
    private static int f4231f = 0;

    /* JADX INFO: renamed from: g */
    private static int f4232g = 0;

    /* JADX INFO: renamed from: h */
    private static int f4233h = 0;

    /* JADX INFO: renamed from: i */
    private static int f4234i = 1;

    /* JADX INFO: renamed from: j */
    private static long f4235j = 0;

    /* JADX INFO: renamed from: k */
    private static long f4236k = 0;

    /* JADX INFO: renamed from: l */
    private static final int f4237l = 40;

    /* JADX INFO: renamed from: m */
    private static final int f4238m = 50000;

    /* JADX INFO: renamed from: n */
    private static SensorManager f4239n;

    /* JADX INFO: renamed from: o */
    private static ArrayList<float[]> f4240o = new ArrayList<>();

    /* JADX INFO: renamed from: p */
    private static SensorEventListener f4241p = new SensorEventListener() { // from class: com.umeng.commonsdk.proguard.r.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) throws Throwable {
            if (C1795r.f4232g >= 15) {
                if (C1795r.f4231f < 20) {
                    C1795r.m4077e();
                    C1795r.f4240o.add(sensorEvent.values);
                }
                if (C1795r.f4231f == 20) {
                    C1795r.m4077e();
                    if (C1795r.f4234i == 1) {
                        long unused = C1795r.f4235j = System.currentTimeMillis();
                    }
                    if (C1795r.f4234i == 2) {
                        long unused2 = C1795r.f4236k = System.currentTimeMillis();
                    }
                    C1795r.m4082h();
                    C1795r.m4086l();
                    return;
                }
                return;
            }
            C1795r.m4073c();
        }
    };

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.r$a */
    /* JADX INFO: compiled from: SystemLayerUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        public int f4244a;

        /* JADX INFO: renamed from: b */
        public int f4245b;

        /* JADX INFO: renamed from: c */
        public long f4246c;
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ int m4073c() {
        int i = f4232g;
        f4232g = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: e */
    static /* synthetic */ int m4077e() {
        int i = f4231f;
        f4231f = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: h */
    static /* synthetic */ int m4082h() {
        int i = f4234i;
        f4234i = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: a */
    public static List<Sensor> m4068a(Context context) {
        if (context == null) {
            return null;
        }
        return ((SensorManager) context.getSystemService(C1799v.f4284W)).getSensorList(-1);
    }

    /* JADX INFO: renamed from: b */
    public static void m4072b(Context context) {
        if (context == null || m4069a()) {
            return;
        }
        f4228c = true;
        f4230e = context.getApplicationContext();
        String strM3715a = C1732b.m3715a(context);
        String packageName = context.getPackageName();
        if (strM3715a == null || !strM3715a.equals(packageName)) {
            return;
        }
        f4239n = (SensorManager) context.getSystemService(C1799v.f4284W);
        final Sensor defaultSensor = f4239n.getDefaultSensor(4);
        final Sensor defaultSensor2 = f4239n.getDefaultSensor(1);
        if (defaultSensor != null) {
            f4233h = 4;
            f4239n.registerListener(f4241p, defaultSensor, f4238m);
        } else if (defaultSensor2 != null) {
            f4233h = 1;
            f4239n.registerListener(f4241p, defaultSensor2, f4238m);
        }
        int iNextInt = (new Random().nextInt(3) * 1000) + PayConstants.PAY_SYSTEM_ERROR;
        f4229d = new HandlerThread("sensor_thread");
        f4229d.start();
        new Handler(f4229d.getLooper()).postDelayed(new Runnable() { // from class: com.umeng.commonsdk.proguard.r.2
            @Override // java.lang.Runnable
            public void run() {
                int unused = C1795r.f4231f = 0;
                if (defaultSensor != null) {
                    C1795r.f4239n.registerListener(C1795r.f4241p, defaultSensor, C1795r.f4238m);
                } else if (defaultSensor2 != null) {
                    C1795r.f4239n.registerListener(C1795r.f4241p, defaultSensor2, C1795r.f4238m);
                }
            }
        }, iNextInt);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l */
    public static void m4086l() throws Throwable {
        if (f4239n != null) {
            f4239n.unregisterListener(f4241p);
        }
        if (f4240o.size() == 40) {
            m4080f(f4230e);
            if (f4240o != null) {
                f4240o.clear();
            }
            if (f4229d != null) {
                f4229d.quit();
                f4229d = null;
            }
            f4230e = null;
            f4228c = false;
        }
    }

    /* JADX INFO: renamed from: c */
    public static JSONArray m4074c(Context context) {
        String string;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4226a, 0);
        if (sharedPreferences == null || (string = sharedPreferences.getString(f4227b, null)) == null) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m4080f(Context context) throws Throwable {
        int i;
        if (context == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < 2; i2++) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                int i3 = 20;
                if (i2 == 1) {
                    i = 40;
                } else {
                    i3 = 0;
                    i = 20;
                }
                while (i3 < i) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("x", f4240o.get(i3)[0]);
                    jSONObject2.put("y", f4240o.get(i3)[1]);
                    jSONObject2.put("z", f4240o.get(i3)[2]);
                    jSONArray2.put(jSONObject2);
                    i3++;
                }
                if (f4233h == 4) {
                    jSONObject.put("g", jSONArray2);
                } else if (f4233h == 1) {
                    jSONObject.put(C1799v.f4296ah, jSONArray2);
                }
                if (i2 == 0) {
                    jSONObject.put("ts", f4235j);
                } else {
                    jSONObject.put("ts", f4236k);
                }
                jSONArray.put(jSONObject);
                UMWorkDispatch.sendEvent(context, C1736a.f4006l, C1763b.m3919a(context).m3920a(), jSONArray.toString());
            }
        } catch (Exception e) {
            C1782e.m3972a(context, e);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4076d(Context context) {
        if (context == null) {
            return;
        }
        context.getApplicationContext().getSharedPreferences(f4226a, 0).edit().remove(f4227b).commit();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized boolean m4069a() {
        return f4228c;
    }

    /* JADX INFO: renamed from: e */
    public static List<a> m4078e(Context context) throws Throwable {
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.CAMERA")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
                for (String str : cameraManager.getCameraIdList()) {
                    Size size = (Size) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
                    if (size != null) {
                        a aVar = new a();
                        aVar.f4244a = size.getWidth();
                        aVar.f4245b = size.getHeight();
                        aVar.f4246c = System.currentTimeMillis();
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception unused) {
            C1817e.m4406c("camera access exception");
        }
        return arrayList;
    }
}
