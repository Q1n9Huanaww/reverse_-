package com.cyjh.mobileanjian.ipc.uip;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.JsonReader;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.p018ui.SlidingTabLayout;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mobileanjian.ipc.uip.C0800c.AnonymousClass1;
import com.cyjh.mqsdk.C0852R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.p092io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UipHelper implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final int UIP_MESSAGE = 0;

    /* JADX INFO: renamed from: a */
    private static final String f1360a = new File(Environment.getExternalStorageDirectory(), ".uip.config.json").getAbsolutePath();

    /* JADX INFO: renamed from: b */
    private Context f1361b;

    /* JADX INFO: renamed from: c */
    private SlidingTabLayout f1362c;

    /* JADX INFO: renamed from: d */
    private C0798a f1363d;

    /* JADX INFO: renamed from: e */
    private C0800c f1364e;

    /* JADX INFO: renamed from: f */
    private List<ScrollView> f1365f;

    /* JADX INFO: renamed from: g */
    private boolean f1366g;

    /* JADX INFO: renamed from: h */
    private Handler f1367h = new Handler() { // from class: com.cyjh.mobileanjian.ipc.uip.UipHelper.2
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            try {
                UiMessage.CommandToUip from = UiMessage.CommandToUip.parseFrom((ByteString) message.obj);
                UiMessage.CommandToUip.Command_Type command = from.getCommand();
                String controlId = from.getControlId();
                new StringBuilder("GET UIP Command -->").append(from);
                if (command == UiMessage.CommandToUip.Command_Type.GET_UIP_ATTRIBUTE) {
                    UipHelper.m1019a(UipHelper.this, controlId);
                } else if (command == UiMessage.CommandToUip.Command_Type.SET_UIP_ATTRIBUTE) {
                    UipHelper.m1020a(UipHelper.this, controlId, from.getUipAttributeData());
                }
            } catch (InvalidProtocolBufferException e) {
                ThrowableExtension.printStackTrace(e);
            } catch (JSONException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
    };

    /* JADX INFO: renamed from: i */
    private JSONObject f1368i;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public UipHelper(Context context) {
        this.f1361b = context;
    }

    public LinearLayout parseLayoutFromJson(String str, IUipJsonParser iUipJsonParser) {
        if (this.f1361b.getResources().getConfiguration().orientation == 2) {
            return m1025c(str, iUipJsonParser);
        }
        return m1023b(str, iUipJsonParser);
    }

    /* JADX INFO: renamed from: a */
    private LinearLayout m1017a(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f1361b);
        }
        LinearLayout linearLayout = new LinearLayout(this.f1361b);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        ViewPager viewPager = new ViewPager(this.f1361b);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams2);
        linearLayout.addView(viewPager);
        this.f1363d = new C0798a(this.f1361b);
        this.f1363d.setLayoutParams(new LinearLayout.LayoutParams(-2, this.f1363d.m1046a(20.0f)));
        this.f1363d.setPadding(0, this.f1363d.m1046a(10.0f), 0, 0);
        linearLayout.addView(this.f1363d);
        List<ScrollView> arrayList = new ArrayList<>(32);
        ArrayList arrayList2 = new ArrayList(32);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f1361b.getString(C0852R.string.ui_activity))) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String strNextName = jsonReader.nextName();
                    LinearLayout linearLayout2 = new LinearLayout(this.f1361b);
                    linearLayout2.setOrientation(1);
                    linearLayout2.setLayoutParams(layoutParams);
                    ScrollView scrollView = new ScrollView(this.f1361b);
                    scrollView.setTag(strNextName);
                    scrollView.setLayoutParams(layoutParams);
                    scrollView.addView(linearLayout2);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_linearlayout))) {
                            linearLayout2.addView(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_textview))) {
                            linearLayout2.addView(iUipJsonParser.parseTextView(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_button))) {
                            linearLayout2.addView(iUipJsonParser.parseButton(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_edittext))) {
                            linearLayout2.addView(iUipJsonParser.parseEditText(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_checkbox))) {
                            linearLayout2.addView(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_spinner))) {
                            linearLayout2.addView(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList.size() < 32) {
                        arrayList2.add(strNextName);
                        arrayList.add(scrollView);
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalStateException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        viewPager.setAdapter(new C0803f(arrayList, arrayList2));
        this.f1363d.setViewPager(viewPager);
        bindEvent(arrayList);
        saveToConfigFile(f1360a);
        return linearLayout;
    }

    /* JADX INFO: renamed from: b */
    private LinearLayout m1023b(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f1361b);
        }
        LinearLayout linearLayout = new LinearLayout(this.f1361b);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        this.f1362c = new SlidingTabLayout(this.f1361b);
        this.f1362c.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f1362c.setSelectedIndicatorColors(this.f1361b.getResources().getColor(C0852R.color.ui_show_blue));
        this.f1362c.setDistributeEvenly(true);
        linearLayout.addView(this.f1362c);
        ViewPager viewPager = new ViewPager(this.f1361b);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams2);
        linearLayout.addView(viewPager);
        List<ScrollView> arrayList = new ArrayList<>(32);
        ArrayList arrayList2 = new ArrayList(32);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f1361b.getString(C0852R.string.ui_activity))) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String strNextName = jsonReader.nextName();
                    LinearLayout linearLayout2 = new LinearLayout(this.f1361b);
                    linearLayout2.setOrientation(1);
                    linearLayout2.setLayoutParams(layoutParams);
                    ScrollView scrollView = new ScrollView(this.f1361b);
                    scrollView.setTag(strNextName);
                    scrollView.setLayoutParams(layoutParams);
                    scrollView.addView(linearLayout2);
                    scrollView.setDescendantFocusability(131072);
                    scrollView.setFocusable(true);
                    scrollView.setFocusableInTouchMode(true);
                    scrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyjh.mobileanjian.ipc.uip.UipHelper.1
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            view.requestFocusFromTouch();
                            return false;
                        }
                    });
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_linearlayout))) {
                            linearLayout2.addView(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_textview))) {
                            linearLayout2.addView(iUipJsonParser.parseTextView(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_button))) {
                            linearLayout2.addView(iUipJsonParser.parseButton(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_edittext))) {
                            linearLayout2.addView(iUipJsonParser.parseEditText(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_checkbox))) {
                            linearLayout2.addView(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_spinner))) {
                            linearLayout2.addView(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList.size() < 32) {
                        arrayList2.add(strNextName);
                        arrayList.add(scrollView);
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalStateException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        viewPager.setAdapter(new C0803f(arrayList, arrayList2));
        this.f1362c.setViewPager(viewPager);
        bindEvent(arrayList);
        saveToConfigFile(f1360a);
        return linearLayout;
    }

    /* JADX INFO: renamed from: c */
    private LinearLayout m1025c(String str, IUipJsonParser iUipJsonParser) {
        if (iUipJsonParser == null) {
            iUipJsonParser = new DefaultUipJsonParser(this.f1361b);
        }
        this.f1364e = new C0800c(this.f1361b);
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && jsonReader.nextName().startsWith(this.f1361b.getString(C0852R.string.ui_activity))) {
                jsonReader.beginObject();
                for (int i = 0; i < 32 && jsonReader.hasNext(); i++) {
                    String strNextName = jsonReader.nextName();
                    C0800c c0800c = this.f1364e;
                    RadioButton radioButton = new RadioButton(c0800c.getContext());
                    radioButton.setId(C0800c.f1400a[c0800c.f1407g]);
                    radioButton.setPadding(0, 0, 0, c0800c.f1404d);
                    radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
                    radioButton.setTextColor(c0800c.getResources().getColorStateList(C0852R.color.selector_tab));
                    radioButton.setText(strNextName);
                    radioButton.setLines(1);
                    radioButton.setEllipsize(TextUtils.TruncateAt.END);
                    c0800c.f1402b.addView(radioButton);
                    c0800c.f1406f = new ScrollView(c0800c.getContext());
                    c0800c.f1406f.setDescendantFocusability(131072);
                    c0800c.f1406f.setFocusable(true);
                    c0800c.f1406f.setFocusableInTouchMode(true);
                    c0800c.f1406f.setOnTouchListener(c0800c.new AnonymousClass1());
                    c0800c.f1406f.setTag(strNextName);
                    c0800c.f1406f.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    LinearLayout linearLayout = new LinearLayout(c0800c.getContext());
                    linearLayout.setOrientation(1);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    c0800c.f1405e = linearLayout;
                    c0800c.f1406f.addView(c0800c.f1405e);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_linearlayout))) {
                            this.f1364e.m1054a(iUipJsonParser.parseHorizontalLinearLayout(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_textview))) {
                            this.f1364e.m1054a(iUipJsonParser.parseTextView(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_button))) {
                            this.f1364e.m1054a(iUipJsonParser.parseButton(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_edittext))) {
                            this.f1364e.m1054a(iUipJsonParser.parseEditText(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_checkbox))) {
                            this.f1364e.m1054a(iUipJsonParser.parseCheckBox(jsonReader));
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_spinner))) {
                            this.f1364e.m1054a(iUipJsonParser.parseSpinner(jsonReader));
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    C0800c c0800c2 = this.f1364e;
                    c0800c2.f1403c.add(c0800c2.f1406f);
                    c0800c2.f1407g++;
                }
                jsonReader.endObject();
            }
            jsonReader.endObject();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalStateException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        this.f1364e.setSelectTab(0);
        bindEvent(this.f1364e.getScrollViewList());
        saveToConfigFile(f1360a);
        return this.f1364e;
    }

    public View transform(int i) {
        for (ScrollView scrollView : this.f1365f) {
            ViewParent parent = scrollView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(scrollView);
            }
            new StringBuilder("childCount of scrollview: ").append(((ViewGroup) scrollView.getChildAt(0)).getChildCount());
        }
        if (i == 2) {
            this.f1364e = new C0800c(this.f1361b);
            this.f1364e.m1055a(this.f1365f);
            this.f1364e.setSelectTab(this.f1362c.getViewPager().getCurrentItem());
            return this.f1364e;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ScrollView> it2 = this.f1365f.iterator();
        while (it2.hasNext()) {
            arrayList.add((String) it2.next().getTag());
        }
        LinearLayout linearLayout = new LinearLayout(this.f1361b);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        this.f1362c = new SlidingTabLayout(this.f1361b);
        this.f1362c.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f1362c.setSelectedIndicatorColors(this.f1361b.getResources().getColor(C0852R.color.ui_show_blue));
        this.f1362c.setDistributeEvenly(true);
        linearLayout.addView(this.f1362c);
        ViewPager viewPager = new ViewPager(this.f1361b);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(-1, -1);
        layoutParams.weight = 1.0f;
        viewPager.setLayoutParams(layoutParams);
        linearLayout.addView(viewPager);
        viewPager.setAdapter(new C0803f(this.f1365f, arrayList));
        viewPager.setCurrentItem(this.f1364e.getCurrentItem());
        this.f1362c.setViewPager(viewPager);
        return linearLayout;
    }

    public void bindEvent(List<ScrollView> list) {
        UipEventStub.registerHandler(this.f1367h);
        this.f1365f = list;
        Iterator<ScrollView> it2 = list.iterator();
        while (it2.hasNext()) {
            m1018a(it2.next());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1018a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof C0799b) || (childAt instanceof LinearLayout)) {
                m1018a((ViewGroup) childAt);
            } else if (!TextUtils.isEmpty((CharSequence) childAt.getTag(C0852R.id.uip_function_key))) {
                if (childAt instanceof Spinner) {
                    ((Spinner) childAt).setOnItemSelectedListener(this);
                } else if (childAt instanceof CheckBox) {
                    ((CheckBox) childAt).setOnClickListener(this);
                } else if (childAt instanceof Button) {
                    ((Button) childAt).setOnClickListener(this);
                }
            }
        }
    }

    public void restoreConfig() {
        configViewFromFile(f1360a);
    }

    /* JADX INFO: renamed from: a */
    private void m1021a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Iterator<ScrollView> it2 = this.f1365f.iterator();
        while (it2.hasNext()) {
            View viewFindViewWithTag = it2.next().findViewWithTag(str);
            if (viewFindViewWithTag instanceof Spinner) {
                Spinner spinner = (Spinner) viewFindViewWithTag;
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_spinner_defaultitem), spinner.getSelectedItemPosition());
                List<String> list = ((C0802e) spinner.getAdapter()).f1412a;
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it3 = list.iterator();
                while (it3.hasNext()) {
                    jSONArray.put(it3.next());
                }
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_spinner_items), jSONArray);
                break;
            }
            if (viewFindViewWithTag instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) viewFindViewWithTag;
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_checkbox_hintcontent), checkBox.getText().toString());
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_checkbox_checked), checkBox.isChecked());
                break;
            } else if (viewFindViewWithTag instanceof Button) {
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_textview_textcontent), ((Button) viewFindViewWithTag).getText().toString());
                break;
            } else if (viewFindViewWithTag instanceof EditText) {
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_edittext_defaultcontent), ((EditText) viewFindViewWithTag).getText().toString());
                break;
            } else if (viewFindViewWithTag instanceof TextView) {
                jSONObject.put(this.f1361b.getString(C0852R.string.ui_textview_textcontent), ((TextView) viewFindViewWithTag).getText().toString());
                break;
            }
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).setUipAttributeData(jSONObject.toString()).build().toByteString());
    }

    /* JADX INFO: renamed from: a */
    private void m1022a(String str, String str2) {
        JsonReader jsonReader = new JsonReader(new StringReader(str2));
        try {
            Iterator<ScrollView> it2 = this.f1365f.iterator();
            while (it2.hasNext()) {
                View viewFindViewWithTag = it2.next().findViewWithTag(str);
                if (viewFindViewWithTag instanceof Spinner) {
                    Spinner spinner = (Spinner) viewFindViewWithTag;
                    int iNextInt = -1;
                    ArrayList arrayList = null;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName = jsonReader.nextName();
                        if (strNextName.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_spinner_defaultitem))) {
                            iNextInt = jsonReader.nextInt();
                        } else if (strNextName.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_spinner_items))) {
                            arrayList = new ArrayList();
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                arrayList.add(jsonReader.nextString());
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList != null) {
                        C0802e c0802e = new C0802e(this.f1361b, arrayList);
                        c0802e.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter((SpinnerAdapter) c0802e);
                    }
                    if (iNextInt < 0 || iNextInt >= spinner.getCount()) {
                        break;
                        break;
                    } else {
                        spinner.setSelection(iNextInt);
                        break;
                    }
                }
                if (viewFindViewWithTag instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_checkbox_hintcontent))) {
                            checkBox.setText(jsonReader.nextString());
                        } else if (strNextName2.equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_checkbox_checked))) {
                            checkBox.setChecked(jsonReader.nextBoolean());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof Button) {
                    Button button = (Button) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_textview_textcontent))) {
                            button.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof EditText) {
                    EditText editText = (EditText) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_edittext_defaultcontent))) {
                            editText.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof TextView) {
                    TextView textView = (TextView) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(this.f1361b.getString(C0852R.string.ui_textview_textcontent))) {
                            textView.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalStateException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).build().toByteString());
    }

    public void configViewFromFile(String str) {
        try {
            configViewFromJson(FileUtils.readFileToString(new File(str)));
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void configViewFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f1366g = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Iterator<ScrollView> it2 = this.f1365f.iterator();
                while (it2.hasNext()) {
                    View viewFindViewWithTag = it2.next().findViewWithTag(next);
                    if (viewFindViewWithTag != null) {
                        if (viewFindViewWithTag instanceof CheckBox) {
                            ((CheckBox) viewFindViewWithTag).setChecked(jSONObject.getBoolean(next));
                        } else if (viewFindViewWithTag instanceof Spinner) {
                            Spinner spinner = (Spinner) viewFindViewWithTag;
                            spinner.setSelection(jSONObject.getInt(next) < spinner.getAdapter().getCount() ? jSONObject.getInt(next) : 0);
                        } else if (viewFindViewWithTag instanceof EditText) {
                            ((EditText) viewFindViewWithTag).setText(jSONObject.getString(next));
                        }
                    }
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.f1366g = false;
    }

    public String saveConfigToJson() {
        this.f1368i = new JSONObject();
        Iterator<ScrollView> it2 = this.f1365f.iterator();
        while (it2.hasNext()) {
            try {
                m1024b((LinearLayout) it2.next().getChildAt(0));
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return this.f1368i.toString();
    }

    public void saveToConfigFile(String str) {
        try {
            FileUtils.writeStringToFile(new File(str), saveConfigToJson());
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1024b(ViewGroup viewGroup) throws JSONException {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            String str = (String) childAt.getTag();
            if ((childAt instanceof LinearLayout) || (childAt instanceof C0799b)) {
                m1024b((ViewGroup) childAt);
            } else if (childAt instanceof CheckBox) {
                this.f1368i.put(str, ((CheckBox) childAt).isChecked());
            } else if (childAt instanceof Spinner) {
                this.f1368i.put(str, ((Spinner) childAt).getSelectedItemPosition());
            } else if (childAt instanceof EditText) {
                this.f1368i.put(str, ((EditText) childAt).getText().toString());
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f1366g) {
            return;
        }
        UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId((String) view.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).setEventFunctionName((String) view.getTag(C0852R.id.uip_function_key)).build()).build().toByteString());
        new StringBuilder("onClick Function Name: ").append((String) view.getTag(C0852R.id.uip_function_key));
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        new StringBuilder("isCodeEvent: ").append(this.f1366g);
        if (this.f1366g) {
            return;
        }
        UiMessage.ControlEvent controlEventBuild = UiMessage.ControlEvent.newBuilder().setControlId((String) adapterView.getTag()).setType(UiMessage.ControlEvent.Event_Type.ON_CLICK).setEventArgs(String.valueOf(i)).setEventFunctionName((String) adapterView.getTag(C0852R.id.uip_function_key)).build();
        UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(controlEventBuild).build().toByteString());
        new StringBuilder("onItemSelected: ").append(controlEventBuild);
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1019a(UipHelper uipHelper, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Iterator<ScrollView> it2 = uipHelper.f1365f.iterator();
        while (it2.hasNext()) {
            View viewFindViewWithTag = it2.next().findViewWithTag(str);
            if (viewFindViewWithTag instanceof Spinner) {
                Spinner spinner = (Spinner) viewFindViewWithTag;
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_spinner_defaultitem), spinner.getSelectedItemPosition());
                List<String> list = ((C0802e) spinner.getAdapter()).f1412a;
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it3 = list.iterator();
                while (it3.hasNext()) {
                    jSONArray.put(it3.next());
                }
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_spinner_items), jSONArray);
                break;
            }
            if (viewFindViewWithTag instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) viewFindViewWithTag;
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_checkbox_hintcontent), checkBox.getText().toString());
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_checkbox_checked), checkBox.isChecked());
                break;
            } else if (viewFindViewWithTag instanceof Button) {
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_textview_textcontent), ((Button) viewFindViewWithTag).getText().toString());
                break;
            } else if (viewFindViewWithTag instanceof EditText) {
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_edittext_defaultcontent), ((EditText) viewFindViewWithTag).getText().toString());
                break;
            } else if (viewFindViewWithTag instanceof TextView) {
                jSONObject.put(uipHelper.f1361b.getString(C0852R.string.ui_textview_textcontent), ((TextView) viewFindViewWithTag).getText().toString());
                break;
            }
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).setUipAttributeData(jSONObject.toString()).build().toByteString());
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1020a(UipHelper uipHelper, String str, String str2) {
        JsonReader jsonReader = new JsonReader(new StringReader(str2));
        try {
            Iterator<ScrollView> it2 = uipHelper.f1365f.iterator();
            while (it2.hasNext()) {
                View viewFindViewWithTag = it2.next().findViewWithTag(str);
                if (viewFindViewWithTag instanceof Spinner) {
                    Spinner spinner = (Spinner) viewFindViewWithTag;
                    int iNextInt = -1;
                    ArrayList arrayList = null;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName = jsonReader.nextName();
                        if (strNextName.equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_spinner_defaultitem))) {
                            iNextInt = jsonReader.nextInt();
                        } else if (strNextName.equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_spinner_items))) {
                            arrayList = new ArrayList();
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                arrayList.add(jsonReader.nextString());
                            }
                            jsonReader.endArray();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    if (arrayList != null) {
                        C0802e c0802e = new C0802e(uipHelper.f1361b, arrayList);
                        c0802e.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter((SpinnerAdapter) c0802e);
                    }
                    if (iNextInt < 0 || iNextInt >= spinner.getCount()) {
                        break;
                        break;
                    } else {
                        spinner.setSelection(iNextInt);
                        break;
                    }
                }
                if (viewFindViewWithTag instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        if (strNextName2.equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_checkbox_hintcontent))) {
                            checkBox.setText(jsonReader.nextString());
                        } else if (strNextName2.equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_checkbox_checked))) {
                            checkBox.setChecked(jsonReader.nextBoolean());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof Button) {
                    Button button = (Button) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_textview_textcontent))) {
                            button.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof EditText) {
                    EditText editText = (EditText) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_edittext_defaultcontent))) {
                            editText.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
                if (viewFindViewWithTag instanceof TextView) {
                    TextView textView = (TextView) viewFindViewWithTag;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        if (jsonReader.nextName().equalsIgnoreCase(uipHelper.f1361b.getString(C0852R.string.ui_textview_textcontent))) {
                            textView.setText(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    break;
                }
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalStateException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        UipEventStub.UiRequestReturn(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.RSP_MSG).setIsSuccess(true).build().toByteString());
    }
}
