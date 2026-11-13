package com.didi.virtualapk.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityThread;
import android.app.LoadedApk;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.os.Build;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.utils.Reflector;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class ResourcesManager {
    public static final String TAG = "VA.LoadedPlugin";
    private static Configuration mDefaultConfiguration;

    ResourcesManager() {
    }

    public static synchronized Resources createResources(Context context, String str, File file) throws Exception {
        if (Build.VERSION.SDK_INT >= 24) {
            return createResourcesForN(context, str, file);
        }
        Resources resourcesCreateResourcesSimple = createResourcesSimple(context, file.getAbsolutePath());
        hookResources(context, resourcesCreateResourcesSimple);
        return resourcesCreateResourcesSimple;
    }

    private static Resources createResourcesSimple(Context context, String str) throws Exception {
        AssetManager assets;
        Resources resources;
        Resources resources2 = context.getResources();
        Reflector reflectorMethod = Reflector.m1337on((Class<?>) AssetManager.class).method("addAssetPath", String.class);
        if (Build.VERSION.SDK_INT < 21) {
            assets = (AssetManager) AssetManager.class.newInstance();
            reflectorMethod.bind(assets);
            if (((Integer) reflectorMethod.call(context.getApplicationInfo().sourceDir)).intValue() == 0) {
                throw new RuntimeException("createResources failed, can't addAssetPath for " + context.getApplicationInfo().sourceDir);
            }
        } else {
            assets = resources2.getAssets();
            reflectorMethod.bind(assets);
        }
        if (((Integer) reflectorMethod.call(str)).intValue() == 0) {
            throw new RuntimeException("createResources failed, can't addAssetPath for " + str);
        }
        List<LoadedPlugin> allLoadedPlugins = PluginManager.getInstance(context).getAllLoadedPlugins();
        for (LoadedPlugin loadedPlugin : allLoadedPlugins) {
            if (((Integer) reflectorMethod.call(loadedPlugin.getLocation())).intValue() == 0) {
                throw new RuntimeException("createResources failed, can't addAssetPath for " + loadedPlugin.getLocation());
            }
        }
        if (!isMiUi(resources2)) {
            if (!isVivo(resources2)) {
                if (!isNubia(resources2)) {
                    if (!isNotRawResources(resources2)) {
                        resources = new Resources(assets, resources2.getDisplayMetrics(), resources2.getConfiguration());
                    } else {
                        resources = AdaptationResourcesCompat.createResources(resources2, assets);
                    }
                } else {
                    resources = NubiaResourcesCompat.createResources(resources2, assets);
                }
            } else {
                resources = VivoResourcesCompat.createResources(context, resources2, assets);
            }
        } else {
            resources = MiUiResourcesCompat.createResources(resources2, assets);
        }
        Iterator<LoadedPlugin> it2 = allLoadedPlugins.iterator();
        while (it2.hasNext()) {
            it2.next().updateResources(resources);
        }
        return resources;
    }

    public static void hookResources(Context context, Resources resources) {
        Object resourcesManager;
        if (Build.VERSION.SDK_INT >= 24) {
            return;
        }
        try {
            Reflector reflectorWith = Reflector.with(context);
            reflectorWith.field("mResources").set(resources);
            Reflector.with(reflectorWith.field("mPackageInfo").get()).field("mResources").set(resources);
            ActivityThread activityThreadCurrentActivityThread = ActivityThread.currentActivityThread();
            if (Build.VERSION.SDK_INT >= 19) {
                resourcesManager = android.app.ResourcesManager.getInstance();
            } else {
                resourcesManager = Reflector.with(activityThreadCurrentActivityThread).field("mResourcesManager").get();
            }
            Map map = (Map) Reflector.with(resourcesManager).field("mActiveResources").get();
            map.put(map.keySet().iterator().next(), new WeakReference(resources));
        } catch (Exception e) {
            Log.w("VA.LoadedPlugin", e);
        }
    }

    @TargetApi(24)
    private static Resources createResourcesForN(Context context, String str, File file) throws Exception {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String absolutePath = file.getAbsolutePath();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String str2 = applicationInfo.publicSourceDir;
        applicationInfo.splitSourceDirs = append(applicationInfo.splitSourceDirs, absolutePath);
        LoadedApk loadedApk = (LoadedApk) Reflector.with(context).field("mPackageInfo").get();
        Reflector reflectorField = Reflector.with(loadedApk).field("mSplitResDirs");
        reflectorField.set(append((String[]) reflectorField.get(), absolutePath));
        android.app.ResourcesManager resourcesManager = android.app.ResourcesManager.getInstance();
        ArrayMap arrayMap = (ArrayMap) Reflector.with(resourcesManager).field("mResourceImpls").get();
        synchronized (resourcesManager) {
            HashMap map = new HashMap();
            if (Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT != 0)) {
                ResourcesManagerCompatForP.resolveResourcesImplMap(arrayMap, map, context, loadedApk);
            } else {
                ResourcesManagerCompatForN.resolveResourcesImplMap(arrayMap, map, str2, absolutePath);
            }
            arrayMap.clear();
            arrayMap.putAll(map);
        }
        android.app.ResourcesManager.getInstance().appendLibAssetForMainAssetPath(str2, str + ".vastub");
        Resources resources = context.getResources();
        Iterator<LoadedPlugin> it2 = PluginManager.getInstance(context).getAllLoadedPlugins().iterator();
        while (it2.hasNext()) {
            it2.next().updateResources(resources);
        }
        Log.d("VA.LoadedPlugin", "createResourcesForN cost time: +" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        return resources;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] append(String[] strArr, String str) {
        if (contains(strArr, str)) {
            return strArr;
        }
        int length = (strArr != null ? strArr.length : 0) + 1;
        String[] strArr2 = new String[length];
        if (strArr != null) {
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        strArr2[length - 1] = str;
        return strArr2;
    }

    @TargetApi(19)
    private static boolean contains(String[] strArr, String str) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (Objects.equals(str2, str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMiUi(Resources resources) {
        return resources.getClass().getName().equals("android.content.res.MiuiResources");
    }

    private static boolean isVivo(Resources resources) {
        return resources.getClass().getName().equals("android.content.res.VivoResources");
    }

    private static boolean isNubia(Resources resources) {
        return resources.getClass().getName().equals("android.content.res.NubiaResources");
    }

    private static boolean isNotRawResources(Resources resources) {
        return !resources.getClass().getName().equals("android.content.res.Resources");
    }

    private static final class MiUiResourcesCompat {
        private MiUiResourcesCompat() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Resources createResources(Resources resources, AssetManager assetManager) throws Exception {
            return (Resources) Reflector.m1338on("android.content.res.MiuiResources").constructor(AssetManager.class, DisplayMetrics.class, Configuration.class).newInstance(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        }
    }

    private static final class VivoResourcesCompat {
        private VivoResourcesCompat() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Resources createResources(Context context, Resources resources, AssetManager assetManager) throws Exception {
            Reflector reflectorM1338on = Reflector.m1338on("android.content.res.VivoResources");
            Resources resources2 = (Resources) reflectorM1338on.constructor(AssetManager.class, DisplayMetrics.class, Configuration.class).newInstance(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            reflectorM1338on.method("init", String.class).callByCaller(resources2, context.getPackageName());
            reflectorM1338on.field("mThemeValues");
            reflectorM1338on.set(resources2, reflectorM1338on.get(resources));
            return resources2;
        }
    }

    private static final class NubiaResourcesCompat {
        private NubiaResourcesCompat() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Resources createResources(Resources resources, AssetManager assetManager) throws Exception {
            return (Resources) Reflector.m1338on("android.content.res.NubiaResources").constructor(AssetManager.class, DisplayMetrics.class, Configuration.class).newInstance(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        }
    }

    private static final class AdaptationResourcesCompat {
        private AdaptationResourcesCompat() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Resources createResources(Resources resources, AssetManager assetManager) throws Exception {
            try {
                return (Resources) Reflector.with(resources).constructor(AssetManager.class, DisplayMetrics.class, Configuration.class).newInstance(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            } catch (Exception unused) {
                return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            }
        }
    }

    private static final class ResourcesManagerCompatForN {
        private ResourcesManagerCompatForN() {
        }

        @TargetApi(19)
        public static void resolveResourcesImplMap(Map<ResourcesKey, WeakReference<ResourcesImpl>> map, Map<ResourcesKey, WeakReference<ResourcesImpl>> map2, String str, String str2) throws Exception {
            for (Map.Entry<ResourcesKey, WeakReference<ResourcesImpl>> entry : map.entrySet()) {
                ResourcesKey key = entry.getKey();
                if (Objects.equals(key.mResDir, str)) {
                    map2.put(new ResourcesKey(key.mResDir, ResourcesManager.append(key.mSplitResDirs, str2), key.mOverlayDirs, key.mLibDirs, key.mDisplayId, key.mOverrideConfiguration, key.mCompatInfo), entry.getValue());
                } else {
                    map2.put(key, entry.getValue());
                }
            }
        }
    }

    private static final class ResourcesManagerCompatForP {
        private ResourcesManagerCompatForP() {
        }

        @TargetApi(17)
        public static void resolveResourcesImplMap(Map<ResourcesKey, WeakReference<ResourcesImpl>> map, Map<ResourcesKey, WeakReference<ResourcesImpl>> map2, Context context, LoadedApk loadedApk) throws Exception {
            HashMap map3 = new HashMap();
            HashMap map4 = new HashMap();
            if (ResourcesManager.mDefaultConfiguration == null) {
                Configuration unused = ResourcesManager.mDefaultConfiguration = new Configuration();
            }
            map3.put(context.createConfigurationContext(ResourcesManager.mDefaultConfiguration).getResources().getImpl(), context);
            Iterator<WeakReference<Activity>> it2 = PluginManager.getInstance(context).getInstrumentation().getActivities().iterator();
            while (it2.hasNext()) {
                Activity activity = it2.next().get();
                if (activity != null) {
                    map3.put(activity.createConfigurationContext(activity.getResources().getConfiguration()).getResources().getImpl(), activity);
                }
            }
            for (Map.Entry<ResourcesKey, WeakReference<ResourcesImpl>> entry : map.entrySet()) {
                ResourcesImpl resourcesImpl = entry.getValue().get();
                if (resourcesImpl != null) {
                    map4.put(resourcesImpl, entry.getKey());
                }
                map2.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry entry2 : map3.entrySet()) {
                ResourcesKey resourcesKey = (ResourcesKey) map4.get(entry2.getKey());
                ResourcesImpl impl = ((Context) entry2.getValue()).getResources().getImpl();
                map2.put(resourcesKey, new WeakReference<>(impl));
                map2.remove(map4.get(impl));
            }
        }
    }
}
