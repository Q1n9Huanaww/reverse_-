package com.cyjh.share.bean.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.share.bean.SignaClass;
import com.cyjh.share.util.SignUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class BaseRequestInfo implements Parcelable {
    public static final Parcelable.Creator<BaseRequestInfo> CREATOR = new Parcelable.Creator<BaseRequestInfo>() { // from class: com.cyjh.share.bean.request.BaseRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequestInfo createFromParcel(Parcel parcel) {
            return new BaseRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequestInfo[] newArray(int i) {
            return new BaseRequestInfo[i];
        }
    };
    public String Data;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseRequestInfo() {
    }

    public Map<String, String> toMapPrames(BaseRequestValueInfo baseRequestValueInfo) throws Exception {
        int randomInt = getRandomInt();
        SignaClass signaClass = new SignaClass();
        signaClass.SignContent = objectToString(baseRequestValueInfo);
        Log.e("zzz", "BaseRequestInfo--toMapPrames--1:" + signaClass.SignContent);
        signaClass.Signature = SignUtil.rsaSign(signaClass.SignContent);
        this.Data = toDesJson(signaClass, randomInt);
        HashMap map = new HashMap();
        map.put(URLConstant.TOKEN_PARAMS_DATA, this.Data);
        map.put("R", String.valueOf(randomInt));
        return map;
    }

    private int getRandomInt() {
        return new Random().nextInt(8);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Data);
    }

    protected BaseRequestInfo(Parcel parcel) {
        this.Data = parcel.readString();
    }

    private String toDesJson(Object obj, int i) throws Exception {
        String strObjectToString = objectToString(obj);
        return TextUtils.isEmpty(strObjectToString) ? "" : SignUtil.des(strObjectToString, i);
    }

    private static String objectToString(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}
