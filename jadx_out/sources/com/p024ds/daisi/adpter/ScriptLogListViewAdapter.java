package com.p024ds.daisi.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.ScriptLog;
import com.p024ds.daisi.log.CommonLog;
import com.warnship.autoplay.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ScriptLogListViewAdapter extends BaseAdapter {
    private AppContext appContext;
    private CommonLog commonLog = new CommonLog();
    private Context context;
    private boolean isManage;
    private LayoutInflater layoutInflater;
    private ArrayList<ScriptLog> scriptLogList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ScriptLogListViewAdapter(Context context, ArrayList<ScriptLog> arrayList) {
        this.context = context;
        this.appContext = (AppContext) context.getApplicationContext();
        this.scriptLogList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.scriptLogList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.scriptLogList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.litview_item_scriptlog, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tvSize = (TextView) view.findViewById(R.id.tv_listviewitem_scriptlog_size);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_listviewitem_scriptlog_name);
            viewHolder.cbSelect = (CheckBox) view.findViewById(R.id.cb_listviewitem_scriptlog_select);
            viewHolder.imgArrow = (ImageView) view.findViewById(R.id.img_listviewitem_scriptlog_select);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ScriptLog scriptLog = this.scriptLogList.get(i);
        if (this.isManage) {
            viewHolder.cbSelect.setVisibility(0);
            viewHolder.imgArrow.setVisibility(8);
        } else {
            viewHolder.cbSelect.setVisibility(8);
            viewHolder.imgArrow.setVisibility(0);
        }
        viewHolder.tvSize.setText(scriptLog.getSize());
        viewHolder.tvName.setText(scriptLog.getName());
        viewHolder.cbSelect.setOnCheckedChangeListener(new OnCheckedChangeListener(i));
        viewHolder.cbSelect.setChecked(scriptLog.isSelect());
        return view;
    }

    static class ViewHolder {
        CheckBox cbSelect;
        ImageView imgArrow;
        TextView tvName;
        TextView tvSize;

        ViewHolder() {
        }
    }

    private class OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private int position;

        public OnCheckedChangeListener(int i) {
            this.position = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ((ScriptLog) ScriptLogListViewAdapter.this.scriptLogList.get(this.position)).setSelect(z);
        }
    }

    public boolean isManage() {
        return this.isManage;
    }

    public void setManage(boolean z) {
        this.isManage = z;
    }
}
