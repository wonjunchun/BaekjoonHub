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
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> findByName = new HashMap<>();
        Map<Integer, String> findByNum = new HashMap<>();
        for(int n = 1; n <= N; n++){
            String input = br.readLine();
            findByName.put(input, n);
            findByNum.put(n, input);
        }
        for(int m = 0; m < M; m++){
            String input = br.readLine();
            if(input.charAt(0) >= 48 && input.charAt(0) <= 57){ //숫자 입력인 경우
                System.out.println(findByNum.get(Integer.parseInt(input)));
            }
            else{ //문자 입력한 경우
                System.out.println(findByName.get(input));
            }
        }
    }
}
