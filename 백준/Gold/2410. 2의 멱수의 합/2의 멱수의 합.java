import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N + 1];
        dp[1] = 1;
        if(N >= 2)
            dp[2] = 2;
        if(N >= 3)
            for(int n = 3; n <= N; n++){
                //n이 홀수일 때는 n-1의 개수 그대로 따라감
                if(n % 2 != 0){
                    dp[n] = dp[n - 1];
                }
                //n이 짝수일 때는 dp[n-1] + dp[n/2] 따라감
                else{
                    dp[n] = (dp[n - 1] + dp[n / 2]) % 1000000000;
                }
            }
        System.out.println(dp[N]);
    }
}
