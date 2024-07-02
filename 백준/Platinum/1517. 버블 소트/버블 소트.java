import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * 1~n 정수 순열 a1, a2, ..., an -> 숫자 단 한번만 등장(중복안됨)
 * 순열 내의 두 정수 -> 큰 수가 작은 수보다 앞에 있으면 inversion이 발생
 * ex) 4 2 7 1 5 6 3 -> 10번의 inversions 발생
 * 4-2, 4-1, 4-3, 2-1, 7-1, 7-5, 7-6, 7-3, 5-3, 6-3
 * inversions 횟수 세라
 * 마치 merge sort시 순서 바꾸는 횟수와 비슷함
 */
public class Main {
    static int[] permutation, temp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        permutation = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        temp = new int[N];
        long result = mergeSort(0, N-1);
        System.out.println(result);
    }
    static long mergeSort(int left, int right){
        long inversionCnt = 0;

        if(left < right){
            int mid = (left + right) / 2;
            inversionCnt += mergeSort(left, mid);
            inversionCnt += mergeSort(mid + 1, right);
            inversionCnt += merge(left, mid, right);
        }
        return inversionCnt;
    }
    static long merge(int left, int mid, int right){ //left~mid 배열1, mid+1~right 배열2 둘이 합침
        int i = left;
        int j = mid + 1;
        int k = left;
        int l;
        long inversionCnt = 0;
        while(i <= mid && j <= right){
            if(permutation[i] <= permutation[j]){ //두 정수 비교시 오름차순이라면
                temp[k++] = permutation[i++]; //temp 배열에 permutation 값 그대로 기록
            }
            else{ //오름차순이 아니라면 inversion 발생(j가 i보다 작으므로)
                inversionCnt += mid - i + 1; //i와 j만 inversion 하는것이 아니라, 배열1에서 i보다 큰 원소 모두와 inversion 발생
                temp[k++] = permutation[j++]; //j가 i보다 작으므로 j를 기록
            }
        }
        if(i > mid){ //배열1 원소 다 기록했다면, 배열2 남은 원소 마저 기록해줌
            for(l = j; l <= right; l++, k++){
                temp[k] = permutation[l];
            }
        }
        else{ //배열2 원소 다 기록했다면, 배열1 남은 원소 마저 기록해줌
            for(l = i; l <= mid; l++, k++){
                temp[k] = permutation[l];
            }
        }

        for(l = left; l <= right; l++){ //정렬 완료된 temp를 permutation에 기록시켜줌
            permutation[l] = temp[l];
        }
        return inversionCnt;
    }
}
