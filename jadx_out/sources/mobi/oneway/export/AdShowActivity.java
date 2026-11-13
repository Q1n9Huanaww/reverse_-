package mobi.oneway.export;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import mobi.oneway.export.enums.PluginErrorType;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p084e.C1929c;
import mobi.oneway.export.p087g.C1953g;
import mobi.oneway.export.plugin.IActivityInterface;

/* JADX INFO: loaded from: classes.dex */
public class AdShowActivity extends Activity {
    IActivityInterface iActivityInterface;
    Class pluginActivityClass;

    public AdShowActivity() {
        this.pluginActivityClass = null;
        this.iActivityInterface = null;
        try {
            this.pluginActivityClass = C1903b.m4720a(C1899a.f4828k);
            this.iActivityInterface = (IActivityInterface) this.pluginActivityClass.newInstance();
            this.iActivityInterface.attach(this);
        } catch (Exception e) {
            C1929c.m4872a(PluginErrorType.shell_error_reflectClass, C1953g.m4999a((Throwable) e));
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onCreate(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onDestroy();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.iActivityInterface != null ? this.iActivityInterface.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyDownInHost(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onPause();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onSaveInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onStart();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.iActivityInterface != null) {
            this.iActivityInterface.onStop();
        }
    }
}
