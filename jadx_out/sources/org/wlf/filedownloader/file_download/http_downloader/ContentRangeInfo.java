package org.wlf.filedownloader.file_download.http_downloader;

import android.text.TextUtils;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes2.dex */
public class ContentRangeInfo {
    public final String contentType;
    public final long endPos;
    public final long startPos;
    public final long totalLength;

    private ContentRangeInfo(String contentType, long startPos, long endPos, long totalLength) {
        this.contentType = contentType;
        this.startPos = startPos;
        this.endPos = endPos;
        this.totalLength = totalLength;
    }

    public static ContentRangeInfo getContentRangeInfo(String contentRangeStr) {
        String[] contentRangeStrArrayTemp;
        String[] contentRangeAndLengthArrayTemp;
        String contentType = null;
        String rangeStartPos = null;
        String rangeEndPos = null;
        String totalLength = null;
        if (!TextUtils.isEmpty(contentRangeStr) && (contentRangeStrArrayTemp = contentRangeStr.split(" ")) != null && contentRangeStrArrayTemp.length >= 2) {
            contentType = contentRangeStrArrayTemp[0];
            String contentRangeAndLength = contentRangeStrArrayTemp[1];
            if (!TextUtils.isEmpty(contentRangeAndLength) && (contentRangeAndLengthArrayTemp = contentRangeAndLength.split(InternalZipConstants.ZIP_FILE_SEPARATOR)) != null && contentRangeAndLengthArrayTemp.length >= 2) {
                String contentRanges = contentRangeAndLengthArrayTemp[0];
                String[] contentRangesArrayTemp = contentRanges.split("-");
                if (contentRangesArrayTemp != null && contentRangesArrayTemp.length >= 2) {
                    rangeStartPos = contentRangesArrayTemp[0];
                    rangeEndPos = contentRangesArrayTemp[1];
                }
                totalLength = contentRangeAndLengthArrayTemp[1];
            }
        }
        if (!TextUtils.isEmpty(contentType) && !TextUtils.isEmpty(rangeStartPos) && !TextUtils.isEmpty(rangeEndPos) && !TextUtils.isEmpty(totalLength)) {
            try {
                long startPos = Long.parseLong(rangeStartPos);
                long endPos = Long.parseLong(rangeEndPos);
                long totalLen = Long.parseLong(totalLength);
                ContentRangeInfo contentRangeInfo = new ContentRangeInfo(contentType.trim(), startPos, 1 + endPos, totalLen);
                return contentRangeInfo;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
