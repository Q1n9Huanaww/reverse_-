package com.cyjh.feedback.lib.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.cyjh.feedback.lib.utils.ImageLoader;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ImageSelectAdapter extends BaseAdapter {
    private Context context;
    private List<String> mImgPaths;
    private LayoutInflater mInflater;
    private OnSelectImgNumber mSelectImgNumber;
    private Set<String> mSetSelect = new HashSet();

    public interface OnSelectImgNumber {
        void isSelectImg(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ImageSelectAdapter(Context context, List<String> list) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mImgPaths = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mImgPaths.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mImgPaths.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate;
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            viewInflate = this.mInflater.inflate(C0738R.layout.image_adapter_item, viewGroup, false);
            viewHolder.imageButton = (ImageButton) viewInflate.findViewById(C0738R.id.id_img_select_grid_item);
            viewHolder.imageView = (ImageView) viewInflate.findViewById(C0738R.id.id_img_grid_item);
            viewInflate.setTag(viewHolder);
        } else {
            viewInflate = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(C0738R.mipmap.pictures_no);
        final String str = this.mImgPaths.get(i);
        ImageLoader.getInstance(ImageLoader.Type.FILO, 3).imageLoader(str, viewHolder.imageView);
        viewHolder.imageView.setColorFilter((ColorFilter) null);
        viewHolder.imageButton.setVisibility(4);
        if (this.mSetSelect.contains(str)) {
            viewHolder.imageView.setColorFilter(Color.parseColor("#77000000"));
            viewHolder.imageButton.setVisibility(0);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.feedback.lib.adapter.ImageSelectAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if ((new File(str).length() / 1024) - PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH > 0) {
                    Toast.makeText(ImageSelectAdapter.this.context, ImageSelectAdapter.this.context.getString(C0738R.string.img_content_too_large), 1).show();
                    return;
                }
                if (ImageSelectAdapter.this.mSetSelect.contains(str)) {
                    ImageSelectAdapter.this.mSetSelect.remove(str);
                    viewHolder.imageView.setColorFilter((ColorFilter) null);
                    viewHolder.imageButton.setVisibility(4);
                } else if (ImageSelectAdapter.this.mSetSelect.size() > 3) {
                    Toast.makeText(ImageSelectAdapter.this.context, ImageSelectAdapter.this.context.getString(C0738R.string.img_count_more), 1).show();
                    return;
                } else {
                    ImageSelectAdapter.this.mSetSelect.add(str);
                    viewHolder.imageView.setColorFilter(Color.parseColor("#77000000"));
                    viewHolder.imageButton.setVisibility(0);
                }
                if (ImageSelectAdapter.this.mSelectImgNumber != null) {
                    ImageSelectAdapter.this.mSelectImgNumber.isSelectImg(ImageSelectAdapter.this.mSetSelect.size());
                }
            }
        });
        return viewInflate;
    }

    public Set<String> getmSetSelect() {
        return this.mSetSelect;
    }

    class ViewHolder {
        ImageButton imageButton;
        ImageView imageView;

        ViewHolder() {
        }
    }

    public void setOnSelectImgNumber(OnSelectImgNumber onSelectImgNumber) {
        this.mSelectImgNumber = onSelectImgNumber;
    }
}
