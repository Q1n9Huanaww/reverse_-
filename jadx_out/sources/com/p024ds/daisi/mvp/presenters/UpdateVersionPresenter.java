package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.mvp.views.UpdateVersionView;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.xutils.XUtilsHttpClient;
import com.p024ds.daisi.util.ToastUtils;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class UpdateVersionPresenter implements BasePresenter {
    private CommonLog commonLog = new CommonLog();
    private Context mContext;
    private UpdateVersionView updateVersionView;

    public UpdateVersionPresenter(Context context, UpdateVersionView updateVersionView) {
        this.mContext = context;
        this.updateVersionView = updateVersionView;
    }

    public void updateVersion(final boolean z, final Context context) {
        String string = this.mContext.getString(R.string.pay_appid);
        String packageName = this.mContext.getPackageName();
        String string2 = this.mContext.getString(R.string.pay_vsersion);
        this.commonLog.m1348e("appid>>>" + string);
        this.commonLog.m1348e("packageName>>>" + packageName);
        this.commonLog.m1348e("version>>>" + string2);
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter("AppId", string);
        requestParams.addQueryStringParameter(UpdateInfo.PACKAGENAME, packageName);
        requestParams.addQueryStringParameter(UpdateInfo.VERSION, string2);
        XUtilsHttpClient.getInstence(this.mContext).send(HttpRequest.HttpMethod.GET, URLConstant.UPDATE_URL, requestParams, new RequestCallBack<String>() { // from class: com.ds.daisi.mvp.presenters.UpdateVersionPresenter.1
            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onFailure(HttpException httpException, String str) {
            }

            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onSuccess(ResponseInfo<String> responseInfo) {
                UpdateVersionPresenter.this.commonLog.m1348e("responseInfo.result>>>" + responseInfo.result);
                if (TextUtils.isEmpty(responseInfo.result) || "null".equals(responseInfo.result)) {
                    if (z) {
                        ToastUtils.showToastLong(UpdateVersionPresenter.this.mContext, R.string.update_newest);
                    }
                } else {
                    UpdateInfo updateInfo = ((UpdateInfo[]) new Gson().fromJson(responseInfo.result, UpdateInfo[].class))[0];
                    updateInfo.mContext = context;
                    UpdateVersionPresenter.this.updateVersionView.onUpdateSuccessful(updateInfo);
                }
            }
        });
    }
}
