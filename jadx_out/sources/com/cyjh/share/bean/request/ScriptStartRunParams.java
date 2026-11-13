package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ScriptStartRunParams extends BaseRequestValueInfo {
    public static final Parcelable.Creator<ScriptStartRunParams> CREATOR = new Parcelable.Creator<ScriptStartRunParams>() { // from class: com.cyjh.share.bean.request.ScriptStartRunParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunParams createFromParcel(Parcel parcel) {
            return new ScriptStartRunParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStartRunParams[] newArray(int i) {
            return new ScriptStartRunParams[i];
        }
    };
    public String RegCode;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ScriptStartRunParams(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "ScriptStartRunParams{RegCode='" + this.RegCode + "', TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + '}';
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.RegCode);
    }

    protected ScriptStartRunParams(Parcel parcel) {
        super(parcel);
        this.RegCode = parcel.readString();
    }
}
