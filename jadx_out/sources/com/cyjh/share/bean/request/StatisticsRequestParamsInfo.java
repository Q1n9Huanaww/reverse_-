package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class StatisticsRequestParamsInfo extends BaseRequestValueInfo {
    public static final Parcelable.Creator<StatisticsRequestParamsInfo> CREATOR = new Parcelable.Creator<StatisticsRequestParamsInfo>() { // from class: com.cyjh.share.bean.request.StatisticsRequestParamsInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisticsRequestParamsInfo createFromParcel(Parcel parcel) {
            return new StatisticsRequestParamsInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatisticsRequestParamsInfo[] newArray(int i) {
            return new StatisticsRequestParamsInfo[i];
        }
    };
    public int Action;
    public int BussinessId;
    public int CloudPhoneType;
    public long RelationId;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.BussinessId);
        parcel.writeInt(this.Action);
        parcel.writeInt(this.CloudPhoneType);
        parcel.writeLong(this.RelationId);
    }

    public StatisticsRequestParamsInfo(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    protected StatisticsRequestParamsInfo(Parcel parcel) {
        super(parcel);
        this.BussinessId = parcel.readInt();
        this.Action = parcel.readInt();
        this.CloudPhoneType = parcel.readInt();
        this.RelationId = parcel.readLong();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "StatisticsRequestParamsInfo{BussinessId=" + this.BussinessId + ", Action=" + this.Action + ", TemplateFileId=" + this.TemplateFileId + ", RelationId=" + this.RelationId + ", CloudPhoneType=" + this.CloudPhoneType + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + '}';
    }
}
