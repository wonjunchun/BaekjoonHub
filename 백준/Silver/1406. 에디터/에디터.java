import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>(); //커서의 왼쪽 문자 저장하는 스택
        for(int n = 0; n < str.length(); n++){
            stack.push(str.charAt(n));
        }
        int M = Integer.parseInt(br.readLine());
        Stack<Character> stack2 = new Stack<>(); //커서 오른쪽의 문자 잠시 보관하는 스택
        StringTokenizer st;
        for(int m = 0; m < M; m++){ //명령어 M줄 수행
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);
            switch (op){
                case 'L': //커서 왼쪽 이동
                    if(!stack.isEmpty()){
                        stack2.push(stack.pop());
                    }
                    break;
                case 'D': //커서 오른쪽 이동
                    if(!stack2.isEmpty()){
                        stack.push(stack2.pop());
                    }
                    break;
                case 'B': //커서 왼쪽 문자 삭제
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                    break;
                case 'P': //새 문자 커서 왼쪽에 추가
                    stack.push(st.nextToken().charAt(0));
                    break;
            }
        }
        //명령어 끝나고, stack2의 문자들 stack으로 옮김
        while(!stack2.isEmpty()){
            stack.push(stack2.pop());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < stack.size(); i++){
            sb.append(stack.get(i));
        }
        System.out.println(sb.toString());
    }
}
