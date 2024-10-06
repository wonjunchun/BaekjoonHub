import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
    static class Tower{
        int index;
        int height;

        public Tower(int index, int height) {
            this.height = height;
            this.index = index;
        }
    }
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Tower> stack = new Stack<>();
        for(int n = 1; n <= N; n++){
            int curHeight = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()){
                if(curHeight < stack.peek().height){ //수신탑 발견했다면
                    System.out.print(stack.peek().index + " ");
                    break;
                }
                stack.pop(); //수신탑 발견할 때까지 pop
            }
            if(stack.isEmpty()){ //수신탑이 없으면
                System.out.print(0 + " ");
            }
            stack.push(new Tower(n, curHeight)); //현재 높이를 스택에 저장
        }
    }
}