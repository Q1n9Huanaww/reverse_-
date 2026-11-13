package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.entity.AccountPhone;
import com.p024ds.daisi.entity.LotteryDataEntry;
import com.p024ds.daisi.entity.TokenBean;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyJsonRequestManager;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyRequestQueue;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.DesUtil;
import com.p024ds.daisi.util.MD5Util;
import com.p024ds.daisi.util.ToastUtils;
import com.warnship.autoplay.R;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VerifyTokenManager implements BaseManager {
    private static final String CHECKED_TOKEN_TYPE = "2";
    private static final String GENERATOR_TOKEN_TYPE = "1";

    private JSONObject generateData(Context context, String str, String str2, String str3) {
        try {
            int random = AppDeviceUtils.getRandom(7);
            String strFromArray = ((LotteryDataEntry) new Gson().fromJson(str, LotteryDataEntry.class)).getStrFromArray(random);
            String strValueOf = String.valueOf(random);
            String strMD5 = MD5Util.MD5("Type=" + str2 + "&Token=" + str3 + "&AppId=" + context.getString(R.string.pay_appid) + "&UserName=" + AccountPhone.phone + "&RCode=" + strValueOf + "&Codes=" + strFromArray);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(URLConstant.TOKEN_PARAM_TYPE, String.valueOf(str2));
            jSONObject.put("AppId", context.getString(R.string.pay_appid));
            jSONObject.put("Token", str3);
            jSONObject.put(URLConstant.TOKEN_PARAMS_USERNAME, AccountPhone.phone);
            jSONObject.put(URLConstant.TOKEN_PARAMS_RCODE, strValueOf);
            jSONObject.put(URLConstant.TOKEN_PARAMS_SIGN, strMD5);
            StringBuilder sb = new StringBuilder();
            sb.append("data to jsonString");
            sb.append(jSONObject.toString());
            Logger.m3442e(sb.toString(), new Object[0]);
            String strEncode = DesUtil.encode(jSONObject.toString(), Constants.DESkey, Constants.DESIV);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", URLEncoder.encode(strEncode));
            return jSONObject2;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            ToastUtils.showToastLong(context, context.getString(R.string.json_params_exception));
            return null;
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
            ToastUtils.showToastLong(context, context.getString(R.string.params_encrypt_exception));
            return null;
        }
    }

    private String getUrl(String str) {
        return MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY_VERIFY, str).build().toString();
    }

    public void cancelTag() {
        VolleyRequestQueue.getRequestQueue().cancelAll(VerifyTokenManager.class.getCanonicalName());
        VolleyRequestQueue.getRequestQueue().cancelAll(VerifyTokenManager.class.getSimpleName());
    }

    public void generateToken(final Context context, final String str, final Callback<TokenBean> callback) {
        try {
            String url = getUrl("Token");
            Log.e("zzz", "generateToken:" + url);
            JSONObject jSONObjectGenerateData = generateData(context, str, "1", "");
            if (jSONObjectGenerateData == null) {
                return;
            }
            cancelTag();
            VolleyManager.requestJsonObjectPost(url, VerifyTokenManager.class.getCanonicalName(), jSONObjectGenerateData, new VolleyJsonRequestManager(context) { // from class: com.ds.daisi.mvp.managers.VerifyTokenManager.1
                @Override // com.p024ds.daisi.net.volley.VolleyJsonRequestManager
                public void onJsonReqSuccessRes(JSONObject jSONObject) {
                    VerifyTokenManager.this.parseJson(jSONObject, str, callback, context, "1");
                }

                @Override // com.p024ds.daisi.net.volley.VolleyJsonRequestManager
                public void onRequestErrorRes(String str2) {
                    ToastUtils.showToastLong(context.getApplicationContext(), str2);
                }
            });
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void checkToken(final Context context, String str, final String str2, final Callback<TokenBean> callback) {
        try {
            String url = getUrl("Token");
            JSONObject jSONObjectGenerateData = generateData(context, str2, "2", str);
            if (jSONObjectGenerateData == null) {
                return;
            }
            cancelTag();
            VolleyManager.requestJsonObjectPost(url, VerifyTokenManager.class.getSimpleName(), jSONObjectGenerateData, new VolleyJsonRequestManager(context) { // from class: com.ds.daisi.mvp.managers.VerifyTokenManager.2
                @Override // com.p024ds.daisi.net.volley.VolleyJsonRequestManager
                public void onJsonReqSuccessRes(JSONObject jSONObject) {
                    Logger.m3442e("checkToken res:" + jSONObject, new Object[0]);
                    VerifyTokenManager.this.parseJson(jSONObject, str2, callback, context, "2");
                }

                @Override // com.p024ds.daisi.net.volley.VolleyJsonRequestManager
                public void onRequestErrorRes(String str3) {
                    ToastUtils.showToastLong(context.getApplicationContext(), str3);
                }
            });
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseJson(JSONObject jSONObject, String str, Callback<TokenBean> callback, Context context, String str2) {
        try {
            String string = jSONObject.getString("Code");
            String string2 = jSONObject.getString("Msg");
            Logger.m3442e("code=" + string + ",msg:" + string2, new Object[0]);
            if (string.equals(AccountManager.TYPE_LOGIN)) {
                String strDecode = DesUtil.decode(jSONObject.getString(URLConstant.TOKEN_PARAMS_DATA), Constants.DESkey, Constants.DESIV);
                Logger.m3442e("deData:" + strDecode, new Object[0]);
                new JSONObject(strDecode).getString("CheckInfo");
                TokenBean tokenBeanCheckSign = (TokenBean) new Gson().fromJson(strDecode, TokenBean.class);
                if (str2.equals("1")) {
                    if (tokenBeanCheckSign.CheckInfo.Result.equals(AccountManager.TYPE_LOGIN)) {
                        tokenBeanCheckSign = checkSign(str, tokenBeanCheckSign);
                    } else {
                        tokenBeanCheckSign.setCheckSign(false);
                    }
                } else if (tokenBeanCheckSign.CheckInfo.Result.equals(AccountManager.TYPE_LOGIN) || tokenBeanCheckSign.CheckInfo.Result.equals("1")) {
                    tokenBeanCheckSign = checkSign(str, tokenBeanCheckSign);
                } else {
                    tokenBeanCheckSign.setCheckSign(false);
                }
                callback.finish(tokenBeanCheckSign);
                return;
            }
            if (TextUtils.isEmpty(string2)) {
                string2 = context.getString(R.string.return_data_exception);
            }
            ToastUtils.showToastLong(context, string2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
            ToastUtils.showToastLong(context, context.getString(R.string.des_decrypt_exception) + "--" + str2);
        }
    }

    private TokenBean checkSign(String str, TokenBean tokenBean) {
        LotteryDataEntry lotteryDataEntry = (LotteryDataEntry) new Gson().fromJson(str, LotteryDataEntry.class);
        String str2 = tokenBean.RCode;
        String strFromArray = lotteryDataEntry.getStrFromArray(Integer.parseInt(str2));
        String str3 = "{\"Result\":" + tokenBean.CheckInfo.Result + ",\"Info\":\"" + tokenBean.CheckInfo.Info + "\",\"ExpireDate\":" + tokenBean.CheckInfo.ExpireDate + "}";
        Logger.m3442e("jsonCheckInfo:" + str3, new Object[0]);
        StringBuilder sb = new StringBuilder();
        sb.append("Check=");
        sb.append(str3);
        sb.append("&RCode=");
        sb.append(str2);
        sb.append("&Codes=");
        sb.append(strFromArray);
        Logger.m3442e("sb.toString:" + sb.toString(), new Object[0]);
        String strMD5 = MD5Util.MD5(sb.toString());
        Logger.m3442e("md5Sign=" + strMD5, new Object[0]);
        tokenBean.setCheckSign(strMD5.equalsIgnoreCase(tokenBean.Sign));
        Logger.m3442e("equals=" + tokenBean.getCheckSign(), new Object[0]);
        return tokenBean;
    }
}
