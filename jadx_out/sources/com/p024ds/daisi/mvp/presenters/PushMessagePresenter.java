package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.share.bean.NotifyMsgBean;
import com.cyjh.share.mvp.view.PushMessageView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyRequestQueue;
import com.p024ds.daisi.net.volley.VolleyStrRequestManager;
import com.warnship.autoplay.R;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PushMessagePresenter implements BasePresenter {
    private static final String IS_FREE = "0";
    private static final String REQUEST_TAG = "PushMessagePresenter.class.getCanonicalName()";
    private Context context;
    private PushMessageView pushMessageView;

    public PushMessagePresenter(PushMessageView pushMessageView, Context context) {
        this.pushMessageView = pushMessageView;
        this.context = context;
    }

    public void acquireMessagePush(String str) {
        String string = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY, URLConstant.MSG_REQUEST_NAME).appendQueryParameter(URLConstant.MSG_REQUEST_PLATFORMS, Constants.ANDROID).appendQueryParameter(URLConstant.MSG_REQUEST_IMIE, str).appendQueryParameter(URLConstant.MSG_REQUEST_IS_FREE, "0").appendQueryParameter(URLConstant.MSG_REQUEST_GUID, this.context.getString(R.string.pay_appid)).build().toString();
        Logger.m3442e("收费消息通知url：" + string, new Object[0]);
        VolleyManager.requestStringGet(string, REQUEST_TAG, new VolleyStrRequestManager(this.context) { // from class: com.ds.daisi.mvp.presenters.PushMessagePresenter.1
            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestSuccessRes(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    if (jSONObject.has(URLConstant.TOKEN_PARAMS_DATA)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
                        if (jSONObject2.has("rdata")) {
                            JSONArray jSONArray = jSONObject2.getJSONArray("rdata");
                            if (jSONArray.length() != 0) {
                                NotifyMsgBean[] notifyMsgBeanArr = (NotifyMsgBean[]) new Gson().fromJson(jSONArray.toString(), NotifyMsgBean[].class);
                                if (notifyMsgBeanArr.length > 0) {
                                    PushMessagePresenter.this.pushMessageView.onSuccessfulAcquireNews(Arrays.asList(notifyMsgBeanArr));
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }

            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestErrorRes(String str2) {
                Logger.m3442e(str2 + "请求失败", new Object[0]);
            }
        });
    }

    public void onStopRequest() {
        VolleyRequestQueue.getRequestQueue().cancelAll(REQUEST_TAG);
    }
}
