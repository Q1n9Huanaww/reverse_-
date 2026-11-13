package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import com.p024ds.daisi.mvp.managers.BlackListManager;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.views.BlackListView;
import com.p024ds.daisi.mvp.views.TokenBillView;
import com.p024ds.daisi.net.volley.VolleyRequestQueue;

/* JADX INFO: loaded from: classes.dex */
public class BlackListPresenter extends TokenBillPresent {
    private BlackListManager blackListManager;
    private BlackListView blackListView;

    public BlackListPresenter(TokenBillView tokenBillView) {
        super(tokenBillView);
        this.blackListView = (BlackListView) tokenBillView;
        this.blackListManager = new BlackListManager();
    }

    public void isSetBlackList(Context context) {
        this.blackListManager.isSetBlackList(context, BlackListPresenter.class.getSimpleName(), new Callback<String>() { // from class: com.ds.daisi.mvp.presenters.BlackListPresenter.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(String str) {
                BlackListPresenter.this.blackListView.setBlackListResult(str);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                BlackListPresenter.this.blackListView.getBlackError();
            }
        });
    }

    @Override // com.p024ds.daisi.mvp.presenters.TokenBillPresent
    public void cancelReq() {
        VolleyRequestQueue.getRequestQueue().cancelAll(BlackListPresenter.class.getSimpleName());
        VolleyRequestQueue.getRequestQueue().cancelAll(BlackListPresenter.class.getCanonicalName());
    }
}
