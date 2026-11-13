package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.share.C0854R;
import com.cyjh.share.bean.request.UpdateRequestInfo;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.VersionUpdateView;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.ToastUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppVersionUpdatePresenter extends AbstractHttpPresenter {
    public static final String TAG = "AppVersionUpdatePresenter";
    private long currentTimestamp;
    private String deviceName;
    private boolean isToast;
    private Context mContext;
    private UpdateRequestInfo mUpdateRequestInfo;
    private VersionUpdateView mUpdateView;
    private String scriptId;

    public AppVersionUpdatePresenter(Context context, VersionUpdateView versionUpdateView, boolean z) {
        this.isToast = false;
        this.mContext = context;
        this.mUpdateView = versionUpdateView;
        this.isToast = z;
    }

    public AppVersionUpdatePresenter(Context context, VersionUpdateView versionUpdateView, boolean z, UpdateRequestInfo updateRequestInfo) {
        this.isToast = false;
        this.mContext = context;
        this.mUpdateView = versionUpdateView;
        this.isToast = z;
        this.mUpdateRequestInfo = updateRequestInfo;
    }

    public void updateVersion(Context context) {
        try {
            UpdateRequestInfo updateRequestInfo = new UpdateRequestInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            if (this.mUpdateRequestInfo != null) {
                updateRequestInfo.ScriptId = this.mUpdateRequestInfo.getScriptId();
                updateRequestInfo.DeviceName = this.mUpdateRequestInfo.getDeviceName();
                updateRequestInfo.ScriptVersion = this.mUpdateRequestInfo.getScriptVersion();
                updateRequestInfo.IsScriptHotUpgrade = this.mUpdateRequestInfo.getIsScriptHotUpgrade();
                if (!TextUtils.isEmpty(this.mUpdateRequestInfo.AppVersion)) {
                    updateRequestInfo.AppVersion = this.mUpdateRequestInfo.AppVersion;
                }
            }
            SlLog.m1334i(TAG, "updateVersion --> 1 paramsMap=" + updateRequestInfo.toString());
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(updateRequestInfo);
            this.currentTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            String string = MyConfig.getBuilderPay(InterfaceRelatedConstants.VERSION_UPDATE_NAME).build().toString();
            SlLog.m1334i(TAG, "updateVersion --> 2 url=" + string + ",paramsMap=" + mapPrames.toString());
            this.f1688mA.sendPostRequest(this.mContext, getClass().getCanonicalName(), string, mapPrames);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "com.cyjh.share.AppVersionUpdatePresenter--updateVersion:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            String str = (String) obj;
            SlLog.m1334i(TAG, "uiDataSuccess --> data=" + str);
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
            int i = jSONObject.getInt("Code");
            String string = jSONObject.getString(Constants.DATABASE_TABLE_MSG);
            if (i == 200) {
                VersionUpdateInfo versionUpdateInfo = (VersionUpdateInfo) GsonExUtil.parsData(jSONObject2.toString(), VersionUpdateInfo.class);
                if (versionUpdateInfo != null && this.currentTimestamp == versionUpdateInfo.ClientTimestamp) {
                    if (versionUpdateInfo.UpdateType == 0) {
                        this.mUpdateView.onUpdateEmpty();
                        if (this.isToast) {
                            ToastUtils.showToastLong(VariableAndConstantsManager.getInstance().getContext(), C0854R.string.version_newest);
                        }
                    } else {
                        this.mUpdateView.onUpdateSuccessful(versionUpdateInfo);
                    }
                } else {
                    this.mUpdateView.onUpdateFailure(VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.json_parse_exception));
                }
            } else {
                SlLog.m1334i(TAG, "onUpdateFailure --> message=" + string);
                this.mUpdateView.onUpdateFailure(string);
            }
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.AppVersionUpdatePresenter--onErrorResponse:" + e.getMessage());
            this.mUpdateView.onUpdateFailure(e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "com.cyjh.share.AppVersionUpdatePresenter--onErrorResponse:" + str);
        this.mUpdateView.onUpdateFailure(str);
    }

    public void onCancel() {
        this.f1688mA.stopRequest(getClass().getCanonicalName());
    }
}
