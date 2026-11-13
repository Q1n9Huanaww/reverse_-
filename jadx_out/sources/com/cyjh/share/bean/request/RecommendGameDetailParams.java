package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RecommendGameDetailParams extends BaseRequestValueInfo implements Parcelable {
    public static final Parcelable.Creator<RecommendGameDetailParams> CREATOR = new Parcelable.Creator<RecommendGameDetailParams>() { // from class: com.cyjh.share.bean.request.RecommendGameDetailParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendGameDetailParams createFromParcel(Parcel parcel) {
            return new RecommendGameDetailParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendGameDetailParams[] newArray(int i) {
            return new RecommendGameDetailParams[i];
        }
    };
    public long GameId;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecommendGameDetailParams(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    protected RecommendGameDetailParams(Parcel parcel) {
        super(parcel);
        this.GameId = parcel.readLong();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.GameId);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "RecommendGameDetailParams{TemplateFileId=" + this.TemplateFileId + ", GameId=" + this.GameId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + '}';
    }
}
