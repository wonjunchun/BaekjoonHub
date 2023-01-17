import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            int N = sc.nextInt();
            int[][] matrix = new int[N][N];
            for(int n = 0; n < N; n++){
                for(int m = 0; m < N; m++){
                    matrix[n][m] = sc.nextInt();
                }
            }
            System.out.println("#"+t);
            for(int n = 0; n < N; n++){
                for(int m = 0; m < N; m++){
                    System.out.print(matrix[N-m-1][n]);
                }
                System.out.print(" ");
                for(int m = 0; m < N; m++){
                    System.out.print(matrix[N-n-1][N-m-1]);
                }
                System.out.print(" ");
                for(int m = 0; m < N; m++){
                    System.out.print(matrix[m][N-n-1]);
                }
                System.out.println();
            }
        }
    }
}