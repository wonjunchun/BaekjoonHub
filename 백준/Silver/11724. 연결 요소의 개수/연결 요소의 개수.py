from collections import deque
from sys import stdin

def connected_component(N, components, connected_matrix):
    mark = 1
    for current_node in range(N):
        if components[current_node] == 0: # 아직 방문하지 않았다면
            queue = deque([])
            queue.append(current_node)
            components[current_node] = mark
            while queue:
                current = queue.popleft()
                for n in range(N):
                    if connected_matrix[current][n] != 0 and components[n] == 0:
                        # n이 현재노드와 연결되어있고, 아직 방문한적이 없다면
                        queue.append(n)
                        components[n] = mark
            mark += 1
    return mark - 1

input = stdin.readline

[N, M] = list(map(int, input().split()))
components = [0 for n in range(N)]
connected_matrix = [[0 for n in range(N)] for n in range(N)]

for m in range(M):
    [v0, v1] = edge = list(map(int, input().split()))
    connected_matrix[v0-1][v1-1] = 1
    connected_matrix[v1-1][v0-1] = 1

print(connected_component(N, components, connected_matrix))
