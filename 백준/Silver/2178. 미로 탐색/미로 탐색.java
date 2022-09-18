import java.io.*;
import java.util.*;

class Maze{
    int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //4방향
    //BFS
    public int maze(int[][] map, int N, int M){
        Deque<int[]> queue = new ArrayDeque<int[]>();
        List<Integer> minList = new ArrayList<Integer>();
        //큐에 넣을때, {n, m, 현재까지 칸수} 정보 넣으면 됨
        queue.add(new int[]{0, 0, 0});
        while(!queue.isEmpty()){
            int[] current = queue.pollFirst(); //현재 좌표와 칸수
            if(current[0] < 0 || current[0] >= N || current[1] < 0 || current[1] >= M || map[current[0]][current[1]] != 1){
                //현재 좌표가 맵의 범위를 벗어나거나, 현재 좌표가 지나갈수 없는 칸일때
                continue;
            }
            
            else if(current[0] == N-1 && current[1] == M-1){ //현재 좌표가 목적지면 minList에 칸수 넣음
                minList.add(current[2]+1);
                map[current[0]][current[1]] = -1; //중복 방문하지 않도록 표기
                continue;

            }
            else{
                map[current[0]][current[1]] = -1; //중복 방문하지 않도록 표기
                for(int i = 0; i < 4; i++){
                    queue.add(new int[]{current[0]+direction[i][0], current[1]+direction[i][1], current[2]+1});
                }
            }
        }
        int result = Collections.min(minList);
        return result;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int n = 0; n < N; n++){
            String tempStr = br.readLine();
            for(int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(Character.toString(tempStr.charAt(m)));
            }
        }
        Maze maze = new Maze();
        int result = maze.maze(map, N, M);
        System.out.println(result);

    }
}
