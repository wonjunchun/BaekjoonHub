import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] glasses = new int[n];
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            glasses[i] = Integer.parseInt(br.readLine());
        }
        int result = 0;
        if(n == 1){
            result = glasses[0];
        }
        else if(n == 2){
            result = glasses[0]+glasses[1];
        }
        else{
            dp[0] = glasses[0];
            dp[1] = glasses[0]+glasses[1];
            dp[2] = Math.max(glasses[0]+glasses[2], glasses[1]+glasses[2]);
            dp[2] = Math.max(dp[2], dp[1]); //현재 잔 마신 경우와 안마신 경우 중 최대값 선택
            if(n == 3){
                result = dp[2];
            }
            else{
                for(int i = 3; i < n; i++){
                    //{i-3, i-1, i}, {i-3, i-2, i} 선택하는 경우 중 더 큰 경우를 dp에 넣음
                    dp[i] = Math.max(dp[i-3]+glasses[i-1]+glasses[i], dp[i-2]+glasses[i]);
                    dp[i] = Math.max(dp[i], dp[i-1]); //현재 잔 마신 경우와 안마신 경우 중 최대값
                }
                result = dp[n-1];
            }
        }
        System.out.println(result);
    }
}
