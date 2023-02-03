import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();
        for(int n = 0; n < N; n++){
            words.add(br.readLine());
        }
        words.sort(new Comparator<String>() { //양수이면 더 뒤에오는 str
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2); //길이가 같으면 문자열 비교
                }
                else{
                    return s1.length() - s2.length();
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        String prevWord = "";
        for(String w: words){
            if(!w.equals(prevWord)){
                sb.append(w+"\n");
            }
            prevWord = w;
        }
        System.out.printf(sb.toString());
    }
}