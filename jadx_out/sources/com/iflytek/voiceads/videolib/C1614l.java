package com.iflytek.voiceads.videolib;

import android.media.AudioManager;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.l */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1614l implements AudioManager.OnAudioFocusChangeListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JZPlayer f3523a;

    C1614l(JZPlayer jZPlayer) {
        this.f3523a = jZPlayer;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        switch (i) {
            case -2:
                try {
                    JZPlayer jZPlayerM3317c = C1617o.m3317c();
                    if (jZPlayerM3317c != null && jZPlayerM3317c.f3452g == 3) {
                        jZPlayerM3317c.f3458m.performClick();
                        break;
                    }
                } catch (Exception e) {
                    C1593g.m3190b("JZVideoPlayer", "audio focus" + e.getMessage());
                    return;
                }
                break;
            case -1:
                this.f3523a.m3227a();
                break;
        }
    }
}
