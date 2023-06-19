import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, D;
    static int enemyCnt;
    static int maximumEnemyKill = 0; //잡을 수 있는 최대 적 수
    static int[][] map;
    static int[][] simulationMap; //매 시뮬레이션마다 참고할 맵
    static int[] comb; //조합을 통해 궁수의 위치 뽑아냄
    static int[][] directions = {{0, -1}, {-1, 0}, {0, 1}}; //왼쪽, 위쪽, 오른쪽 순 탐색
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M]; //맨 윗행은 0으로만 채움. 맨 아래 행 바로 밑은 성벽(궁수 존재)
        //적들은 1~N 까지 있을수있음
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(map[n][m] == 1) enemyCnt++; //적의 수 셈
            }
        }
        comb = new int[3];
        //1. 조합을 통해 궁수 3명 배치할 위치 찾음
        combination(0, 0); //start, cnt

        System.out.println(maximumEnemyKill);
    }

    private static void combination(int start, int cnt) {
        //3명의 궁수 선택 완료했다면
        if(cnt == 3){
            //현재 선택된 궁수들로 시뮬레이션(매 조합마다 죽일 수 있는 적의 수 세도록)
            simulation(); //시뮬레이션
            return; //종료
        }
        for(int i = start; i < M; i++){
            comb[cnt] = i; //현재 값을 조합에 포함시킴
            combination(i+1, cnt+1); //다음값 탐색
        }
    }

    private static void simulation() {
        //현재 선택된 궁수들로 시뮬레이션 시작
        int enemyKillCnt = 0; //궁수가 잡은 적 수
        int remainEnemy = enemyCnt; //남은 적 수
        List<int[]> targets = new LinkedList<>(); //타겟팅된 적 목록
        simulationMap = new int[N+1][M];
        for(int n = 1; n <= N; n++){
            simulationMap[n] = map[n].clone();
        }

        while(remainEnemy > 0){ //적이 모두 사라질때까지 반복
            //2. 각 궁수 별로 제일 가까운 적 타겟팅
            for(int c: comb){
                int[] target = bfs(N, c);
                if(target[0] < 0 || target[1] < 0) continue; //궁수가 적을 타겟팅 못했다면 다음 궁수로
                targets.add(target);
            }
            //3. 타겟팅된 적 죽임
            while(!targets.isEmpty()){
                int[] currentEnemy = targets.remove(0);
                if(simulationMap[currentEnemy[0]][currentEnemy[1]] == 1){
                    //그 자리에 적이 아직 살아있다면, 죽임
                    simulationMap[currentEnemy[0]][currentEnemy[1]] = 0;
                    enemyKillCnt++; //궁수가 죽인 적 수 ++
                    remainEnemy--; //총 적 수 갱신
                }
            }
            //4. 남은 적 전진, 맨 앞 행의 적은 성벽 만나면 사라짐
            int countFrontEnemy = 0;
            for(int enemy: simulationMap[N]){
                countFrontEnemy += enemy;
            }
            remainEnemy -= countFrontEnemy; //전방의 적 수 만큼 감소
            for(int r = N-1; r >= 0; r--){
                simulationMap[r+1] = simulationMap[r].clone(); //적들 한칸씩 전진이동
            }
        }
        //현재 궁수 조합의 적 킬 수가 기존 최대 적 킬 수 보다 많다면 갱신
        if(enemyKillCnt > maximumEnemyKill){
            maximumEnemyKill = enemyKillCnt;
        }
    }

    //현재 궁수로부터 가장 가까운 적 찾음(적의 좌표 반환)
    private static int[] bfs(int row, int col) {
        //궁수 바로앞에 적이 있다면 바로 반환
        if(simulationMap[row][col] == 1) return new int[]{row, col};
        else{
            boolean[][] visited = new boolean[N+1][M];
            Deque<int[]> queue = new ArrayDeque<>();
            //큐 명세 {행, 열}
            queue.add(new int[]{row, col, 1});
            visited[row][col] = true;
            while(!queue.isEmpty()){
                int[] current = queue.pollFirst();
                int curR = current[0];
                int curC = current[1];
                int curDist = current[2];
                for(int[] d: directions){
                    int nextR = curR + d[0];
                    int nextC = curC + d[1];
                    int nextDist = curDist + 1;
                    if(nextDist > D) break; //이미 궁수의 사정거리를 벗어났다면
                    //다음 좌표가 범위를 벗어났거나 이미 방문한 좌표라면 continue
                    if(nextR < 0 || nextR >= N+1 || nextC < 0 || nextC >= M || visited[nextR][nextC]) continue;

                    if(simulationMap[nextR][nextC] == 1){ //제일 가까운 적을 찾았다면
                        return new int[]{nextR, nextC};
                    }
                    else{
                        queue.add(new int[]{nextR, nextC, nextDist});
                        visited[nextR][nextC] = true;
                    }
                }
            }
            //while 문 끝날때까지 적 찾지 못했다면, 공격못함. {-1, -1} 반환
            return new int[]{-1, -1};
        }
    }
}
