p = r"D:\软件开发\mw_reverse\script.dec.bin"
d = bytearray(open(p,'rb').read())
offs=[0,1522883,2789043,len(d)]
for i in range(3):
    b=bytearray(d[offs[i]:offs[i+1]])
    assert b[:4]==b"\x1bLua", (i,b[:4])
    b[4]=0x51   # patch version 5.2->5.1 for decompiler
    open(rf"D:\软件开发\mw_reverse\chunk{i+1}.luac",'wb').write(bytes(b))
    print("chunk",i+1,"len",len(b))
