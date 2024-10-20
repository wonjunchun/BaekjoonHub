import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        public int n, m;
        public int time;

        public Node(int n, int m, int time) {
            this.n = n;
            this.m = m;
            this.time = time;
        }
    }
    static int M, N;
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        int result = bfs();
        if(!isAllRipen()) result = -1;
        if(result > 0) result--;
        System.out.println(result);
    }
    static boolean isAllRipen(){
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(map[n][m] == 0){ //덜익은것 있다면 false
                    return false;
                }
            }
        }
        return true;
    }
    static int bfs(){
        int result = 0;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(map[n][m] == 1){
                    queue.add(new Node(n, m, 1));
                    visited[n][m] = true;
                }
            }
        }
        while(!queue.isEmpty()){
            Node current = queue.poll();

            for(int[] d: directions){
                int nextN = current.n + d[0];
                int nextM = current.m + d[1];
                if(nextN < 0 || nextN >= N || nextM < 0 || nextM >= M || visited[nextN][nextM]) continue;
                if(map[nextN][nextM] == 0){ //안익은 과일일때
                    queue.add(new Node(nextN, nextM, current.time+1));
                    result = Math.max(result, current.time + 1);
                    visited[nextN][nextM] = true;
                    map[nextN][nextM] = current.time+1;
                }
            }
        }
        return result;
    }
}
