import sys, json, struct, io, re, collections
sys.path.insert(0, r"D:\软件开发\mw_reverse")
from decompiler import disasm, decode_word
d = open(r"D:\软件开发\mw_reverse\chunk1.luac","rb").read()
funcs = json.loads(open(r"D:\软件开发\mw_reverse\_funcs.json", encoding="utf-8", errors="replace").read())
def u32(p): return struct.unpack('<I', d[p:p+4])[0]
def consts_of(fn):
    q=fn['off']+4*fn['ncode']; nk=u32(q); q+=4; out=[]
    for _ in range(nk):
        t=d[q]; q+=1
        if t==0: out.append(None)
        elif t==1: out.append(bool(d[q])); q+=1
        elif t==3: out.append(struct.unpack('<d', d[q:q+8])[0]); q+=8
        elif t==4:
            ln=u32(q); q+=4; s=d[q:q+ln]; q+=ln
            out.append(s[:-1].decode('utf-8','replace') if ln else "")
        else: break
    return out
def ins_of(fn):
    return [int.from_bytes(d[fn['off']+4*i:fn['off']+4*i+4],"little") for i in range(fn['ncode'])]

def title(cs):
    cands=[]
    for c in cs:
        if isinstance(c,str) and re.search(r"[\u4e00-\u9fff]", c) and len(c)<=24 and not c.startswith("id"):
            cands.append(c)
    return cands[0] if cands else ""

taps=[]; sleeps=[]
for i,fn in enumerate(funcs):
    cs=consts_of(fn); ins=ins_of(fn)
    for k,w in enumerate(ins):
        dd=decode_word(w)
        if dd['name']!='GETTABLE': continue
        key = dd['C']
        if key < 256: continue
        nm = cs[key-256] if key-256 < len(cs) else None
        if not isinstance(nm,str): continue
        if nm in ("Tap","TapClick","Click","LongTap"):
            nums=[]
            for j in range(k+1, min(k+5,len(ins))):
                d2=decode_word(ins[j])
                if d2['name']=='LOADK':
                    v = cs[d2['Bx']] if d2['Bx'] < len(cs) else None
                    if isinstance(v,(int,float)): nums.append(int(v))
                if d2['name']=='CALL': break
            if len(nums)>=2: taps.append((i, title(cs), nums[0], nums[1], nums[2] if len(nums)>2 else None))
        elif nm=="Sleep":
            for j in range(k+1, min(k+4,len(ins))):
                d2=decode_word(ins[j])
                if d2['name']=='LOADK':
                    v = cs[d2['Bx']] if d2['Bx'] < len(cs) else None
                    if isinstance(v,(int,float)): sleeps.append((i, title(cs), int(v)))
                    break
print(f"Tap 调用 {len(taps)} 处，Sleep 调用 {len(sleeps)} 处")
xs=[t[2] for t in taps]; ys=[t[3] for t in taps]
if taps:
    print(f"Tap 坐标范围 x {min(xs)}..{max(xs)}  y {min(ys)}..{max(ys)}")
    print("最常见的 Tap 点:", collections.Counter((t[2],t[3]) for t in taps).most_common(12))
sv=[s[2] for s in sleeps]
if sleeps:
    print(f"\nSleep 时长分布(ms) 最常见:", collections.Counter(sv).most_common(15))
json.dump({'taps':taps,'sleeps':sleeps}, open(r"D:\软件开发\mw_reverse\_params.json","w"), ensure_ascii=False)
