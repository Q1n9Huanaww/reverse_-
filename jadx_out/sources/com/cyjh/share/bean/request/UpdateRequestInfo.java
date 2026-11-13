package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class UpdateRequestInfo extends BaseRequestValueInfo {
    public static final Parcelable.Creator<UpdateRequestInfo> CREATOR = new Parcelable.Creator<UpdateRequestInfo>() { // from class: com.cyjh.share.bean.request.UpdateRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateRequestInfo createFromParcel(Parcel parcel) {
            return new UpdateRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpdateRequestInfo[] newArray(int i) {
            return new UpdateRequestInfo[i];
        }
    };
    public String DeviceName;
    public int IsScriptHotUpgrade;
    public String ScriptId;
    public int ScriptVersion;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UpdateRequestInfo() {
    }

    public UpdateRequestInfo(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.ScriptId);
        parcel.writeString(this.DeviceName);
        parcel.writeInt(this.ScriptVersion);
        parcel.writeInt(this.IsScriptHotUpgrade);
    }

    protected UpdateRequestInfo(Parcel parcel) {
        super(parcel);
        this.ScriptId = parcel.readString();
        this.DeviceName = parcel.readString();
        this.ScriptVersion = parcel.readInt();
        this.IsScriptHotUpgrade = parcel.readInt();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "UpdateRequestInfo{TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + ", DeviceCode='" + this.DeviceCode + "', AppInfo='" + this.AppInfo + "', ScriptId='" + this.ScriptId + "', DeviceName='" + this.DeviceName + "', ScriptVersion='" + this.ScriptVersion + "', IsScriptHotUpgrade='" + this.IsScriptHotUpgrade + "'}";
    }

    public String getDeviceName() {
        return this.DeviceName;
    }

    public void setDeviceName(String str) {
        this.DeviceName = str;
    }

    public String getScriptId() {
        return this.ScriptId;
    }

    public void setScriptId(String str) {
        this.ScriptId = str;
    }

    public int getScriptVersion() {
        return this.ScriptVersion;
    }

    public void setScriptVersion(int i) {
        this.ScriptVersion = i;
    }

    public int getIsScriptHotUpgrade() {
        return this.IsScriptHotUpgrade;
    }

    public void setIsScriptHotUpgrade(int i) {
        this.IsScriptHotUpgrade = i;
    }

    public static Parcelable.Creator<UpdateRequestInfo> getCREATOR() {
        return CREATOR;
    }
}
