from collections import deque
import sys

def find_city(N, M, K, X, conn_matrix):
    # X: 시작점
    inf = 1000000
    dist = [0 for n in range(N+1)] #각 노드의 최단거리 정보 담는 배열
    visited = [0 for n in range(N+1)]
    #BFS
    queue = deque([X])
    visited[X] = 1
    dist[X] = 0
    result = []
    while queue:
        current_node = queue.popleft()
        for n in conn_matrix[current_node]:
            if visited[n] == 0:
                visited[n] = 1
                queue.append(n)
                dist[n] = dist[current_node] + 1 #n이 현재 노드와 연결된 노드이므로
                if dist[n] == K: #n 노드가 최단거리가 K인 도시이면
                    result.append(n)
    if not result:
        print(-1)
    else:
        result.sort()
        for r in result:
            print(r)

input = sys.stdin.readline
N, M, K, X = map(int, input().split())
conn_matrix = [[] for n in range(N+1)]
for m in range(M):
    src, dst = map(int, input().split())
    conn_matrix[src].append(dst) #src 노드에 연결된 노드들 전부 conn_matrix에 추가함

find_city(N, M, K, X, conn_matrix)