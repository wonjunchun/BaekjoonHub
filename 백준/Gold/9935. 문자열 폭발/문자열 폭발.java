import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        String inputStr = br.readLine();
        String subStr = br.readLine();
        if(subStr.length() > inputStr.length()){
            //서브문자열이 본문자열보다 길면 폭발할 일 없음
            System.out.println(inputStr);
        }
        else{
            for(int i = 0; i < inputStr.length(); i++){
                stack.add(inputStr.charAt(i));
                //현재 문자가 서브문자열의 마지막문자와 같다면, 스택에 들어간 문자들 역순으로 폭발문자열 검사
                if(stack.size() >= subStr.length() && stack.peek() == subStr.charAt(subStr.length()-1)){
                    boolean isSub = true;
                    for(int j = 0; j < subStr.length(); j++){
                        if(stack.get(stack.size()-subStr.length()+j) != subStr.charAt(j)){
                            isSub = false;
                            break;
                        }
                    }
                    if(isSub){ //문자열 폭발 시
                        for(int j = 0; j < subStr.length(); j++){
                            stack.pop(); //스택에서 제거
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(char c: stack){ //스택의 문자열 sb에 누적
                sb.append(c);
            }
            if(sb.length() == 0){
                System.out.println("FRULA");
            }
            else{
                System.out.println(sb.toString());
            }
        }
    }
}