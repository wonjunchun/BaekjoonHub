import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int current;
        int parent;

        public Node(int current, int parent) {
            this.current = current;
            this.parent = parent;
        }
    }
    static List<Integer>[] adjList;
    static int N;
    static int[] parents;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new LinkedList[N + 1];
        for (int n = 1; n <= N; n++) {
            adjList[n] = new LinkedList<>();
        }
        StringTokenizer st;
        for(int n = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            adjList[src].add(dst);
            adjList[dst].add(src);
        }
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));
        visited[1] = true;
        while(!queue.isEmpty()){
            Node current = queue.poll();
            int curNode = current.current;
            int parentNode = current.parent;
            parents[curNode] = parentNode;
            for(int next: adjList[curNode]){
                if(visited[next]) continue;
                queue.add(new Node(next, curNode));
                visited[next] = true;
            }
        }
        for(int n = 2; n <= N; n++){
            System.out.println(parents[n]);
        }
    }
}
