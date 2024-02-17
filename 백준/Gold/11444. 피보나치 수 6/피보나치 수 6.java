import java.util.Scanner;

/**
 * |Fn+1|   |1 1|n  |F1|
 * |    | = |   | * |  |
 * | Fn |   |1 0|   |F0|
 */

public class Main {
    final static long mod = 1000000007;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long result = fibonacci(N);
        System.out.println(result);
    }

    static long fibonacci(long n) {
        if(n == 0) return 0;
        else if(n == 1) return 1;
        else{
            long[] result = new long[2];
            long[][] matrix = {{1, 1}, {1, 0}};
            long[] origin = {1, 0};
            result = multiplyResult(pow(matrix, n), origin);
            return result[1] % mod;
        }
    }

    private static long[][] pow(long[][] matrix, long exp) {
        if(exp == 1 || exp == 0) return matrix;
        long[][] result = pow(matrix, exp / 2);
        result = multiplyMatrix(result, result);
        if(exp % 2 == 1) result = multiplyMatrix(result, matrix);
        return result;
    }
    static long[][] multiplyMatrix(long[][] matrix1, long[][] matrix2){
        long[][] result = new long[2][2];
        result[0][0] = (matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0]) % mod;
        result[0][1] = (matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1]) % mod;
        result[1][0] = (matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0]) % mod;
        result[1][1] = (matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1]) % mod;
        return result;
    }

    static long[] multiplyResult(long[][] matrix, long[] origin){
        long[] result = new long[2];
        result[0] = matrix[0][0] * origin[0] + matrix[0][1] * origin[1];
        result[1] = matrix[1][0] * origin[0] + matrix[1][1] * origin[1];
        return result;
    }
}
