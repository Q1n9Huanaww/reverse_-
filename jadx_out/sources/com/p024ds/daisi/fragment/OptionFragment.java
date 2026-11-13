package com.p024ds.daisi.fragment;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.uip.UisScriptRunner;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mqm.MiscUtilities;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.rom.Rom;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.view.SubmitProcessButton;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.dialog.DownloadDialogFragment;
import com.p024ds.daisi.dialog.MIUIGuideDialog;
import com.p024ds.daisi.dialog.NoRootDialog;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.mvp.presenters.VerifyPermissionPresenter;
import com.p024ds.daisi.mvp.views.VerifyPermissionView;
import com.p024ds.daisi.net.xutils.XUtilsHttpClient;
import com.p024ds.daisi.pay.PayConstants;
import com.p024ds.daisi.rom.FloatWindowManager;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.ExpireTimeUtil;
import com.p024ds.daisi.util.FileUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.LogUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class OptionFragment extends BaseFragement implements View.OnClickListener, VerifyPermissionView, DownloadDialogFragment.OnDownloadClickListener, PayConstants {
    public static final int ACCOUNT_FREEZE = 0;
    public static final int ACCOUNT_NORMAL = 1;
    public static final int ACCOUNT_SYSTEM_EXCEPTION = -1;
    public static final String ACTION_DUETIME = "action_com.cyjh.elfin.fragment.OptionFragment_duetime";
    public static final String ACTION_IS_LOGIN = "com.cyjh.elfin.fragment.OptionFragment_is_login";
    private static final String HONEYCOMB_PACAKGE = "com.cyjh.gundam";
    public static final String INTENT_EXTRA_DUETIME = "com.cyjh.elfin.fragment.OptionFragment_duetime";
    private static final int NID = 20150617;
    public static final int RESULT_DEFAULT = 0;
    public static final int RESULT_ENTRY_REFRESH = 3;
    public static final int RESULT_FLOAT_WINDOW = 5;
    public static final int RESULT_MORE_TIME = 4;
    private static final String URL_HONEYCOMB = "http://m.anjian.com/help/jiaoben/youxifengwo-xiaojl.apk";
    private Button btnGetMoreTime;
    private Button btnRun;
    private DownloadDialogFragment downloadDialog;
    private HttpHandler<File> downloadHandler;
    private DueTimeReceiver dueTimeReceiver;
    private ElfinPayActivity elfinPayActivity;
    private boolean isLogin;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;
    private String regCode;
    private ScrollView scrollViewUI;
    private TextView tvDueTime;
    private int typeVerifyFreeze;
    private LinearLayout uipLinearLayoutUI;
    private VerifyPermissionPresenter verifyPermissionPresenter;
    private View viewParent;
    private ArrayList<CheckBox> CheckBoxList = new ArrayList<>();
    private ArrayList<Spinner> SpinnerList = new ArrayList<>();
    private ArrayList<EditText> EditTextList = new ArrayList<>();
    private final int MESSAGE_WHAT_LOADSCRIPT = 100;
    private final int MESSAGE_WHAT_ACCOUNT_FREEZR = 101;
    public boolean isUIVlide = false;
    private long uipFileLength = -1;
    private Handler handler = new Handler() { // from class: com.ds.daisi.fragment.OptionFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 100) {
                return;
            }
            if (OptionFragment.this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_GUIDEACTIVITY, true) && System.getProperty("http.agent").contains(Rom.ROM_MIUI)) {
                new MIUIGuideDialog(OptionFragment.this.elfinPayActivity).show();
                return;
            }
            if (!FloatWindowManager.getInstance().checkPermission(AppContext.getInstance())) {
                FloatWindowManager.getInstance().applyPermission(OptionFragment.this.getActivity());
                return;
            }
            Logger.m3442e("试用状态的" + OptionFragment.this.appContext.payCheckType, new Object[0]);
            if (OptionFragment.this.appContext.payCheckType == 1) {
                OptionFragment.this.verifyPermissionPresenter.getScriptTryCount(OptionFragment.this.getActivity());
                return;
            }
            OptionFragment.this.appContext.mSharedUsed.edit().putString(Constants.SCRIPT_USER_TYPE, String.valueOf(0)).commit();
            OptionFragment.this.appContext.mSharedUsed.edit().putString(Constants.SCRIPT_TRY_COUNT, String.valueOf(-1)).commit();
            OptionFragment.this.onStartFloatWindow();
        }
    };

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_option;
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        setUserVisibleHint(true);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.elfinPayActivity = (ElfinPayActivity) getActivity();
        this.appContext = (AppContext) this.elfinPayActivity.getApplicationContext();
        this.verifyPermissionPresenter = new VerifyPermissionPresenter(this);
        EventBus.getDefault().register(this);
        this.viewParent = getView();
        initView();
        copyScriptFiles();
        initNotification();
        this.regCode = AppUtils.getFileContent(InterfaceRelatedConstants.REG_CODE_FILE_SAVE, getActivity());
        Log.e("zzz", "OptionFragment--onActivityCreated:regCode:" + this.regCode);
        this.dueTimeReceiver = new DueTimeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_DUETIME);
        intentFilter.addAction(ACTION_IS_LOGIN);
        LocalBroadcastManager.getInstance(this.elfinPayActivity).registerReceiver(this.dueTimeReceiver, intentFilter);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        isUserLogin();
        if (this.isLogin) {
            this.typeVerifyFreeze = 3;
            this.verifyPermissionPresenter.checkedUserFreeze(getActivity());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isUserLogin() {
        this.isLogin = getActivity().getSharedPreferences(Constants.USER_MESSAGE, 0).getBoolean(Constants.IS_LOGIN, false);
        Logger.m3442e("是否登录:" + this.isLogin, new Object[0]);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        Logger.m3442e("onStop是否执行了", new Object[0]);
        new SaveSettingValueAsyncTask().execute(new Void[0]);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageThreadMain(MsgItem msgItem) {
        if (msgItem.getMsgType() != 1004) {
            return;
        }
        initQUI();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
        EventBus.getDefault().unregister(this);
        LocalBroadcastManager.getInstance(this.elfinPayActivity).unregisterReceiver(this.dueTimeReceiver);
    }

    public void copyScriptFiles() {
        InputStream inputStreamOpen;
        InputStream inputStreamOpen2;
        InputStream inputStreamOpen3;
        InputStream inputStreamOpen4;
        InputStream inputStream;
        AssetManager assets = this.appContext.getAssets();
        File lcFile = this.appContext.mScript.getLcFile();
        File propFile = this.appContext.mScript.getPropFile();
        File atcFile = this.appContext.mScript.getAtcFile();
        File uiFile = this.appContext.mScript.getUiFile();
        File uipFile = this.appContext.mScript.getUipFile();
        File rtdFile = this.appContext.mScript.getRtdFile();
        try {
            inputStreamOpen4 = assets.open(APPConstant.SCRIPT_FILENAME_LC);
            try {
                inputStreamOpen3 = assets.open(APPConstant.SCRIPT_FILENAME_PROP);
                try {
                    inputStreamOpen = assets.open(APPConstant.SCRIPT_FILENAME_ATC);
                    try {
                        inputStreamOpen2 = assets.open(APPConstant.SCRIPT_FILENAME_UI);
                        try {
                            InputStream inputStreamOpen5 = assets.open(APPConstant.SCRIPT_FILENAME_UIP);
                            try {
                                inputStream = inputStreamOpen5;
                                try {
                                    new copyScriptFilesTask(lcFile, propFile, atcFile, uiFile, rtdFile, uipFile).execute(inputStreamOpen4, inputStreamOpen3, inputStreamOpen, inputStreamOpen2, assets.open(APPConstant.SCRIPT_FILENAME_RTD), inputStream);
                                } catch (IOException unused) {
                                    new copyScriptFilesTask(lcFile, propFile, atcFile, uiFile, uipFile).execute(inputStreamOpen4, inputStreamOpen3, inputStreamOpen, inputStreamOpen2, inputStream);
                                }
                            } catch (IOException unused2) {
                                inputStream = inputStreamOpen5;
                            }
                        } catch (IOException unused3) {
                            inputStream = null;
                            new copyScriptFilesTask(lcFile, propFile, atcFile, uiFile, uipFile).execute(inputStreamOpen4, inputStreamOpen3, inputStreamOpen, inputStreamOpen2, inputStream);
                        }
                    } catch (IOException unused4) {
                        inputStreamOpen2 = null;
                        inputStream = null;
                        new copyScriptFilesTask(lcFile, propFile, atcFile, uiFile, uipFile).execute(inputStreamOpen4, inputStreamOpen3, inputStreamOpen, inputStreamOpen2, inputStream);
                    }
                } catch (IOException unused5) {
                    inputStreamOpen = null;
                }
            } catch (IOException unused6) {
                inputStreamOpen = null;
                inputStreamOpen2 = null;
                inputStreamOpen3 = null;
            }
        } catch (IOException unused7) {
            inputStreamOpen = null;
            inputStreamOpen2 = null;
            inputStreamOpen3 = null;
            inputStreamOpen4 = null;
        }
    }

    @Override // com.p024ds.daisi.mvp.views.VerifyPermissionView
    public void accountCheckedResult(AccountResult accountResult) {
        LogUtils.logError("OptionFragment accountCheckedResult");
        if (TextUtils.isEmpty(accountResult.Msg)) {
            return;
        }
        switch (Integer.parseInt(accountResult.Code)) {
            case -1:
                ToastUtils.showToastLong(getActivity(), accountResult.Msg);
                break;
            case 0:
                ToastUtils.showToastLong(getActivity(), accountResult.Msg);
                this.tvDueTime.setVisibility(8);
                this.appContext.dueTime = -100;
                break;
            case 1:
                switch (this.typeVerifyFreeze) {
                    case 3:
                        LogUtils.logError("OptionFragment RESULT_ENTRY_REFRESH");
                        break;
                    case 5:
                        this.handler.sendEmptyMessage(100);
                        break;
                }
                this.typeVerifyFreeze = 0;
                break;
        }
    }

    @Override // com.p024ds.daisi.mvp.views.VerifyPermissionView
    public void getTryCountResult(AccountResult accountResult) {
        if (!TextUtils.isEmpty(accountResult.Code)) {
            this.appContext.mSharedUsed.edit().putString(Constants.SCRIPT_USER_TYPE, accountResult.Code).commit();
            this.appContext.mSharedUsed.edit().putString(Constants.SCRIPT_TRY_COUNT, accountResult.TryCount).commit();
        }
        onStartFloatWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartFloatWindow() {
        if (this.appContext.STATE_SCRIPT == 0) {
            this.appContext.STATE_SCRIPT = 1;
        }
        this.elfinPayActivity.moveTaskToBack(true);
        this.appContext.showFloatView();
        this.appContext.mElfinFloatView.mContext = getActivity();
    }

    private class copyScriptFilesTask extends AsyncTask<InputStream, Void, Boolean> {
        private File[] destFiles;

        public copyScriptFilesTask(File... fileArr) {
            this.destFiles = fileArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(InputStream... inputStreamArr) {
            for (int i = 0; i < inputStreamArr.length; i++) {
                FileUtils.copyToFile(inputStreamArr[i], this.destFiles[i]);
            }
            if (OptionFragment.this.appContext.mScript.getUipFile().exists() && OptionFragment.this.appContext.mScript.getUipFile().length() > 0) {
                UisScriptRunner.getInstance().startLoop(this.destFiles[0].getAbsolutePath());
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            OptionFragment.this.initQUI();
        }
    }

    private void initView() {
        this.scrollViewUI = (ScrollView) this.viewParent.findViewById(R.id.scrollview_ui);
        this.tvDueTime = (TextView) this.viewParent.findViewById(R.id.tv_option_duetime);
        this.btnGetMoreTime = (Button) this.viewParent.findViewById(R.id.btn_option_getmoretime);
        this.btnRun = (Button) this.viewParent.findViewById(R.id.btn_option_start);
        this.btnRun.setOnClickListener(this);
        this.btnGetMoreTime.setOnClickListener(this);
        this.uipLinearLayoutUI = (LinearLayout) this.viewParent.findViewById(R.id.ll_option_ui_parent);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.uipLinearLayoutUI.getLayoutParams();
        layoutParams.width = (int) (AppDeviceUtils.getResolution(getActivity()).x * 0.9f);
        this.uipLinearLayoutUI.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initQUI() {
        MiscUtilities miscUtilities = new MiscUtilities();
        this.uipFileLength = this.appContext.mScript.getUipFile().length();
        if (this.appContext.mScript.getUipFile().exists() && this.uipFileLength > 0) {
            String strLoadUIFile = miscUtilities.LoadUIFile(this.appContext.mScript.getUipFile().getAbsolutePath(), true);
            this.commonLog.m1348e("转换后>>>" + strLoadUIFile);
            LinearLayout layoutFromJson = this.appContext.mUipHelper.parseLayoutFromJson(strLoadUIFile, null);
            this.uipLinearLayoutUI.removeAllViews();
            this.uipLinearLayoutUI.addView(layoutFromJson);
        } else {
            String strLoadUIFile2 = miscUtilities.LoadUIFile(this.appContext.mScript.getUiFile().getAbsolutePath(), true);
            this.commonLog.m1348e("转换后>>>" + strLoadUIFile2);
            LinearLayout linearLayoutParsUIFile = ParsUIFile(strLoadUIFile2);
            if (linearLayoutParsUIFile.getChildCount() > 0) {
                this.scrollViewUI.removeViewAt(0);
                this.scrollViewUI.addView(linearLayoutParsUIFile);
            }
        }
        try {
            parseOptionJson();
            this.isUIVlide = true;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private LinearLayout ParsUIFile(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        LinearLayout linearLayout = new LinearLayout(this.elfinPayActivity);
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().startsWith(getString(R.string.ui_activity))) {
                    linearLayout = ParMainLayout(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (Exception unused) {
            this.commonLog.m1348e("界面标签解析出错了！！");
        }
        return linearLayout;
    }

    private LinearLayout ParMainLayout(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(this.elfinPayActivity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getString(R.string.ui_linearlayout).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsHorizontalLayout(jsonReader));
            } else if (getString(R.string.ui_textview).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsTextView(jsonReader));
            } else if (getString(R.string.ui_edittext).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsEditText(jsonReader));
            } else if (getString(R.string.ui_checkbox).equals(strNextName)) {
                linearLayout.addView(ParsCheckBox(jsonReader));
            } else if (getString(R.string.ui_spinner).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParSpinner(jsonReader));
            } else {
                jsonReader.skipValue();
                this.commonLog.m1348e("ParMainLayout没有此节点");
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    private LinearLayout ParsHorizontalLayout(JsonReader jsonReader) throws IOException {
        LinearLayout linearLayout = new LinearLayout(this.elfinPayActivity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getString(R.string.ui_linearlayout).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsHorizontalLayout(jsonReader));
            } else if (getString(R.string.ui_textview).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsTextView(jsonReader));
            } else if (getString(R.string.ui_edittext).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParsEditText(jsonReader));
            } else if (getString(R.string.ui_checkbox).equals(strNextName)) {
                linearLayout.addView(ParsCheckBox(jsonReader));
            } else if (getString(R.string.ui_spinner).equalsIgnoreCase(strNextName)) {
                linearLayout.addView(ParSpinner(jsonReader));
            } else {
                this.commonLog.m1348e("ParsHorizontalLayout没有此节点");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return linearLayout;
    }

    private TextView ParsTextView(JsonReader jsonReader) throws IOException {
        TextView textView = new TextView(this.elfinPayActivity);
        textView.setGravity(17);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(this.appContext.getInteger(R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                textView.setTag(jsonReader.nextString());
            } else if (getString(R.string.ui_textview_textcontent).equalsIgnoreCase(strNextName)) {
                textView.setText(jsonReader.nextString());
            } else if (getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.appContext.getInteger(R.integer.ui_textsize_min)) {
                    textView.setTextSize(iNextInt);
                } else if (iNextInt != this.appContext.getInteger(R.integer.ui_textSize_default)) {
                    textView.setTextSize(this.appContext.getInteger(R.integer.ui_textsize_min));
                }
            } else if (getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.appContext.getInteger(R.integer.ui_default_height)) {
                    textView.setHeight(iNextInt2);
                }
            } else if (getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.appContext.getInteger(R.integer.ui_default_width)) {
                    textView.setWidth(iNextInt3);
                }
            } else {
                this.commonLog.m1348e("ParsTextView没有此节点");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return textView;
    }

    private EditText ParsEditText(JsonReader jsonReader) throws IOException {
        boolean zNextBoolean;
        EditText editText = new EditText(this.elfinPayActivity);
        editText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        editText.setTextSize(this.appContext.getInteger(R.integer.ui_textSize_normal));
        editText.setSingleLine(true);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                editText.setTag(jsonReader.nextString());
            } else if (getString(R.string.ui_edittext_hintcontent).equalsIgnoreCase(strNextName)) {
                editText.setHint(jsonReader.nextString());
            } else if (getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.appContext.getInteger(R.integer.ui_textsize_min)) {
                    editText.setTextSize(iNextInt);
                } else if (iNextInt != this.appContext.getInteger(R.integer.ui_textSize_default)) {
                    editText.setTextSize(this.appContext.getInteger(R.integer.ui_textsize_min));
                }
            } else if (getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.appContext.getInteger(R.integer.ui_default_height)) {
                    editText.setHeight(iNextInt2);
                }
            } else if (getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.appContext.getInteger(R.integer.ui_default_width)) {
                    editText.setWidth(iNextInt3);
                }
            } else if (getString(R.string.ui_edittext_maxlength).equalsIgnoreCase(strNextName)) {
                int iNextInt4 = jsonReader.nextInt();
                if (iNextInt4 > 0) {
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(iNextInt4)});
                }
            } else if (getString(R.string.ui_edittext_inputnumber).equalsIgnoreCase(strNextName)) {
                try {
                    zNextBoolean = jsonReader.nextBoolean();
                } catch (Exception unused) {
                    this.commonLog.m1348e("EditText解析异常了");
                    zNextBoolean = false;
                }
                if (zNextBoolean) {
                    editText.setInputType(2);
                }
            } else if (getString(R.string.ui_edittext_defaultcontent).equalsIgnoreCase(strNextName)) {
                editText.setText(jsonReader.nextString());
            } else {
                this.commonLog.m1348e("ParsEditText没有此节点");
                jsonReader.skipValue();
            }
        }
        this.EditTextList.add(editText);
        jsonReader.endObject();
        return editText;
    }

    private CheckBox ParsCheckBox(JsonReader jsonReader) throws IOException {
        CheckBox checkBox = new CheckBox(this.elfinPayActivity);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        checkBox.setTextSize(this.appContext.getInteger(R.integer.ui_textSize_normal));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                String strNextString = jsonReader.nextString();
                this.commonLog.m1348e("名称>>>>" + strNextString);
                checkBox.setTag(strNextString);
            } else if (getString(R.string.ui_checkbox_hintcontent).equalsIgnoreCase(strNextName)) {
                checkBox.setText(jsonReader.nextString());
            } else if (getString(R.string.ui_checkbox_checked).equalsIgnoreCase(strNextName)) {
                if (jsonReader.nextBoolean()) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            } else if (getString(R.string.ui_textsize).equalsIgnoreCase(strNextName)) {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt > this.appContext.getInteger(R.integer.ui_textsize_min)) {
                    checkBox.setTextSize(iNextInt);
                } else if (iNextInt != this.appContext.getInteger(R.integer.ui_textSize_default)) {
                    checkBox.setTextSize(this.appContext.getInteger(R.integer.ui_textsize_min));
                }
            } else if (getString(R.string.ui_layout_height).equalsIgnoreCase(strNextName)) {
                int iNextInt2 = jsonReader.nextInt();
                if (iNextInt2 > this.appContext.getInteger(R.integer.ui_default_height)) {
                    checkBox.setHeight(iNextInt2);
                }
            } else if (getString(R.string.ui_layout_width).equalsIgnoreCase(strNextName)) {
                int iNextInt3 = jsonReader.nextInt();
                if (iNextInt3 > this.appContext.getInteger(R.integer.ui_default_width)) {
                    checkBox.setWidth(iNextInt3);
                }
            } else {
                this.commonLog.m1348e("ParsCheckBox没有此节点");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        this.CheckBoxList.add(checkBox);
        return checkBox;
    }

    private Spinner ParSpinner(JsonReader jsonReader) throws IOException {
        Spinner spinner = new Spinner(this.elfinPayActivity);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        spinner.setBackgroundResource(R.drawable.bg_spinner);
        spinner.setPadding(0, 0, 5, 0);
        spinner.setGravity(3);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            ArrayList arrayList = new ArrayList();
            if (getString(R.string.ui_name).equalsIgnoreCase(strNextName)) {
                spinner.setTag(jsonReader.nextString());
            } else if (getString(R.string.ui_spinner_items).equalsIgnoreCase(strNextName)) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                ArrayAdapter arrayAdapter = new ArrayAdapter(this.elfinPayActivity, android.R.layout.simple_spinner_item, arrayList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
            } else if (getString(R.string.ui_spinner_defaultitem).equalsIgnoreCase(strNextName)) {
                try {
                    spinner.setSelection(jsonReader.nextInt(), true);
                } catch (Exception unused) {
                    spinner.setSelection(0);
                }
            } else {
                this.commonLog.m1348e("ParSpinner没有此节点");
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        this.SpinnerList.add(spinner);
        return spinner;
    }

    private void initNotification() {
        this.mNotifyManager = (NotificationManager) getActivity().getSystemService("notification");
        this.mBuilder = new NotificationCompat.Builder(getActivity());
        this.mBuilder.setContentTitle("下载").setContentText("游戏蜂窝下载").setSmallIcon(R.drawable.ic_launcher);
    }

    @Override // com.ds.daisi.dialog.DownloadDialogFragment.OnDownloadClickListener
    public void downloadOnClick(final SubmitProcessButton submitProcessButton) {
        if (AppDeviceUtils.isAInstallPackage(getActivity(), HONEYCOMB_PACAKGE)) {
            AppDeviceUtils.openApp(getActivity(), HONEYCOMB_PACAKGE);
            return;
        }
        final File file = new File(Environment.getExternalStorageDirectory(), "youxifengwo.apk");
        if (this.downloadHandler != null) {
            this.commonLog.m1348e("downloadHandler.getState()>>" + this.downloadHandler.getState());
            switch (this.downloadHandler.getState()) {
                case WAITING:
                case STARTED:
                    this.downloadHandler.cancel();
                    this.downloadHandler.cancel();
                    break;
                case LOADING:
                    return;
                case SUCCESS:
                    AppDeviceUtils.installAPK(getActivity(), file);
                    this.downloadDialog.dismiss();
                    return;
            }
        }
        this.downloadHandler = XUtilsHttpClient.getInstence(getActivity()).download(URL_HONEYCOMB, file.getAbsolutePath(), new RequestCallBack<File>() { // from class: com.ds.daisi.fragment.OptionFragment.1
            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onFailure(HttpException httpException, String str) {
            }

            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Log.i("NLog", "onSuccess");
                OptionFragment.this.mBuilder.setProgress(100, 100, false);
                OptionFragment.this.mNotifyManager.notify(OptionFragment.NID, OptionFragment.this.mBuilder.build());
                AppDeviceUtils.installAPK(OptionFragment.this.getActivity(), file);
                OptionFragment.this.downloadDialog.dismiss();
            }

            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onStart() {
                super.onStart();
                OptionFragment.this.commonLog.m1348e("开始下载>>>");
                OptionFragment.this.mBuilder.setProgress(100, 0, false);
                OptionFragment.this.mNotifyManager.notify(OptionFragment.NID, OptionFragment.this.mBuilder.build());
            }

            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onLoading(long j, long j2, boolean z) {
                super.onLoading(j, j2, z);
                int i = (int) ((j2 * 100) / j);
                submitProcessButton.setProgress(i);
                OptionFragment.this.mBuilder.setProgress(100, i, false);
                OptionFragment.this.mNotifyManager.notify(OptionFragment.NID, OptionFragment.this.mBuilder.build());
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_option_start /* 2131755402 */:
                if (!RootUtil.isRoot()) {
                    new NoRootDialog(getActivity()).show();
                } else {
                    this.typeVerifyFreeze = 5;
                    this.verifyPermissionPresenter.checkedUserFreeze(getActivity());
                }
                break;
            case R.id.btn_option_getmoretime /* 2131755403 */:
                IntentUtils.toCallRegisterCodeActivity(getActivity(), this.regCode);
                break;
        }
    }

    public void generateOptionJson() throws Exception {
        if (this.appContext.mScript.getUipFile().exists() && this.uipFileLength > 0) {
            this.appContext.mUipHelper.saveToConfigFile(this.appContext.mScript.getCfgFile().getAbsolutePath());
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (CheckBox checkBox : this.CheckBoxList) {
            jSONObject.put(checkBox.getTag().toString(), checkBox.isChecked());
        }
        for (Spinner spinner : this.SpinnerList) {
            jSONObject.put(spinner.getTag().toString(), spinner.getSelectedItemPosition());
        }
        for (EditText editText : this.EditTextList) {
            jSONObject.put(editText.getTag().toString(), editText.getText().toString());
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.appContext.mScript.getCfgFile().getAbsolutePath()), "UTF-8"));
        bufferedWriter.write(jSONObject.toString());
        bufferedWriter.close();
    }

    private void parseOptionJson() throws JSONException {
        File cfgFile = this.appContext.mScript.getCfgFile();
        if (cfgFile.exists()) {
            String textFile = FileUtils.readTextFile(cfgFile);
            this.commonLog.m1348e("json>>>" + textFile);
            if (this.appContext.mScript.getUipFile().exists() && this.uipFileLength > 0) {
                this.appContext.mUipHelper.configViewFromJson(textFile);
                return;
            }
            JSONObject jSONObject = new JSONObject(textFile);
            for (Spinner spinner : this.SpinnerList) {
                String string = spinner.getTag().toString();
                this.commonLog.m1348e("tag>>>" + string);
                spinner.setSelection(jSONObject.getInt(string));
            }
            for (CheckBox checkBox : this.CheckBoxList) {
                checkBox.setChecked(jSONObject.getBoolean(checkBox.getTag().toString()));
            }
            for (EditText editText : this.EditTextList) {
                editText.setText(jSONObject.getString(editText.getTag().toString()));
            }
        }
    }

    private class DueTimeReceiver extends BroadcastReceiver {
        private DueTimeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            OptionFragment.this.commonLog.m1348e("进入DueTimeReceiver>>>");
            if (OptionFragment.ACTION_DUETIME.equals(intent.getAction())) {
                switch (OptionFragment.this.appContext.STATE_APP) {
                    case 0:
                        OptionFragment.this.commonLog.m1348e("PAY_CHECKAPP_PAID>>");
                        OptionFragment.this.tvDueTime.setVisibility(0);
                        int i = Integer.parseInt(intent.getStringExtra(OptionFragment.INTENT_EXTRA_DUETIME));
                        OptionFragment.this.appContext.dueTime = i * 60;
                        OptionFragment.this.tvDueTime.setText(OptionFragment.this.getTime(i));
                        OptionFragment.this.appContext.payCheckType = 0;
                        break;
                    case 1:
                        OptionFragment.this.commonLog.m1348e("PAY_CHECKAPP_TRIAL>>");
                        OptionFragment.this.tvDueTime.setVisibility(0);
                        int i2 = Integer.parseInt(intent.getStringExtra(OptionFragment.INTENT_EXTRA_DUETIME));
                        OptionFragment.this.appContext.dueTime = i2 * 60;
                        if (OptionFragment.this.appContext.dueTime < 0) {
                            OptionFragment.this.tvDueTime.setText(R.string.optionfragment_duetime_no);
                        } else {
                            OptionFragment.this.tvDueTime.setText(String.format(OptionFragment.this.getString(R.string.optionfragment_unpaid), Integer.valueOf(i2)));
                        }
                        OptionFragment.this.appContext.payCheckType = 1;
                        break;
                    case 2:
                        OptionFragment.this.commonLog.m1348e("PAY_CHECKAPP_FREE>>");
                        OptionFragment.this.tvDueTime.setVisibility(0);
                        OptionFragment.this.appContext.dueTime = 0;
                        OptionFragment.this.tvDueTime.setText(R.string.optionfragment_free);
                        OptionFragment.this.appContext.payCheckType = 2;
                        break;
                }
            }
            if (OptionFragment.ACTION_IS_LOGIN.equals(intent.getAction())) {
                OptionFragment.this.tvDueTime.setVisibility(8);
                OptionFragment.this.isUserLogin();
            }
        }
    }

    public String getTime(int i) {
        this.commonLog.m1348e("getTime>>>的时间" + i);
        if (!isAdded()) {
            return null;
        }
        if (i >= 1440) {
            this.commonLog.m1348e("dueTime >= 1440");
            return String.format(getString(R.string.optionfragment_duetime_day), Integer.valueOf(i / ExpireTimeUtil.MINUTE_OF_DAY), Integer.valueOf((i % ExpireTimeUtil.MINUTE_OF_DAY) / 60), Integer.valueOf(i % 60));
        }
        if (i > 60 && i < 1440) {
            this.commonLog.m1348e("dueTime > 60 && dueTime < 1440");
            return String.format(getString(R.string.optionfragment_duetime_hour), Integer.valueOf(i / 60), Integer.valueOf(i % 60));
        }
        if (i <= 60 && i > 0) {
            this.commonLog.m1348e("dueTime <= 60 && dueTime > 0");
            return String.format(getString(R.string.optionfragment_duetime_minute), Integer.valueOf(i));
        }
        if (i >= 0) {
            return null;
        }
        this.commonLog.m1348e("dueTime < 0");
        return getString(R.string.optionfragment_duetime_no);
    }

    class SaveSettingValueAsyncTask extends AsyncTask<Void, Void, Void> {
        SaveSettingValueAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            try {
                OptionFragment.this.generateOptionJson();
                return null;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }
    }
}
