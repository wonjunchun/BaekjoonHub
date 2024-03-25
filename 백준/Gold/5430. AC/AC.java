import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            String[] ops = br.readLine().split("");
            int len = Integer.parseInt(br.readLine());
            String input = br.readLine();
            int[] array;
            if(input.length() <= 2) array = new int[0];
            else array = Stream.of(input.substring(1, input.length()-1).split(",")).mapToInt(Integer::parseInt)
                    .toArray();
            boolean isFront = true;
            boolean isErrorOccured = false;
            Deque<Integer> deque = new LinkedList<>();
            for(int num: array) deque.add(num);
            //함수 수행
            OPERATION: for(String op: ops){
                switch(op){
                    case "R":
                        isFront = !isFront;
                        break;
                    case "D":
                        if(deque.isEmpty()){ //deque 비었는데 삭제하면 에러 발생
                            isErrorOccured = true;
                            break OPERATION;
                        } else{
                            if(isFront){
                                deque.pollFirst();
                            } else{ //반대방향
                                deque.pollLast();
                            }
                        }
                        break;
                }
            }
            //결과 출력
            if(isErrorOccured) sb.append("error").append("\n");
            else{
                sb.append("[");
                if(!deque.isEmpty()){
                    if(isFront){
                        while(deque.size() > 1){
                            sb.append(deque.pollFirst()).append(",");
                        }
                        sb.append(deque.pollFirst());
                    }
                    else{
                        while(deque.size() > 1){
                            sb.append(deque.pollLast()).append(",");
                        }
                        sb.append(deque.pollLast());
                    }
                }
                sb.append("]").append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
