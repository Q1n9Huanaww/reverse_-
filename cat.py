import json, re, collections
u = json.load(open(r"D:\软件开发\mw_reverse\strings.json",encoding='utf-8'))
cj  = [t for c,t in u if re.search(r"[\u4e00-\u9fff]", t)]
asc = [t for c,t in u if re.fullmatch(r"[A-Za-z0-9_\.\-/:@#%\$\{\}\[\]]+", t) and len(t)>1]
img = [t for c,t in u if re.search(r"\.(png|jpg|jpeg|bmp|tpl|dat)$", t, re.I)]
url = [t for c,t in u if re.search(r"(https?://|www\.|\.top|\.com|\.cn)", t)]
print("cjk",len(cj),"ascii-ish",len(asc),"img",len(img),"url",len(url))
print("=== urls ===")
for t in sorted(set(url))[:40]: print("  ",t[:140])
print("=== image files ===")
for t in sorted(set(img))[:80]: print("  ",t[:100])
print("=== ascii sample (alpha names) ===")
alpha=[t for t in asc if re.fullmatch(r"[A-Za-z_][A-Za-z0-9_]*", t)]
print(len(alpha))
print(", ".join(sorted(set(alpha))[:400]))
