package org.jsoup.parser;

import android.support.v4.view.InputDeviceCompat;
import com.didi.virtualapk.delegate.LocalService;
import com.umeng.commonsdk.proguard.C1799v;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.commons.mail.EmailConstants;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/* JADX INFO: loaded from: classes2.dex */
public class HtmlTreeBuilder extends TreeBuilder {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int MaxScopeSearchDepth = 100;
    static final String[] TagSearchButton;
    static final String[] TagSearchEndTags;
    static final String[] TagSearchList;
    static final String[] TagSearchSelectScope;
    static final String[] TagSearchSpecial;
    static final String[] TagSearchTableScope;
    static final String[] TagsSearchInScope;
    private static final int maxQueueDepth = 256;
    private static final int maxUsedFormattingElements = 12;
    private boolean baseUriSetFromDoc;

    @Nullable
    private Element contextElement;
    private Token.EndTag emptyEnd;

    @Nullable
    private FormElement formElement;
    private ArrayList<Element> formattingElements;
    private boolean fosterInserts;
    private boolean fragmentParsing;
    private boolean framesetOk;

    @Nullable
    private Element headElement;
    private HtmlTreeBuilderState originalState;
    private List<String> pendingTableCharacters;
    private String[] specificScopeTarget = {null};
    private HtmlTreeBuilderState state;

    static {
        $assertionsDisabled = !HtmlTreeBuilder.class.desiredAssertionStatus();
        TagsSearchInScope = new String[]{"applet", "caption", EmailConstants.TEXT_SUBTYPE_HTML, "marquee", "object", "table", "td", "th"};
        TagSearchList = new String[]{"ol", "ul"};
        TagSearchButton = new String[]{"button"};
        TagSearchTableScope = new String[]{EmailConstants.TEXT_SUBTYPE_HTML, "table"};
        TagSearchSelectScope = new String[]{"optgroup", "option"};
        TagSearchEndTags = new String[]{"dd", "dt", "li", "optgroup", "option", C1799v.f4299ak, "rp", "rt"};
        TagSearchSpecial = new String[]{"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", "br", "button", "caption", "center", "col", "colgroup", LocalService.EXTRA_COMMAND, "dd", "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", EmailConstants.TEXT_SUBTYPE_HTML, "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", C1799v.f4299ak, "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    }

    @Override // org.jsoup.parser.TreeBuilder
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    @Override // org.jsoup.parser.TreeBuilder
    ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    @Override // org.jsoup.parser.TreeBuilder
    @ParametersAreNonnullByDefault
    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag();
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    @Override // org.jsoup.parser.TreeBuilder
    List<Node> parseFragment(String inputFragment, @Nullable Element context, String baseUri, Parser parser) {
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(new StringReader(inputFragment), baseUri, parser);
        this.contextElement = context;
        this.fragmentParsing = true;
        Element root = null;
        if (context != null) {
            if (context.ownerDocument() != null) {
                this.doc.quirksMode(context.ownerDocument().quirksMode());
            }
            String contextTag = context.normalName();
            if (StringUtil.m5147in(contextTag, "title", "textarea")) {
                this.tokeniser.transition(TokeniserState.Rcdata);
            } else if (StringUtil.m5147in(contextTag, "iframe", "noembed", "noframes", "style", "xmp")) {
                this.tokeniser.transition(TokeniserState.Rawtext);
            } else if (contextTag.equals("script")) {
                this.tokeniser.transition(TokeniserState.ScriptData);
            } else if (!contextTag.equals("noscript") && contextTag.equals("plaintext")) {
                this.tokeniser.transition(TokeniserState.Data);
            } else {
                this.tokeniser.transition(TokeniserState.Data);
            }
            root = new Element(Tag.valueOf(contextTag, this.settings), baseUri);
            this.doc.appendChild(root);
            this.stack.add(root);
            resetInsertionMode();
            Elements contextChain = context.parents();
            contextChain.add(0, context);
            for (Element parent : contextChain) {
                if (parent instanceof FormElement) {
                    this.formElement = (FormElement) parent;
                    break;
                }
            }
        }
        runParser();
        if (context != null) {
            List<Node> nodes = root.siblingNodes();
            if (!nodes.isEmpty()) {
                root.insertChildren(-1, nodes);
            }
            return root.childNodes();
        }
        return this.doc.childNodes();
    }

    @Override // org.jsoup.parser.TreeBuilder
    protected boolean process(Token token) {
        this.currentToken = token;
        return this.state.process(token, this);
    }

    boolean process(Token token, HtmlTreeBuilderState state) {
        this.currentToken = token;
        return state.process(token, this);
    }

    void transition(HtmlTreeBuilderState state) {
        this.state = state;
    }

    HtmlTreeBuilderState state() {
        return this.state;
    }

    void markInsertionMode() {
        this.originalState = this.state;
    }

    HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    void framesetOk(boolean framesetOk) {
        this.framesetOk = framesetOk;
    }

    boolean framesetOk() {
        return this.framesetOk;
    }

    Document getDocument() {
        return this.doc;
    }

    String getBaseUri() {
        return this.baseUri;
    }

    void maybeSetBaseUri(Element base) {
        if (!this.baseUriSetFromDoc) {
            String href = base.absUrl("href");
            if (href.length() != 0) {
                this.baseUri = href;
                this.baseUriSetFromDoc = true;
                this.doc.setBaseUri(href);
            }
        }
    }

    boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    void error(HtmlTreeBuilderState state) {
        if (this.parser.getErrors().canAddError()) {
            this.parser.getErrors().add(new ParseError(this.reader.pos(), "Unexpected token [%s] when in state [%s]", this.currentToken.tokenType(), state));
        }
    }

    Element insert(Token.StartTag startTag) {
        if (startTag.hasAttributes() && !startTag.attributes.isEmpty()) {
            int dupes = startTag.attributes.deduplicate(this.settings);
            if (dupes > 0) {
                error("Duplicate attribute");
            }
        }
        if (startTag.isSelfClosing()) {
            Element el = insertEmpty(startTag);
            this.stack.add(el);
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit(this.emptyEnd.reset().name(el.tagName()));
            return el;
        }
        Element el2 = new Element(Tag.valueOf(startTag.name(), this.settings), null, this.settings.normalizeAttributes(startTag.attributes));
        insert(el2);
        return el2;
    }

    Element insertStartTag(String startTagName) {
        Element el = new Element(Tag.valueOf(startTagName, this.settings), null);
        insert(el);
        return el;
    }

    void insert(Element el) {
        insertNode(el);
        this.stack.add(el);
    }

    Element insertEmpty(Token.StartTag startTag) {
        Tag tag = Tag.valueOf(startTag.name(), this.settings);
        Element el = new Element(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        insertNode(el);
        if (startTag.isSelfClosing()) {
            if (tag.isKnownTag()) {
                if (!tag.isEmpty()) {
                    this.tokeniser.error("Tag cannot be self closing; not a void tag");
                }
            } else {
                tag.setSelfClosing();
            }
        }
        return el;
    }

    FormElement insertForm(Token.StartTag startTag, boolean onStack) {
        Tag tag = Tag.valueOf(startTag.name(), this.settings);
        FormElement el = new FormElement(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        setFormElement(el);
        insertNode(el);
        if (onStack) {
            this.stack.add(el);
        }
        return el;
    }

    void insert(Token.Comment commentToken) {
        Comment comment = new Comment(commentToken.getData());
        insertNode(comment);
    }

    void insert(Token.Character characterToken) {
        Node node;
        Element el = currentElement();
        String tagName = el.normalName();
        String data = characterToken.getData();
        if (characterToken.isCData()) {
            node = new CDataNode(data);
        } else if (isContentForTagData(tagName)) {
            node = new DataNode(data);
        } else {
            node = new TextNode(data);
        }
        el.appendChild(node);
    }

    private void insertNode(Node node) {
        if (this.stack.isEmpty()) {
            this.doc.appendChild(node);
        } else if (isFosterInserts()) {
            insertInFosterParent(node);
        } else {
            currentElement().appendChild(node);
        }
        if ((node instanceof Element) && ((Element) node).tag().isFormListed() && this.formElement != null) {
            this.formElement.addElement((Element) node);
        }
    }

    Element pop() {
        int size = this.stack.size();
        return this.stack.remove(size - 1);
    }

    void push(Element element) {
        this.stack.add(element);
    }

    ArrayList<Element> getStack() {
        return this.stack;
    }

    boolean onStack(Element el) {
        return isElementInQueue(this.stack, el);
    }

    private boolean isElementInQueue(ArrayList<Element> queue, Element element) {
        int bottom = queue.size() - 1;
        int upper = bottom >= 256 ? bottom + InputDeviceCompat.SOURCE_ANY : 0;
        for (int pos = bottom; pos >= upper; pos--) {
            Element next = queue.get(pos);
            if (next == element) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    Element getFromStack(String elName) {
        int bottom = this.stack.size() - 1;
        int upper = bottom >= 256 ? bottom + InputDeviceCompat.SOURCE_ANY : 0;
        for (int pos = bottom; pos >= upper; pos--) {
            Element next = this.stack.get(pos);
            if (next.normalName().equals(elName)) {
                return next;
            }
        }
        return null;
    }

    boolean removeFromStack(Element el) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (next == el) {
                this.stack.remove(pos);
                return true;
            }
        }
        return false;
    }

    @Nullable
    Element popStackToClose(String elName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element el = this.stack.get(pos);
            this.stack.remove(pos);
            if (el.normalName().equals(elName)) {
                return el;
            }
        }
        return null;
    }

    void popStackToClose(String... elNames) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            this.stack.remove(pos);
            if (StringUtil.inSorted(next.normalName(), elNames)) {
                return;
            }
        }
    }

    void popStackToBefore(String elName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (!next.normalName().equals(elName)) {
                this.stack.remove(pos);
            } else {
                return;
            }
        }
    }

    void clearStackToTableContext() {
        clearStackToContext("table");
    }

    void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    void clearStackToTableRowContext() {
        clearStackToContext("tr", "template");
    }

    private void clearStackToContext(String... nodeNames) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (!StringUtil.m5147in(next.normalName(), nodeNames) && !next.normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                this.stack.remove(pos);
            } else {
                return;
            }
        }
    }

    Element aboveOnStack(Element el) {
        if (!$assertionsDisabled && !onStack(el)) {
            throw new AssertionError();
        }
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element next = this.stack.get(pos);
            if (next == el) {
                return this.stack.get(pos - 1);
            }
        }
        return null;
    }

    void insertOnStackAfter(Element after, Element in) {
        int i = this.stack.lastIndexOf(after);
        Validate.isTrue(i != -1);
        this.stack.add(i + 1, in);
    }

    void replaceOnStack(Element out, Element in) {
        replaceInQueue(this.stack, out, in);
    }

    private void replaceInQueue(ArrayList<Element> queue, Element out, Element in) {
        int i = queue.lastIndexOf(out);
        Validate.isTrue(i != -1);
        queue.set(i, in);
    }

    void resetInsertionMode() {
        boolean last = false;
        int bottom = this.stack.size() - 1;
        int upper = bottom >= 256 ? bottom + InputDeviceCompat.SOURCE_ANY : 0;
        if (this.stack.size() == 0) {
            transition(HtmlTreeBuilderState.InBody);
        }
        for (int pos = bottom; pos >= upper; pos--) {
            Element node = this.stack.get(pos);
            if (pos == 0) {
                last = true;
                if (this.fragmentParsing) {
                    node = this.contextElement;
                }
            }
            String name = node != null ? node.normalName() : "";
            if ("select".equals(name)) {
                transition(HtmlTreeBuilderState.InSelect);
                return;
            }
            if ("td".equals(name) || ("th".equals(name) && !last)) {
                transition(HtmlTreeBuilderState.InCell);
                return;
            }
            if ("tr".equals(name)) {
                transition(HtmlTreeBuilderState.InRow);
                return;
            }
            if ("tbody".equals(name) || "thead".equals(name) || "tfoot".equals(name)) {
                transition(HtmlTreeBuilderState.InTableBody);
                return;
            }
            if ("caption".equals(name)) {
                transition(HtmlTreeBuilderState.InCaption);
                return;
            }
            if ("colgroup".equals(name)) {
                transition(HtmlTreeBuilderState.InColumnGroup);
                return;
            }
            if ("table".equals(name)) {
                transition(HtmlTreeBuilderState.InTable);
                return;
            }
            if ("head".equals(name) && !last) {
                transition(HtmlTreeBuilderState.InHead);
                return;
            }
            if ("body".equals(name)) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            }
            if ("frameset".equals(name)) {
                transition(HtmlTreeBuilderState.InFrameset);
                return;
            } else if (EmailConstants.TEXT_SUBTYPE_HTML.equals(name)) {
                transition(this.headElement == null ? HtmlTreeBuilderState.BeforeHead : HtmlTreeBuilderState.AfterHead);
                return;
            } else {
                if (last) {
                    transition(HtmlTreeBuilderState.InBody);
                    return;
                }
            }
        }
    }

    private boolean inSpecificScope(String targetName, String[] baseTypes, String[] extraTypes) {
        this.specificScopeTarget[0] = targetName;
        return inSpecificScope(this.specificScopeTarget, baseTypes, extraTypes);
    }

    private boolean inSpecificScope(String[] targetNames, String[] baseTypes, String[] extraTypes) {
        int bottom = this.stack.size() - 1;
        int top = bottom > 100 ? bottom - 100 : 0;
        for (int pos = bottom; pos >= top; pos--) {
            String elName = this.stack.get(pos).normalName();
            if (StringUtil.inSorted(elName, targetNames)) {
                return true;
            }
            if (StringUtil.inSorted(elName, baseTypes)) {
                return false;
            }
            if (extraTypes != null && StringUtil.inSorted(elName, extraTypes)) {
                return false;
            }
        }
        return false;
    }

    boolean inScope(String[] targetNames) {
        return inSpecificScope(targetNames, TagsSearchInScope, (String[]) null);
    }

    boolean inScope(String targetName) {
        return inScope(targetName, null);
    }

    boolean inScope(String targetName, String[] extras) {
        return inSpecificScope(targetName, TagsSearchInScope, extras);
    }

    boolean inListItemScope(String targetName) {
        return inScope(targetName, TagSearchList);
    }

    boolean inButtonScope(String targetName) {
        return inScope(targetName, TagSearchButton);
    }

    boolean inTableScope(String targetName) {
        return inSpecificScope(targetName, TagSearchTableScope, (String[]) null);
    }

    boolean inSelectScope(String targetName) {
        for (int pos = this.stack.size() - 1; pos >= 0; pos--) {
            Element el = this.stack.get(pos);
            String elName = el.normalName();
            if (elName.equals(targetName)) {
                return true;
            }
            if (!StringUtil.inSorted(elName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    void setHeadElement(Element headElement) {
        this.headElement = headElement;
    }

    Element getHeadElement() {
        return this.headElement;
    }

    boolean isFosterInserts() {
        return this.fosterInserts;
    }

    void setFosterInserts(boolean fosterInserts) {
        this.fosterInserts = fosterInserts;
    }

    FormElement getFormElement() {
        return this.formElement;
    }

    void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    void newPendingTableCharacters() {
        this.pendingTableCharacters = new ArrayList();
    }

    List<String> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    void generateImpliedEndTags(String excludeTag) {
        while (excludeTag != null && !currentElementIs(excludeTag) && StringUtil.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            pop();
        }
    }

    void generateImpliedEndTags() {
        generateImpliedEndTags(null);
    }

    boolean isSpecial(Element el) {
        String name = el.normalName();
        return StringUtil.inSorted(name, TagSearchSpecial);
    }

    Element lastFormattingElement() {
        if (this.formattingElements.size() > 0) {
            return this.formattingElements.get(this.formattingElements.size() - 1);
        }
        return null;
    }

    int positionOfElement(Element el) {
        for (int i = 0; i < this.formattingElements.size(); i++) {
            if (el == this.formattingElements.get(i)) {
                return i;
            }
        }
        return -1;
    }

    Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    void pushActiveFormattingElements(Element in) {
        checkActiveFormattingElements(in);
        this.formattingElements.add(in);
    }

    void pushWithBookmark(Element in, int bookmark) {
        checkActiveFormattingElements(in);
        try {
            this.formattingElements.add(bookmark, in);
        } catch (IndexOutOfBoundsException e) {
            this.formattingElements.add(in);
        }
    }

    void checkActiveFormattingElements(Element in) {
        int numSeen = 0;
        for (int pos = this.formattingElements.size() - 1; pos >= 0; pos--) {
            Element el = this.formattingElements.get(pos);
            if (el != null) {
                if (isSameFormattingElement(in, el)) {
                    numSeen++;
                }
                if (numSeen == 3) {
                    this.formattingElements.remove(pos);
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean isSameFormattingElement(Element a, Element b) {
        return a.normalName().equals(b.normalName()) && a.attributes().equals(b.attributes());
    }

    void reconstructFormattingElements() {
        Element last = lastFormattingElement();
        if (last != null && !onStack(last)) {
            Element entry = last;
            int size = this.formattingElements.size();
            int ceil = size - 12;
            if (ceil < 0) {
                ceil = 0;
            }
            int pos = size - 1;
            boolean skip = false;
            do {
                if (pos == ceil) {
                    skip = true;
                    break;
                }
                pos--;
                Element entry2 = this.formattingElements.get(pos);
                entry = entry2;
                if (entry == null) {
                    break;
                }
            } while (!onStack(entry));
            do {
                if (!skip) {
                    pos++;
                    Element entry3 = this.formattingElements.get(pos);
                    entry = entry3;
                }
                Validate.notNull(entry);
                skip = false;
                Element newEl = insertStartTag(entry.normalName());
                if (entry.attributesSize() > 0) {
                    newEl.attributes().addAll(entry.attributes());
                }
                this.formattingElements.set(pos, newEl);
            } while (pos != size - 1);
        }
    }

    void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty()) {
            Element el = removeLastFormattingElement();
            if (el == null) {
                return;
            }
        }
    }

    void removeFromActiveFormattingElements(Element el) {
        for (int pos = this.formattingElements.size() - 1; pos >= 0; pos--) {
            Element next = this.formattingElements.get(pos);
            if (next == el) {
                this.formattingElements.remove(pos);
                return;
            }
        }
    }

    boolean isInActiveFormattingElements(Element el) {
        return isElementInQueue(this.formattingElements, el);
    }

    Element getActiveFormattingElement(String nodeName) {
        for (int pos = this.formattingElements.size() - 1; pos >= 0; pos--) {
            Element next = this.formattingElements.get(pos);
            if (next == null) {
                break;
            }
            if (next.normalName().equals(nodeName)) {
                return next;
            }
        }
        return null;
    }

    void replaceActiveFormattingElement(Element out, Element in) {
        replaceInQueue(this.formattingElements, out, in);
    }

    void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    void insertInFosterParent(Node in) {
        Element fosterParent;
        Element lastTable = getFromStack("table");
        boolean isLastTableParent = false;
        if (lastTable != null) {
            if (lastTable.parent() != null) {
                fosterParent = lastTable.parent();
                isLastTableParent = true;
            } else {
                fosterParent = aboveOnStack(lastTable);
            }
        } else {
            fosterParent = this.stack.get(0);
        }
        if (isLastTableParent) {
            Validate.notNull(lastTable);
            lastTable.before(in);
        } else {
            fosterParent.appendChild(in);
        }
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }

    @Override // org.jsoup.parser.TreeBuilder
    protected boolean isContentForTagData(String normalName) {
        return normalName.equals("script") || normalName.equals("style");
    }
}
