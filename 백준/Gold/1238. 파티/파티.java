import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Graph implements Comparable<Graph>{
        public int dst;
        public int cost;

        public Graph(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }

        @Override
        public int compareTo(Graph o) {
            return this.cost - o.cost;
        }
    }
    static LinkedList<Graph>[] map;
    static int N, M, X;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            map[n] = new LinkedList<>();
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[src].add(new Graph(dst, cost));
        }
        result = new int[N + 1];
        for(int n = 1; n <= N; n++){ //각 학생 집에서 X까지의 최단경로 구함
            result[n] += dijkstra(n)[X];
        }
        int[] distance = dijkstra(X); //X에서 각 학생 집까지의 최단경로 구함
        for(int n = 1; n <= N; n++){
            result[n] += distance[n];
        }
        Arrays.sort(result);
        System.out.println(result[N]);
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.add(new Graph(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Graph current = pq.poll();
            int curDst = current.dst;
            int curCost = current.cost;
            visited[curDst] = true;
            for(Graph g: map[curDst]){
                if(!visited[g.dst]){
                    if(distance[g.dst] > distance[curDst] + g.cost){
                        distance[g.dst] = distance[curDst] + g.cost;
                        pq.add(new Graph(g.dst, distance[g.dst]));
                    }
                }
            }
        }
        return distance;
    }
}
