import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Monkey{
        public int h, w;
        public int moveCnt;
        public int horseMoveCnt;

        public Monkey(int h, int w, int moveCnt, int horseMoveCnt) {
            this.h = h;
            this.w = w;
            this.moveCnt = moveCnt;
            this.horseMoveCnt = horseMoveCnt;
        }
    }
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //4방향 움직임
    static int[][] horseDirections = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}}; //말의 8방향 움직임
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        for(int h = 0; h < H; h++){
            st = new StringTokenizer(br.readLine());
            for(int w = 0; w < W; w++){
                map[h][w] = Integer.parseInt(st.nextToken());
            }
        }
        int result;
        if(H == 1 && W == 1) result = 0;
        else result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        visited = new boolean[H][W][K+1];
        Deque<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Monkey current = queue.pollFirst();
            int curH = current.h;
            int curW = current.w;
            int curMove = current.moveCnt;
            int curHorseMove = current.horseMoveCnt;

            for(int[] d: directions){
                int nextH = curH + d[0];
                int nextW = curW + d[1];
                if(nextH == H-1 && nextW == W-1) return curMove + 1; //목적지 도달 시 종료
                //좌표 범위 벗어나거나, 이미 방문했거나, 장애물인 경우 continue
                if(nextH < 0 || nextH >= H || nextW < 0 || nextW >= W || visited[nextH][nextW][curHorseMove] || map[nextH][nextW] == 1) continue;
                queue.add(new Monkey(nextH, nextW, curMove + 1, curHorseMove));
                visited[nextH][nextW][curHorseMove] = true;
            }
            if(curHorseMove < K){ //아직 말의 움직임 횟수 다 사용하지 않은 경우
                for(int[] h: horseDirections){
                    int nextH = curH + h[0];
                    int nextW = curW + h[1];
                    if(nextH == H-1 && nextW == W-1) return curMove + 1; //목적지 도달 시 종료
                    //좌표 범위 벗어나거나, 이미 방문했거나, 장애물인 경우 continue
                    if(nextH < 0 || nextH >= H || nextW < 0 || nextW >= W || visited[nextH][nextW][curHorseMove+1] || map[nextH][nextW] == 1) continue;
                    queue.add(new Monkey(nextH, nextW, curMove + 1, curHorseMove + 1));
                    visited[nextH][nextW][curHorseMove+1] = true;
                }
            }
        }
        return -1;
    }
}
