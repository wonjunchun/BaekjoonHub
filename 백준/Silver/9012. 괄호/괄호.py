from collections import deque

def parenthesis(X):
    X = list(X)
    stack = deque([])
    result = True
    for x in X:
        if x == '(':
            stack.append(x)
        else: # ')'인 경우
            if stack: # 닫는괄호 남아있는데 스택 비어있다면, 성립안됨
                stack.pop()
            else:
                result = False
    if stack: # 반복문 종료시에도 스택에 남아있으면
        result = False
    return result

T = int(input())
for t in range(T):
    X = input()
    result = parenthesis(X)
    if result == True:
        print('YES')
    else:
        print('NO')

