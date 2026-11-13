package com.p024ds.daisi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RegCodeStatusInfo implements Parcelable {
    public static final Parcelable.Creator<RegCodeStatusInfo> CREATOR = new Parcelable.Creator<RegCodeStatusInfo>() { // from class: com.ds.daisi.entity.RegCodeStatusInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegCodeStatusInfo createFromParcel(Parcel parcel) {
            return new RegCodeStatusInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegCodeStatusInfo[] newArray(int i) {
            return new RegCodeStatusInfo[i];
        }
    };
    public String regCode;
    public int status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.regCode);
        parcel.writeInt(this.status);
    }

    public RegCodeStatusInfo() {
    }

    protected RegCodeStatusInfo(Parcel parcel) {
        this.regCode = parcel.readString();
        this.status = parcel.readInt();
    }

    public String toString() {
        return "RegCodeStatusInfo{regCode='" + this.regCode + "', status=" + this.status + '}';
    }
}
