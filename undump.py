import struct, json, sys
p = r"D:\软件开发\mw_reverse\chunk1.luac"
d = open(p,'rb').read()
pos = 0
def rd(n):
    global pos
    b = d[pos:pos+n]; pos += n; return b
sig = rd(4); ver = rd(1)[0]; fmt = rd(1)[0]; endian = rd(1)[0]
si, ss, sn, sl = rd(1)[0], rd(1)[0], rd(1)[0], rd(1)[0]
integ = rd(1)[0]; tail = rd(6)
print("sig",sig,"ver",hex(ver),"fmt",fmt,"endian",endian,"int",si,"size_t",ss,"ins",sn,"num",sl,"integ",integ,"tail",tail.hex())
strings=[]
funcs=[]
def read_string():
    n = struct.unpack('<I', rd(4))[0]
    if n == 0: return None
    return rd(n)[:-1].decode('utf-8','replace')
def read_func(depth):
    src = read_string()
    ld, lld = struct.unpack('<II', rd(8))
    nups, nparams, vararg, maxstack = rd(1)[0], rd(1)[0], rd(1)[0], rd(1)[0]
    ncode = struct.unpack('<I', rd(4))[0]
    code = struct.unpack('<%dI'%ncode, rd(4*ncode)) if ncode else ()
    nk = struct.unpack('<I', rd(4))[0]
    consts=[]
    for _ in range(nk):
        t = rd(1)[0]
        if t==0: consts.append(None)
        elif t==1: consts.append(bool(rd(1)[0]))
        elif t==3: consts.append(struct.unpack('<d', rd(8))[0])
        elif t==4:
            s = read_string(); consts.append(s)
            if s: strings.append(s)
        else: raise SystemExit("bad const type %d at %d"%(t,pos))
    np = struct.unpack('<I', rd(4))[0]
    protos=[read_func(depth+1) for _ in range(np)]
    nli = struct.unpack('<I', rd(4))[0]; rd(4*nli)
    nlv = struct.unpack('<I', rd(4))[0]
    for _ in range(nlv):
        read_string(); struct.unpack('<II', rd(8))
    nuv = struct.unpack('<I', rd(4))[0]
    for _ in range(nuv): read_string()
    funcs.append(dict(src=src, ld=ld, lld=lld, ncode=ncode, k=len(consts), np=np))
    return dict(src=src, code=code, consts=consts, protos=protos, nups=nups, nparams=nparams, maxstack=maxstack)
main = read_func(0)
print("top-level funcs:", len(funcs), "strings:", len(strings), "consumed", pos, "of", len(d))
json.dump(dict(strings=strings, funcs=funcs), open(r"D:\软件开发\mw_reverse\chunk1.meta.json",'w',encoding='utf-8'), ensure_ascii=False)
print("---- sample strings ----")
for s in strings[:30]: print("  |", s[:100].replace("\n","\\n"))
