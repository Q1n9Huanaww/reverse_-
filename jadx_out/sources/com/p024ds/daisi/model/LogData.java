package com.p024ds.daisi.model;

import android.content.Context;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.xutils.XUtilsHttpClient;
import com.p024ds.daisi.util.UniqIdUtil;
import com.warnship.autoplay.R;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes.dex */
public class LogData {
    private static final String DATA = "data";
    private static LogData logData;
    private Data Data;
    private String LogType = "3";

    public LogData(Context context) {
        this.Data = new Data(context);
    }

    public static LogData getInstance(Context context) {
        if (logData == null) {
            logData = new LogData(context);
        }
        return logData;
    }

    public String getLogType() {
        return this.LogType;
    }

    public void setLogType(String str) {
        this.LogType = str;
    }

    public Data getData() {
        return this.Data;
    }

    public void setData(Data data) {
        this.Data = data;
    }

    public void sendLog(Context context, RequestCallBack requestCallBack) {
        final CommonLog commonLog = new CommonLog();
        String json = new GsonBuilder().create().toJson(this);
        commonLog.m1348e("dataString>>>>" + json);
        HttpUtils instence = XUtilsHttpClient.getInstence(context);
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter(DATA, json);
        instence.send(HttpRequest.HttpMethod.GET, URLConstant.LOGURL, requestParams, new RequestCallBack<String>() { // from class: com.ds.daisi.model.LogData.1
            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onSuccess(ResponseInfo<String> responseInfo) {
                commonLog.m1348e("访问成功");
            }

            @Override // com.lidroid.xutils.http.callback.RequestCallBack
            public void onFailure(HttpException httpException, String str) {
                commonLog.m1348e(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL + URLConstant.LOGURL);
                commonLog.m1348e("访问失败" + httpException.getMessage() + str);
            }
        });
    }

    public static class Data {
        private String AppID;
        private String AppVersion;
        private String MachineCode;
        private String IsFree = AccountManager.TYPE_LOGIN;
        private String UsedTime = AccountManager.TYPE_LOGIN;

        public Data(Context context) {
            this.AppVersion = context.getString(R.string.pay_vsersion);
            this.AppID = context.getString(R.string.pay_appid);
            this.MachineCode = UniqIdUtil.getUniqId(context);
        }

        public String getAppVersion() {
            return this.AppVersion;
        }

        public void setAppVersion(String str) {
            this.AppVersion = str;
        }

        public String getAppID() {
            return this.AppID;
        }

        public void setAppID(String str) {
            this.AppID = str;
        }

        public String getMachineCode() {
            return this.MachineCode;
        }

        public void setMachineCode(String str) {
            this.MachineCode = str;
        }

        public String getIsFree() {
            return this.IsFree;
        }

        public void setIsFree(String str) {
            this.IsFree = str;
        }

        public String getUsedTime() {
            return this.UsedTime;
        }

        public void setUsedTime(String str) {
            this.UsedTime = str;
        }
    }
}
