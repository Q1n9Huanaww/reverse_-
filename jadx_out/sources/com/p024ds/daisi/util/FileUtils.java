package com.p024ds.daisi.util;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");

    public static String makePath(String str, String str2) {
        if (str.endsWith(File.separator)) {
            return str + str2;
        }
        return str + File.separator + str2;
    }

    public static String readTextFile(File file) {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            do {
                i = bufferedReader.read();
                if (i != -1) {
                    stringBuffer.append((char) i);
                }
            } while (i != -1);
            bufferedReader.close();
            String string = stringBuffer.toString();
            int iIndexOf = string.indexOf(IOUtils.LINE_SEPARATOR_WINDOWS);
            int iIndexOf2 = string.indexOf("\r");
            if (iIndexOf != -1) {
                return string.replaceAll(IOUtils.LINE_SEPARATOR_WINDOWS, "\n");
            }
            return iIndexOf2 != -1 ? string.replaceAll("\r", "\n") : string;
        } catch (IOException unused) {
            return null;
        } catch (OutOfMemoryError unused2) {
            return null;
        }
    }

    public static class FileSuffixrFilter implements FilenameFilter {
        private String fileSuffix;

        public FileSuffixrFilter(String str) {
            this.fileSuffix = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(this.fileSuffix);
        }
    }

    public static String formetFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        if (j < 1024) {
            return decimalFormat.format(j) + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        }
        if (j < org.apache.commons.p092io.FileUtils.ONE_GB) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "G";
    }

    public static class copyFilesTask extends AsyncTask<File[], ArrayList<HashMap<String, Integer>>, ArrayList<HashMap<String, Boolean>>> {
        private OnPostExecuteListener onPostExecuteListener;

        public interface OnPostExecuteListener {
            void onPostExecute(ArrayList<HashMap<String, Boolean>> arrayList);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ArrayList<HashMap<String, Boolean>> doInBackground(File[]... fileArr) {
            new ArrayList(fileArr[0].length);
            for (int i = 0; i < fileArr[0].length; i++) {
                FileUtils.copyFile(fileArr[0][i], fileArr[1][i]);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(ArrayList<HashMap<String, Integer>>... arrayListArr) {
            super.onProgressUpdate((Object[]) arrayListArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ArrayList<HashMap<String, Boolean>> arrayList) {
            super.onPostExecute(arrayList);
            this.onPostExecuteListener.onPostExecute(arrayList);
        }

        public void setOnPostExecuteListener(OnPostExecuteListener onPostExecuteListener) {
            this.onPostExecuteListener = onPostExecuteListener;
        }
    }

    public static boolean copyFile(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return copyToFile(fileInputStream, file2);
            } finally {
                fileInputStream.close();
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean copyToFile(InputStream inputStream, File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                }
                fileOutputStream.flush();
                try {
                    fileOutputStream.getFD().sync();
                } catch (IOException unused) {
                }
                fileOutputStream.close();
                return true;
            } catch (Throwable th) {
                fileOutputStream.flush();
                try {
                    fileOutputStream.getFD().sync();
                } catch (IOException unused2) {
                }
                fileOutputStream.close();
                throw th;
            }
        } catch (IOException unused3) {
            return false;
        }
    }

    public static boolean isFilenameSafe(File file) {
        return SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static String readTextFile(File file, int i, String str) throws IOException {
        int i2;
        int i3;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try {
            long length = file.length();
            if (i <= 0 && (length <= 0 || i != 0)) {
                if (i >= 0) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    do {
                        i2 = bufferedInputStream.read(bArr);
                        if (i2 > 0) {
                            byteArrayOutputStream.write(bArr, 0, i2);
                        }
                    } while (i2 == bArr.length);
                    String string = byteArrayOutputStream.toString();
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return string;
                }
                byte[] bArr2 = null;
                byte[] bArr3 = null;
                boolean z = false;
                while (true) {
                    if (bArr2 != null) {
                        z = true;
                    }
                    if (bArr2 == null) {
                        bArr2 = new byte[-i];
                    }
                    i3 = bufferedInputStream.read(bArr2);
                    if (i3 != bArr2.length) {
                        break;
                    }
                    byte[] bArr4 = bArr3;
                    bArr3 = bArr2;
                    bArr2 = bArr4;
                }
                if (bArr3 == null && i3 <= 0) {
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return "";
                }
                if (bArr3 == null) {
                    String str2 = new String(bArr2, 0, i3);
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return str2;
                }
                if (i3 > 0) {
                    System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
                    System.arraycopy(bArr2, 0, bArr3, bArr3.length - i3, i3);
                    z = true;
                }
                if (str != null && z) {
                    String str3 = str + new String(bArr3);
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return str3;
                }
                String str4 = new String(bArr3);
                bufferedInputStream.close();
                fileInputStream.close();
                return str4;
            }
            if (length > 0 && (i == 0 || length < i)) {
                i = (int) length;
            }
            byte[] bArr5 = new byte[i + 1];
            int i4 = bufferedInputStream.read(bArr5);
            if (i4 <= 0) {
                bufferedInputStream.close();
                fileInputStream.close();
                return "";
            }
            if (i4 <= i) {
                String str5 = new String(bArr5, 0, i4);
                bufferedInputStream.close();
                fileInputStream.close();
                return str5;
            }
            if (str == null) {
                String str6 = new String(bArr5, 0, i);
                bufferedInputStream.close();
                fileInputStream.close();
                return str6;
            }
            String str7 = new String(bArr5, 0, i) + str;
            bufferedInputStream.close();
            fileInputStream.close();
            return str7;
        } catch (Throwable th) {
            bufferedInputStream.close();
            fileInputStream.close();
            throw th;
        }
    }

    public static void stringToFile(String str, String str2) throws IOException {
        FileWriter fileWriter = new FileWriter(str);
        try {
            fileWriter.write(str2);
        } finally {
            fileWriter.close();
        }
    }
}
