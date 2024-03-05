import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point>{
        int row, col;
        int cost;

        public Point(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }
    static int N;
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            map = new int[N][N];
            for(int row = 0; row < N; row++){
                st = new StringTokenizer(br.readLine());
                for(int col = 0; col < N; col++){
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            int result = bfs();
            System.out.printf("Problem %d: %d\n", testcase++, result);
        }
    }

    static int bfs() { //최단경로를 찾기위한 bfs(dijkstra)
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int costs[][] = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        pq.add(new Point(0, 0, map[0][0]));
        while(!pq.isEmpty()){
            Point current = pq.poll();
            int curRow = current.row;
            int curCol = current.col;
            int curCost = current.cost;
            visited[curRow][curCol] = true; //방문처리
            costs[curRow][curCol] = curCost;

            if(curRow == N-1 && curCol == N-1) break;

            for(int[] d: directions){
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol]) continue;
                pq.add(new Point(nextRow, nextCol, curCost + map[nextRow][nextCol]));
            }
        }
        return costs[N-1][N-1];
    }
}
