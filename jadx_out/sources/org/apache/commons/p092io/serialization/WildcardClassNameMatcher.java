package org.apache.commons.p092io.serialization;

import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
final class WildcardClassNameMatcher implements ClassNameMatcher {
    private final String pattern;

    public WildcardClassNameMatcher(String str) {
        this.pattern = str;
    }

    @Override // org.apache.commons.p092io.serialization.ClassNameMatcher
    public final boolean matches(String str) {
        return FilenameUtils.wildcardMatch(str, this.pattern);
    }
}
