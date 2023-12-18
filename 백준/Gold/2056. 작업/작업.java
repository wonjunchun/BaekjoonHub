import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] schedules; //연결관계 그래프
    static int[] degree; //각 노드의 진입차수
    static int[] time; //각 작업의 소요시간
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        schedules = new List[N+1];
        degree = new int[N + 1];
        time = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st;
        for(int n = 1; n <= N; n++){
            schedules[n] = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            time[n] = Integer.parseInt(st.nextToken()); //소요시간 정보 넣음
            dp[n] = time[n]; //초기화
            degree[n] = Integer.parseInt(st.nextToken()); //선행 작업 개수 넣음
            for(int i = 0; i < degree[n]; i++){ //선행작업 연결관계 schedules에 넣음
                int src = Integer.parseInt(st.nextToken());
                schedules[src].add(n);
            }
        }
        //차수 0인 노드 큐에 넣음
        Deque<Integer> queue = new LinkedList<>();
        for(int n = 1; n <= N; n++){
            if(degree[n] == 0){
                queue.add(n);
            }
        }

        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            for(int next: schedules[current]){ //현재 노드의 다음 노드 탐색
                //다음 작업으로 넘어가려면, 선행 작업을 다 끝내놔야 하므로
                //선행작업 중 제일 큰 소요시간 만큼 걸림
                dp[next] = Math.max(dp[next], dp[current]+time[next]);
                degree[next]--;
                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }
        for(int n = 1; n <= N; n++){
            result = Math.max(dp[n], result);
        }
        System.out.println(result);
    }
}
