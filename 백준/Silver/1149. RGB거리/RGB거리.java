import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] colors = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];
        StringTokenizer st;
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            colors[n][0] = Integer.parseInt(st.nextToken());
            colors[n][1] = Integer.parseInt(st.nextToken());
            colors[n][2] = Integer.parseInt(st.nextToken());
        }
        dp[1][0] = colors[1][0];
        dp[1][1] = colors[1][1];
        dp[1][2] = colors[1][2];

        for(int n = 2; n <= N; n++){
            dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + colors[n][0];
            dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + colors[n][1];
            dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + colors[n][2];
        }
        int result = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
        System.out.println(result);
    }
}
