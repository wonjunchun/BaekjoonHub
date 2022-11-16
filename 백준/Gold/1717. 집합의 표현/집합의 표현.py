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
    else:
        parent[b] = a

N, M = map(int, input().split())
parent = [-1 for n in range(N+1)]
for m in range(M):
    calc, a, b = map(int, input().split())
    if calc == 0: #합집합 연산일때
        union(a, b, parent)
    elif calc == 1: #같은 집합인지 찾는 연산일때
        root_a = find(a, parent)
        root_b = find(b, parent)
        if root_a == root_b: #같은 집합에 속해있다면
            print('YES')
        else:
            print('NO')