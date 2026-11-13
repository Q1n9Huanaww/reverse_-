package com.cyjh.mobileanjian.ipc.uip;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import com.cyjh.mqsdk.C0852R;
import java.util.ArrayList;
import java.util.List;
import mobi.oneway.export.p083d.InterfaceC1925e;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.b */
/* JADX INFO: compiled from: FlowLayout.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(14)
public final class C0799b extends ViewGroup {

    /* JADX INFO: renamed from: a */
    private int f1395a;

    /* JADX INFO: renamed from: b */
    private final List<List<View>> f1396b;

    /* JADX INFO: renamed from: c */
    private final List<Integer> f1397c;

    /* JADX INFO: renamed from: d */
    private final List<Integer> f1398d;

    public C0799b(Context context) {
        this(context, (byte) 0);
    }

    private C0799b(Context context, byte b) {
        this(context, (char) 0);
    }

    private C0799b(Context context, char c) {
        super(context, null, 0);
        this.f1395a = (m1050b() ? GravityCompat.START : 3) | 48;
        this.f1396b = new ArrayList();
        this.f1397c = new ArrayList();
        this.f1398d = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, C0852R.styleable.FlowLayout, 0, 0);
        try {
            int i = typedArrayObtainStyledAttributes.getInt(C0852R.styleable.FlowLayout_android_gravity, -1);
            if (i > 0) {
                setGravity(i);
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0092  */
    /* JADX WARN: Code duplicated, block: B:25:0x0097 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0099  */
    /* JADX WARN: Code duplicated, block: B:27:0x009c  */
    /* JADX WARN: Code duplicated, block: B:30:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:31:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:33:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:34:0x00e6  */
    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int measuredWidth;
        int i9;
        int iMax;
        super.onMeasure(i, i2);
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        int i10 = paddingTop;
        int i11 = 0;
        int i12 = 0;
        int iMax2 = 0;
        int i13 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            boolean z = i12 == childCount + (-1);
            if (childAt.getVisibility() == 8) {
                if (z) {
                    i10 += i11;
                    iMax2 = Math.max(iMax2, i13);
                }
                i3 = size2;
            } else {
                i3 = size2;
                int i14 = i11;
                measureChildWithMargins(childAt, i, i13, i2, i10);
                a aVar = (a) childAt.getLayoutParams();
                if (aVar.width == -1) {
                    i6 = size - (aVar.leftMargin + aVar.rightMargin);
                } else {
                    if (aVar.width >= 0) {
                        i6 = aVar.width;
                    } else {
                        i4 = size;
                        i5 = Integer.MIN_VALUE;
                    }
                    if (aVar.height >= 0) {
                        i7 = aVar.height;
                        i8 = InterfaceC1925e.f4896e;
                    } else if (mode2 == 0) {
                        i7 = 0;
                        i8 = 0;
                    } else {
                        i7 = i3;
                        i8 = Integer.MIN_VALUE;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i4, i5), View.MeasureSpec.makeMeasureSpec(i7, i8));
                    measuredWidth = childAt.getMeasuredWidth() + aVar.leftMargin + aVar.rightMargin;
                    i9 = i13 + measuredWidth;
                    if (i9 > size) {
                        iMax2 = Math.max(iMax2, i13);
                        i10 += i14;
                        i13 = measuredWidth;
                        iMax = childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin;
                    } else {
                        iMax = Math.max(i14, childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin);
                        i13 = i9;
                    }
                    if (z) {
                        i10 += iMax;
                        i11 = iMax;
                        iMax2 = Math.max(iMax2, i13);
                    } else {
                        i11 = iMax;
                    }
                }
                i4 = i6;
                i5 = InterfaceC1925e.f4896e;
                if (aVar.height >= 0) {
                    i7 = aVar.height;
                    i8 = InterfaceC1925e.f4896e;
                } else if (mode2 == 0) {
                    i7 = 0;
                    i8 = 0;
                } else {
                    i7 = i3;
                    i8 = Integer.MIN_VALUE;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i4, i5), View.MeasureSpec.makeMeasureSpec(i7, i8));
                measuredWidth = childAt.getMeasuredWidth() + aVar.leftMargin + aVar.rightMargin;
                i9 = i13 + measuredWidth;
                if (i9 > size) {
                    iMax2 = Math.max(iMax2, i13);
                    i10 += i14;
                    i13 = measuredWidth;
                    iMax = childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin;
                } else {
                    iMax = Math.max(i14, childAt.getMeasuredHeight() + aVar.topMargin + aVar.bottomMargin);
                    i13 = i9;
                }
                if (z) {
                    i10 += iMax;
                    i11 = iMax;
                    iMax2 = Math.max(iMax2, i13);
                } else {
                    i11 = iMax;
                }
            }
            i12++;
            size2 = i3;
        }
        int i15 = size2;
        int paddingLeft = getPaddingLeft() + getPaddingRight() + iMax2;
        if (mode == 1073741824) {
            paddingLeft = size;
        }
        setMeasuredDimension(paddingLeft, mode2 == 1073741824 ? i15 : i10);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0174  */
    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        C0799b c0799b = this;
        c0799b.f1396b.clear();
        c0799b.f1397c.clear();
        c0799b.f1398d.clear();
        int width = getWidth();
        int height = getHeight();
        int paddingTop = getPaddingTop();
        ArrayList arrayList = new ArrayList();
        int i10 = c0799b.f1395a & 7;
        if (i10 != 1) {
            f = i10 != 5 ? 0.0f : 1.0f;
        } else {
            f = 0.5f;
        }
        int i11 = paddingTop;
        ArrayList arrayList2 = arrayList;
        int i12 = 0;
        int iMax = 0;
        int i13 = 0;
        while (true) {
            i5 = 8;
            if (i12 >= getChildCount()) {
                break;
            }
            View childAt = c0799b.getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + aVar.leftMargin + aVar.rightMargin;
                int measuredHeight = childAt.getMeasuredHeight() + aVar.bottomMargin + aVar.topMargin;
                if (i13 + measuredWidth > width) {
                    c0799b.f1397c.add(Integer.valueOf(iMax));
                    c0799b.f1396b.add(arrayList2);
                    c0799b.f1398d.add(Integer.valueOf(((int) ((width - i13) * f)) + getPaddingLeft()));
                    i11 += iMax;
                    arrayList2 = new ArrayList();
                    iMax = 0;
                    i13 = 0;
                }
                i13 += measuredWidth;
                iMax = Math.max(iMax, measuredHeight);
                arrayList2.add(childAt);
            }
            i12++;
        }
        c0799b.f1397c.add(Integer.valueOf(iMax));
        c0799b.f1396b.add(arrayList2);
        c0799b.f1398d.add(Integer.valueOf(((int) ((width - i13) * f)) + getPaddingLeft()));
        int i14 = i11 + iMax;
        int i15 = c0799b.f1395a & 112;
        if (i15 != 16) {
            i6 = i15 != 80 ? 0 : height - i14;
        } else {
            i6 = (height - i14) / 2;
        }
        int size = c0799b.f1396b.size();
        int paddingTop2 = getPaddingTop();
        int i16 = 0;
        while (i16 < size) {
            int iIntValue = c0799b.f1397c.get(i16).intValue();
            List<View> list = c0799b.f1396b.get(i16);
            int iIntValue2 = c0799b.f1398d.get(i16).intValue();
            int size2 = list.size();
            int i17 = iIntValue2;
            int i18 = 0;
            while (i18 < size2) {
                View view = list.get(i18);
                if (view.getVisibility() != i5) {
                    a aVar2 = (a) view.getLayoutParams();
                    if (aVar2.height == -1) {
                        if (aVar2.width == -1) {
                            i8 = i13;
                        } else {
                            if (aVar2.width >= 0) {
                                i8 = aVar2.width;
                            } else {
                                i8 = i13;
                                i9 = Integer.MIN_VALUE;
                            }
                            view.measure(View.MeasureSpec.makeMeasureSpec(i8, i9), View.MeasureSpec.makeMeasureSpec((iIntValue - aVar2.topMargin) - aVar2.bottomMargin, InterfaceC1925e.f4896e));
                        }
                        i9 = InterfaceC1925e.f4896e;
                        view.measure(View.MeasureSpec.makeMeasureSpec(i8, i9), View.MeasureSpec.makeMeasureSpec((iIntValue - aVar2.topMargin) - aVar2.bottomMargin, InterfaceC1925e.f4896e));
                    }
                    int measuredWidth2 = view.getMeasuredWidth();
                    int measuredHeight2 = view.getMeasuredHeight();
                    if (Gravity.isVertical(aVar2.f1399a)) {
                        int i19 = aVar2.f1399a;
                        if (i19 != 80) {
                            switch (i19) {
                                case 16:
                                case 17:
                                    i7 = (((iIntValue - measuredHeight2) - aVar2.topMargin) - aVar2.bottomMargin) / 2;
                                    break;
                                default:
                                    i7 = 0;
                                    break;
                            }
                        } else {
                            i7 = ((iIntValue - measuredHeight2) - aVar2.topMargin) - aVar2.bottomMargin;
                        }
                    } else {
                        i7 = 0;
                    }
                    view.layout(aVar2.leftMargin + i17, aVar2.topMargin + paddingTop2 + i7 + i6, i17 + measuredWidth2 + aVar2.leftMargin, measuredHeight2 + paddingTop2 + aVar2.topMargin + i7 + i6);
                    i17 += measuredWidth2 + aVar2.leftMargin + aVar2.rightMargin;
                }
                i18++;
                size = size;
                i13 = i13;
                list = list;
                i5 = 8;
            }
            paddingTop2 += iIntValue;
            i16++;
            c0799b = this;
            i5 = 8;
        }
    }

    /* JADX INFO: renamed from: a */
    private static a m1049a(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    /* JADX INFO: renamed from: a */
    private a m1048a(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: renamed from: a */
    private static a m1047a() {
        return new a(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof a);
    }

    @TargetApi(14)
    public final void setGravity(int i) {
        if (this.f1395a != i) {
            if ((8388615 & i) == 0) {
                i |= m1050b() ? GravityCompat.START : 3;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f1395a = i;
            requestLayout();
        }
    }

    public final int getGravity() {
        return this.f1395a;
    }

    /* JADX INFO: renamed from: b */
    private static boolean m1050b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.b$a */
    /* JADX INFO: compiled from: FlowLayout.java */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a */
        public int f1399a;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1399a = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0852R.styleable.FlowLayout_Layout);
            try {
                this.f1399a = typedArrayObtainStyledAttributes.getInt(C0852R.styleable.FlowLayout_Layout_android_layout_gravity, -1);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f1399a = -1;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1399a = -1;
        }
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new a(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    @Override // android.view.ViewGroup
    public final /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }
}
