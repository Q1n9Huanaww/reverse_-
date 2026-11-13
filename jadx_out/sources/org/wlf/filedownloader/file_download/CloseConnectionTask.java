package org.wlf.filedownloader.file_download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes2.dex */
public class CloseConnectionTask implements Runnable {
    private InputStream mInputStream;
    private HttpURLConnection mURLConnection;

    public CloseConnectionTask(HttpURLConnection urlConnection, InputStream inputStream) {
        this.mURLConnection = null;
        this.mInputStream = null;
        this.mURLConnection = urlConnection;
        this.mInputStream = inputStream;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mInputStream != null) {
            try {
                this.mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.mURLConnection != null) {
            this.mURLConnection.disconnect();
        }
    }
}
