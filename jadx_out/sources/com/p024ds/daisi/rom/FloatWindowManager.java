package com.p024ds.daisi.rom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import com.p024ds.daisi.dialog.FloatGuideDialog;

/* JADX INFO: loaded from: classes.dex */
public class FloatWindowManager {
    private static final String TAG = "FloatWindowManager";
    private static volatile FloatWindowManager instance;
    private FloatGuideDialog dialog;
    private boolean isWindowDismiss = true;
    private WindowManager windowManager = null;
    private WindowManager.LayoutParams mParams = null;

    private interface OnConfirmResult {
        void confirmResult(boolean z);
    }

    public static FloatWindowManager getInstance() {
        if (instance == null) {
            synchronized (FloatWindowManager.class) {
                if (instance == null) {
                    instance = new FloatWindowManager();
                }
            }
        }
        return instance;
    }

    public void applyOrShowFloatWindow(Context context) {
        if (checkPermission(context)) {
            return;
        }
        applyPermission(context);
    }

    public boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return miuiPermissionCheck(context);
            }
            if (RomUtils.checkIsMeizuRom()) {
                return meizuPermissionCheck(context);
            }
            if (RomUtils.checkIsHuaweiRom()) {
                return huaweiPermissionCheck(context);
            }
            if (RomUtils.checkIs360Rom()) {
                return qikuPermissionCheck(context);
            }
            if (RomUtils.checkIsOppoRom()) {
                return oppoROMPermissionCheck(context);
            }
        }
        return commonROMPermissionCheck(context);
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private boolean oppoROMPermissionCheck(Context context) {
        return OppoUtils.checkFloatWindowPermission(context);
    }

    private boolean commonROMPermissionCheck(Context context) {
        Boolean bool;
        if (RomUtils.checkIsMeizuRom()) {
            return meizuPermissionCheck(context);
        }
        if (Build.VERSION.SDK_INT < 23) {
            bool = true;
        } else {
            try {
                bool = (Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context);
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
                bool = true;
            }
        }
        return bool.booleanValue();
    }

    public void applyPermission(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                miuiROMPermissionApply(context);
                return;
            }
            if (RomUtils.checkIsMeizuRom()) {
                meizuROMPermissionApply(context);
                return;
            }
            if (RomUtils.checkIsHuaweiRom()) {
                huaweiROMPermissionApply(context);
                return;
            } else if (RomUtils.checkIs360Rom()) {
                ROM360PermissionApply(context);
                return;
            } else {
                if (RomUtils.checkIsOppoRom()) {
                    oppoROMPermissionApply(context);
                    return;
                }
                return;
            }
        }
        commonROMPermissionApply(context);
    }

    private void ROM360PermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.1
            @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    QikuUtils.applyPermission(context);
                } else {
                    Log.e(FloatWindowManager.TAG, "ROM:360, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void huaweiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.2
            @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    HuaweiUtils.applyPermission(context);
                } else {
                    Log.e(FloatWindowManager.TAG, "ROM:huawei, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void meizuROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.3
            @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    MeizuUtils.applyPermission(context);
                } else {
                    Log.e(FloatWindowManager.TAG, "ROM:meizu, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void miuiROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.4
            @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
            public void confirmResult(boolean z) throws Throwable {
                if (z) {
                    MiuiUtils.applyMiuiPermission(context);
                } else {
                    Log.e(FloatWindowManager.TAG, "ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void oppoROMPermissionApply(final Context context) {
        showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.5
            @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
            public void confirmResult(boolean z) {
                if (z) {
                    OppoUtils.applyOppoPermission(context);
                } else {
                    Log.e(FloatWindowManager.TAG, "ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }
            }
        });
    }

    private void commonROMPermissionApply(final Context context) {
        if (RomUtils.checkIsMeizuRom()) {
            meizuROMPermissionApply(context);
        } else if (Build.VERSION.SDK_INT >= 23) {
            showConfirmDialog(context, new OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.6
                @Override // com.ds.daisi.rom.FloatWindowManager.OnConfirmResult
                public void confirmResult(boolean z) {
                    if (z) {
                        try {
                            FloatWindowManager.commonROMPermissionApplyInternal(context);
                            return;
                        } catch (Exception e) {
                            Log.e(FloatWindowManager.TAG, Log.getStackTraceString(e));
                            return;
                        }
                    }
                    Log.d(FloatWindowManager.TAG, "user manually refuse OVERLAY_PERMISSION");
                }
            });
        }
    }

    public static void commonROMPermissionApplyInternal(Context context) throws IllegalAccessException, NoSuchFieldException {
        Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
        intent.setFlags(268435456);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    private void showConfirmDialog(Context context, OnConfirmResult onConfirmResult) {
        showConfirmDialog(context, "您的手机没有授予悬浮窗权限，请开启后再试", onConfirmResult);
    }

    private void showConfirmDialog(Context context, String str, final OnConfirmResult onConfirmResult) {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        this.dialog = new FloatGuideDialog(context);
        this.dialog.setConfirmResult(new FloatGuideDialog.OnConfirmResult() { // from class: com.ds.daisi.rom.FloatWindowManager.7
            @Override // com.ds.daisi.dialog.FloatGuideDialog.OnConfirmResult
            public void confirmResult(boolean z) {
                onConfirmResult.confirmResult(z);
                FloatWindowManager.this.dialog.dismiss();
            }
        });
        this.dialog.show();
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
