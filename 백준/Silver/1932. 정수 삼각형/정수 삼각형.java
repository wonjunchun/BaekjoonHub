import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N][N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m <= n; m++){
                triangle[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[N][N];
        dp[N-1] = triangle[N-1].clone();
        for(int n = N-2; n >= 0; n--){
            for(int m = 0; m <= n; m++){
                dp[n][m] = Math.max(dp[n+1][m], dp[n+1][m+1]) + triangle[n][m];
            }
        }
        System.out.println(dp[0][0]);
    }
}
