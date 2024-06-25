import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());
        long[] array = new long[(int) N + 2];
        long result = 0l;

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            array[n] = Integer.parseInt(st.nextToken());
        }

        if(B < C){ //B가 C보다 작다면, 무조건 B 사는게 이득
            for(int n = 0; n < N; n++){
                result += array[n];
            }
            System.out.println(result*B);
        }
        else{
            for(int n = 0; n < N; n++){
                //i) 두번째 값이 세번째 값보다 큰 경우
                if(array[n+1] > array[n+2]){
                    //첫번째 공장과 두번째 공장에서 라면 최대한 사고
                    long count = Math.min(array[n], array[n+1]-array[n+2]);
                    result += (B+C) * count; //5원씩 소비
                    array[n] -= count;
                    array[n+1] -= count;

                    //첫번째, 두번째, 세번째 공장에서 라면 최대한 구매
                    count = Math.min(array[n], Math.min(array[n + 1], array[n + 2]));
                    result += (B+(2*C)) * count; //7원씩 소비
                    array[n] -= count;
                    array[n+1] -= count;
                    array[n+2] -= count;
                }
                //ii) 세번째 값이 두번째 값보다 큰 경우
                else{
                    //첫번째, 두번째, 세번째 공장에서 라면 최대한 구매
                    long count = Math.min(array[n], Math.min(array[n + 1], array[n + 2]));
                    result += (B+(2*C)) * count;
                    array[n] -= count;
                    array[n+1] -= count;
                    array[n+2] -= count;

                    //첫번째, 두번째 공장에서 라면 최대한 구매
                    count = Math.min(array[n], array[n + 1]);
                    result += (B+C) * count;
                    array[n] -= count;
                    array[n+1] -= count;
                }
                result += B * array[n]; //남은 라면은 하나씩 구매
            }
            System.out.println(result);
        }
    }
}
