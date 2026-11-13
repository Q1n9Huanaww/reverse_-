package org.jsoup.parser;

import javax.annotation.Nullable;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

/* JADX INFO: loaded from: classes2.dex */
abstract class Token {
    TokenType type;

    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    abstract Token reset();

    private Token() {
    }

    String tokenType() {
        return getClass().getSimpleName();
    }

    static void reset(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    static final class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        String pubSysKey;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        Doctype() {
            super();
            this.name = new StringBuilder();
            this.pubSysKey = null;
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
            this.type = TokenType.Doctype;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            reset(this.name);
            this.pubSysKey = null;
            reset(this.publicIdentifier);
            reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        String getName() {
            return this.name.toString();
        }

        String getPubSysKey() {
            return this.pubSysKey;
        }

        String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    static abstract class Tag extends Token {
        private static final int MaxAttributes = 512;
        private final StringBuilder attrName;

        @Nullable
        private String attrNameS;
        private final StringBuilder attrValue;

        @Nullable
        private String attrValueS;

        @Nullable
        Attributes attributes;
        private boolean hasAttrName;
        private boolean hasAttrValue;
        private boolean hasEmptyAttrValue;

        @Nullable
        protected String normalName;
        boolean selfClosing;

        @Nullable
        protected String tagName;

        public abstract String toString();

        Tag() {
            super();
            this.attrName = new StringBuilder();
            this.hasAttrName = false;
            this.attrValue = new StringBuilder();
            this.hasAttrValue = false;
            this.hasEmptyAttrValue = false;
            this.selfClosing = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Tag reset() {
            this.tagName = null;
            this.normalName = null;
            reset(this.attrName);
            this.attrNameS = null;
            this.hasAttrName = false;
            reset(this.attrValue);
            this.attrValueS = null;
            this.hasEmptyAttrValue = false;
            this.hasAttrValue = false;
            this.selfClosing = false;
            this.attributes = null;
            return this;
        }

        final void newAttribute() {
            String value;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.hasAttrName && this.attributes.size() < 512) {
                String name = (this.attrName.length() > 0 ? this.attrName.toString() : this.attrNameS).trim();
                if (name.length() > 0) {
                    if (this.hasAttrValue) {
                        value = this.attrValue.length() > 0 ? this.attrValue.toString() : this.attrValueS;
                    } else if (this.hasEmptyAttrValue) {
                        value = "";
                    } else {
                        value = null;
                    }
                    this.attributes.add(name, value);
                }
            }
            reset(this.attrName);
            this.attrNameS = null;
            this.hasAttrName = false;
            reset(this.attrValue);
            this.attrValueS = null;
            this.hasAttrValue = false;
            this.hasEmptyAttrValue = false;
        }

        final boolean hasAttributes() {
            return this.attributes != null;
        }

        final boolean hasAttribute(String key) {
            return this.attributes != null && this.attributes.hasKey(key);
        }

        final void finaliseTag() {
            if (this.hasAttrName) {
                newAttribute();
            }
        }

        final String name() {
            Validate.isFalse(this.tagName == null || this.tagName.length() == 0);
            return this.tagName;
        }

        final String normalName() {
            return this.normalName;
        }

        final String toStringName() {
            return this.tagName != null ? this.tagName : "[unset]";
        }

        final Tag name(String name) {
            this.tagName = name;
            this.normalName = Normalizer.lowerCase(name);
            return this;
        }

        final boolean isSelfClosing() {
            return this.selfClosing;
        }

        final void appendTagName(String append) {
            String append2 = append.replace((char) 0, (char) 65533);
            if (this.tagName != null) {
                append2 = this.tagName.concat(append2);
            }
            this.tagName = append2;
            this.normalName = Normalizer.lowerCase(this.tagName);
        }

        final void appendTagName(char append) {
            appendTagName(String.valueOf(append));
        }

        final void appendAttributeName(String append) {
            String append2 = append.replace((char) 0, (char) 65533);
            ensureAttrName();
            if (this.attrName.length() == 0) {
                this.attrNameS = append2;
            } else {
                this.attrName.append(append2);
            }
        }

        final void appendAttributeName(char append) {
            ensureAttrName();
            this.attrName.append(append);
        }

        final void appendAttributeValue(String append) {
            ensureAttrValue();
            if (this.attrValue.length() == 0) {
                this.attrValueS = append;
            } else {
                this.attrValue.append(append);
            }
        }

        final void appendAttributeValue(char append) {
            ensureAttrValue();
            this.attrValue.append(append);
        }

        final void appendAttributeValue(char[] append) {
            ensureAttrValue();
            this.attrValue.append(append);
        }

        final void appendAttributeValue(int[] appendCodepoints) {
            ensureAttrValue();
            for (int codepoint : appendCodepoints) {
                this.attrValue.appendCodePoint(codepoint);
            }
        }

        final void setEmptyAttributeValue() {
            this.hasEmptyAttrValue = true;
        }

        private void ensureAttrName() {
            this.hasAttrName = true;
            if (this.attrNameS != null) {
                this.attrName.append(this.attrNameS);
                this.attrNameS = null;
            }
        }

        private void ensureAttrValue() {
            this.hasAttrValue = true;
            if (this.attrValueS != null) {
                this.attrValue.append(this.attrValueS);
                this.attrValueS = null;
            }
        }
    }

    static final class StartTag extends Tag {
        StartTag() {
            this.type = TokenType.StartTag;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token.Tag, org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.attributes = null;
            return this;
        }

        StartTag nameAttr(String name, Attributes attributes) {
            this.tagName = name;
            this.attributes = attributes;
            this.normalName = Normalizer.lowerCase(this.tagName);
            return this;
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            return (!hasAttributes() || this.attributes.size() <= 0) ? "<" + toStringName() + ">" : "<" + toStringName() + " " + this.attributes.toString() + ">";
        }
    }

    static final class EndTag extends Tag {
        EndTag() {
            this.type = TokenType.EndTag;
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            return "</" + toStringName() + ">";
        }
    }

    static final class Comment extends Token {
        boolean bogus;
        private final StringBuilder data;
        private String dataS;

        @Override // org.jsoup.parser.Token
        Token reset() {
            reset(this.data);
            this.dataS = null;
            this.bogus = false;
            return this;
        }

        Comment() {
            super();
            this.data = new StringBuilder();
            this.bogus = false;
            this.type = TokenType.Comment;
        }

        String getData() {
            return this.dataS != null ? this.dataS : this.data.toString();
        }

        final Comment append(String append) {
            ensureData();
            if (this.data.length() == 0) {
                this.dataS = append;
            } else {
                this.data.append(append);
            }
            return this;
        }

        final Comment append(char append) {
            ensureData();
            this.data.append(append);
            return this;
        }

        private void ensureData() {
            if (this.dataS != null) {
                this.data.append(this.dataS);
                this.dataS = null;
            }
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    static class Character extends Token {
        private String data;

        Character() {
            super();
            this.type = TokenType.Character;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            this.data = null;
            return this;
        }

        Character data(String data) {
            this.data = data;
            return this;
        }

        String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    static final class CData extends Character {
        CData(String data) {
            data(data);
        }

        @Override // org.jsoup.parser.Token.Character
        public String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    static final class EOF extends Token {
        EOF() {
            super();
            this.type = TokenType.EOF;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            return this;
        }

        public String toString() {
            return "";
        }
    }

    final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    final Doctype asDoctype() {
        return (Doctype) this;
    }

    final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    final StartTag asStartTag() {
        return (StartTag) this;
    }

    final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    final EndTag asEndTag() {
        return (EndTag) this;
    }

    final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    final Comment asComment() {
        return (Comment) this;
    }

    final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    final boolean isCData() {
        return this instanceof CData;
    }

    final Character asCharacter() {
        return (Character) this;
    }

    final boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}
