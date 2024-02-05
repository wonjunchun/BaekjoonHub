import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().split("");
        }
        int pipelines = 0;
        for(int r = 0; r < R; r++){
            if(dfs(r, 0)){ //만약 r 시작점에서의 파이프라인 경로가 존재한다면
                pipelines++;
            }
        }
        System.out.println(pipelines);
    }

    static boolean dfs(int row, int col){
        //해당칸에 파이프 연결 성공했으면 더이상 파이프 못놓으므로 방문처리 해제안함
        //해당칸에 파이프 연결 실패했다면 다음 시도에 이 칸 방문해도 실해하므로 방문 해제 안함
        map[row][col] = "-"; //방문표시
        if(col == C-1){ //맨끝 열까지 도달했다면 성공
            return true;
        }
        //오른쪽 위 대각선 탐색(오른쪽 위 대각선이 갈 수 있는 곳이면)
        if(row-1 >= 0 && map[row-1][col+1].equals(".")){
            if(dfs(row-1, col+1)){ //경로가 존재하면
                return true;
            }
        }
        //오른쪽 탐색
        if(map[row][col+1].equals(".")){
            if(dfs(row, col+1)){
                return true;
            }
        }
        //오른쪽 아래 대각선 탐색
        if(row+1 < R && map[row+1][col+1].equals(".")){
            if(dfs(row+1, col+1)){
                return true;
            }
        }
        return false; //도달 가능 경로가 없으면 false
    }
}
