import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * x<= k <= y
     * 등차수열의 합 공식 : (x+y)(y-x+1)/2
     * k의 합이 소수여야 한다?
     * => (x+y)(y-x+1)=2*소수
     * i) (y-x+1)이 소수라면, x+y = 2임. 불가능함
     * ii) (x+y)가 소수라면, 항의 개수를 나타내는 (y-x+1) = 2임.
     * 즉, 항 개수가 2개 뿐이니, x, x+1의 합이 소수인지만 판별하면 됨
     * @param args
     */
    public static void main(String[] args) throws IOException {
        boolean[] isPrime = new boolean[500000];
        for(int i = 2; i < 500000; i++){
            int num = i+i+1;
            if(num % 2 == 0) continue; //짝수이면 소수가 아님
            boolean isNotPrime = false;
            for(int j = 3; j <= Math.sqrt(num); j += 2){
                //소수 판별
                if(num % j == 0) {
                    isNotPrime = true;
                    break;
                }
            }
            if(!isNotPrime) isPrime[i] = true;
        }
        int[] primeCnt = new int[500000]; //2부터 num까지 k합이 소수인 애들 개수(누적합)
        int count = 0;
        for(int i = 2; i < 500000; i++){
            if(isPrime[i]) count++;
            primeCnt[i] = count;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            sb.append(primeCnt[R-1]-primeCnt[L-1]).append('\n'); //x+1값도 합해졌으니, R-1, L-1 해줘야
        }
        System.out.println(sb.toString());
    }
}
