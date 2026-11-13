package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import com.google.gson.Gson;
import com.p024ds.daisi.entity.LotteryDataEntry;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.TokenBillManager;
import com.p024ds.daisi.mvp.views.TokenBillView;
import com.p024ds.daisi.net.volley.VolleyRequestQueue;

/* JADX INFO: loaded from: classes.dex */
public class TokenBillPresent implements BasePresenter {
    private TokenBillManager tokenBillManager = new TokenBillManager();
    private TokenBillView tokenBillView;

    public TokenBillPresent(TokenBillView tokenBillView) {
        this.tokenBillView = tokenBillView;
    }

    public void getCodes(Context context) {
        this.tokenBillManager.getCodes(context, BlackListPresenter.class.getCanonicalName(), new Callback<String>() { // from class: com.ds.daisi.mvp.presenters.TokenBillPresent.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(String str) {
                LotteryDataEntry lotteryDataEntry = (LotteryDataEntry) new Gson().fromJson(str, LotteryDataEntry.class);
                if (!lotteryDataEntry.Msg.equals("成功") || lotteryDataEntry.Codes.Code1.size() <= 0) {
                    TokenBillPresent.this.tokenBillView.getLotteryArray("");
                } else {
                    TokenBillPresent.this.tokenBillView.getLotteryArray(str);
                }
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                TokenBillPresent.this.tokenBillView.getLotteryByFail(str);
            }
        });
    }

    public void cancelReq() {
        VolleyRequestQueue.getRequestQueue().cancelAll(BlackListPresenter.class.getCanonicalName());
    }
}
