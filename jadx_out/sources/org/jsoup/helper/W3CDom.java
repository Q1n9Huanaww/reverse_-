package org.jsoup.helper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.mail.EmailConstants;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: classes2.dex */
public class W3CDom {
    protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public W3CDom() {
        this.factory.setNamespaceAware(true);
    }

    public static Document convert(org.jsoup.nodes.Document in) {
        return new W3CDom().fromJsoup(in);
    }

    public static String asString(Document doc, @Nullable Map<String, String> properties) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            if (properties != null) {
                transformer.setOutputProperties(propertiesFromMap(properties));
            }
            if (doc.getDoctype() != null) {
                DocumentType doctype = doc.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    transformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase(EmailConstants.TEXT_SUBTYPE_HTML) && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties props = new Properties();
        props.putAll(map);
        return props;
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap(EmailConstants.TEXT_SUBTYPE_HTML);
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    private static HashMap<String, String> methodMap(String method) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method", method);
        return map;
    }

    public Document fromJsoup(org.jsoup.nodes.Document in) {
        Validate.notNull(in);
        try {
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();
            Document out = builder.newDocument();
            org.jsoup.nodes.DocumentType doctype = in.documentType();
            if (doctype != null) {
                DocumentType documentType = impl.createDocumentType(doctype.name(), doctype.publicId(), doctype.systemId());
                out.appendChild(documentType);
            }
            out.setXmlStandalone(true);
            convert(in, out);
            return out;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void convert(org.jsoup.nodes.Document in, Document out) {
        if (!StringUtil.isBlank(in.location())) {
            out.setDocumentURI(in.location());
        }
        Element rootEl = in.child(0);
        NodeTraversor.traverse(new W3CBuilder(out), rootEl);
    }

    public String asString(Document doc) {
        return asString(doc, null);
    }

    protected static class W3CBuilder implements NodeVisitor {
        private static final Pattern attrKeyReplace = Pattern.compile("[^-a-zA-Z0-9_:.]");
        private static final Pattern attrKeyValid = Pattern.compile("[a-zA-Z_:][-a-zA-Z0-9_:.]*");
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private Node dest;
        private final Document doc;
        private final Stack<HashMap<String, String>> namespacesStack = new Stack<>();

        public W3CBuilder(Document doc) {
            this.doc = doc;
            this.namespacesStack.push(new HashMap<>());
            this.dest = doc;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x005f A[Catch: DOMException -> 0x0068, TRY_LEAVE, TryCatch #0 {DOMException -> 0x0068, blocks: (B:6:0x003c, B:8:0x0044, B:9:0x004e, B:11:0x005f), top: B:24:0x003c }] */
        @Override // org.jsoup.select.NodeVisitor
        public void head(org.jsoup.nodes.Node source, int depth) {
            org.w3c.dom.Element el;
            this.namespacesStack.push(new HashMap<>(this.namespacesStack.peek()));
            if (source instanceof Element) {
                Element sourceEl = (Element) source;
                String prefix = updateNamespaces(sourceEl);
                String namespace = this.namespacesStack.peek().get(prefix);
                String tagName = sourceEl.tagName();
                if (namespace != null) {
                    el = this.doc.createElementNS(namespace, tagName);
                } else {
                    try {
                        if (tagName.contains(":")) {
                            el = this.doc.createElementNS("", tagName);
                        } else {
                            el = this.doc.createElementNS(namespace, tagName);
                        }
                    } catch (DOMException e) {
                        this.dest.appendChild(this.doc.createTextNode("<" + tagName + ">"));
                        return;
                    }
                }
                copyAttributes(sourceEl, el);
                this.dest.appendChild(el);
                this.dest = el;
                return;
            }
            if (source instanceof TextNode) {
                TextNode sourceText = (TextNode) source;
                Text text = this.doc.createTextNode(sourceText.getWholeText());
                this.dest.appendChild(text);
            } else if (source instanceof Comment) {
                Comment sourceComment = (Comment) source;
                org.w3c.dom.Comment comment = this.doc.createComment(sourceComment.getData());
                this.dest.appendChild(comment);
            } else if (source instanceof DataNode) {
                DataNode sourceData = (DataNode) source;
                Text node = this.doc.createTextNode(sourceData.getWholeData());
                this.dest.appendChild(node);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(org.jsoup.nodes.Node source, int depth) {
            if ((source instanceof Element) && (this.dest.getParentNode() instanceof org.w3c.dom.Element)) {
                this.dest = this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }

        private void copyAttributes(org.jsoup.nodes.Node source, org.w3c.dom.Element el) {
            for (Attribute attribute : source.attributes()) {
                String key = attrKeyReplace.matcher(attribute.getKey()).replaceAll("");
                if (attrKeyValid.matcher(key).matches()) {
                    el.setAttribute(key, attribute.getValue());
                }
            }
        }

        private String updateNamespaces(Element el) {
            String prefix;
            Attributes attributes = el.attributes();
            for (Attribute attr : attributes) {
                String key = attr.getKey();
                if (key.equals(xmlnsKey)) {
                    prefix = "";
                } else if (key.startsWith(xmlnsPrefix)) {
                    prefix = key.substring(xmlnsPrefix.length());
                }
                this.namespacesStack.peek().put(prefix, attr.getValue());
            }
            int pos = el.tagName().indexOf(58);
            return pos > 0 ? el.tagName().substring(0, pos) : "";
        }
    }
}
