package com.androidquery.util;

import android.util.Xml;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class XmlDom {
    private Element root;

    public Element getElement() {
        return this.root;
    }

    public XmlDom(Element element) {
        this.root = element;
    }

    public XmlDom(String str) throws SAXException {
        this(str.getBytes());
    }

    public XmlDom(byte[] bArr) throws SAXException {
        this(new ByteArrayInputStream(bArr));
    }

    public XmlDom(InputStream inputStream) throws SAXException {
        try {
            this.root = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream).getDocumentElement();
        } catch (IOException e) {
            throw new SAXException(e);
        } catch (ParserConfigurationException unused) {
        }
    }

    public XmlDom tag(String str) {
        NodeList elementsByTagName = this.root.getElementsByTagName(str);
        if (elementsByTagName == null || elementsByTagName.getLength() <= 0) {
            return null;
        }
        return new XmlDom((Element) elementsByTagName.item(0));
    }

    public XmlDom tag(String str, String str2, String str3) {
        List<XmlDom> listTags = tags(str, str2, str3);
        if (listTags.size() == 0) {
            return null;
        }
        return listTags.get(0);
    }

    public List<XmlDom> tags(String str) {
        return tags(str, null, null);
    }

    public XmlDom child(String str) {
        return child(str, null, null);
    }

    public XmlDom child(String str, String str2, String str3) {
        List<XmlDom> listChildren = children(str, str2, str3);
        if (listChildren.size() == 0) {
            return null;
        }
        return listChildren.get(0);
    }

    public List<XmlDom> children(String str) {
        return children(str, null, null);
    }

    public List<XmlDom> children(String str, String str2, String str3) {
        return convert(this.root.getChildNodes(), str, str2, str3);
    }

    public List<XmlDom> tags(String str, String str2, String str3) {
        return convert(this.root.getElementsByTagName(str), (String) null, str2, str3);
    }

    private static List<XmlDom> convert(NodeList nodeList, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < nodeList.getLength(); i++) {
            XmlDom xmlDomConvert = convert(nodeList.item(i), str, str2, str3);
            if (xmlDomConvert != null) {
                arrayList.add(xmlDomConvert);
            }
        }
        return arrayList;
    }

    private static XmlDom convert(Node node, String str, String str2, String str3) {
        if (node.getNodeType() != 1) {
            return null;
        }
        Element element = (Element) node;
        if (str != null && !str.equals(element.getTagName())) {
            return null;
        }
        if (str2 != null && !element.hasAttribute(str2)) {
            return null;
        }
        if (str3 == null || str3.equals(element.getAttribute(str2))) {
            return new XmlDom(element);
        }
        return null;
    }

    public String text(String str) {
        XmlDom xmlDomChild = child(str);
        if (xmlDomChild == null) {
            return null;
        }
        return xmlDomChild.text();
    }

    public String attr(String str) {
        return this.root.getAttribute(str);
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        return serialize(this.root, i);
    }

    private String serialize(Element element, int i) {
        String str;
        try {
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            StringWriter stringWriter = new StringWriter();
            xmlSerializerNewSerializer.setOutput(stringWriter);
            xmlSerializerNewSerializer.startDocument("utf-8", null);
            if (i > 0) {
                char[] cArr = new char[i];
                Arrays.fill(cArr, ' ');
                str = new String(cArr);
            } else {
                str = null;
            }
            serialize(this.root, xmlSerializerNewSerializer, 0, str);
            xmlSerializerNewSerializer.endDocument();
            return stringWriter.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private void writeSpace(XmlSerializer xmlSerializer, int i, String str) throws Exception {
        if (str != null) {
            xmlSerializer.text("\n");
            for (int i2 = 0; i2 < i; i2++) {
                xmlSerializer.text(str);
            }
        }
    }

    public String text() {
        NodeList childNodes = this.root.getChildNodes();
        if (childNodes.getLength() == 1) {
            return childNodes.item(0).getNodeValue();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < childNodes.getLength(); i++) {
            sb.append(text(childNodes.item(i)));
        }
        return sb.toString();
    }

    private String text(Node node) {
        String nodeValue;
        switch (node.getNodeType()) {
            case 3:
                nodeValue = node.getNodeValue();
                if (nodeValue != null) {
                    nodeValue = nodeValue.trim();
                }
                break;
            case 4:
                nodeValue = node.getNodeValue();
                break;
            default:
                nodeValue = null;
                break;
        }
        return nodeValue == null ? "" : nodeValue;
    }

    private void serialize(Element element, XmlSerializer xmlSerializer, int i, String str) throws Exception {
        String tagName = element.getTagName();
        writeSpace(xmlSerializer, i, str);
        xmlSerializer.startTag("", tagName);
        if (element.hasAttributes()) {
            NamedNodeMap attributes = element.getAttributes();
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                Attr attr = (Attr) attributes.item(i2);
                xmlSerializer.attribute("", attr.getName(), attr.getValue());
            }
        }
        if (element.hasChildNodes()) {
            NodeList childNodes = element.getChildNodes();
            int i3 = 0;
            for (int i4 = 0; i4 < childNodes.getLength(); i4++) {
                Node nodeItem = childNodes.item(i4);
                short nodeType = nodeItem.getNodeType();
                if (nodeType == 1) {
                    serialize((Element) nodeItem, xmlSerializer, i + 1, str);
                    i3++;
                } else {
                    switch (nodeType) {
                        case 3:
                            xmlSerializer.text(text(nodeItem));
                            break;
                        case 4:
                            xmlSerializer.cdsect(text(nodeItem));
                            break;
                    }
                }
            }
            if (i3 > 0) {
                writeSpace(xmlSerializer, i, str);
            }
        }
        xmlSerializer.endTag("", tagName);
    }
}
