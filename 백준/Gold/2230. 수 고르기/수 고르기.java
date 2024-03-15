import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for(int n = 0; n < N; n++){
            arr[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int tail = 0;
        int difference = Integer.MAX_VALUE;
        for(int head = 0; head < N; head++){
            //차이 M미만이면 tail을 이동시켜줌
            while(tail < N && arr[tail]-arr[head] < M) tail++;
            if(tail == N) break; //tail이 범위 벗어나면 종료
            difference = Math.min(difference, arr[tail] - arr[head]);
        }
        System.out.println(difference);
    }
}
