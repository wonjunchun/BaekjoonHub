import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        long dp[][] = new long[N+1][K+1];
        //점화식 : dp[N][K] = SIGMA_k(1, K) dp[N-1][k]
        //초항
        for(int k = 1; k <= K; k++){
            dp[1][k] = k;
        }
        for(int n = 2; n <= N; n++){
            for(int k = 1; k <= K; k++){
                for(int i = 1; i <= k; i++){
                    dp[n][k] += dp[n-1][i];
                    dp[n][k] %= 1000000000;
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
