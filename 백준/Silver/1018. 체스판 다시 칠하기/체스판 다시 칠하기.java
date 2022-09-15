import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;
class ChessRedraw{
    public int chessRedraw(char[][] chessTable, int N, int M){
        char[] chessPattern1 = {'W', 'B'};
        //char[] chessPattern2 = {'B', 'W'};
        int redrawCount1 = 0;
        int redrawCount2 = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(chessTable[n][m] != chessPattern1[(n+m)%2]){
                    //가로세로 좌표의 합(짝/홀 여부) 이용해서 체스 패턴 여부 파악
                    redrawCount1++;
                }
                else{ //['W', 'B'] 순서가 아닌 ['B', 'W'] 순서일수도 있으므로
                    redrawCount2++;
                }
            }
        }

        return Math.min(redrawCount1, redrawCount2);
    }
    public int findSubTable(char[][] chessTable, int N, int M){ //8*8 크기의 체스판 중에서 제일 다시 칠할 칸 적은 subTable 찾기
        int min = 64;
        for(int n = 0; n <= N-8; n++){
            for(int m = 0; m <= M-8; m++){
                char[][] subTable = new char[8][8];
                    for(int i = n; i < n+8; i++)
                        System.arraycopy(chessTable[i], m, subTable[i-n], 0, 8);
                int redrawCount = chessRedraw(subTable, 8, 8);
                if(redrawCount < min) min = redrawCount;
            }
        }
        return min;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] chessTable = new char[N][M];

        for(int n = 0; n < N; n++){
            String str = br.readLine();
            for(int m = 0; m < M; m++){
                chessTable[n][m] = str.charAt(m);
            }
        }
        ChessRedraw chessRedraw = new ChessRedraw();
        System.out.println(chessRedraw.findSubTable(chessTable, N, M));

    }
}
