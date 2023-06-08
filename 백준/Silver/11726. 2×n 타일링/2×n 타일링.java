import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    /**
     * 2n타일링
     * [아이디어]
     * 일단 2*1타일 채우는 경우 1가지임. f(1) = 1
     * 2*2 타일 채우는 경우 2가지임. f(2) = 2
     * 2*n타일을 채우는경우를 2*1로 시작하는 경우와 2*2로 시작하는 경우로 나눠서 생각하기
     * f(3) = 2*3타일 채우는 경우의 수
     * - 2*1 + 2*(3-1) : f(2)
     * - 2*2 + 2*(3-2) : 2f(1)
     * - 근데, 2*1 + 2*1 + 2*1인 경우가 겹침. f(1) 한번 빼주기
     * - f(3) = f(2) + f(1)
     *
     * 일반항 f(n) = f(n-1) + f(n-2)
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BigInteger[] dp = new BigInteger[1001]; //0~1000. 경우의 수 저장
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("2");
        //f(n) = f(n-1) + 2f(n-2)
        for(int i = 3; i <= 1000; i++){
            //dp[i] = dp[i-1] + dp[i-2];
            dp[i] = dp[i-1].add(dp[i-2]);
        }
        int n = sc.nextInt(); //입력값
        System.out.println(dp[n].mod(new BigInteger("10007")).toString());
    }
}
