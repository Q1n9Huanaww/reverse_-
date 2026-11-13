package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class AccountResult {
    public String Code;
    public String ExpireDate;
    public String Msg;
    public String TryCount;
    public String VerificationCode;

    public String toString() {
        return "AccountResult{Code='" + this.Code + "', Msg='" + this.Msg + "', VerificationCode='" + this.VerificationCode + "', ExpireDate='" + this.ExpireDate + "', TryCount='" + this.TryCount + "'}";
    }
}
