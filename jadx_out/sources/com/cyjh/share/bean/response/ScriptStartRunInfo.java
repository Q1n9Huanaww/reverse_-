package com.cyjh.share.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ScriptStartRunInfo implements Parcelable {
    public static final Parcelable.Creator<ScriptStartRunInfo> CREATOR = new Parcelable.Creator<ScriptStartRunInfo>() { // from class: com.cyjh.share.bean.response.ScriptStartRunInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunInfo createFromParcel(Parcel parcel) {
            return new ScriptStartRunInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunInfo[] newArray(int i) {
            return new ScriptStartRunInfo[i];
        }
    };
    public long ClientTimestamp;
    public int DailyTryTimes;
    public String ExpireTime;
    public int OnceTryMinute;
    public String RunGuid;
    public long ServerTimestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ScriptStartRunInfo(Parcel parcel) {
        this.ClientTimestamp = parcel.readLong();
        this.ServerTimestamp = parcel.readLong();
        this.DailyTryTimes = parcel.readInt();
        this.OnceTryMinute = parcel.readInt();
        this.ExpireTime = parcel.readString();
        this.RunGuid = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.ClientTimestamp);
        parcel.writeLong(this.ServerTimestamp);
        parcel.writeInt(this.DailyTryTimes);
        parcel.writeInt(this.OnceTryMinute);
        parcel.writeString(this.ExpireTime);
        parcel.writeString(this.RunGuid);
    }
}
