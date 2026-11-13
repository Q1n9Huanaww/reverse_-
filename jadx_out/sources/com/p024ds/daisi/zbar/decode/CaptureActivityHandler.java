package com.p024ds.daisi.zbar.decode;

import android.os.Handler;
import android.os.Message;
import com.p024ds.daisi.activity.SweepCodeActivity;
import com.p024ds.daisi.zbar.camera.CameraManager;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public final class CaptureActivityHandler extends Handler {
    SweepCodeActivity activity;
    DecodeThread decodeThread;
    private State state;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(SweepCodeActivity sweepCodeActivity) {
        this.decodeThread = null;
        this.activity = null;
        this.activity = sweepCodeActivity;
        this.decodeThread = new DecodeThread(sweepCodeActivity);
        this.decodeThread.start();
        this.state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case R.id.auto_focus /* 2131755012 */:
                if (this.state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
                    return;
                }
                return;
            case R.id.decode_failed /* 2131755014 */:
                break;
            case R.id.decode_succeeded /* 2131755015 */:
                this.state = State.SUCCESS;
                this.activity.handleDecode((String) message.obj);
                break;
            case R.id.restart_preview /* 2131755029 */:
                restartPreviewAndDecode();
                return;
            default:
                return;
        }
        this.state = State.PREVIEW;
        CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        CameraManager.get().stopPreview();
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
        removeMessages(R.id.decode);
        removeMessages(R.id.auto_focus);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
        }
    }
}
