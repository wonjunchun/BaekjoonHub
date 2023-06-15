import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N; //M:세로, N:가로
    static int[][] map;
    static int[][] dp;

//    static int routeCnt = 0;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[m], -1); //dp배열 최초값 -1로 설정(visited 역할도 같이 함)
            for(int n = 0; n < N; n++){
                map[m][n] = Integer.parseInt(st.nextToken());
            }
        }
        int result = dfs(0, 0);
        System.out.println(result);
    }

    private static int dfs(int m, int n) {
        //종료조건(목적지 도달했으면 종료)
        if(m == M-1 && n == N-1){
            return 1;
        }
        if(dp[m][n] == -1){ //아직 현좌표가 방문하지 않은 곳이라면 탐색
            dp[m][n] = 0; //현좌표 방문 처리

            for(int[] d: directions){
                int nextM = m + d[0];
                int nextN = n + d[1];

                //다음 좌표가 범위를 벗어난 좌표이면 continue;
                if(nextM < 0 || nextM >= M || nextN < 0 || nextN >= N) continue;
                if(map[m][n] > map[nextM][nextN]){ //내리막길이면
                    dp[m][n] += dfs(nextM, nextN); //다음좌표에서 가능한 경로의 수를 현좌표에 저장                    
                }
            }
        }
        return dp[m][n];
    }
}
