import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
            return this.cost-o.cost;
        }
    }
    static int[][] adjMatrix;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        int[] start = new int[2];
        int minCost = Integer.MAX_VALUE;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                adjMatrix[n][m] = Integer.parseInt(st.nextToken());
                if(adjMatrix[n][m] != 0 && adjMatrix[n][m] < minCost){
                    minCost = adjMatrix[n][m];
                    start = new int[]{n, m}; //가장 작은 cost를 갖는 간선의 정점부터 시작하도록
                }
            }
        }
        long result = prim(start);
        System.out.println(result);
    }

    private static long prim(int[] start) {
        boolean[] visited = new boolean[N];
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        pq.add(new Graph(start[0], 0));
        distance[start[0]] = 0;
        while(!pq.isEmpty()){
            Graph current = pq.poll();
            int curDst = current.dst;
            int curCost = current.cost;
            if(visited[curDst]) continue;
            visited[curDst] = true;

            for(int nextDst = 0; nextDst < N; nextDst++){
                //아직 방문 안했고, 현재 노드에서 방문 가능한 노드이면
                if(adjMatrix[curDst][nextDst] != 0 && !visited[nextDst]){
                    //nextDst에 연결된 간선 중 최단 cost 갱신(pq에서도 최소비용 갖는 순으로 뽑힘)
                    distance[nextDst] = Math.min(distance[nextDst], adjMatrix[curDst][nextDst]);
                    pq.add(new Graph(nextDst, distance[nextDst]));
                }
            }
        }
        long result = 0;
        for(int d: distance){
            result += d;
        }
        return result;
    }
}
