package javax.activation;

import com.sun.activation.registries.LogSupport;
import com.sun.activation.registries.MimeTypeFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class MimetypesFileTypeMap extends FileTypeMap {
    private static final int PROG = 0;
    private static MimeTypeFile defDB = null;
    private static String defaultType = "application/octet-stream";

    /* JADX INFO: renamed from: DB */
    private MimeTypeFile[] f4737DB;

    public MimetypesFileTypeMap() throws Throwable {
        Vector vector = new Vector(5);
        vector.addElement(null);
        LogSupport.log("MimetypesFileTypeMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (property != null) {
                MimeTypeFile mimeTypeFileLoadFile = loadFile(String.valueOf(property) + File.separator + ".mime.types");
                if (mimeTypeFileLoadFile != null) {
                    vector.addElement(mimeTypeFileLoadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MimetypesFileTypeMap: load SYS");
        try {
            MimeTypeFile mimeTypeFileLoadFile2 = loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "mime.types");
            if (mimeTypeFileLoadFile2 != null) {
                vector.addElement(mimeTypeFileLoadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MimetypesFileTypeMap: load JAR");
        loadAllResources(vector, "mime.types");
        LogSupport.log("MimetypesFileTypeMap: load DEF");
        synchronized (MimetypesFileTypeMap.class) {
            if (defDB == null) {
                defDB = loadResource("/mimetypes.default");
            }
        }
        if (defDB != null) {
            vector.addElement(defDB);
        }
        this.f4737DB = new MimeTypeFile[vector.size()];
        vector.copyInto(this.f4737DB);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x004a A[EXC_TOP_SPLITTER, PHI: r1
  0x004a: PHI (r1v6 java.io.InputStream) = (r1v4 java.io.InputStream), (r1v5 java.io.InputStream), (r1v8 java.io.InputStream) binds: [B:29:0x006c, B:35:0x0086, B:18:0x0048] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private MimeTypeFile loadResource(String str) throws Throwable {
        InputStream resourceAsStream;
        InputStream inputStream = null;
        try {
            try {
                resourceAsStream = SecuritySupport.getResourceAsStream(getClass(), str);
                try {
                    if (resourceAsStream != null) {
                        MimeTypeFile mimeTypeFile = new MimeTypeFile(resourceAsStream);
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MimetypesFileTypeMap: successfully loaded mime types file: " + str);
                        }
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        return mimeTypeFile;
                    }
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("MimetypesFileTypeMap: not loading mime types file: " + str);
                    }
                    if (resourceAsStream != null) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                } catch (IOException e) {
                    e = e;
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("MimetypesFileTypeMap: can't load " + str, e);
                    }
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                } catch (SecurityException e2) {
                    e = e2;
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("MimetypesFileTypeMap: can't load " + str, e);
                    }
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            resourceAsStream = null;
        } catch (SecurityException e4) {
            e = e4;
            resourceAsStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                inputStream.close();
            }
            throw th;
        }
    }

    private void loadAllResources(Vector vector, String str) throws Throwable {
        URL[] systemResources;
        InputStream inputStreamOpenStream;
        SecurityException e;
        IOException e2;
        int i = 0;
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = getClass().getClassLoader();
            }
            if (contextClassLoader != null) {
                systemResources = SecuritySupport.getResources(contextClassLoader, str);
            } else {
                systemResources = SecuritySupport.getSystemResources(str);
            }
            if (systemResources != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("MimetypesFileTypeMap: getResources");
                }
                int i2 = 0;
                while (i < systemResources.length) {
                    try {
                        URL url = systemResources[i];
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MimetypesFileTypeMap: URL " + url);
                        }
                        try {
                            inputStreamOpenStream = SecuritySupport.openStream(url);
                            if (inputStreamOpenStream != null) {
                                try {
                                    try {
                                        vector.addElement(new MimeTypeFile(inputStreamOpenStream));
                                        try {
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MimetypesFileTypeMap: successfully loaded mime types from URL: " + url);
                                            }
                                            i2 = 1;
                                        } catch (IOException e3) {
                                            e2 = e3;
                                            i2 = 1;
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MimetypesFileTypeMap: can't load " + url, e2);
                                            }
                                            if (inputStreamOpenStream != null) {
                                            }
                                            i++;
                                        } catch (SecurityException e4) {
                                            e = e4;
                                            i2 = 1;
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MimetypesFileTypeMap: can't load " + url, e);
                                            }
                                            if (inputStreamOpenStream != null) {
                                            }
                                            i++;
                                        } catch (Throwable th) {
                                            th = th;
                                            i2 = 1;
                                            if (inputStreamOpenStream != null) {
                                                try {
                                                    inputStreamOpenStream.close();
                                                } catch (IOException unused) {
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (IOException e5) {
                                    e2 = e5;
                                } catch (SecurityException e6) {
                                    e = e6;
                                }
                            } else if (LogSupport.isLoggable()) {
                                LogSupport.log("MimetypesFileTypeMap: not loading mime types from URL: " + url);
                            }
                            if (inputStreamOpenStream != null) {
                                try {
                                    inputStreamOpenStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                        } catch (IOException e7) {
                            inputStreamOpenStream = null;
                            e2 = e7;
                        } catch (SecurityException e8) {
                            inputStreamOpenStream = null;
                            e = e8;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStreamOpenStream = null;
                        }
                        i++;
                    } catch (Exception e9) {
                        e = e9;
                        i = i2;
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MimetypesFileTypeMap: can't load " + str, e);
                        }
                    }
                }
                i = i2;
            }
        } catch (Exception e10) {
            e = e10;
        }
        if (i == 0) {
            LogSupport.log("MimetypesFileTypeMap: !anyLoaded");
            MimeTypeFile mimeTypeFileLoadResource = loadResource(InternalZipConstants.ZIP_FILE_SEPARATOR + str);
            if (mimeTypeFileLoadResource != null) {
                vector.addElement(mimeTypeFileLoadResource);
            }
        }
    }

    private MimeTypeFile loadFile(String str) {
        try {
            return new MimeTypeFile(str);
        } catch (IOException unused) {
            return null;
        }
    }

    public MimetypesFileTypeMap(String str) throws IOException {
        this();
        this.f4737DB[0] = new MimeTypeFile(str);
    }

    public MimetypesFileTypeMap(InputStream inputStream) {
        this();
        try {
            this.f4737DB[0] = new MimeTypeFile(inputStream);
        } catch (IOException unused) {
        }
    }

    public synchronized void addMimeTypes(String str) {
        if (this.f4737DB[0] == null) {
            this.f4737DB[0] = new MimeTypeFile();
        }
        this.f4737DB[0].appendToRegistry(str);
    }

    @Override // javax.activation.FileTypeMap
    public String getContentType(File file) {
        return getContentType(file.getName());
    }

    @Override // javax.activation.FileTypeMap
    public synchronized String getContentType(String str) {
        String mIMETypeString;
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf < 0) {
            return defaultType;
        }
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (strSubstring.length() == 0) {
            return defaultType;
        }
        for (int i = 0; i < this.f4737DB.length; i++) {
            if (this.f4737DB[i] != null && (mIMETypeString = this.f4737DB[i].getMIMETypeString(strSubstring)) != null) {
                return mIMETypeString;
            }
        }
        return defaultType;
    }
}
