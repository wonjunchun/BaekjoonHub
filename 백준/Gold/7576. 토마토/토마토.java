import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int M, N; //가로, 세로
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        Deque<int[]> queue = new ArrayDeque<>();

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(map[n][m] == 1) queue.add(new int[]{n, m, 1}); // 큐 명세 {n, m, 지난일수}
            }
        }
        while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int curN = current[0];
            int curM = current[1];
            int curDay = current[2];

            for(int[] d: directions){
                int nextN = curN+d[0];
                int nextM = curM+d[1];
                int nextDay = curDay + 1;

                //다음 좌표가 범위를 벗어나거나, 다음 좌표에 익지 않은 토마토가 없다면, continue
                if(nextN < 0 || nextN >= N || nextM < 0 || nextM >= M || map[nextN][nextM] != 0) continue;
                map[nextN][nextM] = nextDay; //맵에 토마토가 익은 일차 기록
                if(result < nextDay) result = nextDay;
                queue.add(new int[]{nextN, nextM, nextDay});
            }
        }
        boolean isZero = false;

        //0이 하나라도 있다면, 전부 익는 것이 불가능한 경우임
        A: for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
//                System.out.print(map[n][m]+" ");
                if(map[n][m] == 0){
                    isZero = true;
                    break A;
                }
            }
//            System.out.println();
        }

        if(isZero) System.out.println(-1); //전부 익는 게 불가능하면 -1
        else System.out.println(result-1); //전부 익는 데 걸리는 일 수 출력
    }
}
