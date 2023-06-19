import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS(Least Common Subsequence)최장공통부분수열
 * 두 수열 주어졌을때 모두의 부분수열 되는 수열 중 가장 긴 것
 * ACAYKP, CAPCAK => ACAK
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int str1Len = str1.length();
        int str2Len = str2.length();
        int[][] LCS = new int[str1Len+1][str2Len+1];
        for(int i = 1; i <= str1Len; i++){
            for(int j = 1; j <= str2Len; j++){
                //만약 두 문자가 다르면
                if(str1.charAt(i-1) != str2.charAt(j-1)){
                    //좌측, 위측의 값 중 더 큰값 채택
                    LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
                }
                //두 문자가 같다면
                else{
                    //이전 부분 수열에서 현재 문자 추가했으므로 길이 1 증가
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                }
            }
        }
        System.out.println(LCS[str1Len][str2Len]);
    }
}