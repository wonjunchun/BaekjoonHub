N, M = map(int, input().split())
s1 = set([])
s2 = set([])
for n in range(N):
    s1.add(input())
for m in range(M):
    s2.add(input())
result = list(s1 & s2)
result.sort()
print(len(result))
for r in result:
    print(r)