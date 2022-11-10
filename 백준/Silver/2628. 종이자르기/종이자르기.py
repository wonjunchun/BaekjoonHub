N, M = map(int, input().split())
line = int(input())
horizontal_list = [N] #가로 리스트
vertical_list = [M] #세로 리스트
for l in range(line):
    #가로세로, 좌표
    hv, coordinate = map(int, input().split())
    if hv == 0: #가로로 자르는 점선이면(세로좌표이면)
        vertical_list.append(coordinate)
    elif hv == 1: #세로로 자르는 점선이면(가로좌표이면)
        horizontal_list.append(coordinate)
horizontal_list.sort()
vertical_list.sort()

max_h = 0
max_v = 0
prev_h = 0
prev_v = 0
for h in horizontal_list:
    if h-prev_h > max_h: #현재 간격이 max_h보다 크면
        max_h = h-prev_h
    prev_h = h
for v in vertical_list:
    if v-prev_v > max_v:
        max_v = v-prev_v
    prev_v = v
print(max_h * max_v)