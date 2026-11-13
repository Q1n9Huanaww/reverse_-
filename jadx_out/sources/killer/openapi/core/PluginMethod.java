package killer.openapi.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class PluginMethod {
    private final PluginHandler handler;
    private final Method method;

    public PluginMethod(Method method, PluginHandler handler) {
        this.method = method;
        this.handler = handler;
    }

    public Object invoke(Object... arg) throws IllegalAccessException, InvocationTargetException {
        return this.method.invoke(this.handler, arg);
    }
}
