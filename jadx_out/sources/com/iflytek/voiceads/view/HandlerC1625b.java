package com.iflytek.voiceads.view;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1587a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class HandlerC1625b extends Handler {

    /* JADX INFO: renamed from: a */
    private C1587a.c f3563a;

    /* JADX INFO: renamed from: b */
    private a f3564b;

    /* JADX INFO: renamed from: com.iflytek.voiceads.view.b$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo3333a();

        /* JADX INFO: renamed from: a */
        void mo3336a(Message message);

        /* JADX INFO: renamed from: b */
        void mo3341b();

        /* JADX INFO: renamed from: b */
        void mo3342b(Message message);

        /* JADX INFO: renamed from: c */
        void mo3343c();

        /* JADX INFO: renamed from: c */
        void mo3344c(Message message);
    }

    /* JADX INFO: renamed from: com.iflytek.voiceads.view.b$b */
    public enum b {
        max,
        normal,
        min
    }

    public HandlerC1625b(Looper looper, a aVar) {
        super(looper);
        this.f3563a = C1587a.c.init;
        this.f3564b = aVar;
    }

    /* JADX INFO: renamed from: a */
    public synchronized C1587a.c m3361a() {
        return this.f3563a;
    }

    /* JADX INFO: renamed from: a */
    public void m3362a(int i) {
        m3365a(obtainMessage(i), b.normal, 0);
    }

    /* JADX INFO: renamed from: a */
    public void m3363a(int i, int i2) {
        m3365a(obtainMessage(i, Integer.valueOf(i2)), b.normal, 0);
    }

    /* JADX INFO: renamed from: a */
    public void m3364a(Message message, int i) {
        m3365a(message, b.normal, i);
    }

    /* JADX INFO: renamed from: a */
    protected void m3365a(Message message, b bVar, int i) {
        if (m3361a() == C1587a.c.exit) {
            C1593g.m3188a(SDKConstants.TAG, "Ad status is exit, invalid request!");
            return;
        }
        switch (message.what) {
            case 0:
                m3366a(C1587a.c.init);
                break;
            case 1:
                m3366a(C1587a.c.request);
                break;
            case 2:
                m3366a(C1587a.c.response);
                break;
            case 3:
                m3366a(C1587a.c.show);
                break;
            case 5:
                m3366a(C1587a.c.end);
                break;
            case 7:
                m3366a(C1587a.c.exit);
                break;
        }
        if (b.max == bVar) {
            sendMessageAtFrontOfQueue(message);
        } else {
            sendMessageDelayed(message, i);
        }
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m3366a(C1587a.c cVar) {
        C1593g.m3188a(SDKConstants.TAG, "setStatus: pre=" + this.f3563a + ", cur=" + cVar);
        if (this.f3563a == C1587a.c.exit) {
            C1593g.m3188a(SDKConstants.TAG, "Invalid setting of ad status, current status is already exit!");
        } else {
            this.f3563a = cVar;
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case 1:
                    this.f3564b.mo3336a(message);
                    break;
                case 2:
                    this.f3564b.mo3333a();
                    break;
                case 3:
                    this.f3564b.mo3341b();
                    break;
                case 4:
                    this.f3564b.mo3342b(message);
                    break;
                case 5:
                    this.f3564b.mo3344c(message);
                    break;
                case 7:
                    this.f3564b.mo3343c();
                    break;
            }
        } catch (Exception e) {
            m3366a(C1587a.c.end);
            C1593g.m3190b(SDKConstants.TAG, "process handle:" + e.getMessage());
        }
    }
}
