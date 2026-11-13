package com.p024ds.daisi.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.warnship.autoplay.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TabAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private String[] pageTitles;

    public TabAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
        super(fragmentManager);
        this.fragments = arrayList;
        this.pageTitles = context.getResources().getStringArray(R.array.array_pagetitles_name);
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.pageTitles[i];
    }
}
