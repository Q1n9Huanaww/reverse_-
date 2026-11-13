package com.cyjh.feedback.lib.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.cyjh.feedback.lib.base.BaseDialog;
import com.cyjh.feedback.lib.utils.NetUtils;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import com.cyjh.share.bean.request.FeedbackRequestInfo;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.presenter.FeedbackCommitDataPresenter;
import com.cyjh.share.mvp.presenter.GetTokenAliCloudPresenter;
import com.cyjh.share.mvp.view.FeedbackView;
import com.cyjh.share.oss.MyOSSUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeedBackDialog extends BaseDialog implements View.OnClickListener, FeedbackView {
    private static final int COMMIT_DATA_FAILURE = 0;
    private static final int COMMIT_DATA_SUCCESS = 1;
    private static final String DEFAULT = String.valueOf(0);
    private static final int NO_ROOT = 2;
    private static final int YES_ROOT = 1;
    private volatile int count;
    private List<String> imgList;
    private Button mBtnBack;
    private Button mBtnCommit;
    private FeedbackCommitDataPresenter mCommitDataPresenter;
    private Context mContext;
    private LoadingDialog mLoadingDialog;
    private FeedbackRequestInfo mRequestInfo;
    private TextView mTvDeicesName;
    private TextView mTvDevicesRoot;
    private TextView mTvDevicesSystem;
    private TextView mTvFeedBackContent;
    private OnResultCode onResultCode;

    public interface OnResultCode {
        void getResultCode(int i);
    }

    public FeedBackDialog(Context context, FeedbackRequestInfo feedbackRequestInfo, List<String> list) {
        super(context, C0738R.style.Theme_Dialog);
        this.count = 0;
        this.mRequestInfo = feedbackRequestInfo;
        this.mContext = context;
        this.imgList = list;
    }

    @Override // com.cyjh.feedback.lib.base.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0738R.layout.dialog_feedback_info);
        setBlurEffect(0.3f);
        setDialogSize(0.8f, 0.6f);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        this.mTvDeicesName = (TextView) findViewById(C0738R.id.id_tv_device_name);
        this.mTvDevicesSystem = (TextView) findViewById(C0738R.id.id_tv_device_system);
        this.mTvDevicesRoot = (TextView) findViewById(C0738R.id.id_tv_device_root);
        this.mTvFeedBackContent = (TextView) findViewById(C0738R.id.id_tv_feedback_content);
        this.mBtnBack = (Button) findViewById(C0738R.id.id_btn_back);
        this.mBtnCommit = (Button) findViewById(C0738R.id.id_btn_commit_feedback_info);
    }

    private void initData() {
        this.mTvDeicesName.setText(getTextPartColor(this.mContext.getString(C0738R.string.devices_name, this.mRequestInfo.DeviceModel), this.mRequestInfo.DeviceModel));
        this.mTvDevicesSystem.setText(getTextPartColor(this.mContext.getString(C0738R.string.devices_use_system, this.mRequestInfo.AndoridVersion), this.mRequestInfo.AndoridVersion));
        switch (this.mRequestInfo.RootStatus) {
            case 1:
                this.mTvDevicesRoot.setText(getTextPartColor(this.mContext.getString(C0738R.string.devices_is_root, this.mContext.getString(C0738R.string.devices_yes_root)), this.mContext.getString(C0738R.string.devices_yes_root)));
                break;
            case 2:
                this.mTvDevicesRoot.setText(getTextPartColor(this.mContext.getString(C0738R.string.devices_is_root, this.mContext.getString(C0738R.string.devices_no_root)), this.mContext.getString(C0738R.string.devices_no_root)));
                break;
        }
        if (this.mRequestInfo.Content.equals(DEFAULT)) {
            return;
        }
        this.mTvFeedBackContent.setText(this.mRequestInfo.Content);
    }

    private void initEvent() {
        this.mBtnCommit.setOnClickListener(this);
        this.mBtnBack.setOnClickListener(this);
    }

    private SpannableStringBuilder getTextPartColor(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        int length = str2.length() + iIndexOf;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#3c7ac5")), iIndexOf, length, 34);
        return spannableStringBuilder;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C0738R.id.id_btn_back) {
            dismiss();
            return;
        }
        if (id == C0738R.id.id_btn_commit_feedback_info) {
            try {
                if (!NetUtils.is3rd(this.mContext) && !NetUtils.isWifiEnabled(this.mContext)) {
                    Toast.makeText(this.mContext, this.mContext.getString(C0738R.string.is_net_open), 1).show();
                    return;
                }
                MyOSSUtils myOSSUtilsBuild = new MyOSSUtils.Builder(this.mContext, VariableAndConstantsManager.getInstance().mAliCloudServerResponse).build();
                if (myOSSUtilsBuild.isTokenExpireTime()) {
                    Toast.makeText(this.mContext, "阿里云Token过期，稍后再试！", 1).show();
                    new GetTokenAliCloudPresenter().load(this.mContext);
                    return;
                }
                if (this.mLoadingDialog == null) {
                    this.mLoadingDialog = new LoadingDialog(this.mContext);
                }
                this.mLoadingDialog.show();
                this.mCommitDataPresenter = new FeedbackCommitDataPresenter(this);
                if (this.imgList.size() > 0 && VariableAndConstantsManager.getInstance().mAliCloudServerResponse != null) {
                    final ArrayList arrayList = new ArrayList();
                    for (String str : this.imgList) {
                        myOSSUtilsBuild.uploadFile(str, VariableAndConstantsManager.getInstance().mAliCloudServerResponse.OssStoragePath + new File(str).getName(), new MyOSSUtils.UploadFileCallBack() { // from class: com.cyjh.feedback.lib.dialog.FeedBackDialog.1
                            @Override // com.cyjh.share.oss.MyOSSUtils.UploadFileCallBack
                            public void uploadProgress(long j, long j2) {
                            }

                            @Override // com.cyjh.share.oss.MyOSSUtils.UploadFileCallBack
                            public void uploadSuc(String str2, String str3) {
                                arrayList.add(str3);
                                FeedBackDialog.this.uploadImgSucNextOpera(arrayList);
                            }

                            @Override // com.cyjh.share.oss.MyOSSUtils.UploadFileCallBack
                            public void uploadFail(String str2) {
                                FeedBackDialog.this.uploadImgSucNextOpera(arrayList);
                            }
                        });
                    }
                    return;
                }
                this.mCommitDataPresenter.commitFeedback(this.mContext, this.mRequestInfo);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                Log.e("zzz", "FeedBackDialog--提交数据给后台：" + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadImgSucNextOpera(List<String> list) {
        this.count++;
        if (this.count == this.imgList.size()) {
            if (list.size() > 0) {
                this.mRequestInfo.FreebackImages = (String[]) list.toArray(new String[list.size()]);
            }
            this.mCommitDataPresenter.commitFeedback(this.mContext, this.mRequestInfo);
        }
    }

    @Override // com.cyjh.share.mvp.view.FeedbackView
    public void onSuc(String str) {
        this.mLoadingDialog.dismiss();
        if (this.onResultCode != null) {
            this.onResultCode.getResultCode(1);
        }
        dismiss();
    }

    @Override // com.cyjh.share.mvp.view.FeedbackView
    public void onFail(String str) {
        this.mLoadingDialog.dismiss();
        if (this.onResultCode != null) {
            this.onResultCode.getResultCode(0);
        }
        dismiss();
    }

    public void setOnResultCode(OnResultCode onResultCode) {
        this.onResultCode = onResultCode;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.mRequestInfo = null;
        this.count = 0;
        if (this.mCommitDataPresenter != null) {
            this.mCommitDataPresenter.onCancel();
        }
    }
}
