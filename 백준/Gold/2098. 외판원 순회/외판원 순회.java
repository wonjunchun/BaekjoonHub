import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] adjMatrix;
    static int[][] cost;
    static int dstVisit;
    static int INF = 999999999; //Integer.MAX_VALUE 쓰기엔, 덧셈하다가 오버플로 날 수 있음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        cost = new int[N][1 << N];
        for(int n = 0; n < N; n++){
            adjMatrix[n] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(cost[n], -1);
        }
        dstVisit = (1 << N) - 1; //모든 도시를 다 방문한 경우의 비트상태
        int result = dfs(0, 1);
        System.out.println(result);
    }

    private static int dfs(int curCity, int curVisit) {
        if(curVisit == dstVisit){ //모든 도시 다 방문했다면
            if(adjMatrix[curCity][0] == 0) return INF; //현재 도시에서 출발지 갈 방법 없다면, INF
            else return adjMatrix[curCity][0]; //현재 도시에서 출발지 가는데 걸리는 거리
        }
        if(cost[curCity][curVisit] != -1) return cost[curCity][curVisit]; //이미 현재 방문경로에 대한 cost 계산되어있다면 그값 반환
        cost[curCity][curVisit] = INF; //아니라면, 일단 INF

        for(int n = 0; n < N; n++){
            if(adjMatrix[curCity][n] == 0) continue; //현재 도시와 n번 도시 연결 안됐다면 continue
            if((curVisit & (1 << n)) == (1 << n)) continue; //n번 도시 이미 방문했다면 continue
            cost[curCity][curVisit] = Math.min(cost[curCity][curVisit], adjMatrix[curCity][n]
                    + dfs(n, curVisit | 1 << n)); //현재 cost와 n번도시 경유 시 cost 비교 후 갱신
        }
        return cost[curCity][curVisit]; //현재 방문경로에 대한 최저 cost 반환
    }
}