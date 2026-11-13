"""
script.lc 反编译原型：opcode 映射 + 指令解码
=================================================================
映射由结构指纹 + 已知样本（LuaAuxLib.GetTickCount / os.execute("mkdir -p "..path)）推定。
真实指令集 = Lua 5.2（40 opcode），存储编号是打乱的置换表。
"""
from __future__ import annotations

import json
import struct

# 存储 op → Lua 5.2 语义
OPMAP = {
    25: "MOVE",       # A=reg, B=reg, C=0
    26: "LOADK",      # B=0, Bx=const（Bx<nk 100%）
    14: "LOADNIL",    # A=reg, B=0, C∈{0,1}
    28: "LOADBOOL",   # A=reg, B∈{0,1}, C∈{0,1}
    29: "GETUPVAL",   # A=reg, B=upval(0..7), C=0
    31: "GETTABUP",   # A=reg, B=upval(0..6), C=RK
    32: "GETTABLE",   # A,B=reg, C=RK
    30: "SETUPVAL",   # A=reg, B=upval(0..8), C=0
    33: "SETTABUP",   # A=upval, B=RK, C=RK
    35: "SETTABLE",   # A=reg, B=RK, C=RK
    37: "SELF",       # A=reg, B=obj, C=方法名常量
    9:  "CONCAT",     # A,B,C=reg
    10: "JMP",        # sBx（跳转合法率 0.99）
    11: "EQ",         # A∈{0,1} 比较类型, B/C=RK
    12: "LT",
    13: "LE",
    23: "TEST",       # A=reg, C∈{0,1} 恒为 1
    16: "CALL",       # A=reg, B=参数+1, C=返回值+1
    17: "TAILCALL",   # A=reg, B=参数+1, C=0
    18: "RETURN",     # A=reg, B=返回值+1
    20: "FORPREP",    # A=循环reg, sBx>0
    22: "FORLOOP",    # A=循环reg, sBx<0
    24: "CLOSURE",    # Bx=proto 索引（Bx<nprotos）
    36: "VARARG",     # A=reg, B=返回值+1
    38: "VARARG?",    # 待定
    0: "ADD?", 1: "SUB?", 2: "MUL?", 3: "DIV?", 4: "MOD?", 5: "POW?",
    6: "UNM?", 8: "LEN?", 15: "NOT?", 19: "TFORCALL?", 21: "SETLIST?", 34: "CLOSE?",
}

RK_BIT = 256


def bits(x, lo, n):
    return (x >> lo) & ((1 << n) - 1)


def decode_word(w: int) -> dict:
    return {
        "op": w & 0x3F,
        "name": OPMAP.get(w & 0x3F, f"?{w & 0x3F}"),
        "A": bits(w, 6, 8),
        "C": bits(w, 14, 9),
        "B": bits(w, 23, 9),
        "Bx": bits(w, 14, 18),
        "sBx": bits(w, 14, 18) - 131071,
    }


def operand(v: int, consts) -> str:
    """RK 编码：>=256 是常量索引，否则是寄存器"""
    if v >= RK_BIT:
        i = v - RK_BIT
        if 0 <= i < len(consts):
            c = consts[i]
            if isinstance(c, str):
                return json.dumps(c, ensure_ascii=False)
            if isinstance(c, float) and c == int(c):
                return f"K{i}={int(c)}"
            return f"K{i}={c!r}"
        return f"K{i}"
    return f"R{v}"


def disasm(ins: list, consts: list) -> list:
    out = []
    for i, w in enumerate(ins):
        d = decode_word(w)
        n, a, b, c = d["name"], d["A"], d["B"], d["C"]
        if n == "LOADK":
            s = f"R{a} = {operand(d['Bx'] + RK_BIT, consts)}"
        elif n in ("GETTABUP",):
            s = f"R{a} = UpVal[{b}][{operand(c, consts)}]"
        elif n in ("SETTABUP",):
            s = f"UpVal[{a}][{operand(b, consts)}] = {operand(c, consts)}"
        elif n == "GETTABLE":
            s = f"R{a} = R{b}[{operand(c, consts)}]"
        elif n == "SETTABLE":
            s = f"R{a}[{operand(b, consts)}] = {operand(c, consts)}"
        elif n == "SELF":
            s = f"R{a+1}=R{b}; R{a}=R{b}[{operand(c, consts)}]"
        elif n == "GETUPVAL":
            s = f"R{a} = UpVal[{b}]"
        elif n == "SETUPVAL":
            s = f"UpVal[{b}] = R{a}"
        elif n == "MOVE":
            s = f"R{a} = R{b}"
        elif n == "LOADNIL":
            s = f"R{a}..R{a+b} = nil"
        elif n == "LOADBOOL":
            s = f"R{a} = {bool(b)}" + ("; pc++" if c else "")
        elif n == "CALL":
            s = f"R{a} = R{a}({max(0,b-1)} 参) -> {max(0,c-1)} 返"
        elif n == "TAILCALL":
            s = f"return R{a}({max(0,b-1)} 参)"
        elif n == "RETURN":
            s = f"return R{a}...({max(0,b-1)} 个)"
        elif n == "JMP":
            s = f"goto {i+1+d['sBx']:+d}"
        elif n in ("EQ", "LT", "LE"):
            s = f"if {operand(b, consts)} {n.lower()} {operand(c, consts)} ({'=' if a else '~='}) then pc++"
        elif n == "TEST":
            s = f"if R{a} {'truthy' if c else 'falsy'} then pc++"
        elif n == "CONCAT":
            s = f"R{a} = R{b} .. ... .. R{c}"
        elif n == "CLOSURE":
            s = f"R{a} = closure(proto{d['Bx']})"
        elif n == "VARARG":
            s = f"R{a}... = ...({max(0,b-1)} 个)"
        elif n == "FORPREP":
            s = f"prep R{a}, goto {i+1+d['sBx']:+d}"
        elif n == "FORLOOP":
            s = f"R{a} += step; if cont goto {i+1+d['sBx']:+d}"
        else:
            s = f"A={a} B={operand(b,consts)} C={operand(c,consts)}"
        out.append(f"{i:4d}  {n:<10} {s}")
    return out


def load_funcs(path: str = r"D:\软件开发\mw_reverse\_funcs.json") -> list:
    return json.loads(open(path, encoding="utf-8", errors="replace").read())


def func_ins(d: bytes, fn: dict) -> list:
    return [int.from_bytes(d[fn["off"] + 4 * i: fn["off"] + 4 * i + 4], "little")
            for i in range(fn["ncode"])]
