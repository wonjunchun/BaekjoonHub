import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static List<Graph>[] graph;
    public static int V, E, start;
    public static boolean[] visited;
    public static class Graph implements Comparable<Graph>{
        int dst; //도착지 정점
        int weight; //가중치
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
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        //start = Integer.parseInt(br.readLine());

        graph = new List[V+1];
        for(int v = 1; v <= V; v++){
            graph[v] = new LinkedList<Graph>();
        }

        for(int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Graph(v, w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] answer = dijkstra();
        System.out.println(answer[end]);

    }
    public static int[] dijkstra(){ //시작점부터 각 정점까지 최단거리 반환
        int[] distance = new int[V+1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        visited = new boolean[V+1];
        visited[start] = true;
        distance[start] = 0;

        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.add(new Graph(start, 0));

        while(!pq.isEmpty()){
            Graph current = pq.poll();
            visited[current.dst] = true;
            if(current.weight > distance[current.dst]) continue;
            for(Graph g: graph[current.dst]){ //현재 정점에 인접한 정점 탐색
                //아직 방문하지 않았고, 기존 거리보다 current를 경유하여 가는 것이 빠르다면, 값 갱신
                if(!visited[g.dst] && distance[g.dst] > distance[current.dst] + g.weight){
                    distance[g.dst] = distance[current.dst] + g.weight;
                    pq.add(new Graph(g.dst, distance[g.dst])); //갱신한 distance를 반영하여 pq에 넣음
                }
            }
        }
        return distance;
    }
}