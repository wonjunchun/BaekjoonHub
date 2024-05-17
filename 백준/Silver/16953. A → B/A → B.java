import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Point{
        long pos, count;

        public Point(long pos, long count) {
            this.pos = pos;
            this.count = count;
        }
    }
    static int A, B;
    static long min = -1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        bfs();
        System.out.println(min);
    }
    static void bfs(){
        boolean[] visited = new boolean[1000000001];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(A, 0));
        visited[A] = true;
        Q : while(!queue.isEmpty()){
            Point current = queue.poll();
            long curPos = current.pos;
            long curCount = current.count;

            long[] nextPoint = {curPos * 2, curPos * 10 + 1};
            for(long next:nextPoint){
                if(next == B){ //목적지 도달하면, 필요 연산 최솟값 갱신
                    min = curCount + 2;
                    break Q;
                }
                if(next < 0 || next > 1000000000) continue;
                if(visited[(int)next]) continue;
                queue.add(new Point(next, curCount + 1));
                visited[(int)next] = true;
            }
        }
    }
}
