N, K = map(int, input().split())
numerator = 1
denominator = 1
iteration = K
for k in range(iteration, 0, -1):
    numerator *= N
    denominator *= K
    N -= 1
    K -= 1
print(numerator//denominator)