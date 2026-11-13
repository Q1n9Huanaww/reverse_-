package com.p024ds.daisi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class FileVersion implements Parcelable {
    public static final Parcelable.Creator<FileVersion> CREATOR = new Parcelable.Creator<FileVersion>() { // from class: com.ds.daisi.entity.FileVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileVersion[] newArray(int i) {
            return new FileVersion[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileVersion createFromParcel(Parcel parcel) {
            return new FileVersion(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }
    };
    int atc;

    /* JADX INFO: renamed from: mq */
    int f1706mq;
    int prop;
    int rtd;

    /* JADX INFO: renamed from: ui */
    int f1707ui;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FileVersion() {
    }

    public FileVersion(int i, int i2, int i3, int i4, int i5) {
        this.atc = i;
        this.f1706mq = i2;
        this.prop = i3;
        this.f1707ui = i4;
        this.rtd = i5;
    }

    public int getAtc() {
        return this.atc;
    }

    public void setAtc(int i) {
        this.atc = i;
    }

    public int getMq() {
        return this.f1706mq;
    }

    public void setMq(int i) {
        this.f1706mq = i;
    }

    public int getProp() {
        return this.prop;
    }

    public void setProp(int i) {
        this.prop = i;
    }

    public int getUi() {
        return this.f1707ui;
    }

    public void setUI(int i) {
        this.f1707ui = i;
    }

    public int getRtd() {
        return this.rtd;
    }

    public void setRtd(int i) {
        this.rtd = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.atc);
        parcel.writeInt(this.f1706mq);
        parcel.writeInt(this.prop);
        parcel.writeInt(this.f1707ui);
        parcel.writeInt(this.rtd);
    }
}
