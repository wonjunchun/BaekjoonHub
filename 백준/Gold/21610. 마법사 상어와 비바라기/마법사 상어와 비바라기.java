import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] move;
    static int[][] directions = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    static int[][] diagonalDirections = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        move = new int[M][2];
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            move[m][0] = Integer.parseInt(st.nextToken());
            move[m][1] = Integer.parseInt(st.nextToken());
        }
        simulation();
        int result = waterAmount();
        System.out.println(result);
    }

    static int waterAmount() {
        int result = 0;
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                result += map[r][c];
            }
        }
        return result;
    }

    static void simulation() {
        Deque<int[]> cloud = new LinkedList<>(); //구름 저장
        Deque<int[]> nextCloud = new LinkedList<>(); //다음구름 저장
        boolean[][] visited = new boolean[N][N];
        //최초 구름
        cloud.add(new int[]{N - 1, 0});
        cloud.add(new int[]{N - 1, 1});
        cloud.add(new int[]{N - 2, 0});
        cloud.add(new int[]{N - 2, 1});

        for(int[] m: move){ //이동 명령들
            int di = m[0] - 1;
            int si = m[1];
            //i) 각 구름들 di 방향으로 si칸 이동시킨 후, 비 내리고 구름 사라지게 함
            while(!cloud.isEmpty()){
                int[] current = cloud.pollFirst();
                int curRow = (current[0] + directions[di][0] * si);
                int curCol = (current[1] + directions[di][1] * si);
                curRow = curRow < 0? (curRow + 50*N) % N : curRow % N;
                curCol = curCol < 0? (curCol + 50*N) % N : curCol % N;
                map[curRow][curCol]++;
                visited[curRow][curCol] = true;
                nextCloud.add(new int[]{curRow, curCol});
            }
            int[][] newMap = new int[N][N];
            for(int n = 0; n < N; n++){
                newMap[n] = map[n].clone();
            }
            //ii) 아까 비내린 칸들의 인접 대각선 칸 체크, 물복사버그
            while(!nextCloud.isEmpty()){
                int[] current = nextCloud.pollFirst();
                int curRow = current[0];
                int curCol = current[1];
                int numOfWater = 0;
                for(int[] d: diagonalDirections){
                    int diagonalRow = curRow + d[0];
                    int diagonalCol = curCol + d[1];
                    //대각선이 좌표범위 벗어나면 continue
                    if(diagonalRow < 0 || diagonalRow >= N || diagonalCol < 0 || diagonalCol >= N) continue;
                    if(map[diagonalRow][diagonalCol] >= 1){
                        numOfWater++;
                    }
                }
                newMap[curRow][curCol] += numOfWater;
            }
            //iii) 바구니 저장된 물의 양이 2이상인 모든 칸에 구름 생성, 물의양 -2
            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    if(newMap[r][c] >= 2 && !visited[r][c]){
                        cloud.add(new int[]{r, c});
                        newMap[r][c] -= 2;
                    }
                }
            }
            map = newMap;
            visited = new boolean[N][N];
        }
    }
}
