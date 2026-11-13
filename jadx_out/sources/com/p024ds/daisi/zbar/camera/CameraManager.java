package com.p024ds.daisi.zbar.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.p024ds.daisi.constant.Constants;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class CameraManager {
    static final int SDK_INT;
    private static CameraManager cameraManager;
    private final AutoFocusCallback autoFocusCallback;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private boolean initialized;
    private Camera.Parameters parameter;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private final boolean useOneShotPreviewCallback;

    static {
        int i;
        try {
            i = Build.VERSION.SDK_INT;
        } catch (NumberFormatException unused) {
            i = Constants.REQUEST_CODE_APP_INSTALL;
        }
        SDK_INT = i;
    }

    public static void init(Context context) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context);
        }
    }

    public static CameraManager get() {
        return cameraManager;
    }

    private CameraManager(Context context) {
        this.configManager = new CameraConfigurationManager(context);
        this.useOneShotPreviewCallback = SDK_INT > 3;
        this.previewCallback = new PreviewCallback(this.configManager, this.useOneShotPreviewCallback);
        this.autoFocusCallback = new AutoFocusCallback();
    }

    public void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        if (this.camera == null) {
            this.camera = Camera.open();
            if (this.camera == null) {
                throw new IOException();
            }
            this.camera.setPreviewDisplay(surfaceHolder);
            if (!this.initialized) {
                this.initialized = true;
                this.configManager.initFromCameraParameters(this.camera);
            }
            this.configManager.setDesiredCameraParameters(this.camera);
            FlashlightManager.enableFlashlight();
        }
    }

    public Point getCameraResolution() {
        return this.configManager.getCameraResolution();
    }

    public void closeDriver() {
        if (this.camera != null) {
            FlashlightManager.disableFlashlight();
            this.camera.release();
            this.camera = null;
        }
    }

    public void startPreview() {
        if (this.camera == null || this.previewing) {
            return;
        }
        this.camera.startPreview();
        this.previewing = true;
    }

    public void stopPreview() {
        if (this.camera == null || !this.previewing) {
            return;
        }
        if (!this.useOneShotPreviewCallback) {
            this.camera.setPreviewCallback(null);
        }
        this.camera.stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.autoFocusCallback.setHandler(null, 0);
        this.previewing = false;
    }

    public void requestPreviewFrame(Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.previewCallback.setHandler(handler, i);
        if (this.useOneShotPreviewCallback) {
            this.camera.setOneShotPreviewCallback(this.previewCallback);
        } else {
            this.camera.setPreviewCallback(this.previewCallback);
        }
    }

    public void requestAutoFocus(Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.autoFocusCallback.setHandler(handler, i);
        this.camera.autoFocus(this.autoFocusCallback);
    }

    public void openLight() {
        Log.e("zzz", "openLight1");
        if (this.camera != null) {
            Log.e("zzz", "openLight2");
            this.parameter = this.camera.getParameters();
            this.parameter.setFlashMode("torch");
            this.camera.setParameters(this.parameter);
        }
    }

    public void offLight() {
        if (this.camera != null) {
            this.parameter = this.camera.getParameters();
            this.parameter.setFlashMode("off");
            this.camera.setParameters(this.parameter);
        }
    }
}
