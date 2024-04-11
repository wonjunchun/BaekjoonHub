import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    static int N;
    static List<Integer>[] adjList;
    static int[] constructTime;
    static int[] degree; //진입차수
    static boolean[] visited;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        constructTime = new int[N + 1];
        degree = new int[N + 1];
        result = new int[N + 1];
        visited = new boolean[N + 1];
        for(int n = 1; n <= N; n++){
            adjList[n] = new LinkedList<>();
        }
        for(int n = 1; n <= N; n++){
            int[] inputArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            constructTime[n] = inputArr[0]; //건설시간
            for(int i = 1; i < inputArr.length-1; i++){
                adjList[inputArr[i]].add(n); //먼저 지어야하는 건물의 다음 건물로 n 추가
            }
            degree[n] = inputArr.length-2; //진입차수
        }
        //위상정렬
        Deque<Integer> queue = new LinkedList<>();
        for(int n = 1; n <= N; n++){
            if(degree[n] == 0){ //진입차수 0인 애들 큐에 넣음
                queue.add(n);
                result[n] = constructTime[n];
            }
        }
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            for(int next: adjList[current]){ //현재 건물의 다음 원소들
                if(visited[next]) continue;
                //이전 건물중 가장 오래 걸리는 건물 시간 + next 시간
                result[next] = Math.max(result[next], result[current] + constructTime[next]);
                if(--degree[next] == 0){ //진입차수 0이 됐다면
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int n = 1; n <= N; n++){
            sb.append(result[n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
