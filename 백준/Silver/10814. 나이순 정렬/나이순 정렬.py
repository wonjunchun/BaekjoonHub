N = int(input())
arr = []
for n in range(N):
    arr.append(input().split())
for n in range(N):
    arr[n][0] = int(arr[n][0])
arr.sort(key=lambda x:x[0])
for a in arr:
    print(a[0], a[1])