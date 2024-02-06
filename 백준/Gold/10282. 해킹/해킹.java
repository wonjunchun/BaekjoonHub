import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, start;
    static class Dependency implements Comparable<Dependency>{
        public int dst; //목적지 컴퓨터
        public int time; //도달까지 걸리는시간

        public Dependency(int dst, int time){
            this.dst = dst;
            this.time = time;
        }

        @Override
        public int compareTo(Dependency o) {
            return this.time - o.time;
        }
    }
    static LinkedList<Dependency>[] dependencies;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //컴퓨터 대수
            int D = Integer.parseInt(st.nextToken()); //의존성 수
            start = Integer.parseInt(st.nextToken()); //해킹당한 컴퓨터 번호
            dependencies = new LinkedList[N+1];
            for(int n = 1; n <= N; n++){
                dependencies[n] = new LinkedList<>();
            }
            for(int d = 0; d < D; d++){
                st = new StringTokenizer(br.readLine());
                int dst = Integer.parseInt(st.nextToken());
                int src = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());
                dependencies[src].add(new Dependency(dst, sec));
            }
            int[] result = dijkstra();
            int totalCom = 0;
            int totalTime = 0;
            for(int r = 1; r <= N; r++){
                if(result[r] != Integer.MAX_VALUE){
                    totalCom++;
                    if(result[r] > totalTime) totalTime = result[r];
                }
            }
            System.out.println(totalCom + " " + totalTime);
        }
    }

    static int[] dijkstra() {
        int[] result = new int[N + 1]; //start로부터 각 컴퓨터까지 도달시간
        Arrays.fill(result, Integer.MAX_VALUE); //inf로 초기화
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Dependency> pq = new PriorityQueue<>();
        pq.add(new Dependency(start, 0)); //시작컴퓨터 pq에 넣음
        result[start] = 0;

        while(!pq.isEmpty()){
            Dependency current = pq.poll();
            int curDst = current.dst;
            int curTime = current.time;
            if(visited[curDst]) continue; //이미 방문한 상태면 continue
            visited[curDst] = true;
            for(Dependency d: dependencies[curDst]){
                if(!visited[d.dst]){ //다음 컴퓨터가 아직 방문 안한 상태일때
                    if(result[d.dst] > result[curDst] + d.time){ //최단 경로로 갱신
                        result[d.dst] = result[curDst] + d.time;
                        pq.add(new Dependency(d.dst, result[d.dst])); //pq에 넣음
                    }
                }
            }
        }
        return result;
    }
}
