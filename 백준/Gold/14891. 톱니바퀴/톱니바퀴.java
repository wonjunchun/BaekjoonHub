import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static LinkedList<Integer>[] gears;
    static int[][] rotates;
    static int K;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new LinkedList[5];
        for(int i = 1; i <= 4; i++){
            gears[i] = new LinkedList<>();
            int[] gear = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < 8; j++){
                gears[i].add(gear[j]);
            }
        }
        K = Integer.parseInt(br.readLine());
        rotates = new int[K][2];
        StringTokenizer st;
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            rotates[k][0] = Integer.parseInt(st.nextToken());
            rotates[k][1] = Integer.parseInt(st.nextToken());
        }
        simulation(); //톱니바퀴 회전시킴
        int result = calculate(); //톱니바퀴 점수 합 계산
        System.out.println(result);
    }

    static int calculate() {
        int sum = 0;
        for(int i = 1; i <= 4; i++){
            sum += (gears[i].get(0) << (i - 1));
        }
        return sum;
    }

    static void simulation() {
        for(int k = 0; k < K; k++){
            visited = new boolean[5];
            visited[rotates[k][0]] = true;
            rotate(rotates[k][0], rotates[k][1]); //회전시킴(맞닿은 바퀴도 회전시킴)
        }
    }

    static void rotate(int gear, int direction) {
        //i) 좌측 우측 톱니바퀴 확인 후, 회전 가능한 상태면 rotate 재귀호출
        //좌측에 톱니바퀴가 존재하고, 현재 톱니바퀴와 맞닿은 면이 다른극이면
        if(gear > 1 && gears[gear-1].get(2) != gears[gear].get(6) && !visited[gear-1]){
            visited[gear-1] = true;
            rotate(gear-1, direction*(-1));
        }
        //우측도 마찬가지
        if(gear < 4 && gears[gear+1].get(6) != gears[gear].get(2) && !visited[gear+1]){
            visited[gear+1] = true;
            rotate(gear + 1, direction * (-1));
        }
        //ii) 현재 톱니바퀴 회전시킴
        if(direction > 0){ //시계방향 회전 시, 맨끝 원소를 맨앞에 붙임
            int temp = gears[gear].removeLast();
            gears[gear].addFirst(temp);
        }
        else if(direction < 0){
            int temp = gears[gear].removeFirst();
            gears[gear].addLast(temp);
        }
    }
}
