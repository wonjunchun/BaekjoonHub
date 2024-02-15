import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < input.length; i++){
            if(input[i] >= 'A' && input[i] <= 'Z'){ //문자열(피연산자)이면, 문자열에 넣음
                sb.append(input[i]);
            }
            else{
                switch(input[i]){
                    case '(':
                        stack.push(input[i]);
                        break;
                    case ')':
                        while(!stack.isEmpty() && stack.peek() != '('){
                            sb.append(stack.pop());
                        }
                        if(!stack.isEmpty()) stack.pop(); //'('연산자 pop
                        break;
                    default: //나머지 사칙연산인 경우
                        //스택에 있는 연산자 우선순위가 현재 연산자의 우선순위보다 높으면 다 pop시킴
                        while(!stack.isEmpty() && priority(stack.peek()) >= priority(input[i])){
                            sb.append(stack.pop());
                        }
                        stack.push(input[i]);
                }
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
    static int priority(char inputChar){ //연산자 우선순위
        if(inputChar == '*' || inputChar == '/') return 2; //우선순위 높음
        else if (inputChar == '+' || inputChar == '-') return 1;
        else return 0;
    }
}
