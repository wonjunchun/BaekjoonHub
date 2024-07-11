import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        double current = 0, next;
        //뉴턴-랩슨법 사용
        //Xn+1 = Xn - (f(xn) / f'(xn))
        //근사 해가 10^-9의 오차 가질때까지 반복
        while(Math.abs(A*current+B*Math.sin(current)-C) > 0.000000001){
            next = current - (A * current + B * Math.sin(current) - C) / (A + B * Math.cos(current));
            current = next;
        }
        System.out.println(current);
    }
}
