package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes.dex */
public final class FileUtils {
    /* JADX WARN: Code duplicated, block: B:59:0x00b3 A[Catch: IOException -> 0x00af, TRY_LEAVE, TryCatch #9 {IOException -> 0x00af, blocks: (B:55:0x00ab, B:59:0x00b3), top: B:66:0x00ab }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [int] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.FileOutputStream] */
    public static boolean copyRawFile(Context context, int i, String str) throws Throwable {
        InputStream inputStream = null;
        boolean z = false;
        try {
            try {
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.createNewFile();
                    } else if (file.isDirectory()) {
                        file = new File(str + File.separator + context.getResources().getResourceName(i));
                    }
                    byte[] bArr = new byte[4096];
                    InputStream inputStreamOpenRawResource = context.getResources().openRawResource(i);
                    try {
                        i = new FileOutputStream(file);
                        while (true) {
                            try {
                                int i2 = inputStreamOpenRawResource.read(bArr);
                                if (i2 <= 0) {
                                    break;
                                }
                                i.write(bArr, 0, i2);
                            } catch (FileNotFoundException e) {
                                inputStream = inputStreamOpenRawResource;
                                e = e;
                                i = i;
                                ThrowableExtension.printStackTrace(e);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (i != 0) {
                                    i.close();
                                }
                            } catch (IOException e2) {
                                inputStream = inputStreamOpenRawResource;
                                e = e2;
                                i = i;
                                ThrowableExtension.printStackTrace(e);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (i != 0) {
                                    i.close();
                                }
                            } catch (Throwable th) {
                                inputStream = inputStreamOpenRawResource;
                                th = th;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                        if (i != 0) {
                                            i.close();
                                        }
                                    } catch (IOException e3) {
                                        ThrowableExtension.printStackTrace(e3);
                                        throw th;
                                    }
                                } else if (i != 0) {
                                    i.close();
                                }
                                throw th;
                            }
                        }
                        z = true;
                        if (inputStreamOpenRawResource != null) {
                            inputStreamOpenRawResource.close();
                        }
                        i.close();
                    } catch (FileNotFoundException e4) {
                        inputStream = inputStreamOpenRawResource;
                        e = e4;
                        i = 0;
                    } catch (IOException e5) {
                        inputStream = inputStreamOpenRawResource;
                        e = e5;
                        i = 0;
                    } catch (Throwable th2) {
                        inputStream = inputStreamOpenRawResource;
                        th = th2;
                        i = 0;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                i = 0;
            } catch (IOException e7) {
                e = e7;
                i = 0;
            } catch (Throwable th4) {
                th = th4;
                i = 0;
            }
        } catch (IOException e8) {
            ThrowableExtension.printStackTrace(e8);
        }
        return z;
    }

    public static void copyAssetsFile(Context context, String str, String str2) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i > 0) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.flush();
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1057a(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        while (stringBuffer.charAt(0) == File.separatorChar) {
            stringBuffer.deleteCharAt(0);
            if (stringBuffer.length() == 0) {
                return null;
            }
        }
        while (stringBuffer.charAt(stringBuffer.length() - 1) == File.separator.charAt(0)) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            if (stringBuffer.length() == 0) {
                return null;
            }
        }
        return stringBuffer.toString();
    }

    public static String makeAbsolutePath(String str, String... strArr) {
        if (str == null) {
            return null;
        }
        for (String str2 : strArr) {
            if (str2 == null) {
                return null;
            }
        }
        String str3 = File.separator;
        StringBuffer stringBuffer = new StringBuffer();
        if (!str.startsWith(str3)) {
            stringBuffer.append(str3);
        }
        stringBuffer.append(str);
        if (!str.endsWith(str3)) {
            stringBuffer.append(str3);
        }
        for (String str4 : strArr) {
            String strM1057a = m1057a(str4);
            if (strM1057a != null) {
                stringBuffer.append(strM1057a);
                stringBuffer.append(str3);
            }
        }
        if (stringBuffer.lastIndexOf(str3) == stringBuffer.length() - 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static void writeStringToFile(File file, String str) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static String getBaseFilePath(String str) {
        int iLastIndexOf;
        return (str == null || str.length() <= 0 || (iLastIndexOf = str.lastIndexOf(46)) < 0 || iLastIndexOf >= str.length()) ? str : str.substring(0, iLastIndexOf);
    }

    public static void recursionDeleteFiles(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                recursionDeleteFiles(file2.getAbsolutePath());
            }
            file2.delete();
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x00a1 A[Catch: IOException -> 0x009c, TRY_LEAVE, TryCatch #12 {IOException -> 0x009c, blocks: (B:30:0x0098, B:34:0x00a1), top: B:94:0x0098 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x00fc A[Catch: IOException -> 0x00f7, TRY_LEAVE, TryCatch #1 {IOException -> 0x00f7, blocks: (B:77:0x00f3, B:81:0x00fc), top: B:88:0x00f3 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x00f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.nio.channels.FileChannel] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.nio.channels.FileChannel] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static final int moveFiles(String str, String str2, boolean z) throws Throwable {
        FileChannel fileChannel;
        Throwable th;
        IOException iOException;
        FileNotFoundException fileNotFoundException;
        FileChannel channel = str2;
        File file = new File(str);
        File file2 = new File((String) channel);
        if (!file.exists()) {
            return 1;
        }
        if (!file2.exists()) {
            return 2;
        }
        if (!file2.isDirectory()) {
            return 3;
        }
        if (file.isFile()) {
            if (new File(((String) channel) + File.separator + file.getName()).exists() && !z) {
                return 4;
            }
            FileChannel fileChannel2 = null;
            try {
                try {
                    try {
                        FileChannel channel2 = new FileInputStream(str).getChannel();
                        try {
                            channel = new FileInputStream(((String) channel) + File.separator + file.getName()).getChannel();
                            long j = 0;
                            while (true) {
                                try {
                                    long jTransferFrom = j + channel.transferFrom(channel2, j, 0 - j);
                                    if (jTransferFrom >= 0) {
                                        break;
                                    }
                                    j = jTransferFrom;
                                } catch (FileNotFoundException e) {
                                    e = e;
                                    fileChannel2 = channel2;
                                    fileNotFoundException = e;
                                    channel = channel;
                                    ThrowableExtension.printStackTrace(fileNotFoundException);
                                    if (fileChannel2 != null) {
                                        fileChannel2.close();
                                    }
                                    if (channel != 0) {
                                        channel.close();
                                    }
                                    return -1;
                                } catch (IOException e2) {
                                    e = e2;
                                    fileChannel2 = channel2;
                                    iOException = e;
                                    channel = channel;
                                    ThrowableExtension.printStackTrace(iOException);
                                    if (fileChannel2 != null) {
                                        fileChannel2.close();
                                    }
                                    if (channel != 0) {
                                        channel.close();
                                    }
                                    return -1;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileChannel2 = channel2;
                                    th = th;
                                    fileChannel = channel;
                                    if (fileChannel2 != null) {
                                        try {
                                            fileChannel2.close();
                                        } catch (IOException e3) {
                                            ThrowableExtension.printStackTrace(e3);
                                            throw th;
                                        }
                                    }
                                    if (fileChannel != 0) {
                                        throw th;
                                    }
                                    fileChannel.close();
                                    throw th;
                                }
                            }
                            file.delete();
                            if (channel2 != null) {
                                try {
                                    channel2.close();
                                    if (channel != 0) {
                                        channel.close();
                                    }
                                } catch (IOException e4) {
                                    ThrowableExtension.printStackTrace(e4);
                                }
                            } else if (channel != 0) {
                                channel.close();
                            }
                        } catch (FileNotFoundException e5) {
                            e = e5;
                            channel = 0;
                        } catch (IOException e6) {
                            e = e6;
                            channel = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            channel = 0;
                        }
                    } catch (IOException e7) {
                        ThrowableExtension.printStackTrace(e7);
                        return -1;
                    }
                } catch (FileNotFoundException e8) {
                    fileNotFoundException = e8;
                    channel = 0;
                } catch (IOException e9) {
                    iOException = e9;
                    channel = 0;
                } catch (Throwable th4) {
                    th = th4;
                    fileChannel = 0;
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (fileChannel != 0) {
                        throw th;
                    }
                    fileChannel.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } else {
            file.isDirectory();
        }
        return 0;
    }

    public static String file2Text(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(str);
        try {
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, i, str2));
            }
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return stringBuffer.toString();
    }
}
