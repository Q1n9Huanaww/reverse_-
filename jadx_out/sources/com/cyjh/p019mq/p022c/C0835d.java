package com.cyjh.p019mq.p022c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;
import com.cyjh.mobileanjian.ipc.C0741a;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptMessageCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnUiElementCallback;
import com.cyjh.mobileanjian.ipc.p018ui.C0781b;
import com.cyjh.mobileanjian.ipc.p018ui.C0782c;
import com.cyjh.mobileanjian.ipc.p018ui.C0789j;
import com.cyjh.mobileanjian.ipc.p018ui.ViewOnClickListenerC0787h;
import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.cyjh.mobileanjian.ipc.share.proto.C0770b;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C0807d;
import com.cyjh.mobileanjian.ipc.view.C0825b;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.p021b.C0831a;
import com.cyjh.p019mq.p022c.C0833b.AnonymousClass2;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p056i.C1342b;
import com.google.p028a.p056i.p057a.EnumC1337f;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.ime.input.C1631a;
import com.ime.input.InputKb;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.cyjh.mq.c.d */
/* JADX INFO: compiled from: MqmHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0835d {

    /* JADX INFO: renamed from: h */
    private static final int f1577h = 1;

    /* JADX INFO: renamed from: i */
    private static final int f1578i = 2;

    /* JADX INFO: renamed from: j */
    private static final int f1579j = 3;

    /* JADX INFO: renamed from: k */
    private static final int f1580k = 4;

    /* JADX INFO: renamed from: l */
    private static final int f1581l = 5;

    /* JADX INFO: renamed from: m */
    private static final int f1582m = 10;

    /* JADX INFO: renamed from: n */
    private static final int f1583n = 6;

    /* JADX INFO: renamed from: o */
    private static final int f1584o = 7;

    /* JADX INFO: renamed from: p */
    private static final int f1585p = 8;

    /* JADX INFO: renamed from: q */
    private static final int f1586q = 9;

    /* JADX INFO: renamed from: r */
    private static final int f1587r = 16;

    /* JADX INFO: renamed from: s */
    private static final int f1588s = 17;

    /* JADX INFO: renamed from: t */
    private static final int f1589t = 18;

    /* JADX INFO: renamed from: u */
    private static final int f1590u = 19;

    /* JADX INFO: renamed from: v */
    private static final int f1591v = 20;

    /* JADX INFO: renamed from: w */
    private static final int f1592w = 21;

    /* JADX INFO: renamed from: x */
    private static final int f1593x = 22;

    /* JADX INFO: renamed from: y */
    private static final int f1594y = 23;

    /* JADX INFO: renamed from: a */
    Context f1595a;

    /* JADX INFO: renamed from: b */
    C0741a f1596b;

    /* JADX INFO: renamed from: c */
    C0833b f1597c;

    /* JADX INFO: renamed from: d */
    ViewOnClickListenerC0787h f1598d;

    /* JADX INFO: renamed from: g */
    private boolean f1601g;

    /* JADX INFO: renamed from: f */
    private TessBaseAPI f1600f = null;

    /* JADX INFO: renamed from: e */
    Handler f1599e = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mq.c.d.1

        /* JADX INFO: renamed from: b */
        private ExToast f1603b = null;

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            switch (i) {
                case 1:
                    if (this.f1603b == null) {
                        this.f1603b = new ExToast(C0835d.this.f1595a);
                    }
                    Ipc.IpcMessage ipcMessage = (Ipc.IpcMessage) message.obj;
                    this.f1603b.show(ipcMessage.getArg2(0), ipcMessage.getArg1(0), ipcMessage.getArg1(1), ipcMessage.getArg1(2));
                    break;
                case 2:
                    if (C0835d.this.f1597c.f1554k != null) {
                        C0835d.this.f1597c.f1554k.onStartScript();
                    }
                    if (C0835d.this.f1597c.f1555l != null) {
                        C0835d.this.f1597c.f1555l.onStartScript();
                    }
                    break;
                case 3:
                    Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP SCRIPT_STOPPED");
                    ViewOnClickListenerC0787h viewOnClickListenerC0787h = C0835d.this.f1598d;
                    viewOnClickListenerC0787h.f1247f.clear();
                    viewOnClickListenerC0787h.f1248g.clear();
                    viewOnClickListenerC0787h.f1249h.clear();
                    viewOnClickListenerC0787h.m927a();
                    viewOnClickListenerC0787h.f1243b = 0;
                    viewOnClickListenerC0787h.f1250i = 1;
                    for (int i2 = 0; i2 < 32; i2++) {
                        viewOnClickListenerC0787h.f1242a[i2] = null;
                        if (viewOnClickListenerC0787h.f1244c[i2] != null) {
                            viewOnClickListenerC0787h.f1244c[i2].clear();
                        }
                    }
                    viewOnClickListenerC0787h.f1245d = new C0789j[32];
                    viewOnClickListenerC0787h.f1246e = 0;
                    if (this.f1603b != null) {
                        this.f1603b.hide();
                    }
                    if (C0835d.this.f1597c.f1554k != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP mConnection.getBasicScriptListener()");
                        C0835d.this.f1597c.f1554k.onStopScript(message.arg1, (String) message.obj);
                    }
                    if (C0835d.this.f1597c.f1555l != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP mConnection.getOnScriptListener() != null");
                        C0835d.this.f1597c.f1555l.onStopScript(message.arg1, (String) message.obj);
                    }
                    break;
                case 4:
                    if (C0835d.this.f1597c.f1554k != null) {
                        C0835d.this.f1597c.f1554k.onScriptIsRunning();
                    }
                    if (C0835d.this.f1597c.f1555l != null) {
                        C0835d.this.f1597c.f1555l.onScriptIsRunning();
                    }
                    break;
                case 5:
                    if (message.obj == null) {
                        Toast.makeText(C0835d.this.f1595a, message.arg1, 0).show();
                    } else {
                        Toast.makeText(C0835d.this.f1595a, (CharSequence) message.obj, 0).show();
                    }
                    break;
                case 6:
                    Ipc.IpcMessage ipcMessage2 = (Ipc.IpcMessage) message.obj;
                    String arg2 = ipcMessage2.getArg2(0);
                    int arg1 = ipcMessage2.getArg1(0);
                    ipcMessage2.getArg1(0);
                    ipcMessage2.getArg1(0);
                    new C0781b(C0835d.this.f1595a, arg2, arg1, new C0781b.a() { // from class: com.cyjh.mq.c.d.1.1
                        @Override // com.cyjh.mobileanjian.ipc.p018ui.C0781b.a
                        /* JADX INFO: renamed from: a */
                        public final void mo648a(int i3) {
                            C0835d.this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(66).addArg1(i3).build());
                        }
                    }).m833a();
                    break;
                case 7:
                    Ipc.IpcMessage ipcMessage3 = (Ipc.IpcMessage) message.obj;
                    String arg3 = ipcMessage3.getArg2(0);
                    ipcMessage3.getArg1(0);
                    ipcMessage3.getArg1(0);
                    new C0782c(C0835d.this.f1595a, arg3, new C0782c.a() { // from class: com.cyjh.mq.c.d.1.2
                        @Override // com.cyjh.mobileanjian.ipc.p018ui.C0782c.a
                        /* JADX INFO: renamed from: a */
                        public final void mo649a(String str) {
                            C0835d.this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(67).addArg2(str).build());
                        }
                    }).m839a();
                    break;
                case 8:
                    if (C0835d.this.f1597c.f1554k != null) {
                        C0835d.this.f1597c.f1554k.onPause();
                    }
                    if (C0835d.this.f1597c.f1555l != null) {
                        C0835d.this.f1597c.f1555l.onPause();
                    }
                    break;
                case 9:
                    if (C0835d.this.f1597c.f1554k != null) {
                        C0835d.this.f1597c.f1554k.onResume();
                    }
                    if (C0835d.this.f1597c.f1555l != null) {
                        C0835d.this.f1597c.f1555l.onResume();
                    }
                    break;
                case 10:
                    if (this.f1603b != null) {
                        this.f1603b.hide();
                    }
                    break;
                default:
                    switch (i) {
                        case 16:
                            if (C0835d.this.f1597c.f1555l != null) {
                                C0835d.this.f1597c.f1555l.onUpdateControlBarPos(message.arg1 / 100.0f, message.arg2, ((Integer) message.obj).intValue());
                            }
                            break;
                        case 17:
                            OnKeyEventListener onKeyEventListener = C0830a.f1528h;
                            if (onKeyEventListener != null) {
                                onKeyEventListener.onKeyEvent(message.arg1);
                            }
                            break;
                        case 18:
                            try {
                                UiMessage.CommandToUi from = UiMessage.CommandToUi.parseFrom((ByteString) message.obj);
                                if (from != null) {
                                    C0835d.this.f1598d.m928a(from);
                                }
                            } catch (InvalidProtocolBufferException e) {
                                ThrowableExtension.printStackTrace(e);
                                return;
                            }
                            break;
                        case 19:
                            if (C0835d.this.f1597c.f1556m != null) {
                                C0835d.this.f1597c.f1556m.onCallback(message.arg1, (String) message.obj);
                            }
                            break;
                        case 20:
                            if (C0835d.this.f1597c.f1557n != null) {
                                C0835d.this.f1597c.f1557n.callback((String) message.obj);
                            }
                            break;
                        case 21:
                            if (C0835d.this.f1597c.f1558o != null) {
                                C0835d.this.f1597c.f1558o.callback(message.arg1, (String) message.obj);
                            }
                            break;
                        case 22:
                            if (C0835d.this.f1597c.f1560q != null) {
                                C0835d.this.f1597c.f1560q.doSpecialFuction(message.arg1, (String) message.obj);
                            }
                            break;
                        case 23:
                            C0825b c0825b = new C0825b(C0835d.this.f1595a);
                            Ipc.IpcMessage ipcMessage4 = (Ipc.IpcMessage) message.obj;
                            c0825b.m1149a(ipcMessage4.getArg1(0), ipcMessage4.getArg1(1), ipcMessage4.getArg2(0));
                            break;
                    }
                    break;
            }
        }
    };

    /* JADX INFO: renamed from: a */
    private boolean m1250a() {
        File file = new File(this.f1595a.getFilesDir().getAbsolutePath() + "/tessdata/", InterfaceRelatedConstants.ENG_TRAINED_DATA);
        File file2 = new File(this.f1595a.getFilesDir().getAbsolutePath() + "/tessdata/", InterfaceRelatedConstants.CHI_SIM_TRAINED_DATA);
        if (!file.exists() || !file2.exists()) {
            return false;
        }
        this.f1600f = new TessBaseAPI();
        boolean zM2779a = this.f1600f.m2779a(this.f1595a.getFilesDir().getAbsolutePath(), "chi_sim+eng");
        Log.i("MqmHandler", "initTessBass: init ret=" + zM2779a);
        return zM2779a;
    }

    public C0835d(Context context, C0833b c0833b) {
        this.f1597c = null;
        this.f1601g = false;
        this.f1595a = context;
        this.f1598d = new ViewOnClickListenerC0787h(this.f1595a);
        this.f1596b = new C0741a(this.f1595a);
        this.f1597c = c0833b;
        WindowManager windowManager = (WindowManager) this.f1595a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.densityDpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        String strM678d = this.f1596b.m678d();
        String strM676c = this.f1596b.m676c();
        String strM673b = this.f1596b.m673b();
        String str = this.f1595a.getPackageName() + InternalZipConstants.ZIP_FILE_SEPARATOR + InputKb.class.getName();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String str2 = UUID.randomUUID().toString() + ".tmp";
        strM678d = strM678d == null ? "UNKNOW" : strM678d;
        strM676c = strM676c == null ? "UNKNOW" : strM676c;
        File file = new File(absolutePath, str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        Ipc.IpcMessage.Builder cmd = Ipc.IpcMessage.newBuilder().setCmd(14);
        cmd.addArg1(i).addArg1(i2).addArg1(i3).addArg2(strM678d).addArg2(strM676c).addArg2(strM673b).addArg2(str).addArg2(str2).addArg2(absolutePath).addArg2(this.f1595a.getPackageName());
        this.f1597c.m1234a(cmd.build());
        this.f1601g = m1250a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX INFO: renamed from: a */
    private void m1246a(Ipc.IpcMessage ipcMessage) {
        InputConnection currentInputConnection;
        Ipc.IpcMessage ipcMessageBuild;
        if (ipcMessage == null) {
        }
        OnRecordScriptCallback onRecordScriptCallback = this.f1597c.f1551h;
        int cmd = ipcMessage.getCmd();
        switch (cmd) {
            case 4:
                this.f1597c.f1548e = true;
                m1268a(C0852R.string.toast_on_start_script);
                this.f1599e.sendEmptyMessage(2);
                break;
            case 5:
                this.f1599e.sendEmptyMessage(4);
                break;
            case 6:
                this.f1596b.m670a();
                C0741a.m665f();
                int arg1 = ipcMessage.getArg1(0);
                this.f1597c.f1548e = false;
                if (arg1 == 105 || arg1 == 0) {
                    m1268a(C0852R.string.toast_on_stop_script);
                }
                this.f1599e.obtainMessage(3, arg1, 0, ipcMessage.getArg2(0)).sendToTarget();
                break;
            default:
                switch (cmd) {
                    case 27:
                        OnUiElementCallback onUiElementCallback = this.f1597c.f1559p;
                        if (onUiElementCallback != null) {
                            onUiElementCallback.onUiElementback(ipcMessage.getArg2(0));
                        }
                        break;
                    case 28:
                        String strValueOf = String.valueOf((ipcMessage.getArg1(0) << 16) | ipcMessage.getArg1(1));
                        OnUiElementCallback onUiElementCallback2 = this.f1597c.f1559p;
                        if (onUiElementCallback2 != null) {
                            onUiElementCallback2.onScreenShotDone(strValueOf, ipcMessage.getFileData());
                        }
                        break;
                    default:
                        switch (cmd) {
                            case 32:
                                OnScriptMessageCallback onScriptMessageCallback = this.f1597c.f1553j;
                                if (onScriptMessageCallback != null) {
                                    onScriptMessageCallback.onTracePrint(ipcMessage.getArg2(0));
                                }
                                break;
                            case 33:
                                this.f1599e.obtainMessage(1, ipcMessage).sendToTarget();
                                break;
                            case 34:
                                this.f1596b.m671a(ipcMessage.getArg1(0));
                                break;
                            case 35:
                                C0741a.m663c(ipcMessage.getArg2(0));
                                break;
                            case 36:
                                this.f1596b.m672a(ipcMessage.getArg2(0));
                                break;
                            case 37:
                                this.f1596b.m674b(ipcMessage.getArg1(0));
                                break;
                            case 38:
                                C0741a c0741a = this.f1596b;
                                String arg2 = ipcMessage.getArg2(0);
                                C1631a c1631a = c0741a.f949b;
                                if (c1631a.f3579a != null && (currentInputConnection = c1631a.f3579a.getCurrentInputConnection()) != null) {
                                    currentInputConnection.commitText(arg2, arg2.length());
                                }
                                this.f1597c.m1234a(C0770b.m800a(38));
                                break;
                            case 39:
                                this.f1596b.m675b(ipcMessage.getArg2(0));
                                break;
                            case 40:
                                this.f1599e.obtainMessage(16, (int) (ipcMessage.getArg3(0) * 100.0f), ipcMessage.getArg1(0), Integer.valueOf(ipcMessage.getArg1(1))).sendToTarget();
                                break;
                            case 41:
                                this.f1596b.m681e();
                                break;
                            case 42:
                                this.f1599e.sendEmptyMessage(8);
                                break;
                            case 43:
                                this.f1599e.sendEmptyMessage(9);
                                break;
                            default:
                                switch (cmd) {
                                    case 50:
                                        this.f1597c.f1562s.onRpcReturn(ipcMessage.getArg2(0));
                                        break;
                                    case 51:
                                        C0741a.m665f();
                                        break;
                                    case 52:
                                        C0807d.m1078a(this.f1595a, ipcMessage.getArg2(0), ipcMessage.getArg2(1), ipcMessage.getArg2(2));
                                        break;
                                    case 53:
                                        C0807d.m1077a(this.f1595a, ipcMessage.getArg2(0));
                                        break;
                                    case 54:
                                        C0807d.m1076a(this.f1595a);
                                        break;
                                    case 55:
                                        this.f1596b.m683g();
                                        break;
                                    case 56:
                                        this.f1596b.m684h();
                                        break;
                                    case 57:
                                        this.f1596b.m677c(ipcMessage.getArg1(0));
                                        break;
                                    case 58:
                                        this.f1596b.m680d(ipcMessage.getArg1(0));
                                        break;
                                    case 59:
                                        this.f1596b.m682e(ipcMessage.getArg1(0));
                                        break;
                                    default:
                                        switch (cmd) {
                                            case 64:
                                                this.f1599e.obtainMessage(18, ipcMessage.getFileData()).sendToTarget();
                                                break;
                                            case 65:
                                                break;
                                            case 66:
                                                this.f1599e.obtainMessage(6, ipcMessage).sendToTarget();
                                                break;
                                            case 67:
                                                this.f1599e.obtainMessage(7, ipcMessage).sendToTarget();
                                                break;
                                            default:
                                                switch (cmd) {
                                                    case 98:
                                                        this.f1599e.obtainMessage(20, ipcMessage.getArg2(0)).sendToTarget();
                                                        break;
                                                    case 99:
                                                        this.f1599e.obtainMessage(22, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                        break;
                                                    case 100:
                                                        m1269a(ipcMessage.getArg2(0), ipcMessage.getArg2(1));
                                                        break;
                                                    case 101:
                                                        String arg3 = ipcMessage.getArg2(0);
                                                        ipcMessage.getArg2(1);
                                                        File file = new File(arg3);
                                                        ContentResolver contentResolver = this.f1595a.getContentResolver();
                                                        ContentValues contentValues = new ContentValues();
                                                        contentValues.put("title", file.getName());
                                                        contentValues.put("_display_name", file.getName());
                                                        contentValues.put("mime_type", "video/3gp");
                                                        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                                        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                                        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                                                        contentValues.put("_data", file.getAbsolutePath());
                                                        contentValues.put("_size", Long.valueOf(file.length()));
                                                        this.f1595a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
                                                        break;
                                                    case 102:
                                                        String str = "_data like \"" + ipcMessage.getArg2(0) + "%\"";
                                                        this.f1595a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null);
                                                        this.f1595a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str, null);
                                                        break;
                                                    case 103:
                                                        ((WifiManager) this.f1595a.getSystemService("wifi")).setWifiEnabled(ipcMessage.getArg1(0) == 1);
                                                        break;
                                                    case 104:
                                                        Settings.System.putInt(this.f1596b.f948a.getContentResolver(), "accelerometer_rotation", (ipcMessage.getArg1(0) == 1 ? 1 : 0) ^ 1);
                                                        break;
                                                    case 105:
                                                        ?? r3 = ipcMessage.getArg1(0) != 1 ? 0 : 1;
                                                        C0741a c0741a2 = this.f1596b;
                                                        Settings.System.putInt(c0741a2.f948a.getContentResolver(), "airplane_mode_on", r3);
                                                        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
                                                        intent.putExtra("state", (boolean) r3);
                                                        c0741a2.f948a.sendBroadcast(intent);
                                                        break;
                                                    case 106:
                                                        this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(106).addArg2(this.f1596b.m685i()).build());
                                                        break;
                                                    case 107:
                                                        this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(107).addArg2(this.f1596b.m679d(ipcMessage.getArg2(0))).build());
                                                        break;
                                                    case 108:
                                                        this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(108).addArg2(this.f1596b.m686j()).build());
                                                        break;
                                                    case 109:
                                                        String strM1267a = m1267a(ipcMessage.getArg2Bytes(0), ipcMessage.getArg1(0), ipcMessage.getArg1(1), ipcMessage.getArg1(2));
                                                        Log.e("GET_OCR_TEXT", "strOrcText:" + strM1267a);
                                                        this.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(109).addArg2(strM1267a).build());
                                                        break;
                                                    case 110:
                                                        this.f1599e.obtainMessage(23, ipcMessage).sendToTarget();
                                                        break;
                                                    case 111:
                                                        ipcMessage.getArg1(0);
                                                        this.f1596b.m687k();
                                                        break;
                                                    case 112:
                                                        m1248a(ipcMessage.getArg2(0), ipcMessage.getArg2(1), ipcMessage.getArg1(0));
                                                        break;
                                                    default:
                                                        switch (cmd) {
                                                            case 14:
                                                                C0831a.a aVar = new C0831a.a();
                                                                aVar.f1534a = ipcMessage.getArg1(0);
                                                                aVar.f1535b = ipcMessage.getArg1(1);
                                                                aVar.f1536c = ipcMessage.getArg1(2) != 0;
                                                                this.f1597c.f1549f = new C0831a(aVar, (byte) 0);
                                                                this.f1597c.new AnonymousClass2().start();
                                                                break;
                                                            case 19:
                                                                if (onRecordScriptCallback != null) {
                                                                    onRecordScriptCallback.onOpenRecord();
                                                                }
                                                                break;
                                                            case 21:
                                                                int arg4 = ipcMessage.getArg1(0);
                                                                if (onRecordScriptCallback != null) {
                                                                    onRecordScriptCallback.onStartRecord(arg4);
                                                                }
                                                                break;
                                                            case 23:
                                                                String arg5 = ipcMessage.getArg2(0);
                                                                if (onRecordScriptCallback != null) {
                                                                    onRecordScriptCallback.onFinishRecord(arg5);
                                                                }
                                                                break;
                                                            case 25:
                                                                int arg6 = ipcMessage.getArg1(0);
                                                                int arg7 = ipcMessage.getArg1(1);
                                                                int arg8 = ipcMessage.getArg1(2);
                                                                String strValueOf2 = String.valueOf((arg6 << 16) | arg7);
                                                                OnScreenShotCallback onScreenShotCallback = this.f1597c.f1552i;
                                                                if (arg8 == 0) {
                                                                    this.f1597c.f1547d = false;
                                                                }
                                                                if (onScreenShotCallback != null) {
                                                                    onScreenShotCallback.onScreenShotDone(strValueOf2, ipcMessage.getFileData());
                                                                }
                                                                break;
                                                            case 48:
                                                                Object objInvoke = Invocator.invoke(ipcMessage.getPkgName(), ipcMessage.getClassName(), ipcMessage.getMethodName(), ipcMessage.getTypesList(), ipcMessage.getParamsList(), 0);
                                                                new StringBuilder("retObj = ").append(objInvoke);
                                                                if (ipcMessage.getIsSyncCall()) {
                                                                    Ipc.FundType type = ipcMessage.getRetValue().getType();
                                                                    Ipc.ReturnValue.Builder type2 = Ipc.ReturnValue.newBuilder().setType(type);
                                                                    Ipc.IpcMessage.Builder waitId = Ipc.IpcMessage.newBuilder().setCmd(ipcMessage.getCmd()).setIsSyncCall(true).setWaitId(ipcMessage.getWaitId());
                                                                    try {
                                                                        switch (type) {
                                                                            case BOOLEAN:
                                                                                type2.setValBoolean(((Boolean) objInvoke).booleanValue());
                                                                                break;
                                                                            case INT:
                                                                                type2.setValInt(((Integer) objInvoke).intValue());
                                                                                break;
                                                                            case LONG:
                                                                                type2.setValLong(((Long) objInvoke).longValue());
                                                                                break;
                                                                            case FLOAT:
                                                                                type2.setValFloat(((Float) objInvoke).floatValue());
                                                                                break;
                                                                            case DOUBLE:
                                                                                type2.setValDouble(((Double) objInvoke).doubleValue());
                                                                                break;
                                                                            case STRING:
                                                                                type2.setValString(objInvoke == null ? "" : (String) objInvoke);
                                                                                break;
                                                                        }
                                                                    } catch (Throwable th) {
                                                                        ThrowableExtension.printStackTrace(th);
                                                                    }
                                                                    if (type != Ipc.FundType.VOID) {
                                                                        waitId.setRetValue(type2.build());
                                                                    }
                                                                    try {
                                                                        ipcMessageBuild = waitId.build();
                                                                    } catch (UninitializedMessageException e) {
                                                                        ThrowableExtension.printStackTrace(e);
                                                                        ipcMessageBuild = null;
                                                                    }
                                                                    if (ipcMessageBuild != null) {
                                                                        this.f1597c.m1234a(ipcMessageBuild);
                                                                    }
                                                                }
                                                                break;
                                                            case 81:
                                                                this.f1599e.obtainMessage(21, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                                break;
                                                            case 96:
                                                                this.f1599e.obtainMessage(19, ipcMessage.getArg1(0), 0, ipcMessage.getArg2(0)).sendToTarget();
                                                                break;
                                                            case C0769a.f1097aC /* 129 */:
                                                                this.f1599e.obtainMessage(17, ipcMessage.getArg1(0), 0).sendToTarget();
                                                                break;
                                                            case 257:
                                                                OnScriptMessageCallback onScriptMessageCallback2 = this.f1597c.f1553j;
                                                                if (onScriptMessageCallback2 != null) {
                                                                    onScriptMessageCallback2.onDebugMessage(ipcMessage.getFileData());
                                                                }
                                                                break;
                                                            case 65535:
                                                                Iterator<EngineStateObserver> it2 = this.f1597c.f1550g.iterator();
                                                                while (it2.hasNext()) {
                                                                    it2.next().onExit();
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1253b(Ipc.IpcMessage ipcMessage) {
        C0831a.a aVar = new C0831a.a();
        aVar.f1534a = ipcMessage.getArg1(0);
        aVar.f1535b = ipcMessage.getArg1(1);
        aVar.f1536c = ipcMessage.getArg1(2) != 0;
        this.f1597c.f1549f = new C0831a(aVar, (byte) 0);
    }

    /* JADX INFO: renamed from: b */
    private void m1252b() {
        this.f1597c.f1548e = true;
        m1268a(C0852R.string.toast_on_start_script);
        this.f1599e.sendEmptyMessage(2);
    }

    /* JADX INFO: renamed from: c */
    private void m1256c() {
        this.f1599e.sendEmptyMessage(8);
    }

    /* JADX INFO: renamed from: d */
    private void m1259d() {
        this.f1599e.sendEmptyMessage(9);
    }

    /* JADX INFO: renamed from: c */
    private void m1257c(Ipc.IpcMessage ipcMessage) {
        this.f1596b.m670a();
        C0741a.m665f();
        int arg1 = ipcMessage.getArg1(0);
        this.f1597c.f1548e = false;
        if (arg1 == 105 || arg1 == 0) {
            m1268a(C0852R.string.toast_on_stop_script);
        }
        this.f1599e.obtainMessage(3, arg1, 0, ipcMessage.getArg2(0)).sendToTarget();
    }

    /* JADX INFO: renamed from: d */
    private void m1260d(Ipc.IpcMessage ipcMessage) {
        OnScriptMessageCallback onScriptMessageCallback = this.f1597c.f1553j;
        if (onScriptMessageCallback != null) {
            onScriptMessageCallback.onTracePrint(ipcMessage.getArg2(0));
        }
    }

    /* JADX INFO: renamed from: e */
    private void m1262e(Ipc.IpcMessage ipcMessage) {
        OnScriptMessageCallback onScriptMessageCallback = this.f1597c.f1553j;
        if (onScriptMessageCallback != null) {
            onScriptMessageCallback.onDebugMessage(ipcMessage.getFileData());
        }
    }

    /* JADX INFO: renamed from: f */
    private void m1264f(Ipc.IpcMessage ipcMessage) {
        int arg1 = ipcMessage.getArg1(0);
        int arg2 = ipcMessage.getArg1(1);
        int arg3 = ipcMessage.getArg1(2);
        String strValueOf = String.valueOf((arg1 << 16) | arg2);
        OnScreenShotCallback onScreenShotCallback = this.f1597c.f1552i;
        if (arg3 == 0) {
            this.f1597c.f1547d = false;
        }
        if (onScreenShotCallback != null) {
            onScreenShotCallback.onScreenShotDone(strValueOf, ipcMessage.getFileData());
        }
    }

    /* JADX INFO: renamed from: g */
    private void m1265g(Ipc.IpcMessage ipcMessage) {
        String strValueOf = String.valueOf((ipcMessage.getArg1(0) << 16) | ipcMessage.getArg1(1));
        OnUiElementCallback onUiElementCallback = this.f1597c.f1559p;
        if (onUiElementCallback != null) {
            onUiElementCallback.onScreenShotDone(strValueOf, ipcMessage.getFileData());
        }
    }

    /* JADX INFO: renamed from: h */
    private void m1266h(Ipc.IpcMessage ipcMessage) {
        OnUiElementCallback onUiElementCallback = this.f1597c.f1559p;
        if (onUiElementCallback != null) {
            onUiElementCallback.onUiElementback(ipcMessage.getArg2(0));
        }
    }

    /* JADX INFO: renamed from: e */
    private void m1261e() {
        WindowManager windowManager = (WindowManager) this.f1595a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.densityDpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        String strM678d = this.f1596b.m678d();
        String strM676c = this.f1596b.m676c();
        String strM673b = this.f1596b.m673b();
        String str = this.f1595a.getPackageName() + InternalZipConstants.ZIP_FILE_SEPARATOR + InputKb.class.getName();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String str2 = UUID.randomUUID().toString() + ".tmp";
        if (strM678d == null) {
            strM678d = "UNKNOW";
        }
        if (strM676c == null) {
            strM676c = "UNKNOW";
        }
        File file = new File(absolutePath, str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        Ipc.IpcMessage.Builder cmd = Ipc.IpcMessage.newBuilder().setCmd(14);
        cmd.addArg1(i).addArg1(i2).addArg1(i3).addArg2(strM678d).addArg2(strM676c).addArg2(strM673b).addArg2(str).addArg2(str2).addArg2(absolutePath).addArg2(this.f1595a.getPackageName());
        this.f1597c.m1234a(cmd.build());
    }

    /* JADX INFO: renamed from: a */
    final void m1268a(int i) {
        Message messageObtainMessage = this.f1599e.obtainMessage(5);
        messageObtainMessage.arg1 = i;
        messageObtainMessage.sendToTarget();
    }

    /* JADX INFO: renamed from: a */
    private void m1247a(String str) {
        this.f1599e.obtainMessage(5, str).sendToTarget();
    }

    /* JADX INFO: renamed from: f */
    private void m1263f() {
        this.f1599e.sendEmptyMessage(10);
    }

    /* JADX INFO: renamed from: a */
    public final void m1269a(String str, String str2) {
        Log.e("InsertImageToGallery", "InsertImageToGallery:" + str + "|" + str2);
        File file = new File(str);
        if (Build.VERSION.SDK_INT < 29) {
            try {
                MediaStore.Images.Media.insertImage(this.f1595a.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
            } catch (FileNotFoundException e) {
                ThrowableExtension.printStackTrace(e);
            }
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("description", "This is an qr image");
            contentValues.put("_display_name", str2);
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("title", "Image.jpg");
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver contentResolver = this.f1595a.getContentResolver();
            Uri uriInsert = contentResolver.insert(uri, contentValues);
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                OutputStream outputStreamOpenOutputStream = uriInsert != null ? contentResolver.openOutputStream(uriInsert) : null;
                if (outputStreamOpenOutputStream != null) {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = bufferedInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            outputStreamOpenOutputStream.write(bArr, 0, i);
                        }
                    }
                    outputStreamOpenOutputStream.flush();
                }
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            MediaScannerConnection.scanFile(this.f1595a, new String[]{file.getAbsolutePath()}, null, null);
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        this.f1595a.sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: b */
    private void m1254b(String str) {
        File file = new File(str);
        ContentResolver contentResolver = this.f1595a.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("mime_type", "video/3gp");
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("_size", Long.valueOf(file.length()));
        this.f1595a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
    }

    /* JADX INFO: renamed from: c */
    private void m1258c(String str) {
        String str2 = "_data like \"" + str + "%\"";
        this.f1595a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str2, null);
        this.f1595a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str2, null);
    }

    /* JADX INFO: renamed from: a */
    private void m1249a(boolean z) {
        ((WifiManager) this.f1595a.getSystemService("wifi")).setWifiEnabled(z);
    }

    /* JADX INFO: renamed from: a */
    public final String m1267a(ByteString byteString, int i, int i2, int i3) {
        Log.e("TAG", "GetOrcText: width=" + i + " hight=" + i2 + " size=" + i3);
        byte[] byteArray = byteString.toByteArray();
        int i4 = i3 / 4;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < 4; i6++) {
                iArr[i5] = iArr[i5] + ((byteArray[(i5 * 4) + i6] & 255) << ((3 - i6) * 8));
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        if (!this.f1601g) {
            this.f1601g = m1250a();
        }
        if (!this.f1601g) {
            return "";
        }
        this.f1600f.m2778a(bitmapCreateBitmap);
        String strM2777a = this.f1600f.m2777a();
        bitmapCreateBitmap.recycle();
        return strM2777a;
    }

    /* JADX INFO: renamed from: a */
    public static void m1248a(String str, String str2, int i) {
        C1194b c1194bMo1517a;
        C1342b c1342b = new C1342b();
        HashMap map = new HashMap();
        map.put(EnumC1245g.CHARACTER_SET, "utf-8");
        map.put(EnumC1245g.ERROR_CORRECTION, EnumC1337f.M);
        map.put(EnumC1245g.MARGIN, 2);
        try {
            c1194bMo1517a = c1342b.mo1517a(str2, EnumC1138a.QR_CODE, i, i, map);
        } catch (C1368w e) {
            ThrowableExtension.printStackTrace(e);
            c1194bMo1517a = null;
        }
        int[] iArr = new int[i * i];
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (c1194bMo1517a.m1789a(i2, i3)) {
                    iArr[(i2 * i) + i3] = -16777216;
                } else {
                    iArr[(i2 * i) + i3] = -1;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, i, i, Bitmap.Config.RGB_565);
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
            Log.e("RootIpcDex", "ImageQREnCode IOException" + e2.toString());
        }
    }
}
