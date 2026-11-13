package org.jsoup.parser;

import com.didi.virtualapk.delegate.LocalService;
import com.umeng.commonsdk.proguard.C1770bg;
import com.umeng.commonsdk.proguard.C1799v;
import java.util.ArrayList;
import org.apache.commons.mail.EmailConstants;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.litepal.util.Const;

/* JADX INFO: loaded from: classes2.dex */
enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                return true;
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                Token.Doctype d = t.asDoctype();
                DocumentType doctype = new DocumentType(tb.settings.normalizeTag(d.getName()), d.getPublicIdentifier(), d.getSystemIdentifier());
                doctype.setPubSysKey(d.getPubSysKey());
                tb.getDocument().appendChild(doctype);
                if (d.isForceQuirks()) {
                    tb.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                tb.transition(BeforeHtml);
                return true;
            }
            tb.transition(BeforeHtml);
            return tb.process(t);
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (!t.isComment()) {
                if (HtmlTreeBuilderState.isWhitespace(t)) {
                    tb.insert(t.asCharacter());
                } else if (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    tb.insert(t.asStartTag());
                    tb.transition(BeforeHead);
                } else {
                    if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                        return anythingElse(t, tb);
                    }
                    if (t.isEndTag()) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                }
            } else {
                tb.insert(t.asComment());
            }
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.insertStartTag(EmailConstants.TEXT_SUBTYPE_HTML);
            tb.transition(BeforeHead);
            return tb.process(t);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
            } else if (t.isComment()) {
                tb.insert(t.asComment());
            } else {
                if (t.isDoctype()) {
                    tb.error(this);
                    return false;
                }
                if (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return InBody.process(t, tb);
                }
                if (t.isStartTag() && t.asStartTag().normalName().equals("head")) {
                    Element head = tb.insert(t.asStartTag());
                    tb.setHeadElement(head);
                    tb.transition(InHead);
                } else {
                    if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                        tb.processStartTag("head");
                        return tb.process(t);
                    }
                    if (t.isEndTag()) {
                        tb.error(this);
                        return false;
                    }
                    tb.processStartTag("head");
                    return tb.process(t);
                }
            }
            return true;
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            switch (t.type) {
                case Comment:
                    tb.insert(t.asComment());
                    return true;
                case Doctype:
                    tb.error(this);
                    return false;
                case StartTag:
                    Token.StartTag start = t.asStartTag();
                    String name = start.normalName();
                    if (name.equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        return InBody.process(t, tb);
                    }
                    if (StringUtil.inSorted(name, Constants.InHeadEmpty)) {
                        Element el = tb.insertEmpty(start);
                        if (!name.equals("base") || !el.hasAttr("href")) {
                            return true;
                        }
                        tb.maybeSetBaseUri(el);
                        return true;
                    }
                    if (name.equals("meta")) {
                        tb.insertEmpty(start);
                        return true;
                    }
                    if (name.equals("title")) {
                        HtmlTreeBuilderState.handleRcData(start, tb);
                        return true;
                    }
                    if (StringUtil.inSorted(name, Constants.InHeadRaw)) {
                        HtmlTreeBuilderState.handleRawtext(start, tb);
                        return true;
                    }
                    if (name.equals("noscript")) {
                        tb.insert(start);
                        tb.transition(InHeadNoscript);
                        return true;
                    }
                    if (name.equals("script")) {
                        tb.tokeniser.transition(TokeniserState.ScriptData);
                        tb.markInsertionMode();
                        tb.transition(Text);
                        tb.insert(start);
                        return true;
                    }
                    if (name.equals("head")) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                case EndTag:
                    Token.EndTag end = t.asEndTag();
                    String name2 = end.normalName();
                    if (name2.equals("head")) {
                        tb.pop();
                        tb.transition(AfterHead);
                        return true;
                    }
                    if (StringUtil.inSorted(name2, Constants.InHeadEnd)) {
                        return anythingElse(t, tb);
                    }
                    tb.error(this);
                    return false;
                default:
                    return anythingElse(t, tb);
            }
        }

        private boolean anythingElse(Token t, TreeBuilder tb) {
            tb.processEndTag("head");
            return tb.process(t);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isDoctype()) {
                tb.error(this);
            } else {
                if (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return tb.process(t, InBody);
                }
                if (!t.isEndTag() || !t.asEndTag().normalName().equals("noscript")) {
                    if (HtmlTreeBuilderState.isWhitespace(t) || t.isComment() || (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                        return tb.process(t, InHead);
                    }
                    if (t.isEndTag() && t.asEndTag().normalName().equals("br")) {
                        return anythingElse(t, tb);
                    }
                    if ((t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) || t.isEndTag()) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                }
                tb.pop();
                tb.transition(InHead);
            }
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            tb.insert(new Token.Character().data(t.toString()));
            return true;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
            } else if (t.isComment()) {
                tb.insert(t.asComment());
            } else if (t.isDoctype()) {
                tb.error(this);
            } else if (t.isStartTag()) {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return tb.process(t, InBody);
                }
                if (name.equals("body")) {
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    tb.transition(InBody);
                } else if (name.equals("frameset")) {
                    tb.insert(startTag);
                    tb.transition(InFrameset);
                } else if (StringUtil.inSorted(name, Constants.InBodyStartToHead)) {
                    tb.error(this);
                    Element head = tb.getHeadElement();
                    tb.push(head);
                    tb.process(t, InHead);
                    tb.removeFromStack(head);
                } else {
                    if (name.equals("head")) {
                        tb.error(this);
                        return false;
                    }
                    anythingElse(t, tb);
                }
            } else if (!t.isEndTag() || StringUtil.inSorted(t.asEndTag().normalName(), Constants.AfterHeadBody)) {
                anythingElse(t, tb);
            } else {
                tb.error(this);
                return false;
            }
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.processStartTag("body");
            tb.framesetOk(true);
            return tb.process(t);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            switch (t.type) {
                case Comment:
                    tb.insert(t.asComment());
                    return true;
                case Doctype:
                    tb.error(this);
                    return false;
                case StartTag:
                    return inBodyStartTag(t, tb);
                case EndTag:
                    return inBodyEndTag(t, tb);
                case Character:
                    Token.Character c = t.asCharacter();
                    if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                        tb.error(this);
                        return false;
                    }
                    if (tb.framesetOk() && HtmlTreeBuilderState.isWhitespace(c)) {
                        tb.reconstructFormattingElements();
                        tb.insert(c);
                    } else {
                        tb.reconstructFormattingElements();
                        tb.insert(c);
                        tb.framesetOk(false);
                    }
                    return true;
                default:
                    return true;
            }
        }

        /* JADX WARN: Code duplicated, block: B:132:0x02b3  */
        /* JADX WARN: Code duplicated, block: B:277:0x0827  */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private boolean inBodyStartTag(Token t, HtmlTreeBuilder tb) {
            String prompt;
            Token.StartTag startTag = t.asStartTag();
            String name = startTag.normalName();
            byte b = -1;
            switch (name.hashCode()) {
                case -1644953643:
                    if (name.equals("frameset")) {
                        b = 5;
                    }
                    break;
                case -1377687758:
                    if (name.equals("button")) {
                        b = 8;
                    }
                    break;
                case -1191214428:
                    if (name.equals("iframe")) {
                        b = 17;
                    }
                    break;
                case -1010136971:
                    if (name.equals("option")) {
                        b = 33;
                    }
                    break;
                case -1003243718:
                    if (name.equals("textarea")) {
                        b = C1770bg.f4125m;
                    }
                    break;
                case -906021636:
                    if (name.equals("select")) {
                        b = 19;
                    }
                    break;
                case -80773204:
                    if (name.equals("optgroup")) {
                        b = 32;
                    }
                    break;
                case 97:
                    if (name.equals(C1799v.f4296ah)) {
                        b = 0;
                    }
                    break;
                case 3200:
                    if (name.equals("dd")) {
                        b = 30;
                    }
                    break;
                case 3216:
                    if (name.equals("dt")) {
                        b = 31;
                    }
                    break;
                case 3273:
                    if (name.equals("h1")) {
                        b = 22;
                    }
                    break;
                case 3274:
                    if (name.equals("h2")) {
                        b = 23;
                    }
                    break;
                case 3275:
                    if (name.equals("h3")) {
                        b = 24;
                    }
                    break;
                case 3276:
                    if (name.equals("h4")) {
                        b = 25;
                    }
                    break;
                case 3277:
                    if (name.equals("h5")) {
                        b = 26;
                    }
                    break;
                case 3278:
                    if (name.equals("h6")) {
                        b = 27;
                    }
                    break;
                case 3338:
                    if (name.equals("hr")) {
                        b = 12;
                    }
                    break;
                case 3453:
                    if (name.equals("li")) {
                        b = 2;
                    }
                    break;
                case 3646:
                    if (name.equals("rp")) {
                        b = 34;
                    }
                    break;
                case 3650:
                    if (name.equals("rt")) {
                        b = 35;
                    }
                    break;
                case 111267:
                    if (name.equals("pre")) {
                        b = 28;
                    }
                    break;
                case 114276:
                    if (name.equals("svg")) {
                        b = 21;
                    }
                    break;
                case 118811:
                    if (name.equals("xmp")) {
                        b = C1770bg.f4126n;
                    }
                    break;
                case 3029410:
                    if (name.equals("body")) {
                        b = 4;
                    }
                    break;
                case 3148996:
                    if (name.equals("form")) {
                        b = 6;
                    }
                    break;
                case 3213227:
                    if (name.equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        b = 3;
                    }
                    break;
                case 3344136:
                    if (name.equals("math")) {
                        b = 20;
                    }
                    break;
                case 3386833:
                    if (name.equals("nobr")) {
                        b = 9;
                    }
                    break;
                case 3536714:
                    if (name.equals("span")) {
                        b = 1;
                    }
                    break;
                case 100313435:
                    if (name.equals("image")) {
                        b = C1770bg.f4123k;
                    }
                    break;
                case 100358090:
                    if (name.equals("input")) {
                        b = 11;
                    }
                    break;
                case 110115790:
                    if (name.equals("table")) {
                        b = 10;
                    }
                    break;
                case 181975684:
                    if (name.equals("listing")) {
                        b = 29;
                    }
                    break;
                case 1973234167:
                    if (name.equals("plaintext")) {
                        b = 7;
                    }
                    break;
                case 2091304424:
                    if (name.equals("isindex")) {
                        b = C1770bg.f4124l;
                    }
                    break;
                case 2115613112:
                    if (name.equals("noembed")) {
                        b = 18;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    if (tb.getActiveFormattingElement(C1799v.f4296ah) != null) {
                        tb.error(this);
                        tb.processEndTag(C1799v.f4296ah);
                        Element remainingA = tb.getFromStack(C1799v.f4296ah);
                        if (remainingA != null) {
                            tb.removeFromActiveFormattingElements(remainingA);
                            tb.removeFromStack(remainingA);
                        }
                    }
                    tb.reconstructFormattingElements();
                    tb.pushActiveFormattingElements(tb.insert(startTag));
                    return true;
                case 1:
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case 2:
                    tb.framesetOk(false);
                    ArrayList<Element> stack = tb.getStack();
                    for (int i = stack.size() - 1; i > 0; i--) {
                        Element el = stack.get(i);
                        if (el.normalName().equals("li")) {
                            tb.processEndTag("li");
                        } else if (!tb.isSpecial(el) || StringUtil.inSorted(el.normalName(), Constants.InBodyStartLiBreakers)) {
                        }
                        if (tb.inButtonScope(C1799v.f4299ak)) {
                            tb.processEndTag(C1799v.f4299ak);
                        }
                        tb.insert(startTag);
                        return true;
                    }
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insert(startTag);
                    return true;
                case 3:
                    tb.error(this);
                    if (tb.getStack().size() > 0) {
                        Element html = tb.getStack().get(0);
                        if (startTag.hasAttributes()) {
                            for (Attribute attribute : startTag.attributes) {
                                if (!html.hasAttr(attribute.getKey())) {
                                    html.attributes().put(attribute);
                                }
                            }
                        }
                    }
                    return true;
                case 4:
                    tb.error(this);
                    ArrayList<Element> stack2 = tb.getStack();
                    if (stack2.size() == 1 || (stack2.size() > 2 && !stack2.get(1).normalName().equals("body"))) {
                        return false;
                    }
                    tb.framesetOk(false);
                    Element body = stack2.get(1);
                    if (startTag.hasAttributes()) {
                        for (Attribute attribute2 : startTag.attributes) {
                            if (!body.hasAttr(attribute2.getKey())) {
                                body.attributes().put(attribute2);
                            }
                        }
                    }
                    return true;
                case 5:
                    tb.error(this);
                    ArrayList<Element> stack3 = tb.getStack();
                    if (stack3.size() == 1 || ((stack3.size() > 2 && !stack3.get(1).normalName().equals("body")) || !tb.framesetOk())) {
                        return false;
                    }
                    Element second = stack3.get(1);
                    if (second.parent() != null) {
                        second.remove();
                    }
                    while (stack3.size() > 1) {
                        stack3.remove(stack3.size() - 1);
                    }
                    tb.insert(startTag);
                    tb.transition(InFrameset);
                    return true;
                case 6:
                    if (tb.getFormElement() != null) {
                        tb.error(this);
                        return false;
                    }
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insertForm(startTag, true);
                    return true;
                case 7:
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insert(startTag);
                    tb.tokeniser.transition(TokeniserState.PLAINTEXT);
                    return true;
                case 8:
                    if (tb.inButtonScope("button")) {
                        tb.error(this);
                        tb.processEndTag("button");
                        tb.process(startTag);
                    } else {
                        tb.reconstructFormattingElements();
                        tb.insert(startTag);
                        tb.framesetOk(false);
                    }
                    return true;
                case 9:
                    tb.reconstructFormattingElements();
                    if (tb.inScope("nobr")) {
                        tb.error(this);
                        tb.processEndTag("nobr");
                        tb.reconstructFormattingElements();
                    }
                    tb.pushActiveFormattingElements(tb.insert(startTag));
                    return true;
                case 10:
                    if (tb.getDocument().quirksMode() != Document.QuirksMode.quirks && tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    tb.transition(InTable);
                    return true;
                case 11:
                    tb.reconstructFormattingElements();
                    if (!tb.insertEmpty(startTag).attr("type").equalsIgnoreCase("hidden")) {
                        tb.framesetOk(false);
                    }
                    return true;
                case 12:
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insertEmpty(startTag);
                    tb.framesetOk(false);
                    return true;
                case 13:
                    if (tb.getFromStack("svg") == null) {
                        return tb.process(startTag.name("img"));
                    }
                    tb.insert(startTag);
                    return true;
                case 14:
                    tb.error(this);
                    if (tb.getFormElement() != null) {
                        return false;
                    }
                    tb.processStartTag("form");
                    if (startTag.hasAttribute("action")) {
                        Element form = tb.getFormElement();
                        String action = startTag.attributes.get("action");
                        form.attributes().put("action", action);
                    }
                    tb.processStartTag("hr");
                    tb.processStartTag("label");
                    if (startTag.hasAttribute("prompt")) {
                        prompt = startTag.attributes.get("prompt");
                    } else {
                        prompt = "This is a searchable index. Enter search keywords: ";
                    }
                    tb.process(new Token.Character().data(prompt));
                    Attributes inputAttribs = new Attributes();
                    if (startTag.hasAttributes()) {
                        for (Attribute attr : startTag.attributes) {
                            if (!StringUtil.inSorted(attr.getKey(), Constants.InBodyStartInputAttribs)) {
                                inputAttribs.put(attr);
                            }
                        }
                    }
                    inputAttribs.put(Const.TableSchema.COLUMN_NAME, "isindex");
                    tb.processStartTag("input", inputAttribs);
                    tb.processEndTag("label");
                    tb.processStartTag("hr");
                    tb.processEndTag("form");
                    return true;
                case 15:
                    tb.insert(startTag);
                    if (!startTag.isSelfClosing()) {
                        tb.tokeniser.transition(TokeniserState.Rcdata);
                        tb.markInsertionMode();
                        tb.framesetOk(false);
                        tb.transition(Text);
                    }
                    return true;
                case 16:
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.reconstructFormattingElements();
                    tb.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case 17:
                    tb.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case 18:
                    HtmlTreeBuilderState.handleRawtext(startTag, tb);
                    return true;
                case 19:
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    tb.framesetOk(false);
                    HtmlTreeBuilderState state = tb.state();
                    if (state.equals(InTable) || state.equals(InCaption) || state.equals(InTableBody) || state.equals(InRow) || state.equals(InCell)) {
                        tb.transition(InSelectInTable);
                    } else {
                        tb.transition(InSelect);
                    }
                    return true;
                case 20:
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case 21:
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    if (StringUtil.inSorted(tb.currentElement().normalName(), Constants.Headings)) {
                        tb.error(this);
                        tb.pop();
                    }
                    tb.insert(startTag);
                    return true;
                case 28:
                case 29:
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insert(startTag);
                    tb.reader.matchConsume("\n");
                    tb.framesetOk(false);
                    return true;
                case 30:
                case 31:
                    tb.framesetOk(false);
                    ArrayList<Element> stack4 = tb.getStack();
                    for (int i2 = stack4.size() - 1; i2 > 0; i2--) {
                        Element el2 = stack4.get(i2);
                        if (StringUtil.inSorted(el2.normalName(), Constants.DdDt)) {
                            tb.processEndTag(el2.normalName());
                        } else if (!tb.isSpecial(el2) || StringUtil.inSorted(el2.normalName(), Constants.InBodyStartLiBreakers)) {
                        }
                        if (tb.inButtonScope(C1799v.f4299ak)) {
                            tb.processEndTag(C1799v.f4299ak);
                        }
                        tb.insert(startTag);
                        return true;
                    }
                    if (tb.inButtonScope(C1799v.f4299ak)) {
                        tb.processEndTag(C1799v.f4299ak);
                    }
                    tb.insert(startTag);
                    return true;
                case 32:
                case 33:
                    if (tb.currentElementIs("option")) {
                        tb.processEndTag("option");
                    }
                    tb.reconstructFormattingElements();
                    tb.insert(startTag);
                    return true;
                case 34:
                case 35:
                    if (tb.inScope("ruby")) {
                        tb.generateImpliedEndTags();
                        if (!tb.currentElementIs("ruby")) {
                            tb.error(this);
                            tb.popStackToBefore("ruby");
                        }
                        tb.insert(startTag);
                    }
                    return true;
                default:
                    if (StringUtil.inSorted(name, Constants.InBodyStartEmptyFormatters)) {
                        tb.reconstructFormattingElements();
                        tb.insertEmpty(startTag);
                        tb.framesetOk(false);
                    } else if (StringUtil.inSorted(name, Constants.InBodyStartPClosers)) {
                        if (tb.inButtonScope(C1799v.f4299ak)) {
                            tb.processEndTag(C1799v.f4299ak);
                        }
                        tb.insert(startTag);
                    } else {
                        if (StringUtil.inSorted(name, Constants.InBodyStartToHead)) {
                            return tb.process(t, InHead);
                        }
                        if (StringUtil.inSorted(name, Constants.Formatters)) {
                            tb.reconstructFormattingElements();
                            tb.pushActiveFormattingElements(tb.insert(startTag));
                        } else if (StringUtil.inSorted(name, Constants.InBodyStartApplets)) {
                            tb.reconstructFormattingElements();
                            tb.insert(startTag);
                            tb.insertMarkerToFormattingElements();
                            tb.framesetOk(false);
                        } else if (StringUtil.inSorted(name, Constants.InBodyStartMedia)) {
                            tb.insertEmpty(startTag);
                        } else {
                            if (StringUtil.inSorted(name, Constants.InBodyStartDrop)) {
                                tb.error(this);
                                return false;
                            }
                            tb.reconstructFormattingElements();
                            tb.insert(startTag);
                        }
                    }
                    return true;
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private boolean inBodyEndTag(Token t, HtmlTreeBuilder tb) {
            Token.EndTag endTag = t.asEndTag();
            String name = endTag.normalName();
            byte b = -1;
            switch (name.hashCode()) {
                case 112:
                    if (name.equals(C1799v.f4299ak)) {
                        b = 6;
                    }
                    break;
                case 3152:
                    if (name.equals("br")) {
                        b = C1770bg.f4125m;
                    }
                    break;
                case 3200:
                    if (name.equals("dd")) {
                        b = 7;
                    }
                    break;
                case 3216:
                    if (name.equals("dt")) {
                        b = 8;
                    }
                    break;
                case 3273:
                    if (name.equals("h1")) {
                        b = 9;
                    }
                    break;
                case 3274:
                    if (name.equals("h2")) {
                        b = 10;
                    }
                    break;
                case 3275:
                    if (name.equals("h3")) {
                        b = 11;
                    }
                    break;
                case 3276:
                    if (name.equals("h4")) {
                        b = 12;
                    }
                    break;
                case 3277:
                    if (name.equals("h5")) {
                        b = C1770bg.f4123k;
                    }
                    break;
                case 3278:
                    if (name.equals("h6")) {
                        b = C1770bg.f4124l;
                    }
                    break;
                case 3453:
                    if (name.equals("li")) {
                        b = 2;
                    }
                    break;
                case 3029410:
                    if (name.equals("body")) {
                        b = 3;
                    }
                    break;
                case 3148996:
                    if (name.equals("form")) {
                        b = 5;
                    }
                    break;
                case 3213227:
                    if (name.equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        b = 4;
                    }
                    break;
                case 3536714:
                    if (name.equals("span")) {
                        b = 1;
                    }
                    break;
                case 1869063452:
                    if (name.equals("sarcasm")) {
                        b = 0;
                    }
                    break;
            }
            switch (b) {
                case 0:
                case 1:
                    return anyOtherEndTag(t, tb);
                case 2:
                    if (!tb.inListItemScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    break;
                    break;
                case 3:
                    if (!tb.inScope("body")) {
                        tb.error(this);
                        return false;
                    }
                    tb.transition(AfterBody);
                    break;
                    break;
                case 4:
                    boolean notIgnored = tb.processEndTag("body");
                    if (notIgnored) {
                        return tb.process(endTag);
                    }
                    break;
                case 5:
                    Element currentForm = tb.getFormElement();
                    tb.setFormElement(null);
                    if (currentForm == null || !tb.inScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.removeFromStack(currentForm);
                    break;
                    break;
                case 6:
                    if (!tb.inButtonScope(name)) {
                        tb.error(this);
                        tb.processStartTag(name);
                        return tb.process(endTag);
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    break;
                    break;
                case 7:
                case 8:
                    if (!tb.inScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    break;
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    if (!tb.inScope(Constants.Headings)) {
                        tb.error(this);
                        return false;
                    }
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(Constants.Headings);
                    break;
                    break;
                case 15:
                    tb.error(this);
                    tb.processStartTag("br");
                    return false;
                default:
                    if (StringUtil.inSorted(name, Constants.InBodyEndAdoptionFormatters)) {
                        return inBodyEndTagAdoption(t, tb);
                    }
                    if (StringUtil.inSorted(name, Constants.InBodyEndClosers)) {
                        if (!tb.inScope(name)) {
                            tb.error(this);
                            return false;
                        }
                        tb.generateImpliedEndTags();
                        if (!tb.currentElementIs(name)) {
                            tb.error(this);
                        }
                        tb.popStackToClose(name);
                    } else if (StringUtil.inSorted(name, Constants.InBodyStartApplets)) {
                        if (!tb.inScope(Const.TableSchema.COLUMN_NAME)) {
                            if (!tb.inScope(name)) {
                                tb.error(this);
                                return false;
                            }
                            tb.generateImpliedEndTags();
                            if (!tb.currentElementIs(name)) {
                                tb.error(this);
                            }
                            tb.popStackToClose(name);
                            tb.clearFormattingElementsToLastMarker();
                        }
                    } else {
                        return anyOtherEndTag(t, tb);
                    }
                    break;
                    break;
            }
            return true;
        }

        boolean anyOtherEndTag(Token t, HtmlTreeBuilder tb) {
            String name = t.asEndTag().normalName;
            ArrayList<Element> stack = tb.getStack();
            Element elFromStack = tb.getFromStack(name);
            if (elFromStack == null) {
                tb.error(this);
                return false;
            }
            for (int pos = stack.size() - 1; pos >= 0; pos--) {
                Element node = stack.get(pos);
                if (node.normalName().equals(name)) {
                    tb.generateImpliedEndTags(name);
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    break;
                }
                if (tb.isSpecial(node)) {
                    tb.error(this);
                    return false;
                }
            }
            return true;
        }

        private boolean inBodyEndTagAdoption(Token t, HtmlTreeBuilder tb) {
            Token.EndTag endTag = t.asEndTag();
            String name = endTag.normalName();
            ArrayList<Element> stack = tb.getStack();
            for (int i = 0; i < 8; i++) {
                Element formatEl = tb.getActiveFormattingElement(name);
                if (formatEl == null) {
                    return anyOtherEndTag(t, tb);
                }
                if (!tb.onStack(formatEl)) {
                    tb.error(this);
                    tb.removeFromActiveFormattingElements(formatEl);
                    return true;
                }
                if (!tb.inScope(formatEl.normalName())) {
                    tb.error(this);
                    return false;
                }
                if (tb.currentElement() != formatEl) {
                    tb.error(this);
                }
                Element furthestBlock = null;
                Element commonAncestor = null;
                boolean seenFormattingElement = false;
                int stackSize = stack.size();
                int bookmark = -1;
                for (int si = 1; si < stackSize && si < 64; si++) {
                    Element el = stack.get(si);
                    if (el == formatEl) {
                        Element commonAncestor2 = stack.get(si - 1);
                        commonAncestor = commonAncestor2;
                        seenFormattingElement = true;
                        bookmark = tb.positionOfElement(el);
                    } else if (seenFormattingElement && tb.isSpecial(el)) {
                        furthestBlock = el;
                        break;
                    }
                }
                if (furthestBlock == null) {
                    tb.popStackToClose(formatEl.normalName());
                    tb.removeFromActiveFormattingElements(formatEl);
                    return true;
                }
                Element node = furthestBlock;
                Element lastNode = furthestBlock;
                for (int j = 0; j < 3; j++) {
                    if (tb.onStack(node)) {
                        node = tb.aboveOnStack(node);
                    }
                    if (!tb.isInActiveFormattingElements(node)) {
                        tb.removeFromStack(node);
                    } else {
                        if (node == formatEl) {
                            break;
                        }
                        Element replacement = new Element(Tag.valueOf(node.nodeName(), ParseSettings.preserveCase), tb.getBaseUri());
                        tb.replaceActiveFormattingElement(node, replacement);
                        tb.replaceOnStack(node, replacement);
                        node = replacement;
                        if (lastNode == furthestBlock) {
                            bookmark = tb.positionOfElement(node) + 1;
                        }
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        node.appendChild(lastNode);
                        lastNode = node;
                    }
                }
                if (commonAncestor != null) {
                    if (StringUtil.inSorted(commonAncestor.normalName(), Constants.InBodyEndTableFosters)) {
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        tb.insertInFosterParent(lastNode);
                    } else {
                        if (lastNode.parent() != null) {
                            lastNode.remove();
                        }
                        commonAncestor.appendChild(lastNode);
                    }
                }
                Element adopter = new Element(formatEl.tag(), tb.getBaseUri());
                adopter.attributes().addAll(formatEl.attributes());
                Node[] childNodes = (Node[]) furthestBlock.childNodes().toArray(new Node[0]);
                for (Node childNode : childNodes) {
                    adopter.appendChild(childNode);
                }
                furthestBlock.appendChild(adopter);
                tb.removeFromActiveFormattingElements(formatEl);
                tb.pushWithBookmark(adopter, bookmark);
                tb.removeFromStack(formatEl);
                tb.insertOnStackAfter(furthestBlock, adopter);
            }
            return true;
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isCharacter()) {
                tb.insert(t.asCharacter());
            } else {
                if (t.isEOF()) {
                    tb.error(this);
                    tb.pop();
                    tb.transition(tb.originalState());
                    return tb.process(t);
                }
                if (t.isEndTag()) {
                    tb.pop();
                    tb.transition(tb.originalState());
                }
            }
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isCharacter() && StringUtil.inSorted(tb.currentElement().normalName(), Constants.InTableFoster)) {
                tb.newPendingTableCharacters();
                tb.markInsertionMode();
                tb.transition(InTableText);
                return tb.process(t);
            }
            if (t.isComment()) {
                tb.insert(t.asComment());
                return true;
            }
            if (t.isDoctype()) {
                tb.error(this);
                return false;
            }
            if (t.isStartTag()) {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals("caption")) {
                    tb.clearStackToTableContext();
                    tb.insertMarkerToFormattingElements();
                    tb.insert(startTag);
                    tb.transition(InCaption);
                    return true;
                }
                if (name.equals("colgroup")) {
                    tb.clearStackToTableContext();
                    tb.insert(startTag);
                    tb.transition(InColumnGroup);
                    return true;
                }
                if (name.equals("col")) {
                    tb.clearStackToTableContext();
                    tb.processStartTag("colgroup");
                    return tb.process(t);
                }
                if (StringUtil.inSorted(name, Constants.InTableToBody)) {
                    tb.clearStackToTableContext();
                    tb.insert(startTag);
                    tb.transition(InTableBody);
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InTableAddBody)) {
                    tb.clearStackToTableContext();
                    tb.processStartTag("tbody");
                    return tb.process(t);
                }
                if (name.equals("table")) {
                    tb.error(this);
                    if (!tb.inTableScope(name)) {
                        return false;
                    }
                    tb.popStackToClose(name);
                    tb.resetInsertionMode();
                    if (tb.state() == InTable) {
                        tb.insert(startTag);
                        return true;
                    }
                    return tb.process(t);
                }
                if (StringUtil.inSorted(name, Constants.InTableToHead)) {
                    return tb.process(t, InHead);
                }
                if (name.equals("input")) {
                    if (!startTag.hasAttributes() || !startTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                        return anythingElse(t, tb);
                    }
                    tb.insertEmpty(startTag);
                    return true;
                }
                if (name.equals("form")) {
                    tb.error(this);
                    if (tb.getFormElement() != null) {
                        return false;
                    }
                    tb.insertForm(startTag, false);
                    return true;
                }
                return anythingElse(t, tb);
            }
            if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name2 = endTag.normalName();
                if (name2.equals("table")) {
                    if (!tb.inTableScope(name2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.popStackToClose("table");
                    tb.resetInsertionMode();
                    return true;
                }
                if (StringUtil.inSorted(name2, Constants.InTableEndErr)) {
                    tb.error(this);
                    return false;
                }
                return anythingElse(t, tb);
            }
            if (t.isEOF()) {
                if (!tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return true;
                }
                tb.error(this);
                return true;
            }
            return anythingElse(t, tb);
        }

        boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            tb.setFosterInserts(true);
            tb.process(t, InBody);
            tb.setFosterInserts(false);
            return true;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.type == Token.TokenType.Character) {
                Token.Character c = t.asCharacter();
                if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                    tb.error(this);
                    return false;
                }
                tb.getPendingTableCharacters().add(c.getData());
                return true;
            }
            if (tb.getPendingTableCharacters().size() > 0) {
                for (String character : tb.getPendingTableCharacters()) {
                    if (!HtmlTreeBuilderState.isWhitespace(character)) {
                        tb.error(this);
                        if (StringUtil.inSorted(tb.currentElement().normalName(), Constants.InTableFoster)) {
                            tb.setFosterInserts(true);
                            tb.process(new Token.Character().data(character), InBody);
                            tb.setFosterInserts(false);
                        } else {
                            tb.process(new Token.Character().data(character), InBody);
                        }
                    } else {
                        tb.insert(new Token.Character().data(character));
                    }
                }
                tb.newPendingTableCharacters();
            }
            tb.transition(tb.originalState());
            return tb.process(t);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isEndTag() && t.asEndTag().normalName().equals("caption")) {
                Token.EndTag endTag = t.asEndTag();
                String name = endTag.normalName();
                if (!tb.inTableScope(name)) {
                    tb.error(this);
                    return false;
                }
                tb.generateImpliedEndTags();
                if (!tb.currentElementIs("caption")) {
                    tb.error(this);
                }
                tb.popStackToClose("caption");
                tb.clearFormattingElementsToLastMarker();
                tb.transition(InTable);
            } else if ((t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InCellCol)) || (t.isEndTag() && t.asEndTag().normalName().equals("table"))) {
                tb.error(this);
                boolean processed = tb.processEndTag("caption");
                if (processed) {
                    return tb.process(t);
                }
            } else {
                if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                    tb.error(this);
                    return false;
                }
                return tb.process(t, InBody);
            }
            return true;
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
                return true;
            }
            switch (C211924.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 1:
                    tb.insert(t.asComment());
                    return true;
                case 2:
                    tb.error(this);
                    return true;
                case 3:
                    Token.StartTag startTag = t.asStartTag();
                    switch (startTag.normalName()) {
                        case "html":
                            return tb.process(t, InBody);
                        case "col":
                            tb.insertEmpty(startTag);
                            return true;
                        default:
                            return anythingElse(t, tb);
                    }
                case 4:
                    Token.EndTag endTag = t.asEndTag();
                    if (endTag.normalName.equals("colgroup")) {
                        if (tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                            tb.error(this);
                            return false;
                        }
                        tb.pop();
                        tb.transition(InTable);
                        return true;
                    }
                    return anythingElse(t, tb);
                case 5:
                default:
                    return anythingElse(t, tb);
                case 6:
                    if (tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        return true;
                    }
                    return anythingElse(t, tb);
            }
        }

        private boolean anythingElse(Token t, TreeBuilder tb) {
            boolean processed = tb.processEndTag("colgroup");
            if (processed) {
                return tb.process(t);
            }
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            switch (C211924.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 3:
                    Token.StartTag startTag = t.asStartTag();
                    String name = startTag.normalName();
                    if (name.equals("template")) {
                        tb.insert(startTag);
                    } else if (name.equals("tr")) {
                        tb.clearStackToTableBodyContext();
                        tb.insert(startTag);
                        tb.transition(InRow);
                    } else {
                        if (StringUtil.inSorted(name, Constants.InCellNames)) {
                            tb.error(this);
                            tb.processStartTag("tr");
                            return tb.process(startTag);
                        }
                        if (StringUtil.inSorted(name, Constants.InTableBodyExit)) {
                            return exitTableBody(t, tb);
                        }
                        return anythingElse(t, tb);
                    }
                    return true;
                case 4:
                    Token.EndTag endTag = t.asEndTag();
                    String name2 = endTag.normalName();
                    if (StringUtil.inSorted(name2, Constants.InTableEndIgnore)) {
                        if (!tb.inTableScope(name2)) {
                            tb.error(this);
                            return false;
                        }
                        tb.clearStackToTableBodyContext();
                        tb.pop();
                        tb.transition(InTable);
                        return true;
                    }
                    if (name2.equals("table")) {
                        return exitTableBody(t, tb);
                    }
                    if (StringUtil.inSorted(name2, Constants.InTableBodyEndIgnore)) {
                        tb.error(this);
                        return false;
                    }
                    return anythingElse(t, tb);
                default:
                    return anythingElse(t, tb);
            }
        }

        private boolean exitTableBody(Token t, HtmlTreeBuilder tb) {
            if (!tb.inTableScope("tbody") && !tb.inTableScope("thead") && !tb.inScope("tfoot")) {
                tb.error(this);
                return false;
            }
            tb.clearStackToTableBodyContext();
            tb.processEndTag(tb.currentElement().normalName());
            return tb.process(t);
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InTable);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isStartTag()) {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals("template")) {
                    tb.insert(startTag);
                } else if (StringUtil.inSorted(name, Constants.InCellNames)) {
                    tb.clearStackToTableRowContext();
                    tb.insert(startTag);
                    tb.transition(InCell);
                    tb.insertMarkerToFormattingElements();
                } else {
                    if (StringUtil.inSorted(name, Constants.InRowMissing)) {
                        return handleMissingTr(t, tb);
                    }
                    return anythingElse(t, tb);
                }
            } else if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name2 = endTag.normalName();
                if (name2.equals("tr")) {
                    if (!tb.inTableScope(name2)) {
                        tb.error(this);
                        return false;
                    }
                    tb.clearStackToTableRowContext();
                    tb.pop();
                    tb.transition(InTableBody);
                } else {
                    if (name2.equals("table")) {
                        return handleMissingTr(t, tb);
                    }
                    if (StringUtil.inSorted(name2, Constants.InTableToBody)) {
                        if (!tb.inTableScope(name2) || !tb.inTableScope("tr")) {
                            tb.error(this);
                            return false;
                        }
                        tb.clearStackToTableRowContext();
                        tb.pop();
                        tb.transition(InTableBody);
                    } else {
                        if (StringUtil.inSorted(name2, Constants.InRowIgnore)) {
                            tb.error(this);
                            return false;
                        }
                        return anythingElse(t, tb);
                    }
                }
            } else {
                return anythingElse(t, tb);
            }
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InTable);
        }

        private boolean handleMissingTr(Token t, TreeBuilder tb) {
            boolean processed = tb.processEndTag("tr");
            if (processed) {
                return tb.process(t);
            }
            return false;
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isEndTag()) {
                Token.EndTag endTag = t.asEndTag();
                String name = endTag.normalName();
                if (StringUtil.inSorted(name, Constants.InCellNames)) {
                    if (!tb.inTableScope(name)) {
                        tb.error(this);
                        tb.transition(InRow);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElementIs(name)) {
                        tb.error(this);
                    }
                    tb.popStackToClose(name);
                    tb.clearFormattingElementsToLastMarker();
                    tb.transition(InRow);
                    return true;
                }
                if (StringUtil.inSorted(name, Constants.InCellBody)) {
                    tb.error(this);
                    return false;
                }
                if (StringUtil.inSorted(name, Constants.InCellTable)) {
                    if (!tb.inTableScope(name)) {
                        tb.error(this);
                        return false;
                    }
                    closeCell(tb);
                    return tb.process(t);
                }
                return anythingElse(t, tb);
            }
            if (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InCellCol)) {
                if (!tb.inTableScope("td") && !tb.inTableScope("th")) {
                    tb.error(this);
                    return false;
                }
                closeCell(tb);
                return tb.process(t);
            }
            return anythingElse(t, tb);
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            return tb.process(t, InBody);
        }

        private void closeCell(HtmlTreeBuilder tb) {
            if (tb.inTableScope("td")) {
                tb.processEndTag("td");
            } else {
                tb.processEndTag("th");
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            switch (C211924.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
                case 1:
                    tb.insert(t.asComment());
                    break;
                case 2:
                    tb.error(this);
                    return false;
                case 3:
                    Token.StartTag start = t.asStartTag();
                    String name = start.normalName();
                    if (name.equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        return tb.process(start, InBody);
                    }
                    if (name.equals("option")) {
                        if (tb.currentElementIs("option")) {
                            tb.processEndTag("option");
                        }
                        tb.insert(start);
                    } else if (name.equals("optgroup")) {
                        if (tb.currentElementIs("option")) {
                            tb.processEndTag("option");
                        }
                        if (tb.currentElementIs("optgroup")) {
                            tb.processEndTag("optgroup");
                        }
                        tb.insert(start);
                    } else {
                        if (name.equals("select")) {
                            tb.error(this);
                            return tb.processEndTag("select");
                        }
                        if (StringUtil.inSorted(name, Constants.InSelectEnd)) {
                            tb.error(this);
                            if (!tb.inSelectScope("select")) {
                                return false;
                            }
                            tb.processEndTag("select");
                            return tb.process(start);
                        }
                        if (name.equals("script")) {
                            return tb.process(t, InHead);
                        }
                        return anythingElse(t, tb);
                    }
                    break;
                    break;
                case 4:
                    Token.EndTag end = t.asEndTag();
                    String name2 = end.normalName();
                    switch (name2) {
                        case "optgroup":
                            if (tb.currentElementIs("option") && tb.aboveOnStack(tb.currentElement()) != null && tb.aboveOnStack(tb.currentElement()).normalName().equals("optgroup")) {
                                tb.processEndTag("option");
                            }
                            if (tb.currentElementIs("optgroup")) {
                                tb.pop();
                                break;
                            } else {
                                tb.error(this);
                                break;
                            }
                            break;
                        case "option":
                            if (tb.currentElementIs("option")) {
                                tb.pop();
                                break;
                            } else {
                                tb.error(this);
                                break;
                            }
                            break;
                        case "select":
                            if (!tb.inSelectScope(name2)) {
                                tb.error(this);
                                return false;
                            }
                            tb.popStackToClose(name2);
                            tb.resetInsertionMode();
                            break;
                            break;
                        default:
                            return anythingElse(t, tb);
                    }
                    break;
                case 5:
                    Token.Character c = t.asCharacter();
                    if (c.getData().equals(HtmlTreeBuilderState.nullString)) {
                        tb.error(this);
                        return false;
                    }
                    tb.insert(c);
                    break;
                    break;
                case 6:
                    if (!tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        tb.error(this);
                    }
                    break;
                default:
                    return anythingElse(t, tb);
            }
            return true;
        }

        private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
            tb.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isStartTag() && StringUtil.inSorted(t.asStartTag().normalName(), Constants.InSelecTableEnd)) {
                tb.error(this);
                tb.popStackToClose("select");
                tb.resetInsertionMode();
                return tb.process(t);
            }
            if (t.isEndTag() && StringUtil.inSorted(t.asEndTag().normalName(), Constants.InSelecTableEnd)) {
                tb.error(this);
                if (tb.inTableScope(t.asEndTag().normalName())) {
                    tb.popStackToClose("select");
                    tb.resetInsertionMode();
                    return tb.process(t);
                }
                return false;
            }
            return tb.process(t, InSelect);
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
            } else if (t.isComment()) {
                tb.insert(t.asComment());
            } else {
                if (t.isDoctype()) {
                    tb.error(this);
                    return false;
                }
                if (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return tb.process(t, InBody);
                }
                if (t.isEndTag() && t.asEndTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    if (tb.isFragmentParsing()) {
                        tb.error(this);
                        return false;
                    }
                    tb.transition(AfterAfterBody);
                } else if (!t.isEOF()) {
                    tb.error(this);
                    tb.transition(InBody);
                    return tb.process(t);
                }
            }
            return true;
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
            } else if (t.isComment()) {
                tb.insert(t.asComment());
            } else {
                if (t.isDoctype()) {
                    tb.error(this);
                    return false;
                }
                if (t.isStartTag()) {
                    Token.StartTag start = t.asStartTag();
                    switch (start.normalName()) {
                        case "html":
                            return tb.process(start, InBody);
                        case "frameset":
                            tb.insert(start);
                            break;
                        case "frame":
                            tb.insertEmpty(start);
                            break;
                        case "noframes":
                            return tb.process(start, InHead);
                        default:
                            tb.error(this);
                            return false;
                    }
                } else if (t.isEndTag() && t.asEndTag().normalName().equals("frameset")) {
                    if (tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        tb.error(this);
                        return false;
                    }
                    tb.pop();
                    if (!tb.isFragmentParsing() && !tb.currentElementIs("frameset")) {
                        tb.transition(AfterFrameset);
                    }
                } else if (t.isEOF()) {
                    if (!tb.currentElementIs(EmailConstants.TEXT_SUBTYPE_HTML)) {
                        tb.error(this);
                        return true;
                    }
                } else {
                    tb.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (HtmlTreeBuilderState.isWhitespace(t)) {
                tb.insert(t.asCharacter());
            } else if (t.isComment()) {
                tb.insert(t.asComment());
            } else {
                if (t.isDoctype()) {
                    tb.error(this);
                    return false;
                }
                if (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    return tb.process(t, InBody);
                }
                if (t.isEndTag() && t.asEndTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML)) {
                    tb.transition(AfterAfterFrameset);
                } else {
                    if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                        return tb.process(t, InHead);
                    }
                    if (!t.isEOF()) {
                        tb.error(this);
                        return false;
                    }
                }
            }
            return true;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isComment()) {
                tb.insert(t.asComment());
            } else if (!t.isDoctype() && (!t.isStartTag() || !t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML))) {
                if (HtmlTreeBuilderState.isWhitespace(t)) {
                    Element html = tb.popStackToClose(EmailConstants.TEXT_SUBTYPE_HTML);
                    tb.insert(t.asCharacter());
                    if (html != null) {
                        tb.stack.add(html);
                        Element body = html.selectFirst("body");
                        if (body != null) {
                            tb.stack.add(body);
                        }
                    }
                } else if (!t.isEOF()) {
                    tb.error(this);
                    tb.transition(InBody);
                    return tb.process(t);
                }
            } else {
                return tb.process(t, InBody);
            }
            return true;
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            if (t.isComment()) {
                tb.insert(t.asComment());
            } else {
                if (t.isDoctype() || HtmlTreeBuilderState.isWhitespace(t) || (t.isStartTag() && t.asStartTag().normalName().equals(EmailConstants.TEXT_SUBTYPE_HTML))) {
                    return tb.process(t, InBody);
                }
                if (!t.isEOF()) {
                    if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                        return tb.process(t, InHead);
                    }
                    tb.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token t, HtmlTreeBuilder tb) {
            return true;
        }
    };

    private static final String nullString = String.valueOf((char) 0);

    abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token t) {
        if (!t.isCharacter()) {
            return false;
        }
        String data = t.asCharacter().getData();
        return StringUtil.isBlank(data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(String data) {
        return StringUtil.isBlank(data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rcdata);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insert(startTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insert(startTag);
    }

    static final class Constants {
        static final String[] InHeadEmpty = {"base", "basefont", "bgsound", LocalService.EXTRA_COMMAND, "link"};
        static final String[] InHeadRaw = {"noframes", "style"};
        static final String[] InHeadEnd = {"body", "br", EmailConstants.TEXT_SUBTYPE_HTML};
        static final String[] AfterHeadBody = {"body", EmailConstants.TEXT_SUBTYPE_HTML};
        static final String[] BeforeHtmlToHead = {"body", "br", "head", EmailConstants.TEXT_SUBTYPE_HTML};
        static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", "style"};
        static final String[] InBodyStartToHead = {"base", "basefont", "bgsound", LocalService.EXTRA_COMMAND, "link", "meta", "noframes", "script", "style", "title"};
        static final String[] InBodyStartPClosers = {"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", C1799v.f4299ak, "section", "summary", "ul"};
        static final String[] Headings = {"h1", "h2", "h3", "h4", "h5", "h6"};
        static final String[] InBodyStartLiBreakers = {"address", "div", C1799v.f4299ak};
        static final String[] DdDt = {"dd", "dt"};
        static final String[] Formatters = {"b", "big", "code", "em", "font", C1799v.f4301am, C1799v.f4300al, "small", "strike", "strong", "tt", "u"};
        static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        static final String[] InBodyStartEmptyFormatters = {"area", "br", "embed", "img", "keygen", "wbr"};
        static final String[] InBodyStartMedia = {"param", "source", "track"};
        static final String[] InBodyStartInputAttribs = {"action", Const.TableSchema.COLUMN_NAME, "prompt"};
        static final String[] InBodyStartDrop = {"caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InBodyEndClosers = {"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
        static final String[] InBodyEndAdoptionFormatters = {C1799v.f4296ah, "b", "big", "code", "em", "font", C1799v.f4301am, "nobr", C1799v.f4300al, "small", "strike", "strong", "tt", "u"};
        static final String[] InBodyEndTableFosters = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableToBody = {"tbody", "tfoot", "thead"};
        static final String[] InTableAddBody = {"td", "th", "tr"};
        static final String[] InTableToHead = {"script", "style"};
        static final String[] InCellNames = {"td", "th"};
        static final String[] InCellBody = {"body", "caption", "col", "colgroup", EmailConstants.TEXT_SUBTYPE_HTML};
        static final String[] InCellTable = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InCellCol = {"caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndErr = {"body", "caption", "col", "colgroup", EmailConstants.TEXT_SUBTYPE_HTML, "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableFoster = {"table", "tbody", "tfoot", "thead", "tr"};
        static final String[] InTableBodyExit = {"caption", "col", "colgroup", "tbody", "tfoot", "thead"};
        static final String[] InTableBodyEndIgnore = {"body", "caption", "col", "colgroup", EmailConstants.TEXT_SUBTYPE_HTML, "td", "th", "tr"};
        static final String[] InRowMissing = {"caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        static final String[] InRowIgnore = {"body", "caption", "col", "colgroup", EmailConstants.TEXT_SUBTYPE_HTML, "td", "th"};
        static final String[] InSelectEnd = {"input", "keygen", "textarea"};
        static final String[] InSelecTableEnd = {"caption", "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        static final String[] InTableEndIgnore = {"tbody", "tfoot", "thead"};
        static final String[] InHeadNoscriptIgnore = {"head", "noscript"};
        static final String[] InCaptionIgnore = {"body", "col", "colgroup", EmailConstants.TEXT_SUBTYPE_HTML, "tbody", "td", "tfoot", "th", "thead", "tr"};

        Constants() {
        }
    }
}
