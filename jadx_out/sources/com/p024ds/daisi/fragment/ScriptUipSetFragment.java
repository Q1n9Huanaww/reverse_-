package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.mobileanjian.ipc.uip.UipHelper;
import com.cyjh.mobileanjian.ipc.uip.UisScriptRunner;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mqm.MiscUtilities;
import com.cyjh.share.bean.ILFYBean;
import com.cyjh.share.bean.response.FengLingAdInfo;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.counttimer.SecondTimer;
import com.cyjh.share.helper.RecognitionLibraryDownloadHelper;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.CommonUtils;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.ShellUtils;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SlToast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.IFLYNativeAd;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.activity.news.FengLingAdWebViewActivity;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.dialog.NoRootDialog;
import com.p024ds.daisi.dialog.PermissionGuideDialog;
import com.p024ds.daisi.download.ApkDownloadHelper;
import com.p024ds.daisi.entity.AdEvent;
import com.p024ds.daisi.entity.ElfinFloatViewEvent;
import com.p024ds.daisi.entity.FeLingAdReportInfo;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.entity.RefreshTimeEvent;
import com.p024ds.daisi.entity.ScriptUIEvent;
import com.p024ds.daisi.listener.IFLYADListener;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.FengLingAdManager;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.rom.FloatWindowManager;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.CollectionUtils;
import com.p024ds.daisi.util.ExpireTimeUtil;
import com.p024ds.daisi.util.FeLingAdDBHelper;
import com.p024ds.daisi.util.FileUtils;
import com.p024ds.daisi.util.GlideUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.LogUtils;
import com.p024ds.daisi.util.ScriptDownloadHelper;
import com.p024ds.daisi.util.ScriptUtil;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUipSetFragment extends BaseFragement implements View.OnClickListener {
    private static final int BACKGROUND_AD = 102;
    private static final int BACKGROUND_AD_LOAD_MESSAGE = 10051;
    private static final int BG_DISPLAY_SUCCESS_STATIS_MESSAGE = 10052;
    private static final int CLICK_AD_COUNT_TIME = 5;
    private static final int DISPLAY_FENG_LING_AD_SUCCESS_MESSAGE = 10054;
    public static final String DISPLAY_TRY_TIME_FLAG = "-1";
    private static final int FENG_LING_AD = 101;
    private static final int FENG_LING_NET_REQUEST_MESSAGE = 10053;
    private static final int ILFY_DISPLAY_SUCCESS_STATIS = 10050;
    private static final int REQUEST_FENG_LING_AD_WEB_VIEW = 300;
    public static final int SECONDS_IN_FUTURE = 10;
    public static final String TAG = "ScriptUipSetFragment";
    private static final int XUN_FEI_AD = 100;
    private NativeDataRef adDataRef;
    private Button btnRunScript;
    private ElfinPayActivity elfinPayActivity;
    private FengLingAdManager fengLingAdManager;
    private Button mBtnGetMoreTime;
    private float mClickDwonX;
    private float mClickDwonY;
    private float mClickUpX;
    private float mClickUpY;
    private ScriptConfigSaveRunnable mConfigSaveRunnable;
    private SubmitCountTimer mCountTimer;
    private FengLingAdInfo mFengLingAdInfo;
    private GestureDetector mGestureDetector;
    private ImageView mImgBottomAd;
    private boolean mIsViewCreated;
    private RelativeLayout mRelativeBottomAd;
    private CopyScriptFilesTask mScriptFilesTask;
    private SdCardScriptFilesTask mSdCardScriptFilesTask;
    private TextView mTvDeleteAd;
    private TextView mTvDisplayExpireTime;
    private TextView mTvILFYAdMark;
    private LinearLayout uipLinearLayoutUI;
    private View viewParent;
    private int counter = 0;
    private MyHandler mHandler = new MyHandler();
    private boolean preventMultipleClicks = false;
    private int mCurrentAdType = 100;
    private SVCGestureListener mGestureListener = new SVCGestureListener();
    ElfinPayActivity.MyTouchListener myTouchListener = new ElfinPayActivity.MyTouchListener() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.1
        @Override // com.ds.daisi.activity.ElfinPayActivity.MyTouchListener
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return ScriptUipSetFragment.this.mGestureDetector.onTouchEvent(motionEvent);
        }
    };
    private IFLYADListener mNativeListener = new IFLYADListener() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.3
        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdLoaded(NativeDataRef nativeDataRef) {
            LogUtils.m1356i(ScriptUipSetFragment.TAG, "IFLYADListener onAdLoaded -->");
            SlToast.showToastShort(ScriptUipSetFragment.this.getActivity(), "讯飞广告加载成功");
            ScriptUipSetFragment.this.adDataRef = nativeDataRef;
            ScriptUipSetFragment.this.displayBottomAD(ScriptUipSetFragment.this.adDataRef.getImgUrl());
        }

        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdFailed(AdError adError) {
            LogUtils.m1356i(ScriptUipSetFragment.TAG, "IFLYADListener onAdFailed -->");
            SlToast.showToastShort(ScriptUipSetFragment.this.getActivity(), "讯飞广告加载失败 message=" + adError.getMessage());
            ScriptUipSetFragment.this.setCurrentAdType(101);
            ScriptUipSetFragment.this.mHandler.obtainMessage(ScriptUipSetFragment.FENG_LING_NET_REQUEST_MESSAGE).sendToTarget();
        }
    };

    public void checkStoragePermission() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            SlLog.m1333e(TAG, "External storage is not mounted");
            return;
        }
        File file = new File(getContext().getFilesDir().getAbsolutePath() + "/assets");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_scriptuip_set;
    }

    public void judgeTimeOperate() {
    }

    static /* synthetic */ int access$1408(ScriptUipSetFragment scriptUipSetFragment) {
        int i = scriptUipSetFragment.counter;
        scriptUipSetFragment.counter = i + 1;
        return i;
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        SlLog.m1334i(TAG, "onSaveInstanceState --> ");
        bundle.putInt("onceTryMinute", ParamsWrap.getParamsWrap().onceTryMinute);
        bundle.putLong("ExpireTime", AppContext.getInstance().ExpireTime);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        SlLog.m1334i(TAG, "onViewStateRestored --> ");
        if (bundle != null) {
            ParamsWrap.getParamsWrap().onceTryMinute = bundle.getInt("onceTryMinute");
            AppContext.getInstance().ExpireTime = bundle.getLong("ExpireTime");
            SlLog.m1334i(TAG, "onViewStateRestored --> onceTryMinute=" + ParamsWrap.getParamsWrap().onceTryMinute);
            SlLog.m1334i(TAG, "onViewStateRestored --> ExpireTime=" + AppContext.getInstance().ExpireTime);
        }
        judgeTimeOperate();
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ((ElfinPayActivity) getActivity()).registerMyTouchListener(this.myTouchListener);
        return viewOnCreateView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_option_start /* 2131755402 */:
                if (!RootUtil.isRoot()) {
                    new NoRootDialog(getActivity()).show();
                    break;
                } else if (!this.preventMultipleClicks) {
                    this.preventMultipleClicks = true;
                    showRunFloatJudgeOperate();
                    break;
                }
                break;
            case R.id.btn_option_getmoretime /* 2131755403 */:
                IntentUtils.toCallBindRegCodeActivity(getActivity(), AppContext.getInstance().regCode);
                break;
            case R.id.id_img_ad /* 2131755432 */:
                onClickBottomImg();
                break;
            case R.id.tv_delete_ad /* 2131755433 */:
                if (this.counter < 5) {
                    onClickBottomImg();
                } else {
                    cancelTimer();
                    this.mRelativeBottomAd.setVisibility(8);
                }
                break;
        }
    }

    private void showRunFloatJudgeOperate() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                boolean zCanDrawOverlays = Settings.canDrawOverlays(getActivity());
                if (Build.VERSION.SDK_INT >= 26) {
                    zCanDrawOverlays = !this.appContext.sharedPreferences.getBoolean(PermissionGuideDialog.FIRST_OPEN_FLOAT_PERMISSION, true);
                }
                SlLog.m1334i(TAG, "showRunFloatJudgeOperate --> canDrawOverlays=" + zCanDrawOverlays);
                if (zCanDrawOverlays) {
                    showRunFloat();
                    return;
                } else {
                    new PermissionGuideDialog(getActivity(), new PermissionGuideDialog.OnDialogClickListener(this) { // from class: com.ds.daisi.fragment.ScriptUipSetFragment$$Lambda$0
                        private final ScriptUipSetFragment arg$1;

                        {
                            this.arg$1 = this;
                        }

                        @Override // com.ds.daisi.dialog.PermissionGuideDialog.OnDialogClickListener
                        public void onDialogClick(int i) {
                            this.arg$1.lambda$showRunFloatJudgeOperate$0$ScriptUipSetFragment(i);
                        }
                    }).show();
                    return;
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        showRunFloat();
    }

    final /* synthetic */ void lambda$showRunFloatJudgeOperate$0$ScriptUipSetFragment(int i) {
        if (101 == i) {
            this.preventMultipleClicks = false;
        }
    }

    private void showRunFloat() {
        SlLog.m1334i(TAG, "showRunFloat -->");
        this.mConfigSaveRunnable = new ScriptConfigSaveRunnable();
        ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(this.mConfigSaveRunnable);
        if (!FloatWindowManager.getInstance().checkPermission(AppContext.getInstance())) {
            FloatWindowManager.getInstance().applyPermission(getActivity());
            return;
        }
        if (this.appContext.STATE_SCRIPT == 0) {
            this.appContext.STATE_SCRIPT = 1;
        }
        this.appContext.showFloatView();
        this.appContext.mElfinFloatView.mContext = getActivity();
        this.elfinPayActivity.moveTaskToBack(true);
    }

    private void onClickBottomImg() {
        this.mRelativeBottomAd.setVisibility(8);
        cancelTimer();
        if (this.mCurrentAdType == 100 && this.adDataRef != null) {
            this.adDataRef.onClick(this.mRelativeBottomAd);
        } else if (this.mCurrentAdType == 101) {
            onClickImgFeLingAd();
        } else if (this.mCurrentAdType == 102) {
            AppStatisticsPresenter.getStatisticsPresenter().setCallback(new AppStatisticsPresenter.OnCallback() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.2
                @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
                public void onCallbackSuc(Object obj) {
                    AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
                    ParamsWrap.getParamsWrap().onClickBackgroundAdOpera(ScriptUipSetFragment.this.elfinPayActivity);
                }
            }).loadAd(this.elfinPayActivity, 1, 3, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(OptionFragment.class.getCanonicalName());
        this.preventMultipleClicks = false;
        SlLog.m1334i(TAG, "onResume --> regCode=" + AppContext.getInstance().regCode + ",bindRegCodeBackTime=" + this.elfinPayActivity.bindRegCodeBackTime);
        EventBus.getDefault().post(new RefreshTimeEvent());
        judgeTimeOperate();
        if (Build.VERSION.SDK_INT < 26) {
            Log.d("AutoStart", "SDK版本过低，不执行自动启动");
            return;
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("auto_start_prefs", 0);
        if (!sharedPreferences.getBoolean("has_auto_started", false) && RootUtil.isRoot()) {
            sharedPreferences.edit().putBoolean("has_auto_started", true).apply();
            if (this.preventMultipleClicks) {
                return;
            }
            this.preventMultipleClicks = true;
            showRunFloatJudgeOperate();
        }
    }

    private void displayTryTime() {
        String string = getString(R.string.optionfragment_duetime_no);
        if (ParamsWrap.getParamsWrap().onceTryMinute > 0) {
            string = String.format(getString(R.string.optionfragment_unpaid), Integer.valueOf(ParamsWrap.getParamsWrap().onceTryMinute));
        }
        this.mTvDisplayExpireTime.setText(string);
        this.mTvDisplayExpireTime.setVisibility(0);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mConfigSaveRunnable != null) {
            ThreadPoolProxyFactory.getNormalThreadPool().removeRunnable(this.mConfigSaveRunnable);
        }
        EventBus.getDefault().unregister(this);
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mScriptFilesTask != null) {
            this.mScriptFilesTask.cancel(false);
            this.mScriptFilesTask = null;
        }
        cancelTimer();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ((ElfinPayActivity) getActivity()).unRegisterMyTouchListener(this.myTouchListener);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.elfinPayActivity = (ElfinPayActivity) getActivity();
        this.appContext = AppContext.getInstance();
        this.mIsViewCreated = true;
        if (getView() != null) {
            this.viewParent = getView();
            initView();
            initListener();
        }
        this.mGestureDetector = new GestureDetector(getActivity(), this.mGestureListener);
    }

    private void initListener() {
        this.mTvDeleteAd.setOnClickListener(this);
        this.mImgBottomAd.setOnClickListener(this);
        this.btnRunScript.setOnClickListener(this);
        this.mBtnGetMoreTime.setOnClickListener(this);
        this.elfinPayActivity.setOnDisplayExpireTimeBack(new ElfinPayActivity.OnDisplayExpireTimeBackListener(this) { // from class: com.ds.daisi.fragment.ScriptUipSetFragment$$Lambda$1
            private final ScriptUipSetFragment arg$1;

            {
                this.arg$1 = this;
            }

            @Override // com.ds.daisi.activity.ElfinPayActivity.OnDisplayExpireTimeBackListener
            public void onDisplayExpireTimeBack() {
                this.arg$1.lambda$initListener$1$ScriptUipSetFragment();
            }
        });
    }

    final /* synthetic */ void lambda$initListener$1$ScriptUipSetFragment() {
        SlLog.m1334i(TAG, "initListener setOnDisplayExpireTimeBack -->");
        judgeTimeOperate();
    }

    private void displayExpireTimeOperate() {
        long jCurrentTimeMillis = (AppContext.getInstance().ExpireTime - (System.currentTimeMillis() / 1000)) / 60;
        if (jCurrentTimeMillis > 0) {
            displayRemainTime(jCurrentTimeMillis);
        }
    }

    private void displayRemainTime(long j) {
        this.mTvDisplayExpireTime.setText(ExpireTimeUtil.convertTimeFormat(getActivity(), j));
    }

    public void copyScriptFiles() {
        AssetManager assets = this.appContext.getAssets();
        File lcFile = this.appContext.mScript.getLcFile();
        File propFile = this.appContext.mScript.getPropFile();
        File atcFile = this.appContext.mScript.getAtcFile();
        File uipFile = this.appContext.mScript.getUipFile();
        File rtdFile = this.appContext.mScript.getRtdFile();
        try {
            InputStream inputStreamOpen = assets.open(APPConstant.SCRIPT_FILENAME_LC);
            InputStream inputStreamOpen2 = assets.open(APPConstant.SCRIPT_FILENAME_PROP);
            InputStream inputStreamOpen3 = assets.open(APPConstant.SCRIPT_FILENAME_ATC);
            InputStream inputStreamOpen4 = assets.open(APPConstant.SCRIPT_FILENAME_UIP);
            InputStream inputStreamOpen5 = assets.open(APPConstant.SCRIPT_FILENAME_RTD);
            this.mScriptFilesTask = new CopyScriptFilesTask(new File[]{lcFile, propFile, atcFile, rtdFile, uipFile});
            this.mScriptFilesTask.execute(inputStreamOpen, inputStreamOpen2, inputStreamOpen3, inputStreamOpen5, inputStreamOpen4);
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private static class CopyScriptFilesTask extends AsyncTask<InputStream, Void, Boolean> {
        private File[] destFiles;
        private WeakReference<ScriptUipSetFragment> mReference;

        private CopyScriptFilesTask(ScriptUipSetFragment scriptUipSetFragment, File... fileArr) {
            this.mReference = new WeakReference<>(scriptUipSetFragment);
            this.destFiles = fileArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(InputStream... inputStreamArr) {
            for (int i = 0; i < inputStreamArr.length; i++) {
                FileUtils.copyToFile(inputStreamArr[i], this.destFiles[i]);
            }
            ScriptUipSetFragment scriptUipSetFragment = this.mReference.get();
            if (scriptUipSetFragment != null) {
                AppContext appContext = AppContext.getInstance();
                if (appContext.mScript.getUipFile().exists() && appContext.mScript.getUipFile().length() > 0) {
                    UisScriptRunner.getInstance().startLoop(this.destFiles[0].getAbsolutePath());
                }
                scriptUipSetFragment.pushApkToSystemApp();
                RecognitionLibraryDownloadHelper.get().download(InterfaceRelatedConstants.RECOGNITION_LIBRARY_URL, null);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            ScriptUipSetFragment scriptUipSetFragment = this.mReference.get();
            if (scriptUipSetFragment != null) {
                scriptUipSetFragment.initQUI();
            }
        }
    }

    @RequiresApi(api = 3)
    private static class SdCardScriptFilesTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<ScriptUipSetFragment> mReference;
        private File sdCardLcFile;
        private File sdCardUipFile;

        private SdCardScriptFilesTask(ScriptUipSetFragment scriptUipSetFragment, File... fileArr) {
            this.mReference = new WeakReference<>(scriptUipSetFragment);
            this.sdCardLcFile = fileArr[0];
            this.sdCardUipFile = fileArr[1];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            ScriptUipSetFragment scriptUipSetFragment = this.mReference.get();
            if (scriptUipSetFragment != null) {
                if (this.sdCardUipFile.exists() && this.sdCardUipFile.length() > 0) {
                    UisScriptRunner.getInstance().startLoop(this.sdCardLcFile.getAbsolutePath());
                }
                scriptUipSetFragment.pushApkToSystemApp();
                RecognitionLibraryDownloadHelper.get().download(InterfaceRelatedConstants.RECOGNITION_LIBRARY_URL, null);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            ScriptUipSetFragment scriptUipSetFragment = this.mReference.get();
            if (scriptUipSetFragment != null) {
                scriptUipSetFragment.initQUI();
            }
        }
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initData();
    }

    private void initData() {
        this.fengLingAdManager = new FengLingAdManager();
        if (AppContext.getInstance().isUseSdcardScript) {
            sdCardScriptFiles();
        } else {
            copyScriptFiles();
        }
        EventBus.getDefault().register(this);
        FeLingAdDBHelper.getInstance().deleteAllFeLingAdReportInfo(Constants.FENG_LING_AD_BANNER_ID);
    }

    public void sdCardScriptFiles() {
        String appAssetsDir = ScriptUtil.getAppAssetsDir(getActivity());
        try {
            this.mSdCardScriptFilesTask = new SdCardScriptFilesTask(new File[]{new File(appAssetsDir, APPConstant.SCRIPT_FILENAME_LC), new File(appAssetsDir, APPConstant.SCRIPT_FILENAME_UIP)});
            this.mSdCardScriptFilesTask.execute(new Void[0]);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void judgeLoadAdOperate() {
        if (CommonUtils.isSimulator(getActivity())) {
            loadBackgroundAd();
        } else {
            initNativeAd();
        }
    }

    private void initNativeAd() {
        ILFYBean iLFYBeanDisplayILFYAd = ParamsWrap.getParamsWrap().displayILFYAd(3, IFLYADListener.IFLYAD_PART_SCREEN_KEY);
        if (iLFYBeanDisplayILFYAd.isDisplay) {
            try {
                IFLYNativeAd iFLYNativeAd = new IFLYNativeAd(getActivity(), iLFYBeanDisplayILFYAd.key, this.mNativeListener);
                iFLYNativeAd.setParameter(AdKeys.APP_VER, AppDeviceUtils.getVersionName(getActivity()));
                iFLYNativeAd.loadAd();
                return;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        setCurrentAdType(101);
        loadFengLingAd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBackgroundAd() {
        if (ParamsWrap.getParamsWrap().isDisplayBackgroundSetAd(3)) {
            String str = ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.ImageUrl;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SlToast.showToastShort(getActivity(), "后台广告加载成功");
            displayBottomAD(str);
        }
    }

    private void initView() {
        this.uipLinearLayoutUI = (LinearLayout) this.viewParent.findViewById(R.id.uip_linearlayout);
        this.btnRunScript = (Button) this.viewParent.findViewById(R.id.btn_option_start);
        this.mRelativeBottomAd = (RelativeLayout) this.viewParent.findViewById(R.id.rl_bottom_content_ad);
        this.mImgBottomAd = (ImageView) this.viewParent.findViewById(R.id.id_img_ad);
        this.mTvDeleteAd = (TextView) this.viewParent.findViewById(R.id.tv_delete_ad);
        this.mTvILFYAdMark = (TextView) this.viewParent.findViewById(R.id.tv_ilfyad_mark);
        this.mTvDeleteAd.setTextColor(Color.parseColor("#ffffff"));
        this.mBtnGetMoreTime = (Button) this.viewParent.findViewById(R.id.btn_option_getmoretime);
        this.mTvDisplayExpireTime = (TextView) this.viewParent.findViewById(R.id.tv_option_duetime);
        Point resolution = AppDeviceUtils.getResolution(getContext());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.uipLinearLayoutUI.getLayoutParams();
        int i = ((int) (resolution.x - (resolution.x * 0.9f))) / 2;
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        this.uipLinearLayoutUI.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initQUI() {
        long length = this.appContext.mScript.getUipFile().length();
        if (this.appContext.mScript.getUipFile().exists() && length > 0) {
            LinearLayout layoutFromJson = this.appContext.mUipHelper.parseLayoutFromJson(new MiscUtilities().LoadUIFile(this.appContext.mScript.getUipFile().getAbsolutePath(), true), null);
            this.uipLinearLayoutUI.removeAllViews();
            this.uipLinearLayoutUI.addView(layoutFromJson);
        }
        try {
            parseOptionJson();
            this.elfinPayActivity.isDisplayUI = true;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void parseOptionJson() throws JSONException {
        File cfgFile = this.appContext.mScript.getCfgFile();
        if (cfgFile.exists()) {
            long length = this.appContext.mScript.getUipFile().length();
            if (!this.appContext.mScript.getUipFile().exists() || length <= 0) {
                return;
            }
            String textFile = FileUtils.readTextFile(cfgFile);
            if (this.appContext.mUipHelper == null) {
                this.appContext.mUipHelper = new UipHelper(getActivity());
            }
            this.appContext.mUipHelper.configViewFromJson(textFile);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentAdType(int i) {
        this.mCurrentAdType = i;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealWithAdEvent(AdEvent adEvent) {
        switch (adEvent.getType()) {
            case 100:
                onDownloadSuccessOperate();
                break;
            case 101:
                onInstallSuccessOperate(adEvent.getPackageName());
                break;
        }
    }

    private void onDownloadSuccessOperate() {
        SlLog.m1334i(TAG, "onDownloadSuccessOperate --> 1 ");
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(Constants.FENG_LING_AD_BANNER_ID);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            SlLog.m1334i(TAG, "onDownloadSuccessOperate --> 2");
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                if (feLingAdReportInfo != null && feLingAdReportInfo.getState() >= 1) {
                    String url = feLingAdReportInfo.getUrl();
                    if (1 == feLingAdReportInfo.getType() && feLingAdReportInfo.getPostState() == 0) {
                        reportLogInfo(url);
                        feLingAdReportInfo.setPostState(1);
                        feLingAdReportInfo.save();
                        SlLog.m1332d(TAG, "onDownloadSuccessOperate --> 3 url=" + url);
                    }
                }
            }
        }
    }

    private void onInstallSuccessOperate(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SlLog.m1334i(TAG, "onInstallSuccessOperate --> 1 ");
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(Constants.FENG_LING_AD_BANNER_ID);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            SlLog.m1334i(TAG, "onInstallSuccessOperate --> 2 ");
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                String url = feLingAdReportInfo.getUrl();
                SlLog.m1334i(TAG, "onInstallSuccessOperate --> 3 from packageName=" + str + ",local packageName=" + feLingAdReportInfo.getApkPageName());
                if (str.contains(feLingAdReportInfo.getApkPageName())) {
                    if (2 == feLingAdReportInfo.getType() && feLingAdReportInfo.getPostState() == 0) {
                        LogUtils.m1356i(TAG, "onInstallSuccessOperate --> packageName=" + str);
                        reportLogInfo(url);
                        feLingAdReportInfo.setPostState(1);
                    }
                    feLingAdReportInfo.setState(2);
                    feLingAdReportInfo.save();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayBottomAD(String str) {
        if (str.endsWith(InterfaceRelatedConstants.PIC_GIF_SUFFIX)) {
            RequestOptions defaultOption = GlideUtils.getDefaultOption();
            defaultOption.diskCacheStrategy(DiskCacheStrategy.DATA);
            GlideUtils.loadGif(getActivity(), str, this.mImgBottomAd, defaultOption);
        } else {
            RequestOptions defaultOption2 = GlideUtils.getDefaultOption();
            defaultOption2.diskCacheStrategy(DiskCacheStrategy.NONE);
            GlideUtils.load(getActivity(), str, this.mImgBottomAd, defaultOption2);
        }
        displayAd();
    }

    private void displayAd() {
        if (this.adDataRef != null) {
            this.adDataRef.onExposure(this.mRelativeBottomAd);
            if (!TextUtils.isEmpty(this.adDataRef.getAdSourceMark())) {
                this.mTvILFYAdMark.setText(String.format(getString(R.string.ad_source_mark), this.adDataRef.getAdSourceMark()));
                this.mTvILFYAdMark.setVisibility(0);
            }
        }
        startCountTimer();
        this.mTvDeleteAd.setVisibility(0);
        this.mRelativeBottomAd.setVisibility(0);
        if (this.mCurrentAdType == 100) {
            if (this.adDataRef != null) {
                this.mHandler.obtainMessage(ILFY_DISPLAY_SUCCESS_STATIS).sendToTarget();
            }
        } else if (this.mCurrentAdType == 101) {
            this.mHandler.obtainMessage(DISPLAY_FENG_LING_AD_SUCCESS_MESSAGE).sendToTarget();
        } else if (this.mCurrentAdType == 102) {
            this.mHandler.obtainMessage(BG_DISPLAY_SUCCESS_STATIS_MESSAGE).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFengLingAd() {
        LogUtils.m1356i(TAG, "loadFengLingAd -->  isSimulator=" + CommonUtils.isSimulator(AppContext.getInstance()));
        if (CommonUtils.isSimulator(AppContext.getInstance())) {
            setCurrentAdType(102);
            this.mHandler.obtainMessage(BACKGROUND_AD_LOAD_MESSAGE).sendToTarget();
        } else {
            try {
                this.fengLingAdManager.getFengLingAdInfo(TAG, 1, new Callback<FengLingAdInfo>() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.4
                    @Override // com.p024ds.daisi.mvp.managers.Callback
                    public void finish(FengLingAdInfo fengLingAdInfo) {
                        ScriptUipSetFragment.this.mFengLingAdInfo = fengLingAdInfo;
                        ScriptUipSetFragment.this.displayBottomAD(fengLingAdInfo.getImgs().getSrc());
                    }

                    @Override // com.p024ds.daisi.mvp.managers.Callback
                    public void error(String str) {
                        LogUtils.m1356i(ScriptUipSetFragment.TAG, "getFengLingAdInfo error --->，开始加载后台配置的广告---3" + str);
                        ScriptUipSetFragment.this.setCurrentAdType(102);
                        ScriptUipSetFragment.this.mHandler.obtainMessage(ScriptUipSetFragment.BACKGROUND_AD_LOAD_MESSAGE).sendToTarget();
                    }
                }, getActivity(), MyBuildConfig.FENG_LING_AD_URL);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShowImgFeLingAd() {
        SlLog.m1334i(TAG, "onShowImgFeLingAd -->");
        reportLogInfo(this.mFengLingAdInfo.getWebTracks().getImptrackUrls());
    }

    private void reportLogInfo(List<String> list) {
        SlLog.m1334i(TAG, "reportLogInfo --> targetUrlList 1");
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : list) {
            SlLog.m1334i(TAG, "reportLogInfo --> targetUrlList 2 url=" + str);
            reportLogInfo(str);
        }
    }

    private String dealWithUrl(String str) {
        String strReplace = str.contains(FengLingAdManager.EVENT_TIME) ? str.replace(FengLingAdManager.EVENT_TIME, String.valueOf(System.currentTimeMillis())) : str;
        if (str.contains(FengLingAdManager.CLICK_DOWN_X)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_DOWN_X, String.valueOf(this.mClickDwonX));
        }
        if (str.contains(FengLingAdManager.CLICK_DOWN_Y)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_DOWN_Y, String.valueOf(this.mClickDwonY));
        }
        if (str.contains(FengLingAdManager.CLICK_UP_X)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_UP_X, String.valueOf(this.mClickUpX));
        }
        return str.contains(FengLingAdManager.CLICK_UP_Y) ? strReplace.replace(FengLingAdManager.CLICK_UP_Y, String.valueOf(this.mClickUpY)) : strReplace;
    }

    private void reportLogInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strDealWithUrl = dealWithUrl(str);
        SlLog.m1334i(TAG, "reportLogInfo --> srcUrl=" + strDealWithUrl);
        this.fengLingAdManager.reportLogInfo(getActivity(), strDealWithUrl, new Callback<FengLingAdInfo>() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.5
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(FengLingAdInfo fengLingAdInfo) {
                SlLog.m1334i(ScriptUipSetFragment.TAG, "reportLogInfo --> finish ");
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str2) {
                SlLog.m1334i(ScriptUipSetFragment.TAG, "reportLogInfo --> error=" + str2);
            }
        });
    }

    private void onClickImgFeLingAd() {
        int actionName = this.mFengLingAdInfo.getActionName();
        reportLogInfo(this.mFengLingAdInfo.getWebTracks().getCtrackUrls());
        String clickUrl = this.mFengLingAdInfo.getClickUrl();
        SlLog.m1334i(TAG, "onClickImgFeLingAd -->  actionName=" + actionName + ",clickUrl=" + clickUrl);
        if (actionName == 12) {
            saveInfoToDB();
            new ApkDownloadHelper(getActivity(), Constants.FENG_LING_AD_BANNER_ID).downloadAPK(clickUrl);
        } else {
            toCallFeLingWebView(clickUrl);
        }
    }

    private void saveInfoToDB() {
        FengLingAdInfo.WebTracks webTracks = this.mFengLingAdInfo.getWebTracks();
        List<String> dstrackUrls = webTracks.getDstrackUrls();
        List<String> istrackUrls = webTracks.getIstrackUrls();
        if (CollectionUtils.isNotEmpty(dstrackUrls)) {
            Iterator<String> it2 = dstrackUrls.iterator();
            while (it2.hasNext()) {
                String strDealWithUrl = dealWithUrl(it2.next());
                SlLog.m1334i(TAG, "saveInfoToDB --> dealWithDstrackUrl=" + strDealWithUrl);
                new FeLingAdReportInfo(Constants.FENG_LING_AD_BANNER_ID, strDealWithUrl, 1, 0, 0).save();
            }
        }
        if (CollectionUtils.isNotEmpty(istrackUrls)) {
            Iterator<String> it3 = istrackUrls.iterator();
            while (it3.hasNext()) {
                new FeLingAdReportInfo(Constants.FENG_LING_AD_BANNER_ID, dealWithUrl(it3.next()), 2, 0, 0).save();
            }
        }
    }

    private void toCallFeLingWebView(String str) {
        Intent intent = new Intent(getActivity(), (Class<?>) FengLingAdWebViewActivity.class);
        intent.putExtra("web_view_url", str);
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    private void startCountTimer() {
        if (this.mCountTimer == null) {
            this.mCountTimer = new SubmitCountTimer(10);
        }
        this.mCountTimer.start();
    }

    private void cancelTimer() {
        if (this.mCountTimer != null) {
            this.counter = 0;
            this.mCountTimer.cancel();
            this.mCountTimer = null;
        }
    }

    private class SubmitCountTimer extends SecondTimer {
        private SubmitCountTimer(int i) {
            super(i);
        }

        @Override // com.cyjh.share.counttimer.SimpleTimer
        protected void onTick(int i) {
            if (ScriptUipSetFragment.this.isAdded()) {
                ScriptUipSetFragment.access$1408(ScriptUipSetFragment.this);
                SpannableString spannableString = new SpannableString(String.format(ScriptUipSetFragment.this.getString(R.string.ad_delete_btn), Integer.valueOf(i)));
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7900")), 0, "点击关闭".length(), 17);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), "点击关闭".length() + 1, "点击关闭".length() + 2, 17);
                if (ScriptUipSetFragment.this.mTvDeleteAd != null) {
                    ScriptUipSetFragment.this.mTvDeleteAd.setText(spannableString);
                }
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            ScriptUipSetFragment.this.counter = 0;
            ScriptUipSetFragment.this.mRelativeBottomAd.setVisibility(8);
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<ScriptUipSetFragment> mWeakReference;

        private MyHandler(ScriptUipSetFragment scriptUipSetFragment) {
            this.mWeakReference = new WeakReference<>(scriptUipSetFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ScriptUipSetFragment scriptUipSetFragment = this.mWeakReference.get();
            if (scriptUipSetFragment != null) {
                switch (message.what) {
                    case ScriptUipSetFragment.ILFY_DISPLAY_SUCCESS_STATIS /* 10050 */:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(scriptUipSetFragment.elfinPayActivity, 2, 2, 3L);
                        break;
                    case ScriptUipSetFragment.BACKGROUND_AD_LOAD_MESSAGE /* 10051 */:
                        scriptUipSetFragment.loadBackgroundAd();
                        break;
                    case ScriptUipSetFragment.BG_DISPLAY_SUCCESS_STATIS_MESSAGE /* 10052 */:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(scriptUipSetFragment.elfinPayActivity, 1, 2, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
                        break;
                    case ScriptUipSetFragment.FENG_LING_NET_REQUEST_MESSAGE /* 10053 */:
                        scriptUipSetFragment.loadFengLingAd();
                        break;
                    case ScriptUipSetFragment.DISPLAY_FENG_LING_AD_SUCCESS_MESSAGE /* 10054 */:
                        scriptUipSetFragment.onShowImgFeLingAd();
                        break;
                }
            }
        }
    }

    public void generateOptionJson() throws Exception {
        long length = this.appContext.mScript.getUipFile().length();
        if (!this.appContext.mScript.getUipFile().exists() || length <= 0) {
            return;
        }
        this.appContext.mUipHelper.saveToConfigFile(this.appContext.mScript.getCfgFile().getAbsolutePath());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageThreadMain(MsgItem msgItem) {
        if (msgItem.getMsgType() != 1004) {
            return;
        }
        initQUI();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveScriptUIEvent(ScriptUIEvent scriptUIEvent) {
        SlLog.m1334i(TAG, "onReceiveScriptUIEvent --> 更新Script UI");
        initQUI();
        if (scriptUIEvent.getType() == ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_IMMEDIATELY_AND_ENGINE_RUNNING) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.fragment.ScriptUipSetFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    EventBus.getDefault().post(new ElfinFloatViewEvent());
                }
            }, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushApkToSystemApp() {
        boolean z = this.appContext.sharedPreferences.getBoolean(APPConstant.HAS_ALREADY_PUSH_SYSTEM_APP, false);
        SlLog.m1334i(TAG, "pushApkToSystemApp --> hasAlreadyPushSystemApp=" + z);
        if (!z) {
            this.appContext.sharedPreferences.edit().putBoolean(APPConstant.HAS_ALREADY_PUSH_SYSTEM_APP, true).commit();
            getActivity().getPackageName();
            FileUtil.getFileByAbsolutePath(Environment.getExternalStorageDirectory().getAbsolutePath(), Constants.PUSH_SYSTEM_APP_NAME);
            boolean zCopyAssetsFile = CommonUtil.copyAssetsFile(getActivity(), Constants.PUSH_SYSTEM_APP_NAME, "/sdcard/app-release.apk");
            SlLog.m1334i(TAG, "pushApkToSystemApp --> filePath=/sdcard/app-release.apk,copyAppResult=" + zCopyAssetsFile);
            ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand(new String[]{"setenforce 0", "mount -o remount,rw /system", "chmod 777 /system", "chmod 777 /system/app", "cat /sdcard/app-release.apk > /system/app" + File.separator + Constants.PUSH_SYSTEM_APP_NAME, "chmod 777 /system/app" + File.separator + Constants.PUSH_SYSTEM_APP_NAME, "mount -o remount,ro /system"}, true, 0);
            boolean zIsEmpty = TextUtils.isEmpty(commandResultExecCommand.errorMsg);
            this.appContext.sharedPreferences.edit().putBoolean(APPConstant.HAS_ALREADY_PUSH_SYSTEM_APP, zIsEmpty).commit();
            SlLog.m1334i(TAG, "pushApkToSystemApp --> errorMsg=" + commandResultExecCommand.errorMsg + ",successMsg=" + commandResultExecCommand.successMsg + ",cmdResult=" + zIsEmpty);
        }
        checkStoragePermission();
    }

    private class ScriptConfigSaveRunnable implements Runnable {
        private ScriptConfigSaveRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ScriptUipSetFragment.this.generateOptionJson();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public class SVCGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        public SVCGestureListener() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            ScriptUipSetFragment.this.mClickUpX = motionEvent.getX();
            ScriptUipSetFragment.this.mClickUpY = motionEvent.getY();
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            ScriptUipSetFragment.this.mClickDwonX = motionEvent.getX();
            ScriptUipSetFragment.this.mClickDwonY = motionEvent.getY();
            return true;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        SlLog.m1334i(TAG, "setUserVisibleHint --> isVisibleToUser=" + z);
        if (this.mIsViewCreated && z) {
            judgeTimeOperate();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        SlLog.m1334i(TAG, "onHiddenChanged --> hidden=" + z);
        if (z) {
            return;
        }
        judgeTimeOperate();
    }

    public void btnRunScriptPerformClick() {
        try {
            this.btnRunScript.performClick();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
