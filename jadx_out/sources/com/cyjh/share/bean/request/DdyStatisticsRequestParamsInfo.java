package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DdyStatisticsRequestParamsInfo extends BaseRequestValueInfo {
    public static final Parcelable.Creator<DdyStatisticsRequestParamsInfo> CREATOR = new Parcelable.Creator<DdyStatisticsRequestParamsInfo>() { // from class: com.cyjh.share.bean.request.DdyStatisticsRequestParamsInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyStatisticsRequestParamsInfo createFromParcel(Parcel parcel) {
            return new DdyStatisticsRequestParamsInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DdyStatisticsRequestParamsInfo[] newArray(int i) {
            return new DdyStatisticsRequestParamsInfo[i];
        }
    };
    public int IsChargeStatistics;
    public int IsDDYun;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.IsDDYun);
        parcel.writeInt(this.IsChargeStatistics);
    }

    public DdyStatisticsRequestParamsInfo(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    protected DdyStatisticsRequestParamsInfo(Parcel parcel) {
        super(parcel);
        this.IsDDYun = parcel.readInt();
        this.IsChargeStatistics = parcel.readInt();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "StatisticsRequestParamsInfo{IsDDYun=" + this.IsDDYun + "IsChargeStatistics=" + this.IsChargeStatistics + ", TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + '}';
    }
}
