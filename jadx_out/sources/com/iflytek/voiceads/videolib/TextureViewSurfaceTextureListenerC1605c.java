package com.iflytek.voiceads.videolib;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.TextureView;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class TextureViewSurfaceTextureListenerC1605c implements TextureView.SurfaceTextureListener {

    /* JADX INFO: renamed from: a */
    public static JZTextureView f3499a;

    /* JADX INFO: renamed from: b */
    public static TextureViewSurfaceTextureListenerC1605c f3500b;

    /* JADX INFO: renamed from: c */
    public static SurfaceTexture f3501c;

    /* JADX INFO: renamed from: d */
    public static Surface f3502d;

    /* JADX INFO: renamed from: e */
    public AbstractC1604b f3503e;

    /* JADX INFO: renamed from: f */
    public int f3504f = -1;

    /* JADX INFO: renamed from: g */
    public int f3505g = 0;

    /* JADX INFO: renamed from: h */
    public int f3506h = 0;

    /* JADX INFO: renamed from: i */
    public HandlerThread f3507i = new HandlerThread("JZVideoPlayer");

    /* JADX INFO: renamed from: j */
    public a f3508j;

    /* JADX INFO: renamed from: k */
    public Handler f3509k;

    /* JADX INFO: renamed from: com.iflytek.voiceads.videolib.c$a */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    TextureViewSurfaceTextureListenerC1605c.this.f3505g = 0;
                    TextureViewSurfaceTextureListenerC1605c.this.f3506h = 0;
                    TextureViewSurfaceTextureListenerC1605c.this.f3503e.mo3293b();
                    if (TextureViewSurfaceTextureListenerC1605c.f3502d != null) {
                        TextureViewSurfaceTextureListenerC1605c.f3502d.release();
                    }
                    TextureViewSurfaceTextureListenerC1605c.f3502d = new Surface(TextureViewSurfaceTextureListenerC1605c.f3501c);
                    TextureViewSurfaceTextureListenerC1605c.this.f3503e.mo3291a(TextureViewSurfaceTextureListenerC1605c.f3502d);
                    break;
                case 2:
                    TextureViewSurfaceTextureListenerC1605c.this.f3503e.mo3295d();
                    break;
            }
        }
    }

    public TextureViewSurfaceTextureListenerC1605c() {
        this.f3507i.start();
        this.f3508j = new a(this.f3507i.getLooper());
        this.f3509k = new Handler();
        if (this.f3503e == null) {
            this.f3503e = new C1606d();
        }
    }

    /* JADX INFO: renamed from: a */
    public static TextureViewSurfaceTextureListenerC1605c m3298a() {
        if (f3500b == null) {
            f3500b = new TextureViewSurfaceTextureListenerC1605c();
        }
        return f3500b;
    }

    /* JADX INFO: renamed from: a */
    public static void m3299a(long j) {
        m3298a().f3503e.mo3290a(j);
    }

    /* JADX INFO: renamed from: a */
    public static void m3300a(C1603a c1603a) {
        m3298a().f3503e.f3498a = c1603a;
    }

    /* JADX INFO: renamed from: a */
    public static void m3301a(boolean z) {
        m3298a().f3503e.mo3292a(z);
    }

    /* JADX INFO: renamed from: b */
    public static Object m3302b() {
        if (m3298a().f3503e.f3498a == null) {
            return null;
        }
        return m3298a().f3503e.f3498a.m3286a();
    }

    /* JADX INFO: renamed from: c */
    public static long m3303c() {
        return m3298a().f3503e.mo3296e();
    }

    /* JADX INFO: renamed from: d */
    public static long m3304d() {
        return m3298a().f3503e.mo3297f();
    }

    /* JADX INFO: renamed from: e */
    public static void m3305e() {
        m3298a().f3503e.mo3294c();
    }

    /* JADX INFO: renamed from: f */
    public static void m3306f() {
        m3298a().f3503e.mo3289a();
    }

    /* JADX INFO: renamed from: g */
    public void m3307g() {
        Message message = new Message();
        message.what = 2;
        this.f3508j.sendMessage(message);
    }

    /* JADX INFO: renamed from: h */
    public void m3308h() {
        m3307g();
        Message message = new Message();
        message.what = 0;
        this.f3508j.sendMessage(message);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (C1617o.m3317c() == null) {
            return;
        }
        C1593g.m3188a("JZVideoPlayer", "onSurfaceTextureAvailable");
        try {
            if (f3501c == null) {
                f3501c = surfaceTexture;
                m3308h();
            } else {
                f3499a.setSurfaceTexture(f3501c);
            }
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "onSurfaceTextureAvailable:" + e.getMessage());
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return f3501c == null;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
