import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int count = 0, maxArea = 0;
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
        for(int n = 0; n < N; n++){
            for (int m = 0; m < M; m++) {
                //그림이고, 아직 방문 안했다면
                if(map[n][m] == 1 && !visited[n][m]){
                    int area = bfs(n, m); //해당 그림의 넓이 구함
                    maxArea = Math.max(area, maxArea);
                    count++;
                }
            }
        }
        System.out.println(count);
        System.out.println(maxArea);
    }

    private static int bfs(int curRow, int curCol) {
        int curArea = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(curRow, curCol));
        visited[curRow][curCol] = true;
        curArea++;

        while(!queue.isEmpty()){
            Node current = queue.poll();
            for(int[] d: directions){
                int nextRow = current.row + d[0];
                int nextCol = current.col + d[1];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || visited[nextRow][nextCol]) continue;
                if(map[nextRow][nextCol] != 1) continue;
                queue.add(new Node(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
                curArea++;
            }
        }
        return curArea;
    }
}
