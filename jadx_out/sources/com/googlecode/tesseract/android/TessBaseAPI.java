package com.googlecode.tesseract.android;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.Pixa;
import com.googlecode.leptonica.android.ReadFile;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class TessBaseAPI {

    /* JADX INFO: renamed from: b */
    public static final String f2984b = "tessedit_char_whitelist";

    /* JADX INFO: renamed from: c */
    public static final String f2985c = "tessedit_char_blacklist";

    /* JADX INFO: renamed from: d */
    public static final String f2986d = "save_blob_choices";

    /* JADX INFO: renamed from: e */
    public static final String f2987e = "T";

    /* JADX INFO: renamed from: f */
    public static final String f2988f = "F";

    /* JADX INFO: renamed from: g */
    public static final int f2989g = 0;

    /* JADX INFO: renamed from: h */
    @Deprecated
    public static final int f2990h = 1;

    /* JADX INFO: renamed from: i */
    @Deprecated
    public static final int f2991i = 2;

    /* JADX INFO: renamed from: j */
    public static final int f2992j = 3;

    /* JADX INFO: renamed from: a */
    long f2993a;

    /* JADX INFO: renamed from: k */
    private InterfaceC1475c f2994k;

    /* JADX INFO: renamed from: l */
    private boolean f2995l;

    /* JADX INFO: renamed from: com.googlecode.tesseract.android.TessBaseAPI$a */
    public static final class C1473a {

        /* JADX INFO: renamed from: a */
        public static final int f2996a = 0;

        /* JADX INFO: renamed from: b */
        public static final int f2997b = 1;

        /* JADX INFO: renamed from: c */
        public static final int f2998c = 2;

        /* JADX INFO: renamed from: d */
        public static final int f2999d = 3;

        /* JADX INFO: renamed from: e */
        public static final int f3000e = 4;
    }

    /* JADX INFO: renamed from: com.googlecode.tesseract.android.TessBaseAPI$b */
    public static final class C1474b {

        /* JADX INFO: renamed from: a */
        public static final int f3001a = 0;

        /* JADX INFO: renamed from: b */
        public static final int f3002b = 1;

        /* JADX INFO: renamed from: c */
        public static final int f3003c = 2;

        /* JADX INFO: renamed from: d */
        public static final int f3004d = 3;

        /* JADX INFO: renamed from: e */
        public static final int f3005e = 4;

        /* JADX INFO: renamed from: f */
        public static final int f3006f = 5;

        /* JADX INFO: renamed from: g */
        public static final int f3007g = 6;

        /* JADX INFO: renamed from: h */
        public static final int f3008h = 7;

        /* JADX INFO: renamed from: i */
        public static final int f3009i = 8;

        /* JADX INFO: renamed from: j */
        public static final int f3010j = 9;

        /* JADX INFO: renamed from: k */
        public static final int f3011k = 10;

        /* JADX INFO: renamed from: l */
        public static final int f3012l = 11;

        /* JADX INFO: renamed from: m */
        public static final int f3013m = 12;

        /* JADX INFO: renamed from: n */
        public static final int f3014n = 13;
    }

    /* JADX INFO: renamed from: com.googlecode.tesseract.android.TessBaseAPI$c */
    public interface InterfaceC1475c {
        /* JADX INFO: renamed from: a */
        void m2780a();
    }

    private native boolean nativeAddPageToDocument(long j, long j2, String str, long j3);

    private native boolean nativeBeginDocument(long j, String str);

    private static native void nativeClassInit();

    private native void nativeClear(long j);

    private native long nativeConstruct();

    private native void nativeEnd(long j);

    private native boolean nativeEndDocument(long j);

    private native String nativeGetBoxText(long j, int i);

    private native long nativeGetConnectedComponents(long j);

    private native String nativeGetHOCRText(long j, int i);

    private native String nativeGetInitLanguagesAsString(long j);

    private native int nativeGetPageSegMode(long j);

    private native long nativeGetRegions(long j);

    private native long nativeGetResultIterator(long j);

    private native long nativeGetStrips(long j);

    private native long nativeGetTextlines(long j);

    private native long nativeGetThresholdedImage(long j);

    private native String nativeGetUTF8Text(long j);

    private native String nativeGetVersion(long j);

    private native long nativeGetWords(long j);

    private native boolean nativeInit(long j, String str, String str2);

    private native boolean nativeInitOem(long j, String str, String str2, int i);

    private native int nativeMeanConfidence(long j);

    private native void nativeReadConfigFile(long j, String str);

    private native void nativeSetDebug(long j, boolean z);

    private native void nativeSetImageBytes(long j, byte[] bArr, int i, int i2, int i3, int i4);

    private native void nativeSetImagePix(long j, long j2);

    private native void nativeSetInputName(long j, String str);

    private native void nativeSetOutputName(long j, String str);

    private native void nativeSetPageSegMode(long j, int i);

    private native void nativeSetRectangle(long j, int i, int i2, int i3, int i4);

    private native boolean nativeSetVariable(long j, String str, String str2);

    private native void nativeStop(long j);

    private native int[] nativeWordConfidences(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
        nativeClassInit();
    }

    /* JADX INFO: renamed from: com.googlecode.tesseract.android.TessBaseAPI$d */
    public class C1476d {

        /* JADX INFO: renamed from: b */
        private final int f3016b;

        /* JADX INFO: renamed from: c */
        private final Rect f3017c;

        /* JADX INFO: renamed from: d */
        private final Rect f3018d;

        public C1476d(int i, Rect rect, Rect rect2) {
            this.f3016b = i;
            this.f3017c = rect;
            this.f3018d = rect2;
        }

        /* JADX INFO: renamed from: a */
        private int m2781a() {
            return this.f3016b;
        }

        /* JADX INFO: renamed from: b */
        private Rect m2782b() {
            return this.f3017c;
        }

        /* JADX INFO: renamed from: c */
        private Rect m2783c() {
            return this.f3018d;
        }
    }

    public TessBaseAPI() {
        this.f2993a = nativeConstruct();
        if (this.f2993a == 0) {
            throw new RuntimeException("Can't create TessBaseApi object");
        }
        this.f2995l = false;
    }

    private TessBaseAPI(InterfaceC1475c interfaceC1475c) {
        this();
        this.f2994k = interfaceC1475c;
    }

    /* JADX INFO: renamed from: b */
    private boolean m2758b(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Data path must not be null!");
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        String str3 = str;
        if (!new File(str3).exists()) {
            throw new IllegalArgumentException("Data path does not exist!");
        }
        File file = new File(str3 + InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
        }
        for (String str4 : str2.split("\\+")) {
            if (!str4.startsWith("~")) {
                File file2 = new File(file + File.separator + str4 + ".traineddata");
                if (!file2.exists()) {
                    throw new IllegalArgumentException("Data file not found at " + file2);
                }
            }
        }
        boolean zNativeInitOem = nativeInitOem(this.f2993a, str3, str2, 3);
        if (zNativeInitOem) {
            this.f2995l = false;
        }
        return zNativeInitOem;
    }

    /* JADX INFO: renamed from: b */
    private String m2754b() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeGetInitLanguagesAsString(this.f2993a);
    }

    /* JADX INFO: renamed from: c */
    private void m2760c() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeClear(this.f2993a);
    }

    /* JADX INFO: renamed from: d */
    private void m2763d() {
        if (this.f2995l) {
            return;
        }
        nativeEnd(this.f2993a);
        this.f2995l = true;
    }

    /* JADX INFO: renamed from: c */
    private boolean m2762c(String str, String str2) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeSetVariable(this.f2993a, str, str2);
    }

    /* JADX INFO: renamed from: e */
    private int m2764e() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeGetPageSegMode(this.f2993a);
    }

    /* JADX INFO: renamed from: a */
    private void m2743a(int i) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetPageSegMode(this.f2993a, i);
    }

    /* JADX INFO: renamed from: a */
    private void m2749a(boolean z) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetDebug(this.f2993a, z);
    }

    /* JADX INFO: renamed from: a */
    private void m2745a(Rect rect) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        int i = rect.left;
        int i2 = rect.top;
        int iWidth = rect.width();
        int iHeight = rect.height();
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetRectangle(this.f2993a, i, i2, iWidth, iHeight);
    }

    /* JADX INFO: renamed from: a */
    private void m2744a(int i, int i2, int i3, int i4) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetRectangle(this.f2993a, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    private void m2747a(File file) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        Pix pixM2714a = ReadFile.m2714a(file);
        if (pixM2714a == null) {
            throw new RuntimeException("Failed to read image file");
        }
        nativeSetImagePix(this.f2993a, pixM2714a.m2684a());
        pixM2714a.m2685b();
    }

    /* JADX INFO: renamed from: a */
    public final void m2778a(Bitmap bitmap) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        Pix pixM2713a = ReadFile.m2713a(bitmap);
        if (pixM2713a == null) {
            throw new RuntimeException("Failed to read bitmap");
        }
        nativeSetImagePix(this.f2993a, pixM2713a.m2684a());
        pixM2713a.m2685b();
    }

    /* JADX INFO: renamed from: a */
    private void m2746a(Pix pix) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetImagePix(this.f2993a, pix.m2684a());
    }

    /* JADX INFO: renamed from: a */
    private void m2750a(byte[] bArr, int i, int i2, int i3, int i4) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetImageBytes(this.f2993a, bArr, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    public final String m2777a() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        String strNativeGetUTF8Text = nativeGetUTF8Text(this.f2993a);
        if (strNativeGetUTF8Text != null) {
            return strNativeGetUTF8Text.trim();
        }
        return null;
    }

    /* JADX INFO: renamed from: f */
    private int m2765f() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeMeanConfidence(this.f2993a);
    }

    /* JADX INFO: renamed from: g */
    private int[] m2766g() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        int[] iArrNativeWordConfidences = nativeWordConfidences(this.f2993a);
        return iArrNativeWordConfidences == null ? new int[0] : iArrNativeWordConfidences;
    }

    /* JADX INFO: renamed from: h */
    private Pix m2767h() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pix(nativeGetThresholdedImage(this.f2993a));
    }

    /* JADX INFO: renamed from: i */
    private Pixa m2768i() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pixa(nativeGetRegions(this.f2993a), 0, 0);
    }

    /* JADX INFO: renamed from: j */
    private Pixa m2769j() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pixa(nativeGetTextlines(this.f2993a), 0, 0);
    }

    /* JADX INFO: renamed from: k */
    private Pixa m2770k() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pixa(nativeGetStrips(this.f2993a), 0, 0);
    }

    /* JADX INFO: renamed from: l */
    private Pixa m2771l() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pixa(nativeGetWords(this.f2993a), 0, 0);
    }

    /* JADX INFO: renamed from: m */
    private Pixa m2772m() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return new Pixa(nativeGetConnectedComponents(this.f2993a), 0, 0);
    }

    /* JADX INFO: renamed from: n */
    private ResultIterator m2773n() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        long jNativeGetResultIterator = nativeGetResultIterator(this.f2993a);
        if (jNativeGetResultIterator == 0) {
            return null;
        }
        return new ResultIterator(jNativeGetResultIterator);
    }

    /* JADX INFO: renamed from: b */
    private String m2755b(int i) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeGetHOCRText(this.f2993a, i);
    }

    /* JADX INFO: renamed from: a */
    private void m2748a(String str) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetInputName(this.f2993a, str);
    }

    /* JADX INFO: renamed from: b */
    private void m2756b(String str) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeSetOutputName(this.f2993a, str);
    }

    /* JADX INFO: renamed from: c */
    private void m2761c(String str) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeReadConfigFile(this.f2993a, str);
    }

    /* JADX INFO: renamed from: c */
    private String m2759c(int i) {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        return nativeGetBoxText(this.f2993a, i);
    }

    /* JADX INFO: renamed from: o */
    private String m2774o() {
        return nativeGetVersion(this.f2993a);
    }

    /* JADX INFO: renamed from: p */
    private void m2775p() {
        if (this.f2995l) {
            throw new IllegalStateException();
        }
        nativeStop(this.f2993a);
    }

    protected void onProgressValues(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.f2994k != null) {
            new C1476d(i, new Rect(i2, i8 - i4, i3, i8 - i5), new Rect(i6, i9, i7, i8));
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m2753a(TessPdfRenderer tessPdfRenderer, String str) {
        return nativeBeginDocument(tessPdfRenderer.m2785a(), str);
    }

    /* JADX INFO: renamed from: a */
    private boolean m2752a(TessPdfRenderer tessPdfRenderer) {
        return nativeBeginDocument(tessPdfRenderer.m2785a(), "");
    }

    /* JADX INFO: renamed from: b */
    private boolean m2757b(TessPdfRenderer tessPdfRenderer) {
        return nativeEndDocument(tessPdfRenderer.m2785a());
    }

    /* JADX INFO: renamed from: a */
    private boolean m2751a(Pix pix, String str, TessPdfRenderer tessPdfRenderer) {
        return nativeAddPageToDocument(this.f2993a, pix.m2684a(), str, tessPdfRenderer.m2785a());
    }

    /* JADX INFO: renamed from: q */
    private long m2776q() {
        return this.f2993a;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m2779a(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Data path must not be null!");
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        String str3 = str;
        if (!new File(str3).exists()) {
            throw new IllegalArgumentException("Data path does not exist!");
        }
        File file = new File(str3 + InterfaceRelatedConstants.RECOGNITION_LIBRARY_ASSETS);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
        }
        for (String str4 : str2.split("\\+")) {
            if (!str4.startsWith("~")) {
                File file2 = new File(file + File.separator + str4 + ".traineddata");
                if (!file2.exists()) {
                    throw new IllegalArgumentException("Data file not found at " + file2);
                }
            }
        }
        boolean zNativeInitOem = nativeInitOem(this.f2993a, str3, str2, 3);
        if (zNativeInitOem) {
            this.f2995l = false;
        }
        return zNativeInitOem;
    }
}
