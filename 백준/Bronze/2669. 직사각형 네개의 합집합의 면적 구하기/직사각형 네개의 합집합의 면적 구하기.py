
grid = [[0 for i in range(100)] for j in range(100)]
sum = 0
for n in range(4):
    rect = list(map(int, input().split()))
    x1 = rect[0]
    y1 = rect[1]
    x2 = rect[2]
    y2 = rect[3]

    for x in range(x1, x2):
        for y in range(y1, y2):
            if grid[x][y] == 0: #아직 포함되지 않은 영역이면
                grid[x][y] = 1
                sum += 1

print(sum)