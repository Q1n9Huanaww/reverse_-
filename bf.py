import struct
def scan(name):
    d=open(rf"D:\软件开发\mw_reverse\{name}.luac",'rb').read()
    print("====",name,len(d))
    cands=[]
    for o in range(14,60):
        for endian in ('<','>'):
            n=struct.unpack(endian+'I', d[o:o+4])[0]
            if 0 < n < 400000 and o+4+4*n+4 <= len(d):
                nk_off=o+4+4*n
                nk=struct.unpack(endian+'I', d[nk_off:nk_off+4])[0]
                if 0 < nk < 20000:
                    # check first const tag plausible
                    t=d[nk_off+4]
                    cands.append((o,endian,n,nk,t,struct.unpack(endian+'I',d[o+4+4*n+4:nk_off+8])[0] if nk_off+8<=len(d) else -1))
    for c in cands: print("   off=%d %s ncode=%d nk=%d firsttag=%d next=%d" % (c[0],'LE' if c[1]=='<' else 'BE',c[2],c[3],c[4],c[5]))
scan('chunk1'); scan('chunk3')
