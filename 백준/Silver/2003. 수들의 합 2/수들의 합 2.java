import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int end = 0;
        int sum = arr[0];
        int cnt = 0;
        for(int start = 0; start < N; sum -= arr[start], start++){
            while(end < N-1 && sum < M){
                end++;
                sum += arr[end];
            }
            if(sum == M) cnt++;
        }
        System.out.println(cnt);
    }
}
