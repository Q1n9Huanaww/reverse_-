package com.p024ds.daisi.net.xutils;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.fragment.SplashFragment;
import com.p024ds.daisi.log.CommonLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

/* JADX INFO: loaded from: classes.dex */
public class HttpTools {
    public static final String ACTION_ADDUSER = "adduser";
    public static final String ACTION_CHECKEMAIL = "checkemail";
    public static final String ACTION_CHECKNAME = "checkname";
    public static final String ACTION_CHECKUSERLOGIN = "checkuserlogin";
    public static final String APID = "MQ1CHS";
    public static final String APKEY = "516cf79ca36f66e28a9caa8ad8a4c7bc";
    public static final String DATEKEY = "YuFjNTBhMn";
    public static final int ERRCODE_EMAILEXIST = -6;
    public static final int ERRCODE_EMAILFORMAT = -4;
    public static final int ERRCODE_EMAILILLEGAL = -5;
    public static final int ERRCODE_USERNAMEEXIST = -3;
    public static final int ERRCODE_USERNAMEFORMAT = -1;
    public static final int ERRCODE_USERNAMEORPASSWORD = -2;
    public static final String HTTP_END = "&id=MQ1CHS";
    public static final String HTTP_HEAD = "http://bbs.anjian.com/api.php?mod=u&egg=";
    public static final String HTTP_TIME = "http://bbs.anjian.com/api.php?mod=u&gt";
    public static final String JSON_HTTPRESULT_BOOM = "boom";
    public static final String JSON_HTTPRESULT_ERRCODE = "errcode";
    public static final String JSON_HTTPRESULT_RET = "ret";
    public static final String JSON_USER_UID = "uid";
    public static final String JSON_USER_USERNAME = "username";
    public static final int REQUEST_SUCCEED = 1000;
    public static final int RET_ERROR = 2;
    public static final int RET_REFUSE = 1;
    public static final int RET_SUCCEED = 0;
    public static final int SC_UNKNOW = -99;
    private final String HTTPSTATUS_INTERNAL_SERVER_ERROR;
    private final String HTTPSTATUS_REQUEST_TIMEOUT;
    private final String REQUESTEXCEPTION_CLIENTPROTOCOL;
    private final String REQUESTEXCEPTION_CONNECTTIMEOUT;
    private final String REQUESTEXCEPTION_IO;
    private final String REQUESTEXCEPTION_UNKNOW;
    private final String REQUESTEXCEPTION_UNKNOWNHOST;
    private AndroidHttpClient androidHttpClient;
    private CommonLog commonLog;
    private Context context;
    public int httpstatus;
    private String strResult;

    public HttpTools() {
        this.commonLog = new CommonLog();
        this.REQUESTEXCEPTION_UNKNOW = "未知错误，请检查网络后访问！";
        this.REQUESTEXCEPTION_CONNECTTIMEOUT = "连接超时！";
        this.REQUESTEXCEPTION_CLIENTPROTOCOL = "协议错误！";
        this.REQUESTEXCEPTION_UNKNOWNHOST = "访问不到服务器，请检查网络！";
        this.REQUESTEXCEPTION_IO = "IO异常！";
        this.HTTPSTATUS_REQUEST_TIMEOUT = "请求超时！";
        this.HTTPSTATUS_INTERNAL_SERVER_ERROR = "服务器内部错误！";
    }

    public HttpTools(Context context) {
        this();
        this.context = context;
    }

    public String doGet(String str, List<NameValuePair> list) {
        return doGet(generateUrl(str, list));
    }

    public String doGet(String str) {
        if (this.androidHttpClient == null) {
            this.androidHttpClient = AndroidHttpClient.newInstance("Android");
        }
        HttpGet httpGet = new HttpGet(str);
        try {
            try {
                try {
                    try {
                        HttpResponse httpResponseExecute = this.androidHttpClient.execute(httpGet);
                        if (httpResponseExecute.getStatusLine().getStatusCode() == 200) {
                            this.httpstatus = 200;
                            this.strResult = EntityUtils.toString(httpResponseExecute.getEntity());
                            this.commonLog.m1348e("返回结果OK>>>>" + this.strResult);
                        } else if (httpResponseExecute.getStatusLine().getStatusCode() == 500) {
                            this.httpstatus = SplashFragment.DELAY_500_MILLIS;
                            this.strResult = "服务器内部错误！";
                            this.commonLog.m1348e("服务器内部错误>>>" + this.strResult);
                        } else if (httpResponseExecute.getStatusLine().getStatusCode() == 408) {
                            this.httpstatus = UiMessage.CommandToUi.Command_Type.GET_VALUE_VALUE;
                            this.strResult = "请求超时！";
                            this.commonLog.m1348e(" 请求超时>>>" + this.strResult);
                        } else if (httpResponseExecute.getStatusLine().getStatusCode() == 302) {
                            this.httpstatus = UiMessage.CommandToUi.Command_Type.SET_VISIBLE_VALUE;
                            this.strResult = httpResponseExecute.getLastHeader("Location").toString().substring(9).trim();
                            this.commonLog.m1348e("strResult>>>" + this.strResult);
                        } else {
                            this.httpstatus = -99;
                            this.strResult = "请求错误>>>> " + httpResponseExecute.getStatusLine().toString();
                            this.strResult = "未知错误，请检查网络后访问！";
                        }
                    } catch (UnknownHostException e) {
                        this.strResult = e.getMessage();
                        this.commonLog.m1348e("UnknownHostException>>>" + this.strResult);
                        this.strResult = "访问不到服务器，请检查网络！";
                        this.commonLog.m1348e("UnknownHostException>>>" + this.strResult);
                    }
                } catch (ClientProtocolException e2) {
                    this.strResult = e2.getMessage();
                    this.commonLog.m1348e("ClientProtocolException>>>" + this.strResult);
                    this.strResult = "协议错误！";
                    this.commonLog.m1348e("ClientProtocolException>>>" + this.strResult);
                } catch (IOException e3) {
                    this.strResult = e3.getMessage();
                    this.commonLog.m1348e("IOException>>>" + this.strResult);
                    this.strResult = "IO异常！";
                    this.commonLog.m1348e("IOException>>>" + this.strResult);
                }
            } catch (ConnectTimeoutException e4) {
                this.strResult = e4.getMessage();
                this.commonLog.m1348e("ConnectTimeoutException>>>" + this.strResult);
                this.strResult = "连接超时！";
                this.commonLog.m1348e("ConnectTimeoutException>>>" + this.strResult);
            } catch (Exception e5) {
                this.strResult = e5.getMessage();
                this.commonLog.m1348e("Exception>>>" + this.strResult);
                this.strResult = "未知错误，请检查网络后访问！";
                this.commonLog.m1348e("Exception>>>" + this.strResult);
            }
            httpGet.abort();
            shutdownHttpClient();
            this.commonLog.m1348e("返回结果>>>>" + this.strResult);
            return this.strResult;
        } catch (Throwable th) {
            httpGet.abort();
            shutdownHttpClient();
            throw th;
        }
    }

    public String doPost(String str, List<NameValuePair> list) {
        if (this.androidHttpClient == null) {
            this.androidHttpClient = AndroidHttpClient.newInstance("Android");
        }
        HttpPost httpPost = new HttpPost(str);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list, "GBK"));
            HttpResponse httpResponseExecute = this.androidHttpClient.execute(httpPost);
            int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
            this.commonLog.m1348e("statusCode>>>" + statusCode);
            if (statusCode == 200) {
                this.httpstatus = 200;
                this.strResult = EntityUtils.toString(httpResponseExecute.getEntity());
            } else if (statusCode == 500) {
                this.httpstatus = SplashFragment.DELAY_500_MILLIS;
                this.strResult = "服务器内部错误！";
                this.commonLog.m1348e("服务器内部错误>>>" + this.strResult);
            } else if (statusCode == 408) {
                this.httpstatus = UiMessage.CommandToUi.Command_Type.GET_VALUE_VALUE;
                this.strResult = "请求超时！";
                this.commonLog.m1348e(" 请求超时>>>" + this.strResult);
            } else {
                this.httpstatus = -99;
                this.strResult = "请求错误>>>> " + httpResponseExecute.getStatusLine().toString();
                this.commonLog.m1348e("else>>>" + this.strResult);
                this.strResult = "未知错误，请检查网络后访问！";
                this.commonLog.m1348e("else>>>" + this.strResult);
            }
        } catch (IOException e) {
            this.strResult = e.getMessage();
            this.commonLog.m1348e("IOException>>>" + this.strResult);
            this.strResult = "IO异常！";
            this.commonLog.m1348e("IOException>>>" + this.strResult);
        } catch (Exception e2) {
            this.strResult = e2.getMessage();
            this.commonLog.m1348e("Exception>>>" + this.strResult);
            this.strResult = "未知错误，请检查网络后访问！";
            this.commonLog.m1348e("Exception>>>" + this.strResult);
        } catch (ClientProtocolException e3) {
            this.strResult = e3.getMessage();
            this.commonLog.m1348e("ClientProtocolException>>>" + this.strResult);
            this.strResult = "协议错误！";
            this.commonLog.m1348e("ClientProtocolException>>>" + this.strResult);
        } catch (UnknownHostException e4) {
            this.strResult = e4.getMessage();
            this.commonLog.m1348e("UnknownHostException>>>" + this.strResult);
            this.strResult = "访问不到服务器，请检查网络！";
            this.commonLog.m1348e("UnknownHostException>>>" + this.strResult);
        } catch (ConnectTimeoutException e5) {
            this.strResult = e5.getMessage();
            this.commonLog.m1348e("ConnectTimeoutException>>>" + this.strResult);
            this.strResult = "连接超时！";
            this.commonLog.m1348e("ConnectTimeoutException>>>" + this.strResult);
        } finally {
            httpPost.abort();
            shutdownHttpClient();
        }
        return this.strResult;
    }

    public static String generateUrl(String str, List<NameValuePair> list) {
        String string = "";
        if (list == null) {
            list = new ArrayList<>();
        }
        for (NameValuePair nameValuePair : list) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                String str2 = "&" + nameValuePair.getName() + "=" + URLEncoder.encode(nameValuePair.getValue(), "UTF-8");
                try {
                    sb.append(str2);
                    string = sb.toString();
                } catch (UnsupportedEncodingException e) {
                    e = e;
                    string = str2;
                    ThrowableExtension.printStackTrace(e);
                }
            } catch (UnsupportedEncodingException e2) {
                e = e2;
            }
        }
        if (string.equals("")) {
            return str;
        }
        return str + string.replaceFirst("&", "?");
    }

    public void shutdownHttpClient() {
        if (this.androidHttpClient != null) {
            if (this.androidHttpClient.getConnectionManager() != null) {
                this.androidHttpClient.getConnectionManager().shutdown();
            }
            this.androidHttpClient.close();
            this.androidHttpClient = null;
        }
    }
}
