package org.jsoup.parser;

import android.support.v4.app.NotificationCompat;
import com.didi.virtualapk.delegate.LocalService;
import com.p024ds.daisi.activity.RegisterActivity;
import com.umeng.commonsdk.proguard.C1799v;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.mail.EmailConstants;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;

/* JADX INFO: loaded from: classes2.dex */
public class Tag implements Cloneable {
    private String normalName;
    private String tagName;
    private static final Map<String, Tag> tags = new HashMap();
    private static final String[] blockTags = {EmailConstants.TEXT_SUBTYPE_HTML, "head", "body", "frameset", "script", "noscript", "style", "meta", "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", C1799v.f4299ak, "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "pre", "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "dl", "dt", "dd", "li", "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", RegisterActivity.REGISTER_BY_MAIN, "svg", "math", "center"};
    private static final String[] inlineTags = {"object", "base", "font", "tt", C1799v.f4301am, "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", C1799v.f4296ah, "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", NotificationCompat.CATEGORY_PROGRESS, "meter", "area", "param", "source", "track", "summary", LocalService.EXTRA_COMMAND, "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", C1799v.f4300al};
    private static final String[] emptyTags = {"meta", "link", "base", "frame", "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", LocalService.EXTRA_COMMAND, "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] formatAsInlineTags = {"title", C1799v.f4296ah, C1799v.f4299ak, "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", C1799v.f4300al};
    private static final String[] preserveWhitespaceTags = {"pre", "plaintext", "title", "textarea"};
    private static final String[] formListedTags = {"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"};
    private static final String[] formSubmitTags = {"input", "keygen", "object", "select", "textarea"};
    private boolean isBlock = true;
    private boolean formatAsBlock = true;
    private boolean empty = false;
    private boolean selfClosing = false;
    private boolean preserveWhitespace = false;
    private boolean formList = false;
    private boolean formSubmit = false;

    static {
        for (String tagName : blockTags) {
            register(new Tag(tagName));
        }
        for (String tagName2 : inlineTags) {
            Tag tag = new Tag(tagName2);
            tag.isBlock = false;
            tag.formatAsBlock = false;
            register(tag);
        }
        for (String tagName3 : emptyTags) {
            Tag tag2 = tags.get(tagName3);
            Validate.notNull(tag2);
            tag2.empty = true;
        }
        for (String tagName4 : formatAsInlineTags) {
            Tag tag3 = tags.get(tagName4);
            Validate.notNull(tag3);
            tag3.formatAsBlock = false;
        }
        for (String tagName5 : preserveWhitespaceTags) {
            Tag tag4 = tags.get(tagName5);
            Validate.notNull(tag4);
            tag4.preserveWhitespace = true;
        }
        for (String tagName6 : formListedTags) {
            Tag tag5 = tags.get(tagName6);
            Validate.notNull(tag5);
            tag5.formList = true;
        }
        for (String tagName7 : formSubmitTags) {
            Tag tag6 = tags.get(tagName7);
            Validate.notNull(tag6);
            tag6.formSubmit = true;
        }
    }

    private Tag(String tagName) {
        this.tagName = tagName;
        this.normalName = Normalizer.lowerCase(tagName);
    }

    public String getName() {
        return this.tagName;
    }

    public String normalName() {
        return this.normalName;
    }

    public static Tag valueOf(String tagName, ParseSettings settings) {
        Validate.notNull(tagName);
        Tag tag = tags.get(tagName);
        if (tag == null) {
            String tagName2 = settings.normalizeTag(tagName);
            Validate.notEmpty(tagName2);
            String normalName = Normalizer.lowerCase(tagName2);
            Tag tag2 = tags.get(normalName);
            if (tag2 == null) {
                Tag tag3 = new Tag(tagName2);
                tag3.isBlock = false;
                return tag3;
            }
            if (settings.preserveTagCase() && !tagName2.equals(normalName)) {
                Tag tag4 = tag2.clone();
                tag4.tagName = tagName2;
                return tag4;
            }
            return tag2;
        }
        return tag;
    }

    public static Tag valueOf(String tagName) {
        return valueOf(tagName, ParseSettings.preserveCase);
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String tagName) {
        return tags.containsKey(tagName);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        if (this.tagName.equals(tag.tagName) && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.formList == tag.formList) {
            return this.formSubmit == tag.formSubmit;
        }
        return false;
    }

    public int hashCode() {
        int result = this.tagName.hashCode();
        return (((((((((((((result * 31) + (this.isBlock ? 1 : 0)) * 31) + (this.formatAsBlock ? 1 : 0)) * 31) + (this.empty ? 1 : 0)) * 31) + (this.selfClosing ? 1 : 0)) * 31) + (this.preserveWhitespace ? 1 : 0)) * 31) + (this.formList ? 1 : 0)) * 31) + (this.formSubmit ? 1 : 0);
    }

    public String toString() {
        return this.tagName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Tag clone() {
        try {
            return (Tag) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void register(Tag tag) {
        tags.put(tag.tagName, tag);
    }
}
