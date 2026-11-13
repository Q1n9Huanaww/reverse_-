package com.cyjh.share.manager;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.share.bean.SignaClass;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.request.DeviceInfo;
import com.cyjh.share.bean.response.AliCloudServerResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.helper.PhoneConfigHelper;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SignUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class VariableAndConstantsManager {
    public static long sCurrentTimestamp;
    private static VariableAndConstantsManager sVariableAndConstantManager;
    public AliCloudServerResponse mAliCloudServerResponse;
    private Context mContext;
    public DeviceInfo mDeviceInfo = new DeviceInfo();
    public int templateType;

    public Context getContext() {
        return this.mContext;
    }

    public void setContext(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private VariableAndConstantsManager() {
    }

    public static VariableAndConstantsManager getInstance() {
        if (sVariableAndConstantManager == null) {
            synchronized (VariableAndConstantsManager.class) {
                if (sVariableAndConstantManager == null) {
                    sVariableAndConstantManager = new VariableAndConstantsManager();
                }
            }
        }
        return sVariableAndConstantManager;
    }

    public BaseRequestValueInfo getBaseRequestParams(Context context) {
        BaseRequestValueInfo baseRequestValueInfo = new BaseRequestValueInfo();
        if (TextUtils.isEmpty(this.mDeviceInfo.DeviceCode) || TextUtils.isEmpty(this.mDeviceInfo.DeviceId)) {
            DeviceInfo deviceInfoAcquireDevicesId = AppUtils.acquireDevicesId(context);
            this.mDeviceInfo.DeviceId = deviceInfoAcquireDevicesId.DeviceId;
            this.mDeviceInfo.DeviceCode = deviceInfoAcquireDevicesId.DeviceCode;
        }
        baseRequestValueInfo.DeviceId = this.mDeviceInfo.DeviceId;
        baseRequestValueInfo.DeviceCode = this.mDeviceInfo.DeviceCode;
        baseRequestValueInfo.TemplateVersion = MyConfig.getTempVersionNumber();
        baseRequestValueInfo.AppId = MyConfig.getAppid();
        baseRequestValueInfo.TemplateFileId = MyConfig.getTemplateFileId();
        baseRequestValueInfo.AppVersion = MyConfig.getAppVersion();
        baseRequestValueInfo.AppInfo = MyConfig.getAppInfo();
        sCurrentTimestamp = System.currentTimeMillis() / 1000;
        baseRequestValueInfo.ClientTimestamp = sCurrentTimestamp;
        return baseRequestValueInfo;
    }

    public Map<String, String> toMapPrames(BaseRequestValueInfo baseRequestValueInfo) throws Exception {
        HashMap map;
        synchronized (this) {
            SignaClass signaClass = new SignaClass();
            signaClass.SignContent = GsonExUtil.class2Data(baseRequestValueInfo);
            signaClass.Signature = SignUtil.rsaSign(signaClass.SignContent);
            int randomInt = getRandomInt();
            String desJson = toDesJson(signaClass, randomInt);
            map = new HashMap();
            map.put(URLConstant.TOKEN_PARAMS_DATA, desJson);
            map.put("R", String.valueOf(randomInt));
        }
        return map;
    }

    private int getRandomInt() {
        return new Random().nextInt(8);
    }

    private String toDesJson(Object obj, int i) throws Exception {
        String strClass2Data = GsonExUtil.class2Data(obj);
        return TextUtils.isEmpty(strClass2Data) ? "" : SignUtil.des(strClass2Data, i);
    }

    public boolean isAppFree() {
        return this.templateType == 1;
    }

    public String toRecognitionGuideWebViewPrams(String str, BaseRequestValueInfo baseRequestValueInfo) {
        String string;
        synchronized (this) {
            StringBuffer stringBuffer = new StringBuffer(str);
            try {
                stringBuffer.append("?AppId=");
                stringBuffer.append(baseRequestValueInfo.AppId);
                stringBuffer.append("&AppVersion=");
                stringBuffer.append(baseRequestValueInfo.AppVersion);
                stringBuffer.append("&PlatformId=");
                stringBuffer.append(PhoneConfigHelper.get().getCloudPhoneType());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            string = stringBuffer.toString();
        }
        return string;
    }
}
