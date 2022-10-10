import math
from sys import stdin

input = stdin.readline
# log n으로 자리수를 알수 있는 성질과(log10 10 == 1)
# log n + log m = log (n*m)인 성질을 이용
T = int(input()) #testcase
dp = [0, 0]
dp_index = 1

for t in range(T):
    num = int(input())
    if num <= dp_index: # 이미 dp 테이블에 존재하면 그 값 반환
        result = dp[num]
    else: # dp 테이블에 없는 값이라면, 계산
        for n in range(dp_index + 1, num+1):
            dp.append(dp[n-1]+math.log10(n))
        dp_index = num
        result = dp[num]
    print(int(result)+1) # 자리수 반환
