import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static String[][][] building;
    static boolean[][][] visited; //어차피 뻗어나가는 방향으로만 진행하므로, 다시 돌아오는 경우 생각안해도될듯

    static int L, R, C;
    static int[][] directions = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    static int[] S, E; //시작점 종료점 좌표 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        while(true){ // 종료입력 들어오기 전까지 계속 반복
            input = br.readLine().split(" ");
            L = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);

            if(L == 0 && R == 0 && C == 0) break; //종료 조건

            //input
            building = new String[L][R][C];
            visited = new boolean[L][R][C];
            for(int l = 0; l < L; l++){
                for(int r = 0; r < R; r++){
                    input = br.readLine().split("");
                    for(int c = 0; c < C; c++){
                        building[l][r][c] = input[c];
                        if(input[c].equals("S")){
                            S = new int[]{l, r, c}; //시작 좌표
                        }
                        else if(input[c].equals("E")){
                            E = new int[]{l, r, c}; //종료 좌표
                        }
                    }
                }
                br.readLine();
            }

            //시작점부터 bfs
            int result = bfs();

            if(result < 0){
                System.out.println("Trapped!");
            }
            else{
                System.out.printf("Escaped in %d minute(s).\n", result);
            }
        }


    }

    private static int bfs() {
        //시작점부터 bfs
        int result = -1; // 값 갱신이 안되면 -1 반환하도록

        Deque<int[]> queue = new ArrayDeque<>();
        //큐 명세 {l, r, c, time, visited}
        visited[S[0]][S[1]][S[2]] = true; //시작점 방문 처리
        queue.add(new int[]{S[0], S[1], S[2], 0});
        A : while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int curL = (int)current[0];
            int curR = (int)current[1];
            int curC = (int)current[2];
            int curTime = (int)current[3];

            for(int[] d: directions){
                int nextL = curL + d[0];
                int nextR = curR + d[1];
                int nextC = curC + d[2];
                
                //만약 유효하지 않은 좌표거나, 이미 방문한 좌표라면, continue
                if(nextL < 0 || nextL >= L || nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || visited[nextL][nextR][nextC]){
                    continue;
                }
                
                //만약 다음 좌표가 종료 지점이라면, 소요시간 기록 후 탈출
                if(nextL == E[0] && nextR == E[1] && nextC == E[2]){
                    result = curTime+1; //현재 지점보다 한칸 더 가야 하므로
                    break A;
                }
                
                //다음 좌표가 이동이 가능한 칸이라면, 큐에 넣음
                if(building[nextL][nextR][nextC].equals(".")){
                    visited[nextL][nextR][nextC] = true;
                    queue.add(new int[]{nextL, nextR, nextC, curTime+1});
                }
            }
        }
        return result;
    }

}
