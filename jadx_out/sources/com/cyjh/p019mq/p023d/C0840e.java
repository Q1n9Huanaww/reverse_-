package com.cyjh.p019mq.p023d;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mq.d.e */
/* JADX INFO: compiled from: ShellUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0840e {

    /* JADX INFO: renamed from: a */
    public static final String f1609a = "su";

    /* JADX INFO: renamed from: b */
    public static final String f1610b = "sh";

    /* JADX INFO: renamed from: c */
    public static final String f1611c = "exit\n";

    /* JADX INFO: renamed from: d */
    public static final String f1612d = "\n";

    /* JADX INFO: renamed from: e */
    private static boolean f1613e;

    private C0840e() {
        throw new AssertionError();
    }

    /* JADX INFO: renamed from: a */
    public static a m1292a(String str) {
        return m1296a(new String[]{str}, false, true, 100);
    }

    /* JADX INFO: renamed from: a */
    private static a m1293a(List<String> list, boolean z, int i) {
        return m1296a(list == null ? null : (String[]) list.toArray(new String[0]), z, true, i);
    }

    /* JADX INFO: renamed from: a */
    private static a m1295a(String[] strArr, boolean z, int i) {
        return m1296a(strArr, z, true, i);
    }

    /* JADX INFO: renamed from: b */
    private static a m1298b(String str) {
        return m1296a(new String[]{str}, true, false, 0);
    }

    /* JADX INFO: renamed from: a */
    private static a m1294a(List<String> list, boolean z, boolean z2, int i) {
        return m1296a(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, i);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x017f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x0181 A[Catch: IOException -> 0x017d, TryCatch #17 {IOException -> 0x017d, blocks: (B:109:0x0179, B:113:0x0181, B:115:0x0186), top: B:185:0x0179 }] */
    /* JADX WARN: Code duplicated, block: B:115:0x0186 A[Catch: IOException -> 0x017d, TRY_LEAVE, TryCatch #17 {IOException -> 0x017d, blocks: (B:109:0x0179, B:113:0x0181, B:115:0x0186), top: B:185:0x0179 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x018f A[PHI: r4 r8 r9 r10
  0x018f: PHI (r4v8 java.lang.StringBuilder) = (r4v6 java.lang.StringBuilder), (r4v7 java.lang.StringBuilder), (r4v9 java.lang.StringBuilder) binds: [B:118:0x018d, B:135:0x01b4, B:152:0x01d8] A[DONT_GENERATE, DONT_INLINE]
  0x018f: PHI (r8v16 ??) = (r8v14 ??), (r8v15 ??), (r8v17 ??) binds: [B:118:0x018d, B:135:0x01b4, B:152:0x01d8] A[DONT_GENERATE, DONT_INLINE]
  0x018f: PHI (r9v7 int) = (r9v5 int), (r9v6 int), (r9v8 int) binds: [B:118:0x018d, B:135:0x01b4, B:152:0x01d8] A[DONT_GENERATE, DONT_INLINE]
  0x018f: PHI (r10v12 java.lang.StringBuilder) = (r10v10 java.lang.StringBuilder), (r10v11 java.lang.StringBuilder), (r10v13 java.lang.StringBuilder) binds: [B:118:0x018d, B:135:0x01b4, B:152:0x01d8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:129:0x01a6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:130:0x01a8 A[Catch: IOException -> 0x01a4, TryCatch #29 {IOException -> 0x01a4, blocks: (B:126:0x01a0, B:130:0x01a8, B:132:0x01ad), top: B:191:0x01a0 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ad A[Catch: IOException -> 0x01a4, TRY_LEAVE, TryCatch #29 {IOException -> 0x01a4, blocks: (B:126:0x01a0, B:130:0x01a8, B:132:0x01ad), top: B:191:0x01a0 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x01ca A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:147:0x01cc A[Catch: IOException -> 0x01c8, TryCatch #15 {IOException -> 0x01c8, blocks: (B:143:0x01c4, B:147:0x01cc, B:149:0x01d1), top: B:183:0x01c4 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x01d1 A[Catch: IOException -> 0x01c8, TRY_LEAVE, TryCatch #15 {IOException -> 0x01c8, blocks: (B:143:0x01c4, B:147:0x01cc, B:149:0x01d1), top: B:183:0x01c4 }] */
    /* JADX WARN: Code duplicated, block: B:156:0x01df  */
    /* JADX WARN: Code duplicated, block: B:157:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:160:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:169:0x01fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:170:0x01fc A[Catch: IOException -> 0x01f8, TryCatch #4 {IOException -> 0x01f8, blocks: (B:166:0x01f4, B:170:0x01fc, B:172:0x0201), top: B:180:0x01f4 }] */
    /* JADX WARN: Code duplicated, block: B:172:0x0201 A[Catch: IOException -> 0x01f8, TRY_LEAVE, TryCatch #4 {IOException -> 0x01f8, blocks: (B:166:0x01f4, B:170:0x01fc, B:172:0x0201), top: B:180:0x01f4 }] */
    /* JADX WARN: Code duplicated, block: B:176:0x020a  */
    /* JADX WARN: Code duplicated, block: B:180:0x01f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x01c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x0179 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x01a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:210:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x010c A[Catch: IOException -> 0x0115, TryCatch #20 {IOException -> 0x0115, blocks: (B:67:0x0107, B:69:0x010c, B:71:0x0111), top: B:189:0x0107 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0111 A[Catch: IOException -> 0x0115, TRY_LEAVE, TryCatch #20 {IOException -> 0x0115, blocks: (B:67:0x0107, B:69:0x010c, B:71:0x0111), top: B:189:0x0107 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x011b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v6 */
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
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
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
    /* JADX INFO: renamed from: a */
    private static a m1296a(String[] strArr, boolean z, boolean z2, int i) throws Throwable {
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
        ?? r0;
        ?? r5;
        ?? r6;
        ?? r1;
        ?? r7;
        ?? r17;
        ?? r18;
        String string;
        StringBuilder sb12;
        StringBuilder sb13;
        ?? r19;
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
        ?? bufferedReader5 = -1;
        ?? r23 = 0;
        r23 = 0;
        if (strArr == null || strArr.length == 0) {
            return new a(-1, null, null);
        }
        BufferedReader bufferedReader6 = null;
        f1613e = false;
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
                                new b(z, i).start();
                            }
                            iWaitFor = z.waitFor();
                            try {
                                Log.i("SYS", "command finished.");
                                f1613e = true;
                                try {
                                    if (z2) {
                                        sb12 = new StringBuilder();
                                        try {
                                            sb13 = new StringBuilder();
                                            try {
                                                bufferedReader5 = new BufferedReader(new InputStreamReader(z.getInputStream()));
                                                try {
                                                    BufferedReader bufferedReader7 = new BufferedReader(new InputStreamReader(z.getErrorStream()));
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
                                                            bufferedReader4 = bufferedReader7;
                                                            sb = sb12;
                                                            r18 = r22;
                                                            r17 = bufferedReader4;
                                                            r7 = dataOutputStream;
                                                            sb11 = sb16;
                                                            r16 = z;
                                                            ThrowableExtension.printStackTrace(e);
                                                            if (r7 != 0) {
                                                                try {
                                                                    r7.close();
                                                                    if (r18 != 0) {
                                                                        r18.close();
                                                                    }
                                                                    if (r17 != 0) {
                                                                        r17.close();
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
                                                                    return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                                }
                                                            } else {
                                                                if (r18 != 0) {
                                                                    r18.close();
                                                                }
                                                                if (r17 != 0) {
                                                                    r17.close();
                                                                }
                                                            }
                                                            if (r16 != 0) {
                                                                r16.destroy();
                                                            }
                                                        } catch (Exception e3) {
                                                            e = e3;
                                                            sb15 = sb13;
                                                            r21 = bufferedReader5;
                                                            bufferedReader3 = bufferedReader7;
                                                            sb = sb12;
                                                            r1 = r21;
                                                            r6 = bufferedReader3;
                                                            r5 = dataOutputStream;
                                                            sb11 = sb15;
                                                            r16 = z;
                                                            ThrowableExtension.printStackTrace(e);
                                                            if (r5 != 0) {
                                                                try {
                                                                    r5.close();
                                                                    if (r1 != 0) {
                                                                        r1.close();
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
                                                                    return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                                }
                                                            } else {
                                                                if (r1 != 0) {
                                                                    r1.close();
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
                                                            bufferedReader2 = bufferedReader7;
                                                            sb = sb12;
                                                            r0 = r20;
                                                            r2 = bufferedReader2;
                                                            r4 = dataOutputStream;
                                                            sb11 = sb14;
                                                            r16 = z;
                                                            ThrowableExtension.printStackTrace(th);
                                                            if (r4 != 0) {
                                                                try {
                                                                    r4.close();
                                                                    if (r0 != 0) {
                                                                        r0.close();
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
                                                                    return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                                                }
                                                            } else {
                                                                if (r0 != 0) {
                                                                    r0.close();
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
                                                        String line2 = bufferedReader7.readLine();
                                                        r19 = bufferedReader5;
                                                        bufferedReader = bufferedReader7;
                                                        if (line2 == null) {
                                                            break;
                                                        }
                                                        sb13.append(line2);
                                                    }
                                                    dataOutputStream.close();
                                                    if (r19 != 0) {
                                                        r19.close();
                                                    }
                                                    if (bufferedReader != null) {
                                                        bufferedReader.close();
                                                    }
                                                    if (z != 0) {
                                                        z.destroy();
                                                    }
                                                    sb11 = sb13;
                                                    sb = sb12;
                                                    iWaitFor = iWaitFor;
                                                } catch (IOException e6) {
                                                    sb11 = sb13;
                                                    sb = sb12;
                                                    iWaitFor = iWaitFor;
                                                    e = e6;
                                                    r17 = 0;
                                                    r18 = bufferedReader5;
                                                    r7 = dataOutputStream;
                                                    r16 = z;
                                                    ThrowableExtension.printStackTrace(e);
                                                    if (r7 != 0) {
                                                        r7.close();
                                                        if (r18 != 0) {
                                                            r18.close();
                                                        }
                                                        if (r17 != 0) {
                                                            r17.close();
                                                        }
                                                    } else {
                                                        if (r18 != 0) {
                                                            r18.close();
                                                        }
                                                        if (r17 != 0) {
                                                            r17.close();
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
                                                    r1 = bufferedReader5;
                                                    r5 = dataOutputStream;
                                                    r16 = z;
                                                    ThrowableExtension.printStackTrace(e);
                                                    if (r5 != 0) {
                                                        r5.close();
                                                        if (r1 != 0) {
                                                            r1.close();
                                                        }
                                                        if (r6 != 0) {
                                                            r6.close();
                                                        }
                                                    } else {
                                                        if (r1 != 0) {
                                                            r1.close();
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
                                                    r0 = bufferedReader5;
                                                    r4 = dataOutputStream;
                                                    r16 = z;
                                                    ThrowableExtension.printStackTrace(th);
                                                    if (r4 != 0) {
                                                        r4.close();
                                                        if (r0 != 0) {
                                                            r0.close();
                                                        }
                                                        if (r2 != 0) {
                                                            r2.close();
                                                        }
                                                    } else {
                                                        if (r0 != 0) {
                                                            r0.close();
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
                                                r17 = 0;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                e = e8;
                                                r18 = 0;
                                                r7 = dataOutputStream;
                                                r16 = z;
                                            } catch (Exception e9) {
                                                sb11 = sb13;
                                                r6 = 0;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                e = e9;
                                                r1 = 0;
                                                r5 = dataOutputStream;
                                                r16 = z;
                                            } catch (Throwable th3) {
                                                sb11 = sb13;
                                                r2 = 0;
                                                sb = sb12;
                                                iWaitFor = iWaitFor;
                                                th = th3;
                                                r0 = 0;
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
                                        if (sb == null) {
                                            string = null;
                                        } else {
                                            string = sb.toString();
                                        }
                                        return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                                    }
                                    sb12 = null;
                                    sb13 = null;
                                    r19 = 0;
                                    bufferedReader = null;
                                    dataOutputStream.close();
                                    if (r19 != 0) {
                                        r19.close();
                                    }
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                } catch (IOException e12) {
                                    ThrowableExtension.printStackTrace(e12);
                                }
                                if (z != 0) {
                                    z.destroy();
                                }
                                sb11 = sb13;
                                sb = sb12;
                                iWaitFor = iWaitFor;
                            } catch (IOException e13) {
                                sb = null;
                                r18 = 0;
                                r17 = 0;
                                sb11 = null;
                                iWaitFor = iWaitFor;
                                e = e13;
                                r7 = dataOutputStream;
                                r16 = z;
                            } catch (Exception e14) {
                                sb = null;
                                r1 = 0;
                                r6 = 0;
                                sb11 = null;
                                iWaitFor = iWaitFor;
                                e = e14;
                                r5 = dataOutputStream;
                                r16 = z;
                            } catch (Throwable th5) {
                                sb = null;
                                r0 = 0;
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
                                if (r23 != 0) {
                                    r23.close();
                                }
                                if (bufferedReader6 != null) {
                                    bufferedReader6.close();
                                }
                            } else {
                                if (r23 != 0) {
                                    r23.close();
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
                        r18 = sb10;
                        r17 = sb9;
                        r7 = obj3;
                        sb11 = sb4;
                        r16 = r14;
                        ThrowableExtension.printStackTrace(e);
                        if (r7 != 0) {
                            r7.close();
                            if (r18 != 0) {
                                r18.close();
                            }
                            if (r17 != 0) {
                                r17.close();
                            }
                        } else {
                            if (r18 != 0) {
                                r18.close();
                            }
                            if (r17 != 0) {
                                r17.close();
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
                        return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                    } catch (Exception e16) {
                        e = e16;
                        sb = null;
                        sb8 = null;
                        sb7 = null;
                        sb3 = null;
                        obj2 = dataOutputStream;
                        r13 = z;
                        iWaitFor = -1;
                        r1 = sb8;
                        r6 = sb7;
                        r5 = obj2;
                        sb11 = sb3;
                        r16 = r13;
                        ThrowableExtension.printStackTrace(e);
                        if (r5 != 0) {
                            r5.close();
                            if (r1 != 0) {
                                r1.close();
                            }
                            if (r6 != 0) {
                                r6.close();
                            }
                        } else {
                            if (r1 != 0) {
                                r1.close();
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
                        return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                    } catch (Throwable th7) {
                        th = th7;
                        sb = null;
                        sb6 = null;
                        sb5 = null;
                        sb2 = null;
                        obj = dataOutputStream;
                        r12 = z;
                        iWaitFor = -1;
                        r0 = sb6;
                        r2 = sb5;
                        r4 = obj;
                        sb11 = sb2;
                        r16 = r12;
                        ThrowableExtension.printStackTrace(th);
                        if (r4 != 0) {
                            r4.close();
                            if (r0 != 0) {
                                r0.close();
                            }
                            if (r2 != 0) {
                                r2.close();
                            }
                        } else {
                            if (r0 != 0) {
                                r0.close();
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
                        return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
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
                    r18 = sb10;
                    r17 = sb9;
                    r7 = obj3;
                    sb11 = sb4;
                    r16 = r14;
                    ThrowableExtension.printStackTrace(e);
                    if (r7 != 0) {
                        r7.close();
                        if (r18 != 0) {
                            r18.close();
                        }
                        if (r17 != 0) {
                            r17.close();
                        }
                    } else {
                        if (r18 != 0) {
                            r18.close();
                        }
                        if (r17 != 0) {
                            r17.close();
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
                    return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
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
                    r1 = sb8;
                    r6 = sb7;
                    r5 = obj2;
                    sb11 = sb3;
                    r16 = r13;
                    ThrowableExtension.printStackTrace(e);
                    if (r5 != 0) {
                        r5.close();
                        if (r1 != 0) {
                            r1.close();
                        }
                        if (r6 != 0) {
                            r6.close();
                        }
                    } else {
                        if (r1 != 0) {
                            r1.close();
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
                    return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
                } catch (Throwable th8) {
                    th = th8;
                    bufferedReader6 = null;
                    r9 = z;
                    r3 = bufferedReader6;
                    r15 = r9;
                    if (r3 != 0) {
                        r3.close();
                        if (r23 != 0) {
                            r23.close();
                        }
                        if (bufferedReader6 != null) {
                            bufferedReader6.close();
                        }
                    } else {
                        if (r23 != 0) {
                            r23.close();
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
        return new a(iWaitFor, string, sb11 != null ? sb11.toString() : null);
    }

    /* JADX INFO: renamed from: com.cyjh.mq.d.e$a */
    /* JADX INFO: compiled from: ShellUtils.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        public int f1614a;

        /* JADX INFO: renamed from: b */
        public String f1615b;

        /* JADX INFO: renamed from: c */
        public String f1616c;

        private a(int i) {
            this.f1614a = i;
        }

        public a(int i, String str, String str2) {
            this.f1614a = i;
            this.f1615b = str;
            this.f1616c = str2;
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mq.d.e$b */
    /* JADX INFO: compiled from: ShellUtils.java */
    private static class b extends Thread {

        /* JADX INFO: renamed from: a */
        private Process f1617a;

        /* JADX INFO: renamed from: b */
        private int f1618b;

        public b(Process process, int i) {
            this.f1617a = process;
            this.f1618b = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                sleep(this.f1618b * 1000);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
            if (C0840e.f1613e) {
                return;
            }
            this.f1617a.destroy();
        }
    }

    /* JADX INFO: renamed from: b */
    private static boolean m1299b() {
        return m1296a(new String[]{"echo root"}, true, false, 0).f1614a == 0;
    }
}
