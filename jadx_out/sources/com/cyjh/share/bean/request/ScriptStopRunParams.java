package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ScriptStopRunParams extends BaseRequestValueInfo {
    public static final Parcelable.Creator<ScriptStopRunParams> CREATOR = new Parcelable.Creator<ScriptStopRunParams>() { // from class: com.cyjh.share.bean.request.ScriptStopRunParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStopRunParams createFromParcel(Parcel parcel) {
            return new ScriptStopRunParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScriptStopRunParams[] newArray(int i) {
            return new ScriptStopRunParams[i];
        }
    };
    public String RunGuid;

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ScriptStopRunParams(BaseRequestValueInfo baseRequestValueInfo) {
        super(baseRequestValueInfo);
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo
    public String toString() {
        return "ScriptStopRunParams{RunGuid='" + this.RunGuid + "', TemplateFileId=" + this.TemplateFileId + ", TemplateVersion='" + this.TemplateVersion + "', AppId=" + this.AppId + ", AppVersion='" + this.AppVersion + "', DeviceId='" + this.DeviceId + "', ClientTimestamp=" + this.ClientTimestamp + ", IsRedFinger=" + this.IsRedFinger + '}';
    }

    @Override // com.cyjh.share.bean.request.BaseRequestValueInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.RunGuid);
    }

    protected ScriptStopRunParams(Parcel parcel) {
        super(parcel);
        this.RunGuid = parcel.readString();
    }
}
