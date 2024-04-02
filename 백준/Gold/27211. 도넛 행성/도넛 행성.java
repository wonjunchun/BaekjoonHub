import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(map[n][m] == 0 && !visited[n][m]){
                    bfs(n, m);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static void bfs(int row, int col) {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int curRow = current[0];
            int curCol = current[1];

            for(int[] d: directions){
                int nextRow = (N + curRow + d[0]) % N;
                int nextCol = (M + curCol + d[1]) % M;
                if(visited[nextRow][nextCol] || map[nextRow][nextCol] == 1) continue;
                queue.add(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
    }
}
