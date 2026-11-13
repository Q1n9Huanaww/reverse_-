package com.p024ds.daisi.zbar.decode;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.activity.SweepCodeActivity;
import com.p024ds.daisi.zbar.lib.PlanarYUVLuminanceSource;
import com.warnship.autoplay.R;
import com.zbar.lib.ZbarManager;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
final class DecodeHandler extends Handler {
    SweepCodeActivity activity;

    DecodeHandler(SweepCodeActivity sweepCodeActivity) {
        this.activity = null;
        this.activity = sweepCodeActivity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2);
        } else {
            if (i != R.id.quit) {
                return;
            }
            Looper.myLooper().quit();
        }
    }

    private void decode(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        String strDecode = new ZbarManager().decode(bArr2, i2, i, true, this.activity.getX(), this.activity.getY(), this.activity.getCropWidth(), this.activity.getCropHeight());
        if (strDecode != null) {
            if (this.activity.isNeedCapture()) {
                PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(bArr2, i2, i, this.activity.getX(), this.activity.getY(), this.activity.getCropWidth(), this.activity.getCropHeight(), false);
                int[] iArrRenderThumbnail = planarYUVLuminanceSource.renderThumbnail();
                int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrRenderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
                try {
                    String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Qrcode/";
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(str + "Qrcode.jpg");
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            if (this.activity.getHandler() != null) {
                Message message = new Message();
                message.obj = strDecode;
                message.what = R.id.decode_succeeded;
                this.activity.getHandler().sendMessage(message);
                return;
            }
            return;
        }
        if (this.activity.getHandler() != null) {
            this.activity.getHandler().sendEmptyMessage(R.id.decode_failed);
        }
    }
}
