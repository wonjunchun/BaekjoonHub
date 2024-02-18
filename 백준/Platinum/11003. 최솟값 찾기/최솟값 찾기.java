import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Element{
        int value;
        int index;
        public Element(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Deque<Element> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int n = 0; n < N; n++){
            int element = Integer.parseInt(st.nextToken());
            //deque 뒤의 원소 중 현재 element보다 큰 값들 제거(deque가 항상 오름차순으로 존재할 수 있도록 함)
            while(!queue.isEmpty() && queue.peekLast().value > element) queue.pollLast();
            queue.add(new Element(element, n));
            if(queue.peekFirst().index <= n-L) queue.pollFirst(); //deque의 제일 오래된 원소 삭제
            sb.append(queue.peekFirst().value).append(" ");
        }
        System.out.println(sb.toString());
    }
}
