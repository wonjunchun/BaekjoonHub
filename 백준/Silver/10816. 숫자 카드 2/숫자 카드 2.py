N = int(input())
numcard = list(map(int, input().split()))
card_count = {} #카드 개수 세는 목적의 해시맵
for n in numcard:
    if card_count.get(n) != None:
        card_count[n] += 1
    else:
        card_count[n] = 1
M = int(input())
card_list = list(map(int, input().split()))
for c in card_list:
    if card_count.get(c) != None:
        print(card_count[c], end=' ')
    else:
        print(0, end=' ')