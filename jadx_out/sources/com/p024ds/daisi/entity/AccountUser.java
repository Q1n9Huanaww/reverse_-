package com.p024ds.daisi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AccountUser implements Parcelable {
    public static final Parcelable.Creator<AccountUser> CREATOR = new Parcelable.Creator<AccountUser>() { // from class: com.ds.daisi.entity.AccountUser.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountUser createFromParcel(Parcel parcel) {
            return new AccountUser(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountUser[] newArray(int i) {
            return new AccountUser[i];
        }
    };
    public String bindphone;
    public String codetype;
    public String newpassword;
    public String packagename;
    public String password;
    public String phone;
    public String type;
    public String usertype;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AccountUser() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phone);
        parcel.writeString(this.password);
        parcel.writeString(this.newpassword);
        parcel.writeString(this.type);
        parcel.writeString(this.packagename);
        parcel.writeString(this.codetype);
        parcel.writeString(this.usertype);
        parcel.writeString(this.bindphone);
    }

    protected AccountUser(Parcel parcel) {
        this.phone = parcel.readString();
        this.password = parcel.readString();
        this.newpassword = parcel.readString();
        this.type = parcel.readString();
        this.packagename = parcel.readString();
        this.codetype = parcel.readString();
        this.usertype = parcel.readString();
        this.bindphone = parcel.readString();
    }

    public String toString() {
        return "AccountUser{phone='" + this.phone + "', password='" + this.password + "', newpassword='" + this.newpassword + "', type='" + this.type + "', packagename='" + this.packagename + "', codetype='" + this.codetype + "', usertype='" + this.usertype + "', bindphone='" + this.bindphone + "'}";
    }
}
