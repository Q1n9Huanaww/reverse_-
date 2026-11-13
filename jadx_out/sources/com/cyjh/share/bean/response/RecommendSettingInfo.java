package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RecommendSettingInfo implements Parcelable {
    public static final Parcelable.Creator<RecommendSettingInfo> CREATOR = new Parcelable.Creator<RecommendSettingInfo>() { // from class: com.cyjh.share.bean.response.RecommendSettingInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendSettingInfo createFromParcel(Parcel parcel) {
            return new RecommendSettingInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendSettingInfo[] newArray(int i) {
            return new RecommendSettingInfo[i];
        }
    };

    /* JADX INFO: renamed from: Id */
    public int f1683Id;
    public String LinkUrl;
    public String RecommendType;
    public int Sequence;
    public String Title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecommendSettingInfo() {
    }

    protected RecommendSettingInfo(Parcel parcel) {
        this.Title = parcel.readString();
        this.f1683Id = parcel.readInt();
        this.RecommendType = parcel.readString();
        this.LinkUrl = parcel.readString();
        this.Sequence = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Title);
        parcel.writeInt(this.f1683Id);
        parcel.writeString(this.RecommendType);
        parcel.writeString(this.LinkUrl);
        parcel.writeInt(this.Sequence);
    }

    public String toString() {
        return "RecommendSettingInfo{Title='" + this.Title + "', Id=" + this.f1683Id + ", RecommendType='" + this.RecommendType + "', LinkUrl='" + this.LinkUrl + "', Sequence=" + this.Sequence + '}';
    }
}
