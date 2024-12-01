import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] degrees = new int[N + 1];
        LinkedList<Integer>[] graph = new LinkedList[N + 1];
        for(int n = 1; n <= N; n++){
            graph[n] = new LinkedList<>();
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());

            graph[src].add(dst); //src 다음에 dst가 오도록 연결
            degrees[dst]++; //dst의 진입차수 증가
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //쉬운 문제부터 풀도록 pq 사용
        StringBuilder sb = new StringBuilder();
        for(int n = 1; n <= N; n++){
            if(degrees[n] == 0){
                pq.add(n); //진입차수가 0이면(이 문제보다 먼저 풀어야하는 문제 없으면) 해당 문제 pq에 넣음
            }
        }
        while(!pq.isEmpty()){
            int current = pq.poll();
            sb.append(current).append(" ");
            for(int next: graph[current]){ //현재 문제 풀고 다음에 풀 수 있는 문제들 확인
                degrees[next]--; //다음 문제의 진입차수 -1
                if(degrees[next] == 0){ //next 진입차수가 0이면
                    pq.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
