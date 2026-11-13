package org.wlf.filedownloader.file_download.http_downloader;

/* JADX INFO: loaded from: classes2.dex */
public class Range {
    public final long endPos;
    public final long startPos;

    public Range(long startPos, long endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public long getLength() {
        return this.endPos - this.startPos;
    }

    public static boolean isLegal(Range range) {
        return range != null && range.startPos >= 0 && range.endPos > 0 && range.endPos > range.startPos;
    }

    public boolean equals(Object o) {
        if (o instanceof Range) {
            Range other = (Range) o;
            if (other.startPos == this.startPos && other.endPos == this.endPos) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[" + this.startPos + "," + this.endPos + "]";
    }
}
