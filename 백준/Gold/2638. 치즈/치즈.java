import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Cheese{
        int row, col;

        public Cheese(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Cheese> cheeseList;
    static int cheeseCnt = 0;
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
                if(map[n][m] == 1) cheeseCnt++;
            }
        }
        int time = 0;
        while(true){
            time++;
            cheeseList = new LinkedList<>(); //이번에 녹는 치즈 목록
            visited = new boolean[N][M];
            outsideAir(0, 0); //바깥공기 dfs로 마킹

            for(int n = 0; n < N; n++){
                for(int m = 0; m < M; m++){
                    if(map[n][m] == 1 && melt(n, m) >= 2){
                        cheeseList.add(new Cheese(n, m));
                    }
                }
            }
            for(Cheese cheese: cheeseList){
                map[cheese.row][cheese.col] = 2; //치즈 녹이고 공기 됨
                cheeseCnt--;
            }
            if(cheeseCnt <= 0) break;
        }
        System.out.println(time);
    }

    private static void outsideAir(int row, int col) { //바깥공기 2로 만들고 방문처리해주는 dfs함수
        map[row][col] = 2;
        visited[row][col] = true;
        for(int[] d: directions){
            int nextRow = row + d[0];
            int nextCol = col + d[1];
            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if(!visited[nextRow][nextCol] && (map[nextRow][nextCol] == 0 || map[nextRow][nextCol] == 2)){
                visited[nextRow][nextCol] = true;
                map[nextRow][nextCol] = 2;
                outsideAir(nextRow, nextCol);
            }
        }
    }

    private static int melt(int row, int col) { //해당 칸이 이번에 녹는 치즈인지 체크(2개 면이 외부공기와 맞닿아있으면 녹음)
        int count = 0;
        for(int[] d: directions){
            int nextRow = row + d[0];
            int nextCol = col + d[1];
            if(map[nextRow][nextCol] == 2) count++;
        }
        return count;
    }
}
