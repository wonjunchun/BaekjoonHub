from collections import deque

K = int(input())
stack = deque([])
for k in range(K):
    num = int(input())
    if num == 0:
        stack.pop()
    else:
        stack.append(num)
print(sum(list(stack)))