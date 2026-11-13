package com.p024ds.daisi.util;

import android.content.Context;
import android.util.JsonReader;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.BestResolution;
import com.p024ds.daisi.entity.FileVersion;
import com.p024ds.daisi.entity.Script;
import com.p024ds.daisi.entity.ScriptInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class JsonUtils {
    private static final String PROPFILE_BESTRESOLUTION = "BestResolution";
    private static final String PROPFILE_BESTRESOLUTION_X = "X";
    private static final String PROPFILE_BESTRESOLUTION_Y = "Y";
    private static final String PROPFILE_CHANGEFILELIST = "ChangeFileList";
    private static final String PROPFILE_DESCRIPTION = "Description";
    private static final String PROPFILE_FILEVERSION = "FileVersion";
    private static final String PROPFILE_SCRIPTID = "id";
    private static final String PROPFILE_SCRIPTNAME = "Name";
    private static final String PROPFILE_SUFFIX_ATC = ".atc";
    private static final String PROPFILE_SUFFIX_MQ = ".mq";
    private static final String PROPFILE_SUFFIX_PROP = ".prop";
    private static final String PROPFILE_SUFFIX_RTD = ".rtd";
    private static final String PROPFILE_SUFFIX_UI = ".ui";
    private static final String PROP_FILE_APP_ID = "AppID";
    private static final String PROP_SEL_ID = "SelID";
    private static final String PROP_VERSION = "Version";
    private static final String SCRIPT_VERSION = "ScriptVersion";

    public static Script parPropJson(InputStream inputStream) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "GBK"));
        jsonReader.beginObject();
        Script script = new Script();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (strNextName.equals(PROP_FILE_APP_ID)) {
                script.setAppId(jsonReader.nextString());
            } else if (strNextName.equals(PROPFILE_BESTRESOLUTION)) {
                script.setBestResolution(parBestResolutionJson(jsonReader));
            } else if (strNextName.equals(PROPFILE_CHANGEFILELIST)) {
                script.setChangeFileList(jsonReader.nextInt());
            } else if (strNextName.equals(PROPFILE_DESCRIPTION)) {
                script.setDescription(jsonReader.nextString());
            } else if (strNextName.equals(PROPFILE_FILEVERSION)) {
                script.setFileVersion(parFileVersionJson(jsonReader));
            } else if (strNextName.equals(PROPFILE_SCRIPTNAME)) {
                script.setName(jsonReader.nextString());
            } else if (strNextName.equals("id")) {
                script.setId(jsonReader.nextString());
            } else if (strNextName.equals(PROP_SEL_ID)) {
                script.setSelId(jsonReader.nextString());
            } else if (strNextName.equals("Version")) {
                script.setVersion(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return script;
    }

    private static BestResolution parBestResolutionJson(JsonReader jsonReader) throws IOException {
        BestResolution bestResolution = new BestResolution();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (strNextName.equals(PROPFILE_BESTRESOLUTION_X)) {
                bestResolution.setX(jsonReader.nextInt());
            } else if (strNextName.equals(PROPFILE_BESTRESOLUTION_Y)) {
                bestResolution.setY(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return bestResolution;
    }

    private static FileVersion parFileVersionJson(JsonReader jsonReader) throws IOException {
        FileVersion fileVersion = new FileVersion();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (strNextName.equals(".atc")) {
                fileVersion.setAtc(jsonReader.nextInt());
            } else if (strNextName.equals(".mq")) {
                fileVersion.setMq(jsonReader.nextInt());
            } else if (strNextName.equals(".prop")) {
                fileVersion.setProp(jsonReader.nextInt());
            } else if (strNextName.equals(".ui")) {
                fileVersion.setUI(jsonReader.nextInt());
            } else if (strNextName.equals(".rtd")) {
                fileVersion.setRtd(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return fileVersion;
    }

    public static String parseScriptInfoJson(Context context, String str) {
        ScriptInfo scriptInfo = new ScriptInfo();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(context.getAssets().open(str), "GBK"));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (SCRIPT_VERSION.equals(jsonReader.nextName())) {
                    scriptInfo.setScriptVersion(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return scriptInfo.getScriptVersion();
    }

    public static String parseScriptInfoJson(File file) {
        ScriptInfo scriptInfo = new ScriptInfo();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (SCRIPT_VERSION.equals(jsonReader.nextName())) {
                    scriptInfo.setScriptVersion(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return scriptInfo.getScriptVersion();
    }
}
