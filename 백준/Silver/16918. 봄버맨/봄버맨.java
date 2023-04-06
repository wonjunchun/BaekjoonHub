
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int R, C, N;
    static String[][] map;
    static int[][] timemap; //폭탄이 놓인 시점을 기록하는 맵
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        map = new String[R][C];
        timemap = new int[R][C];
//        for(int r = 0; r < R; r++) {
//            Arrays.fill(timemap[r], -1);
//        }
        Deque<int[]> bombList = new ArrayDeque<>();
        for(int r = 0; r < R; r++) {
            String[] inputStr = br.readLine().split("");
            for(int c = 0; c < C; c++) {
                map[r][c] = inputStr[c];
                if(map[r][c].equals("O")) {
                    //큐 명세 {시간, r, c}
                    bombList.add(new int[] {0, r, c});
                    timemap[r][c] = 0;
                }
            }
        }

        int time = 0;
        while(time < N) {
            time++;
//			print(map);
//			System.out.println("=============");
            if(time == 1) continue;
            if(bombList.isEmpty()) {
                for(int r = 0; r < R; r++){
                    for(int c = 0; c < C; c++){
                        map[r][c] = "O";
                        bombList.add(new int[]{time, r, c});
                        timemap[r][c] = time;
                    }
                }
                continue;
            }

            switch(time-bombList.peek()[0]) {
                case 3: //3초 차이나면 터질 타이밍. 터지기만함. map 갱신
                    String[][] newMap = mapCopy(map);
                    while(!bombList.isEmpty()) {
                        if(time-bombList.peek()[0] != 3) break;
                        int[] current = bombList.poll();
                        int curH = current[0];
                        int curR = current[1];
                        int curC = current[2];
                        if(map[curR][curC] == ".") { //이미 빈칸이라면 지난 폭발때 폭탄 사라짐
                            continue; //다음 폭탄 체크하러감
                        }
                        if(timemap[curR][curC] != curH) { //해당 되는 시간에 놓인 폭탄이 아니라면 지금 터질 타이밍 아님
                            //한번 터지고, 빈칸 전체에 폭탄 놓이는 시점에 덮였을 확률 존재
                            continue;
                        }
                        newMap[curR][curC] = "."; //현재칸 폭발

                        for(int[] d: directions) {
                            int nextR = curR+d[0];
                            int nextC = curC+d[1];
                            //범위 벗어나거나 이미 빈칸이면 continue
                            if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || map[nextR][nextC].equals(".")) continue;
                            newMap[nextR][nextC] = "."; //폭탄 터뜨리고 빈칸으로 만듦
                        }
                    }
                    map = newMap; //새맵 갱신
                    break;
                case 2: //2초 지난 시점이면 빈칸에 새 폭탄 심어줌
                    for(int r = 0; r < R; r++) {
                        for(int c = 0; c < C; c++) {
                            if(map[r][c].equals(".")) {
                                map[r][c] = "O"; //폭탄심기
                                timemap[r][c] = time; //timemap은 놓을때만 갱신
                                bombList.add(new int[] {time, r, c});
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        print(map);
    }


    private static void print(String[][] curMap) {
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                System.out.print(curMap[r][c]);
            }
            System.out.println();
        }

    }

    public static int[][] timemapCopy(int[][] curMap){
        int[][] newMap = new int[R][C];
        for(int r = 0; r < R; r++) {
            newMap[r] = curMap[r].clone();
        }
        return newMap;
    }
    public static String[][] mapCopy(String[][] curMap){
        String[][] newMap = new String[R][C];
        for(int r = 0; r < R; r++) {
            newMap[r] = curMap[r].clone();
        }
        return newMap;
    }

}