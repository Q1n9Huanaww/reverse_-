package com.cyjh.share.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public class DownloadUtil {
    private static String TAG = "DownloadUtil";
    private static DownloadUtil downloadUtil;
    private int downLoadLength;
    private Call mCall;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private RandomAccessFile randomAccessFile;

    public interface OnDownloadListener {
        void onDownloadFailed(Exception exc);

        void onDownloadSuccess(File file);

        void onDownloading(long j, long j2, int i);
    }

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
    }

    public void download(String str, final String str2, final String str3, final OnDownloadListener onDownloadListener) {
        SlLog.m1334i(TAG, "download 1 --> destFileDir=" + str2 + ",destFileName=" + str3);
        this.mCall = this.okHttpClient.newCall(new Request.Builder().url(str).build());
        this.mCall.enqueue(new Callback() { // from class: com.cyjh.share.util.DownloadUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                onDownloadListener.onDownloadFailed(iOException);
            }

            /* JADX WARN: Code duplicated, block: B:46:0x0100 A[Catch: IOException -> 0x0121, TryCatch #5 {IOException -> 0x0121, blocks: (B:44:0x00fb, B:46:0x0100, B:47:0x0103), top: B:55:0x00fb }] */
            /* JADX WARN: Code duplicated, block: B:55:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Throwable {
                FileOutputStream fileOutputStream;
                InputStream inputStreamByteStream;
                String str4;
                StringBuilder sb;
                byte[] bArr = new byte[2048];
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str3);
                InputStream inputStream = null;
                try {
                    try {
                        inputStreamByteStream = response.body().byteStream();
                        try {
                            long jContentLength = response.body().contentLength();
                            fileOutputStream = new FileOutputStream(file2);
                            long j = 0;
                            while (true) {
                                try {
                                    int i = inputStreamByteStream.read(bArr);
                                    if (i == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, i);
                                    long j2 = j + ((long) i);
                                    DownloadUtil.this.downLoadLength += i;
                                    SlLog.m1334i(DownloadUtil.TAG, "download 2 --> downLoadLength=" + DownloadUtil.this.downLoadLength + ",contentLength=" + jContentLength);
                                    onDownloadListener.onDownloading(jContentLength, j2, (int) (((((float) j2) * 1.0f) / ((float) jContentLength)) * 100.0f));
                                    j = j2;
                                } catch (Exception e) {
                                    e = e;
                                    inputStream = inputStreamByteStream;
                                    try {
                                        onDownloadListener.onDownloadFailed(e);
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        str4 = DownloadUtil.TAG;
                                        sb = new StringBuilder();
                                        sb.append("download 3 --> downLoadLength=");
                                        sb.append(DownloadUtil.this.downLoadLength);
                                    } catch (Throwable th) {
                                        th = th;
                                        inputStreamByteStream = inputStream;
                                        if (inputStreamByteStream != null) {
                                            try {
                                                inputStreamByteStream.close();
                                            } catch (IOException unused) {
                                                throw th;
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        SlLog.m1334i(DownloadUtil.TAG, "download 3 --> downLoadLength=" + DownloadUtil.this.downLoadLength);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (inputStreamByteStream != null) {
                                        inputStreamByteStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    SlLog.m1334i(DownloadUtil.TAG, "download 3 --> downLoadLength=" + DownloadUtil.this.downLoadLength);
                                    throw th;
                                }
                            }
                            fileOutputStream.flush();
                            onDownloadListener.onDownloadSuccess(file2);
                            DownloadUtil.this.downLoadLength = 0;
                            if (inputStreamByteStream != null) {
                                inputStreamByteStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            str4 = DownloadUtil.TAG;
                            sb = new StringBuilder();
                            sb.append("download 3 --> downLoadLength=");
                            sb.append(DownloadUtil.this.downLoadLength);
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = null;
                        }
                    } catch (IOException unused2) {
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    inputStreamByteStream = null;
                }
                SlLog.m1334i(str4, sb.toString());
            }
        });
    }

    public void download(String str, final String str2, final String str3, int i, final OnDownloadListener onDownloadListener) {
        SlLog.m1334i(TAG, "download 1 --> downLoadLength=" + this.downLoadLength + ",contentLength=" + i);
        this.mCall = this.okHttpClient.newCall(new Request.Builder().url(str).build());
        this.mCall.enqueue(new Callback() { // from class: com.cyjh.share.util.DownloadUtil.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                onDownloadListener.onDownloadFailed(iOException);
            }

            /* JADX WARN: Code duplicated, block: B:46:0x0100 A[Catch: IOException -> 0x0121, TryCatch #5 {IOException -> 0x0121, blocks: (B:44:0x00fb, B:46:0x0100, B:47:0x0103), top: B:55:0x00fb }] */
            /* JADX WARN: Code duplicated, block: B:55:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Throwable {
                FileOutputStream fileOutputStream;
                InputStream inputStreamByteStream;
                String str4;
                StringBuilder sb;
                byte[] bArr = new byte[2048];
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str3);
                InputStream inputStream = null;
                try {
                    try {
                        inputStreamByteStream = response.body().byteStream();
                        try {
                            long jContentLength = response.body().contentLength();
                            fileOutputStream = new FileOutputStream(file2);
                            long j = 0;
                            while (true) {
                                try {
                                    int i2 = inputStreamByteStream.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, i2);
                                    long j2 = j + ((long) i2);
                                    DownloadUtil.this.downLoadLength += i2;
                                    SlLog.m1334i(DownloadUtil.TAG, "download 2 --> downLoadLength=" + DownloadUtil.this.downLoadLength + ",contentLength=" + jContentLength);
                                    onDownloadListener.onDownloading(jContentLength, j2, (int) (((((float) j2) * 1.0f) / ((float) jContentLength)) * 100.0f));
                                    j = j2;
                                } catch (Exception e) {
                                    e = e;
                                    inputStream = inputStreamByteStream;
                                    try {
                                        onDownloadListener.onDownloadFailed(e);
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        str4 = DownloadUtil.TAG;
                                        sb = new StringBuilder();
                                        sb.append("download 3 --> downLoadLength=");
                                        sb.append(DownloadUtil.this.downLoadLength);
                                    } catch (Throwable th) {
                                        th = th;
                                        inputStreamByteStream = inputStream;
                                        if (inputStreamByteStream != null) {
                                            try {
                                                inputStreamByteStream.close();
                                            } catch (IOException unused) {
                                                throw th;
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        SlLog.m1334i(DownloadUtil.TAG, "download 3 --> downLoadLength=" + DownloadUtil.this.downLoadLength);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (inputStreamByteStream != null) {
                                        inputStreamByteStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    SlLog.m1334i(DownloadUtil.TAG, "download 3 --> downLoadLength=" + DownloadUtil.this.downLoadLength);
                                    throw th;
                                }
                            }
                            fileOutputStream.flush();
                            onDownloadListener.onDownloadSuccess(file2);
                            DownloadUtil.this.downLoadLength = 0;
                            if (inputStreamByteStream != null) {
                                inputStreamByteStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            str4 = DownloadUtil.TAG;
                            sb = new StringBuilder();
                            sb.append("download 3 --> downLoadLength=");
                            sb.append(DownloadUtil.this.downLoadLength);
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = null;
                        }
                    } catch (IOException unused2) {
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    inputStreamByteStream = null;
                }
                SlLog.m1334i(str4, sb.toString());
            }
        });
    }

    public void cancelDownload() {
        if (this.mCall != null) {
            this.mCall.cancel();
        }
    }
}
