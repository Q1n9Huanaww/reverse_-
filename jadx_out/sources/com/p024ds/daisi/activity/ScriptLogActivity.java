package com.p024ds.daisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.p024ds.daisi.adpter.ScriptLogListViewAdapter;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.entity.ScriptLog;
import com.p024ds.daisi.util.FileComparator;
import com.p024ds.daisi.util.FileUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ScriptLogActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnRemoveAll;
    private Button btnSelectAll;
    private boolean isManage = false;
    private ListView listviewScriptlog;
    private ArrayList<ScriptLog> scriptLogList;
    private ScriptLogListViewAdapter scriptLogListViewAdapter;
    private TitleView titleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scriptlog);
        initView();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(ScriptLogActivity.class.getCanonicalName());
        MobclickAgent.onResume(this);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(ScriptLogActivity.class.getCanonicalName());
        MobclickAgent.onPause(this);
    }

    private void initView() {
        initTitle();
        initListView();
        this.btnRemoveAll = (Button) findViewById(R.id.btn_scriptlog_removeall);
        this.btnSelectAll = (Button) findViewById(R.id.btn_scriptlog_selectall);
        this.btnRemoveAll.setOnClickListener(this);
        this.btnSelectAll.setOnClickListener(this);
    }

    private void initTitle() {
        this.titleView = (TitleView) findViewById(R.id.titleview_scriptlog);
        this.titleView.setTitleText(R.string.scriptlog);
        this.titleView.setleftImage(R.drawable.ic_back);
        this.titleView.setOnLeftImageViewListener(new OnLeftImageViewListener());
        this.titleView.setRightImage(R.drawable.ic_manage);
        this.titleView.setOnRightImageViewListener(new OnRightImageViewListener());
    }

    private void initListView() {
        this.listviewScriptlog = (ListView) findViewById(R.id.listview_scriptlog);
        this.listviewScriptlog.setOnItemClickListener(this);
        getScriptLogListData();
        this.scriptLogListViewAdapter = new ScriptLogListViewAdapter(this, this.scriptLogList);
        this.listviewScriptlog.setAdapter((ListAdapter) this.scriptLogListViewAdapter);
    }

    private void getScriptLogListData() {
        File[] fileArrListFiles = new File(this.appContext.mPathPackagenameLog).listFiles(new FileUtils.FileSuffixrFilter(APPConstant.FILE_SUFFIX_LOG));
        this.scriptLogList = new ArrayList<>();
        if (fileArrListFiles != null) {
            Arrays.sort(fileArrListFiles, new FileComparator());
            for (File file : fileArrListFiles) {
                ScriptLog scriptLog = new ScriptLog();
                scriptLog.setSize(FileUtils.formetFileSize(file.length()));
                scriptLog.setName(file.getName());
                scriptLog.setFile(file);
                this.scriptLogList.add(scriptLog);
            }
        }
    }

    private class OnLeftImageViewListener implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            ScriptLogActivity.this.finish();
        }
    }

    private class OnRightImageViewListener implements TitleView.OnRightImageViewListener {
        private OnRightImageViewListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnRightImageViewListener
        public void onClick(View view) {
            ScriptLogActivity.this.isManage = !ScriptLogActivity.this.isManage;
            if (ScriptLogActivity.this.isManage) {
                ScriptLogActivity.this.btnRemoveAll.setVisibility(0);
                ScriptLogActivity.this.btnSelectAll.setVisibility(0);
                ScriptLogActivity.this.titleView.setRightImage(R.drawable.ic_cancel);
            } else {
                ScriptLogActivity.this.btnRemoveAll.setVisibility(8);
                ScriptLogActivity.this.btnSelectAll.setVisibility(8);
                ScriptLogActivity.this.titleView.setRightImage(R.drawable.ic_manage);
            }
            Iterator it2 = ScriptLogActivity.this.scriptLogList.iterator();
            while (it2.hasNext()) {
                ((ScriptLog) it2.next()).setSelect(false);
            }
            ScriptLogActivity.this.scriptLogListViewAdapter.setManage(ScriptLogActivity.this.isManage);
            ScriptLogActivity.this.scriptLogListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scriptlog_removeall /* 2131755203 */:
                deleteScriptLogs();
                break;
            case R.id.btn_scriptlog_selectall /* 2131755204 */:
                Iterator<ScriptLog> it2 = this.scriptLogList.iterator();
                while (it2.hasNext()) {
                    it2.next().setSelect(true);
                }
                this.scriptLogListViewAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void deleteScriptLogs() {
        boolean z;
        Iterator<ScriptLog> it2 = this.scriptLogList.iterator();
        while (true) {
            z = false;
            if (it2.hasNext()) {
                if (it2.next().isSelect()) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            this.appContext.showToast(R.string.scriptlog_unselect);
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.commonLog.m1348e("scriptLog>>" + this.scriptLogList.size());
        for (ScriptLog scriptLog : this.scriptLogList) {
            if (scriptLog.isSelect() && scriptLog.getFile().delete()) {
                this.commonLog.m1348e("scriptLog>>" + scriptLog.getName());
                arrayList.add(scriptLog);
            }
        }
        this.scriptLogList.removeAll(arrayList);
        this.scriptLogListViewAdapter.notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.isManage) {
            return;
        }
        ScriptLog scriptLog = this.scriptLogList.get(i);
        Intent intent = new Intent(this, (Class<?>) ScriptLogDetailActivity.class);
        intent.putExtra(APPConstant.INTENT_KEY_FLAG_LASTLOG, false);
        intent.putExtra(APPConstant.INTENT_KEY_SCRIPTLOG, scriptLog);
        startActivity(intent);
    }
}
