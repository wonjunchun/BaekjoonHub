import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, E;
    static int v1, v2;
    static LinkedList<Graph>[] graph;
    static class Graph implements Comparable<Graph>{
        public int dst;
        public int weight;

        public Graph(int dst, int weight){
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public int compareTo(Graph o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new LinkedList[N+1];
        for(int n = 1; n <= N; n++){
            graph[n] = new LinkedList<>();
        }
        for(int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[src].add(new Graph(dst, weight));
            graph[dst].add(new Graph(src, weight));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        //i) 1 -> v1, v1 -> v2, v2 -> N의 최단경로합
        int result1 = 0, result2 = 0;
        int[] route = new int[3];
        route[0] = dijkstra(1, v1);
        route[1] = dijkstra(v1, v2);
        route[2] = dijkstra(v2, N);
        for(int r: route){
            if(r < 0){
                result1 = -1;
                break;
            }
            result1 += r;
        }

        //ii) 1 -> v2, v2 -> v1, v1 -> N의 최단경로합
        route = new int[3];
        route[0] = dijkstra(1, v2);
        route[1] = dijkstra(v2, v1);
        route[2] = dijkstra(v1, N);
        for(int r: route){
            if(r < 0){
                result2 = -1;
                break;
            }
            result2 += r;
        }

        if(result1 >= 0 && result2 >= 0){ //둘다 최단경로 존재시
            System.out.println(Math.min(result1, result2));
        }
        else{ //둘중 하나만 최단경로 존재하거나, 둘다 경로 존재 안하면
            System.out.println(Math.max(result1, result2));
        }
    }

    static int dijkstra(int src, int dst) { //src부터 dst까지 최단거리 반환
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];
        visited[src] = true;
        distance[src] = 0;

        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.add(new Graph(src, 0));

        while (!pq.isEmpty()) {
            Graph current = pq.poll();
            visited[current.dst] = true;
            for(Graph g: graph[current.dst]){ //현재 정점에 인접한 정점 탐색
                //아직 방문 안했고, 기존 거리보다 current 경유가 빠르다면 값 갱신
                if (!visited[g.dst] && distance[g.dst] > distance[current.dst] + g.weight) {
                    distance[g.dst] = distance[current.dst] + g.weight;
                    pq.add(new Graph(g.dst, distance[g.dst]));
                }
            }
        }
        if(distance[dst] == Integer.MAX_VALUE) return -1;
        return distance[dst];
    }
}
