import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int[] ascDp;
    static int[] descDp;
    static int[] sequence;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sequence = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ascDp = new int[N]; //가장 긴 증가 부분수열 길이 저장
        descDp = new int[N]; //가장 긴 감소 부분수열 길이 저장

        for(int n = 0; n < N; n++){
            LIS(n);
            LDS(n);
        }
        int result = 0;
        for(int n = 0; n < N; n++){
            result = Math.max(result, ascDp[n] + descDp[n]);
        }
        System.out.println(result-1);

    }

    private static int LDS(int N) {
        if(descDp[N] == 0){
            descDp[N] = 1;
            //N 이후 노드들 탐색
            for(int n = N+1; n < descDp.length; n++){
                //이후 노드 중 sequence[N]값보다 작은 값 발견 시
                if(sequence[n] < sequence[N]){
                    descDp[N] = Math.max(descDp[N], LDS(n) + 1);
                }
            }
        }
        return descDp[N];
    }

    private static int LIS(int N) {
        if(ascDp[N] == 0){ //아직 탐색하지 않은 위치의 경우
            ascDp[N] = 1;
            //N 이전 노드들 탐색
            for(int n = N-1; n >= 0; n--){
                //이전 노드 중 sequence[N}값 보다 작은 값 발견시
                if(sequence[n] < sequence[N]){
                    ascDp[N] = Math.max(ascDp[N], LIS(n) + 1);
                }
            }
        }
        return ascDp[N];
    }
}
