import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node>{
        int startTime, endTime;

        public Node(){}
        public Node(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Node o) { //시작시간 오름차순 우선, 종료시간 오름차순 후순위
            if(this.startTime == o.startTime) return this.endTime - o.endTime;
            return this.startTime - o.startTime;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] classes = new Node[N];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classes[n] = new Node();
            classes[n].startTime = start;
            classes[n].endTime = end;
        }
        Arrays.sort(classes); //정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //종료시간을 담아 다음 강의의 시작시간과 비교 목적의 pq
        pq.add(classes[0].endTime);
        for(int n = 1; n < N; n++){
            //pq의 최상위원소(종료시간)가 현재 startTime보다 먼저 끝난다면, pq에서 제거
            //이후 현재 classes의 endTime pq에 추가
            //현재 강의실에 수용 가능한 강의면 기존 종료시간 제거하고 현재 강의 종료시간 추가
            //아니면 현재 강의 종료시간 추가만 함(새 강의실)
            //pq의 원소 개수만큼 강의실 잡음
            if(pq.peek() <= classes[n].startTime) pq.poll();
            pq.add(classes[n].endTime);
        }
        System.out.println(pq.size());
    }
}
