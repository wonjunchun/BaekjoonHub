def onoff(switch: int) -> int:
    if switch == 0:
        return 1
    else:
        return 0

N = int(input())
switch_status = list(map(int, input().split()))
S = int(input())
student_status = []

for s in range(S):
    student = list(map(int, input().split()))

    if student[0] == 1: #남학생인 경우
        switch_num = N//student[1] # 몇개 바꿔야 하는지
        for i in range(1, switch_num+1):
            switch_status[i*student[1]-1] = onoff(switch_status[i*student[1]-1])

    elif student[0] == 2: #여학생인 경우
        switch_range = 0
        for i in range(N//2):
            if 1 <= student[1]-i <= N and 1 <= student[1]+i <= N:
                # 이번 구간이 스위치 범위 내에 있다면
                if switch_status[student[1]-i-1] == switch_status[student[1]+i-1]:
                    # 이번 구간 내에 스위치 상태가 좌우대칭이면,
                    if student[1]-i-1 == student[1]+i-1: # 구간의 시작점이면, 한번만 스위치상태 바꿈
                        switch_status[student[1]-i-1] = onoff(switch_status[student[1]-i-1])
                    else: # 그 외에는, 둘다 상태 바꿈
                        switch_status[student[1]-i-1] = onoff(switch_status[student[1]-i-1])
                        switch_status[student[1]+i-1] = onoff(switch_status[student[1]+i-1])
                else: #구간 내 스위치가 더이상 좌우대칭이 아니면, break
                    break
            else: #구간이 스위치 범위를 벗어난다면, break
                break

count = 0
for s in switch_status:
    if count % 20 == 0 and count != 0:
        print()
    print(s, end=' ')
    count += 1
