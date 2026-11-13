package com.androidquery.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import com.androidquery.AQuery;
import com.cyjh.share.oss.Config;

/* JADX INFO: loaded from: classes.dex */
public class Progress implements Runnable {
    private Activity act;
    private int bytes;
    private int current;

    /* JADX INFO: renamed from: pb */
    private ProgressBar f65pb;

    /* JADX INFO: renamed from: pd */
    private ProgressDialog f66pd;
    private boolean unknown;
    private String url;
    private View view;

    public Progress(Object obj) {
        if (obj instanceof ProgressBar) {
            this.f65pb = (ProgressBar) obj;
            return;
        }
        if (obj instanceof ProgressDialog) {
            this.f66pd = (ProgressDialog) obj;
        } else if (obj instanceof Activity) {
            this.act = (Activity) obj;
        } else if (obj instanceof View) {
            this.view = (View) obj;
        }
    }

    public void reset() {
        if (this.f65pb != null) {
            this.f65pb.setProgress(0);
            this.f65pb.setMax(com.p024ds.daisi.constant.Constants.REQUEST_CODE_APP_INSTALL);
        }
        if (this.f66pd != null) {
            this.f66pd.setProgress(0);
            this.f66pd.setMax(com.p024ds.daisi.constant.Constants.REQUEST_CODE_APP_INSTALL);
        }
        if (this.act != null) {
            this.act.setProgress(0);
        }
        this.unknown = false;
        this.current = 0;
        this.bytes = com.p024ds.daisi.constant.Constants.REQUEST_CODE_APP_INSTALL;
    }

    public void setBytes(int i) {
        if (i <= 0) {
            this.unknown = true;
            i = com.p024ds.daisi.constant.Constants.REQUEST_CODE_APP_INSTALL;
        }
        this.bytes = i;
        if (this.f65pb != null) {
            this.f65pb.setProgress(0);
            this.f65pb.setMax(i);
        }
        if (this.f66pd != null) {
            this.f66pd.setProgress(0);
            this.f66pd.setMax(i);
        }
    }

    public void increment(int i) {
        int i2;
        if (this.f65pb != null) {
            this.f65pb.incrementProgressBy(this.unknown ? 1 : i);
        }
        if (this.f66pd != null) {
            this.f66pd.incrementProgressBy(this.unknown ? 1 : i);
        }
        if (this.act != null) {
            if (this.unknown) {
                i2 = this.current;
                this.current = i2 + 1;
            } else {
                this.current += i;
                i2 = (this.current * com.p024ds.daisi.constant.Constants.REQUEST_CODE_APP_INSTALL) / this.bytes;
            }
            if (i2 > 9999) {
                i2 = Config.FAIL;
            }
            this.act.setProgress(i2);
        }
    }

    public void done() {
        if (this.f65pb != null) {
            this.f65pb.setProgress(this.f65pb.getMax());
        }
        if (this.f66pd != null) {
            this.f66pd.setProgress(this.f66pd.getMax());
        }
        if (this.act != null) {
            this.act.setProgress(Config.FAIL);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        dismiss(this.url);
    }

    public void show(String str) {
        reset();
        if (this.f66pd != null) {
            new AQuery(this.f66pd.getContext()).show(this.f66pd);
        }
        if (this.act != null) {
            this.act.setProgressBarIndeterminateVisibility(true);
            this.act.setProgressBarVisibility(true);
        }
        if (this.f65pb != null) {
            this.f65pb.setTag(Constants.TAG_URL, str);
            this.f65pb.setVisibility(0);
        }
        if (this.view != null) {
            this.view.setTag(Constants.TAG_URL, str);
            this.view.setVisibility(0);
        }
    }

    public void hide(String str) {
        if (AQUtility.isUIThread()) {
            dismiss(str);
        } else {
            this.url = str;
            AQUtility.post(this);
        }
    }

    private void dismiss(String str) {
        if (this.f66pd != null) {
            new AQuery(this.f66pd.getContext()).dismiss(this.f66pd);
        }
        if (this.act != null) {
            this.act.setProgressBarIndeterminateVisibility(false);
            this.act.setProgressBarVisibility(false);
        }
        if (this.f65pb != null) {
            this.f65pb.setTag(Constants.TAG_URL, str);
            this.f65pb.setVisibility(0);
        }
        View view = this.f65pb;
        if (view == null) {
            view = this.view;
        }
        if (view != null) {
            Object tag = view.getTag(Constants.TAG_URL);
            if (tag == null || tag.equals(str)) {
                view.setTag(Constants.TAG_URL, null);
                if (this.f65pb == null || !this.f65pb.isIndeterminate()) {
                    return;
                }
                view.setVisibility(8);
            }
        }
    }

    private void showProgress(Object obj, String str, boolean z) {
        if (obj != null) {
            if (obj instanceof View) {
                View view = (View) obj;
                ProgressBar progressBar = obj instanceof ProgressBar ? (ProgressBar) obj : null;
                if (z) {
                    view.setTag(Constants.TAG_URL, str);
                    view.setVisibility(0);
                    if (progressBar != null) {
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        return;
                    }
                    return;
                }
                Object tag = view.getTag(Constants.TAG_URL);
                if (tag == null || tag.equals(str)) {
                    view.setTag(Constants.TAG_URL, null);
                    if (progressBar == null || !progressBar.isIndeterminate()) {
                        return;
                    }
                    view.setVisibility(8);
                    return;
                }
                return;
            }
            if (obj instanceof Dialog) {
                Dialog dialog = (Dialog) obj;
                AQuery aQuery = new AQuery(dialog.getContext());
                if (z) {
                    aQuery.show(dialog);
                    return;
                } else {
                    aQuery.dismiss(dialog);
                    return;
                }
            }
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                activity.setProgressBarIndeterminateVisibility(z);
                activity.setProgressBarVisibility(z);
                if (z) {
                    activity.setProgress(0);
                }
            }
        }
    }
}
