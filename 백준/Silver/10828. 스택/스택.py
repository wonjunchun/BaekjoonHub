import sys


class Stack:
    def __init__(self):
        self.stack = []
        self.size = 0

    def push(self, X):
        self.stack += [X]
        self.size += 1

    def pop(self):
        if self.size == 0:
            return -1
        else:
            item = self.stack[self.stack_size()-1]
            self.stack = self.stack[:self.stack_size()-1]
            self.size -= 1
            return item

    def stack_size(self):
        return self.size

    def empty(self):
        if self.size == 0:
            return 1
        else: return 0

    def top(self):
        if self.size == 0:
            return -1
        else:
            return self.stack[self.stack_size()-1]

input = sys.stdin.readline
N = int(input())
stack = Stack()
for n in range(N):
    cmd = input().split()
    if cmd[0] == 'push':
        stack.push(cmd[1])
    elif cmd[0] == 'pop':
        print(stack.pop())
    elif cmd[0] == 'size':
        print(stack.stack_size())
    elif cmd[0] == 'empty':
        print(stack.empty())
    elif cmd[0] == 'top':
        print(stack.top())
