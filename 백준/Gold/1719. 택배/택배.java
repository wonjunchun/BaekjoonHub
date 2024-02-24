import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Graph implements Comparable<Graph> {
        int dst;
        int cost;

        public Graph(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }

        @Override
        public int compareTo(Graph o) {
            return this.cost - o.cost;
        }
    }

    static LinkedList<Graph>[] adjList;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            adjList[n] = new LinkedList<>();
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[src].add(new Graph(dst, cost));
            adjList[dst].add(new Graph(src, cost));
        }
        for(int start = 1; start <= N; start++){
            int[] distance = dijkstra(start); //각 경로 첫번째 방문 노드
            for(int n = 1; n <= N; n++){
                if(n == start) System.out.print("- ");
                else System.out.print(distance[n] + " ");
            }
            System.out.println();
        }
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        LinkedList<Integer>[] path = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            path[n] = new LinkedList<>();
        }
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.add(new Graph(start, 0));
        distance[start] = 0;


        while (!pq.isEmpty()) {
            Graph current = pq.poll();
            int curDst = current.dst;
            int curCost = current.cost;

            if(visited[curDst]) continue;
            visited[curDst] = true;

            for(Graph g: adjList[curDst]){
                if(!visited[g.dst]){
                    if(distance[g.dst] > distance[curDst] + g.cost){
                        distance[g.dst] = distance[curDst] + g.cost;
                        pq.add(new Graph(g.dst, distance[g.dst]));
                        path[g.dst].clear();
                        path[g.dst].addAll(path[curDst]);
                        path[g.dst].add(g.dst);
                    }
                }
            }
        }
        int[] result = new int[N + 1];
        for(int n = 1; n <= N; n++){
            if(!path[n].isEmpty())
                result[n] = path[n].get(0); //각 경로의 시작점
        }
        return result;
    }
}
