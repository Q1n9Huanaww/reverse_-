package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Nullable;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/* JADX INFO: loaded from: classes2.dex */
public class Elements extends ArrayList<Element> {
    public Elements() {
    }

    public Elements(int initialCapacity) {
        super(initialCapacity);
    }

    public Elements(Collection<Element> elements) {
        super(elements);
    }

    public Elements(List<Element> elements) {
        super(elements);
    }

    public Elements(Element... elements) {
        super(Arrays.asList(elements));
    }

    @Override // java.util.ArrayList
    public Elements clone() {
        Elements clone = new Elements(size());
        for (Element e : this) {
            clone.add(e.mo5194clone());
        }
        return clone;
    }

    public String attr(String attributeKey) {
        for (Element element : this) {
            if (element.hasAttr(attributeKey)) {
                return element.attr(attributeKey);
            }
        }
        return "";
    }

    public boolean hasAttr(String attributeKey) {
        for (Element element : this) {
            if (element.hasAttr(attributeKey)) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachAttr(String attributeKey) {
        List<String> attrs = new ArrayList<>(size());
        for (Element element : this) {
            if (element.hasAttr(attributeKey)) {
                attrs.add(element.attr(attributeKey));
            }
        }
        return attrs;
    }

    public Elements attr(String attributeKey, String attributeValue) {
        for (Element element : this) {
            element.attr(attributeKey, attributeValue);
        }
        return this;
    }

    public Elements removeAttr(String attributeKey) {
        for (Element element : this) {
            element.removeAttr(attributeKey);
        }
        return this;
    }

    public Elements addClass(String className) {
        for (Element element : this) {
            element.addClass(className);
        }
        return this;
    }

    public Elements removeClass(String className) {
        for (Element element : this) {
            element.removeClass(className);
        }
        return this;
    }

    public Elements toggleClass(String className) {
        for (Element element : this) {
            element.toggleClass(className);
        }
        return this;
    }

    public boolean hasClass(String className) {
        for (Element element : this) {
            if (element.hasClass(className)) {
                return true;
            }
        }
        return false;
    }

    public String val() {
        return size() > 0 ? first().val() : "";
    }

    public Elements val(String value) {
        for (Element element : this) {
            element.val(value);
        }
        return this;
    }

    public String text() {
        StringBuilder sb = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(element.text());
        }
        return StringUtil.releaseBuilder(sb);
    }

    public boolean hasText() {
        for (Element element : this) {
            if (element.hasText()) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachText() {
        ArrayList<String> texts = new ArrayList<>(size());
        for (Element el : this) {
            if (el.hasText()) {
                texts.add(el.text());
            }
        }
        return texts;
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.html());
        }
        return StringUtil.releaseBuilder(sb);
    }

    public String outerHtml() {
        StringBuilder sb = StringUtil.borrowBuilder();
        for (Element element : this) {
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.outerHtml());
        }
        return StringUtil.releaseBuilder(sb);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return outerHtml();
    }

    public Elements tagName(String tagName) {
        for (Element element : this) {
            element.tagName(tagName);
        }
        return this;
    }

    public Elements html(String html) {
        for (Element element : this) {
            element.html(html);
        }
        return this;
    }

    public Elements prepend(String html) {
        for (Element element : this) {
            element.prepend(html);
        }
        return this;
    }

    public Elements append(String html) {
        for (Element element : this) {
            element.append(html);
        }
        return this;
    }

    public Elements before(String html) {
        for (Element element : this) {
            element.before(html);
        }
        return this;
    }

    public Elements after(String html) {
        for (Element element : this) {
            element.after(html);
        }
        return this;
    }

    public Elements wrap(String html) {
        Validate.notEmpty(html);
        for (Element element : this) {
            element.wrap(html);
        }
        return this;
    }

    public Elements unwrap() {
        for (Element element : this) {
            element.unwrap();
        }
        return this;
    }

    public Elements empty() {
        for (Element element : this) {
            element.empty();
        }
        return this;
    }

    public Elements remove() {
        for (Element element : this) {
            element.remove();
        }
        return this;
    }

    public Elements select(String query) {
        return Selector.select(query, this);
    }

    public Elements not(String query) {
        Elements out = Selector.select(query, this);
        return Selector.filterOut(this, out);
    }

    /* JADX INFO: renamed from: eq */
    public Elements m5152eq(int index) {
        return size() > index ? new Elements(get(index)) : new Elements();
    }

    /* JADX INFO: renamed from: is */
    public boolean m5153is(String query) {
        Evaluator eval = QueryParser.parse(query);
        for (Element e : this) {
            if (e.m5151is(eval)) {
                return true;
            }
        }
        return false;
    }

    public Elements next() {
        return siblings(null, true, false);
    }

    public Elements next(String query) {
        return siblings(query, true, false);
    }

    public Elements nextAll() {
        return siblings(null, true, true);
    }

    public Elements nextAll(String query) {
        return siblings(query, true, true);
    }

    public Elements prev() {
        return siblings(null, false, false);
    }

    public Elements prev(String query) {
        return siblings(query, false, false);
    }

    public Elements prevAll() {
        return siblings(null, false, true);
    }

    public Elements prevAll(String query) {
        return siblings(query, false, true);
    }

    private Elements siblings(@Nullable String query, boolean next, boolean all) {
        Elements els = new Elements();
        Evaluator eval = query != null ? QueryParser.parse(query) : null;
        for (Element e : this) {
            do {
                Element sib = next ? e.nextElementSibling() : e.previousElementSibling();
                if (sib == null) {
                    break;
                }
                if (eval == null || sib.m5151is(eval)) {
                    els.add(sib);
                }
                e = sib;
            } while (all);
        }
        return els;
    }

    public Elements parents() {
        HashSet<Element> combo = new LinkedHashSet<>();
        for (Element e : this) {
            combo.addAll(e.parents());
        }
        return new Elements(combo);
    }

    @Nullable
    public Element first() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    @Nullable
    public Element last() {
        if (isEmpty()) {
            return null;
        }
        return get(size() - 1);
    }

    public Elements traverse(NodeVisitor nodeVisitor) {
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Elements filter(NodeFilter nodeFilter) {
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public List<FormElement> forms() {
        ArrayList<FormElement> forms = new ArrayList<>();
        for (Element el : this) {
            if (el instanceof FormElement) {
                forms.add((FormElement) el);
            }
        }
        return forms;
    }

    public List<Comment> comments() {
        return childNodesOfType(Comment.class);
    }

    public List<TextNode> textNodes() {
        return childNodesOfType(TextNode.class);
    }

    public List<DataNode> dataNodes() {
        return childNodesOfType(DataNode.class);
    }

    private <T extends Node> List<T> childNodesOfType(Class<T> tClass) {
        ArrayList<T> nodes = new ArrayList<>();
        for (Element el : this) {
            for (int i = 0; i < el.childNodeSize(); i++) {
                Node node = el.childNode(i);
                if (tClass.isInstance(node)) {
                    nodes.add(tClass.cast(node));
                }
            }
        }
        return nodes;
    }
}
