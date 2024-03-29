import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] input = new int[N + 1];
        Map<Integer, Long> map = new HashMap<>(); //<누적합값, 누적합경우의수개수>
        st = new StringTokenizer(br.readLine());
        long answer = 0;
        for(int i = 1; i <= N; i++){
            //i) 누적합 구하기
            input[i] = Integer.parseInt(st.nextToken()) + input[i-1]; //누적합
            if(input[i] == K){ //누적합이 K이면
                answer++; //결과 1 추가
            }
            //ii) 누적합이 A, A-K = B라고 하면
            //B의 경우의 수가 존재한다면, A구간에서 B구간까지 뺀 값이 K가 되므로 이것도 answer에 반영
            if(map.containsKey(input[i] - K)){
                answer += map.get(input[i] - K);
            }
            //iii) 누적된 경우의 수가 존재하면 그 경우의수++, 존재하지 않으면 1로 만들어줌
            if(map.containsKey(input[i])){
                map.put(input[i], map.get(input[i])+1);
            } else{
                map.put(input[i], 1l);
            }
        }
        System.out.println(answer);
    }
}
