import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Connection implements Comparable<Connection>{
        int dst, cost;
        public Connection(int dst, int cost){
            this.dst = dst;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o){
            return this.cost - o.cost;
        }
    }
    static int N, M;
    static List<Connection>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        for(int n = 1; n <= N; n++){
            adjList[n] = new LinkedList<>();
        }
        StringTokenizer st;
        int minNode = 0; //최소 간선이 되는 노드에서부터 시작하기 위해
        int minCost = Integer.MAX_VALUE;
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[src].add(new Connection(dst, cost));
            adjList[dst].add(new Connection(src, cost));
            if(cost < minCost){
                minNode = dst;
                minCost = cost;
            }
        }
        int result = prim(minNode);
        System.out.println(result);
    }

    private static int prim(int start) {
        int answer = 0; //연결 총 거리
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Connection> pq = new PriorityQueue<>();
        pq.add(new Connection(start, 0));

        while(!pq.isEmpty()){
            Connection current = pq.poll();
            int curDst = current.dst;
            int curCost = current.cost;
            if(visited[curDst]) continue;
            visited[curDst] = true;
            answer += curCost; //현재 노드 방문하는 시점에 최소비용값 누적
            
            for(Connection next: adjList[curDst]){
                int nextDst = next.dst;
                int nextCost = next.cost;
                if(!visited[nextDst]){
                    pq.add(new Connection(nextDst, nextCost));
                }
            }
        }
        return answer;
    }
}