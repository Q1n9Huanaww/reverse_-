package org.wlf.filedownloader.file_download.db_recorder;

import android.content.Context;
import java.util.List;
import org.wlf.filedownloader.p095db.BaseContentDbHelper;
import org.wlf.filedownloader.p095db.ContentDbDao;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileDbHelper extends BaseContentDbHelper {
    private static final String DB_NAME = "download_file.db";
    private static final int DB_VERSION = 3;

    public DownloadFileDbHelper(Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override // org.wlf.filedownloader.p095db.BaseContentDbHelper
    protected void onConfigContentDbDaos(List<ContentDbDao> contentDbDaos) {
        DownloadFileDao downloadFileDao = new DownloadFileDao(this);
        contentDbDaos.add(downloadFileDao);
    }
}
