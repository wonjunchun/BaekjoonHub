import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] map;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new char[N][2*N];
        for(int n = 0; n < N; n++){
            Arrays.fill(map[n], ' ');
        }
        star(0, N-1, N);
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < N; r++){
            for(int c = 0; c < 2*N; c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static void star(int row, int col, int len){
        if(len == 3){ //len이 3이 되면 더이상 분할 안함. 별찍고 종료
            map[row][col] = '*';
            map[row + 1][col - 1] = '*';
            map[row + 1][col + 1] = '*';
            for (int c = col - 2; c <= col + 2; c++) {
                map[row + 2][c] = '*';
            }
            return;
        }
        //top점 좌표 기준으로 3방향 분할 재귀 탐색
        star(row, col, len / 2); //상단 부분 삼각형
        star(row + (len / 2), col - (len / 2), len / 2); //왼쪽 아래 부분 삼각형
        star(row + (len / 2), col + (len / 2), len / 2); //오른쪽 아래 부분 삼각형
    }
}
