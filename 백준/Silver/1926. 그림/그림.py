import sys
from collections import deque

directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
def picture(N: int, M: int, canvas):
    visited = [[0 for m in range(M)] for n in range(N)]
    pic_count = 0 # 캔버스 내의 그림 개수
    max_width = 0 # 캔버스 내 그림의 최대 넓이
    for n in range(N):
        for m in range(M):
            width = 0
            if canvas[n][m] == 1 and visited[n][m] == 0:
                # 아직 방문 안한 그림 영역이면, BFS
                queue = deque([])
                queue.append([n, m])
                visited[n][m] = 1
                width += 1
                # 큐 명세 : [n, m]
                while queue:
                    pic = queue.popleft()
                    y = pic[0]
                    x = pic[1]
                    for d in directions:
                        if 0 <= y+d[0] < N and 0 <= x+d[1] < M:
                            #올바른 범위 내에 있으면
                            if canvas[y+d[0]][x+d[1]] == 1 and visited[y+d[0]][x+d[1]] == 0:
                                # 다음 좌표의 값 1이고 아직 방문하지 않았다면
                                queue.append([y+d[0], x+d[1]])
                                width += 1 #현재 그림의 넓이 ++
                                visited[y+d[0]][x+d[1]] = 1 #방문 처리
                if width > max_width: #현재 그림의 넓이가 max_width보다 넓으면
                    max_width = width
                pic_count += 1
    return pic_count, max_width

input = sys.stdin.readline
N, M = map(int, input().split())
canvas = []
for n in range(N):
    canvas.append(list(map(int, input().split())))

pic_count, max_width = picture(N, M, canvas)
print(pic_count)
print(max_width)