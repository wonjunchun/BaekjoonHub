import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] graph; //각 노드간 관계
    static int[] degree; //진입차수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new LinkedList[N+1]; //각 노드간 관계 저장
        degree = new int[N+1];

        for(int n = 1; n <= N; n++){
            graph[n] = new LinkedList<Integer>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
//            List<Integer> suborder = new LinkedList<>();
            int singers = Integer.parseInt(st.nextToken()); //보조 PD가 담당한 가수의 수
            if(singers == 0) continue;
            int start = Integer.parseInt(st.nextToken());
            int end = start;
            while(st.hasMoreTokens()){
//                suborder.add(Integer.parseInt(st.nextToken()));
                end = Integer.parseInt(st.nextToken());
                graph[start].add(end); //연결관계 추가
                start = end;
                degree[end] += 1; //end의 진입차수 +1
            }
        }

        //위상정렬
        //큐에 들어간 순서대로
        Deque<Integer> queue = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        for(int n = 1; n <= N; n++){
            if(degree[n] == 0){ //진입차수가 0이면 큐에 넣음
                queue.add(n);
            }
        }
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            result.add(current);

            for(int n: graph[current]){ //현재 노드의 다음에 오는 노드들 탐색
                degree[n] -= 1; //current 노드 제거했으므로 진입차수 감소
                if(degree[n] == 0){
                    queue.add(n);
                }
            }
        }

        if(result.size() != N){ //위상 정렬 안되는 경우
            System.out.println(0);
        } else{
            for(int r: result) System.out.println(r);
        }
    }
}
