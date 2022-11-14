from collections import deque

directions = [[-2, -1], [-2, 1], [2, -1], [2, 1], [-1, -2], [-1, 2], [1, -2], [1, 2]]
def BFS(length: int, knight, dst) -> int:
    visited = [[0 for l in range(length)] for l in range(length)]
    queue = deque([]) # 큐 명세 [y, x, 이동한 횟수, visited]
    visited[knight[0]][knight[1]] = 1  # 방문 처리
    queue.append([knight[0], knight[1], 0, visited])
    move = -1

    while queue:
        current = queue.popleft()
        cur_y = current[0]
        cur_x = current[1]
        cur_move = current[2]
        cur_visited = current[3] #방문여부

        if cur_y == dst[0] and cur_x == dst[1]:
            #현재 좌표와 목적지 좌표가 같으면 break
            move = cur_move
            break
        else:
            for d in directions:
                if 0 <= cur_y+d[0] < length and 0 <= cur_x+d[1] < length and cur_visited[cur_y+d[0]][cur_x+d[1]] == 0:
                    #유효한 좌표이면서, 아직 방문 안한 곳이면 큐에 넣음
                    cur_visited[cur_y + d[0]][cur_x + d[1]] = 1
                    queue.append([cur_y+d[0], cur_x+d[1], cur_move+1, cur_visited])

    return move

T = int(input())
for t in range(T):
    length = int(input())
    knight = list(map(int, input().split()))
    dst = list(map(int, input().split()))
    print(BFS(length, knight, dst))
