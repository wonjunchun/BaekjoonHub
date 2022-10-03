def retire(N, schedule):
    #schedule 명세 [Ti(소요기간), Pi(금액)]
    dp = [0 for n in range(N+1)]
    for d in range(N):
        Ti = schedule[d][0] #현재일의 상담 소요일
        Pi = schedule[d][1] #현재일의 상담 금액
        if d + Ti <= N: # 현재일의 상담 종료일이 퇴직일을 초과하지 않을경우
            # 상담 종료일의 dp값에 현재 상담 마친 후의 이익누적값과 기존 이익누적값 비교해서 더 큰값 넣음
            dp[d+Ti] = max(dp[d+Ti], dp[d] + Pi)
        # dp[d+1]에 별다른 값 없으면, 이전일까지 얻은 이익 최대누적값 넣음
        dp[d+1] = max(dp[d+1], dp[d])
    return dp[N]


N = int(input())
schedule = []
for n in range(N):
    sc = list(map(int, input().split(" ")))
    schedule.append(sc)
print(retire(N, schedule))