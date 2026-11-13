package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Nullable;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

/* JADX INFO: loaded from: classes2.dex */
public class Attribute implements Map.Entry<String, String>, Cloneable {
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String key;

    @Nullable
    Attributes parent;

    @Nullable
    private String val;

    public Attribute(String key, @Nullable String value) {
        this(key, value, null);
    }

    public Attribute(String key, @Nullable String val, @Nullable Attributes parent) {
        Validate.notNull(key);
        String key2 = key.trim();
        Validate.notEmpty(key2);
        this.key = key2;
        this.val = val;
        this.parent = parent;
    }

    @Override // java.util.Map.Entry
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        int i;
        Validate.notNull(key);
        String key2 = key.trim();
        Validate.notEmpty(key2);
        if (this.parent != null && (i = this.parent.indexOfKey(this.key)) != -1) {
            this.parent.keys[i] = key2;
        }
        this.key = key2;
    }

    @Override // java.util.Map.Entry
    public String getValue() {
        return Attributes.checkNotNull(this.val);
    }

    public boolean hasDeclaredValue() {
        return this.val != null;
    }

    @Override // java.util.Map.Entry
    public String setValue(@Nullable String val) {
        int i;
        String oldVal = this.val;
        if (this.parent != null && (i = this.parent.indexOfKey(this.key)) != -1) {
            oldVal = this.parent.get(this.key);
            this.parent.vals[i] = val;
        }
        this.val = val;
        return Attributes.checkNotNull(oldVal);
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            html(sb, new Document("").outputSettings());
            return StringUtil.releaseBuilder(sb);
        } catch (IOException exception) {
            throw new SerializationException(exception);
        }
    }

    protected static void html(String key, @Nullable String val, Appendable accum, Document.OutputSettings out) throws IOException {
        accum.append(key);
        if (!shouldCollapseAttribute(key, val, out)) {
            accum.append("=\"");
            Entities.escape(accum, Attributes.checkNotNull(val), out, true, false, false);
            accum.append('\"');
        }
    }

    protected void html(Appendable accum, Document.OutputSettings out) throws IOException {
        html(this.key, this.val, accum, out);
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String unencodedKey, String encodedValue) {
        String value = Entities.unescape(encodedValue, true);
        return new Attribute(unencodedKey, value, null);
    }

    protected boolean isDataAttribute() {
        return isDataAttribute(this.key);
    }

    protected static boolean isDataAttribute(String key) {
        return key.startsWith("data-") && key.length() > "data-".length();
    }

    protected final boolean shouldCollapseAttribute(Document.OutputSettings out) {
        return shouldCollapseAttribute(this.key, this.val, out);
    }

    protected static boolean shouldCollapseAttribute(String key, @Nullable String val, Document.OutputSettings out) {
        return out.syntax() == Document.OutputSettings.Syntax.html && (val == null || ((val.isEmpty() || val.equalsIgnoreCase(key)) && isBooleanAttribute(key)));
    }

    protected static boolean isBooleanAttribute(String key) {
        return Arrays.binarySearch(booleanAttributes, key) >= 0;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        if (this.key == null ? attribute.key != null : !this.key.equals(attribute.key)) {
            return false;
        }
        if (this.val != null) {
            return this.val.equals(attribute.val);
        }
        return attribute.val == null;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int result = this.key != null ? this.key.hashCode() : 0;
        return (result * 31) + (this.val != null ? this.val.hashCode() : 0);
    }

    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
