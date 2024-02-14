import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int set = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        for(int m = 0; m < M; m++){
            String inputLine = br.readLine();
            operation(inputLine);
        }
        System.out.println(sb.toString());
    }
    static void operation(String input){
        StringTokenizer st = new StringTokenizer(input);
        String operator = st.nextToken();
        int operand;
        switch(operator){
            case "add":
                operand = Integer.parseInt(st.nextToken());
                set = set | (1 << operand);
                break;
            case "remove":
                operand = Integer.parseInt(st.nextToken());
                set = set & ~(1 << operand);
                break;
            case "check":
                operand = Integer.parseInt(st.nextToken());
                if((set & (1 << operand)) != 0){ //해당 원소 존재하면
//                    System.out.println(1);
                    sb.append(1).append("\n");
                }
                else{ //원소 없는 경우
//                    System.out.println(0);
                    sb.append(0).append("\n");
                }
                break;
            case "toggle":
                operand = Integer.parseInt(st.nextToken());
                set = set ^ (1 << operand);
                break;
            case "all":
                set = ~0;
                break;
            case "empty":
                set = 0;
                break;
        }
    }
}
