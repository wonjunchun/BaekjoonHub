import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int white = 0, blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < N; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        split(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }
    static void split(int rowStart, int colStart, int length){
        //i) 모두 하얀색 또는 파란색인지 체크
        if(isAllColor(rowStart, colStart, length, 1) == 1){
            blue++;
            return;
        }
        else if(isAllColor(rowStart, colStart, length, 0) == 0){
            white++;
            return;
        }
        //ii) 모두 하얀색 or 파란색이 아니라면 분할
        split(rowStart, colStart, length / 2);
        split(rowStart, colStart+(length/2), length/2);
        split(rowStart + (length / 2), colStart, length / 2);
        split(rowStart + (length / 2), colStart + (length / 2), length / 2);

    }
    static int isAllColor(int rowStart, int colStart, int length, int color){
        for(int r = rowStart; r < rowStart+length; r++){
            for(int c = colStart; c < colStart+length; c++){
                if(map[r][c] != color) return -1;
            }
        }
        return color;
    }
}