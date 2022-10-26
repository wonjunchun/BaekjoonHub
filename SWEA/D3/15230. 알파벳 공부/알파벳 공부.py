class Node(object):
    def __init__(self, key, data=None):
        self.key = key
        self.data = data # 문자열의 종료지점을 알림, 종료시점의 최종 문자열 저장
        self.children = {}

class Trie:
    def __init__(self):
        self.head = Node(None)
    
    def insert(self, string):
        current_node = self.head
        
        for char in string:
            if char not in current_node.children: #children에 현재 char가 없으면
                current_node.children[char] = Node(char) #넣어줌
            current_node = current_node.children[char] #자식 노드로 이동
        current_node.data = string

    def search(self, string):
        current_node = self.head

        for char in string:
            if char in current_node.children:
                current_node = current_node.children[char] #자식 노드로 이동
            else:
                return False

            if current_node.data: #존재하는 데이터이면
                return True
            else:
                return False

    def starts_with(self, prefix): # 입력된 문자열로 시작되는 단어들 목록
        current_node = self.head
        words = []

        for p in prefix:
            if p in current_node.children:
                current_node = current_node.children[p]
            else:
                return None
        #prefix의 종점 찾아서, 종점 노드의 children을 current에 넣음
        current_node = [current_node]
        next_node = []
        #prefix로 시작하는 모든 data 탐색해서 words에 넣음
        while True:
            for node in current_node:
                if node.data:
                    words.append(node.data)
                next_node.extend(list(node.children.values()))
            if len(next_node) != 0:
                current_node = next_node
                next_node = []
            else:
                break
        return words

    def similarity(self, string): # 15230 문제해결용. 기존 문자열과 유사도 반환
        current_node = self.head
        similarity = 0
        for char in string:
            if char in current_node.children:
                current_node = current_node.children[char]
                similarity += 1
            else:
                break
        return similarity

T = int(input())
trie = Trie()
trie.insert('abcdefghijklmnopqrstuvwxyz')
for t in range(T):
    str = input()
    result = trie.similarity(str)
    print('#{} {}'.format(t+1, result))
