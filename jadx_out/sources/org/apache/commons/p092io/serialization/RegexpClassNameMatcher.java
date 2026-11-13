package org.apache.commons.p092io.serialization;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String str) {
        this(Pattern.compile(str));
    }

    public RegexpClassNameMatcher(Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Null pattern");
        }
        this.pattern = pattern;
    }

    @Override // org.apache.commons.p092io.serialization.ClassNameMatcher
    public final boolean matches(String str) {
        return this.pattern.matcher(str).matches();
    }
}
