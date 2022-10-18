from collections import deque
def josephus(N, K):
    queue = deque([n for n in range(1, N+1)])
    result = deque([])
    for n in range(N):
        queue.rotate(-(K-1))
        result.append(queue.popleft())
    return list(result)


N, K = map(int, input().split())
j = josephus(N, K)
print('<', end='')
for n in range(N-1):
    print(j[n], end=', ')
print(j[-1], end='')
print('>')