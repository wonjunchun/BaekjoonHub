from collections import deque

def up(grid): # r = 0 방향으로 밀어줌
    score = 0 #반환할 score
    # 1. 이동방향으로 밀기
    for c in range(4):
        for r in range(3):
            if grid[r][c] == 0 and grid[r+1][c] != 0:
                grid[r][c], grid[r+1][c] = grid[r+1][c], grid[r][c] #swap
        for r in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r-1][c] == 0:
                grid[r-1][c], grid[r][c] = grid[r][c], grid[r-1][c] #swap
        # 2. 같은 타일 합치기
        for r in range(3):
            if grid[r][c] != 0 and grid[r][c] == grid[r+1][c]:
                grid[r][c] += grid[r+1][c]
                score += grid[r][c]
                grid[r+1][c] = 0
        # 3. 다시 이동방향으로 밀기
        for r in range(3):
            if grid[r][c] == 0 and grid[r+1][c] != 0:
                grid[r][c], grid[r+1][c] = grid[r+1][c], grid[r][c] #swap
        for r in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r-1][c] == 0:
                grid[r-1][c], grid[r][c] = grid[r][c], grid[r-1][c] #swap
    return score

def down(grid): # r = 3 방향으로 밀어줌
    score = 0 #반환할 score
    # 1. 이동방향으로 밀기
    for c in range(4):
        for r in range(3, 0, -1):
            if grid[r][c] == 0 and grid[r-1][c] != 0:
                grid[r][c], grid[r-1][c] = grid[r-1][c], grid[r][c] #swap
        for r in range(3):
            if grid[r][c] != 0 and grid[r+1][c] == 0:
                grid[r+1][c], grid[r][c] = grid[r][c], grid[r+1][c] #swap
        # 2. 같은 타일 합치기
        for r in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r][c] == grid[r-1][c]:
                grid[r][c] += grid[r-1][c]
                score += grid[r][c]
                grid[r-1][c] = 0
        # 3. 다시 이동방향으로 밀기
        for r in range(3, 0, -1):
            if grid[r][c] == 0 and grid[r-1][c] != 0:
                grid[r][c], grid[r-1][c] = grid[r-1][c], grid[r][c] #swap
        for r in range(3):
            if grid[r][c] != 0 and grid[r+1][c] == 0:
                grid[r+1][c], grid[r][c] = grid[r][c], grid[r+1][c] #swap
    return score

def right(grid): # c = 3 방향으로 밀어줌
    score = 0 #반환할 score
    # 1. 이동방향으로 밀기
    for r in range(4):
        for c in range(3, 0, -1):
            if grid[r][c] == 0 and grid[r][c-1] != 0:
                grid[r][c], grid[r][c-1] = grid[r][c-1], grid[r][c] #swap
        for c in range(3):
            if grid[r][c] != 0 and grid[r][c+1] == 0:
                grid[r][c+1], grid[r][c] = grid[r][c], grid[r][c+1] #swap
        # 2. 같은 타일 합치기
        for c in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r][c] == grid[r][c-1]:
                grid[r][c] += grid[r][c-1]
                score += grid[r][c]
                grid[r][c-1] = 0
        # 3. 다시 이동방향으로 밀기
        for c in range(3, 0, -1):
            if grid[r][c] == 0 and grid[r][c-1] != 0:
                grid[r][c], grid[r][c-1] = grid[r][c-1], grid[r][c] #swap
        for c in range(3):
            if grid[r][c] != 0 and grid[r][c+1] == 0:
                grid[r][c+1], grid[r][c] = grid[r][c], grid[r][c+1] #swap
    return score

def left(grid): # c = 0 방향으로 밀어줌
    score = 0 #반환할 score
    # 1. 이동방향으로 밀기
    for r in range(4):
        for c in range(3):
            if grid[r][c] == 0 and grid[r][c+1] != 0:
                grid[r][c], grid[r][c+1] = grid[r][c+1], grid[r][c] #swap
        for c in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r][c-1] == 0:
                grid[r][c-1], grid[r][c] = grid[r][c], grid[r][c-1] #swap
        # 2. 같은 타일 합치기
        for c in range(3):
            if grid[r][c] != 0 and grid[r][c] == grid[r][c+1]:
                grid[r][c] += grid[r][c+1]
                score += grid[r][c]
                grid[r][c+1] = 0
        # 3. 다시 이동방향으로 밀기
        for c in range(3):
            if grid[r][c] == 0 and grid[r][c+1] != 0:
                grid[r][c], grid[r][c+1] = grid[r][c+1], grid[r][c] #swap
        for c in range(3, 0, -1):
            if grid[r][c] != 0 and grid[r][c-1] == 0:
                grid[r][c-1], grid[r][c] = grid[r][c], grid[r][c-1] #swap
    return score

current_score = int(input())
moves = input() #이동(문자열)
M = len(moves)//4 #이동 횟수
moves_arr = deque([])
for m in range(M):
    move_direction = moves[4*m]
    new_value = int(moves[4*m+1])
    new_row = int(moves[4*m+2])
    new_col = int(moves[4*m+3])
    moves_arr.append([move_direction, new_value, new_row, new_col])

grid = list(map(int, input().split(" ")))
grid_2D = []

for g in range(4):
    grid_2D.append(grid[4*g:4*g+4])

while moves_arr:
    move = moves_arr.popleft()
    move_direction = move[0]
    new_value = move[1]
    new_row = move[2]
    new_col = move[3]
    if move_direction == 'U':
        current_score += up(grid_2D)
    elif move_direction == 'D':
        current_score += down(grid_2D)
    elif move_direction == 'L':
        current_score += left(grid_2D)
    else:
        current_score += right(grid_2D)
    grid_2D[new_row][new_col] = new_value

print(current_score)
