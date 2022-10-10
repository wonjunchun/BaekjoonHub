def fibonacci(N):
    if N == 0:
        return [1, 0]
    elif N == 1:
        return [0, 1]
    else:
        dp = [[0, 0] for n in range(N+1)]
        #초기값 설정
        dp[0][0] = 1
        dp[0][1] = 0
        dp[1][0] = 0
        dp[1][1] = 1
        for n in range(2, N+1):
            dp[n][0] = dp[n-1][0] + dp[n-2][0]
            dp[n][1] = dp[n-1][1] + dp[n-2][1]
        return dp[N]

T = int(input())

for t in range(T):
    N = int(input())
    result = fibonacci(N)
    print(result[0], result[1])
