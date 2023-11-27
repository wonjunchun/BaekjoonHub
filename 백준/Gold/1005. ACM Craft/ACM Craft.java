import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] buildings; //각 빌딩 짓는데 소요 시간
//    static List<Integer>[] orders; //순서
    static boolean[][] orders; //순서[src][dst]
    static int[] degrees; //위상정렬의 진입차수
    static int[] dp;
    static int N, K, W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            buildings = new int[N+1];
//            orders = new List[N+1];
            orders = new boolean[N+1][N+1];
            degrees = new int[N+1];
            dp = new int[N+1];
            //입력
            st = new StringTokenizer(br.readLine());
            for(int n = 1; n <= N; n++){
                buildings[n] = Integer.parseInt(st.nextToken());
//                orders[n] = new LinkedList();
            }
            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int src = Integer.parseInt(st.nextToken());
                int dst = Integer.parseInt(st.nextToken());
//                orders[src].add(dst);
                orders[src][dst] = true; //해당 순서로 연결되었음을 표시
                //진입차수 ++
                degrees[dst]++;
            }
            W = Integer.parseInt(br.readLine());
            //위상정렬 시작
            Deque<Integer> queue = new LinkedList<>();
            for(int n = 1; n <= N; n++){
                //진입차수 0인 노드 우선 큐에 집어넣음
                if(degrees[n] == 0){
                    queue.add(n);
                    dp[n] = buildings[n];
                }
            }
            
            while(!queue.isEmpty()){
                int prev = queue.pollFirst(); //이전 건물
                for(int i = 1; i <= N; i++){ //다음 건물 목록
                    if(orders[prev][i]){
                        dp[i] = Math.max(dp[i], dp[prev]+buildings[i]); //둘중 최대값
                        degrees[i]--;
                        if(degrees[i] == 0){
                            queue.add(i);
                        }
                    }
                }
            }
            System.out.println(dp[W]);
        }
    }
}
