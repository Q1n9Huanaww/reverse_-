package com.cyjh.mobileanjian.ipc.uip;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.f */
/* JADX INFO: compiled from: UipPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0803f extends PagerAdapter {

    /* JADX INFO: renamed from: a */
    private List<ScrollView> f1413a;

    /* JADX INFO: renamed from: b */
    private List<String> f1414b;

    @Override // android.support.v4.view.PagerAdapter
    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public C0803f(List<ScrollView> list, List<String> list2) {
        this.f1413a = list;
        this.f1414b = list2;
    }

    @Override // android.support.v4.view.PagerAdapter
    public final int getCount() {
        return this.f1413a.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        ScrollView scrollView = this.f1413a.get(i);
        viewGroup.addView(scrollView);
        return scrollView;
    }

    @Override // android.support.v4.view.PagerAdapter
    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.f1413a.get(i));
    }

    @Override // android.support.v4.view.PagerAdapter
    public final CharSequence getPageTitle(int i) {
        return this.f1414b.get(i);
    }
}
