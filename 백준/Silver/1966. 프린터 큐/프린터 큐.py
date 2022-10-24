from collections import deque

T = int(input())
for t in range(T):
    N, M = map(int, input().split())
    queue = deque(list(map(int, input().split())))
    print_count = 0
    
    while queue:
        item = queue.popleft()
        if not queue: # item이 큐의 마지막 원소였을 때
            print_count += 1 #프린트
            break
        if item >= max(list(queue)): # 현재 item의 우선순위가 가장 높을 때
            if M == 0:  # 원하던 문서가 큐의 맨앞에 있을때
                print_count += 1 #프린트
                break #종료
            else: #원하던 문서가 아닐 때
                print_count += 1
                M -= 1 #앞의 문서 하나 빠졌으므로
                continue
        else: #현재 item의 우선순위가 가장높지 않을때
            if M == 0: # 원하던 문서가 큐의 맨 앞에 있을때
                queue.append(item) # 큐의 맨 뒤로 감
                M = len(list(queue))-1 # 맨 뒤에 있음을 나타냄
                continue
            else: #원하던 문서가 아닐 때
                queue.append(item)
                M -= 1 #앞의 문서 하나 빠졌으므로
                continue
    print(print_count)
