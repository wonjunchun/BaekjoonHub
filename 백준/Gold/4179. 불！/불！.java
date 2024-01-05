import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int R, C;
    static String[][] map;
    static int[][] fire, jihun;
    static int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R][C];
        fire = new int[R][C];
        jihun = new int[R][C];
        Deque<int[]> fireQueue = new LinkedList<>();
        Deque<int[]> jihunQueue = new LinkedList<>();

        int result = -1;
        for(int r = 0; r < R; r++){
            Arrays.fill(fire[r], -1);
            Arrays.fill(jihun[r], -1);
        }

        for(int r = 0; r < R; r++){
            String current = br.readLine();
            String[] curRow = current.split("");
            for(int c = 0; c < C; c++){
                map[r][c] = curRow[c];
                if(map[r][c].equals("F")){
                    fireQueue.add(new int[]{r, c, 0});
                    fire[r][c] = 0;
                }
                else if(map[r][c].equals("J")){
                    jihunQueue.add(new int[]{r, c, 0});
                    jihun[r][c] = 0;
                }
            }
        }

        
        if(jihunQueue.peekFirst()[0] == 0 || jihunQueue.peekFirst()[0] == R-1 ||
                jihunQueue.peekFirst()[1] == 0 || jihunQueue.peekFirst()[1] == C-1)
            result = 0;
        else{
            //i) 일단 불을 확산시키면서, 걸린 시간을 fire에 저장
            while(!fireQueue.isEmpty()){
                int[] current = fireQueue.pollFirst();
                int curR = current[0];
                int curC = current[1];
                int curTime = current[2];

                //사방탐색
                for(int[] d: directions){
                    int nextR = curR + d[0];
                    int nextC = curC + d[1];
                    //좌표 범위를 벗어나거나, 이미 방문했다면 continue
                    if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || fire[nextR][nextC] != -1) continue;
                    if(map[nextR][nextC].equals("#")) continue;
                    fireQueue.add(new int[]{nextR, nextC, curTime + 1});
                    fire[nextR][nextC] = curTime + 1; //불이 도달한 시간 표시
                }
            }

            //ii) 지훈이 이동시키면서, 지훈보다 불이 먼저 도달한 칸 이동 못하게 함
            JihunQ: while(!jihunQueue.isEmpty()){
                int[] current = jihunQueue.pollFirst();
                int curR = current[0];
                int curC = current[1];
                int curTime = current[2];

                for (int[] d : directions) {
                    int nextR = curR + d[0];
                    int nextC = curC + d[1];
                    if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || jihun[nextR][nextC] != -1) continue;
                    if(map[nextR][nextC].equals("#")) continue;
                    //다음 칸에 지훈이 도달하는 시간이 불이 도달하는 시간보다 늦다면 continue
                    //다음 칸에 불이 안붙은 경우 fire값은 -1이므로, 이 경우 예외처리
                    if(curTime + 1 >= fire[nextR][nextC] && fire[nextR][nextC] != -1) continue;
                    if(nextR == 0 || nextR == R-1 || nextC == 0 || nextC == C-1){
                        //가장자리 도달했다면, 탈출
                        result = curTime + 1;
                        break JihunQ;
                    }
                    jihunQueue.add(new int[]{nextR, nextC, curTime + 1});
                    jihun[nextR][nextC] = curTime + 1;
                }
            }
        }
        if(result == -1){
            System.out.println("IMPOSSIBLE");
        } else{
            System.out.println(result+1);
        }
    }
}