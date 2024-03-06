import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] price = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++){
                price[n] = Integer.parseInt(st.nextToken());
            }
            int max = price[N - 1];
            long answer = 0;
            for(int i = N-2; i >= 0; i--){ //역순으로 탐색
                if(price[i] <= max){
                    answer += max - price[i]; //현재 얻을 수 있는 이익 누적
                }
                else{ //max보다 현재 가격이 더 크면, max값 갱신
                    max = price[i];
                }
            }
            System.out.println(answer);
        }
    }
}
