import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] acc = new int[N]; //실수 횟수 누적합
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            input[n] = Integer.parseInt(st.nextToken());
            if(n != 0 && input[n] < input[n-1]){ //실수 시
                acc[n] = acc[n-1] + 1;
            }
            else if(n != 0){
                acc[n] = acc[n-1];
            }
        }
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            sb.append(acc[y] - acc[x]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
