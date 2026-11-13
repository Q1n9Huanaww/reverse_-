import struct
d = open(r"D:\软件开发\mw_reverse\chunk1.luac",'rb').read()
print("len", len(d))
print("bytes 0..96:", d[:96].hex(' '))
pos=18
slen=struct.unpack('<I', d[pos:pos+4])[0]
print("first u32 at 18:", slen)
print("bytes 18..80 ascii:", ''.join(chr(c) if 32<=c<127 else '.' for c in d[18:80]))
# scan for plausible structure: find 4-byte ints that are small
import re
for off in range(18, 60):
    v=struct.unpack('<I', d[off:off+4])[0]
    if v<4096: print(off, v)
