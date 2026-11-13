package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ScriptStartRunResponse implements Parcelable {
    public static final Parcelable.Creator<ScriptStartRunResponse> CREATOR = new Parcelable.Creator<ScriptStartRunResponse>() { // from class: com.cyjh.share.bean.response.ScriptStartRunResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunResponse createFromParcel(Parcel parcel) {
            return new ScriptStartRunResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunResponse[] newArray(int i) {
            return new ScriptStartRunResponse[i];
        }
    };
    public static long sClientTime;
    public int Code;
    public ScriptStartRunInfo Data;
    public String Message;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ScriptStartRunResponse(Parcel parcel) {
        this.Code = parcel.readInt();
        this.Message = parcel.readString();
        this.Data = (ScriptStartRunInfo) parcel.readParcelable(ScriptStartRunInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Code);
        parcel.writeString(this.Message);
        parcel.writeParcelable(this.Data, i);
    }
}
