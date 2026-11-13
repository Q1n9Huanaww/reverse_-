package com.p024ds.daisi.floatview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.p019mq.sdk.MqBridge;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.share.bean.request.UpdateRequestInfo;
import com.cyjh.share.bean.response.ScriptStartRunResponse;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.helper.RedFingerHelper;
import com.cyjh.share.mvp.manager.VersionUpdateManager;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SlToast;
import com.cyjh.share.util.SpUtil;
import com.cyjh.share.util.ToastUtils;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.dialog.HideFWDialog;
import com.p024ds.daisi.dialog.ScriptRTDDialog;
import com.p024ds.daisi.dialog.ScriptUIDialog;
import com.p024ds.daisi.entity.ElfinFloatViewEvent;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.services.BootService;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.services.TimerService;
import com.p024ds.daisi.services.VerifyService;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.ErrorUtilHelper;
import com.p024ds.daisi.util.JsonUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ScriptDownloadHelper;
import com.warnship.autoplay.R;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class ElfinFloatView extends LinearLayout implements View.OnClickListener {
    private static final int ELFIN_ORIENTATION_DOWN = 3;
    private static final int ELFIN_ORIENTATION_LEFT = 4;
    private static final int ELFIN_ORIENTATION_RIGHT = 2;
    private static final int ELFIN_ORIENTATION_UP = 1;
    private static final int LIMIT_ANGLE = 60;
    private static final int LIMIT_COUNT = 2;
    private static final int MAX_TRY_COUNT = 5;
    public static final int MSG_DEFAULT_DELAY = 3000;
    private static final int MSG_JUDGE_ENGINE_STATUS = 1000;
    private static final int MSG_OUT_TIME = 1001;
    public static final String TAG = "ElfinFloatView";
    private static final int TIME_ADSORPTION = 2000;
    private static final int TIME_INTERVAL = 1000;
    private static final int TIME_TRANSLUCENT = 5000;
    private boolean isPreventRepeatedClick;
    private AppContext mAppContext;
    public Context mContext;
    private CountDownTimer mCountDownTimer;
    private DeleteFloatView mDeleteFloatView;
    private float mDownX;
    private float mDownY;
    private int mGetEngineStatusTyrCount;
    private MyHandler mHandler;
    private ImageView mImageViewElfin;
    public ImageView mImgRunAndPause;
    public ImageView mImgSettingAndStop;
    private float mLastX;
    private float mLastY;
    private LinearLayout mLinearLayoutElfin;
    private LinearLayout mLinearLayoutInfo;
    private LinearLayout mLinearLayoutRun;
    private LinearLayout mLinearLayoutSetting;
    private LinearLayout mLinearLayoutTools;
    private int mLockState;
    private float mRawCurrentX;
    private float mRawCurrentY;
    private String mRegisterCode;
    private ScriptRTDDialog mScriptRTDDialog;
    private ScriptUIDialog mScriptUIDialog;
    public TextView mTvRunScript;
    public TextView mTvSettingScript;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowManagerParams;
    AppStatisticsPresenter.OnCallback onCallback;
    private HideFWDialog.OnHideCancelClickListener onHideCancelClickListener;
    private HideFWDialog.OnHideOKClickListener onHideOKClickListener;
    private final View.OnTouchListener onTouchListener;

    private static class MyHandler extends Handler {
        private WeakReference<ElfinFloatView> weakReference;

        private MyHandler(ElfinFloatView elfinFloatView) {
            this.weakReference = new WeakReference<>(elfinFloatView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ElfinFloatView elfinFloatView = this.weakReference.get();
            switch (message.what) {
                case 1000:
                    elfinFloatView.judgeEngineStatus();
                    break;
                case 1001:
                    elfinFloatView.exitEngine();
                    break;
            }
        }
    }

    public ElfinFloatView(Context context, DeleteFloatView deleteFloatView) {
        super(context);
        this.isPreventRepeatedClick = false;
        this.mHandler = new MyHandler();
        this.mCountDownTimer = new CountDownTimer(7000L, 1000L) { // from class: com.ds.daisi.floatview.ElfinFloatView.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (j > 3000 || j <= 2000) {
                    return;
                }
                ElfinFloatView.this.mImageViewElfin.setAlpha(0.5f);
                ElfinFloatView.this.changeToolBarState(false);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                int[] iArr = new int[2];
                ElfinFloatView.this.mImageViewElfin.getLocationOnScreen(iArr);
                if (iArr[0] >= (AppDeviceUtils.getResolution(ElfinFloatView.this.getContext()).x - ElfinFloatView.this.mDownX) / 2.0f) {
                    if (ElfinFloatView.this.mAppContext.STATE_SCRIPT == 2 || ElfinFloatView.this.mAppContext.STATE_SCRIPT == 4) {
                        ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_run_left_edge);
                        return;
                    } else if (ElfinFloatView.this.mAppContext.STATE_SCRIPT == 3) {
                        ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_pause_left_edge);
                        return;
                    } else {
                        ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_normal_left_edge);
                        return;
                    }
                }
                if (ElfinFloatView.this.mAppContext.STATE_SCRIPT == 2 || ElfinFloatView.this.mAppContext.STATE_SCRIPT == 4) {
                    ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_run_right_edge);
                } else if (ElfinFloatView.this.mAppContext.STATE_SCRIPT == 3) {
                    ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_pause_right_edge);
                } else {
                    ElfinFloatView.this.mImageViewElfin.setImageResource(R.drawable.elfin_normal_right_edge);
                }
            }
        };
        this.onTouchListener = new View.OnTouchListener() { // from class: com.ds.daisi.floatview.ElfinFloatView.4
            private static final int MIN_MOVEMENT = 5;
            private GestureDetector gestureDetector;
            private ElfinGestureListener gestureListener;

            {
                this.gestureListener = new ElfinGestureListener();
                this.gestureDetector = new GestureDetector(ElfinFloatView.this.getContext(), this.gestureListener);
            }

            public float getDistance(float f, float f2, float f3, float f4) {
                return (float) Math.sqrt(Math.pow(f - f3, 2.0d) + Math.pow(f2 - f4, 2.0d));
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code duplicated, block: B:18:0x00aa  */
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i;
                if (this.gestureDetector.onTouchEvent(motionEvent)) {
                    return true;
                }
                switch (motionEvent.getAction()) {
                    case 0:
                        ElfinFloatView.this.resetElfinState();
                        ElfinFloatView.this.mRawCurrentX = motionEvent.getRawX();
                        ElfinFloatView.this.mRawCurrentY = motionEvent.getRawY() - AppDeviceUtils.getStatusBarHeight(ElfinFloatView.this.getContext());
                        ElfinFloatView.this.mDownX = motionEvent.getX();
                        ElfinFloatView.this.mDownY = motionEvent.getY();
                        return false;
                    case 1:
                        ElfinFloatView.this.mRawCurrentX = motionEvent.getRawX();
                        ElfinFloatView.this.mRawCurrentY = motionEvent.getRawY() - AppDeviceUtils.getStatusBarHeight(ElfinFloatView.this.getContext());
                        if (!ElfinFloatView.this.isHide()) {
                            if (ElfinFloatView.this.mLinearLayoutTools.getVisibility() == 8) {
                                ElfinFloatView.this.moveToEdge();
                            }
                            ElfinFloatView.this.mDeleteFloatView.setVisibility(8);
                            ElfinFloatView.this.mCountDownTimer.start();
                        } else {
                            HideFWDialog hideFWDialog = new HideFWDialog(ElfinFloatView.this.getContext());
                            hideFWDialog.setOnHideOKClickListener(ElfinFloatView.this.onHideOKClickListener);
                            hideFWDialog.setOnHideCancelClickListener(ElfinFloatView.this.onHideCancelClickListener);
                            hideFWDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ds.daisi.floatview.ElfinFloatView.4.1
                                @Override // android.content.DialogInterface.OnCancelListener
                                public void onCancel(DialogInterface dialogInterface) {
                                    ElfinFloatView.this.moveToEdge();
                                }
                            });
                            hideFWDialog.show();
                        }
                        return false;
                    case 2:
                        if (ElfinFloatView.this.mLinearLayoutTools.getVisibility() == 0) {
                            return true;
                        }
                        ElfinFloatView.this.mLastX = ElfinFloatView.this.mRawCurrentX;
                        ElfinFloatView.this.mLastY = ElfinFloatView.this.mRawCurrentY;
                        ElfinFloatView.this.mRawCurrentX = motionEvent.getRawX();
                        ElfinFloatView.this.mRawCurrentY = motionEvent.getRawY() - AppDeviceUtils.getStatusBarHeight(ElfinFloatView.this.getContext());
                        if (getDistance(ElfinFloatView.this.mLastX, ElfinFloatView.this.mLastY, ElfinFloatView.this.mRawCurrentX, ElfinFloatView.this.mRawCurrentY) <= 5.0f) {
                            return true;
                        }
                        double degrees = Math.toDegrees(Math.atan2(ElfinFloatView.this.mRawCurrentY - ElfinFloatView.this.mLastY, ElfinFloatView.this.mRawCurrentX - ElfinFloatView.this.mLastX));
                        if (degrees < -60.0d && degrees > -120.0d) {
                            i = 1;
                        } else if (degrees >= -60.0d && degrees <= 60.0d) {
                            i = 2;
                        } else if (degrees > 60.0d && degrees < 120.0d) {
                            i = 3;
                        } else if (degrees >= 120.0d || degrees <= -120.0d) {
                            i = 4;
                        } else {
                            i = 1;
                        }
                        ElfinFloatView.this.updateFloatViewDirection(i);
                        ElfinFloatView.this.updateFloatViewPosition((int) (ElfinFloatView.this.mRawCurrentX - ElfinFloatView.this.mDownX), (int) (ElfinFloatView.this.mRawCurrentY - ElfinFloatView.this.mDownY));
                        if (ElfinFloatView.this.mDeleteFloatView.getVisibility() == 8) {
                            ElfinFloatView.this.mDeleteFloatView.setVisibility(0);
                        }
                        if (ElfinFloatView.this.isHide()) {
                            ElfinFloatView.this.mDeleteFloatView.setHide();
                        } else {
                            ElfinFloatView.this.mDeleteFloatView.setNormal();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        };
        this.onHideOKClickListener = new HideFWDialog.OnHideOKClickListener() { // from class: com.ds.daisi.floatview.ElfinFloatView.5
            @Override // com.ds.daisi.dialog.HideFWDialog.OnHideOKClickListener
            public void onClick(View view) {
                ElfinFloatView.this.toggleElfinVisbility();
            }
        };
        this.onHideCancelClickListener = new HideFWDialog.OnHideCancelClickListener() { // from class: com.ds.daisi.floatview.ElfinFloatView.6
            @Override // com.ds.daisi.dialog.HideFWDialog.OnHideCancelClickListener
            public void onClick(View view) {
                ElfinFloatView.this.moveToEdge();
            }
        };
        this.onCallback = new AppStatisticsPresenter.OnCallback() { // from class: com.ds.daisi.floatview.ElfinFloatView.7
            @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
            public void onCallbackSuc(Object obj) {
                ElfinFloatView.this.exitEngine();
            }
        };
        this.mAppContext = (AppContext) context;
        this.mRegisterCode = this.mAppContext.regCode;
        initView();
        initWindow();
        this.mDeleteFloatView = deleteFloatView;
        this.mCountDownTimer.start();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.floatview_elfin, this);
        this.mLinearLayoutElfin = (LinearLayout) findViewById(R.id.floatview_elfin_liearlayout_elfin);
        this.mLinearLayoutTools = (LinearLayout) findViewById(R.id.floatview_elfin_liearlayout_tools);
        this.mLinearLayoutRun = (LinearLayout) findViewById(R.id.floatview_linearlayout_run);
        this.mLinearLayoutInfo = (LinearLayout) findViewById(R.id.floatview_linearlayout_info);
        this.mLinearLayoutSetting = (LinearLayout) findViewById(R.id.floatview_linearlayout_setting);
        this.mTvRunScript = (TextView) findViewById(R.id.id_tv_run_and_pause);
        this.mImgRunAndPause = (ImageView) findViewById(R.id.floatview_imageview_run_and_pause);
        this.mTvSettingScript = (TextView) findViewById(R.id.id_tv_setting_and_stop);
        this.mImgSettingAndStop = (ImageView) findViewById(R.id.floatview_imageview_setting);
        this.mLinearLayoutRun.setOnClickListener(this);
        this.mLinearLayoutInfo.setOnClickListener(this);
        this.mLinearLayoutSetting.setOnClickListener(this);
        findViewById(R.id.floatview_linearlayout_exit).setOnClickListener(this);
        initElfinImageView();
    }

    private void initElfinImageView() {
        this.mImageViewElfin = (ImageView) findViewById(R.id.floatview_elfin_imageview_elfin);
        checkBtnState();
        this.mImageViewElfin.setOnClickListener(null);
        this.mImageViewElfin.setOnTouchListener(this.onTouchListener);
    }

    public void checkBtnState() {
        if (this.mAppContext.STATE_SCRIPT == 2) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_pause1);
            this.mTvRunScript.setText(getContext().getString(R.string.floatview_elfin_pause));
            this.mImgRunAndPause.setImageResource(R.drawable.floatview_elfin_pause);
            this.mTvSettingScript.setText(getContext().getString(R.string.floatview_elfin_stop));
            this.mImgSettingAndStop.setImageResource(R.drawable.floatview_elfin_stop);
            return;
        }
        if (this.mAppContext.STATE_SCRIPT == 3) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
            this.mTvRunScript.setText(getContext().getString(R.string.floatview_elfin_run));
            this.mImgRunAndPause.setImageResource(R.drawable.floatview_elfin_run);
            this.mTvSettingScript.setText(getContext().getString(R.string.floatview_elfin_stop));
            this.mImgSettingAndStop.setImageResource(R.drawable.floatview_elfin_stop);
            return;
        }
        this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
        this.mTvRunScript.setText(getContext().getString(R.string.floatview_elfin_run));
        this.mImgRunAndPause.setImageResource(R.drawable.floatview_elfin_run);
        this.mTvSettingScript.setText(getContext().getString(R.string.floatview_elfin_setting));
        this.mImgSettingAndStop.setImageResource(R.drawable.floatview_elfin_setting);
    }

    private void initWindow() {
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
        this.mWindowManagerParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mWindowManagerParams.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.mWindowManagerParams.type = 2005;
        } else {
            this.mWindowManagerParams.type = 2002;
        }
        this.mWindowManagerParams.format = 1;
        this.mWindowManagerParams.flags = 40;
        this.mWindowManagerParams.gravity = 51;
        this.mWindowManagerParams.x = AppDeviceUtils.getResolution(getContext()).x - this.mLinearLayoutElfin.getLayoutParams().width;
        this.mWindowManagerParams.y = AppDeviceUtils.getResolution(getContext()).y / 4;
        this.mRawCurrentY = AppDeviceUtils.getResolution(getContext()).y / 4;
        this.mWindowManagerParams.width = this.mLinearLayoutElfin.getLayoutParams().width;
        this.mWindowManagerParams.height = this.mLinearLayoutElfin.getLayoutParams().height;
    }

    @Override // android.view.View
    public WindowManager.LayoutParams getLayoutParams() {
        return this.mWindowManagerParams;
    }

    public void updateFloatViewPosition(int i, int i2) {
        this.mWindowManagerParams.x = i;
        this.mWindowManagerParams.y = i2;
        this.mWindowManager.updateViewLayout(this, this.mWindowManagerParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToEdge() {
        if (this.mDeleteFloatView.getVisibility() == 0) {
            this.mDeleteFloatView.setVisibility(8);
            this.mDeleteFloatView.setNormal();
        }
        int[] iArr = new int[2];
        this.mImageViewElfin.getLocationOnScreen(iArr);
        if (iArr[0] >= (AppDeviceUtils.getResolution(getContext()).x - this.mDownX) / 2.0f) {
            updateFloatViewPosition(AppDeviceUtils.getResolution(getContext()).x, (int) (this.mRawCurrentY - this.mDownY));
            this.mLinearLayoutElfin.removeView(this.mLinearLayoutTools);
            this.mLinearLayoutElfin.addView(this.mLinearLayoutTools, 0);
        } else {
            updateFloatViewPosition(0, (int) (this.mRawCurrentY - this.mDownY));
            this.mLinearLayoutElfin.removeView(this.mLinearLayoutTools);
            this.mLinearLayoutElfin.addView(this.mLinearLayoutTools, 1);
        }
        if (this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_run1);
        } else if (this.mAppContext.STATE_SCRIPT == 3) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_pause1);
        } else {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatViewDirection(int i) {
        switch (i) {
            case 1:
                if (this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_run1);
                } else if (this.mAppContext.STATE_SCRIPT == 3) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_pause1);
                } else {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
                }
                break;
            case 2:
                if (this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_run_right_up);
                } else if (this.mAppContext.STATE_SCRIPT == 3) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_pause_right_up);
                } else {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_normal_right_up);
                }
                break;
            case 3:
                if (this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_run_down);
                } else if (this.mAppContext.STATE_SCRIPT == 3) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_pause_down);
                } else {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_normal_down);
                }
                break;
            case 4:
                if (this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_run_left_up);
                } else if (this.mAppContext.STATE_SCRIPT == 3) {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_pause_left_up);
                } else {
                    this.mImageViewElfin.setImageResource(R.drawable.elfin_normal_left_up);
                }
                break;
        }
    }

    public void upadateElfinImages() {
        if (this.mAppContext.STATE_SCRIPT == 2) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_run1);
        } else if (this.mAppContext.STATE_SCRIPT == 3) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_pause1);
        } else {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
        }
        resetElfinState();
        this.mCountDownTimer.start();
    }

    public void changeToolBarState(boolean z) {
        updateFloatViewDirection(1);
        if (z) {
            if (this.mLinearLayoutTools.getVisibility() == 8) {
                this.mLinearLayoutTools.setVisibility(0);
            }
        } else if (this.mLinearLayoutTools.getVisibility() == 0) {
            this.mLinearLayoutTools.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetElfinState() {
        this.mCountDownTimer.cancel();
        this.mImageViewElfin.setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHide() {
        int[] iArr = new int[2];
        this.mImageViewElfin.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.mDeleteFloatView.getLocationOnScreen(iArr2);
        return iArr[0] > iArr2[0] && iArr[0] < iArr2[0] + this.mDeleteFloatView.geTextViewtWidth() && iArr[1] > iArr2[1];
    }

    public void rotateScreen() {
        resetElfinState();
        this.mCountDownTimer.start();
        updateFloatViewDirection(1);
        this.mWindowManagerParams.x = AppDeviceUtils.getResolution(getContext()).x - this.mLinearLayoutElfin.getLayoutParams().width;
        this.mWindowManagerParams.y = AppDeviceUtils.getResolution(getContext()).y / 4;
        this.mWindowManager.updateViewLayout(this, this.mWindowManagerParams);
        this.mDeleteFloatView.rotateScreen();
    }

    /* JADX WARN: Unreachable blocks removed: 3, instructions: 3 */
    private void runScriptOperate() {
        if (AppContext.getInstance().isForceUpdate) {
            ToastUtils.showToastShort(AppContext.getInstance(), R.string.version_too_low);
            return;
        }
        if (RedFingerHelper.get().isRunningDisable(AppContext.getInstance())) {
            ToastUtils.showToastShort(AppContext.getInstance(), R.string.environment_init_failure);
            return;
        }
        this.isPreventRepeatedClick = true;
        if (MqRunner.getInstance().isScriptStarted()) {
            MqRunner.getInstance().stop();
            this.isPreventRepeatedClick = false;
            SlToast.showToastLong(getContext(), "runScriptOperate -->\u3000停止引擎");
        } else if (!NetworkUtils.isAvailable(getContext())) {
            com.p024ds.daisi.util.ToastUtils.showToastLong(getContext(), getContext().getString(R.string.network_off));
            this.isPreventRepeatedClick = false;
            return;
        } else {
            if (TextUtils.isEmpty(AppContext.getInstance().regCode)) {
                AppContext.getInstance().regCode = this.mRegisterCode;
            }
            this.mAppContext.checkJarApp();
        }
        if (this.mHandler == null) {
            this.mHandler = new MyHandler();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable(this) { // from class: com.ds.daisi.floatview.ElfinFloatView$$Lambda$0
            private final ElfinFloatView arg$1;

            {
                this.arg$1 = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.lambda$runScriptOperate$0$ElfinFloatView();
            }
        }, 3000L);
    }

    /* JADX INFO: renamed from: com.ds.daisi.floatview.ElfinFloatView$2 */
    class C10082 implements AppStatisticsPresenter.OnClickScriptRunCallback {
        C10082() {
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackSuc(Object obj) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                ScriptStartRunResponse scriptStartRunResponse = (ScriptStartRunResponse) GsonExUtil.parsData(str, ScriptStartRunResponse.class);
                if (scriptStartRunResponse == null || scriptStartRunResponse.Code != 200) {
                    if (scriptStartRunResponse != null) {
                        ToastUtils.showToastLong(AppContext.getInstance(), scriptStartRunResponse.Message);
                    } else {
                        ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_parse_exception);
                    }
                } else if (ElfinFloatView.this.mAppContext.STATE_SCRIPT == 1) {
                    ElfinFloatView.this.updateVersionRequest(false);
                }
            } else {
                ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_data_null);
            }
            AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
            ElfinFloatView.this.isPreventRepeatedClick = false;
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackFail() {
            ElfinFloatView.this.isPreventRepeatedClick = false;
        }
    }

    final /* synthetic */ void lambda$runScriptOperate$0$ElfinFloatView() {
        this.isPreventRepeatedClick = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVersionRequest(boolean z) {
        ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "updateVersionRequest --> ");
        String scriptInfoJson = JsonUtils.parseScriptInfoJson(this.mContext, APPConstant.SCRIPT_INFO);
        String deviceName = CommonUtil.getDeviceName(this.mContext);
        UpdateRequestInfo updateRequestInfo = new UpdateRequestInfo();
        updateRequestInfo.ScriptId = AppContext.getInstance().mScript.getId();
        updateRequestInfo.DeviceName = deviceName;
        updateRequestInfo.ScriptVersion = Integer.parseInt(scriptInfoJson);
        updateRequestInfo.IsScriptHotUpgrade = 1;
        updateRequestInfo.AppVersion = CommonUtil.getAppVersion();
        SlLog.m1334i(TAG, "updateVersionRequest --> 1 ScriptId=" + updateRequestInfo.ScriptId + ",DeviceName=" + updateRequestInfo.DeviceName);
        VersionUpdateManager.builder().setDismissBack(new VersionUpdateManager.OnUpdateVersionBack() { // from class: com.ds.daisi.floatview.ElfinFloatView.3
            @Override // com.cyjh.share.mvp.manager.VersionUpdateManager.OnUpdateVersionBack
            public void onUpdateHas(VersionUpdateInfo versionUpdateInfo) {
                if (versionUpdateInfo == null) {
                    ElfinFloatView.this.mAppContext.checkJarApp();
                    return;
                }
                ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "updateVersionRequest onUpdateHas --> versionUpdateInfo.UpgradeMode=" + versionUpdateInfo.UpgradeMode);
                if (versionUpdateInfo.UpgradeMode != 2 || 5 != versionUpdateInfo.UpdateType) {
                    ElfinFloatView.this.mAppContext.checkJarApp();
                    return;
                }
                SpUtil.getInstance().setAppVersion(versionUpdateInfo.AppVersion);
                AppContext.getInstance().initElf();
                ScriptDownloadHelper.get().init(ElfinFloatView.this.mContext, ElfinFloatView.this.mAppContext.mScript.getId(), ScriptDownloadHelper.HOT_SCRIPT_CHECK_UPDATE_WHEN_START_RUN).downloadScriptOperate(versionUpdateInfo.UpdateUrl);
            }
        }).updateVersion(z, this.mContext, updateRequestInfo);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        resetElfinState();
        int id = view.getId();
        if (id != R.id.floatview_linearlayout_run) {
            if (id != R.id.floatview_linearlayout_setting) {
                if (id == R.id.floatview_linearlayout_info) {
                    this.mScriptRTDDialog = new ScriptRTDDialog(getContext());
                    this.mScriptRTDDialog.show();
                } else if (id == R.id.floatview_linearlayout_exit) {
                    scriptStopStatistics();
                }
            } else if (this.mAppContext.STATE_SCRIPT == 3 || this.mAppContext.STATE_SCRIPT == 2 || this.mAppContext.STATE_SCRIPT == 4) {
                SlLog.m1334i(TAG, "runScriptOperate --> 2");
                SlToast.showToastLong(getContext(), "runScriptOperate -->\u3000停止引擎");
                MqRunner.getInstance().stop();
                this.isPreventRepeatedClick = false;
                ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "ElfinFloatView runScriptOperate --> 停止引擎");
            } else {
                this.mScriptUIDialog = new ScriptUIDialog(getContext());
                this.mScriptUIDialog.mContext = this.mContext;
                this.mScriptUIDialog.show();
            }
        } else {
            if (this.mAppContext.STATE_SCRIPT == 4) {
                return;
            }
            if (this.mAppContext.STATE_SCRIPT == 3) {
                MqRunner.getInstance().resume();
            } else if (this.mAppContext.STATE_SCRIPT == 2) {
                MqRunner.getInstance().pause();
            } else {
                runScriptOperate();
            }
        }
        this.mCountDownTimer.start();
    }

    public void toggleElfinVisbility() {
        resetElfinState();
        if (getVisibility() == 0) {
            changeToolBarState(false);
            this.mDeleteFloatView.setVisibility(8);
            setVisibility(8);
        } else {
            this.mCountDownTimer.start();
            updateFloatViewPosition(AppDeviceUtils.getResolution(getContext()).x, AppDeviceUtils.getResolution(getContext()).y / 4);
            setVisibility(0);
        }
    }

    private class ElfinGestureListener implements GestureDetector.OnGestureListener {
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

        private ElfinGestureListener() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (ElfinFloatView.this.mDeleteFloatView.getVisibility() == 0) {
                ElfinFloatView.this.mDeleteFloatView.setVisibility(8);
            }
            if (ElfinFloatView.this.mLinearLayoutTools.getVisibility() == 0) {
                ElfinFloatView.this.changeToolBarState(false);
            } else {
                ElfinFloatView.this.changeToolBarState(true);
            }
            ElfinFloatView.this.mCountDownTimer.start();
            return true;
        }
    }

    public void posControlBar(float f, int i, int i2) {
        SlLog.m1334i(TAG, "posControlBar --> position=" + f + ",lockState=" + i + ",isRight=" + i2);
        this.mLockState = i;
        if (this.mLockState != 0) {
            this.mImageViewElfin.setOnTouchListener(null);
        }
        if (this.mDeleteFloatView.getVisibility() == 0) {
            this.mDeleteFloatView.setVisibility(8);
            this.mDeleteFloatView.setNormal();
        }
        updateFloatViewPosition(i2 == 0 ? 0 : AppDeviceUtils.getResolution(getContext()).x, (int) ((AppDeviceUtils.getResolution(getContext()).y * f) - getHeight()));
        this.mLinearLayoutElfin.removeView(this.mLinearLayoutTools);
        this.mLinearLayoutElfin.addView(this.mLinearLayoutTools, 0);
        if (this.mAppContext.STATE_SCRIPT == 2) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_run1);
        } else if (this.mAppContext.STATE_SCRIPT == 3) {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_pause1);
        } else {
            this.mImageViewElfin.setImageResource(R.drawable.elfin_normal1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        super.onDetachedFromWindow();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveElfinFloatViewEvent(ElfinFloatViewEvent elfinFloatViewEvent) {
        this.mGetEngineStatusTyrCount = 1;
        SlLog.m1334i(TAG, "onReceiveElfinFloatViewEvent --> isScriptStopRunning=" + AppContext.getInstance().isScriptStopRunning + ", mGetEngineStatusTyrCount=" + this.mGetEngineStatusTyrCount);
        ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "ElfinFloatView onReceiveElfinFloatViewEvent -->");
        judgeEngineStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeEngineStatus() {
        ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "ElfinFloatView judgeEngineStatus --> isScriptStopRunning=" + AppContext.getInstance().isScriptStopRunning);
        if (this.mGetEngineStatusTyrCount >= 5) {
            MqRunner.getInstance().stop();
            this.mGetEngineStatusTyrCount = 1;
            ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "ElfinFloatView judgeEngineStatus --> MqRunner.getInstance().stop()");
        }
        if (this.mHandler == null) {
            ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "ElfinFloatView judgeEngineStatus --> isScriptStopRunning=" + AppContext.getInstance().isScriptStopRunning + ", mHandler == null ");
            this.mHandler = new MyHandler();
        }
        if (AppContext.getInstance().isScriptStopRunning) {
            this.mHandler.removeMessages(1000);
            runScriptOperate();
        } else {
            this.mGetEngineStatusTyrCount++;
            if (this.mHandler.hasMessages(1000)) {
                this.mHandler.removeMessages(1000);
            }
            this.mHandler.sendEmptyMessageDelayed(1000, 3000L);
        }
    }

    private void scriptStopStatistics() {
        if (ParamsWrap.getParamsWrap().mStartRunResponseInfo == null) {
            exitEngine();
        } else {
            AppStatisticsPresenter.getStatisticsPresenter().setCallback(this.onCallback).loadScriptStopRunStatistics(AppContext.getInstance(), ParamsWrap.getParamsWrap().mStartRunResponseInfo.RunGuid);
            this.mHandler.sendEmptyMessageDelayed(1001, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitEngine() {
        this.mHandler.removeMessages(1001);
        if (this.mContext != null) {
            ((Activity) this.mContext).finish();
        }
        getContext().stopService(new Intent(getContext(), (Class<?>) VerifyService.class));
        getContext().stopService(new Intent(getContext(), (Class<?>) TimerService.class));
        getContext().stopService(new Intent(getContext(), (Class<?>) BootService.class));
        getContext().stopService(new Intent(getContext(), (Class<?>) PhoneStateService.class));
        MqBridge.exit();
        Process.killProcess(Process.myPid());
        EventBus.getDefault().unregister(this);
    }
}
