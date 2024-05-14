import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Point{
        int X, time; //현재좌표, 이동시간

        public Point(int X, int time) {
            this.X = X;
            this.time = time;
        }
    }
    static int N, K;
    static int minTime = Integer.MAX_VALUE;
    static int count = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bfs();
        System.out.println(minTime);
        System.out.println(count);
    }
    static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.add(new Point(N, 0));

        while(!queue.isEmpty()){
            Point current = queue.poll();
            int curPoint = current.X;
            int curTime = current.time;
            visited[curPoint] = true; //방문 처리는 큐에서 꺼낼때 함

            if(curPoint == K){ //목적지 도달 시 최단시간 갱신
                minTime = curTime;
                count++;
            }
            if(visited[K] && minTime < curTime){ //이미 목적지 방문했고, 현재 시간이 minTime을 넘었다면 종료
                return;
            }

            int[] nextPoints = {curPoint - 1, curPoint + 1, curPoint * 2};
            for(int next: nextPoints){
                if(next >= 0 && next <= 100000 && !visited[next]){
                    queue.add(new Point(next, curTime + 1));
                }
            }
        }
    }
}