package com.googlecode.tesseract.android;

import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ResultIterator extends PageIterator {

    /* JADX INFO: renamed from: a */
    private final long f2983a;

    private static native float nativeConfidence(long j, int i);

    private static native void nativeDelete(long j);

    private static native String[] nativeGetSymbolChoices(long j);

    private static native String nativeGetUTF8Text(long j, int i);

    private static native boolean nativeIsAtBeginningOf(long j, int i);

    private static native boolean nativeIsAtFinalElement(long j, int i, int i2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    ResultIterator(long j) {
        super(j);
        this.f2983a = j;
    }

    /* JADX INFO: renamed from: a */
    private String m2737a(int i) {
        return nativeGetUTF8Text(this.f2983a, i);
    }

    /* JADX INFO: renamed from: b */
    private float m2740b(int i) {
        return nativeConfidence(this.f2983a, i);
    }

    /* JADX INFO: renamed from: c */
    private boolean m2742c(int i) {
        return nativeIsAtBeginningOf(this.f2983a, i);
    }

    /* JADX INFO: renamed from: a */
    private boolean m2739a(int i, int i2) {
        return nativeIsAtFinalElement(this.f2983a, i, i2);
    }

    /* JADX INFO: renamed from: a */
    private List<Pair<String, Double>> m2738a() {
        String strSubstring;
        String[] strArrNativeGetSymbolChoices = nativeGetSymbolChoices(this.f2983a);
        ArrayList arrayList = new ArrayList();
        for (String str : strArrNativeGetSymbolChoices) {
            int iLastIndexOf = str.lastIndexOf(124);
            Double dValueOf = Double.valueOf(0.0d);
            if (iLastIndexOf > 0) {
                strSubstring = str.substring(0, iLastIndexOf);
                try {
                    dValueOf = Double.valueOf(Double.parseDouble(str.substring(iLastIndexOf + 1)));
                } catch (NumberFormatException unused) {
                    Log.e("ResultIterator", "Invalid confidence level for " + str);
                }
            } else {
                strSubstring = str;
            }
            arrayList.add(new Pair(strSubstring, dValueOf));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: b */
    private void m2741b() {
        nativeDelete(this.f2983a);
    }
}
