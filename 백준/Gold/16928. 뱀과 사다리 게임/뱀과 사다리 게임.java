import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int x, diceCnt;

        public Node(int x, int diceCnt) {
            this.x = x;
            this.diceCnt = diceCnt;
        }
    }
    static int N, M; //N: 사다리수, M: 뱀 수
    static Map<Integer, Integer> ladder;
    static Map<Integer, Integer> snake;
    static int minCount = Integer.MAX_VALUE;
    static int[] directions = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            ladder.put(src, dst);
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            snake.put(src, dst);
        }
        bfs();
        System.out.println(minCount);
    }
    static void bfs(){
        boolean[] visited = new boolean[101];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));
        visited[1] = true;
        while(!queue.isEmpty()){
            Node current = queue.poll();
            int curPoint = current.x;
            int curDiceCnt = current.diceCnt;

            for(int d: directions){
                int nextPoint = curPoint + d;
                if(nextPoint < 1 || nextPoint > 100 || visited[nextPoint]) continue;
                if(nextPoint == 100){
                    minCount = Math.min(minCount, curDiceCnt + 1);
                    return;
                }
                if(ladder.containsKey(nextPoint)){
                    queue.add(new Node(ladder.get(nextPoint), curDiceCnt + 1));
                    visited[nextPoint] = true;
                    visited[ladder.get(nextPoint)] = true;
                }
                else if(snake.containsKey(nextPoint)){
                    queue.add(new Node(snake.get(nextPoint), curDiceCnt + 1));
                    visited[nextPoint] = true;
                    visited[snake.get(nextPoint)] = true;
                }
                else{
                    queue.add(new Node(nextPoint, curDiceCnt + 1));
                    visited[nextPoint] = true;
                }
            }
        }
    }
}
