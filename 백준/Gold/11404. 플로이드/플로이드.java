import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjMatrix;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjMatrix = new int[N + 1][N + 1];
        //초기화
        for(int n = 1; n <= N; n++){
            Arrays.fill(adjMatrix[n], Integer.MAX_VALUE);
//            adjMatrix[n][n] = 0;
        }
        StringTokenizer st;
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(adjMatrix[src][dst] > cost)
                adjMatrix[src][dst] = cost;
        }
        floyd();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int result = (i == j || adjMatrix[i][j] == Integer.MAX_VALUE) ? 0 : adjMatrix[i][j];
                System.out.print(result + " ");
            }
            System.out.println();
        }
    }

    static void floyd() {
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(adjMatrix[i][k] != Integer.MAX_VALUE && adjMatrix[k][j] != Integer.MAX_VALUE)
                        adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }
    }
}
