from collections import deque
from sys import stdin

directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
def color_weakness(N, img):
    def bfs(n, m, N, img, visited):
        color = img[n][m][0] # R, G, B
        queue = deque([])
        queue.append([n, m])
        # 큐 명세 [n, m]
        img[n][m][1] = visited #visited
        while queue:
            current = queue.popleft()
            current_n = current[0]
            current_m = current[1]

            for d in directions:
                next_n = current_n + d[0]
                next_m = current_m + d[1]
                if 0 <= next_n < N and 0 <= next_m < N and img[next_n][next_m][1] != visited and img[next_n][next_m][0] == color:
                    #좌표가 올바르고, 아직 방문하지 않았고, 색깔도 일치하면
                    queue.append([next_n, next_m])
                    img[next_n][next_m][1] = visited #visited 표시

    normal_count = 0
    color_weak_count = 0
    #색약아닌 경우
    for n in range(N):
        for m in range(N):
            if img[n][m][1] == 0: #아직 방문하지 않았으면
                bfs(n, m, N, img, 1)
                normal_count += 1
    #색약인 경우
    for n in range(N):
        for m in range(N):
            if img[n][m][0] == 'G':
                img[n][m][0] = 'R'

    for n in range(N):
        for m in range(N):
            if img[n][m][1] == 1: #아직 방문하지 않았다면
                bfs(n, m, N, img, 0)
                color_weak_count += 1
    return normal_count, color_weak_count

read = stdin.readline
N = int(read())
img = []

#img 명세 [RGB, 방문여부]
for n in range(N):
    str = read()
    img.append([[str[i], 0] for i in range(N)])

normal, color_weak = color_weakness(N, img)
print(normal, color_weak)