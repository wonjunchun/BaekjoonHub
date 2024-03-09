import java.util.Scanner;

/**
 * 점화식
 * f(n) = f(n-1) + 2*f(n-2)
 */
public class Main {
    public static void main(String[] args){
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int n = 3; n <= N; n++){
            dp[n] = (dp[n - 1] + 2 * dp[n - 2]) % 10007;
        }
        System.out.println(dp[N]);
    }
}
