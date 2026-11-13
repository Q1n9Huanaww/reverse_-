package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DomainResult implements Parcelable {
    public static final Parcelable.Creator<DomainResult> CREATOR = new Parcelable.Creator<DomainResult>() { // from class: com.cyjh.share.bean.response.DomainResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainResult createFromParcel(Parcel parcel) {
            return new DomainResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainResult[] newArray(int i) {
            return new DomainResult[i];
        }
    };
    private String App2DomainName;
    private String App3DomainName;
    private String AppDomainName;
    private List<PhoneConfig> PhoneConfig;
    private int RedFingerSwitch;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DomainResult() {
    }

    protected DomainResult(Parcel parcel) {
        this.AppDomainName = parcel.readString();
        this.App2DomainName = parcel.readString();
        this.App3DomainName = parcel.readString();
        this.RedFingerSwitch = parcel.readInt();
        this.PhoneConfig = parcel.createTypedArrayList(PhoneConfig.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.AppDomainName);
        parcel.writeString(this.App2DomainName);
        parcel.writeString(this.App3DomainName);
        parcel.writeInt(this.RedFingerSwitch);
        parcel.writeTypedList(this.PhoneConfig);
    }

    public String getAppDomainName() {
        return this.AppDomainName;
    }

    public void setAppDomainName(String str) {
        this.AppDomainName = str;
    }

    public String getApp2DomainName() {
        return this.App2DomainName;
    }

    public void setApp2DomainName(String str) {
        this.App2DomainName = str;
    }

    public int getRedFingerSwitch() {
        return this.RedFingerSwitch;
    }

    public void setRedFingerSwitch(int i) {
        this.RedFingerSwitch = i;
    }

    public List<PhoneConfig> getPhoneConfig() {
        return this.PhoneConfig;
    }

    public void setPhoneConfig(List<PhoneConfig> list) {
        this.PhoneConfig = list;
    }

    public static Parcelable.Creator<DomainResult> getCREATOR() {
        return CREATOR;
    }

    public String getApp3DomainName() {
        return this.App3DomainName;
    }

    public void setApp3DomainName(String str) {
        this.App3DomainName = str;
    }
}
