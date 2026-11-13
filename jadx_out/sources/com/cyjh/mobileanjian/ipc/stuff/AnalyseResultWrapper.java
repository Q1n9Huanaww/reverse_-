package com.cyjh.mobileanjian.ipc.stuff;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.goldcoast.sdk.domain.AnalyseResult;

/* JADX INFO: loaded from: classes.dex */
public class AnalyseResultWrapper {

    /* JADX INFO: renamed from: a */
    private static Context f1157a;

    /* JADX INFO: renamed from: b */
    private static Handler f1158b = new Handler() { // from class: com.cyjh.mobileanjian.ipc.stuff.AnalyseResultWrapper.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            String string = message.getData().getString("result");
            int i = message.getData().getInt("status");
            Log.i("MyApp", string);
            if (i == 1) {
                Toast.makeText(AnalyseResultWrapper.f1157a, string, 0).show();
            }
        }
    };

    public static AnalyseResult resultBuilder(Context context) {
        f1157a = context;
        return new AnalyseResult() { // from class: com.cyjh.mobileanjian.ipc.stuff.AnalyseResultWrapper.2
            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onSuccess(String str) {
                AnalyseResultWrapper.m804a(str, 0);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onFailed(String str) {
                AnalyseResultWrapper.m804a(str, 1);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onProgress(String str) {
                AnalyseResultWrapper.m804a(str, 2);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onException(String str) {
                AnalyseResultWrapper.m804a(str, 3);
            }
        };
    }

    public static AnalyseResult resultBuilder(Context context, Handler handler) {
        f1157a = context;
        f1158b = null;
        f1158b = handler;
        return resultBuilder(context);
    }

    /* JADX INFO: renamed from: b */
    private static void m805b(String str, int i) {
        if (f1158b == null) {
            return;
        }
        Message messageObtainMessage = f1158b.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("result", str);
        bundle.putInt("status", i);
        messageObtainMessage.setData(bundle);
        f1158b.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m804a(String str, int i) {
        if (f1158b != null) {
            Message messageObtainMessage = f1158b.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("result", str);
            bundle.putInt("status", i);
            messageObtainMessage.setData(bundle);
            f1158b.sendMessage(messageObtainMessage);
        }
    }
}
