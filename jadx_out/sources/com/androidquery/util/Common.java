package com.androidquery.util;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;
import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class Common implements Comparator<File>, Runnable, View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener, AdapterView.OnItemSelectedListener, TextWatcher {
    protected static final int CLEAN_CACHE = 2;
    protected static final int STORE_FILE = 1;
    private boolean fallback;
    private AdapterView.OnItemSelectedListener galleryListener;
    private Object handler;
    private int lastBottom;
    private String method;
    private int methodId;
    private AbsListView.OnScrollListener osl;
    private Object[] params;
    private Class<?>[] sig;
    private int scrollState = 0;
    private boolean galleryListen = false;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public Common forward(Object obj, String str, boolean z, Class<?>[] clsArr) {
        this.handler = obj;
        this.method = str;
        this.fallback = z;
        this.sig = clsArr;
        return this;
    }

    public Common method(int i, Object... objArr) {
        this.methodId = i;
        this.params = objArr;
        return this;
    }

    private Object invoke(Object... objArr) {
        if (this.method != null) {
            if (this.params != null) {
                objArr = this.params;
            }
            Object[] objArr2 = objArr;
            Object obj = this.handler;
            return AQUtility.invokeHandler(obj == null ? this : obj, this.method, this.fallback, true, this.sig, objArr2);
        }
        if (this.methodId == 0) {
            return null;
        }
        switch (this.methodId) {
            case 1:
                AQUtility.store((File) this.params[0], (byte[]) this.params[1]);
                return null;
            case 2:
                AQUtility.cleanCache((File) this.params[0], ((Long) this.params[1]).longValue(), ((Long) this.params[2]).longValue());
                return null;
            default:
                return null;
        }
    }

    @Override // java.util.Comparator
    public int compare(File file, File file2) {
        long jLastModified = file.lastModified();
        long jLastModified2 = file2.lastModified();
        if (jLastModified2 > jLastModified) {
            return 1;
        }
        return jLastModified2 == jLastModified ? 0 : -1;
    }

    @Override // java.lang.Runnable
    public void run() {
        invoke(new Object[0]);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        invoke(view);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        Object objInvoke = invoke(view);
        if (objInvoke instanceof Boolean) {
            return ((Boolean) objInvoke).booleanValue();
        }
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j));
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        checkScrolledBottom(absListView, this.scrollState);
        if (this.osl != null) {
            this.osl.onScroll(absListView, i, i2, i3);
        }
    }

    public int getScrollState() {
        return this.scrollState;
    }

    public void forward(AbsListView.OnScrollListener onScrollListener) {
        this.osl = onScrollListener;
    }

    private void checkScrolledBottom(AbsListView absListView, int i) {
        int count = absListView.getCount();
        int lastVisiblePosition = absListView.getLastVisiblePosition();
        if (i == 0 && count == lastVisiblePosition + 1) {
            if (lastVisiblePosition != this.lastBottom) {
                this.lastBottom = lastVisiblePosition;
                invoke(absListView, Integer.valueOf(i));
                return;
            }
            return;
        }
        this.lastBottom = -1;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.scrollState = i;
        checkScrolledBottom(absListView, i);
        if (absListView instanceof ExpandableListView) {
            onScrollStateChanged((ExpandableListView) absListView, i);
        } else {
            onScrollStateChanged2(absListView, i);
        }
        if (this.osl != null) {
            this.osl.onScrollStateChanged(absListView, i);
        }
    }

    private void onScrollStateChanged(ExpandableListView expandableListView, int i) {
        expandableListView.setTag(Constants.TAG_NUM, Integer.valueOf(i));
        if (i == 0) {
            int firstVisiblePosition = expandableListView.getFirstVisiblePosition();
            int lastVisiblePosition = expandableListView.getLastVisiblePosition() - firstVisiblePosition;
            ExpandableListAdapter expandableListAdapter = expandableListView.getExpandableListAdapter();
            for (int i2 = 0; i2 <= lastVisiblePosition; i2++) {
                long expandableListPosition = expandableListView.getExpandableListPosition(i2 + firstVisiblePosition);
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                if (packedPositionGroup >= 0) {
                    View childAt = expandableListView.getChildAt(i2);
                    Long l = (Long) childAt.getTag(Constants.TAG_NUM);
                    if (l != null && l.longValue() == expandableListPosition) {
                        if (packedPositionChild == -1) {
                            expandableListAdapter.getGroupView(packedPositionGroup, expandableListView.isGroupExpanded(packedPositionGroup), childAt, expandableListView);
                        } else {
                            expandableListAdapter.getChildView(packedPositionGroup, packedPositionChild, packedPositionChild == expandableListAdapter.getChildrenCount(packedPositionGroup) - 1, childAt, expandableListView);
                        }
                        childAt.setTag(Constants.TAG_NUM, null);
                    }
                }
            }
        }
    }

    private void onScrollStateChanged2(AbsListView absListView, int i) {
        absListView.setTag(Constants.TAG_NUM, Integer.valueOf(i));
        if (i == 0) {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            int lastVisiblePosition = absListView.getLastVisiblePosition() - firstVisiblePosition;
            ListAdapter listAdapter = (ListAdapter) absListView.getAdapter();
            for (int i2 = 0; i2 <= lastVisiblePosition; i2++) {
                long j = i2 + firstVisiblePosition;
                View childAt = absListView.getChildAt(i2);
                if (((Number) childAt.getTag(Constants.TAG_NUM)) != null) {
                    listAdapter.getView((int) j, childAt, absListView);
                    childAt.setTag(Constants.TAG_NUM, null);
                }
            }
        }
    }

    public static boolean shouldDelay(int i, int i2, View view, ViewGroup viewGroup, String str) {
        if (str == null || BitmapAjaxCallback.isMemoryCached(str)) {
            return false;
        }
        AbsListView absListView = (AbsListView) viewGroup;
        if (((AbsListView.OnScrollListener) viewGroup.getTag(Constants.TAG_SCROLL_LISTENER)) == null) {
            Common common = new Common();
            absListView.setOnScrollListener(common);
            viewGroup.setTag(Constants.TAG_SCROLL_LISTENER, common);
        }
        Integer num = (Integer) absListView.getTag(Constants.TAG_NUM);
        if (num == null || num.intValue() == 0 || num.intValue() == 1) {
            return false;
        }
        long packedPositionForChild = i2;
        if (viewGroup instanceof ExpandableListView) {
            packedPositionForChild = ExpandableListView.getPackedPositionForChild(i, i2);
        }
        view.setTag(Constants.TAG_NUM, Long.valueOf(packedPositionForChild));
        return true;
    }

    public static boolean shouldDelay(int i, View view, ViewGroup viewGroup, String str) {
        if (viewGroup instanceof Gallery) {
            return shouldDelayGallery(i, view, viewGroup, str);
        }
        return shouldDelay(-2, i, view, viewGroup, str);
    }

    public static boolean shouldDelay(View view, ViewGroup viewGroup, String str, float f, boolean z) {
        return shouldDelay(-1, view, viewGroup, str);
    }

    private static boolean shouldDelayGallery(int i, View view, ViewGroup viewGroup, String str) {
        if (str == null || BitmapAjaxCallback.isMemoryCached(str)) {
            return false;
        }
        Gallery gallery = (Gallery) viewGroup;
        Integer num = (Integer) gallery.getTag(Constants.TAG_NUM);
        if (num == null) {
            num = 0;
            gallery.setTag(Constants.TAG_NUM, 0);
            gallery.setCallbackDuringFling(false);
            new Common().listen(gallery);
        }
        int lastVisiblePosition = ((gallery.getLastVisiblePosition() - gallery.getFirstVisiblePosition()) / 2) + 1;
        int iIntValue = num.intValue() - lastVisiblePosition;
        int iIntValue2 = num.intValue() + lastVisiblePosition;
        if (iIntValue < 0) {
            iIntValue2 -= iIntValue;
            iIntValue = 0;
        }
        if (i >= iIntValue && i <= iIntValue2) {
            view.setTag(Constants.TAG_NUM, Integer.valueOf(i));
            return false;
        }
        view.setTag(Constants.TAG_NUM, null);
        return true;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public void listen(Gallery gallery) {
        this.galleryListener = gallery.getOnItemSelectedListener();
        this.galleryListen = true;
        gallery.setOnItemSelectedListener(this);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j));
        if (this.galleryListener != null) {
            this.galleryListener.onItemSelected(adapterView, view, i, j);
        }
        if (!this.galleryListen || ((Integer) adapterView.getTag(Constants.TAG_NUM)).intValue() == i) {
            return;
        }
        Adapter adapter = adapterView.getAdapter();
        adapterView.setTag(Constants.TAG_NUM, Integer.valueOf(i));
        int childCount = adapterView.getChildCount();
        int firstVisiblePosition = adapterView.getFirstVisiblePosition();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = adapterView.getChildAt(i2);
            int i3 = firstVisiblePosition + i2;
            Integer num = (Integer) childAt.getTag(Constants.TAG_NUM);
            if (num == null || num.intValue() != i3) {
                adapter.getView(i3, childAt, adapterView);
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
        if (this.galleryListener != null) {
            this.galleryListener.onNothingSelected(adapterView);
        }
    }

    public static void showProgress(Object obj, String str, boolean z) {
        if (obj != null) {
            if (obj instanceof View) {
                View view = (View) obj;
                ProgressBar progressBar = obj instanceof ProgressBar ? (ProgressBar) obj : null;
                if (z) {
                    view.setTag(Constants.TAG_URL, str);
                    view.setVisibility(0);
                    if (progressBar != null) {
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        return;
                    }
                    return;
                }
                Object tag = view.getTag(Constants.TAG_URL);
                if (tag == null || tag.equals(str)) {
                    view.setTag(Constants.TAG_URL, null);
                    if (progressBar == null || progressBar.isIndeterminate()) {
                        view.setVisibility(8);
                        return;
                    }
                    return;
                }
                return;
            }
            if (obj instanceof Dialog) {
                Dialog dialog = (Dialog) obj;
                AQuery aQuery = new AQuery(dialog.getContext());
                if (z) {
                    aQuery.show(dialog);
                    return;
                } else {
                    aQuery.dismiss(dialog);
                    return;
                }
            }
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                activity.setProgressBarIndeterminateVisibility(z);
                activity.setProgressBarVisibility(z);
                if (z) {
                    activity.setProgress(0);
                }
            }
        }
    }
}
