from sys import stdin
from collections import deque

def startlink(F, S, G, U, D):
    # F: 총 층수, G: 스타트링크 위치, S: 시작위치
    directions = [U, -D]

    # 큐 명세 : [현재 위치, 누른 버튼 횟수]
    queue = deque([])
    queue.append([S, 0])

    ##visited를 deque로 하니 시간초과. set으로 만들기
    visited = set([S])

    while queue:
        current = queue.popleft()
        current_floor = current[0]
        current_count = current[1]
        
        if current_floor == G: #가고자 하는 층에 도착했으면, return
            return current_count
        for d in directions:
            if 1 <= current_floor + d <= F and current_floor + d not in visited:
                #올바른 범위 내의 층이면서,
                #다음 가고자하는 층이 아직 방문하지 않은 층일 때, 큐에 넣음
                queue.append([current_floor + d, current_count + 1])
                visited.add(current_floor + d)
    return "use the stairs"


#[F, S, G, U, D] = list(map(int, input().split(" ")))
#시간초과 해결용
read = stdin.readline
F, S, G, U, D = map(int, read().split(" "))
print(startlink(F, S, G, U, D))