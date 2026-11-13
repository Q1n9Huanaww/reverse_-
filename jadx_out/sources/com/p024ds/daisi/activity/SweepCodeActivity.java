package com.p024ds.daisi.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.util.ToastUtils;
import com.p024ds.daisi.zbar.camera.CameraManager;
import com.p024ds.daisi.zbar.decode.CaptureActivityHandler;
import com.p024ds.daisi.zbar.decode.InactivityTimer;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SweepCodeActivity extends BaseActivity implements SurfaceHolder.Callback {
    private static final float BEEP_VOLUME = 0.5f;
    public static final String SWEEP_RESULT_BACK = "SWEEP_RESULT_BACK";
    private static final long VIBRATE_DURATION = 200;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private ImageView mQrLineView;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private boolean vibrate;
    private RelativeLayout mContainer = null;
    private RelativeLayout mCropLayout = null;
    private boolean isNeedCapture = false;

    /* JADX INFO: renamed from: x */
    private int f1698x = 0;

    /* JADX INFO: renamed from: y */
    private int f1699y = 0;
    private int cropWidth = 0;
    private int cropHeight = 0;
    boolean flag = true;
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.ds.daisi.activity.SweepCodeActivity.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public boolean isNeedCapture() {
        return this.isNeedCapture;
    }

    public void setNeedCapture(boolean z) {
        this.isNeedCapture = z;
    }

    public int getX() {
        return this.f1698x;
    }

    public void setX(int i) {
        this.f1698x = i;
    }

    public int getY() {
        return this.f1699y;
    }

    public void setY(int i) {
        this.f1699y = i;
    }

    public int getCropWidth() {
        return this.cropWidth;
    }

    public void setCropWidth(int i) {
        this.cropWidth = i;
    }

    public int getCropHeight() {
        return this.cropHeight;
    }

    public void setCropHeight(int i) {
        this.cropHeight = i;
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sweep_code);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 0);
        } else {
            initViewTitle();
            initView();
        }
    }

    private void initViewTitle() {
        TitleView titleView = (TitleView) findViewById(R.id.id_title);
        titleView.setTitleText(getString(R.string.sweep_code_register_code));
        titleView.setVisibilityRightImage(4);
        titleView.setleftImage(R.drawable.ic_back);
        titleView.setOnLeftImageViewListener(new OnLeftImageViewOnClicker());
    }

    private class OnLeftImageViewOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            SweepCodeActivity.this.finish();
        }
    }

    private void initView() {
        CameraManager.init(this);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.mContainer = (RelativeLayout) findViewById(R.id.capture_containter);
        this.mCropLayout = (RelativeLayout) findViewById(R.id.capture_crop_layout);
        int i = getResources().getDisplayMetrics().widthPixels;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCropLayout.getLayoutParams();
        int i2 = (i * 2) / 3;
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.mCropLayout.setLayoutParams(layoutParams);
        this.mQrLineView = (ImageView) findViewById(R.id.capture_scan_line);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 0.0f, 2, 0.9f);
        translateAnimation.setDuration(1500L);
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setRepeatMode(2);
        translateAnimation.setInterpolator(new LinearInterpolator());
        this.mQrLineView.setAnimation(translateAnimation);
        ((LinearLayout) findViewById(R.id.linear_open_light)).setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.activity.SweepCodeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SweepCodeActivity.this.light();
            }
        });
    }

    protected void light() {
        if (this.flag) {
            this.flag = false;
            CameraManager.get().openLight();
        } else {
            this.flag = true;
            CameraManager.get().offLight();
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.capture_preview)).getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.playBeep = true;
        if (((AudioManager) getSystemService("audio")).getRingerMode() != 2) {
            this.playBeep = false;
        }
        initBeepSound();
        this.vibrate = true;
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
        sweepCodeDestroy();
    }

    private void sweepCodeDestroy() {
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void handleDecode(String str) {
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        sweepCodeDestroy();
        this.mQrLineView.clearAnimation();
        if (str != null) {
            Intent intent = new Intent();
            intent.putExtra(SWEEP_RESULT_BACK, str);
            setResult(-1, intent);
            finish();
            return;
        }
        ToastUtils.showToastLong(this, R.string.sweep_error_tips);
    }

    private void playBeepSoundAndVibrate() {
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private void initBeepSound() {
        if (this.playBeep && this.mediaPlayer == null) {
            setVolumeControlStream(3);
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = getResources().openRawResourceFd(R.raw.beep);
            try {
                this.mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                this.mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                this.mediaPlayer = null;
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            Point cameraResolution = CameraManager.get().getCameraResolution();
            int i = cameraResolution.y;
            int i2 = cameraResolution.x;
            int left = (this.mCropLayout.getLeft() * i) / this.mContainer.getWidth();
            int top = (this.mCropLayout.getTop() * i2) / this.mContainer.getHeight();
            int width = (this.mCropLayout.getWidth() * i) / this.mContainer.getWidth();
            int height = (this.mCropLayout.getHeight() * i2) / this.mContainer.getHeight();
            setX(left);
            setY(top);
            setCropWidth(width);
            setCropHeight(height);
            setNeedCapture(true);
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this);
            }
        } catch (IOException unused) {
        } catch (RuntimeException unused2) {
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    public Handler getHandler() {
        return this.handler;
    }

    private void onResultHandler(String str, Bitmap bitmap) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "Scan failed!", 0).show();
        }
    }
}
