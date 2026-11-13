package com.cyjh.share.net.volley;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VollerHttpManager {
    public static final String TAG = "VollerHttpManager";
    private static VollerHttpManager manager;
    private Map<Context, HashSet<Request>> mContextRequestMap = new HashMap();
    private RequestQueue mQueue;

    private VollerHttpManager() {
    }

    public static VollerHttpManager getInstance() {
        if (manager == null) {
            manager = new VollerHttpManager();
        }
        return manager;
    }

    public void init(Context context) {
        if (this.mQueue == null) {
            this.mQueue = Volley.newRequestQueue(context);
        }
    }

    public void init(RequestQueue requestQueue) {
        this.mQueue = requestQueue;
    }

    public void addRequest(Object obj, Request request) {
        if (this.mQueue != null) {
            stopRequest(obj);
            request.setTag(obj);
            this.mQueue.add(request);
            this.mQueue.start();
            return;
        }
        Log.i(TAG, "你应该在 application中初始化队列");
    }

    public void addRequest(Context context, Object obj, Request request) {
        if (this.mQueue != null) {
            if (context != null) {
                HashSet<Request> hashSet = this.mContextRequestMap.get(context);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                }
                hashSet.add(request);
            }
            stopRequest(obj);
            request.setTag(obj);
            this.mQueue.add(request);
            return;
        }
        Log.i(TAG, "你应该在 application中初始化队列");
    }

    public void stopRequest(Object obj) {
        if (this.mQueue != null) {
            this.mQueue.cancelAll(obj);
        } else {
            Log.i(TAG, "你应该在 application中初始化队列");
        }
    }

    public void stopRequest(Context context) {
        if (this.mQueue != null) {
            HashSet<Request> hashSet = this.mContextRequestMap.get(context);
            if (hashSet != null) {
                Iterator<Request> it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    this.mQueue.cancelAll(it2.next().getTag());
                }
                return;
            }
            return;
        }
        Log.i(TAG, "你应该在 application中初始化队列");
    }
}
