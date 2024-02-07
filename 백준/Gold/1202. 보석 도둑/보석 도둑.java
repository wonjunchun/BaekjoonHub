import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Jewelry implements Comparable<Jewelry>{
        public int mass;
        public int value;

        public Jewelry(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            if(this.mass == o.mass) //질량이 같다면 가치 내림차순으로
                return this.value - o.value;
            return this.mass - o.mass; //질량 오름차순
        }
    }
    public static void main(String[] args) throws IOException {
        int N, K;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];
        int[] bags = new int[K];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries[n] = new Jewelry(m, v);
        }
        for(int k = 0; k < K; k++){
            bags[k] = Integer.parseInt(br.readLine());
        }
        //보석과 가방 정렬해줌
        Arrays.sort(jewelries);
        Arrays.sort(bags);

        //우선순위큐에 보석의 가치 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long result = 0;

        for(int n = 0, k = 0; k < K; k++){
            while(n < N && jewelries[n].mass <= bags[k]){ //보석 무게가 현재 가방에 들어갈 정도면
                pq.add(jewelries[n++].value); //pq에 보석 가치 넣고 다음 보석 체크
            }

            if (!pq.isEmpty()) {
                result += pq.poll(); //현재 가방에 수납가능 보석 중 가장 큰 가치 가진 값 더함
            }
        }
        System.out.println(result);
    }
}
