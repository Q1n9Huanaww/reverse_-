package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DomainNameRequestParamsInfo extends BaseRequestValueInfo {
    public static final Parcelable.Creator<DomainNameRequestParamsInfo> CREATOR = new Parcelable.Creator<DomainNameRequestParamsInfo>() { // from class: com.cyjh.share.bean.request.DomainNameRequestParamsInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainNameRequestParamsInfo createFromParcel(Parcel parcel) {
            return new DomainNameRequestParamsInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainNameRequestParamsInfo[] newArray(int i) {
            return new DomainNameRequestParamsInfo[i];
        }
    };
    private final int ANDROID_DEVICE;
    public int DeviceType;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.DeviceType);
    }

    public DomainNameRequestParamsInfo(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
        this.ANDROID_DEVICE = 1;
        this.DeviceType = 1;
    }

    protected DomainNameRequestParamsInfo(Parcel parcel) {
        super(parcel);
        this.ANDROID_DEVICE = 1;
        this.DeviceType = 1;
        this.DeviceType = parcel.readInt();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "DomainNameRequestParamsInfo{DeviceType=" + this.DeviceType + ", TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + '}';
    }

    public int getDeviceType() {
        return this.DeviceType;
    }

    public void setDeviceType(int i) {
        this.DeviceType = i;
    }
}
