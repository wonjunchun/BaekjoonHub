import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] numlist;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;
            numlist = new int[k];
            selected = new int[6];
            for(int i = 0; i < k; i++) {
                numlist[i] = Integer.parseInt(st.nextToken());
            }
            lotto(0, 0);
            System.out.println();
        }
    }
    public static void lotto(int cnt, int start){
        if(cnt == 6){
            for(int s: selected){
                System.out.print(s+" ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i < k; i++){
            selected[cnt] = numlist[i];
            lotto(cnt+1, i+1);
        }
    }
}