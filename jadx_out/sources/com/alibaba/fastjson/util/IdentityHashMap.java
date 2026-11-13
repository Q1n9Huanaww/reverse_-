package com.alibaba.fastjson.util;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
public class IdentityHashMap<V> {
    private final Entry<V>[] buckets;
    private final int indexMask;

    public IdentityHashMap(int tableSize) {
        this.indexMask = tableSize - 1;
        this.buckets = new Entry[tableSize];
    }

    public final V get(Type key) {
        int hash = System.identityHashCode(key);
        int bucket = hash & this.indexMask;
        for (Entry<V> entry = this.buckets[bucket]; entry != null; entry = entry.next) {
            if (key == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(Type key, V value) {
        int hash = System.identityHashCode(key);
        int bucket = hash & this.indexMask;
        for (Entry<V> entry = this.buckets[bucket]; entry != null; entry = entry.next) {
            if (key == entry.key) {
                entry.value = value;
                return true;
            }
        }
        Entry<V> entry2 = new Entry<>(key, value, hash, this.buckets[bucket]);
        this.buckets[bucket] = entry2;
        return false;
    }

    public Class findClass(String keyString) {
        for (int i = 0; i < this.buckets.length; i++) {
            Entry<V> entry = this.buckets[i];
            if (entry != null) {
                for (Entry<V> entry2 = entry; entry2 != null; entry2 = entry2.next) {
                    Type key = entry.key;
                    if (key instanceof Class) {
                        Class clazz = (Class) key;
                        String className = clazz.getName();
                        if (className.equals(keyString)) {
                            return clazz;
                        }
                    }
                }
            }
        }
        return null;
    }

    protected static final class Entry<V> {
        public final int hashCode;
        public final Type key;
        public final Entry<V> next;
        public V value;

        public Entry(Type key, V value, int hash, Entry<V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hash;
        }
    }
}
