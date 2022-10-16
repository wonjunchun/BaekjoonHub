#A = [0]
import sys

sum = 0
xsum = 0
def query_1(x):
    #global A
    global sum, xsum
    #A.append(x)
    sum += x
    xsum ^= x

def query_2(x):
    #global A
    global sum, xsum
    #A.remove(x)
    sum -= x
    xsum ^= x

def query_3():
    global sum
    print(sum)

def query_4():
    global xsum
    print(xsum)

input = sys.stdin.readline
M = int(input())
for m in range(M):
    q = list(map(int, input().split()))
    if q[0] == 1:
        query_1(q[1])
    elif q[0] == 2:
        query_2(q[1])
    elif q[0] == 3:
        query_3()
    else:
        query_4()