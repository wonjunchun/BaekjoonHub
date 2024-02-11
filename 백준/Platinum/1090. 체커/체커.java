import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] checkers = new int[N][2];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            checkers[n][0] = Integer.parseInt(st.nextToken());
            checkers[n][1] = Integer.parseInt(st.nextToken());
        }
        int[] result = new int[N];
        Arrays.fill(result, Integer.MAX_VALUE);
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int x = checkers[i][0]; //체커를 모을 칸의 x좌표
                int y = checkers[j][1]; //체커를 모을 칸의 y좌표
                PriorityQueue<Integer> pq = new PriorityQueue<>(); //목표 좌표와 체커 간의 거리 최소값 구할 pq
                int[] distance = new int[N]; //현재 목표좌표에서의 체커들이 같은칸에 모이기위한 최소이동횟수
                for(int k = 0; k < N; k++){
                    int curDistance = Math.abs(x - checkers[k][0]) + Math.abs(y - checkers[k][1]); //맨해튼거리 구함
                    pq.add(curDistance);
                }
                distance[0] = pq.poll();
                for(int k = 1; k < N; k++){
                    distance[k] = distance[k - 1] + pq.poll();
                }
                for(int k = 0; k < N; k++){
                    result[k] = Math.min(result[k], distance[k]);
                }
            }
        }
        for(int r: result){
            System.out.print(r + " ");
        }
    }
}
