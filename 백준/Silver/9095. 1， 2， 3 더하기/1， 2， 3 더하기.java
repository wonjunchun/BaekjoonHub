import java.util.Scanner;

public class Main {
    /**
     * n을 1, 2, 3의 합으로 나타내는 경우의 수?
     * <아이디어>
     *     1, 2, 3의 합으로 나타낸다는 것은,
     *     1+(n-1)
     *     2+(n-2)
     *     3+(n-3)
     *     으로 나타낸다는 것과 같음
     *     따라서
     *     n에 대한 경우의 수를 나타내는 f(n)은
     *     f(n) = f(n-3)+f(n-2)+f(n-1) 이 성립함
     *     f(1) = 1, f(2) = 2, f(3) = 4
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] dp = new int[11]; //11보다 작은 양수. 경우의 수 저장하는 배열
        int n;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i <= 10; i++){
            dp[i] = dp[i-3]+dp[i-2]+dp[i-1]; // 값 저장
        }
        for(int t = 0; t < T; t++){
            n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
