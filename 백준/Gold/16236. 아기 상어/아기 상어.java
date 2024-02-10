import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Shark implements Comparable<Shark>{
        public int row, col;
        public int time;

        public Shark(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Shark o) {
            if(this.time == o.time){
                if(this.row == o.row){
                    return this.col - o.col;
                }
                else{
                    return this.row - o.row;
                }
            }
            return this.time - o.time;
        }
    }
    static int N, M = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; //위, 왼쪽 우선 탐색
    static int[] start = new int[2]; //아기 상어 시작 위치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(map[n][m] == 9){
                    start[0] = n;
                    start[1] = m;
                    map[n][m] = 0;
                }
            }
        }
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        int size = 2; //상어 크기
        int eat = 0; //먹은 물고기 수
        int move = 0; //움직인 총 크기

        while(true){
            visited = new boolean[N][N];
            PriorityQueue<Shark> queue = new PriorityQueue<>();
            queue.add(new Shark(start[0], start[1], 0));
            visited[start[0]][start[1]] = true;
            boolean check = false;
            while (!queue.isEmpty()) {
                Shark current = queue.poll();
                int curRow = current.row;
                int curCol = current.col;
                int curTime = current.time;
                //이번 먹이를 먹은 후 다음에 시작할 점 미리 기록해둠
                start[0] = curRow;
                start[1] = curCol;
                if(map[curRow][curCol] != 0 && map[curRow][curCol] < size){
                    map[curRow][curCol] = 0; //물고기 제거
                    eat++;
                    move += curTime; //움직인 거리를 총 이동 거리에 누적
                    check = true; //먹이 먹었음을 체크
                    break;
                }
                for(int[] d: directions){
                    int nextRow = curRow + d[0];
                    int nextCol = curCol + d[1];
                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol] ||
                            map[nextRow][nextCol] > size) continue;
                    queue.add(new Shark(nextRow, nextCol, curTime + 1));
                    visited[nextRow][nextCol] = true;
                }
            }
            if(!check){ //큐 전부 비워질동안 먹이 먹은적이 없다면 종료
                break;
            }
            if(size == eat){ //사이즈와 먹이 먹은 수 동일하다면
                size++; //상어 성장
                eat = 0;
            }
        }
        return move;
    }
}
