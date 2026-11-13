package killer.elfin.update;

import android.os.SystemClock;
import android.util.Log;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.p019mq.sdk.MqRunner;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.fragment.SplashFragment;
import java.io.File;
import java.io.IOException;
import killer.core.entity.ScriptVersionInfo;
import killer.elfin.Free;
import killer.elfin.config.GlobalConfig;
import killer.elfin.util.ToastUtil;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.p092io.FileUtils;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;

/* JADX INFO: loaded from: classes2.dex */
public class ScriptDownload {
    private static ScriptDownload instance;
    public static int HOT_SCRIPT_DOWNLOAD_IMMEDIATELY_AND_ENGINE_RUNNING = 1003;
    public static boolean isDownloading = false;

    public void download(final ScriptVersionInfo scriptVersionInfo) {
        if (Free.getContext() != null) {
            final File downloadDir = new File(Free.getContext().getExternalCacheDir(), "script");
            if (!FileDownloader.isInit()) {
                FileDownloadConfiguration.Builder builder = new FileDownloadConfiguration.Builder(Free.getContext());
                builder.configFileDownloadDir(downloadDir.getAbsolutePath());
                builder.configDownloadTaskSize(1);
                builder.configRetryDownloadTimes(1);
                builder.configConnectTimeout(5000);
                FileDownloadConfiguration configuration = builder.build();
                FileDownloader.init(configuration);
            }
            FileDownloader.delete(scriptVersionInfo.downloadUrl, true, (OnDeleteDownloadFileListener) null);
            FileDownloader.registerDownloadStatusListener(new OnSimpleFileDownloadStatusListener() { // from class: killer.elfin.update.ScriptDownload.1
                @Override // org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener, org.wlf.filedownloader.listener.OnFileDownloadStatusListener
                public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime) {
                    ToastUtil.toast(String.format("脚本更新中,剩余(%d)秒", Long.valueOf(remainingTime)), SplashFragment.DELAY_500_MILLIS, 10, 10);
                }

                @Override // org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener, org.wlf.filedownloader.listener.OnFileDownloadStatusListener
                public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
                    try {
                        String assetsDir = Free.getContext().getFilesDir().getAbsolutePath() + File.separator + "assets";
                        ZipFile zipFile = new ZipFile(new File(downloadFileInfo.getFilePath()));
                        zipFile.extractAll(assetsDir);
                        File assetsAtc = new File(assetsDir, APPConstant.SCRIPT_FILENAME_ATC);
                        if (!assetsAtc.exists()) {
                            File sourceAtc = new File(Free.getContext().getFilesDir().getAbsolutePath(), APPConstant.SCRIPT_FILENAME_ATC);
                            FileUtils.copyFile(sourceAtc, assetsAtc, false);
                        }
                        boolean isScriptRun = Free.runner.isScriptStarted();
                        MqRunner.getInstance().stop();
                        GlobalConfig.setScriptVersion(Long.valueOf(scriptVersionInfo.version));
                        Free.initScript();
                        if (isScriptRun) {
                            new Thread(new Runnable() { // from class: killer.elfin.update.ScriptDownload.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    SystemClock.sleep(5000L);
                                    Free.runner.runScript();
                                }
                            }).start();
                        }
                        ToastUtil.toast("脚本热更成功", ExToast.LENGTH_SHORT, 10, 10);
                    } catch (Exception e) {
                        Log.e("ScriptDownload", "onFileDownloadStatusCompleted", e);
                        ToastUtil.toast("脚本热更失败-" + e.getMessage(), ExToast.LENGTH_SHORT, 10, 10);
                    } finally {
                        FileDownloader.delete(downloadFileInfo.getUrl(), true, (OnDeleteDownloadFileListener) null);
                        FileDownloader.unregisterDownloadStatusListener(this);
                        try {
                            FileUtils.deleteDirectory(downloadDir);
                        } catch (IOException e2) {
                        }
                    }
                }

                @Override // org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener, org.wlf.filedownloader.listener.OnFileDownloadStatusListener
                public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
                    Log.e("ScriptDownload", "onFileDownloadStatusFailed", failReason);
                    ToastUtil.toast("脚本热更失败-" + failReason.getMessage(), ExToast.LENGTH_SHORT, 10, 10);
                    FileDownloader.unregisterDownloadStatusListener(this);
                    try {
                        FileUtils.deleteDirectory(downloadDir);
                    } catch (IOException e) {
                    }
                }
            });
            FileDownloader.start(scriptVersionInfo.downloadUrl);
        }
    }

    public static ScriptDownload get() {
        ScriptDownload scriptDownload;
        if (instance == null) {
            synchronized (ScriptDownload.class) {
                if (instance == null) {
                    instance = new ScriptDownload();
                }
                scriptDownload = instance;
            }
            return scriptDownload;
        }
        return instance;
    }
}
