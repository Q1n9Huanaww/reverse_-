package com.cyjh.feedback.lib.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.cyjh.feedback.lib.activity.ImageSelectActivity;
import com.cyjh.feedback.lib.adapter.ImageShowAdapter;
import com.cyjh.feedback.lib.base.BaseFragment;
import com.cyjh.feedback.lib.dialog.FeedBackDialog;
import com.cyjh.feedback.lib.dialog.LoadingDialog;
import com.cyjh.feedback.lib.utils.AppDevicesUtils;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import com.cyjh.share.bean.request.FeedbackRequestInfo;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.presenter.GetTokenAliCloudPresenter;
import com.cyjh.share.util.AppUtils;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class FeedBackInfoFragment extends BaseFragment implements View.OnClickListener {
    private static final int CORE_NUMBER_CPU1 = 1;
    private static final int CORE_NUMBER_CPU16 = 16;
    private static final int CORE_NUMBER_CPU2 = 2;
    private static final int CORE_NUMBER_CPU3 = 3;
    private static final int CORE_NUMBER_CPU4 = 4;
    private static final int CORE_NUMBER_CPU8 = 8;
    public static final String FEEDBACK_TIPS_MESSAGE = "feedbackTips";
    public static final String IS_PHONE_ROOT = "isPhoneRoot";
    public static final int NO_ROOT = 0;
    private static final int PIC_COUNT_MAX = 4;
    public static final int REQUEST_CODE = 256;
    public static final int VALUE_EMPTY = 0;
    public static final int YES_ROOT = 1;
    private boolean isRoot;
    private ImageShowAdapter mAdapter;
    private List<String> mAddList = new ArrayList();
    private EditText mFeedBackInfo;
    private String mFeedbackTips;
    private GetTokenAliCloudPresenter mGetTokenAliCloudPresenter;
    private GridView mGridView;
    private LoadingDialog mLoadingDialog;
    private EditText mQQOrPhone;
    private FeedbackRequestInfo mRequestInfo;
    private TextView mTvComplaintsQQ;
    private TextView mTvImageCount;
    private TextView mTvRecordFontNumber;
    private View viewLine;

    private void showFeedbackDialog() {
        FeedBackDialog feedBackDialog = new FeedBackDialog(getActivity(), this.mRequestInfo, this.mAddList);
        feedBackDialog.show();
        feedBackDialog.setOnResultCode(new FeedBackDialog.OnResultCode() { // from class: com.cyjh.feedback.lib.fragment.FeedBackInfoFragment.1
            @Override // com.cyjh.feedback.lib.dialog.FeedBackDialog.OnResultCode
            public void getResultCode(int i) {
                Logger.m3442e("code:" + i, new Object[0]);
                if (FeedBackInfoFragment.this.getActivity() != null) {
                    FeedBackInfoFragment.this.mAddList.clear();
                    if (i == 1) {
                        FeedBackInfoFragment.this.getActivity().setResult(-1);
                    } else {
                        FeedBackInfoFragment.this.getActivity().setResult(0);
                    }
                    FeedBackInfoFragment.this.getActivity().finish();
                }
            }
        });
    }

    public static FeedBackInfoFragment newInstance(String str, boolean z) {
        FeedBackInfoFragment feedBackInfoFragment = new FeedBackInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FEEDBACK_TIPS_MESSAGE, str);
        bundle.putBoolean(IS_PHONE_ROOT, z);
        feedBackInfoFragment.setArguments(bundle);
        return feedBackInfoFragment;
    }

    @Override // com.cyjh.feedback.lib.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mFeedbackTips = getArguments().getString(FEEDBACK_TIPS_MESSAGE);
            this.isRoot = getArguments().getBoolean(IS_PHONE_ROOT);
        }
    }

    @Override // com.cyjh.feedback.lib.base.BaseFragment
    public int getFragmentLayoutId() {
        return C0738R.layout.fragment_feedback_info;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mGetTokenAliCloudPresenter = new GetTokenAliCloudPresenter();
        this.mGetTokenAliCloudPresenter.load(getActivity());
    }

    private void initData() {
        this.mLoadingDialog = new LoadingDialog(getActivity());
        if (!TextUtils.isEmpty(this.mFeedbackTips)) {
            this.viewLine.setVisibility(0);
            this.mTvComplaintsQQ.setText(this.mFeedbackTips);
            this.mTvComplaintsQQ.setVisibility(0);
        } else {
            this.mTvComplaintsQQ.setVisibility(8);
            this.viewLine.setVisibility(8);
        }
    }

    private void initView(View view) {
        this.mGridView = (GridView) view.findViewById(C0738R.id.grid_view);
        this.mQQOrPhone = (EditText) view.findViewById(C0738R.id.id_et_qq_phone);
        this.mFeedBackInfo = (EditText) view.findViewById(C0738R.id.id_et_feedback);
        this.mTvImageCount = (TextView) view.findViewById(C0738R.id.id_tv_image_num);
        Button button = (Button) view.findViewById(C0738R.id.id_btn_commit_feedback);
        this.mTvRecordFontNumber = (TextView) view.findViewById(C0738R.id.id_tv_feedback_input_font_num);
        this.mAdapter = new ImageShowAdapter(getActivity(), null);
        this.mGridView.setAdapter((ListAdapter) this.mAdapter);
        button.setOnClickListener(this);
        this.mTvImageCount.setText(getString(C0738R.string.image_add, Integer.valueOf(this.mAddList.size())));
        this.mTvRecordFontNumber.setText(getString(C0738R.string.feedback_num_hint, 0));
        this.mTvComplaintsQQ = (TextView) view.findViewById(C0738R.id.id_complaints_qq);
        this.viewLine = view.findViewById(C0738R.id.id_line);
    }

    private void initEvent() {
        this.mFeedBackInfo.addTextChangedListener(new TextWatcher() { // from class: com.cyjh.feedback.lib.fragment.FeedBackInfoFragment.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Logger.m3442e("长度:" + charSequence.length() + ((Object) charSequence) + "反馈信息" + i3, new Object[0]);
                FeedBackInfoFragment.this.mTvRecordFontNumber.setText(FeedBackInfoFragment.this.getString(C0738R.string.feedback_num_hint, Integer.valueOf(charSequence.length())));
            }
        });
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.cyjh.feedback.lib.fragment.FeedBackInfoFragment.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == FeedBackInfoFragment.this.mAddList.size()) {
                    FeedBackInfoFragment.this.startActivityForResult(new Intent(FeedBackInfoFragment.this.getActivity(), (Class<?>) ImageSelectActivity.class), 256);
                }
            }
        });
        this.mAdapter.setmDeleteSelectImg(new ImageShowAdapter.OnDeleteSelectImg() { // from class: com.cyjh.feedback.lib.fragment.FeedBackInfoFragment.4
            @Override // com.cyjh.feedback.lib.adapter.ImageShowAdapter.OnDeleteSelectImg
            public void onClick(int i) {
                FeedBackInfoFragment.this.mTvImageCount.setText(FeedBackInfoFragment.this.getString(C0738R.string.image_add, Integer.valueOf(i)));
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == C0738R.id.id_btn_commit_feedback) {
            this.mLoadingDialog.show();
            getFeedbackData();
            submitFeedbackInfo();
        }
    }

    private void submitFeedbackInfo() {
        if (this.mRequestInfo == null) {
            this.mLoadingDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(this.mRequestInfo.Contact)) {
            this.mRequestInfo.Contact = String.valueOf(0);
        }
        if (TextUtils.isEmpty(this.mRequestInfo.Content)) {
            this.mRequestInfo.Content = String.valueOf(0);
        }
        if (TextUtils.isEmpty(this.mRequestInfo.AndoridVersion)) {
            this.mRequestInfo.AndoridVersion = String.valueOf(0);
        }
        if (TextUtils.isEmpty(this.mRequestInfo.CoreVersion)) {
            this.mRequestInfo.CoreVersion = String.valueOf(0);
        }
        if (TextUtils.isEmpty(this.mRequestInfo.SerialNumber)) {
            this.mRequestInfo.SerialNumber = String.valueOf(0);
        }
        this.mLoadingDialog.dismiss();
        showFeedbackDialog();
    }

    private void getFeedbackData() {
        String strTrim = this.mQQOrPhone.getText().toString().trim();
        String strTrim2 = this.mFeedBackInfo.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim) && TextUtils.isEmpty(strTrim2)) {
            Toast.makeText(getActivity(), getString(C0738R.string.no_fill_info), 1).show();
            return;
        }
        if (this.mRequestInfo == null) {
            this.mRequestInfo = new FeedbackRequestInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(getActivity()));
        }
        this.mRequestInfo.Contact = strTrim;
        this.mRequestInfo.Content = strTrim2;
        this.mRequestInfo.AndoridVersion = Build.VERSION.RELEASE;
        this.mRequestInfo.DeviceModel = Build.MODEL;
        if (this.isRoot) {
            this.mRequestInfo.RootStatus = 1;
        } else {
            this.mRequestInfo.RootStatus = 0;
        }
        int i = AppUtils.getResolution(getActivity()).x;
        int i2 = AppUtils.getResolution(getActivity()).y;
        this.mRequestInfo.Resolution = i + Marker.ANY_MARKER + i2;
        this.mRequestInfo.RAM = Formatter.formatFileSize(getActivity(), AppUtils.getTotalMemorySize(getActivity()));
        this.mRequestInfo.DPI = AppUtils.getScreenDPI(getActivity());
        this.mRequestInfo.SerialNumber = AppUtils.getSerialNumber();
        this.mRequestInfo.CoreVersion = AppUtils.getKernelVersion();
        this.mRequestInfo.SystemVersion = AppUtils.getPhoneSystemVersion();
        String cPUCoreNum = getCPUCoreNum(AppDevicesUtils.getNumCores());
        String cpuInfo = AppDevicesUtils.getCpuInfo();
        String strValueOf = String.valueOf(0);
        if (!TextUtils.isEmpty(cPUCoreNum) && !TextUtils.isEmpty(cpuInfo)) {
            cPUCoreNum = cPUCoreNum + "，CPU型号：" + cpuInfo;
        } else if (TextUtils.isEmpty(cPUCoreNum) || !TextUtils.isEmpty(cpuInfo)) {
            if (!TextUtils.isEmpty(cPUCoreNum) || TextUtils.isEmpty(cpuInfo)) {
                cPUCoreNum = strValueOf;
            } else {
                cPUCoreNum = "CPU型号：" + cpuInfo;
            }
        }
        this.mRequestInfo.Processor = cPUCoreNum;
    }

    private String getCPUCoreNum(int i) {
        if (i == 8) {
            return getString(C0738R.string.cpu_core_eight);
        }
        if (i != 16) {
            switch (i) {
                case 1:
                    return getString(C0738R.string.cpu_core_one);
                case 2:
                    return getString(C0738R.string.cpu_core_two);
                case 3:
                    return getString(C0738R.string.cpu_core_three);
                case 4:
                    return getString(C0738R.string.cpu_core_four);
                default:
                    return null;
            }
        }
        return getString(C0738R.string.cpu_core_sixteen);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayList;
        super.onActivityResult(i, i2, intent);
        if (i != 256 || i2 != -1 || intent == null || (stringArrayList = intent.getBundleExtra("bundle").getStringArrayList("image")) == null) {
            return;
        }
        Logger.m3442e("mDataList:" + stringArrayList.size(), new Object[0]);
        int size = this.mAddList.size();
        Logger.m3442e("mAddListSize:" + this.mAddList.size(), new Object[0]);
        if (stringArrayList.size() + size > 4) {
            Toast.makeText(getActivity(), getString(C0738R.string.img_num_limit, Integer.valueOf(size), Integer.valueOf(4 - size)), 1).show();
            stringArrayList.clear();
            return;
        }
        for (String str : stringArrayList) {
            if (!this.mAddList.contains(str)) {
                this.mAddList.add(str);
            }
        }
        this.mTvImageCount.setText(getString(C0738R.string.image_add, Integer.valueOf(this.mAddList.size())));
        this.mAdapter.setmData(this.mAddList);
        this.mGridView.setAdapter((ListAdapter) this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(FeedBackInfoFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(FeedBackInfoFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mGetTokenAliCloudPresenter != null) {
            this.mGetTokenAliCloudPresenter.onCancel();
        }
    }
}
