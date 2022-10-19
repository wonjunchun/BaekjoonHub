loop = True

while loop:
    current = list(map(int, input().split()))
    if current == [0, 0, 0]:
        loop = False
        continue
    else:
        current.sort()
        if current[0]*current[0] + current[1]*current[1] == current[2]*current[2]:
            print('right')
        else:
            print('wrong')
