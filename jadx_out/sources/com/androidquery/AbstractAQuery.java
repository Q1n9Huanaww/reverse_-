package com.androidquery;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.androidquery.AbstractAQuery;
import com.androidquery.auth.AccountHandle;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.callback.ImageOptions;
import com.androidquery.callback.Transformer;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.WebImage;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAQuery<T extends AbstractAQuery<T>> implements Constants {
    private Activity act;

    /* JADX INFO: renamed from: ah */
    protected AccountHandle f50ah;
    private Constructor<T> constructor;
    private Context context;
    private int policy = 0;
    protected Object progress;
    private HttpHost proxy;
    private View root;
    private Transformer trans;
    protected View view;
    private static final Class<?>[] ON_CLICK_SIG = {View.class};
    private static Class<?>[] ON_ITEM_SIG = {AdapterView.class, View.class, Integer.TYPE, Long.TYPE};
    private static Class<?>[] ON_SCROLLED_STATE_SIG = {AbsListView.class, Integer.TYPE};
    private static final Class<?>[] TEXT_CHANGE_SIG = {CharSequence.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
    private static Class<?>[] PENDING_TRANSITION_SIG = {Integer.TYPE, Integer.TYPE};
    private static final Class<?>[] OVER_SCROLL_SIG = {Integer.TYPE};
    private static Class<?>[] LAYER_TYPE_SIG = {Integer.TYPE, Paint.class};
    private static WeakHashMap<Dialog, Void> dialogs = new WeakHashMap<>();

    protected T self() {
        return this;
    }

    protected T create(View view) {
        T t = null;
        try {
            T tNewInstance = getConstructor().newInstance(view);
            try {
                tNewInstance.act = this.act;
                return tNewInstance;
            } catch (Exception e) {
                t = tNewInstance;
                e = e;
                ThrowableExtension.printStackTrace(e);
                return t;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private Constructor<T> getConstructor() {
        if (this.constructor == null) {
            try {
                this.constructor = (Constructor<T>) getClass().getConstructor(View.class);
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return this.constructor;
    }

    public AbstractAQuery(Activity activity) {
        this.act = activity;
    }

    public AbstractAQuery(View view) {
        this.root = view;
        this.view = view;
    }

    public AbstractAQuery(Activity activity, View view) {
        this.root = view;
        this.view = view;
        this.act = activity;
    }

    public AbstractAQuery(Context context) {
        this.context = context;
    }

    private View findView(int i) {
        if (this.root != null) {
            return this.root.findViewById(i);
        }
        if (this.act != null) {
            return this.act.findViewById(i);
        }
        return null;
    }

    private View findView(String str) {
        View childAt;
        if (this.root != null) {
            return this.root.findViewWithTag(str);
        }
        if (this.act == null || (childAt = ((ViewGroup) this.act.findViewById(R.id.content)).getChildAt(0)) == null) {
            return null;
        }
        return childAt.findViewWithTag(str);
    }

    private View findView(int... iArr) {
        View viewFindView = findView(iArr[0]);
        for (int i = 1; i < iArr.length && viewFindView != null; i++) {
            viewFindView = viewFindView.findViewById(iArr[i]);
        }
        return viewFindView;
    }

    public T find(int i) {
        return (T) create(findView(i));
    }

    public T parent(int i) {
        View view = this.view;
        while (view != null) {
            if (view.getId() != i) {
                Object parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            }
            return (T) create(view);
        }
        view = null;
        return (T) create(view);
    }

    public T recycle(View view) {
        this.root = view;
        this.view = view;
        reset();
        this.context = null;
        return (T) self();
    }

    public View getView() {
        return this.view;
    }

    /* JADX INFO: renamed from: id */
    public T m19id(int i) {
        return (T) m20id(findView(i));
    }

    /* JADX INFO: renamed from: id */
    public T m20id(View view) {
        this.view = view;
        reset();
        return (T) self();
    }

    /* JADX INFO: renamed from: id */
    public T m21id(String str) {
        return (T) m20id(findView(str));
    }

    /* JADX INFO: renamed from: id */
    public T m22id(int... iArr) {
        return (T) m20id(findView(iArr));
    }

    public T progress(int i) {
        this.progress = findView(i);
        return (T) self();
    }

    public T progress(Object obj) {
        this.progress = obj;
        return (T) self();
    }

    public T progress(Dialog dialog) {
        this.progress = dialog;
        return (T) self();
    }

    public T auth(AccountHandle accountHandle) {
        this.f50ah = accountHandle;
        return (T) self();
    }

    public T transformer(Transformer transformer) {
        this.trans = transformer;
        return (T) self();
    }

    public T policy(int i) {
        this.policy = i;
        return (T) self();
    }

    public T proxy(String str, int i) {
        this.proxy = new HttpHost(str, i);
        return (T) self();
    }

    public T rating(float f) {
        if (this.view instanceof RatingBar) {
            ((RatingBar) this.view).setRating(f);
        }
        return (T) self();
    }

    public T text(int i) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setText(i);
        }
        return (T) self();
    }

    public T text(int i, Object... objArr) {
        Context context = getContext();
        if (context != null) {
            text(context.getString(i, objArr));
        }
        return (T) self();
    }

    public T text(CharSequence charSequence) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setText(charSequence);
        }
        return (T) self();
    }

    public T text(CharSequence charSequence, boolean z) {
        if (z && (charSequence == null || charSequence.length() == 0)) {
            return (T) gone();
        }
        return (T) text(charSequence);
    }

    public T text(Spanned spanned) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setText(spanned);
        }
        return (T) self();
    }

    public T textColor(int i) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setTextColor(i);
        }
        return (T) self();
    }

    public T textColorId(int i) {
        return (T) textColor(getContext().getResources().getColor(i));
    }

    public T typeface(Typeface typeface) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setTypeface(typeface);
        }
        return (T) self();
    }

    public T textSize(float f) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).setTextSize(f);
        }
        return (T) self();
    }

    public T adapter(Adapter adapter) {
        if (this.view instanceof AdapterView) {
            ((AdapterView) this.view).setAdapter(adapter);
        }
        return (T) self();
    }

    public T adapter(ExpandableListAdapter expandableListAdapter) {
        if (this.view instanceof ExpandableListView) {
            ((ExpandableListView) this.view).setAdapter(expandableListAdapter);
        }
        return (T) self();
    }

    public T image(int i) {
        if (this.view instanceof ImageView) {
            ImageView imageView = (ImageView) this.view;
            imageView.setTag(Constants.TAG_URL, null);
            if (i == 0) {
                imageView.setImageBitmap(null);
            } else {
                imageView.setImageResource(i);
            }
        }
        return (T) self();
    }

    public T image(Drawable drawable) {
        if (this.view instanceof ImageView) {
            ImageView imageView = (ImageView) this.view;
            imageView.setTag(Constants.TAG_URL, null);
            imageView.setImageDrawable(drawable);
        }
        return (T) self();
    }

    public T image(Bitmap bitmap) {
        if (this.view instanceof ImageView) {
            ImageView imageView = (ImageView) this.view;
            imageView.setTag(Constants.TAG_URL, null);
            imageView.setImageBitmap(bitmap);
        }
        return (T) self();
    }

    public T image(String str) {
        return (T) image(str, true, true, 0, 0);
    }

    public T image(String str, boolean z, boolean z2) {
        return (T) image(str, z, z2, 0, 0);
    }

    public T image(String str, boolean z, boolean z2, int i, int i2) {
        return (T) image(str, z, z2, i, i2, null, 0);
    }

    public T image(String str, boolean z, boolean z2, int i, int i2, Bitmap bitmap, int i3) {
        return (T) image(str, z, z2, i, i2, bitmap, i3, 0.0f);
    }

    public T image(String str, boolean z, boolean z2, int i, int i2, Bitmap bitmap, int i3, float f) {
        return (T) image(str, z, z2, i, i2, bitmap, i3, f, 0, null);
    }

    protected T image(String str, boolean z, boolean z2, int i, int i2, Bitmap bitmap, int i3, float f, int i4, String str2) {
        if (this.view instanceof ImageView) {
            BitmapAjaxCallback.async(this.act, getContext(), (ImageView) this.view, str, z, z2, i, i2, bitmap, i3, f, Float.MAX_VALUE, this.progress, this.f50ah, this.policy, i4, this.proxy, str2);
            reset();
        }
        return (T) self();
    }

    public T image(String str, ImageOptions imageOptions) {
        return (T) image(str, imageOptions, (String) null);
    }

    protected T image(String str, ImageOptions imageOptions, String str2) {
        if (this.view instanceof ImageView) {
            BitmapAjaxCallback.async(this.act, getContext(), (ImageView) this.view, str, this.progress, this.f50ah, imageOptions, this.proxy, str2);
            reset();
        }
        return (T) self();
    }

    public T image(BitmapAjaxCallback bitmapAjaxCallback) {
        if (this.view instanceof ImageView) {
            bitmapAjaxCallback.imageView((ImageView) this.view);
            invoke(bitmapAjaxCallback);
        }
        return (T) self();
    }

    public T image(String str, boolean z, boolean z2, int i, int i2, BitmapAjaxCallback bitmapAjaxCallback) {
        bitmapAjaxCallback.targetWidth(i).fallback(i2).url(str).memCache(z).fileCache(z2);
        return (T) image(bitmapAjaxCallback);
    }

    public T image(File file, int i) {
        return (T) image(file, true, i, null);
    }

    public T image(File file, boolean z, int i, BitmapAjaxCallback bitmapAjaxCallback) {
        if (bitmapAjaxCallback == null) {
            bitmapAjaxCallback = new BitmapAjaxCallback();
        }
        BitmapAjaxCallback bitmapAjaxCallback2 = bitmapAjaxCallback;
        bitmapAjaxCallback2.file(file);
        return (T) image(file != null ? file.getAbsolutePath() : null, z, true, i, 0, bitmapAjaxCallback2);
    }

    public T image(Bitmap bitmap, float f) {
        BitmapAjaxCallback bitmapAjaxCallback = new BitmapAjaxCallback();
        bitmapAjaxCallback.ratio(f).bitmap(bitmap);
        return (T) image(bitmapAjaxCallback);
    }

    public T tag(Object obj) {
        if (this.view != null) {
            this.view.setTag(obj);
        }
        return (T) self();
    }

    public T tag(int i, Object obj) {
        if (this.view != null) {
            this.view.setTag(i, obj);
        }
        return (T) self();
    }

    public T transparent(boolean z) {
        if (this.view != null) {
            AQUtility.transparent(this.view, z);
        }
        return (T) self();
    }

    public T enabled(boolean z) {
        if (this.view != null) {
            this.view.setEnabled(z);
        }
        return (T) self();
    }

    public T checked(boolean z) {
        if (this.view instanceof CompoundButton) {
            ((CompoundButton) this.view).setChecked(z);
        }
        return (T) self();
    }

    public boolean isChecked() {
        if (this.view instanceof CompoundButton) {
            return ((CompoundButton) this.view).isChecked();
        }
        return false;
    }

    public T clickable(boolean z) {
        if (this.view != null) {
            this.view.setClickable(z);
        }
        return (T) self();
    }

    public T gone() {
        return (T) visibility(8);
    }

    public T invisible() {
        return (T) visibility(4);
    }

    public T visible() {
        return (T) visibility(0);
    }

    public T visibility(int i) {
        if (this.view != null && this.view.getVisibility() != i) {
            this.view.setVisibility(i);
        }
        return (T) self();
    }

    public T background(int i) {
        if (this.view != null) {
            if (i != 0) {
                this.view.setBackgroundResource(i);
            } else {
                this.view.setBackgroundDrawable(null);
            }
        }
        return (T) self();
    }

    public T backgroundColor(int i) {
        if (this.view != null) {
            this.view.setBackgroundColor(i);
        }
        return (T) self();
    }

    public T backgroundColorId(int i) {
        if (this.view != null) {
            this.view.setBackgroundColor(getContext().getResources().getColor(i));
        }
        return (T) self();
    }

    public T dataChanged() {
        if (this.view instanceof AdapterView) {
            Adapter adapter = ((AdapterView) this.view).getAdapter();
            if (adapter instanceof BaseAdapter) {
                ((BaseAdapter) adapter).notifyDataSetChanged();
            }
        }
        return (T) self();
    }

    public boolean isExist() {
        return this.view != null;
    }

    public Object getTag() {
        if (this.view != null) {
            return this.view.getTag();
        }
        return null;
    }

    public Object getTag(int i) {
        if (this.view != null) {
            return this.view.getTag(i);
        }
        return null;
    }

    public ImageView getImageView() {
        return (ImageView) this.view;
    }

    public Gallery getGallery() {
        return (Gallery) this.view;
    }

    public TextView getTextView() {
        return (TextView) this.view;
    }

    public EditText getEditText() {
        return (EditText) this.view;
    }

    public ProgressBar getProgressBar() {
        return (ProgressBar) this.view;
    }

    public SeekBar getSeekBar() {
        return (SeekBar) this.view;
    }

    public Button getButton() {
        return (Button) this.view;
    }

    public CheckBox getCheckBox() {
        return (CheckBox) this.view;
    }

    public ListView getListView() {
        return (ListView) this.view;
    }

    public ExpandableListView getExpandableListView() {
        return (ExpandableListView) this.view;
    }

    public GridView getGridView() {
        return (GridView) this.view;
    }

    public RatingBar getRatingBar() {
        return (RatingBar) this.view;
    }

    public WebView getWebView() {
        return (WebView) this.view;
    }

    public Spinner getSpinner() {
        return (Spinner) this.view;
    }

    public Editable getEditable() {
        if (this.view instanceof EditText) {
            return ((EditText) this.view).getEditableText();
        }
        return null;
    }

    public CharSequence getText() {
        if (this.view instanceof TextView) {
            return ((TextView) this.view).getText();
        }
        return null;
    }

    public Object getSelectedItem() {
        if (this.view instanceof AdapterView) {
            return ((AdapterView) this.view).getSelectedItem();
        }
        return null;
    }

    public int getSelectedItemPosition() {
        if (this.view instanceof AdapterView) {
            return ((AdapterView) this.view).getSelectedItemPosition();
        }
        return -1;
    }

    public T clicked(Object obj, String str) {
        return (T) clicked(new Common().forward(obj, str, true, ON_CLICK_SIG));
    }

    public T clicked(View.OnClickListener onClickListener) {
        if (this.view != null) {
            this.view.setOnClickListener(onClickListener);
        }
        return (T) self();
    }

    public T longClicked(Object obj, String str) {
        return (T) longClicked(new Common().forward(obj, str, true, ON_CLICK_SIG));
    }

    public T longClicked(View.OnLongClickListener onLongClickListener) {
        if (this.view != null) {
            this.view.setOnLongClickListener(onLongClickListener);
        }
        return (T) self();
    }

    public T itemClicked(Object obj, String str) {
        return (T) itemClicked(new Common().forward(obj, str, true, ON_ITEM_SIG));
    }

    public T itemClicked(AdapterView.OnItemClickListener onItemClickListener) {
        if (this.view instanceof AdapterView) {
            ((AdapterView) this.view).setOnItemClickListener(onItemClickListener);
        }
        return (T) self();
    }

    public T itemSelected(Object obj, String str) {
        return (T) itemSelected(new Common().forward(obj, str, true, ON_ITEM_SIG));
    }

    public T itemSelected(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        if (this.view instanceof AdapterView) {
            ((AdapterView) this.view).setOnItemSelectedListener(onItemSelectedListener);
        }
        return (T) self();
    }

    public T setSelection(int i) {
        if (this.view instanceof AdapterView) {
            ((AdapterView) this.view).setSelection(i);
        }
        return (T) self();
    }

    public T scrolledBottom(Object obj, String str) {
        if (this.view instanceof AbsListView) {
            setScrollListener().forward(obj, str, true, ON_SCROLLED_STATE_SIG);
        }
        return (T) self();
    }

    private Common setScrollListener() {
        AbsListView absListView = (AbsListView) this.view;
        Common common = (Common) absListView.getTag(Constants.TAG_SCROLL_LISTENER);
        if (common != null) {
            return common;
        }
        Common common2 = new Common();
        absListView.setOnScrollListener(common2);
        absListView.setTag(Constants.TAG_SCROLL_LISTENER, common2);
        AQUtility.debug("set scroll listenr");
        return common2;
    }

    public T scrolled(AbsListView.OnScrollListener onScrollListener) {
        if (this.view instanceof AbsListView) {
            setScrollListener().forward(onScrollListener);
        }
        return (T) self();
    }

    public T textChanged(Object obj, String str) {
        if (this.view instanceof TextView) {
            ((TextView) this.view).addTextChangedListener(new Common().forward(obj, str, true, TEXT_CHANGE_SIG));
        }
        return (T) self();
    }

    public T overridePendingTransition5(int i, int i2) {
        if (this.act != null) {
            AQUtility.invokeHandler(this.act, "overridePendingTransition", false, false, PENDING_TRANSITION_SIG, Integer.valueOf(i), Integer.valueOf(i2));
        }
        return (T) self();
    }

    public T setOverScrollMode9(int i) {
        if (this.view instanceof AbsListView) {
            AQUtility.invokeHandler(this.view, "setOverScrollMode", false, false, OVER_SCROLL_SIG, Integer.valueOf(i));
        }
        return (T) self();
    }

    public T setLayerType11(int i, Paint paint) {
        if (this.view != null) {
            AQUtility.invokeHandler(this.view, "setLayerType", false, false, LAYER_TYPE_SIG, Integer.valueOf(i), paint);
        }
        return (T) self();
    }

    public Object invoke(String str, Class<?>[] clsArr, Object... objArr) {
        Object obj = this.view;
        if (obj == null) {
            obj = this.act;
        }
        return AQUtility.invokeHandler(obj, str, false, false, clsArr, objArr);
    }

    public T hardwareAccelerated11() {
        if (this.act != null) {
            this.act.getWindow().setFlags(16777216, 16777216);
        }
        return (T) self();
    }

    public T clear() {
        if (this.view != null) {
            if (this.view instanceof ImageView) {
                ImageView imageView = (ImageView) this.view;
                imageView.setImageBitmap(null);
                imageView.setTag(Constants.TAG_URL, null);
            } else if (this.view instanceof WebView) {
                WebView webView = (WebView) this.view;
                webView.stopLoading();
                webView.clearView();
                webView.setTag(Constants.TAG_URL, null);
            } else if (this.view instanceof TextView) {
                ((TextView) this.view).setText("");
            }
        }
        return (T) self();
    }

    public T margin(float f, float f2, float f3, float f4) {
        if (this.view != null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                Context context = getContext();
                ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(AQUtility.dip2pixel(context, f), AQUtility.dip2pixel(context, f2), AQUtility.dip2pixel(context, f3), AQUtility.dip2pixel(context, f4));
                this.view.setLayoutParams(layoutParams);
            }
        }
        return (T) self();
    }

    public T width(int i) {
        size(true, i, true);
        return (T) self();
    }

    public T height(int i) {
        size(false, i, true);
        return (T) self();
    }

    public T width(int i, boolean z) {
        size(true, i, z);
        return (T) self();
    }

    public T height(int i, boolean z) {
        size(false, i, z);
        return (T) self();
    }

    private void size(boolean z, int i, boolean z2) {
        if (this.view != null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            Context context = getContext();
            if (i > 0 && z2) {
                i = AQUtility.dip2pixel(context, i);
            }
            if (z) {
                layoutParams.width = i;
            } else {
                layoutParams.height = i;
            }
            this.view.setLayoutParams(layoutParams);
        }
    }

    public Context getContext() {
        if (this.act != null) {
            return this.act;
        }
        if (this.root != null) {
            return this.root.getContext();
        }
        return this.context;
    }

    public <K> T ajax(AjaxCallback<K> ajaxCallback) {
        return (T) invoke(ajaxCallback);
    }

    protected <K> T invoke(AbstractAjaxCallback<?, K> abstractAjaxCallback) {
        if (this.f50ah != null) {
            abstractAjaxCallback.auth(this.f50ah);
        }
        if (this.progress != null) {
            abstractAjaxCallback.progress(this.progress);
        }
        if (this.trans != null) {
            abstractAjaxCallback.transformer(this.trans);
        }
        abstractAjaxCallback.policy(this.policy);
        if (this.proxy != null) {
            abstractAjaxCallback.proxy(this.proxy.getHostName(), this.proxy.getPort());
        }
        if (this.act != null) {
            abstractAjaxCallback.async(this.act);
        } else {
            abstractAjaxCallback.async(getContext());
        }
        reset();
        return (T) self();
    }

    protected void reset() {
        this.f50ah = null;
        this.progress = null;
        this.trans = null;
        this.policy = 0;
        this.proxy = null;
    }

    public <K> T ajax(String str, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.type(cls).url(str);
        return (T) ajax(ajaxCallback);
    }

    public <K> T ajax(String str, Class<K> cls, long j, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.type(cls).url(str).fileCache(true).expire(j);
        return (T) ajax(ajaxCallback);
    }

    public <K> T ajax(String str, Class<K> cls, Object obj, String str2) {
        AjaxCallback<K> ajaxCallback = new AjaxCallback<>();
        ajaxCallback.type(cls).weakHandler(obj, str2);
        return (T) ajax(str, cls, ajaxCallback);
    }

    public <K> T ajax(String str, Class<K> cls, long j, Object obj, String str2) {
        AjaxCallback<K> ajaxCallback = new AjaxCallback<>();
        ajaxCallback.type(cls).weakHandler(obj, str2).fileCache(true).expire(j);
        return (T) ajax(str, cls, ajaxCallback);
    }

    public <K> T ajax(String str, Map<String, ?> map, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.type(cls).url(str).params(map);
        return (T) ajax(ajaxCallback);
    }

    public <K> T ajax(String str, Map<String, ?> map, Class<K> cls, Object obj, String str2) {
        AjaxCallback<K> ajaxCallback = new AjaxCallback<>();
        ajaxCallback.type(cls).weakHandler(obj, str2);
        return (T) ajax(str, map, cls, ajaxCallback);
    }

    public <K> T delete(String str, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.url(str).type(cls).method(2);
        return (T) ajax(ajaxCallback);
    }

    public <K> T put(String str, String str2, HttpEntity httpEntity, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.url(str).type(cls).method(3).header("Content-Type", str2).param(Constants.POST_ENTITY, httpEntity);
        return (T) ajax(ajaxCallback);
    }

    public <K> T post(String str, String str2, HttpEntity httpEntity, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        ajaxCallback.url(str).type(cls).method(1).header("Content-Type", str2).param(Constants.POST_ENTITY, httpEntity);
        return (T) ajax(ajaxCallback);
    }

    public <K> T post(String str, JSONObject jSONObject, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        try {
            return (T) post(str, "application/json", new StringEntity(jSONObject.toString(), "UTF-8"), cls, ajaxCallback);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <K> T put(String str, JSONObject jSONObject, Class<K> cls, AjaxCallback<K> ajaxCallback) {
        try {
            return (T) put(str, "application/json", new StringEntity(jSONObject.toString(), "UTF-8"), cls, ajaxCallback);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <K> T delete(String str, Class<K> cls, Object obj, String str2) {
        AjaxCallback<K> ajaxCallback = new AjaxCallback<>();
        ajaxCallback.weakHandler(obj, str2);
        return (T) delete(str, cls, ajaxCallback);
    }

    public <K> T sync(AjaxCallback<K> ajaxCallback) {
        ajax(ajaxCallback);
        ajaxCallback.block();
        return (T) self();
    }

    public T cache(String str, long j) {
        return (T) ajax(str, byte[].class, j, (Object) null, (String) null);
    }

    public T ajaxCancel() {
        AjaxCallback.cancel();
        return (T) self();
    }

    public File getCachedFile(String str) {
        File existedCacheByUrl = AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(getContext(), 1), str);
        return existedCacheByUrl == null ? AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(getContext(), 0), str) : existedCacheByUrl;
    }

    public T invalidate(String str) {
        File cachedFile = getCachedFile(str);
        if (cachedFile != null) {
            cachedFile.delete();
        }
        return (T) self();
    }

    public Bitmap getCachedImage(String str) {
        return getCachedImage(str, 0);
    }

    public Bitmap getCachedImage(String str, int i) {
        File cachedFile;
        Bitmap memoryCached = BitmapAjaxCallback.getMemoryCached(str, i);
        return (memoryCached != null || (cachedFile = getCachedFile(str)) == null) ? memoryCached : BitmapAjaxCallback.getResizedImage(cachedFile.getAbsolutePath(), null, i, true, 0);
    }

    public Bitmap getCachedImage(int i) {
        return BitmapAjaxCallback.getMemoryCached(getContext(), i);
    }

    @Deprecated
    public boolean shouldDelay(View view, ViewGroup viewGroup, String str, float f) {
        return Common.shouldDelay(view, viewGroup, str, f, true);
    }

    @Deprecated
    public boolean shouldDelay(View view, ViewGroup viewGroup, String str, float f, boolean z) {
        return Common.shouldDelay(view, viewGroup, str, f, z);
    }

    public boolean shouldDelay(int i, boolean z, View view, ViewGroup viewGroup, String str) {
        return Common.shouldDelay(i, -1, view, viewGroup, str);
    }

    public boolean shouldDelay(int i, int i2, boolean z, View view, ViewGroup viewGroup, String str) {
        return Common.shouldDelay(i, i2, view, viewGroup, str);
    }

    public boolean shouldDelay(int i, View view, ViewGroup viewGroup, String str) {
        if (viewGroup instanceof ExpandableListView) {
            throw new IllegalArgumentException("Please use the other shouldDelay methods for expandable list.");
        }
        return Common.shouldDelay(i, view, viewGroup, str);
    }

    public File makeSharedFile(String str, String str2) {
        File tempDir;
        File file = null;
        try {
            File cachedFile = getCachedFile(str);
            if (cachedFile == null || (tempDir = AQUtility.getTempDir()) == null) {
                return null;
            }
            File file2 = new File(tempDir, str2);
            try {
                file2.createNewFile();
                FileInputStream fileInputStream = new FileInputStream(cachedFile);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                FileChannel channel = fileInputStream.getChannel();
                FileChannel channel2 = fileOutputStream.getChannel();
                try {
                    channel.transferTo(0L, channel.size(), channel2);
                    return file2;
                } finally {
                    AQUtility.close(fileInputStream);
                    AQUtility.close(fileOutputStream);
                    AQUtility.close(channel);
                    AQUtility.close(channel2);
                }
            } catch (Exception e) {
                e = e;
                file = file2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        AQUtility.debug((Throwable) e);
        return file;
    }

    public T animate(int i) {
        return (T) animate(i, null);
    }

    public T animate(int i, Animation.AnimationListener animationListener) {
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), i);
        animationLoadAnimation.setAnimationListener(animationListener);
        return (T) animate(animationLoadAnimation);
    }

    public T animate(Animation animation) {
        if (this.view != null && animation != null) {
            this.view.startAnimation(animation);
        }
        return (T) self();
    }

    public T click() {
        if (this.view != null) {
            this.view.performClick();
        }
        return (T) self();
    }

    public T longClick() {
        if (this.view != null) {
            this.view.performLongClick();
        }
        return (T) self();
    }

    public T show(Dialog dialog) {
        if (dialog != null) {
            try {
                dialog.show();
                dialogs.put(dialog, null);
            } catch (Exception unused) {
            }
        }
        return (T) self();
    }

    public T dismiss(Dialog dialog) {
        if (dialog != null) {
            try {
                dialogs.remove(dialog);
                dialog.dismiss();
            } catch (Exception unused) {
            }
        }
        return (T) self();
    }

    public T dismiss() {
        Iterator<Dialog> it2 = dialogs.keySet().iterator();
        while (it2.hasNext()) {
            try {
                it2.next().dismiss();
            } catch (Exception unused) {
            }
            it2.remove();
        }
        return (T) self();
    }

    public T webImage(String str) {
        return (T) webImage(str, true, false, -16777216);
    }

    public T webImage(String str, boolean z, boolean z2, int i) {
        if (this.view instanceof WebView) {
            setLayerType11(1, null);
            new WebImage((WebView) this.view, str, this.progress, z, z2, i).load();
            this.progress = null;
        }
        return (T) self();
    }

    public View inflate(View view, int i, ViewGroup viewGroup) {
        LayoutInflater layoutInflater;
        Integer num;
        if (view != null && (num = (Integer) view.getTag(Constants.TAG_LAYOUT)) != null && num.intValue() == i) {
            return view;
        }
        if (this.act != null) {
            layoutInflater = this.act.getLayoutInflater();
        } else {
            layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        }
        View viewInflate = layoutInflater.inflate(i, viewGroup, false);
        viewInflate.setTag(Constants.TAG_LAYOUT, Integer.valueOf(i));
        return viewInflate;
    }

    public T expand(int i, boolean z) {
        if (this.view instanceof ExpandableListView) {
            ExpandableListView expandableListView = (ExpandableListView) this.view;
            if (z) {
                expandableListView.expandGroup(i);
            } else {
                expandableListView.collapseGroup(i);
            }
        }
        return (T) self();
    }

    public T expand(boolean z) {
        ExpandableListView expandableListView;
        ExpandableListAdapter expandableListAdapter;
        if ((this.view instanceof ExpandableListView) && (expandableListAdapter = (expandableListView = (ExpandableListView) this.view).getExpandableListAdapter()) != null) {
            int groupCount = expandableListAdapter.getGroupCount();
            for (int i = 0; i < groupCount; i++) {
                if (z) {
                    expandableListView.expandGroup(i);
                } else {
                    expandableListView.collapseGroup(i);
                }
            }
        }
        return (T) self();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T download(String str, File file, AjaxCallback<File> ajaxCallback) {
        ((AjaxCallback) ajaxCallback.url(str)).type(File.class).targetFile(file);
        return (T) ajax(ajaxCallback);
    }

    public T download(String str, File file, Object obj, String str2) {
        AjaxCallback<File> ajaxCallback = new AjaxCallback<>();
        ajaxCallback.weakHandler(obj, str2);
        return (T) download(str, file, ajaxCallback);
    }
}
