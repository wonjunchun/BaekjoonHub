import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        String pattern = br.readLine();
        KMP(inputStr, pattern);
    }

    private static void KMP(String inputStr, String pattern) {
        int[] table = makeTable(pattern);
        int inputLen = inputStr.length();
        int patternLen = pattern.length();
        int idx = 0; //현재 대응되는 글자수
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < inputLen; i++){
            //패턴의 idx번째 글자와 입력문자열의 해당(i) 글자 불일치 시, 현재 대응된 글자수를 table[idx-1]번으로 줄임
            while(idx > 0 && inputStr.charAt(i) != pattern.charAt(idx)){
                idx = table[idx - 1];
            }
            //글자가 대응될 경우
            if(inputStr.charAt(i) == pattern.charAt(idx)){
                if(idx == patternLen-1){ //패턴 문자열 전부 나타났다면 나타난 위치 출력 및 나타난 횟수 갱신
                    sb.append((i-idx+1)+" ");
                    cnt++;
                    idx = table[idx];
                }
                else{
                    idx += 1;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static int[] makeTable(String pattern) {
        //table[i] : 0~i까지 접두사도 되고 접미사도 되는 문자열의 최대 길이 저장
        int patternLen = pattern.length();
        int[] table = new int[patternLen];
        int idx = 0;
        for(int i = 1; i < patternLen; i++){
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)){
                idx = table[idx-1]; //idx 갱신
            }
            if(pattern.charAt(i) == pattern.charAt(idx)){ //접두사와 접미사의 문자가 같으면
                table[i] = ++idx; //지금까지 접두사도되고 접미사도 되는 문자열 최대 길이 저장
            }
        }
        return table;
    }
}
