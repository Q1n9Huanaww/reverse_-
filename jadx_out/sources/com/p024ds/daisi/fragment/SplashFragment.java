package com.p024ds.daisi.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.p019mq.sdk.MqBridge;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.entity.TemplateStatus;
import com.p024ds.daisi.manager.DialogManager;
import com.p024ds.daisi.mvp.presenters.BlackListPresenter;
import com.p024ds.daisi.mvp.presenters.TemplatePresenter;
import com.p024ds.daisi.mvp.views.BlackListView;
import com.p024ds.daisi.mvp.views.TemplateView;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.services.TimerService;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ScriptVerifyUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.util.ArrayList;
import java.util.List;
import mobi.oneway.export.p083d.InterfaceC1925e;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplashFragment extends BaseFragement implements TemplateView, BlackListView {
    public static final int AN_HOUR = 60;
    private static final int APP_CLOSED = 1;
    private static final int APP_START = 3;
    private static final int APP_USE_TIPS = 2;
    private static final float CIRCLE_SIZE_VALUE = 8.0f;
    private static final int CIRCLE_TOTAL_COUNT = 6;
    public static final int CODES_FAIL_MESSAGE = 12;
    private static final int CYCLE_TIME_DOT = 100;
    private static final int DEFAULT_VALUE = 0;
    public static final int DELAY_500_MILLIS = 500;
    public static final int HALF_AN_HOUR = 30;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    public static final int SINGLE_APK_MESSAGE = 11;
    public static final int TEMPLATE_APK_MESSAGE = 10;
    private int currentDotCounts;
    private BlackListPresenter initDataPresenter;
    private boolean isBootRun;
    private GradientDrawable mBlueGradientDrawable;
    private GradientDrawable mGrayGradientDrawable;
    private LinearLayout mLinearCircleDot;
    private RunnableTask mRunnableTask;
    private String remarkText;
    private TemplatePresenter templatePresenter;
    private boolean isSecondRequestFalg = false;
    private List<ImageView> imageViewLists = new ArrayList();
    private int countReq = 0;
    private boolean checkedCallMain = false;
    private boolean isRepeat = false;
    private boolean isCheckApk = true;
    private boolean isSendMsg = false;
    private boolean isBlackListAcquireComplete = false;
    private Handler mHandler = new Handler() { // from class: com.ds.daisi.fragment.SplashFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 10:
                    SplashFragment.this.templatePresenter.singleApkOrTemplateChecked(SplashFragment.class.getCanonicalName(), SplashFragment.this.getActivity(), URLConstant.TEMPLATE_MANAGER);
                    break;
                case 11:
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j = SplashFragment.this.appContext.mSharedUsed.getLong(Constants.SINGLE_APK_REQ_TIME, 0L);
                    Logger.m3442e("SINGLE_APK_MESSAGE:  currentTime=" + jCurrentTimeMillis + ",saveTime=" + j, new Object[0]);
                    Logger.m3442e("SINGLE_APK_MESSAGE isSendMsg=" + SplashFragment.this.isSendMsg + ",isCheckApk=" + SplashFragment.this.isCheckApk, new Object[0]);
                    if (jCurrentTimeMillis < j || SplashFragment.this.isSendMsg) {
                        if (SplashFragment.this.isSendMsg) {
                            SplashFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.fragment.SplashFragment.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    SplashFragment.this.toMainActivity();
                                }
                            }, 500L);
                        }
                    } else {
                        SplashFragment.this.templatePresenter.singleApkOrTemplateChecked(SplashFragment.class.getCanonicalName(), SplashFragment.this.getActivity(), URLConstant.MONOMER_INSTALLATION_PACKAGE);
                    }
                    break;
                case 12:
                    SplashFragment.this.isRepeat = true;
                    SplashFragment.this.initDataPresenter.getCodes(SplashFragment.this.getActivity());
                    break;
            }
        }
    };
    private String[] permissions = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CALL_PHONE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"};

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_splash;
    }

    static /* synthetic */ int access$908(SplashFragment splashFragment) {
        int i = splashFragment.currentDotCounts;
        splashFragment.currentDotCounts = i + 1;
        return i;
    }

    public static SplashFragment newInstance(boolean z) {
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SplashFragment.class.getCanonicalName(), z);
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isBootRun = getArguments().getBoolean(SplashFragment.class.getCanonicalName());
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        NetworkUtils.isAvailable(getActivity());
        this.templatePresenter = new TemplatePresenter(this);
        this.initDataPresenter = new BlackListPresenter(this);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions();
        } else {
            Log.e("zzz", "SplashFragment--低于22");
            initRequest();
        }
    }

    private void initRequest() {
        AppContext.getInstance().initEngine();
        this.initDataPresenter.isSetBlackList(getActivity());
        this.initDataPresenter.getCodes(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isHasNetwork(boolean z) {
        if (!z) {
            AlertDialog alertDialogShowMessageDialog = DialogManager.getInstance().showMessageDialog(getActivity(), R.layout.dialog_network_inspection, R.style.Theme_Dialog);
            alertDialogShowMessageDialog.setCancelable(false);
            alertDialogShowMessageDialog.show();
            TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_reset);
            TextView textView2 = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_setting);
            textView.setOnClickListener(new ResetNetworkListener(alertDialogShowMessageDialog));
            textView2.setOnClickListener(new SettingNetworkListener());
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.appContext.mSharedUsed.getLong(Constants.TEMPLATE_APK_REQ_TIME, 0L);
        Logger.m3442e("currentTime=" + jCurrentTimeMillis + ",saveTime=" + j, new Object[0]);
        if (jCurrentTimeMillis >= j) {
            this.templatePresenter.singleApkOrTemplateChecked(SplashFragment.class.getCanonicalName(), getActivity(), URLConstant.TEMPLATE_MANAGER);
        } else {
            this.isCheckApk = false;
            this.mHandler.obtainMessage(11).sendToTarget();
        }
        this.initDataPresenter.isSetBlackList(getActivity());
        this.initDataPresenter.getCodes(getActivity());
    }

    private void getDialogMessageByServer(int i, String str) {
        AlertDialog alertDialogShowMessageDialog = DialogManager.getInstance().showMessageDialog(getActivity(), R.layout.dialog_version_inspection, R.style.Theme_Dialog);
        alertDialogShowMessageDialog.setCancelable(false);
        alertDialogShowMessageDialog.show();
        TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_message);
        String strReplace = str.replace(IOUtils.LINE_SEPARATOR_WINDOWS, "");
        Logger.m3442e("文本提示：" + strReplace.length(), new Object[0]);
        if (i == 1 && TextUtils.isEmpty(strReplace)) {
            textView.setText(getString(R.string.version_disable));
        } else {
            textView.setText(strReplace);
        }
        ((TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_known)).setOnClickListener(new VersionKnownListener(alertDialogShowMessageDialog, i));
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
    }

    private void initView(View view) {
        this.mLinearCircleDot = (LinearLayout) view.findViewById(R.id.id_ll_dot);
        initCircleDot();
        if (this.mRunnableTask == null) {
            this.mRunnableTask = new RunnableTask();
            this.mRunnableTask.start();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mRunnableTask != null) {
            this.mHandler.removeCallbacks(this.mRunnableTask);
            this.mRunnableTask = null;
        }
        this.initDataPresenter.cancelReq();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(SplashFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(SplashFragment.class.getCanonicalName());
    }

    private void initCircleDot() {
        this.mLinearCircleDot.removeAllViews();
        this.imageViewLists.clear();
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(getActivity());
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setCornerRadius(AppDeviceUtils.getDipToPx(getActivity(), CIRCLE_SIZE_VALUE));
            gradientDrawable.setSize(AppDeviceUtils.getDipToPx(getActivity(), CIRCLE_SIZE_VALUE), AppDeviceUtils.getDipToPx(getActivity(), CIRCLE_SIZE_VALUE));
            if (i == 0) {
                gradientDrawable.setColor(Color.parseColor("#3c7ac5"));
                this.mBlueGradientDrawable = gradientDrawable;
            } else {
                gradientDrawable.setColor(Color.parseColor("#bababa"));
                if (i == 5) {
                    this.mGrayGradientDrawable = gradientDrawable;
                }
            }
            imageView.setImageDrawable(gradientDrawable);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, AppDeviceUtils.getDipToPx(getActivity(), CIRCLE_SIZE_VALUE), 0);
            this.mLinearCircleDot.addView(imageView, layoutParams);
            this.imageViewLists.add(imageView);
        }
    }

    @Override // com.p024ds.daisi.mvp.views.TemplateView
    public void apkCheckedResult(TemplateStatus templateStatus) {
        if (templateStatus != null) {
            long jCurrentTimeMillis = System.currentTimeMillis() + 7200000;
            if (!this.isSecondRequestFalg) {
                this.isSecondRequestFalg = true;
                this.appContext.mSharedUsed.edit().putLong(Constants.TEMPLATE_APK_REQ_TIME, jCurrentTimeMillis).commit();
                switch (Integer.parseInt(templateStatus.status)) {
                    case 1:
                        getDialogMessageByServer(1, templateStatus.remark);
                        return;
                    case 2:
                        this.remarkText = templateStatus.remark;
                        break;
                    case 3:
                        break;
                    default:
                        return;
                }
                this.mHandler.obtainMessage(11).sendToTarget();
                return;
            }
            this.isSecondRequestFalg = false;
            this.appContext.mSharedUsed.edit().putLong(Constants.SINGLE_APK_REQ_TIME, jCurrentTimeMillis).commit();
            switch (Integer.parseInt(templateStatus.status)) {
                case 1:
                    getDialogMessageByServer(1, templateStatus.remark);
                    break;
                case 2:
                    String str = templateStatus.remark;
                    if (!TextUtils.isEmpty(this.remarkText)) {
                        getDialogMessageByServer(2, this.remarkText);
                    } else if (!TextUtils.isEmpty(str)) {
                        getDialogMessageByServer(2, str);
                    }
                    break;
                case 3:
                    if (!TextUtils.isEmpty(this.remarkText)) {
                        getDialogMessageByServer(2, this.remarkText);
                    } else {
                        toMainActivity();
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toMainActivity() {
        if (this.checkedCallMain) {
            return;
        }
        this.checkedCallMain = true;
        Intent intent = new Intent(getActivity(), (Class<?>) ElfinPayActivity.class);
        intent.putExtra(ElfinPayActivity.class.getCanonicalName(), this.isBootRun);
        startActivity(intent);
        getActivity().finish();
    }

    @Override // com.p024ds.daisi.mvp.views.TemplateView
    public void errorMsg(String str) {
        this.countReq++;
        if (str.startsWith(String.valueOf(11)) && this.countReq <= 5) {
            str = String.format(getString(R.string.req_count_template), Integer.valueOf(this.countReq)) + str.substring(2);
            this.mHandler.obtainMessage(11).sendToTarget();
        } else if (str.startsWith(String.valueOf(10)) && this.countReq <= 5) {
            str = String.format(getString(R.string.req_count_template), Integer.valueOf(this.countReq)) + str.substring(2);
            this.mHandler.obtainMessage(10).sendToTarget();
        }
        Logger.m3442e("msg:" + str + ",countReq=" + this.countReq, new Object[0]);
        ToastUtils.showToastLong(getActivity(), str);
    }

    @Override // com.p024ds.daisi.mvp.views.BlackListView
    public void setBlackListResult(String str) {
        Logger.m3442e("result=" + str, new Object[0]);
        this.isBlackListAcquireComplete = true;
        if (!TextUtils.isEmpty(str)) {
            Logger.m3442e("随机时间：" + ((int) ((Math.random() * 60.0d) + 30.0d)), new Object[0]);
        }
        verifyNetRequest();
    }

    @Override // com.p024ds.daisi.mvp.views.BlackListView
    public void getBlackError() {
        this.isBlackListAcquireComplete = true;
        verifyNetRequest();
    }

    @Override // com.p024ds.daisi.mvp.views.TokenBillView
    public void getLotteryArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ScriptVerifyUtils.saveCodes(str);
        Logger.m3442e("票据的获取：" + str, new Object[0]);
        this.isSendMsg = true;
        verifyNetRequest();
    }

    @Override // com.p024ds.daisi.mvp.views.TokenBillView
    public void getLotteryByFail(String str) {
        if (!this.isRepeat) {
            this.mHandler.sendEmptyMessage(12);
        } else {
            this.isSendMsg = true;
            verifyNetRequest();
        }
    }

    private void verifyNetRequest() {
        if (this.isBlackListAcquireComplete && this.isSendMsg) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.fragment.SplashFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    SplashFragment.this.toMainActivity();
                }
            }, 500L);
        }
    }

    private class RunnableTask implements Runnable {
        private RunnableTask() {
        }

        public void start() {
            SplashFragment.this.mHandler.removeCallbacks(this);
            SplashFragment.this.mHandler.postDelayed(this, 100L);
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashFragment.access$908(SplashFragment.this);
            for (int i = 0; i < SplashFragment.this.imageViewLists.size(); i++) {
                if (i == SplashFragment.this.currentDotCounts % SplashFragment.this.imageViewLists.size()) {
                    ((ImageView) SplashFragment.this.imageViewLists.get(i)).setImageDrawable(SplashFragment.this.mBlueGradientDrawable);
                } else {
                    ((ImageView) SplashFragment.this.imageViewLists.get(i)).setImageDrawable(SplashFragment.this.mGrayGradientDrawable);
                }
            }
            SplashFragment.this.mHandler.removeCallbacks(this);
            SplashFragment.this.mHandler.postDelayed(this, 100L);
        }
    }

    private class SettingNetworkListener implements View.OnClickListener {
        private SettingNetworkListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SplashFragment.this.startActivity(new Intent("android.settings.DATA_ROAMING_SETTINGS"));
        }
    }

    private class ResetNetworkListener implements View.OnClickListener {
        private AlertDialog mAlertDialog;

        public ResetNetworkListener(AlertDialog alertDialog) {
            this.mAlertDialog = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mAlertDialog.dismiss();
            SplashFragment.this.isHasNetwork(NetworkUtils.isAvailable(SplashFragment.this.getActivity()));
        }
    }

    private class VersionKnownListener implements View.OnClickListener {
        private AlertDialog mAlertDialog;
        private int returnCode;

        public VersionKnownListener(AlertDialog alertDialog, int i) {
            this.mAlertDialog = alertDialog;
            this.returnCode = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mAlertDialog.dismiss();
            if (this.returnCode != 1) {
                SplashFragment.this.toMainActivity();
                return;
            }
            SplashFragment.this.appContext.mSharedUsed.edit().putLong(Constants.TEMPLATE_APK_REQ_TIME, 0L).commit();
            SplashFragment.this.appContext.mSharedUsed.edit().putLong(Constants.SINGLE_APK_REQ_TIME, 0L).commit();
            SplashFragment.this.getActivity().stopService(new Intent(SplashFragment.this.getActivity(), (Class<?>) PhoneStateService.class));
            MqBridge.exit();
            SplashFragment.this.getActivity().stopService(new Intent(SplashFragment.this.getActivity(), (Class<?>) TimerService.class));
            SplashFragment.this.getActivity().finish();
            System.exit(0);
        }
    }

    private void requestPermissions() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), str) != 0) {
                Log.e("zzz", "permission==" + str);
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            Log.e("zzz", "SplashFrment--permissionList.isEmpty()");
            initRequest();
        } else {
            Log.e("zzz", "SplashFrment--permissionList.请求");
            requestPermissions((String[]) arrayList.toArray(new String[0]), 1);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = true;
        if (i == 1) {
            for (int i2 : iArr) {
                Log.e("zzz", "grant--" + i2);
                if (i2 != 0) {
                    z = false;
                    break;
                }
            }
            if (z) {
                Log.e("zzz", "SplashFragment--isAllGranted");
                initRequest();
            } else {
                openAppDetails();
            }
        }
    }

    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("没有授权的权限，请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() { // from class: com.ds.daisi.fragment.SplashFragment.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + SplashFragment.this.getActivity().getPackageName()));
                intent.addFlags(268435456);
                intent.addFlags(InterfaceC1925e.f4896e);
                intent.addFlags(8388608);
                SplashFragment.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener) null);
        builder.show();
    }
}
