import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] isFriend;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isFriend = new boolean[N+1][N+1];
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            isFriend[src][dst] = true;
            isFriend[dst][src] = true;
        }
        int min = Integer.MAX_VALUE;
        int answer = 0;
        for(int n = 1; n <= N; n++){
            int current = kevin(n); //현재 유저의 케빈베이컨수 구함
            if(current < min){
                min = current;
                answer = n;
            }
        }
        System.out.println(answer);
    }
    static int kevin(int user){ //한 유저의 케빈베이컨 수 구하는 함수
        int result = 0;
        for(int n = 1; n <= N; n++){
            if(n != user){
                result += bfs(n, user);
            }
        }
        return result;
    }

    private static int bfs(int target, int user) { //타겟과 현재 유저와의 촌수 구하는 함수
        boolean[] visited = new boolean[N+1];
        Deque<int[]> queue = new LinkedList<>();
        for(int n = 1; n <= N; n++){
            if(isFriend[user][n]){
                queue.add(new int[]{n, 0});
                visited[n] = true;
            }
        }
        visited[user] = true;
        int result = 0;
        Q: while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int curUser = current[0];
            int curDist = current[1];
            for(int n = 1; n <= N; n++){
                if(isFriend[curUser][n] && !visited[n]){
                    if(n == target){ //내가 찾고자 하는 친구에 도달했다면
                        result = curDist + 1;
                        break Q;
                    }
                    else{
                        queue.add(new int[]{n, curDist + 1});
                        visited[n] = true;
                    }
                }
            }
        }
        return result;
    }
}
