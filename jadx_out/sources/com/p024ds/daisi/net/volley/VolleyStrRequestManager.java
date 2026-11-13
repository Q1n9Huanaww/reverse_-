package com.p024ds.daisi.net.volley;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.async.NetworkReqMessageTask;
import com.p024ds.daisi.constant.Constants;
import com.warnship.autoplay.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class VolleyStrRequestManager {
    public OnRequestErrorListener mVolleyErrorListener;
    public OnStrReqSuccessListener mVolleySuccessListener;
    public String networkTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + "\t\trequest begin";

    public abstract void onRequestErrorRes(String str);

    public abstract void onRequestSuccessRes(String str);

    public VolleyStrRequestManager(Context context) {
        this.mVolleyErrorListener = new OnRequestErrorListener(context);
        this.mVolleySuccessListener = new OnStrReqSuccessListener(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OnStrReqSuccessListener implements Response.Listener<String> {
        private Context context;

        public OnStrReqSuccessListener(Context context) {
            this.context = context;
        }

        @Override // com.android.volley.Response.Listener
        public void onResponse(String str) {
            new NetworkReqMessageTask(("\t\t\t" + VolleyStrRequestManager.this.networkTime + "\nnetwork request successful response message\n") + str + "\n\t\t\t\trequest end\n\n\n", this.context).execute(Constants.getSaveFilePath(this.context, Constants.NETWORK_MESSAGE, Constants.NETWORK_REQUEST_LOG));
            VolleyStrRequestManager.this.onRequestSuccessRes(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OnRequestErrorListener implements Response.ErrorListener {
        private Context context = AppContext.getInstance();

        public OnRequestErrorListener(Context context) {
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            String string;
            String str;
            if (volleyError instanceof ServerError) {
                string = this.context.getString(R.string.network_server_error);
            } else if (volleyError instanceof NoConnectionError) {
                string = this.context.getString(R.string.network_no_connection_error);
            } else if (volleyError instanceof NetworkError) {
                string = this.context.getString(R.string.network_network_error);
            } else if (volleyError instanceof ParseError) {
                string = this.context.getString(R.string.network_parse_error);
            } else if (volleyError instanceof AuthFailureError) {
                string = this.context.getString(R.string.network_auth_failure_error);
            } else {
                string = volleyError instanceof TimeoutError ? this.context.getString(R.string.network_timeout_error) : null;
            }
            String str2 = "\t\t\t" + VolleyStrRequestManager.this.networkTime + "\nnetwork request error message\n" + string + "\n" + volleyError.toString() + "\t" + volleyError.getMessage();
            Logger.m3442e(volleyError.getMessage() + "::" + volleyError.toString() + ":" + volleyError.networkResponse, new Object[0]);
            try {
                if (volleyError.networkResponse != null) {
                    Logger.m3442e("Server response status code:" + volleyError.networkResponse.statusCode, new Object[0]);
                    String str3 = str2 + "\nServer response status code:\t" + volleyError.networkResponse.statusCode;
                    try {
                        if (volleyError.networkResponse.data.length != 0) {
                            Logger.m3442e("Object return error message:" + new String(volleyError.networkResponse.data), new Object[0]);
                            str2 = str3 + "\nreturn error message:\t" + new String(volleyError.networkResponse.data);
                        } else {
                            str2 = str3;
                        }
                    } catch (Exception e) {
                        e = e;
                        str2 = str3;
                        Logger.m3442e("networkException:" + e.getMessage() + "\t," + e.toString(), new Object[0]);
                        str = str2 + "\n" + e.getMessage() + "\t," + e.toString();
                        new NetworkReqMessageTask(str + "\n\t\t\t\trequest end\n\n\n", this.context).execute(Constants.getSaveFilePath(this.context, Constants.NETWORK_MESSAGE, Constants.NETWORK_REQUEST_LOG));
                    }
                }
                Logger.m3442e("NetworkTimeMs:" + volleyError.getNetworkTimeMs() + "ms", new Object[0]);
                str = str2 + "\nNetworkTimeMs:\t" + volleyError.getNetworkTimeMs() + "ms";
            } catch (Exception e2) {
                e = e2;
            }
            if (string == null) {
                string = this.context.getString(R.string.network_again_error);
            }
            new NetworkReqMessageTask(str + "\n\t\t\t\trequest end\n\n\n", this.context).execute(Constants.getSaveFilePath(this.context, Constants.NETWORK_MESSAGE, Constants.NETWORK_REQUEST_LOG));
            VolleyStrRequestManager.this.onRequestErrorRes(string);
        }
    }
}
