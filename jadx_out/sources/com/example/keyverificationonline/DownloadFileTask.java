package com.example.keyverificationonline;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private DownloadListener listener;

    interface DownloadListener {
        void onDownloadFinished(String str);

        void onProgress(int i);
    }

    public DownloadFileTask(Context context, DownloadListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:14:0x0058 A[Catch: Exception -> 0x0054, TRY_LEAVE, TryCatch #8 {Exception -> 0x0054, blocks: (B:10:0x0050, B:14:0x0058), top: B:89:0x0050 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x011b A[Catch: Exception -> 0x0117, TRY_LEAVE, TryCatch #4 {Exception -> 0x0117, blocks: (B:63:0x0113, B:67:0x011b), top: B:85:0x0113 }] */
    @Override // android.os.AsyncTask
    public String doInBackground(String... urls) throws Throwable {
        Throwable th;
        OutputStream output;
        Uri fileUri;
        String fileUrl = urls[0];
        String fileName = "app_update.apk";
        InputStream input = null;
        HttpURLConnection connection = null;
        try {
            try {
                URL url = new URL(fileUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int fileLength = connection.getContentLength();
                try {
                    if (Build.VERSION.SDK_INT >= 29) {
                        ContentValues values = new ContentValues();
                        values.put("_display_name", "app_update.apk");
                        values.put("mime_type", "application/vnd.android.package-archive");
                        values.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
                        fileUri = this.context.getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                        if (fileUri == null) {
                            if (0 != 0) {
                                try {
                                    input.close();
                                    if (connection != null) {
                                        connection.disconnect();
                                    }
                                } catch (Exception e) {
                                }
                            } else if (connection != null) {
                                connection.disconnect();
                            }
                            return "Failed to create file.";
                        }
                        output = this.context.getContentResolver().openOutputStream(fileUri);
                    } else {
                        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File outputFile = new File(downloadDir, "app_update.apk");
                        if (outputFile.exists()) {
                            outputFile.delete();
                        }
                        output = new FileOutputStream(outputFile);
                        fileUri = Uri.fromFile(outputFile);
                    }
                    input = new BufferedInputStream(url.openStream());
                    byte[] data = new byte[4096];
                    long total = 0;
                    while (true) {
                        int count = input.read(data);
                        if (count == -1) {
                            break;
                        }
                        String fileUrl2 = fileUrl;
                        String fileName2 = fileName;
                        long total2 = total + ((long) count);
                        if (fileLength > 0) {
                            try {
                                long j = 100 * total2;
                                long total3 = fileLength;
                                publishProgress(Integer.valueOf((int) (j / total3)));
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                        if (output != null) {
                            output.write(data, 0, count);
                        }
                        fileUrl = fileUrl2;
                        fileName = fileName2;
                        total = total2;
                        e = e2;
                        Exception e3 = e;
                        e3.printStackTrace();
                        String string = e3.toString();
                        if (input != null) {
                            try {
                                input.close();
                                if (connection != null) {
                                    connection.disconnect();
                                }
                            } catch (Exception e4) {
                            }
                        } else if (connection != null) {
                            connection.disconnect();
                        }
                        return string;
                    }
                    if (output != null) {
                        output.flush();
                        output.close();
                    }
                    try {
                        input.close();
                        if (connection != null) {
                            connection.disconnect();
                        }
                    } catch (Exception e5) {
                    }
                    if (fileUri != null) {
                        return fileUri.toString();
                    }
                    return null;
                } catch (Exception e6) {
                    e = e6;
                } catch (Throwable th2) {
                    th = th2;
                    if (input != null) {
                        try {
                            input.close();
                        } catch (Exception e7) {
                            throw th;
                        }
                    }
                    if (connection == null) {
                        throw th;
                    }
                    connection.disconnect();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e8) {
            e = e8;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate((Object[]) progress);
        this.listener.onProgress(progress[0].intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String filePath) {
        this.listener.onDownloadFinished(filePath);
    }
}
