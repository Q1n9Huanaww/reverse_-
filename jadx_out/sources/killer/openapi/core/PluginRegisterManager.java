package killer.openapi.core;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public class PluginRegisterManager {
    private static final ConcurrentMap<String, PluginMethod> handleMethods = new ConcurrentHashMap();

    public Object dispatcher(String uri, Object... arg) throws IllegalAccessException, InvocationTargetException {
        PluginMethod handlerMethod = handleMethods.get(uri);
        if (handlerMethod != null) {
            return handlerMethod.invoke(arg);
        }
        return null;
    }

    public void registerPlugin(PluginRegister register) {
        List<PluginHandler> pluginHandlers = new ArrayList<>();
        register.register(pluginHandlers);
        if (pluginHandlers.size() > 0) {
            registerPluginHandler(register, pluginHandlers);
        }
    }

    private void registerPluginHandler(PluginRegister register, List<PluginHandler> handlers) {
        for (PluginHandler handler : handlers) {
            Class<?> handlerClass = handler.getClass();
            if (handlerClass.getAnnotation(PluginMapper.class) != null) {
                detectHandlerMethods(register, handler);
            }
        }
    }

    private void detectHandlerMethods(PluginRegister register, PluginHandler handler) {
        Method[] methods = handler.getClass().getDeclaredMethods();
        for (Method method : methods) {
            PluginMapper router = (PluginMapper) method.getAnnotation(PluginMapper.class);
            if (router != null) {
                String mapping = getMappingForMethod(register, handler, method);
                Log.i("mapping", mapping);
                registerHandlerMethod(handler, method, mapping);
            }
        }
    }

    private void registerHandlerMethod(PluginHandler handler, Method method, String mapping) {
        PluginMethod handlerMethod = new PluginMethod(method, handler);
        handleMethods.put(mapping, handlerMethod);
    }

    private String getMappingForMethod(PluginRegister register, PluginHandler handler, Method method) {
        PluginMapper methodRouter = (PluginMapper) method.getAnnotation(PluginMapper.class);
        PluginMapper handlerRouter = (PluginMapper) handler.getClass().getAnnotation(PluginMapper.class);
        if (methodRouter == null || handlerRouter == null) {
            return null;
        }
        return String.format("/%s/%s/%s", register.getPluginName(), handlerRouter.value(), methodRouter.value());
    }
}
