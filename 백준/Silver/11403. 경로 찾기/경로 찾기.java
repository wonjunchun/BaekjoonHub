import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjMatrix;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                adjMatrix[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k = 0; k < N; k++){
            for(int n = 0; n < N; n++){
                for(int m = 0; m < N; m++){
                    if(adjMatrix[n][k] != 0 && adjMatrix[k][m] != 0)
                        adjMatrix[n][m] = 1;
                }
            }
        }
        for(int n = 0; n < N; n++){
            for(int m = 0; m < N; m++){
                System.out.print(adjMatrix[n][m]+" ");
            }
            System.out.println();
        }
    }
}
