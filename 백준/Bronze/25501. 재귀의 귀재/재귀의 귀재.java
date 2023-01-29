import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] recursion(String s, int l, int r){
        //반환 배열의 명세 : [팰린드롬 여부, 재귀 횟수]
        if(l >= r) return new int[] {1, 1};
        else if(s.charAt(l) != s.charAt(r)) return new int[] {0, 1};
        else{
            int[] result = recursion(s, l+1, r-1);
            return new int[] {result[0], result[1]+1}; //recursion 호출시마다 ++
        }
    }

    public static int[] isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            String inputStr = br.readLine();
            int[] result = isPalindrome(inputStr);
            System.out.printf("%d %d\n", result[0], result[1]);
        }
    }
}