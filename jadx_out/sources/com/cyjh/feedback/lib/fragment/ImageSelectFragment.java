package com.cyjh.feedback.lib.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.cyjh.feedback.lib.adapter.ImageSelectAdapter;
import com.cyjh.feedback.lib.base.BaseFragment;
import com.cyjh.feedback.lib.entities.FolderBean;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImageSelectFragment extends BaseFragment implements View.OnClickListener {
    private static final int LOAD_DEFAULT = 272;
    private static final String SEPERATOR = ",";
    private ImageSelectAdapter mAdapter;
    private Button mButtonSure;
    private List<FolderBean> mFolderList;
    private GridView mGridView;
    private Handler mHandler = new Handler() { // from class: com.cyjh.feedback.lib.fragment.ImageSelectFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            FolderBean folderBean = (FolderBean) message.obj;
            ImageSelectFragment.this.mProgressDialog.dismiss();
            ImageSelectFragment.this.data2View(folderBean);
        }
    };
    private List<String> mImg;
    private File mImgDir;
    private int mPicSize;
    private ProgressDialog mProgressDialog;
    private ArrayList<String> mdatas;

    /* JADX INFO: Access modifiers changed from: private */
    public void data2View(FolderBean folderBean) {
        if (TextUtils.isEmpty(folderBean.getDirPath())) {
            Toast.makeText(getActivity(), getString(C0738R.string.no_everything_picture), 0).show();
            Logger.m3442e("folderBean.getDirPath()", new Object[0]);
            return;
        }
        if (this.mImgDir == null) {
            Toast.makeText(getActivity(), getString(C0738R.string.no_everything_picture), 1).show();
            Logger.m3442e("mImgDir == null", new Object[0]);
        } else if (this.mImg != null && this.mImg.size() <= 0) {
            Toast.makeText(getActivity(), getString(C0738R.string.no_everything_picture), 1).show();
            Logger.m3442e("mImg != null && mImg.size() <= 0", new Object[0]);
        } else {
            this.mAdapter = new ImageSelectAdapter(getActivity(), this.mImg);
            this.mGridView.setAdapter((ListAdapter) this.mAdapter);
            this.mAdapter.notifyDataSetChanged();
            initEvent();
        }
    }

    public static ImageSelectFragment newInstance() {
        return new ImageSelectFragment();
    }

    @Override // com.cyjh.feedback.lib.base.BaseFragment
    public int getFragmentLayoutId() {
        return C0738R.layout.fragment_select_image;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
    }

    private void initEvent() {
        this.mAdapter.setOnSelectImgNumber(new ImageSelectAdapter.OnSelectImgNumber() { // from class: com.cyjh.feedback.lib.fragment.ImageSelectFragment.2
            @Override // com.cyjh.feedback.lib.adapter.ImageSelectAdapter.OnSelectImgNumber
            public void isSelectImg(int i) {
                ImageSelectFragment.this.mButtonSure.setText(ImageSelectFragment.this.getString(C0738R.string.btn_sure, Integer.valueOf(i)));
            }
        });
    }

    private void initView(View view) {
        this.mdatas = new ArrayList<>();
        this.mGridView = (GridView) view.findViewById(C0738R.id.grid_view);
        this.mImg = new ArrayList();
        this.mFolderList = new ArrayList();
        this.mButtonSure = (Button) view.findViewById(C0738R.id.id_btn_sure);
        this.mButtonSure.setText(getString(C0738R.string.btn_sure, Integer.valueOf(this.mdatas.size())));
        this.mButtonSure.setOnClickListener(this);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.cyjh.feedback.lib.fragment.ImageSelectFragment$3] */
    private void initData() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            Toast.makeText(getActivity(), getString(C0738R.string.no_sdk), 1).show();
        } else {
            this.mProgressDialog = ProgressDialog.show(getActivity(), null, getString(C0738R.string.loading_somethings));
            new Thread() { // from class: com.cyjh.feedback.lib.fragment.ImageSelectFragment.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Cursor cursorQuery = ImageSelectFragment.this.getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "mime_type= ? or mime_type=?", new String[]{"image/jpeg", "image/png"}, "date_modified");
                    HashSet hashSet = new HashSet();
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
                        File parentFile = new File(string).getParentFile();
                        if (parentFile != null) {
                            String absolutePath = parentFile.getAbsolutePath();
                            if (!hashSet.contains(absolutePath)) {
                                hashSet.add(absolutePath);
                                FolderBean folderBean = new FolderBean();
                                folderBean.setDirPath(absolutePath);
                                folderBean.setFirstImgPath(string);
                                if (parentFile.list() != null) {
                                    int length = parentFile.list(new FilenameFilter() { // from class: com.cyjh.feedback.lib.fragment.ImageSelectFragment.3.1
                                        @Override // java.io.FilenameFilter
                                        public boolean accept(File file, String str) {
                                            return ImageSelectFragment.this.filterImgBySuffix(str);
                                        }
                                    }).length;
                                    if (length > ImageSelectFragment.this.mPicSize) {
                                        ImageSelectFragment.this.mPicSize = length;
                                        ImageSelectFragment.this.mImgDir = parentFile;
                                    }
                                    folderBean.setDirCount(length);
                                    ImageSelectFragment.this.mFolderList.add(folderBean);
                                }
                            }
                        }
                    }
                    cursorQuery.close();
                    FolderBean folderBean2 = new FolderBean();
                    if (ImageSelectFragment.this.mFolderList != null && ImageSelectFragment.this.mFolderList.size() > 0) {
                        folderBean2.setDirName(ImageSelectFragment.this.getString(C0738R.string.all_photo_mobile));
                        folderBean2.setFirstImgPath(((FolderBean) ImageSelectFragment.this.mFolderList.get(0)).getFirstImgPath());
                    }
                    String str = "";
                    int dirCount = 0;
                    for (FolderBean folderBean3 : ImageSelectFragment.this.mFolderList) {
                        str = str + folderBean3.getDirPath() + ImageSelectFragment.SEPERATOR;
                        dirCount += folderBean3.getDirCount();
                    }
                    folderBean2.setDirCount(dirCount);
                    folderBean2.setDirPath(str);
                    ImageSelectFragment.this.mFolderList.add(0, folderBean2);
                    ImageSelectFragment.this.gridViewDatas(folderBean2);
                    Message messageObtainMessage = ImageSelectFragment.this.mHandler.obtainMessage();
                    messageObtainMessage.what = 272;
                    messageObtainMessage.obj = folderBean2;
                    ImageSelectFragment.this.mHandler.sendMessage(messageObtainMessage);
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gridViewDatas(FolderBean folderBean) {
        this.mImg.clear();
        for (String str : folderBean.getDirPath().split(SEPERATOR)) {
            String[] list = new File(str).list(new FilenameFilter() { // from class: com.cyjh.feedback.lib.fragment.ImageSelectFragment.4
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return ImageSelectFragment.this.filterImgBySuffix(str2);
                }
            });
            if (list != null) {
                for (String str2 : list) {
                    this.mImg.add(str + File.separator + str2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean filterImgBySuffix(String str) {
        return str.toLowerCase().endsWith(".jpg") || str.toLowerCase().endsWith(".jpeg") || str.toLowerCase().endsWith(".png");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != C0738R.id.id_btn_sure || this.mAdapter == null) {
            return;
        }
        if (this.mAdapter.getmSetSelect().size() <= 0) {
            Toast.makeText(getActivity(), getString(C0738R.string.please_choose_photo), 1).show();
            return;
        }
        Iterator<String> it2 = this.mAdapter.getmSetSelect().iterator();
        while (it2.hasNext()) {
            this.mdatas.add(it2.next());
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("image", this.mdatas);
        intent.putExtra("bundle", bundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(ImageSelectFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(ImageSelectFragment.class.getCanonicalName());
    }
}
