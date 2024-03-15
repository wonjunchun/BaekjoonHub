import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int row, col;
        int dist; //이동한 총 거리

        public Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
    static int N, M, min;
    static LinkedList<Node> viruses;
    static int vsize;
    static int[][] map, newMap;
    static boolean[][] visited;
    static Deque<Node> queue;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] combi;
    static int originEmptySpace = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        newMap = new int[N][N];
        viruses = new LinkedList<>();
        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
                if(map[row][col] == 2){
                    viruses.add(new Node(row, col, 0));
                }
                else if(map[row][col] == 0){
                    originEmptySpace++;
                }
            }
        }
        if(originEmptySpace == 0){
            System.out.println(0);
        }
        else{
            vsize = viruses.size();
            combi = new int[M];
            min = Integer.MAX_VALUE;
            combination(0, 0); //조합

            min = (min == Integer.MAX_VALUE) ? -1 : min;
            System.out.println(min);
        }
    }

    static void combination(int cnt, int start) {
        if(cnt == M){
            bfs(originEmptySpace);
            return;
        }
        for(int i = start; i < vsize; i++){
            combi[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }

    static void bfs(int emptySpace) {
        queue = new LinkedList<>();
        visited = new boolean[N][N];
        for(int c: combi){
            queue.add(viruses.get(c));
            visited[viruses.get(c).row][viruses.get(c).col] = true;
        }
        int result = 0;
        for(int n = 0; n < N; n++){
            newMap[n] = map[n].clone(); //newMap 초기화
        }
        while(!queue.isEmpty()){
            Node current = queue.pollFirst();
            int curRow = current.row;
            int curCol = current.col;
            int curDist = current.dist;

            for(int[] d: directions){
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol]) continue;
                if(map[nextRow][nextCol] == 1) continue;
                if(map[nextRow][nextCol] == 0) emptySpace--;
                queue.add(new Node(nextRow, nextCol, curDist + 1));
                visited[nextRow][nextCol] = true;
                newMap[nextRow][nextCol] = 2; //spread 표시
                if(emptySpace == 0){ //모든 칸 다 퍼졌다면
                    min = Math.min(min, curDist + 1);
                    return;
                }
            }
        }
    }
}
