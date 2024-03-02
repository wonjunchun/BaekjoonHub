import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[] items;
    static int[][] adjMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N + 1];
        adjMatrix = new int[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            items[n] = Integer.parseInt(st.nextToken());
            Arrays.fill(adjMatrix[n], Integer.MAX_VALUE);
            adjMatrix[n][n] = 0;
        }
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjMatrix[src][dst] = cost;
            adjMatrix[dst][src] = cost;
        }
        //플로이드워샬
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(adjMatrix[i][k] != Integer.MAX_VALUE && adjMatrix[k][j] != Integer.MAX_VALUE){
                        adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                    }
                }
            }
        }
        //각 지점에 낙하 후, 예은이가 얻을 수 있는 최대 아이템 수 구함(수색범위 M)
        int maxItem = 0;
        for(int n = 1; n <= N; n++){ //n지점에 낙하 시 얻을 수 있는 아이템 수 구함
            int item = 0;
            for(int i = 1; i <= N; i++){
                //i지점이 n지점에서 도달 가능하고, 수색 범위 내에 있다면
                if(adjMatrix[n][i] != Integer.MAX_VALUE && adjMatrix[n][i] <= M){
                    item += items[i];
                }
            }
            maxItem = Integer.max(maxItem, item);
        }
        System.out.println(maxItem);
    }
}
