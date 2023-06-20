import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] num, comb;
    static int N, M;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        num = new int[N];
        for(int n = 0; n < N; n++){
            num[n] = sc.nextInt();
        }
        Arrays.sort(num); //정렬

        comb = new int[M];
        combination(0, 0);
    }

    private static void combination(int cnt, int start) {
        if(cnt == M){
            for(int c: comb){
                System.out.print(c+" ");
            }
            System.out.println();
            return;
        }
        int prev = -1; //중복 수열 나오지 않도록 선택 가지치기용
        for(int i = start; i < N; i++){
            if(prev != num[i]){
                prev = num[i];
                comb[cnt] = num[i];
                combination(cnt+1, i);
            }
        }
    }
}
