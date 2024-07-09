import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    static class TrieNode{
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean terminal;

        public TrieNode(){}
        public void insert(String word){
            TrieNode trieNode = this;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                trieNode.childNode.putIfAbsent(c, new TrieNode()); //삽입
                trieNode = trieNode.childNode.get(c); //다음 트라이 노드로 이동

                if(i == word.length()-1){
                    trieNode.terminal = true; //단말 노드 표시
                }
            }
        }
        public int module(String word){ //모듈 사용 시 글자 입력 위해 버튼 누른 횟수
            TrieNode trieNode = this;
            int cnt = 0;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                TrieNode node = trieNode.childNode.get(c);
                if(i == 0){
                    cnt++;
                }
                else if(trieNode.terminal || trieNode.childNode.size()>1){
                    cnt++;
                }
                trieNode = node;
            }
            return cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine()) != null){
            try{
                int N = Integer.parseInt(input);
                List<String> inputData = new LinkedList<>();
                TrieNode trie = new TrieNode();
                for(int n = 0; n < N; n++){
                    String str = br.readLine();
                    inputData.add(str);
                    trie.insert(str);
                }
                double result = 0;
                for(String str: inputData){
                    result += trie.module(str); //입력 시 버튼 누른 수 누적
                }
                System.out.printf("%.2f\n", result/inputData.size()); //평균 계산
            } catch(NumberFormatException e){
                return;
            }
        }
    }
}
