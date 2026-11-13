import struct
for name in ('chunk1','chunk3'):
    d=open(rf"D:\软件开发\mw_reverse\{name}.luac",'rb').read()
    print("====",name,"len",len(d))
    for i in range(12,48):
        print(f"  off {i}: 0x{d[i]:02x}", end="")
        if (i-12)%4==3:
            o=i-3
            print(f"   [u32@{o}] LE={struct.unpack('<I',d[o:o+4])[0]:<12} BE={struct.unpack('>I',d[o:o+4])[0]}")
        else: print()
    print("  tail-16:", d[-16:].hex(' '))
