package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PhoneConfig implements Parcelable {
    public static final Parcelable.Creator<PhoneConfig> CREATOR = new Parcelable.Creator<PhoneConfig>() { // from class: com.cyjh.share.bean.response.PhoneConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhoneConfig createFromParcel(Parcel parcel) {
            return new PhoneConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhoneConfig[] newArray(int i) {
            return new PhoneConfig[i];
        }
    };
    private int Active;
    private int CloudPhoneType;
    private String DisableMsg;
    private String ImageUrl;
    private int JudgeMode;
    private String LinkUrl;
    private String Package;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PhoneConfig() {
    }

    protected PhoneConfig(Parcel parcel) {
        this.Active = parcel.readInt();
        this.DisableMsg = parcel.readString();
        this.Package = parcel.readString();
        this.JudgeMode = parcel.readInt();
        this.CloudPhoneType = parcel.readInt();
        this.ImageUrl = parcel.readString();
        this.LinkUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Active);
        parcel.writeString(this.DisableMsg);
        parcel.writeString(this.Package);
        parcel.writeInt(this.JudgeMode);
        parcel.writeInt(this.CloudPhoneType);
        parcel.writeString(this.ImageUrl);
        parcel.writeString(this.LinkUrl);
    }

    public int getActive() {
        return this.Active;
    }

    public void setActive(int i) {
        this.Active = i;
    }

    public String getDisableMsg() {
        return this.DisableMsg;
    }

    public void setDisableMsg(String str) {
        this.DisableMsg = str;
    }

    public String getPackage() {
        return this.Package;
    }

    public void setPackage(String str) {
        this.Package = str;
    }

    public int getJudgeMode() {
        return this.JudgeMode;
    }

    public void setJudgeMode(int i) {
        this.JudgeMode = i;
    }

    public int getCloudPhoneType() {
        return this.CloudPhoneType;
    }

    public void setCloudPhoneType(int i) {
        this.CloudPhoneType = i;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public void setImageUrl(String str) {
        this.ImageUrl = str;
    }

    public String getLinkUrl() {
        return this.LinkUrl;
    }

    public void setLinkUrl(String str) {
        this.LinkUrl = str;
    }

    public static Parcelable.Creator<PhoneConfig> getCREATOR() {
        return CREATOR;
    }
}
