N = int(input())

A = list(map(int, input().split()))
A = set(A)

M = int(input())
nums = list(map(int, input().split()))
for n in nums:
    s1 = set([n])
    if A & s1 == s1: #만약 n이 A에 존재한다면
        print(1)
    else:
        print(0)