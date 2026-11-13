package com.p024ds.daisi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class BestResolution implements Parcelable {
    public static final Parcelable.Creator<BestResolution> CREATOR = new Parcelable.Creator<BestResolution>() { // from class: com.ds.daisi.entity.BestResolution.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BestResolution[] newArray(int i) {
            return new BestResolution[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BestResolution createFromParcel(Parcel parcel) {
            return new BestResolution(parcel.readInt(), parcel.readInt());
        }
    };

    /* JADX INFO: renamed from: X */
    private int f1700X;

    /* JADX INFO: renamed from: Y */
    private int f1701Y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BestResolution() {
    }

    public BestResolution(int i, int i2) {
        this.f1700X = i;
        this.f1701Y = i2;
    }

    public int getX() {
        return this.f1700X;
    }

    public void setX(int i) {
        this.f1700X = i;
    }

    public int getY() {
        return this.f1701Y;
    }

    public void setY(int i) {
        this.f1701Y = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1700X);
        parcel.writeInt(this.f1701Y);
    }

    public String toString() {
        return this.f1700X + "X" + this.f1701Y;
    }
}
