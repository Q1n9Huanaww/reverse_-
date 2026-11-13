import struct, re, json, os
src = r"D:\软件开发\mw_reverse\script.dec.bin"
d = open(src,'rb').read()
offs=[0,1522883,2789043,len(d)]
out=[]
for ci in range(3):
    b=d[offs[ci]:offs[ci+1]]
    i=0; n=len(b)
    while i < n-8:
        ln = struct.unpack('<I', b[i:i+4])[0]
        if 1 <= ln <= 2000 and i+4+ln < n:
            s = b[i+4:i+4+ln]
            if s[-1:] == b"\x00":
                body = s[:-1]
                try:
                    t = body.decode('utf-8')
                    # plausible string: printable-ish
                    bad = sum(1 for ch in t if ord(ch)<32 and ch not in "\r\n\t")
                    if bad <= 2 and len(t)>0:
                        out.append((ci, t))
                        i += 4+ln
                        continue
                except UnicodeDecodeError:
                    pass
        i += 1
print("extracted", len(out))
seen=set(); uniq=[]
for c,t in out:
    if t in seen: continue
    seen.add(t); uniq.append((c,t))
print("unique", len(uniq))
json.dump(uniq, open(r"D:\软件开发\mw_reverse\strings.json",'w',encoding='utf-8'), ensure_ascii=False)
with open(r"D:\软件开发\mw_reverse\strings.txt",'w',encoding='utf-8') as f:
    for c,t in uniq: f.write(f"[{c}] {t}\n")
# quick categorization
b64=[t for c,t in uniq if len(t)>200 and re.fullmatch(r"[A-Za-z0-9+/=]+", t)]
print("long base64-like blobs:", len(b64))
png=[t for c,t in uniq if t.startswith("iVBOR")]
print("PNG-b64:", len(png))
for t in b64[:5]: print("  blob len", len(t), t[:60])
