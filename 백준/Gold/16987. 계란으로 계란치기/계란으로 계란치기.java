import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max = 0;
    static int[][] eggs;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        eggs = new int[N][2]; //내구도, 무게
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            eggs[n][0] = Integer.parseInt(st.nextToken()); //내구도
            eggs[n][1] = Integer.parseInt(st.nextToken()); //무게
        }
        visited = new boolean[N]; //계란 깨졌는지 여부
        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int cnt, int value) { //cnt : 현재 손에 든 계란, value : 깨진 계란 개수
        if(cnt == N || value == N-1){ //모든 경우 순회했거나, 계란 N-1개 깨진 경우
            max = Math.max(max, value);
            return;
        }
        if(visited[cnt]){ //현재 들은 계란이 이미 깨져있다면
            dfs(cnt + 1, value); //다음 계란 들음
            return;
        }
        for(int n = 0; n < N; n++){
            //상대 계란이 이미 깨져있거나, 들고있는 계란인 경우
            if(visited[n] || n == cnt) continue;
            eggs[cnt][0] -= eggs[n][1]; //들고있는 계란 내구도 갱신
            eggs[n][0] -= eggs[cnt][1]; //상대 계란 내구도 갱신
            //두 계란 모두 깨지는 경우
            if(eggs[cnt][0] <= 0 && eggs[n][0] <= 0){
                visited[cnt] = true;
                visited[n] = true;
                dfs(cnt + 1, value + 2); //다음 계란 탐색
                visited[cnt] = false;
                visited[n] = false;
            }
            //현재 손에 든 계란만 깨지는 경우
            else if(eggs[cnt][0] <= 0){
                visited[cnt] = true;
                dfs(cnt + 1, value + 1);
                visited[cnt] = false;
            }
            //상대 계란만 깨지는 경우
            else if(eggs[n][0] <= 0){
                visited[n] = true;
                dfs(cnt + 1, value + 1); //다음 계란 들음
                visited[n] = false;
            }
            else{ //아무것도 안깨진 경우
                dfs(cnt + 1, value);
            }
            //계란 내구도 복원
            eggs[cnt][0] += eggs[n][1];
            eggs[n][0] += eggs[cnt][1];
        }
    }
}
