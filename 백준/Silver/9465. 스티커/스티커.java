import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];
            sticker[0] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sticker[1] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] dp = new int[2][N+1];
            dp[0][1] = sticker[0][0];
            dp[1][1] = sticker[1][0];
            for(int n = 2; n <= N; n++){
                //반대편 행의 n-1, n-2값 중 최대를 택함
                dp[0][n] = Math.max(dp[1][n - 1], dp[1][n - 2]) + sticker[0][n - 1];
                dp[1][n] = Math.max(dp[0][n - 1], dp[0][n - 2]) + sticker[1][n - 1];
            }
            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
