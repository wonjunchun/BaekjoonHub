import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] V = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int v = 1; v <= N; v++){
            V[v] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N+1][M+1]; //해당되는 볼륨에 도달할수 있는지 여부 확인

        dp[0][S] = true; //시작값 도달 가능 표시
        for(int n = 1; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(!dp[n-1][m]) continue; //만약 이전 곡에서 m 볼륨에 도달한 경우의 수가 없다면 continue
                if(m + V[n] <= M){
                    dp[n][m+V[n]] = true; //도달 가능
                }
                if(m - V[n] >= 0){
                    dp[n][m-V[n]] = true;
                }
            }
        }
        int result = -1;
        for(int m = M; m >= 0; m--){ //마지막 곡 볼륨 중 최대값
            if(dp[N][m]){
                result = m; //결과값 갱신 후 탐색종료
                break;
            }
        }
        System.out.println(result);
    }
}