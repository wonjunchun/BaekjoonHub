import heapq
from sys import stdin

input = stdin.readline
N = int(input())
hq = [] # heap queue

for n in range(N):
    i = int(input())
    if i == 0: # 0을 입력받으면, 배열에서 가장 작은 값 출력
        if not hq:
            print(0)
        else:
            print(heapq.heappop(hq))
    else:
        heapq.heappush(hq, i)