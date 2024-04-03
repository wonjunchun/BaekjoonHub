import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> stack = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            switch(operation){
                case 1:
                    stack.add(Integer.parseInt(st.nextToken()));
                    break;
                case 2:
                    if(stack.isEmpty()){
                        sb.append(-1).append("\n");
                    }
                    else{
                        sb.append(stack.pollLast()).append("\n");
                    }
                    break;
                case 3:
                    sb.append(stack.size()).append("\n");
                    break;
                case 4:
                    if(stack.isEmpty()){
                        sb.append(1).append("\n");
                    }
                    else{
                        sb.append(0).append("\n");
                    }
                    break;
                case 5:
                    if(stack.isEmpty()){
                        sb.append(-1).append("\n");
                    }
                    else{
                        sb.append(stack.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}
