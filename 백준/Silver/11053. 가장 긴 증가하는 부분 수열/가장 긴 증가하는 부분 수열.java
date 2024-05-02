import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];
        for(int i = 0; i < N; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){ //i보다 앞의 구간만 체크하는 용도
                //j가 i보다 작으면서 dp[i]가 dp[j]+1 보다 작으면
                if(sequence[j] < sequence[i] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1; //갱신
                }
            }
        }
        int result = 0;
        for(int i = 0; i < N; i++){
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
