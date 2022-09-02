def numbering(map: list, complex_num: int, i: int, j: int) -> int: # 단지내 집의 수 반환
    #pass
    if i < 0 or i > len(map)-1 or j < 0 or j > len(map)-1: # 현 위치가 올바른 index가 아닌경우, 종료
        return 0
    if map[i][j] != 1: # 0이거나, 다른 단지에 포함된 경우
        return 0
    map[i][j] = -complex_num # 음수로 마킹
    
    # 인접한 집 탐색 & 현재 집 개수 추가
    return numbering(map, complex_num, i - 1, j) + numbering(map, complex_num, i + 1, j) + numbering(map, complex_num, i, j - 1) + numbering(map, complex_num, i, j + 1) + 1


N = int(input())
map = []
for idx in range(N):
    tmp_str = input()
    tmp_lst = []
    for c in tmp_str:
        tmp_lst.append(int(c))
    map.append(tmp_lst)

complex_num = 0
house_num = []

for i in range(N):
    for j in range(N):
        if map[i][j] == 1:
            complex_num += 1
            h = numbering(map, complex_num, i, j)
            house_num.append(h)

print(complex_num)
house_num.sort()
for n in house_num:
    print(n)