def make_1(N: int) -> int:
    dp = [0 for n in range(N+1)]
    if N == 1: return 0
    elif N == 2: return 1
    elif N == 3: return 1
    else:
        dp[2] = 1
        dp[3] = 1
        for n in range(4, N+1):
            calc = []
            if n % 3 == 0 and n//3 > 0:
                calc.append(dp[n//3]+1) #현재 n//3 연산 한번 추가됐으므로, +1
            if n % 2 == 0 and n//2 > 0:
                calc.append(dp[n//2]+1)
            if n-1 > 0:
                calc.append(dp[n-1]+1)
            dp[n] = min(calc)

        return dp[N]

N = int(input())
print(make_1(N))