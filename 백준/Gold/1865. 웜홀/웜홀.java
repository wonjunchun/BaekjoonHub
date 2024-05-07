import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Graph{
        int end, time;

        public Graph(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }
    static int N, M, W;
    static int[] distance;
    static List<Graph>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < TC; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //지점 수
            M = Integer.parseInt(st.nextToken()); //도로 수
            W = Integer.parseInt(st.nextToken()); //웜홀 수
            distance = new int[N + 1];
            adjList = new List[N + 1];
            for(int n = 1; n <= N; n++){
                adjList[n] = new LinkedList<>();
            }
            for(int m = 0; m < M+W; m++){ //M번째 입력까지는 도로, M+1부터 M+W 입력까지는 웜홀
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()); //시작점
                int E = Integer.parseInt(st.nextToken()); //도착점
                int T = Integer.parseInt(st.nextToken()); //cost

                if(m < M){ //도로의 경우 양방향
                    adjList[S].add(new Graph(E, T));
                    adjList[E].add(new Graph(S, T));
                }
                else{ //웜홀의 경우 단방향
                    adjList[S].add(new Graph(E, -T));
                }
            }
            boolean isMinusCycle = false;
            for(int n = 1; n <= N; n++){
                if(bellmanFord(n)){ //음수 사이클 발생 시 YES
                    isMinusCycle = true;
                    sb.append("YES").append("\n");
                    break;
                }
            }
            if(!isMinusCycle){ //음수사이클 발생 안하면 NO
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static boolean bellmanFord(int start) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        boolean update = false;

        for(int n = 1; n < N; n++){ //최단거리 갱신 과정 N-1번 반복
            update = false;
            //최단거리 갱신
            for(int i = 1; i <= N; i++){
                for(Graph graph: adjList[i]){
                    //현재 노드에 도달 가능하고 다음 노드 방문하는 것이 방문 안하는것 보다 경로가 짧다면
                    if(distance[i] != Integer.MAX_VALUE && distance[graph.end] > distance[i] + graph.time){
                        distance[graph.end] = distance[i]+ graph.time;
                        update = true;
                    }
                }
            }
            if(!update){ //더이상 최단거리 갱신이 일어나지 않으면 종료
                break;
            }
        }
        //N-1번째까지 계속 업데이트 발생했다면, N번째도 업데이트 발생하면 음수 사이클이 일어난것임
        if(update){
            for(int i = 1; i <= N; i++){
                for(Graph graph: adjList[i]){
                    if(distance[i] != Integer.MAX_VALUE && distance[graph.end] > distance[i] + graph.time){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}