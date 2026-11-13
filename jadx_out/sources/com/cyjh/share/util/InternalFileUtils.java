package com.cyjh.share.util;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class InternalFileUtils {
    private static final int FAILED = 0;
    private static final int SUCCESS = 1;
    private static InternalFileUtils instance;
    private FileOperateCallback callback;
    private Context context;
    private String errorStr;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.share.util.InternalFileUtils.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (InternalFileUtils.this.callback != null) {
                if (message.what == 1) {
                    InternalFileUtils.this.callback.onSuccess();
                }
                if (message.what == 0) {
                    InternalFileUtils.this.callback.onFailed(message.obj.toString());
                }
            }
        }
    };
    private volatile boolean isSuccess;

    public interface FileOperateCallback {
        void onFailed(String str);

        void onSuccess();
    }

    public static InternalFileUtils getInstance(Context context) {
        if (instance == null) {
            instance = new InternalFileUtils(context);
        }
        return instance;
    }

    private InternalFileUtils(Context context) {
        this.context = context;
    }

    public InternalFileUtils copyAssetsToSD(final String str, final String str2) {
        new Thread(new Runnable() { // from class: com.cyjh.share.util.InternalFileUtils.2
            @Override // java.lang.Runnable
            public void run() {
                InternalFileUtils.this.copyAssetsToDst(InternalFileUtils.this.context, str, str2);
                if (InternalFileUtils.this.isSuccess) {
                    InternalFileUtils.this.handler.obtainMessage(1).sendToTarget();
                } else {
                    InternalFileUtils.this.handler.obtainMessage(0, InternalFileUtils.this.errorStr).sendToTarget();
                }
            }
        }).start();
        return this;
    }

    public void setFileOperateCallback(FileOperateCallback fileOperateCallback) {
        this.callback = fileOperateCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyAssetsToDst(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                File file = new File(Environment.getExternalStorageDirectory(), str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                for (String str3 : list) {
                    if (str.equals("")) {
                        copyAssetsToDst(context, str3, str2 + File.separator + str3);
                    } else {
                        copyAssetsToDst(context, str + File.separator + str3, str2 + File.separator + str3);
                    }
                }
            } else {
                File file2 = new File(Environment.getExternalStorageDirectory(), str2);
                InputStream inputStreamOpen = context.getAssets().open(str);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStreamOpen.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.flush();
                inputStreamOpen.close();
                fileOutputStream.close();
            }
            this.isSuccess = true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.errorStr = e.getMessage();
            this.isSuccess = false;
        }
    }
}
