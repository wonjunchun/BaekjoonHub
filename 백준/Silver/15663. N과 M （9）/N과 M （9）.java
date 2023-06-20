import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] num, perm;
    static boolean[] selected;
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
        selected = new boolean[N];
        perm = new int[M];
        permutation(0);
    }

    private static void permutation(int cnt) {
        if(cnt == M){
            for(int p: perm){
                System.out.print(p+" ");
            }
            System.out.println();
            return;
        }
        //중복되는 숫자 선택 가지치기함
        int prev = -1; //이전에 선택한값
        for(int i = 0; i < N; i++){
            if(!selected[i] && prev != num[i]){ //아직 선택안했고, 이전에 선택한값과 다르다면
                prev = num[i];
                selected[i] = true; //선택 처리
                perm[cnt] = num[i];
                permutation(cnt+1);
                selected[i] = false;
            }
        }
    }
}