package org.wlf.filedownloader.file_download.file_saver;

import android.os.SystemClock;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.wlf.filedownloader.base.FailException;
import org.wlf.filedownloader.base.FailReason;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.base.Stoppable;
import org.wlf.filedownloader.file_download.http_downloader.ContentLengthInputStream;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.MathUtil;

/* JADX INFO: loaded from: classes2.dex */
public class FileSaver implements Save, Stoppable {
    private static final int BUFFER_SIZE_WRITE_TO_FILE = 32768;
    private static final String TAG = FileSaver.class.getSimpleName();
    private long mFileTotalSize;
    private OnFileSaveListener mOnFileSaveListener;
    private String mSaveFilePath;
    private String mTempFilePath;
    private String mUrl;
    private int mBufferSizeWriteToFile = 32768;
    private DownloadNoticeStrategy mDownloadNoticeStrategy = DownloadNoticeStrategy.NOTICE_AUTO;
    private boolean mIsStopped = false;
    private boolean mIsNotifyEnd = false;

    public interface OnFileSaveListener {
        void onSaveDataEnd(int i, boolean z);

        void onSaveDataStart();

        void onSavingData(int i, long j);
    }

    public FileSaver(String url, String tempFilePath, String saveFilePath, long fileTotalSize) {
        this.mUrl = url;
        this.mTempFilePath = tempFilePath;
        this.mSaveFilePath = saveFilePath;
        this.mFileTotalSize = fileTotalSize;
    }

    public void setOnFileSaveListener(OnFileSaveListener onFileSaveListener) {
        this.mOnFileSaveListener = onFileSaveListener;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0361 A[Catch: Exception -> 0x00d6, all -> 0x0410, TryCatch #6 {Exception -> 0x00d6, all -> 0x0410, blocks: (B:4:0x0060, B:5:0x00a6, B:7:0x00ae, B:9:0x00c6, B:11:0x00cc, B:12:0x00d5, B:26:0x013a, B:27:0x0160, B:29:0x0165, B:31:0x017d, B:34:0x01eb, B:36:0x01f4, B:39:0x0262, B:41:0x0275, B:44:0x02e3, B:46:0x02f1, B:50:0x0361, B:55:0x03d1, B:57:0x03df, B:59:0x03e5, B:61:0x03eb, B:62:0x040f, B:65:0x0415, B:67:0x041f, B:69:0x042d, B:70:0x045f, B:71:0x0460, B:79:0x04e8, B:81:0x04f0, B:82:0x04f9), top: B:97:0x0060 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:76:0x049c  */
    /* JADX WARN: Code duplicated, block: B:79:0x04e8 A[Catch: Exception -> 0x00d6, all -> 0x0410, TRY_ENTER, TryCatch #6 {Exception -> 0x00d6, all -> 0x0410, blocks: (B:4:0x0060, B:5:0x00a6, B:7:0x00ae, B:9:0x00c6, B:11:0x00cc, B:12:0x00d5, B:26:0x013a, B:27:0x0160, B:29:0x0165, B:31:0x017d, B:34:0x01eb, B:36:0x01f4, B:39:0x0262, B:41:0x0275, B:44:0x02e3, B:46:0x02f1, B:50:0x0361, B:55:0x03d1, B:57:0x03df, B:59:0x03e5, B:61:0x03eb, B:62:0x040f, B:65:0x0415, B:67:0x041f, B:69:0x042d, B:70:0x045f, B:71:0x0460, B:79:0x04e8, B:81:0x04f0, B:82:0x04f9), top: B:97:0x0060 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x04f0 A[Catch: Exception -> 0x00d6, all -> 0x0410, TryCatch #6 {Exception -> 0x00d6, all -> 0x0410, blocks: (B:4:0x0060, B:5:0x00a6, B:7:0x00ae, B:9:0x00c6, B:11:0x00cc, B:12:0x00d5, B:26:0x013a, B:27:0x0160, B:29:0x0165, B:31:0x017d, B:34:0x01eb, B:36:0x01f4, B:39:0x0262, B:41:0x0275, B:44:0x02e3, B:46:0x02f1, B:50:0x0361, B:55:0x03d1, B:57:0x03df, B:59:0x03e5, B:61:0x03eb, B:62:0x040f, B:65:0x0415, B:67:0x041f, B:69:0x042d, B:70:0x045f, B:71:0x0460, B:79:0x04e8, B:81:0x04f0, B:82:0x04f9), top: B:97:0x0060 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x0497 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.wlf.filedownloader.file_download.file_saver.Save
    public void saveData(ContentLengthInputStream inputStream, long startPosInTotal) throws Throwable {
        boolean isCompleted = false;
        String url = this.mUrl;
        byte[] buffer = new byte[this.mBufferSizeWriteToFile];
        RandomAccessFile randomAccessFile = null;
        int needNotifySize = 0;
        try {
            try {
                checkIsStop();
                FileUtil.createFileParentDir(this.mTempFilePath);
                FileUtil.createFileParentDir(this.mSaveFilePath);
                File tempFile = new File(this.mTempFilePath);
                File saveFile = new File(this.mSaveFilePath);
                long handledFileSize = 0;
                long needHandleSize = inputStream.getLength();
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(tempFile, "rwd");
                try {
                    randomAccessFile2.seek(startPosInTotal);
                    String filePath = tempFile.getAbsolutePath();
                    notifyStart();
                    Log.m5156d(TAG, TAG + ".saveData 1、准备写文件缓存，路径：" + tempFile.getAbsolutePath() + "，url：" + url);
                    long lastNotifyTime = SystemClock.elapsedRealtime();
                    long curTime = SystemClock.elapsedRealtime();
                    while (!this.mIsStopped) {
                        int readSize = inputStream.read(buffer, 0, this.mBufferSizeWriteToFile);
                        if (readSize != -1) {
                            if (!tempFile.exists()) {
                                throw new FileSaveException("temp file not exist!", FileSaveException.TYPE_TEMP_FILE_DOES_NOT_EXIST);
                            }
                            randomAccessFile2.write(buffer, 0, readSize);
                            int increaseSize = readSize - 0;
                            handledFileSize += (long) increaseSize;
                            needNotifySize += increaseSize;
                            curTime = SystemClock.elapsedRealtime();
                            long dTime = curTime - lastNotifyTime;
                            switch (this.mDownloadNoticeStrategy) {
                                case NOTICE_AUTO:
                                    long maxNotifySize = (long) (needHandleSize * 0.5d);
                                    if (dTime >= DownloadNoticeStrategy.NOTICE_BY_TIME.getValue()) {
                                        Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                                        if (notifySaving(needNotifySize, needHandleSize)) {
                                            needNotifySize = 0;
                                            lastNotifyTime = curTime;
                                        }
                                    } else if (needNotifySize >= maxNotifySize) {
                                        Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                                        if (notifySaving(needNotifySize, needHandleSize)) {
                                            needNotifySize = 0;
                                            lastNotifyTime = curTime;
                                        }
                                    }
                                    break;
                                case NOTICE_BY_SIZE:
                                    if (needNotifySize >= this.mDownloadNoticeStrategy.getValue()) {
                                        Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                                        if (notifySaving(needNotifySize, needHandleSize)) {
                                            needNotifySize = 0;
                                            lastNotifyTime = curTime;
                                        }
                                    }
                                    break;
                                case NOTICE_BY_TIME:
                                    if (dTime >= this.mDownloadNoticeStrategy.getValue()) {
                                        Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                                        if (notifySaving(needNotifySize, needHandleSize)) {
                                            needNotifySize = 0;
                                            lastNotifyTime = curTime;
                                        }
                                    }
                                    break;
                            }
                        } else {
                            if (needNotifySize > 0) {
                                Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                                if (notifySaving(needNotifySize, needHandleSize)) {
                                    needNotifySize = 0;
                                }
                            }
                            if (needHandleSize != handledFileSize && tempFile.length() == this.mFileTotalSize) {
                                if (saveFile.exists()) {
                                    boolean deleteResult = saveFile.delete();
                                    if (!deleteResult) {
                                        throw new FileSaveException("delete old file:" + saveFile.getAbsolutePath() + " failed!", FileSaveException.TYPE_FILE_CAN_NOT_STORAGE);
                                    }
                                }
                                isCompleted = tempFile.renameTo(saveFile);
                                if (!isCompleted) {
                                    isCompleted = FileUtil.copyFile(tempFile, saveFile, true);
                                }
                                if (!isCompleted) {
                                    throw new FileSaveException("rename temp file:" + tempFile.getAbsolutePath() + " to save " + saveFile.getAbsolutePath() + " failed!", FileSaveException.TYPE_RENAME_TEMP_FILE_ERROR);
                                }
                                filePath = saveFile.getAbsolutePath();
                                Log.m5156d(TAG, TAG + ".saveData 3、文件保存完成，路径：" + saveFile.getAbsolutePath() + "，url：" + url);
                            } else if (!this.mIsStopped) {
                                throw new FileSaveException("saving data error!", FileSaveException.TYPE_UNKNOWN);
                            }
                            if (randomAccessFile2 != null) {
                                try {
                                    randomAccessFile2.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (0 == 0) {
                                notifyEnd(needNotifySize, isCompleted);
                            }
                            this.mIsStopped = true;
                            Log.m5156d(TAG, TAG + ".saveData 3、文件保存【已结束】，是否有异常：false，保存路径：" + filePath + "，url：" + url);
                        }
                    }
                    if (needNotifySize > 0) {
                        Log.m5156d(TAG, TAG + ".saveData 2、正在写文件缓存，已处理：" + handledFileSize + "，总共需要处理：" + needHandleSize + "，完成（百分比）：" + MathUtil.formatNumber((handledFileSize / needHandleSize) * 100.0d) + "%，url：" + url);
                        if (notifySaving(needNotifySize, needHandleSize)) {
                            needNotifySize = 0;
                        }
                    }
                    if (needHandleSize != handledFileSize) {
                        if (!this.mIsStopped) {
                            throw new FileSaveException("saving data error!", FileSaveException.TYPE_UNKNOWN);
                        }
                    } else if (!this.mIsStopped) {
                        throw new FileSaveException("saving data error!", FileSaveException.TYPE_UNKNOWN);
                    }
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    if (0 == 0) {
                        notifyEnd(needNotifySize, isCompleted);
                    }
                    this.mIsStopped = true;
                    Log.m5156d(TAG, TAG + ".saveData 3、文件保存【已结束】，是否有异常：false，保存路径：" + filePath + "，url：" + url);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (e instanceof FileSaveException) {
                        throw ((FileSaveException) e);
                    }
                    throw new FileSaveException(e);
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (1 == 0) {
                        notifyEnd(0, false);
                    }
                    this.mIsStopped = true;
                    Log.m5156d(TAG, TAG + ".saveData 3、文件保存【已结束】，是否有异常：true，保存路径：" + ((String) null) + "，url：" + url);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void notifyStart() {
        if (this.mOnFileSaveListener != null) {
            this.mOnFileSaveListener.onSaveDataStart();
        }
        Log.m5160i(TAG, "file-downloader-save 准备开始保存数据");
    }

    private boolean notifySaving(int needNotifySize, long needHandleSize) {
        if (this.mOnFileSaveListener == null) {
            return false;
        }
        this.mOnFileSaveListener.onSavingData(needNotifySize, needHandleSize);
        Log.m5160i(TAG, "file-downloader-save 正在保存数据，needNotifySize：" + needNotifySize + "，needHandleSize：" + needHandleSize);
        return true;
    }

    private void notifyEnd(int increaseSize, boolean complete) {
        if (!this.mIsNotifyEnd) {
            if (this.mOnFileSaveListener != null) {
                this.mOnFileSaveListener.onSaveDataEnd(increaseSize, complete);
            }
            this.mIsNotifyEnd = true;
            Log.m5160i(TAG, "file-downloader-save 保存数据完成，是否整个文件全部下载完成：" + complete);
        }
    }

    private void checkIsStop() throws FileSaveException {
        if (isStopped()) {
            Log.m5158e(TAG, TAG + ".checkIsStop --已经处理完了/强制停止了，不能再处理数据！");
            throw new FileSaveException("the file saver has been stopped,it can not handle data any more!", FileSaveException.TYPE_SAVER_HAS_BEEN_STOPPED);
        }
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public void stop() {
        this.mIsStopped = true;
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public boolean isStopped() {
        return this.mIsStopped;
    }

    public static class FileSaveException extends FailException {
        public static final String TYPE_RENAME_TEMP_FILE_ERROR = FileSaveException.class.getName() + "_TYPE_RENAME_TEMP_FILE_ERROR";
        public static final String TYPE_SAVER_HAS_BEEN_STOPPED = FileSaveException.class.getName() + "_TYPE_SAVER_HAS_BEEN_STOPPED";
        public static final String TYPE_TEMP_FILE_DOES_NOT_EXIST = FileSaveException.class.getName() + "_TYPE_TEMP_FILE_DOES_NOT_EXIST";
        public static final String TYPE_FILE_CAN_NOT_STORAGE = FileSaveException.class.getName() + "_TYPE_FILE_CAN_NOT_STORAGE";

        public FileSaveException(String detailMessage, String type) {
            super(detailMessage, type);
        }

        public FileSaveException(Throwable throwable) {
            super(throwable);
        }

        @Override // org.wlf.filedownloader.base.FailReason
        protected void onInitTypeWithThrowable(Throwable throwable) {
            super.onInitTypeWithThrowable(throwable);
            if (!isTypeInit() && throwable != null) {
                if (throwable instanceof FailReason) {
                    FailReason failReason = (FailReason) throwable;
                    setTypeByOriginalClassInstanceType(failReason.getOriginalCause());
                    if (isTypeInit()) {
                    }
                    return;
                }
                setTypeByOriginalClassInstanceType(throwable);
            }
        }

        private void setTypeByOriginalClassInstanceType(Throwable throwable) {
            if (throwable != null && (throwable instanceof IOException)) {
            }
        }
    }
}
