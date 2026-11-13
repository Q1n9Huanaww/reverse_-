package org.jsoup.select;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* JADX INFO: loaded from: classes2.dex */
public class NodeTraversor {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !NodeTraversor.class.desiredAssertionStatus();
    }

    public static void traverse(NodeVisitor visitor, Node root) {
        Validate.notNull(visitor);
        Validate.notNull(root);
        Node node = root;
        int depth = 0;
        while (node != null) {
            Node parent = node.parentNode();
            visitor.head(node, depth);
            if (parent != null && !node.hasParent()) {
                node = parent.childNode(node.siblingIndex());
            }
            if (node.childNodeSize() > 0) {
                node = node.childNode(0);
                depth++;
            } else {
                while (true) {
                    if (!$assertionsDisabled && node == null) {
                        throw new AssertionError();
                    }
                    if (node.nextSibling() != null || depth <= 0) {
                        break;
                    }
                    visitor.tail(node, depth);
                    node = node.parentNode();
                    depth--;
                }
                visitor.tail(node, depth);
                if (node != root) {
                    node = node.nextSibling();
                } else {
                    return;
                }
            }
        }
    }

    public static void traverse(NodeVisitor visitor, Elements elements) {
        Validate.notNull(visitor);
        Validate.notNull(elements);
        for (Element el : elements) {
            traverse(visitor, el);
        }
    }

    public static NodeFilter.FilterResult filter(NodeFilter filter, Node root) {
        Node node = root;
        int depth = 0;
        while (node != null) {
            NodeFilter.FilterResult result = filter.head(node, depth);
            if (result != NodeFilter.FilterResult.STOP) {
                if (result == NodeFilter.FilterResult.CONTINUE && node.childNodeSize() > 0) {
                    node = node.childNode(0);
                    depth++;
                } else {
                    while (true) {
                        if (!$assertionsDisabled && node == null) {
                            throw new AssertionError();
                        }
                        if (node.nextSibling() != null || depth <= 0) {
                            break;
                        }
                        if ((result != NodeFilter.FilterResult.CONTINUE && result != NodeFilter.FilterResult.SKIP_CHILDREN) || (result = filter.tail(node, depth)) != NodeFilter.FilterResult.STOP) {
                            Node prev = node;
                            node = node.parentNode();
                            depth--;
                            if (result == NodeFilter.FilterResult.REMOVE) {
                                prev.remove();
                            }
                            result = NodeFilter.FilterResult.CONTINUE;
                        } else {
                            return result;
                        }
                    }
                    if ((result != NodeFilter.FilterResult.CONTINUE && result != NodeFilter.FilterResult.SKIP_CHILDREN) || (result = filter.tail(node, depth)) != NodeFilter.FilterResult.STOP) {
                        if (node != root) {
                            Node prev2 = node;
                            node = node.nextSibling();
                            if (result == NodeFilter.FilterResult.REMOVE) {
                                prev2.remove();
                            }
                        } else {
                            return result;
                        }
                    } else {
                        return result;
                    }
                }
            } else {
                return result;
            }
        }
        return NodeFilter.FilterResult.CONTINUE;
    }

    public static void filter(NodeFilter filter, Elements elements) {
        Validate.notNull(filter);
        Validate.notNull(elements);
        for (Element el : elements) {
            if (filter(filter, el) == NodeFilter.FilterResult.STOP) {
                return;
            }
        }
    }
}
