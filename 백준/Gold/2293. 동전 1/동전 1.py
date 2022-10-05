
def coin_one(N, K, coin):
    dp = [0 for k in range(K+1)]
    dp[0] = 1 # 0원을 만들수 있는 경우는 한가지

    for n in range(N):
        for k in range(coin[n], K+1):
            # k원 만들수있는 경우의 수는 (k - coin[n])의 값을 계속 누적시키면 됨
            dp[k] += dp[k-coin[n]]
    return dp[K]


[N, K] = list(map(int, input().split(" ")))
# N가지 동전으로 K원 만드는 경우의 수
coin = []
for n in range(N):
    coin.append(int(input()))
coin.sort()
print(coin_one(N, K, coin))