import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			String input = br.readLine();
			String[] inputArr = input.split("");
			Stack<String> stack1 = new Stack<>(); //완성문자열 최종적으로 들어갈곳
			Stack<String> stack2 = new Stack<>(); //최초로 문자열 들어가는곳
			Stack<String> stack3 = new Stack<>(); //방향키 이동시 임시로 문자열 저장하는곳
			
			//1. 일단 스택2에 입력문자열 역순으로 저장
			for(int i = inputArr.length-1; i >= 0; i--) {
				stack2.add(inputArr[i]);
			}
			
			//2. 스택2에서 하나씩 pop하면서 스택1에 넣어줌(스택2가 비지않았을동안)
			//"<" : 스택1의 top pop해서 스택3로 넣어줌(커서이동), 스택1 비어있다면 아무것도안함
			//">" : 스택3의 top pop해서 스택1로 넣어줌(커서이동)
			//"-" : 스택1의 top 제거(백스페이스), 스택1 비어있다면 아무것도안함
			//나머지 문자 : 스택2의 top pop해서 스택1으로(커서이동 ">" 과 비슷)
			while(!stack2.isEmpty()) {
				String op = stack2.pop();
				switch(op) {
				case "<": //앞으로 커서 이동
					if(!stack1.isEmpty()) {
						stack3.add(stack1.pop());
					}
					break;
				case "-": //백스페이스
					if(!stack1.isEmpty()) {
						stack1.pop();//제거
					}
					break;
				case ">"://뒤로 커서 이동
					if(!stack3.isEmpty()) {
						stack1.add(stack3.pop());
					}
					break;
				default: //일반 문자
					stack1.add(op);
					break;
				}
			}
			while(!stack3.isEmpty()) {
				stack1.add(stack3.pop());
			}
			//모든 과정 끝나면 스택1에만 문자열이 있음(역순으로)
			String[] resultArr = new String[stack1.size()];
			for(int i = stack1.size()-1; i >= 0; i--) {
				resultArr[i] = stack1.pop();
			}
			//하나의 문자열로 합쳐줌
			System.out.println(String.join("", resultArr));
		}
	}
}