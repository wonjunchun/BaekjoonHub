import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;
    static int[][] originMatrix, resultMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        originMatrix = new int[N][N];
        resultMatrix = new int[N][N];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                originMatrix[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        resultMatrix = pow(B);
        for(int n = 0; n < N; n++){
            for(int m = 0; m < N; m++){
                System.out.print(resultMatrix[n][m] + " ");
            }
            System.out.println();
        }
    }
    static int[][] pow(long square){
        if(square == 1L){
            return multiplyMatrix(originMatrix, elementartMatrix());
        } else {
            int[][] result = pow(square / 2); //지수 절반으로 나누어 재귀호출
            result = multiplyMatrix(result, result);
            if(square % 2 == 1L){ //지수가 홀수이면
                result = multiplyMatrix(result, originMatrix);
            }
            return result;
        }

    }
    //두 행렬의 행렬곱연산
    static int[][] multiplyMatrix(int[][] A, int[][] B){
        int[][] result = new int[N][N];
        for(int n = 0; n < N; n++){
            for(int m = 0; m < N; m++){
                for(int l = 0; l < N; l++){
                    result[n][m] += A[n][l] * B[l][m];
                    result[n][m] %= 1000;
                }
            }
        }
        return result;
    }
    static int[][] elementartMatrix(){
        int[][] result = new int[N][N];
        for(int n = 0; n < N; n++){
            result[n][n] = 1;
        }
        return result;
    }
}
