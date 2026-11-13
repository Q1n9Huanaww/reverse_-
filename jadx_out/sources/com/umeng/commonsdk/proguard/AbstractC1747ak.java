package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.AbstractC1747ak;
import com.umeng.commonsdk.proguard.InterfaceC1744ah;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ak */
/* JADX INFO: compiled from: TUnion.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1747ak<T extends AbstractC1747ak<?, ?>, F extends InterfaceC1744ah> implements InterfaceC1737aa<T, F> {

    /* JADX INFO: renamed from: c */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4017c = new HashMap();

    /* JADX INFO: renamed from: a */
    protected Object f4018a;

    /* JADX INFO: renamed from: b */
    protected F f4019b;

    /* JADX INFO: renamed from: a */
    protected abstract F m3816a(short s);

    /* JADX INFO: renamed from: a */
    protected abstract Object m3819a(AbstractC1762az abstractC1762az, C1757au c1757au) throws C1743ag;

    /* JADX INFO: renamed from: a */
    protected abstract Object m3820a(AbstractC1762az abstractC1762az, short s) throws C1743ag;

    /* JADX INFO: renamed from: a */
    protected abstract void m3823a(AbstractC1762az abstractC1762az) throws C1743ag;

    /* JADX INFO: renamed from: b */
    protected abstract void m3825b(F f, Object obj) throws ClassCastException;

    /* JADX INFO: renamed from: b */
    protected abstract void m3826b(AbstractC1762az abstractC1762az) throws C1743ag;

    /* JADX INFO: renamed from: c */
    protected abstract C1757au m3829c(F f);

    /* JADX INFO: renamed from: d */
    protected abstract C1768be m3831d();

    protected AbstractC1747ak() {
        this.f4019b = null;
        this.f4018a = null;
    }

    static {
        f4017c.put(AbstractC1773bj.class, new b());
        f4017c.put(AbstractC1774bk.class, new d());
    }

    protected AbstractC1747ak(F f, Object obj) {
        m3822a(f, obj);
    }

    protected AbstractC1747ak(AbstractC1747ak<T, F> abstractC1747ak) {
        if (!abstractC1747ak.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.f4019b = abstractC1747ak.f4019b;
        this.f4018a = m3811a(abstractC1747ak.f4018a);
    }

    /* JADX INFO: renamed from: a */
    private static Object m3811a(Object obj) {
        if (obj instanceof InterfaceC1737aa) {
            return ((InterfaceC1737aa) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return C1738ab.m3787d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return m3812a((List) obj);
        }
        if (obj instanceof Set) {
            return m3814a((Set) obj);
        }
        return obj instanceof Map ? m3813a((Map<Object, Object>) obj) : obj;
    }

    /* JADX INFO: renamed from: a */
    private static Map m3813a(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(m3811a(entry.getKey()), m3811a(entry.getValue()));
        }
        return map2;
    }

    /* JADX INFO: renamed from: a */
    private static Set m3814a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            hashSet.add(m3811a(it2.next()));
        }
        return hashSet;
    }

    /* JADX INFO: renamed from: a */
    private static List m3812a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(m3811a(it2.next()));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    public F m3815a() {
        return this.f4019b;
    }

    /* JADX INFO: renamed from: b */
    public Object m3824b() {
        return this.f4018a;
    }

    /* JADX INFO: renamed from: a */
    public Object m3818a(F f) {
        if (f != this.f4019b) {
            throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.f4019b);
        }
        return m3824b();
    }

    /* JADX INFO: renamed from: a */
    public Object m3817a(int i) {
        return m3818a(m3816a((short) i));
    }

    /* JADX INFO: renamed from: c */
    public boolean m3830c() {
        return this.f4019b != null;
    }

    /* JADX INFO: renamed from: b */
    public boolean m3828b(F f) {
        return this.f4019b == f;
    }

    /* JADX INFO: renamed from: b */
    public boolean m3827b(int i) {
        return m3828b(m3816a((short) i));
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4017c.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    /* JADX INFO: renamed from: a */
    public void m3822a(F f, Object obj) {
        m3825b(f, obj);
        this.f4019b = f;
        this.f4018a = obj;
    }

    /* JADX INFO: renamed from: a */
    public void m3821a(int i, Object obj) {
        m3822a(m3816a((short) i), obj);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4017c.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (m3815a() != null) {
            Object objM3824b = m3824b();
            sb.append(m3829c(m3815a()).f4082a);
            sb.append(":");
            if (objM3824b instanceof ByteBuffer) {
                C1738ab.m3782a((ByteBuffer) objM3824b, sb);
            } else {
                sb.append(objM3824b.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public final void clear() {
        this.f4019b = null;
        this.f4018a = null;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ak$b */
    /* JADX INFO: compiled from: TUnion.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ak$a */
    /* JADX INFO: compiled from: TUnion.java */
    private static class a extends AbstractC1773bj<AbstractC1747ak> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, AbstractC1747ak abstractC1747ak) throws C1743ag {
            abstractC1747ak.f4019b = null;
            abstractC1747ak.f4018a = null;
            abstractC1762az.mo3876j();
            C1757au c1757auMo3878l = abstractC1762az.mo3878l();
            abstractC1747ak.f4018a = abstractC1747ak.m3819a(abstractC1762az, c1757auMo3878l);
            if (abstractC1747ak.f4018a != null) {
                abstractC1747ak.f4019b = (F) abstractC1747ak.m3816a(c1757auMo3878l.f4084c);
            }
            abstractC1762az.mo3879m();
            abstractC1762az.mo3878l();
            abstractC1762az.mo3877k();
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, AbstractC1747ak abstractC1747ak) throws C1743ag {
            if (abstractC1747ak.m3815a() == null || abstractC1747ak.m3824b() == null) {
                throw new C1764ba("Cannot write a TUnion with no set value!");
            }
            abstractC1762az.mo3860a(abstractC1747ak.m3831d());
            abstractC1762az.mo3855a(abstractC1747ak.m3829c(abstractC1747ak.f4019b));
            abstractC1747ak.m3823a(abstractC1762az);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ak$d */
    /* JADX INFO: compiled from: TUnion.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ak$c */
    /* JADX INFO: compiled from: TUnion.java */
    private static class c extends AbstractC1774bk<AbstractC1747ak> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, AbstractC1747ak abstractC1747ak) throws C1743ag {
            abstractC1747ak.f4019b = null;
            abstractC1747ak.f4018a = null;
            short sMo3888v = abstractC1762az.mo3888v();
            abstractC1747ak.f4018a = abstractC1747ak.m3820a(abstractC1762az, sMo3888v);
            if (abstractC1747ak.f4018a != null) {
                abstractC1747ak.f4019b = (F) abstractC1747ak.m3816a(sMo3888v);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, AbstractC1747ak abstractC1747ak) throws C1743ag {
            if (abstractC1747ak.m3815a() == null || abstractC1747ak.m3824b() == null) {
                throw new C1764ba("Cannot write a TUnion with no set value!");
            }
            abstractC1762az.mo3863a(abstractC1747ak.f4019b.mo3806a());
            abstractC1747ak.m3826b(abstractC1762az);
        }
    }
}
