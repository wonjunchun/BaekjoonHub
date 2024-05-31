import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            int K = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> pq = new TreeMap<>();
            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                String operator = st.nextToken();
                int operand = Integer.parseInt(st.nextToken());
                switch (operator){
                    case "I":
                        pq.put(operand, pq.getOrDefault(operand, 0) + 1);
                        break;
                    case "D":
                        if(!pq.isEmpty()){
                            if(operand < 0){ //최솟값 삭제 시
                                int key = pq.firstKey();
                                if(pq.get(key) == 1) pq.remove(key);
                                else pq.put(key, pq.get(key) - 1);
                            }
                            else{ //최댓값 삭제 시
                                int key = pq.lastKey();
                                if(pq.get(key) == 1) pq.remove(key);
                                else pq.put(key, pq.get(key) - 1);
                            }
                        }
                        break;
                }
            }
            if(pq.isEmpty()) sb.append("EMPTY").append("\n");
            else{
                sb.append(pq.lastKey()).append(" ").append(pq.firstKey()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
