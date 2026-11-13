package org.apache.commons.p092io.input;

/* JADX INFO: loaded from: classes.dex */
public interface TailerListener {
    void fileNotFound();

    void fileRotated();

    void handle(Exception exc);

    void handle(String str);

    void init(Tailer tailer);
}
