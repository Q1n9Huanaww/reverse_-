package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class MsgItem {
    public static final int CLEAR_ALL_MESSAGE = 1003;
    public static final int CLEAR_INTERFACE_MESSAGE = 1002;
    public static final int CLEAR_RED_DOT = 1001;
    public static final int GUIAD_APK_DOWNLOAD_SUC = 2011;
    public static final int MAINAD_APK_DOWNLOAD_SUC = 2012;
    public static final int NOTIFY_DIALOG_DISMISS_MESSAGE = 1006;
    public static final int REFRESH_SWITCH_SCRIPT_RUN = 1007;
    public static final int REFRESH_UI_MESSAGE = 1004;
    public static final int SCRIPT_NEED_UPGRADE = 1005;
    private String message;
    private int msgType;

    public MsgItem(int i) {
        this.msgType = i;
    }

    public MsgItem(int i, String str) {
        this.msgType = i;
        this.message = str;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public static class BindRegCodeBus {
        private long expireTime;

        public BindRegCodeBus(long j) {
            this.expireTime = j;
        }

        public long getExpireTime() {
            return this.expireTime;
        }
    }

    public static class UpdateMessageToDialog {
        private String message;

        public UpdateMessageToDialog(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static class WebPageDownButtonEvent {
        private int msgType;

        public WebPageDownButtonEvent(int i) {
            this.msgType = i;
        }

        public int getMsgType() {
            return this.msgType;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
