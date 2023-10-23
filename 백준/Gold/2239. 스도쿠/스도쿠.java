import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//*다시보기
public class Main {
    static int[][] map = new int[9][9];
    static boolean[][] rows = new boolean[9][10]; //해당 행에 숫자 있는지 여부
    static boolean[][] cols = new boolean[9][10]; //해당 열에 숫자 있는지 여부
    static boolean[][] subMap = new boolean[9][10]; //해당 격자칸에 숫자 있는지 여부
    static boolean isPrinted = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int r = 0; r < 9; r++){
            String[] input = br.readLine().split("");
            for(int c = 0; c < 9; c++){
                map[r][c] = Integer.parseInt(input[c]);
                rows[r][map[r][c]] = true;
                cols[c][map[r][c]] = true;
                subMap[3*(r/3) + (c/3)][map[r][c]] = true;
            }
        }
        sudoku(0);
    }
    public static void sudoku(int num){
        if(num >= 81){
            //최초 한번만 출력하고 종료
//            if(isPrinted) return;
            for(int r = 0; r < 9; r++){
                for(int  c = 0; c < 9; c++){
                    System.out.print(map[r][c]);
                }
                System.out.println();
            }
//            isPrinted = true;
//            return;
            System.exit(0);
        }
        int r = num/9;
        int c = num%9;
        if(map[r][c] != 0) sudoku(num+1); //만약 이미 숫자 적힌 칸이면 다음칸으로
        else{
            for(int i = 1; i <= 9; i++){
                //이미 해당숫자가 같은 행이나 열, 격자칸에 있다면 continue
                if(rows[r][i] || cols[c][i] || subMap[3*(r/3)+(c/3)][i]) continue;
                //해당 칸에 해당 숫자 새기고 다음 칸 탐색
                rows[r][i] = true;
                cols[c][i] = true;
                subMap[3*(r/3)+(c/3)][i] = true;
                map[r][c] = i;
                sudoku(num+1);
                //다시 해당 칸 초기화 후 다음 숫자 탐색
                rows[r][i] = false;
                cols[c][i] = false;
                subMap[3*(r/3)+(c/3)][i] = false;
                map[r][c] = 0;
            }
        }
    }
}
