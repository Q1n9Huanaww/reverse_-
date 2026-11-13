package com.umeng.commonsdk.proguard;

import android.os.Process;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.o */
/* JADX INFO: compiled from: ProcessUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class C1792o {

    /* JADX INFO: renamed from: a */
    private static final String f4217a = "\n";

    /* JADX INFO: renamed from: b */
    private static final byte[] f4218b = "\nexit\n".getBytes();

    /* JADX INFO: renamed from: c */
    private static byte[] f4219c = new byte[32];

    /* JADX WARN: Code duplicated, block: B:22:0x0063 A[PHI: r7 r9
  0x0063: PHI (r7v5 ??) = (r7v13 ??), (r7v14 ??), (r7v15 ??) binds: [B:72:0x00d3, B:83:0x00e4, B:21:0x0061] A[DONT_GENERATE, DONT_INLINE]
  0x0063: PHI (r9v19 ??) = (r9v22 ??), (r9v23 ??), (r9v24 ??) binds: [B:72:0x00d3, B:83:0x00e4, B:21:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:86:0x00ea A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:87:0x00eb  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v18, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v21, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX INFO: renamed from: a */
    public static String m4043a(String... strArr) throws Throwable {
        OutputStream outputStream;
        ?? r2;
        ?? r3;
        ?? r4;
        BufferedReader bufferedReader;
        Throwable th;
        ?? r9;
        ?? r10;
        OutputStream outputStream2;
        OutputStream outputStream3;
        ?? r11;
        OutputStream outputStream4;
        ?? r12;
        OutputStream outputStream5;
        ?? r13;
        ?? r5;
        OutputStream outputStream6;
        ?? r14;
        ?? r6;
        OutputStream outputStream7;
        ?? r15;
        ?? r7;
        ?? r8;
        ?? bufferedReader2;
        ?? r16;
        ?? r17;
        ?? r18;
        ?? inputStreamReader;
        ?? errorStream;
        ?? inputStream;
        ?? sb;
        ?? r19;
        ?? r20;
        ?? r21;
        try {
            try {
                strArr = new ProcessBuilder(new String[0]).command((String[]) strArr).start();
                try {
                    outputStream = strArr.getOutputStream();
                    try {
                        inputStream = strArr.getInputStream();
                        try {
                            errorStream = strArr.getErrorStream();
                            try {
                                outputStream.write(f4218b);
                                outputStream.flush();
                                strArr.waitFor();
                                inputStreamReader = new InputStreamReader(inputStream);
                                try {
                                    bufferedReader2 = new BufferedReader(inputStreamReader);
                                    try {
                                        String line = bufferedReader2.readLine();
                                        if (line != null) {
                                            sb = new StringBuilder();
                                            try {
                                                sb.append(line);
                                                sb.append(f4217a);
                                                while (true) {
                                                    String line2 = bufferedReader2.readLine();
                                                    if (line2 == null) {
                                                        break;
                                                    }
                                                    sb.append(line2);
                                                    sb.append(f4217a);
                                                }
                                            } catch (IOException e) {
                                                e = e;
                                                ThrowableExtension.printStackTrace(e);
                                                m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                                r21 = sb;
                                                r20 = strArr;
                                                r19 = sb;
                                                if (strArr != 0) {
                                                }
                                                if (r19 == 0) {
                                                    return null;
                                                }
                                                return r19.toString();
                                            } catch (Exception e2) {
                                                e = e2;
                                                ThrowableExtension.printStackTrace(e);
                                                m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                                r21 = sb;
                                                r20 = strArr;
                                                r19 = sb;
                                                if (strArr != 0) {
                                                }
                                                if (r19 == 0) {
                                                    return null;
                                                }
                                                return r19.toString();
                                            }
                                        } else {
                                            sb = 0;
                                        }
                                        do {
                                        } while (errorStream.read(f4219c) > 0);
                                        m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                        r21 = sb;
                                        r20 = strArr;
                                        r19 = sb;
                                        if (strArr != 0) {
                                            m4047c(r20);
                                            r19 = r21;
                                        }
                                    } catch (IOException e3) {
                                        e = e3;
                                        sb = 0;
                                    } catch (Exception e4) {
                                        e = e4;
                                        sb = 0;
                                    }
                                } catch (IOException e5) {
                                    e = e5;
                                    bufferedReader2 = 0;
                                    inputStream = inputStream;
                                    errorStream = errorStream;
                                    inputStreamReader = inputStreamReader;
                                    strArr = strArr;
                                    sb = bufferedReader2;
                                    ThrowableExtension.printStackTrace(e);
                                    m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                    r21 = sb;
                                    r20 = strArr;
                                    r19 = sb;
                                    if (strArr != 0) {
                                        m4047c(r20);
                                        r19 = r21;
                                    }
                                    if (r19 == 0) {
                                        return null;
                                    }
                                    return r19.toString();
                                } catch (Exception e6) {
                                    e = e6;
                                    bufferedReader2 = 0;
                                    inputStream = inputStream;
                                    errorStream = errorStream;
                                    inputStreamReader = inputStreamReader;
                                    strArr = strArr;
                                    sb = bufferedReader2;
                                    ThrowableExtension.printStackTrace(e);
                                    m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                    r21 = sb;
                                    r20 = strArr;
                                    r19 = sb;
                                    if (strArr != 0) {
                                        m4047c(r20);
                                        r19 = r21;
                                    }
                                    if (r19 == 0) {
                                        return null;
                                    }
                                    return r19.toString();
                                } catch (Throwable th2) {
                                    bufferedReader = null;
                                    th = th2;
                                    r2 = inputStream;
                                    r3 = errorStream;
                                    r4 = inputStreamReader;
                                    m4044a(outputStream, r3, r2, r4, bufferedReader);
                                    if (strArr != 0) {
                                        m4047c(strArr);
                                    }
                                    throw th;
                                }
                            } catch (IOException e7) {
                                e = e7;
                                outputStream7 = null;
                                r18 = inputStream;
                                r17 = errorStream;
                                r16 = strArr;
                                bufferedReader2 = outputStream7;
                                inputStream = r18;
                                errorStream = r17;
                                inputStreamReader = outputStream7;
                                strArr = r16;
                                sb = bufferedReader2;
                                ThrowableExtension.printStackTrace(e);
                                m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                r21 = sb;
                                r20 = strArr;
                                r19 = sb;
                                if (strArr != 0) {
                                    m4047c(r20);
                                    r19 = r21;
                                }
                                if (r19 == 0) {
                                    return null;
                                }
                                return r19.toString();
                            } catch (Exception e8) {
                                e = e8;
                                outputStream6 = null;
                                r8 = inputStream;
                                r7 = errorStream;
                                r15 = strArr;
                                bufferedReader2 = outputStream6;
                                inputStream = r8;
                                errorStream = r7;
                                inputStreamReader = outputStream6;
                                strArr = r15;
                                sb = bufferedReader2;
                                ThrowableExtension.printStackTrace(e);
                                m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                                r21 = sb;
                                r20 = strArr;
                                r19 = sb;
                                if (strArr != 0) {
                                    m4047c(r20);
                                    r19 = r21;
                                }
                                if (r19 == 0) {
                                    return null;
                                }
                                return r19.toString();
                            } catch (Throwable th3) {
                                bufferedReader = null;
                                th = th3;
                                r4 = 0;
                                r2 = inputStream;
                                r3 = errorStream;
                            }
                        } catch (IOException e9) {
                            e = e9;
                            outputStream5 = null;
                            r6 = inputStream;
                            r14 = strArr;
                            outputStream7 = outputStream5;
                            r18 = r6;
                            r17 = outputStream5;
                            r16 = r14;
                            bufferedReader2 = outputStream7;
                            inputStream = r18;
                            errorStream = r17;
                            inputStreamReader = outputStream7;
                            strArr = r16;
                            sb = bufferedReader2;
                            ThrowableExtension.printStackTrace(e);
                            m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                            r21 = sb;
                            r20 = strArr;
                            r19 = sb;
                            if (strArr != 0) {
                                m4047c(r20);
                                r19 = r21;
                            }
                            if (r19 == 0) {
                                return null;
                            }
                            return r19.toString();
                        } catch (Exception e10) {
                            e = e10;
                            outputStream4 = null;
                            r5 = inputStream;
                            r13 = strArr;
                            outputStream6 = outputStream4;
                            r8 = r5;
                            r7 = outputStream4;
                            r15 = r13;
                            bufferedReader2 = outputStream6;
                            inputStream = r8;
                            errorStream = r7;
                            inputStreamReader = outputStream6;
                            strArr = r15;
                            sb = bufferedReader2;
                            ThrowableExtension.printStackTrace(e);
                            m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                            r21 = sb;
                            r20 = strArr;
                            r19 = sb;
                            if (strArr != 0) {
                                m4047c(r20);
                                r19 = r21;
                            }
                            if (r19 == 0) {
                                return null;
                            }
                            return r19.toString();
                        } catch (Throwable th4) {
                            r4 = 0;
                            bufferedReader = null;
                            th = th4;
                            r3 = 0;
                            r2 = inputStream;
                        }
                    } catch (IOException e11) {
                        e = e11;
                        outputStream3 = null;
                        r12 = strArr;
                        outputStream5 = outputStream3;
                        r6 = outputStream3;
                        r14 = r12;
                        outputStream7 = outputStream5;
                        r18 = r6;
                        r17 = outputStream5;
                        r16 = r14;
                        bufferedReader2 = outputStream7;
                        inputStream = r18;
                        errorStream = r17;
                        inputStreamReader = outputStream7;
                        strArr = r16;
                        sb = bufferedReader2;
                        ThrowableExtension.printStackTrace(e);
                        m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                        r21 = sb;
                        r20 = strArr;
                        r19 = sb;
                        if (strArr != 0) {
                            m4047c(r20);
                            r19 = r21;
                        }
                        if (r19 == 0) {
                            return null;
                        }
                        return r19.toString();
                    } catch (Exception e12) {
                        e = e12;
                        outputStream2 = null;
                        r11 = strArr;
                        outputStream4 = outputStream2;
                        r5 = outputStream2;
                        r13 = r11;
                        outputStream6 = outputStream4;
                        r8 = r5;
                        r7 = outputStream4;
                        r15 = r13;
                        bufferedReader2 = outputStream6;
                        inputStream = r8;
                        errorStream = r7;
                        inputStreamReader = outputStream6;
                        strArr = r15;
                        sb = bufferedReader2;
                        ThrowableExtension.printStackTrace(e);
                        m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                        r21 = sb;
                        r20 = strArr;
                        r19 = sb;
                        if (strArr != 0) {
                            m4047c(r20);
                            r19 = r21;
                        }
                        if (r19 == 0) {
                            return null;
                        }
                        return r19.toString();
                    } catch (Throwable th5) {
                        r3 = 0;
                        r4 = 0;
                        bufferedReader = null;
                        th = th5;
                        r2 = 0;
                    }
                } catch (IOException e13) {
                    e = e13;
                    outputStream = null;
                    r10 = strArr;
                    outputStream3 = outputStream;
                    r12 = r10;
                    outputStream5 = outputStream3;
                    r6 = outputStream3;
                    r14 = r12;
                    outputStream7 = outputStream5;
                    r18 = r6;
                    r17 = outputStream5;
                    r16 = r14;
                    bufferedReader2 = outputStream7;
                    inputStream = r18;
                    errorStream = r17;
                    inputStreamReader = outputStream7;
                    strArr = r16;
                    sb = bufferedReader2;
                    ThrowableExtension.printStackTrace(e);
                    m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                    r21 = sb;
                    r20 = strArr;
                    r19 = sb;
                    if (strArr != 0) {
                        m4047c(r20);
                        r19 = r21;
                    }
                    if (r19 == 0) {
                        return null;
                    }
                    return r19.toString();
                } catch (Exception e14) {
                    e = e14;
                    outputStream = null;
                    r9 = strArr;
                    outputStream2 = outputStream;
                    r11 = r9;
                    outputStream4 = outputStream2;
                    r5 = outputStream2;
                    r13 = r11;
                    outputStream6 = outputStream4;
                    r8 = r5;
                    r7 = outputStream4;
                    r15 = r13;
                    bufferedReader2 = outputStream6;
                    inputStream = r8;
                    errorStream = r7;
                    inputStreamReader = outputStream6;
                    strArr = r15;
                    sb = bufferedReader2;
                    ThrowableExtension.printStackTrace(e);
                    m4044a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader2);
                    r21 = sb;
                    r20 = strArr;
                    r19 = sb;
                    if (strArr != 0) {
                        m4047c(r20);
                        r19 = r21;
                    }
                    if (r19 == 0) {
                        return null;
                    }
                    return r19.toString();
                } catch (Throwable th6) {
                    r2 = 0;
                    r3 = 0;
                    r4 = 0;
                    bufferedReader = null;
                    th = th6;
                    outputStream = null;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (IOException e15) {
            e = e15;
            r10 = 0;
            outputStream = null;
        } catch (Exception e16) {
            e = e16;
            r9 = 0;
            outputStream = null;
        } catch (Throwable th8) {
            outputStream = null;
            r2 = 0;
            r3 = 0;
            r4 = 0;
            bufferedReader = null;
            th = th8;
            strArr = 0;
        }
        if (r19 == 0) {
            return null;
        }
        return r19.toString();
    }

    /* JADX INFO: renamed from: a */
    private static void m4044a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e3) {
                ThrowableExtension.printStackTrace(e3);
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException e4) {
                ThrowableExtension.printStackTrace(e4);
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                ThrowableExtension.printStackTrace(e5);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m4045a(Process process) {
        int iM4046b = m4046b(process);
        if (iM4046b != 0) {
            try {
                try {
                    Process.killProcess(iM4046b);
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                process.destroy();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static int m4046b(Process process) {
        String string = process.toString();
        try {
            return Integer.parseInt(string.substring(string.indexOf("=") + 1, string.indexOf("]")));
        } catch (Exception unused) {
            return 0;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m4047c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    m4045a(process);
                }
            } catch (IllegalThreadStateException unused) {
                m4045a(process);
            }
        }
    }
}
