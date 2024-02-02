import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Register{
        public String number; //현재 레지스터에 저장된 숫자(문자열 형태)
        public String operation; //현재까지 명령어 나열

        public Register(String number, String operation) {
            this.number = number;
            this.operation = operation;
        }
    }
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            String result = bfs(src, dst); //최단 명령 나열
            System.out.println(result);
        }
    }

    static String bfs(int src, int dst) {
        visited = new boolean[10000];
        Deque<Register> queue = new LinkedList<>();
        queue.add(new Register(padding(src), ""));
        visited[src] = true;
        while(!queue.isEmpty()){
            Register current = queue.pollFirst();
            String curNumberStr = current.number;
            String curOp = current.operation;
            int curNumber = Integer.parseInt(curNumberStr);

            //D
            int nextNumber = (curNumber * 2) % 10000;
            if(nextNumber == dst){
                return curOp + "D";
            }
            if(!visited[nextNumber]){
                queue.add(new Register(padding(nextNumber), curOp + "D"));
                visited[nextNumber] = true;
            }
            //S
            nextNumber = curNumber == 0 ? 9999 : curNumber - 1;
            if(nextNumber == dst){
                return curOp + "S";
            }
            if(!visited[nextNumber]){
                queue.add(new Register(padding(nextNumber), curOp + "S"));
                visited[nextNumber] = true;
            }
            //L
            String nextNumberStr = curNumberStr.substring(1, 4) + curNumberStr.substring(0, 1);
            nextNumber = Integer.parseInt(nextNumberStr);
            if(nextNumber == dst){
                return curOp + "L";
            }
            if(!visited[nextNumber]){
                queue.add(new Register(nextNumberStr, curOp + "L"));
            }
            //R
            nextNumberStr = curNumberStr.substring(3, 4) + curNumberStr.substring(0, 3);
            nextNumber = Integer.parseInt(nextNumberStr);
            if(nextNumber == dst){
                return curOp + "R";
            }
            if(!visited[nextNumber]){
                queue.add(new Register(nextNumberStr, curOp + "R"));
            }
        }
        return "";
    }

    static String padding(int number){
        String result = "";
        if(number < 10){ //한자리수인 경우
            result = "000" + Integer.toString(number);
        }
        else if(number < 100){ //두자리수인 경우
            result = "00" + Integer.toString(number);
        }
        else if(number < 1000){ //세자리수인 경우
            result = "0" + Integer.toString(number);
        }
        else{
            result = Integer.toString(number);
        }
        return result;
    }
}