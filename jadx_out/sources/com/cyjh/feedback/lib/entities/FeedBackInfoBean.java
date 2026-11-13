package com.cyjh.feedback.lib.entities;

/* JADX INFO: loaded from: classes.dex */
public class FeedBackInfoBean {
    private String AndoridData;
    private String BaseBandVersion;
    private String CoreVersion;
    private String DPI;
    private PicDataBean Data;
    private String FeedBack;
    private String GUID;
    private String IMEI;
    private String Model;
    private String Process;

    /* JADX INFO: renamed from: QQ */
    private String f940QQ;
    private String RAM;
    private String Resolution;
    private String RooStatus;
    private String SerialNumber;
    private String SystemVersion;
    private String TelNumber;

    public String getModel() {
        return this.Model;
    }

    public void setModel(String str) {
        this.Model = str;
    }

    public String getAndoridData() {
        return this.AndoridData;
    }

    public void setAndoridData(String str) {
        this.AndoridData = str;
    }

    public String getRooStatus() {
        return this.RooStatus;
    }

    public void setRooStatus(String str) {
        this.RooStatus = str;
    }

    public String getSystemVersion() {
        return this.SystemVersion;
    }

    public void setSystemVersion(String str) {
        this.SystemVersion = str;
    }

    public String getProcess() {
        return this.Process;
    }

    public void setProcess(String str) {
        this.Process = str;
    }

    public String getIMEI() {
        return this.IMEI;
    }

    public void setIMEI(String str) {
        this.IMEI = str;
    }

    public String getBaseBandVersion() {
        return this.BaseBandVersion;
    }

    public void setBaseBandVersion(String str) {
        this.BaseBandVersion = str;
    }

    public String getCoreVersion() {
        return this.CoreVersion;
    }

    public void setCoreVersion(String str) {
        this.CoreVersion = str;
    }

    public String getQQ() {
        return this.f940QQ;
    }

    public void setQQ(String str) {
        this.f940QQ = str;
    }

    public String getTelNumber() {
        return this.TelNumber;
    }

    public void setTelNumber(String str) {
        this.TelNumber = str;
    }

    public String getFeedBack() {
        return this.FeedBack;
    }

    public void setFeedBack(String str) {
        this.FeedBack = str;
    }

    public String getRAM() {
        return this.RAM;
    }

    public void setRAM(String str) {
        this.RAM = str;
    }

    public String getGUID() {
        return this.GUID;
    }

    public void setGUID(String str) {
        this.GUID = str;
    }

    public String getResolution() {
        return this.Resolution;
    }

    public void setResolution(String str) {
        this.Resolution = str;
    }

    public String getDPI() {
        return this.DPI;
    }

    public void setDPI(String str) {
        this.DPI = str;
    }

    public String getSerialNumber() {
        return this.SerialNumber;
    }

    public void setSerialNumber(String str) {
        this.SerialNumber = str;
    }

    public PicDataBean getData() {
        return this.Data;
    }

    public void setData(PicDataBean picDataBean) {
        this.Data = picDataBean;
    }

    public String toString() {
        return "FeedBackInfoBean{Model='" + this.Model + "', AndoridData='" + this.AndoridData + "', RooStatus='" + this.RooStatus + "', SystemVersion='" + this.SystemVersion + "', Process='" + this.Process + "', IMEI='" + this.IMEI + "', BaseBandVersion='" + this.BaseBandVersion + "', CoreVersion='" + this.CoreVersion + "', QQ='" + this.f940QQ + "', TelNumber='" + this.TelNumber + "', FeedBack='" + this.FeedBack + "', RAM='" + this.RAM + "', GUID='" + this.GUID + "', DPI='" + this.DPI + "', Resolution='" + this.Resolution + "', SerialNumber='" + this.SerialNumber + "', Data=" + this.Data + '}';
    }
}
