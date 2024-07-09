import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            String[] phoneNum = new String[N];
            for(int n = 0; n < N; n++){
                phoneNum[n] = br.readLine();
            }
            Arrays.sort(phoneNum);
            if(isConsistent(N, phoneNum)) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    static boolean isConsistent(int N, String[] phoneNum){
        for(int n = 0; n < N-1; n++){
            //특정 문자열과 바로 다음 문자열간 접두어 관계만 파악하면 됨
            if(phoneNum[n+1].startsWith(phoneNum[n])) return false;
        }
        return true;
    }
}
