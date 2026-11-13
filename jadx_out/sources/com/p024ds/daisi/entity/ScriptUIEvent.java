package com.p024ds.daisi.entity;

import com.p024ds.daisi.util.ScriptDownloadHelper;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUIEvent {
    int type;

    public ScriptUIEvent(int i) {
        this.type = ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_NEXT_TIME;
        this.type = i;
    }

    public ScriptUIEvent() {
        this.type = ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_NEXT_TIME;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
