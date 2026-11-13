package com.cyjh.p019mq.p022c;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputConnection;
import com.cyjh.mobileanjian.ipc.C0741a;
import com.cyjh.mobileanjian.ipc.C0744b;
import com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnMqScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnRpcCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptMessageCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnUiElementCallback;
import com.cyjh.mobileanjian.ipc.interfaces.ScriptStateObserver;
import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.cyjh.mobileanjian.ipc.share.proto.C0770b;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.utils.C0807d;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.p021b.C0831a;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.p019mq.sdk.inf.OnElfCallback;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import com.ime.input.C1631a;
import java.io.File;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: renamed from: com.cyjh.mq.c.b */
/* JADX INFO: compiled from: IpcConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0833b extends C0832a {

    /* JADX INFO: renamed from: t */
    private static final int f1542t = 4096;

    /* JADX INFO: renamed from: u */
    private static final int f1543u = 1024;

    /* JADX INFO: renamed from: a */
    boolean f1544a;

    /* JADX INFO: renamed from: b */
    boolean f1545b;

    /* JADX INFO: renamed from: c */
    Context f1546c;

    /* JADX INFO: renamed from: d */
    public boolean f1547d;

    /* JADX INFO: renamed from: e */
    public boolean f1548e;

    /* JADX INFO: renamed from: f */
    C0831a f1549f;

    /* JADX INFO: renamed from: g */
    List<EngineStateObserver> f1550g;

    /* JADX INFO: renamed from: h */
    OnRecordScriptCallback f1551h;

    /* JADX INFO: renamed from: i */
    public OnScreenShotCallback f1552i;

    /* JADX INFO: renamed from: j */
    OnScriptMessageCallback f1553j;

    /* JADX INFO: renamed from: k */
    BasicScriptListener f1554k;

    /* JADX INFO: renamed from: l */
    public OnScriptListener f1555l;

    /* JADX INFO: renamed from: m */
    OnRequestCallback f1556m;

    /* JADX INFO: renamed from: n */
    OnMqScriptCallback f1557n;

    /* JADX INFO: renamed from: o */
    public OnElfCallback f1558o;

    /* JADX INFO: renamed from: p */
    OnUiElementCallback f1559p;

    /* JADX INFO: renamed from: q */
    public OnSpecialMqCmdCallback f1560q;

    /* JADX INFO: renamed from: r */
    ArrayBlockingQueue<Ipc.IpcMessage> f1561r;

    /* JADX INFO: renamed from: s */
    OnRpcCallback f1562s;

    /* JADX INFO: renamed from: v */
    private C0835d f1563v;

    /* JADX INFO: renamed from: w */
    private Vector<ScriptStateObserver> f1564w;

    /* JADX INFO: renamed from: x */
    private Thread f1565x;

    public C0833b(Context context, Socket socket) {
        super(socket);
        this.f1544a = true;
        this.f1545b = false;
        this.f1563v = null;
        this.f1546c = null;
        this.f1547d = false;
        this.f1548e = false;
        this.f1550g = new ArrayList();
        this.f1564w = new Vector<>();
        this.f1551h = null;
        this.f1552i = null;
        this.f1553j = null;
        this.f1554k = null;
        this.f1555l = null;
        this.f1561r = new ArrayBlockingQueue<>(1024);
        this.f1565x = new Thread("send_thread") { // from class: com.cyjh.mq.c.b.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                super.run();
                while (C0833b.this.f1544a) {
                    try {
                        C0770b c0770b = new C0770b(C0833b.this.f1561r.take());
                        C0833b c0833b = C0833b.this;
                        int length = c0770b.mo801a().length;
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[length + 4]);
                        byteBufferWrap.putInt(length);
                        byteBufferWrap.put(c0770b.mo801a());
                        byteBufferWrap.flip();
                        c0833b.m1190a(byteBufferWrap);
                    } catch (InterruptedException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        };
        this.f1562s = null;
        this.f1546c = context;
        this.f1563v = new C0835d(context, this);
    }

    public C0833b(Context context, LocalSocket localSocket) {
        super(localSocket);
        this.f1544a = true;
        this.f1545b = false;
        this.f1563v = null;
        this.f1546c = null;
        this.f1547d = false;
        this.f1548e = false;
        this.f1550g = new ArrayList();
        this.f1564w = new Vector<>();
        this.f1551h = null;
        this.f1552i = null;
        this.f1553j = null;
        this.f1554k = null;
        this.f1555l = null;
        this.f1561r = new ArrayBlockingQueue<>(1024);
        this.f1565x = new Thread("send_thread") { // from class: com.cyjh.mq.c.b.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                super.run();
                while (C0833b.this.f1544a) {
                    try {
                        C0770b c0770b = new C0770b(C0833b.this.f1561r.take());
                        C0833b c0833b = C0833b.this;
                        int length = c0770b.mo801a().length;
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[length + 4]);
                        byteBufferWrap.putInt(length);
                        byteBufferWrap.put(c0770b.mo801a());
                        byteBufferWrap.flip();
                        c0833b.m1190a(byteBufferWrap);
                    } catch (InterruptedException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        };
        this.f1562s = null;
        this.f1546c = context;
        this.f1563v = new C0835d(context, this);
    }

    /* JADX INFO: renamed from: c */
    private boolean m1210c() {
        return this.f1545b;
    }

    /* JADX INFO: renamed from: d */
    private int m1211d() {
        byte[] bArr = new byte[4];
        if (m1188a(bArr) < 0) {
            return -1;
        }
        return ByteBuffer.wrap(bArr).getInt();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m1234a(Ipc.IpcMessage ipcMessage) {
        this.f1561r.add(ipcMessage);
    }

    /* JADX INFO: renamed from: a */
    private void m1195a(OnRpcCallback onRpcCallback) {
        this.f1562s = onRpcCallback;
    }

    /* JADX INFO: renamed from: f */
    private OnRpcCallback m1216f() {
        return this.f1562s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v42, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v74 */
    /* JADX INFO: renamed from: b */
    public final void m1235b() {
        Ipc.IpcMessage ipcMessageBuild;
        InputConnection currentInputConnection;
        this.f1545b = true;
        this.f1565x.start();
        while (true) {
            if (this.f1544a) {
                Ipc.IpcMessage ipcMessageM1214e = m1214e();
                C0835d c0835d = this.f1563v;
                if (ipcMessageM1214e != null) {
                    OnRecordScriptCallback onRecordScriptCallback = c0835d.f1597c.f1551h;
                    int cmd = ipcMessageM1214e.getCmd();
                    switch (cmd) {
                        case 4:
                            c0835d.f1597c.f1548e = true;
                            c0835d.m1268a(C0852R.string.toast_on_start_script);
                            c0835d.f1599e.sendEmptyMessage(2);
                            break;
                        case 5:
                            c0835d.f1599e.sendEmptyMessage(4);
                            break;
                        case 6:
                            c0835d.f1596b.m670a();
                            C0741a.m665f();
                            int arg1 = ipcMessageM1214e.getArg1(0);
                            c0835d.f1597c.f1548e = false;
                            if (arg1 == 105 || arg1 == 0) {
                                c0835d.m1268a(C0852R.string.toast_on_stop_script);
                            }
                            c0835d.f1599e.obtainMessage(3, arg1, 0, ipcMessageM1214e.getArg2(0)).sendToTarget();
                            break;
                        default:
                            switch (cmd) {
                                case 27:
                                    OnUiElementCallback onUiElementCallback = c0835d.f1597c.f1559p;
                                    if (onUiElementCallback != null) {
                                        onUiElementCallback.onUiElementback(ipcMessageM1214e.getArg2(0));
                                    }
                                    break;
                                case 28:
                                    String strValueOf = String.valueOf((ipcMessageM1214e.getArg1(0) << 16) | ipcMessageM1214e.getArg1(1));
                                    OnUiElementCallback onUiElementCallback2 = c0835d.f1597c.f1559p;
                                    if (onUiElementCallback2 != null) {
                                        onUiElementCallback2.onScreenShotDone(strValueOf, ipcMessageM1214e.getFileData());
                                    }
                                    break;
                                default:
                                    switch (cmd) {
                                        case 32:
                                            OnScriptMessageCallback onScriptMessageCallback = c0835d.f1597c.f1553j;
                                            if (onScriptMessageCallback != null) {
                                                onScriptMessageCallback.onTracePrint(ipcMessageM1214e.getArg2(0));
                                            }
                                            break;
                                        case 33:
                                            c0835d.f1599e.obtainMessage(1, ipcMessageM1214e).sendToTarget();
                                            break;
                                        case 34:
                                            c0835d.f1596b.m671a(ipcMessageM1214e.getArg1(0));
                                            break;
                                        case 35:
                                            C0741a.m663c(ipcMessageM1214e.getArg2(0));
                                            break;
                                        case 36:
                                            c0835d.f1596b.m672a(ipcMessageM1214e.getArg2(0));
                                            break;
                                        case 37:
                                            c0835d.f1596b.m674b(ipcMessageM1214e.getArg1(0));
                                            break;
                                        case 38:
                                            C0741a c0741a = c0835d.f1596b;
                                            String arg2 = ipcMessageM1214e.getArg2(0);
                                            C1631a c1631a = c0741a.f949b;
                                            if (c1631a.f3579a != null && (currentInputConnection = c1631a.f3579a.getCurrentInputConnection()) != null) {
                                                currentInputConnection.commitText(arg2, arg2.length());
                                            }
                                            c0835d.f1597c.m1234a(C0770b.m800a(38));
                                            break;
                                        case 39:
                                            c0835d.f1596b.m675b(ipcMessageM1214e.getArg2(0));
                                            break;
                                        case 40:
                                            c0835d.f1599e.obtainMessage(16, (int) (ipcMessageM1214e.getArg3(0) * 100.0f), ipcMessageM1214e.getArg1(0), Integer.valueOf(ipcMessageM1214e.getArg1(1))).sendToTarget();
                                            break;
                                        case 41:
                                            c0835d.f1596b.m681e();
                                            break;
                                        case 42:
                                            c0835d.f1599e.sendEmptyMessage(8);
                                            break;
                                        case 43:
                                            c0835d.f1599e.sendEmptyMessage(9);
                                            break;
                                        default:
                                            switch (cmd) {
                                                case 50:
                                                    c0835d.f1597c.f1562s.onRpcReturn(ipcMessageM1214e.getArg2(0));
                                                    break;
                                                case 51:
                                                    C0741a.m665f();
                                                    break;
                                                case 52:
                                                    C0807d.m1078a(c0835d.f1595a, ipcMessageM1214e.getArg2(0), ipcMessageM1214e.getArg2(1), ipcMessageM1214e.getArg2(2));
                                                    break;
                                                case 53:
                                                    C0807d.m1077a(c0835d.f1595a, ipcMessageM1214e.getArg2(0));
                                                    break;
                                                case 54:
                                                    C0807d.m1076a(c0835d.f1595a);
                                                    break;
                                                case 55:
                                                    c0835d.f1596b.m683g();
                                                    break;
                                                case 56:
                                                    c0835d.f1596b.m684h();
                                                    break;
                                                case 57:
                                                    c0835d.f1596b.m677c(ipcMessageM1214e.getArg1(0));
                                                    break;
                                                case 58:
                                                    c0835d.f1596b.m680d(ipcMessageM1214e.getArg1(0));
                                                    break;
                                                case 59:
                                                    c0835d.f1596b.m682e(ipcMessageM1214e.getArg1(0));
                                                    break;
                                                default:
                                                    switch (cmd) {
                                                        case 64:
                                                            c0835d.f1599e.obtainMessage(18, ipcMessageM1214e.getFileData()).sendToTarget();
                                                            break;
                                                        case 65:
                                                            break;
                                                        case 66:
                                                            c0835d.f1599e.obtainMessage(6, ipcMessageM1214e).sendToTarget();
                                                            break;
                                                        case 67:
                                                            c0835d.f1599e.obtainMessage(7, ipcMessageM1214e).sendToTarget();
                                                            break;
                                                        default:
                                                            switch (cmd) {
                                                                case 98:
                                                                    c0835d.f1599e.obtainMessage(20, ipcMessageM1214e.getArg2(0)).sendToTarget();
                                                                    break;
                                                                case 99:
                                                                    c0835d.f1599e.obtainMessage(22, ipcMessageM1214e.getArg1(0), 0, ipcMessageM1214e.getArg2(0)).sendToTarget();
                                                                    break;
                                                                case 100:
                                                                    c0835d.m1269a(ipcMessageM1214e.getArg2(0), ipcMessageM1214e.getArg2(1));
                                                                    break;
                                                                case 101:
                                                                    String arg3 = ipcMessageM1214e.getArg2(0);
                                                                    ipcMessageM1214e.getArg2(1);
                                                                    File file = new File(arg3);
                                                                    ContentResolver contentResolver = c0835d.f1595a.getContentResolver();
                                                                    ContentValues contentValues = new ContentValues();
                                                                    contentValues.put("title", file.getName());
                                                                    contentValues.put("_display_name", file.getName());
                                                                    contentValues.put("mime_type", "video/3gp");
                                                                    contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                                                                    contentValues.put("_data", file.getAbsolutePath());
                                                                    contentValues.put("_size", Long.valueOf(file.length()));
                                                                    c0835d.f1595a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)));
                                                                    break;
                                                                case 102:
                                                                    String str = "_data like \"" + ipcMessageM1214e.getArg2(0) + "%\"";
                                                                    c0835d.f1595a.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null);
                                                                    c0835d.f1595a.getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str, null);
                                                                    break;
                                                                case 103:
                                                                    ((WifiManager) c0835d.f1595a.getSystemService("wifi")).setWifiEnabled(ipcMessageM1214e.getArg1(0) == 1);
                                                                    break;
                                                                case 104:
                                                                    Settings.System.putInt(c0835d.f1596b.f948a.getContentResolver(), "accelerometer_rotation", (ipcMessageM1214e.getArg1(0) == 1 ? 1 : 0) ^ 1);
                                                                    break;
                                                                case 105:
                                                                    ?? r2 = ipcMessageM1214e.getArg1(0) == 1 ? 1 : 0;
                                                                    C0741a c0741a2 = c0835d.f1596b;
                                                                    Settings.System.putInt(c0741a2.f948a.getContentResolver(), "airplane_mode_on", r2);
                                                                    Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
                                                                    intent.putExtra("state", (boolean) r2);
                                                                    c0741a2.f948a.sendBroadcast(intent);
                                                                    break;
                                                                case 106:
                                                                    c0835d.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(106).addArg2(c0835d.f1596b.m685i()).build());
                                                                    break;
                                                                case 107:
                                                                    c0835d.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(107).addArg2(c0835d.f1596b.m679d(ipcMessageM1214e.getArg2(0))).build());
                                                                    break;
                                                                case 108:
                                                                    c0835d.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(108).addArg2(c0835d.f1596b.m686j()).build());
                                                                    break;
                                                                case 109:
                                                                    String strM1267a = c0835d.m1267a(ipcMessageM1214e.getArg2Bytes(0), ipcMessageM1214e.getArg1(0), ipcMessageM1214e.getArg1(1), ipcMessageM1214e.getArg1(2));
                                                                    Log.e("GET_OCR_TEXT", "strOrcText:" + strM1267a);
                                                                    c0835d.f1597c.m1234a(Ipc.IpcMessage.newBuilder().setCmd(109).addArg2(strM1267a).build());
                                                                    break;
                                                                case 110:
                                                                    c0835d.f1599e.obtainMessage(23, ipcMessageM1214e).sendToTarget();
                                                                    break;
                                                                case 111:
                                                                    ipcMessageM1214e.getArg1(0);
                                                                    c0835d.f1596b.m687k();
                                                                    break;
                                                                case 112:
                                                                    C0835d.m1248a(ipcMessageM1214e.getArg2(0), ipcMessageM1214e.getArg2(1), ipcMessageM1214e.getArg1(0));
                                                                    break;
                                                                default:
                                                                    switch (cmd) {
                                                                        case 14:
                                                                            C0831a.a aVar = new C0831a.a();
                                                                            aVar.f1534a = ipcMessageM1214e.getArg1(0);
                                                                            aVar.f1535b = ipcMessageM1214e.getArg1(1);
                                                                            aVar.f1536c = ipcMessageM1214e.getArg1(2) != 0;
                                                                            c0835d.f1597c.f1549f = new C0831a(aVar, (byte) 0);
                                                                            c0835d.f1597c.new AnonymousClass2().start();
                                                                            break;
                                                                        case 19:
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onOpenRecord();
                                                                            }
                                                                            break;
                                                                        case 21:
                                                                            int arg4 = ipcMessageM1214e.getArg1(0);
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onStartRecord(arg4);
                                                                            }
                                                                            break;
                                                                        case 23:
                                                                            String arg5 = ipcMessageM1214e.getArg2(0);
                                                                            if (onRecordScriptCallback != null) {
                                                                                onRecordScriptCallback.onFinishRecord(arg5);
                                                                            }
                                                                            break;
                                                                        case 25:
                                                                            int arg6 = ipcMessageM1214e.getArg1(0);
                                                                            int arg7 = ipcMessageM1214e.getArg1(1);
                                                                            int arg8 = ipcMessageM1214e.getArg1(2);
                                                                            String strValueOf2 = String.valueOf((arg6 << 16) | arg7);
                                                                            OnScreenShotCallback onScreenShotCallback = c0835d.f1597c.f1552i;
                                                                            if (arg8 == 0) {
                                                                                c0835d.f1597c.f1547d = false;
                                                                            }
                                                                            if (onScreenShotCallback != null) {
                                                                                onScreenShotCallback.onScreenShotDone(strValueOf2, ipcMessageM1214e.getFileData());
                                                                            }
                                                                            break;
                                                                        case 48:
                                                                            Object objInvoke = Invocator.invoke(ipcMessageM1214e.getPkgName(), ipcMessageM1214e.getClassName(), ipcMessageM1214e.getMethodName(), ipcMessageM1214e.getTypesList(), ipcMessageM1214e.getParamsList(), 0);
                                                                            new StringBuilder("retObj = ").append(objInvoke);
                                                                            if (ipcMessageM1214e.getIsSyncCall()) {
                                                                                Ipc.FundType type = ipcMessageM1214e.getRetValue().getType();
                                                                                Ipc.ReturnValue.Builder type2 = Ipc.ReturnValue.newBuilder().setType(type);
                                                                                Ipc.IpcMessage.Builder waitId = Ipc.IpcMessage.newBuilder().setCmd(ipcMessageM1214e.getCmd()).setIsSyncCall(true).setWaitId(ipcMessageM1214e.getWaitId());
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
                                                                                    c0835d.f1597c.m1234a(ipcMessageBuild);
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 81:
                                                                            c0835d.f1599e.obtainMessage(21, ipcMessageM1214e.getArg1(0), 0, ipcMessageM1214e.getArg2(0)).sendToTarget();
                                                                            break;
                                                                        case 96:
                                                                            c0835d.f1599e.obtainMessage(19, ipcMessageM1214e.getArg1(0), 0, ipcMessageM1214e.getArg2(0)).sendToTarget();
                                                                            break;
                                                                        case C0769a.f1097aC /* 129 */:
                                                                            c0835d.f1599e.obtainMessage(17, ipcMessageM1214e.getArg1(0), 0).sendToTarget();
                                                                            break;
                                                                        case 257:
                                                                            OnScriptMessageCallback onScriptMessageCallback2 = c0835d.f1597c.f1553j;
                                                                            if (onScriptMessageCallback2 != null) {
                                                                                onScriptMessageCallback2.onDebugMessage(ipcMessageM1214e.getFileData());
                                                                            }
                                                                            break;
                                                                        case 65535:
                                                                            Iterator<EngineStateObserver> it2 = c0835d.f1597c.f1550g.iterator();
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
            } else {
                this.f1545b = false;
                return;
            }
        }
    }

    @Override // com.cyjh.p019mq.p022c.C0832a
    /* JADX INFO: renamed from: a */
    protected final void mo1189a() {
        super.mo1189a();
        this.f1544a = false;
        m1234a(C0770b.m800a(3));
    }

    /* JADX INFO: renamed from: g */
    private C0831a m1217g() {
        return this.f1549f;
    }

    /* JADX INFO: renamed from: a */
    private void m1202a(C0831a c0831a) {
        this.f1549f = c0831a;
    }

    /* JADX INFO: renamed from: a */
    private void m1205a(boolean z) {
        this.f1548e = z;
    }

    /* JADX INFO: renamed from: h */
    private boolean m1218h() {
        return this.f1548e;
    }

    /* JADX INFO: renamed from: b */
    private void m1208b(boolean z) {
        this.f1547d = z;
    }

    /* JADX INFO: renamed from: i */
    private boolean m1219i() {
        return this.f1547d;
    }

    /* JADX INFO: renamed from: a */
    private void m1201a(ScriptStateObserver scriptStateObserver) {
        this.f1564w.add(scriptStateObserver);
    }

    /* JADX INFO: renamed from: j */
    private Vector<ScriptStateObserver> m1220j() {
        return this.f1564w;
    }

    /* JADX INFO: renamed from: a */
    private void m1191a(BasicScriptListener basicScriptListener) {
        this.f1554k = basicScriptListener;
    }

    /* JADX INFO: renamed from: k */
    private BasicScriptListener m1221k() {
        return this.f1554k;
    }

    /* JADX INFO: renamed from: a */
    private void m1197a(OnScriptListener onScriptListener) {
        this.f1555l = onScriptListener;
    }

    /* JADX INFO: renamed from: l */
    private OnScriptListener m1222l() {
        return this.f1555l;
    }

    /* JADX INFO: renamed from: a */
    private void m1193a(OnRecordScriptCallback onRecordScriptCallback) {
        this.f1551h = onRecordScriptCallback;
    }

    /* JADX INFO: renamed from: m */
    private OnRecordScriptCallback m1223m() {
        return this.f1551h;
    }

    /* JADX INFO: renamed from: a */
    private void m1196a(OnScreenShotCallback onScreenShotCallback) {
        this.f1552i = onScreenShotCallback;
    }

    /* JADX INFO: renamed from: n */
    private OnScreenShotCallback m1224n() {
        return this.f1552i;
    }

    /* JADX INFO: renamed from: a */
    private void m1198a(OnScriptMessageCallback onScriptMessageCallback) {
        this.f1553j = onScriptMessageCallback;
    }

    /* JADX INFO: renamed from: o */
    private OnScriptMessageCallback m1225o() {
        return this.f1553j;
    }

    /* JADX INFO: renamed from: a */
    private void m1204a(List<EngineStateObserver> list) {
        this.f1550g = list;
    }

    /* JADX INFO: renamed from: a */
    private void m1194a(OnRequestCallback onRequestCallback) {
        this.f1556m = onRequestCallback;
    }

    /* JADX INFO: renamed from: p */
    private OnRequestCallback m1226p() {
        return this.f1556m;
    }

    /* JADX INFO: renamed from: a */
    private void m1192a(OnMqScriptCallback onMqScriptCallback) {
        this.f1557n = onMqScriptCallback;
    }

    /* JADX INFO: renamed from: q */
    private OnMqScriptCallback m1227q() {
        return this.f1557n;
    }

    /* JADX INFO: renamed from: r */
    private OnElfCallback m1228r() {
        return this.f1558o;
    }

    /* JADX INFO: renamed from: a */
    private void m1203a(OnElfCallback onElfCallback) {
        this.f1558o = onElfCallback;
    }

    /* JADX INFO: renamed from: s */
    private OnUiElementCallback m1229s() {
        return this.f1559p;
    }

    /* JADX INFO: renamed from: a */
    private void m1200a(OnUiElementCallback onUiElementCallback) {
        this.f1559p = onUiElementCallback;
    }

    /* JADX INFO: renamed from: com.cyjh.mq.c.b$2, reason: invalid class name */
    /* JADX INFO: compiled from: IpcConnection.java */
    final class AnonymousClass2 extends Thread {
        AnonymousClass2() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Iterator<EngineStateObserver> it2 = C0833b.this.f1550g.iterator();
            while (it2.hasNext()) {
                it2.next().onConnected(C0833b.this);
            }
            if (C0830a.f1530j != null) {
                C0830a.f1530j.onEngineStart(C0833b.this.f1549f.f1531a);
            }
        }
    }

    /* JADX INFO: renamed from: t */
    private void m1230t() {
        new AnonymousClass2().start();
    }

    /* JADX INFO: renamed from: u */
    private void m1231u() {
        Iterator<EngineStateObserver> it2 = this.f1550g.iterator();
        while (it2.hasNext()) {
            it2.next().onExit();
        }
    }

    /* JADX INFO: renamed from: v */
    private void m1232v() {
        C0744b c0744b = new C0744b();
        c0744b.f985a = MqRunner.getInstance().isScriptStarted();
        if (MqRunner.getInstance().isScriptStarted()) {
            this.f1563v.f1599e.sendEmptyMessage(10);
            if (this.f1554k != null) {
                Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP root进程异常终止");
                this.f1554k.onStopScript(1002, "root进程异常终止");
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.cyjh.mq.c.b.3
                @Override // java.lang.Runnable
                public final void run() {
                    if (C0833b.this.f1555l != null) {
                        Log.d("JAVA_RUNNER", "ScriptRunnerLite WHAT_STOP root进程异常终止 2");
                        C0833b.this.f1555l.onStopScript(1002, "root进程异常终止");
                    }
                    ExToast.makeText(C0833b.this.f1546c, C0852R.string.toast_engine_crash_then_restart, ExToast.LENGTH_SHORT).show();
                }
            });
        }
        if (this.f1547d && this.f1552i != null) {
            this.f1547d = false;
            this.f1552i.onScreenShotFailed(1002);
        }
        Iterator<EngineStateObserver> it2 = this.f1550g.iterator();
        while (it2.hasNext()) {
            it2.next().onCrash(c0744b);
        }
    }

    /* JADX INFO: renamed from: w */
    private OnSpecialMqCmdCallback m1233w() {
        return this.f1560q;
    }

    /* JADX INFO: renamed from: a */
    private void m1199a(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
        this.f1560q = onSpecialMqCmdCallback;
    }

    /* JADX INFO: renamed from: e */
    private Ipc.IpcMessage m1214e() {
        byte[] bArr = new byte[4];
        int i = m1188a(bArr) < 0 ? -1 : ByteBuffer.wrap(bArr).getInt();
        if (i < 0) {
            mo1189a();
            m1232v();
            return null;
        }
        byte[] bArr2 = new byte[i];
        if (m1188a(bArr2) < 0) {
            mo1189a();
            m1232v();
            return null;
        }
        try {
            return Ipc.IpcMessage.parseFrom(bArr2);
        } catch (InvalidProtocolBufferException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}
