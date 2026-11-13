package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class TokenBean {
    public TokenInfo CheckInfo;
    public String RCode;
    public String Sign;
    private boolean checkSign;

    public class TokenInfo {
        public String ExpireDate;
        public String Info;
        public String Result;

        public TokenInfo() {
        }

        public String toString() {
            return "TokenInfo{Result='" + this.Result + "', Info='" + this.Info + "', ExpireDate='" + this.ExpireDate + "'}";
        }
    }

    public boolean getCheckSign() {
        return this.checkSign;
    }

    public void setCheckSign(boolean z) {
        this.checkSign = z;
    }
}
