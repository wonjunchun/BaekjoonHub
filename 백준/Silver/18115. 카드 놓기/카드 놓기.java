import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] skills = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> result = new LinkedList<>(); //카드 뽑은 결과(맨앞이 맨위 카드)
        Deque<Integer> origin = new LinkedList<>(); //원래 카드 순서
        for(int i = 1; i <= N; i++){
            result.add(i);
        }
        for(int i = N-1; i >= 0; i--){
            switch(skills[i]){
                case 1: //제일 위 카드 1장 바닥에 놓는 경우
                    //역순으로 결과 카드의 제일 위 카드를 원래 카드 제일 위에 넣어줌
                    origin.addFirst(result.pollFirst());
                    break;
                case 2: //위에서 두번째 카드 바닥에 놓는 경우
                    //origin의 맨 위 카드 우선 제거 후 result의 맨위 카드 넣고, 다시 origin의 맨위카드 맨위에 넣음
                    int originTop = origin.removeFirst();
                    origin.addFirst(result.pollFirst());
                    origin.addFirst(originTop);
                    break;
                case 3: //제일 밑에 있는 카드 바닥에 놓는 경우
                    //origin의 맨 아래에 카드 넣음
                    origin.addLast(result.pollFirst());
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!origin.isEmpty()){
            sb.append(origin.pollFirst()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
