import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static StringBuilder sb;
    static class TrieNode{
        TreeMap<String, TrieNode> childNode = new TreeMap<>(); //디렉토리 이름 순으로 정렬해야 하므로
        boolean terminal;
        public TrieNode(){}
        public void insert(String directory){
//            String[] directories = directory.split("\\"); //split이 안됨.
            StringTokenizer st = new StringTokenizer(directory, "\\");
            TrieNode trieNode = this;
//            for(String str: directories){
            while(st.hasMoreTokens()){
                String str = st.nextToken();
                trieNode.childNode.putIfAbsent(str, new TrieNode()); //해당 자식 없으면 새로 생성
                trieNode = trieNode.childNode.get(str); //다음 트라이 노드로 이동
            }
            trieNode.terminal = true; //단말 노드 표시
        }
        public void printDirectory(){
            preOrder(0, this);
        }
        public void preOrder(int depth, TrieNode currentTrieNode){ //전위순회로 디렉토리 순회
            TreeMap<String, TrieNode> child = currentTrieNode.childNode;
            if(child == null) return; //자식이 없다면 종료
            for(String key: child.keySet()){
                for(int d = 0; d < depth; d++)
                    sb.append(" "); //깊이 만큼 들여쓰기
                sb.append(key).append("\n");
                preOrder(depth+1, child.get(key));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TrieNode trie = new TrieNode();
        for(int n = 0; n < N; n++){
            trie.insert(br.readLine());
        }
        trie.printDirectory();
        System.out.println(sb.toString());
    }
}
