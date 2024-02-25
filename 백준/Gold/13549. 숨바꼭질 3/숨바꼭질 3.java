import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N, K;
    static boolean[] visited = new boolean[200001];
    static int[] directions = {-1, 1};
    static class Point implements Comparable<Point>{
        int X;
        int time;

        public Point(int x, int time) {
            X = x;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return this.time - o.time;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        int time = dijkstra();
        System.out.println(time);
    }
    static int dijkstra(){
        int[] distance = new int[200001];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(N, 0));
        distance[N] = 0;
        int time = 0;

        Q: while(!pq.isEmpty()){
            Point current = pq.poll();
            int curX = current.X;
            int curTime = current.time;

            if(distance[curX] < curTime) continue;
            if(visited[curX]) continue;
            visited[curX] = true;

            //X-1, X+1
            for(int d: directions){
                int nextX = curX + d;
                if(nextX < 0 || nextX > 200000) continue;
                if(!visited[nextX]){
                    if(distance[nextX] > curTime+1){
                        distance[nextX] = curTime+1;
                        pq.add(new Point(nextX, distance[nextX]));
                    }
                }
            }
            int nextX = curX * 2;

            if(nextX < 0 || nextX > 200000) continue;
            if(!visited[nextX]){
                if(distance[nextX] > curTime){
                    distance[nextX] = curTime;
                    pq.add(new Point(nextX, distance[nextX]));
                }
            }
        }
        return distance[K];
    }
}
