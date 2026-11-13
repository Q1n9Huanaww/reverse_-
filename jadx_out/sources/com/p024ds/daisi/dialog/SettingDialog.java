package com.p024ds.daisi.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import com.cyjh.p019mq.sdk.MqBridge;
import com.cyjh.share.bean.request.UpdateRequestInfo;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.dialog.LibraryUILoadingDialog;
import com.cyjh.share.helper.RecognitionLibraryDownloadHelper;
import com.cyjh.share.mvp.manager.VersionUpdateManager;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.FastClickUtil;
import com.cyjh.share.util.SlLog;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ScriptLogDetailActivity;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ScriptLog;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.services.BootService;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.services.TimerService;
import com.p024ds.daisi.services.VerifyService;
import com.p024ds.daisi.util.FileComparator;
import com.p024ds.daisi.util.FileUtils;
import com.p024ds.daisi.util.JsonUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.warnship.autoplay.R;
import java.io.File;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class SettingDialog extends BaseDialog implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static final String TAG = "SettingDialog";
    private boolean isDismiss;
    private Button mButtonExitApp;
    private Button mButtonScriptLog;
    private Button mButtonUpdate;
    public Context mContext;
    private ImageView mImageViewBack;
    private Switch mSwitcOpenRunScript;
    private Switch mSwitchWordRecognitionLibrary;
    private Switch mSwtichCallStop;
    private Switch mSwtichFloatviewCtrl;
    private Switch mSwtichVibration;
    private Switch mSwtichVolumeKeyCtrl;
    private Switch mSwtichWaggleStop;

    public SettingDialog(Context context) {
        super(context, R.style.Theme_Dialog);
        this.mAppContext = (AppContext) context.getApplicationContext();
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
        setContentView(R.layout.setting_dialog);
        setBlurEffect(0.1f);
        setDialogSize(0.9f, 0.6f);
        initView();
    }

    private void initView() {
        this.mSwtichCallStop = (Switch) findViewById(R.id.setting_swtich_call_stop);
        this.mSwtichVolumeKeyCtrl = (Switch) findViewById(R.id.setting_swtich_volume_key_ctrl);
        this.mSwtichWaggleStop = (Switch) findViewById(R.id.setting_swtich_waggle_stop);
        this.mSwtichFloatviewCtrl = (Switch) findViewById(R.id.setting_swtich_floatview_ctrl);
        this.mSwtichVibration = (Switch) findViewById(R.id.setting_swtich_vibration);
        this.mSwitcOpenRunScript = (Switch) findViewById(R.id.setting_swtich_open_run);
        this.mSwitchWordRecognitionLibrary = (Switch) findViewById(R.id.switch_setting_word_recognition_library);
        this.mButtonScriptLog = (Button) findViewById(R.id.setting_button_scriptlog);
        this.mButtonUpdate = (Button) findViewById(R.id.setting_button_update);
        this.mButtonExitApp = (Button) findViewById(R.id.setting_button_exitapp);
        this.mImageViewBack = (ImageView) findViewById(R.id.setting_dialog_imageview_back);
        this.mSwtichCallStop.setOnCheckedChangeListener(this);
        this.mSwtichVolumeKeyCtrl.setOnCheckedChangeListener(this);
        this.mSwtichWaggleStop.setOnCheckedChangeListener(this);
        this.mSwtichFloatviewCtrl.setOnCheckedChangeListener(this);
        this.mSwtichVibration.setOnCheckedChangeListener(this);
        this.mSwitcOpenRunScript.setOnCheckedChangeListener(this);
        this.mButtonScriptLog.setOnClickListener(this);
        this.mButtonExitApp.setOnClickListener(this);
        this.mButtonUpdate.setOnClickListener(this);
        this.mImageViewBack.setOnClickListener(this);
        judgeRecognitionLibraryAvailable();
        initSwitchStatus();
    }

    private void initSwitchStatus() {
        this.mSwtichCallStop.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, true));
        this.mSwtichVolumeKeyCtrl.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VOLUMEKEYCTRL, true));
        this.mSwtichWaggleStop.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_WAGGLESTOP, true));
        this.mSwtichFloatviewCtrl.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_FLOATVIEWCTRL, true));
        this.mSwtichVibration.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VIBRATION, true));
        this.mSwitcOpenRunScript.setChecked(this.mAppContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, false));
    }

    private void judgeRecognitionLibraryAvailable() {
        if (RecognitionLibraryDownloadHelper.get().isRecognitionLibraryAvailable()) {
            this.mSwitchWordRecognitionLibrary.setChecked(true);
            this.mSwitchWordRecognitionLibrary.setClickable(false);
        } else {
            this.mSwitchWordRecognitionLibrary.setChecked(false);
            this.mSwitchWordRecognitionLibrary.setOnCheckedChangeListener(this);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SharedPreferences.Editor editorEdit = this.mAppContext.sharedPreferences.edit();
        int id = compoundButton.getId();
        if (id != R.id.switch_setting_word_recognition_library) {
            switch (id) {
                case R.id.setting_swtich_volume_key_ctrl /* 2131755483 */:
                    if (!z && !this.mSwtichFloatviewCtrl.isChecked()) {
                        this.mAppContext.showToast(R.string.setting_illegal);
                        this.mSwtichVolumeKeyCtrl.setChecked(true);
                    } else {
                        editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VOLUMEKEYCTRL, z);
                    }
                    break;
                case R.id.setting_swtich_floatview_ctrl /* 2131755484 */:
                    if (!z && !this.mSwtichVolumeKeyCtrl.isChecked()) {
                        this.mAppContext.showToast(R.string.setting_illegal);
                        this.mSwtichFloatviewCtrl.setChecked(true);
                    } else {
                        editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_FLOATVIEWCTRL, z);
                        this.mAppContext.mElfinFloatView.toggleElfinVisbility();
                    }
                    break;
                case R.id.setting_swtich_waggle_stop /* 2131755485 */:
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_WAGGLESTOP, z);
                    break;
                case R.id.setting_swtich_call_stop /* 2131755486 */:
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, z);
                    break;
                case R.id.setting_swtich_vibration /* 2131755487 */:
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VIBRATION, z);
                    break;
                case R.id.setting_swtich_open_run /* 2131755488 */:
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, z);
                    Logger.m3442e("Setting设置了开机启动脚本" + z, new Object[0]);
                    CommonUtil.operateElfinPackageNames(getContext(), z);
                    EventBus.getDefault().post(new MsgItem(1007, z ? "1" : AccountManager.TYPE_LOGIN));
                    SlLog.m1332d(TAG, "onCheckedChanged --> isChecked:" + z);
                    break;
            }
        } else if (z) {
            recognitionLibraryDownloadOperate();
        }
        editorEdit.commit();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_button_update /* 2131755348 */:
                updateVersionRequest();
                break;
            case R.id.setting_dialog_imageview_back /* 2131755482 */:
                this.isDismiss = false;
                dismiss();
                new ScriptUIDialog(getContext()).show();
                break;
            case R.id.setting_button_scriptlog /* 2131755489 */:
                File[] fileArrListFiles = new File(this.mAppContext.mPathPackagenameLog).listFiles(new FileUtils.FileSuffixrFilter(APPConstant.FILE_SUFFIX_LOG));
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    this.mAppContext.showToast(R.string.no_script_log);
                } else {
                    Arrays.sort(fileArrListFiles, new FileComparator());
                    File file = fileArrListFiles[0];
                    ScriptLog scriptLog = new ScriptLog();
                    scriptLog.setName(file.getName());
                    scriptLog.setSize(FileUtils.formetFileSize(file.length()));
                    scriptLog.setFile(file);
                    Intent intent = new Intent(getContext(), (Class<?>) ScriptLogDetailActivity.class);
                    intent.putExtra(APPConstant.INTENT_KEY_FLAG_LASTLOG, true);
                    intent.putExtra(APPConstant.INTENT_KEY_SCRIPTLOG, scriptLog);
                    intent.addFlags(268435456);
                    getContext().startActivity(intent);
                    this.isDismiss = true;
                    dismiss();
                }
                break;
            case R.id.setting_button_exitapp /* 2131755490 */:
                this.isDismiss = false;
                getContext().getSharedPreferences(APPConstant.SP_NAME_APPLICATION, 0).edit().putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, this.mSwitcOpenRunScript.isChecked()).apply();
                CommonUtil.operateElfinPackageNames(getContext(), this.mSwitcOpenRunScript.isChecked());
                SlLog.m1332d(TAG, "onClick --> exit isChecked:" + this.mSwitcOpenRunScript.isChecked());
                if (this.mContext != null) {
                    ((Activity) this.mContext).finish();
                }
                getContext().stopService(new Intent(getContext(), (Class<?>) VerifyService.class));
                getContext().stopService(new Intent(getContext(), (Class<?>) TimerService.class));
                getContext().stopService(new Intent(getContext(), (Class<?>) BootService.class));
                getContext().stopService(new Intent(getContext(), (Class<?>) PhoneStateService.class));
                MqBridge.exit();
                Process.killProcess(Process.myPid());
                break;
        }
    }

    private void updateVersionRequest() {
        if (FastClickUtil.getInstance().isFastClick()) {
            return;
        }
        if (!NetworkUtils.isAvailable(getContext())) {
            ToastUtils.showToastLong(getContext(), R.string.network_off);
            return;
        }
        this.isDismiss = false;
        String scriptInfoJson = JsonUtils.parseScriptInfoJson(getContext(), APPConstant.SCRIPT_INFO);
        String deviceName = CommonUtil.getDeviceName(getContext());
        UpdateRequestInfo updateRequestInfo = new UpdateRequestInfo();
        updateRequestInfo.ScriptId = AppContext.getInstance().mScript.getId();
        updateRequestInfo.DeviceName = deviceName;
        updateRequestInfo.ScriptVersion = Integer.parseInt(scriptInfoJson);
        SlLog.m1334i(TAG, "updateVersionRequest --> 1 ScriptId=" + updateRequestInfo.ScriptId + ",DeviceName=" + updateRequestInfo.DeviceName + ",ScriptVersion=" + updateRequestInfo.ScriptVersion);
        VersionUpdateManager.builder().setDismissBack(new VersionUpdateManager.OnUpdateVersionBack(this) { // from class: com.ds.daisi.dialog.SettingDialog$$Lambda$0
            private final SettingDialog arg$1;

            {
                this.arg$1 = this;
            }

            @Override // com.cyjh.share.mvp.manager.VersionUpdateManager.OnUpdateVersionBack
            public void onUpdateHas(VersionUpdateInfo versionUpdateInfo) {
                this.arg$1.lambda$updateVersionRequest$0$SettingDialog(versionUpdateInfo);
            }
        }).updateVersion(true, getContext(), updateRequestInfo);
    }

    final /* synthetic */ void lambda$updateVersionRequest$0$SettingDialog(VersionUpdateInfo versionUpdateInfo) {
        new UpdateDialog(getContext(), versionUpdateInfo, true).show();
    }

    public boolean isDismiss() {
        return this.isDismiss;
    }

    private void recognitionLibraryDownloadOperate() {
        final LibraryUILoadingDialog libraryUILoadingDialog = new LibraryUILoadingDialog(getContext());
        libraryUILoadingDialog.show();
        RecognitionLibraryDownloadHelper.get().download(InterfaceRelatedConstants.RECOGNITION_LIBRARY_URL, new RecognitionLibraryDownloadHelper.OnDownloadListener() { // from class: com.ds.daisi.dialog.SettingDialog.1
            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void alreadyExist() {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(SettingDialog.this.getContext(), "文字识别库已下载！");
            }

            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void onDownloadSuccess() {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(SettingDialog.this.getContext(), "文字识别库下载成功！");
                SettingDialog.this.mSwitchWordRecognitionLibrary.setClickable(false);
            }

            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void onDownloadFailed(String str) {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(SettingDialog.this.getContext(), "文字识别库下载失败！");
                SettingDialog.this.mSwitchWordRecognitionLibrary.setClickable(true);
                SettingDialog.this.mSwitchWordRecognitionLibrary.setChecked(false);
            }
        });
    }
}
