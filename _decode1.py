import struct, itertools, collections
d = open(r"D:\软件开发\mw_reverse\chunk1.luac","rb").read()
CS, NC, NK = 33, 4270, 1284
words = [d[CS+4*i:CS+4*i+4] for i in range(NC)]

OPS = ["MOVE","LOADK","LOADBOOL","LOADNIL","GETUPVAL","GETGLOBAL","GETTABLE","SETGLOBAL",
       "SETUPVAL","SETTABLE","NEWTABLE","SELF","ADD","SUB","MUL","DIV","MOD","POW","UNM",
       "NOT","LEN","CONCAT","JMP","EQ","LT","LE","TEST","TESTSET","CALL","TAILCALL",
       "RETURN","FORLOOP","FORPREP","TFORLOOP","SETLIST","CLOSE","CLOSURE","VARARG"]
IDX = {"MOVE":0,"LOADK":1,"LOADBOOL":2,"LOADNIL":3,"GETUPVAL":4,"GETGLOBAL":5,"GETTABLE":6,
       "SETGLOBAL":7,"SETUPVAL":8,"SETTABLE":9,"NEWTABLE":10,"SELF":11,"JMP":22,"EQ":23,
       "LT":24,"LE":25,"TEST":26,"TESTSET":27,"CALL":28,"TAILCALL":29,"RETURN":30,
       "FORLOOP":31,"FORPREP":32,"TFORLOOP":33,"SETLIST":34,"CLOSE":35,"CLOSURE":36,"VARARG":37}

def decode(w_):
    v = int.from_bytes(w_, "little")
    op = v & 0x3F
    Bx = v >> 14
    sBx = Bx - 131071
    return op, v, Bx, sBx

def score(decoded_ops_v):
    """给一个 (op, v) 序列打分：用标准 5.1 语义做结构约束校验。"""
    if any(o > 37 for o in decoded_ops_v[:,0]):
        return -1, {}
    n = len(decoded_ops_v)
    ops = decoded_ops_v[:,0]; vs = decoded_ops_v[:,1]
    stat = collections.Counter()
    # 约束1：LOADK / GETGLOBAL / SETGLOBAL / GETUPVAL 的 Bx 必须 < nk
    idx_ops = {1,5,7}
    tot = 0; ok = 0
    for i in range(n):
        if ops[i] in idx_ops:
            tot += 1
            if (int(vs[i]) >> 14) < NK: ok += 1
    if tot: stat["idx_ok"] = ok/tot
    # 约束2：JMP 目标必须落在范围内
    tot = 0; ok = 0
    for i in range(n):
        if ops[i] == 22:
            tot += 1
            tgt = i + 1 + ((int(vs[i]) >> 14) - 131071)
            if 0 <= tgt < n: ok += 1
    if tot: stat["jmp_ok"] = ok/tot
    # 约束3：CLOSURE 的 Bx 必须 < 函数总数（估计 < 3000）
    tot = 0; ok = 0
    for i in range(n):
        if ops[i] == 36:
            tot += 1
            if (int(vs[i]) >> 14) < 3000: ok += 1
    if tot: stat["cls_ok"] = ok/tot
    # 约束4：RETURN 数量合理（>=1, <= 80）
    ret = int((ops == 30).sum())
    stat["return"] = ret
    # 约束5：CALL 的 A 必须 < 200（寄存器上限）
    tot = 0; ok = 0
    for i in range(n):
        if ops[i] in (28,29):
            tot += 1
            if ((int(vs[i]) >> 6) & 0xFF) < 160: ok += 1
    if tot: stat["call_a_ok"] = ok/tot
    s = 0.0
    s += 3.0 * stat.get("idx_ok", 0)
    s += 3.0 * stat.get("jmp_ok", 0)
    s += 1.0 * stat.get("cls_ok", 0)
    s += 1.5 * stat.get("call_a_ok", 0)
    if 1 <= ret <= 80: s += 2.0
    return s, stat

results = []
for perm in itertools.permutations(range(4)):
    permuted = [bytes(w[i] for i in perm) for w in words]
    arr = [[o, v] for o, v, _b, _s in (decode(w) for w in permuted)]
    import numpy as np
    a = np.array(arr, dtype=np.int64)
    s, st = score(a)
    if s > 0:
        results.append((s, perm, st))

results.sort(key=lambda x: -x[0])
print("=== 字节置换最优 5 组 ===")
for s, perm, st in results[:5]:
    print(f"  perm={perm} score={s:.2f} {st}")

# 额外：标准 LE + opcode XOR 掩码
import numpy as np
best = []
for m in range(64):
    arr = []
    for w in words:
        v = int.from_bytes(w, "little")
        raw = v & 0x3F
        op = raw ^ m
        v2 = (v & ~0x3F) | op
        arr.append([op, v2])
    a = np.array(arr, dtype=np.int64)
    s, st = score(a)
    if s > 0:
        best.append((s, m, st))
best.sort(key=lambda x: -x[0])
print("\n=== 标准LE + opcode XOR 掩码最优 5 组 ===")
for s, m, st in best[:5]:
    print(f"  mask=0x{m:02X} score={s:.2f} {st}")
