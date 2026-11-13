package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RecommendGameListParams extends BaseRequestValueInfo implements Parcelable {
    public static final Parcelable.Creator<RecommendGameListParams> CREATOR = new Parcelable.Creator<RecommendGameListParams>() { // from class: com.cyjh.share.bean.request.RecommendGameListParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendGameListParams createFromParcel(Parcel parcel) {
            return new RecommendGameListParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendGameListParams[] newArray(int i) {
            return new RecommendGameListParams[i];
        }
    };
    public int CurrentPage;
    public int PageSize;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecommendGameListParams(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    protected RecommendGameListParams(Parcel parcel) {
        super(parcel);
        this.CurrentPage = parcel.readInt();
        this.PageSize = parcel.readInt();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.CurrentPage);
        parcel.writeInt(this.PageSize);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "RecommendGameListParams{TemplateFileId=" + this.TemplateFileId + ", CurrentPage=" + this.CurrentPage + ", PageSize=" + this.PageSize + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + '}';
    }
}
