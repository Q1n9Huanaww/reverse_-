package com.cyjh.share.util;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";
    private static boolean mCommandFinished;

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false, 0).result == 0;
    }

    public static CommandResult execCommand(String str, boolean z, int i) {
        return execCommand(new String[]{str}, z, true, i);
    }

    public static CommandResult execCommand(List<String> list, boolean z, int i) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, true, i);
    }

    public static CommandResult execCommand(String[] strArr, boolean z, int i) {
        return execCommand(strArr, z, true, i);
    }

    public static CommandResult execCommand(String str, boolean z, boolean z2, int i) {
        return execCommand(new String[]{str}, z, z2, i);
    }

    public static CommandResult execCommand(List<String> list, boolean z, boolean z2, int i) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, i);
    }

    /* JADX WARN: Code duplicated, block: B:115:0x0184 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:116:0x0186 A[Catch: IOException -> 0x0182, TryCatch #13 {IOException -> 0x0182, blocks: (B:112:0x017e, B:116:0x0186, B:118:0x018b), top: B:184:0x017e }] */
    /* JADX WARN: Code duplicated, block: B:118:0x018b A[Catch: IOException -> 0x0182, TRY_LEAVE, TryCatch #13 {IOException -> 0x0182, blocks: (B:112:0x017e, B:116:0x0186, B:118:0x018b), top: B:184:0x017e }] */
    /* JADX WARN: Code duplicated, block: B:122:0x0194 A[PHI: r4 r8 r9 r10
  0x0194: PHI (r4v8 java.lang.StringBuilder) = (r4v6 java.lang.StringBuilder), (r4v7 java.lang.StringBuilder), (r4v9 java.lang.StringBuilder) binds: [B:121:0x0192, B:138:0x01b9, B:155:0x01dd] A[DONT_GENERATE, DONT_INLINE]
  0x0194: PHI (r8v16 ??) = (r8v14 ??), (r8v15 ??), (r8v17 ??) binds: [B:121:0x0192, B:138:0x01b9, B:155:0x01dd] A[DONT_GENERATE, DONT_INLINE]
  0x0194: PHI (r9v7 int) = (r9v5 int), (r9v6 int), (r9v8 int) binds: [B:121:0x0192, B:138:0x01b9, B:155:0x01dd] A[DONT_GENERATE, DONT_INLINE]
  0x0194: PHI (r10v12 java.lang.StringBuilder) = (r10v10 java.lang.StringBuilder), (r10v11 java.lang.StringBuilder), (r10v13 java.lang.StringBuilder) binds: [B:121:0x0192, B:138:0x01b9, B:155:0x01dd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ab A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:133:0x01ad A[Catch: IOException -> 0x01a9, TryCatch #26 {IOException -> 0x01a9, blocks: (B:129:0x01a5, B:133:0x01ad, B:135:0x01b2), top: B:194:0x01a5 }] */
    /* JADX WARN: Code duplicated, block: B:135:0x01b2 A[Catch: IOException -> 0x01a9, TRY_LEAVE, TryCatch #26 {IOException -> 0x01a9, blocks: (B:129:0x01a5, B:133:0x01ad, B:135:0x01b2), top: B:194:0x01a5 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x01cf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:150:0x01d1 A[Catch: IOException -> 0x01cd, TryCatch #23 {IOException -> 0x01cd, blocks: (B:146:0x01c9, B:150:0x01d1, B:152:0x01d6), top: B:190:0x01c9 }] */
    /* JADX WARN: Code duplicated, block: B:152:0x01d6 A[Catch: IOException -> 0x01cd, TRY_LEAVE, TryCatch #23 {IOException -> 0x01cd, blocks: (B:146:0x01c9, B:150:0x01d1, B:152:0x01d6), top: B:190:0x01c9 }] */
    /* JADX WARN: Code duplicated, block: B:159:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:160:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:163:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:172:0x01ff A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:173:0x0201 A[Catch: IOException -> 0x01fd, TryCatch #14 {IOException -> 0x01fd, blocks: (B:169:0x01f9, B:173:0x0201, B:175:0x0206), top: B:186:0x01f9 }] */
    /* JADX WARN: Code duplicated, block: B:175:0x0206 A[Catch: IOException -> 0x01fd, TRY_LEAVE, TryCatch #14 {IOException -> 0x01fd, blocks: (B:169:0x01f9, B:173:0x0201, B:175:0x0206), top: B:186:0x01f9 }] */
    /* JADX WARN: Code duplicated, block: B:179:0x020f  */
    /* JADX WARN: Code duplicated, block: B:184:0x017e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x01f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:190:0x01c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:194:0x01a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:214:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0112 A[Catch: IOException -> 0x010e, TryCatch #25 {IOException -> 0x010e, blocks: (B:69:0x010a, B:73:0x0112, B:75:0x0117), top: B:192:0x010a }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0117 A[Catch: IOException -> 0x010e, TRY_LEAVE, TryCatch #25 {IOException -> 0x010e, blocks: (B:69:0x010a, B:73:0x0112, B:75:0x0117), top: B:192:0x010a }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v50 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
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
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v23, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v36 */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v43 */
    /* JADX WARN: Type inference failed for: r8v44 */
    /* JADX WARN: Type inference failed for: r8v45 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v47 */
    /* JADX WARN: Type inference failed for: r8v48 */
    /* JADX WARN: Type inference failed for: r8v49 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v50 */
    /* JADX WARN: Type inference failed for: r8v51 */
    /* JADX WARN: Type inference failed for: r8v52 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public static CommandResult execCommand(String[] strArr, boolean z, boolean z2, int i) throws Throwable {
        ?? r8;
        StringBuilder sb;
        ?? r9;
        ?? r10;
        ?? r11;
        ?? r3;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        ?? r12;
        Object obj;
        StringBuilder sb5;
        StringBuilder sb6;
        int iWaitFor;
        ?? r13;
        Object obj2;
        StringBuilder sb7;
        StringBuilder sb8;
        ?? r14;
        Object obj3;
        StringBuilder sb9;
        StringBuilder sb10;
        ?? r15;
        ?? r16;
        StringBuilder sb11;
        ?? r4;
        ?? r2;
        ?? r1;
        ?? r5;
        ?? r6;
        ?? r7;
        ?? r17;
        ?? r18;
        ?? r19;
        String string;
        StringBuilder sb12;
        StringBuilder sb13;
        BufferedReader bufferedReader;
        ?? r20;
        BufferedReader bufferedReader2;
        StringBuilder sb14;
        ?? r21;
        BufferedReader bufferedReader3;
        StringBuilder sb15;
        ?? r22;
        BufferedReader bufferedReader4;
        StringBuilder sb16;
        ?? r0 = 0;
        r0 = 0;
        ?? bufferedReader5 = -1;
        if (strArr == null || strArr.length == 0) {
            return new CommandResult(-1, null, null);
        }
        BufferedReader bufferedReader6 = null;
        mCommandFinished = false;
        try {
            try {
                z = Runtime.getRuntime().exec(z != 0 ? "su" : "sh");
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(z.getOutputStream());
                    try {
                        try {
                            for (String str : strArr) {
                                if (str != null) {
                                    dataOutputStream.write(str.getBytes());
                                    dataOutputStream.writeBytes("\n");
                                    dataOutputStream.flush();
                                }
                            }
                            dataOutputStream.writeBytes("exit\n");
                            dataOutputStream.flush();
                            if (i > 0) {
                                new CommandTimeoutThread(z, i).start();
                            }
                            iWaitFor = z.waitFor();
                            try {
                                Log.i("SYS", "command finished.");
                                mCommandFinished = true;
                                if (z2) {
                                    sb12 = new StringBuilder();
                                    try {
                                        sb13 = new StringBuilder();
                                        try {
                                            bufferedReader5 = new BufferedReader(new InputStreamReader(z.getInputStream()));
                                            try {
                                                bufferedReader = new BufferedReader(new InputStreamReader(z.getErrorStream()));
                                                while (true) {
                                                    try {
                                                        String line = bufferedReader5.readLine();
                                                        if (line == null) {
                                                            break;
                                                        }
                                                        sb12.append(line);
                                                    } catch (IOException e) {
                                                        e = e;
                                                        sb16 = sb13;
                                                        r22 = bufferedReader5;
                                                        bufferedReader4 = bufferedReader;
                                                        sb = sb12;
                                                        r19 = r22;
                                                        r18 = bufferedReader4;
                                                        r17 = dataOutputStream;
                                                        sb11 = sb16;
                                                        r16 = z;
                                                        ThrowableExtension.printStackTrace(e);
                                                        if (r17 != 0) {
                                                            try {
                                                                r17.close();
                                                                if (r19 != 0) {
                                                                    r19.close();
                                                                }
                                                                if (r18 != 0) {
                                                                    r18.close();
                                                                }
                                                            } catch (IOException e2) {
                                                                ThrowableExtension.printStackTrace(e2);
                                                                if (r16 != 0) {
                                                                    r16.destroy();
                                                                }
                                                                if (sb == null) {
                                                                    string = null;
                                                                } else {
                                                                    string = sb.toString();
                                                                }
                                                                return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                            }
                                                        } else {
                                                            if (r19 != 0) {
                                                                r19.close();
                                                            }
                                                            if (r18 != 0) {
                                                                r18.close();
                                                            }
                                                        }
                                                        if (r16 != 0) {
                                                            r16.destroy();
                                                        }
                                                    } catch (Exception e3) {
                                                        e = e3;
                                                        sb15 = sb13;
                                                        r21 = bufferedReader5;
                                                        bufferedReader3 = bufferedReader;
                                                        sb = sb12;
                                                        r7 = r21;
                                                        r6 = bufferedReader3;
                                                        r5 = dataOutputStream;
                                                        sb11 = sb15;
                                                        r16 = z;
                                                        ThrowableExtension.printStackTrace(e);
                                                        if (r5 != 0) {
                                                            try {
                                                                r5.close();
                                                                if (r7 != 0) {
                                                                    r7.close();
                                                                }
                                                                if (r6 != 0) {
                                                                    r6.close();
                                                                }
                                                            } catch (IOException e4) {
                                                                ThrowableExtension.printStackTrace(e4);
                                                                if (r16 != 0) {
                                                                    r16.destroy();
                                                                }
                                                                if (sb == null) {
                                                                    string = null;
                                                                } else {
                                                                    string = sb.toString();
                                                                }
                                                                return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                            }
                                                        } else {
                                                            if (r7 != 0) {
                                                                r7.close();
                                                            }
                                                            if (r6 != 0) {
                                                                r6.close();
                                                            }
                                                        }
                                                        if (r16 != 0) {
                                                            r16.destroy();
                                                        }
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        sb14 = sb13;
                                                        r20 = bufferedReader5;
                                                        bufferedReader2 = bufferedReader;
                                                        sb = sb12;
                                                        r1 = r20;
                                                        r2 = bufferedReader2;
                                                        r4 = dataOutputStream;
                                                        sb11 = sb14;
                                                        r16 = z;
                                                        ThrowableExtension.printStackTrace(th);
                                                        if (r4 != 0) {
                                                            try {
                                                                r4.close();
                                                                if (r1 != 0) {
                                                                    r1.close();
                                                                }
                                                                if (r2 != 0) {
                                                                    r2.close();
                                                                }
                                                            } catch (IOException e5) {
                                                                ThrowableExtension.printStackTrace(e5);
                                                                if (r16 != 0) {
                                                                    r16.destroy();
                                                                }
                                                                if (sb == null) {
                                                                    string = null;
                                                                } else {
                                                                    string = sb.toString();
                                                                }
                                                                return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                            }
                                                        } else {
                                                            if (r1 != 0) {
                                                                r1.close();
                                                            }
                                                            if (r2 != 0) {
                                                                r2.close();
                                                            }
                                                        }
                                                        if (r16 != 0) {
                                                            r16.destroy();
                                                        }
                                                    }
                                                }
                                                while (true) {
                                                    String line2 = bufferedReader.readLine();
                                                    if (line2 == null) {
                                                        break;
                                                    }
                                                    sb13.append(line2);
                                                }
                                            } catch (IOException e6) {
                                                sb11 = sb13;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                e = e6;
                                                r18 = 0;
                                                r19 = bufferedReader5;
                                                r17 = dataOutputStream;
                                                r16 = z;
                                                ThrowableExtension.printStackTrace(e);
                                                if (r17 != 0) {
                                                    r17.close();
                                                    if (r19 != 0) {
                                                        r19.close();
                                                    }
                                                    if (r18 != 0) {
                                                        r18.close();
                                                    }
                                                } else {
                                                    if (r19 != 0) {
                                                        r19.close();
                                                    }
                                                    if (r18 != 0) {
                                                        r18.close();
                                                    }
                                                }
                                                if (r16 != 0) {
                                                    r16.destroy();
                                                }
                                            } catch (Exception e7) {
                                                sb11 = sb13;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                e = e7;
                                                r6 = 0;
                                                r7 = bufferedReader5;
                                                r5 = dataOutputStream;
                                                r16 = z;
                                                ThrowableExtension.printStackTrace(e);
                                                if (r5 != 0) {
                                                    r5.close();
                                                    if (r7 != 0) {
                                                        r7.close();
                                                    }
                                                    if (r6 != 0) {
                                                        r6.close();
                                                    }
                                                } else {
                                                    if (r7 != 0) {
                                                        r7.close();
                                                    }
                                                    if (r6 != 0) {
                                                        r6.close();
                                                    }
                                                }
                                                if (r16 != 0) {
                                                    r16.destroy();
                                                }
                                            } catch (Throwable th2) {
                                                sb11 = sb13;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                th = th2;
                                                r2 = 0;
                                                r1 = bufferedReader5;
                                                r4 = dataOutputStream;
                                                r16 = z;
                                                ThrowableExtension.printStackTrace(th);
                                                if (r4 != 0) {
                                                    r4.close();
                                                    if (r1 != 0) {
                                                        r1.close();
                                                    }
                                                    if (r2 != 0) {
                                                        r2.close();
                                                    }
                                                } else {
                                                    if (r1 != 0) {
                                                        r1.close();
                                                    }
                                                    if (r2 != 0) {
                                                        r2.close();
                                                    }
                                                }
                                                if (r16 != 0) {
                                                    r16.destroy();
                                                }
                                            }
                                        } catch (IOException e8) {
                                            sb11 = sb13;
                                            r18 = 0;
                                            sb = sb12;
                                            iWaitFor = iWaitFor;
                                            e = e8;
                                            r19 = 0;
                                            r17 = dataOutputStream;
                                            r16 = z;
                                        } catch (Exception e9) {
                                            sb11 = sb13;
                                            r6 = 0;
                                            sb = sb12;
                                            iWaitFor = iWaitFor;
                                            e = e9;
                                            r7 = 0;
                                            r5 = dataOutputStream;
                                            r16 = z;
                                        } catch (Throwable th3) {
                                            sb11 = sb13;
                                            r2 = 0;
                                            sb = sb12;
                                            iWaitFor = iWaitFor;
                                            th = th3;
                                            r1 = 0;
                                            r4 = dataOutputStream;
                                            r16 = z;
                                        }
                                    } catch (IOException e10) {
                                        r22 = 0;
                                        bufferedReader4 = null;
                                        sb16 = null;
                                        e = e10;
                                    } catch (Exception e11) {
                                        r21 = 0;
                                        bufferedReader3 = null;
                                        sb15 = null;
                                        e = e11;
                                    } catch (Throwable th4) {
                                        r20 = 0;
                                        bufferedReader2 = null;
                                        sb14 = null;
                                        th = th4;
                                    }
                                } else {
                                    sb12 = null;
                                    sb13 = null;
                                    bufferedReader5 = 0;
                                    bufferedReader = null;
                                }
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                        if (bufferedReader5 != 0) {
                                            bufferedReader5.close();
                                        }
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                        }
                                    } catch (IOException e12) {
                                        ThrowableExtension.printStackTrace(e12);
                                    }
                                } else {
                                    if (bufferedReader5 != 0) {
                                        bufferedReader5.close();
                                    }
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                }
                                if (z != 0) {
                                    z.destroy();
                                }
                                sb11 = sb13;
                                sb = sb12;
                                iWaitFor = iWaitFor;
                            } catch (IOException e13) {
                                sb = null;
                                r19 = 0;
                                r18 = 0;
                                sb11 = null;
                                iWaitFor = iWaitFor;
                                e = e13;
                                r17 = dataOutputStream;
                                r16 = z;
                            } catch (Exception e14) {
                                sb = null;
                                r7 = 0;
                                r6 = 0;
                                sb11 = null;
                                iWaitFor = iWaitFor;
                                e = e14;
                                r5 = dataOutputStream;
                                r16 = z;
                            } catch (Throwable th5) {
                                sb = null;
                                r1 = 0;
                                r2 = 0;
                                sb11 = null;
                                iWaitFor = iWaitFor;
                                th = th5;
                                r4 = dataOutputStream;
                                r16 = z;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            bufferedReader6 = null;
                            r3 = dataOutputStream;
                            r15 = z;
                            if (r3 != 0) {
                                r3.close();
                                if (r0 != 0) {
                                    r0.close();
                                }
                                if (bufferedReader6 != null) {
                                    bufferedReader6.close();
                                }
                            } else {
                                if (r0 != 0) {
                                    r0.close();
                                }
                                if (bufferedReader6 != null) {
                                    bufferedReader6.close();
                                }
                            }
                            if (r15 != 0) {
                                throw th;
                            }
                            r15.destroy();
                            throw th;
                        }
                    } catch (IOException e15) {
                        e = e15;
                        sb = null;
                        sb10 = null;
                        sb9 = null;
                        sb4 = null;
                        obj3 = dataOutputStream;
                        r14 = z;
                        iWaitFor = -1;
                        r19 = sb10;
                        r18 = sb9;
                        r17 = obj3;
                        sb11 = sb4;
                        r16 = r14;
                        ThrowableExtension.printStackTrace(e);
                        if (r17 != 0) {
                            r17.close();
                            if (r19 != 0) {
                                r19.close();
                            }
                            if (r18 != 0) {
                                r18.close();
                            }
                        } else {
                            if (r19 != 0) {
                                r19.close();
                            }
                            if (r18 != 0) {
                                r18.close();
                            }
                        }
                        if (r16 != 0) {
                            r16.destroy();
                        }
                        if (sb == null) {
                            string = null;
                        } else {
                            string = sb.toString();
                        }
                        return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                    } catch (Exception e16) {
                        e = e16;
                        sb = null;
                        sb8 = null;
                        sb7 = null;
                        sb3 = null;
                        obj2 = dataOutputStream;
                        r13 = z;
                        iWaitFor = -1;
                        r7 = sb8;
                        r6 = sb7;
                        r5 = obj2;
                        sb11 = sb3;
                        r16 = r13;
                        ThrowableExtension.printStackTrace(e);
                        if (r5 != 0) {
                            r5.close();
                            if (r7 != 0) {
                                r7.close();
                            }
                            if (r6 != 0) {
                                r6.close();
                            }
                        } else {
                            if (r7 != 0) {
                                r7.close();
                            }
                            if (r6 != 0) {
                                r6.close();
                            }
                        }
                        if (r16 != 0) {
                            r16.destroy();
                        }
                        if (sb == null) {
                            string = null;
                        } else {
                            string = sb.toString();
                        }
                        return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                    } catch (Throwable th7) {
                        th = th7;
                        sb = null;
                        sb6 = null;
                        sb5 = null;
                        sb2 = null;
                        obj = dataOutputStream;
                        r12 = z;
                        iWaitFor = -1;
                        r1 = sb6;
                        r2 = sb5;
                        r4 = obj;
                        sb11 = sb2;
                        r16 = r12;
                        ThrowableExtension.printStackTrace(th);
                        if (r4 != 0) {
                            r4.close();
                            if (r1 != 0) {
                                r1.close();
                            }
                            if (r2 != 0) {
                                r2.close();
                            }
                        } else {
                            if (r1 != 0) {
                                r1.close();
                            }
                            if (r2 != 0) {
                                r2.close();
                            }
                        }
                        if (r16 != 0) {
                            r16.destroy();
                        }
                        if (sb == null) {
                            string = null;
                        } else {
                            string = sb.toString();
                        }
                        return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                    }
                } catch (IOException e17) {
                    e = e17;
                    sb = null;
                    r11 = z;
                    StringBuilder sb17 = sb;
                    StringBuilder sb18 = sb17;
                    StringBuilder sb19 = sb18;
                    sb4 = sb19;
                    sb10 = sb17;
                    sb9 = sb18;
                    obj3 = sb19;
                    r14 = r11;
                    iWaitFor = -1;
                    r19 = sb10;
                    r18 = sb9;
                    r17 = obj3;
                    sb11 = sb4;
                    r16 = r14;
                    ThrowableExtension.printStackTrace(e);
                    if (r17 != 0) {
                        r17.close();
                        if (r19 != 0) {
                            r19.close();
                        }
                        if (r18 != 0) {
                            r18.close();
                        }
                    } else {
                        if (r19 != 0) {
                            r19.close();
                        }
                        if (r18 != 0) {
                            r18.close();
                        }
                    }
                    if (r16 != 0) {
                        r16.destroy();
                    }
                    if (sb == null) {
                        string = null;
                    } else {
                        string = sb.toString();
                    }
                    return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                } catch (Exception e18) {
                    e = e18;
                    sb = null;
                    r10 = z;
                    StringBuilder sb20 = sb;
                    StringBuilder sb21 = sb20;
                    StringBuilder sb22 = sb21;
                    sb3 = sb22;
                    sb8 = sb20;
                    sb7 = sb21;
                    obj2 = sb22;
                    r13 = r10;
                    iWaitFor = -1;
                    r7 = sb8;
                    r6 = sb7;
                    r5 = obj2;
                    sb11 = sb3;
                    r16 = r13;
                    ThrowableExtension.printStackTrace(e);
                    if (r5 != 0) {
                        r5.close();
                        if (r7 != 0) {
                            r7.close();
                        }
                        if (r6 != 0) {
                            r6.close();
                        }
                    } else {
                        if (r7 != 0) {
                            r7.close();
                        }
                        if (r6 != 0) {
                            r6.close();
                        }
                    }
                    if (r16 != 0) {
                        r16.destroy();
                    }
                    if (sb == null) {
                        string = null;
                    } else {
                        string = sb.toString();
                    }
                    return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                } catch (Throwable th8) {
                    th = th8;
                    bufferedReader6 = null;
                    r9 = z;
                    r3 = bufferedReader6;
                    r15 = r9;
                    if (r3 != 0) {
                        r3.close();
                        if (r0 != 0) {
                            r0.close();
                        }
                        if (bufferedReader6 != null) {
                            bufferedReader6.close();
                        }
                    } else {
                        if (r0 != 0) {
                            r0.close();
                        }
                        if (bufferedReader6 != null) {
                            bufferedReader6.close();
                        }
                    }
                    if (r15 != 0) {
                        throw th;
                    }
                    r15.destroy();
                    throw th;
                }
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (IOException e19) {
            e = e19;
            r11 = 0;
            sb = null;
        } catch (Exception e20) {
            e = e20;
            r10 = 0;
            sb = null;
        } catch (Throwable th10) {
            th = th10;
            r8 = 0;
            sb = null;
        }
        if (sb == null) {
            string = null;
        } else {
            string = sb.toString();
        }
        return new CommandResult(iWaitFor, string, sb11 != null ? sb11.toString() : null);
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i) {
            this.result = i;
        }

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }
    }

    private static class CommandTimeoutThread extends Thread {
        private Process process;
        private int timeout;

        public CommandTimeoutThread(Process process, int i) {
            this.process = process;
            this.timeout = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                sleep(this.timeout * 1000);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
            if (ShellUtils.mCommandFinished) {
                return;
            }
            this.process.destroy();
        }
    }
}
