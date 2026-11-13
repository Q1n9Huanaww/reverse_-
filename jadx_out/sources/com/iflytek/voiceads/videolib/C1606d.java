package com.iflytek.voiceads.videolib;

import android.media.MediaPlayer;
import android.view.Surface;
import com.iflytek.voiceads.utils.C1593g;
import java.util.Map;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1606d extends AbstractC1604b implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {

    /* JADX INFO: renamed from: b */
    public MediaPlayer f3511b;

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: a */
    public void mo3289a() {
        if (this.f3511b != null) {
            try {
                this.f3511b.start();
            } catch (Exception e) {
                C1593g.m3190b("JZVideoPlayer", "media start" + e.getMessage());
            }
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: a */
    public void mo3290a(long j) {
        if (this.f3511b != null) {
            try {
                this.f3511b.seekTo((int) j);
            } catch (Exception e) {
                C1593g.m3190b("JZVideoPlayer", "media seek:" + e.getMessage());
            }
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: a */
    public void mo3291a(Surface surface) {
        if (this.f3511b != null) {
            this.f3511b.setSurface(surface);
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: a */
    public void mo3292a(boolean z) {
        if (this.f3511b != null) {
            if (z) {
                this.f3511b.setVolume(0.0f, 0.0f);
            } else {
                this.f3511b.setVolume(1.0f, 1.0f);
            }
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: b */
    public void mo3293b() {
        try {
            this.f3511b = new MediaPlayer();
            this.f3511b.setVolume(0.0f, 0.0f);
            this.f3511b.setAudioStreamType(3);
            this.f3511b.setLooping(this.f3498a.f3496f);
            this.f3511b.setOnPreparedListener(this);
            this.f3511b.setOnCompletionListener(this);
            this.f3511b.setOnBufferingUpdateListener(this);
            this.f3511b.setScreenOnWhilePlaying(true);
            this.f3511b.setOnSeekCompleteListener(this);
            this.f3511b.setOnErrorListener(this);
            this.f3511b.setOnInfoListener(this);
            this.f3511b.setOnVideoSizeChangedListener(this);
            MediaPlayer.class.getDeclaredMethod("setDataSource", String.class, Map.class).invoke(this.f3511b, this.f3498a.m3286a().toString(), this.f3498a.f3495e);
            this.f3511b.prepareAsync();
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "media prepare:" + e.getMessage());
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: c */
    public void mo3294c() {
        if (this.f3511b != null) {
            try {
                this.f3511b.pause();
            } catch (Exception e) {
                C1593g.m3190b("JZVideoPlayer", "media pause:" + e.getMessage());
            }
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: d */
    public void mo3295d() {
        if (this.f3511b != null) {
            this.f3511b.release();
        }
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: e */
    public long mo3296e() {
        if (this.f3511b != null) {
            return this.f3511b.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.iflytek.voiceads.videolib.AbstractC1604b
    /* JADX INFO: renamed from: f */
    public long mo3297f() {
        if (this.f3511b != null) {
            return this.f3511b.getDuration();
        }
        return 0L;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1609g(this, i));
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1608f(this));
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1611i(this, i, i2));
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1612j(this, i));
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        String lowerCase = this.f3498a.m3286a().toString().toLowerCase();
        if (lowerCase.contains("mp3") || lowerCase.contains("wav")) {
            TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1607e(this));
        }
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1610h(this));
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3505g = i;
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3506h = i2;
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3509k.post(new RunnableC1613k(this));
    }
}
