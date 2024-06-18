import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        visited = new boolean[N][M];
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                visited[n][m] = true;
                result = Math.max(result, dfs(n, m, 0)); //T자 테트로미노 제외한 나머지 테트로미노의 최대값 찾기
                visited[n][m] = false;
                result = Math.max(result, findTMax(n, m));
            }
        }
        System.out.println(result);
    }

    private static int findTMax(int row, int col) { //현재 좌표 기준으로 4가지 T블럭 방향 놓은 경우 중 최대값
        int result = 0;
        if(row-1 >= 0 && col-1 >= 0 && col+1 < M){
            result = Math.max(result, map[row - 1][col] + map[row][col - 1] + map[row][col + 1] + map[row][col]);
        }
        if(col-1 >= 0 && row+1 < N && col+1 < M){
            result = Math.max(result, map[row][col - 1] + map[row + 1][col] + map[row][col + 1] + map[row][col]);
        }
        if(row-1 >= 0 && col-1 >= 0 && row+1 < N){
            result = Math.max(result, map[row - 1][col] + map[row][col - 1] + map[row + 1][col] + map[row][col]);
        }
        if(row-1 >= 0 && row+1 < N && col+1 < M){
            result = Math.max(result, map[row - 1][col] + map[row + 1][col] + map[row][col + 1] + map[row][col]);
        }
        return result;
    }

    static int dfs(int row, int col, int depth){
        int result = 0;
        if(depth == 3){ //테트로미노의 마지막 블록 값
            return map[row][col];
        }
        for(int[] d: directions){
            int nextRow = row + d[0];
            int nextCol = col + d[1];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if(visited[nextRow][nextCol]) continue;
            visited[nextRow][nextCol] = true;
            result = Math.max(result, map[row][col] + dfs(nextRow, nextCol, depth + 1));
            visited[nextRow][nextCol] = false;
        }
        return result;
    }
}
