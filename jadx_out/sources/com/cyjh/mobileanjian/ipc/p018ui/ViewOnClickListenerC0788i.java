package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.event.C0721c;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.utils.C0806c;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
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

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.i */
/* JADX INFO: compiled from: UiManagerLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC0788i implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: g */
    private static final String f1265g = "^#*([0-9A-Fa-f]{6})|([0-9A-Fa-f]{3})";

    /* JADX INFO: renamed from: h */
    private static final int f1266h = 32;

    /* JADX INFO: renamed from: c */
    public C0786g f1269c;

    /* JADX INFO: renamed from: i */
    private Context f1273i;

    /* JADX INFO: renamed from: a */
    public C0790k[] f1267a = new C0790k[32];

    /* JADX INFO: renamed from: b */
    public int f1268b = 0;

    /* JADX INFO: renamed from: d */
    public HashMap<String, Integer> f1270d = new HashMap<>();

    /* JADX INFO: renamed from: e */
    public HashMap[] f1271e = new HashMap[32];

    /* JADX INFO: renamed from: f */
    public int f1272f = 1;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public ViewOnClickListenerC0788i(Context context) {
        this.f1273i = context;
        this.f1269c = C0786g.m851a(context);
    }

    /* JADX INFO: renamed from: a */
    public final int m952a() {
        int i = this.f1272f + 1;
        this.f1272f = i;
        return i;
    }

    /* JADX INFO: renamed from: b */
    public final int m956b() {
        return (int) ((this.f1273i.getResources().getDisplayMetrics().densityDpi / 160.0f) * 5.0f);
    }

    /* JADX INFO: renamed from: d */
    private void m936d() {
        m959c();
        this.f1268b = 0;
        this.f1272f = 1;
        for (int i = 0; i < 32; i++) {
            this.f1267a[i] = null;
            if (this.f1271e[i] != null) {
                this.f1271e[i].clear();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:60:0x01df  */
    /* JADX INFO: renamed from: d */
    private void m937d(UiMessage.CommandToUi commandToUi) {
        String string;
        int color;
        String controlId = commandToUi.getControlId();
        UiMessage.CommandToUi.Command_Type command = commandToUi.getCommand();
        new StringBuilder("command: ").append(commandToUi);
        int i = 2;
        boolean zM961d = true;
        switch (command) {
            case SAVE_PROFILE:
                try {
                    m954a(commandToUi.getPath());
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (JSONException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                break;
            case LOAD_PROFILE:
                try {
                    m958b(commandToUi.getPath());
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                } catch (JSONException e4) {
                    ThrowableExtension.printStackTrace(e4);
                }
                break;
            case NEW_LAYOUT:
                int width = commandToUi.getWidth();
                int height = commandToUi.getHeight();
                this.f1268b++;
                this.f1267a[this.f1268b] = this.f1269c.m868a(controlId, width, height);
                this.f1270d.put(controlId, Integer.valueOf(this.f1268b));
                this.f1271e[this.f1268b] = new HashMap();
                this.f1267a[this.f1268b].f1320b = new AnonymousClass1(controlId);
                this.f1267a[this.f1268b].f1319a = new AnonymousClass2(controlId);
                break;
            case SHOW_LAYOUT:
                if (this.f1270d.get(controlId) == null) {
                    m953a(C0852R.string.ui_show_not_found_ui, controlId);
                    m931a(false);
                    return;
                } else {
                    if (this.f1267a[this.f1270d.get(controlId).intValue()].f1321c) {
                        m931a(false);
                        return;
                    }
                    this.f1267a[this.f1270d.get(controlId).intValue()].m1008c();
                    m931a(true);
                    C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
                    return;
                }
            case CLOSE_LAYOUT:
                C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(controlId).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
                break;
            case CLOSE_CONTINUE_ACK:
                zM961d = m961d(controlId);
                break;
            case CLOSE_EXIT_ACK:
                m959c();
                break;
            case NEW_ROW:
                if (this.f1267a[this.f1268b] != null) {
                    C0790k c0790k = this.f1267a[this.f1268b];
                    m952a();
                    c0790k.m1002a(controlId, commandToUi.getParentId(), commandToUi.getWidth(), commandToUi.getHeight());
                }
                break;
            case SET_TITLE_TEXT:
                String text = commandToUi.getText();
                if (this.f1270d.get(controlId) == null) {
                    m953a(C0852R.string.ui_show_not_found_ui, controlId);
                    zM961d = false;
                } else {
                    this.f1267a[this.f1270d.get(controlId).intValue()].m1000a(text);
                }
                break;
            case GET_TITLE_TEXT:
                if (this.f1270d.get(controlId) == null) {
                    m953a(C0852R.string.ui_show_not_found_ui, controlId);
                    m931a(false);
                    return;
                } else {
                    C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f1267a[this.f1270d.get(controlId).intValue()].m995a()).build()).build().toByteString());
                    return;
                }
            case SET_TITLE_BACKCOLOR:
                String controlId2 = commandToUi.getControlId();
                String color2 = commandToUi.getColor();
                if (this.f1270d.get(controlId2) == null) {
                    m953a(C0852R.string.ui_show_not_found_ui, controlId2);
                    m931a(false);
                    return;
                }
                if (!color2.matches(f1265g)) {
                    m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m931a(false);
                    return;
                }
                if (!color2.startsWith("#")) {
                    color2 = "#" + color2;
                }
                this.f1267a[this.f1270d.get(controlId2).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color2)));
                m931a(true);
                return;
            case ADD_LINE:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g = this.f1269c;
                    m952a();
                    LinearLayout linearLayoutM870b = c0786g.m870b(controlId, commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.LINE);
                    this.f1267a[this.f1268b].m999a(linearLayoutM870b, commandToUi.getParentId());
                }
                break;
            case ADD_TEXT_VIEW:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g2 = this.f1269c;
                    m952a();
                    TextView textViewM866a = c0786g2.m866a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.TEXT_VIEW);
                    this.f1267a[this.f1268b].m999a(textViewM866a, commandToUi.getParentId());
                }
                break;
            case ADD_EDIT_TEXT:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g3 = this.f1269c;
                    m952a();
                    EditText editTextM873d = c0786g3.m873d(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.EDIT_TEXT);
                    this.f1267a[this.f1268b].m999a(editTextM873d, commandToUi.getParentId());
                    editTextM873d.addTextChangedListener(new a(controlId));
                }
                break;
            case ADD_BUTTON:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g4 = this.f1269c;
                    m952a();
                    Button buttonM869b = c0786g4.m869b(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.BUTTON);
                    buttonM869b.setOnClickListener(this);
                    this.f1267a[this.f1268b].m999a(buttonM869b, commandToUi.getParentId());
                }
                break;
            case ADD_RADIO_GROUP:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g5 = this.f1269c;
                    m952a();
                    RadioGroup radioGroupM864a = c0786g5.m864a(controlId, commandToUi.getItemTextList(), commandToUi.getDefaultItemIndex(), commandToUi.getWidth(), commandToUi.getHeight());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.RADIIO_GROUP);
                    this.f1267a[this.f1268b].m999a(radioGroupM864a, commandToUi.getParentId());
                }
                break;
            case ADD_CHECK_BOX:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g6 = this.f1269c;
                    m952a();
                    CheckBox checkBoxM862a = c0786g6.m862a(controlId, commandToUi.getText(), commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getDefaultCheckStatus());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.CHECK_BOX);
                    this.f1267a[this.f1268b].m999a(checkBoxM862a, commandToUi.getParentId());
                }
                break;
            case ADD_IMAGE_VIEW:
                if (this.f1267a[this.f1268b] != null) {
                    ImageView imageViewM863a = this.f1269c.m863a(m952a(), controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getPath());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.IMAGE_VIEW);
                    imageViewM863a.setOnClickListener(this);
                    this.f1267a[this.f1268b].m999a(imageViewM863a, commandToUi.getParentId());
                }
                break;
            case ADD_WEB_VIEW:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g7 = this.f1269c;
                    m952a();
                    WebView webViewM861a = c0786g7.m861a(controlId, commandToUi.getWidth(), commandToUi.getHeight(), commandToUi.getUrl());
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.WEB_VIEW);
                    this.f1267a[this.f1268b].m999a(webViewM861a, commandToUi.getParentId());
                }
                break;
            case ADD_SPINNER:
                if (this.f1267a[this.f1268b] != null) {
                    C0786g c0786g8 = this.f1269c;
                    m952a();
                    List<String> itemTextList = commandToUi.getItemTextList();
                    int defaultItemIndex = commandToUi.getDefaultItemIndex();
                    commandToUi.getWidth();
                    commandToUi.getHeight();
                    Spinner spinnerM865a = c0786g8.m865a(controlId, itemTextList, defaultItemIndex);
                    this.f1271e[this.f1268b].put(controlId, EnumC0792m.SPINNER);
                    this.f1267a[this.f1268b].m999a(spinnerM865a, commandToUi.getParentId());
                    spinnerM865a.setOnItemSelectedListener(this);
                }
                break;
            case ADD_TAB_HOST:
                if (this.f1267a[this.f1268b] != null) {
                    this.f1267a[this.f1268b].m1001a(commandToUi.getControlId(), commandToUi.getHeight());
                }
                break;
            case ADD_TAB:
                if (this.f1267a[this.f1268b] != null) {
                    zM961d = this.f1267a[this.f1268b].m1003a(m952a(), commandToUi.getControlId(), commandToUi.getParentId(), commandToUi.getText());
                }
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
                zM961d = m955a(commandToUi);
                break;
            case SET_FULLSCREEN:
                if (this.f1270d.get(controlId) != null) {
                    m931a(true);
                } else if (this.f1271e[this.f1268b].get(controlId) == null) {
                    m931a(false);
                } else {
                    View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
                    layoutParams.width = -1;
                    viewM1004b.setLayoutParams(layoutParams);
                }
                break;
            case SET_TEXT:
                m960c(commandToUi);
                return;
            case SET_FONT:
                m957b(commandToUi);
                return;
            case SET_EDIT_INPUT_TYPE:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                String controlId3 = commandToUi.getControlId();
                commandToUi.getText();
                View viewM1004b2 = this.f1267a[this.f1268b].m1004b(controlId3);
                if (viewM1004b2 == null) {
                    m931a(false);
                    return;
                } else if (this.f1271e[this.f1268b].get(controlId3) != EnumC0792m.EDIT_TEXT) {
                    m931a(false);
                    return;
                } else {
                    ((EditText) viewM1004b2).setInputType(commandToUi.getEditInputType());
                    m931a(true);
                    return;
                }
            case GET_TEXT:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                String controlId4 = commandToUi.getControlId();
                if (this.f1271e[this.f1268b].get(controlId4) == null) {
                    m931a(false);
                    return;
                }
                switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId4)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        string = ((TextView) this.f1267a[this.f1268b].m1004b(controlId4)).getText().toString();
                        break;
                    case CHECK_BOX:
                        string = ((CheckBox) this.f1267a[this.f1268b].m1004b(controlId4)).getText().toString();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1267a[this.f1268b].m1004b(controlId4)).findViewById(commandToUi.getItemIndex());
                        if (radioButton == null) {
                            m931a(false);
                            return;
                        }
                        string = radioButton.getText().toString();
                        break;
                    case SPINNER:
                        Spinner spinner = (Spinner) this.f1267a[this.f1268b].m1004b(controlId4);
                        if (commandToUi.getItemIndex() >= spinner.getCount()) {
                            m931a(false);
                            return;
                        } else {
                            new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                            string = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                        }
                        break;
                    default:
                        m931a(false);
                        return;
                }
                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId4).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
                return;
            case SET_TEXTCOLOR:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                String controlId5 = commandToUi.getControlId();
                String color3 = commandToUi.getColor();
                if (!color3.matches(f1265g)) {
                    m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m931a(false);
                    return;
                }
                if (!color3.startsWith("#")) {
                    color3 = "#" + color3;
                }
                int iM1075a = C0806c.m1075a(Color.parseColor(color3));
                if (this.f1270d.get(controlId5) != null) {
                    this.f1267a[this.f1270d.get(controlId5).intValue()].m996a(iM1075a);
                } else {
                    View viewM1004b3 = this.f1267a[this.f1268b].m1004b(controlId5);
                    if (viewM1004b3 == null) {
                        m931a(false);
                        return;
                    }
                    switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId5)) {
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
                                m931a(false);
                                return;
                            }
                            radioButton2.setTextColor(iM1075a);
                            break;
                        case SPINNER:
                            if (commandToUi.getItemIndex() >= ((Spinner) viewM1004b3).getCount()) {
                                m931a(false);
                                return;
                            }
                            break;
                        default:
                            m931a(false);
                            return;
                    }
                }
                m931a(true);
                return;
            case SET_BACKCOLOR:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                String controlId6 = commandToUi.getControlId();
                View viewM1004b4 = this.f1267a[this.f1268b].m1004b(controlId6);
                if (viewM1004b4 == null) {
                    m931a(false);
                    return;
                }
                String color4 = commandToUi.getColor();
                if (!color4.matches(f1265g)) {
                    m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
                    m931a(false);
                    return;
                }
                if (!color4.startsWith("#")) {
                    color4 = "#" + color4;
                }
                int iM1075a2 = C0806c.m1075a(Color.parseColor(color4));
                if (this.f1271e[this.f1268b].get(controlId6) == EnumC0792m.BUTTON) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(iM1075a2);
                    gradientDrawable.setCornerRadius(m956b());
                    if (Build.VERSION.SDK_INT >= 16) {
                        viewM1004b4.setBackground(gradientDrawable);
                    } else {
                        viewM1004b4.setBackgroundDrawable(gradientDrawable);
                    }
                    m931a(true);
                    return;
                }
                if (this.f1271e[this.f1268b].get(controlId6) != EnumC0792m.SPINNER) {
                    viewM1004b4.setBackgroundColor(iM1075a2);
                }
                m931a(true);
                return;
            case GET_VALUE:
                m962e(controlId);
                return;
            case GET_ENABLED:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                View viewM1004b5 = this.f1267a[this.f1268b].m1004b(controlId);
                if (viewM1004b5 == null) {
                    m931a(false);
                    return;
                }
                boolean zIsEnabled = viewM1004b5.isEnabled();
                if (this.f1271e[this.f1268b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                    zIsEnabled = ((EditText) viewM1004b5).isFocusable();
                }
                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
                return;
            case GET_VISIBLE:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                View viewM1004b6 = this.f1267a[this.f1268b].m1004b(controlId);
                if (viewM1004b6 == null) {
                    m931a(false);
                    return;
                }
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
                return;
            case GET_TEXTCOLOR:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                String controlId7 = commandToUi.getControlId();
                if (this.f1271e[this.f1268b].get(controlId7) == null) {
                    m931a(false);
                    return;
                }
                switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId7)) {
                    case TEXT_VIEW:
                    case EDIT_TEXT:
                    case BUTTON:
                        color = ((TextView) this.f1267a[this.f1268b].m1004b(controlId7)).getPaint().getColor();
                        break;
                    case CHECK_BOX:
                        color = ((CheckBox) this.f1267a[this.f1268b].m1004b(controlId7)).getPaint().getColor();
                        break;
                    case RADIIO_GROUP:
                        RadioButton radioButton3 = (RadioButton) ((RadioGroup) this.f1267a[this.f1268b].m1004b(controlId7)).findViewById(commandToUi.getItemIndex());
                        if (radioButton3 == null) {
                            m931a(false);
                            return;
                        }
                        color = radioButton3.getPaint().getColor();
                        break;
                    default:
                        m931a(false);
                        return;
                }
                C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId7).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
                return;
            case SET_CONTROL_GRAVITY:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                View viewM1004b7 = this.f1267a[this.f1268b].m1004b(commandToUi.getControlId());
                if (viewM1004b7 == null) {
                    m931a(false);
                    return;
                }
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewM1004b7.getLayoutParams();
                layoutParams2.gravity = commandToUi.getAlignType();
                viewM1004b7.setLayoutParams(layoutParams2);
                new StringBuilder("set gravity ok; ").append(layoutParams2);
                m931a(true);
                return;
            case SET_PADDING:
                if (this.f1267a[this.f1268b] == null) {
                    m931a(false);
                    return;
                }
                View viewM1004b8 = this.f1267a[this.f1268b].m1004b(commandToUi.getControlId());
                if (viewM1004b8 == null) {
                    m931a(false);
                    return;
                }
                UiMessage.Padding_Var padding = commandToUi.getPadding();
                new StringBuilder("padding: ").append(padding.getLeft());
                new StringBuilder("padding: ").append(padding.getTop());
                new StringBuilder("padding: ").append(padding.getRight());
                new StringBuilder("padding: ").append(padding.getBottom());
                viewM1004b8.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
                m931a(true);
                return;
        }
        m931a(zM961d);
    }

    /* JADX INFO: renamed from: a */
    public static void m931a(boolean z) {
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(z).build().toByteString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX INFO: renamed from: a */
    public final boolean m955a(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            return false;
        }
        String controlId = commandToUi.getControlId();
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m953a(C0852R.string.ui_show_not_found_widget, controlId);
            return false;
        }
        switch (commandToUi.getCommand()) {
            case SET_TEXT_VIEW:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.TEXT_VIEW) {
                    return false;
                }
                TextView textView = (TextView) viewM1004b;
                textView.setText(commandToUi.getText());
                textView.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_EDIT_TEXT:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.EDIT_TEXT) {
                    return false;
                }
                EditText editText = (EditText) viewM1004b;
                editText.setText(commandToUi.getText());
                editText.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_BOTTON:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.BUTTON) {
                    return false;
                }
                Button button = (Button) viewM1004b;
                button.setText(commandToUi.getText());
                button.setLayoutParams(new LinearLayout.LayoutParams(commandToUi.getWidth(), commandToUi.getHeight()));
                return true;
            case SET_CHECK_BOX:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.CHECK_BOX) {
                    return false;
                }
                CheckBox checkBox = (CheckBox) viewM1004b;
                checkBox.setChecked(commandToUi.getDefaultCheckStatus());
                checkBox.setText(commandToUi.getText());
                checkBox.getLayoutParams().width = commandToUi.getWidth();
                checkBox.getLayoutParams().height = commandToUi.getHeight();
                return true;
            case SET_IMAGE_VIEW:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.IMAGE_VIEW) {
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
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.WEB_VIEW) {
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
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.RADIIO_GROUP) {
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
                    RadioButton radioButton = (RadioButton) LayoutInflater.from(this.f1273i).inflate(C0852R.layout.ui_show_radio_button, (ViewGroup) null);
                    radioButton.setId(i);
                    radioButton.setText(itemTextList.get(i));
                    radioButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    radioButton.setPadding(0, m956b(), 0, m956b());
                    radioGroup.addView(radioButton);
                }
                ((RadioButton) radioGroup.findViewById(commandToUi.getDefaultItemIndex() < radioGroup.getChildCount() ? commandToUi.getDefaultItemIndex() : 0)).setChecked(true);
                return true;
            case SET_SPINNER:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.SPINNER) {
                    return false;
                }
                Spinner spinner = (Spinner) viewM1004b;
                ArrayAdapter arrayAdapter = new ArrayAdapter(this.f1273i, C0852R.layout.ui_show_spinner_dropdown_item, commandToUi.getItemTextList());
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                spinner.setSelection(commandToUi.getDefaultItemIndex() < arrayAdapter.getCount() ? commandToUi.getDefaultItemIndex() : 0);
                return true;
            case SET_ENABLED:
                if (this.f1271e[this.f1268b].get(controlId) == EnumC0792m.RADIIO_GROUP) {
                    viewM1004b.setEnabled(commandToUi.getEnabledStatus());
                    RadioGroup radioGroup2 = (RadioGroup) viewM1004b;
                    for (int i2 = 0; i2 < radioGroup2.getChildCount(); i2++) {
                        radioGroup2.getChildAt(i2).setEnabled(commandToUi.getEnabledStatus());
                    }
                } else if (this.f1271e[this.f1268b].get(controlId) == EnumC0792m.EDIT_TEXT) {
                    new StringBuilder("set edittext enabled. ").append(commandToUi.getEnabledStatus());
                    EditText editText2 = (EditText) viewM1004b;
                    editText2.setFocusable(commandToUi.getEnabledStatus());
                    editText2.setFocusableInTouchMode(commandToUi.getEnabledStatus());
                } else {
                    viewM1004b.setEnabled(commandToUi.getEnabledStatus());
                }
                return true;
            case SET_LINE:
                if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.LINE) {
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

    /* JADX INFO: renamed from: f */
    private void m940f(String str) {
        if (this.f1270d.get(str) != null) {
            m931a(true);
            return;
        }
        if (this.f1271e[this.f1268b].get(str) == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(str);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
        layoutParams.width = -1;
        viewM1004b.setLayoutParams(layoutParams);
    }

    /* JADX INFO: renamed from: b */
    public final void m957b(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        new StringBuilder("SET FONT type: ").append(commandToUi.getFontType());
        String controlId = commandToUi.getControlId();
        Typeface typefaceCreate = Typeface.create(commandToUi.getFontType(), 0);
        if (typefaceCreate == null) {
            m931a(false);
            return;
        }
        int fontSize = commandToUi.getFontSize() < 0 ? 14 : commandToUi.getFontSize();
        if (this.f1270d.get(controlId) != null && this.f1267a[this.f1270d.get(controlId).intValue()] != null) {
            this.f1267a[this.f1270d.get(controlId).intValue()].m997a(typefaceCreate, fontSize);
            m931a(true);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        if (this.f1271e[this.f1268b].get(controlId) == null) {
            m931a(false);
            return;
        }
        switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId)) {
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
                m931a(false);
                return;
        }
        m931a(true);
    }

    /* JADX INFO: renamed from: e */
    private void m938e(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        commandToUi.getText();
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m931a(false);
        } else if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.EDIT_TEXT) {
            m931a(false);
        } else {
            ((EditText) viewM1004b).setInputType(commandToUi.getEditInputType());
            m931a(true);
        }
    }

    /* JADX INFO: renamed from: c */
    public final void m960c(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String text = commandToUi.getText();
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        if (this.f1271e[this.f1268b].get(controlId) == null) {
            m931a(false);
            return;
        }
        switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId)) {
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
                    m931a(false);
                    return;
                }
                radioButton.setText(text);
                break;
            case SPINNER:
                Spinner spinner = (Spinner) viewM1004b;
                if (commandToUi.getItemIndex() >= spinner.getCount()) {
                    m931a(false);
                    return;
                }
                ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < arrayAdapter.getCount(); i++) {
                    arrayList.add(arrayAdapter.getItem(i));
                }
                arrayList.set(commandToUi.getItemIndex(), text);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(this.f1273i, C0852R.layout.ui_show_spinner_dropdown_item, arrayList);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter2);
                arrayAdapter2.notifyDataSetChanged();
                break;
                break;
            default:
                m931a(false);
                return;
        }
        m931a(true);
    }

    /* JADX INFO: renamed from: f */
    private void m939f(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (!color.matches(f1265g)) {
            m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m931a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(color));
        if (this.f1270d.get(controlId) != null) {
            this.f1267a[this.f1270d.get(controlId).intValue()].m996a(iM1075a);
            m931a(true);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId)) {
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
                    m931a(false);
                    return;
                }
                radioButton.setTextColor(iM1075a);
                break;
            case SPINNER:
                if (commandToUi.getItemIndex() >= ((Spinner) viewM1004b).getCount()) {
                    m931a(false);
                    return;
                }
                break;
            default:
                m931a(false);
                return;
        }
        m931a(true);
    }

    /* JADX INFO: renamed from: g */
    private void m941g(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        View viewM1004b = this.f1267a[this.f1268b].m1004b(controlId);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        String color = commandToUi.getColor();
        if (!color.matches(f1265g)) {
            m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m931a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        int iM1075a = C0806c.m1075a(Color.parseColor(color));
        if (this.f1271e[this.f1268b].get(controlId) == EnumC0792m.BUTTON) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(iM1075a);
            gradientDrawable.setCornerRadius(m956b());
            if (Build.VERSION.SDK_INT >= 16) {
                viewM1004b.setBackground(gradientDrawable);
            } else {
                viewM1004b.setBackgroundDrawable(gradientDrawable);
            }
            m931a(true);
            return;
        }
        if (this.f1271e[this.f1268b].get(controlId) != EnumC0792m.SPINNER) {
            viewM1004b.setBackgroundColor(iM1075a);
        }
        m931a(true);
    }

    /* JADX INFO: renamed from: h */
    private void m943h(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(commandToUi.getControlId());
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewM1004b.getLayoutParams();
        layoutParams.gravity = commandToUi.getAlignType();
        viewM1004b.setLayoutParams(layoutParams);
        new StringBuilder("set gravity ok; ").append(layoutParams);
        m931a(true);
    }

    /* JADX INFO: renamed from: i */
    private void m945i(UiMessage.CommandToUi commandToUi) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(commandToUi.getControlId());
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        UiMessage.Padding_Var padding = commandToUi.getPadding();
        new StringBuilder("padding: ").append(padding.getLeft());
        new StringBuilder("padding: ").append(padding.getTop());
        new StringBuilder("padding: ").append(padding.getRight());
        new StringBuilder("padding: ").append(padding.getBottom());
        viewM1004b.setPadding(padding.getLeft(), padding.getTop(), padding.getRight(), padding.getBottom());
        m931a(true);
    }

    /* JADX INFO: renamed from: g */
    private void m942g(String str) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(str);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        boolean zIsEnabled = viewM1004b.isEnabled();
        if (this.f1271e[this.f1268b].get(str) == EnumC0792m.EDIT_TEXT) {
            zIsEnabled = ((EditText) viewM1004b).isFocusable();
        }
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(zIsEnabled).build()).build().toByteString());
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002b  */
    /* JADX INFO: renamed from: h */
    private void m944h(String str) {
        int i;
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(str);
        if (viewM1004b == null) {
            m931a(false);
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
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(i).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: j */
    private void m947j(UiMessage.CommandToUi commandToUi) {
        int color;
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f1271e[this.f1268b].get(controlId) == null) {
            m931a(false);
            return;
        }
        switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                color = ((TextView) this.f1267a[this.f1268b].m1004b(controlId)).getPaint().getColor();
                break;
            case CHECK_BOX:
                color = ((CheckBox) this.f1267a[this.f1268b].m1004b(controlId)).getPaint().getColor();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1267a[this.f1268b].m1004b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m931a(false);
                    return;
                }
                color = radioButton.getPaint().getColor();
                break;
            default:
                m931a(false);
                return;
        }
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(String.format("%02X%02X%02X", Integer.valueOf(Color.blue(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.red(color)))).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: i */
    private void m946i(String str) {
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        View viewM1004b = this.f1267a[this.f1268b].m1004b(str);
        if (viewM1004b == null) {
            m931a(false);
            return;
        }
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue("#" + Integer.toHexString(C0806c.m1075a(viewM1004b.getSolidColor()) & ViewCompat.MEASURED_SIZE_MASK)).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: k */
    private void m949k(UiMessage.CommandToUi commandToUi) {
        String string;
        if (this.f1267a[this.f1268b] == null) {
            m931a(false);
            return;
        }
        String controlId = commandToUi.getControlId();
        if (this.f1271e[this.f1268b].get(controlId) == null) {
            m931a(false);
            return;
        }
        switch ((EnumC0792m) this.f1271e[this.f1268b].get(controlId)) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                string = ((TextView) this.f1267a[this.f1268b].m1004b(controlId)).getText().toString();
                break;
            case CHECK_BOX:
                string = ((CheckBox) this.f1267a[this.f1268b].m1004b(controlId)).getText().toString();
                break;
            case RADIIO_GROUP:
                RadioButton radioButton = (RadioButton) ((RadioGroup) this.f1267a[this.f1268b].m1004b(controlId)).findViewById(commandToUi.getItemIndex());
                if (radioButton == null) {
                    m931a(false);
                    return;
                }
                string = radioButton.getText().toString();
                break;
            case SPINNER:
                Spinner spinner = (Spinner) this.f1267a[this.f1268b].m1004b(controlId);
                if (commandToUi.getItemIndex() >= spinner.getCount()) {
                    m931a(false);
                    return;
                } else {
                    new StringBuilder("Spinner Get pos: ").append(spinner.getItemAtPosition(commandToUi.getItemIndex()));
                    string = (String) spinner.getItemAtPosition(commandToUi.getItemIndex());
                }
                break;
            default:
                m931a(false);
                return;
        }
        C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(controlId).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(string).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: a */
    public final void m954a(String str) throws JSONException, IOException {
        if (this.f1267a[this.f1268b] == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : this.f1271e[this.f1268b].entrySet()) {
            String str2 = (String) entry.getKey();
            int i = AnonymousClass3.f1279b[((EnumC0792m) entry.getValue()).ordinal()];
            if (i == 2) {
                jSONObject.put(str2, ((EditText) this.f1267a[this.f1268b].m1004b(str2)).getText().toString());
            } else {
                switch (i) {
                    case 4:
                        jSONObject.put(str2, ((CheckBox) this.f1267a[this.f1268b].m1004b(str2)).isChecked());
                        break;
                    case 5:
                        jSONObject.put(str2, ((RadioGroup) this.f1267a[this.f1268b].m1004b(str2)).getCheckedRadioButtonId());
                        break;
                    case 6:
                        jSONObject.put(str2, ((Spinner) this.f1267a[this.f1268b].m1004b(str2)).getSelectedItemId());
                        break;
                }
            }
        }
        FileUtils.writeStringToFile(new File(str), jSONObject.toString());
    }

    /* JADX INFO: renamed from: b */
    public final void m958b(String str) throws JSONException, IOException {
        if (this.f1271e[this.f1268b] == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject(FileUtils.readFileToString(new File(str)));
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (this.f1271e[this.f1268b].get(next) != null) {
                int i = AnonymousClass3.f1279b[((EnumC0792m) this.f1271e[this.f1268b].get(next)).ordinal()];
                if (i == 2) {
                    ((EditText) this.f1267a[this.f1268b].m1004b(next)).setText(jSONObject.getString(next));
                } else {
                    switch (i) {
                        case 4:
                            ((CheckBox) this.f1267a[this.f1268b].m1004b(next)).setChecked(jSONObject.getBoolean(next));
                            break;
                        case 5:
                            RadioGroup radioGroup = (RadioGroup) this.f1267a[this.f1268b].m1004b(next);
                            if (jSONObject.getInt(next) < radioGroup.getChildCount()) {
                                ((RadioButton) radioGroup.findViewById(jSONObject.getInt(next))).setChecked(true);
                            }
                            break;
                        case 6:
                            Spinner spinner = (Spinner) this.f1267a[this.f1268b].m1004b(next);
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
    private void m930a(String str, int i, int i2) {
        this.f1268b++;
        this.f1267a[this.f1268b] = this.f1269c.m868a(str, i, i2);
        this.f1270d.put(str, Integer.valueOf(this.f1268b));
        this.f1271e[this.f1268b] = new HashMap();
        this.f1267a[this.f1268b].f1320b = new AnonymousClass1(str);
        this.f1267a[this.f1268b].f1319a = new AnonymousClass2(str);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.i$1, reason: invalid class name */
    /* JADX INFO: compiled from: UiManagerLite.java */
    public final class AnonymousClass1 implements View.OnClickListener {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f1274a;

        public AnonymousClass1(String str) {
            this.f1274a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1274a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.i$2, reason: invalid class name */
    /* JADX INFO: compiled from: UiManagerLite.java */
    public final class AnonymousClass2 implements View.OnClickListener {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f1276a;

        public AnonymousClass2(String str) {
            this.f1276a = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            UiMessage.UiToCommand uiToCommandBuild = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1276a).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).setIsSuccess(true).build();
            C0721c.m654b(uiToCommandBuild.toByteString());
            new StringBuilder("exit event: ").append(uiToCommandBuild);
        }
    }

    /* JADX INFO: renamed from: j */
    private void m948j(String str) {
        if (this.f1270d.get(str) == null) {
            m953a(C0852R.string.ui_show_not_found_ui, str);
            m931a(false);
        } else {
            if (this.f1267a[this.f1270d.get(str).intValue()].f1321c) {
                m931a(false);
                return;
            }
            this.f1267a[this.f1270d.get(str).intValue()].m1008c();
            m931a(true);
            C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_SHOW).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m932a(String str, String str2) {
        if (this.f1270d.get(str) == null) {
            m953a(C0852R.string.ui_show_not_found_ui, str);
            return false;
        }
        this.f1267a[this.f1270d.get(str).intValue()].m1000a(str2);
        return true;
    }

    /* JADX INFO: renamed from: k */
    private void m950k(String str) {
        if (this.f1270d.get(str) == null) {
            m953a(C0852R.string.ui_show_not_found_ui, str);
            m931a(false);
        } else {
            C0721c.m652a(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true).addVarTable(UiMessage.ControlVar.newBuilder().setControlId(str).setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(this.f1267a[this.f1270d.get(str).intValue()].m995a()).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: l */
    private void m951l(UiMessage.CommandToUi commandToUi) {
        String controlId = commandToUi.getControlId();
        String color = commandToUi.getColor();
        if (this.f1270d.get(controlId) == null) {
            m953a(C0852R.string.ui_show_not_found_ui, controlId);
            m931a(false);
            return;
        }
        if (!color.matches(f1265g)) {
            m953a(C0852R.string.ui_show_invalid_color_value, new Object[0]);
            m931a(false);
            return;
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        this.f1267a[this.f1270d.get(controlId).intValue()].m1006b(C0806c.m1075a(Color.parseColor(color)));
        m931a(true);
    }

    /* JADX INFO: renamed from: c */
    public static void m935c(String str) {
        C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_CONTINUE).build()).build().toByteString());
    }

    /* JADX INFO: renamed from: d */
    public final boolean m961d(String str) {
        if (this.f1270d.get(str) == null) {
            m953a(C0852R.string.ui_show_not_found_ui, str);
            return false;
        }
        if (!this.f1267a[this.f1270d.get(str).intValue()].f1321c) {
            return false;
        }
        this.f1267a[this.f1270d.get(str).intValue()].m1010e();
        if (this.f1270d.get(str).intValue() == this.f1268b) {
            this.f1268b--;
            while (this.f1268b > 0 && this.f1267a[this.f1268b] == null) {
                this.f1268b--;
            }
            if (this.f1268b < 0) {
                this.f1268b = 0;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: c */
    public final void m959c() {
        for (int i = 31; i > 0; i--) {
            C0790k c0790k = this.f1267a[i];
            if (c0790k != null && c0790k.f1321c) {
                c0790k.m1010e();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m953a(int i, Object... objArr) {
        ExToast.makeText(this.f1273i, String.format(this.f1273i.getString(i), objArr), ExToast.LENGTH_LONG).show();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: renamed from: e */
    public final void m962e(String str) {
        UiMessage.UiToCommand.Builder isSuccess = UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.RSP_MSG).setIsSuccess(true);
        UiMessage.ControlVar.Builder controlId = UiMessage.ControlVar.newBuilder().setControlId(str);
        EnumC0792m enumC0792m = null;
        int i = 0;
        while (true) {
            if (i >= 32) {
                i = 0;
                break;
            } else if (this.f1271e[i] != null && (enumC0792m = (EnumC0792m) this.f1271e[i].get(str)) != null) {
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
            m953a(C0852R.string.ui_show_not_found_widget, str);
            C0721c.m652a(isSuccess.setIsSuccess(false).build().toByteString());
            return;
        }
        switch (enumC0792m) {
            case TEXT_VIEW:
            case EDIT_TEXT:
            case BUTTON:
                controlId.setType(UiMessage.ControlVar.Data_Type.STRING).setStringValue(((TextView) this.f1267a[i].m1004b(str)).getText().toString());
                isSuccess.addVarTable(controlId.build());
                break;
            case CHECK_BOX:
                controlId.setType(UiMessage.ControlVar.Data_Type.BOOL).setBoolValue(((CheckBox) this.f1267a[i].m1004b(str)).isChecked());
                isSuccess.addVarTable(controlId.build());
                break;
            case RADIIO_GROUP:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((RadioGroup) this.f1267a[i].m1004b(str)).getCheckedRadioButtonId());
                isSuccess.addVarTable(controlId.build());
                break;
            case SPINNER:
                controlId.setType(UiMessage.ControlVar.Data_Type.INT).setIntValue(((Spinner) this.f1267a[i].m1004b(str)).getSelectedItemPosition());
                isSuccess.addVarTable(controlId.build());
                break;
            default:
                isSuccess.setIsSuccess(false);
                break;
        }
        new StringBuilder("Ui.getValue result: ").append(isSuccess.build());
        C0721c.m652a(isSuccess.build().toByteString());
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setEventArgs(String.valueOf(i)).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).build()).setIsSuccess(true).build().toByteString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.i$a */
    /* JADX INFO: compiled from: UiManagerLite.java */
    public class a implements TextWatcher {

        /* JADX INFO: renamed from: b */
        private String f1281b;

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public a(String str) {
            this.f1281b = str;
        }

        /* JADX INFO: renamed from: a */
        private String m963a() {
            return this.f1281b;
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            C0721c.m654b(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(this.f1281b).setType(UiMessage.ControlEvent.Event_Type.ON_CHANGE).build()).setIsSuccess(true).build().toByteString());
            ViewOnClickListenerC0788i.this.f1267a[ViewOnClickListenerC0788i.this.f1268b].m1005b();
        }
    }
}
