package com.p024ds.daisi.entity;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LotteryDataEntry {
    public String Code;
    public Codes Codes;
    public String Msg;

    public class Codes {
        public List<String> Code1;
        public List<String> Code2;
        public List<String> Code3;
        public List<String> Code4;
        public List<String> Code5;
        public List<String> Code6;
        public List<String> Code7;
        public List<String> Code8;

        public Codes() {
        }

        public String toString() {
            return "Codes{Code1=" + this.Code1 + ", Code2=" + this.Code2 + ", Code3=" + this.Code3 + ", Code4=" + this.Code4 + ", Code5=" + this.Code5 + ", Code6=" + this.Code6 + ", Code7=" + this.Code7 + ", Code8=" + this.Code8 + '}';
        }
    }

    public String toString() {
        return "LotteryDataEntry{Code='" + this.Code + "', Msg='" + this.Msg + "', Codes=" + this.Codes + '}';
    }

    public String getStrFromArray(int i) {
        int i2 = i - 1;
        return this.Codes.Code1.get(i2) + this.Codes.Code2.get(i2) + this.Codes.Code3.get(i2) + this.Codes.Code4.get(i2) + this.Codes.Code5.get(i2) + this.Codes.Code6.get(i2) + this.Codes.Code7.get(i2) + this.Codes.Code8.get(i2);
    }
}
