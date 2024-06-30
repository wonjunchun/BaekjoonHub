import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0, right = N-1;
        int min = Integer.MAX_VALUE;
        int result = -1;
        while(left < right){
            int sum = arr[right] + arr[left];
            if(Math.abs(sum) < min){ //두 값 합이 기존 값들보다 0에 좀 더 가깝다면, 갱신
                min = Math.abs(sum);
                result = sum;
            }
            if(sum > 0){ //합이 0보다 크면, right--
                right--;
            }
            else{
                left++;
            }
        }
        System.out.println(result);
    }
}
