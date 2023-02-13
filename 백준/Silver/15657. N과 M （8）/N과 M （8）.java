import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] numlist;
    static int[] selected;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numlist = new int[N];
        selected = new int[M];
        st = new StringTokenizer(sc.nextLine());
        for(int n = 0; n < N; n++){
            numlist[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numlist);
        find(0, 0);
    }
    public static void find(int cnt, int start){
        if(cnt == M){
            for(int s : selected){
                System.out.print(s+" ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i < N; i++){
            selected[cnt] = numlist[i];
            find(cnt+1, i);
        }
    }
}
