import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N+1][3]; //각 집별 RGB가격
        int[][] dp = new int[N+1][3];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 3; c++){
                cost[n][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int first = 0; first < 3; first++){ //첫번째 집의 색상 선택
            for(int c = 0; c < 3; c++){
                if(c == first){
                    dp[0][c] = cost[0][c];
                }
                else{
                    dp[0][c] = Integer.MAX_VALUE;
                }
            }
            for(int n = 1; n < N; n++){ //2~N번째 집까지의 색상 선택 후 선택시의 최소비용 누적
//                dp[n][0] = Math.min(dp[n-1][1], dp[n-1][2]) + cost[n][0];
//                dp[n][1] = Math.min(dp[n-1][0], dp[n-1][2]) + cost[n][1];
//                dp[n][2] = Math.min(dp[n-1][0], dp[n-1][1]) + cost[n][2];
                for(int c = 0; c < 3; c++){
                    dp[n][c] = Math.min(dp[n-1][(c+1)%3], dp[n-1][(c+2)%3]);
                    if(dp[n][c] != Integer.MAX_VALUE) dp[n][c] += cost[n][c];
                }
            }
            for(int c = 0; c < 3; c++){ //마지막 집까지의 누적값 중 최소값 추리기 위함
                if(c == first){ //처음 집과 색상이 같은 경우는 continue
                    continue;
                }
                answer = Math.min(answer, dp[N-1][c]);
            }
        }
        System.out.println(answer);
    }
}
