package com.androidquery.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Constants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.Locale;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONObject;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: classes.dex */
public class MarketService {
    private static final String BULLET = "•";
    public static final int MAJOR = 2;
    public static final int MINOR = 1;
    public static final int REVISION = 0;
    private static final String SKIP_VERSION = "aqs.skip";

    /* JADX INFO: renamed from: ai */
    private static ApplicationInfo f61ai;

    /* JADX INFO: renamed from: pi */
    private static PackageInfo f62pi;
    private Activity act;

    /* JADX INFO: renamed from: aq */
    private AQuery f63aq;
    private boolean completed;
    private boolean fetch;
    private boolean force;
    private int progress;
    private String version;
    private long expire = 720000;
    private int level = 0;
    private Handler handler = new Handler(this, null);
    private String locale = Locale.getDefault().toString();
    private String rateUrl = getMarketUrl();
    private String updateUrl = this.rateUrl;

    private String getHost() {
        return "https://androidquery.appspot.com";
    }

    public MarketService(Activity activity) {
        this.act = activity;
        this.f63aq = new AQuery(activity);
    }

    public MarketService rateUrl(String str) {
        this.rateUrl = str;
        return this;
    }

    public MarketService level(int i) {
        this.level = i;
        return this;
    }

    public MarketService updateUrl(String str) {
        this.updateUrl = str;
        return this;
    }

    public MarketService locale(String str) {
        this.locale = str;
        return this;
    }

    public MarketService progress(int i) {
        this.progress = i;
        return this;
    }

    public MarketService force(boolean z) {
        this.force = z;
        return this;
    }

    public MarketService expire(long j) {
        this.expire = j;
        return this;
    }

    private ApplicationInfo getApplicationInfo() {
        if (f61ai == null) {
            f61ai = this.act.getApplicationInfo();
        }
        return f61ai;
    }

    private PackageInfo getPackageInfo() {
        if (f62pi == null) {
            try {
                f62pi = this.act.getPackageManager().getPackageInfo(getAppId(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return f62pi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getQueryUrl() {
        String str = String.valueOf(getHost()) + "/api/market?app=" + getAppId() + "&locale=" + this.locale + "&version=" + getVersion() + "&code=" + getVersionCode() + "&aq=" + Constants.VERSION;
        if (!this.force) {
            return str;
        }
        return String.valueOf(str) + "&force=true";
    }

    private String getAppId() {
        return getApplicationInfo().packageName;
    }

    private Drawable getAppIcon() {
        return getApplicationInfo().loadIcon(this.act.getPackageManager());
    }

    private String getVersion() {
        return getPackageInfo().versionName;
    }

    private int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public void checkVersion() {
        String queryUrl = getQueryUrl();
        AjaxCallback ajaxCallback = new AjaxCallback();
        ajaxCallback.url(queryUrl).type(JSONObject.class).handler(this.handler, "marketCb").fileCache(!this.force).expire(this.expire);
        this.f63aq.progress(this.progress).ajax(ajaxCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean openUrl(Activity activity, String str) {
        if (str == null) {
            return false;
        }
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private String getMarketUrl() {
        return "market://details?id=" + getAppId();
    }

    protected void callback(String str, JSONObject jSONObject, AjaxStatus ajaxStatus) {
        if (jSONObject == null) {
            return;
        }
        String strOptString = jSONObject.optString("version", AccountManager.TYPE_LOGIN);
        int iOptInt = jSONObject.optInt("code", 0);
        AQUtility.debug("version", String.valueOf(getVersion()) + "->" + strOptString + ":" + getVersionCode() + "->" + iOptInt);
        AQUtility.debug("outdated", Boolean.valueOf(outdated(strOptString, iOptInt)));
        if (this.force || outdated(strOptString, iOptInt)) {
            showUpdateDialog(jSONObject);
        }
    }

    private boolean outdated(String str, int i) {
        if (str.equals(getSkipVersion(this.act))) {
            return false;
        }
        String version = getVersion();
        int versionCode = getVersionCode();
        if (version.equals(str) || versionCode > i) {
            return false;
        }
        return requireUpdate(version, str, this.level);
    }

    private boolean requireUpdate(String str, String str2, int i) {
        if (str.equals(str2)) {
            return false;
        }
        try {
            String[] strArrSplit = str.split("\\.");
            String[] strArrSplit2 = str2.split("\\.");
            if (strArrSplit.length >= 3 && strArrSplit2.length >= 3) {
                switch (i) {
                    case 0:
                        if (!strArrSplit[strArrSplit.length - 1].equals(strArrSplit2[strArrSplit2.length - 1])) {
                            return true;
                        }
                    case 1:
                        if (!strArrSplit[strArrSplit.length - 2].equals(strArrSplit2[strArrSplit2.length - 2])) {
                            return true;
                        }
                    case 2:
                        return !strArrSplit[strArrSplit.length - 3].equals(strArrSplit2[strArrSplit2.length - 3]);
                    default:
                        return true;
                }
            }
            return true;
        } catch (Exception e) {
            AQUtility.report(e);
            return true;
        }
    }

    protected void showUpdateDialog(JSONObject jSONObject) {
        if (jSONObject != null && this.version == null && isActive()) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("dialog");
            String strOptString = jSONObjectOptJSONObject.optString("update", URLConstant.UPDATE_REQUEST_NAME);
            String strOptString2 = jSONObjectOptJSONObject.optString("skip", "Skip");
            String strOptString3 = jSONObjectOptJSONObject.optString("rate", "Rate");
            String strOptString4 = jSONObjectOptJSONObject.optString("wbody", "");
            String strOptString5 = jSONObjectOptJSONObject.optString("title", "Update Available");
            AQUtility.debug("wbody", strOptString4);
            this.version = jSONObject.optString("version", null);
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.act).setIcon(getAppIcon()).setTitle(strOptString5).setPositiveButton(strOptString3, this.handler).setNeutralButton(strOptString2, this.handler).setNegativeButton(strOptString, this.handler).create();
            alertDialogCreate.setMessage(Html.fromHtml(patchBody(strOptString4), null, this.handler));
            this.f63aq.show(alertDialogCreate);
        }
    }

    private static String patchBody(String str) {
        return "<small>" + str + "</small>";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setSkipVersion(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(SKIP_VERSION, str).commit();
    }

    private static String getSkipVersion(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SKIP_VERSION, null);
    }

    private boolean isActive() {
        return !this.act.isFinishing();
    }

    private class Handler implements DialogInterface.OnClickListener, Html.TagHandler {
        private Handler() {
        }

        /* synthetic */ Handler(MarketService marketService, Handler handler) {
            this();
        }

        public void marketCb(String str, JSONObject jSONObject, AjaxStatus ajaxStatus) {
            if (MarketService.this.act.isFinishing()) {
                return;
            }
            if (jSONObject != null) {
                String strOptString = jSONObject.optString("status");
                if ("1".equals(strOptString)) {
                    if (jSONObject.has("dialog")) {
                        m23cb(str, jSONObject, ajaxStatus);
                    }
                    if (!MarketService.this.fetch && jSONObject.optBoolean("fetch", false) && ajaxStatus.getSource() == 1) {
                        MarketService.this.fetch = true;
                        String strOptString2 = jSONObject.optString("marketUrl", null);
                        AjaxCallback ajaxCallback = new AjaxCallback();
                        ajaxCallback.url(strOptString2).type(String.class).handler(this, "detailCb");
                        MarketService.this.f63aq.progress(MarketService.this.progress).ajax(ajaxCallback);
                        return;
                    }
                    return;
                }
                if (AccountManager.TYPE_LOGIN.equals(strOptString)) {
                    ajaxStatus.invalidate();
                    return;
                } else {
                    m23cb(str, jSONObject, ajaxStatus);
                    return;
                }
            }
            m23cb(str, jSONObject, ajaxStatus);
        }

        /* JADX INFO: renamed from: cb */
        private void m23cb(String str, JSONObject jSONObject, AjaxStatus ajaxStatus) {
            if (MarketService.this.completed) {
                return;
            }
            MarketService.this.completed = true;
            MarketService.this.progress = 0;
            MarketService.this.callback(str, jSONObject, ajaxStatus);
        }

        public void detailCb(String str, String str2, AjaxStatus ajaxStatus) {
            if (str2 == null || str2.length() <= 1000) {
                return;
            }
            String queryUrl = MarketService.this.getQueryUrl();
            AjaxCallback ajaxCallback = new AjaxCallback();
            ajaxCallback.url(queryUrl).type(JSONObject.class).handler(this, "marketCb");
            ajaxCallback.param(EmailConstants.TEXT_SUBTYPE_HTML, str2);
            MarketService.this.f63aq.progress(MarketService.this.progress).ajax(ajaxCallback);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case -3:
                    MarketService.setSkipVersion(MarketService.this.act, MarketService.this.version);
                    break;
                case -2:
                    MarketService.openUrl(MarketService.this.act, MarketService.this.updateUrl);
                    break;
                case -1:
                    MarketService.openUrl(MarketService.this.act, MarketService.this.rateUrl);
                    break;
            }
        }

        @Override // android.text.Html.TagHandler
        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if ("li".equals(str)) {
                if (z) {
                    editable.append("  ");
                    editable.append(MarketService.BULLET);
                    editable.append("  ");
                    return;
                }
                editable.append("\n");
            }
        }
    }
}
