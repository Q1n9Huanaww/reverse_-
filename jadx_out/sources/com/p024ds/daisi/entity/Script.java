package com.p024ds.daisi.entity;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class Script {
    public static final int SCRIPT_STATE_FREE = 0;
    public static final int SCRIPT_STATE_LOAD = 1;
    public static final int SCRIPT_STATE_PAUSE = 3;
    public static final int SCRIPT_STATE_PAUSE_BY_HEART_BEAT_VERIFI_ERR = 4;
    public static final int SCRIPT_STATE_RUNING = 2;
    private String appId;
    private File atcFile;
    private BestResolution bestResolution;
    private File cfgFile;
    private int changeFileList;
    private String description;
    private FileVersion fileVersion;

    /* JADX INFO: renamed from: id */
    private String f1708id;
    private File lcFile;
    private String name;
    private File propFile;
    private File rtdFile;
    private String selId;
    private String size;
    private File uiFile;
    private File uipFile;
    private String version;

    public int getChangeFileList() {
        return this.changeFileList;
    }

    public void setChangeFileList(int i) {
        this.changeFileList = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getId() {
        return this.f1708id;
    }

    public void setId(String str) {
        this.f1708id = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public File getLcFile() {
        return this.lcFile;
    }

    public void setLcFile(File file) {
        this.lcFile = file;
    }

    public File getPropFile() {
        return this.propFile;
    }

    public void setPropFile(File file) {
        this.propFile = file;
    }

    public File getAtcFile() {
        return this.atcFile;
    }

    public void setAtcFile(File file) {
        this.atcFile = file;
    }

    public File getUiFile() {
        return this.uiFile;
    }

    public void setUiFile(File file) {
        this.uiFile = file;
    }

    public File getUipFile() {
        return this.uipFile;
    }

    public void setUipFile(File file) {
        this.uipFile = file;
    }

    public File getRtdFile() {
        return this.rtdFile;
    }

    public void setRtdFile(File file) {
        this.rtdFile = file;
    }

    public FileVersion getFileVersion() {
        return this.fileVersion;
    }

    public File getCfgFile() {
        return this.cfgFile;
    }

    public void setCfgFile(File file) {
        this.cfgFile = file;
    }

    public void setFileVersion(FileVersion fileVersion) {
        this.fileVersion = fileVersion;
    }

    public BestResolution getBestResolution() {
        return this.bestResolution;
    }

    public void setBestResolution(BestResolution bestResolution) {
        this.bestResolution = bestResolution;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getSelId() {
        return this.selId;
    }

    public void setSelId(String str) {
        this.selId = str;
    }
}
