import sys
from collections import deque

directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]

input = sys.stdin.readline
N, M = map(int, input().split())
iceberg = []
for n in range(N):
    iceberg.append(list(map(int, input().split())))
#빙산이 분리되기 전까지 반복
year = 0
while True:
    # 1. 각 칸마다 얼마나 녹을지 계산
    melt_count = [[0 for m in range(M)] for n in range(N)]
    for n in range(N):
        for m in range(M):
            melt = 0
            if iceberg[n][m] > 0:
                for d in directions:
                    # 현재 좌표의 동서남북의 0 개수 셈
                    if 0 <= n + d[0] < N and 0 <= m+d[1] < M:
                        if iceberg[n+d[0]][m+d[1]] == 0:
                            melt += 1
            melt_count[n][m] = melt

    # 2. melt_count 만큼 빙산 녹임
    for n in range(N):
        for m in range(M):
            if iceberg[n][m] > 0 and melt_count[n][m] > 0: #빙산과 melt_count 있는 경우에
                iceberg[n][m] -= melt_count[n][m]
                if iceberg[n][m] < 0:
                    iceberg[n][m] = 0

    year += 1
    # 3. 빙산 덩어리수 체크(1개가 아니게 되면 종료하고, year 출력)
    piece = 0 #빙산 덩어리수
    visited = [[0 for m in range(M)] for n in range(N)]
    for n in range(N):
        for m in range(M):
            if iceberg[n][m] != 0 and visited[n][m] == 0:
                #빙산이 아직 있고, 방문하지 않았다면, BFS
                queue = deque([])
                queue.append([n, m])
                visited[n][m] = 1 #방문 처리
                while queue:
                    ice = queue.popleft()
                    for d in directions:
                        y = ice[0] + d[0]
                        x = ice[1] + d[1]
                        if 0 <= y < N and 0 <= x < M:
                            #현재 빙산 좌표의 상하좌우에 있는 빙산 좌표가 올바른 범위내에 있다면, 탐색
                            if iceberg[y][x] != 0 and visited[y][x] == 0:
                                queue.append([y, x])
                                visited[y][x] = 1 #방문 처리
                piece += 1
    
    if piece > 1: #쪼개지면
        break
    elif piece == 0: #다 녹을 때까지 빙산 안 쪼개지면
        year = 0
        break

print(year)
