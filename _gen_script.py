import sys, json, struct
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

# CLOSURE 后面紧跟 SETTABUP/SETTABLE 且键是字符串常量 → 该 proto 的名字
names={}
for i,fn in enumerate(funcs):
    cs = consts_of(fn); ins = ins_of(fn)
    for k, w in enumerate(ins):
        dd = decode_word(w)
        if dd['name']!='CLOSURE': continue
        # 找后续 3 条内的赋值
        for j in range(k+1, min(k+4, len(ins))):
            w2 = decode_word(ins[j])
            if w2['name'] in ('SETTABUP','SETTABLE'):
                key = w2['B'] if w2['name']=='SETTABUP' else w2['B']
                if key >= 256:
                    idx = key-256
                    if 0 <= idx < len(cs) and isinstance(cs[idx], str) and cs[idx]:
                        names[dd['Bx']] = cs[idx]
                break
print(f"推断出 {len(names)} 个函数名")
print("样例:", list(names.items())[:15])
json.dump(names, open(r"D:\软件开发\mw_reverse\_names.json","w"), ensure_ascii=False)

# 生成完整重建脚本
import io
out = io.StringIO()
out.write("-- 由 script.lc 反编译重建（940 个函数，opcode 映射见 REVERSE_FINDINGS.md）\n")
out.write("-- 语法为伪 Lua：寄存器形式，便于对照原逻辑\n\n")
for i,fn in enumerate(funcs):
    cs = consts_of(fn); ins = ins_of(fn)
    nm = names.get(i, f"func_{i}")
    out.write(f"-- ===== [{i}] {nm}  (line {fn['ld']}-{fn['lld']}, {fn['ncode']} 指令, {fn['nk']} 常量) =====\n")
    for line in disasm(ins, cs):
        out.write("  " + line + "\n")
    out.write("\n")
open(r"D:\软件开发\mw_reverse\RECONSTRUCTED_SCRIPT.lua","w",encoding="utf-8").write(out.getvalue())
import os
print("RECONSTRUCTED_SCRIPT.lua 大小", round(os.path.getsize(r"D:\软件开发\mw_reverse\RECONSTRUCTED_SCRIPT.lua")/1024,1), "KB")
