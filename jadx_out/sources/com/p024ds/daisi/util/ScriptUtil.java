package com.p024ds.daisi.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import com.cyjh.mobileanjian.ipc.uip.UipHelper;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.warnship.autoplay.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUtil {
    public static final String SCRIPT_VERSION = "script_version";
    public static final String TAG = "ScriptUtil";
    private static long mLastTime;

    public static boolean generateOptionJson(UipHelper uipHelper, File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            long length = file.length();
            if (!file.exists() || length <= 0) {
                return false;
            }
            SlLog.m1334i(TAG, "generateOptionJson --> ");
            uipHelper.saveToConfigFile(file2.getAbsolutePath());
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean parseOptionJson(Context context, UipHelper uipHelper, File file, File file2) {
        try {
            if (!file2.exists()) {
                return false;
            }
            long length = file.length();
            if (!file.exists() || length <= 0) {
                return false;
            }
            String textFile = FileUtils.readTextFile(file2);
            SlLog.m1334i(TAG, "parseOptionJson --> json=" + textFile);
            if (uipHelper == null) {
                uipHelper = new UipHelper(context);
            }
            uipHelper.configViewFromJson(textFile);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static String getSdcardPackageDir(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName();
    }

    public static String getAppAssetsDir(Context context) {
        try {
            return context.getFilesDir() + File.separator + Constants.SD_CARD_SCRIPT_ASSETS;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String getScriptUIPContent(Context context, boolean z) {
        if (z) {
            return FileUtil.readStringFromFile(FileUtil.getFileByAbsolutePath(getAppAssetsDir(context), APPConstant.SCRIPT_FILENAME_UIP), "GBK");
        }
        return FileUtil.readStringFromFile(FileUtil.getFileByAbsolutePath(context.getFilesDir().getAbsolutePath(), APPConstant.SCRIPT_FILENAME_UIP), "GBK");
    }

    public static String getScriptCFGContent(Context context) {
        return FileUtil.readStringFromFile(FileUtil.getFileByAbsolutePath(context.getFilesDir().getAbsolutePath(), APPConstant.SCRIPT_FILENAME_CFG));
    }

    public static String getScriptInfoJson(Context context, boolean z) {
        if (z) {
            return JsonUtils.parseScriptInfoJson(new File(getAppAssetsDir(context) + File.separator + APPConstant.SCRIPT_INFO));
        }
        return JsonUtils.parseScriptInfoJson(context, APPConstant.SCRIPT_INFO);
    }

    public static String getScriptId(Context context, boolean z) {
        String id;
        try {
            if (z) {
                id = JsonUtils.parPropJson(new FileInputStream(new File(getAppAssetsDir(context) + File.separator + APPConstant.SCRIPT_FILENAME_PROP))).getId();
            } else {
                id = JsonUtils.parPropJson(context.getAssets().open(APPConstant.SCRIPT_FILENAME_PROP)).getId();
            }
            return id;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static boolean isValidScriptId(Context context) {
        String scriptId = getScriptId(context, false);
        String scriptId2 = getScriptId(context, true);
        SlLog.m1334i(TAG, "localScriptId=" + scriptId + ",sdcardScriptId=" + scriptId2);
        return scriptId.equals(scriptId2);
    }

    public static int compareScriptVersion(Context context) {
        String scriptInfoJson = getScriptInfoJson(context, false);
        String scriptInfoJson2 = getScriptInfoJson(context, true);
        try {
            Long lValueOf = 0L;
            if (!TextUtils.isEmpty(scriptInfoJson)) {
                lValueOf = Long.valueOf(Long.parseLong(scriptInfoJson));
            }
            Long lValueOf2 = 0L;
            if (!TextUtils.isEmpty(scriptInfoJson2)) {
                lValueOf2 = Long.valueOf(Long.parseLong(scriptInfoJson2));
            }
            if (lValueOf2.longValue() > lValueOf.longValue()) {
                return 1;
            }
            return lValueOf2.longValue() < lValueOf.longValue() ? -1 : 0;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    public static Bitmap onScreenShotDone(int i, int i2, ByteString byteString) {
        Bitmap bitmapCreateBitmap;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            try {
                bitmapCreateBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(byteString.toByteArray()));
                bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            } catch (Exception e) {
                e = e;
                ThrowableExtension.printStackTrace(e);
            }
        } catch (Exception e2) {
            e = e2;
            bitmapCreateBitmap = null;
        }
        return bitmapCreateBitmap;
    }

    public static boolean isCanUploadInstance() {
        if ((System.currentTimeMillis() - mLastTime) / 1000 <= Constants.UPLOAD_INSTANCE_DATA_INTERVAL) {
            return false;
        }
        mLastTime = System.currentTimeMillis();
        return true;
    }

    public static boolean isValidAppId(Context context, boolean z) {
        String scriptAppId = getScriptAppId(context, z);
        SlLog.m1334i(TAG, "isValidAppId --> scriptAppId=" + scriptAppId);
        return context.getString(R.string.elfin_appid).equals(scriptAppId);
    }

    public static String getScriptAppId(Context context, boolean z) {
        String selId;
        try {
            if (z) {
                selId = JsonUtils.parPropJson(new FileInputStream(new File(getAppAssetsDir(context) + File.separator + APPConstant.SCRIPT_FILENAME_PROP))).getSelId();
            } else {
                selId = JsonUtils.parPropJson(context.getAssets().open(APPConstant.SCRIPT_FILENAME_PROP)).getSelId();
            }
            return selId;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}
