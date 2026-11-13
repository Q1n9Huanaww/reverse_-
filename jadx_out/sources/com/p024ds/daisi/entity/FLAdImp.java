package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class FLAdImp {
    public String adPid;
    public Banner banner;

    public FLAdImp(String str) {
        this.adPid = str;
    }

    public FLAdImp(String str, Banner banner) {
        this.adPid = str;
        this.banner = banner;
    }

    public class Banner {
        public int height;
        public int width;

        public Banner(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public Banner getBanner() {
        return this.banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
