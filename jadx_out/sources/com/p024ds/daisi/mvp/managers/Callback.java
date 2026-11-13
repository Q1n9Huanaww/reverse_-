package com.p024ds.daisi.mvp.managers;

/* JADX INFO: loaded from: classes.dex */
public interface Callback<T> {
    void error(String str);

    void finish(T t);
}
