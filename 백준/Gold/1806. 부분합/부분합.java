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
        int S = Integer.parseInt(st.nextToken());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int end = 0;
        int subtotal = arr[0];
        int minLength = Integer.MAX_VALUE;
        for(int start = 0; start < N; subtotal-=arr[start] ,start++){
            while(end < N-1 && subtotal < S){ //부분합이 S미만이면 end포인터 이동
                end++;
                subtotal += arr[end]; //새로운 값 부분합에 포함
            }
            if(subtotal >= S){
                minLength = Math.min(minLength, end - start + 1);
            }
        }
        int result = minLength == Integer.MAX_VALUE ? 0 : minLength;
        System.out.println(result);
    }
}
