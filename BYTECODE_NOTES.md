# script.lc 字节码格式逆向笔记（第 1 轮）

> 目标：解出按键精灵对 Lua 指令字的变换，恢复原版脚本的坐标/时序/找色参数。
> 本文件记录**已验证**的事实与**待验证**的假设，逐轮更新。

## 一、容器层（已验证）

```
文件 = 22 字节前缀("0A 14" + 20 字节 "Apr  7 2022 14:09:34") + 4 字节 + XOR-0xFF 载荷
解混淆：b ^ 0xFF（0x00 保持 0x00）
载荷 = 3 个 chunk：[0, 1522883) [1522883, 2789043) [2789043, 2800711)
chunk 之间各有 8 字节分隔（疑似校验）：chunk2 前 `9a8d9e93ed125cb2`，chunk3 前 `5f454e5600c56ba4`
```

## 二、dump 头（已验证）

```
偏移 0..17: 1b 4c 75 61 | 52 | 00 | 01 | 04 04 04 08 | 00 | 19 93 0d 0a 1a 0a
            ESC"Lua"    ver  fmt  endian  sizes      integ  LUAC_TAIL
```
- `ver=0x52` 不是障眼法：**指令集是 Lua 5.2**（opcode 0..39，含 `GETTABUP`/`LOADKX`/`TFORCALL`/`EXTRAARG`）
  - 证据：指令里出现 op=38，而 5.1 只到 37
- 头是 **5.1 布局（18 字节）**，但指令集是 5.2 → 定制 fork

## 三、函数布局（已验证，与标准 Lua 不同）

```
main chunk：
  18..28  11 字节头  = ld(4) lld(4) nups(1) numparams(1) maxstack(1)
  29..32  ncode = 4270 (0x10AE)
  33..17112  code（4270×4 = 17080 字节）
  17113   nk = 1284 (0x504)
  17117   常量表（首条 = "QMPlugin"）
  616446  nprotos = 932
  616450  第一个 proto

嵌套 proto（无 source 字段！）：
  ld(4) lld(4) nups(1) numparams(1) maxstack(1) ncode(4) code(4*ncode)
  nk(4) consts  nprotos(4) children  debug(待定)
```

**关键差异（vs 标准 Lua）**：
- 没有 `source` 字段
- 没有 `is_vararg` 字段
- 头只有 11 字节（标准是 4+4+4 或含 source）

## 四、已解析的成果

| 项 | 值 |
|---|---|
| main 常量 | 1284 条，**完整解析**，含 1112 字符串 + 171 数字 |
| main 常量表结束 | 616446 |
| 主 chunk 直接子函数 | 932 个 |
| 首个 proto | ld=90 lld=90 nups=0 np=0 mss=2 **ncode=5** |
| 首个 proto 常量 | `["LuaAuxLib", "GetTickCount"]` |
| 首个 proto 指令 | `1f000400 20404000 11008000 12000000 12008000` |
| 第二个 proto | ld=91 lld=95 mss=3 ncode=13，最后一条 op=18 |

## 五、opcode 变换（部分解开）

**已确认的锚点：**

| 存储 op | 语义 | 证据 |
|---|---|---|
| **18** | **RETURN** | 每个函数最后一条；main 最后一条也是它（仅出现 1 次） |
| **24** | **CLOSURE** | 数量 932 == nprotos，且 Bx 全部落在 0..931（100%） |
| **35** | SETTABLE | B/C 达 509（RK 常量模式），A 固定，成串出现且 B,C 各 +2 步进 |
| **33** | NEWTABLE / SETLIST 候选 | A 恒为 0，B∈9..256，C∈0..41 |

**存储 op 的完整集合**（main chunk 4270 条里只出现 13 种）：

```
26(1428) 35(1027) 24(932) 33(369) 31(311) 32(90) 36(89) 28(12) 29(8) 10(1) 14(1) 9(1) 18(1)
```

**操作数字段是标准布局**（A=bits6-13, C=bits14-22, B=bits23-31）——因为 SETTABLE 序列的 B/C 以 +2 步进、A 不变，完全符合 Lua 建表模式。

## 六、已排除的假设（都实测过，别再试）

| 假设 | 搜索空间 | 结果 |
|---|---|---|
| opcode 是连续 6 位（任意偏移） | 27 位窗口 × 27 起始偏移 | 0 命中 |
| 字节置换后取 opcode | 24 置换 × 27 窗口 | 0 命中 |
| opcode 做了 XOR 掩码 | 64 种 | 最好 mask=0x03 也 MOVE=0/JMP=0 |
| 指令差分 / 累计 XOR / 累计加 | 4 种 | 0 命中 |

硬约束：非法 opcode=0、RETURN 1~60、opcode 种类≥15、JMP>0、EQ/LT/LE 必须配 JMP。

## 七、下一轮要做的

1. **定位 main chunk 里的 JMP**：13 种 op 里没有明确的 JMP（跳跃指令），但 4270 条指令必须有分支。
   用 sBx 范围检查（目标必须落在 [0, 4270)）逐个候选验证。
2. **确定 op 26 / 31 / 36 / 32 / 28 / 29 的语义**：
   26(1428) 与 31(311) 的 Bx 均 < nk，是 LOADK / GETGLOBAL / SETGLOBAL 三者之二。
   用首个 proto 的 `LuaAuxLib.GetTickCount()` 作为已知语义样本反推：
   期望序列 `GETGLOBAL R0,K0 → GETTABLE R0,R0,K1 → CALL R0,1,1 → RETURN`，
   而实际是 `op31 → op32 → op17 → op18 → op18`，可反推 op31=GETGLOBAL, op32=GETTABLE, op17=CALL。
3. **debug 段布局**：proto #1 尾到 proto #2 头之间有 31 字节未知结构，需要定出来才能批量解析 932 个函数。
4. 拿到完整语料后，用 CLOSURE 的 Bx → proto 索引建立函数树，就能逐函数反编译。

## 八、备选路径

若语义反推卡住：用 Ghidra（`D:\tools\ghidra_12.1.4_PUBLIC`）反编译 `libmqm.so`
（`precompiled chunk` 错误串在偏移 6247446）里的 undump → 直接读出 opcode 表与 LoadCode 变换。
