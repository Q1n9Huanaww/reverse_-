package com.cyjh.mobileanjian.ipc.p018ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.l */
/* JADX INFO: compiled from: UiShowPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0791l extends PagerAdapter {

    /* JADX INFO: renamed from: a */
    List<LinearLayout> f1343a;

    /* JADX INFO: renamed from: b */
    List<String> f1344b;

    @Override // android.support.v4.view.PagerAdapter
    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public C0791l(List<LinearLayout> list, List<String> list2) {
        this.f1343a = list;
        this.f1344b = list2;
    }

    @Override // android.support.v4.view.PagerAdapter
    public final int getCount() {
        return this.f1343a.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = this.f1343a.get(i);
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    @Override // android.support.v4.view.PagerAdapter
    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.f1343a.get(i));
    }

    @Override // android.support.v4.view.PagerAdapter
    public final CharSequence getPageTitle(int i) {
        return this.f1344b.get(i);
    }

    /* JADX INFO: renamed from: a */
    private View m1011a(int i) {
        return this.f1343a.get(i);
    }

    /* JADX INFO: renamed from: a */
    private void m1013a(LinearLayout linearLayout, String str) {
        this.f1343a.add(linearLayout);
        this.f1344b.add(str);
        notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: a */
    public final void m1015a(View view, String str, String str2) {
        for (LinearLayout linearLayout : this.f1343a) {
            if (linearLayout.getTag().equals(str)) {
                ((LinearLayout) linearLayout.findViewWithTag(str2)).addView(view);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1014a(String str, String str2, int i, int i2) {
        for (LinearLayout linearLayout : this.f1343a) {
            if (linearLayout.getTag().equals(str2)) {
                linearLayout.addView(C0786g.m851a(linearLayout.getContext()).m872c(str, i, i2));
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private View m1012a(String str) {
        Iterator<LinearLayout> it2 = this.f1343a.iterator();
        View viewFindViewWithTag = null;
        while (it2.hasNext() && (viewFindViewWithTag = it2.next().findViewWithTag(str)) == null) {
        }
        return viewFindViewWithTag;
    }
}
