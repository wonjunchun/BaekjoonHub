import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjMatrix;
    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjMatrix = new int[V + 1][V + 1];
        for(int v = 1; v <= V; v++){
            Arrays.fill(adjMatrix[v], Integer.MAX_VALUE);
            adjMatrix[v][v] = 0;
        }
        for(int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjMatrix[src][dst] = cost;
        }
        for(int k = 1; k <= V; k++){
            for(int i = 1; i <= V; i++){
                for(int j = 1; j <= V; j++){
                    if(i == j) continue;
                    if(adjMatrix[i][k] != Integer.MAX_VALUE && adjMatrix[k][j] != Integer.MAX_VALUE){
                        adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i = 1; i <= V; i++){
            for(int j = 1; j <= V; j++){
                if(i == j) continue;
                //서로 다른 두 정점에서 서로에게 가는 경로 있다면 사이클 존재
                if(adjMatrix[i][j] != Integer.MAX_VALUE && adjMatrix[j][i] != Integer.MAX_VALUE){
                    result = Math.min(result, adjMatrix[i][j] + adjMatrix[j][i]);
                }
            }
        }
        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);
    }
}
