def ascending_number(N: int) -> int:
    dp = [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
    reset = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    if N == 1:
        return sum(dp[0])
    else:
        for n in range(N-1): #N 자리 수만큼 반복(이미 1자리 값은 계산됐으니 N-1번 계산)
            for m in range(10): #dp[1][m]에
                for k in range(m+1): #dp[0][0]~dp[0][m]까지의 합을 넣기위해
                    dp[1][m] += dp[0][k] #값 누적
            dp[0][:] = dp[1][:] #다음 자리수 계산을 위해 dp[1] 값들을 dp[0]으로 옮김
            dp[1][:] = reset[:] #dp[1] 초기화
        return sum(dp[0])

N = int(input())
print(ascending_number(N) % 10007)