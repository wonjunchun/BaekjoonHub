import java.io.*;
import java.util.StringTokenizer;

class SafeArea{
    //DFS
    int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void findArea(int N, int n, int m, int[][] map, int[][] safeMap, int height, int mark){

        if(map[n][m] > height && safeMap[n][m] == 0){
            //물높이보다 높고, 아직 방문하지 않은 영역이라면,
            safeMap[n][m] = mark;
            for(int[] d : direction){
                if(n+d[0] < 0 || n+d[0] >= N || m+d[1] < 0 || m+d[1] >= N){
                    //다음 탐색할 영역 좌표가 올바른 값이 아니면 skip
                    continue;
                }
                else{
                    findArea(N, n+d[0], m+d[1], map, safeMap, height, mark);
                }
            }
        }
    }

    public int safeArea(int N, int[][] map, int max, int height){ //height: 물에 잠기는 높이
        //안전지대 개수만 파악하면됨
        int[][] safeMap = new int[N][N];
        //System.arraycopy(map, 0, safeMap, 0, N);
        int safeCount = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < N; m++){
                if(map[n][m] > height && safeMap[n][m] == 0){
                    //물높이보다 높고, 아직 방문하지 않은 영역이라면
                    safeCount++;
                    //safeMap[n][m] = safeCount;
                    findArea(N, n, m, map, safeMap, height, safeCount);
                }

            }
        }
        return safeCount;

    }
    public int maxSafeArea(int N, int[][] map, int max){
        //각 물 높이에따른 안전지대 개수 중, 최대값 반환
        int maxSafeAreaCount = 0;
        for(int h = 0; h < max; h++){
            int safeAreaCount = safeArea(N, map, max, h);
            if(maxSafeAreaCount < safeAreaCount){
                maxSafeAreaCount = safeAreaCount;
            }
        }
        return maxSafeAreaCount;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int[][] map = new int[N][N];
        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if(max < map[n][m]){
                    max = map[n][m];
                }
            }

        }
        SafeArea safeArea = new SafeArea();
        System.out.println(safeArea.maxSafeArea(N, map, max));
    }
}
