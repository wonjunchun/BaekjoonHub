import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] temperatures = new int[N];
        int[] accTemperatures = new int[N];
        for(int n = 0; n < N; n++){
            temperatures[n] = Integer.parseInt(st.nextToken());
            accTemperatures[n] = (n == 0) ? temperatures[n] : temperatures[n] + accTemperatures[n - 1];
        }
        int max = accTemperatures[K-1];
        for(int n = 0; n < N-K; n++){
            max = Math.max(max, accTemperatures[n + K] - accTemperatures[n]);
        }
        System.out.println(max);
    }
}
