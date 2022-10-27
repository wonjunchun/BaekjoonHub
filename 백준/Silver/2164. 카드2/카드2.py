from collections import deque

def card(N):
    card_list = deque([n for n in range(1, N+1)])
    count = 0
    result = 0
    while card_list:
        current_card = card_list.popleft()
        if not card_list: #마지막 장을 뺐을때,
            result = current_card
        if count % 2 == 0: # 버리기만 하는 경우
            count += 1
            continue
        else: # 맨윗장 빼서 맨아래 넣는 경우
            card_list.append(current_card)
            count += 1
            continue
    return result

N = int(input())
print(card(N))