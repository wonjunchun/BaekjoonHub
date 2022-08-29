import java.io.*;
import java.util.Scanner;

class Solution{
    int[] goingDown(int[][] map, int N){
        int[] result = new int[2];
        if(N == 1){ //한 줄 일때
            result[0] = Math.max(map[0][0], Math.max(map[0][1], map[0][2]));
            result[1] = Math.min(map[0][0], Math.min(map[0][1], map[0][2]));
            return result;
        }

        int[] prevMax = new int[3];
        int[] curMax = new int[3];

        int[] prevMin = new int[3];
        int[] curMin = new int[3];

        for(int i = 0; i < 3; i++){ //initialize
            prevMax[i] = map[0][i];
            prevMin[i] = map[0][i];
        }
        for(int n = 1; n < N; n++){
            curMax[0] = Math.max(prevMax[0], prevMax[1]) + map[n][0];
            curMax[1] = Math.max(prevMax[0], Math.max(prevMax[1], prevMax[2])) + map[n][1];
            curMax[2] = Math.max(prevMax[1], prevMax[2]) + map[n][2];

            curMin[0] = Math.min(prevMin[0], prevMin[1]) + map[n][0];
            curMin[1] = Math.min(prevMin[0], Math.min(prevMin[1], prevMin[2])) + map[n][1];
            curMin[2] = Math.min(prevMin[1], prevMin[2]) + map[n][2];

            prevMax[0] = curMax[0];
            prevMax[1] = curMax[1];
            prevMax[2] = curMax[2];

            prevMin[0] = curMin[0];
            prevMin[1] = curMin[1];
            prevMin[2] = curMin[2];

        }
        int max = Math.max(curMax[0], Math.max(curMax[1], curMax[2]));
        int min = Math.min(curMin[0], Math.min(curMin[1], curMin[2]));


        result[0] = max;
        result[1] = min;
        return result;

    }
}
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        Solution solution = new Solution();
       
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] map = new int[N][3];

        for(int n = 0; n < N; n++){
            for(int i = 0; i < 3; i++){
                map[n][i] = sc.nextInt();
            }
        }
        int[] result = solution.goingDown(map, N);
        System.out.print(result[0]+" "+result[1]);

    }

}