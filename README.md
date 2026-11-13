# reverse_- · 现代战舰助手 APK 逆向分析

本仓库是对 **现代战舰助手（射击者）** APK 的逆向分析产物归档，包含脚本反混淆、字节码反汇编、参数提取与功能映射。

> ⚠️ 仅用于**已获授权的自有/委托项目**的技术分析与兼容性重构。原始脚本、美术、游戏资源版权归其各自权利人所有，本仓库不包含任何游戏本体资源。

---

## 一、目标与结论速览

| 项 | 结论 |
|---|---|
| 目标 APK | `com.cyjh.mobileanjian`（按键精灵手机版）二次封装的游戏辅助 |
| 脚本载荷 | `assets/script.lc`，**XOR 0xFF** 加密 |
| 解密后格式 | **Lua 5.2 指令集**（opcode 编号被重排）+ **5.1 容器头** |
| 反汇编规模 | **940 个函数 / 1.79 MB 反汇编文本** |
| 名称反混淆 | **245 个名称**（十六进制编码的中文标识符）全部还原 |
| 参数提取 | **681 条找色参数** + **217 个 Tap 坐标** + **622 个 Sleep 时长** |
| 游戏本体 | Unity **IL2CPP**（`libil2cpp.so` 121.7 MB，arm64） |
| IL2CPP 元数据 | **已加密**（魔数 `0xEA8F915E` ≠ 标准 `0xFAB11BAF`），静态 dump 被挡住 |

---

## 二、目录结构

### 核心逆向结果（可直接阅读）

| 文件 | 大小 | 说明 |
|---|---|---|
| `DEOBFUSCATED_SCRIPT.lua` | 1.73 MB | **反混淆后的完整反汇编**，245 个中文名已还原，推荐从这里读 |
| `RECONSTRUCTED_SCRIPT.lua` | 1.79 MB | 原始反汇编（含 `ide<hex>` 编码名） |
| `FUNCTION_INDEX.md` | 42 KB | 940 个函数索引（行号 / 指令数 / 名称） |
| `FUNCTION_MAP.md` | 5.4 KB | 按功能域分类的函数名表（雷达 / 飞机 / 移动 / 武器 / 占点 …） |
| `FUNCTION_GAP.md` | 8.3 KB | 与自研实现对照后**尚未覆盖的 136 个函数** |
| `TODO_FEATURES.md` | 3.5 KB | 未覆盖功能按用途归类 |
| `REVERSE_FINDINGS.md` | 6.6 KB | **opcode 映射表**与识别范式 |
| `BYTECODE_NOTES.md` | 4.7 KB | 字节码结构笔记 |

### 参数数据

| 文件 | 说明 |
|---|---|
| `_colorpoints.json` | 681 条找色参数（坐标 + 颜色 + 相似度） |
| `_params.json` | 217 个 Tap 坐标 + 622 个 Sleep 时长 |
| `_funcs.json` | 函数元数据（原型、行号区间、指令数） |
| `strings.json` / `strings.txt` | 脚本内全部字符串 |

### 解包与中间产物

| 路径 | 说明 |
|---|---|
| `app.apk` | 原始 APK（29.6 MB） |
| `script.lc.xorff` | XOR 解密后的脚本载荷 |
| `chunk1/2/3.luac` | 拆分出的字节码块 |
| `script.uip.utf8.txt` / `uip_controls.txt` | UI 布局与控件定义 |
| `apktool_out/` | apktool 完整解包（smali + res + manifest） |
| `jadx_out/` | jadx 反编译的 Java 源码 |

### 工具脚本

| 文件 | 用途 |
|---|---|
| `decompiler.py` | Lua 字节码反汇编器（含重排 opcode 映射） |
| `_params.py` / `_extract_params.py` | 参数提取 |

---

## 三、关键技术发现

### 1. 加密与容器

```
assets/script.lc  --XOR 0xFF-->  明文字节码
明文 = Lua 5.1 容器头 + Lua 5.2 指令集（opcode 编号被置换）
操作数布局（标准 5.2）：
  A = inst[6..13]   C = inst[14..22]   B = inst[23..31]
```

### 2. 名称编码

脚本把中文标识符编码为 `id` + UTF-8 十六进制，例如：

```
ide5a4a7e8a792e5baa6e5b7a6e6bb91e58aa8
  = "id" + e5a4a7 e8a792 e5baa6 e5b7a6 e6bb91 e58aa8
  = 大 角 度 左 黑 动  → 「大角度左黑动」（大角度左滑动）
```

`DEOBFUSCATED_SCRIPT.lua` 已完成全部替换（残留 0 处）。

### 3. 核心战斗逻辑（已还原）

```lua
-- 找敌主循环
function 当前有无敌人()
    SetShareVar("当前有无敌人", False)
    一号导弹为帮助锁定()                 -- 先用导弹建立锁定
    dir = 大视角确定左右移位()            -- 返回 "L" / "R" / "N"
    if dir == "L" then SetShareVar("视角矫正","LLS"); 大角度左滑动(17) end
    if dir == "R" then SetShareVar("视角矫正","RRS"); 大角度右滑动(17) end
end

-- 方向判定：比较左右两区的「海色 / 天色」占比
function 大视角确定左右移位()
    左蓝 = GetColorNum(59,19,508,557,   "0000FF-101010", 1)
    右蓝 = GetColorNum(134,727,591,1202,"0000FF-101010", 1)
    左灰 = GetColorNum(59,19,508,557,   "DADAD9-101010", 1)
    右灰 = GetColorNum(134,727,591,1202,"DADAD9-101010", 1)
    if 右蓝 < 5 or 左蓝 < 5 then return "N" end
    if 左灰 > 3 and 右蓝 < 左蓝 then return "L" end
    if 右灰 > 3 and 左蓝 < 右蓝 then return "R" end
    return "N"
end

-- 搜索敌人中（关键：瞄到敌人就 return，不再转圈）
function 搜索敌人中()
    if 瞄准范围内是否有敌人判断() then return end   -- ← 有敌人立刻停止扫描
    TouchOperation(0, 545, 500, 3, 0)              -- 否则按住油门/摇杆
end
```

### 4. 转向实现（重要）

```lua
function 转飞机头(秒)
    if 秒 < 8 then 秒 = 8 end
    Swipe(518, 1100, 518, 1000)   -- 纯垂直滑动 100px
    while 秒 > 0 do 秒 = 秒 - 1; Sleep(400) end
end
```
原版坐标系为 **720×1280 竖屏**，需按 90° 旋转映射到横屏设备。

### 5. 坐标映射（720×1280 竖屏 → 1920×1092 横屏）

```
lx = py * 1920 / 1280
ly = (720 - px) * 1092 / 720

左采样区 (59,19)-(508,557)      → (28,321)-(835,1002)
右采样区 (134,727)-(591,1202)   → (1090,195)-(1803,888)
转向滑动 (518,1100)->(518,1000) → (1650,306)->(1500,306)
```

### 6. IL2CPP 静态分析受阻

```
libil2cpp.so        121.7 MB  arm64
global-metadata.dat  23.2 MB
魔数 0xEA8F915E ≠ 0xFAB11BAF  → 元数据已加密
内存盲扫血量（214500 / 56000 / 2145000）→ 0 命中
```

结论：要走内存读写路线，必须先逆向 `libil2cpp.so` 里的元数据解密例程。

---

## 四、注意事项

- **大文件未入库**：`il2cpp/base.apk`(2.9 GB)、`libil2cpp.so`(121.7 MB)、`global-metadata.dat`(23.2 MB)、`apktool_out/`、`jadx_out/` 均被 `.gitignore` 排除（GitHub 单文件上限 100 MB）
- 需要这些大文件请自行从原始 APK 解包
- `FUNCTION_GAP.md` 里的 136 个未覆盖函数，可作为后续重构的 checklist

---

## 五、许可

本仓库仅包含**分析产物与笔记**。原始脚本逻辑、游戏资源的知识产权归其权利人，不随本仓库授权。
