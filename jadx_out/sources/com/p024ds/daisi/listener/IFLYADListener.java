package com.p024ds.daisi.listener;

import com.iflytek.voiceads.listener.IFLYNativeListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class IFLYADListener implements IFLYNativeListener {
    public static final int AD_DOWNLOAD = 3;
    public static final int AD_REDIRECT = 2;
    public static final String IFLYAD_BOTTOM_SCREEN_KEY = "5FF8BF855D9553F32AA0CD1DB70FEDC6";
    public static final String IFLYAD_FULLSCREEN_KEY = "21462E427898ADCCC2171718D603B0DE";
    public static final String IFLYAD_FULLSCREEN_KEY_TWO = "761D5F3C12409F07DCE571A8AA3C0480";
    public static final String IFLYAD_FULLSCREEN_VIDEO = "3CCD2FB15632CD8044CD37D46DE41E3C";
    public static final String IFLYAD_PART_SCREEN_KEY = "410D8AC18806EDFE511A00EFD3329F20";

    @Override // com.iflytek.voiceads.listener.DialogListener
    public void onCancel() {
    }

    @Override // com.iflytek.voiceads.listener.DialogListener
    public void onConfirm() {
    }
}
