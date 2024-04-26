import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Graph{
        int dst, cost;

        public Graph(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }
    static int V;
    static long max = 0;
    static int maxNode = 1;
    static List<Graph>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        tree = new List[V + 1];
        StringTokenizer st;
        for(int v = 1; v <= V; v++)
            tree[v] = new LinkedList<>();
        for(int v = 1; v <= V; v++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int dst = Integer.parseInt(st.nextToken());
                if(dst == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[src].add(new Graph(dst, cost));
                tree[dst].add(new Graph(src, cost));
            }
        }

        //임의 노드 1에서 가장 거리 먼 노드 탐색
        visited = new boolean[V + 1];
        dfs(1, 0, 0);
        visited = new boolean[V + 1];
        max = 0;
        //1에서 가장 거리 먼 정점에서 가장 거리 먼 정점을 탐색(트리 지름 탐색)
        dfs(maxNode, 0, 0);
        System.out.println(max);
    }

    private static void dfs(int node, long length, int cnt) {
        if(visited[node]) return;
        if(max < length){
            max = length;
            maxNode = node;
        }
        visited[node] = true;
        for(Graph next: tree[node]){
            if(visited[next.dst]) continue;
            dfs(next.dst, length + next.cost, cnt + 1);
        }
    }
}
