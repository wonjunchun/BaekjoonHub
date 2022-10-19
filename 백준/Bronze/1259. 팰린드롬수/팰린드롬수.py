loop = True
while loop:
    N = input()
    if N == '0':
        loop = False
        continue
    else:
        rev = list(N)
        rev.reverse()
        rev = ''.join(rev)
        if rev == N:
            print('yes')
        else:
            print('no')