import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] directions = {{0, 1}, {1, 1}, {1, 0}};
    static int N;
    static int[][] map;
    static int count = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 1, 0);
        System.out.println(count);
    }
    static void dfs(int row, int col, int dir){
        if(row == N-1 && col == N-1){ //목적지 도달 시
            count++;
            return;
        }
        int start = (dir - 1) < 0 ? 0 : (dir - 1);
        int end = (dir + 1) > 2 ? 2 : (dir + 1);
        for(int d = start; d <= end; d++){
            int nextRow = row + directions[d][0];
            int nextCol = col + directions[d][1];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol]) continue;
            if(map[nextRow][nextCol] == 1) continue; //벽이 있으면 진행 못함
            if(d == 1 && (map[nextRow-1][nextCol] == 1 || map[nextRow][nextCol-1] == 1)) continue; //대각선 시 주변에 벽 있으면 진행불가
            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, d);
            visited[nextRow][nextCol] = false;
        }
    }
}