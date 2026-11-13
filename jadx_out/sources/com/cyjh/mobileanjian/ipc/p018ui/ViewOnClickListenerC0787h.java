package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.p018ui.C0789j.AnonymousClass1;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C0806c;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.sdk.MqRunner;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.p092io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.h */
/* JADX INFO: compiled from: UiManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC0787h implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: j */
    private static final String f1240j = "^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})";

    /* JADX INFO: renamed from: k */
    private static final int f1241k = 32;

    /* JADX INFO: renamed from: l */
    private Context f1251l;

    /* JADX INFO: renamed from: m */
    private C0786g f1252m;

    /* JADX INFO: renamed from: o */
    private LinearLayout f1254o;

    /* JADX INFO: renamed from: a */
    public C0790k[] f1242a = new C0790k[32];

    /* JADX INFO: renamed from: b */
    public int f1243b = 0;

    /* JADX INFO: renamed from: n */
    private HashMap<String, Integer> f1253n = new HashMap<>();

    /* JADX INFO: renamed from: c */
    public HashMap[] f1244c = new HashMap[32];

    /* JADX INFO: renamed from: d */
    public C0789j[] f1245d = new C0789j[32];

    /* JADX INFO: renamed from: e */
    public int f1246e = 0;

    /* JADX INFO: renamed from: f */
    public HashMap<String, Integer> f1247f = new HashMap<>();

    /* JADX INFO: renamed from: g */
    public HashMap<String, EnumC0792m> f1248g = new HashMap<>();

    /* JADX INFO: renamed from: h */
    public HashMap<String, String> f1249h = new HashMap<>();

    /* JADX INFO: renamed from: i */
    public int f1250i = 1;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public ViewOnClickListenerC0787h(Context context) {
        this.f1251l = context;
        this.f1252m = C0786g.m851a(context);
    }

    /* JADX INFO: renamed from: b */
    private int m884b() {
        int i = this.f1250i + 1;
        this.f1250i = i;
        return i;
    }

    /* JADX INFO: renamed from: c */
    private int m890c() {
        return (int) ((this.f1251l.getResources().getDisplayMetrics().densityDpi / 160.0f) * 5.0f);
    }

    /* JADX INFO: renamed from: d */
    private void m894d() {
        this.f1247f.clear();
        this.f1248g.clear();
        this.f1249h.clear();
        m927a();
        this.f1243b = 0;
        this.f1250i = 1;
        for (int i = 0; i < 32; i++) {
            this.f1242a[i] = null;
            if (this.f1244c[i] != null) {
                this.f1244c[i].clear();
            }
        }
        this.f1245d = new C0789j[32];
        this.f1246e = 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:14:0x0070  */
    /* JADX WARN: Code duplicated, block: B:255:0x092c  */
    /* JADX INFO: renamed from: a */
    public final void m928a(UiMessage.CommandToUi commandToUi) {
        String string;
        int color;
        View viewM977a;
        RelativeLayout.LayoutParams layoutParams;
        View viewM977a2;
        final String controlId = commandToUi.getControlId();
        UiMessage.CommandToUi.Command_Type command = commandToUi.getCommand();
        new StringBuilder("command: ").append(commandToUi);
        int i = 2;
        boolean zM908j = true;
        switch (command) {
            case SAVE_PROFILE:
                try {
                    m898e(commandToUi.getPath());
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (JSONException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                m881a(zM908j);
                break;
            case LOAD_PROFILE:
                try {
                    m900f(commandToUi.getPath());
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                } catch (JSONException e4) {
                    ThrowableExtension.printStackTrace(e4);
                }
                m881a(zM908j);
                break;
            case NEW_LAYOUT:
                int width = commandToUi.getWidth();
                int height = commandToUi.getHeight();
                this.f1243b++;
                this.f1242a[this.f1243b] = this.f1252m.m868a(controlId, width, height);
                this.f1253n.put(controlId, Integer.valueOf(this.f1243b));
                this.f1244c[this.f1243b] = new HashMap();
                this.f1242a[this.f1243b].m1007b(new AnonymousClass2(controlId));
                this.f1242a[this.f1243b].m998a(new AnonymousClass3(controlId));
                m881a(zM908j);
                break;
            case SHOW_LAYOUT:
                if (this.f1253n.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_ui, controlId);
                    m881a(false);
                } else if (this.f1242a[this.f1253n.get(controlId).intValue()].m1009d()) {
                    m881a(false);
                } else {
                    this.f1242a[this.f1253n.get(controlId).intValue()].m1008c();
                    m881a(true);
                    MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                }
                break;
            case CLOSE_LAYOUT:
                MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
                m881a(zM908j);
                break;
            case CLOSE_CONTINUE_ACK:
                zM908j = m908j(controlId);
                m881a(zM908j);
                break;
            case CLOSE_EXIT_ACK:
                m927a();
                m881a(zM908j);
                break;
            case NEW_ROW:
                if (this.f1242a[this.f1243b] != null) {
                    C0790k c0790k = this.f1242a[this.f1243b];
                    m884b();
                    c0790k.m1002a(controlId, commandToUi.getParentId(), commandToUi.getWidth(), commandToUi.getHeight());
                }
                m881a(zM908j);
                break;
            case SET_TITLE_TEXT:
                String text = commandToUi.getText();
                if (this.f1253n.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_ui, controlId);
                    zM908j = false;
                } else {
                    this.f1242a[this.f1253n.get(controlId).intValue()].m1000a(text);
                }
                m881a(zM908j);
                break;
            case GET_TITLE_TEXT:
                if (this.f1253n.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_ui, controlId);
                    m881a(false);
                } else {
                    MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f1242a[this.f1253n.get(controlId).intValue()].m995a()).build()).build().toByteString());
                }
                break;
            case SET_TITLE_BACKCOLOR:
                String controlId2 = commandToUi.getControlId();
                String color2 = commandToUi.getColor();
                if (this.f1253n.get(controlId2) == null) {
                    m874a(C0852R.string.ui_show_not_found_ui, controlId2);
                    m881a(false);
                } else if (!color2.matches(f1240j)) {
                    m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m881a(false);
                } else {
                    if (!color2.startsWith("#")) {
                        color2 = "#" + color2;
                    }
                    this.f1242a[this.f1253n.get(controlId2).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color2)));
                    m881a(true);
                }
                break;
            case ADD_LINE:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g = this.f1252m;
                    m884b();
                    LinearLayout linearLayoutM870b = c0786g.m870b(controlId, commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.LINE);
                    this.f1242a[this.f1243b].m999a(linearLayoutM870b, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_TEXT_VIEW:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g2 = this.f1252m;
                    m884b();
                    TextView textViewM866a = c0786g2.m866a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.TEXT_VIEW);
                    this.f1242a[this.f1243b].m999a(textViewM866a, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_EDIT_TEXT:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g3 = this.f1252m;
                    m884b();
                    EditText editTextM873d = c0786g3.m873d(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.EDIT_TEXT);
                    this.f1242a[this.f1243b].m999a(editTextM873d, commandToUi.getParentId());
                    editTextM873d.addTextChangedListener(new a(controlId));
                }
                m881a(zM908j);
                break;
            case ADD_BUTTON:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g4 = this.f1252m;
                    m884b();
                    Button buttonM869b = c0786g4.m869b(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.BUTTON);
                    buttonM869b.setOnClickListener(this);
                    this.f1242a[this.f1243b].m999a(buttonM869b, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_RADIO_GROUP:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g5 = this.f1252m;
                    m884b();
                    RadioGroup radioGroupM864a = c0786g5.m864a(controlId, commandToUi.getItemTextList(), commandToUi.getDefaultItemIndex(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.RADIIO_GROUP);
                    this.f1242a[this.f1243b].m999a(radioGroupM864a, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_CHECK_BOX:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g6 = this.f1252m;
                    m884b();
                    CheckBox checkBoxM862a = c0786g6.m862a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getDefaultCheckStatus());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.CHECK_BOX);
                    this.f1242a[this.f1243b].m999a(checkBoxM862a, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_IMAGE_VIEW:
                if (this.f1242a[this.f1243b] != null) {
                    ImageView imageViewM863a = this.f1252m.m863a(m884b(), controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getPath());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.IMAGE_VIEW);
                    imageViewM863a.setOnClickListener(this);
                    this.f1242a[this.f1243b].m999a(imageViewM863a, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_WEB_VIEW:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g7 = this.f1252m;
                    m884b();
                    WebView webViewM861a = c0786g7.m861a(controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getUrl());
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.WEB_VIEW);
                    this.f1242a[this.f1243b].m999a(webViewM861a, commandToUi.getParentId());
                }
                m881a(zM908j);
                break;
            case ADD_SPINNER:
                if (this.f1242a[this.f1243b] != null) {
                    C0786g c0786g8 = this.f1252m;
                    m884b();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    int defaultItemIndex = commandToUi.getDefaultItemIndex();
                    commandToUi.getWidth();
                    commandToUi.getHeight();
                    Spinner spinnerM865a = c0786g8.m865a(controlId, itemTextList, defaultItemIndex);
                    this.f1244c[this.f1243b].put(controlId, EnumC0792m.SPINNER);
                    this.f1242a[this.f1243b].m999a(spinnerM865a, commandToUi.getParentId());
                    spinnerM865a.setOnItemSelectedListener(this);
                }
                m881a(zM908j);
                break;
            case ADD_TAB_HOST:
                if (this.f1242a[this.f1243b] != null) {
                    this.f1242a[this.f1243b].m1001a(commandToUi.getControlId(), commandToUi.getHeight());
                }
                m881a(zM908j);
                break;
            case ADD_TAB:
                if (this.f1242a[this.f1243b] != null) {
                    zM908j = this.f1242a[this.f1243b].m1003a(m884b(), commandToUi.getControlId(), commandToUi.getParentId(), commandToUi.getText());
                }
                m881a(zM908j);
                break;
            case SET_TEXT_VIEW:
            case SET_EDIT_TEXT:
            case SET_BOTTON:
            case SET_CHECK_BOX:
            case SET_IMAGE_VIEW:
            case SET_WEB_VIEW:
            case SET_RADIO_GROUP:
            case SET_SPINNER:
            case SET_ENABLED:
            case SET_LINE:
            case SET_VISIBLE:
                zM908j = m889b(commandToUi);
                m881a(zM908j);
                break;
            case SET_FULLSCREEN:
                if (this.f1253n.get(controlId) != null) {
                    m881a(true);
                } else if (this.f1244c[this.f1243b].get(controlId) == null) {
                    m881a(false);
                } else {
                    View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                    layoutParams2.width = -1;
                    viewM1004b.setLayoutParams(layoutParams2);
                }
                m881a(zM908j);
                break;
            case SET_TEXT:
                m897e(commandToUi);
                break;
            case SET_FONT:
                m891c(commandToUi);
                break;
            case SET_EDIT_INPUT_TYPE:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    String controlId3 = commandToUi.getControlId();
                    commandToUi.getText();
                    View viewM1004b2 = this.f1242a[this.f1243b].m1004b(controlId3);
                    if (viewM1004b2 == null) {
                        m881a(false);
                    } else if (this.f1244c[this.f1243b].get(controlId3) != EnumC0792m.EDIT_TEXT) {
                        m881a(false);
                    } else {
                        ((EditText) viewM1004b2).setInputType(commandToUi.getEditInputType());
                        m881a(true);
                    }
                }
                break;
            case GET_TEXT:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    String controlId4 = commandToUi.getControlId();
                    if (this.f1244c[this.f1243b].get(controlId4) == null) {
                        m881a(false);
                    } else {
                        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId4)) {
                            case TEXT_VIEW:
                            case EDIT_TEXT:
                            case BUTTON:
                                string = ((TextView) this.f1242a[this.f1243b].m1004b(controlId4)).getText().toString();
                                break;
                            case CHECK_BOX:
                                string = ((CheckBox) this.f1242a[this.f1243b].m1004b(controlId4)).getText().toString();
                                break;
                            case RADIIO_GROUP:
                                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1242a[this.f1243b].m1004b(controlId4)).findViewById(commandToUi.getItemIndex());
                                if (radioButton == null) {
                                    m881a(false);
                                } else {
                                    string = radioButton.getText().toString();
                                }
                                break;
                            case SPINNER:
                                Spinner spinner = (Spinner) this.f1242a[this.f1243b].m1004b(controlId4);
                                if (commandToUi.getItemIndex() >= spinner.getCount()) {
                                    m881a(false);
                                } else {
                                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                                    string = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                                }
                                break;
                            default:
                                m881a(false);
                                break;
                        }
                        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
                    }
                }
                break;
            case SET_TEXTCOLOR:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    String controlId5 = commandToUi.getControlId();
                    String color3 = commandToUi.getColor();
                    if (!color3.matches(f1240j)) {
                        m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                        m881a(false);
                    } else {
                        if (!color3.startsWith("#")) {
                            color3 = "#" + color3;
                        }
                        int iM1075a = C0806c.m1075a(Color.parseColor(color3));
                        if (this.f1253n.get(controlId5) != null) {
                            this.f1242a[this.f1253n.get(controlId5).intValue()].m996a(iM1075a);
                        } else {
                            View viewM1004b3 = this.f1242a[this.f1243b].m1004b(controlId5);
                            if (viewM1004b3 == null) {
                                m881a(false);
                                break;
                            } else {
                                switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId5)) {
                                    case TEXT_VIEW:
                                    case EDIT_TEXT:
                                    case BUTTON:
                                        ((TextView) viewM1004b3).setTextColor(iM1075a);
                                        break;
                                    case CHECK_BOX:
                                        ((CheckBox) viewM1004b3).setTextColor(iM1075a);
                                        break;
                                    case RADIIO_GROUP:
                                        RadioButton radioButton2 = (RadioButton) ((RadioGroup) viewM1004b3).findViewById(commandToUi.getItemIndex());
                                        if (radioButton2 == null) {
                                            m881a(false);
                                        } else {
                                            radioButton2.setTextColor(iM1075a);
                                        }
                                        break;
                                    case SPINNER:
                                        if (commandToUi.getItemIndex() >= ((Spinner) viewM1004b3).getCount()) {
                                            m881a(false);
                                        }
                                        break;
                                    default:
                                        m881a(false);
                                        break;
                                }
                            }
                        }
                        m881a(true);
                    }
                }
                break;
            case SET_BACKCOLOR:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    String controlId6 = commandToUi.getControlId();
                    View viewM1004b4 = this.f1242a[this.f1243b].m1004b(controlId6);
                    if (viewM1004b4 == null) {
                        m881a(false);
                    } else {
                        String color4 = commandToUi.getColor();
                        if (!color4.matches(f1240j)) {
                            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                            m881a(false);
                        } else {
                            if (!color4.startsWith("#")) {
                                color4 = "#" + color4;
                            }
                            int iM1075a2 = C0806c.m1075a(Color.parseColor(color4));
                            if (this.f1244c[this.f1243b].get(controlId6) == EnumC0792m.BUTTON) {
                                GradientDrawable gradientDrawable = new GradientDrawable();
                                gradientDrawable.setColor(iM1075a2);
                                gradientDrawable.setCornerRadius(m890c());
                                viewM1004b4.setBackground(gradientDrawable);
                            } else if (this.f1244c[this.f1243b].get(controlId6) != EnumC0792m.SPINNER) {
                                viewM1004b4.setBackgroundColor(iM1075a2);
                            }
                            m881a(true);
                        }
                    }
                }
                break;
            case GET_VALUE:
                m910k(controlId);
                break;
            case GET_ENABLED:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    View viewM1004b5 = this.f1242a[this.f1243b].m1004b(controlId);
                    if (viewM1004b5 == null) {
                        m881a(false);
                    } else {
                        boolean zIsEnabled = viewM1004b5.isEnabled();
                        if (this.f1244c[this.f1243b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                            zIsEnabled = ((EditText) viewM1004b5).isFocusable();
                        }
                        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
                    }
                }
                break;
            case GET_VISIBLE:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    View viewM1004b6 = this.f1242a[this.f1243b].m1004b(controlId);
                    if (viewM1004b6 == null) {
                        m881a(false);
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
                        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i).build()).build().toByteString());
                    }
                }
                break;
            case GET_TEXTCOLOR:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    String controlId7 = commandToUi.getControlId();
                    if (this.f1244c[this.f1243b].get(controlId7) == null) {
                        m881a(false);
                    } else {
                        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId7)) {
                            case TEXT_VIEW:
                            case EDIT_TEXT:
                            case BUTTON:
                                color = ((TextView) this.f1242a[this.f1243b].m1004b(controlId7)).getPaint().getColor();
                                break;
                            case CHECK_BOX:
                                color = ((CheckBox) this.f1242a[this.f1243b].m1004b(controlId7)).getPaint().getColor();
                                break;
                            case RADIIO_GROUP:
                                RadioButton radioButton3 = (RadioButton) ((RadioGroup) this.f1242a[this.f1243b].m1004b(controlId7)).findViewById(commandToUi.getItemIndex());
                                if (radioButton3 == null) {
                                    m881a(false);
                                } else {
                                    color = radioButton3.getPaint().getColor();
                                }
                                break;
                            default:
                                m881a(false);
                                break;
                        }
                        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
                    }
                }
                break;
            case GET_BACKCOLOR:
            case SET_WINDOW_GRAVITY:
            default:
                m881a(zM908j);
                break;
            case SET_CONTROL_GRAVITY:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    View viewM1004b7 = this.f1242a[this.f1243b].m1004b(commandToUi.getControlId());
                    if (viewM1004b7 == null) {
                        m881a(false);
                    } else {
                        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) viewM1004b7.getLayoutParams();
                        layoutParams3.gravity = commandToUi.getAlignType();
                        viewM1004b7.setLayoutParams(layoutParams3);
                        new StringBuilder("set gravity ok; ").append(layoutParams3);
                        m881a(true);
                    }
                }
                break;
            case SET_PADDING:
                if (this.f1242a[this.f1243b] == null) {
                    m881a(false);
                } else {
                    View viewM1004b8 = this.f1242a[this.f1243b].m1004b(commandToUi.getControlId());
                    if (viewM1004b8 == null) {
                        m881a(false);
                    } else {
                        UiMessage.Padding_Var padding = commandToUi.getPadding();
                        new StringBuilder("padding: ").append(padding.getLeft());
                        new StringBuilder("padding: ").append(padding.getTop());
                        new StringBuilder("padding: ").append(padding.getRight());
                        new StringBuilder("padding: ").append(padding.getBottom());
                        viewM1004b8.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                        m881a(true);
                    }
                }
                break;
            case NEW_FLOAT:
                m878a(controlId, commandToUi.getLeft(), commandToUi.getTop(), commandToUi.getWidth(), commandToUi.getHeight());
                m881a(zM908j);
                break;
            case SHOW_FLOAT:
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else if (this.f1245d[this.f1247f.get(controlId).intValue()].m984d()) {
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].m978a();
                    m881a(true);
                    m879a(controlId, UiMessage.ControlEvent.Event_Type.ON_SHOW);
                }
                break;
            case IS_FW_TOUCHMOVE:
                boolean boolParam = commandToUi.getBoolParam();
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].f1286e = boolParam;
                    m881a(true);
                }
                break;
            case HIDE_FLOAT:
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else if (!this.f1245d[this.f1247f.get(controlId).intValue()].m984d()) {
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].m981b();
                    m881a(true);
                }
                break;
            case CLOSE_FLOAT:
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].m983c();
                    this.f1247f.remove(controlId);
                    m879a(controlId, UiMessage.ControlEvent.Event_Type.ON_CLOSE);
                    m881a(true);
                }
                break;
            case FW_OPACITY:
                int intParam = commandToUi.getIntParam();
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].setOpacity(intParam);
                    m881a(true);
                }
                break;
            case SET_FW_SMOOTH:
                int intParam2 = commandToUi.getIntParam();
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else {
                    this.f1245d[this.f1247f.get(controlId).intValue()].m979a(intParam2);
                    this.f1245d[this.f1247f.get(controlId).intValue()].m985e();
                    m881a(true);
                }
                break;
            case SET_FW_BG:
                String strParam = commandToUi.getStrParam();
                if (!strParam.matches(f1240j)) {
                    m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m881a(false);
                } else {
                    if (!strParam.startsWith("#")) {
                        strParam = "#" + strParam;
                    }
                    int iM1075a3 = C0806c.m1075a(Color.parseColor(strParam));
                    if (this.f1247f.get(controlId) != null) {
                        this.f1245d[this.f1247f.get(controlId).intValue()].m982b(iM1075a3);
                        this.f1245d[this.f1247f.get(controlId).intValue()].m985e();
                    } else {
                        String str = this.f1249h.get(controlId);
                        if (str == null) {
                            m881a(false);
                            break;
                        } else {
                            View viewM977a3 = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId);
                            if (viewM977a3 == null) {
                                m881a(false);
                                break;
                            } else if (this.f1248g.get(controlId) == EnumC0792m.BUTTON) {
                                GradientDrawable gradientDrawable2 = new GradientDrawable();
                                gradientDrawable2.setCornerRadii(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f});
                                gradientDrawable2.setColor(iM1075a3);
                                viewM977a3.setBackground(gradientDrawable2);
                            } else if (this.f1248g.get(controlId) != EnumC0792m.SPINNER) {
                                viewM977a3.setBackgroundColor(iM1075a3);
                            }
                        }
                    }
                    m881a(true);
                }
                break;
            case SET_FW_PIC:
                String strParam2 = commandToUi.getStrParam();
                if (this.f1247f.get(controlId) != null) {
                    C0789j c0789j = this.f1245d[this.f1247f.get(controlId).intValue()];
                    c0789j.f1290i = strParam2;
                    Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(c0789j.f1290i);
                    if (Build.VERSION.SDK_INT >= 16) {
                        c0789j.f1284c.setBackground(new BitmapDrawable(bitmapDecodeFile));
                    }
                    c0789j.f1284c.getBackground().setAlpha((c0789j.f1289h * 255) / 100);
                    this.f1245d[this.f1247f.get(controlId).intValue()].m985e();
                } else {
                    String str2 = this.f1249h.get(controlId);
                    if (str2 == null || (viewM977a = this.f1245d[this.f1247f.get(str2).intValue()].m977a(controlId)) == null) {
                        zM908j = false;
                    } else if (this.f1248g.get(controlId) == EnumC0792m.IMAGE_VIEW) {
                        ((ImageView) viewM977a).setImageURI(Uri.fromFile(new File(strParam2)));
                    }
                }
                m881a(zM908j);
                break;
            case ADD_FW_BUTTON:
                String parentId = commandToUi.getParentId();
                if (this.f1245d[this.f1247f.get(parentId).intValue()] != null) {
                    C0786g c0786g9 = this.f1252m;
                    m884b();
                    String text2 = commandToUi.getText();
                    int width2 = commandToUi.getWidth();
                    int height2 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    Button button = (Button) c0786g9.f1237a.inflate(C0852R.layout.ui_float_button, (ViewGroup) null);
                    button.setTag(controlId);
                    button.setText(text2);
                    button.setAllCaps(false);
                    if (width2 == 0 && height2 == 0) {
                        layoutParams = new RelativeLayout.LayoutParams(-1, 100);
                    } else {
                        layoutParams = new RelativeLayout.LayoutParams(width2, height2);
                    }
                    button.setLayoutParams(layoutParams);
                    this.f1248g.put(controlId, EnumC0792m.BUTTON);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.h.1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ViewOnClickListenerC0787h.m879a(controlId, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                        }
                    });
                    this.f1245d[this.f1247f.get(parentId).intValue()].m980a(button);
                    this.f1249h.put(controlId, parentId);
                    button.setX(commandToUi.getLeft());
                    button.setY(commandToUi.getTop());
                }
                m881a(zM908j);
                break;
            case ADD_FW_TEXT_VIEW:
                String parentId2 = commandToUi.getParentId();
                if (this.f1245d[this.f1247f.get(parentId2).intValue()] != null) {
                    C0786g c0786g10 = this.f1252m;
                    m884b();
                    String text3 = commandToUi.getText();
                    int width3 = commandToUi.getWidth();
                    int height3 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    TextView textView = (TextView) c0786g10.f1237a.inflate(C0852R.layout.ui_float_text_view, (ViewGroup) null);
                    textView.setTag(controlId);
                    textView.setText(text3);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                    if (width3 != -2 && height3 != -2) {
                        layoutParams4.width = width3;
                        layoutParams4.height = height3;
                    }
                    textView.setLayoutParams(layoutParams4);
                    this.f1248g.put(controlId, EnumC0792m.TEXT_VIEW);
                    this.f1245d[this.f1247f.get(parentId2).intValue()].m980a(textView);
                    this.f1249h.put(controlId, parentId2);
                    textView.setX(commandToUi.getLeft());
                    textView.setY(commandToUi.getTop());
                }
                m881a(zM908j);
                break;
            case ADD_FW_IMAGE_VIEW:
                String parentId3 = commandToUi.getParentId();
                if (this.f1245d[this.f1247f.get(parentId3).intValue()] != null) {
                    C0786g c0786g11 = this.f1252m;
                    m884b();
                    String text4 = commandToUi.getText();
                    int width4 = commandToUi.getWidth();
                    int height4 = commandToUi.getHeight();
                    commandToUi.getLeft();
                    commandToUi.getTop();
                    ImageView imageViewM871c = c0786g11.m871c(controlId, text4, width4, height4);
                    this.f1248g.put(controlId, EnumC0792m.IMAGE_VIEW);
                    this.f1245d[this.f1247f.get(parentId3).intValue()].m980a(imageViewM871c);
                    this.f1249h.put(controlId, parentId3);
                    imageViewM871c.setX(commandToUi.getLeft());
                    imageViewM871c.setY(commandToUi.getTop());
                }
                m881a(zM908j);
                break;
            case FW_ZORDER:
                if (this.f1247f.get(controlId) == null) {
                    m874a(C0852R.string.ui_show_not_found_float, controlId);
                    m881a(false);
                } else {
                    if (this.f1245d[this.f1247f.get(controlId).intValue()].m984d()) {
                        C0789j c0789j2 = this.f1245d[this.f1247f.get(controlId).intValue()];
                        if (c0789j2.f1284c != null && c0789j2.m984d()) {
                            c0789j2.f1282a.removeView(c0789j2.f1284c);
                            c0789j2.f1282a.addView(c0789j2.f1284c, c0789j2.f1283b);
                        }
                    }
                    m881a(true);
                }
                break;
            case SET_FW_TEXT_COLOR:
                String controlId8 = commandToUi.getControlId();
                String strParam3 = commandToUi.getStrParam();
                if (!strParam3.matches(f1240j)) {
                    m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m881a(false);
                } else {
                    if (!strParam3.startsWith("#")) {
                        strParam3 = "#" + strParam3;
                    }
                    int iM1075a4 = C0806c.m1075a(Color.parseColor(strParam3));
                    String str3 = this.f1249h.get(controlId8);
                    if (str3 == null) {
                        m881a(false);
                    } else {
                        View viewM977a4 = this.f1245d[this.f1247f.get(str3).intValue()].m977a(controlId8);
                        if (viewM977a4 == null) {
                            m881a(false);
                        } else {
                            if (this.f1248g.get(controlId8) == EnumC0792m.BUTTON) {
                                ((Button) viewM977a4).setTextColor(iM1075a4);
                            }
                            if (this.f1248g.get(controlId8) == EnumC0792m.TEXT_VIEW) {
                                ((TextView) viewM977a4).setTextColor(iM1075a4);
                            }
                            this.f1245d[this.f1247f.get(str3).intValue()].m985e();
                            m881a(true);
                        }
                    }
                }
                break;
            case FW_GET_VALUE:
                JSONObject jSONObject = new JSONObject();
                if (this.f1247f.get(controlId) != null) {
                    try {
                        WindowManager.LayoutParams params = this.f1245d[this.f1247f.get(controlId).intValue()].getParams();
                        jSONObject.put("Left", params.x);
                        jSONObject.put("Top", params.y);
                        jSONObject.put("Width", params.width);
                        jSONObject.put("Height", params.height);
                    } catch (JSONException e5) {
                        ThrowableExtension.printStackTrace(e5);
                    }
                } else {
                    String str4 = this.f1249h.get(controlId);
                    EnumC0792m enumC0792m = this.f1248g.get(controlId);
                    if (enumC0792m == null) {
                        m874a(C0852R.string.ui_show_not_found_widget, controlId);
                        MqRunner.getInstance().mo1306a(jSONObject.toString());
                    } else {
                        int i2 = AnonymousClass4.f1262b[enumC0792m.ordinal()];
                        if (i2 == 1) {
                            TextView textView2 = (TextView) this.f1245d[this.f1247f.get(str4).intValue()].m977a(controlId);
                            try {
                                jSONObject.put("Left", textView2.getLeft());
                                jSONObject.put("Top", textView2.getTop());
                                jSONObject.put("Text", textView2.getText());
                                jSONObject.put("Width", textView2.getWidth());
                                jSONObject.put("Height", textView2.getHeight());
                            } catch (JSONException e6) {
                                ThrowableExtension.printStackTrace(e6);
                            }
                        } else if (i2 == 3) {
                            Button button2 = (Button) this.f1245d[this.f1247f.get(str4).intValue()].m977a(controlId);
                            try {
                                jSONObject.put("Left", button2.getLeft());
                                jSONObject.put("Top", button2.getTop());
                                jSONObject.put("Text", button2.getText());
                                jSONObject.put("Width", button2.getWidth());
                                jSONObject.put("Height", button2.getHeight());
                            } catch (JSONException e7) {
                                ThrowableExtension.printStackTrace(e7);
                            }
                        } else if (i2 == 7) {
                            ImageView imageView = (ImageView) this.f1245d[this.f1247f.get(str4).intValue()].m977a(controlId);
                            try {
                                jSONObject.put("Left", imageView.getLeft());
                                jSONObject.put("Top", imageView.getTop());
                                jSONObject.put("Width", imageView.getWidth());
                                jSONObject.put("Height", imageView.getHeight());
                            } catch (JSONException e8) {
                                ThrowableExtension.printStackTrace(e8);
                            }
                        }
                    }
                    m881a(zM908j);
                    break;
                }
                new StringBuilder("FWGetValue json: ").append(jSONObject.toString());
                MqRunner.getInstance().mo1306a(jSONObject.toString());
                m881a(zM908j);
                break;
            case SET_FW_BUTTON:
            case SET_FW_TEXT_VIEW:
            case SET_FW_IMAGE_VIEW:
                m916n(commandToUi);
                break;
            case SET_FW_TEXT_SIZE:
                zM908j = m918o(commandToUi);
                m881a(zM908j);
                break;
            case SET_FW_ENABLE:
            case SET_FW_VISIBLE:
                zM908j = m920p(commandToUi);
                m881a(zM908j);
                break;
            case GET_FW_ENABLE:
            case GET_FW_TEXT_COLOR:
            case GET_FW_VISIBLE:
                zM908j = m921q(commandToUi);
                m881a(zM908j);
                break;
            case FW_SET_LEFT:
                String controlId9 = commandToUi.getControlId();
                if (this.f1247f.get(controlId9) != null) {
                    this.f1245d[this.f1247f.get(controlId9).intValue()].setFloatLeft(commandToUi.getIntParam());
                } else {
                    String str5 = this.f1249h.get(controlId9);
                    if (str5 == null || (viewM977a2 = this.f1245d[this.f1247f.get(str5).intValue()].m977a(controlId9)) == null) {
                        zM908j = false;
                    } else if (this.f1248g.get(controlId9) == EnumC0792m.BUTTON || this.f1248g.get(controlId9) == EnumC0792m.TEXT_VIEW || this.f1248g.get(controlId9) == EnumC0792m.IMAGE_VIEW) {
                        new StringBuilder("view.setX ").append(commandToUi.getIntParam());
                        viewM977a2.setX(commandToUi.getIntParam());
                    }
                }
                m881a(zM908j);
                break;
            case FW_SET_TOP:
                zM908j = m923s(commandToUi);
                m881a(zM908j);
                break;
            case FW_SET_WIDTH:
                zM908j = m924t(commandToUi);
                m881a(zM908j);
                break;
            case FW_SET_HEIGHT:
                zM908j = m925u(commandToUi);
                m881a(zM908j);
                break;
            case FW_SET_TEXT:
                zM908j = m926v(commandToUi);
                m881a(zM908j);
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m881a(boolean z) {
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(z).build().toByteString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX INFO: renamed from: b */
    private boolean m889b(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            return false;
        }
        String controlId = commandToUi.getControlId();
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m874a(C0852R.string.ui_show_not_found_widget, controlId);
            return false;
        }
        switch (commandToUi.getCommand()) {
            case SET_TEXT_VIEW:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.TEXT_VIEW) {
                    return false;
                }
                TextView textView = (TextView) viewM1004b;
                textView.setText(commandToUi.getText());
                textView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_EDIT_TEXT:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.EDIT_TEXT) {
                    return false;
                }
                EditText editText = (EditText) viewM1004b;
                editText.setText(commandToUi.getText());
                editText.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_BOTTON:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.BUTTON) {
                    return false;
                }
                Button button = (Button) viewM1004b;
                button.setText(commandToUi.getText());
                button.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_CHECK_BOX:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.CHECK_BOX) {
                    return false;
                }
                CheckBox checkBox = (CheckBox) viewM1004b;
                checkBox.setChecked(commandToUi.getDefaultCheckStatus());
                checkBox.setText(commandToUi.getText());
                checkBox.getLayoutParams().width = commandToUi.getWidth();
                checkBox.getLayoutParams().height = commandToUi.getHeight();
                return true;
            case SET_IMAGE_VIEW:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.IMAGE_VIEW) {
                    return false;
                }
                ImageView imageView = (ImageView) viewM1004b;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                layoutParams.width = commandToUi.getWidth();
                layoutParams.height = commandToUi.getHeight();
                imageView.setLayoutParams(layoutParams);
                imageView.setImageURI(Uri.fromFile(new File(commandToUi.getPath())));
                return true;
            case SET_WEB_VIEW:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.WEB_VIEW) {
                    return false;
                }
                String url = commandToUi.getUrl();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                WebView webView = (WebView) viewM1004b;
                webView.loadUrl(url);
                webView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_RADIO_GROUP:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.RADIIO_GROUP) {
                    return false;
                }
                RadioGroup radioGroup = (RadioGroup) viewM1004b;
                radioGroup.clearCheck();
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                layoutParams2.weight = commandToUi.getWidth();
                layoutParams2.height = commandToUi.getHeight();
                radioGroup.setLayoutParams(layoutParams2);
                radioGroup.removeAllViews();
                List<String> itemTextList = commandToUi.getItemTextList();
                for (int i = 0; i < itemTextList.size(); i++) {
                    RadioButton radioButton = (RadioButton) LayoutInflater.from(this.f1251l).inflate(C0852R.layout.ui_show_radio_button, (ViewGroup) null);
                    radioButton.setId(i);
                    radioButton.setText(itemTextList.get(i));
                    radioButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    radioButton.setPadding(0, m890c(), 0, m890c());
                    radioGroup.addView(radioButton);
                }
                ((RadioButton) radioGroup.findViewById(commandToUi.getDefaultItemIndex() < radioGroup.getChildCount() ? commandToUi.getDefaultItemIndex() : 0)).setChecked(true);
                return true;
            case SET_SPINNER:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.SPINNER) {
                    return false;
                }
                Spinner spinner = (Spinner) viewM1004b;
                ArrayAdapter arrayAdapter = new ArrayAdapter(this.f1251l, C0852R.layout.ui_show_spinner_dropdown_item, commandToUi.getItemTextList());
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                spinner.setSelection(commandToUi.getDefaultItemIndex() < arrayAdapter.getCount() ? commandToUi.getDefaultItemIndex() : 0);
                return true;
            case SET_ENABLED:
                if (this.f1244c[this.f1243b].get(controlId) == EnumC0792m.RADIIO_GROUP) {
                    viewM1004b.setEnabled(commandToUi.getEnabledStatus());
                    RadioGroup radioGroup2 = (RadioGroup) viewM1004b;
                    for (int i2 = 0; i2 < radioGroup2.getChildCount(); i2++) {
                        radioGroup2.getChildAt(i2).setEnabled(commandToUi.getEnabledStatus());
                    }
                } else if (this.f1244c[this.f1243b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                    new StringBuilder("set edittext enabled. ").append(commandToUi.getEnabledStatus());
                    EditText editText2 = (EditText) viewM1004b;
                    editText2.setFocusable(commandToUi.getEnabledStatus());
                    editText2.setFocusableInTouchMode(commandToUi.getEnabledStatus());
                } else {
                    viewM1004b.setEnabled(commandToUi.getEnabledStatus());
                }
                return true;
            case SET_LINE:
                if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.LINE) {
                    return false;
                }
                ((LinearLayout) viewM1004b).setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_VISIBLE:
                switch (commandToUi.getVisibleStatus()) {
                    case 2:
                        viewM1004b.setVisibility(4);
                        return true;
                    case 3:
                        viewM1004b.setVisibility(8);
                        return true;
                    default:
                        viewM1004b.setVisibility(0);
                        return true;
                }
            default:
                return true;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m875a(String str) {
        if (this.f1253n.get(str) != null) {
            m881a(true);
            return;
        }
        if (this.f1244c[this.f1243b].get(str) == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(str);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
        layoutParams.width = -1;
        viewM1004b.setLayoutParams(layoutParams);
    }

    /* JADX INFO: renamed from: c */
    private void m891c(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        new StringBuilder("SET FONT type: ").append(commandToUi.getFontType());
        String controlId = commandToUi.getControlId();
        Typeface typefaceCreate = Typeface.create(commandToUi.getFontType(), 0);
        if (typefaceCreate == null) {
            m881a(false);
            return;
        }
        int fontSize = commandToUi.getFontSize() < 0 ? 14 : commandToUi.getFontSize();
        if (this.f1253n.get(controlId) != null && this.f1242a[this.f1253n.get(controlId).intValue()] != null) {
            this.f1242a[this.f1253n.get(controlId).intValue()].m997a(typefaceCreate, fontSize);
            m881a(true);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        if (this.f1244c[this.f1243b].get(controlId) == null) {
            m881a(false);
            return;
        }
        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                TextView textView = (TextView) viewM1004b;
                textView.setTypeface(typefaceCreate);
                textView.setTextSize(2, fontSize);
                break;
            case CHECK_BOX:
                CheckBox checkBox = (CheckBox) viewM1004b;
                checkBox.setTypeface(typefaceCreate);
                checkBox.setTextSize(2, fontSize);
                break;
            case RADIIO_GROUP:
                RadioGroup radioGroup = (RadioGroup) viewM1004b;
                radioGroup.findViewById(commandToUi.getItemIndex());
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    radioButton.setTypeface(typefaceCreate);
                    radioButton.setTextSize(2, fontSize);
                }
                break;
            default:
                m881a(false);
                return;
        }
        m881a(true);
    }

    /* JADX INFO: renamed from: d */
    private void m895d(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        commandToUi.getText();
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m881a(false);
        } else if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.EDIT_TEXT) {
            m881a(false);
        } else {
            ((EditText) viewM1004b).setInputType(commandToUi.getEditInputType());
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: e */
    private void m897e(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String text = commandToUi.getText();
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        if (this.f1244c[this.f1243b].get(controlId) == null) {
            m881a(false);
            return;
        }
        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                ((TextView) viewM1004b).setText(text);
                break;
            case CHECK_BOX:
                ((CheckBox) viewM1004b).setText(text);
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) viewM1004b).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m881a(false);
                    return;
                }
                radioButton.setText(text);
                break;
            case SPINNER:
                Spinner spinner = (Spinner) viewM1004b;
                if (commandToUi.getItemIndex() >= spinner.getCount()) {
                    m881a(false);
                    return;
                }
                ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < arrayAdapter.getCount(); i++) {
                    arrayList.add(arrayAdapter.getItem(i));
                }
                arrayList.set(commandToUi.getItemIndex(), text);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(this.f1251l, C0852R.layout.ui_show_spinner_dropdown_item, arrayList);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                arrayAdapter2.notifyDataSetChanged();
                break;
                break;
            default:
                m881a(false);
                return;
        }
        m881a(true);
    }

    /* JADX INFO: renamed from: f */
    private void m899f(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (!color.matches(f1240j)) {
            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m881a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(color));
        if (this.f1253n.get(controlId) != null) {
            this.f1242a[this.f1253n.get(controlId).intValue()].m996a(iM1075a);
            m881a(true);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                ((TextView) viewM1004b).setTextColor(iM1075a);
                break;
            case CHECK_BOX:
                ((CheckBox) viewM1004b).setTextColor(iM1075a);
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) viewM1004b).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m881a(false);
                    return;
                }
                radioButton.setTextColor(iM1075a);
                break;
            case SPINNER:
                if (commandToUi.getItemIndex() >= ((Spinner) viewM1004b).getCount()) {
                    m881a(false);
                    return;
                }
                break;
            default:
                m881a(false);
                return;
        }
        m881a(true);
    }

    /* JADX INFO: renamed from: g */
    private void m901g(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        View viewM1004b = this.f1242a[this.f1243b].m1004b(controlId);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        String color = commandToUi.getColor();
        if (!color.matches(f1240j)) {
            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m881a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(color));
        if (this.f1244c[this.f1243b].get(controlId) == EnumC0792m.BUTTON) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(iM1075a);
            gradientDrawable.setCornerRadius(m890c());
            viewM1004b.setBackground(gradientDrawable);
            m881a(true);
            return;
        }
        if (this.f1244c[this.f1243b].get(controlId) != EnumC0792m.SPINNER) {
            viewM1004b.setBackgroundColor(iM1075a);
        }
        m881a(true);
    }

    /* JADX INFO: renamed from: h */
    private void m903h(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(commandToUi.getControlId());
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
        layoutParams.gravity = commandToUi.getAlignType();
        viewM1004b.setLayoutParams(layoutParams);
        new StringBuilder("set gravity ok; ").append(layoutParams);
        m881a(true);
    }

    /* JADX INFO: renamed from: i */
    private void m905i(UiMessage.CommandToUi commandToUi) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(commandToUi.getControlId());
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        UiMessage.Padding_Var padding = commandToUi.getPadding();
        new StringBuilder("padding: ").append(padding.getLeft());
        new StringBuilder("padding: ").append(padding.getTop());
        new StringBuilder("padding: ").append(padding.getRight());
        new StringBuilder("padding: ").append(padding.getBottom());
        viewM1004b.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        m881a(true);
    }

    /* JADX INFO: renamed from: b */
    private void m886b(String str) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(str);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        boolean zIsEnabled = viewM1004b.isEnabled();
        if (this.f1244c[this.f1243b].get(str) == EnumC0792m.EDIT_TEXT) {
            zIsEnabled = ((EditText) viewM1004b).isFocusable();
        }
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002b  */
    /* JADX INFO: renamed from: c */
    private void m892c(String str) {
        int i;
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(str);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        int visibility = viewM1004b.getVisibility();
        if (visibility == 0) {
            i = 1;
        } else if (visibility == 4) {
            i = 2;
        } else if (visibility != 8) {
            i = 1;
        } else {
            i = 3;
        }
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: j */
    private void m907j(UiMessage.CommandToUi commandToUi) {
        int color;
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f1244c[this.f1243b].get(controlId) == null) {
            m881a(false);
            return;
        }
        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                color = ((TextView) this.f1242a[this.f1243b].m1004b(controlId)).getPaint().getColor();
                break;
            case CHECK_BOX:
                color = ((CheckBox) this.f1242a[this.f1243b].m1004b(controlId)).getPaint().getColor();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1242a[this.f1243b].m1004b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m881a(false);
                    return;
                }
                color = radioButton.getPaint().getColor();
                break;
            default:
                m881a(false);
                return;
        }
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: d */
    private void m896d(String str) {
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        View viewM1004b = this.f1242a[this.f1243b].m1004b(str);
        if (viewM1004b == null) {
            m881a(false);
            return;
        }
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue("#" + Integer.toHexString(C0806c.m1075a(viewM1004b.getSolidColor()) & ViewCompat.MEASURED_SIZE_MASK)).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: k */
    private void m909k(UiMessage.CommandToUi commandToUi) {
        String string;
        if (this.f1242a[this.f1243b] == null) {
            m881a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f1244c[this.f1243b].get(controlId) == null) {
            m881a(false);
            return;
        }
        switch ((EnumC0792m) this.f1244c[this.f1243b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                string = ((TextView) this.f1242a[this.f1243b].m1004b(controlId)).getText().toString();
                break;
            case CHECK_BOX:
                string = ((CheckBox) this.f1242a[this.f1243b].m1004b(controlId)).getText().toString();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1242a[this.f1243b].m1004b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m881a(false);
                    return;
                }
                string = radioButton.getText().toString();
                break;
            case SPINNER:
                Spinner spinner = (Spinner) this.f1242a[this.f1243b].m1004b(controlId);
                if (commandToUi.getItemIndex() >= spinner.getCount()) {
                    m881a(false);
                    return;
                } else {
                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                    string = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                }
                break;
            default:
                m881a(false);
                return;
        }
        MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: e */
    private void m898e(String str) throws Throwable {
        if (this.f1242a[this.f1243b] == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : this.f1244c[this.f1243b].entrySet()) {
            String str2 = (String) entry.getKey();
            int i = AnonymousClass4.f1262b[((EnumC0792m) entry.getValue()).ordinal()];
            if (i == 2) {
                jSONObject.put(str2, ((EditText) this.f1242a[this.f1243b].m1004b(str2)).getText().toString());
            } else {
                switch (i) {
                    case 4:
                        jSONObject.put(str2, ((CheckBox) this.f1242a[this.f1243b].m1004b(str2)).isChecked());
                        break;
                    case 5:
                        jSONObject.put(str2, ((RadioGroup) this.f1242a[this.f1243b].m1004b(str2)).getCheckedRadioButtonId());
                        break;
                    case 6:
                        jSONObject.put(str2, ((Spinner) this.f1242a[this.f1243b].m1004b(str2)).getSelectedItemId());
                        break;
                }
            }
        }
        FileUtils.writeStringToFile(new File(str), jSONObject.toString());
    }

    /* JADX INFO: renamed from: f */
    private void m900f(String str) throws JSONException, IOException {
        if (this.f1244c[this.f1243b] == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject(FileUtils.readFileToString(new File(str)));
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (this.f1244c[this.f1243b].get(next) != null) {
                int i = AnonymousClass4.f1262b[((EnumC0792m) this.f1244c[this.f1243b].get(next)).ordinal()];
                if (i == 2) {
                    ((EditText) this.f1242a[this.f1243b].m1004b(next)).setText(jSONObject.getString(next));
                } else {
                    switch (i) {
                        case 4:
                            ((CheckBox) this.f1242a[this.f1243b].m1004b(next)).setChecked(jSONObject.getBoolean(next));
                            break;
                        case 5:
                            RadioGroup radioGroup = (RadioGroup) this.f1242a[this.f1243b].m1004b(next);
                            if (jSONObject.getInt(next) < radioGroup.getChildCount()) {
                                ((RadioButton) radioGroup.findViewById(jSONObject.getInt(next))).setChecked(true);
                            }
                            break;
                        case 6:
                            Spinner spinner = (Spinner) this.f1242a[this.f1243b].m1004b(next);
                            if (jSONObject.getInt(next) < spinner.getCount()) {
                                spinner.setSelection(jSONObject.getInt(next));
                            }
                            break;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m877a(String str, int i, int i2) {
        this.f1243b++;
        this.f1242a[this.f1243b] = this.f1252m.m868a(str, i, i2);
        this.f1253n.put(str, Integer.valueOf(this.f1243b));
        this.f1244c[this.f1243b] = new HashMap();
        this.f1242a[this.f1243b].f1320b = new AnonymousClass2(str);
        this.f1242a[this.f1243b].f1319a = new AnonymousClass3(str);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.h$2, reason: invalid class name */
    /* JADX INFO: compiled from: UiManager.java */
    final class AnonymousClass2 implements View.OnClickListener {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f1257a;

        AnonymousClass2(String str) {
            this.f1257a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1257a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.h$3, reason: invalid class name */
    /* JADX INFO: compiled from: UiManager.java */
    final class AnonymousClass3 implements View.OnClickListener {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f1259a;

        AnonymousClass3(String str) {
            this.f1259a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            for (int i = 31; i > 0; i--) {
                if (ViewOnClickListenerC0787h.this.f1242a[i] != null) {
                    UiMessage.UiToCommand uiToCommandBuild = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1259a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).setIsSuccess(true).build();
                    MqRunner.getInstance().mo1308b(uiToCommandBuild.toByteString());
                    new StringBuilder("exit event: ").append(uiToCommandBuild);
                }
            }
        }
    }

    /* JADX INFO: renamed from: g */
    private void m902g(String str) {
        if (this.f1253n.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_ui, str);
            m881a(false);
        } else {
            if (this.f1242a[this.f1253n.get(str).intValue()].f1321c) {
                m881a(false);
                return;
            }
            this.f1242a[this.f1253n.get(str).intValue()].m1008c();
            m881a(true);
            MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m882a(String str, String str2) {
        if (this.f1253n.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_ui, str);
            return false;
        }
        this.f1242a[this.f1253n.get(str).intValue()].m1000a(str2);
        return true;
    }

    /* JADX INFO: renamed from: h */
    private void m904h(String str) {
        if (this.f1253n.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_ui, str);
            m881a(false);
        } else {
            MqRunner.getInstance().mo1305a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f1242a[this.f1253n.get(str).intValue()].m995a()).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: l */
    private void m911l(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (this.f1253n.get(controlId) == null) {
            m874a(C0852R.string.ui_show_not_found_ui, controlId);
            m881a(false);
            return;
        }
        if (!color.matches(f1240j)) {
            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m881a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        this.f1242a[this.f1253n.get(controlId).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color)));
        m881a(true);
    }

    /* JADX INFO: renamed from: i */
    private static void m906i(String str) {
        MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: j */
    private boolean m908j(String str) {
        if (this.f1253n.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_ui, str);
            return false;
        }
        if (!this.f1242a[this.f1253n.get(str).intValue()].f1321c) {
            return false;
        }
        this.f1242a[this.f1253n.get(str).intValue()].m1010e();
        if (this.f1253n.get(str).intValue() == this.f1243b) {
            this.f1243b--;
            while (this.f1243b > 0 && this.f1242a[this.f1243b] == null) {
                this.f1243b--;
            }
            if (this.f1243b < 0) {
                this.f1243b = 0;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public final void m927a() {
        for (int i = 31; i > 0; i--) {
            C0790k c0790k = this.f1242a[i];
            if (c0790k != null && c0790k.f1321c) {
                c0790k.m1010e();
            }
            C0789j c0789j = this.f1245d[i];
            if (c0789j != null && c0789j.f1285d) {
                c0789j.m983c();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m874a(int i, Object... objArr) {
        ExToast.makeText(this.f1251l, String.format(this.f1251l.getString(i), objArr), ExToast.LENGTH_LONG).show();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: renamed from: a */
    public static void m879a(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo1309c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: renamed from: k */
    private void m910k(String str) {
        UiMessage.UiToCommand.Builder isSuccess = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true);
        UiMessage.ControlVar.Builder controlId = UiMessage.ControlVar.newBuilder().setControlId(str);
        EnumC0792m enumC0792m = null;
        int i = 0;
        while (true) {
            if (i >= 32) {
                i = 0;
                break;
            } else if (this.f1244c[i] != null && (enumC0792m = (EnumC0792m) this.f1244c[i].get(str)) != null) {
                break;
            } else {
                i++;
            }
        }
        StringBuilder sb = new StringBuilder("type=");
        sb.append(enumC0792m);
        sb.append("; tempIndex=");
        sb.append(i);
        if (enumC0792m == null) {
            m874a(C0852R.string.ui_show_not_found_widget, str);
            MqRunner.getInstance().mo1305a(isSuccess.setIsSuccess(false).build().toByteString());
            return;
        }
        switch (enumC0792m) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                controlId.setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(((TextView) this.f1242a[i].m1004b(str)).getText().toString());
                isSuccess.addVarTable(controlId.build());
                break;
            case CHECK_BOX:
                controlId.setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(((CheckBox) this.f1242a[i].m1004b(str)).isChecked());
                isSuccess.addVarTable(controlId.build());
                break;
            case RADIIO_GROUP:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((RadioGroup) this.f1242a[i].m1004b(str)).getCheckedRadioButtonId());
                isSuccess.addVarTable(controlId.build());
                break;
            case SPINNER:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((Spinner) this.f1242a[i].m1004b(str)).getSelectedItemPosition());
                isSuccess.addVarTable(controlId.build());
                break;
            default:
                isSuccess.setIsSuccess(false);
                break;
        }
        new StringBuilder("Ui.getValue result: ").append(isSuccess.build());
        MqRunner.getInstance().mo1305a(isSuccess.build().toByteString());
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setEventArgs(String.valueOf(i)).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.h$a */
    /* JADX INFO: compiled from: UiManager.java */
    private class a implements TextWatcher {

        /* JADX INFO: renamed from: b */
        private String f1264b;

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public a(String str) {
            this.f1264b = str;
        }

        /* JADX INFO: renamed from: a */
        private String m929a() {
            return this.f1264b;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            MqRunner.getInstance().mo1308b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1264b).setType(UiMessage.ControlEvent.Event_Type.ON_CHANGE).build()).setIsSuccess(true).build().toByteString());
            ViewOnClickListenerC0787h.this.f1242a[ViewOnClickListenerC0787h.this.f1243b].m1005b();
        }
    }

    /* JADX INFO: renamed from: a */
    private void m878a(String str, int i, int i2, int i3, int i4) {
        if (this.f1247f.get(str) == null) {
            this.f1246e++;
            this.f1245d[this.f1246e] = this.f1252m.m867a(str, i, i2, i3, i4);
            this.f1247f.put(str, Integer.valueOf(this.f1246e));
            return;
        }
        if (this.f1245d[this.f1247f.get(str).intValue()].f1285d) {
            this.f1245d[this.f1247f.get(str).intValue()].m981b();
        }
        Iterator<Map.Entry<String, String>> it2 = this.f1249h.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, String> next = it2.next();
            String key = next.getKey();
            String value = next.getValue();
            StringBuilder sb = new StringBuilder("doNewFloat key:");
            sb.append(key);
            sb.append(" parentTag:");
            sb.append(value);
            sb.append(" tag:");
            sb.append(str);
            if (value.compareTo(str) == 0) {
                it2.remove();
                this.f1248g.remove(key);
            }
        }
        this.f1245d[this.f1247f.get(str).intValue()] = this.f1252m.m867a(str, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: l */
    private void m912l(String str) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
            return;
        }
        if (this.f1245d[this.f1247f.get(str).intValue()].f1285d) {
            m881a(false);
            return;
        }
        C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
        String str2 = (String) c0789j.getTag();
        if (c0789j.f1290i != null && c0789j.f1290i.length() > 0) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(c0789j.f1290i);
            if (Build.VERSION.SDK_INT >= 16) {
                c0789j.f1284c.setBackground(new BitmapDrawable(bitmapDecodeFile));
            }
        } else {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(new float[]{c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g});
            try {
                gradientDrawable.setColor(c0789j.f1287f);
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT >= 16) {
                c0789j.f1284c.setBackground(gradientDrawable);
            }
        }
        c0789j.f1285d = true;
        c0789j.f1284c.setOnTouchListener(c0789j.new AnonymousClass1(str2));
        c0789j.f1282a.addView(c0789j.f1284c, c0789j.f1283b);
        c0789j.f1284c.getBackground().setAlpha((c0789j.f1289h * 255) / 100);
        m881a(true);
        m879a(str, UiMessage.ControlEvent.Event_Type.ON_SHOW);
    }

    /* JADX INFO: renamed from: m */
    private void m914m(String str) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
        } else {
            if (!this.f1245d[this.f1247f.get(str).intValue()].f1285d) {
                m881a(true);
                return;
            }
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            if (c0789j.f1284c != null && c0789j.f1285d) {
                c0789j.f1282a.removeView(c0789j.f1284c);
                c0789j.f1282a.addView(c0789j.f1284c, c0789j.f1283b);
            }
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m880a(String str, boolean z) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
        } else {
            this.f1245d[this.f1247f.get(str).intValue()].f1286e = z;
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: n */
    private void m915n(String str) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
        } else if (!this.f1245d[this.f1247f.get(str).intValue()].f1285d) {
            m881a(false);
        } else {
            this.f1245d[this.f1247f.get(str).intValue()].m981b();
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: o */
    private void m917o(String str) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
        } else {
            this.f1245d[this.f1247f.get(str).intValue()].m983c();
            this.f1247f.remove(str);
            m879a(str, UiMessage.ControlEvent.Event_Type.ON_CLOSE);
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m876a(String str, int i) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
        } else {
            this.f1245d[this.f1247f.get(str).intValue()].setOpacity(i);
            m881a(true);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m887b(String str, int i) {
        if (this.f1247f.get(str) == null) {
            m874a(C0852R.string.ui_show_not_found_float, str);
            m881a(false);
            return;
        }
        C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
        c0789j.f1288g = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g});
        try {
            gradientDrawable.setColor(c0789j.f1287f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            c0789j.f1284c.setBackground(gradientDrawable);
        }
        c0789j.f1284c.getBackground().setAlpha((c0789j.f1289h * 255) / 100);
        this.f1245d[this.f1247f.get(str).intValue()].m985e();
        m881a(true);
    }

    /* JADX INFO: renamed from: b */
    private void m888b(String str, String str2) {
        if (!str2.matches(f1240j)) {
            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m881a(false);
            return;
        }
        if (!str2.startsWith("#")) {
            str2 = "#" + str2;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(str2));
        if (this.f1247f.get(str) != null) {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            c0789j.f1287f = iM1075a;
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(new float[]{c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g, c0789j.f1288g});
            try {
                gradientDrawable.setColor(c0789j.f1287f);
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT >= 16) {
                c0789j.f1284c.setBackground(gradientDrawable);
            }
            c0789j.f1284c.getBackground().setAlpha((c0789j.f1289h * 255) / 100);
            this.f1245d[this.f1247f.get(str).intValue()].m985e();
        } else {
            String str3 = this.f1249h.get(str);
            if (str3 == null) {
                m881a(false);
                return;
            }
            View viewM977a = this.f1245d[this.f1247f.get(str3).intValue()].m977a(str);
            if (viewM977a == null) {
                m881a(false);
                return;
            } else if (this.f1248g.get(str) == EnumC0792m.BUTTON) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadii(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f});
                gradientDrawable2.setColor(iM1075a);
                viewM977a.setBackground(gradientDrawable2);
            } else if (this.f1248g.get(str) != EnumC0792m.SPINNER) {
                viewM977a.setBackgroundColor(iM1075a);
            }
        }
        m881a(true);
    }

    /* JADX INFO: renamed from: c */
    private boolean m893c(String str, String str2) {
        View viewM977a;
        if (this.f1247f.get(str) != null) {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            c0789j.f1290i = str2;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(c0789j.f1290i);
            if (Build.VERSION.SDK_INT >= 16) {
                c0789j.f1284c.setBackground(new BitmapDrawable(bitmapDecodeFile));
            }
            c0789j.f1284c.getBackground().setAlpha((c0789j.f1289h * 255) / 100);
            this.f1245d[this.f1247f.get(str).intValue()].m985e();
            return true;
        }
        String str3 = this.f1249h.get(str);
        if (str3 == null || (viewM977a = this.f1245d[this.f1247f.get(str3).intValue()].m977a(str)) == null) {
            return false;
        }
        if (this.f1248g.get(str) == EnumC0792m.IMAGE_VIEW) {
            ((ImageView) viewM977a).setImageURI(Uri.fromFile(new File(str2)));
        }
        return true;
    }

    /* JADX INFO: renamed from: m */
    private void m913m(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String strParam = commandToUi.getStrParam();
        if (!strParam.matches(f1240j)) {
            m874a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m881a(false);
            return;
        }
        if (!strParam.startsWith("#")) {
            strParam = "#" + strParam;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(strParam));
        String str = this.f1249h.get(controlId);
        if (str == null) {
            m881a(false);
            return;
        }
        View viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId);
        if (viewM977a == null) {
            m881a(false);
            return;
        }
        if (this.f1248g.get(controlId) == EnumC0792m.BUTTON) {
            ((Button) viewM977a).setTextColor(iM1075a);
        }
        if (this.f1248g.get(controlId) == EnumC0792m.TEXT_VIEW) {
            ((TextView) viewM977a).setTextColor(iM1075a);
        }
        this.f1245d[this.f1247f.get(str).intValue()].m985e();
        m881a(true);
    }

    /* JADX INFO: renamed from: p */
    private void m919p(String str) {
        JSONObject jSONObject = new JSONObject();
        if (this.f1247f.get(str) != null) {
            try {
                WindowManager.LayoutParams params = this.f1245d[this.f1247f.get(str).intValue()].getParams();
                jSONObject.put("Left", params.x);
                jSONObject.put("Top", params.y);
                jSONObject.put("Width", params.width);
                jSONObject.put("Height", params.height);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        } else {
            String str2 = this.f1249h.get(str);
            EnumC0792m enumC0792m = this.f1248g.get(str);
            if (enumC0792m == null) {
                m874a(C0852R.string.ui_show_not_found_widget, str);
                MqRunner.getInstance().mo1306a(jSONObject.toString());
                return;
            }
            int i = AnonymousClass4.f1262b[enumC0792m.ordinal()];
            if (i == 1) {
                TextView textView = (TextView) this.f1245d[this.f1247f.get(str2).intValue()].m977a(str);
                try {
                    jSONObject.put("Left", textView.getLeft());
                    jSONObject.put("Top", textView.getTop());
                    jSONObject.put("Text", textView.getText());
                    jSONObject.put("Width", textView.getWidth());
                    jSONObject.put("Height", textView.getHeight());
                } catch (JSONException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            } else if (i == 3) {
                Button button = (Button) this.f1245d[this.f1247f.get(str2).intValue()].m977a(str);
                try {
                    jSONObject.put("Left", button.getLeft());
                    jSONObject.put("Top", button.getTop());
                    jSONObject.put("Text", button.getText());
                    jSONObject.put("Width", button.getWidth());
                    jSONObject.put("Height", button.getHeight());
                } catch (JSONException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            } else if (i == 7) {
                ImageView imageView = (ImageView) this.f1245d[this.f1247f.get(str2).intValue()].m977a(str);
                try {
                    jSONObject.put("Left", imageView.getLeft());
                    jSONObject.put("Top", imageView.getTop());
                    jSONObject.put("Width", imageView.getWidth());
                    jSONObject.put("Height", imageView.getHeight());
                } catch (JSONException e4) {
                    ThrowableExtension.printStackTrace(e4);
                }
            }
        }
        new StringBuilder("FWGetValue json: ").append(jSONObject.toString());
        MqRunner.getInstance().mo1306a(jSONObject.toString());
    }

    /* JADX INFO: renamed from: n */
    private boolean m916n(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String str = this.f1249h.get(controlId);
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        if (str == null) {
            m881a(false);
            return false;
        }
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        View viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId);
        if (viewM977a == null) {
            m881a(false);
            return false;
        }
        new StringBuilder("doReSetView value: ").append(commandToUi.getCommand().toString());
        switch (commandToUi.getCommand()) {
            case SET_FW_BUTTON:
                if (this.f1248g.get(controlId) == EnumC0792m.BUTTON) {
                    Button button = (Button) viewM977a;
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewM977a.getLayoutParams();
                    layoutParams.width = commandToUi.getWidth();
                    layoutParams.height = commandToUi.getHeight();
                    button.setLayoutParams(layoutParams);
                    button.setText(commandToUi.getText());
                    button.setX(commandToUi.getLeft());
                    button.setY(commandToUi.getTop());
                }
                break;
            case SET_FW_TEXT_VIEW:
                if (this.f1248g.get(controlId) == EnumC0792m.TEXT_VIEW) {
                    TextView textView = (TextView) viewM977a;
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) viewM977a.getLayoutParams();
                    layoutParams2.width = commandToUi.getWidth();
                    layoutParams2.height = commandToUi.getHeight();
                    textView.setLayoutParams(layoutParams2);
                    textView.setText(commandToUi.getText());
                    textView.setX(commandToUi.getLeft());
                    textView.setY(commandToUi.getTop());
                }
                break;
            case SET_FW_IMAGE_VIEW:
                if (this.f1248g.get(controlId) == EnumC0792m.IMAGE_VIEW) {
                    ImageView imageView = (ImageView) viewM977a;
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    layoutParams3.width = commandToUi.getWidth();
                    layoutParams3.height = commandToUi.getHeight();
                    imageView.setLayoutParams(layoutParams3);
                    imageView.setX(commandToUi.getLeft());
                    imageView.setY(commandToUi.getTop());
                    imageView.setImageURI(Uri.fromFile(new File(commandToUi.getText())));
                }
                break;
        }
        this.f1245d[this.f1247f.get(str).intValue()].m985e();
        m881a(true);
        return true;
    }

    /* JADX INFO: renamed from: o */
    private boolean m918o(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        String str = this.f1249h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            if (c0789j == null || (viewM977a = c0789j.m977a(controlId)) == null) {
                return false;
            }
            int i = AnonymousClass4.f1262b[this.f1248g.get(controlId).ordinal()];
            if (i == 1 || i == 3) {
                new StringBuilder("setFloatTextSize value: ").append(commandToUi.getFontSize());
                ((TextView) viewM977a).setTextSize(2, commandToUi.getFontSize());
            }
            c0789j.m985e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: p */
    private boolean m920p(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        String str = this.f1249h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            if (c0789j == null || (viewM977a = c0789j.m977a(controlId)) == null) {
                return false;
            }
            switch (commandToUi.getCommand()) {
                case SET_FW_ENABLE:
                    new StringBuilder("doFloatSetStatus ").append(commandToUi.getEnabledStatus());
                    viewM977a.setEnabled(commandToUi.getEnabledStatus());
                    break;
                case SET_FW_VISIBLE:
                    new StringBuilder("doFloatSetStatus ").append(commandToUi.getVisibleStatus());
                    if (commandToUi.getVisibleStatus() == 0) {
                        viewM977a.setVisibility(8);
                    } else {
                        viewM977a.setVisibility(0);
                    }
                    break;
            }
            c0789j.m985e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: q */
    private boolean m921q(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        String str = this.f1249h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            if (c0789j == null || (viewM977a = c0789j.m977a(controlId)) == null) {
                return false;
            }
            String strReplace = "false";
            switch (commandToUi.getCommand()) {
                case GET_FW_ENABLE:
                    if (viewM977a.isEnabled()) {
                        strReplace = "true";
                    }
                    break;
                case GET_FW_TEXT_COLOR:
                    if (this.f1248g.get(controlId) != EnumC0792m.IMAGE_VIEW) {
                        strReplace = String.format("%6s", Integer.toHexString(C0806c.m1075a(((TextView) viewM977a).getCurrentTextColor()) & ViewCompat.MEASURED_SIZE_MASK)).replace(" ", AccountManager.TYPE_LOGIN);
                    }
                    break;
                case GET_FW_VISIBLE:
                    if (viewM977a.getVisibility() == 0) {
                        strReplace = "true";
                    }
                    break;
            }
            MqRunner.getInstance().mo1306a(strReplace);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: r */
    private boolean m922r(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        if (this.f1247f.get(controlId) != null) {
            this.f1245d[this.f1247f.get(controlId).intValue()].setFloatLeft(commandToUi.getIntParam());
            return true;
        }
        String str = this.f1249h.get(controlId);
        if (str == null || (viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId)) == null) {
            return false;
        }
        if (this.f1248g.get(controlId) != EnumC0792m.BUTTON && this.f1248g.get(controlId) != EnumC0792m.TEXT_VIEW && this.f1248g.get(controlId) != EnumC0792m.IMAGE_VIEW) {
            return true;
        }
        new StringBuilder("view.setX ").append(commandToUi.getIntParam());
        viewM977a.setX(commandToUi.getIntParam());
        return true;
    }

    /* JADX INFO: renamed from: s */
    private boolean m923s(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        if (this.f1247f.get(controlId) != null) {
            this.f1245d[this.f1247f.get(controlId).intValue()].setFloatTop(commandToUi.getIntParam());
            return true;
        }
        String str = this.f1249h.get(controlId);
        if (str == null || (viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId)) == null) {
            return false;
        }
        if (this.f1248g.get(controlId) != EnumC0792m.BUTTON && this.f1248g.get(controlId) != EnumC0792m.TEXT_VIEW && this.f1248g.get(controlId) != EnumC0792m.IMAGE_VIEW) {
            return true;
        }
        new StringBuilder("view.setY ").append(commandToUi.getIntParam());
        viewM977a.setY(commandToUi.getIntParam());
        return true;
    }

    /* JADX INFO: renamed from: t */
    private boolean m924t(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        if (this.f1247f.get(controlId) != null) {
            this.f1245d[this.f1247f.get(controlId).intValue()].setFloatWidth(commandToUi.getIntParam());
            return true;
        }
        String str = this.f1249h.get(controlId);
        if (str == null || (viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId)) == null) {
            return false;
        }
        if (this.f1248g.get(controlId) != EnumC0792m.BUTTON && this.f1248g.get(controlId) != EnumC0792m.TEXT_VIEW && this.f1248g.get(controlId) != EnumC0792m.IMAGE_VIEW) {
            return true;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewM977a.getLayoutParams();
        layoutParams.width = commandToUi.getIntParam();
        viewM977a.setLayoutParams(layoutParams);
        return true;
    }

    /* JADX INFO: renamed from: u */
    private boolean m925u(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        if (this.f1247f.get(controlId) != null) {
            this.f1245d[this.f1247f.get(controlId).intValue()].setFloatHeight(commandToUi.getIntParam());
            return true;
        }
        String str = this.f1249h.get(controlId);
        if (str == null || (viewM977a = this.f1245d[this.f1247f.get(str).intValue()].m977a(controlId)) == null) {
            return false;
        }
        if (this.f1248g.get(controlId) != EnumC0792m.BUTTON && this.f1248g.get(controlId) != EnumC0792m.TEXT_VIEW && this.f1248g.get(controlId) != EnumC0792m.IMAGE_VIEW) {
            return true;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewM977a.getLayoutParams();
        layoutParams.height = commandToUi.getIntParam();
        viewM977a.setLayoutParams(layoutParams);
        return true;
    }

    /* JADX INFO: renamed from: v */
    private boolean m926v(UiMessage.CommandToUi commandToUi) {
        View viewM977a;
        String controlId = commandToUi.getControlId();
        new StringBuilder("setFloatText : ").append(commandToUi.getStrParam());
        String str = this.f1249h.get(controlId);
        if (str == null) {
            return false;
        }
        try {
            C0789j c0789j = this.f1245d[this.f1247f.get(str).intValue()];
            if (c0789j == null || (viewM977a = c0789j.m977a(controlId)) == null) {
                return false;
            }
            int i = AnonymousClass4.f1262b[this.f1248g.get(controlId).ordinal()];
            if (i == 1) {
                ((TextView) viewM977a).setText(commandToUi.getStrParam());
            } else if (i == 3) {
                ((Button) viewM977a).setText(commandToUi.getStrParam());
            }
            c0789j.m985e();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
