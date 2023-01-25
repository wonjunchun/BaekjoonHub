import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //시간초과 해결용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] house = new int[N];
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for(int n = 0; n < N; n++){
            house[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(house);

        System.out.println(house[(N-1)/2]); //정 중앙에 있는 집에 설치해야 거리합이 제일 작음

    }
}