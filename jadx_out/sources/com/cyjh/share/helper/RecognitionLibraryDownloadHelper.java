package com.cyjh.share.helper;

import android.content.Context;
import android.os.AsyncTask;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.util.DownloadUtil;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.ZipUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class RecognitionLibraryDownloadHelper {
    private static String TAG = "RecognitionLibraryDownloadHelper";
    private static RecognitionLibraryDownloadHelper instance;
    private OnDownloadListener mCallback;
    private Context mContext;
    DownloadUtil.OnDownloadListener mOnDownloadListener = new DownloadUtil.OnDownloadListener() { // from class: com.cyjh.share.helper.RecognitionLibraryDownloadHelper.1
        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloadSuccess(File file) {
            SlLog.m1334i(RecognitionLibraryDownloadHelper.TAG, "onDownloadSuccess --> file=" + file.getName());
            RecognitionLibraryDownloadHelper.this.new DeleteZipTask(file).execute(new Void[0]);
        }

        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloading(long j, long j2, int i) {
            SlLog.m1334i(RecognitionLibraryDownloadHelper.TAG, "onDownloading --> progress=" + i);
        }

        @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
        public void onDownloadFailed(Exception exc) {
            SlLog.m1334i(RecognitionLibraryDownloadHelper.TAG, "onDownloadFailed --> exception=" + exc.getMessage());
            if (RecognitionLibraryDownloadHelper.this.mCallback != null) {
                RecognitionLibraryDownloadHelper.this.mCallback.onDownloadFailed("下载语音识别库失败！");
            }
        }
    };

    public interface OnDownloadListener {
        void alreadyExist();

        void onDownloadFailed(String str);

        void onDownloadSuccess();
    }

    private RecognitionLibraryDownloadHelper() {
    }

    public static RecognitionLibraryDownloadHelper get() {
        if (instance == null) {
            synchronized (RecognitionLibraryDownloadHelper.class) {
                if (instance == null) {
                    instance = new RecognitionLibraryDownloadHelper();
                }
            }
        }
        return instance;
    }

    public RecognitionLibraryDownloadHelper init(Context context) {
        this.mContext = context;
        return this;
    }

    public void download(String str, OnDownloadListener onDownloadListener) {
        SlLog.m1334i(TAG, "download --> url=" + str);
        this.mCallback = onDownloadListener;
        libraryDownloadTask(str);
    }

    public boolean isRecognitionLibraryAvailable() {
        String str = this.mContext.getFilesDir() + File.separator + InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS;
        File file = new File(str, InterfaceRelatedConstants.CHI_SIM_TRAINED_DATA);
        File file2 = new File(str, InterfaceRelatedConstants.ENG_TRAINED_DATA);
        return file.exists() && file.length() > 0 && file2.exists() && file2.length() > 0;
    }

    private void libraryDownloadTask(String str) {
        if (isRecognitionLibraryAvailable()) {
            if (this.mCallback != null) {
                this.mCallback.alreadyExist();
                return;
            }
            return;
        }
        String str2 = this.mContext.getPackageName() + File.separator + InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS;
        SlLog.m1334i(TAG, "libraryDownloadTask --> path=" + str2);
        new LibraryDownloadTask(this.mContext).execute(str, str2);
    }

    private class LibraryDownloadTask extends AsyncTask<String, Void, Void> {
        private Context mContext;

        public LibraryDownloadTask(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                File fileCreateDataDataFileDir = FileUtil.createDataDataFileDir(this.mContext, InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS);
                File file = new File(fileCreateDataDataFileDir, InterfaceRelatedConstants.CHI_SIM_TRAINED_DATA);
                File file2 = new File(fileCreateDataDataFileDir, InterfaceRelatedConstants.CHI_SIM_TRAINED_DATA);
                FileUtil.deleteSingleFile(file);
                FileUtil.deleteSingleFile(file2);
                String strSubstring = strArr[0].substring(strArr[0].lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR));
                File fileByPath = FileUtil.getFileByPath(this.mContext, strArr[1], strSubstring);
                SlLog.m1334i(RecognitionLibraryDownloadHelper.TAG, "LibraryDownloadTask doInBackground --> zipName=" + strSubstring + ", file path = " + fileByPath.getAbsolutePath());
                DownloadUtil.get().download(strArr[0], fileByPath.getParentFile().getAbsolutePath(), fileByPath.getName(), -1, RecognitionLibraryDownloadHelper.this.mOnDownloadListener);
                return null;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }
    }

    private class DeleteZipTask extends AsyncTask<Void, Void, Boolean> {
        private File srcFile;

        public DeleteZipTask(File file) {
            this.srcFile = file;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            boolean zUnzipFile = ZipUtil.unzipFile(this.srcFile, FileUtil.createDataDataFileDir(RecognitionLibraryDownloadHelper.this.mContext, InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS));
            FileUtil.deleteSingleFile(this.srcFile);
            SlLog.m1334i(RecognitionLibraryDownloadHelper.TAG, "DeleteZipTask doInBackground --> result=" + zUnzipFile);
            return Boolean.valueOf(zUnzipFile);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (RecognitionLibraryDownloadHelper.this.mCallback != null) {
                if (bool.booleanValue()) {
                    RecognitionLibraryDownloadHelper.this.mCallback.onDownloadSuccess();
                } else {
                    RecognitionLibraryDownloadHelper.this.mCallback.onDownloadFailed("下载语音识别库失败！");
                }
            }
        }
    }
}
