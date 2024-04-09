import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static class Graph implements Comparable<Graph>{
        int row, col;
        int dist, changeCnt;

        public Graph(int row, int col, int dist, int changeCnt) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.changeCnt = changeCnt;
        }

        @Override
        public int compareTo(Graph o) { //방 바꾼 횟수 오름차순으로 정렬되도록
            return this.changeCnt-o.changeCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int row = 0; row < N; row++){
            map[row] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        int result = dijkstra();
        System.out.println(result);
    }

    private static int dijkstra() {
        visited = new boolean[N][N];
        PriorityQueue<Graph> queue = new PriorityQueue<>();
        queue.add(new Graph(0, 0, 0, 0));
        visited[0][0] = true;
        while(!queue.isEmpty()){
            Graph current = queue.poll();
            int curRow = current.row;
            int curCol = current.col;
            int curDist = current.dist;
            int curChangeCnt = current.changeCnt;

            for(int[] d: directions){
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol]) continue;
                if(nextRow == N-1 && nextCol == N-1){
                    if(map[nextRow][nextCol] == 0){
                        return curChangeCnt+1;
                    }
                    else return curChangeCnt;
                }
                if(map[nextRow][nextCol] == 0){ //검은방은 흰방으로 바꿔야
                    queue.add(new Graph(nextRow, nextCol, curDist + 1, curChangeCnt + 1));
                }
                else{
                    queue.add(new Graph(nextRow, nextCol, curDist + 1, curChangeCnt));
                }
                visited[nextRow][nextCol] = true;
            }
        }
        return 0;
    }
}
