import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * 2분할
     * 1. 미세먼지 확산
     *  => 확산된 먼지 newMap에 누적시킨 후, map이 newMap을 참조하도록함(갱신)
     * 2. 공기청정
     *  => 공기청정 위아래 부분으로 나누어, 각각 반시계, 시계방향 회전
     * T초 후 방에 남은 미세먼지양 구하기
     * @param args
     */
    static int R, C, T;
    static int upCleaner, downCleaner; //청정기 위 아래 부분의 행좌표 저장
    static int[][] map, newMap;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int dustCnt; //미세먼지수 cnt
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        boolean visitedClnr = false; //이미 청정기 방문했는지
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == -1){
                    if(!visitedClnr){ //아직 청정기 방문 안했다면
                        //청정기 윗부분임
                        upCleaner = r;
                        visitedClnr = true;
                    }
                    else{
                        downCleaner = r;
                    }
                }
            }
        }

        for(int t = 0; t < T; t++){ //T초동안 반복
            //1. 미세먼지 확산
            spread();
            //2. 공기청정기 동작
            clean();
            //3. 미세먼지 수 셈
            dustCnt = countDust();
            if(dustCnt == 0) break; //이미 미세먼지 0이면 종료
        }
        System.out.println(dustCnt);
    }

    private static int countDust() {
        int cnt = 0;
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(map[r][c] > 0) cnt += map[r][c]; //미세먼지값 누적
            }
        }
        return cnt;
    }
    private static void print(int[][] maps){
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                System.out.print(maps[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void clean() { // 공기청정기 동작
        newMap = new int[R][C];
//        newMap[upCleaner][0] = newMap[downCleaner][0] = -1; //공기청정기 기록
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                newMap[r][c] = map[r][c]; //일단 모든 값 복사
            }
        }
//        print(map);
        upcycle();
//        print(newMap);
        downcycle();
//        print(newMap);

        map = newMap; //맵갱신
    }


    private static void downcycle() {
        for(int r = downCleaner+2; r < R; r++){ //맨 왼쪽열 위로 올라감(청정기 있음을 유의)
            newMap[r-1][0] = map[r][0];
        }
        for(int c = 1; c < C; c++){
            newMap[R-1][c-1] = map[R-1][c];
        }
        for(int r = downCleaner; r < R-1; r++){
            newMap[r+1][C-1] = map[r][C-1];
        }
        for(int c = 0; c < C-1; c++){
            if(c == 0) newMap[downCleaner][c+1] = 0; //청정기에서 나온 바람
            else newMap[downCleaner][c+1] = map[downCleaner][c];
        }
    }

    private static void upcycle() { //위쪽 반시계방향 회전
        for(int r = 0; r < upCleaner-1; r++){ //맨 왼쪽열 아래로 내려옴(청정기 있음 유의)
            newMap[r+1][0] = map[r][0];
        }
        for(int c = 0; c < C-1; c++){
            if(c == 0) newMap[upCleaner][c+1] = 0; //청정기로부터 나온 바람
            else newMap[upCleaner][c+1] = map[upCleaner][c];
        }
        for(int r = 1; r <= upCleaner; r++){
            newMap[r-1][C-1] = map[r][C-1];
        }
        for(int c = 1; c < C; c++){
            newMap[0][c-1] = map[0][c];
        }
    }

    private static void spread() { //미세먼지 확산
        newMap = new int[R][C]; // newMap 초기화
        newMap[upCleaner][0] = newMap[downCleaner][0] = -1; //공기청정기 기록
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(map[r][c] > 0){ //미세먼지가 있다면
                    dustSpread(r, c); //그 칸 확산
                }
            }
        }
        //다 확산됐다면, map이 newMap을 참조하도록
        map = newMap;
    }

    private static void dustSpread(int row, int col) { //미세먼지 하나 확산시켜줌
        int dust = map[row][col]; //해당 칸의 미세먼지양
        int subDust = dust/5; //확산될 미세먼지의 양
        int spreadCnt = 0; //확산된 방향의 개수
        for(int[] d: directions){ //4방향탐색
            int newRow = row+d[0];
            int newCol = col+d[1];
            if(newRow < 0 || newRow >= R || newCol < 0 || newCol >= C) continue;
            if(map[newRow][newCol] == -1) continue; //공기청정기 있는 칸은 확산 안함
            
            //확산한 먼지 newMap에 써줌(누적)
            newMap[newRow][newCol] += subDust;
            spreadCnt++;
        }
        //4방향 확산 끝났으면, 기존칸에도 남은먼지 써줌(누적)
        newMap[row][col] += dust - (subDust*spreadCnt);
    }
}
