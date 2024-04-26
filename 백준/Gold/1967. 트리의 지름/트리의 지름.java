import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Graph{
        int dst, cost;
        public Graph(int dst, int cost){
            this.dst = dst;
            this.cost = cost;
        }
    }
    static int N, max = 0;
    static List<Graph>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            tree[n] = new LinkedList<>();
        }
        StringTokenizer st;
        for(int e = 0; e < N-1; e++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree[src].add(new Graph(dst, cost));
            tree[dst].add(new Graph(src, cost));
        }
        for(int n = 1; n <= N; n++){
            visited = new boolean[N + 1];
            visited[n] = true;
            dfs(n, 0, 0);
        }
        System.out.println(max);
    }

    private static void dfs(int node, int length, int cnt) {
        if(cnt == N-1){
            return;
        }
        for(Graph next: tree[node]){
            int nextNode = next.dst;
            int nextCost = next.cost;
            if(visited[nextNode]) continue;
            visited[nextNode] = true;
            max = Math.max(max, length + nextCost);
            dfs(nextNode, length + nextCost, cnt + 1);
            visited[nextNode] = false;
        }
    }
}
