
def step_up(N, step):
    dp = [0 for n in range(N)]
    # dp 초기값 설정
    dp[0] = step[0] # dp[0]은 한칸 올라간 경우밖에 없음
    if N == 1:
        return dp[0]
    
    dp[1] = max(step[0]+step[1], step[1]) # 한칸+한칸 올라간 경우와 두칸 올라간 경우
    if N == 2:
        return dp[1]
    
    dp[2] = max(step[0]+step[2], step[1]+step[2]) # 한칸+두칸 or 두칸+한칸

    for n in range(3, N):
        # 전전칸을 밟고 현재칸 밟는 경우,
        # 전전전칸 밟고 전칸 밟고 현재칸 밟는 경우
        # 두가지 경우 나옴
        dp[n] = max(dp[n-2]+step[n], dp[n-3]+step[n-1]+step[n])

    return dp[N-1]


N = int(input())
step = []
for n in range(N):
    step.append(int(input()))
print(step_up(N, step))