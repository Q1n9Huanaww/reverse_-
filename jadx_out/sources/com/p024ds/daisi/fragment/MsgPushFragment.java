package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyjh.share.bean.NotifyMsgBean;
import com.p024ds.daisi.activity.MessageDetailActivity;
import com.p024ds.daisi.adpter.MsgNotifyAdapter;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.entity.MsgItem;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class MsgPushFragment extends BaseFragement {
    private MsgNotifyAdapter mAdapter;
    private List<NotifyMsgBean> mDataMsgList;
    private ListView mListView;
    private TextView mTvNoMsg;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_msg_push;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        EventBus.getDefault().register(this);
        if (arguments != null) {
            this.mDataMsgList = arguments.getParcelableArrayList(Constants.MSG_DATA);
        }
        initView(view);
        isShowView();
        initEvent();
    }

    public static MsgPushFragment newInstance(ArrayList<NotifyMsgBean> arrayList) {
        MsgPushFragment msgPushFragment = new MsgPushFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.MSG_DATA, arrayList);
        msgPushFragment.setArguments(bundle);
        return msgPushFragment;
    }

    private void initEvent() {
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ds.daisi.fragment.MsgPushFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                NotifyMsgBean notifyMsgBean = (NotifyMsgBean) MsgPushFragment.this.mDataMsgList.get(i);
                Intent intent = new Intent(MsgPushFragment.this.getActivity(), (Class<?>) MessageDetailActivity.class);
                intent.putExtra(MessageDetailActivity.class.getCanonicalName(), Long.parseLong(notifyMsgBean.f1679ID));
                MsgPushFragment.this.startActivity(intent);
            }
        });
    }

    private void isShowView() {
        if (this.mDataMsgList != null && this.mDataMsgList.size() > 0) {
            this.mTvNoMsg.setVisibility(8);
            this.mListView.setVisibility(0);
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
        } else {
            this.mTvNoMsg.setVisibility(0);
            this.mListView.setVisibility(8);
        }
    }

    private void initView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.id_list_view);
        this.mTvNoMsg = (TextView) view.findViewById(R.id.id_tv_no_msg);
        this.mAdapter = new MsgNotifyAdapter(getActivity(), this.mDataMsgList);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(MsgPushFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(MsgPushFragment.class.getCanonicalName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageMainThread(MsgItem msgItem) {
        if (msgItem.getMsgType() != 1002) {
            return;
        }
        this.mAdapter.clearData();
        this.mListView.setEmptyView(this.mTvNoMsg);
        EventBus.getDefault().post(new MsgItem(1003));
    }
}
