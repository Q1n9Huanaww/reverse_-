package com.p024ds.daisi.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyjh.share.bean.NotifyMsgBean;
import com.squareup.picasso.Picasso;
import com.warnship.autoplay.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MsgNotifyAdapter extends BaseAdapter {
    private List<NotifyMsgBean> data;
    private Context mContext;
    private LayoutInflater mInflater;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MsgNotifyAdapter(Context context, List<NotifyMsgBean> list) {
        this.mContext = context;
        this.data = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.data.get(i);
    }

    public void clearData() {
        if (this.data != null && this.data.size() > 0) {
            this.data.clear();
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHold viewHold;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.adapter_msg_notify, (ViewGroup) null);
            viewHold = new ViewHold();
            viewHold.mMsgName = (TextView) view.findViewById(R.id.id_tv_msg_title);
            viewHold.mMsgTime = (TextView) view.findViewById(R.id.id_tv_msg_time);
            viewHold.mMsgDesc = (TextView) view.findViewById(R.id.id_tv_msg_des);
            viewHold.mImg = (ImageView) view.findViewById(R.id.id_img_msg);
            view.setTag(viewHold);
        } else {
            viewHold = (ViewHold) view.getTag();
        }
        viewHold.mMsgName.setText(this.data.get(i).NoticeTitle);
        viewHold.mMsgTime.setText(this.data.get(i).NoticeTime);
        if (!this.data.get(i).DescriptionType.equals(String.valueOf(1))) {
            if (this.data.get(i).DescriptionType.equals(String.valueOf(2))) {
                viewHold.mImg.setVisibility(0);
                viewHold.mMsgDesc.setVisibility(8);
                Picasso.with(this.mContext).load(this.data.get(i).NoticeDescription).fit().error(R.drawable.img_default_msg_bg).placeholder(R.drawable.img_default_msg_bg).into(viewHold.mImg);
            }
        } else {
            viewHold.mImg.setVisibility(8);
            viewHold.mMsgDesc.setVisibility(0);
            viewHold.mMsgDesc.setText(this.data.get(i).NoticeDescription);
        }
        return view;
    }

    class ViewHold {
        private ImageView mImg;
        private TextView mMsgDesc;
        private TextView mMsgName;
        private TextView mMsgTime;

        ViewHold() {
        }
    }
}
