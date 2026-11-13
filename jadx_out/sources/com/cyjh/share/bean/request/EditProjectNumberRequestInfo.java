package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class EditProjectNumberRequestInfo extends BaseRequestValueInfo {
    public static final Parcelable.Creator<EditProjectNumberRequestInfo> CREATOR = new Parcelable.Creator<EditProjectNumberRequestInfo>() { // from class: com.cyjh.share.bean.request.EditProjectNumberRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EditProjectNumberRequestInfo createFromParcel(Parcel parcel) {
            return new EditProjectNumberRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EditProjectNumberRequestInfo[] newArray(int i) {
            return new EditProjectNumberRequestInfo[i];
        }
    };
    public String ScriptId;
    public String ScriptSettings;
    public String ScriptVersion;
    public String StudioProjectKey;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EditProjectNumberRequestInfo(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.ScriptId);
        parcel.writeString(this.StudioProjectKey);
        parcel.writeString(this.ScriptVersion);
        parcel.writeString(this.ScriptSettings);
    }

    protected EditProjectNumberRequestInfo(Parcel parcel) {
        super(parcel);
        this.ScriptId = parcel.readString();
        this.StudioProjectKey = parcel.readString();
        this.ScriptVersion = parcel.readString();
        this.ScriptSettings = parcel.readString();
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "EditProjectNumberRequestInfo{TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + ", DeviceCode='" + this.DeviceCode + "', AppInfo='" + this.AppInfo + "', ScriptId='" + this.ScriptId + "', StudioProjectKey='" + this.StudioProjectKey + "', ScriptVersion='" + this.ScriptVersion + "', ScriptSettings='" + this.ScriptSettings + "'}";
    }

    public String getScriptId() {
        return this.ScriptId;
    }

    public void setScriptId(String str) {
        this.ScriptId = str;
    }

    public String getStudioProjectKey() {
        return this.StudioProjectKey;
    }

    public void setStudioProjectKey(String str) {
        this.StudioProjectKey = str;
    }

    public String getScriptVersion() {
        return this.ScriptVersion;
    }

    public void setScriptVersion(String str) {
        this.ScriptVersion = str;
    }

    public String getScriptSettings() {
        return this.ScriptSettings;
    }

    public void setScriptSettings(String str) {
        this.ScriptSettings = str;
    }

    public static Parcelable.Creator<EditProjectNumberRequestInfo> getCREATOR() {
        return CREATOR;
    }
}
