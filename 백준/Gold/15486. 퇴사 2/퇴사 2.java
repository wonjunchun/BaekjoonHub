import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N+1][2];
        StringTokenizer st;
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            schedule[n][0] = Integer.parseInt(st.nextToken());
            schedule[n][1] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[1500051];
        for(int n = 1; n <= N; n++){
            int day = schedule[n][0];
            int cost = schedule[n][1];
            dp[n + day] = Math.max(dp[n + day], dp[n] + cost); //오늘 상담 소요일 이후의 이익과 오늘까지의 이익+상담이익 중 더 큰값
            dp[n + 1] = Math.max(dp[n], dp[n + 1]); //오늘 이익과 내일 이익 중 더 큰값
        }
        System.out.println(dp[N + 1]);
    }
}
