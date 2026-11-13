package com.iflytek.voiceads.utils;

import android.content.Context;
import android.media.MediaRecorder;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.m */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1599m {

    /* JADX INFO: renamed from: a */
    private Context f3424a;

    C1599m(Context context) {
        this.f3424a = context;
    }

    /* JADX INFO: renamed from: a */
    public boolean m3211a() throws Throwable {
        boolean z = true;
        File fileCreateTempFile = null;
        MediaRecorder mediaRecorder = new MediaRecorder();
        try {
            fileCreateTempFile = File.createTempFile("permission", InterfaceRelatedConstants.CHANNEL_ANJIAN_DEBUG);
            mediaRecorder.setAudioSource(1);
            mediaRecorder.setOutputFormat(3);
            mediaRecorder.setAudioEncoder(1);
            mediaRecorder.setOutputFile(fileCreateTempFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Throwable th) {
            z = this.f3424a.getPackageManager().hasSystemFeature("android.hardware.microphone") ? false : true;
        } finally {
            try {
                mediaRecorder.stop();
            } catch (Exception e) {
            }
            try {
                mediaRecorder.release();
            } catch (Exception e2) {
            }
            if (fileCreateTempFile != null && fileCreateTempFile.exists()) {
                fileCreateTempFile.delete();
            }
        }
        return z;
    }
}
