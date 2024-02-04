import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, resultMap;
    static int N, M;
    static boolean[][] visited;
    static int[] target; //목표지점 좌표
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Point{
        public int n, m, distance;
        public Point(int n, int m, int distance){
            this.n = n;
            this.m = m;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        resultMap = new int[N][M];
        visited = new boolean[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(map[n][m] == 2) target = new int[]{n, m};
            }
        }
        bfs();
        unreachableCheck();
        printMap();
    }

    static void printMap() {
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                System.out.print(resultMap[n][m] + " ");
            }
            System.out.println();
        }
    }

    static void unreachableCheck() {
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(!visited[n][m] && map[n][m] != 0){
                    resultMap[n][m] = -1;
                }
            }
        }
    }

    static void bfs() {
        Deque<Point> queue = new LinkedList<>();
        queue.add(new Point(target[0], target[1], 0));
        resultMap[target[0]][target[1]] = 0;
        visited[target[0]][target[1]] = true;

        while (!queue.isEmpty()) {
            Point current = queue.pollFirst();
            int curN = current.n;
            int curM = current.m;
            int curDist = current.distance;

            for (int[] d : directions) {
                int nextN = curN + d[0];
                int nextM = curM + d[1];
                if(nextN < 0 || nextN >= N || nextM < 0 || nextM >= M || visited[nextN][nextM]) continue;
                if(map[nextN][nextM] == 1){
                    queue.add(new Point(nextN, nextM, curDist + 1));
                    resultMap[nextN][nextM] = curDist + 1;
                    visited[nextN][nextM] = true;
                }
            }
        }
    }
}
