package com.p024ds.daisi.util;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class FileComparator implements Comparator<File> {
    @Override // java.util.Comparator
    public int compare(File file, File file2) {
        return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
    }
}
