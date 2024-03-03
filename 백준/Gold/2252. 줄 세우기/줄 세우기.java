import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] graph;
    static int[] degree; //진입차수
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N + 1];
        graph = new LinkedList[N+1];
        for(int n = 1; n <= N; n++){
            graph[n] = new LinkedList<>();
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            graph[src].add(dst);
            degree[dst]++; //진입차수 증가
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int n = 1; n <= N; n++){
            if(degree[n] == 0) queue.add(n);
        }
        LinkedList<Integer> result = new LinkedList<>();
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            result.add(current);
            for(int next: graph[current]){
                //current 노드에 연결된 다른 노드들의 진입차수 -1
                degree[next]--;
                if(degree[next] == 0){ //진입차수가 0이면, 큐에 넣음
                    queue.add(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int r: result){
            sb.append(r).append(" ");
        }
        System.out.println(sb.toString());
    }
}
