import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, M;
    static List<Shark>[][] map;
    static int total; //낚시왕이 잡은 상어 총 크기
    static int[][] directions = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static class Shark{
        int r;
        int c;
        int s;
        int d;
        int z;
        public Shark(){

        }

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new LinkedList[R+1][C+1];
        for(int r = 1; r <= R; r++){
            for(int c = 1; c <= C; c++){
                map[r][c] = new LinkedList<>();
            }
        }

        //r, c, s, d, z정보 map에 입력
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[r][c].add(new Shark(r, c, s, d, z));
        }
        int time = 0;
        total = 0;
        while(time < C){
            time++; //낚시왕이 오른쪽으로 한칸 이동
            
            catchShark(time); //낚시왕이 있는 열에 있는 상어 중 가장 땅과 가까운 상어 잡음

            moveShark(); //상어 이동함
        }
        System.out.println(total);

    }
    //3. 상어 이동함
    //이동하려는 칸이 격자 칸 넘으면 방향 바꿔줌 (일반화한 공식 필요할듯)
    private static void moveShark() {
        Deque<Shark> queue = new ArrayDeque<>();
        for(int r = 1; r <= R; r++){
            for(int c = 1; c <= C; c++){
                if(!map[r][c].isEmpty()){
                    Shark shark = map[r][c].remove(0);
                    int curR = shark.r;
                    int curC = shark.c;
                    int speed = shark.s;
                    int dir = shark.d;
                    //상어 스피드만큼 이동
                    while(speed > 0){
                        int nextR = curR + directions[dir][0];
                        int nextC = curC + directions[dir][1];
                        //좌표 벗어났다면, 보정
                        if(nextR == 0){ //윗경계 만나면 아래로
                            dir = 2;
                        }
                        else if(nextR == R+1){ //아랫경계 만나면 위로
                            dir = 1;
                        }
                        else if(nextC == 0){ //왼쪽경계 만나면 오른쪽으로
                            dir = 3;
                        }
                        else if(nextC == C+1){ //오른쪽경계 만나면 왼쪽으로
                            dir = 4;
                        }
                        //다음 좌표로 이동
                        curR += directions[dir][0];
                        curC += directions[dir][1];

                        speed--;
                    }
                    //상어의 좌표와 방향 갱신
                    shark.r = curR;
                    shark.c = curC;
                    shark.d = dir;
                    //큐에 넣음
                    queue.add(shark);
                }
            }
        }
        while(!queue.isEmpty()){ //이동 마친 상어 맵에 기록
            //맵에 기록시, 이미 맵 좌표 안에 다른 상어 있다면, 크기 비교 후 큰애만 넣도록
            Shark current = queue.pollFirst();
            int curR = current.r;
            int curC = current.c;
            if(!map[curR][curC].isEmpty()){ //이미 다른 상어가 있다면
                Shark target = map[curR][curC].get(0); //비교대상
                if(current.z > target.z){ //넣으려는 상어가 타겟상어보다 클때
                    map[curR][curC].remove(0); //타겟상어 제거
                    map[curR][curC].add(current);
                }
                else{ //넣으려는 상어가 타겟상어보다 작다면
                    continue; //안넣고 다음 상어로
                }
            }
            else{ //맵 좌표에 다른 상어가 없다면
                map[curR][curC].add(current);
            }
        }
    }

    //2. 낚시왕이 있는 열에 있는 상어 중 가잔 땅과 가까운 상어 잡음
    private static void catchShark(int col) {
        for(int r = 1; r <= R; r++){
            //상어가 있다면
            if(!map[r][col].isEmpty()){
                Shark shark = map[r][col].remove(0);
                total += shark.z; //잡은 상어 크기 누적
                break; //잡았다면 종료
            }
        }
    }
}