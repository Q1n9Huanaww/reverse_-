package org.wlf.filedownloader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadStatusConfiguration {
    private Builder mBuilder;

    public static class Builder {
        private boolean mAutoRelease;
        private Set<String> mListenUrls;

        public Builder addListenUrl(String url) {
            if (UrlUtil.isUrl(url)) {
                if (this.mListenUrls == null) {
                    this.mListenUrls = new HashSet();
                }
                this.mListenUrls.add(url);
            }
            return this;
        }

        public Builder addListenUrls(List<String> urls) {
            List<String> needAdd = new ArrayList<>();
            for (String url : urls) {
                if (UrlUtil.isUrl(url)) {
                    needAdd.add(url);
                }
            }
            if (!CollectionUtil.isEmpty(needAdd)) {
                if (this.mListenUrls == null) {
                    this.mListenUrls = new HashSet();
                }
                this.mListenUrls.addAll(needAdd);
            }
            return this;
        }

        public Builder configAutoRelease(boolean autoRelease) {
            this.mAutoRelease = autoRelease;
            return this;
        }

        public DownloadStatusConfiguration build() {
            return new DownloadStatusConfiguration(this);
        }
    }

    private DownloadStatusConfiguration(Builder builder) {
        this.mBuilder = builder;
    }

    public Set<String> getListenUrls() {
        if (this.mBuilder != null) {
            return this.mBuilder.mListenUrls;
        }
        return null;
    }

    public boolean isAutoRelease() {
        if (this.mBuilder != null) {
            return this.mBuilder.mAutoRelease;
        }
        return false;
    }
}
