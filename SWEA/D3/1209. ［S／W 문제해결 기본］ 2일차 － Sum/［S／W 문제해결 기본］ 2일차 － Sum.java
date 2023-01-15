import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int[][] matrix = new int[100][100];
            int[] sum = new int[202]; //행, 열, 대각선의 합들 저장
            //0~99 : 행의 합, 100~199 : 열의 합, 200~201 : 대각선의 합
            sc.nextInt(); //테스트케이스 번호
            for(int i = 0; i < 100; i++){
                int rowSum = 0;
                for(int j = 0; j < 100; j++){
                    int currentNum = sc.nextInt();
                    matrix[i][j] = currentNum;
                    rowSum += currentNum;
                }
                sum[i] = rowSum;
            }
            int diagonalSum = 0;
            for(int i = 0; i < 100; i++){
                int colSum = 0;
                for(int j = 0; j < 100; j++){
                    colSum += matrix[j][i];
                }
                sum[100+i] = colSum;
                diagonalSum += matrix[i][i];
            }
            sum[200] = diagonalSum;

            diagonalSum = 0;

            for(int i = 0; i < 100; i++){
                diagonalSum += matrix[i][99-i];
            }
            sum[201] = diagonalSum;

            int result = 0;

            for(int i = 0; i <= 201; i++){
                if(sum[i] > result){
                    result = sum[i];
                }
            }
            System.out.println("#" + t + " " + result);
            
        }
    }
}