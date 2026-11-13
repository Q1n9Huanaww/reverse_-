package com.cyjh.feedback.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.cyjh.feedback.lib.utils.ImageLoader;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImageShowAdapter extends BaseAdapter {
    private static final int PIC_COUNT_DEFAULT = 1;
    private static final int PIC_COUNT_MAX = 4;
    private List<String> mData;
    private OnDeleteSelectImg mDeleteSelectImg;
    private LayoutInflater mInflater;

    public interface OnDeleteSelectImg {
        void onClick(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setmData(List<String> list) {
        this.mData = list;
    }

    public ImageShowAdapter(Context context, List<String> list) {
        this.mData = new ArrayList();
        this.mInflater = LayoutInflater.from(context);
        this.mData = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mData == null) {
            return 1;
        }
        if (this.mData.size() < 4) {
            return this.mData.size() + 1;
        }
        return 4;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.mData == null || this.mData.size() <= 0) {
            return null;
        }
        return this.mData.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(C0738R.layout.grid_view_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.f939iv = (ImageView) view.findViewById(C0738R.id.id_iv_grid_item);
            viewHolder.imgDeteleSelect = (ImageView) view.findViewById(C0738R.id.id_img_delete_select);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f939iv.setImageResource(C0738R.mipmap.img_add_img_select);
        viewHolder.imgDeteleSelect.setVisibility(4);
        if (this.mData != null && i < getCount() - 1) {
            ImageLoader.getInstance().imageLoader(this.mData.get(i), viewHolder.f939iv);
            viewHolder.imgDeteleSelect.setVisibility(0);
        }
        if (this.mData != null && this.mData.size() == 4) {
            ImageLoader.getInstance().imageLoader(this.mData.get(i), viewHolder.f939iv);
            viewHolder.imgDeteleSelect.setVisibility(0);
        }
        viewHolder.imgDeteleSelect.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.feedback.lib.adapter.ImageShowAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ImageShowAdapter.this.mData.remove(ImageShowAdapter.this.mData.get(i));
                ImageShowAdapter.this.notifyDataSetChanged();
                if (ImageShowAdapter.this.mDeleteSelectImg != null) {
                    ImageShowAdapter.this.mDeleteSelectImg.onClick(ImageShowAdapter.this.mData.size());
                }
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView imgDeteleSelect;

        /* JADX INFO: renamed from: iv */
        ImageView f939iv;

        ViewHolder() {
        }
    }

    public void setmDeleteSelectImg(OnDeleteSelectImg onDeleteSelectImg) {
        this.mDeleteSelectImg = onDeleteSelectImg;
    }
}
