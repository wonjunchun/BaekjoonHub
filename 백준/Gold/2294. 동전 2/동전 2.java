import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //동전 종류
        int K = Integer.parseInt(st.nextToken()); //동전 가치합
        int[] coins = new int[N];
        int[] dp = new int[K + 1]; //가치합을 만족하는 최소 동전수 저장하는 배열
        for(int n = 0; n < N; n++){
            coins[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins); //오름차순 정렬
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for(int c = 0; c < N; c++){ //동전 종류별로 순회하면서 dp테이블 갱신
            for(int i = coins[c]; i <= K; i++){ //현재 동전 단위부터 순회
                dp[i] = Math.min(dp[i], dp[i-coins[c]]+1); //이전 동전의 동전수와 현재 동전 포함한 동전수 중 최소값
            }
        }
        int result = dp[K] == Integer.MAX_VALUE - 1 ? -1 : dp[K];
        System.out.println(result);
    }
}
