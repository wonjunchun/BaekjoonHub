import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> passwords = new HashMap<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            String url = st.nextToken();
            String password = st.nextToken();
            passwords.put(url, password);
        }
        StringBuilder sb = new StringBuilder();
        for(int m = 0; m < M; m++){
            String url = br.readLine();
            sb.append(passwords.get(url)).append("\n");
        }
        System.out.println(sb.toString());
    }
}
