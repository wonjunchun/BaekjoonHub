from sys import stdin
input = stdin.readline

def find(x):
    if x == friends[x]:
        return x
    else:
        if arr[friends[x]] > arr[x]:
            arr[friends[x]] = arr[x]
        else:
            arr[x] = arr[friends[x]]
        friends[x] = find(friends[x])
        return friends[x]

def union(x, y):
    x = find(x)
    y = find(y)
    if arr[x] > arr[y]:
        arr[x] = arr[y]
    else:
        arr[y] = arr[x]
    if x < y:
        friends[y] = x
    else:
        friends[x] = y

N, M, k = map(int, input().split())
friends = [i for i in range(N+1)]
arr = [0] + list(map(int, input().split()))
for i in range(M):
    a, b = map(int, input().split())
    union(a, b)

tmp = set()
cnt = 0
for i in range(1,N+1):
    a = find(i)
    if a in tmp:
        continue
    else:
        tmp.add(a)
        cnt += arr[a]

if cnt <= k:
    print(cnt)
else:
    print("Oh no")