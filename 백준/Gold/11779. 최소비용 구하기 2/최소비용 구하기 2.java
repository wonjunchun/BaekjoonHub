import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Bus implements Comparable<Bus>{
        public int dst;
        public int cost;

        public Bus(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
    static int N;
    static int start, end;
    static LinkedList<Bus>[] map;
    static LinkedList<Integer> path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            map[n] = new LinkedList<>();
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[src].add(new Bus(dst, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int result = dijkstra();
        System.out.println(result);
        System.out.println(path.size());
        for (int p : path) {
            System.out.print(p + " ");
        }
    }

    static int dijkstra() {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); //inf로 초기화
        LinkedList<Integer>[] paths = new LinkedList[N + 1]; //시작점부터 현재 노드까지의 경로 기록
        boolean[] visited = new boolean[N + 1]; //방문 기록
        for(int n = 1; n <= N; n++){
            paths[n] = new LinkedList<>();
        }

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(start, 0));
        paths[start].add(start);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Bus current = pq.poll();
            int curDst = current.dst;
            int curCost = current.cost;

            if(visited[curDst]) continue;
            visited[curDst] = true;
            for(Bus b: map[curDst]){
                if(!visited[b.dst]){
                    if(distance[b.dst] > distance[curDst]+b.cost){
                        distance[b.dst] = distance[curDst] + b.cost;
                        pq.add(new Bus(b.dst, distance[b.dst]));
                        //최단경로 기록
                        paths[b.dst].clear(); //b.dst의 기존 최단경로 지움
                        paths[b.dst].addAll(paths[curDst]); //curDst 거쳐서 가는 경로 기록
                        paths[b.dst].add(b.dst);
                    }
                }
            }
        }
        path = paths[end];
        return distance[end];
    }
}
