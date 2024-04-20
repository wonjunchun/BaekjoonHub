import sys

class Deque:
    def __init__(self):
        self.deque = []
        self._size = 0

    def push_front(self, X: int):
        self.deque = [X] + self.deque
        self._size += 1

    def push_back(self, X: int):
        self.deque = self.deque + [X]
        self._size += 1

    def pop_front(self):
        if self.empty() == 1:
            return -1
        result = self.deque[0]
        del self.deque[0]
        self._size -= 1
        return result

    def pop_back(self):
        if self.empty() == 1:
            return -1
        result = self.deque[self._size - 1]
        del self.deque[self._size - 1]
        self._size -= 1
        return result

    def sizeof(self):
        return self._size

    def empty(self):
        if self._size == 0:
            return 1
        return 0

    def front(self):
        if self.empty() == 1:
            return -1
        else:
            return self.deque[0]

    def back(self):
        if self.empty() == 1:
            return -1
        else:
            return self.deque[self._size - 1]


input = sys.stdin.readline
N = int(input())
deque = Deque()
for n in range(N):
    op = input().split()
    if op[0] == "push_back":
        deque.push_back(op[1])
    elif op[0] == "push_front":
        deque.push_front(op[1])
    elif op[0] == "pop_front":
        print(deque.pop_front())
    elif op[0] == "pop_back":
        print(deque.pop_back())
    elif op[0] == "size":
        print(str(deque.sizeof()))
    elif op[0] == "empty":
        print(str(deque.empty()))
    elif op[0] == "front":
        print(str(deque.front()))
    elif op[0] == "back":
        print(str(deque.back()))
