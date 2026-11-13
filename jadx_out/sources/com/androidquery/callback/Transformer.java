package com.androidquery.callback;

/* JADX INFO: loaded from: classes.dex */
public interface Transformer {
    <T> T transform(String str, Class<T> cls, String str2, byte[] bArr, AjaxStatus ajaxStatus);
}
