import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int n = 0; n < N; n++){
            map[n] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        //1*1 칸도 입력으로 주어짐
        if(N == 1 && M == 1) return 1;

        Deque<int[]> queue = new LinkedList<>();
        //큐 명세 {n, m, 이동거리(1부터 시작), 벽을 부쉈는지 여부(1이면 부쉈음을 의미)}
        queue.add(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int curN = current[0];
            int curM = current[1];
            int curDist = current[2];
            int isBroken = current[3]; //현재시점의 벽 부쉈는지 여부(1 이면 부쉈음)

            for(int[] d: directions){
                int nextN = curN + d[0];
                int nextM = curM + d[1];

                //목적지 도달했다면 거리 반환 후 종료
                if(nextN == N-1 && nextM == M-1) return curDist + 1;

                //다음 좌표가 좌표 범위 벗어나면 continue
                if(nextN < 0 || nextN >= N || nextM < 0 || nextM >= M) continue;

                //현재 상태(벽 부쉈는지 아닌지)의 방문 체크에서 이미 방문했던곳 또 방문했다면, 최단거리 아님
                if(visited[nextN][nextM][isBroken]) continue;


                //다음칸이 벽이 아니면 이동
                if(map[nextN][nextM] == 0){
                    queue.add(new int[]{nextN, nextM, curDist+1, isBroken});
                    visited[nextN][nextM][isBroken] = true; //방문 처리
                }
                //다음칸이 벽인데, 아직 벽 한번도 안부숴봤다면, 벽 부수고 이동
                if(map[nextN][nextM] == 1 && isBroken == 0){
                    queue.add(new int[]{nextN, nextM, curDist+1, 1});
                    visited[nextN][nextM][1] = true;
                }

            }
        }
        return -1; //bfs 완료 될때까지 목적지 도달 못했다면 -1
    }
}
