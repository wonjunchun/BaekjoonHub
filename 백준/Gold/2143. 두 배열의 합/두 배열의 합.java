import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] arr1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] arr2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //합해서 T가 되는 모든 부 배열쌍의 개수?
        //i) 투포인터로 arr1, arr2에서 구간합 구할 때 특정 숫자 나오는 경우의수 저장(Map에 저장?)
        //ii) arr1의 구간합값 + arr2의 구간합값 = T 가 되는 arr1, arr2의 경우의수를 곱함
        for(int i = 1; i < Math.max(N, M); i++){ //누적합
            if(i < N) arr1[i] += arr1[i - 1];
            if(i < M) arr2[i] += arr2[i - 1];
        }
        int arr1Size = N * (N + 1) / 2;
        int arr2Size = M * (M + 1) / 2;
        //가능한 구간합 조합 다 구함
        long[] arr1Sum = new long[arr1Size];
        long[] arr2Sum = new long[arr2Size];
        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                int arr1Value = arr1[j];
                if(i > 0) arr1Value -= arr1[i - 1];
                arr1Sum[idx++] = arr1Value;
            }
        }
        idx = 0;
        for(int i = 0; i < M; i++){
            for(int j = i; j < M; j++){
                int arr2Value = arr2[j];
                if(i > 0) arr2Value -= arr2[i - 1];
                arr2Sum[idx++] = arr2Value;
            }
        }
        Arrays.sort(arr1Sum);
        Arrays.sort(arr2Sum);
        int left = 0;
        int right = arr2Size-1;
        long count = 0;
        //arr1 구간합값 + arr2 구간합값 = T 되는 경우의 수 세기위해 투포인터 탐색
        while(left < arr1Size && right >= 0){
            long arr1SumVal = arr1Sum[left];
            long arr2SumVal = arr2Sum[right];
            long sum = arr1SumVal + arr2SumVal;
            if(sum == T){
                long arr1Cnt = 0, arr2Cnt = 0;
                while(left < arr1Size && arr1SumVal == arr1Sum[left]){
                    left++;
                    arr1Cnt++;
                }
                while(right >= 0 && arr2SumVal == arr2Sum[right]){
                    right--;
                    arr2Cnt++;
                }
                count += arr1Cnt * arr2Cnt; //각 값이 되는 경우의수의 곱을 누적
            }
            if(sum > T){
                right--;
            }
            else if(sum < T){
                left++;
            }
        }
        System.out.println(count);
    }
}
