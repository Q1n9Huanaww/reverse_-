package com.cyjh.mobileanjian.ipc.utils;

import android.text.TextUtils;
import android.util.JsonReader;
import com.cyjh.mqm.MiscUtilities;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UipConfigUtil {
    public static void saveToConfigFile(String str, File file) throws Throwable {
        try {
            org.apache.commons.p092io.FileUtils.writeStringToFile(file, m1060a(new MiscUtilities().LoadUIFile(str, true)));
            file.setReadable(true, false);
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1060a(String str) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            try {
                jsonReader.beginObject();
                if (jsonReader.nextName().startsWith("界面")) {
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        jsonReader.nextName();
                        m1061a(jsonReader, jSONObject);
                    }
                    jsonReader.endObject();
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return jSONObject.toString();
    }

    /* JADX INFO: renamed from: a */
    private static void m1061a(JsonReader jsonReader, JSONObject jSONObject) {
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                if (strNextName.equals("水平布局")) {
                    m1061a(jsonReader, jSONObject);
                } else {
                    boolean zNextBoolean = false;
                    int iNextInt = 0;
                    String strNextString = null;
                    if (strNextName.equals("多选框")) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String strNextName2 = jsonReader.nextName();
                            if (strNextName2.equals("名称")) {
                                strNextString = jsonReader.nextString();
                            } else if (strNextName2.equals("选中")) {
                                zNextBoolean = jsonReader.nextBoolean();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (strNextString != null) {
                            jSONObject.put(strNextString, zNextBoolean);
                        }
                    } else if (strNextName.equals("输入框")) {
                        jsonReader.beginObject();
                        String strNextString2 = null;
                        while (jsonReader.hasNext()) {
                            String strNextName3 = jsonReader.nextName();
                            if (strNextName3.equals("名称")) {
                                strNextString = jsonReader.nextString();
                            } else if (strNextName3.equals("初始文本")) {
                                strNextString2 = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (strNextString != null) {
                            jSONObject.put(strNextString, strNextString2);
                        }
                    } else if (strNextName.equals("下拉框")) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String strNextName4 = jsonReader.nextName();
                            if (strNextName4.equals("名称")) {
                                strNextString = jsonReader.nextString();
                            } else if (strNextName4.equals("初始选项")) {
                                iNextInt = jsonReader.nextInt();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (strNextString != null) {
                            jSONObject.put(strNextString, iNextInt);
                        }
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (JSONException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }
}
