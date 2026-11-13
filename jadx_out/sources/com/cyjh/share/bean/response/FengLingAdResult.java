package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FengLingAdResult implements Parcelable {
    public static final Parcelable.Creator<FengLingAdResult> CREATOR = new Parcelable.Creator<FengLingAdResult>() { // from class: com.cyjh.share.bean.response.FengLingAdResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FengLingAdResult createFromParcel(Parcel parcel) {
            return new FengLingAdResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FengLingAdResult[] newArray(int i) {
            return new FengLingAdResult[i];
        }
    };
    List<FengLingAdInfo> ads;
    private String callRequestId;
    private int code;
    private String msg;
    private String requestId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FengLingAdResult() {
    }

    protected FengLingAdResult(Parcel parcel) {
        this.code = parcel.readInt();
        this.msg = parcel.readString();
        this.requestId = parcel.readString();
        this.callRequestId = parcel.readString();
        this.ads = parcel.createTypedArrayList(FengLingAdInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.msg);
        parcel.writeString(this.requestId);
        parcel.writeString(this.callRequestId);
        parcel.writeTypedList(this.ads);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getCallRequestId() {
        return this.callRequestId;
    }

    public void setCallRequestId(String str) {
        this.callRequestId = str;
    }

    public List<FengLingAdInfo> getAds() {
        return this.ads;
    }

    public void setAds(List<FengLingAdInfo> list) {
        this.ads = list;
    }

    public static Parcelable.Creator<FengLingAdResult> getCREATOR() {
        return CREATOR;
    }
}
