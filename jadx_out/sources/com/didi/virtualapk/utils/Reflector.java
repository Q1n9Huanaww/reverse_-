package com.didi.virtualapk.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes.dex */
public class Reflector {
    public static final String LOG_TAG = "VA.Reflector";
    protected Object mCaller;
    protected Constructor mConstructor;
    protected Field mField;
    protected Method mMethod;
    protected Class<?> mType;

    public static class ReflectedException extends Exception {
        public ReflectedException(String str) {
            super(str);
        }

        public ReflectedException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: renamed from: on */
    public static Reflector m1338on(@NonNull String str) throws ReflectedException {
        return m1340on(str, true, Reflector.class.getClassLoader());
    }

    /* JADX INFO: renamed from: on */
    public static Reflector m1339on(@NonNull String str, boolean z) throws ReflectedException {
        return m1340on(str, z, Reflector.class.getClassLoader());
    }

    /* JADX INFO: renamed from: on */
    public static Reflector m1340on(@NonNull String str, boolean z, @Nullable ClassLoader classLoader) throws ReflectedException {
        try {
            return m1337on(Class.forName(str, z, classLoader));
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    /* JADX INFO: renamed from: on */
    public static Reflector m1337on(@NonNull Class<?> cls) {
        Reflector reflector = new Reflector();
        reflector.mType = cls;
        return reflector;
    }

    public static Reflector with(@NonNull Object obj) throws ReflectedException {
        return m1337on(obj.getClass()).bind(obj);
    }

    protected Reflector() {
    }

    public Reflector constructor(@Nullable Class<?>... clsArr) throws ReflectedException {
        try {
            this.mConstructor = this.mType.getDeclaredConstructor(clsArr);
            this.mConstructor.setAccessible(true);
            this.mField = null;
            this.mMethod = null;
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public <R> R newInstance(@Nullable Object... objArr) throws ReflectedException {
        if (this.mConstructor == null) {
            throw new ReflectedException("Constructor was null!");
        }
        try {
            return (R) this.mConstructor.newInstance(objArr);
        } catch (InvocationTargetException e) {
            throw new ReflectedException("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    protected Object checked(@Nullable Object obj) throws ReflectedException {
        if (obj == null || this.mType.isInstance(obj)) {
            return obj;
        }
        throw new ReflectedException("Caller [" + obj + "] is not a instance of type [" + this.mType + "]!");
    }

    protected void check(@Nullable Object obj, @Nullable Member member, @NonNull String str) throws ReflectedException {
        if (member == null) {
            throw new ReflectedException(str + " was null!");
        }
        if (obj == null && !Modifier.isStatic(member.getModifiers())) {
            throw new ReflectedException("Need a caller!");
        }
        checked(obj);
    }

    public Reflector bind(@Nullable Object obj) throws ReflectedException {
        this.mCaller = checked(obj);
        return this;
    }

    public Reflector unbind() {
        this.mCaller = null;
        return this;
    }

    public Reflector field(@NonNull String str) throws ReflectedException {
        try {
            this.mField = findField(str);
            this.mField.setAccessible(true);
            this.mConstructor = null;
            this.mMethod = null;
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    protected Field findField(@NonNull String str) throws NoSuchFieldException {
        try {
            return this.mType.getField(str);
        } catch (NoSuchFieldException e) {
            for (Class<?> superclass = this.mType; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    return superclass.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                }
            }
            throw e;
        }
    }

    public <R> R get() throws ReflectedException {
        return (R) get(this.mCaller);
    }

    public <R> R get(@Nullable Object obj) throws ReflectedException {
        check(obj, this.mField, "Field");
        try {
            return (R) this.mField.get(obj);
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public Reflector set(@Nullable Object obj) throws ReflectedException {
        return set(this.mCaller, obj);
    }

    public Reflector set(@Nullable Object obj, @Nullable Object obj2) throws ReflectedException {
        check(obj, this.mField, "Field");
        try {
            this.mField.set(obj, obj2);
            return this;
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public Reflector method(@NonNull String str, @Nullable Class<?>... clsArr) throws ReflectedException {
        try {
            this.mMethod = findMethod(str, clsArr);
            this.mMethod.setAccessible(true);
            this.mConstructor = null;
            this.mField = null;
            return this;
        } catch (NoSuchMethodException e) {
            throw new ReflectedException("Oops!", e);
        }
    }

    protected Method findMethod(@NonNull String str, @Nullable Class<?>... clsArr) throws NoSuchMethodException {
        try {
            return this.mType.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            for (Class<?> superclass = this.mType; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    return superclass.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                }
            }
            throw e;
        }
    }

    public <R> R call(@Nullable Object... objArr) throws ReflectedException {
        return (R) callByCaller(this.mCaller, objArr);
    }

    public <R> R callByCaller(@Nullable Object obj, @Nullable Object... objArr) throws ReflectedException {
        check(obj, this.mMethod, "Method");
        try {
            return (R) this.mMethod.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw new ReflectedException("Oops!", e.getTargetException());
        } catch (Throwable th) {
            throw new ReflectedException("Oops!", th);
        }
    }

    public static class QuietReflector extends Reflector {
        protected Throwable mIgnored;

        @Override // com.didi.virtualapk.utils.Reflector
        public /* bridge */ /* synthetic */ Reflector constructor(@Nullable Class[] clsArr) throws ReflectedException {
            return constructor((Class<?>[]) clsArr);
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public /* bridge */ /* synthetic */ Reflector method(@NonNull String str, @Nullable Class[] clsArr) throws ReflectedException {
            return method(str, (Class<?>[]) clsArr);
        }

        /* JADX INFO: renamed from: on */
        public static QuietReflector m1343on(@NonNull String str) {
            return m1345on(str, true, QuietReflector.class.getClassLoader());
        }

        /* JADX INFO: renamed from: on */
        public static QuietReflector m1344on(@NonNull String str, boolean z) {
            return m1345on(str, z, QuietReflector.class.getClassLoader());
        }

        /* JADX INFO: renamed from: on */
        public static QuietReflector m1345on(@NonNull String str, boolean z, @Nullable ClassLoader classLoader) {
            Class<?> cls;
            try {
                cls = Class.forName(str, z, classLoader);
                try {
                    return m1342on(cls, (Throwable) null);
                } catch (Throwable th) {
                    th = th;
                    return m1342on(cls, th);
                }
            } catch (Throwable th2) {
                th = th2;
                cls = null;
            }
        }

        /* JADX INFO: renamed from: on */
        public static QuietReflector m1341on(@Nullable Class<?> cls) {
            return m1342on(cls, cls == null ? new ReflectedException("Type was null!") : null);
        }

        /* JADX INFO: renamed from: on */
        private static QuietReflector m1342on(@Nullable Class<?> cls, @Nullable Throwable th) {
            QuietReflector quietReflector = new QuietReflector();
            quietReflector.mType = cls;
            quietReflector.mIgnored = th;
            return quietReflector;
        }

        public static QuietReflector with(@Nullable Object obj) {
            if (obj == null) {
                return m1341on((Class<?>) null);
            }
            return m1341on(obj.getClass()).bind(obj);
        }

        protected QuietReflector() {
        }

        public Throwable getIgnored() {
            return this.mIgnored;
        }

        protected boolean skip() {
            return skipAlways() || this.mIgnored != null;
        }

        protected boolean skipAlways() {
            return this.mType == null;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector constructor(@Nullable Class<?>... clsArr) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.constructor(clsArr);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public <R> R newInstance(@Nullable Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.newInstance(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector bind(@Nullable Object obj) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.bind(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector unbind() {
            super.unbind();
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector field(@NonNull String str) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.field(str);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public <R> R get() {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get();
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public <R> R get(@Nullable Object obj) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.get(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector set(@Nullable Object obj) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(obj);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector set(@Nullable Object obj, @Nullable Object obj2) {
            if (skip()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.set(obj, obj2);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public QuietReflector method(@NonNull String str, @Nullable Class<?>... clsArr) {
            if (skipAlways()) {
                return this;
            }
            try {
                this.mIgnored = null;
                super.method(str, clsArr);
            } catch (Throwable th) {
                this.mIgnored = th;
            }
            return this;
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public <R> R call(@Nullable Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.call(objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }

        @Override // com.didi.virtualapk.utils.Reflector
        public <R> R callByCaller(@Nullable Object obj, @Nullable Object... objArr) {
            if (skip()) {
                return null;
            }
            try {
                this.mIgnored = null;
                return (R) super.callByCaller(obj, objArr);
            } catch (Throwable th) {
                this.mIgnored = th;
                return null;
            }
        }
    }
}
