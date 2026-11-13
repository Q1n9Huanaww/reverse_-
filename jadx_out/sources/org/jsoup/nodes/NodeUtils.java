package org.jsoup.nodes;

import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;

/* JADX INFO: loaded from: classes2.dex */
final class NodeUtils {
    NodeUtils() {
    }

    static Document.OutputSettings outputSettings(Node node) {
        Document owner = node.ownerDocument();
        return owner != null ? owner.outputSettings() : new Document("").outputSettings();
    }

    static Parser parser(Node node) {
        Document doc = node.ownerDocument();
        return (doc == null || doc.parser() == null) ? new Parser(new HtmlTreeBuilder()) : doc.parser();
    }
}
