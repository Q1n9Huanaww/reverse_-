package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Scale {
    private static native long nativeScale(long j, float f, float f2);

    private static native long nativeScaleGeneral(long j, float f, float f2, float f3, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.googlecode.leptonica.android.Scale$a */
    public static final class EnumC1471a {

        /* JADX INFO: renamed from: a */
        public static final int f2941a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f2942b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f2943c = 3;

        /* JADX INFO: renamed from: d */
        private static final /* synthetic */ int[] f2944d = {f2941a, f2942b, f2943c};

        private EnumC1471a(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: renamed from: a */
        public static int[] m2726a() {
            return (int[]) f2944d.clone();
        }
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2724a(Pix pix, int i, int i2, int i3) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        float fM2686c = i / pix.m2686c();
        float fM2687d = i2 / pix.m2687d();
        switch (C14701.f2940a[i3 - 1]) {
            case 1:
            default:
                return m2723a(pix, fM2686c, fM2687d);
            case 2:
                fM2686c = Math.min(fM2686c, fM2687d);
                break;
            case 3:
                fM2686c = Math.min(1.0f, Math.min(fM2686c, fM2687d));
                break;
        }
        fM2687d = fM2686c;
        return m2723a(pix, fM2686c, fM2687d);
    }

    /* JADX INFO: renamed from: com.googlecode.leptonica.android.Scale$1 */
    static /* synthetic */ class C14701 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f2940a = new int[EnumC1471a.m2726a().length];

        static {
            try {
                f2940a[EnumC1471a.f2941a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2940a[EnumC1471a.f2942b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2940a[EnumC1471a.f2943c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2722a(Pix pix, float f) {
        return m2723a(pix, f, f);
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2725b(Pix pix, float f) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Scaling factor must be positive");
        }
        return new Pix(nativeScaleGeneral(pix.m2684a(), f, f, 0.0f, 0));
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2723a(Pix pix, float f, float f2) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (f <= 0.0f) {
            throw new IllegalArgumentException("X scaling factor must be positive");
        }
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Y scaling factor must be positive");
        }
        long jNativeScale = nativeScale(pix.m2684a(), f, f2);
        if (jNativeScale == 0) {
            throw new RuntimeException("Failed to natively scale pix");
        }
        return new Pix(jNativeScale);
    }
}
