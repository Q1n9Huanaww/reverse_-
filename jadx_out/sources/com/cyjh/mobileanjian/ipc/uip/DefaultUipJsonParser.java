package com.cyjh.mobileanjian.ipc.uip;

import android.R;
import android.content.Context;
import android.text.InputFilter;
import android.util.JsonReader;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mqsdk.C0852R;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DefaultUipJsonParser implements IUipJsonParser {
    protected Context mContext;
    private C0799b.a mDefaultFlowLayoutChildLayoutParams = new C0799b.a(-2, -2);

    public DefaultUipJsonParser(Context context) {
        this.mContext = context;
        this.mDefaultFlowLayoutChildLayoutParams.f1399a = 17;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public C0799b parseHorizontalLinearLayout(JsonReader jsonReader) throws IllegalStateException, IOException {
        C0799b c0799b = new C0799b(this.mContext);
        c0799b.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        c0799b.setGravity(16);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (this.mContext.getString(C0852R.string.ui_linearlayout).equalsIgnoreCase(strNextName)) {
                c0799b.addView(parseHorizontalLinearLayout(jsonReader));
            } else if (this.mContext.getString(C0852R.string.ui_textview).equalsIgnoreCase(strNextName)) {
                c0799b.addView(parseTextView(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C0852R.string.ui_edittext).equalsIgnoreCase(strNextName)) {
                c0799b.addView(parseEditText(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C0852R.string.ui_checkbox).equals(strNextName)) {
                c0799b.addView(parseCheckBox(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C0852R.string.ui_spinner).equalsIgnoreCase(strNextName)) {
                c0799b.addView(parseSpinner(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else if (this.mContext.getString(C0852R.string.ui_button).equalsIgnoreCase(strNextName)) {
                c0799b.addView(parseButton(jsonReader), this.mDefaultFlowLayoutChildLayoutParams);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return c0799b;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public TextView parseTextView(JsonReader jsonReader) throws IllegalStateException, IOException {
        TextView textView = new TextView(this.mContext);
        textView.setGravity(17);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (this.mContext.getString(C0852R.string.ui_name).equalsIgnoreCase(strNextName)) {
                textView.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_textview_textcontent).equalsIgnoreCase(strNextName)) {
                textView.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min)) {
                    textView.setTextSize(iNextInt);
                } else if (iNextInt != this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_default)) {
                    textView.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_height)) {
                    textView.setHeight(iNextInt2);
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_width)) {
                    textView.setWidth(iNextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return textView;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public Button parseButton(JsonReader jsonReader) throws IllegalStateException, IOException {
        Button button = new Button(this.mContext);
        button.setGravity(17);
        button.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        button.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (this.mContext.getString(C0852R.string.ui_name).equalsIgnoreCase(strNextName)) {
                button.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_textview_textcontent).equalsIgnoreCase(strNextName)) {
                button.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_onclick).equalsIgnoreCase(strNextName)) {
                button.setTag(C0852R.id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min)) {
                    button.setTextSize(iNextInt);
                } else if (iNextInt != this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_default)) {
                    button.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_height)) {
                    button.setHeight(iNextInt2);
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_width)) {
                    button.setWidth(iNextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return button;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public EditText parseEditText(JsonReader jsonReader) throws IllegalStateException, IOException {
        boolean zNextBoolean;
        EditText editText = new EditText(this.mContext);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        editText.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_normal));
        editText.setSingleLine(true);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (this.mContext.getString(C0852R.string.ui_name).equalsIgnoreCase(strNextName)) {
                editText.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_edittext_hintcontent).equalsIgnoreCase(strNextName)) {
                editText.setHint(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min)) {
                    editText.setTextSize(iNextInt);
                } else if (iNextInt != this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_default)) {
                    editText.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_height)) {
                    editText.setHeight(iNextInt2);
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_width)) {
                    editText.setWidth(iNextInt3);
                }
            } else if (this.mContext.getString(C0852R.string.ui_edittext_maxlength).equalsIgnoreCase(strNextName)) {
                int iNextInt4 = jsonReader.nextInt();
                if (iNextInt4 > 0) {
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(iNextInt4)});
                }
            } else if (this.mContext.getString(C0852R.string.ui_edittext_inputnumber).equalsIgnoreCase(strNextName)) {
                try {
                    zNextBoolean = jsonReader.nextBoolean();
                } catch (Exception unused) {
                    zNextBoolean = false;
                }
                if (zNextBoolean) {
                    editText.setInputType(2);
                }
            } else if (this.mContext.getString(C0852R.string.ui_edittext_defaultcontent).equalsIgnoreCase(strNextName)) {
                editText.setText(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        editText.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.uip.DefaultUipJsonParser.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.cyjh.mobileanjian.ipc.uip.DefaultUipJsonParser.2
            @Override // android.view.ActionMode.Callback
            public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
        return editText;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public CheckBox parseCheckBox(JsonReader jsonReader) throws IllegalStateException, IOException {
        CheckBox checkBox = new CheckBox(this.mContext);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        checkBox.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (this.mContext.getString(C0852R.string.ui_name).equalsIgnoreCase(strNextName)) {
                checkBox.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_onclick).equalsIgnoreCase(strNextName)) {
                checkBox.setTag(C0852R.id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_checkbox_hintcontent).equalsIgnoreCase(strNextName)) {
                checkBox.setText(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_checkbox_checked).equalsIgnoreCase(strNextName)) {
                if (jsonReader.nextBoolean()) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            } else if (this.mContext.getString(C0852R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min)) {
                    checkBox.setTextSize(iNextInt);
                } else if (iNextInt != this.mContext.getResources().getInteger(C0852R.integer.ui_textSize_default)) {
                    checkBox.setTextSize(this.mContext.getResources().getInteger(C0852R.integer.ui_textsize_min));
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_height)) {
                    checkBox.setHeight(iNextInt2);
                }
            } else if (this.mContext.getString(C0852R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.mContext.getResources().getInteger(C0852R.integer.ui_default_width)) {
                    checkBox.setWidth(iNextInt3);
                }
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return checkBox;
    }

    @Override // com.cyjh.mobileanjian.ipc.uip.IUipJsonParser
    public Spinner parseSpinner(JsonReader jsonReader) throws IllegalStateException, IOException {
        Spinner spinner = new Spinner(this.mContext);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        spinner.setBackgroundResource(C0852R.drawable.spinner_bg);
        spinner.setPadding(0, 0, 5, 0);
        spinner.setGravity(3);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            ArrayList arrayList = new ArrayList();
            if (this.mContext.getString(C0852R.string.ui_name).equalsIgnoreCase(strNextName)) {
                spinner.setTag(jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_onSelect).equalsIgnoreCase(strNextName)) {
                spinner.setTag(C0852R.id.uip_function_key, jsonReader.nextString());
            } else if (this.mContext.getString(C0852R.string.ui_spinner_items).equalsIgnoreCase(strNextName)) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                C0802e c0802e = new C0802e(this.mContext, arrayList);
                c0802e.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter((SpinnerAdapter) c0802e);
            } else if (this.mContext.getString(C0852R.string.ui_spinner_defaultitem).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt >= spinner.getAdapter().getCount()) {
                    iNextInt = 0;
                }
                spinner.setSelection(iNextInt);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return spinner;
    }
}
