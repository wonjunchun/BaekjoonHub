import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) return o1 - o2;
                else return Math.abs(o1) - Math.abs(o2);
            }
        });
        for(int n = 0; n < N; n++){
            int x = Integer.parseInt(br.readLine());
            if(x == 0){ //x가 0인 경우, pq의 절댓값이 가장 작은 값 출력
                int poll = 0;
                if(!pq.isEmpty()){
                    poll = pq.poll();
                }
                System.out.println(poll);
            }
            else pq.add(x); //그 외의 경우 pq에 x넣음
        }
    }
}
