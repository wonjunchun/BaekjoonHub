import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        dfs(0, 0);
        System.out.println(result);
    }

    //조합
    private static void dfs(int cnt, int start) { //cnt: 넴모 개수
        result += check(cnt);
        if(cnt == N*M) return; //넴모 모두 채웠다면 종료
        for(int i = start; i < N*M; i++){
            int row = i / M;
            int col = i % M;
            if(visited[row][col]) continue;
            visited[row][col] = true;
            dfs(cnt + 1, i + 1);
            visited[row][col] = false;
        }
    }

    private static int check(int cnt) { //2*2 사각형 안생기면 1 반환
        if(cnt < 4) return 1; //넴모 3개 이하면 2*2 안생김
        for(int row = 0; row < N-1; row++){
            for(int col = 0; col < M-1; col++){
                if(visited[row][col] && visited[row][col+1] && visited[row+1][col] && visited[row+1][col+1])
                    return 0;
            }
        }
        return 1;
    }
}
