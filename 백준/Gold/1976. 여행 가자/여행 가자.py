import sys

def find(num: int, parent):
    if parent[num] < 0:
        return num
    parent[num] = find(parent[num], parent)
    return parent[num]

def union(a: int, b: int, parent):
    a = find(a, parent)
    b = find(b, parent)
    if a == b:
        return
    parent[b] = a

input = sys.stdin.readline
N = int(input())
M = int(input())
parent = [-1 for n in range(N+1)]
for n in range(N):
    cur_connect = list(map(int, input().split()))
    for c in range(N):
        if cur_connect[c] == 1:
            union(n+1, c+1, parent)

travel_plan = list(map(int, input().split()))
root = find(travel_plan[0], parent)
is_available = True
for t in travel_plan:
    if find(t, parent) != root:
        is_available = False
if is_available:
    print('YES')
else:
    print('NO')
