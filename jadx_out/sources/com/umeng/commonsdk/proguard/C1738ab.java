package com.umeng.commonsdk.proguard;

import android.support.v4.app.FrameMetricsAggregator;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ab */
/* JADX INFO: compiled from: TBaseHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1738ab {

    /* JADX INFO: renamed from: a */
    private static final Comparator f4007a = new a();

    /* JADX INFO: renamed from: a */
    public static int m3767a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b2 < b ? 1 : 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3768a(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        return d2 < d ? 1 : 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3769a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i2 < i ? 1 : 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3770a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j2 < j ? 1 : 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3778a(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s2 < s ? 1 : 0;
    }

    private C1738ab() {
    }

    /* JADX INFO: renamed from: a */
    public static int m3772a(Object obj, Object obj2) {
        if (obj instanceof Comparable) {
            return m3771a((Comparable) obj, (Comparable) obj2);
        }
        if (obj instanceof List) {
            return m3775a((List) obj, (List) obj2);
        }
        if (obj instanceof Set) {
            return m3777a((Set) obj, (Set) obj2);
        }
        if (obj instanceof Map) {
            return m3776a((Map) obj, (Map) obj2);
        }
        if (obj instanceof byte[]) {
            return m3780a((byte[]) obj, (byte[]) obj2);
        }
        throw new IllegalArgumentException("Cannot compare objects of type " + obj.getClass());
    }

    /* JADX INFO: renamed from: a */
    public static int m3779a(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    /* JADX INFO: renamed from: a */
    public static int m3773a(String str, String str2) {
        return str.compareTo(str2);
    }

    /* JADX INFO: renamed from: a */
    public static int m3780a(byte[] bArr, byte[] bArr2) {
        int iM3769a = m3769a(bArr.length, bArr2.length);
        if (iM3769a != 0) {
            return iM3769a;
        }
        for (int i = 0; i < bArr.length; i++) {
            int iM3767a = m3767a(bArr[i], bArr2[i]);
            if (iM3767a != 0) {
                return iM3767a;
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3771a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* JADX INFO: renamed from: a */
    public static int m3775a(List list, List list2) {
        int iM3769a = m3769a(list.size(), list2.size());
        if (iM3769a != 0) {
            return iM3769a;
        }
        for (int i = 0; i < list.size(); i++) {
            int iCompare = f4007a.compare(list.get(i), list2.get(i));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3777a(Set set, Set set2) {
        int iM3769a = m3769a(set.size(), set2.size());
        if (iM3769a != 0) {
            return iM3769a;
        }
        TreeSet treeSet = new TreeSet(f4007a);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(f4007a);
        treeSet2.addAll(set2);
        Iterator it2 = treeSet.iterator();
        Iterator it3 = treeSet2.iterator();
        while (it2.hasNext() && it3.hasNext()) {
            int iCompare = f4007a.compare(it2.next(), it3.next());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: a */
    public static int m3776a(Map map, Map map2) {
        int iM3769a = m3769a(map.size(), map2.size());
        if (iM3769a != 0) {
            return iM3769a;
        }
        TreeMap treeMap = new TreeMap(f4007a);
        treeMap.putAll(map);
        Iterator it2 = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(f4007a);
        treeMap2.putAll(map2);
        Iterator it3 = treeMap2.entrySet().iterator();
        while (it2.hasNext() && it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Map.Entry entry2 = (Map.Entry) it3.next();
            int iCompare = f4007a.compare(entry.getKey(), entry2.getKey());
            if (iCompare != 0) {
                return iCompare;
            }
            int iCompare2 = f4007a.compare(entry.getValue(), entry2.getValue());
            if (iCompare2 != 0) {
                return iCompare2;
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ab$a */
    /* JADX INFO: compiled from: TBaseHelper.java */
    private static class a implements Comparator {
        private a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return C1738ab.m3775a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return C1738ab.m3777a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return C1738ab.m3776a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return C1738ab.m3780a((byte[]) obj, (byte[]) obj2);
            }
            return C1738ab.m3771a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m3782a(ByteBuffer byteBuffer, StringBuilder sb) {
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset();
        int iPosition = byteBuffer.position() + iArrayOffset;
        int iLimit = iArrayOffset + byteBuffer.limit();
        int i = iLimit - iPosition > 128 ? iPosition + 128 : iLimit;
        for (int i2 = iPosition; i2 < i; i2++) {
            if (i2 > iPosition) {
                sb.append(" ");
            }
            sb.append(m3781a(bArrArray[i2]));
        }
        if (iLimit != i) {
            sb.append("...");
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m3781a(byte b) {
        return Integer.toHexString((b | 256) & FrameMetricsAggregator.EVERY_DURATION).toUpperCase().substring(1);
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m3783a(ByteBuffer byteBuffer) {
        if (m3785b(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        m3774a(byteBuffer, bArr, 0);
        return bArr;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3785b(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* JADX INFO: renamed from: a */
    public static int m3774a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int iRemaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, iRemaining);
        return iRemaining;
    }

    /* JADX INFO: renamed from: c */
    public static ByteBuffer m3786c(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return m3785b(byteBuffer) ? byteBuffer : ByteBuffer.wrap(m3783a(byteBuffer));
    }

    /* JADX INFO: renamed from: d */
    public static ByteBuffer m3787d(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[byteBuffer.remaining()]);
        if (byteBuffer.hasArray()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBufferWrap.array(), 0, byteBuffer.remaining());
        } else {
            byteBuffer.slice().get(byteBufferWrap.array());
        }
        return byteBufferWrap;
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m3784a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
