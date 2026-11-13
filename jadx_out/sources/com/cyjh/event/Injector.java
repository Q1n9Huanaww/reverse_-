package com.cyjh.event;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.C0741a;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.p018ui.C0781b;
import com.cyjh.mobileanjian.ipc.p018ui.C0782c;
import com.cyjh.mobileanjian.ipc.p018ui.C0786g;
import com.cyjh.mobileanjian.ipc.p018ui.C0790k;
import com.cyjh.mobileanjian.ipc.p018ui.EnumC0792m;
import com.cyjh.mobileanjian.ipc.p018ui.ViewOnClickListenerC0788i;
import com.cyjh.mobileanjian.ipc.p018ui.ViewOnClickListenerC0788i.AnonymousClass1;
import com.cyjh.mobileanjian.ipc.p018ui.ViewOnClickListenerC0788i.AnonymousClass2;
import com.cyjh.mobileanjian.ipc.p018ui.ViewOnClickListenerC0788i.a;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C0806c;
import com.cyjh.mobileanjian.ipc.utils.C0807d;
import com.cyjh.mobileanjian.ipc.view.C0825b;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.p023d.C0841f;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.p019mq.sdk.MqRunnerLite;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.p028a.C1189c;
import com.google.p028a.C1356k;
import com.google.p028a.C1360o;
import com.google.p028a.C1363r;
import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1205h;
import com.google.p028a.p035c.C1207j;
import com.google.p028a.p056i.C1342b;
import com.google.p028a.p056i.p057a.EnumC1337f;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.p092io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes.dex */
public final class Injector {

    /* JADX INFO: renamed from: a */
    private static final String f891a = "Injector";

    /* JADX INFO: renamed from: b */
    private static Instrumentation f892b = null;

    /* JADX INFO: renamed from: c */
    private static Context f893c = null;

    /* JADX INFO: renamed from: d */
    private static ExToast f894d = null;

    /* JADX INFO: renamed from: e */
    private static C0741a f895e = null;

    /* JADX INFO: renamed from: f */
    private static ViewOnClickListenerC0788i f896f = null;

    /* JADX INFO: renamed from: g */
    private static TessBaseAPI f897g = null;

    /* JADX INFO: renamed from: h */
    private static boolean f898h = false;

    /* JADX INFO: renamed from: i */
    private static Handler f899i = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.event.Injector.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Injector.m628a((C0720b) message.obj);
                    break;
                case 2:
                    Injector.m632b((C0720b) message.obj);
                    break;
                case 3:
                    Injector.m636c((C0720b) message.obj);
                    break;
                case 4:
                    Injector.m639d((C0720b) message.obj);
                    break;
                case 5:
                    Injector.m631b();
                    break;
                case 6:
                    Injector.m624a();
                    break;
                case 7:
                    Injector.m629a((byte[]) message.obj);
                    break;
                case 8:
                    C0720b c0720b = (C0720b) message.obj;
                    if (!MqRunnerLite.getInstance().f1634d) {
                        MqRunner.getInstance();
                    } else {
                        if (MqRunnerLite.getInstance().f1633c != null) {
                            MqRunnerLite.getInstance().f1633c.onCallback(c0720b.f931a, c0720b.f934d);
                        }
                        if (MqRunnerLite.getInstance().f1632b != null) {
                            MqRunnerLite.getInstance().f1632b.callback(c0720b.f931a, c0720b.f934d);
                        }
                    }
                    break;
            }
        }
    };

    /* JADX INFO: renamed from: j */
    private static final float f900j = 0.0f;

    /* JADX INFO: renamed from: k */
    private static final float f901k = -1.5707964f;

    /* JADX INFO: renamed from: l */
    private static final float f902l = -3.1415927f;

    /* JADX INFO: renamed from: m */
    private static final float f903m = 1.5707964f;

    /* JADX INFO: renamed from: n */
    private static boolean f904n = false;

    /* JADX INFO: renamed from: o */
    private static int f905o = 0;

    /* JADX INFO: renamed from: p */
    private static String f906p = null;

    /* JADX INFO: renamed from: q */
    private static final int f907q = 1;

    /* JADX INFO: renamed from: r */
    private static final int f908r = 2;

    /* JADX INFO: renamed from: s */
    private static final String f909s = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11";

    /* JADX INFO: renamed from: t */
    private static final int f910t = 5;

    /* JADX INFO: renamed from: u */
    private static final int f911u = 1;

    /* JADX INFO: renamed from: v */
    private static final int f912v = 2;

    /* JADX INFO: renamed from: w */
    private static final int f913w = 3;

    /* JADX INFO: renamed from: x */
    private static C0717a[] f914x;

    /* JADX INFO: renamed from: y */
    private static MotionEvent.PointerProperties[] f915y;

    /* JADX INFO: renamed from: z */
    private static MotionEvent.PointerCoords[] f916z;

    /* JADX INFO: renamed from: com.cyjh.event.Injector$b */
    interface InterfaceC0718b {

        /* JADX INFO: renamed from: a */
        public static final int f921a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f922b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f923c = 3;

        /* JADX INFO: renamed from: d */
        public static final int f924d = 4;

        /* JADX INFO: renamed from: e */
        public static final int f925e = 5;

        /* JADX INFO: renamed from: f */
        public static final int f926f = 6;

        /* JADX INFO: renamed from: g */
        public static final int f927g = 7;

        /* JADX INFO: renamed from: h */
        public static final int f928h = 8;
    }

    public static void ClearCaches(String str) {
    }

    public static String GetFullUiElement() {
        return "";
    }

    public static String GetUiElement() {
        return "";
    }

    public static int GetVersion() {
        return 6;
    }

    public static String GetWebViewElement(String str) {
        return "";
    }

    public static int InjectWebViewElement(String str, String str2) {
        return 0;
    }

    public static void NotifyApp(String str) {
    }

    public static int SetAirplaneMode(boolean z) {
        return -1;
    }

    public static void StopScript() {
    }

    public static void TracePrint(String str) {
    }

    public static int UninstallApp(String str, String str2) {
        return -1;
    }

    /* JADX INFO: renamed from: a */
    private static final float m621a(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    public static void switchToIm(String str) {
    }

    private Injector() {
    }

    public static void init(Context context) {
        if (context == null) {
            throw new NullPointerException("Context is null...");
        }
        f893c = context.getApplicationContext();
        f895e = new C0741a(f893c);
        f896f = new ViewOnClickListenerC0788i(f893c);
        f898h = initTessBass();
    }

    public static void release() {
        C0741a.m665f();
        f895e.m670a();
        ViewOnClickListenerC0788i viewOnClickListenerC0788i = f896f;
        viewOnClickListenerC0788i.m959c();
        viewOnClickListenerC0788i.f1268b = 0;
        viewOnClickListenerC0788i.f1272f = 1;
        for (int i = 0; i < 32; i++) {
            viewOnClickListenerC0788i.f1267a[i] = null;
            if (viewOnClickListenerC0788i.f1271e[i] != null) {
                viewOnClickListenerC0788i.f1271e[i].clear();
            }
        }
        releasePointerInfoArray();
    }

    public static boolean initTessBass() {
        File file = new File(f893c.getFilesDir().getAbsolutePath() + "/tessdata/", InterfaceRelatedConstants.ENG_TRAINED_DATA);
        File file2 = new File(f893c.getFilesDir().getAbsolutePath() + "/tessdata/", InterfaceRelatedConstants.CHI_SIM_TRAINED_DATA);
        if (!file.exists() || !file2.exists()) {
            return false;
        }
        f897g = new TessBaseAPI();
        boolean zM2779a = f897g.m2779a(f893c.getFilesDir().getAbsolutePath(), "chi_sim+eng");
        Log.i("MqmHandler", "initTessBass: init ret=" + zM2779a);
        return zM2779a;
    }

    /* JADX INFO: renamed from: a */
    private static void m627a(int i, C0720b c0720b) {
        StringBuilder sb = new StringBuilder("sendToTargetHandler ");
        sb.append(i);
        sb.append("         ");
        sb.append(c0720b);
        f899i.obtainMessage(i, c0720b).sendToTarget();
    }

    /* JADX INFO: renamed from: e */
    private static void m641e(C0720b c0720b) {
        if (f894d == null) {
            f894d = new ExToast(f893c);
        }
        f894d.show(c0720b.f934d, c0720b.f931a, c0720b.f932b, c0720b.f933c);
    }

    /* JADX INFO: renamed from: com.cyjh.event.Injector$2 */
    static class C07152 implements C0781b.a {
        C07152() {
        }

        @Override // com.cyjh.mobileanjian.ipc.p018ui.C0781b.a
        /* JADX INFO: renamed from: a */
        public final void mo648a(int i) {
            Injector.setSyncReturnValue(i, "");
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m643f(C0720b c0720b) {
        new C0781b(f893c, c0720b.f934d, c0720b.f931a, new C07152()).m833a();
    }

    /* JADX INFO: renamed from: com.cyjh.event.Injector$3 */
    static class C07163 implements C0782c.a {
        C07163() {
        }

        @Override // com.cyjh.mobileanjian.ipc.p018ui.C0782c.a
        /* JADX INFO: renamed from: a */
        public final void mo649a(String str) {
            Injector.setSyncReturnValue(0, str);
        }
    }

    /* JADX INFO: renamed from: g */
    private static void m645g(C0720b c0720b) {
        new C0782c(f893c, c0720b.f934d, new C07163()).m839a();
    }

    /* JADX INFO: renamed from: h */
    private static void m647h(C0720b c0720b) {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onUpdateControlBarPos(c0720b.f935e, c0720b.f931a, c0720b.f932b);
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m635c() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onResume();
        }
    }

    /* JADX INFO: renamed from: d */
    private static void m638d() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onPause();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:62:0x01ed  */
    /* JADX INFO: renamed from: b */
    private static void m633b(byte[] bArr) {
        String string;
        int color;
        try {
            UiMessage.CommandToUi from = UiMessage.CommandToUi.parseFrom(ByteString.copyFrom(bArr));
            if (from != null) {
                ViewOnClickListenerC0788i viewOnClickListenerC0788i = f896f;
                String controlId = from.getControlId();
                UiMessage.CommandToUi.Command_Type command = from.getCommand();
                new StringBuilder("command: ").append(from);
                int i = 2;
                boolean zM961d = true;
                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1278a[command.ordinal()]) {
                    case 1:
                        try {
                            try {
                                viewOnClickListenerC0788i.m954a(from.getPath());
                            } catch (IOException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        } catch (JSONException e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 2:
                        try {
                            try {
                                viewOnClickListenerC0788i.m958b(from.getPath());
                            } catch (JSONException e3) {
                                ThrowableExtension.printStackTrace(e3);
                            }
                        } catch (IOException e4) {
                            ThrowableExtension.printStackTrace(e4);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 3:
                        int width = from.getWidth();
                        int height = from.getHeight();
                        viewOnClickListenerC0788i.f1268b++;
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] = viewOnClickListenerC0788i.f1269c.m868a(controlId, width, height);
                        viewOnClickListenerC0788i.f1270d.put(controlId, Integer.valueOf(viewOnClickListenerC0788i.f1268b));
                        viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b] = new HashMap();
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].f1320b = viewOnClickListenerC0788i.new AnonymousClass1(controlId);
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].f1319a = viewOnClickListenerC0788i.new AnonymousClass2(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 4:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].f1321c) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m1008c();
                            ViewOnClickListenerC0788i.m931a(true);
                            C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                        }
                        break;
                    case 5:
                        ViewOnClickListenerC0788i.m935c(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 6:
                        zM961d = viewOnClickListenerC0788i.m961d(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 7:
                        viewOnClickListenerC0788i.m959c();
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 8:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0790k c0790k = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b];
                            viewOnClickListenerC0788i.m952a();
                            c0790k.m1002a(controlId, from.getParentId(), from.getWidth(), from.getHeight());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 9:
                        String text = from.getText();
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            zM961d = false;
                        } else {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m1000a(text);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 10:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m995a()).build()).build().toByteString());
                        }
                        break;
                    case 11:
                        String controlId2 = from.getControlId();
                        String color2 = from.getColor();
                        if (viewOnClickListenerC0788i.f1270d.get(controlId2) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId2);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else if (!color2.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            if (!color2.startsWith("#")) {
                                color2 = "#" + color2;
                            }
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId2).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color2)));
                            ViewOnClickListenerC0788i.m931a(true);
                        }
                        break;
                    case 12:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            LinearLayout linearLayoutM870b = c0786g.m870b(controlId, from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.LINE);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(linearLayoutM870b, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 13:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g2 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            TextView textViewM866a = c0786g2.m866a(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.TEXT_VIEW);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(textViewM866a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 14:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g3 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            EditText editTextM873d = c0786g3.m873d(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.EDIT_TEXT);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(editTextM873d, from.getParentId());
                            editTextM873d.addTextChangedListener(viewOnClickListenerC0788i.new a(controlId));
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 15:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g4 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            Button buttonM869b = c0786g4.m869b(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.BUTTON);
                            buttonM869b.setOnClickListener(viewOnClickListenerC0788i);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(buttonM869b, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 16:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g5 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            RadioGroup radioGroupM864a = c0786g5.m864a(controlId, from.getItemTextList(), from.getDefaultItemIndex(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.RADIIO_GROUP);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(radioGroupM864a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 17:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g6 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            CheckBox checkBoxM862a = c0786g6.m862a(controlId, from.getText(), from.getWidth(), from.getHeight(), from.getDefaultCheckStatus());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.CHECK_BOX);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(checkBoxM862a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 18:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            ImageView imageViewM863a = viewOnClickListenerC0788i.f1269c.m863a(viewOnClickListenerC0788i.m952a(), controlId, from.getWidth(), from.getHeight(), from.getPath());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.IMAGE_VIEW);
                            imageViewM863a.setOnClickListener(viewOnClickListenerC0788i);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(imageViewM863a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 19:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g7 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            WebView webViewM861a = c0786g7.m861a(controlId, from.getWidth(), from.getHeight(), from.getUrl());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.WEB_VIEW);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(webViewM861a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 20:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g8 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            List<String> itemTextList = from.getItemTextList();
                            int defaultItemIndex = from.getDefaultItemIndex();
                            from.getWidth();
                            from.getHeight();
                            Spinner spinnerM865a = c0786g8.m865a(controlId, itemTextList, defaultItemIndex);
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.SPINNER);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(spinnerM865a, from.getParentId());
                            spinnerM865a.setOnItemSelectedListener(viewOnClickListenerC0788i);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 21:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1001a(from.getControlId(), from.getHeight());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 22:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            zM961d = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1003a(viewOnClickListenerC0788i.m952a(), from.getControlId(), from.getParentId(), from.getText());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                        zM961d = viewOnClickListenerC0788i.m955a(from);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 34:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) != null) {
                            ViewOnClickListenerC0788i.m931a(true);
                        } else if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId) == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                            layoutParams.width = -1;
                            viewM1004b.setLayoutParams(layoutParams);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 35:
                        viewOnClickListenerC0788i.m960c(from);
                        break;
                    case 36:
                        viewOnClickListenerC0788i.m957b(from);
                        break;
                    case 37:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId3 = from.getControlId();
                            from.getText();
                            View viewM1004b2 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId3);
                            if (viewM1004b2 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId3) != EnumC0792m.EDIT_TEXT) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                ((EditText) viewM1004b2).setInputType(from.getEditInputType());
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 38:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId4 = from.getControlId();
                            if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId4) == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId4)).ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        string = ((TextView) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).getText().toString();
                                        break;
                                    case 4:
                                        string = ((CheckBox) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).getText().toString();
                                        break;
                                    case 5:
                                        RadioButton radioButton = (RadioButton) ((RadioGroup) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).findViewById(from.getItemIndex());
                                        if (radioButton == null) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            string = radioButton.getText().toString();
                                        }
                                        break;
                                    case 6:
                                        Spinner spinner = (Spinner) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4);
                                        if (from.getItemIndex() >= spinner.getCount()) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(from.getItemIndex()));
                                            string = (String) spinner.getItemAtPosition(from.getItemIndex());
                                        }
                                        break;
                                    default:
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
                            }
                        }
                        break;
                    case 39:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId5 = from.getControlId();
                            String color3 = from.getColor();
                            if (!color3.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                                viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                if (!color3.startsWith("#")) {
                                    color3 = "#" + color3;
                                }
                                int iM1075a = C0806c.m1075a(Color.parseColor(color3));
                                if (viewOnClickListenerC0788i.f1270d.get(controlId5) != null) {
                                    viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId5).intValue()].m996a(iM1075a);
                                } else {
                                    View viewM1004b3 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId5);
                                    if (viewM1004b3 == null) {
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                    } else {
                                        switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId5)).ordinal()]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                ((TextView) viewM1004b3).setTextColor(iM1075a);
                                                break;
                                            case 4:
                                                ((CheckBox) viewM1004b3).setTextColor(iM1075a);
                                                break;
                                            case 5:
                                                RadioButton radioButton2 = (RadioButton) ((RadioGroup) viewM1004b3).findViewById(from.getItemIndex());
                                                if (radioButton2 == null) {
                                                    ViewOnClickListenerC0788i.m931a(false);
                                                } else {
                                                    radioButton2.setTextColor(iM1075a);
                                                }
                                                break;
                                            case 6:
                                                if (from.getItemIndex() >= ((Spinner) viewM1004b3).getCount()) {
                                                    ViewOnClickListenerC0788i.m931a(false);
                                                }
                                                break;
                                            default:
                                                ViewOnClickListenerC0788i.m931a(false);
                                                break;
                                        }
                                    }
                                }
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 40:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId6 = from.getControlId();
                            View viewM1004b4 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId6);
                            if (viewM1004b4 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                String color4 = from.getColor();
                                if (!color4.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                                    viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                                    ViewOnClickListenerC0788i.m931a(false);
                                } else {
                                    if (!color4.startsWith("#")) {
                                        color4 = "#" + color4;
                                    }
                                    int iM1075a2 = C0806c.m1075a(Color.parseColor(color4));
                                    if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId6) == EnumC0792m.BUTTON) {
                                        GradientDrawable gradientDrawable = new GradientDrawable();
                                        gradientDrawable.setColor(iM1075a2);
                                        gradientDrawable.setCornerRadius(viewOnClickListenerC0788i.m956b());
                                        if (Build.VERSION.SDK_INT >= 16) {
                                            viewM1004b4.setBackground(gradientDrawable);
                                        } else {
                                            viewM1004b4.setBackgroundDrawable(gradientDrawable);
                                        }
                                        ViewOnClickListenerC0788i.m931a(true);
                                    } else {
                                        if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId6) != EnumC0792m.SPINNER) {
                                            viewM1004b4.setBackgroundColor(iM1075a2);
                                        }
                                        ViewOnClickListenerC0788i.m931a(true);
                                    }
                                }
                            }
                        }
                        break;
                    case 41:
                        viewOnClickListenerC0788i.m962e(controlId);
                        break;
                    case 42:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b5 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            if (viewM1004b5 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                boolean zIsEnabled = viewM1004b5.isEnabled();
                                if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                                    zIsEnabled = ((EditText) viewM1004b5).isFocusable();
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
                            }
                        }
                        break;
                    case 43:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b6 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            if (viewM1004b6 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                int visibility = viewM1004b6.getVisibility();
                                if (visibility == 0) {
                                    i = 1;
                                } else if (visibility != 4) {
                                    if (visibility != 8) {
                                        i = 1;
                                    } else {
                                        i = 3;
                                    }
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i).build()).build().toByteString());
                            }
                        }
                        break;
                    case 44:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId7 = from.getControlId();
                            if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId7) == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId7)).ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        color = ((TextView) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).getPaint().getColor();
                                        break;
                                    case 4:
                                        color = ((CheckBox) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).getPaint().getColor();
                                        break;
                                    case 5:
                                        RadioButton radioButton3 = (RadioButton) ((RadioGroup) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).findViewById(from.getItemIndex());
                                        if (radioButton3 == null) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            color = radioButton3.getPaint().getColor();
                                        }
                                        break;
                                    default:
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
                            }
                        }
                        break;
                    case 45:
                    case 47:
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 46:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b7 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(from.getControlId());
                            if (viewM1004b7 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewM1004b7.getLayoutParams();
                                layoutParams2.gravity = from.getAlignType();
                                viewM1004b7.setLayoutParams(layoutParams2);
                                new StringBuilder("set gravity ok; ").append(layoutParams2);
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 48:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b8 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(from.getControlId());
                            if (viewM1004b8 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                UiMessage.Padding_Var padding = from.getPadding();
                                new StringBuilder("padding: ").append(padding.getLeft());
                                new StringBuilder("padding: ").append(padding.getTop());
                                new StringBuilder("padding: ").append(padding.getRight());
                                new StringBuilder("padding: ").append(padding.getBottom());
                                viewM1004b8.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    default:
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                }
            }
        } catch (InvalidProtocolBufferException e5) {
            ThrowableExtension.printStackTrace(e5);
        }
    }

    /* JADX INFO: renamed from: e */
    private static Instrumentation m640e() {
        if (f892b == null) {
            f892b = new Instrumentation();
        }
        return f892b;
    }

    public static void Tap(float f, float f2, int i) {
        StringBuilder sb = new StringBuilder("Tap1：x=");
        sb.append(f);
        sb.append(",y = ");
        sb.append(f2);
        sb.append(",delay = ");
        sb.append(i);
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoords.x = f;
            pointerCoords.y = f2;
            pointerCoords.orientation = GetTapRotation();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            StringBuilder sb2 = new StringBuilder("Tap2 ：x=");
            sb2.append(f);
            sb2.append(",y = ");
            sb2.append(f2);
            sb2.append(",delay = ");
            sb2.append(i);
            if (i != 0) {
                try {
                    Thread.sleep(i);
                } catch (Exception unused) {
                }
            }
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            StringBuilder sb3 = new StringBuilder("Tap3 ：x=");
            sb3.append(f);
            sb3.append(",y = ");
            sb3.append(f2);
            sb3.append(",delay = ");
            sb3.append(i);
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyPress(int i) {
        if (MqRunnerLite.getInstance().f1635e != null) {
            MqRunnerLite.getInstance().f1635e.keyPress(i);
        }
    }

    public static void Swipe(float f, float f2, float f3, float f4, int i) {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, f, f2, 0));
            int i2 = i / 11;
            for (int i3 = 1; i3 <= 11; i3++) {
                try {
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    float f5 = i3 / 11.0f;
                    m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, 2, ((f3 - f) * f5) + f, ((f4 - f2) * f5) + f2, 0));
                    if (i2 != 0) {
                        Thread.sleep(i2);
                    }
                } catch (InterruptedException e) {
                    ThrowableExtension.printStackTrace(e);
                    long jUptimeMillis3 = SystemClock.uptimeMillis();
                    m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis3, jUptimeMillis3, 1, f3, f4, 0));
                }
            }
            long jUptimeMillis4 = SystemClock.uptimeMillis();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis4, jUptimeMillis4, 1, f3, f4, 0));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyDown(int i) {
        try {
            m640e().sendKeySync(new KeyEvent(0, i));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void KeyUp(int i) {
        try {
            m640e().sendKeySync(new KeyEvent(1, i));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void sendText(String str, int i) {
        if (MqRunnerLite.getInstance().f1635e != null) {
            MqRunnerLite.getInstance().f1635e.inputText(str);
        }
    }

    public static void TouchMove(int i, float f, float f2, int i2) {
        C0717a c0717aM630b;
        try {
            if (f914x == null || (c0717aM630b = m630b(i)) == null) {
                return;
            }
            float f3 = c0717aM630b.f919c;
            float f4 = c0717aM630b.f920d;
            int i3 = 11;
            if (i2 <= 200) {
                float f5 = f3 - f;
                float f6 = f4 - f2;
                double dSqrt = Math.sqrt((f5 * f5) + (f6 * f6));
                double dMin = Math.min(C0830a.f1527g.getApplicationContext().getResources().getDisplayMetrics().widthPixels, C0830a.f1527g.getApplicationContext().getResources().getDisplayMetrics().heightPixels);
                if (!(2.0d * dSqrt > dMin)) {
                    i3 = ((int) ((dSqrt * 20.0d) / dMin)) + 1;
                }
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            for (int i4 = 1; i4 <= i3; i4++) {
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                float f7 = i4 / i3;
                m626a(i, ((f - f3) * f7) + f3, ((f2 - f4) * f7) + f4);
                int iM622a = m622a(i, 2);
                int iM644g = m644g();
                m646h();
                m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, iM622a, iM644g, f915y, f916z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
                if (i2 != 0) {
                    long jUptimeMillis3 = (((long) i2) - (SystemClock.uptimeMillis() - jUptimeMillis)) / ((long) ((i3 + 1) - i4));
                    if (jUptimeMillis3 >= 10) {
                        Thread.sleep(jUptimeMillis3);
                    }
                }
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void TouchMoveEvent(int i, float f, float f2, int i2) {
        try {
            if (f914x == null || m630b(i) == null) {
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            m626a(i, f, f2);
            int iM622a = m622a(i, 2);
            int iM644g = m644g();
            m646h();
            if (i2 > 10) {
                Thread.sleep(i2 - 15);
            }
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, iM622a, iM644g, f915y, f916z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    public static void TouchUp(int i) {
        try {
            if (f914x == null || m630b(i) == null) {
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            int iM622a = m622a(i, 3);
            int iM644g = m644g();
            m646h();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, iM622a, iM644g, f915y, f916z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            for (int i2 = 0; i2 < 5; i2++) {
                if (i == f914x[i2].f918b) {
                    f914x[i2].f917a = true;
                    f914x[i2].f918b = 0;
                    f914x[i2].f919c = 0.0f;
                    f914x[i2].f920d = 0.0f;
                }
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void ImageQREnCode(String str, String str2, int i) {
        C1194b c1194bMo1517a;
        C1342b c1342b = new C1342b();
        HashMap map = new HashMap();
        map.put(EnumC1245g.CHARACTER_SET, "utf-8");
        map.put(EnumC1245g.ERROR_CORRECTION, EnumC1337f.M);
        map.put(EnumC1245g.MARGIN, 2);
        try {
            c1194bMo1517a = c1342b.mo1517a(str2, EnumC1138a.QR_CODE, 400, 400, map);
        } catch (C1368w e) {
            ThrowableExtension.printStackTrace(e);
            c1194bMo1517a = null;
        }
        int[] iArr = new int[160000];
        for (int i2 = 0; i2 < 400; i2++) {
            for (int i3 = 0; i3 < 400; i3++) {
                if (c1194bMo1517a.m1789a(i2, i3)) {
                    iArr[(i2 * 400) + i3] = -16777216;
                } else {
                    iArr[(i2 * 400) + i3] = -1;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, 400, 400, Bitmap.Config.RGB_565);
        if (bitmapCreateBitmap == null) {
            return;
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (str.endsWith(".png")) {
                bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } else {
                bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e2) {
            Log.e("RootIpcFramework", "ImageQREnCode IOException" + e2.toString());
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String ImageQRDeCode(String str) throws Throwable {
        FileInputStream fileInputStream;
        Bitmap bitmap;
        C1360o c1360o;
        HashMap map = new HashMap();
        map.put(EnumC1233e.CHARACTER_SET, "utf-8");
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                    bitmap = bitmapDecodeStream;
                } catch (Exception e2) {
                    e = e2;
                    ThrowableExtension.printStackTrace(e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            ThrowableExtension.printStackTrace(e3);
                        }
                    }
                    bitmap = null;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        ThrowableExtension.printStackTrace(e4);
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[width * height];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            c1360o = new C1360o(width, height, iArr);
            try {
                C1363r c1363rMo1490a = new C1356k().mo1490a(new C1189c(new C1207j(c1360o)), map);
                Log.i("RootIpcFramework", "syncDecodeQRCode: result is:" + c1363rMo1490a.f2839a);
                return c1363rMo1490a.f2839a;
            } catch (Exception e6) {
                e = e6;
                ThrowableExtension.printStackTrace(e);
                if (c1360o != null) {
                    try {
                        return new C1356k().mo1490a(new C1189c(new C1205h(c1360o)), map).f2839a;
                    } catch (Throwable th3) {
                        ThrowableExtension.printStackTrace(th3);
                        return null;
                    }
                }
                return null;
            }
        } catch (Exception e7) {
            e = e7;
            c1360o = null;
        }
    }

    public static void InsertImageToGallery(String str, int i) throws Throwable {
        File file = new File(str);
        if (file.exists()) {
            if (i == 1) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
                File file2 = new File(str2);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                File file3 = new File(str2 + file.getName());
                if (file3.exists()) {
                    file3.delete();
                }
                try {
                    FileUtils.copyFile(file, file3);
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                file = file3;
            }
            if (Build.VERSION.SDK_INT < 29) {
                try {
                    MediaStore.Images.Media.insertImage(f893c.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
                } catch (FileNotFoundException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("description", "This is an qr image");
                contentValues.put("_display_name", file.getName());
                contentValues.put("mime_type", "image/jpeg");
                contentValues.put("title", "Image.jpg");
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = f893c.getContentResolver();
                Uri uriInsert = contentResolver.insert(uri, contentValues);
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    OutputStream outputStreamOpenOutputStream = uriInsert != null ? contentResolver.openOutputStream(uriInsert) : null;
                    if (outputStreamOpenOutputStream != null) {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i2 = bufferedInputStream.read(bArr);
                            if (i2 == -1) {
                                break;
                            } else {
                                outputStreamOpenOutputStream.write(bArr, 0, i2);
                            }
                        }
                        outputStreamOpenOutputStream.flush();
                    }
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            }
            if (Build.VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(f893c, new String[]{file.getAbsolutePath()}, null, null);
                return;
            }
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            f893c.sendBroadcast(intent);
        }
    }

    public static void InsertVideoToGallery(String str) throws Throwable {
        File file = new File(str);
        if (file.exists()) {
            String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdir();
            }
            File file3 = new File(str2 + file.getName());
            if (file3.exists()) {
                file3.delete();
            }
            try {
                FileUtils.copyFile(file, file3);
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ContentResolver contentResolver = f893c.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", file3.getName());
            contentValues.put("_display_name", file3.getName());
            contentValues.put("mime_type", "video/3gp");
            contentValues.put("datetaken", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("date_modified", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("_data", file3.getAbsolutePath());
            contentValues.put("_size", Long.valueOf(file3.length()));
            f893c.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
        }
    }

    public static void ClearAllPhotos() {
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/anjian/";
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }
        String str2 = "_data like \"" + str + "%\"";
        f893c.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str2, null);
        f893c.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str2, null);
    }

    public static void SaveSnapShot(int[] iArr, int i, int i2, int i3, String str, int i4) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            return;
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, i4, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("RootIpcFramework", "SaveSnapShot IOException" + e.toString());
        }
    }

    public static void SetCellularDataEnable(boolean z) {
        ConnectivityManager connectivityManager = (ConnectivityManager) f893c.getSystemService("connectivity");
        try {
            connectivityManager.getClass().getMethod("setMobileDataEnabled", Boolean.TYPE).invoke(connectivityManager, Boolean.valueOf(z));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void SetWifiEnable(boolean z) {
        ((WifiManager) f893c.getSystemService("wifi")).setWifiEnabled(z);
    }

    public static void SetBTEnable(boolean z) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (z) {
            defaultAdapter.enable();
        } else {
            defaultAdapter.disable();
        }
    }

    public static void SetRotationLockEnable(boolean z) {
        Settings.System.putInt(f893c.getContentResolver(), "accelerometer_rotation", !z ? 1 : 0);
    }

    public static String GetNetType() {
        return f895e.m685i();
    }

    /* JADX WARN: Code duplicated, block: B:64:0x01ac A[Catch: IOException -> 0x0171, TRY_ENTER, TryCatch #4 {IOException -> 0x0171, blocks: (B:47:0x016d, B:51:0x0176, B:64:0x01ac, B:66:0x01b1), top: B:82:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x01b1 A[Catch: IOException -> 0x0171, TRY_LEAVE, TryCatch #4 {IOException -> 0x0171, blocks: (B:47:0x016d, B:51:0x0176, B:64:0x01ac, B:66:0x01b1), top: B:82:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01d5 A[Catch: IOException -> 0x01d0, TRY_LEAVE, TryCatch #6 {IOException -> 0x01d0, blocks: (B:72:0x01cc, B:76:0x01d5), top: B:83:0x01cc }] */
    /* JADX WARN: Code duplicated, block: B:83:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:? A[SYNTHETIC] */
    public static String UrlRequestEx(int i, String str) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        PrintWriter printWriter;
        Exception exc;
        Throwable th2;
        PrintWriter printWriter2;
        BufferedReader bufferedReader2;
        Log.d("RootIpcFramework", "UrlRequestEx: type=" + i + " postData:" + str);
        String str2 = "";
        try {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
                String string2 = jSONObject.getString("data");
                String string3 = jSONObject.getString("code");
                String string4 = jSONObject.getString("setcookie");
                String string5 = jSONObject.getString("cookie");
                if (string3 == null || string3.isEmpty()) {
                    string3 = "UTF-8";
                }
                if (!string.contains("http")) {
                    string = "http://" + string;
                }
                URLConnection uRLConnectionOpenConnection = new URL(string).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                uRLConnectionOpenConnection.setReadTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                if (jSONObject.get("header") instanceof JSONArray) {
                    Log.e("RootIpcFramework", "jsonObject: is JSONArray");
                    JSONObject jSONObject2 = jSONObject.getJSONArray("header").getJSONObject(0);
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        String string6 = jSONObject2.getString(next);
                        Log.e("RootIpcFramework", "UrlRequestEx: key=" + next + "value=" + string6);
                        uRLConnectionOpenConnection.setRequestProperty(next, string6);
                    }
                }
                if (!string5.isEmpty()) {
                    uRLConnectionOpenConnection.setRequestProperty("Cookie", string5);
                }
                if (i == 2) {
                    uRLConnectionOpenConnection.setDoOutput(true);
                    uRLConnectionOpenConnection.setDoInput(true);
                    printWriter = new PrintWriter(new OutputStreamWriter(uRLConnectionOpenConnection.getOutputStream(), string3));
                    try {
                        printWriter.print(string2);
                        printWriter.flush();
                        bufferedReader2 = new BufferedReader(new InputStreamReader(uRLConnectionOpenConnection.getInputStream(), string3));
                        printWriter2 = printWriter;
                    } catch (Exception e) {
                        exc = e;
                        bufferedReader = null;
                        try {
                            Log.e("RootIpcFramework", "UrlRequestEx: Exception:" + exc.toString());
                            ThrowableExtension.printStackTrace(exc);
                            if (printWriter != null) {
                                printWriter.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            Log.e("RootIpcFramework", "UrlRequestEx: result:" + str2);
                            return str2;
                        } catch (Throwable th3) {
                            th2 = th3;
                            th = th2;
                            if (printWriter != null) {
                                try {
                                    printWriter.close();
                                } catch (IOException e2) {
                                    ThrowableExtension.printStackTrace(e2);
                                    throw th;
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                throw th;
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = null;
                        if (printWriter != null) {
                            printWriter.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            throw th;
                        }
                        throw th;
                    }
                } else if (i == 1) {
                    uRLConnectionOpenConnection.connect();
                    bufferedReader2 = new BufferedReader(new InputStreamReader(uRLConnectionOpenConnection.getInputStream(), string3));
                    printWriter2 = null;
                } else {
                    printWriter2 = null;
                    bufferedReader2 = null;
                }
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        str2 = str2 + line;
                    } catch (Exception e3) {
                        bufferedReader = bufferedReader2;
                        printWriter = printWriter2;
                        exc = e3;
                        Log.e("RootIpcFramework", "UrlRequestEx: Exception:" + exc.toString());
                        ThrowableExtension.printStackTrace(exc);
                        if (printWriter != null) {
                            printWriter.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        bufferedReader = bufferedReader2;
                        printWriter = printWriter2;
                        th = th2;
                        if (printWriter != null) {
                            printWriter.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            throw th;
                        }
                        throw th;
                    }
                }
                String headerField = uRLConnectionOpenConnection.getHeaderField("Set-Cookie");
                if (headerField != null && !headerField.isEmpty() && !string4.isEmpty()) {
                    File file = new File(string4);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file.getName(), false);
                    fileWriter.write(headerField);
                    fileWriter.close();
                }
                if (printWriter2 != null) {
                    printWriter2.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
            } catch (IOException e4) {
                ThrowableExtension.printStackTrace(e4);
            }
        } catch (Exception e5) {
            exc = e5;
            bufferedReader = null;
            printWriter = null;
        } catch (Throwable th6) {
            th = th6;
            bufferedReader = null;
            printWriter = null;
        }
        Log.e("RootIpcFramework", "UrlRequestEx: result:" + str2);
        return str2;
    }

    public static void RandomTap(float f, float f2, int i, String str) {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            StringBuilder sb = new StringBuilder("RandomTap：x=");
            sb.append(f);
            sb.append(",y = ");
            sb.append(f2);
            sb.append(",random = ");
            sb.append(i);
            double d = i;
            float fRandom = f + ((int) ((Math.random() * d) - ((double) (i / 2))));
            float fRandom2 = f2 + ((int) ((Math.random() * d) - ((double) (i / 2))));
            StringBuilder sb2 = new StringBuilder("RandomTap：realX=");
            sb2.append(fRandom);
            sb2.append(",realY = ");
            sb2.append(fRandom2);
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            pointerCoords.x = fRandom;
            pointerCoords.y = fRandom2;
            pointerCoords.orientation = GetTapRotation();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            try {
                Thread.sleep(50L);
            } catch (Exception unused) {
            }
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, 1, new int[]{0}, new MotionEvent.PointerCoords[]{pointerCoords}, 0, 0.0f, 0.0f, 0, 0, 0, 0));
            new C0825b(f893c).m1149a((int) fRandom, (int) fRandom2, str);
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void RandomsTap(float f, float f2, int i, String str) {
        float f3;
        StringBuilder sb = new StringBuilder("RandomsTap：x=");
        sb.append(f);
        sb.append(",y = ");
        sb.append(f2);
        sb.append(",random = ");
        sb.append(i);
        double d = i;
        double d2 = i / 2;
        float fRandom = f + ((int) ((Math.random() * d) - d2));
        float fRandom2 = f2 + ((int) ((Math.random() * d) - d2));
        StringBuilder sb2 = new StringBuilder("RandomTap：x2=");
        sb2.append(fRandom);
        sb2.append(",y2 = ");
        sb2.append(fRandom2);
        long jUptimeMillis = SystemClock.uptimeMillis();
        m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, f, f2, 0));
        try {
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            f3 = fRandom;
            try {
                m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, 2, fRandom, fRandom2, 0));
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                e = e;
                ThrowableExtension.printStackTrace(e);
            }
        } catch (InterruptedException e2) {
            e = e2;
            f3 = fRandom;
        }
        long jUptimeMillis3 = SystemClock.uptimeMillis();
        m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis3, jUptimeMillis3, 1, f3, fRandom2, 0));
        new C0825b(f893c).m1149a((int) f3, (int) fRandom2, str);
    }

    public static void MoveZoomIn(float f, float f2, float f3, float f4, int i) {
        Log.e("RootIpcFramework", "MoveZoomIn");
        float f5 = (f + f3) / 2.0f;
        float f6 = (f2 + f4) / 2.0f;
        TouchDown(10123, f5, f6);
        TouchDown(10124, f5, f6);
        int i2 = i / 11;
        if (i2 < 10) {
            i2 = 10;
        }
        float f7 = (f - f5) / 11.0f;
        float f8 = (f2 - f6) / 11.0f;
        float f9 = (f3 - f5) / 11.0f;
        float f10 = (f4 - f6) / 11.0f;
        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        float f11 = f5;
        float f12 = f6;
        float f13 = f12;
        float f14 = f11;
        for (int i3 = 0; i3 < 11; i3++) {
            f14 += f7;
            f12 += f8;
            f11 += f9;
            f13 += f10;
            TouchMoveEvent(10123, f14, f12, 0);
            TouchMoveEvent(10124, f11, f13, 0);
            try {
                Thread.sleep(i2);
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        TouchUp(10123);
        TouchUp(10124);
    }

    public static void MoveZoomOut(float f, float f2, float f3, float f4, int i) {
        Log.e("RootIpcFramework", "MoveZoomOut");
        float f5 = (f + f3) / 2.0f;
        float f6 = (f2 + f4) / 2.0f;
        TouchDown(10125, f, f2);
        TouchDown(10126, f3, f4);
        int i2 = i / 11;
        if (i2 < 10) {
            i2 = 10;
        }
        float f7 = (f5 - f) / 11.0f;
        float f8 = (f6 - f2) / 11.0f;
        float f9 = (f5 - f3) / 11.0f;
        float f10 = (f6 - f4) / 11.0f;
        try {
            Thread.sleep(20L);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        float f11 = f4;
        float f12 = f3;
        float f13 = f2;
        float f14 = f;
        for (int i3 = 0; i3 < 11; i3++) {
            f14 += f7;
            f13 += f8;
            f12 += f9;
            f11 += f10;
            TouchMoveEvent(10125, f14, f13, 0);
            TouchMoveEvent(10126, f12, f11, 0);
            try {
                Thread.sleep(i2);
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        TouchUp(10125);
        TouchUp(10126);
    }

    public static String GetAppPath(String str) {
        try {
            return f893c.getPackageManager().getApplicationInfo(str, 0).sourceDir;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String OcrText(byte[] bArr, int i, int i2, int i3) {
        Log.e("TAG", "GetOrcText: width=" + i2 + " hight=" + i3 + " size=" + i);
        if (!f898h) {
            f898h = initTessBass();
        }
        if (!f898h) {
            return "";
        }
        int i4 = i / 4;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                iArr[i5] = iArr[i5] + ((bArr[(i5 * 4) + i6] & 255) << ((3 - i6) * 8));
            }
        }
        f897g.m2778a(Bitmap.createBitmap(iArr, i2, i3, Bitmap.Config.ARGB_8888));
        return f897g.m2777a();
    }

    public static boolean GetVPNStatus() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp()) {
                    String name = networkInterface.getName();
                    Log.e("GetVPNStatus", "GetVPNStatus name:" + networkInterface.getName());
                    if (name.contains("tun") || name.contains("ppp") || name.contains("pptp")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static void SetVPNEnable(boolean z) {
        f895e.m687k();
    }

    public static boolean IsRoot() {
        String str = System.getenv("PATH");
        new ArrayList();
        for (String str2 : str.split(":")) {
            File file = new File(str2, "su");
            if (file.exists() && file.canExecute()) {
                return true;
            }
        }
        return false;
    }

    public static boolean Is64Bit() {
        Class<?> cls;
        Method declaredMethod;
        Object objInvoke;
        Method declaredMethod2;
        try {
            if (Build.VERSION.SDK_INT < 21 || (cls = Class.forName("dalvik.system.VMRuntime")) == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (objInvoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
                return false;
            }
            Object objInvoke2 = declaredMethod2.invoke(objInvoke, new Object[0]);
            if (objInvoke2 instanceof Boolean) {
                return ((Boolean) objInvoke2).booleanValue();
            }
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
        return false;
    }

    public static void SetControlBarPos(float f, int i, int i2) {
        C0720b c0720b = new C0720b();
        c0720b.f935e = f;
        c0720b.f931a = i;
        c0720b.f932b = i2;
        m627a(4, c0720b);
    }

    public static String GetSdcardDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static void ShowMessage(String str, int i, int i2, int i3) {
        C0720b c0720b = new C0720b();
        c0720b.f931a = i2;
        c0720b.f932b = i3;
        c0720b.f933c = i;
        c0720b.f934d = str;
        m627a(1, c0720b);
    }

    public static void Vibrate(int i) {
        f895e.m671a(i);
    }

    public static void KeepScreen(boolean z) {
        f895e.m674b(z ? 10 : 0);
    }

    public static void AddContact(String str, String str2, String str3) {
        C0807d.m1078a(f893c, str, str2, str3);
    }

    public static void DeleteContact(String str) {
        C0807d.m1077a(f893c, str);
    }

    public static void ClearContacts() {
        C0807d.m1076a(f893c);
    }

    public static void RunApp(String str, String str2) {
        if (MqRunnerLite.getInstance().f1635e != null) {
            MqRunnerLite.getInstance().f1635e.launchApp(str);
        }
    }

    public static void KillApp(String str) {
        if (MqRunnerLite.getInstance().f1635e != null) {
            MqRunnerLite.getInstance().f1635e.killApp(str);
        }
    }

    public static void SpecialFunction(int i, String str) {
        StringBuilder sb = new StringBuilder("SpecialFunction   ");
        sb.append(i);
        sb.append(", ");
        sb.append(str);
        if (MqRunnerLite.getInstance().f1635e != null) {
            MqRunnerLite.getInstance().f1635e.doSpecialFuction(i, str);
        }
    }

    public static void PlaySound(String str) {
        C0741a.m663c(str);
    }

    public static void StopPlay() {
        C0741a.m665f();
    }

    public static void FreeupMemory() {
        f895e.m681e();
    }

    public static int GetDisplayDpi() {
        return f893c.getResources().getDisplayMetrics().densityDpi;
    }

    public static String GetRunningApp() {
        return MqRunnerLite.getInstance().f1635e.getRunningPackages();
    }

    public static String GetForegroundPackage() {
        return MqRunnerLite.getInstance().f1635e != null ? MqRunnerLite.getInstance().f1635e.getForegroundPackage() : "";
    }

    public static void OnPause() {
        f899i.sendEmptyMessage(6);
    }

    public static void OnResume() {
        f899i.sendEmptyMessage(5);
    }

    public static void LockScreen() {
        f895e.m683g();
    }

    public static void UnlockScreen() {
        f895e.m684h();
    }

    public static void SetAutoLockTime(int i) {
        f895e.m677c(i);
    }

    public static String GetDeviceName() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && !TextUtils.isEmpty(defaultAdapter.getName())) {
                return defaultAdapter.getName();
            }
        } catch (Exception unused) {
        }
        return Build.MODEL;
    }

    public static void SetBacklightLevel(int i) {
        if (i > 100) {
            i = 100;
        }
        f895e.m680d(i);
    }

    public static void SetVolumeLevel(int i) {
        if (i > 100) {
            i = 100;
        }
        f895e.m682e(i);
    }

    public static void setSyncReturnValue(int i, String str) {
        f905o = i;
        f906p = str;
        f904n = true;
    }

    public static int ShowMsgBox(String str, int i, int i2, int i3) {
        f904n = false;
        C0720b c0720b = new C0720b();
        c0720b.f931a = i;
        c0720b.f932b = i2;
        c0720b.f933c = i3;
        c0720b.f934d = str;
        m627a(2, c0720b);
        while (!f904n) {
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return f905o;
    }

    public static String ShowInputDialog(String str, int i, int i2) {
        f904n = false;
        C0720b c0720b = new C0720b();
        c0720b.f931a = i;
        c0720b.f932b = i2;
        c0720b.f934d = str;
        m627a(3, c0720b);
        while (!f904n) {
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        new StringBuilder("InputDialog typed text: ").append(f906p);
        return f906p;
    }

    public static boolean SendSimpleEmail(String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str6)) {
            return false;
        }
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName(str);
        simpleEmail.setSmtpPort(465);
        simpleEmail.setAuthentication(str2, str3);
        simpleEmail.setSSLOnConnect(true);
        try {
            simpleEmail.setFrom(str2);
            simpleEmail.setSubject(str4);
            simpleEmail.setMsg(str5);
            for (String str7 : str6.split(";")) {
                simpleEmail.addTo(str7.trim());
            }
            simpleEmail.send();
            return true;
        } catch (EmailException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static float GetTapRotation() {
        switch (GetScreenRotation()) {
            case 0:
                Log.e(f891a, "Tap Tap ROTATION_0");
                return 0.0f;
            case 1:
                Log.e(f891a, "Tap Tap ROTATION_90");
                return f901k;
            case 2:
                Log.e(f891a, "Tap Tap ROTATION_180");
                return f902l;
            case 3:
                Log.e(f891a, "Tap Tap ROTATION_270");
                return f903m;
            default:
                return 0.0f;
        }
    }

    public static byte[] SendUiRequest(byte[] bArr) {
        f899i.obtainMessage(7, bArr).sendToTarget();
        return C0721c.m653a();
    }

    public static byte[] GetUiEvent() {
        return C0721c.m655b();
    }

    public static byte[] GetFloatEvent() {
        Log.e("Injector", "GetFloatEvent: ");
        ByteString byteStringM658d = C0721c.m658d();
        if (byteStringM658d == null) {
            return null;
        }
        return byteStringM658d.toByteArray();
    }

    public static void FloatEventThreadExit() {
        C0721c.m659e();
        Log.e("Injector", "FloatEventThreadExit: ");
    }

    public static String UrlRequest(int i, String str, String str2, int i2) {
        if (i2 <= 0) {
            i2 = 5;
        }
        try {
            if (i == 1) {
                int i3 = i2 * 1000;
                String strBody = HttpRequest.get(m623a(str)).connectTimeout(i3).readTimeout(i3).useCaches(false).body();
                return strBody == null ? "" : strBody;
            }
            if (i != 2) {
                return "";
            }
            int i4 = i2 * 1000;
            String strBody2 = new HttpRequest(str, "POST").header("User-Agent", f909s).followRedirects(false).connectTimeout(i4).readTimeout(i4).useCaches(false).send(str2).body();
            return strBody2 == null ? "" : strBody2;
        } catch (HttpRequest.HttpRequestException unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m623a(String str) {
        try {
            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str);
            while (matcher.find()) {
                String strGroup = matcher.group();
                str = str.replaceAll(strGroup, URLEncoder.encode(strGroup, "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return str;
    }

    public static void OnResponse(int i, String str) {
        C0720b c0720b = new C0720b();
        c0720b.f931a = i;
        c0720b.f934d = str;
        m627a(8, c0720b);
    }

    public static void OnElfCallback(int i, String str) {
        C0720b c0720b = new C0720b();
        c0720b.f931a = i;
        c0720b.f934d = str;
        m627a(8, c0720b);
    }

    public static String GetAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    /* JADX INFO: renamed from: com.cyjh.event.Injector$a */
    private static class C0717a {

        /* JADX INFO: renamed from: a */
        public boolean f917a;

        /* JADX INFO: renamed from: b */
        public int f918b;

        /* JADX INFO: renamed from: c */
        public float f919c;

        /* JADX INFO: renamed from: d */
        public float f920d;

        private C0717a() {
        }

        /* synthetic */ C0717a(byte b) {
            this();
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m642f() {
        if (f914x == null) {
            f914x = new C0717a[5];
            byte b = 0;
            for (int i = 0; i < 5; i++) {
                C0717a c0717a = new C0717a(b);
                c0717a.f917a = true;
                c0717a.f918b = 0;
                c0717a.f919c = 0.0f;
                c0717a.f920d = 0.0f;
                f914x[i] = c0717a;
            }
        }
    }

    public static void releasePointerInfoArray() {
        f914x = null;
    }

    /* JADX INFO: renamed from: a */
    private static void m625a(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (i == f914x[i2].f918b) {
                f914x[i2].f917a = true;
                f914x[i2].f918b = 0;
                f914x[i2].f919c = 0.0f;
                f914x[i2].f920d = 0.0f;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m626a(int i, float f, float f2) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f914x[i2].f917a && f914x[i2].f918b == i) {
                f914x[i2].f918b = i;
                f914x[i2].f919c = f;
                f914x[i2].f920d = f2;
                f914x[i2].f917a = false;
                return;
            }
        }
        for (int i3 = 0; i3 < 5; i3++) {
            if (f914x[i3].f917a) {
                f914x[i3].f918b = i;
                f914x[i3].f919c = f;
                f914x[i3].f920d = f2;
                f914x[i3].f917a = false;
                return;
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static C0717a m630b(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f914x[i2].f917a && f914x[i2].f918b == i) {
                return f914x[i2];
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: c */
    private static int m634c(int i) {
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f914x[i2].f917a && f914x[i2].f918b == i) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX INFO: renamed from: d */
    private static int m637d(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (!f914x[i3].f917a) {
                i2++;
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: g */
    private static int m644g() {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f914x[i2].f917a) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: h */
    private static void m646h() {
        int iM644g = m644g();
        if (iM644g == 0) {
            return;
        }
        f915y = new MotionEvent.PointerProperties[iM644g];
        f916z = new MotionEvent.PointerCoords[iM644g];
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (!f914x[i2].f917a) {
                if (i < iM644g) {
                    MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                    MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                    pointerProperties.id = i2;
                    pointerCoords.x = f914x[i2].f919c;
                    pointerCoords.y = f914x[i2].f920d;
                    pointerCoords.orientation = GetTapRotation();
                    f915y[i] = pointerProperties;
                    f916z[i] = pointerCoords;
                }
                i++;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m622a(int i, int i2) {
        int iM644g = m644g();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= 5) {
                i4 = -1;
                break;
            }
            if (!f914x[i4].f917a && f914x[i4].f918b == i) {
                break;
            }
            i4++;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            if (!f914x[i6].f917a) {
                i5++;
            }
        }
        switch (i2) {
            case 1:
                if (1 != iM644g) {
                    i3 = (i5 * 256) + 5;
                }
                break;
            case 2:
                i3 = 1 == iM644g ? 2 : (i5 * 256) + 2;
                break;
            case 3:
                i3 = 1 == iM644g ? 1 : (i5 * 256) + 6;
                break;
        }
        StringBuilder sb = new StringBuilder("injector>>>>>>touchType:");
        sb.append(i2);
        sb.append("action:");
        sb.append(i3);
        sb.append("---pointerNum:");
        sb.append(iM644g);
        sb.append("---pointerId:");
        sb.append(i5);
        return i3;
    }

    public static void TouchDown(int i, float f, float f2) {
        try {
            if (f914x == null) {
                f914x = new C0717a[5];
                byte b = 0;
                for (int i2 = 0; i2 < 5; i2++) {
                    C0717a c0717a = new C0717a(b);
                    c0717a.f917a = true;
                    c0717a.f918b = 0;
                    c0717a.f919c = 0.0f;
                    c0717a.f920d = 0.0f;
                    f914x[i2] = c0717a;
                }
            }
            if (m644g() >= 5) {
                return;
            }
            m626a(i, f, f2);
            long jUptimeMillis = SystemClock.uptimeMillis();
            int iM622a = m622a(i, 1);
            int iM644g = m644g();
            m646h();
            m640e().sendPointerSync(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, iM622a, iM644g, f915y, f916z, 0, 0, 0.0f, 0.0f, 0, 0, 0, 0));
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static String GetDeviceID() {
        Context applicationContext = C0830a.f1527g.getApplicationContext();
        String deviceId = ((TelephonyManager) applicationContext.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
        if (deviceId == null || deviceId.equals("")) {
            deviceId = C0841f.m1300a(applicationContext);
        }
        return TextUtils.isEmpty(deviceId) ? C0841f.m1300a(C0830a.f1527g.getApplicationContext()) : deviceId;
    }

    public static int GetScreenRotation() {
        return ((WindowManager) C0830a.f1527g.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m628a(C0720b c0720b) {
        if (f894d == null) {
            f894d = new ExToast(f893c);
        }
        f894d.show(c0720b.f934d, c0720b.f931a, c0720b.f932b, c0720b.f933c);
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m632b(C0720b c0720b) {
        new C0781b(f893c, c0720b.f934d, c0720b.f931a, new C07152()).m833a();
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ void m636c(C0720b c0720b) {
        new C0782c(f893c, c0720b.f934d, new C07163()).m839a();
    }

    /* JADX INFO: renamed from: d */
    static /* synthetic */ void m639d(C0720b c0720b) {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onUpdateControlBarPos(c0720b.f935e, c0720b.f931a, c0720b.f932b);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m624a() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onPause();
        }
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ void m631b() {
        OnScriptListener onScriptListener = MqRunnerLite.getInstance().f1631a;
        if (onScriptListener != null) {
            onScriptListener.onResume();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:62:0x01ed  */
    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m629a(byte[] bArr) {
        String string;
        int color;
        try {
            UiMessage.CommandToUi from = UiMessage.CommandToUi.parseFrom(ByteString.copyFrom(bArr));
            if (from != null) {
                ViewOnClickListenerC0788i viewOnClickListenerC0788i = f896f;
                String controlId = from.getControlId();
                UiMessage.CommandToUi.Command_Type command = from.getCommand();
                new StringBuilder("command: ").append(from);
                int i = 2;
                boolean zM961d = true;
                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1278a[command.ordinal()]) {
                    case 1:
                        try {
                            try {
                                viewOnClickListenerC0788i.m954a(from.getPath());
                            } catch (IOException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        } catch (JSONException e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 2:
                        try {
                            try {
                                viewOnClickListenerC0788i.m958b(from.getPath());
                            } catch (JSONException e3) {
                                ThrowableExtension.printStackTrace(e3);
                            }
                        } catch (IOException e4) {
                            ThrowableExtension.printStackTrace(e4);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 3:
                        int width = from.getWidth();
                        int height = from.getHeight();
                        viewOnClickListenerC0788i.f1268b++;
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] = viewOnClickListenerC0788i.f1269c.m868a(controlId, width, height);
                        viewOnClickListenerC0788i.f1270d.put(controlId, Integer.valueOf(viewOnClickListenerC0788i.f1268b));
                        viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b] = new HashMap();
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].f1320b = viewOnClickListenerC0788i.new AnonymousClass1(controlId);
                        viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].f1319a = viewOnClickListenerC0788i.new AnonymousClass2(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 4:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].f1321c) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m1008c();
                            ViewOnClickListenerC0788i.m931a(true);
                            C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                        }
                        break;
                    case 5:
                        ViewOnClickListenerC0788i.m935c(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 6:
                        zM961d = viewOnClickListenerC0788i.m961d(controlId);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 7:
                        viewOnClickListenerC0788i.m959c();
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 8:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0790k c0790k = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b];
                            viewOnClickListenerC0788i.m952a();
                            c0790k.m1002a(controlId, from.getParentId(), from.getWidth(), from.getHeight());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 9:
                        String text = from.getText();
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            zM961d = false;
                        } else {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m1000a(text);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 10:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId).intValue()].m995a()).build()).build().toByteString());
                        }
                        break;
                    case 11:
                        String controlId2 = from.getControlId();
                        String color2 = from.getColor();
                        if (viewOnClickListenerC0788i.f1270d.get(controlId2) == null) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_not_found_ui, controlId2);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else if (!color2.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                            viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            if (!color2.startsWith("#")) {
                                color2 = "#" + color2;
                            }
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId2).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color2)));
                            ViewOnClickListenerC0788i.m931a(true);
                        }
                        break;
                    case 12:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            LinearLayout linearLayoutM870b = c0786g.m870b(controlId, from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.LINE);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(linearLayoutM870b, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 13:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g2 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            TextView textViewM866a = c0786g2.m866a(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.TEXT_VIEW);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(textViewM866a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 14:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g3 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            EditText editTextM873d = c0786g3.m873d(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.EDIT_TEXT);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(editTextM873d, from.getParentId());
                            editTextM873d.addTextChangedListener(viewOnClickListenerC0788i.new a(controlId));
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 15:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g4 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            Button buttonM869b = c0786g4.m869b(controlId, from.getText(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.BUTTON);
                            buttonM869b.setOnClickListener(viewOnClickListenerC0788i);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(buttonM869b, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 16:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g5 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            RadioGroup radioGroupM864a = c0786g5.m864a(controlId, from.getItemTextList(), from.getDefaultItemIndex(), from.getWidth(), from.getHeight());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.RADIIO_GROUP);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(radioGroupM864a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 17:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g6 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            CheckBox checkBoxM862a = c0786g6.m862a(controlId, from.getText(), from.getWidth(), from.getHeight(), from.getDefaultCheckStatus());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.CHECK_BOX);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(checkBoxM862a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 18:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            ImageView imageViewM863a = viewOnClickListenerC0788i.f1269c.m863a(viewOnClickListenerC0788i.m952a(), controlId, from.getWidth(), from.getHeight(), from.getPath());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.IMAGE_VIEW);
                            imageViewM863a.setOnClickListener(viewOnClickListenerC0788i);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(imageViewM863a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 19:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g7 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            WebView webViewM861a = c0786g7.m861a(controlId, from.getWidth(), from.getHeight(), from.getUrl());
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.WEB_VIEW);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(webViewM861a, from.getParentId());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 20:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            C0786g c0786g8 = viewOnClickListenerC0788i.f1269c;
                            viewOnClickListenerC0788i.m952a();
                            List<String> itemTextList = from.getItemTextList();
                            int defaultItemIndex = from.getDefaultItemIndex();
                            from.getWidth();
                            from.getHeight();
                            Spinner spinnerM865a = c0786g8.m865a(controlId, itemTextList, defaultItemIndex);
                            viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].put(controlId, EnumC0792m.SPINNER);
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m999a(spinnerM865a, from.getParentId());
                            spinnerM865a.setOnItemSelectedListener(viewOnClickListenerC0788i);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 21:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1001a(from.getControlId(), from.getHeight());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 22:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] != null) {
                            zM961d = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1003a(viewOnClickListenerC0788i.m952a(), from.getControlId(), from.getParentId(), from.getText());
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                        zM961d = viewOnClickListenerC0788i.m955a(from);
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 34:
                        if (viewOnClickListenerC0788i.f1270d.get(controlId) != null) {
                            ViewOnClickListenerC0788i.m931a(true);
                        } else if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId) == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                            layoutParams.width = -1;
                            viewM1004b.setLayoutParams(layoutParams);
                        }
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 35:
                        viewOnClickListenerC0788i.m960c(from);
                        break;
                    case 36:
                        viewOnClickListenerC0788i.m957b(from);
                        break;
                    case 37:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId3 = from.getControlId();
                            from.getText();
                            View viewM1004b2 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId3);
                            if (viewM1004b2 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId3) != EnumC0792m.EDIT_TEXT) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                ((EditText) viewM1004b2).setInputType(from.getEditInputType());
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 38:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId4 = from.getControlId();
                            if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId4) == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId4)).ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        string = ((TextView) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).getText().toString();
                                        break;
                                    case 4:
                                        string = ((CheckBox) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).getText().toString();
                                        break;
                                    case 5:
                                        RadioButton radioButton = (RadioButton) ((RadioGroup) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4)).findViewById(from.getItemIndex());
                                        if (radioButton == null) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            string = radioButton.getText().toString();
                                        }
                                        break;
                                    case 6:
                                        Spinner spinner = (Spinner) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId4);
                                        if (from.getItemIndex() >= spinner.getCount()) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(from.getItemIndex()));
                                            string = (String) spinner.getItemAtPosition(from.getItemIndex());
                                        }
                                        break;
                                    default:
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
                            }
                        }
                        break;
                    case 39:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId5 = from.getControlId();
                            String color3 = from.getColor();
                            if (!color3.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                                viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                if (!color3.startsWith("#")) {
                                    color3 = "#" + color3;
                                }
                                int iM1075a = C0806c.m1075a(Color.parseColor(color3));
                                if (viewOnClickListenerC0788i.f1270d.get(controlId5) != null) {
                                    viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1270d.get(controlId5).intValue()].m996a(iM1075a);
                                } else {
                                    View viewM1004b3 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId5);
                                    if (viewM1004b3 == null) {
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                    } else {
                                        switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId5)).ordinal()]) {
                                            case 1:
                                            case 2:
                                            case 3:
                                                ((TextView) viewM1004b3).setTextColor(iM1075a);
                                                break;
                                            case 4:
                                                ((CheckBox) viewM1004b3).setTextColor(iM1075a);
                                                break;
                                            case 5:
                                                RadioButton radioButton2 = (RadioButton) ((RadioGroup) viewM1004b3).findViewById(from.getItemIndex());
                                                if (radioButton2 == null) {
                                                    ViewOnClickListenerC0788i.m931a(false);
                                                } else {
                                                    radioButton2.setTextColor(iM1075a);
                                                }
                                                break;
                                            case 6:
                                                if (from.getItemIndex() >= ((Spinner) viewM1004b3).getCount()) {
                                                    ViewOnClickListenerC0788i.m931a(false);
                                                }
                                                break;
                                            default:
                                                ViewOnClickListenerC0788i.m931a(false);
                                                break;
                                        }
                                    }
                                }
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 40:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId6 = from.getControlId();
                            View viewM1004b4 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId6);
                            if (viewM1004b4 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                String color4 = from.getColor();
                                if (!color4.matches("^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})")) {
                                    viewOnClickListenerC0788i.m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                                    ViewOnClickListenerC0788i.m931a(false);
                                } else {
                                    if (!color4.startsWith("#")) {
                                        color4 = "#" + color4;
                                    }
                                    int iM1075a2 = C0806c.m1075a(Color.parseColor(color4));
                                    if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId6) == EnumC0792m.BUTTON) {
                                        GradientDrawable gradientDrawable = new GradientDrawable();
                                        gradientDrawable.setColor(iM1075a2);
                                        gradientDrawable.setCornerRadius(viewOnClickListenerC0788i.m956b());
                                        if (Build.VERSION.SDK_INT >= 16) {
                                            viewM1004b4.setBackground(gradientDrawable);
                                        } else {
                                            viewM1004b4.setBackgroundDrawable(gradientDrawable);
                                        }
                                        ViewOnClickListenerC0788i.m931a(true);
                                    } else {
                                        if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId6) != EnumC0792m.SPINNER) {
                                            viewM1004b4.setBackgroundColor(iM1075a2);
                                        }
                                        ViewOnClickListenerC0788i.m931a(true);
                                    }
                                }
                            }
                        }
                        break;
                    case 41:
                        viewOnClickListenerC0788i.m962e(controlId);
                        break;
                    case 42:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b5 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            if (viewM1004b5 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                boolean zIsEnabled = viewM1004b5.isEnabled();
                                if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                                    zIsEnabled = ((EditText) viewM1004b5).isFocusable();
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
                            }
                        }
                        break;
                    case 43:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b6 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId);
                            if (viewM1004b6 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                int visibility = viewM1004b6.getVisibility();
                                if (visibility == 0) {
                                    i = 1;
                                } else if (visibility != 4) {
                                    if (visibility != 8) {
                                        i = 1;
                                    } else {
                                        i = 3;
                                    }
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i).build()).build().toByteString());
                            }
                        }
                        break;
                    case 44:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            String controlId7 = from.getControlId();
                            if (viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId7) == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                switch (ViewOnClickListenerC0788i.AnonymousClass3.f1279b[((EnumC0792m) viewOnClickListenerC0788i.f1271e[viewOnClickListenerC0788i.f1268b].get(controlId7)).ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                        color = ((TextView) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).getPaint().getColor();
                                        break;
                                    case 4:
                                        color = ((CheckBox) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).getPaint().getColor();
                                        break;
                                    case 5:
                                        RadioButton radioButton3 = (RadioButton) ((RadioGroup) viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(controlId7)).findViewById(from.getItemIndex());
                                        if (radioButton3 == null) {
                                            ViewOnClickListenerC0788i.m931a(false);
                                        } else {
                                            color = radioButton3.getPaint().getColor();
                                        }
                                        break;
                                    default:
                                        ViewOnClickListenerC0788i.m931a(false);
                                        break;
                                }
                                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
                            }
                        }
                        break;
                    case 45:
                    case 47:
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                    case 46:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b7 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(from.getControlId());
                            if (viewM1004b7 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewM1004b7.getLayoutParams();
                                layoutParams2.gravity = from.getAlignType();
                                viewM1004b7.setLayoutParams(layoutParams2);
                                new StringBuilder("set gravity ok; ").append(layoutParams2);
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    case 48:
                        if (viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b] == null) {
                            ViewOnClickListenerC0788i.m931a(false);
                        } else {
                            View viewM1004b8 = viewOnClickListenerC0788i.f1267a[viewOnClickListenerC0788i.f1268b].m1004b(from.getControlId());
                            if (viewM1004b8 == null) {
                                ViewOnClickListenerC0788i.m931a(false);
                            } else {
                                UiMessage.Padding_Var padding = from.getPadding();
                                new StringBuilder("padding: ").append(padding.getLeft());
                                new StringBuilder("padding: ").append(padding.getTop());
                                new StringBuilder("padding: ").append(padding.getRight());
                                new StringBuilder("padding: ").append(padding.getBottom());
                                viewM1004b8.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                                ViewOnClickListenerC0788i.m931a(true);
                            }
                        }
                        break;
                    default:
                        ViewOnClickListenerC0788i.m931a(zM961d);
                        break;
                }
            }
        } catch (InvalidProtocolBufferException e5) {
            ThrowableExtension.printStackTrace(e5);
        }
    }
}
