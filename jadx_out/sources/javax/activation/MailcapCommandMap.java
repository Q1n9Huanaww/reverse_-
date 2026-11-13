package javax.activation;

import com.sun.activation.registries.LogSupport;
import com.sun.activation.registries.MailcapFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class MailcapCommandMap extends CommandMap {
    private static final int PROG = 0;
    private static MailcapFile defDB;

    /* JADX INFO: renamed from: DB */
    private MailcapFile[] f4736DB;

    public MailcapCommandMap() throws Throwable {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(null);
        LogSupport.log("MailcapCommandMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (property != null) {
                MailcapFile mailcapFileLoadFile = loadFile(String.valueOf(property) + File.separator + ".mailcap");
                if (mailcapFileLoadFile != null) {
                    arrayList.add(mailcapFileLoadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MailcapCommandMap: load SYS");
        try {
            MailcapFile mailcapFileLoadFile2 = loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "mailcap");
            if (mailcapFileLoadFile2 != null) {
                arrayList.add(mailcapFileLoadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MailcapCommandMap: load JAR");
        loadAllResources(arrayList, "mailcap");
        LogSupport.log("MailcapCommandMap: load DEF");
        synchronized (MailcapCommandMap.class) {
            if (defDB == null) {
                defDB = loadResource("mailcap.default");
            }
        }
        if (defDB != null) {
            arrayList.add(defDB);
        }
        this.f4736DB = new MailcapFile[arrayList.size()];
        this.f4736DB = (MailcapFile[]) arrayList.toArray(this.f4736DB);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x004a A[EXC_TOP_SPLITTER, PHI: r1
  0x004a: PHI (r1v6 java.io.InputStream) = (r1v4 java.io.InputStream), (r1v5 java.io.InputStream), (r1v8 java.io.InputStream) binds: [B:29:0x006c, B:35:0x0086, B:18:0x0048] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private MailcapFile loadResource(String str) throws Throwable {
        InputStream resourceAsStream;
        InputStream inputStream = null;
        try {
            try {
                resourceAsStream = SecuritySupport.getResourceAsStream(getClass(), str);
                try {
                    if (resourceAsStream != null) {
                        MailcapFile mailcapFile = new MailcapFile(resourceAsStream);
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MailcapCommandMap: successfully loaded mailcap file: " + str);
                        }
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        return mailcapFile;
                    }
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("MailcapCommandMap: not loading mailcap file: " + str);
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
                        LogSupport.log("MailcapCommandMap: can't load " + str, e);
                    }
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                } catch (SecurityException e2) {
                    e = e2;
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("MailcapCommandMap: can't load " + str, e);
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

    private void loadAllResources(List list, String str) throws Throwable {
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
                    LogSupport.log("MailcapCommandMap: getResources");
                }
                int i2 = 0;
                while (i < systemResources.length) {
                    try {
                        URL url = systemResources[i];
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("MailcapCommandMap: URL " + url);
                        }
                        try {
                            inputStreamOpenStream = SecuritySupport.openStream(url);
                            if (inputStreamOpenStream != null) {
                                try {
                                    try {
                                        list.add(new MailcapFile(inputStreamOpenStream));
                                        try {
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MailcapCommandMap: successfully loaded mailcap file from URL: " + url);
                                            }
                                            i2 = 1;
                                        } catch (IOException e3) {
                                            e2 = e3;
                                            i2 = 1;
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MailcapCommandMap: can't load " + url, e2);
                                            }
                                            if (inputStreamOpenStream != null) {
                                            }
                                            i++;
                                        } catch (SecurityException e4) {
                                            e = e4;
                                            i2 = 1;
                                            if (LogSupport.isLoggable()) {
                                                LogSupport.log("MailcapCommandMap: can't load " + url, e);
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
                                LogSupport.log("MailcapCommandMap: not loading mailcap file from URL: " + url);
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
                            LogSupport.log("MailcapCommandMap: can't load " + str, e);
                        }
                    }
                }
                i = i2;
            }
        } catch (Exception e10) {
            e = e10;
        }
        if (i == 0) {
            if (LogSupport.isLoggable()) {
                LogSupport.log("MailcapCommandMap: !anyLoaded");
            }
            MailcapFile mailcapFileLoadResource = loadResource(InternalZipConstants.ZIP_FILE_SEPARATOR + str);
            if (mailcapFileLoadResource != null) {
                list.add(mailcapFileLoadResource);
            }
        }
    }

    private MailcapFile loadFile(String str) {
        try {
            return new MailcapFile(str);
        } catch (IOException unused) {
            return null;
        }
    }

    public MailcapCommandMap(String str) throws IOException {
        this();
        if (LogSupport.isLoggable()) {
            LogSupport.log("MailcapCommandMap: load PROG from " + str);
        }
        if (this.f4736DB[0] == null) {
            this.f4736DB[0] = new MailcapFile(str);
        }
    }

    public MailcapCommandMap(InputStream inputStream) {
        this();
        LogSupport.log("MailcapCommandMap: load PROG");
        if (this.f4736DB[0] == null) {
            try {
                this.f4736DB[0] = new MailcapFile(inputStream);
            } catch (IOException unused) {
            }
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo[] getPreferredCommands(String str) {
        ArrayList arrayList;
        Map mailcapFallbackList;
        Map mailcapList;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null && (mailcapList = this.f4736DB[i].getMailcapList(str)) != null) {
                appendPrefCmdsToList(mailcapList, arrayList);
            }
        }
        for (int i2 = 0; i2 < this.f4736DB.length; i2++) {
            if (this.f4736DB[i2] != null && (mailcapFallbackList = this.f4736DB[i2].getMailcapFallbackList(str)) != null) {
                appendPrefCmdsToList(mailcapFallbackList, arrayList);
            }
        }
        return (CommandInfo[]) arrayList.toArray(new CommandInfo[arrayList.size()]);
    }

    private void appendPrefCmdsToList(Map map, List list) {
        for (String str : map.keySet()) {
            if (!checkForVerb(list, str)) {
                list.add(new CommandInfo(str, (String) ((List) map.get(str)).get(0)));
            }
        }
    }

    private boolean checkForVerb(List list, String str) {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            if (((CommandInfo) it2.next()).getCommandName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo[] getAllCommands(String str) {
        ArrayList arrayList;
        Map mailcapFallbackList;
        Map mailcapList;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null && (mailcapList = this.f4736DB[i].getMailcapList(str)) != null) {
                appendCmdsToList(mailcapList, arrayList);
            }
        }
        for (int i2 = 0; i2 < this.f4736DB.length; i2++) {
            if (this.f4736DB[i2] != null && (mailcapFallbackList = this.f4736DB[i2].getMailcapFallbackList(str)) != null) {
                appendCmdsToList(mailcapFallbackList, arrayList);
            }
        }
        return (CommandInfo[]) arrayList.toArray(new CommandInfo[arrayList.size()]);
    }

    private void appendCmdsToList(Map map, List list) {
        for (String str : map.keySet()) {
            Iterator it2 = ((List) map.get(str)).iterator();
            while (it2.hasNext()) {
                list.add(new CommandInfo(str, (String) it2.next()));
            }
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo getCommand(String str, String str2) {
        Map mailcapFallbackList;
        List list;
        String str3;
        Map mailcapList;
        List list2;
        String str4;
        if (str != null) {
            try {
                str = str.toLowerCase(Locale.ENGLISH);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null && (mailcapList = this.f4736DB[i].getMailcapList(str)) != null && (list2 = (List) mailcapList.get(str2)) != null && (str4 = (String) list2.get(0)) != null) {
                return new CommandInfo(str2, str4);
            }
        }
        for (int i2 = 0; i2 < this.f4736DB.length; i2++) {
            if (this.f4736DB[i2] != null && (mailcapFallbackList = this.f4736DB[i2].getMailcapFallbackList(str)) != null && (list = (List) mailcapFallbackList.get(str2)) != null && (str3 = (String) list.get(0)) != null) {
                return new CommandInfo(str2, str3);
            }
        }
        return null;
    }

    public synchronized void addMailcap(String str) {
        LogSupport.log("MailcapCommandMap: add to PROG");
        if (this.f4736DB[0] == null) {
            this.f4736DB[0] = new MailcapFile();
        }
        this.f4736DB[0].appendToMailcap(str);
    }

    @Override // javax.activation.CommandMap
    public synchronized DataContentHandler createDataContentHandler(String str) {
        List list;
        DataContentHandler dataContentHandler;
        List list2;
        DataContentHandler dataContentHandler2;
        if (LogSupport.isLoggable()) {
            LogSupport.log("MailcapCommandMap: createDataContentHandler for " + str);
        }
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("  search DB #" + i);
                }
                Map mailcapList = this.f4736DB[i].getMailcapList(str);
                if (mailcapList != null && (list2 = (List) mailcapList.get("content-handler")) != null && (dataContentHandler2 = getDataContentHandler((String) list2.get(0))) != null) {
                    return dataContentHandler2;
                }
            }
        }
        for (int i2 = 0; i2 < this.f4736DB.length; i2++) {
            if (this.f4736DB[i2] != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("  search fallback DB #" + i2);
                }
                Map mailcapFallbackList = this.f4736DB[i2].getMailcapFallbackList(str);
                if (mailcapFallbackList != null && (list = (List) mailcapFallbackList.get("content-handler")) != null && (dataContentHandler = getDataContentHandler((String) list.get(0))) != null) {
                    return dataContentHandler;
                }
            }
        }
        return null;
    }

    private DataContentHandler getDataContentHandler(String str) {
        Class<?> cls;
        if (LogSupport.isLoggable()) {
            LogSupport.log("    got content-handler");
        }
        if (LogSupport.isLoggable()) {
            LogSupport.log("      class " + str);
        }
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = getClass().getClassLoader();
            }
            try {
                cls = contextClassLoader.loadClass(str);
            } catch (Exception unused) {
                cls = Class.forName(str);
            }
            if (cls != null) {
                return (DataContentHandler) cls.newInstance();
            }
            return null;
        } catch (ClassNotFoundException e) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e);
            return null;
        } catch (IllegalAccessException e2) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e2);
            return null;
        } catch (InstantiationException e3) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e3);
            return null;
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized String[] getMimeTypes() {
        ArrayList arrayList;
        String[] mimeTypes;
        arrayList = new ArrayList();
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null && (mimeTypes = this.f4736DB[i].getMimeTypes()) != null) {
                for (int i2 = 0; i2 < mimeTypes.length; i2++) {
                    if (!arrayList.contains(mimeTypes[i2])) {
                        arrayList.add(mimeTypes[i2]);
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public synchronized String[] getNativeCommands(String str) {
        ArrayList arrayList;
        String[] nativeCommands;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f4736DB.length; i++) {
            if (this.f4736DB[i] != null && (nativeCommands = this.f4736DB[i].getNativeCommands(str)) != null) {
                for (int i2 = 0; i2 < nativeCommands.length; i2++) {
                    if (!arrayList.contains(nativeCommands[i2])) {
                        arrayList.add(nativeCommands[i2]);
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
