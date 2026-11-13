package com.p024ds.daisi.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mqm.MiscUtilities;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.warnship.autoplay.R;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.apache.commons.p092io.FileUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUIDialog extends BaseDialog implements View.OnClickListener {
    protected CommonLog commonLog;
    private ArrayList<CheckBox> mArrayListCheckBox;
    private ArrayList<EditText> mArrayListEditText;
    private ArrayList<Spinner> mArrayListSpinner;
    private Button mButtonSave;
    public Context mContext;
    private ImageView mImageViewSetting;
    private ScrollView mScrollViewUI;
    private LinearLayout mUIPLinearLayout;

    public ScriptUIDialog(Context context) {
        super(context, R.style.Theme_Dialog);
        this.mArrayListCheckBox = new ArrayList<>();
        this.mArrayListSpinner = new ArrayList<>();
        this.mArrayListEditText = new ArrayList<>();
        this.commonLog = new CommonLog();
        if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            if (getWindow() != null) {
                getWindow().setType(2005);
            }
        } else if (getWindow() != null) {
            getWindow().setType(2002);
        }
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_scriptui);
        setBlurEffect(0.1f);
        if (this.mAppContext.checkedScreenOrientation) {
            setDialogSize(0.9f, 0.8f);
        } else {
            setDialogSize(0.9f, 0.6f);
        }
        initView();
        initUIFile(2, true);
        initEvent();
    }

    private void initEvent() {
        this.mAppContext.setScreenConversion(new AppContext.ScreenOrientationConversion() { // from class: com.ds.daisi.dialog.ScriptUIDialog.1
            @Override // com.ds.daisi.AppContext.ScreenOrientationConversion
            public void onLandscapeOrientation() {
                ScriptUIDialog.this.convertScreen(0.9f, 0.8f, 2, false);
            }

            @Override // com.ds.daisi.AppContext.ScreenOrientationConversion
            public void onPortraitOrientation() {
                ScriptUIDialog.this.convertScreen(0.9f, 0.6f, 1, false);
            }
        });
    }

    private void initView() {
        this.mImageViewSetting = (ImageView) findViewById(R.id.scriptui_imageview_setting);
        this.mButtonSave = (Button) findViewById(R.id.scriptui_button_savesetting);
        this.mScrollViewUI = (ScrollView) findViewById(R.id.scriptui_scrollview);
        this.mImageViewSetting.setOnClickListener(this);
        this.mButtonSave.setOnClickListener(this);
        this.mUIPLinearLayout = (LinearLayout) findViewById(R.id.ll_dialog_ui_parent);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mUIPLinearLayout.getLayoutParams();
        layoutParams.width = (int) (AppDeviceUtils.getResolution(getContext()).x * 0.9f);
        this.mUIPLinearLayout.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initUIFile(int i, boolean z) {
        MiscUtilities miscUtilities = new MiscUtilities();
        if (this.mAppContext.mScript.getUipFile().exists() && this.mAppContext.mScript.getUipFile().length() > 0) {
            String strLoadUIFile = miscUtilities.LoadUIFile(this.mAppContext.mScript.getUipFile().getAbsolutePath(), true);
            this.commonLog.m1348e("转换后>>>" + strLoadUIFile);
            if (z) {
                LinearLayout layoutFromJson = this.mAppContext.mUipHelper.parseLayoutFromJson(strLoadUIFile, null);
                this.mUIPLinearLayout.removeAllViews();
                this.mUIPLinearLayout.addView(layoutFromJson);
                try {
                    parseUIConfigFile(this.mAppContext.mScript.getCfgFile());
                    return;
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                    return;
                }
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mUIPLinearLayout.getLayoutParams();
            layoutParams.width = (int) (AppDeviceUtils.getResolution(getContext()).x * 0.9f);
            this.mUIPLinearLayout.setLayoutParams(layoutParams);
            View viewTransform = this.mAppContext.mUipHelper.transform(i);
            this.mUIPLinearLayout.removeAllViews();
            this.mUIPLinearLayout.addView(viewTransform);
            return;
        }
        String strLoadUIFile2 = miscUtilities.LoadUIFile(this.mAppContext.mScript.getUiFile().getAbsolutePath(), true);
        this.commonLog.m1348e("转换后>>>" + strLoadUIFile2);
        LinearLayout linearLayoutParsUIFile = ParsUIFile(strLoadUIFile2);
        if (linearLayoutParsUIFile.getChildCount() > 0) {
            this.mScrollViewUI.removeViewAt(0);
            this.mScrollViewUI.addView(linearLayoutParsUIFile);
        }
        try {
            parseUIConfigFile(this.mAppContext.mScript.getCfgFile());
        } catch (JSONException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    private LinearLayout ParsUIFile(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        LinearLayout linearLayout = new LinearLayout(getContext());
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().startsWith(getContext().getString(R.string.ui_activity))) {
                    linearLayout = ParMainLayout(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (Exception unused) {
        }
        return linearLayout;
    }

    private LinearLayout ParMainLayout(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getContext().getString(R.string.ui_linearlayout).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsHorizontalLayout(jsonReader));
            } else if (getContext().getString(R.string.ui_textview).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsTextView(jsonReader));
            } else if (getContext().getString(R.string.ui_edittext).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsEditText(jsonReader));
            } else if (getContext().getString(R.string.ui_checkbox).equals(strNextName)) {
                linearLayout.addView(ParsCheckBox(jsonReader));
            } else if (getContext().getString(R.string.ui_spinner).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParSpinner(jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    private LinearLayout ParsHorizontalLayout(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getContext().getString(R.string.ui_linearlayout).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsHorizontalLayout(jsonReader));
            } else if (getContext().getString(R.string.ui_textview).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsTextView(jsonReader));
            } else if (getContext().getString(R.string.ui_edittext).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsEditText(jsonReader));
            } else if (getContext().getString(R.string.ui_checkbox).equals(strNextName)) {
                linearLayout.addView(ParsCheckBox(jsonReader));
            } else if (getContext().getString(R.string.ui_spinner).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParSpinner(jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    private TextView ParsTextView(JsonReader jsonReader) throws IOException {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(getInteger(R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getContext().getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                textView.setTag(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_textview_textcontent).equalsIgnoreCase(strNextName)) {
                textView.setText(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > getInteger(R.integer.ui_textsize_min)) {
                    textView.setTextSize(iNextInt);
                } else if (iNextInt != getInteger(R.integer.ui_textSize_default)) {
                    textView.setTextSize(getInteger(R.integer.ui_textsize_min));
                }
            } else if (getContext().getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > getInteger(R.integer.ui_default_height)) {
                    textView.setHeight(iNextInt2);
                }
            } else if (getContext().getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > getInteger(R.integer.ui_default_width)) {
                    textView.setWidth(iNextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return textView;
    }

    private EditText ParsEditText(JsonReader jsonReader) throws IOException {
        boolean zNextBoolean;
        EditText editText = new EditText(getContext());
        editText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        editText.setTextSize(getInteger(R.integer.ui_textSize_normal));
        editText.setSingleLine(true);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getContext().getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                editText.setTag(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_edittext_hintcontent).equalsIgnoreCase(strNextName)) {
                editText.setHint(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > getInteger(R.integer.ui_textsize_min)) {
                    editText.setTextSize(iNextInt);
                } else if (iNextInt != getInteger(R.integer.ui_textSize_default)) {
                    editText.setTextSize(getInteger(R.integer.ui_textsize_min));
                }
            } else if (getContext().getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > getInteger(R.integer.ui_default_height)) {
                    editText.setHeight(iNextInt2);
                }
            } else if (getContext().getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > getInteger(R.integer.ui_default_width)) {
                    editText.setWidth(iNextInt3);
                }
            } else if (getContext().getString(R.string.ui_edittext_maxlength).equalsIgnoreCase(strNextName)) {
                int iNextInt4 = jsonReader.nextInt();
                if (iNextInt4 > 0) {
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(iNextInt4)});
                }
            } else if (getContext().getString(R.string.ui_edittext_inputnumber).equalsIgnoreCase(strNextName)) {
                try {
                    zNextBoolean = jsonReader.nextBoolean();
                } catch (Exception unused) {
                    zNextBoolean = false;
                }
                if (zNextBoolean) {
                    editText.setInputType(2);
                }
            } else if (getContext().getString(R.string.ui_edittext_defaultcontent).equalsIgnoreCase(strNextName)) {
                editText.setText(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
            }
        }
        this.mArrayListEditText.add(editText);
        jsonReader.endObject();
        return editText;
    }

    private CheckBox ParsCheckBox(JsonReader jsonReader) throws IOException {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        checkBox.setTextSize(getInteger(R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getContext().getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                checkBox.setTag(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_checkbox_hintcontent).equalsIgnoreCase(strNextName)) {
                checkBox.setText(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_checkbox_checked).equalsIgnoreCase(strNextName)) {
                if (jsonReader.nextBoolean()) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            } else if (getContext().getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > getInteger(R.integer.ui_textsize_min)) {
                    checkBox.setTextSize(iNextInt);
                } else if (iNextInt != getInteger(R.integer.ui_textSize_default)) {
                    checkBox.setTextSize(getInteger(R.integer.ui_textsize_min));
                }
            } else if (getContext().getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > getInteger(R.integer.ui_default_height)) {
                    checkBox.setHeight(iNextInt2);
                }
            } else if (getContext().getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > getInteger(R.integer.ui_default_width)) {
                    checkBox.setWidth(iNextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        this.mArrayListCheckBox.add(checkBox);
        jsonReader.endObject();
        return checkBox;
    }

    private Spinner ParSpinner(JsonReader jsonReader) throws IOException {
        Spinner spinner = new Spinner(getContext());
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            ArrayList arrayList = new ArrayList();
            if (getContext().getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                spinner.setTag(jsonReader.nextString());
            } else if (getContext().getString(R.string.ui_spinner_items).equalsIgnoreCase(strNextName)) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
            } else if (getContext().getString(R.string.ui_spinner_defaultitem).equalsIgnoreCase(strNextName)) {
                try {
                    spinner.setSelection(jsonReader.nextInt(), true);
                } catch (Exception unused) {
                    spinner.setSelection(0);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        this.mArrayListSpinner.add(spinner);
        return spinner;
    }

    private void parseUIConfigFile(File file) throws JSONException {
        String fileToString;
        if (file == null || !file.exists() || file.isDirectory()) {
            return;
        }
        try {
            fileToString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            fileToString = null;
        }
        this.commonLog.m1348e("json>>>" + fileToString);
        if (this.mAppContext.mScript.getUipFile().exists() && this.mAppContext.mScript.getUipFile().length() > 0) {
            this.mAppContext.mUipHelper.configViewFromJson(fileToString);
            this.commonLog.m1348e("configViewFromJson>>>" + fileToString);
            return;
        }
        JSONObject jSONObject = new JSONObject(fileToString);
        for (Spinner spinner : this.mArrayListSpinner) {
            spinner.setSelection(jSONObject.getInt(spinner.getTag().toString()));
        }
        for (CheckBox checkBox : this.mArrayListCheckBox) {
            checkBox.setChecked(jSONObject.getBoolean(checkBox.getTag().toString()));
        }
        for (EditText editText : this.mArrayListEditText) {
            editText.setText(jSONObject.getString(editText.getTag().toString()));
        }
    }

    private void generateUIConfigFile(File file) throws Exception {
        if (file == null || file.isDirectory()) {
            return;
        }
        if (this.mAppContext.mScript.getUipFile().exists() && this.mAppContext.mScript.getUipFile().length() > 0) {
            this.mAppContext.mUipHelper.saveToConfigFile(this.mAppContext.mScript.getCfgFile().getAbsolutePath());
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (CheckBox checkBox : this.mArrayListCheckBox) {
            jSONObject.put(checkBox.getTag().toString(), checkBox.isChecked());
        }
        for (Spinner spinner : this.mArrayListSpinner) {
            jSONObject.put(spinner.getTag().toString(), spinner.getSelectedItemPosition());
        }
        for (EditText editText : this.mArrayListEditText) {
            jSONObject.put(editText.getTag().toString(), editText.getText().toString());
        }
        FileUtils.writeStringToFile(file, jSONObject.toString(), "UTF-8");
    }

    private int getInteger(int i) {
        return getContext().getResources().getInteger(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.scriptui_imageview_setting) {
            dismiss();
            final SettingDialog settingDialog = new SettingDialog(getContext());
            settingDialog.mContext = this.mContext;
            settingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ds.daisi.dialog.ScriptUIDialog.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (settingDialog.isDismiss()) {
                        ScriptUIDialog.this.dismiss();
                    }
                }
            });
            settingDialog.show();
            return;
        }
        if (id != R.id.scriptui_button_savesetting) {
            return;
        }
        try {
            generateUIConfigFile(this.mAppContext.mScript.getCfgFile());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        EventBus.getDefault().post(new MsgItem(1004));
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertScreen(final float f, final float f2, final int i, final boolean z) {
        if (isShowing()) {
            dismiss();
            new Handler().postDelayed(new Runnable() { // from class: com.ds.daisi.dialog.ScriptUIDialog.3
                @Override // java.lang.Runnable
                public void run() {
                    ScriptUIDialog.this.setDialogSize(f, f2);
                    ScriptUIDialog.this.initUIFile(i, z);
                    ScriptUIDialog.this.show();
                }
            }, 500L);
        }
    }
}
