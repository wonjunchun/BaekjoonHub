import sys
def sorting(arr): #quick sort 구현
    # 1. 우선 리스트의 가운데 값을 pivot으로 잡고
    # 2. pivot보다 작은 값의 그룹, 큰 값의 그룹으로 나눔
    # 3. 작은 값 그룹, 큰 값 그룹을 quick sort 재귀 호출
    if len(arr) <= 1: #길이가 1 이하이면 그냥 반환
        return arr
    pivot = arr[len(arr)//2] #리스트 가운데 값을 pivot으로
    less, equal, great = [], [], []
    for a in arr:
        if a < pivot:
            less.append(a)
        elif a == pivot:
            equal.append(a)
        else:
            great.append(a)
    return sorting(less) + equal + sorting(great)

input = sys.stdin.readline
sys.setrecursionlimit(500000)
N = int(input())
arr = []
for n in range(N):
    arr.append(int(input()))

#result = sorting(arr)
arr.sort()
result = arr
for r in result:
    print(r)