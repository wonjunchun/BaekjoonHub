import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static class Point{
        public int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static class Log{
        public Point[] points;
        public int isVertical, moveCnt; //0:수평, 1:수직
        public Log(Point[] p, int isVertical, int moveCnt){
            this.points = p;
            this.isVertical = isVertical;
            this.moveCnt = moveCnt;
        }
    }
    static int N;
    static Point[] begin, end;
    static int[][] map;
    static boolean[][][] visited; //N*N*2 -> 수직, 수평 이동시 두가지 케이스로 나눔
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N][2];
        begin = new Point[3];
        end = new Point[3];
        int beginCnt = 0, endCnt = 0;
        for(int y = 0; y < N; y++){
            String curLine = br.readLine();
            for (int x = 0; x < N; x++) {
                char curChar = curLine.charAt(x);
                if(curChar == 'B'){
                    begin[beginCnt] = new Point(y, x);
                    beginCnt++;
                    map[y][x] = 0;
                }
                else if(curChar == 'E'){
                    end[endCnt] = new Point(y, x);
                    endCnt++;
                    map[y][x] = 0;
                }
                else{
                    map[y][x] = Integer.parseInt(Character.toString(curChar));
                }
            }
        }
        int result = bfs();
        System.out.println(result);
    }
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //U, D, L, R

    static int bfs() {
        int isVertical = 0;
        if(begin[0].x == begin[1].x) isVertical = 1;
        Deque<Log> queue = new LinkedList<>();
        queue.add(new Log(begin, isVertical, 0));
        //방문여부 체크 시, 통나무의 중앙 좌표만 체크
        visited[begin[1].y][begin[1].x][isVertical] = true;
        Q: while (!queue.isEmpty()) {
            Log current = queue.pollFirst();
            Point[] curPoint = current.points;
            int curVertical = current.isVertical;
            int curMove = current.moveCnt;

            //U, D, L, R 순회 후, T 해보기
            for(int[] d: directions){
                Point[] nextPoint = move(curPoint, d); //일단 다음 좌표 구해놓기
                //만약 다음 좌표가 종점이면, 그냥 종료
                if(isSamePoint(nextPoint, end)){
                    return curMove + 1;
                }
                //해당 방향으로 이동 가능하고, 아직 방문 안했다면
                if(isMoveAvail(nextPoint) && !visited[nextPoint[1].y][nextPoint[1].x][curVertical]){
                    visited[nextPoint[1].y][nextPoint[1].x][curVertical] = true;
                    queue.add(new Log(nextPoint, curVertical, curMove + 1));
                }
            }
            //이제, 회전 시켜보기
            int nextVertical = curVertical == 0 ? 1 : 0;
            Point[] nextPoint = rotate(curPoint, nextVertical);
            //만약 다음 좌표가 종점이면, 그냥 종료
            if(isSamePoint(nextPoint, end)){
                return curMove + 1;
            }
            if(isRotateAvail(nextPoint) && !visited[nextPoint[1].y][nextPoint[1].x][nextVertical]){
                visited[nextPoint[1].y][nextPoint[1].x][nextVertical] = true;
                queue.add(new Log(nextPoint, nextVertical, curMove + 1));
            }
        }

        return 0; //도달할 수 없다면 0 반환
    }

    //회전 가능한지 체크(다음 좌표 기준으로)
    static boolean isRotateAvail(Point[] nextPoint) {
        //i) 해당 다음좌표가 유효한지 체크
        if(!isMoveAvail(nextPoint)) return false;
        //ii) 해당 다음좌표 중앙 기준 3*3 이내에 나무가 없는지 체크
        for(int y = nextPoint[1].y-1; y <= nextPoint[1].y+1; y++){
            for(int x = nextPoint[1].x-1; x <= nextPoint[1].x+1; x++){
                if(map[y][x] == 1) return false; //나무 하나라도 있으면 회전 불가
            }
        }
        return true;
    }

    static Point[] rotate(Point[] curPoint, int nextVertical) {
        Point[] newPoint = new Point[3];
        for(int i = 0; i < 3; i++){
            newPoint[i] = new Point(0, 0);
        }
        if(nextVertical == 0){ //수평 방향으로 회전시킴
            newPoint[0].y = curPoint[1].y;
            newPoint[0].x = curPoint[1].x - 1;
            newPoint[1].y = curPoint[1].y;
            newPoint[1].x = curPoint[1].x;
            newPoint[2].y = curPoint[1].y;
            newPoint[2].x = curPoint[1].x + 1;
        }
        else{ //수직 방향으로 회전시킴
            newPoint[0].y = curPoint[1].y - 1;
            newPoint[0].x = curPoint[1].x;
            newPoint[1].y = curPoint[1].y;
            newPoint[1].x = curPoint[1].x;
            newPoint[2].y = curPoint[1].y + 1;
            newPoint[2].x = curPoint[1].x;
        }
        return newPoint;
    }

    //두 점의 좌표가 같은지 체크
    static boolean isSamePoint(Point[] src, Point[] dst) {
        for(int i = 0; i < 3; i++){
            if(src[i].y != dst[i].y || src[i].x != dst[i].x) return false;
        }
        return true;
    }

    //다음 좌표 구하는 메서드
    static Point[] move(Point[] curPoint, int[] d) {
        Point[] newPoint = new Point[3];
        for(int i = 0; i < 3; i++){
            newPoint[i] = new Point(curPoint[i].y + d[0], curPoint[i].x + d[1]);
        }
        return newPoint;
    }

    //새로운 좌표가 유효한 좌표인지 여부 확인
    static boolean isMoveAvail(Point[] nextPoint) {
        for(int i = 0; i < 3; i++){
            //i) 새 좌표가 좌표 범위를 벗어나는지 체크
            if(nextPoint[i].y < 0 || nextPoint[i].y >= N || nextPoint[i].x < 0 || nextPoint[i].x >= N){
                return false;
            }
            //ii) 새 좌표에 아직 베지않은 나무가 있는지 체크(map[y][x] == 1인지 체크)
            if(map[nextPoint[i].y][nextPoint[i].x] == 1) return false;
        }
        return true;
    }
}
