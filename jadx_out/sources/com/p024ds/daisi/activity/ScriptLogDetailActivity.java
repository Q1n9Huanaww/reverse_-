package com.p024ds.daisi.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.entity.ScriptLog;
import com.p024ds.daisi.util.FileUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ScriptLogDetailActivity extends BaseActivity implements View.OnClickListener {
    private Button btnShowMore;
    private boolean isLastScriptLog;
    private ScriptLog scriptLog;
    private TitleView titleView;
    private TextView tvScriptLog;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scriptlogdetail);
        initScriptLog();
        initView();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(ScriptLogDetailActivity.class.getCanonicalName());
        MobclickAgent.onResume(this);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(ScriptLogDetailActivity.class.getCanonicalName());
        MobclickAgent.onPause(this);
    }

    private void initScriptLog() {
        Intent intent = getIntent();
        this.isLastScriptLog = intent.getBooleanExtra(APPConstant.INTENT_KEY_FLAG_LASTLOG, false);
        this.scriptLog = (ScriptLog) intent.getParcelableExtra(APPConstant.INTENT_KEY_SCRIPTLOG);
    }

    private void initView() {
        initTitle();
        initShowMoreButton();
        this.tvScriptLog = (TextView) findViewById(R.id.tv_scriptlogdetail_scriptlog);
        new ReadScriptLogTask().execute(this.scriptLog.getFile());
    }

    private void initTitle() {
        this.titleView = (TitleView) findViewById(R.id.titleview_scriptlogdetail);
        this.titleView.setTitleText(this.scriptLog.getName());
        this.titleView.setVisibilityRightImage(4);
        this.titleView.setleftImage(R.drawable.ic_back);
        this.titleView.setOnLeftImageViewListener(new OnLeftImageViewListener());
        this.titleView.setTitleSize(18);
    }

    private void initShowMoreButton() {
        this.btnShowMore = (Button) findViewById(R.id.btn_scriptlogdetail_showmore);
        if (this.isLastScriptLog) {
            this.btnShowMore.setVisibility(0);
            this.btnShowMore.setOnClickListener(this);
        } else {
            this.btnShowMore.setVisibility(8);
        }
    }

    private class OnLeftImageViewListener implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            ScriptLogDetailActivity.this.finish();
        }
    }

    private class ReadScriptLogTask extends AsyncTask<File, Integer, String> {
        private ReadScriptLogTask() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            ScriptLogDetailActivity.this.showProgressDialog(ScriptLogDetailActivity.this, R.string.scriptlogdetail_progressdialog_title, R.string.scriptlogdetail_progressdialog_content);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(File... fileArr) {
            String textFile;
            IOException e;
            try {
                textFile = FileUtils.readTextFile(ScriptLogDetailActivity.this.scriptLog.getFile(), 0, null);
                try {
                    ScriptLogDetailActivity.this.commonLog.m1348e("readTextFile>>>" + textFile);
                } catch (IOException e2) {
                    e = e2;
                    ThrowableExtension.printStackTrace(e);
                }
            } catch (IOException e3) {
                textFile = null;
                e = e3;
            }
            return textFile;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            ScriptLogDetailActivity.this.tvScriptLog.setText(str);
            ScriptLogDetailActivity.this.dismissProgressDialog();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_scriptlogdetail_showmore) {
            return;
        }
        startActivity(new Intent(this, (Class<?>) ScriptLogActivity.class));
    }
}
