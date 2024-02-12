import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Point[] points; //좌표들. 0: 상근이 집, N+1: 페스티벌, 나머지 편의점
    static LinkedList<Integer>[] adjList; //인접리스트
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            points = new Point[N + 2];
            adjList = new LinkedList[N + 2];
            for (int n = 0; n < N + 2; n++) { //초기화
                points[n] = new Point(0, 0);
                adjList[n] = new LinkedList<>();
            }
            StringTokenizer st;
            for (int n = 0; n < N + 2; n++) {
                st = new StringTokenizer(br.readLine());
                points[n].x = Integer.parseInt(st.nextToken());
                points[n].y = Integer.parseInt(st.nextToken());
            }
            for(int i = 0; i < N+2; i++){
                for(int j = i+1; j < N+2; j++){
                    if(distance(points[i], points[j]) <= 1000){ //두 점간 거리가 1000m 이내면 이동가능
                        //인접리스트에 두 점 연결관계 저장
                        adjList[i].add(j);
                        adjList[j].add(i);
                    }
                }
            }
            String result = bfs() ? "happy" : "sad";
            System.out.println(result);
        }
    }

    static boolean bfs() {
        visited = new boolean[N + 2];
        Deque<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int current = queue.pollFirst();

            for(int next: adjList[current]){ //현재 노드와 연결된 노드들 탐색
                if (next == N + 1) { //만약 페스티벌 도달 했다면
                    return true;
                }
                if(!visited[next]){ //아직 방문하지 않은 곳이면
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        return false; //큐 전부 돌 동안 페스티벌 도달 못했다면 false
    }

    static int distance(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
