import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[] visited;
    static int max = 1;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[26]; //각 칸이 아닌, 알파벳 방문했는지 체크함
        for(int r = 0; r < R; r++){
            String inputLine = br.readLine();
            for(int c = 0; c < C; c++){
                board[r][c] = inputLine.charAt(c);
            }
        }
        dfs(0, 0, 0);
        System.out.println(max);
    }
    static void dfs(int row, int col, int length){
        if(visited[board[row][col]-'A']){ //이미 방문한 알파벳이면
            max = Math.max(max, length); //지금까지 max길이 갱신
            return;
        }
        visited[board[row][col]-'A'] = true; //현재칸 방문처리
        for(int[] d: directions){
            int nextR = row + d[0];
            int nextC = col + d[1];
            if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) continue;
            dfs(nextR, nextC, length + 1);
        }
        visited[board[row][col]-'A'] = false; //현재칸 방문해제
    }
}